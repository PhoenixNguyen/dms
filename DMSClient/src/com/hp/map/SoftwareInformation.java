package com.hp.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hp.common.HttpHelper;
import com.hp.common.UpdateApp;
import com.hp.rest.CheckingInternet;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SoftwareInformation extends MainMenuActivity{
	TextView version ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.software_information);
		
		version = (TextView)findViewById(R.id.version);
		version.setText("1.0");
	}
	
	private int getUpgradeVersion(){
		if(CheckingInternet.isOnline()){
			System.out.println("Internet access!!____________________");
		}
		else{
											
			System.out.println("NO Internet access!!____________________");
			Toast.makeText(this, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();				
			return 0;
			
		}
		
		String url = "https://raw.githubusercontent.com/PhoenixNguyen/dms/master/app/version.txt";
		JSONObject jsonObj;
		int version = 0;
		try {
		      jsonObj = new JSONObject(HttpHelper.makeRequest(url));
		      version = jsonObj.getInt("versionCode");
		      
	     } catch (JSONException e) {
	    	 e.printStackTrace();
	    	 return 0;
	     }
		
		return version;
	}
	
	public static int getVersionCode(Context context) {
	   PackageManager pm = context.getPackageManager();
	   try {
	      PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
	      return pi.versionCode;
	   } catch (NameNotFoundException ex) {}
	   return 0;
	}

	public void update(View v){
		
		int newVersion = getUpgradeVersion();
		int currVersion = getVersionCode(this);
		Log.v("Version", "newVersion: " + newVersion + " currVersion: "+currVersion);
		if(newVersion <= currVersion){
			Toast.makeText(this, "Phiên bản của bạn đã là mới nhất!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		//Auto update
		UpdateApp atualizaApp = new UpdateApp();
        atualizaApp.setContext(getApplicationContext());
        atualizaApp.execute("https://raw.githubusercontent.com/PhoenixNguyen/dms/master/app/dms.apk");
        
	}
}
