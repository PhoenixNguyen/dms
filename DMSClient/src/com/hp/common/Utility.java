package com.hp.common;

public class Utility {
	public static String displayDayOfWeek(int day){
		if(day == 7 || day == 0)
			return "Chủ nhật";
		
		return "Thứ "+ (day+1);
	}
}
