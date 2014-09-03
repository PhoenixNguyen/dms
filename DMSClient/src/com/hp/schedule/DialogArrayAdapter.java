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

import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TimePicker;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class DialogArrayAdapter extends ArrayAdapter<Customer> implements OnCheckedChangeListener{
	private final Context context;
	private final List<Customer> values;
	private final int resource;
	
	private boolean[][] checkBoxState;
	
	public DialogArrayAdapter(Context pContext, int resource, List<Customer> pValues){
		super(pContext, resource, pValues);

		this.context = pContext;
		this.values = new ArrayList<Customer>();
		this.values.addAll(pValues);
		
		this.resource = resource;
		checkBoxState = new boolean[2][pValues.size()];
	}
	
	public class ViewHolder {
		TextView name;
		CheckBox checkbox;
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent)
    {
		final Customer customer = values.get(position);
		ViewHolder holder=new ViewHolder();
		
		LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//
		convertView = vi.inflate(R.layout.schedule_dialog_feature, null);
		
        holder.name=(TextView)convertView.findViewById(R.id.name);
        holder.checkbox=(CheckBox)convertView.findViewById(R.id.checkBox1);            

        holder.checkbox.setClickable(true);           
        holder.checkbox.setFocusable(false);  
        holder.checkbox.setTag(position);

        holder.checkbox.setOnCheckedChangeListener(this);

        holder.checkbox.setText(customer.getMaDoiTuong()+ "");
        holder.name.setText(customer.getDoiTuong()+ "");          

        if(checkBoxState[0][position]==true)
        {
            holder.checkbox.setChecked(true); 
            
        }
        else
        {
            holder.checkbox.setChecked(false);
                      
        }           

        return convertView;
    }   
	
	public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
    {                       
        //EventItems cart=eventList.get((Integer)buttonView.getTag());    
		int position = (Integer)buttonView.getTag();
		
		final Customer customer = values.get(position);
		
        if(buttonView.isChecked())
        {
        	checkBoxState[0][position] = true;   
        	
        	if(!checkBoxState[1][position]){
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
	                    
	//                    for(String key : Schedule_CalendarActivity.mTakeCustomersList.keySet()){
	//    					Timestamp value = Schedule_CalendarActivity.mTakeCustomersList.get(key);
	//    					System.out.println(key + " ___ " + value);
	//    				}
	                    
	                }
	            }, hour, minute, true);//Yes 24 hour time
	            mTimePicker.setTitle("Select Time");
	            mTimePicker.show();
	            
	          //Scheduled
	          checkBoxState[1][position] = true;
            }
        }
        else 
        {               
        	checkBoxState[0][position] = false;
        	
        	//Destroy
  			for(int i = 0; i < Schedule_CalendarActivity.mTakeCustomersList.size(); i++){
  				if(Schedule_CalendarActivity.mTakeCustomersList.get(i).getMaKH() == customer.getMaDoiTuong()){
  					Schedule_CalendarActivity.mTakeCustomersList.remove(i);
  					break;
  				}
  			}
  			
  			//Scheduled reset
  			checkBoxState[1][position] = false;
        }   
    }     
}
