/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.schedule;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.hp.domain.Customer;
import com.hp.domain.Schedule;
import com.hp.map.R;
import com.hp.map.Schedule_CalendarActivity;
import com.hp.rest.Rest;

import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * A widget that describes an activity that demonstrates a feature.
 */
public final class DialogFeatureView extends FrameLayout {

	private Context context;
	private TimePicker timePicker1;
	
    /**
     * Constructs a feature view by inflating layout/feature.xml.
     */
    public DialogFeatureView(Context context) {
        super(context);
        this.context = context;
        
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.schedule_dialog_feature, this);
    }

    /**
     * Set the resource id of the title of the demo.
     *
     * @param titleId the resource id of the title of the demo
     */
    public synchronized void setCustomer(final Customer customer) {
    	CheckBox chkBox = (CheckBox) (findViewById(R.id.checkBox1));
    	TextView name = (TextView) (findViewById(R.id.name));
    	
    	chkBox.setText(customer.getMaDoiTuong());
    	name.setText(customer.getDoiTuong());
    	
    	chkBox.setOnClickListener(new OnClickListener() {
        	 
      	  @Override
      	  public void onClick(View v) {
                      //is chkIos checked?
      		if (((CheckBox) v).isChecked()) {
      			//Do something
      			Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                	int t = 0;
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    	//Ignore first times
                    	if(t == 1){
	                        System.out.println( selectedHour + ":" + selectedMinute);
	                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                        
	                        String time = Schedule_CalendarActivity.mTakeTheDate +" "+selectedHour + ":" + selectedMinute + ":00";
	                        System.out.println(time);
	                        Date datetime = null;
	                        try {
								datetime = dateFormat.parse(time);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                        
	                        Schedule schedule = new Schedule(Rest.mStaff.getId(), customer.getMaDoiTuong(), Timestamp.valueOf(dateFormat.format(datetime))
	                        		, true, customer.getDoiTuong(), Rest.mStaff.getName());
	                        
	                        Schedule_CalendarActivity.mTakeCustomersList.add(schedule);
                    	}
                    	t++;
                        //Schedule_CalendarActivity.mTakeCustomersList.put(titleId, Timestamp.valueOf(dateFormat.format(datetime)));
                        
//                        for(String key : Schedule_CalendarActivity.mTakeCustomersList.keySet()){
//        					Timestamp value = Schedule_CalendarActivity.mTakeCustomersList.get(key);
//        					System.out.println(key + " ___ " + value);
//        				}
                        
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

      		}
      		else {
      			//Destroy
      			for(int i = 0; i < Schedule_CalendarActivity.mTakeCustomersList.size(); i++){
      				if(Schedule_CalendarActivity.mTakeCustomersList.get(i).getMaKH() == customer.getMaDoiTuong()){
      					Schedule_CalendarActivity.mTakeCustomersList.remove(i);
      					break;
      				}
      			}
      			
      		}
       
      	  }
      	});
    }
    
    

    
}
