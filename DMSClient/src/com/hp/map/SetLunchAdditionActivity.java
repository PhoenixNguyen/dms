package com.hp.map;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.hp.domain.SetLunch;
import com.hp.gps.MapLocation;
import com.hp.map.CalendarAdditionActivity.DatePickerFragment;
import com.hp.map.CalendarAdditionActivity.TimePickerFragment;
import com.hp.rest.Rest;
import com.hp.rest.CalendarAPI.ModifyCalendarTask;
import com.hp.rest.SetLunchAPI.ModifySetLunchTask;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class SetLunchAdditionActivity extends MainMenuActivity implements OnItemSelectedListener{
	public Context context = this;
	private Spinner cities_spinner;
	
	public Spinner date_spinner;
	//public Spinner time_spinner;
	
	public EditText calendar_content;
	
	@SuppressLint("SimpleDateFormat")
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	
	protected void onCreate(Bundle bundle){
		super.onCreate(bundle);
		setContentView(R.layout.calendar_addition);
		
		calendar_content = (EditText) findViewById(R.id.calendar_content);
		calendar_content.setHint("Nhập nội dung");
		
		cities_spinner = (Spinner) findViewById(R.id.cities_spinner);
		cities_spinner.setVisibility(View.GONE);
		
		date_spinner = (Spinner) findViewById(R.id.pick_date);
		//time_spinner = (Spinner) findViewById(R.id.pick_time);
		
		//cities_spinner.setOnItemSelectedListener(this);
		date_spinner.setOnItemSelectedListener(this);
		//time_spinner.setOnItemSelectedListener(this);
		
		//spinnerAdapter(cities_spinner);
		dateSpinnerAdapter(date_spinner, new Date());
		//timeSpinnerAdapter(time_spinner, new Date());
		
		date_spinner.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                	showDatePickerDialog(null);
                }
                return false;
            }
        });
//		time_spinner.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                	showTimePickerDialog(null);
//                }
//                return false;
//            }
//        });
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

	    int itemId = item.getItemId();
	    switch (itemId) {
	    case android.R.id.home:
	    	menuDialog();

	        // Toast.makeText(this, "home pressed", Toast.LENGTH_LONG).show();
	        break;
	        
	    case R.id.action_save:
	    	saveSetLunch();
            return true;
               
        default:
            return super.onOptionsItemSelected(item);

	    }

	    return true;
	}
	
	private void saveSetLunch() {
		
		String content = calendar_content.getText().toString();
		//String province = cities_spinner.getSelectedItem().toString();
		String date = date_spinner.getSelectedItem().toString(); 
		//String time = time_spinner.getSelectedItem().toString();
		
		//System.out.println(content + " || " +province+ " || " +date+ " || " );
		if(content.equals("")){
			Toast.makeText(this, "Nội dung không được trống", Toast.LENGTH_SHORT).show();
			return ;
		}
			
		
		SetLunch setLunch= null;
		try {
			setLunch = new SetLunch(Rest.mStaff, df.parse(date), content, "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModifySetLunchTask addCalendar = new ModifySetLunchTask(context, ModifySetLunchTask.ACTION_ADD, "putSetLunch", setLunch, null, null, null, this);
		addCalendar.execute();
		
	}

	private void spinnerAdapter(Spinner spinner) {
//		// Create an ArrayAdapter using the string array and a default spinner layout
//		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//		        R.array.cities_array, android.R.layout.simple_spinner_item);
//		// Specify the layout to use when the list of choices appears
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		ArrayAdapter<String> allCities = new ArrayAdapter<String>(this,
			     android.R.layout.simple_spinner_dropdown_item, MapLocation.getAllCityOfVietNam());
		
		// Apply the adapter to the spinner
		spinner.setAdapter(allCities);
		
	}
	
	public void dateSpinnerAdapter(Spinner spinner, Date time) {
		String []dates = {df.format(time)};
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,   android.R.layout.simple_spinner_item, dates);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		
	}
//	public void timeSpinnerAdapter(Spinner spinner, Date time) {
//		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//		String []times = {df.format(time)};
//		
//		// Create an ArrayAdapter using the string array and a default spinner layout
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,   android.R.layout.simple_spinner_item, times);
//		// Specify the layout to use when the list of choices appears
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//		// Apply the adapter to the spinner
//		spinner.setAdapter(adapter);
//		
//	}
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    
	public boolean onCreateOptionsMenu(Menu menu){
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.customer_manager_menu, menu);
		
		return true;
	}
	
	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getFragmentManager(), "datePicker");
	}
	
	public void showTimePickerDialog(View v) {
	    DialogFragment newFragment = new TimePickerFragment();
	    newFragment.show(getFragmentManager(), "timePicker");
	}
	
	@SuppressLint("ValidFragment")
	public class DatePickerFragment extends DialogFragment
		    implements DatePickerDialog.OnDateSetListener {
		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		
		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		
		@SuppressWarnings("deprecation")
		public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
			dateSpinnerAdapter(date_spinner, new Date(year -1900, month, day));
		}
	}
	
	@SuppressLint("ValidFragment")
	public class TimePickerFragment extends DialogFragment
	    implements TimePickerDialog.OnTimeSetListener {
	
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker
		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		
		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
		}
		
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user
			//timeSpinnerAdapter(time_spinner, new Date(0, 0, 0, hourOfDay, minute, 0));
		}
	}
}
