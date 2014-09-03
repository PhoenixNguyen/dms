/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.InventoryManager;
import java.util.List;

/**
 *
 * @author HP
 */
public interface InventoryManagerDAO {
    public boolean saveOrUpdate(InventoryManager pInventoryManager);
        
    //Get
    public List<InventoryManager> getInventoryManagersList();
    public InventoryManager getInventoryManager(int pInventoryManager);
    public boolean update(InventoryManager pInventoryManager);
    
    public List<InventoryManager> getInventoryManagersList(String pStaff, int pPermission);
    public List<InventoryManager> getInventoryManagersList(String pStaff, String pFrom, String pTo);
    
    public InventoryManager getInventoryManager(String pID);
    
    //delete
    public boolean delete(InventoryManager pInventoryManager);
    
    //Search InventoryManager
    public List<InventoryManager> getSearchInventoryList(String pText);
}
