package com.hp.rest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.hp.domain.TimeKeeper;
import com.hp.map.TimeKeeperActivity;
import com.sun.jersey.api.client.ClientResponse;

public class TimeKeeperAPI {
	public static List<TimeKeeper> timeKeeperList = new ArrayList<TimeKeeper>();
	
	// ///////////////// DELETE AND INSERT AND
		// /////////////////////////////////////////////////////////////////////////////
		public static class ModifyTimeKeeperTask extends
				AsyncTask<Void, Void, String> {
			public static String ACTION_DELETE = "delete";
			public static String ACTION_EDIT = "edit";
			public static String ACTION_ADD = "add";
			
			Context context;
			String action;
			String method;
			TimeKeeper timeKeeper;
			
			TimeKeeperActivity activity;
			
			public ModifyTimeKeeperTask(Context context,String action, String method,
					TimeKeeper timeKeeper, TimeKeeperActivity activity) {
				this.context = context;
				this.action = action;
				this.method = method;
				this.timeKeeper = timeKeeper;
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
				ClientResponse response = null;	
				try{

					response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, ConvertObjectToString(timeKeeper));

				}catch(Exception e){
					e.printStackTrace();
					return "nohost";
				}
				
				String output = response.toString();
				System.out.println("input 1: " + output);

				String result = "";
				if (response.getStatus() == 200)
						result = response.getEntity(String.class);
				
				if (result.equalsIgnoreCase("true")){

					return "success";
				} 
				else
					if (result.equalsIgnoreCase("nocalendar")){

						return "nocalendar";
					}
				else {

					return "fail";
				}
				// =====================================================================================

			}

			protected void onPostExecute(String result) {
				if (result.equals("success")) {
					// do something
					
					if(action.equalsIgnoreCase(ACTION_ADD)){
						Toast.makeText(context, "Chấm công thành công. Xin cảm ơn! ", Toast.LENGTH_SHORT)
									.show();
						
						activity.getTimeKeepingList();
					}
					
					
				}else
					if (result.equals("nocalendar")) {
						Toast.makeText(context, "Không tồn tại lịch công tác cho ngày hôm nay tại địa điểm này. Hãy lên kế hoạch trước! ", Toast.LENGTH_SHORT)
						.show();
					}
				
				else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("fail")) {
					
						Toast.makeText(
								context,
								"Chấm công lỗi. Hãy thử lại!",
								Toast.LENGTH_SHORT).show();
				} 
				else if (result.equals("nohost")){
            		Toast.makeText(context, "Không thể kết nối được với máy chủ!", Toast.LENGTH_SHORT).show();
            	}
				else {
					
					Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

				}
				
				dialog.dismiss();
			}

			public String ConvertObjectToString(TimeKeeper timeKeeper) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(timeKeeper);

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
		public static class GetTimeKeeperTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String staff;
			
			TimeKeeperActivity activity;
			
			public GetTimeKeeperTask(Context context, String method,
					String staff, TimeKeeperActivity activity) {
				this.context = context;
				this.method = method;
				this.staff = staff;
				this.activity = activity;
			}

			ProgressDialog dialog;

			protected void onPreExecute() {
				dialog = ProgressDialog.show(context, "", "Đang xử lý ... ", true);
			}

			@SuppressLint("SimpleDateFormat")
			protected String doInBackground(Void... params) {
				// do something
				if (CheckingInternet.isOnline()) {
					System.out.println("Internet access!!____________________");
				} else {
					
					System.out.println("NO Internet access!!____________________");

					return "nointernet";

				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				// handling
				ClientResponse response = null;	
				try{

					response = Rest.mService
						.path("webresources")
						.path(method)
						.accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, staff+"::" +df.format(new Date()));
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
					
					activity.displayTimeKeepings();
					
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
					timeKeeperList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, TimeKeeper.class));
					
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
