/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserDAO {
    //get list user: 
    //pPermission = 1: Tong giam doc
    //pPermission = 2: Giam doc
    public List<String> getListUser(int pPermission);
    public List<String> getListUser();
    //Authorize
    public boolean authorize(String username, String password);
    
    //Get user
    public User getUser(String username);
    public User getUser(int stt);
    
    //Get List user
    public List<User> getUserList();
    
    //update
    public boolean updateUser(User user);
    
    //delete
    public boolean deleteUser(User user);
    
    //new
    public boolean saveUser(User user);
    
}
