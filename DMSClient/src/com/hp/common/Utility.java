package com.hp.common;

import java.util.Calendar;
import java.util.Date;

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
}
