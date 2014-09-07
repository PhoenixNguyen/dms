/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Calendar;
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
    
}
