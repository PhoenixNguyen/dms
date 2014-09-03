package com.hp.map;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.domain.Customer;
import com.hp.map.R;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerAdditionActivity extends MainMenuActivity{
	
	public Context context = this;
	
	public EditText cus_id;
	public EditText cus_name;
	public EditText cus_state;
	public EditText cus_line;
	public EditText cus_area;
	public EditText cus_address;
	public EditText cus_phone;
	public EditText cus_fax;
	public EditText cus_note;
		
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.customer_addition);
				
		cus_id = (EditText)findViewById(R.id.cus_id);
		cus_name = (EditText)findViewById(R.id.cus_name);
		cus_state = (EditText)findViewById(R.id.cus_state);
		cus_line = (EditText)findViewById(R.id.cus_line);
		cus_area = (EditText)findViewById(R.id.cus_area);
		cus_address = (EditText)findViewById(R.id.cus_address);
		cus_phone = (EditText)findViewById(R.id.cus_phone);
		cus_fax = (EditText)findViewById(R.id.cus_fax);
		cus_note = (EditText)findViewById(R.id.cus_note);
	
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.customer_manager_menu, menu);
		
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
//        case R.id.action_search:
//        	
//            return true;
        case R.id.action_save:
        	getCustomer();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	        
	    case R.id.action_save:
        	getCustomer();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	public void getCustomer(){
			String id = cus_id.getText().toString();
			String name = cus_name.getText().toString();
			String state = cus_state.getText().toString();
			String line = cus_line.getText().toString();
			String area = cus_area.getText().toString();
			String address = cus_address.getText().toString();
			String phone = cus_phone.getText().toString();
			String fax = cus_fax.getText().toString();
			String note = cus_note.getText().toString();
			
			Customer customer = new Customer(id, name
					, Rest.mStaff.getId(), state
					, line, area
					, address, phone
					, fax, note
					, 0d, 0d);
			//insert
			insertCustomer(customer);
		
	}
	
	public void insertCustomer(Customer customer){
		
		ModifyCustomerTask insertData = new ModifyCustomerTask(context, "insertCustomer", customer, true);
		insertData.execute();
		
		//loading
		GetCustomerListTask getData = new GetCustomerListTask(context, "getCustomersListStart", Rest.mStaff.getId(),
			    true, customer);
        getData.execute();
	      
	}

	
	
}
