package com.hp.menu;

import android.app.Activity;

public class DetailsList {
	
	//Title
	public String title;
	//source for photo
	public int src;
	//class to init
	public Class<? extends Activity> activityClass;
	
	public DetailsList(String title, int src,
			Class<? extends Activity> activityClass) {
		super();
		this.title = title;
		this.src = src;
		this.activityClass = activityClass;
	}
}
