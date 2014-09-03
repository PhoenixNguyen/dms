package com.hp.map;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hp.domain.Product;
import com.hp.domain.Stock;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.rest.Rest;
import com.sun.jersey.api.client.ClientResponse;

public class InventoryManagerActivity extends TakeOrdersManagerActivity{
	public void init(){
			activityClass = InventoryManagerDetailActivity.class;
			getList = "getInventoryManagerList";
			deleteValue = "deleteInventoryManager";
		}
	
}