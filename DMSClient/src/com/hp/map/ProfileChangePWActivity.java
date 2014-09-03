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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileChangePWActivity extends MainMenuActivity{
	private TextView my_info;
	
	private EditText pw;
	private EditText pw1;
	private EditText pw2;
	
	
	private Staff staff;
	
	public void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.staff_pw);
		
		my_info = (TextView)findViewById(R.id.my_info);
		
		pw = (EditText)findViewById(R.id.pw);
		pw1 = (EditText)findViewById(R.id.pw1);
		pw2 = (EditText)findViewById(R.id.pw2);
		
		
		
		my_info.setText("Thay đổi mật khẩu ");
		
		
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
		if(pw.getText().toString().compareTo(Rest.mStaff.getPw()) != 0){
			Toast.makeText(context, "Mật khẩu cũ không đúng, hãy thử lại ", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if(pw1.getText().toString().compareTo(pw2.getText().toString()) != 0){
			Toast.makeText(context, "Xác nhận mật khẩu không khớp, hãy thử lại ", Toast.LENGTH_SHORT).show();
			return;
		}
			
			
		staff.setId(Rest.mStaff.getId());
		staff.setManager(Rest.mStaff.getManager());
		staff.setPermission(Rest.mStaff.getPermission());
		
		staff.setStt(Rest.mStaff.getStt());
		staff.setStatus(true);
		staff.setDate(Rest.mStaff.getDate());
		
		staff.setAdress(Rest.mStaff.getAdress());
		
		staff.setJob(Rest.mStaff.getJob());
		staff.setName(Rest.mStaff.getName());
		staff.setPhone(Rest.mStaff.getPhone());
		
		staff.setPw(pw1.getText().toString());
		
		EditUserTask update = new EditUserTask(context, "updateStaff", staff
			   );
		update.execute();
	}
	
}
