package com.hp.rest;

import java.io.IOException;
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

import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Customer;
import com.hp.domain.Staff;
import com.hp.domain.TakeOrder;
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

public class TakeOrderAPI {
	public static List<TakeOrder> takeOrderList;

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
	public static class PutTakeOrderTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method1;
		String method2;
		String staff;
		String content1;
		String content2;
		
		TakeOrder_AmountActivity activity;
		boolean update;

		public PutTakeOrderTask(Context context, String method1, String method2, 
				String content1, String content2, String staff,
				TakeOrder_AmountActivity activity, boolean update) {
			this.context = context;
			this.method1 = method1;
			this.method2 = method2;
			this.staff = staff;
			this.content1 = content1;
			this.content2 = content2;
			
			this.activity = activity;
			this.update = update;
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
			ClientResponse response1 = Rest.mService.path("webresources")
					.path(method1).accept("application/json")
					.type("application/json").post(ClientResponse.class, content1);
			System.out.println("________________ " + response1.toString());

			ClientResponse response2 = Rest.mService.path("webresources")
					.path(method2).accept("application/json")
					.type("application/json").post(ClientResponse.class, content2);
			System.out.println("________________ " + response2.toString());
			
			if (response1.getStatus() != 200 || (response1.getEntity(String.class).compareTo("true") != 0)) {

				return "nodata1";
			} 
			else
				if (response2.getStatus() != 200 || (response2.getEntity(String.class).compareTo("0") == 0)) {

					return "nodata2";
				}
			else {
				
				return "success";
			}
			// =====================================================================================

		}

		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				// do something
				
				Toast.makeText(context, "Đã lưu", Toast.LENGTH_SHORT).show();
				
				//RESET
				activity.resetValue();
				
				if(update){
		            TakeOrder_ProductActivity.add_take_order_detail = false;
		            TakeOrder_ProductActivity.timeLine = true;
		            TakeOrdersDetailManagerActivity.add_detail = 0;
				}
				
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("nodata1")) {
				Toast.makeText(context, "Lỗi không mong muốn: Không thể lưu bản ghi này, hãy thử lại", Toast.LENGTH_SHORT)
						.show();
			} else 
				if (result.equals("nodata2"))
			{
				
				Toast.makeText(context, "Lỗi không mong muốn: Không thể lưu bản ghi chi tiết này, hãy thử lại", Toast.LENGTH_SHORT).show();

			}
			
			
			dialog.dismiss();
		}

//		public boolean ConvertStringToObjectList(String input) {
//			// pair to object
//			ObjectMapper mapper = new ObjectMapper();
//
//			try {
//				customerList = mapper.readValue(
//						input,
//						TypeFactory.defaultInstance().constructCollectionType(
//								List.class, Customer.class));
//				// System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
//			} catch (JsonGenerationException e) {
//				e.printStackTrace();
//				return false;
//			} catch (JsonMappingException e) {
//				e.printStackTrace();
//				return false;
//			} catch (IOException e) {
//				e.printStackTrace();
//				return false;
//			}
//
//			return true;
//		}

	}
	
	// ///////////////// DELETE AND INSERT AND
		// EDIT/////////////////////////////////////////////////////////////////////////////
		public static class ModifyTakeOrderTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			TakeOrder takeOrder;
			OrdersManagerArrayAdapter adapter;
			ListView ordersListView;
			
			TakeOrdersManagerActivity activity;
			
			public ModifyTakeOrderTask(Context context, String method,
					TakeOrder takeOrder, OrdersManagerArrayAdapter adapter,  ListView ordersListView,
					TakeOrdersManagerActivity activity) {
				this.context = context;
				this.method = method;
				this.takeOrder = takeOrder;
				this.ordersListView = ordersListView;
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
						.post(ClientResponse.class, ConvertObjectToString(takeOrder));

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
					
					Toast.makeText(context, "Đã xóa ", Toast.LENGTH_SHORT)
								.show();
					
					activity.dialog.dismiss();
					
					//Refresh
			        activity.getOrderList();
			        adapter = new OrdersManagerArrayAdapter(context,
							android.R.layout.simple_list_item_1, takeOrderList);
					ordersListView.setAdapter(adapter);

					
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

			public String ConvertObjectToString(TakeOrder takeOrder) {
				ObjectMapper mapper = new ObjectMapper();
				String cusStr = new String();

				try {

					cusStr = mapper.writeValueAsString(takeOrder);

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
		
		
		// ///////////////// LOADING
		///////////////////////////////////////////////////////////////////////////////
		public static class GetTakeOrderTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String staff;
			
			TakeOrdersManagerActivity activity;
			
			public GetTakeOrderTask(Context context, String method,
					String staff, TakeOrdersManagerActivity activity) {
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

				// Deleting
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
					takeOrderList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, TakeOrder.class));
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
