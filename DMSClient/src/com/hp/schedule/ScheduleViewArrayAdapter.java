package com.hp.schedule;

//import com.hp.map.DetailsList;
//import com.hp.map.FeatureView;
import java.util.ArrayList;
import java.util.List;

import com.hp.domain.Schedule;
import com.hp.map.R;
import com.hp.map.R.layout;
import com.hp.schedule.ListViewSchedules;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ScheduleViewArrayAdapter extends ArrayAdapter<Schedule>{
	private final Context context;
	private final List<Schedule> values;
	private final int resource;
	
	
	public ScheduleViewArrayAdapter(Context pContext, int resource, List<Schedule> pValues){
		super(pContext, resource, pValues);
		this.context = pContext;
		this.values = new ArrayList<Schedule>();
		this.values.addAll(pValues);
		
		this.resource = resource;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		FeatureView featureView;
        if (convertView instanceof FeatureView) {
            featureView = (FeatureView) convertView;
        } else {
            featureView = new FeatureView(getContext());
        }
        

        featureView.setCustomerID(values.get(position).getMaKH());
        featureView.setTime(values.get(position).getTime() + "");
        
        featureView.setCustomerName(values.get(position).getTenKhachHang());
        featureView.setStaffID("Nhân viên: " + values.get(position).getMaNV());

        return featureView;
	}
	
}
