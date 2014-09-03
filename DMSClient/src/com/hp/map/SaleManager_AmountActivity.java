package com.hp.map;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.hp.domain.Product;
import com.hp.domain.Stock;
import com.hp.rest.Rest;
import com.sun.jersey.api.client.ClientResponse;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SaleManager_AmountActivity extends TakeOrder_AmountActivity{
	public void init(){
		putData = "putOriginalSaleOrder";
		putDataDetail = "putOriginalSaleOrderDetail";
		title.setText("Bán hàng");
//		sum.setText("Tổng giá trị");
//		linearlayout_discount.setVisibility(View.GONE);
	}
	
	
}
