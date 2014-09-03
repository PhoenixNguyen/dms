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
import com.hp.domain.Staff;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author HP
 */
public class ProductDAOImpl implements ProductDAO{
    public List<Product> getProductList(String pProvider){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Product> courses = null;
        try{
            courses = session.createQuery("from Product where provider='" +pProvider+"'" ).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    
    }
    
    public List<Product> getProductList(){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Product> courses = null;
        try{
            courses = session.createQuery("from Product " ).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    
    }
    
    public List<Product> getCustomerProductList(String pCustomerID, String pProvider){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Product> courses = null;
        try{
            courses = session.createQuery("from Product as p where p.provider='" +pProvider+"'" +" and p.productID IN " //p.mProvider='" +pProvider+"'" +" and
                    + "(select distinct sod.barcode "
                    + " from SaleOrderDetail as sod, SaleOrder as so"
                    + " where so.id = sod.takeOrderID "
                    + " and so.customerID='"+pCustomerID+"')" ).list(); //and so.mCustomerID='"+pCustomerID+"'
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    
    }
    
    public Product loadProduct(int pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        Product courses = null;
        try{
            //Query query = session.createQuery("from Customer where mMaDoiTuong='"+pCustomer+"'");
            //courses = (Customer)query;
                    
            courses = (Product)session.get(Product.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(Product pProduct){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pProduct);
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
    public boolean saveOrUpdate(Product pProduct){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pProduct);
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
    public boolean delete(Product pProduct){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pProduct);
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
    
    //Search product
    public List<Product> getSearchProductList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Product> courses = null;
        try{
            String str = "from Product where "
                    + " lower(productID) like :pText "
                    + " or lower(barcode) like :pText "
                    + " or lower(productName) like :pText "
                    + " or lower(brand) like :pText "
                    + " or lower(origin) like :pText "
                    + " or lower(packingSpecifications) like :pText "
                    + " or lower(quantification) like :pText "
                    
                    + " or lower(vatTax) like :pText "
                    + " or lower(importPrices) like :pText "
                    + " or lower(exportPrices) like :pText "
                    + " or lower(provider) like :pText "
                    + " or lower(description) like :pText "
                    
                    + " order by productID";
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
