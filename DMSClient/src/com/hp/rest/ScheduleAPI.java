package com.hp.rest;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.ProgressDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Customer;
import com.hp.domain.Schedule;
import com.hp.map.CustomerListActivity;
import com.hp.map.CustomerMapActivity;
import com.hp.map.R;
import com.hp.map.Schedule_CalendarActivity;
import com.hp.schedule.DialogArrayAdapter;
import com.hp.schedule.ScheduleViewArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

public class ScheduleAPI {
	public static List<Schedule> scheduleList;

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
	public static class GetScheduleListTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method;
		String info;

		ListView listView;
		Schedule_CalendarActivity activity;
		

		public GetScheduleListTask(Context context, String method, String info,
				ListView listView, Schedule_CalendarActivity activity) {
			this.context = context;
			this.method = method;
			this.info = info;
			
			this.listView = listView;
			this.activity = activity;
		}

		ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(context, "", "Đang tải ... ", true);
		}

		protected String doInBackground(Void... params) {
			// do something
			if (CheckingInternet.isOnline()) {
				System.out.println("Internet access!!____________________");
			} else {
				
				System.out.println("NO Internet access!!____________________");

				return "nointernet";

			}

			// Getting
			ClientResponse response = Rest.mService.path("webresources")
					.path(method).accept("application/json")
					.type("application/json").post(ClientResponse.class, info);
			System.out.println("________________ " + response.toString());

			if (response.getStatus() != 200 || response.getLength() < 2 ) {

				return "nodata";
			} else {

				String re = response.getEntity(String.class);
				System.out.println("________________ " + re);

				// Convert
				if (ConvertStringToObjectList(re))
					return "success";
				else
					return "nodata";
			}
			// =====================================================================================

		}

		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				// do something
				
				Schedule[] listV = new Schedule[]{};
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i = 0; i < scheduleList.size(); i++){
					Calendar cal = Calendar.getInstance();
					cal.setTime(scheduleList.get(i).getTime());
					cal.add(Calendar.HOUR, -7);
					scheduleList.get(i).setTime(Timestamp.valueOf(dateFormat.format(cal.getTime())));
					
				}
				
				
				listView.setAdapter(new ScheduleViewArrayAdapter(context, android.R.layout.simple_list_item_1, scheduleList));
				
				listView.setOnItemClickListener(new OnItemClickListener()
				{
				     @Override
				     public void onItemClick(AdapterView<?> a, View v,int position, long id) 
				     {
				    	 Schedule selectedValue = (Schedule) listView.getAdapter().getItem(position);
				    	 activity.addCustomerDialog(selectedValue);
//				    	 
//				          //Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_LONG).show();
//				    	// custom dialog

				      }
				});
				
				
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("nodata")) {
				Toast.makeText(context, "Không có dữ liệu!", Toast.LENGTH_SHORT)
						.show();
				
				
			} else {
				
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

			}
			
			dialog.dismiss();
		}

		public boolean ConvertStringToObjectList(String input) {
			// pair to object
			ObjectMapper mapper = new ObjectMapper();

			try {
				scheduleList = mapper.readValue(
						input,
						TypeFactory.defaultInstance().constructCollectionType(
								List.class, Schedule.class));
				// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
			} catch (JsonGenerationException e) {
				e.printStackTrace();
				return false;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

			return true;
		}

	}
	
	
	// ///////////////// DELETE AND INSERT AND
		// EDIT/////////////////////////////////////////////////////////////////////////////
		public static class ModifyScheduleTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String info;

			Schedule_CalendarActivity activity;
			
			public ModifyScheduleTask(Context context, String method,
					String info, Schedule_CalendarActivity activity) {
				this.context = context;
				this.method = method;
				this.info = info;
				this.activity = activity;
			}

			ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(context, "", "Đang xử lý ... ", true);
			}

			protected String doInBackground(Void... params) {
				// do something
				if (CheckingInternet.isOnline()) {
					System.out.println("Internet access!!____________________");
				} else {
					
					System.out.println("NO Internet access!!____________________");

					return "nointernet";

				}

				// Deleting
				ClientResponse response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, info);

				String output = response.toString();
				System.out.println("input 1: " + output);

				if ((response.getStatus() == 200)
						&& output.compareTo("status:0") != 0) {

					return "success";
				} else {

					return "fail";
				}
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
						Toast.makeText(context, "Đã xóa ", Toast.LENGTH_SHORT)
								.show();
					

					
				} else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("fail")) {
					
						Toast.makeText(
								context,
								"Không thể xóa dữ liệu. Lịch này đã tồn tại ở dữ liệu khác!",
								Toast.LENGTH_SHORT).show();
					
				} else {
					
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

				}
				
				activity.dialog.dismiss();
				dialog.dismiss();
			}

			public String ConvertObjectToString(Customer customer) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(customer);

				} catch (JsonGenerationException ex) {

					ex.printStackTrace();

				} catch (JsonMappingException ex) {

					ex.printStackTrace();

				} catch (IOException ex) {

					ex.printStackTrace();

				}

				return cusStr;
			}

		}
		
		
		public static List<Customer> customerList;

		// ///////////////// LOAD
		// /////////////////////////////////////////////////////////////////////////////
		public static class GetCustomerListInScheduleTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String info;

			ListView listViewCus;
			Schedule_CalendarActivity activity;
			public GetCustomerListInScheduleTask(Context context, String method, String info, ListView listViewCus,
					Schedule_CalendarActivity activity) {
				this.context = context;
				this.method = method;
				this.info = info;
				this.listViewCus = listViewCus;
				this.activity = activity;
			}

			
			ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(context, "", "Đang tải ... ", true);
			}

			protected String doInBackground(Void... params) {
				// do something
				if (CheckingInternet.isOnline()) {
					System.out.println("Internet access!!____________________");
				} else {
					
					System.out.println("NO Internet access!!____________________");

					return "nointernet";

				}

				// Getting
				ClientResponse response = Rest.mService.path("webresources")
						.path(method).accept("application/json")
						.type("application/json").post(ClientResponse.class, info);
				System.out.println("________________ " + response.toString());

				if (response.getStatus() != 200) {

					return "nodata";
				} else {

					String re = response.getEntity(String.class);
					System.out.println("________________ " + re);

					// Convert
					if (ConvertStringToObjectList(re))
						return "success";
					else
						return "nodata";
				}
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
					listViewCus.setAdapter(new DialogArrayAdapter(context, android.R.layout.simple_list_item_1, customerList));
					
					listViewCus.setOnItemClickListener(new OnItemClickListener()
					{
					     @Override
					     public void onItemClick(AdapterView<?> a, View v,int position, long id) 
					     {


					      }
					});
					
					
				} else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("nodata")) {
					Toast.makeText(context, "Không có dữ liệu!", Toast.LENGTH_SHORT)
							.show();
				} else {
					
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

				}
				
				activity.dialogAdd.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				activity.dialogAdd.show();
				dialog.dismiss();
			}

			public boolean ConvertStringToObjectList(String input) {
				// pair to object
				ObjectMapper mapper = new ObjectMapper();

				try {
					customerList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, Customer.class));
					// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
				} catch (JsonGenerationException e) {
					e.printStackTrace();
					return false;
				} catch (JsonMappingException e) {
					e.printStackTrace();
					return false;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}

				return true;
			}

		}
		
		// ///////////////// PUT
		// /////////////////////////////////////////////////////////////////////////////
		public static class PutScheduleListTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			List<Schedule> info;

			Schedule_CalendarActivity activity;
			

			public PutScheduleListTask(Context context, String method, List<Schedule> info,
					Schedule_CalendarActivity activity) {
				this.context = context;
				this.method = method;
				this.info = new ArrayList<Schedule>();
				this.info.addAll(info);
				
				this.activity = activity;
			}

			ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(context, "", "Đang xử lý ... ", true);
			}

			protected String doInBackground(Void... params) {
				// do something
				if (CheckingInternet.isOnline()) {
					System.out.println("Internet access!!____________________");
				} else {
					
					System.out.println("NO Internet access!!____________________");

					return "nointernet";

				}

				// Getting
				ClientResponse response = Rest.mService.path("webresources")
						.path(method).accept("application/json")
						.type("application/json").post(ClientResponse.class, ConvertListToString(info));
				System.out.println("________________ " + response.toString());

				if (response.getStatus() != 200  ) {

					return "nodata";
				} else {

					String re = response.getEntity(String.class);
					System.out.println("________________ " + re);

					// Convert
					if (re.compareTo("0") != 0)
						return "success";
					else
						return "nodata";
				}
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
					Toast.makeText(context, "Đã lên lịch!", Toast.LENGTH_SHORT).show();
					
					
				} else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("nodata")) {
					Toast.makeText(context, "Giá trị trống", Toast.LENGTH_SHORT).show();
					
					
				} else {
					
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

				}
				
				//destroy
				Schedule_CalendarActivity.mTakeCustomersList.clear();
				activity.dialogAdd.dismiss();
				
				dialog.dismiss();
			}

			public String ConvertListToString(List<Schedule> input) {
				// pair to object
				ObjectMapper mapper = new ObjectMapper();
				String listStr;
				try {
					listStr = mapper.writeValueAsString(input);
					
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

				return listStr;
			}

		}
}
