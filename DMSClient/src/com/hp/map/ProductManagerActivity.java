package com.hp.map;

import com.hp.domain.Product;
import com.hp.rest.ProductAPI.ModifyProductTask;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;

public class ProductManagerActivity extends TakeOrder_ProductActivity{
	
	public static Product selectedValue;
	
	public void init(){
		//Reset values and init it
		mProductsMap.clear();
		timeLine = true;
		
		title.setText("Quản lý sản phẩm");
		title.setVisibility(View.GONE);
		
		command = "getProductsList";
		customerID = "";
		
		mManager = true;
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.order_menu, menu);
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();
	    	return true;
	        
	    case R.id.action_add:
        	insertProduct();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	}
	
	public void insertProduct(){
		startActivity(new Intent(this, ProductAdditionActivity.class));
	}
	
	@Override
    public void onItemClick(AdapterView<?> a, View v, final int position, long id) 
    {
 		selectedValue = (Product) listView.getAdapter().getItem(position);
 		
 		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = li.inflate(R.layout.customer_comfirm_dialog, null, false);
		dialog.setContentView(view);
		dialog.setTitle("Lựa chọn của bạn");
		
		//final Button edit = (Button)dialog.findViewById(R.id.detail);
		final Button detail = (Button)dialog.findViewById(R.id.detail); detail.setText("Xem chi tiết");
		//final Button delete = (Button)dialog.findViewById(R.id.edit);
		final Button cancel = (Button)dialog.findViewById(R.id.cancel);
		
		/*edit.setVisibility(View.INVISIBLE);
		delete.setVisibility(View.INVISIBLE);
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(context, ProductEditerActivity.class));
				dialog.dismiss();
			}
		});*/
		
		detail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						startActivity(new Intent(context, ProductEditerActivity.class));
						dialog.dismiss();
			}
		});
		
		/*delete.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						comfirmDeleteDialog(selectedValue);
						dialog.dismiss();
					}
				});*/
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		
		dialog.show();
    }
	
	public void comfirmDeleteDialog(final Product product){
		final Dialog dialog = new Dialog(context);
		LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = li.inflate(R.layout.customer_comfirm_dialog, null, false);
		dialog.setContentView(v);
		dialog.setTitle("Lựa chọn của bạn");
		
		//final Button edit = (Button)dialog.findViewById(R.id.edit);
		final Button detail = (Button)dialog.findViewById(R.id.detail);
		//final Button delete = (Button)dialog.findViewById(R.id.delete);
		//delete.setVisibility(View.GONE);
		final Button cancel = (Button)dialog.findViewById(R.id.cancel);
		cancel.setVisibility(View.GONE);
		
		//edit.setText("Chấp nhận");
		detail.setText("Hủy");
		
		/*edit.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteProduct(product);
				dialog.dismiss();
			}
		});*/
		detail.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		
		dialog.show();
	}
	
	public void deleteProduct(Product product){
		
		ModifyProductTask deleteData = new ModifyProductTask(context, "deleteProduct", product, false, this);
		deleteData.execute();
		
		
	}

}
