/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Customer;
import com.hp.domain.CustomerImage;
import com.hp.domain.RoadManagement;
import com.hp.domain.User;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class CustomerImageDAOImpl implements CustomerImageDAO{
    @Override
    public boolean saveOrUpdate(CustomerImage pCustomerImage){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.saveOrUpdate(pCustomerImage);
            session.getTransaction().commit();
        }catch(HibernateException e){
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
        
        return true;
    }
    
    public List<CustomerImage> getCustomerImageList(String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<CustomerImage> courses = null;
        try{
            String datefinal="";
            String toDateFinal = "";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 && pToDate.compareTo("") != 0){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                Date date2 = sdf.parse(pToDate);
                toDateFinal = sdf2.format(date2);
                
                System.out.println(" DATECONVERT: " + datefinal );
            }
            
            if(datefinal.compareTo("") == 0)
                courses = session.createQuery("from CustomerImage where customerID ='"+pCustomer+"' order by time  ").list(); 
            else
                courses = session.createQuery("from CustomerImage as cus where cus.customerID ='"+pCustomer+"' "
                        + " and cast (cus.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by cus.time").list();
           
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<CustomerImage> getCustomerImageList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<CustomerImage> courses = null;
        try{
           
           
            courses = session.createQuery("from CustomerImage where status = true  order by id  ").list(); 
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public CustomerImage getCustomerImage(String pStaff){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        CustomerImage customerImage = null;
        try{
            String sql = "from CustomerImage where lower(staffID)='"+pStaff.toLowerCase()+"' "  +" and status = true";
            Query query = session.createQuery(sql);
            
            customerImage = (CustomerImage)query.uniqueResult();
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return customerImage;
    }
    
    public CustomerImage getCustomerImage(int stt){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        CustomerImage customerImage = null;
        try{
            customerImage = (CustomerImage)session.get(CustomerImage.class, stt);
                                  
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return customerImage;
    }
    
    public int updateStatus(String pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        int status = 0;
        try{
            String sql = "update CustomerImage set status = false where lower(customerID)='"+pCustomer.toLowerCase()+"' "  +" and status = true";
            Query query = session.createQuery(sql);
            
            status = query.executeUpdate();
            session.getTransaction().commit();          
            
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        finally {
            session.close();
        }
        
        return status;
    }
}
