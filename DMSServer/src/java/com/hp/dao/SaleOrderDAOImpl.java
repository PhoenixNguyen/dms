/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Staff;
import com.hp.domain.SaleOrder;
import com.hp.domain.TakeOrder;
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
public class SaleOrderDAOImpl implements SaleOrderDAO{
    public boolean saveOrUpdate(SaleOrder pSaleOrder){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        
        transaction = session.beginTransaction();
        try{
            
            session.save(pSaleOrder);
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
    
    public List<SaleOrder> getSaleOrdersList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<SaleOrder> courses = null;
        try{
                courses = session.createQuery("from SaleOrder ").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<SaleOrder> getSaleOrdersList(String pStaff){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<SaleOrder> courses = null;
        try{
                courses = session.createQuery("from SaleOrder where creater='"+pStaff+"'").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public SaleOrder getSaleOrder(int pSaleOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        SaleOrder courses = null;
        try{
                courses = (SaleOrder)session.get(SaleOrder.class, pSaleOrder);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public SaleOrder getSaleOrder(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        SaleOrder courses = null;
        try{
            //Query q = session.createQuery("from SaleOrder where mID='"+pID+"'");
            Query q = session.createSQLQuery("select * from tb_hoadonbanhang where hoadonbanhang_ma_hoa_don='"+pID+"'")
                    .addEntity(SaleOrder.class);
             courses = (SaleOrder)q.list().get(0);
            //courses = (SaleOrder)session.get(SaleOrder.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(SaleOrder pSaleOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pSaleOrder);
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
    public boolean delete(SaleOrder pSaleOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pSaleOrder);
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
    
    //Search SaleOrder
    public List<SaleOrder> getSearchSaleList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<SaleOrder> courses = null;
        try{
            String str = "from SaleOrder where "
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
    
    //Get SaleOrders to report
    public List<SaleOrder> getSaleOrderList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<SaleOrder> courses = null;
        
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
            String sql = "";
            if(pCustomer != null){
                if(datefinal.compareTo("") == 0)
                    sql = "from SaleOrder where customerID='"+pCustomer+"' order by takeOrderDate";
                else
                    sql = "from SaleOrder where customerID='"+pCustomer+"' "
                            + " and cast (takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by takeOrderDate";
            }
            else
                
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    sql = "from SaleOrder where creater='"+pStaff+"' order by takeOrderDate";
                else
                    sql = "from SaleOrder where creater='"+pStaff+"' "
                            + " and cast (takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by takeOrderDate";

            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    sql = "select to from SaleOrder as to, Staff as st where "
                            + " st.id = to.creater and st.manager = '" +pManagerID +"'"
                            + " order by to.takeOrderDate";
                else
                    sql = "select to from SaleOrder as to, Staff as st where "
                            + " st.id = to.creater and st.manager = '" +pManagerID +"'"
                            + " and cast (to.takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by to.takeOrderDate";
                
                    
            }
            else{
                if(datefinal.compareTo("") == 0)
                    sql = "from SaleOrder order by takeOrderDate";
                else
                    sql = "from SaleOrder where  "
                            + "  cast (takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by takeOrderDate";
                
            }
            
            courses = session.createQuery(sql).list();
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
}
