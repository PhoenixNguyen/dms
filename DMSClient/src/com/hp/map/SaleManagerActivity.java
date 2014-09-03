package com.hp.map;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hp.domain.TakeOrder;
import com.hp.order_manager.OrdersManagerArrayAdapter;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.SaleOrderAPI.GetTakeOrderListTask;
import com.hp.rest.SaleOrderAPI.PutTakeOrderTask;
import com.hp.rest.TakeOrderAPI;
import com.hp.sale_order.DialogArrayAdapter;
import com.sun.jersey.api.client.ClientResponse;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SaleManagerActivity extends TakeOrdersManagerActivity{
	
	public void init(){
		activityClass = SaleManagerDetailActivity.class; //SaleManagerDetail_Main_Activity
		getList = "getSaleOrderList";
		deleteValue = "deleteSaleOrder";
	}
	
	public void newTakeOrder(){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog.setContentView(v);
		
		dialog.setTitle("Tạo mới hóa đơn bán hàng bằng ");
	
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Tạo mới bằng tay");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(context, CustomerListActivity.class));
			
				dialog.dismiss();
			}
		});

		//
		Button dialogDeleteButton = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hóa đơn đặt hàng");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				loadTakeOrderDialog(null);
				//commitDialog(selectedValue);
				dialog.dismiss();
			}
		});
		dialog.show();
		
		
	}
	
	String[] selectedValue ;
	public Dialog dialogLoad;
	public void loadTakeOrderDialog(String pDate){
		selectedValue = new String[]{""};
		
		dialogLoad = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.schedule_dialog, null, false);
		dialogLoad.setContentView(v);
		final ListView listViewCus = (ListView)dialogLoad.findViewById(R.id.list_view_cus);
		
		dialogLoad.setTitle("Chọn hóa đơn đặt");
		
		GetTakeOrderListTask getData = new GetTakeOrderListTask(context, "getTakeOrderList", Rest.mStaff.getId(),
				listViewCus, selectedValue, SaleManagerActivity.this);
        getData.execute();
	   
		
		Button dialogButton = (Button) dialogLoad.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Chấp nhận");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.out.println("__22 " + selectedValue[0]);
				PutTakeOrderTask putData = new PutTakeOrderTask(context, "putSaleOrder", adapter,
						ordersListView, selectedValue, SaleManagerActivity.this);
				putData.execute();
		        
			}
		});
		
		
		Button dialogButtonDistroy = (Button) dialogLoad.findViewById(R.id.dialogButtonNO);
		dialogButtonDistroy.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogButtonDistroy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialogLoad.dismiss();
		        
			}
		});

		
		
	}
	
	@SuppressLint("NewApi")
	static <T> T[] append(T[] arr, T element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
}
