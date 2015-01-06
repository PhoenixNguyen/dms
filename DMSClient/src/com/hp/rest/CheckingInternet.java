package com.hp.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import com.hp.common.HttpHelper;

import android.os.StrictMode;

public class CheckingInternet {

	public CheckingInternet(){
		
	}
	
	public static boolean isOnline() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
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
  
  public static int checkUrl(String url, int timeout){
	  try {
		  URL u = new URL (url);
		  HttpURLConnection huc = (HttpURLConnection)u.openConnection();
		  huc.setConnectTimeout(timeout);
		  huc.setRequestMethod ("GET");
		  huc.connect () ; 
		  int code = huc.getResponseCode();
		  System.out.println("code: "+code);
		  
		  return code;
	} catch (Exception e){
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	}
  }
}
