/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.CalendarDAO;
import com.hp.dao.CalendarDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class CalendarAction extends ActionSupport{
    private CalendarDAO calendarDAO = new CalendarDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    
    private List<Calendar> calendarList = new ArrayList<Calendar>();

    public String displayCalendars(){
        Staff staff = staffDAO.loadStaff("cau_giay");
        Calendar calendar = new Calendar();//calendarDAO.getCalendar(1);
        calendar.setCalendarDate(new Date());
        calendar.setContent("abc");
        calendar.setProvince("Hà Nội");
        
        calendar.setStaff(staff);
        calendarDAO.saveOrUpdate(calendar);
        
        calendarList = calendarDAO.getCalendarList();
        
        return SUCCESS;
    }
    
    public List<Calendar> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<Calendar> calendarList) {
        this.calendarList = calendarList;
    }
    
    
}
