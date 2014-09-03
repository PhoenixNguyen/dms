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

public class ProductEditerActivity extends ProductAdditionActivity {
	
	
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
		
		
		//Set again
		product_id.setText(ProductManagerActivity.selectedValue.getProductID());
		product_id.setEnabled(false);
		product_name.setText(ProductManagerActivity.selectedValue.getProductName());
		product_barcode.setText(ProductManagerActivity.selectedValue.getBarcode());
		product_brand.setText(ProductManagerActivity.selectedValue.getBrand());
		product_origin.setText(ProductManagerActivity.selectedValue.getOrigin());
		product_packing.setText(ProductManagerActivity.selectedValue.getPackingSpecifications());
		product_quantification.setText(ProductManagerActivity.selectedValue.getQuantification());
		product_tax.setText(ProductManagerActivity.selectedValue.getVatTax()+"");
		product_import_price.setText(ProductManagerActivity.selectedValue.getImportPrices()+"");
		product_export_price.setText(ProductManagerActivity.selectedValue.getExportPrices()+"");
		product_description.setText(ProductManagerActivity.selectedValue.getDescription());
		
		final List<String> list = new ArrayList<String>();
		int key = 0;
		for(int i = 0; i < ProviderAPI.providersList.size(); i++){
	
			//Add
			list.add(ProviderAPI.providersList.get(i).getId());
			
			if(ProductManagerActivity.selectedValue.getProvider().compareTo(ProviderAPI.providersList.get(i).getId()) == 0){
				key = i;
			}
		}
		
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		product_provider.setAdapter(dataAdapter);
		//Set a product provider 
		product_provider.setSelection(key);
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
			
			ProductManagerActivity.selectedValue.setProductName(name);
			ProductManagerActivity.selectedValue.setBarcode(barcode);
			ProductManagerActivity.selectedValue.setBrand(brand);
			ProductManagerActivity.selectedValue.setOrigin(origin);
			ProductManagerActivity.selectedValue.setPackingSpecifications(packing);
			ProductManagerActivity.selectedValue.setQuantification(quantification);
			ProductManagerActivity.selectedValue.setVatTax(tax2);
			ProductManagerActivity.selectedValue.setImportPrices(im_pr2);
			ProductManagerActivity.selectedValue.setExportPrices(ex_pr2);
			ProductManagerActivity.selectedValue.setDescription(description);
			ProductManagerActivity.selectedValue.setProvider(provider_selected);
			
			System.out.println("provider_selected: "+provider_selected + " name: " + name);
			insertProduct(ProductManagerActivity.selectedValue);
		
	}
	
	public void insertProduct(Product product){
		
		ModifyProductTask addData = new ModifyProductTask(context, "updateProduct", product, true);
		addData.execute();
		
		
	}

	public boolean isOnline() { 
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE); 
		return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnectedOrConnecting(); 
	}

}
