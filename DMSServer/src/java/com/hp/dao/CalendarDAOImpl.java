/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import com.hp.domain.Calendar;
import com.hp.domain.Staff;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
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
                courses = session.createQuery("from Calendar order by calendarDate desc").list();
            
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.update(calendar);
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
    public boolean delete(Calendar calendar) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            Query query = session.createQuery("delete Calendar where stt = " + calendar.getStt());
            query.executeUpdate();
            
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
            criteria.addOrder(Order.desc("calendarDate"));
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
    public List<Calendar> getCalendarList(String staff_id, String city, Date date) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(Calendar.class);
            //inner
            Criteria criteriaInner = criteria.createCriteria("staff");
            criteriaInner.add(Restrictions.eq("id", StringUtils.trimToEmpty(staff_id)));
            
            criteria.add(Restrictions.eq("calendarDate", date));
            criteria.add(Restrictions.eq("province", city));
             
            return criteria.list();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
        finally {
            session.close();
        }
        
    }
    
    @Override
    public List<Calendar> getCalendarList(String pManagerID, String pStaff, Date pFromDate, Date pToDate) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(Calendar.class);
            Criteria criteriaInner = criteria.createCriteria("staff");
            
            if(pStaff!=null && !pStaff.equals("")){
                criteriaInner.add(Restrictions.eq("id", pStaff));
            }
            else
                if(pManagerID!= null && !pManagerID.equals("")){
                    criteriaInner.add(Restrictions.eq("manager", pManagerID));
                }
            
            if(pFromDate != null){
                criteria.add(Restrictions.ge("calendarDate", pFromDate));
            }
            
            if(pToDate != null){
                criteria.add(Restrictions.le("calendarDate", pToDate));
            }
            
            criteria.addOrder(Order.asc("calendarDate"));
            System.err.println(criteria.toString());
            
            return criteria.list();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
        finally {
            session.close();
        }
        
    }
}
