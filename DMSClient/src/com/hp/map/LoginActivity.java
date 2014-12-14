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
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Criteria;
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
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.common.LoadingView;
import com.hp.domain.Customer;
import com.hp.domain.RoadManagement;
import com.hp.domain.Staff;
import com.hp.gps.MyLocationListener;
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
	
	public CheckBox remember_me;
	
	private Context context = this;
	
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
        mPassword = (EditText)findViewById(R.id.password);
        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
		final String imei =   mngr.getDeviceId();
		  
        remember_me = (CheckBox)findViewById(R.id.remember_me);
        
        SharedPreferences sp = context.getSharedPreferences("loginSaved", Context.MODE_PRIVATE);
        String username = sp.getString("username", null);
        String password = sp.getString("password", null);
        if(username != null && password != null){
        	mUsername.setText(username);//ba_dinhtu_liem
            mPassword.setText(password);//111111 
        }
        else{
        	mUsername.setText("");//ba_dinhtu_liem
            mPassword.setText("");//111111
        }
        
        Log.e("App pre","nnj");
        
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//LOADING DATA

				//Authenticate
				AuthenticateUserTask authenticate = new AuthenticateUserTask(context, mUsername.getText().toString(), mPassword.getText().toString(), imei, 
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
    
    @SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    
    @Override
	public void onBackPressed() {
    	//onDestroy();
    	
    	moveTaskToBack(true);
    	  
	}
        
}
