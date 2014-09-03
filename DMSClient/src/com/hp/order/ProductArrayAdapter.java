package com.hp.order;

import java.util.ArrayList;
import java.util.List;

import com.hp.domain.Product;
import com.hp.domain.TakeOrder;
import com.hp.map.R;
import com.hp.map.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

public class ProductArrayAdapter extends ArrayAdapter<Product>{
	private  Context context;
	private  List<Product> values;
	private  int resource;
	
	private ArrayList<Product> originalList;
	private NameFilter filter;
	
	private boolean mManager;
	
	public ProductArrayAdapter(Context pContext, int resource, List<Product> pValues, boolean pManager){
		super(pContext, resource, pValues);
		this.context = pContext;
		this.values = pValues;
		this.resource = resource;
		this.mManager = pManager;
		
		this.originalList = new ArrayList<Product>();
	    this.originalList.addAll(pValues);
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		FeatureView featureView;
        if (convertView instanceof FeatureView) {
            featureView = (FeatureView) convertView;
        } else {
            featureView = new FeatureView(getContext());
        }

        

        featureView.setTitleId(values.get(position).getProductID());
        featureView.setDescriptionId(values.get(position).getProductName());
        featureView.setPrice("Giá: "+values.get(position).getExportPrices() + "");
        
        if(!mManager){
        	featureView.setTotal(values.get(position).getTotal() + "");
        }
        

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
	     ArrayList<Product> filteredItems = new ArrayList<Product>();

	     for(int i = 0, l = originalList.size(); i < l; i++)
	     {
	    	 Product nameList = originalList.get(i);
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

	     values = (ArrayList<Product>)results.values;
	     notifyDataSetChanged();
	     clear();
	     for(int i = 0, l = values.size(); i < l; i++)
	      add(values.get(i));
	     notifyDataSetInvalidated();
	    }
	   }
}
