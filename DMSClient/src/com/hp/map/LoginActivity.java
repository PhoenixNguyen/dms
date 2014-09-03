package com.hp.map;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.common.LoadingView;
import com.hp.domain.Customer;
import com.hp.domain.RoadManagement;
import com.hp.domain.Staff;
import com.hp.rest.CheckingInternet;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.UserAPI.AuthenticateUserTask;
import com.sun.jersey.api.client.ClientResponse;
 
@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class LoginActivity extends Activity {
	
	EditText mUsername;
	EditText mPassword;
	
	private Context context = this;
	
	private Thread thread = new ThreadClass();
    public static Looper threadLooper = null;
    
    @SuppressLint("NewApi")
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	if (android.os.Build.VERSION.SDK_INT > 9) {
    	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    	    StrictMode.setThreadPolicy(policy);
    	}
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.login);
        		
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        mUsername = (EditText)findViewById(R.id.username);
        mUsername.setText("");//ba_dinhtu_liem
        mPassword = (EditText)findViewById(R.id.password);
        mPassword.setText("");//111111
        
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//LOADING DATA

				//Authenticate
				AuthenticateUserTask authenticate = new AuthenticateUserTask(context, mUsername.getText().toString(), mPassword.getText().toString(), 
						LoginActivity.this);
				authenticate.execute();

				
			}
		});
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
            	
                // Switching to Register screen
//                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
//                startActivity(i);
            }
        });
        
       
        
    }
    
    public void doBackground(){

    	// Begin the location reading thread.
        thread.start();

        // do UI stuff in here
        // never sleep in UI thread.  Example only.
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        // end the thread.
//        threadLooper.quit();
//        // quit the activity
//        this.finish();
        //getLocation();
        
    }

    @SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    
    private class ThreadClass extends Thread {
        @Override
        public void run() {
            Looper.prepare();

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            MyLocationListener locListen = new MyLocationListener();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 20000, 50, locListen);

            threadLooper = Looper.myLooper();

            Looper.loop();  // loop until "quit()" is called.

            // remove the update listener to prevent the locationManager from calling it.
            locationManager.removeUpdates(locListen);
        }
    }

    private class MyLocationListener implements LocationListener {      
        @Override
        public void onLocationChanged(Location location) {
        	if(location!= null && location.getLatitude()> 0){
        		System.out.println("latitude20: " + location.getLatitude() + " longitude20: " + location.getLongitude());
        		
        		//SEND
        		pụtJourney((float)location.getLatitude(), (float)location.getLongitude());
        	}
            /* Do very intensive work here */
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            }
        
        ///
        public void pụtJourney(float pX, float pY){
        	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		Date date = new Date();
    		String datestr = dateFormat.format(date);
        	RoadManagement roadManagement = new RoadManagement(Rest.mStaff.getId(), Rest.mStaff.getName(), 
        			Timestamp.valueOf(datestr), pX, pY, "");
        	
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

    		//Order ---------------------------------------------------------------
    		ClientResponse response = Rest.mService.path("webresources").path("putStaffJourney").accept("application/json")
    		.type("application/json").post(ClientResponse.class, objectStr);
    	
    	    String output = response.toString();
    	    System.out.println("input 1: " + output);
    	
    	    if ((response.getStatus() == 200) && (response.getEntity(String.class).compareTo("true") == 0)) {
    	        //Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
    	        // refresh customers
    	    	System.out.println("Đã gửi");
    	
    	    }else
    	    	System.out.println("Không thể gửi");
    	    	//Toast.makeText(context, "Không thể gửi, hãy xem lại kết nối", Toast.LENGTH_SHORT).show();
    	
    	    System.out.println("Server response .... \n");
    	    System.out.println("input 0: " + output);
        }
    }
    
    @Override
	public void onBackPressed() {
    	//onDestroy();
    	
    	moveTaskToBack(true);
    	  
	}
    
        
      
}
