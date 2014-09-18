/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CalendarDAO {
    public boolean saveOrUpdate(Calendar calendar);
    public List<Calendar> getCalendarList();
    public boolean update(Calendar calendar);
    public boolean delete(Calendar calendar);
    public Calendar getCalendar(int id);
    
    public List<Calendar> getCalendarList(Staff staff);
    
    public List<Calendar> getCalendarList(String city, Date date);
    
    public List<Calendar> getCalendarList(String pManagerID, String pStaff, Date pFromDate, Date pToDate);
}
