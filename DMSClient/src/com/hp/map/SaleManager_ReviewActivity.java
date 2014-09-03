package com.hp.map;

import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.domain.TakeOrderDetail;
import com.hp.order_manager.OrdersManagerDetailArrayAdapter;

public class SaleManager_ReviewActivity extends TakeOrder_ReViewActivity{
	public void init(){
	}
	
	
//	public void addCustomerDialog(final TakeOrderDetail selectedValue, final int position){
//		final Dialog dialog = new Dialog(context);
//		dialog.setContentView(R.layout.order_product_dialog);
//		dialog.setTitle("Thay đổi số lượng");
//
//		// set the custom dialog components - text, image and button
//		TextView text = (TextView) dialog.findViewById(R.id.name);
//		text.setText(""+selectedValue.getProductName());
//
//		TextView price = (TextView) dialog.findViewById(R.id.price);
//		price.setText(""+selectedValue.getBeforeOrderPrice());
//		
//		final EditText discount = (EditText) dialog.findViewById(R.id.discount);
//		//discount.setText(takeOrderDetailList.get(position).getmDiscount()+"");
//		discount.setVisibility(View.GONE);
//				
//		final EditText count = (EditText)dialog.findViewById(R.id.count);
//		count.setText(takeOrderDetailList.get(position).getNumber()+"");
//		
//		final EditText note = (EditText) dialog.findViewById(R.id.note);
//		note.setText(takeOrderDetailList.get(position).getNote()+"");
//		
//		//discount product
////		final TextView product_discount = (TextView) dialog.findViewById(R.id.product_discount);
////		final EditText product_discount_count = (EditText) dialog.findViewById(R.id.product_discount_count);
////		product_discount.setVisibility(View.VISIBLE);
////		product_discount_count.setVisibility(View.VISIBLE);
//		
//		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
//		dialogButton.setText("Cập nhật");
//		// if button is clicked, close the custom dialog
//		dialogButton.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				
//				String count2 = count.getText().toString();
//				
//				int number = 0;
//				int discount = 0;
//				if(count2.compareTo("") != 0 && String.valueOf(count2).length() < 10)
//					number = Integer.parseInt(count2);
//				else{
//					Toast.makeText(context, "Hãy nhập số lượng nhiều hơn 0 và ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
//					return;
//				}
//				
//				
//				
//				float priceTotal = (float)Math.ceil((takeOrderDetailList.get(position).getAfterOrderPrice() - 
//						takeOrderDetailList.get(position).getAfterOrderPrice() * discount / 100) * number);
//				takeOrderDetailList.get(position).setDiscount(discount);
//				takeOrderDetailList.get(position).setNumber(number);
//				takeOrderDetailList.get(position).setPriceTotal(priceTotal);
//				takeOrderDetailList.get(position).setNote(note.getText().toString());
//
//				dialog.dismiss();
//				
//				//reload
//				adapter = new OrdersManagerDetailArrayAdapter(context,
//						android.R.layout.simple_list_item_1, takeOrderDetailList);
//				ordersListView.setAdapter(adapter);
//				
//				//Set call back product list
//				callbackSetMap(selectedValue.getProductID(), number, 0, note.getText().toString(), 0);
//				//if number = 0 --> remove
//				if(number == 0){
//					onResume();
//				}
//				
//				
//			}
//		});
//
//		//Cancel
//		Button dialogButtonNO = (Button) dialog.findViewById(R.id.dialogButtonNO);
//		dialogButtonNO.setText("Hủy");
//		// if button is clicked, close the custom dialog
//		dialogButtonNO.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				dialog.dismiss();
//			}
//		});
//		
//		dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
//		dialog.show();
//		
//	}
}
