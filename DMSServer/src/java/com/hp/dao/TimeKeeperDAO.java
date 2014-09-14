/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Staff;
import com.hp.domain.TimeKeeper;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface TimeKeeperDAO {
    public boolean saveOrUpdate(TimeKeeper timeKeeper);
    public List<TimeKeeper> getTimeKeeperList();
    public boolean update(TimeKeeper timeKeeper);
    public boolean delete(TimeKeeper timeKeeper);
    public TimeKeeper getTimeKeeper(int id);
    
    public List<TimeKeeper> getTimeKeeperList(Staff staff);
    public List<TimeKeeper> getTimeKeeperList(Staff staff, Date date);
    
    public List<TimeKeeper> getTimeKeeperList(String pManagerID, String pStaff, Date pFromDate, Date pToDate);
}
