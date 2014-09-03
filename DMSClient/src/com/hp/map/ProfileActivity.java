package com.hp.map;

import com.hp.rest.Rest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends MainMenuActivity{
	private TextView my_info;
	
	private EditText name;
	private EditText address;
	private EditText job;
	private EditText phone;
	private EditText date;
	private EditText permission;
	private EditText manager;
	
	private Button password;
	
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.staff_profile);
		
		my_info = (TextView)findViewById(R.id.my_info);
		
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
		
		
		my_info.setText("Xin chào " + Rest.mStaff.getId() + " - " + Rest.mStaff.getName());
		
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
}
