package com.hp.menu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DialogArrayAdapter extends ArrayAdapter<DetailsList>{
	private final Context context;
	private final DetailsList[] values;
	private final int resource;
	
	public DialogArrayAdapter(Context pContext, int resource, DetailsList[] pValues){
		super(pContext, resource, pValues);		//Reset list
		
		this.context = pContext;
		this.values = pValues;
		this.resource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		
		
		DialogFeatureView featureView;
        if (convertView instanceof DialogFeatureView) {
            featureView = (DialogFeatureView) convertView;
        } else {
            featureView = new DialogFeatureView(getContext());
        }
        
        
        featureView.setTitle(values[position].title);
        featureView.setIcon(values[position].src);
        
        if(values[position].src == 0){
        	featureView.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }
               

        return featureView;
	}
	
}
