package com.hp.rest;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

public class CheckingInternet {

	public CheckingInternet(){
		
	}
	
	public static boolean isOnline() {
        try {
        	InetAddress.getByName("google.com").isReachable(2);

        return true;
        	} catch (UnknownHostException e){
        return false;
        } catch (IOException e){
        	return false;
        }
    }
	
//  public boolean isOnline() { 
//		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); 
//		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected(); 
//	}
  
  
  
  private boolean isOnTheInternet() {
      try {
          URLConnection urlConnection = new URL("http://yourserver").openConnection();
          urlConnection.setConnectTimeout(400);
          urlConnection.connect();
          return true;
      } catch (Exception e) {
          return false;
      }
  }
}
