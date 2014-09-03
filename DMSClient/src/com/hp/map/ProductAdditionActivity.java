package com.hp.map;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.domain.Customer;
import com.hp.domain.Product;
import com.hp.rest.ProviderAPI;
import com.hp.rest.Rest;
import com.hp.rest.ProductAPI.ModifyProductTask;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProductAdditionActivity extends MainMenuActivity {
	
	public Context context = this;
		
	
	public EditText product_id;
	public EditText product_name;
	public EditText product_barcode;
	public EditText product_brand;
	public EditText product_origin;
	public EditText product_packing;
	public EditText product_quantification;
	public EditText product_tax;
	public EditText product_import_price;
	
	public EditText product_export_price;
	//public EditText product_provider;
	public EditText product_description;
	public EditText product_image_path;
	
	
	
	public Spinner product_provider;
	
	public String provider_selected;
	
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.product_addition);
				
		product_id = (EditText)findViewById(R.id.product_id);
		product_name = (EditText)findViewById(R.id.product_name);
		product_barcode = (EditText)findViewById(R.id.product_barcode);
		product_brand = (EditText)findViewById(R.id.product_origin);
		product_origin = (EditText)findViewById(R.id.product_origin);
		product_packing = (EditText)findViewById(R.id.product_packing);
		product_quantification = (EditText)findViewById(R.id.product_quantification);
		product_tax = (EditText)findViewById(R.id.product_tax);
		product_import_price = (EditText)findViewById(R.id.product_import_price);
		
		product_export_price = (EditText)findViewById(R.id.product_export_price);
		
		product_provider = (Spinner)findViewById(R.id.product_provider);
		
		product_description = (EditText)findViewById(R.id.product_description);
		//product_image_path = (EditText)findViewById(R.id.product_image_path);
		
		
		final List<String> list = new ArrayList<String>();
		for(int i = 0; i < ProviderAPI.providersList.size(); i++){
	
			//Add
			list.add(ProviderAPI.providersList.get(i).getId());
		}
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		product_provider.setAdapter(dataAdapter);
		product_provider.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
				provider_selected = list.get(pos);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.customer_manager_menu, menu);
		
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
//        case R.id.action_search:
//        	
//            return true;
        case R.id.action_save:
        	getProduct();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);
        }
    }
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	        
	    case R.id.action_save:
        	getProduct();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	public void getProduct(){
		
			String id = product_id.getText().toString();
			String name = product_name.getText().toString();
			String barcode = product_barcode.getText().toString();
			String brand = product_brand.getText().toString();
			String origin = product_origin.getText().toString();
			String packing = product_packing.getText().toString();
			String quantification = product_quantification.getText().toString();
			String tax = product_tax.getText().toString();
			String import_price = product_import_price.getText().toString();
			
			String export_price = product_export_price.getText().toString();
			//String provider = product_provider.getText().toString();
			String description = product_description.getText().toString();
			//String note = product_image_path.getText().toString();
			
			float tax2 = 0;
			float im_pr2 = 0;
			float ex_pr2 = 0;
			
			if(tax!= null && tax.compareTo("") != 0)
				tax2 = Float.parseFloat(tax);
			if(import_price!= null && import_price.compareTo("") != 0)
				im_pr2 = Float.parseFloat(import_price);
			if(export_price!= null && export_price.compareTo("") != 0)
				ex_pr2 = Float.parseFloat(export_price);
			
			Product product = new Product(barcode, id
					, name, brand
					, origin, packing
					, quantification, tax2
					, im_pr2, ex_pr2
					, provider_selected, description
					, "");
			System.out.println("provider_selected: "+provider_selected);
			insertProduct(product);
		
	}
	
	public void insertProduct(Product product){
		
		ModifyProductTask addData = new ModifyProductTask(context, "insertProduct", product, true);
		addData.execute();
		
	}

	public boolean isOnline() { 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); 
		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting(); 
	}

}
