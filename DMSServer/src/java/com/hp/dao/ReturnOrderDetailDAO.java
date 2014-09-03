/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.ReturnOrderDetail;
import java.util.List;

/**
 *
 * @author HP
 */
public interface ReturnOrderDetailDAO {
    public boolean saveOrUpdate(ReturnOrderDetail pReturnOrder);
    
    //GET
    public List<ReturnOrderDetail> getDetailReturnOrdersList(String pID);
    public ReturnOrderDetail getReturnOrderDetail(int pReturnOrderDetail);
    public ReturnOrderDetail getReturnOrderDetail(String pOrderID, int pLine);
    
    public boolean update(ReturnOrderDetail pReturnOrderDetail);
    
    //delete
    public boolean delete(ReturnOrderDetail pReturnOrderDetail);
    //delete where order id
    public boolean delete(String pID);
}
