package com.hp.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Customer;
import com.hp.gps.MapLocation;
import com.hp.rest.CustomerAPI;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;

public class CustomerListActivity extends MainMenuActivity implements OnItemSelectedListener{
	
	public static String LOAD_CUSTOMER = "getAllCustomers";
	
	private ListView listView;
	private Context context = this;
	
	private EditText input_text;
	
	public CustomerArrayAdapter customerAdapter;
	
	public static Customer customer;
	
	private Spinner cities_spinner;
	
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.customer_list);
		
		// Set up action bar.
//        final ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowHomeEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(false);
        
		cities_spinner = (Spinner) findViewById(R.id.cities_spinner);
		input_text = (EditText)findViewById(R.id.input_text);
		
		cities_spinner.setOnItemSelectedListener(this);
		spinnerAdapter(cities_spinner);
		
		input_text.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				customerAdapter.getFilter().filter(s);
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		System.out.println("____Preparing UPDAte list");
		addListView();
	}

//	public void onResume(){
//		super.onResume();
//		this.onCreate(null);
//	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order_menu, menu);
		
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
//        case R.id.action_search:
//        	
//            return true;
        case R.id.action_add:
        	insertCustomer();
            return true;
//        case R.id.action_refresh:
//        	refreshCustomers();
//            return true;        
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
	        
	    case R.id.action_add:
        	insertCustomer();
            return true;
//	    case R.id.action_refresh:
//        	refreshCustomers();
//            return true;   
	    default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	private void spinnerAdapter(Spinner spinner) {
		
		List<String> cities = new ArrayList<String>();
		cities.addAll(MapLocation.getAllCityOfVietNam());
		cities.add(0, "Tất cả");
		
		ArrayAdapter<String> allCities = new ArrayAdapter<String>(this,
			     android.R.layout.simple_spinner_dropdown_item, cities);
		
		// Apply the adapter to the spinner
		spinner.setAdapter(allCities);
		
	}
	
	public void refreshCustomers(){
		GetCustomerListTask getData = new GetCustomerListTask(context, LOAD_CUSTOMER, Rest.mStaff.getId(), //getCustomersListStart
			    true, customerAdapter, listView, this);
        getData.execute();
	}
	public void addListView(){
		//System.out.println("____UPDAte list " + Rest.customerList.size());
		//Update list customer
		//Rest.getCustomersList(Rest.mStaff.getId());
		GetCustomerListTask getData = new GetCustomerListTask(context, LOAD_CUSTOMER, Rest.mStaff.getId(), //getCustomersListStart
				listView, customerAdapter, customer,
				this, true);
    	getData.execute();
    	
	}
	
	public void choiceDialog(final Customer customer){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_comfirm_dialog, null, false);
		dialog.setContentView(v);
		dialog.setTitle("Lựa chọn của bạn");
		
		final Button edit = (Button)dialog.findViewById(R.id.detail);
		final Button detail = (Button)dialog.findViewById(R.id.delete); detail.setText("Xem chi tiết");
		final Button delete = (Button)dialog.findViewById(R.id.edit);
		final Button cancel = (Button)dialog.findViewById(R.id.cancel);
		
		edit.setVisibility(View.INVISIBLE);
		delete.setVisibility(View.INVISIBLE);
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(context, CustomerEditerActivity.class));
				dialog.dismiss();
			}
		});
		
		detail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				        Intent t = new Intent(context, CustomerMapActivity.class);
				        t.putExtra("POSITION_CLICK", customer.getMaDoiTuong());
				        
				        startActivity(t);
				        dialog.dismiss();
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						comfirmDeleteDialog(customer);
						dialog.dismiss();
					}
				});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		
		dialog.show();
	}
	
	public void comfirmDeleteDialog(final Customer customer){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_comfirm_dialog, null, false);
		dialog.setContentView(v);
		dialog.setTitle("Lựa chọn của bạn");
		
		final Button edit = (Button)dialog.findViewById(R.id.edit);
		final Button detail = (Button)dialog.findViewById(R.id.detail);
		final Button delete = (Button)dialog.findViewById(R.id.delete);
		delete.setVisibility(View.GONE);
		final Button cancel = (Button)dialog.findViewById(R.id.cancel);
		cancel.setVisibility(View.GONE);
		
		edit.setText("Chấp nhận");
		detail.setText("Hủy");
		
		edit.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteCustomer(customer);
				dialog.dismiss();
			}
		});
		detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	
	public void insertCustomer(){
		startActivity(new Intent(this, CustomerAdditionActivity.class));
		
	}
	
	public void deleteCustomer(Customer customer){
		
		ModifyCustomerTask deleteData = new ModifyCustomerTask(context, "deleteCustomer", customer, false);
		deleteData.execute();
    	
        GetCustomerListTask getData = new GetCustomerListTask(context, LOAD_CUSTOMER, Rest.mStaff.getId(), //getCustomersListStart
		    true, customerAdapter, listView, this);
        getData.execute();
		

	}
	
	@SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		String spinnerValue = cities_spinner.getSelectedItem().toString();
		
		List<Customer> filters = new ArrayList<Customer>();
		
		if(!spinnerValue.equalsIgnoreCase("Tất cả"))
			for(Customer cus : CustomerAPI.customerList){
				if(cus.getTinhThanh() != null && cus.getTinhThanh().equalsIgnoreCase(spinnerValue)){
					filters.add(cus);
				}
			}
		else{
			filters.addAll(CustomerAPI.customerList);
		}
		
		//List view
		listView = (ListView)findViewById(R.id.list);
		customerAdapter = new CustomerArrayAdapter(context, filters);
		listView.setAdapter(customerAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener()
		{
		     @Override
		     public void onItemClick(AdapterView<?> a, View v,int position, long id) 
		     {
		    	customer = (Customer) listView.getAdapter().getItem(position);
		    	
		    	//open dialog
		    	choiceDialog(customer);
		      }
		});
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
		
}
