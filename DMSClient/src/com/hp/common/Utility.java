package com.hp.common;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.content.Context;
import android.content.SharedPreferences;

import com.hp.domain.Staff;
import com.hp.rest.Rest;

public class Utility {
	public static String displayDayOfWeek(int day){
		if(day == 7 || day == 0)
			return "Chủ nhật";
		
		return "Thứ "+ (day+1);
	}
	
	public static Date convertToLocalTime(Date date){
		if(date == null)
			return null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, -7);
		
		return c.getTime();
	}
	
	public static Staff convertJsonToStaff(String json){
		Staff staff = null;
    	ObjectMapper mapper = new ObjectMapper();
        
        try {
        	staff = mapper.readValue(json, Staff.class);
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
        
        return staff;
    }
	
	public static Staff keepLogined(Context context){
		System.out.println("keepLogined!");
		if(Rest.mStaff == null){
			SharedPreferences sp = context.getApplicationContext().getSharedPreferences(SharedConstant.LOGIN_STORE, Context.MODE_PRIVATE);
	        String staffJSON = sp.getString(SharedConstant.LOGIN_STAFF, null);
	        System.out.println("staffJSON: "+staffJSON);
	        if(staffJSON != null){
	        	Rest.mStaff = convertJsonToStaff(staffJSON);
	        	return Rest.mStaff;
	        }else
	        	return null;
		}else
			return Rest.mStaff;
	}
}
