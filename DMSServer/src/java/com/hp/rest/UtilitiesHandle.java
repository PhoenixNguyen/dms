/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.rest;

import com.hp.common.HttpHelper;
import com.hp.dao.CalendarDAO;
import com.hp.dao.CalendarDAOImpl;
import com.hp.dao.ForLeaveDAO;
import com.hp.dao.ForLeaveDAOImpl;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.SetLunchDAO;
import com.hp.dao.SetLunchDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.TimeKeeperDAO;
import com.hp.dao.TimeKeeperDAOImpl;
import com.hp.domain.Calendar;
import com.hp.domain.ForLeave;
import com.hp.domain.RoadManagement;
import com.hp.domain.SetLunch;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
        
        List<Calendar> calendarList = calendarDAO.getCalendarList(calendar.getStaff().getId(), calendar.getProvince(), calendar.getCalendarDate());
        
        if(calendarList != null && calendarList.size() > 0){
            return Response.status(200).entity("existcalendar").build();
        }
        
        Date today = new Date();
        calendar.setCreatedTime(Timestamp.valueOf(dateFormat.format(today)));
        calendar.setUpdatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        return Response.status(200).entity(calendarDAO.saveOrUpdate(calendar) + "").build();
    }
    
    @POST
    @Path("/updateCalendar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCalendar( String pData ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        
        ObjectMapper mapper = new ObjectMapper();
        Calendar calendar = new Calendar();
        try {
                calendar = mapper.readValue(pData, Calendar.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        } catch (JsonMappingException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        } catch (IOException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        }
        
        if(calendar == null)
            return Response.status(200).entity("false").build();
        
        //Read only
        if(calendarDAO.getCalendar(calendar.getStt()).getStatus() != 0){
            return Response.status(200).entity("readonly").build();
        }
        
        calendar.setStatus(1);
        
        Date today = new Date();
        calendar.setUpdatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        //get city in this day
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
        
        System.out.println(calendar.getStaff().getId() + " " + sdf.format(today));
        
        String fromDate = sdf.format(today);
        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTime(today);
        c.add(java.util.Calendar.DAY_OF_MONTH, 1);
        
        String endDate = sdf.format(c.getTime());
        
        List<List<RoadManagement>> roads = new ArrayList<List<RoadManagement>>();
        roads = roadManagementDAO.getRoad(null, calendar.getStaff().getId(), "", fromDate, fromDate);
        
        
        String cities = "";
        if(roads != null && roads.size() > 0){
            List<RoadManagement> roads1 = roads.get(0);
            if(roads1 != null && roads1.size() > 0)
            for(RoadManagement rm : roads1){
                String city = getAddress(rm);
                if(cities.equals(""))
                    cities = city;
                else
                if(cities.indexOf(city) == -1)
                    cities += ", " + city;
            }
        }
        if(!cities.equalsIgnoreCase(""))
            calendar.setNote(cities);
        
        return Response.status(200).entity(calendarDAO.update(calendar) + "").build();
    }
    
    private String getAddress(RoadManagement last) {
        String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
		        + last.getViDo() + "," + last.getKinhDo() + "&sensor=false";
            JSONObject jsonObj;
            String City = "";
            String address = "";
         try {

          jsonObj = new JSONObject(HttpHelper.makeRequest(url));

          String Status = jsonObj.getString("status");
          if (Status.equalsIgnoreCase("OK")) {
                   JSONArray Results = jsonObj.getJSONArray("results");
                   JSONObject zero = Results.getJSONObject(0);

                   address = zero.getString("formatted_address").toString();

                   String[] long_name = address.split(",");

                   int number = long_name.length - 2;
                   City = long_name[number];
                   //System.out.println("CityName _______________________________ --->" + City + "");

                   //Toast.makeText(this, "CityName: " + City, Toast.LENGTH_SHORT).show();

                   if (!City.equals("")) {
                       return City;
                    //finish_service();
                   }
          }

         } catch (JSONException e) {
             e.printStackTrace();
             return "";
         }
         return "";
    }
    
    @POST
    @Path("/deleteCalendar")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCalendar( String pData ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        
        ObjectMapper mapper = new ObjectMapper();
        Calendar calendar = new Calendar();
        try {
                calendar = mapper.readValue(pData, Calendar.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        } catch (JsonMappingException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        } catch (IOException e) {
                e.printStackTrace();
                return Response.status(200).entity("false").build();
        }
        
        if(calendar == null)
            return Response.status(200).entity("false").build();
        
        //Read only
        if(calendarDAO.getCalendar(calendar.getStt()).getStatus() != 0){
            return Response.status(200).entity("readonly").build();
        }
        
        return Response.status(200).entity(calendarDAO.delete(calendar) + "").build();
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
        
        //Check calendar exist
        CalendarDAO calendarDAO = new CalendarDAOImpl();
//        List<Calendar> calendarList = calendarDAO.getCalendarList(timeKeeper.getProvince(), timeKeeper.getTimeAt());
//        
//        if(calendarList == null || calendarList.size() == 0){
//            return Response.status(200).entity("nocalendar").build();
//        }
        
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
    
    @POST
    @Path("/getSetLunchList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<SetLunch> getSetLunchList(String pData) {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        StaffDAO staffDAO = new StaffDAOImpl();
        staff = staffDAO.loadStaff(pData);
        
        if(staff == null)
            return null;
        
        SetLunchDAO setLunchDAO = new SetLunchDAOImpl();
        
        return setLunchDAO.getSetLunchList(staff);
        
    }
    
    @POST
    @Path("/putSetLunch")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putSetLunch( String pData ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SetLunchDAO setLunchDAO = new SetLunchDAOImpl();
        
        ObjectMapper mapper = new ObjectMapper();
        SetLunch setLunch = new SetLunch();
        try {
                setLunch = mapper.readValue(pData, SetLunch.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        if(setLunch == null)
            return Response.status(200).entity("false").build();
        
        List<SetLunch> setLunchList = setLunchDAO.getSetLunchList(setLunch.getStaff(), setLunch.getTimeAt());
        
        if(setLunchList != null && setLunchList.size() > 0){
            return Response.status(200).entity("existsetlunch").build();
        }
        
        Date today = new Date();
        setLunch.setCreatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        return Response.status(200).entity(setLunchDAO.saveOrUpdate(setLunch) + "").build();
    }
    
    @POST
    @Path("/getForLeaveList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<ForLeave> getForLeaveList(String pData) {
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        StaffDAO staffDAO = new StaffDAOImpl();
        staff = staffDAO.loadStaff(pData);
        
        if(staff == null)
            return null;
        
        ForLeaveDAO forLeaveDAO = new ForLeaveDAOImpl();
        
        return forLeaveDAO.getForLeaveList(staff);
        
    }
    
    @POST
    @Path("/putForLeave")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putForLeave( String pData ) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ForLeaveDAO forLeaveDAO = new ForLeaveDAOImpl();
        
        ObjectMapper mapper = new ObjectMapper();
        ForLeave forLeave = new ForLeave();
        try {
                forLeave = mapper.readValue(pData, ForLeave.class);
                
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        if(forLeave == null)
            return Response.status(200).entity("false").build();
        
        List<ForLeave> forLeaveList = forLeaveDAO.getForLeaveList(forLeave.getStaff(), forLeave.getTimeAt());
        
        if(forLeaveList != null && forLeaveList.size() > 0){
            return Response.status(200).entity("existforleave").build();
        }
        
        Date today = new Date();
        forLeave.setCreatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        return Response.status(200).entity(forLeaveDAO.saveOrUpdate(forLeave) + "").build();
    }
}
