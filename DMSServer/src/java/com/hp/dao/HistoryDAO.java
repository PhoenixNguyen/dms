/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.History;
import java.util.List;

/**
 *
 * @author HP
 */
public interface HistoryDAO {
    public boolean saveOrUpdate(History pHistory);
    public boolean updateHistory(History pHistory);
    public List<History> getHistoryList(String pUser);
    
    public History getHistory(String time);
    public History getHistory(int id);
}
