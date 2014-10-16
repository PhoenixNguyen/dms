/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.ForLeave;
import com.hp.domain.Staff;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ForLeaveDAO {
    public boolean saveOrUpdate(ForLeave forLeave);
    public List<ForLeave> getForLeaveList();
    public boolean update(ForLeave forLeave);
    public boolean delete(ForLeave forLeave);
    public ForLeave getForLeave(int id);
    
    public List<ForLeave> getForLeaveList(Staff staff);
    public List<ForLeave> getForLeaveList(Staff staff, Date date);
    
    public List<ForLeave> getForLeaveList(String pManagerID, String pStaff, Date pFromDate, Date pToDate);
}
