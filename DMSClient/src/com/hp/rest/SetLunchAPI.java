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
import com.hp.domain.SetLunch;
import com.hp.map.SetLunchAdditionActivity;
import com.hp.map.SetLunchManagerActivity;
import com.hp.set.lunch.SetLunchArrayAdapter;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

public class SetLunchAPI {
	public static List<SetLunch> setLunchList = new ArrayList<SetLunch>();
	
	// ///////////////// DELETE AND INSERT AND
		// /////////////////////////////////////////////////////////////////////////////
		public static class ModifySetLunchTask extends
				AsyncTask<Void, Void, String> {
			public static String ACTION_DELETE = "delete";
			public static String ACTION_EDIT = "edit";
			public static String ACTION_ADD = "add";
			
			Context context;
			String action;
			String method;
			SetLunch setLunch;
			SetLunchArrayAdapter adapter;
			ListView setLunchListView;
			
			SetLunchManagerActivity activity;
			SetLunchAdditionActivity addActivity;
			
			public ModifySetLunchTask(Context context,String action, String method,
					SetLunch setLunch, SetLunchArrayAdapter adapter,  ListView setLunchListView,
					SetLunchManagerActivity activity, SetLunchAdditionActivity addActivity) {
				this.context = context;
				this.action = action;
				this.method = method;
				this.setLunch = setLunch;
				this.adapter = adapter;
				this.setLunchListView = setLunchListView;
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
						.post(ClientResponse.class, ConvertObjectToString(setLunch));
				}catch(Exception e){
					e.printStackTrace();
					return "nohost";
				}
				
				try {
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
						if (result.equalsIgnoreCase("existsetlunch")){

							return "existsetlunch";
						}
						else
							if (result.equalsIgnoreCase("readonly")){

								return "readonly";
							}
					
					else {

						return "fail";
					}
				} catch (ClientHandlerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "nohost";
				} catch (UniformInterfaceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "nohost";
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
						
						//activity.dialogCommit.dismiss();
						
						//Refresh
//				        activity.getCalendarList();
//				        adapter = new CalendarArrayAdapter(context,
//								android.R.layout.simple_list_item_1, calendarList);
//				        calendarListView.setAdapter(adapter);
					}
					
					if(action.equalsIgnoreCase(ACTION_ADD)){
						Toast.makeText(context, "Đặt cơm thành công ", Toast.LENGTH_SHORT)
									.show();
						
						//addActivity.dialog.dismiss();
					}
					
					if(action.equalsIgnoreCase(ACTION_EDIT)){
						Toast.makeText(context, "Đề nghị hoàn thành công tác thành công", Toast.LENGTH_SHORT)
									.show();
						
//						activity.dialog.dismiss();
//						
//						//Refresh
//				        activity.getCalendarList();
//				        adapter = new CalendarArrayAdapter(context,
//								android.R.layout.simple_list_item_1, calendarList);
//				        calendarListView.setAdapter(adapter);
					}
					
				} 
				else if (result.equals("existsetlunch")) {
					Toast.makeText(context,
							"Đặt cơm hôm nay đã tồn tại. Hãy chọn ngày khác!",
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

			public String ConvertObjectToString(SetLunch setLunch) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(setLunch);

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
		public static class GetSetLunchTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String staff;
			
			SetLunchManagerActivity activity;
			
			public GetSetLunchTask(Context context, String method,
					String staff, SetLunchManagerActivity activity) {
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
				
				try {
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
				} catch (ClientHandlerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "nohost";
				} catch (UniformInterfaceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return "nohost";
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
					setLunchList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, SetLunch.class));
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
