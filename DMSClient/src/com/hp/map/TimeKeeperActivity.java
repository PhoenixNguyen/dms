package com.hp.map;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hp.common.HttpHelper;
import com.hp.common.Unicode2NoSign;
import com.hp.domain.TimeKeeper;
import com.hp.gps.MapLocation;
import com.hp.gps.MyLocationListener;
import com.hp.rest.CheckingInternet;
import com.hp.rest.Rest;
import com.hp.rest.TimeKeeperAPI;
import com.hp.rest.TimeKeeperAPI.GetTimeKeeperTask;
import com.hp.rest.TimeKeeperAPI.ModifyTimeKeeperTask;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TimeKeeperActivity extends MainMenuActivity{
	
	public TextView time_keeper;
	public LinearLayout times_timekeeping;
	public String getList;
	public TextView total_time;
	
	private SimpleDateFormat df;
	
	@SuppressLint("SimpleDateFormat")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_keeper);
		
		df = new SimpleDateFormat("dd/MM/yyyy");
		
		times_timekeeping = (LinearLayout)findViewById(R.id.times_timekeeping);
		time_keeper = (TextView)findViewById(R.id.time_keeper);
		total_time = (TextView)findViewById(R.id.total_time);
		
		time_keeper.setText("Chấm công ngày: " + df.format(new Date()));
		
		//gps = new GPS(this);
		
		init();
		getTimeKeepingList();
		
		getAddress();
	}
	
	

	private void init() {
		
		getList = "getTimeKeeperList";
	}

	public void getTimeKeepingList(){
		// GET From server

		GetTimeKeeperTask getData = new GetTimeKeeperTask(context, getList, Rest.mStaff.getId(), this );
        getData.execute();
	        
	}
	
	@SuppressLint("SimpleDateFormat")
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
			
			//Time
			params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.weight = 0.7f;
			TextView times1_txt = new TextView(this);
			Calendar c = Calendar.getInstance();
			c.setTime(timeKeeper.getTimeAt());
			c.add(Calendar.HOUR, -7);
			times1_txt.setText("  " + df.format(c.getTime()));
			times1_txt.setTextSize(18);
			
			//Status
			params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.weight = 0.3f;
			ImageView times1_img = new ImageView(this);
			times1_img.setImageResource(R.drawable.ok);
			times1_img.setLayoutParams(params);
			
			//Add
			times1.addView(times1_txt);
			times1.addView(times1_img);
			
			times_timekeeping.addView(times1);
			
		}
		
		total_time.setText("Tổng số giờ làm việc " + hours + " giờ.");
	}
	
	@SuppressLint("SimpleDateFormat")
	public String getAddress(){
		//check internet
		if(CheckingInternet.isOnline()){
			System.out.println("Internet access!!____________________");
		}
		else{
											
			System.out.println("NO Internet access!!____________________");
			Toast.makeText(this, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();				
			return null;
			
		}
		
		if(MyLocationListener.location.getLatitude() == 0 && MyLocationListener.location.getLongitude() == 0){
			Toast.makeText(this, "Đang cập nhật vị trí ...", Toast.LENGTH_SHORT).show();
			return "";
		}
		//Toast.makeText(this, String.valueOf(MyLocationListener.location.getLatitude()) + " " +String.valueOf(MyLocationListener.location.getLongitude()), Toast.LENGTH_SHORT).show();
		
		//Get City
		String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
		        + MyLocationListener.location.getLatitude() + "," + MyLocationListener.location.getLongitude() + "&sensor=false";
		JSONObject jsonObj;
		String City = "";
		String address = "";
	     try {
	      
	      jsonObj = new JSONObject(HttpHelper.makeRequest(url));

	      String Status = jsonObj.getString("status");
	      if (Status.equalsIgnoreCase("OK")) {
		       JSONArray Results = jsonObj.getJSONArray("results");
		       JSONObject zero = Results.getJSONObject(0);
		       
		       address = zero.getString("formatted_address").toString();
		       System.out.println("Full address _______________________________ --->" + address + "");
		       
		       String[] long_name = address.split(",");
		       
		       int number = long_name.length - 2;
		       City = long_name[number];
		       Log.d(" CityName _______________________________ ---> ", City + "");
		       System.out.println("CityName _______________________________ --->" + City + "");
		       
		       //Toast.makeText(this, "CityName: " + City, Toast.LENGTH_SHORT).show();
		       
		       if (!City.equals("")) {
		        //finish_service();
		       }
	      }

	     } catch (JSONException e) {
	    	 e.printStackTrace();
	     }
		//End Get City
		
	     if(!address.equals("")){
	    	 time_keeper.setText("Chấm công ngày: " + df.format(new Date()) + "\n" + 
	    			 "_____________________________________ \n" +
	    			 "Vị trí hiện tại của bạn: " + " \n" + address);
	     }
	     
	     return City;
	}
	@SuppressLint("SimpleDateFormat")
	public void timeKeeping(View v){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//Get City
		String city = getAddress();
		if(city == null)
			return;
		
		if(city.equals("")){
			Toast.makeText(this, "Đang xác định thành phố ...", Toast.LENGTH_SHORT).show();
			return ;
			
		}
		
		String cityPut = "";
		//End Get City
		for(String cityCore : MapLocation.getAllCityOfVietNam()){
			if(city.toLowerCase().indexOf(Unicode2NoSign.convert(cityCore).toLowerCase()) != -1 ||
					city.toLowerCase().indexOf(Unicode2NoSign.convert(cityCore).replace(" ", "").toLowerCase()) != -1 ){
				
				cityPut = cityCore;
				
				Log.v("CITY PUT: ", cityPut);
				break;
			}
		}
		
		Log.v("CITY PUT: ", cityPut);
		
		if(cityPut.equals("")){
			Toast.makeText(this, "Không xác định được thành phố, hãy liên lạc với nhà sản xuất. Xin cảm ơn!", Toast.LENGTH_SHORT).show();
			return ;
		}
		
		TimeKeeper timeKeeper = new TimeKeeper(Rest.mStaff, Timestamp.valueOf(df.format(new Date())), 
				cityPut, 0, "");
		
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
