package com.hp.map;

import java.util.Collections;
import java.util.Set;

import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.domain.Product;
import com.hp.order.CustomOnItemSelectedListener;
import com.hp.order.ProductArrayAdapter;

public class InventoryManager_ProductActivity extends TakeOrder_ProductActivity{
	public void init(){
		
		title.setText("Quản lý hàng tồn");
		command = "getProductsList";
		customerID = "";
		mManager = false;		
//		title = (TextView)findViewById(R.id.title);
//		title.setText("Quản lý kho");
	}
	
	
	@Override
    public void onItemClick(AdapterView<?> a, View v, final int position, long id) 
    {
 		final Product selectedValue = (Product) listView.getAdapter().getItem(position);
 
 		//Toast.makeText(getBaseContext(), "Click", Toast.LENGTH_LONG).show();
 		// custom dialog
		final Dialog dialog = new Dialog(context);
		
		dialog.setContentView(R.layout.order_product_dialog);
		dialog.setTitle("Số lượng");

		// set the custom dialog components - text, image and button
		EditText text = (EditText) dialog.findViewById(R.id.name);
		text.setText(""+selectedValue.getProductName());

		EditText price = (EditText) dialog.findViewById(R.id.price);
		price.setText(""+selectedValue.getExportPrices());
		
		final EditText discount = (EditText) dialog.findViewById(R.id.discount);
		discount.setVisibility(View.GONE);
		
		final EditText count = (EditText)dialog.findViewById(R.id.count);
		
		final EditText note = (EditText) dialog.findViewById(R.id.note);
		
		//discount product
//		final TextView product_discount = (TextView) dialog.findViewById(R.id.product_discount);
//		final EditText product_discount_count = (EditText) dialog.findViewById(R.id.product_discount_count);
//		product_discount.setVisibility(View.VISIBLE);
//		product_discount_count.setVisibility(View.VISIBLE);
		
		Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonYES);
		dialogButton.setText("Cập nhật");
		// if button is clicked, close the custom dialog
		dialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			
				System.out.println("__ "+ line);
				String count2 = count.getText().toString();
				
				int number = 0;
				int discount = 0;
				if(count2.compareTo("") != 0 && String.valueOf(count2).length() < 10)
					number = Integer.parseInt(count2);
				else{
					Toast.makeText(context, "Hãy nhập số lượng nhiều hơn 0 và ít hơn 0.1 tỷ ", Toast.LENGTH_SHORT).show();
					return;
				}
				
				
				
				TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + "").get(position).setTotal(number);
				TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + "").get(position).setDiscount(discount);
				TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + "").get(position).setNote(note.getText().toString());
				
				Collections.sort(TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + ""));
				Collections.reverse(TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + ""));
				
				//finish
				dialog.dismiss();
				
				adapter = new ProductArrayAdapter(context, android.R.layout.simple_list_item_1
						, TakeOrder_ProductActivity.mProductsMap.get(CustomOnItemSelectedListener.mProviderIndex + ""), mManager);
				listView.setAdapter(adapter);
				
				//SET total line
				Set<String> keyset = TakeOrder_ProductActivity.mProductsMap.keySet();
				line = 0;
				for(String key : keyset){
				for(int i = 0; i < TakeOrder_ProductActivity.mProductsMap.get(key).size(); i++)
					if(TakeOrder_ProductActivity.mProductsMap.get(key).get(i).getTotal() > 0){
						line++;
					}
				}
				total_value.setText(line+"");
				//
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
		dialog.getWindow().setLayout(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		dialog.show();
     }
}
