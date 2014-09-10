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
import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        
        Date today = new Date();
        calendar.setCreatedTime(Timestamp.valueOf(dateFormat.format(today)));
        calendar.setUpdatedTime(Timestamp.valueOf(dateFormat.format(today)));
        
        CalendarDAO calendarDAO = new CalendarDAOImpl();
        return Response.status(200).entity(calendarDAO.saveOrUpdate(calendar) + "").build();
    }
}
