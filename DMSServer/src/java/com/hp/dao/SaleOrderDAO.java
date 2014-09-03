/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.SaleOrder;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SaleOrderDAO {
    public boolean saveOrUpdate(SaleOrder pSaleOrder);
        
    //Get
    public List<SaleOrder> getSaleOrdersList();
    public SaleOrder getSaleOrder(int pSaleOrder);
    public boolean update(SaleOrder pSaleOrder);
    public List<SaleOrder> getSaleOrdersList(String pStaff);
    public SaleOrder getSaleOrder(String pID);
    
    //delete
    public boolean delete(SaleOrder pSaleOrder);
    
    //Search SaleOrder
    public List<SaleOrder> getSearchSaleList(String pText);
    
    //Get SaleOrders to report
    public List<SaleOrder> getSaleOrderList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate);
}
