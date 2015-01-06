package com.hp.map;


import com.hp.common.SharedConstant;
import com.hp.common.Utility;
import com.hp.gps.BackgroundLocationService;
import com.hp.menu.DetailListData;
import com.hp.menu.DetailsList;
import com.hp.menu.DialogArrayAdapter;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainMenuActivity extends Activity  {

	public Context context = this; 
	private ListView lv;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_main);
		
		if(getActionBar() != null){
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setLogo(R.drawable.ic_launcher);
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		if(BackgroundLocationService.mLocationClient == null){
			// Init service 
			System.out.println("turn on");
			startService(new Intent(context, BackgroundLocationService.class));
		}
		
		//Rest.mStaff = null;
		
		System.out.println("Test!");
		Utility.keepLogined(context);
		
	}

	public void onResume(){
		super.onResume();
	}
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		//getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
	
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_MENU) {
	        // ........
	    	//Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
	    	menuDialog();
	    	
	        return true;
	    }
	    return super.onKeyUp(keyCode, event);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;

	    }

	    return true;
	}
	
	@SuppressWarnings("deprecation")
	public void menuDialog(){
		final Dialog dialog = new Dialog(this);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.menu_dialog, null, false);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(v);
		
		dialog.setTitle("Danh mục chính");
		
		Display display = getWindowManager().getDefaultDisplay();
		
		dialog.getWindow().setLayout(2*display.getWidth()/3, LayoutParams.FILL_PARENT);
		dialog.getWindow().getAttributes().gravity = Gravity.LEFT|Gravity.CENTER_VERTICAL;
		
		lv = (ListView)dialog.findViewById(R.id.menu_list_view);
		
		lv.setAdapter(new DialogArrayAdapter(context, android.R.layout.simple_list_item_1, DetailListData.MENU_LIST));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				DetailsList selectedValue = (DetailsList)lv.getAdapter().getItem(arg2);
				if(selectedValue.activityClass != null){
					//if sigout
					if(selectedValue.activityClass == LoginActivity.class){
						//LoginActivity.threadLooper.quit();
						SharedPreferences sp = context.getSharedPreferences(SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
						SharedPreferences.Editor editor = sp.edit();
						
						// Logout
						editor.putBoolean(SharedConstant.LOGINED_STAFF, false);
						editor.commit();
					}
					startActivity(new Intent(context, selectedValue.activityClass));
				}
			}
		});
		
		dialog.show();
		
//		ImageView iv = (ImageView)dialog.findViewById(R.id.menu_list_view);
//		iv.setImageResource(1);
	}

}

