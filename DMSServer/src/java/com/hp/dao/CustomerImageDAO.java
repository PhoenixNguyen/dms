/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.CustomerImage;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CustomerImageDAO {
    public boolean saveOrUpdate(CustomerImage pCustomerImage);
    public CustomerImage getCustomerImage(int stt);
    public int updateStatus(String pCustomer);
    
    
    public List<CustomerImage> getCustomerImageList(String pCustomer, String pDate, String pToDate);
    public List<CustomerImage> getCustomerImageList();
}
