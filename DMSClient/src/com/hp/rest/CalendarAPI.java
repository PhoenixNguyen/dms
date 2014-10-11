package com.hp.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.hp.calendar.CalendarArrayAdapter;
import com.hp.domain.Calendar;
import com.hp.map.CalendarAdditionActivity;
import com.hp.map.CalendarManagerActivity;
import com.sun.jersey.api.client.ClientResponse;

public class CalendarAPI {
	public static List<Calendar> calendarList = new ArrayList<Calendar>();
	
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
				this.adapter = adapter;
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
				ClientResponse response = null;
				try{
				    response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, ConvertObjectToString(calendar));
				}catch(Exception e){
					e.printStackTrace();
					return "nohost";
				}
				
				String output = response.toString();
				System.out.println("input 1: " + output);

				//
				String result = "";
				if (response.getStatus() == 200)
						result = response.getEntity(String.class);
				
				if (result.equalsIgnoreCase("true")){

					return "success";
				} 
				else
					if (result.equalsIgnoreCase("existcalendar")){

						return "existcalendar";
					}
					else
						if (result.equalsIgnoreCase("readonly")){

							return "readonly";
						}
				
				else {

					return "fail";
				}
				//
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
					if(action.equalsIgnoreCase(ACTION_DELETE)){
						Toast.makeText(context, "Xóa lịch công tác thành công ", Toast.LENGTH_SHORT)
									.show();
						
						activity.dialogCommit.dismiss();
						
						//Refresh
				        activity.getCalendarList();
				        adapter = new CalendarArrayAdapter(context,
								android.R.layout.simple_list_item_1, calendarList);
				        calendarListView.setAdapter(adapter);
					}
					
					if(action.equalsIgnoreCase(ACTION_ADD)){
						Toast.makeText(context, "Thêm lịch công tác thành công ", Toast.LENGTH_SHORT)
									.show();
						
						//addActivity.dialog.dismiss();
					}
					
					if(action.equalsIgnoreCase(ACTION_EDIT)){
						Toast.makeText(context, "Đề nghị hoàn thành công tác thành công", Toast.LENGTH_SHORT)
									.show();
						
						activity.dialog.dismiss();
						
						//Refresh
				        activity.getCalendarList();
				        adapter = new CalendarArrayAdapter(context,
								android.R.layout.simple_list_item_1, calendarList);
				        calendarListView.setAdapter(adapter);
					}
					
				} 
				else if (result.equals("existcalendar")) {
					Toast.makeText(context,
							"Ngày làm việc ngày này và địa điểm hiện tại đã tồn tại lịch công tác. Hãy chọn ngày khác!",
							Toast.LENGTH_LONG).show();
				}
				
				else if (result.equals("readonly")) {
					Toast.makeText(context,
							"Lịch công tác đã đề nghị, không thể tác động!",
							Toast.LENGTH_LONG).show();
				}
				
				else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("fail")) {
					
						Toast.makeText(
								context,
								"Lỗi không xác định, hãy thử lại sau",
								Toast.LENGTH_SHORT).show();
				} 
				else
                	if (result.equals("nohost")){
                		Toast.makeText(context, "Không thể kết nối được với máy chủ!", Toast.LENGTH_SHORT).show();
                	}
				
				else {
					
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
				ClientResponse response = null;
				try{
				   response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, staff);
				}catch(Exception e){
					e.printStackTrace();
					return "nohost";
				}
				
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
				} 
				else if (result.equals("nohost")){
            		Toast.makeText(context, "Không thể kết nối được với máy chủ!", Toast.LENGTH_SHORT).show();
            	}
				
				else {
					
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
				
				return true;
			}

		}
}
