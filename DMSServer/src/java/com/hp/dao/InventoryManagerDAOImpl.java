/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Staff;
import com.hp.domain.InventoryManager;
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
public class InventoryManagerDAOImpl implements InventoryManagerDAO{
    public boolean saveOrUpdate(InventoryManager pInventoryManager){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        
        transaction = session.beginTransaction();
        try{
            
            session.save(pInventoryManager);
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
    
    public List<InventoryManager> getInventoryManagersList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<InventoryManager> courses = null;
        try{
                courses = session.createQuery("from InventoryManager order by takeOrderDate desc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<InventoryManager> getInventoryManagersList(String pStaff, int pPermission){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<InventoryManager> courses = null;
        try{
            if(pPermission == 1)
                courses = session.createQuery("from InventoryManager order by creater, takeOrderDate desc").list();
            else
                
                courses = session.createQuery("from InventoryManager where creater='"+pStaff+"' order by takeOrderDate desc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<InventoryManager> getInventoryManagersList(String pStaff, String pFrom, String pTo){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<InventoryManager> courses = null;
        try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                Date from = sdf.parse(pFrom);
                Date to = sdf.parse(pTo);
            
                courses = session.createQuery("from InventoryManager where creater='"+pStaff+"'" 
                                                + " and takeOrderDate BETWEEN '" +sdf2.format(from)+ "' "
                                                + " and '" +sdf2.format(to)+"'").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public InventoryManager getInventoryManager(int pInventoryManager){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        InventoryManager courses = null;
        try{
                courses = (InventoryManager)session.get(InventoryManager.class, pInventoryManager);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public InventoryManager getInventoryManager(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        InventoryManager courses = null;
        try{
            //Query q = session.createQuery("from InventoryManager where mID='"+pID+"'");
            Query q = session.createSQLQuery("select * from tb_kiemkho where kiemkho_ma_hoa_don='"+pID+"'")
                    .addEntity(InventoryManager.class);
            
            if(q.list() == null)
                return null;
            
             courses = (InventoryManager)q.list().get(0);
            //courses = (InventoryManager)session.get(InventoryManager.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(InventoryManager pInventoryManager){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pInventoryManager);
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
    public boolean delete(InventoryManager pInventoryManager){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pInventoryManager);
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
    
    //Search InventoryManager
    public List<InventoryManager> getSearchInventoryList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<InventoryManager> courses = null;
        try{
            String str = "from InventoryManager where "
                    + " lower(id) like :pText "
                    + " or lower(takeOrderDate) like :pText "
                    + " or lower(deliveryDate) like :pText "
                    + " or lower(customerID) like :pText "
                    + " or lower(customerName) like :pText "
                    + " or lower(customerAddress) like :pText "
                    + " or lower(phoneNumber) like :pText "
                    
                    + " or lower(deliveryAddress) like :pText "
                    + " or lower(shippingVehicle) like :pText "
                    + " or lower(tax) like :pText "
                    + " or lower(afterPrivate) like :pText "
                    
                    + " or lower(orderStatus) like :pText "
                    + " or lower(orderEstablishDate) like :pText "
                    + " or lower(orderEditDate) like :pText "
                    + " or lower(creater) like :pText "
                    + " or lower(editer) like :pText "
                    + " or lower(note) like :pText "
                    
                    + " order by id";
            Query query = session.createQuery(str);
            query.setParameter("pText", "%" +pText.toLowerCase()+ "%");
            
            courses = query.list();
            //courses = session.createQuery("from Customer where lower()order by maDoiTuong").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
}
