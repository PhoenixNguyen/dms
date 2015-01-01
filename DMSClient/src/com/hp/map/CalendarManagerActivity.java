package com.hp.map;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hp.calendar.CalendarArrayAdapter;
import com.hp.domain.Calendar;
import com.hp.domain.DateTimePicker;
import com.hp.domain.DateTimePicker.DateWatcher;
import com.hp.rest.CalendarAPI;
import com.hp.rest.CalendarAPI.GetCalendarTask;
import com.hp.rest.CalendarAPI.ModifyCalendarTask;
import com.hp.rest.Rest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CalendarManagerActivity extends MainMenuActivity implements OnClickListener, DateWatcher {

	private TextView id[];
	//public static List<TakeOrder> takeOrderList = new ArrayList<TakeOrder>();
	private List<Calendar> calendarListFilter = new ArrayList<Calendar>();
	private boolean filter = false;
	
	public ListView calendarListView;

	private EditText id_search;
	private EditText name_search;

	public CalendarArrayAdapter adapter;

	private EditText start;
	private EditText end;
	
	private String result_string_start;
	private String result_string_end;
	
	public Context context = this;
	
	public TextView title;
	
	public Class<? extends Activity> activityClass;
	public String getList;
	public String deleteValue;
	
	public static Calendar selectedValue;
	
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orders_manager);
		
		start = (EditText)findViewById(R.id.start);
		end = (EditText)findViewById(R.id.end);
		
		title = (TextView)findViewById(R.id.title);
		
		//INIT
		init();
		
		getCalendarList();
		//addListView();

		id_search = (EditText) findViewById(R.id.id_search);

		id_search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				// When user changed the Text
				CalendarManagerActivity.this.adapter.getFilter().filter(cs);
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
		getList = "getCalendarList";
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
        	newCalendar();
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

	        break;
	        
	    case R.id.action_add:
	    	newCalendar();
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

		

		if (getCalendarList(filter).size() == 0) {
			return;
		}
		// List<Product> productsList = new ArrayList<Product>();
		// Product product = new Product(1, "Welcome", "Welcome",
		// "Choose providers list");
		// productsList.add(product);

		calendarListView = (ListView) findViewById(R.id.list_view_product);
		adapter = new CalendarArrayAdapter(this,
				android.R.layout.simple_list_item_1, getCalendarList(filter));
		calendarListView.setAdapter(adapter);

		calendarListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				System.out.println("Click!");
				selectedValue = (Calendar) calendarListView.getAdapter().getItem(position);
				if(selectedValue.getStatus() == 0)
					addCustomerDialog(selectedValue);
				else
					Toast.makeText(context, "Lịch công tác đã đề nghị không thể tác động!", Toast.LENGTH_LONG).show();
				
			}
		});
	}
	
	public Dialog dialog;
	public void addCustomerDialog(final Calendar selectedValue){
		dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.submit_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Lựa chọn của bạn: ");
		
		final EditText report = (EditText) dialog.findViewById(R.id.report);
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Hoàn thành");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selectedValue.setStatus(1);
				if(report != null && !report.getText().toString().equals(""))
					selectedValue.setReport(report.getText().toString());
				else{
					Toast.makeText(context, "Hãy nhập nội dung báo cáo", Toast.LENGTH_SHORT).show();
					return;
				}
				
				ModifyCalendarTask editCalendar = new ModifyCalendarTask(context, ModifyCalendarTask.ACTION_EDIT, "updateCalendar", selectedValue, adapter, calendarListView, CalendarManagerActivity.this, null);
				editCalendar.execute();
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Xóa lịch");
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
	
	public Dialog dialogCommit;
	public void commitDialog(final Calendar selectedValue){
		dialogCommit = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialogCommit.setContentView(v);
		
		dialogCommit.setTitle("Cảnh báo, xóa lịch! ");
	
		Button dialogButton = (Button) dialogCommit.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Chấp nhận");
		// if button is clicked, close the custom dialog
		
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//Sys
				
				ModifyCalendarTask deleteCalendar = new ModifyCalendarTask(context, ModifyCalendarTask.ACTION_DELETE, "deleteCalendar", selectedValue, adapter, calendarListView, CalendarManagerActivity.this, null);
				deleteCalendar.execute();
								
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialogCommit.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							
				dialogCommit.dismiss();
			}
		});
		dialogCommit.show();
		
	}
	
	public void getCalendarList(){
		// GET From server

		GetCalendarTask getData = new GetCalendarTask(context, getList, Rest.mStaff.getId(), this );
        getData.execute();
	        
	}
	
	public List<Calendar> getCalendarList(boolean filter){
		if(filter == false){
			return CalendarAPI.calendarList;
		}
		
		else
			return calendarListFilter;
	}
	public void onClick(View v) {
		for (int i = 0; i < getCalendarList(filter).size(); i++)
			if (v == id[i]) {
				System.out.println("click: " + i + " "
						+ id[i].getText().toString());
//				Intent intent = new Intent(getApplicationContext(),
//						TakeOrdersDetailManagerActivity.class);
//				intent.putExtra("ORDER_ID", id[i].getText().toString());
//				
//				startActivity(intent);
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
         @SuppressLint("SimpleDateFormat")
		public void onClick(View v) {
               mDateTimePicker.clearFocus();
               // TODO Auto-generated method stub
               //reset
               if(mDateTimePicker.getYear()== 0){
            	   start.setText("Từ");
            	   result_string_start = null;
               }
               else{
               
	               result_string_start = String.valueOf(mDateTimePicker.getDay()) + "/" + mDateTimePicker.getMonth() + "/" + String.valueOf(mDateTimePicker.getYear());
	                                                //+ "  " + String.valueOf(mDateTimePicker.getHour()) + ":" + String.valueOf(mDateTimePicker.getMinute()+":0");
	               
	               SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
	               try {
	            	   start.setText(df.format(df2.parse(result_string_start)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               }
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
         @SuppressLint("SimpleDateFormat")
		public void onClick(View v) {
               mDateTimePicker.clearFocus();
               // TODO Auto-generated method stub
               //reset
               if(mDateTimePicker.getYear()== 0){
            	   end.setText("Đến");
            	   result_string_end = null;
               }
               else{
	               result_string_end = String.valueOf(mDateTimePicker.getDay()) + "/" + mDateTimePicker.getMonth() + "/" +  String.valueOf(mDateTimePicker.getYear());
	                                                //+ "  " + String.valueOf(mDateTimePicker.getHour()) + ":" + String.valueOf(mDateTimePicker.getMinute()+":0");
	               
	               SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
	               try {
	            	   end.setText(df.format(df2.parse(result_string_end)));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               }
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

	@SuppressLint("SimpleDateFormat")
	public void button_filter(View view){
		//Toast.makeText(this, "Chức năng đang xây dựng", Toast.LENGTH_SHORT).show();
		
		if(CalendarAPI.calendarList == null || CalendarAPI.calendarList.size() <= 0)
			return;
		
		filter = true;
		calendarListFilter.clear();
		
		DateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Date startDate = null;
		Date endDate = null;
		try {
			if(result_string_start != null)
				startDate = dateFormat2.parse(result_string_start);
			
			if(result_string_end != null )
				endDate = dateFormat2.parse(result_string_end);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(startDate);
		
		for(int i = 0; i < CalendarAPI.calendarList.size(); i++){
			
			if(CalendarAPI.calendarList.get(i).getCalendarDate() == null)
				continue;
			Date compare = CalendarAPI.calendarList.get(i).getCalendarDate();
				
			System.out.println(compare);
			if(checkDate(compare, startDate, endDate)){
				
				calendarListFilter.add(CalendarAPI.calendarList.get(i));
				//System.out.println(CalendarAPI.calendarList.get(i).getStt());
			}
		}
		
		adapter = new CalendarArrayAdapter(this,
				android.R.layout.simple_list_item_1, calendarListFilter);
		calendarListView.setAdapter(adapter);
		//onResume();
		
	}
	
	private boolean checkDate(Date compare, Date startDate, Date endDate){
		
		Date end = null;
		if(endDate != null){
			java.util.Calendar c = java.util.Calendar.getInstance();
			c.setTime(endDate);
			c.add(java.util.Calendar.DATE, 1);
			
			end = c.getTime();
		}
		if(compare != null && startDate != null && end != null && compare.after(startDate) 
				&& compare.before(end)){
			return true;
		}
		if(compare != null && startDate != null && end == null && compare.after(startDate) 
				){
			return true;
		}
		if(compare != null && startDate == null && end != null  
				&& compare.before(end)){
			return true;
		}
		if(compare != null && startDate == null && end == null  
				){
			return true;
		}
		return false;
	}
	public void onDateChanged(Calendar c) {
		// TODO Auto-generated method stub
		
	}
	

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		return cm.getActiveNetworkInfo() != null
				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	
	public void newCalendar(){
		startActivity(new Intent(this, CalendarAdditionActivity.class));
	}

	@Override
	public void onDateChanged(java.util.Calendar c) {
		// TODO Auto-generated method stub
		
	}
}
