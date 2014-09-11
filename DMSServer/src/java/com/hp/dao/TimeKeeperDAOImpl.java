/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import com.hp.domain.Staff;
import com.hp.domain.TimeKeeper;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HP
 */
public class TimeKeeperDAOImpl implements TimeKeeperDAO{

    @Override
    public boolean saveOrUpdate(TimeKeeper timeKeeper) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.save(timeKeeper);
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
    public List<TimeKeeper> getTimeKeeperList() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        List<TimeKeeper> courses = null;
        try{
                courses = session.createQuery("from TimeKeeper order by staff asc, timeAt asc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public boolean update(TimeKeeper timeKeeper) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.update(timeKeeper);
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
    public boolean delete(TimeKeeper timeKeeper) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.delete(timeKeeper);
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
    public TimeKeeper getTimeKeeper(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        TimeKeeper courses = null;
        try{
            courses = (TimeKeeper)session.get(TimeKeeper.class, id);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public List<TimeKeeper> getTimeKeeperList(Staff staff) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(TimeKeeper.class);
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

    @Override
    public List<TimeKeeper> getTimeKeeperList(Staff staff, Date date) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            
            c.add(Calendar.DAY_OF_MONTH, 1);
            
            Criteria criteria = session.createCriteria(TimeKeeper.class);
            criteria.add(Restrictions.eq("staff", staff));
            criteria.add(Restrictions.between("timeAt", date, c.getTime()));
            criteria.addOrder(Order.asc("timeAt"));
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
