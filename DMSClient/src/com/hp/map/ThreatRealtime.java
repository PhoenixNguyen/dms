package com.hp.map;

import java.util.Calendar;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.owlike.genson.Context;

import android.app.Activity;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class ThreatRealtime extends  Thread  {
	private double x;
	private double y;
	
	private Context context ;
	public ThreatRealtime(String str) {
        super(str);
       
    }
    public void run() {
        //for (int i = 0; i < 10; i++) {
    	int i =0;
    	while(i >= 0){
    		
            System.out.println(i + " " + getName());
            System.out.println(GetLocation.x +" "+ GetLocation.y);
            try {
                sleep(5000);
                i++;
            } catch (InterruptedException e) {}
        }
        System.out.println("DONE! " + getName());
    }
  
//    public LatLng getLocation()
//    {
//     // Get the location manager
//     //LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//     Criteria criteria = new Criteria();
////     String bestProvider = locationManager.getBestProvider(criteria, false);
////     Location location = locationManager.getLastKnownLocation(bestProvider);
//     Double lat,lon;
//     try {
//       lat = location.getLatitude ();
//       lon = location.getLongitude ();
//       return new LatLng(lat, lon);
//     }
//     catch (NullPointerException e){
//         e.printStackTrace();
//       return null;
//     }
//    }

public static class GetLocation extends Activity implements LocationListener {
    LocationManager mLocationManager;
    public static double x;
    public static double y;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_map);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location != null && location.getTime() > Calendar.getInstance().getTimeInMillis() - 2 * 60 * 1000) {
            // Do something with the recent location fix
            //  otherwise wait for the update below
        }
        else {
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    public void onLocationChanged(Location location) {
        if (location != null) {
        	x = location.getLatitude();
        	y = location.getLongitude();
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            mLocationManager.removeUpdates(this);
        }
    }

    // Required functions    
    public void onProviderDisabled(String arg0) {}
    public void onProviderEnabled(String arg0) {}
    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {}
}

}