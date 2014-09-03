/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.History;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class HistoryDAOImpl implements HistoryDAO{
    
    @Override
    public boolean saveOrUpdate(History pHistory){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pHistory);
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
    
    public boolean updateHistory(History pHistory){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            session.update(pHistory);
            
            session.getTransaction().commit();          
            
        }catch(Exception e){
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
        
        return true;
    }
    
    public List<History> getHistoryList(String pUser){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        List<History> historyList = null;
        try{
            String sql = "from History where user = '"+pUser+"'";
            Query query = session.createQuery(sql);
            
            historyList = query.list();
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return historyList;
    }
 
    public History getHistory(String time){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        History history = null;
        try{
            String sql = "from History where login='"+time+"'";
            Query query = session.createQuery(sql);
            
            history = (History)query.uniqueResult();
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return history;
    }
    
    public History getHistory(int id){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        History history = null;
        try{
           
            
            history = (History)session.get(History.class, id);
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return history;
    }
}
