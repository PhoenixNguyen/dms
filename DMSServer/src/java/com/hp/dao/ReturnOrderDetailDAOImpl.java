/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.ReturnOrder;
import com.hp.domain.ReturnOrderDetail;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ReturnOrderDetailDAOImpl implements ReturnOrderDetailDAO{
    public boolean saveOrUpdate(ReturnOrderDetail pReturnOrder){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        
        transaction = session.beginTransaction();
        try{
            
            session.save(pReturnOrder);
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
    
    public List<ReturnOrderDetail> getDetailReturnOrdersList(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<ReturnOrderDetail> courses = null;
        try{
                courses = session.createQuery("from ReturnOrderDetail where takeOrderID='"+pID+"'").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
     public ReturnOrderDetail getReturnOrderDetail(int pReturnOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        ReturnOrderDetail courses = null;
        try{
                courses = (ReturnOrderDetail)session.get(ReturnOrderDetail.class, pReturnOrderDetail);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
     
     public ReturnOrderDetail getReturnOrderDetail(String pOrderID, int pLine){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<ReturnOrderDetail> courses = null;
        try{
                Query q = session.createSQLQuery("select * from tb_chitietdontrahang where "
                        + " chitietdontrahang_ma_hoa_don ='"+pOrderID+"' "
                        + " and chitietdontrahang_dong=" + pLine).addEntity(ReturnOrderDetail.class);
                
                courses = q.list();
                
                
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        if(courses == null || courses.size() == 0)
            return null;
        else
            return courses.get(0);
    }
     
     @Override
    public boolean update(ReturnOrderDetail pReturnOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pReturnOrderDetail);
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
    
   
    @Override
    public boolean delete(ReturnOrderDetail pReturnOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pReturnOrderDetail);
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
    
    @Override
    public boolean delete(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            Query q = session.createQuery("delete ReturnOrderDetail where takeOrderID='"+pID+"'");
            q.executeUpdate();
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
}
