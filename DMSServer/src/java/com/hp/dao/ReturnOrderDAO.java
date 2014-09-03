/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.ReturnOrder;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ReturnOrderDAO {
    public boolean saveOrUpdate(ReturnOrder pReturnOrder);
        
    //Get
    public List<ReturnOrder> getReturnOrdersList();
    public ReturnOrder getReturnOrder(int pReturnOrder);
    public boolean update(ReturnOrder pReturnOrder);
    public List<ReturnOrder> getReturnOrdersList(String pStaff);
    public ReturnOrder getReturnOrder(String pID);
    
    //delete
    public boolean delete(ReturnOrder pReturnOrder);
}
