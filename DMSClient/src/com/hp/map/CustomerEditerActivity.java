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

public class CustomerEditerActivity extends CustomerAdditionActivity {
	
	
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
		
		cus_id.setEnabled(false);
		
		cus_id.setText(CustomerListActivity.customer.getMaDoiTuong());
		cus_name.setText(CustomerListActivity.customer.getDoiTuong());
		cus_state.setText(CustomerListActivity.customer.getTinhThanh());
		cus_line.setText(CustomerListActivity.customer.getTuyenBanHangThu() + "");
		cus_area.setText(CustomerListActivity.customer.getX());
		cus_address.setText(CustomerListActivity.customer.getDiaChi());
		cus_phone.setText(CustomerListActivity.customer.getDienThoai() +"");
		cus_fax.setText(CustomerListActivity.customer.getFax());
		cus_note.setText(CustomerListActivity.customer.getGhiChu());
		
	}
		
	public void getCustomer(){
			//String id = cus_id.getText().toString();
			String name = cus_name.getText().toString();
			String state = cus_state.getText().toString();
			String line = cus_line.getText().toString();
			String area = cus_area.getText().toString();
			String address = cus_address.getText().toString();
			String phone = cus_phone.getText().toString();
			String fax = cus_fax.getText().toString();
			String note = cus_note.getText().toString();
			
			CustomerListActivity.customer.setDiaChi(address);
			CustomerListActivity.customer.setDienThoai(phone);
			CustomerListActivity.customer.setDoiTuong(name);
			CustomerListActivity.customer.setFax(fax);
			CustomerListActivity.customer.setGhiChu(note);
			CustomerListActivity.customer.setTinhThanh(state);
			CustomerListActivity.customer.setX(area);
			CustomerListActivity.customer.setTuyenBanHangThu(line);
			//insert
			insertCustomer(CustomerListActivity.customer);
		
	}
	
	public void insertCustomer(Customer customer){
		ModifyCustomerTask insertData = new ModifyCustomerTask(context, "updateCustomer", customer, true);
		insertData.execute();
		
		//loading
		GetCustomerListTask getData = new GetCustomerListTask(context, "getCustomersListStart", Rest.mStaff.getId(),
			    true, customer);
        getData.execute();
			
		
	}

	
}
