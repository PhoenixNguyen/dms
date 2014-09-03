/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.demo;

/**
 *
 * @author HP
 */
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class GetCurrentDateTime {

    public static void main(String[] args) throws ParseException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //get current date time with Date()
        Date date = new Date();
//        System.out.println(dateFormat.format(date));

        //get current date time with Calendar()
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));

//        cal.add(Calendar.WEEK_OF_MONTH, 1);
//        cal.set(Calendar.HOUR_OF_DAY, 13);
//        cal.set(Calendar.MINUTE, 00);
//        cal.set(Calendar.SECOND, 00);

        System.out.println(dateFormat.format(cal.getTime()));
        boolean b= date.after(dateFormat.parse("2012/11/12 15:55:29"));
        System.out.println(b);
        System.out.println(dateFormat.format(cal.getTime()));
        
        System.out.println(Math.round(Math.random()*10));
         DecimalFormat df= new DecimalFormat("0.000");
        String size= df.format(56788.98/(1024*1024)) +" Mb";
        System.out.println(1123456788/(1024*1024) );
        System.out.println(size);

    }
}
