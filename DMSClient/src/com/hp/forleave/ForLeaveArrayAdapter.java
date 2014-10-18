package com.hp.forleave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hp.common.Utility;
import com.hp.domain.ForLeave;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

public class ForLeaveArrayAdapter extends ArrayAdapter<ForLeave>{
	private final Context context;
	private List<ForLeave> values;
	private final int resource;
	
	private ArrayList<ForLeave> originalList;
	private NameFilter filter;
	   
	public ForLeaveArrayAdapter(Context pContext, int resource, List<ForLeave> pValues){
		super(pContext, resource, pValues);
		this.context = pContext;
		this.values = pValues;
		this.resource = resource;
		
		this.originalList = new ArrayList<ForLeave>();
	    this.originalList.addAll(pValues);
	}
	
	@SuppressLint("SimpleDateFormat")
	@SuppressWarnings("deprecation")
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
        
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        featureView.setTitleId(Utility.displayDayOfWeek(values.get(position).getTimeAt().getDay()) + ", "+ df.format(values.get(position).getTimeAt()));
        featureView.setProvince("Địa điểm: ");
        featureView.setDescription("Nội dung: " + values.get(position).getContent());
        featureView.setStatus(0);
        
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
	     ArrayList<ForLeave> filteredItems = new ArrayList<ForLeave>();

	     for(int i = 0, l = originalList.size(); i < l; i++)
	     {
	    	 ForLeave nameList = originalList.get(i);
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

	     values = (ArrayList<ForLeave>)results.values;
	     notifyDataSetChanged();
	     clear();
	     for(int i = 0, l = values.size(); i < l; i++)
	      add(values.get(i));
	     notifyDataSetInvalidated();
	    }
	   }
	
}
