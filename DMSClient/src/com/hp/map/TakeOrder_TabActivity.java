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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class TakeOrder_TabActivity extends TabActivity{
	
	public Context context = this; 
	private ListView lv;
	
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_tab);
		
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setLogo(R.drawable.ic_launcher);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Resources recource = getResources();
		final TabHost tabHost = getTabHost();
		
		//Product tab
		Intent intentProduct = new Intent().setClass(this, TakeOrder_ProductActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		final TabSpec tabSpecProduct = tabHost
				.newTabSpec("Product")
				.setIndicator("Sản phẩm")
				.setContent(intentProduct);
		
		//Amount tab
		Intent intentAmount = new Intent().setClass(this, TakeOrder_AmountActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;
		final TabSpec tabSpecAmount = tabHost
				.newTabSpec("Amount")
				.setIndicator("Tổng cộng")
				.setContent(intentAmount);
		
		//Review tab
		Intent intentReview = new Intent().setClass(this, TakeOrder_ReViewActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);;
		final TabSpec tabSpecReview = tabHost
				.newTabSpec("Review")
				.setIndicator("Xem lại")
				.setContent(intentReview);
		
		tabHost.addTab(tabSpecProduct);
		tabHost.addTab(tabSpecReview);
		tabHost.addTab(tabSpecAmount);
		
		if(TakeOrder_ProductActivity.restart == 10)
			tabHost.setCurrentTab(1);
		else
			tabHost.setCurrentTab(0);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
	         @Override
	         public void onTabChanged(String tabId) {
	        	 int currentTabId = tabHost.getCurrentTab();
	        	 //tabHost.clearAllTabs();
        	    //setupTabs();
	        	 System.out.println("tabId: " + tabId + " currentTabId:" + currentTabId);
        	    //tabHost.setCurrentTab(tabId);
	        	 
	        	 if(tabId.equalsIgnoreCase("Review") && TakeOrder_ProductActivity.restart < 1){
	        		// TakeOrder_ProductActivity.restart++;
	        		 
	        		 /*tabHost.clearAllTabs();
	        		 tabHost.addTab(tabSpecProduct);
        			 tabHost.addTab(tabSpecReview);
        			 tabHost.addTab(tabSpecAmount);*/
        			
	        		 //tabHost.setCurrentTab(0);
	        	 }
	         }	
		 });
		 
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
	
//	public boolean onCreateOptionsMenu(Menu menu){
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.customer_manager_menu, menu);
//		
//		return true;
//	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
//	    case R.id.action_save:
//	    	//TakeOrder_AmountActivity.saveOrder();
//            return true;
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
						//LoginActivity.threadLooper.quit();
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