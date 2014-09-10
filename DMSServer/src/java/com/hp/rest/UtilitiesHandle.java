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
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
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
}
