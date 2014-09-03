package com.hp.map;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.hp.domain.Schedule;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.hp.order_manager.OrdersManagerDetailArrayAdapter;
import com.hp.rest.Rest;
import com.hp.sale_order.DialogArrayAdapter;

import com.sun.jersey.api.client.ClientResponse;

import android.annotation.SuppressLint;
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

import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SaleOrdersManagerActivity extends MainMenuActivity implements OnClickListener, DateWatcher {

	private List<TakeOrder> takeOrderList = new ArrayList<TakeOrder>();
	private List<TakeOrder> takeOrderListFilter = new ArrayList<TakeOrder>();
	private boolean filter = false;
	
	private ListView ordersListView;

	private EditText id_search;
	private EditText name_search;

	private OrdersManagerArrayAdapter adapter;

	private EditText start;
	private EditText end;
	
	private String result_string_start;
	private String result_string_end;
	
	private Context context = this;
	
	private ImageButton new_sale_order;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sale_order_manager);
		
		start = (EditText)findViewById(R.id.start);
		end = (EditText)findViewById(R.id.end);
		
		new_sale_order = (ImageButton)findViewById(R.id.new_sale_order);
		new_sale_order.setOnClickListener(this);
		
		getOrderList();
		addListView();

		id_search = (EditText) findViewById(R.id.id_search);

		id_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				SaleOrdersManagerActivity.this.adapter.getFilter().filter(cs);
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
				TakeOrder selectedValue = (TakeOrder) ordersListView.getAdapter().getItem(position);
		    	 addCustomerDialog(selectedValue);
				
			}
		});
	}

	public void addCustomerDialog(final TakeOrder selectedValue){
		
		Intent intent = new Intent(getApplicationContext(),
				SaleOrderDetail_Main_Activity.class);
		intent.putExtra("ORDER_ID", selectedValue.getId());

		startActivity(intent);
		
	}
	
	public List<TakeOrder> getOrderList(){
		// GET From server

				ClientResponse response = Rest.mService.path("webresources")
						.path("getSaleOrderList").accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, Rest.mStaff.getId());
				System.out.println("________________ " + response.toString());

				if (response.getStatus() != 200) {

					return null;
				}

				String re = response.getEntity(String.class);
				System.out.println("________________ " + re);

				// pair to object
				ObjectMapper mapper = new ObjectMapper();

				try {
					// File jsonFile = new File(jsonFilePath);
					takeOrderList = mapper.readValue(re, TypeFactory.defaultInstance()
							.constructCollectionType(List.class, TakeOrder.class));
					// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
				} catch (JsonGenerationException e) {
					e.printStackTrace();
					return null;
				} catch (JsonMappingException e) {
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
				
				return takeOrderList;
	}
	
	public List<TakeOrder> getOrderList(boolean filter){
		if(filter == false){
			return takeOrderList;
		}
		
		else
			return takeOrderListFilter;
	}
	public void onClick(View v) {

		//New sale order
		if(v == new_sale_order){
			addNewSaleOrderDialog(null);
		}
	}

	public void addNewSaleOrderDialog(final TakeOrder selectedValue){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Tạo mới hóa đơn bán hàng bằng ");
	
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Tạo mới bằng tay");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// show the map
//				Intent intent = new Intent(getApplicationContext(),
//						SaleOrderDetail_Main_Activity.class);
//				intent.putExtra("ORDER_ID", selectedValue.getmID());
//
//				startActivity(intent);
		        
				dialog.dismiss();
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hóa đơn đặt hàng");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				loadTakeOrderDialog(null);
				//commitDialog(selectedValue);
				dialog.dismiss();
			}
		});
		dialog.show();
		
	}
	
	String selectedValue = null;
	public void loadTakeOrderDialog(String pDate){
		// Check the internet
		if(isOnline()){
			System.out.println("Internet access!!____________________");
		}
		else{
			System.out.println("NO Internet access!!____________________");
			Toast.makeText(context, "No internet access, please try again later!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.schedule_dialog, null, false);
		dialog.setContentView(v);
		final ListView listViewCus = (ListView)dialog.findViewById(R.id.list_view_cus);
		
		dialog.setTitle("Chọn hóa đơn đặt");
		
		// List Take order -------------------------------------------
		//Get getTakeOrderList  ========================================
		ClientResponse response = Rest.mService.path("webresources")
				.path("getTakeOrderList").accept("application/json")
				.type("application/json")
				.post(ClientResponse.class, Rest.mStaff.getId());
		System.out.println("________________ " + response.toString());

		if (response.getStatus() != 200) {

			return ;
		}

		String re = response.getEntity(String.class);
		System.out.println("________________ " + re);

		// pair to object
		ObjectMapper mapper = new ObjectMapper();
		List<TakeOrder> takeOrderList2;
		try {
			// File jsonFile = new File(jsonFilePath);
			takeOrderList2 = mapper.readValue(re, TypeFactory.defaultInstance()
					.constructCollectionType(List.class, TakeOrder.class));
			// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return ;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return ;
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		String[] take_order_id_list = new String[]{};
		
		for(int i = 0; i < takeOrderList2.size(); i++){
			take_order_id_list = append(take_order_id_list, takeOrderList2.get(i).getId());
			
		}
		
		//ListView listViewCus = (ListView)findViewById(R.id.list_view_cus);
		listViewCus.setAdapter(new DialogArrayAdapter(context, android.R.layout.simple_list_item_1, take_order_id_list));
		//listViewCus.setBackgroundResource(R.drawable.artists_list_backgroundcolor);
		listViewCus.setSelector( R.drawable.artists_list_backgroundcolor);
		listViewCus.setOnItemClickListener(new OnItemClickListener()
		{
		     @Override
		     public void onItemClick(AdapterView<?> a, View v,int position, long id) 
		     {
		    	 selectedValue = (String) listViewCus.getAdapter().getItem(position);
//		    	 
//		          //Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_LONG).show();
//		    	// custom dialog

		      }
		});
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check the internet
				if(isOnline()){
					System.out.println("Internet access!!____________________");
				}
				else{
					System.out.println("NO Internet access!!____________________");
					Toast.makeText(context, "No internet access, please try again later!", Toast.LENGTH_SHORT).show();
					return;
				}
							
				//================= put in server======================================
				
				//Post
				ClientResponse response = Rest.mService.path("webresources").path("putSaleOrder").accept("application/json")
						.type("application/json").post(ClientResponse.class, selectedValue);
						
				if (response.getStatus() != 200) {
		            throw new RuntimeException("Failed : HTTP error code : "
		                    + response.getStatus());
		        }
				if (response.getStatus() != 200) {

					return;
				}
		        String output = response.toString();
		        System.out.println("Server response .... \n");
		        System.out.println(output);
		        
				/////==================================================================
				
				dialog.dismiss();
				
				//Reload
				getOrderList();
				adapter = new OrdersManagerArrayAdapter(context,
						android.R.layout.simple_list_item_1, takeOrderList);
				ordersListView.setAdapter(adapter);
			}
		});

		dialog.show();
		
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
		
		for(int i = 0; i < takeOrderList.size(); i++){
			Date compare = null;
			try {
				if(takeOrderList.get(i).getOrderEstablishDate() == null)
					continue;
				String date = takeOrderList.get(i).getOrderEstablishDate().toString();
				
				compare = dateFormat1.parse(date);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(compare);
			if(compare.after(startDate) 
					&& compare.before(endDate)){
				
				takeOrderListFilter.add(takeOrderList.get(i));
				System.out.println(takeOrderList.get(i).getId());
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
	
	@SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
}
