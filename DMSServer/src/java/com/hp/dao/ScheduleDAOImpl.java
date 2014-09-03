/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Customer;
import com.hp.domain.RoadManagement;
import com.hp.domain.Schedule;
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
public class ScheduleDAOImpl implements ScheduleDAO{
    
    @Override
    public boolean saveOrUpdate(Schedule pSchedule){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.saveOrUpdate(pSchedule);
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
    public List<Schedule> getScheduleList(String pMaNV, String pDate, int permission){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        Query query = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(pDate);
            String str;
            if(permission == 1)
                str = "from Schedule where time BETWEEN '"
                        + sdf.format(date) +"' and DATEADD(dd, 1, '"+sdf.format(date) +"') "
                        + "order by maNV, time ";
            else
                str = "from Schedule where lower(maNV)='"+pMaNV.toLowerCase()+"' and time BETWEEN '"
                        + sdf.format(date) +"' and DATEADD(dd, 1, '"+sdf.format(date) +"') "
                        + "order by time ";
            
            courses = session.createQuery(str).list();
//            query.setString(0, pMaNV);
//            query.setDate(1, pDate);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    public int deletechedule(String pID, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        int status = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(pDate);
            
            String str = "delete Schedule where  maKH='"+pID+ "' and "
                    + " time='"+sdf.format(date)+"'" ;
            Query query = session.createQuery(str);
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
    
    @Override
    public List<Schedule> getSchedulesList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        try{
            courses = session.createQuery("from Schedule order by time").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    public List<Schedule> getSchedulesList(String pManagerID, String pStaff, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        
        try{
            String datefinal="";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                
                System.out.println(" DATECONVERT: " + datefinal );
            }
            
            System.out.print(pManagerID);           
            
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule where maNV='"+pStaff+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maNV='"+pStaff+"' "
                            + " and cast (sc.time as date) = '"+datefinal+"'  order by sc.time").list();
                
                return courses;
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " order by sc.time").list();
                else
                courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " and cast (sc.time as date) = '"+datefinal+"'  order by sc.time").list();
                return courses;
            }
            else{
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule  order by time").list();
                else
                    courses = session.createQuery("from Schedule where cast (time as date) = '"+datefinal+"'   order by time").list();
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
    
    public List<Schedule> getSchedulesList(String pManagerID, String pStaff, String pCustomer, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        
        try{
            String datefinal="";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                
                System.out.println(" DATECONVERT: " + datefinal );
            }
            
            System.out.print(pManagerID);           
            
            if(pCustomer != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule where maKH='"+pCustomer+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maKH='"+pCustomer+"' "
                            + " and cast (sc.time as date) = '"+datefinal+"'  order by sc.time").list();
                
                return courses;
            }
            else 
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule where maNV='"+pStaff+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maNV='"+pStaff+"' "
                            + " and cast (sc.time as date) = '"+datefinal+"'  order by sc.time").list();
                
                return courses;
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " order by sc.time").list();
                else
                courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " and cast (sc.time as date) = '"+datefinal+"'  order by sc.time").list();
                return courses;
            }
            else{
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule  order by time").list();
                else
                    courses = session.createQuery("from Schedule where cast (time as date) = '"+datefinal+"'   order by time").list();
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
    
    public List<Schedule> getSchedulesListForSchedules(String pManagerID, String pStaff, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        
        try{
            String datefinal="";
            String toDateFinal = "";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 && pToDate.compareTo("") != 0){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                Date date2 = sdf.parse(pToDate);
                toDateFinal = sdf2.format(date2);
                
                System.out.println(" DATECONVERT: " + datefinal );
            }
            
            System.out.print(pManagerID);           
            
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule where maNV='"+pStaff+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maNV='"+pStaff+"' "
                            + " and cast (sc.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by sc.time").list();
                
                return courses;
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " order by sc.time").list();
                else
                courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " and cast (sc.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'  order by sc.time").list();
                return courses;
            }
            else{
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule  order by time").list();
                else
                    courses = session.createQuery("from Schedule where cast (time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'   order by time").list();
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
    
    public List<Schedule> getSchedulesListForSchedules(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Schedule> courses = null;
        
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
                    courses = session.createQuery("from Schedule where maKH='"+pCustomer+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maKH='"+pCustomer+"' "
                            + " and cast (sc.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by sc.time").list();
                
                return courses;
            }
            else
                
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule where maNV='"+pStaff+"' order by time").list();
                else
                    courses = session.createQuery("select sc from Schedule as sc where sc.maNV='"+pStaff+"' "
                            + " and cast (sc.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' order by sc.time").list();
                
                return courses;
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " order by sc.time").list();
                else
                courses = session.createQuery("select sc from Schedule as sc, Staff as st where "
                        + "st.id = sc.maNV and st.manager = '" +pManagerID +"'"
                        + " and cast (sc.time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'  order by sc.time").list();
                return courses;
            }
            else{
                if(datefinal.compareTo("") == 0)
                    courses = session.createQuery("from Schedule  order by time").list();
                else
                    courses = session.createQuery("from Schedule where cast (time as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'   order by time").list();
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
