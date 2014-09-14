package com.hp.calendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hp.common.Utility;
import com.hp.domain.Calendar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

public class CalendarArrayAdapter extends ArrayAdapter<Calendar>{
	private final Context context;
	private List<Calendar> values;
	private final int resource;
	
	private ArrayList<Calendar> originalList;
	private NameFilter filter;
	   
	public CalendarArrayAdapter(Context pContext, int resource, List<Calendar> pValues){
		super(pContext, resource, pValues);
		this.context = pContext;
		this.values = pValues;
		this.resource = resource;
		
		this.originalList = new ArrayList<Calendar>();
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
        
        featureView.setTitleId(Utility.displayDayOfWeek(values.get(position).getCalendarDate().getDay()) + ", "+ df.format(values.get(position).getCalendarDate()));
        featureView.setProvince("Địa điểm: " + values.get(position).getProvince());
        featureView.setDescription("Công việc: " + values.get(position).getContent());
        featureView.setStatus(values.get(position).getStatus());
        
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
	     ArrayList<Calendar> filteredItems = new ArrayList<Calendar>();

	     for(int i = 0, l = originalList.size(); i < l; i++)
	     {
	    	 Calendar nameList = originalList.get(i);
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

	     values = (ArrayList<Calendar>)results.values;
	     notifyDataSetChanged();
	     clear();
	     for(int i = 0, l = values.size(); i < l; i++)
	      add(values.get(i));
	     notifyDataSetInvalidated();
	    }
	   }
	
}
