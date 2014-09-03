/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.TakeOrder;
import java.util.List;

/**
 *
 * @author HP
 */
public interface TakeOrderDAO {
    public boolean saveOrUpdate(TakeOrder pTakeOrder);
        
    //Get
    public List<TakeOrder> getTakeOrdersList();
    public TakeOrder getTakeOrder(int pTakeOrder);
    public boolean update(TakeOrder pTakeOrder);
    
    public List<TakeOrder> getTakeOrdersList(String pStaff, int pPermission);
    public List<TakeOrder> getTakeOrdersList(String pStaff, String pFrom, String pTo);
    
    public TakeOrder getTakeOrder(String pID);
    
    //delete
    public boolean delete(TakeOrder pTakeOrder);
    
    //Search TakeOrder
    public List<TakeOrder> getSearchTakeOrderList(String pText);
    
    //Get TakeOrders to report
    public List<TakeOrder> getTakeOrderList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate);
}
