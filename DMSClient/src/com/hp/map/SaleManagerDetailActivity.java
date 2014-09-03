package com.hp.map;

import java.io.IOException;
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

public class SaleManagerDetailActivity extends TakeOrdersDetailManagerActivity{
	

	public void init(){
		getListDetail = "getSaleOrderDetailList";
		
		updateData = "updateDetailSaleOrder";
		deleteData = "deleteDetailSaleOrder";
		
		//
		TakeOrder_ProductActivity.timeLine = true;
		
		//
		TakeOrdersDetailManagerActivity.add_detail = 2;
	}
	
//	public void newOrderDetail(View view){
//		//Status = true
//		TakeOrder_ProductActivity.add_take_order_detail = true;
//		TakeOrder_ProductActivity.timeLine = true;
//		
//		startActivity(new Intent(this, SaleManager_TabActivity.class));
//		
//	}
	
	public void newOrderDetail(){
		//Status = true
		TakeOrder_ProductActivity.add_take_order_detail = true;
		TakeOrder_ProductActivity.timeLine = true;
		
		startActivity(new Intent(this, SaleManager_TabActivity.class));
		
	}
}
