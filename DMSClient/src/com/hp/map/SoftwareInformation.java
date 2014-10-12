package com.hp.map;

import com.hp.common.UpdateApp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SoftwareInformation extends MainMenuActivity{
	TextView version ;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.software_information);
		
		version = (TextView)findViewById(R.id.version);
		version.setText("1.0");
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
		//Auto update
		UpdateApp atualizaApp = new UpdateApp();
        atualizaApp.setContext(getApplicationContext());
        atualizaApp.execute("https://raw.githubusercontent.com/PhoenixNguyen/dms/master/app/dms.apk");
        
	}
}
