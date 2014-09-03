package com.hp.order_manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.hp.domain.TakeOrder;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

public class OrdersManagerArrayAdapter extends ArrayAdapter<TakeOrder>{
	private final Context context;
	private List<TakeOrder> values;
	private final int resource;
	
	private ArrayList<TakeOrder> originalList;
	private NameFilter filter;
	   
	public OrdersManagerArrayAdapter(Context pContext, int resource, List<TakeOrder> pValues){
		super(pContext, resource, pValues);
		this.context = pContext;
		this.values = pValues;
		this.resource = resource;
		
		this.originalList = new ArrayList<TakeOrder>();
	    this.originalList.addAll(pValues);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		FeatureView featureView;
        if (convertView instanceof FeatureView) {
            featureView = (FeatureView) convertView;
        } else {
            featureView = new FeatureView(getContext());
        }

        if(position%2 == 0){
	        //getResources().getColor(R.color.mycolor)
	        featureView.setBackgroundColor(Color.parseColor("#FFFF99"));
        }
        else
        	featureView.setBackgroundColor(Color.parseColor("#CCFF99"));

        featureView.setTitleId(values.get(position).getId());
        featureView.setDescriptionId(values.get(position).getCustomerName());
        featureView.setValue((new BigDecimal(values.get(position).getAfterPrivate())).toString());
        featureView.setStaff("Người tạo: " + values.get(position).getCreater());
        
        return featureView;
	}
	
	
	@Override
	   public Filter getFilter() {
	    if (filter == null){
	     filter  = new NameFilter();
	    }
	    return filter;
	   }
	   private class NameFilter extends Filter
	   {

	    @Override
	    protected FilterResults performFiltering(CharSequence constraint) {

	     constraint = constraint.toString().toLowerCase();
	     FilterResults result = new FilterResults();
	     if(constraint != null && constraint.toString().length() > 0)
	     {
	     ArrayList<TakeOrder> filteredItems = new ArrayList<TakeOrder>();

	     for(int i = 0, l = originalList.size(); i < l; i++)
	     {
	    	 TakeOrder nameList = originalList.get(i);
	      if(nameList.toString().toLowerCase().contains(constraint))
	       filteredItems.add(nameList);
	     }
	     result.count = filteredItems.size();
	     result.values = filteredItems;
	     }
	     else
	     {
	      synchronized(this)
	      {
	       result.values = originalList;
	       result.count = originalList.size();
	      }
	     }
	     return result;
	    }

	    @SuppressWarnings("unchecked")
	    @Override
	    protected void publishResults(CharSequence constraint,
	      FilterResults results) {

	     values = (ArrayList<TakeOrder>)results.values;
	     notifyDataSetChanged();
	     clear();
	     for(int i = 0, l = values.size(); i < l; i++)
	      add(values.get(i));
	     notifyDataSetInvalidated();
	    }
	   }
	
}
