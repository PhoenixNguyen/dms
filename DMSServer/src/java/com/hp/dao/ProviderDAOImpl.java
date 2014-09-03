/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Customer;
import com.hp.domain.Product;
import com.hp.domain.Provider;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ProviderDAOImpl implements ProviderDAO{
    public List<Provider> getProvidersList(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Provider> courses = null;
        try{
            courses = session.createQuery("from Provider " ).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<Provider> getProvidersList(String customerID){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Provider> courses = null;
        try{
            courses = session.createQuery("select distinct per "
                    + "from Provider as per, Product as pct, SaleOrder as so, SaleOrderDetail as sod "
                    + "where per.id = pct.provider and "
                    + " so.id = sod.takeOrderID and "
                    + " pct.productID = sod.barcode and "
                    + " so.customerID='"+customerID+"'" ).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<String> getProvidersIDList(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<String> courses = null;
        try{
            courses = session.createQuery("select id from Provider " ).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public Provider loadProvider(int pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        Provider courses = null;
        try{
            //Query query = session.createQuery("from Customer where mMaDoiTuong='"+pCustomer+"'");
            //courses = (Customer)query;
                    
            courses = (Provider)session.get(Provider.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(Provider pProvider){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pProvider);
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
    public boolean saveOrUpdate(Provider pProvider){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pProvider);
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
    public boolean delete(Provider pProvider){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pProvider);
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
    
    //Search Provider
    public List<Provider> getSearchProviderList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Provider> courses = null;
        try{
            String str = "from Provider where "
                    + " lower(id) like :pText "
                    + " or lower(name) like :pText "
                    + " or lower(address) like :pText "
                    + " or lower(phoneNumber) like :pText "
                    + " or lower(fax) like :pText "
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
