/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.StaffHistory;
import java.util.List;

/**
 *
 * @author HP
 */
public interface StaffHistoryDAO {
    public boolean saveOrUpdate(StaffHistory pStaffHistory);
    public StaffHistory getStaffHistory(int id);
    public StaffHistory getStaffHistory(String pCustomer, String time);
    public List<StaffHistory> getStaffHistoryList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate);
    
}
