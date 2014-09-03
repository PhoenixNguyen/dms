package com.hp.rest;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.Activity;
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
import com.hp.datahandle.DataConvert;
import com.hp.domain.Customer;
import com.hp.domain.DataInfo;
import com.hp.map.CustomerListActivity;
import com.hp.map.CustomerMapActivity;
import com.hp.map.R;
import com.hp.map.TakeImagesActivity;
import com.sun.jersey.api.client.ClientResponse;

public class CustomerAPI {

	public static List<Customer> customerList;

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
	public static class GetCustomerListTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method;
		String staff;

		// Delete
		boolean delete;
		CustomerArrayAdapter customerAdapter;
		ListView listView;

		// insert
		Customer customer;
		boolean insert;

		// update location
		boolean updateLocation;
		CustomerMapActivity activity;
		
		CustomerListActivity customerActivity;
		//GET LIST
		boolean get;

		//CustomerListActivity customerActivity;
		public GetCustomerListTask(Context context, String method, String staff) {
			this.context = context;
			this.method = method;
			this.staff = staff;
		}

		public GetCustomerListTask(Context context, String method,
				String staff, boolean delete,
				CustomerArrayAdapter customerAdapter, ListView listView, CustomerListActivity activity) {
			this.context = context;
			this.method = method;
			this.staff = staff;

			this.delete = delete;
			this.customerAdapter = customerAdapter;
			this.listView = listView;
			
			this.customerActivity = activity;
		}

		public GetCustomerListTask(Context context, String method,
				String staff, boolean insert, Customer customer) {
			this.context = context;
			this.method = method;
			this.staff = staff;

			this.insert = insert;
			this.customer = customer;
		}

		public GetCustomerListTask(Context context, String method,
				String staff, boolean updateLocation,
				CustomerMapActivity activity) {
			this.context = context;
			this.method = method;
			this.staff = staff;

			this.updateLocation = updateLocation;
			this.activity = activity;
		}

		public GetCustomerListTask(Context context, String method, String staff, 
				ListView listView, CustomerArrayAdapter customerAdapter, Customer customer,
				CustomerListActivity activity, boolean get) {
			this.context = context;
			this.method = method;
			this.staff = staff;
			
			this.customerAdapter = customerAdapter;
			this.listView = listView;
			this.customer = customer;
			this.customerActivity = activity;
			this.get = get;
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
				if (delete) {
					customerAdapter = new CustomerArrayAdapter(context,
							CustomerAPI.customerList);
					listView.setAdapter(customerAdapter);
					
					customerActivity.customerAdapter = customerAdapter;
				}
				if (insert) {
					// Switch
					Intent t = new Intent(context, CustomerMapActivity.class);
					t.putExtra("POSITION_CLICK", customer.getMaDoiTuong());

					context.startActivity(t);
				}
				if (updateLocation) {
					activity.setUpMap();
				}
				
				if(get){
					//List view
					listView = (ListView)customerActivity.findViewById(R.id.list);
					customerAdapter = new CustomerArrayAdapter(context, CustomerAPI.customerList);
					listView.setAdapter(customerAdapter);
					customerActivity.customerAdapter = customerAdapter;
					
					listView.setOnItemClickListener(new OnItemClickListener()
					{
					     @Override
					     public void onItemClick(AdapterView<?> a, View v,int position, long id) 
					     {
					    	customer = (Customer) listView.getAdapter().getItem(position);
					    	
					    	customerActivity.customer = customer;
					    	//open dialog
					    	customerActivity.choiceDialog(customer);
					      }
					});
				}
				
				
				dialog.dismiss();
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("nodata")) {
				Toast.makeText(context, "Không có dữ liệu!", Toast.LENGTH_SHORT)
						.show();
			} else {
				dialog.dismiss();
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

			}
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

	// ///////////////// DELETE AND INSERT AND
	// EDIT/////////////////////////////////////////////////////////////////////////////
	public static class ModifyCustomerTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method;
		Customer customer;

		boolean insert;

		public ModifyCustomerTask(Context context, String method,
				Customer customer, boolean insert) {
			this.context = context;
			this.method = method;
			this.customer = customer;
			this.insert = insert;
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
					.post(ClientResponse.class, ConvertObjectToString(customer));

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
				if (!insert)
					Toast.makeText(context, "Đã xóa ", Toast.LENGTH_SHORT)
							.show();
				else {
					Toast.makeText(context, "Đã lưu ", Toast.LENGTH_SHORT)
							.show();

				}

				
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("fail")) {
				if (!insert)
					Toast.makeText(
							context,
							"Không thể xóa dữ liệu. Khách hàng này đã tồn tại ở dữ liệu khác!",
							Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(
							context,
							"Không thể lưu dữ liệu. Mã KH không được trống và không trùng với khách hàng khác",
							Toast.LENGTH_SHORT).show();
			} else {
				
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

			}
			
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

	/////////////////// UPLoad Images
	///////////////////////////////////////////////////////////////////////////////
	public static class UploadCustomerImageTask extends
			AsyncTask<Void, Void, String> {
		
		Context context;
		String method;
		String path;

		public UploadCustomerImageTask(Context context, String method,
				String path) {
			this.context = context;
			this.method = method;
			this.path = path;
			
		}

		public  ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(context, "", "Đang xử lý ... ", true);
		}

		protected String doInBackground(Void... params) {
			// do something
			if (CheckingInternet.isOnline()) {
				System.out.println("Internet access!!____________________");
			} else {
				//dialog.dismiss();
				System.out.println("NO Internet access!!____________________");

				return "nointernet";

			}

			DataInfo data = new DataInfo(Rest.mStaff.getId(),
					CustomerMapActivity.mSelectedCustomer.getMaDoiTuong(),
					CustomerMapActivity.mSelectedCustomer.getDoiTuong(),
					DataConvert.encodeImageToString(path), "");
			
			System.out.println(data.getTenKhachHang());
			// uploading
			ClientResponse response = Rest.mService
					.path("webresources")
					.path(method)
					.accept("application/json")
					.type("application/json")
					.post(ClientResponse.class, ConvertObjectToString(data));

			String output = response.toString();
			System.out.println("input 1: " + output);

			if (response.getStatus() == 200
					) {

				return "success";
			} else {

				return "fail";
			}
			// =====================================================================================

		}

		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				
				Toast.makeText(context, "Đã gửi thành công", Toast.LENGTH_SHORT)
						.show();
				TakeImagesActivity.pathSave = "";
				
			} else if (result.equals("nointernet")) {
				Toast.makeText(context,
						"Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!",
						Toast.LENGTH_SHORT).show();
			} else if (result.equals("fail")) {
				
				Toast.makeText(
						context,
						"Không thể gửi dữ liệu. Có lỗi xảy ra, hãy thử lại sau",
						Toast.LENGTH_SHORT).show();
			} else {
				//dialog.dismiss();
				Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

			}
			dialog.dismiss();
			
		}

		public String ConvertObjectToString(DataInfo data) {
			ObjectMapper mapper = new ObjectMapper();
			String cusStr = new String();

			try {

				cusStr = mapper.writeValueAsString(data);

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

}
