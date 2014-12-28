package com.hp.gps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.hp.common.HttpHelper;
import com.hp.domain.RoadManagement;
import com.hp.map.ProfileActivity;
import com.hp.rest.CheckingInternet;
import com.hp.rest.Rest;
import com.sun.jersey.api.client.ClientResponse;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * BackgroundLocationService used for tracking user location in the background.
 * 
 * @author cblack
 */
public class BackgroundLocationService extends Service implements
		GooglePlayServicesClient.ConnectionCallbacks,
		GooglePlayServicesClient.OnConnectionFailedListener, LocationListener,
		com.google.android.gms.location.LocationListener {

	public static Location CURRENT_LOCATION;

	IBinder mBinder = new LocalBinder();

	private LocationClient mLocationClient;
	private LocationRequest mLocationRequest;
	// Flag that indicates if a request is underway.
	private boolean mInProgress;

	private Boolean servicesAvailable = false;

	public class LocalBinder extends Binder {
		public BackgroundLocationService getServerInstance() {
			return BackgroundLocationService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();

		mInProgress = false;
		// Create the LocationRequest object
		mLocationRequest = LocationRequest.create();
		// Use high accuracy
		mLocationRequest
				.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
		// Set the update interval to 5 seconds
		mLocationRequest.setInterval(Constants.UPDATE_INTERVAL);
		// Set the fastest update interval to 1 second
		mLocationRequest.setFastestInterval(Constants.FASTEST_INTERVAL);
		// With distance
		mLocationRequest.setSmallestDisplacement(Constants.MIN_DISTANCE);

		servicesAvailable = servicesConnected();

		/*
		 * Create a new location client, using the enclosing class to handle
		 * callbacks.
		 */
		mLocationClient = new LocationClient(this, this, this);

	}

	private boolean servicesConnected() {

		// Check that Google Play services is available
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(this);

		// If Google Play services is available
		if (ConnectionResult.SUCCESS == resultCode) {

			return true;
		} else {

			return false;
		}
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);

		if (!servicesAvailable || mLocationClient.isConnected() || mInProgress)
			return START_STICKY;

		setUpLocationClientIfNeeded();
		if (!mLocationClient.isConnected() || !mLocationClient.isConnecting()
				&& !mInProgress) {
			appendLog(DateFormat.getDateTimeInstance().format(new Date())
					+ ": Started", Constants.LOG_FILE);
			mInProgress = true;
			mLocationClient.connect();
		}

		return START_STICKY;
	}

	/*
	 * Create a new location client, using the enclosing class to handle
	 * callbacks.
	 */
	private void setUpLocationClientIfNeeded() {
		if (mLocationClient == null)
			mLocationClient = new LocationClient(this, this, this);
	}

	// Define the callback method that receives location updates
	@Override
	public void onLocationChanged(Location location) {
		// Report to the UI that the location was updated
		CURRENT_LOCATION = location;

		String address = ProfileActivity.getAddress(location);

		pụtJourney((float) location.getLatitude(),
				(float) location.getLongitude(), address);

		String msg = Double.toString(location.getLatitude()) + ","
				+ Double.toString(location.getLongitude());
		Log.d("debug____", msg);
		// Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		appendLog(msg, Constants.LOCATION_FILE);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public String getTime() {
		SimpleDateFormat mDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return mDateFormat.format(new Date());
	}

	public void appendLog(String text, String filename) {
		File logFile = new File(filename);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			// BufferedWriter for performance, true to set append to file flag
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile,
					true));
			buf.append(text);
			buf.newLine();
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onDestroy() {
		// Turn off the request flag
		mInProgress = false;
		if (servicesAvailable && mLocationClient != null) {
			mLocationClient.removeLocationUpdates(this);
			// Destroy the current location client
			mLocationClient = null;
		}
		// Display the connection status
		// Toast.makeText(this, DateFormat.getDateTimeInstance().format(new
		// Date()) + ": Disconnected. Please re-connect.",
		// Toast.LENGTH_SHORT).show();
		appendLog(DateFormat.getDateTimeInstance().format(new Date())
				+ ": Stopped", Constants.LOG_FILE);
		super.onDestroy();
	}

	/*
	 * Called by Location Services when the request to connect the client
	 * finishes successfully. At this point, you can request the current
	 * location or start periodic updates
	 */
	@Override
	public void onConnected(Bundle bundle) {

		// Request location updates using static settings
		mLocationClient.requestLocationUpdates(mLocationRequest, this);
		appendLog(DateFormat.getDateTimeInstance().format(new Date())
				+ ": Connected", Constants.LOG_FILE);

	}

	/*
	 * Called by Location Services if the connection to the location client
	 * drops because of an error.
	 */
	@Override
	public void onDisconnected() {
		// Turn off the request flag
		mInProgress = false;
		// Destroy the current location client
		mLocationClient = null;
		// Display the connection status
		// Toast.makeText(this, DateFormat.getDateTimeInstance().format(new
		// Date()) + ": Disconnected. Please re-connect.",
		// Toast.LENGTH_SHORT).show();
		appendLog(DateFormat.getDateTimeInstance().format(new Date())
				+ ": Disconnected", Constants.LOG_FILE);
	}

	/*
	 * Called by Location Services if the attempt to Location Services fails.
	 */
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		mInProgress = false;

		/*
		 * Google Play services can resolve some errors it detects. If the error
		 * has a resolution, try sending an Intent to start a Google Play
		 * services activity that can resolve error.
		 */
		if (connectionResult.hasResolution()) {

			// If no resolution is available, display an error dialog
		} else {

		}
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

	@SuppressLint("SimpleDateFormat")
	public void pụtJourney(float pX, float pY, String address) {
		if (pX == 0 && pY == 0)
			return;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String datestr = dateFormat.format(date);
		RoadManagement roadManagement = new RoadManagement(Rest.mStaff.getId(),
				Rest.mStaff.getName(), Timestamp.valueOf(datestr), pX, pY,
				address);

		ObjectMapper mapper = new ObjectMapper();
		String objectStr = new String();

		try {

			objectStr = mapper.writeValueAsString(roadManagement);

		} catch (JsonGenerationException ex) {

			ex.printStackTrace();

		} catch (JsonMappingException ex) {

			ex.printStackTrace();

		} catch (IOException ex) {

			ex.printStackTrace();

		}

		// Order ---------------------------------------------------------------
		ClientResponse response = Rest.mService.path("webresources")
				.path("putStaffJourney").accept("application/json")
				.type("application/json").post(ClientResponse.class, objectStr);

		String output = response.toString();
		System.out.println("input 1: " + output);

		if ((response.getStatus() == 200)
				&& (response.getEntity(String.class).compareTo("true") == 0)) {
			// Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
			// refresh customers
			System.out.println("Đã gửi");

		} else
			System.out.println("Không thể gửi");
		// Toast.makeText(context, "Không thể gửi, hãy xem lại kết nối",
		// Toast.LENGTH_SHORT).show();

		System.out.println("Server response .... \n");
		System.out.println("input 0: " + output);
	}
}
