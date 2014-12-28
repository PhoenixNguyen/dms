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

import com.hp.domain.ForLeave;
import com.hp.forleave.ForLeaveArrayAdapter;
import com.hp.map.ForLeaveAdditionActivity;
import com.hp.map.ForLeaveManagerActivity;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

public class ForLeaveAPI {
	public static List<ForLeave> forLeaveList = new ArrayList<ForLeave>();
	
	// ///////////////// DELETE AND INSERT AND
		// /////////////////////////////////////////////////////////////////////////////
		public static class ModifyForLeaveTask extends
				AsyncTask<Void, Void, String> {
			public static String ACTION_DELETE = "delete";
			public static String ACTION_EDIT = "edit";
			public static String ACTION_ADD = "add";
			
			Context context;
			String action;
			String method;
			ForLeave forLeave;
			ForLeaveArrayAdapter adapter;
			ListView forLeaveListView;
			
			ForLeaveManagerActivity activity;
			ForLeaveAdditionActivity addActivity;
			
			public ModifyForLeaveTask(Context context,String action, String method,
					ForLeave forLeave, ForLeaveArrayAdapter adapter,  ListView forLeaveListView,
					ForLeaveManagerActivity activity, ForLeaveAdditionActivity addActivity) {
				this.context = context;
				this.action = action;
				this.method = method;
				this.forLeave = forLeave;
				this.adapter = adapter;
				this.forLeaveListView = forLeaveListView;
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
						.post(ClientResponse.class, ConvertObjectToString(forLeave));
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
						if (result.equalsIgnoreCase("existforleave")){

							return "existforleave";
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
						
					}
					
					if(action.equalsIgnoreCase(ACTION_ADD)){
						Toast.makeText(context, "Xin nghỉ phép thành công ", Toast.LENGTH_SHORT)
									.show();
						
						//addActivity.dialog.dismiss();
					}
					
					if(action.equalsIgnoreCase(ACTION_EDIT)){
						Toast.makeText(context, "Đề nghị hoàn thành công tác thành công", Toast.LENGTH_SHORT)
									.show();
						
					}
					
				} 
				else if (result.equals("existforleave")) {
					Toast.makeText(context,
							"Bạn đã xin nghỉ phép ngày này. Hãy chọn ngày khác!",
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

			public String ConvertObjectToString(ForLeave forLeave) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(forLeave);

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
		public static class GetForLeaveTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String staff;
			
			ForLeaveManagerActivity activity;
			
			public GetForLeaveTask(Context context, String method,
					String staff, ForLeaveManagerActivity activity) {
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
					forLeaveList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, ForLeave.class));
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
