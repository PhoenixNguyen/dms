package com.hp.sale_order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hp.map.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.hp.order_manager.OrdersManagerDetailArrayAdapter;
import com.hp.rest.Rest;
import com.sun.jersey.api.client.ClientResponse;

import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import android.widget.LinearLayout;
import android.widget.ListView;

import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ReturnOrderFragment extends Fragment {
	public static String ORDER_ID = "section_number";
	
	private LinearLayout layout;
	public static String order_id;
	
	public static List<TakeOrderDetail> takeOrderDetailList = new ArrayList<TakeOrderDetail>();
	private static Context context ;
	private static ListView ordersListView;
	private static OrdersManagerDetailArrayAdapter adapter;
	
	private static View rootView;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
		this.context = container.getContext();
		rootView = inflater.inflate(R.layout.orders_detail_manager, container, false);
        
		order_id = AppSectionsPagerAdapter.selected_order;
		
		((TextView)rootView.findViewById(R.id.title)).setText("Order: "+order_id);
//		title.setText("Order: "+order_id);
		
		getOrderList();
		addListView();
		
		return rootView;
	}
	
	
	
	public void onResume(){
		super.onResume();
		this.onCreate(null);
	}
	
	
	public static void addListView() {

		// Check the internet
		if (isOnline()) {
			System.out.println("Internet access!!____________________");
		} else {
			System.out.println("NO Internet access!!____________________");
			Toast.makeText(context, "No internet access, please try again later!",
					Toast.LENGTH_SHORT).show();
			return;
		}

		

		if (takeOrderDetailList == null) {
			return;
		}
		// List<Product> productsList = new ArrayList<Product>();
		// Product product = new Product(1, "Welcome", "Welcome",
		// "Choose providers list");
		// productsList.add(product);

		ordersListView = (ListView) rootView.findViewById(R.id.list_view_product);
		adapter = new OrdersManagerDetailArrayAdapter(context,
				android.R.layout.simple_list_item_1, takeOrderDetailList);
		ordersListView.setAdapter(adapter);

		ordersListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				System.out.println("Click!");
				TakeOrderDetail selectedValue = (TakeOrderDetail) ordersListView.getAdapter().getItem(position);
		    	 addCustomerDialog(selectedValue);
				
			}
		});
	}

	public static void addCustomerDialog(final TakeOrderDetail selectedValue){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.order_product_dialog);
		dialog.setTitle("Thay đổi số lượng");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText("Tên sản phẩm: "+selectedValue.getProductName());

		TextView price = (TextView) dialog.findViewById(R.id.price);
		price.setText("Giá sản phẩm: "+selectedValue.getBeforeOrderPrice());
		
		final EditText count = (EditText)dialog.findViewById(R.id.count);
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Lưu lại");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(count == null )
					return;
				
				int number = Integer.parseInt(count.getText().toString());
				if(number >  selectedValue.getNumber()){
					Toast.makeText(context, "Số lượng bạn nhập lớn hơn trong hóa đơn!!", Toast.LENGTH_SHORT).show();
					return;
				}
				selectedValue.setNumber(number);
				selectedValue.setPriceTotal(selectedValue.getAfterOrderPrice() * number);
				
				//Sys
				
				ObjectMapper mapper = new ObjectMapper();
		        String orderDetail = new String();

				try {

					orderDetail = mapper.writeValueAsString(selectedValue);
					
				} catch (JsonGenerationException ex) {

					ex.printStackTrace();

				} catch (JsonMappingException ex) {

					ex.printStackTrace();

				} catch (IOException ex) {

					ex.printStackTrace();

				}
		       
				//Order ---------------------------------------------------------------
				ClientResponse response = Rest.mService.path("webresources").path("putDetailReturnOrder").accept("application/json")
				.type("application/json").post(ClientResponse.class, orderDetail);

		        String output = response.toString();
		        System.out.println("input 1: " + output);
		        
		        if ((response.getStatus() == 200) && (response.getEntity(String.class).compareTo("true") == 0)) {
		            Toast.makeText(context, "Đã cập nhật", Toast.LENGTH_SHORT).show();
		            // refresh customers
		            
		            
		        }else
		        	Toast.makeText(context, "Không thể cập nhật, hãy xem lại kết nối", Toast.LENGTH_SHORT).show();
		        
		        System.out.println("Server response .... \n");
		        System.out.println("input 0: " + output);
		       
		       
				dialog.dismiss();
				
				//reload
				getOrderList();
				adapter = new OrdersManagerDetailArrayAdapter(context,
						android.R.layout.simple_list_item_1, takeOrderDetailList);
				ordersListView.setAdapter(adapter);
			}
		});

		//Cancel
		Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogButtonNO.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogButtonNO.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		Button dialogButtonDelete = (Button) dialog.findViewById(R.id.dialogButtonDelete);
		dialogButtonDelete.setVisibility(1);
		dialogButtonDelete.setText("");
		// if button is clicked, close the custom dialog
		dialogButtonDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				commitDialog(selectedValue);
				dialog.dismiss();
			}
		});
		
		dialog.show();
		
	}
	
	public static void commitDialog(final TakeOrderDetail selectedValue){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Cảnh báo, xóa bản ghi! ");
	
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Chấp nhận");
		// if button is clicked, close the custom dialog
		
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				

				//Sys
				
//				ObjectMapper mapper = new ObjectMapper();
//		        String orderDetail = new String();
//
//				try {
//
//					orderDetail = mapper.writeValueAsString(selectedValue);
//					
//				} catch (JsonGenerationException ex) {
//
//					ex.printStackTrace();
//
//				} catch (JsonMappingException ex) {
//
//					ex.printStackTrace();
//
//				} catch (IOException ex) {
//
//					ex.printStackTrace();
//
//				}
//		       
//				//Order ---------------------------------------------------------------
//				ClientResponse response = Rest.mService.path("webresources").path("deleteDetailOrder").accept("application/json")
//				.type("application/json").post(ClientResponse.class, orderDetail);
//
//		        String output = response.toString();
//		        System.out.println("input 1: " + output);
//		        
//		        if ((response.getStatus() == 200) && (response.getEntity(String.class).compareTo("true") == 0)) {
//		            Toast.makeText(context, "Đã xóa", Toast.LENGTH_SHORT).show();
//		            // refresh customers
//		            
//		            
//		        }else
//		        	Toast.makeText(context, "Không thể xóa, hãy xem lại kết nối", Toast.LENGTH_SHORT).show();
//		        
//		        System.out.println("Server response .... \n");
//		        System.out.println("input 0: " + output);
		       
		       
				dialog.dismiss();
				
				//reload
//				getOrderList();
//				adapter = new OrdersManagerDetailArrayAdapter(context,
//						android.R.layout.simple_list_item_1, takeOrderDetailList);
//				ordersListView.setAdapter(adapter);
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							
				dialog.dismiss();
			}
		});
		dialog.show();
		
	}
	
	public static void getOrderList(){
		//GET From server
		
		ClientResponse response = Rest.mService.path("webresources").path("getReturnOrderDetailList")
				.accept("application/json")
				.type("application/json").post(ClientResponse.class, order_id);
        System.out.println("________________ "+ response.toString());
        
        if(response.getStatus() != 200){
        	
        	return;
        }
        
        String re = response.getEntity(String.class);
        System.out.println("________________ "+ re);
        
        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        
		try {
//							File jsonFile = new File(jsonFilePath);
			takeOrderDetailList = mapper.readValue(re, TypeFactory.defaultInstance().constructCollectionType(List.class,
					TakeOrderDetail.class));
			//System.out.println("++++++++++++++ mdt "+customerList.get(0).getmMaDoiTuong());
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return ;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return ;
		} catch (IOException e) {
			e.printStackTrace();
			return ;
		}
		
	}
	
	public static boolean isOnline() {
//		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//		return cm.getActiveNetworkInfo() != null
//				&& cm.getActiveNetworkInfo().isConnectedOrConnecting();
		return true;
	}

	
	
}
