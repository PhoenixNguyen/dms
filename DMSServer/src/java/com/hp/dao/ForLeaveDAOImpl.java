/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import com.hp.domain.ForLeave;
import com.hp.domain.Staff;
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
public class ForLeaveDAOImpl implements ForLeaveDAO{
    @Override
    public boolean saveOrUpdate(ForLeave forLeave) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.save(forLeave);
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
    public List<ForLeave> getForLeaveList() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        List<ForLeave> courses = null;
        try{
                courses = session.createQuery("from ForLeave order by staff asc, timeAt asc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public boolean update(ForLeave forLeave) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.update(forLeave);
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
    public boolean delete(ForLeave forLeave) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
    
        try{
            session.delete(forLeave);
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
    public ForLeave getForLeave(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        
        ForLeave courses = null;
        try{
            courses = (ForLeave)session.get(ForLeave.class, id);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }

    @Override
    public List<ForLeave> getForLeaveList(Staff staff) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(ForLeave.class);
            criteria.add(Restrictions.eq("staff", staff));
            criteria.addOrder(Order.desc("timeAt"));
             
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
    public List<ForLeave> getForLeaveList(Staff staff, Date date) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            
            c.add(Calendar.DAY_OF_MONTH, 1);
            
            Criteria criteria = session.createCriteria(ForLeave.class);
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
 
    @Override
    public List<ForLeave> getForLeaveList(String pManagerID, String pStaff, Date pFromDate, Date pToDate) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Criteria criteria = session.createCriteria(ForLeave.class);
            Criteria criteriaInner = criteria.createCriteria("staff");
            
            if(pStaff!=null && !pStaff.equals("")){
                criteriaInner.add(Restrictions.eq("id", pStaff));
            }
            else
                if(pManagerID!= null && !pManagerID.equals("")){
                    criteriaInner.add(Restrictions.eq("manager", pManagerID));
                }
            
            if(pFromDate != null){
                criteria.add(Restrictions.ge("timeAt", pFromDate));
            }
            
            if(pToDate != null){
                criteria.add(Restrictions.le("timeAt", pToDate));
            }
            
            criteria.addOrder(Order.asc("timeAt"));
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
