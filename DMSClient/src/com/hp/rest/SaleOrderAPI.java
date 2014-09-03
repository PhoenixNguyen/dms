package com.hp.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.annotation.SuppressLint;
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
import com.hp.domain.TakeOrder;
import com.hp.map.CustomerListActivity;
import com.hp.map.CustomerMapActivity;
import com.hp.map.R;
import com.hp.map.SaleManagerActivity;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.hp.sale_order.DialogArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

public class SaleOrderAPI {
	public static List<TakeOrder> takeOrderList;
	
	public static class GetTakeOrderListTask extends
	AsyncTask<Void, Void, String> {
	Context context;
	String method;
	String staff;
	
	ListView listViewCus;
	String[] selectedValue;
	SaleManagerActivity activity;
	
	public GetTakeOrderListTask(Context context, String method, String staff, ListView listViewCus,
			String[] selectedValue, SaleManagerActivity activity) {
		this.context = context;
		this.method = method;
		this.staff = staff;
		this.listViewCus = listViewCus;
		this.selectedValue = selectedValue;
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
				.type("application/json").post(ClientResponse.class, staff);
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
			String[] take_order_id_list = new String[]{};
			
			for(int i = 0; i < takeOrderList.size(); i++){
				take_order_id_list = append(take_order_id_list, takeOrderList.get(i).getId());
				
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
			    	 selectedValue[0] = (String) listViewCus.getAdapter().getItem(position);
			    	 System.out.println("__ " + selectedValue[0]);
//			    	 
//			          //Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_LONG).show();
//			    	// custom dialog

			      }
			});
			
			activity.dialogLoad.show();
			
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
	
	@SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
	
	}
	
	public static class PutTakeOrderTask extends
		AsyncTask<Void, Void, String> {
		Context context;
		String method;
		OrdersManagerArrayAdapter adapter;
		
		ListView listViewCus;
		String[] selectedValue;
		SaleManagerActivity activity;
		
		public PutTakeOrderTask(Context context, String method, OrdersManagerArrayAdapter adapter, ListView listViewCus,
				String[] selectedValue, SaleManagerActivity activity) {
			this.context = context;
			this.method = method;
			this.adapter = adapter;
			this.listViewCus = listViewCus;
			this.selectedValue = selectedValue;
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
					.type("application/json").post(ClientResponse.class, selectedValue[0]);
			System.out.println("________________ " + response.toString());
		
			if (response.getStatus() != 200) {
		
				return "nodata";
			} else {
		
				return "success";
				
			}
			// =====================================================================================
		
		}
		
		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				// do something
				activity.getOrderList();
				adapter = new OrdersManagerArrayAdapter(context,
						android.R.layout.simple_list_item_1, TakeOrderAPI.takeOrderList);
				listViewCus.setAdapter(adapter);
				activity.dialogLoad.dismiss();
				
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
		
		@SuppressLint("NewApi")
		static <T> T[] append(T[] arr, T element) {
	        final int N = arr.length;
	        arr = Arrays.copyOf(arr, N + 1);
	        arr[N] = element;
	        return arr;
	    }
	
	}
}
