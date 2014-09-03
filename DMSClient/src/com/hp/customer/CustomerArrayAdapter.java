package com.hp.customer;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.hp.customer.FeatureView;
import com.hp.domain.Customer;
import com.hp.domain.Product;
import com.hp.map.R;

public class CustomerArrayAdapter extends ArrayAdapter<Customer>{
	
	private ArrayList<Customer> originalList;
	private CustomerFilter filter;
	
	private  List<Customer> values;
	/**
     * @param demos An array containing the details of the demos to be displayed.
     */
    public CustomerArrayAdapter(Context context, List<Customer> pValues) {
        super(context, R.layout.feature, R.id.title, pValues);
        
        this.originalList = new ArrayList<Customer>();
        this.originalList.addAll(pValues);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeatureView featureView;
        if (convertView instanceof FeatureView) {
            featureView = (FeatureView) convertView;
        } else {
            featureView = new FeatureView(getContext());
        }

        Customer customer = getItem(position);

        featureView.setCustomerID(customer.getMaDoiTuong());
        featureView.setCustomerName(customer.getDoiTuong());
        featureView.setStaff("Nhân viên quản lý: "+customer.getMaNhanVien());

        return featureView;
    }
    
    @Override
	   public Filter getFilter() {
	    if (filter == null){
	     filter  = new CustomerFilter();
	    }
	    return filter;
	   }
	   private class CustomerFilter extends Filter
	   {

	    @Override
	    protected FilterResults performFiltering(CharSequence constraint) {

	     constraint = constraint.toString().toLowerCase();
	     FilterResults result = new FilterResults();
	     if(constraint != null && constraint.toString().length() > 0)
	     {
	     ArrayList<Customer> filteredItems = new ArrayList<Customer>();

	     for(int i = 0, l = originalList.size(); i < l; i++)
	     {
	    	 Customer nameList = originalList.get(i);
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

	     values = (ArrayList<Customer>)results.values;
	     notifyDataSetChanged();
	     clear();
	     for(int i = 0, l = values.size(); i < l; i++)
	      add(values.get(i));
	     notifyDataSetInvalidated();
	    }
	   }
}


