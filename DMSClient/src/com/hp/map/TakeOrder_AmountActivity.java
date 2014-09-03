package com.hp.map;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.domain.TakeOrder;
import com.hp.rest.Rest;
import com.hp.rest.TakeOrderAPI;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.hp.rest.TakeOrderAPI.PutTakeOrderTask;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TakeOrder_AmountActivity extends Activity implements OnClickListener{
	
	private Context context = this;
	
	private EditText total_value;
	private EditText document_value;
	private EditText discount_value;
	private EditText tax_value;
	private EditText sum_value;
	private EditText dc2_value;
	private EditText note_value;
	
	private EditText customer_id;
	private EditText customer_name;
	
	private EditText discount_percent;
	
	private Button save;
	float pricesTotal;
	float discount;
	
	private int numberTotal;
	
	public String putData;
	public String putDataDetail;
	
	public TextView title;
	public TextView sum;
	public LinearLayout linearlayout_discount;
	
	public EditText note;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.amount);
		
		title = (TextView)findViewById(R.id.title);
		sum = (TextView)findViewById(R.id.sum);
		linearlayout_discount = (LinearLayout)findViewById(R.id.linearlayout_discount);

		customer_id = (EditText)findViewById(R.id.customer_id);
		customer_name = (EditText)findViewById(R.id.customer_name);
		
		total_value = (EditText)findViewById(R.id.total_value);
		document_value = (EditText)findViewById(R.id.document_value);
		
		discount_percent = (EditText)findViewById(R.id.discount_percent);
		discount_value = (EditText)findViewById(R.id.discount_value);
		sum_value = (EditText)findViewById(R.id.sum_value);
		
		note = (EditText)findViewById(R.id.note);
	
		save = (Button)findViewById(R.id.save);
		
		//init something
		init();
				
		pricesTotal = 0;
		numberTotal = 0;
		for(int i = 0; i < TakeOrder_ReViewActivity.takeOrderDetailList.size(); i++){
			numberTotal = numberTotal +  TakeOrder_ReViewActivity.takeOrderDetailList.get(i).getNumber();
			pricesTotal = pricesTotal +  TakeOrder_ReViewActivity.takeOrderDetailList.get(i).getPriceTotal() ;
					
		}
		
		if(numberTotal == 0)
			return;
		
		document_value.setText(TakeOrder_ReViewActivity.takeOrderDetailList.size() + " sản phẩm và " + numberTotal + " đầu mục");
		//sum_value.setText(pricesTotal + "");
		total_value.setText((new BigDecimal(pricesTotal)).toString());
		sum_value.setText((new BigDecimal(pricesTotal)).toString());
		
		save.setOnClickListener(this);
		
		
		//Add Take Order
		if(!TakeOrder_ProductActivity.add_take_order_detail && CustomerMapActivity.mSelectedCustomer != null){
			customer_id.setText(CustomerMapActivity.mSelectedCustomer.getMaDoiTuong());
			customer_name.setText(CustomerMapActivity.mSelectedCustomer.getDoiTuong());
		}
		//ELSE Update TakeOrder
		else
		{
			if(TakeOrderAPI.takeOrderList != null && TakeOrderAPI.takeOrderList.size() > 0)
			for(int i = 0; i < TakeOrderAPI.takeOrderList.size(); i++){
				System.out.println("__++"+TakeOrderAPI.takeOrderList.get(i).getId() +" -- " + TakeOrder_ProductActivity.take_order_id);
	        	if(TakeOrderAPI.takeOrderList.get(i).getId().compareTo(                      //TakeOrdersDetailManagerActivity.takeOrderDetailList.get(j) 
	        			TakeOrder_ProductActivity.take_order_id) == 0){
	        		System.out.println(" ACC ");
	        		customer_id.setText(TakeOrderAPI.takeOrderList.get(i).getId());
	    			customer_name.setText(TakeOrderAPI.takeOrderList.get(i).getCustomerName());
	    			
	    			//Show discount
	    			discount_percent.setText(TakeOrderAPI.takeOrderList.get(i).getDiscount() +"");
	    			//set discount
					discount = (float)Math.ceil(pricesTotal*TakeOrderAPI.takeOrderList.get(i).getDiscount()/100);
					
					discount_value.setText((new BigDecimal(discount)).toString());
					
					//set sume
					sum_value.setText((new BigDecimal(pricesTotal - discount)).toString());
					
					note.setText(TakeOrderAPI.takeOrderList.get(i).getNote());
	        		break;
	        	}
	        }
			
			
		}
		
	}
	
	public void init(){
		putData = "putTakeOrder";
		putDataDetail = "putOrdersDetailList";
	}
	@Override
	protected void onResume() {

	   super.onResume();
	   this.onCreate(null);
	}

	public void setDiscount(View view){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.order_product_dialog);
		dialog.setTitle("Đặt giảm giá");
		
		TextView text0 = (TextView) dialog.findViewById(R.id.name);
		text0.setVisibility(View.GONE);
		TextView price0 = (TextView) dialog.findViewById(R.id.price);
		price0.setVisibility(View.GONE);
		EditText discount0 = (EditText) dialog.findViewById(R.id.discount);
		discount0.setVisibility(View.GONE);
		EditText note0 = (EditText) dialog.findViewById(R.id.note);
		note0.setVisibility(View.GONE);
		
		TextView product_name = (TextView) dialog.findViewById(R.id.product_name);
		product_name.setVisibility(View.GONE);
		TextView product_price = (TextView) dialog.findViewById(R.id.product_price);
		product_price.setVisibility(View.GONE);
		
		final EditText count = (EditText)dialog.findViewById(R.id.count);
		count.setHint("Phần trăm");
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Cập nhật");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				String count2 = count.getText().toString();
				int number = 0;
				if(count2.compareTo("") != 0 && String.valueOf(count2).length() < 10)
					number = Integer.parseInt(count2);
				else{
					Toast.makeText(context, "Hãy nhập số lượng nhiều hơn 0 và ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
					return;
				}
				
				//set discount
				discount = (float)Math.ceil(pricesTotal*number/100);
				
				discount_percent.setText(number+"");
				discount_value.setText((new BigDecimal(discount)).toString());
				
				//set sume
				sum_value.setText((new BigDecimal(pricesTotal - discount)).toString());
				dialog.dismiss();
			}
		});
		
		Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNO);
		dialogButtonNO.setText("Hủy");
		// if button is clicked, close the custom dialog
		dialogButtonNO.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//finish
				dialog.dismiss();
			}
			
		});
		dialog.show();
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == save){
			//Create order:
			if(numberTotal == 0)
				return;
			
			//If add more products into take order ========================================================
			if(TakeOrder_ProductActivity.add_take_order_detail){
					//Set order ID
					for(int i = 0; i < TakeOrder_ReViewActivity.takeOrderDetailList.size(); i++){
						
						TakeOrder_ReViewActivity.takeOrderDetailList.get(i).setTakeOrderID(
								TakeOrder_ProductActivity.take_order_id);
					}
					
					// Send
					ObjectMapper mapper = new ObjectMapper();
			        String orderDetailList = new String();
			        String TakeOrderStr = new String();
			        TakeOrder order = null;
			        for(int i = 0; i < TakeOrderAPI.takeOrderList.size(); i++){
			        	if(TakeOrderAPI.takeOrderList.get(i).getId().compareTo(
			        			TakeOrder_ProductActivity.take_order_id) == 0){
			        		order = TakeOrderAPI.takeOrderList.get(i);
			        		order.setDiscount(Float.parseFloat(discount_percent.getText().toString()));
			        		order.setAfterPrivate(pricesTotal - discount);
			        		order.setNote(note.getText().toString());
			        		
			        		break;
			        	}
			        }
					try {
						TakeOrderStr = mapper.writeValueAsString(order);
						orderDetailList = mapper.writeValueAsString(TakeOrder_ReViewActivity.takeOrderDetailList);
						
					} catch (JsonGenerationException ex) {

						ex.printStackTrace();

					} catch (JsonMappingException ex) {

						ex.printStackTrace();

					} catch (IOException ex) {

						ex.printStackTrace();

					}
					
					String update_main_command = "updateAddingTakeOrder";
					String update_detail_command = "addOrdersDetailForTakeOrder";
					
					//If update Inventory
					if(TakeOrdersDetailManagerActivity.add_detail == 1){
						update_main_command = "updateAddingInventory";
						update_detail_command = "updateAddingInventoryDetail";
					}
					else
					
					//If update Sale order
					if(TakeOrdersDetailManagerActivity.add_detail == 2){
						update_main_command = "updateAddingSaleOrder";
						update_detail_command = "updateAddingSaleOrderDetail";
					}
					//UPDATE
					//Order ---------------------------------------------------------------
					
					PutTakeOrderTask puttData = new PutTakeOrderTask(context, update_main_command, update_detail_command, 
							TakeOrderStr, orderDetailList, Rest.mStaff.getId(), this, true
						    );
					puttData.execute();
				      
				
				return;
			}
			
			//NEW
			/////////////////////////////////////////////////////////////////////////////=========================
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new  Date();
			String date2 = df.format(date);
			
			String orderID = CustomerMapActivity.mSelectedCustomer.getMaDoiTuong()+"-" + date2;
			
			TakeOrder order = new TakeOrder(orderID
					, Timestamp.valueOf(date2), Timestamp.valueOf(date2)
					, CustomerMapActivity.mSelectedCustomer.getMaDoiTuong()
					, CustomerMapActivity.mSelectedCustomer.getDoiTuong()
					, CustomerMapActivity.mSelectedCustomer.getDiaChi()
					, CustomerMapActivity.mSelectedCustomer.getDienThoai()
					, CustomerMapActivity.mSelectedCustomer.getDiaChi()
					, ""
					
					, 0
					, pricesTotal - discount, pricesTotal - discount
					, Integer.parseInt(discount_percent.getText().toString())
					, 0, Timestamp.valueOf(date2), Timestamp.valueOf(date2)
					, Rest.mStaff.getId(), Rest.mStaff.getId());
			
			//Set order ID
			for(int i = 0; i < TakeOrder_ReViewActivity.takeOrderDetailList.size(); i++){
				TakeOrder_ReViewActivity.takeOrderDetailList.get(i).setTakeOrderID(orderID);
			}
			
			// Send
			ObjectMapper mapper = new ObjectMapper();
	        String TakeOrderStr = new String();
	        String orderDetailList = new String();

			try {

				TakeOrderStr = mapper.writeValueAsString(order);
				orderDetailList = mapper.writeValueAsString(TakeOrder_ReViewActivity.takeOrderDetailList);
				
			} catch (JsonGenerationException ex) {

				ex.printStackTrace();

			} catch (JsonMappingException ex) {

				ex.printStackTrace();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
	       
			PutTakeOrderTask puttData = new PutTakeOrderTask(context, putData, putDataDetail, 
					TakeOrderStr, orderDetailList, Rest.mStaff.getId(), this, false
				    );
			puttData.execute();

//	        
	        ////////////////////////// save order detail//////////////////////////////////
	        
		}
	}
	
	public void updateTakeOrderDetail(){
		
	}
	
	public void updateInventoryDetail(){
		
	}
	
	public void resetValue(){
		TakeOrder_ProductActivity.mProductsMap.clear();
		TakeOrder_ReViewActivity.takeOrderDetailList.clear();
		pricesTotal = 0;
		discount = 0;
		TakeOrder_ProductActivity.timeLine = true;
		TakeOrder_ProductActivity.add_take_order_detail = false;
		
		onResume();
	}
}
