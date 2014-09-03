package com.hp.map;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hp.domain.Customer;
import com.hp.domain.DateTimePicker;
import com.hp.domain.DateTimePicker.DateWatcher;
import com.hp.domain.Product;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.hp.order_manager.OrdersManagerDetailArrayAdapter;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;
import com.hp.rest.TakeOrderAPI;
import com.hp.rest.TakeOrderAPI.GetTakeOrderTask;
import com.hp.rest.TakeOrderAPI.ModifyTakeOrderTask;
import com.hp.schedule.ListViewSchedules;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Activity;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.LayoutInflater;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TakeOrdersManagerActivity extends MainMenuActivity implements OnClickListener, DateWatcher {

	private TextView id[];
	//public static List<TakeOrder> takeOrderList = new ArrayList<TakeOrder>();
	private List<TakeOrder> takeOrderListFilter = new ArrayList<TakeOrder>();
	private boolean filter = false;
	
	public ListView ordersListView;

	private EditText id_search;
	private EditText name_search;

	public OrdersManagerArrayAdapter adapter;

	private EditText start;
	private EditText end;
	
	private String result_string_start;
	private String result_string_end;
	
	public Context context = this;
	
	public TextView title;
	
	public Class<? extends Activity> activityClass;
	public String getList;
	public String deleteValue;
	
	public static TakeOrder selectedValue;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orders_manager);
		
		start = (EditText)findViewById(R.id.start);
		end = (EditText)findViewById(R.id.end);
		
		title = (TextView)findViewById(R.id.title);
		
		//INIT
		init();
		
		getOrderList();
		//addListView();

		id_search = (EditText) findViewById(R.id.id_search);

		id_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				TakeOrdersManagerActivity.this.adapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
			}

		});
	}

	public void init(){
		activityClass = TakeOrdersDetailManagerActivity.class;
		getList = "getTakeOrderList";
		deleteValue = "deleteOrder";
	}

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
        	newTakeOrder();
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
	        
	    case R.id.action_add:
        	newTakeOrder();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	public void addListView() {

		// Check the internet
		if (isOnline()) {
			System.out.println("Internet access!!____________________");
		} else {
			System.out.println("NO Internet access!!____________________");
			Toast.makeText(this, "No internet access, please try again later!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		

		if (getOrderList(filter).size() == 0) {
			return;
		}
		// List<Product> productsList = new ArrayList<Product>();
		// Product product = new Product(1, "Welcome", "Welcome",
		// "Choose providers list");
		// productsList.add(product);

		ordersListView = (ListView) findViewById(R.id.list_view_product);
		adapter = new OrdersManagerArrayAdapter(this,
				android.R.layout.simple_list_item_1, getOrderList(filter));
		ordersListView.setAdapter(adapter);

		ordersListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				System.out.println("Click!");
				selectedValue = (TakeOrder) ordersListView.getAdapter().getItem(position);
		    	 addCustomerDialog(selectedValue);
				
			}
		});
	}
	
	public void addCustomerDialog(final TakeOrder selectedValue){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Lựa chọn của bạn: ");
	
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Hiển thị chi tiết");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// show the map
				Intent intent = new Intent(getApplicationContext(),
						activityClass);
								
				startActivity(intent);
		        
				dialog.dismiss();
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Xóa bản ghi");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				commitDialog(selectedValue);
				dialog.dismiss();
			}
		});
		dialog.show();
		
	}
	
	public Dialog dialog;
	public void commitDialog(final TakeOrder selectedValue){
		dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Cảnh báo, xóa bản ghi! ");
	
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Chấp nhận");
		// if button is clicked, close the custom dialog
		
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Sys
				ModifyTakeOrderTask deleteData = new ModifyTakeOrderTask(context, deleteValue, selectedValue, 
						adapter, ordersListView, TakeOrdersManagerActivity.this);
				deleteData.execute();
								
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							
				dialog.dismiss();
			}
		});
		dialog.show();
		
	}
	
	public void getOrderList(){
		// GET From server

		GetTakeOrderTask getData = new GetTakeOrderTask(context, getList, Rest.mStaff.getId(), this );
        getData.execute();
	        
	}
	
	public List<TakeOrder> getOrderList(boolean filter){
		if(filter == false){
			return TakeOrderAPI.takeOrderList;
		}
		
		else
			return takeOrderListFilter;
	}
	public void onClick(View v) {
		for (int i = 0; i < getOrderList(filter).size(); i++)
			if (v == id[i]) {
				System.out.println("click: " + i + " "
						+ id[i].getText().toString());
				Intent intent = new Intent(getApplicationContext(),
						TakeOrdersDetailManagerActivity.class);
				intent.putExtra("ORDER_ID", id[i].getText().toString());
				
				startActivity(intent);
			}
	}

	public void button_click_1(View view){ 
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(this);
        // Inflate the root layout
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.order_manager_dialog_set_time, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
        mDateTimePicker.setDateChangedListener(this);
                 
        // Update demo edittext when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
               mDateTimePicker.clearFocus();
               // TODO Auto-generated method stub
               result_string_start = mDateTimePicker.getMonth() + "/" + String.valueOf(mDateTimePicker.getDay()) + "/" + String.valueOf(mDateTimePicker.getYear())
                                                + "  " + String.valueOf(mDateTimePicker.getHour()) + ":" + String.valueOf(mDateTimePicker.getMinute()+":0");
               start.setText(result_string_start);
               mDateTimeDialog.dismiss();
         }
         });

         // Cancel the dialog when the "Cancel" button is clicked
         ((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                     // TODO Auto-generated method stub
                     mDateTimeDialog.cancel();
                }
         });

                // Reset Date and Time pickers when the "Reset" button is clicked
       
         ((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                      // TODO Auto-generated method stub
                      mDateTimePicker.reset();
                }
         });
                 
        // Setup TimePicker
        // No title on the dialog window
        mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set the dialog content view
        mDateTimeDialog.setContentView(mDateTimeDialogView);
        // Display the dialog
        mDateTimeDialog.show();                
	}

	public void button_click_2(View view){ 
        // Create the dialog
        final Dialog mDateTimeDialog = new Dialog(this);
        // Inflate the root layout
        final RelativeLayout mDateTimeDialogView = (RelativeLayout) getLayoutInflater().inflate(R.layout.order_manager_dialog_set_time, null);
        // Grab widget instance
        final DateTimePicker mDateTimePicker = (DateTimePicker) mDateTimeDialogView.findViewById(R.id.DateTimePicker);
        mDateTimePicker.setDateChangedListener(this);
                 
        // Update demo edittext when the "OK" button is clicked
        ((Button) mDateTimeDialogView.findViewById(R.id.SetDateTime)).setOnClickListener(new OnClickListener() {
         public void onClick(View v) {
               mDateTimePicker.clearFocus();
               // TODO Auto-generated method stub
               result_string_end = mDateTimePicker.getMonth() + "/" + String.valueOf(mDateTimePicker.getDay()) + "/" + String.valueOf(mDateTimePicker.getYear())
                                                + "  " + String.valueOf(mDateTimePicker.getHour()) + ":" + String.valueOf(mDateTimePicker.getMinute()+":0");
               end.setText(result_string_end);
               mDateTimeDialog.dismiss();
         }
         });

         // Cancel the dialog when the "Cancel" button is clicked
         ((Button) mDateTimeDialogView.findViewById(R.id.CancelDialog)).setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                     // TODO Auto-generated method stub
                     mDateTimeDialog.cancel();
                }
         });

                // Reset Date and Time pickers when the "Reset" button is clicked
       
         ((Button) mDateTimeDialogView.findViewById(R.id.ResetDateTime)).setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                      // TODO Auto-generated method stub
                      mDateTimePicker.reset();
                }
         });
                 
        // Setup TimePicker
        // No title on the dialog window
        mDateTimeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Set the dialog content view
        mDateTimeDialog.setContentView(mDateTimeDialogView);
        // Display the dialog
        mDateTimeDialog.show();                
	}

	public void button_filter(View view){
		
		if(result_string_start == null || result_string_end == null)
			return;
		
		filter = true;
		takeOrderListFilter.clear();
		
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat dateFormat2 = new SimpleDateFormat("MMM/dd/yyyy HH:mm:ss");
		
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = dateFormat2.parse(result_string_start);
			endDate = dateFormat2.parse(result_string_end);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(startDate);
		
		for(int i = 0; i < TakeOrderAPI.takeOrderList.size(); i++){
			Date compare = null;
			try {
				if(TakeOrderAPI.takeOrderList.get(i).getOrderEstablishDate() == null)
					continue;
				String date = TakeOrderAPI.takeOrderList.get(i).getOrderEstablishDate().toString();
				
				compare = dateFormat1.parse(date);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(compare);
			if(compare.after(startDate) 
					&& compare.before(endDate)){
				
				takeOrderListFilter.add(TakeOrderAPI.takeOrderList.get(i));
				System.out.println(TakeOrderAPI.takeOrderList.get(i).getId());
			}
		}
		
		adapter = new OrdersManagerArrayAdapter(this,
				android.R.layout.simple_list_item_1, takeOrderListFilter);
		ordersListView.setAdapter(adapter);
		//onResume();
		
	}
	@Override
	public void onDateChanged(Calendar c) {
		// TODO Auto-generated method stub
		
	}
	

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}
	
	public void newTakeOrder(){
		startActivity(new Intent(this, CustomerListActivity.class));
	}
}
