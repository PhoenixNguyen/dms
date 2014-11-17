package com.hp.gps;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.domain.RoadManagement;
import com.hp.rest.Rest;
import com.sun.jersey.api.client.ClientResponse;

import android.annotation.SuppressLint;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {
	  
	  public static Location location;
	  @Override
	  public void onLocationChanged(Location location) {
		  MyLocationListener.setLocation(location);
		  
		// Initialize the location fields
		  Log.v("Location changed:: ", "Changed");
		  
		  Log.v("Latitude: ", String.valueOf(location.getLatitude()));
		  Log.v("Longitude: ", String.valueOf(location.getLongitude()));
		  
		  pụtJourney((float)location.getLatitude(), (float)location.getLongitude());
		  //provText.setText(provider + " provider has been selected.");
	  }

	  public static Location getLocation() {
		return location;
	}

	public static void setLocation(Location location) {
		MyLocationListener.location = location;
	}

	@Override
	  public void onStatusChanged(String provider, int status, Bundle extras) {
		  Log.v("Status changed: ", provider + "'s status changed to "+status +"!");
		  
	  }

	  @Override
	  public void onProviderEnabled(String provider) {
		  Log.v("Provider enabled: ", "Provider " + provider + " enabled!");
		  
	  }

	  @Override
	  public void onProviderDisabled(String provider) {
		  Log.v("Provider disabled: ", "Provider " + provider + " disabled!");
		  
	  }
	  
	  @SuppressLint("SimpleDateFormat")
	  public void pụtJourney(float pX, float pY){
		  if(pX == 0 && pY == 0)
			  return;
		  
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
