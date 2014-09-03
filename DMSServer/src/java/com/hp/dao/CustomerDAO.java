/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Customer;
import com.hp.domain.Staff;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public interface CustomerDAO {
    public boolean saveOrUpdate(Customer pCustomer);
    public boolean update(Customer pCustomer);
    public boolean delete(Customer pCustomer);
    
    public List<Customer> getListCustomers(String pStaff, int pPermission);
            
    public List<Customer> loadCustomersWithLocations();
    //and hava all sales man of manager
    public List<Customer> loadCustomersWithLocations(String pManagerID, String pStaff, String pCustomer);
    //Get List customer to ajax
    public List<String> getListCustomer(String pStaff);
    
    //Set customer location
    public int update(String pID, float pX, float pY);
    
    //get list for schedule (services)
    public List<Customer> getListCustomerSchedule(String pStaff, String pDate, int pPermission);
    
    //For MAP schedule
    public List<Customer> loadCustomersWithLocationsForSchedule();
    public List<Customer> loadCustomersWithLocationsForSchedule(String pManagerID, String pStaff);
    
    //Customer detail
    public List<Customer> loadCustomersDetail(String pCustomer);
    
    //View customers list
    public List<Customer> getListCustomer();
    //get a customer
    public Customer loadCustomer(String pCustomer);
    public Customer loadCustomer(int pCustomer);
    
    public List<Staff> loadStaffsWithLocationsForSchedule();
    public List<List<Customer>> customerScheduleList(String pManagerID, String pStaff, String pDate);
    public List<List<Customer>> customerScheduleList(String pManagerID, String pStaff, String pCustomer, String pDate);
    
    //Search customers
    public List<Customer> getSearchCustomerList(String pText);
}
