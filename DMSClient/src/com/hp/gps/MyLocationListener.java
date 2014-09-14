package com.hp.gps;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

public class MyLocationListener implements LocationListener {
	  
	  public static Location location;
	  @Override
	  public void onLocationChanged(Location location) {
		  MyLocationListener.location = location;
		  
		// Initialize the location fields
		  Log.v("Location changed:: ", "Changed");
		  
		  Log.v("Latitude: ", String.valueOf(location.getLatitude()));
		  Log.v("Longitude: ", String.valueOf(location.getLongitude()));
		  
		  //provText.setText(provider + " provider has been selected.");
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
}
