/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Staff;
import com.hp.domain.ReturnOrder;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ReturnOrderDAOImpl implements ReturnOrderDAO{
    public boolean saveOrUpdate(ReturnOrder pReturnOrder){
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
    
    public List<ReturnOrder> getReturnOrdersList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<ReturnOrder> courses = null;
        try{
                courses = session.createQuery("from ReturnOrder ").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<ReturnOrder> getReturnOrdersList(String pStaff){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<ReturnOrder> courses = null;
        try{
                courses = session.createQuery("from ReturnOrder where creater='"+pStaff+"'").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public ReturnOrder getReturnOrder(int pReturnOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        ReturnOrder courses = null;
        try{
                courses = (ReturnOrder)session.get(ReturnOrder.class, pReturnOrder);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public ReturnOrder getReturnOrder(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        ReturnOrder courses = null;
        try{
            //Query q = session.createQuery("from ReturnOrder where mID='"+pID+"'");
            Query q = session.createSQLQuery("select * from tb_hoadontrahang where hoadontrahang_ma_hoa_don='"+pID+"'")
                    .addEntity(ReturnOrder.class);
             courses = (ReturnOrder)q.list().get(0);
            //courses = (ReturnOrder)session.get(ReturnOrder.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(ReturnOrder pReturnOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pReturnOrder);
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
    public boolean delete(ReturnOrder pReturnOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pReturnOrder);
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
