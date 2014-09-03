package com.hp.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import com.hp.domain.Product;

import com.hp.map.TakeOrder_ProductActivity;
import com.hp.map.TakeOrdersDetailManagerActivity;
import com.hp.rest.ProductAPI.GetProductListTask;
import com.hp.rest.Rest;
import com.hp.rest.CustomerAPI.GetCustomerListTask;
import com.sun.jersey.api.client.ClientResponse;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
	
	ListView listView;
	Context context;
	EditText search;
	ProductArrayAdapter adapter;
	String command;
	String customerID;
	
	public static int mProviderIndex = 0;
	
	private boolean mManager;
	
	public CustomOnItemSelectedListener(Context context, ListView listView
			, EditText search, String command, String customerID, boolean pManager){
		this.listView = listView;
		this.context = context;
		this.search = search;
		this.command = command;
		this.customerID= customerID; 
		this.mManager = pManager;
		
	}
	public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
//		Toast.makeText(parent.getContext(), 
//			"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
//			Toast.LENGTH_SHORT).show();
		mProviderIndex = pos;
		String providerID = parent.getItemAtPosition(pos).toString();
		String data;
		if(customerID == null || customerID == "")
			data = providerID;
		else
			data = providerID +"::" + customerID;
		
		System.out.println("____ " + data + "___ " );
				
		GetProductListTask getData = new GetProductListTask(context, command,
			   adapter, listView, mManager, pos, search);
        getData.execute();
	    		
	  }
	 
	  
	  @Override
	  public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	  }
}
