/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.SetLunch;
import com.hp.domain.Staff;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SetLunchDAO {
    public boolean saveOrUpdate(SetLunch setLunch);
    public List<SetLunch> getSetLunchList();
    public boolean update(SetLunch setLunch);
    public boolean delete(SetLunch setLunch);
    public SetLunch getSetLunch(int id);
    
    public List<SetLunch> getSetLunchList(Staff staff);
    public List<SetLunch> getSetLunchList(Staff staff, Date date);
    
    public List<SetLunch> getSetLunchList(String pManagerID, String pStaff, Date pFromDate, Date pToDate);
}
