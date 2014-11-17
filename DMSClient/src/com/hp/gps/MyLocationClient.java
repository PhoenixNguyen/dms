package com.hp.gps;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.hp.map.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationClient extends Activity  implements 
	GooglePlayServicesClient.ConnectionCallbacks,
	GooglePlayServicesClient.OnConnectionFailedListener, 
	LocationListener {

	Context context =this;
	// locations objects
	public static LocationClient mLocationClient;
	public static Location mCurrentLocation;
    LocationRequest mLocationRequest;
    
	TextView txtLong,txtLat;
	
	
	public MyLocationClient() {

		// 3. create LocationClient
		mLocationClient = new LocationClient(this, this, this);
				
		// 4. create & set LocationRequest for Location update
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        // Set the update interval to 5 seconds
        mLocationRequest.setInterval(1000 * 5);
        // Set the fastest update interval to 1 second
        mLocationRequest.setFastestInterval(1000 * 1);

		
	}
	public void onStart() {
        //super.onStart();
        // 1. connect the client.
        mLocationClient.connect();
    }
	public void onStop() {
        //super.onStop();
        // 1. disconnecting the client invalidates it.
        mLocationClient.disconnect();
    }
	 
	// GooglePlayServicesClient.OnConnectionFailedListener
	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
        //Toast.makeText(this, "Connection Failed", Toast.LENGTH_SHORT).show();
    }

	// GooglePlayServicesClient.ConnectionCallbacks 
	@Override
	public void onConnected(Bundle arg0) {
		
		if(mLocationClient != null)
			mLocationClient.requestLocationUpdates(mLocationRequest,  this);

       // Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
		
        if(mLocationClient != null){
        	// get location
        	mCurrentLocation = mLocationClient.getLastLocation();
        		/*try{
        			
        			// set TextView(s) 
        			txtLat.setText(mCurrentLocation.getLatitude()+"");
        			txtLong.setText(mCurrentLocation.getLongitude()+"");
        			
        		}catch(NullPointerException npe){
        			
        		}*/
        }

	}
	@Override
	public void onDisconnected() {
		//Toast.makeText(this, "Disconnected.", Toast.LENGTH_SHORT).show();
	
	}

	// LocationListener
	@Override
	public void onLocationChanged(Location location) {
		//Toast.makeText(this, "Location changed.", Toast.LENGTH_SHORT).show();
		mCurrentLocation = mLocationClient.getLastLocation();
		
		/*txtLat.setText(mCurrentLocation.getLatitude()+"");
		txtLong.setText(mCurrentLocation.getLongitude()+"");*/
	}
	
}