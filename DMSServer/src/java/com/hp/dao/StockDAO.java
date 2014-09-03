/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Stock;

/**
 *
 * @author HP
 */
public interface StockDAO {
    public boolean saveOrUpdate(Stock pStock);
    public Stock getStock(int pStock);
    public Stock getStock(String pID);
    public boolean update(Stock pStock);
    public boolean delete(Stock pStock);
}
