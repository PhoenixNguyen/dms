package com.hp.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hp.common.HttpHelper;
import com.hp.common.UpdateApp;
import com.hp.common.UpdateApp.Update;
import com.hp.rest.CheckingInternet;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SoftwareInformation extends MainMenuActivity{
	Context context = this;
	TextView version ;
	String newVersion = "";
	
	public String getNewVersion() {
		return newVersion;
	}

	public void setNewVersion(String newVersion) {
		this.newVersion = newVersion;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.software_information);
		
		version = (TextView)findViewById(R.id.version);
		version.setText(getVersionCodeName(this));
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
		      setNewVersion(jsonObj.getString("versionName"));
		      
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
	public static String getVersionCodeName(Context context) {
		   PackageManager pm = context.getPackageManager();
		   try {
		      PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
		      return pi.versionName;
		   } catch (NameNotFoundException ex) {}
		   return "";
		}
	
	public void update(View view){
		
		int newVersion = getUpgradeVersion();
		int currVersion = getVersionCode(this);
		Log.v("Version", "newVersion: " + newVersion + " currVersion: "+currVersion);
		if(newVersion <= currVersion){
			Toast.makeText(this, "Phiên bản của bạn đã là mới nhất!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_comfirm_dialog, null, false);
		dialog.setContentView(v);
		dialog.setTitle("Phiên bản mới nhất: " + getNewVersion());
		
		final Button detail = (Button)dialog.findViewById(R.id.detail); detail.setText("Cập nhật");
		final Button cancel = (Button)dialog.findViewById(R.id.cancel);
		
		detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Auto update
				String url = "https://raw.githubusercontent.com/PhoenixNguyen/dms/master/app/dms.apk";
				Update atualizaApp = new Update(context, url);
		        atualizaApp.execute();
		        
		        dialog.dismiss();
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
        
	}
}
