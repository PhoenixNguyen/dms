package com.hp.map;

import com.hp.domain.Staff;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.UserAPI.EditUserTask;

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

public class ProfileEditActivity extends MainMenuActivity{
	private TextView my_info;
	
	private EditText name;
	private EditText address;
	private EditText job;
	private EditText phone;
	private EditText date;
	private EditText permission;
	private EditText manager;
	
	private Button password;
	
	private Staff staff;
	
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
		
		
		
		
		my_info.setText("Sửa thông tin ");
		
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
		
		staff = new Staff();
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.staff_edit, menu);
		
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
	        
	    case R.id.action_done:
        	updateData();
            return true;
	    
	    default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	public void updateData(){
		staff.setId(Rest.mStaff.getId());
		staff.setManager(Rest.mStaff.getManager());
		staff.setPermission(Rest.mStaff.getPermission());
		staff.setPw(Rest.mStaff.getPw());
		staff.setStt(Rest.mStaff.getStt());
		staff.setStatus(true);
		staff.setDate(Rest.mStaff.getDate());
		
		staff.setAdress(address.getText().toString());
		
		staff.setJob(job.getText().toString());
		staff.setName(name.getText().toString());
		staff.setPhone(phone.getText().toString());
		
		
		
		EditUserTask update = new EditUserTask(context, "updateStaff", staff
			   );
		update.execute();
	}
	
	public void changePW(View view){
		startActivity(new Intent(this, ProfileChangePWActivity.class));
	}
	
	
}
