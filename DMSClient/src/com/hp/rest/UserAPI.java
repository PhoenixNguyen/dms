package com.hp.rest;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.provider.Settings;
import android.widget.Toast;

import com.hp.domain.RoadManagement;
import com.hp.domain.Staff;
import com.hp.gps.MyLocationListener;
import com.hp.map.CustomerListActivity;
import com.hp.map.LoginActivity;
import com.hp.map.ProfileActivity;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.sun.jersey.api.client.ClientResponse;

public class UserAPI {
	public static class AuthenticateUserTask extends AsyncTask<Void,Void,String>
    {
		Context context;
		String username;
		String password;
		LoginActivity activity;
		
		
    	ProgressDialog dialog;
    	
    	public AuthenticateUserTask(Context context, String username, String password,
    			LoginActivity activity){
    		
    		this.context = context;
    		this.username = username;
    		
    		this.password = password;
    		this.activity = activity;
    	}
    	
    	
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(context, "",
  				  "Đang đăng nhập", true);
		}
        @SuppressWarnings("static-access")
		protected String doInBackground(Void... params)
        {
            //do something  
			if(CheckingInternet.isOnline()){
				System.out.println("Internet access!!____________________");
			}
			else{
												
				System.out.println("NO Internet access!!____________________");
								
				return "nointernet";
				
			}
							
	
			// Connect server
	        new Rest("").connectWebservices();
	        
			System.out.println("USERNAME___" + username);
			//Init Http request
			System.out.println("__ " + password);
			
			
			// Getting
			ClientResponse response = null;
			
			try{
				response = Rest.mService.path("webresources")
					.path("getStaff").accept("application/json")
					.type("application/json").post(ClientResponse.class, username +"::" + password);
			}catch(Exception e){
				e.printStackTrace();
				return "nohost";
			}
			
			System.out.println("________________ " + response.toString());

			if (response.getStatus() != 200) {

				return "nodata";
			} else {

				String re = response.getEntity(String.class);
				System.out.println("________________ " + re);

				// Convert
				if (convertStringToObject(re)){
					return "success";
					
				}
				else
					return "nodata";
			}
			
			// =====================================================================================
	    
        }

        protected void onPostExecute(String result)
        {
            if (result.equals("success")){
				//Run thread to do backgroud send location
				//activity.doBackground();
            	updateCurrentLocation(activity);
            	
				// TODO Auto-generated method stub
				Intent i = new Intent(activity.getApplicationContext(), ProfileActivity.class);
				i.putExtra("login", 1);
				
				activity.startActivity(i);
	        	//new ThreatRealtime("hello").start();
				
				//Save passwork
				if(activity.remember_me.isChecked()){
					SharedPreferences sp = context.getSharedPreferences("loginSaved", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("username", Rest.mStaff.getId());
					editor.putString("password", Rest.mStaff.getPw());
					editor.commit();
				}
				else{
					SharedPreferences sp = context.getSharedPreferences("loginSaved", Context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sp.edit();
					editor.putString("username", "");
					editor.putString("password", "");
					editor.commit();
				}
				
	        	
            }
            else
            	if (result.equals("nointernet")){
            		Toast.makeText(context, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();
            	}
            	else
                	if (result.equals("nohost")){
                		Toast.makeText(context, "Không thể kết nối được với máy chủ!", Toast.LENGTH_SHORT).show();
                	}
		           else
		           {       
		        	   					
		        	   Toast.makeText(context, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
						
		           }
            
            dialog.dismiss();
        }   
        
        public boolean convertStringToObject(String str){
        	ObjectMapper mapper = new ObjectMapper();
            
            try {
            	Rest.mStaff = mapper.readValue(str, Staff.class);
    			
    		} catch (JsonGenerationException e) {
    			e.printStackTrace();
    			return false;
    		} catch (JsonMappingException e) {
    			e.printStackTrace();
    			return false;
    		} catch (IOException e) {
    			e.printStackTrace();
    			return false;
    		}
            
            if(Rest.mStaff != null)
            	return true;
            else
            	return false;
        }
    } 
	
	private static void updateCurrentLocation(Activity activity) {
		// Get the location manager
		  LocationManager locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		  // Define the criteria how to select the location provider
		  Criteria criteria = new Criteria();
		  criteria.setAccuracy(Criteria.ACCURACY_COARSE);	//default
		  
		  criteria.setCostAllowed(false); 
		  // get the best provider depending on the criteria
		  String provider = locationManager.getBestProvider(criteria, false);
	    
		  // the last known location of this provider
		  Location location = locationManager.getLastKnownLocation(provider);

		  MyLocationListener mylistener = new MyLocationListener();
	
		  if (location != null) {
			  mylistener.onLocationChanged(location);
		  } else {
			  // leads to the settings because there is no last known location
			  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			  activity.startActivity(intent);
		  }
		  // location updates: at least 1 meter and 200millsecs change
		  locationManager.requestLocationUpdates(provider, 10000, 50, mylistener);
		
	}
	
	//EDIT STAFF
	public static class EditUserTask extends AsyncTask<Void,Void,String>
    {
		Context context;
		String method;
		
		Staff staff;
		
		
		
    	ProgressDialog dialog;
    	
    	public EditUserTask(Context context, String method, Staff staff){
    		
    		this.context = context;
    		this.method = method;
    		
    		this.staff = staff;
    	}
    	
    	
    	protected void onPreExecute() {
    		dialog = ProgressDialog.show(context, "",
  				  "Đang xử lý", true);
		}
        protected String doInBackground(Void... params)
        {
            //do something  
			if(CheckingInternet.isOnline()){
				System.out.println("Internet access!!____________________");
			}
			else{
												
				System.out.println("NO Internet access!!____________________");
								
				return "nointernet";
				
			}
			
			// Getting
			ClientResponse response = null;
			try{
			response = Rest.mService.path("webresources")
					.path(method).accept("application/json")
					.type("application/json").post(ClientResponse.class, convertObjectToString(staff));
			}catch(Exception e){
				e.printStackTrace();
				return "nohost";
			}
			
			System.out.println("________________ " + response.toString());

			if (response.getStatus() != 200) {

				return "nodata";
			} else {

				String re = response.getEntity(String.class);
				System.out.println("________________ " + re);

				// Convert
				if (re.compareTo("true") == 0)
					return "success";
				else
					return "fail";
			}
			
			// =====================================================================================
	    
        }

        protected void onPostExecute(String result)
        {
            if (result.equals("success")){
                //do something
            	Rest.mStaff = staff;
            	Toast.makeText(context, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
	        	
            }
            else
				if (result.equals("nointernet")){
					Toast.makeText(context, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();
				}
			else
				if (result.equals("fail")){
					
					Toast.makeText(context, "Không thể lưu dữ liệu. hãy thử lại sau", Toast.LENGTH_SHORT).show();
				}
				else
                	if (result.equals("nohost")){
                		Toast.makeText(context, "Không thể kết nối được với máy chủ!", Toast.LENGTH_SHORT).show();
                	}
			else
			{       
			 				
			 Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				
			}
            
            dialog.dismiss();
        }   
        
        public String convertObjectToString(Staff staff){
        	ObjectMapper mapper = new ObjectMapper();
        	String str = new String();
            try {
            	str = mapper.writeValueAsString(staff);
    			
    		} catch (JsonGenerationException e) {
    			e.printStackTrace();
    			return "";
    		} catch (JsonMappingException e) {
    			e.printStackTrace();
    			return "";
    		} catch (IOException e) {
    			e.printStackTrace();
    			return "";
    		}
            
            return str;
        }
    } 
}
