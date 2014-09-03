/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Announcement;
import com.hp.domain.Customer;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class AnnouncementDAOImpl implements AnnouncementDAO{
    @Override
    public boolean saveOrUpdate(Announcement pAnnouncement){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pAnnouncement);
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
    
    public int updateStatus(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        int status = 0;
        try{
            String sql = "update Announcement set status = false where  status = true";
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
    
    public Announcement getAnnouncement(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        Announcement announcement = null;
        try{
            String sql = "from Announcement where status = true";
            Query query = session.createQuery(sql);
            
            announcement = (Announcement)query.uniqueResult();
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return announcement;
    }
    
    @Override
    public List<Announcement> loadAnnouncementList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Announcement> courses = null;
        try{
            //String q = "from Customer ";
            Query query = session.createQuery("from Announcement order by date desc");
                        
            courses = query.list();
            //courses = session.createQuery("from Customer where mXCoordinates = 0").list(); //where mXCoordinates is NOT NULL and  mYCoordinates is NOT NULL
            
            // order by  mXCoordinates desc
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
}
