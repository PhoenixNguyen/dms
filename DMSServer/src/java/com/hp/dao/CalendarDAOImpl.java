/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class CalendarDAOImpl implements CalendarDAO{
    
    @Override
    public boolean saveOrUpdate(Calendar calendar) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.save(calendar);
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
    public List<Calendar> getCalendarList() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        List<Calendar> courses = null;
        try{
                courses = session.createQuery("from Calendar ").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public boolean update(Calendar calendar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Calendar calendar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Calendar getCalendar(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        Calendar courses = null;
        try{
            courses = (Calendar)session.get(Calendar.class, id);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public List<Calendar> getCalendarList(Staff staff) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(Calendar.class);
            criteria.add(Restrictions.eq("staff", staff));
            
            return criteria.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return null;
    }
    
}
