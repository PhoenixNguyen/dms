package com.hp.sale_order;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.hp.map.R;

@SuppressLint("NewApi")
public class SaleValueFragment extends Fragment{
	
	private Context context ;
	private View rootView;
	
	private TextView sale;
	private TextView return_sale;
	private TextView value;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        
		this.context = container.getContext();
		rootView = inflater.inflate(R.layout.sale_order_value, container, false);
        
		sale = (TextView)rootView.findViewById(R.id.sale);
		return_sale = (TextView)rootView.findViewById(R.id.return_order);
		value = (TextView)rootView.findViewById(R.id.value);
		
		float saleTotal = 0;
		float returnTotal = 0;
		float valueTotal = 0;
		
		for(int i = 0; i < SaleOrderFragment.takeOrderDetailList.size(); i++){
			saleTotal += SaleOrderFragment.takeOrderDetailList.get(i).getPriceTotal();
		}
		for(int i = 0; i < ReturnOrderFragment.takeOrderDetailList.size(); i++){
			returnTotal += ReturnOrderFragment.takeOrderDetailList.get(i).getPriceTotal();
		}
		
		valueTotal = saleTotal - returnTotal;
		
		sale.setText("Tổng giá trị hóa đơn: " + saleTotal);
		return_sale.setText("Tổng giá trị trả về: " + returnTotal);
		
		value.setText("Tổng giá trị thu: " + valueTotal);
		
		return rootView;
	}
}
