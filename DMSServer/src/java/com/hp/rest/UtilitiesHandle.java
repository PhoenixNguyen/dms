/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.rest;

import com.hp.dao.CalendarDAO;
import com.hp.dao.CalendarDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.TimeKeeperDAO;
import com.hp.dao.TimeKeeperDAOImpl;
import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import com.hp.domain.TimeKeeper;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.HOURS;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
/**
 *
 * @author HP
 */

@Path("utility")
public class UtilitiesHandle {
    @Context
    private UriInfo context;
    
    public UtilitiesHandle(){
        
    }
    
    @POST
    @Path("/getCalendarList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Calendar> getCalendarList(String pData) {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        StaffDAO staffDAO = new StaffDAOImpl();
        staff = staffDAO.loadStaff(pData);
        
        if(staff == null)
            return null;
        
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        
        return calendarDAO.getCalendarList(staff);
        
    }
    
    @POST
    @Path("/putCalendar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putCalendar( String pData ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        
        ObjectMapper mapper = new ObjectMapper();
        Calendar calendar = new Calendar();
        try {
                calendar = mapper.readValue(pData, Calendar.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        if(calendar == null)
            return Response.status(200).entity("false").build();
        
        List<Calendar> calendarList = calendarDAO.getCalendarList(calendar.getProvince(), calendar.getCalendarDate());
        
        if(calendarList != null && calendarList.size() > 0){
            return Response.status(200).entity("existcalendar").build();
        }
        
        Date today = new Date();
        calendar.setCreatedTime(Timestamp.valueOf(dateFormat.format(today)));
        calendar.setUpdatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        return Response.status(200).entity(calendarDAO.saveOrUpdate(calendar) + "").build();
    }
    
    @POST
    @Path("/getTimeKeeperList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TimeKeeper> getTimeKeeperList(String pData) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        String[] total = null;
        String staffId = "";
        String date = "";
        try{
            total = pData.split("::");
            staffId = total[0];
            date = total[1];
        
        }catch(Exception e){
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        StaffDAO staffDAO = new StaffDAOImpl();
        staff = staffDAO.loadStaff(staffId);
        
        if(staff == null)
            return null;
        
        TimeKeeperDAO timeKeeperDAO = new TimeKeeperDAOImpl();
        
        return timeKeeperDAO.getTimeKeeperList(staff, df.parse(date));
        
    }
    
    @POST
    @Path("/putTimeKeeper")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putTimeKeeper( String pData ) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        ObjectMapper mapper = new ObjectMapper();
        TimeKeeper timeKeeper = new TimeKeeper();
        try {
                timeKeeper = mapper.readValue(pData, TimeKeeper.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        if(timeKeeper == null)
            return Response.status(200).entity("false").build();
        
        //Check calendar
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        List<Calendar> calendarList = calendarDAO.getCalendarList(timeKeeper.getProvince(), timeKeeper.getTimeAt());
        
        if(calendarList == null || calendarList.size() == 0){
            return Response.status(200).entity("nocalendar").build();
        }
        
        TimeKeeperDAO timeKeeperDAO = new TimeKeeperDAOImpl();
        List<TimeKeeper> todayList = timeKeeperDAO.getTimeKeeperList(timeKeeper.getStaff(), df.parse(df.format(timeKeeper.getTimeAt())));
        
        if(todayList != null && todayList.size() > 0){
            TimeKeeper start = todayList.get(0);
            
            int diffInDays = (int)( (timeKeeper.getTimeAt().getTime() - start.getTimeAt().getTime()) / (1000 * 60 * 60 * 24) );
            
            long duration  = timeKeeper.getTimeAt().getTime() - start.getTimeAt().getTime();

            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
            long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);

            System.out.println("diffInSeconds: " + diffInSeconds + " diffInMinutes: " + diffInMinutes + " diffInHours: " + diffInHours);     
            
            DecimalFormat dc = new DecimalFormat("####0.0");
            System.out.println(Float.parseFloat(dc.format(diffInMinutes/60f)));
            timeKeeper.setTimeBetween( Float.parseFloat(dc.format(diffInMinutes/60f)));
        }
        return Response.status(200).entity(timeKeeperDAO.saveOrUpdate(timeKeeper) + "").build();
    }
}
