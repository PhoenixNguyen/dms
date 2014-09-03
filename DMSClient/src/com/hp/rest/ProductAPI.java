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
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hp.customer.CustomerArrayAdapter;
import com.hp.domain.Customer;
import com.hp.domain.Product;
import com.hp.map.CustomerMapActivity;
import com.hp.map.ProductManagerActivity;
import com.hp.map.TakeOrder_ProductActivity;
import com.hp.map.TakeOrdersDetailManagerActivity;
import com.hp.order.ProductArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

public class ProductAPI {
	public static List<Product> productList;

	// ///////////////// LOAD
	// /////////////////////////////////////////////////////////////////////////////
	public static class GetProductListTask extends
			AsyncTask<Void, Void, String> {
		Context context;
		String method;

		
		ProductArrayAdapter adapter;
		ListView listView;
		boolean mManager;
		int mProviderIndex;
		EditText search;
		

//		// Delete
//		boolean delete;
//		CustomerArrayAdapter customerAdapter;
//		ListView listView;
//
//		// insert
//		Customer customer;
//		boolean insert;
//
//		// update location
//		boolean updateLocation;
//		CustomerMapActivity activity;

		public GetProductListTask(Context context, String method,
				ProductArrayAdapter adapter, ListView listView, 
				boolean mManager, int mProviderIndex, EditText search) {
			this.context = context;
			this.method = method;
			
			this.adapter = adapter;
			this.listView= listView; 
			this.mManager = mManager; 
			this.mProviderIndex = mProviderIndex; 
			this.search = search; 
		}

		// public GetProductListTask(Context context, String method, String
		// staff, boolean delete,
		// CustomerArrayAdapter customerAdapter, ListView listView){
		// this.context = context;
		// this.method = method;
		// this.staff = staff;
		//
		// this.delete = delete;
		// this.customerAdapter = customerAdapter;
		// this.listView = listView;
		// }
		//
		// public GetProductListTask(Context context, String method, String
		// staff, boolean insert, Customer customer){
		// this.context = context;
		// this.method = method;
		// this.staff = staff;
		//
		// this.insert = insert;
		// this.customer = customer;
		// }
		//
		// public GetProductListTask(Context context, String method, String
		// staff, boolean updateLocation, CustomerMapActivity activity){
		// this.context = context;
		// this.method = method;
		// this.staff = staff;
		//
		// this.updateLocation = updateLocation;
		// this.activity = activity;
		// }

		ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = ProgressDialog.show(context, "", "Đang tải ... ", true);
		}

		protected String doInBackground(Void... params) {
			// do something
			if(TakeOrder_ProductActivity.timeLine == true)
				TakeOrder_ProductActivity.mProductsMap.clear();
			else
				return "success";
			
			for(int k = 0; k< ProviderAPI.providersList.size(); k++){
				if (CheckingInternet.isOnline()) {
					System.out.println("Internet access!!____________________");
				} else {
					
					System.out.println("NO Internet access!!____________________");
	
					return "nointernet";
	
				}
	
				// Getting
				ClientResponse response = Rest.mService.path("webresources")
						.path(method).accept("application/json")
						.type("application/json")
						.post(ClientResponse.class, ProviderAPI.providersList.get(k).getId());
				System.out.println("________________ " + response.toString());
	
				if (response.getStatus() != 200) {
	
					return "nodata";
				} else {
	
					String re = response.getEntity(String.class);
					System.out.println("________________ " + re);
	
					// Convert
					if (ConvertStringToObjectList(re)){
						TakeOrder_ProductActivity.mProductsMap.put(k + "", productList);

						//
						// if add more products for take order
						if (TakeOrder_ProductActivity.add_take_order_detail) {

							for (int i = 0; i < TakeOrder_ProductActivity.mProductsMap
									.get(k + "").size(); i++) {
								for (int j = 0; j < TakeOrderDetailAPI.takeOrderDetailList
										.size(); j++) {
									if (TakeOrder_ProductActivity.mProductsMap
											.get(k + "")
											.get(i)
											.getProductID()
											.compareTo(
													TakeOrderDetailAPI.takeOrderDetailList
															.get(j).getProductID()) == 0) {

										TakeOrder_ProductActivity.mProductsMap
												.get(k + "")
												.get(i)
												.setTotal(
														TakeOrderDetailAPI.takeOrderDetailList
																.get(j).getNumber());
										TakeOrder_ProductActivity.mProductsMap
												.get(k + "")
												.get(i)
												.setDiscount(
														TakeOrderDetailAPI.takeOrderDetailList
																.get(j).getDiscount());

										TakeOrder_ProductActivity.mProductsMap
												.get(k + "")
												.get(i)
												.setNote(
														TakeOrderDetailAPI.takeOrderDetailList
																.get(j).getNote());

										TakeOrder_ProductActivity.mProductsMap
												.get(k + "")
												.get(i)
												.setPromotionalProductAmounts(
														TakeOrderDetailAPI.takeOrderDetailList
																.get(j)
																.getPromotionalProductMount());

									}
								}
							}

							TakeOrder_ProductActivity.take_order_id = TakeOrdersDetailManagerActivity.order_id;
						}
						// }

						// timeline = false for not init again
						TakeOrder_ProductActivity.timeLine = false;
						
						
					}
					else
						return "nodata";
				}
				
				
			}
			
			return "success";
			// =====================================================================================

		}

		protected void onPostExecute(String result) {
			if (result.equals("success")) {
				// do something
				// if(delete){
				// customerAdapter = new CustomerArrayAdapter(context,
				// CustomerAPI.customerList);
				// listView.setAdapter(customerAdapter);
				// }
				// if(insert){
				// //Switch
				// Intent t = new Intent(context, CustomerMapActivity.class);
				// t.putExtra("POSITION_CLICK", customer.getMaDoiTuong());
				//
				// context.startActivity(t);
				// }
				// if(updateLocation){
				// activity.setUpMap();
				// }

				// If mProductsMap have not contained this key then import this
				// list
				
			    // Something after add all product =====================================================================
				adapter = new ProductArrayAdapter(context, android.R.layout.simple_list_item_1
						, TakeOrder_ProductActivity.mProductsMap.get(mProviderIndex + ""), mManager);
				listView.setAdapter(adapter);
				//Set total
				if(mManager)
					TakeOrder_ProductActivity.total_value.setText(TakeOrder_ProductActivity.mProductsMap.get(mProviderIndex + "").size()+"");
				//
				
				//Add search listenner
				search.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence cs, int arg1, int arg2,
							int arg3) {
						// When user changed the Text
						adapter.getFilter().filter(cs);
					}

					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1,
							int arg2, int arg3) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
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
				productList = mapper.readValue(
						input,
						TypeFactory.defaultInstance().constructCollectionType(
								List.class, Product.class));
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
	
	
	/////////////////// DELETE AND INSERT AND EDIT/////////////////////////////////////////////////////////////////////////////
	public static class ModifyProductTask extends AsyncTask<Void,Void,String>
	{
		Context context;
		String method;
		Product product;
		
		boolean insert;
		
		//Delete
		ProductManagerActivity activity;
		
		public ModifyProductTask(Context context, String method, Product product, boolean insert){
			this.context = context;
			this.method = method;
			this.product = product;
			this.insert = insert;
		}
		
		public ModifyProductTask(Context context, String method, Product product, boolean insert, ProductManagerActivity activity){
			this.context = context;
			this.method = method;
			this.product = product;
			this.insert = insert;
			this.activity = activity;
			
		}
		
		ProgressDialog dialog;
		protected void onPreExecute() {
		dialog = ProgressDialog.show(context, "",
			  "Đang xử lý ... ", true);
		}
		
		protected String doInBackground(Void... params)
		{
		//do something  
		if(CheckingInternet.isOnline()){
			System.out.println("Internet access!!____________________");
		}
		else{
			dialog.dismiss();									
			System.out.println("NO Internet access!!____________________");
							
			return "nointernet";
			
		}
		
		//Deleting
		ClientResponse response = Rest.mService.path("webresources").path(method).accept("application/json")
				.type("application/json").post(ClientResponse.class, ConvertObjectToString(product));
		
		String output = response.toString();
		System.out.println("input 1: " + output);
		
		if((response.getStatus() == 200) && (response.getEntity(String.class).compareTo("true") == 0)){
			
			return "success";
		}
		else{
				        
		  return "fail";
		}
		// =====================================================================================
		
		}
		
		protected void onPostExecute(String result)
		{
			if (result.equals("success")){
			  //do something
				if(!insert){
					Toast.makeText(context, "Đã xóa ", Toast.LENGTH_SHORT).show();
					activity.onResume();
				}
				else{
					Toast.makeText(context, "Đã lưu ", Toast.LENGTH_SHORT).show();
					
				}
				
				
			}
			else
				if (result.equals("nointernet")){
					Toast.makeText(context, "Không có kết nối mạng, mở 3G hoặc Wifi để tiếp tục!", Toast.LENGTH_SHORT).show();
				}
			else
				if (result.equals("fail")){
					if(!insert)
						Toast.makeText(context, "Không thể xóa dữ liệu. Sản phẩm này đã tồn tại ở dữ liệu khác!", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(context, "Không thể lưu dữ liệu. Mã Sản phẩm không được trống và không trùng với Sản phẩm khác", Toast.LENGTH_SHORT).show();
				}
			else
			{       
			 				
			 Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
				
			}
			
			dialog.dismiss();
		} 
		
		public String  ConvertObjectToString(Product product){
			ObjectMapper mapper = new ObjectMapper();
			String cusStr = new String();
			
			try {
			
				cusStr = mapper.writeValueAsString(product);
				
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
