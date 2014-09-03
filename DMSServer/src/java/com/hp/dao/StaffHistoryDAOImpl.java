/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.History;
import com.hp.domain.StaffHistory;
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
public class StaffHistoryDAOImpl implements StaffHistoryDAO{
    @Override
    public boolean saveOrUpdate(StaffHistory pStaffHistory){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pStaffHistory);
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
    
    public StaffHistory getStaffHistory(int id){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        StaffHistory staffHistory = null;
        try{
           
            
            staffHistory = (StaffHistory)session.get(StaffHistory.class, id);
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return staffHistory;
    }
    
    public StaffHistory getStaffHistory(String pCustomer, String time){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        boolean status = false;
        StaffHistory staffHistory = null;
        try{
//            String datefinal="";
//            System.out.println(" DATE: " + time); 
//            if(time != null && time.compareTo("")!= 0 ){
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
//                
//                Date date = sdf.parse(time);
//                datefinal = sdf2.format(date);
//                
//                
//                System.out.println(" DATECONVERT: " + datefinal );
//            }
            
            Query query = session.createQuery("from StaffHistory where customer ='"+pCustomer+"' and cast(startTime as date) = '"+ time +"'");
            staffHistory = (StaffHistory)query.uniqueResult();
                      
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return staffHistory;
    }
    
    public List<StaffHistory> getStaffHistoryList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<StaffHistory> courses = null;
        
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
            
            System.out.print(pManagerID);           
            
            if(pCustomer != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from StaffHistory where customer='"+pCustomer+"' order by startTime").list();
                else
                    courses = session.createQuery("select sc from StaffHistory as sc where sc.customer='"+pCustomer+"' "
                            + " and cast (sc.startTime as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by sc.startTime").list();
                
                return courses;
            }
            else
                
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from StaffHistory where staff='"+pStaff+"' order by startTime").list();
                else
                    courses = session.createQuery("select sc from StaffHistory as sc where sc.staff='"+pStaff+"' "
                            + " and cast (sc.startTime as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by sc.startTime").list();
                
                return courses;
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("select sc from StaffHistory as sc, Staff as st where "
                        + "st.id = sc.staff and st.manager = '" +pManagerID +"'"
                        + " order by sc.startTime").list();
                else
                courses = session.createQuery("select sc from StaffHistory as sc, Staff as st where "
                        + "st.id = sc.staff and st.manager = '" +pManagerID +"'"
                        + " and cast (sc.startTime as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'  order by sc.startTime").list();
                return courses;
            }
            else{
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from StaffHistory  order by startTime").list();
                else
                    courses = session.createQuery("from StaffHistory where cast (startTime as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'   order by startTime").list();
                return courses;
            }
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
}
