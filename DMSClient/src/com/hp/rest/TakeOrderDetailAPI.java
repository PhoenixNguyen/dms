package com.hp.rest;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.hp.domain.Customer;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.map.TakeOrdersDetailManagerActivity;
import com.hp.map.TakeOrdersManagerActivity;
import com.hp.order_manager.OrdersManagerDetailArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

public class TakeOrderDetailAPI {
	
	public static List<TakeOrderDetail> takeOrderDetailList;
	// ///////////////// DELETE AND INSERT AND
		// EDIT/////////////////////////////////////////////////////////////////////////////
		public static class ModifyTakeOrderDetailTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			TakeOrderDetail takeOrderDetail;
			OrdersManagerDetailArrayAdapter adapter;
			ListView ordersListView ;
			TakeOrdersDetailManagerActivity activity;
			
			Dialog dialogTakeOrder;
			
			public ModifyTakeOrderDetailTask(Context context, String method,
					TakeOrderDetail takeOrderDetail, OrdersManagerDetailArrayAdapter adapter,
					ListView ordersListView , TakeOrdersDetailManagerActivity activity, Dialog dialog) {
				this.context = context;
				this.method = method;
				
				this.takeOrderDetail = takeOrderDetail;
				this.adapter = adapter;
				this.ordersListView = ordersListView;
				this.activity = activity;
				this.dialogTakeOrder = dialog;
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
						.post(ClientResponse.class, ConvertObjectToString(takeOrderDetail));

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
					
					Toast.makeText(context, "Đã cập nhật ", Toast.LENGTH_SHORT)
								.show();
					

					
					dialogTakeOrder.dismiss();
					//reload
					activity.getOrderList();
					adapter = new OrdersManagerDetailArrayAdapter(context,
							android.R.layout.simple_list_item_1, takeOrderDetailList);
					ordersListView.setAdapter(adapter);
					
				} else if (result.equals("nointernet")) {
					Toast.makeText(context,
							"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
							Toast.LENGTH_SHORT).show();
				} else if (result.equals("fail")) {
					
						Toast.makeText(
								context,
								"Không thể cập nhật dữ liệu. Bản ghi này này đã tồn tại ở dữ liệu khác!",
								Toast.LENGTH_SHORT).show();
				}
					else
						Toast.makeText(
								context,
								"Không thể cập nhật dữ liệu. Bản ghi này này đã tồn tại ở dữ liệu khác",
								Toast.LENGTH_SHORT).show();
				dialog.dismiss();
			}

			public String ConvertObjectToString(TakeOrderDetail takeOrder) {
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
		public static class GetTakeOrderDetailTask extends
				AsyncTask<Void, Void, String> {
			Context context;
			String method;
			String orderID;
			
			TakeOrdersDetailManagerActivity activity;
			
			public GetTakeOrderDetailTask(Context context, String method,
					String orderID, TakeOrdersDetailManagerActivity activity) {
				this.context = context;
				this.method = method;
				this.orderID = orderID;
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
						.post(ClientResponse.class, orderID);

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
					takeOrderDetailList = mapper.readValue(
							input,
							TypeFactory.defaultInstance().constructCollectionType(
									List.class, TakeOrderDetail.class));
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
