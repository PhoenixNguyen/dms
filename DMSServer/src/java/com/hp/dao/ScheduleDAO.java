/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Schedule;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ScheduleDAO {
    public boolean saveOrUpdate(Schedule pSchedule);
    
    //if permission = 1 as admin get all data in the date
    //if permission = 2 its normal
    public List<Schedule> getScheduleList(String pMaNV, String pDate, int permission);
    
    public int deletechedule(String pID, String pDate);
    
    //Get
    public List<Schedule> getSchedulesList();
    public List<Schedule> getSchedulesList(String pManagerID, String pStaff, String pDate);
    public List<Schedule> getSchedulesListForSchedules(String pManagerID, String pStaff, String pDate, String pToDate);
    
    public List<Schedule> getSchedulesList(String pManagerID, String pStaff, String pCustomer, String pDate);
    public List<Schedule> getSchedulesListForSchedules(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate);
}
