package com.hp.map;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.CustomerAPI.ModifyCustomerTask;
import com.hp.rest.TakeOrderDetailAPI;
import com.hp.rest.TakeOrderDetailAPI.GetTakeOrderDetailTask;
import com.hp.rest.TakeOrderDetailAPI.ModifyTakeOrderDetailTask;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TableRow.LayoutParams;

public class TakeOrdersDetailManagerActivity extends Activity{
	
	//add_detail= 0 if update takeorder, add_detail= 1 if update inventory, add_detail= 2 if update sale order
	public static int add_detail = 0;
	
	public static String order_id;
	
	//public static List<TakeOrderDetail> takeOrderDetailList = new ArrayList<TakeOrderDetail>();
	public Context context = this;
	public ListView ordersListView;
	public OrdersManagerDetailArrayAdapter adapter;
	
	public String getListDetail;
	
	public String updateData;
	public String deleteData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orders_detail_manager);
				
		if(TakeOrdersManagerActivity.selectedValue != null){
		
			TextView title = (TextView)findViewById(R.id.title);
			order_id = TakeOrdersManagerActivity.selectedValue.getId();
			title.setText("Mã HĐ: "+order_id);
			
			TextView cus_id0 = (TextView)findViewById(R.id.cus_id);
			TextView discount0 = (TextView)findViewById(R.id.discount);
			TextView valuetotal0 = (TextView)findViewById(R.id.valuetotal);
			TextView note2 = (TextView)findViewById(R.id.note);
			
			cus_id0.setVisibility(View.VISIBLE);
			discount0.setVisibility(View.VISIBLE);
			valuetotal0.setVisibility(View.VISIBLE);
			note2.setVisibility(View.VISIBLE);
			
			cus_id0.setText("Mã KH: " + TakeOrdersManagerActivity.selectedValue.getCustomerID());
			discount0.setText("Giảm giá (%): " + TakeOrdersManagerActivity.selectedValue.getDiscount());
			valuetotal0.setText("Tổng giá trị: " + new BigDecimal(TakeOrdersManagerActivity.selectedValue.getAfterPrivate()).toString());
			note2.setText("Ghi chú: " + TakeOrdersManagerActivity.selectedValue.getNote());
		}
		//init item commons
		init();
		
		getOrderList();
		
	}
	
	public void init(){
		getListDetail = "getTakeOrderDetailList";
		updateData = "updateDetailOrder";
		deleteData = "deleteDetailOrder";
		
		//update takeorder
		add_detail = 0;
	}
	
	public void onResume(){
		super.onResume();
		this.onCreate(null);
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order_menu, menu);
		
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
//        case R.id.action_search:
//        	
//            return true;
        case R.id.action_add:
        	newOrderDetail();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
//	    case android.R.id.home:
//	    	menuDialog();
//
//	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
//	        break;
	        
	    case R.id.action_add:
        	newOrderDetail();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	    //return true;
	}
	
	public void addListView() {

		
		if (TakeOrderDetailAPI.takeOrderDetailList.size() == 0) {
			return;
		}

		ordersListView = (ListView) findViewById(R.id.list_view_product);
		adapter = new OrdersManagerDetailArrayAdapter(this,
				android.R.layout.simple_list_item_1, TakeOrderDetailAPI.takeOrderDetailList);
		ordersListView.setAdapter(adapter);

		ordersListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> a, View v, int position,
					long id) {
				System.out.println("Click!");
				TakeOrderDetail selectedValue = (TakeOrderDetail) ordersListView.getAdapter().getItem(position);
		    	 addCustomerDialog(selectedValue, position);
				
			}
		});
	}

	
	public void addCustomerDialog(final TakeOrderDetail selectedValue, final int position){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.order_product_dialog);
		dialog.setTitle("Thay đổi số lượng");

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.name);
		text.setText(""+selectedValue.getProductName());

		TextView price = (TextView) dialog.findViewById(R.id.price);
		price.setText(""+selectedValue.getBeforeOrderPrice());
		
		final EditText discount = (EditText) dialog.findViewById(R.id.discount);
		discount.setText(selectedValue.getDiscount()+"");
		
		final EditText count = (EditText)dialog.findViewById(R.id.count);
		count.setText(selectedValue.getNumber()+"");
		
		final EditText note = (EditText) dialog.findViewById(R.id.note);
		note.setText(selectedValue.getNote()+"");
		
		//discount product
		final TextView product_discount = (TextView) dialog.findViewById(R.id.product_discount);
		final EditText product_discount_count = (EditText) dialog.findViewById(R.id.product_discount_count);
		product_discount.setVisibility(View.VISIBLE);
		product_discount_count.setVisibility(View.VISIBLE);
		product_discount_count.setText(selectedValue.getPromotionalProductMount()+"");
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Cập nhật");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				if(count == null)
//					return;
//				
//				int number = Integer.parseInt(count.getText().toString());
				
				String count2 = count.getText().toString();
				String discount2 = discount.getText().toString();
				String product_discount_count2 = product_discount_count.getText().toString();
				
				int number = 0;
				int discount = 0;
				int promotionalAmount = 0;
				
				if(count2.compareTo("") != 0 && String.valueOf(count2).length() < 10)
					number = Integer.parseInt(count2);
				else{
					Toast.makeText(context, "Hãy nhập số lượng nhiều hơn 0 và ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(String.valueOf(discount2).length() < 10){
					if(discount2.compareTo("") != 0){
						discount = Integer.parseInt(discount2);
						if(discount > 100){
							Toast.makeText(context, "Bạn đã nhập quá 100% ", Toast.LENGTH_SHORT).show();
							return;
						}
					}
				}
				else{
					Toast.makeText(context, "Hãy nhập phần trăm ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
					return;
				}
				
				//promotinal product
				if(String.valueOf(product_discount_count2).length() < 10){
					if(product_discount_count2.compareTo("") != 0)
						promotionalAmount = Integer.parseInt(product_discount_count2);
				}
				else{
					Toast.makeText(context, "Hãy nhập số lượng nhiều hơn 0 và ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
					return;
				}
				
				float priceTotal = (float)Math.ceil((selectedValue.getAfterOrderPrice() - 
						selectedValue.getAfterOrderPrice() * discount / 100) * number);
				selectedValue.setDiscount(discount);
				selectedValue.setNumber(number);
				selectedValue.setPriceTotal(priceTotal);
				selectedValue.setNote(note.getText().toString());
				selectedValue.setPromotionalProductMount(promotionalAmount);
				
			
				//Sys
				ModifyTakeOrderDetailTask update = new ModifyTakeOrderDetailTask(context, updateData, 
						selectedValue, adapter, ordersListView, TakeOrdersDetailManagerActivity.this, dialog);
				update.execute();
				
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
		dialogButtonDelete.setText("Xóa");
		// if button is clicked, close the custom dialog
		dialogButtonDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				commitDialog(selectedValue);
				dialog.dismiss();
			}
		});
		
		dialog.getWindow().setLayout(android.app.ActionBar.LayoutParams.FILL_PARENT, android.app.ActionBar.LayoutParams.WRAP_CONTENT);
		dialog.show();
		
	}
	
	
	public void commitDialog(final TakeOrderDetail selectedValue){
		final Dialog dialog2 = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_selected_dialog, null, false);
		dialog2.setContentView(v);
		
		dialog2.setTitle("Cảnh báo, xóa bản ghi! ");
	
		Button dialogButton = (Button) dialog2.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Chấp nhận");
		// if button is clicked, close the custom dialog
		
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				

				//Sys
				ModifyTakeOrderDetailTask update = new ModifyTakeOrderDetailTask(context, deleteData, 
						selectedValue, adapter, ordersListView, TakeOrdersDetailManagerActivity.this, dialog2);
				update.execute();
				
			}
		});

		//Delete a schedule
		Button dialogDeleteButton = (Button) dialog2.findViewById(R.id.dialogButtonNO);
		dialogDeleteButton.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogDeleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
							
				dialog2.dismiss();
			}
		});
		dialog2.show();
		
	}
	
	public void getOrderList(){
		//GET From server
		GetTakeOrderDetailTask getData = new GetTakeOrderDetailTask(context, getListDetail, order_id,
				    this);
         getData.execute();
		  
	}
	
	
	
	public void newOrderDetail(){
		//Status = true
		TakeOrder_ProductActivity.add_take_order_detail = true;
		TakeOrder_ProductActivity.timeLine = true;
		
		startActivity(new Intent(this, TakeOrder_TabActivity.class));
		
	}
}
