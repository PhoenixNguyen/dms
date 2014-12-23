package com.hp.map;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class InventoryManager_TabActivity extends TakeOrder_TabActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_tab);
		
		Resources recource = getResources();
		TabHost tabHost = getTabHost();
		
		//Product tab
		Intent intentProduct = new Intent().setClass(this, InventoryManager_ProductActivity.class);
		TabSpec tabSpecProduct = tabHost
				.newTabSpec("Product")
				.setIndicator("Sản phẩm")
				.setContent(intentProduct);
		
		//Amount tab
		Intent intentAmount = new Intent().setClass(this, InventoryManager_AmountActivity.class);
		TabSpec tabSpecAmount = tabHost
				.newTabSpec("Amount")
				.setIndicator("Tổng cộng")
				.setContent(intentAmount);
		
		//Review tab
		Intent intentReview = new Intent().setClass(this, InventoryManager_ReviewActivity.class);
		TabSpec tabSpecReview = tabHost
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
	}
}