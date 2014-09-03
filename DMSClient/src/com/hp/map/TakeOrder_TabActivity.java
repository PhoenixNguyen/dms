package com.hp.map;

import com.hp.menu.DetailListData;
import com.hp.menu.DetailsList;
import com.hp.menu.DialogArrayAdapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.TabActivity;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class TakeOrder_TabActivity extends TabActivity{
	
	public Context context = this; 
	private ListView lv;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_tab);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setIcon(R.drawable.ic_drawer);
		
		Resources recource = getResources();
		TabHost tabHost = getTabHost();
		
		//Product tab
		Intent intentProduct = new Intent().setClass(this, TakeOrder_ProductActivity.class);
		TabSpec tabSpecProduct = tabHost
				.newTabSpec("Product")
				.setIndicator("Sản phẩm")
				.setContent(intentProduct);
		
		//Amount tab
		Intent intentAmount = new Intent().setClass(this, TakeOrder_AmountActivity.class);
		TabSpec tabSpecAmount = tabHost
				.newTabSpec("Amount")
				.setIndicator("Tổng cộng")
				.setContent(intentAmount);
		
		//Review tab
		Intent intentReview = new Intent().setClass(this, TakeOrder_ReViewActivity.class);
		TabSpec tabSpecReview = tabHost
				.newTabSpec("Review")
				.setIndicator("Xem lại")
				.setContent(intentReview);
		
		tabHost.addTab(tabSpecProduct);
		tabHost.addTab(tabSpecReview);
		tabHost.addTab(tabSpecAmount);
		
		tabHost.setCurrentTab(0);
	}
	
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
						LoginActivity.threadLooper.quit();
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