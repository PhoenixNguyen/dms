package com.hp.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.hp.calendar.CalendarArrayAdapter;
import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Calendar;
import com.hp.domain.Customer;
import com.hp.domain.Staff;
import com.hp.domain.TakeOrder;
import com.hp.map.CalendarAdditionActivity;
import com.hp.map.CalendarManagerActivity;
import com.hp.map.CustomerListActivity;
import com.hp.map.CustomerMapActivity;
import com.hp.map.InventoryManagerDetailActivity;
import com.hp.map.R;
import com.hp.map.TakeOrder_AmountActivity;
import com.hp.map.TakeOrder_ProductActivity;
import com.hp.map.TakeOrdersDetailManagerActivity;
import com.hp.map.TakeOrdersManagerActivity;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

public class CalendarAPI {
	public static List<Calendar> calendarList = new ArrayList<Calendar>();

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
//	public static class PutCalendarTask extends
//			AsyncTask<Void, Void, String> {
//		Context context;
//		String method1;
//		String method2;
//		String staff;
//		String content1;
//		String content2;
//		
//		TakeOrder_AmountActivity activity;
//		boolean update;
//
//		public PutTakeOrderTask(Context context, String method1, String method2, 
//				String content1, String content2, String staff,
//				TakeOrder_AmountActivity activity, boolean update) {
//			this.context = context;
//			this.method1 = method1;
//			this.method2 = method2;
//			this.staff = staff;
//			this.content1 = content1;
//			this.content2 = content2;
//			
//			this.activity = activity;
//			this.update = update;
//		}
//
//		
//		
//		ProgressDialog dialog;
//
//		protected void onPreExecute() {
//			dialog = ProgressDialog.show(context, "", "Đang xử lý ... ", true);
//		}
//
//		protected String doInBackground(Void... params) {
//			// do something
//			if (CheckingInternet.isOnline()) {
//				System.out.println("Internet access!!____________________");
//			} else {
//				
//				System.out.println("NO Internet access!!____________________");
//
//				return "nointernet";
//
//			}
//
//			// Getting
//			ClientResponse response1 = Rest.mService.path("webresources")
//					.path(method1).accept("application/json")
//					.type("application/json").post(ClientResponse.class, content1);
//			System.out.println("________________ " + response1.toString());
//
//			ClientResponse response2 = Rest.mService.path("webresources")
//					.path(method2).accept("application/json")
//					.type("application/json").post(ClientResponse.class, content2);
//			System.out.println("________________ " + response2.toString());
//			
//			if (response1.getStatus() != 200 || (response1.getEntity(String.class).compareTo("true") != 0)) {
//
//				return "nodata1";
//			} 
//			else
//				if (response2.getStatus() != 200 || (response2.getEntity(String.class).compareTo("0") == 0)) {
//
//					return "nodata2";
//				}
//			else {
//				
//				return "success";
//			}
//			// =====================================================================================
//
//		}
//
//		protected void onPostExecute(String result) {
//			if (result.equals("success")) {
//				// do something
//				
//				Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
//				
//				//RESET
//				activity.resetValue();
//				
//				if(update){
//		            TakeOrder_ProductActivity.add_take_order_detail = false;
//		            TakeOrder_ProductActivity.timeLine = true;
//		            TakeOrdersDetailManagerActivity.add_detail = 0;
//				}
//				
//			} else if (result.equals("nointernet")) {
//				Toast.makeText(context,
//						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
//						Toast.LENGTH_SHORT).show();
//			} else if (result.equals("nodata1")) {
//				Toast.makeText(context, "Lỗi không mong muốn: Không thể lưu bản ghi này, hãy thử lại", Toast.LENGTH_SHORT)
//						.show();
//			} else 
//				if (result.equals("nodata2"))
//			{
//				
//				Toast.makeText(context, "Lỗi không mong muốn: Không thể lưu bản ghi chi tiết này, hãy thử lại", Toast.LENGTH_SHORT).show();
//
//			}
//			
//			
//			dialog.dismiss();
//		}
//
////		public boolean ConvertStringToObjectList(String input) {
////			// pair to object
////			ObjectMapper mapper = new ObjectMapper();
////
////			try {
////				customerList = mapper.readValue(
////						input,
////						TypeFactory.defaultInstance().constructCollectionType(
////								List.class, Customer.class));
////				// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
////			} catch (JsonGenerationException e) {
////				e.printStackTrace();
////				return false;
////			} catch (JsonMappingException e) {
////				e.printStackTrace();
////				return false;
////			} catch (IOException e) {
////				e.printStackTrace();
////				return false;
////			}
////
////			return true;
////		}
//
//	}
	
	// ///////////////// DELETE AND INSERT AND
		// /////////////////////////////////////////////////////////////////////////////
		public static class ModifyCalendarTask extends
				AsyncTask<Void, Void, String> {
			public static String ACTION_DELETE = "delete";
			public static String ACTION_EDIT = "edit";
			public static String ACTION_ADD = "add";
			
			Context context;
			String action;
			String method;
			Calendar calendar;
			CalendarArrayAdapter adapter;
			ListView calendarListView;
			
			CalendarManagerActivity activity;
			CalendarAdditionActivity addActivity;
			
			public ModifyCalendarTask(Context context,String action, String method,
					Calendar calendar, CalendarArrayAdapter adapter,  ListView calendarListView,
					CalendarManagerActivity activity, CalendarAdditionActivity addActivity) {
				this.context = context;
				this.action = action;
				this.method = method;
				this.calendar = calendar;
				this.calendarListView = calendarListView;
				this.activity = activity;
				this.addActivity = addActivity;
				
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
						.post(ClientResponse.class, ConvertObjectToString(calendar));

				String output = response.toString();
				System.out.println("input 1: " + output);

				if ((response.getStatus() == 200)
						&& (response.getEntity(String.class).compareTo("true") == 0)) {

					return "success";
				} else {

					return "fail";
				}
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
					if(action.equalsIgnoreCase(ACTION_DELETE)){
						Toast.makeText(context, "Đã xóa ", Toast.LENGTH_SHORT)
									.show();
						
						activity.dialog.dismiss();
						
						//Refresh
				        activity.getCalendarList();
				        adapter = new CalendarArrayAdapter(context,
								android.R.layout.simple_list_item_1, calendarList);
				        calendarListView.setAdapter(adapter);
					}
					
					if(action.equalsIgnoreCase(ACTION_ADD)){
						Toast.makeText(context, "Thêm lịch làm việc thành công ", Toast.LENGTH_SHORT)
									.show();
						
						//addActivity.dialog.dismiss();
					}
					
					if(action.equalsIgnoreCase(ACTION_EDIT)){
						Toast.makeText(context, "Sửa lịch làm việc thành công ", Toast.LENGTH_SHORT)
									.show();
						
						//activity.dialog.dismiss();
					}
					
				} else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("fail")) {
					
						Toast.makeText(
								context,
								"Không thể xóa. Dữ liệu đang được sử dụng cho đối tượng khác",
								Toast.LENGTH_SHORT).show();
				} else {
					
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

				}
				
				dialog.dismiss();
			}

			public String ConvertObjectToString(Calendar calendar) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(calendar);

				} catch (JsonGenerationException ex) {
					
					ex.printStackTrace();
					return "";
				} catch (JsonMappingException ex) {

					ex.printStackTrace();
					return "";
				} catch (IOException ex) {

					ex.printStackTrace();
					return "";
				}

				return cusStr;
			}

		}
		
		
		// ///////////////// LOADING
		///////////////////////////////////////////////////////////////////////////////
		public static class GetCalendarTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String staff;
			
			CalendarManagerActivity activity;
			
			public GetCalendarTask(Context context, String method,
					String staff, CalendarManagerActivity activity) {
				this.context = context;
				this.method = method;
				this.staff = staff;
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

				// handling
				ClientResponse response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, staff);

				String output = response.toString();
				System.out.println("input 1: " + output);

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
					
					activity.addListView();
					
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
					calendarList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, Calendar.class));
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
				
//				Staff staff = new Staff();
//				staff.setId("001");
//				staff.setName("HP");
//				
//				Calendar calendar = new Calendar();
//				calendar.setCalendarDate(new Date());
//				calendar.setContent("Hello demo");
//				calendar.setStaff(staff);
//				
//				Staff staff2 = new Staff();
//				staff2.setId("001");
//				staff2.setName("HP");
//				
//				Calendar calendar2 = new Calendar();
//				calendar2.setCalendarDate(new Date());
//				calendar2.setContent("Hello demo");
//				calendar2.setStaff(staff2);
//				
//				calendarList.add(calendar);
//				calendarList.add(calendar2);
				
				return true;
			}

		}
}
