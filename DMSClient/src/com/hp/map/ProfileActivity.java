package com.hp.map;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hp.common.HttpHelper;
import com.hp.gps.BackgroundLocationService;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CheckingInternet;
import com.hp.rest.Rest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class ProfileActivity extends MainMenuActivity  
{
	private TextView my_info;
	private static TextView my_location;
	private EditText name;
	private EditText address;
	private EditText job;
	private EditText phone;
	private EditText date;
	private EditText permission;
	private EditText manager;
	
	private Button password;
	
	private static SimpleDateFormat df;
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.staff_profile);
		
		df = new SimpleDateFormat("dd/MM/yyyy");
		
		my_info = (TextView)findViewById(R.id.my_info);
		my_location = (TextView)findViewById(R.id.my_location);
		
		name = (EditText)findViewById(R.id.name);
		address = (EditText)findViewById(R.id.address);
		job = (EditText)findViewById(R.id.job);
		phone = (EditText)findViewById(R.id.phone);
		date = (EditText)findViewById(R.id.date);
		permission = (EditText)findViewById(R.id.permission);
		manager = (EditText)findViewById(R.id.manager);
		
		password = (Button)findViewById(R.id.password);
		
		name.setFocusable(false);
		address.setFocusable(false);
		job.setFocusable(false);
		phone.setFocusable(false);
		
		
		my_info.setText("Xin chào! " + Rest.mStaff.getId() + " - " + Rest.mStaff.getName());
		
		name.setText(Rest.mStaff.getName());
		address.setText(Rest.mStaff.getAdress());
		job.setText(Rest.mStaff.getJob());
		phone.setText(Rest.mStaff.getPhone());
		
		manager.setText(Rest.mStaff.getManager());
		if(Rest.mStaff.getPermission() == 1)
			permission.setText("Quản lý");
		if(Rest.mStaff.getPermission() == 2)
			permission.setText("Nhân viên bán hàng");
		if(Rest.mStaff.getPermission() == 3)
			permission.setText("Nhân viên lấy vị trí");
		
		
		Intent i = getIntent();
		if(i != null){
			int val = i.getIntExtra("login", 0);
			if(val == 1){
				GetCustomerListTask getData = new GetCustomerListTask(ProfileActivity.this, context, CustomerListActivity.LOAD_CUSTOMER, Rest.mStaff.getId()); //getCustomersListStart
            	getData.execute();
			}
		}
		
		// Init service 
		startService(new Intent(context, BackgroundLocationService.class));
		if(BackgroundLocationService.CURRENT_LOCATION != null)
			getAddress(BackgroundLocationService.CURRENT_LOCATION);
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.staff_infomation, menu);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	        
	    case R.id.action_edit:
        	startActivity(new Intent(this, ProfileEditActivity.class));
            return true;
	    
	    default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	public void changePW(View view){
		startActivity(new Intent(this, ProfileChangePWActivity.class));
	}
	
	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String getAddress(Location location){
		//check internet
		if(CheckingInternet.isOnline()){
			System.out.println("Internet access!!____________________");
		}
		else{
											
			System.out.println("NO Internet access!!____________________");
			//Toast.makeText(this, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();				
			return null;
			
		}
		
		if(location == null)
			return "";
		
		if(location.getLatitude() == 0 && location.getLongitude() == 0){
			//Toast.makeText(this, "Đang cập nhật vị trí ...", Toast.LENGTH_SHORT).show();
			return "";
		}
		//Toast.makeText(this, String.valueOf(MyLocationListener.location.getLatitude()) + " " +String.valueOf(MyLocationListener.location.getLongitude()), Toast.LENGTH_SHORT).show();
		
		//Get City
		String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
		        + location.getLatitude() + "," + location.getLongitude() + "&sensor=false";
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
	    	 my_location.setText("" + df.format(new Date()) + "\n" + 
	    			 "" +
	    			 "Vị trí hiện tại: " + " \n" + address);
	     }
	     
	     return address;
	}
	
}
