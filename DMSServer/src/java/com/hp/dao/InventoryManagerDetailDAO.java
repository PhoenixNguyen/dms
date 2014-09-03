/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.InventoryManagerDetail;
import java.util.List;

/**
 *
 * @author HP
 */
public interface InventoryManagerDetailDAO {
    public boolean saveOrUpdate(InventoryManagerDetail pTakeOrder);
    
    //GET
    public List<InventoryManagerDetail> getInventoryManagerDetailList(String pID);
    public InventoryManagerDetail getInventoryManagerDetail(int pInventoryManagerDetail);
    public boolean update(InventoryManagerDetail pInventoryManagerDetail);
    
    //delete
    public boolean delete(InventoryManagerDetail pInventoryManagerDetail);
    //delete where order id
    public boolean delete(String pID);
}
