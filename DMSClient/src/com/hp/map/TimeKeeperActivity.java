package com.hp.map;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.hp.domain.TimeKeeper;
import com.hp.rest.Rest;
import com.hp.rest.TimeKeeperAPI;
import com.hp.rest.CalendarAPI.ModifyCalendarTask;
import com.hp.rest.TimeKeeperAPI.GetTimeKeeperTask;
import com.hp.rest.TimeKeeperAPI.ModifyTimeKeeperTask;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TimeKeeperActivity extends MainMenuActivity{
	
	public TextView time_keeper;
	public LinearLayout times_timekeeping;
	public String getList;
	public TextView total_time;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_keeper);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		times_timekeeping = (LinearLayout)findViewById(R.id.times_timekeeping);
		time_keeper = (TextView)findViewById(R.id.time_keeper);
		total_time = (TextView)findViewById(R.id.total_time);
		
		time_keeper.setText("Chấm công ngày: " + df.format(new Date()));
		
		init();
		getTimeKeepingList();
	}
	
	private void init() {
		
		getList = "getTimeKeeperList";
	}

	public void getTimeKeepingList(){
		// GET From server

		GetTimeKeeperTask getData = new GetTimeKeeperTask(context, getList, Rest.mStaff.getId(), this );
        getData.execute();
	        
	}
	
	public void displayTimeKeepings(){
		//reset
		times_timekeeping.removeAllViews();
		
		float hours = 0;
		for(TimeKeeper timeKeeper : TimeKeeperAPI.timeKeeperList){
			if(hours < timeKeeper.getTimeBetween())
				hours = timeKeeper.getTimeBetween();
			
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			
			LinearLayout times1 = new LinearLayout(this);
			times1.setOrientation(LinearLayout.HORIZONTAL);
			times1.setLayoutParams(params);
			
			params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			ImageView times1_img = new ImageView(this);
			times1_img.setImageResource(R.drawable.tick);
			times1_img.setLayoutParams(params);
			
			params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			TextView times1_txt = new TextView(this);
			times1_txt.setText("" + df.format(timeKeeper.getTimeAt()));
			times1_txt.setTextSize(18);
			
			times1.addView(times1_img);
			times1.addView(times1_txt);
			
			times_timekeeping.addView(times1);
			
		}
		
		total_time.setText("Tổng số giờ làm việc " + hours + " giờ.");
	}
	
	public void timeKeeping(View v){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeKeeper timeKeeper = new TimeKeeper(Rest.mStaff, Timestamp.valueOf(df.format(new Date())), 
				"Hà Nội", 0, "");
		
		ModifyTimeKeeperTask addTimeKeeper = new ModifyTimeKeeperTask(this, ModifyTimeKeeperTask.ACTION_ADD, "putTimeKeeper", timeKeeper, this);
		addTimeKeeper.execute();
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order_menu, menu);
		
		MenuItem item = menu.findItem(R.id.action_add);
		item.setVisible(false);
		return true;
	}
}
