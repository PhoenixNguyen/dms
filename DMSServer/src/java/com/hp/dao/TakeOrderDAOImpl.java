/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Staff;
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
public class TakeOrderDAOImpl implements TakeOrderDAO{
    public boolean saveOrUpdate(TakeOrder pTakeOrder){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction;
        
        transaction = session.beginTransaction();
        try{
            
            session.save(pTakeOrder);
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
    
    public List<TakeOrder> getTakeOrdersList(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<TakeOrder> courses = null;
        try{
                courses = session.createQuery("from TakeOrder order by takeOrderDate desc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<TakeOrder> getTakeOrdersList(String pStaff, int pPermission){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<TakeOrder> courses = null;
        try{
            if(pPermission == 1)
                courses = session.createQuery("from TakeOrder order by takeOrderDate desc").list();
            else
                
                courses = session.createQuery("from TakeOrder where creater='"+pStaff+"' order by takeOrderDate desc").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<TakeOrder> getTakeOrdersList(String pStaff, String pFrom, String pTo){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<TakeOrder> courses = null;
        try{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                
                Date from = sdf.parse(pFrom);
                Date to = sdf.parse(pTo);
            
                courses = session.createQuery("from TakeOrder where mcreater='"+pStaff+"'" 
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
    
    public TakeOrder getTakeOrder(int pTakeOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        TakeOrder courses = null;
        try{
                courses = (TakeOrder)session.get(TakeOrder.class, pTakeOrder);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public TakeOrder getTakeOrder(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        TakeOrder courses = null;
        try{
            //Query q = session.createQuery("from TakeOrder where mID='"+pID+"'");
//            Query q = session.createSQLQuery("select * from tb_hoadondathang where hoadondathang_ma_hoa_don='"+pID+"'")
//                    .addEntity(TakeOrder.class);
//            
//            if(q.list() == null)
//                return null;
//            
//             courses = (TakeOrder)q.list().get(0);
            
            Query q = session.createQuery("from TakeOrder where id='"+pID+"'")
                    ;
            courses = (TakeOrder)q.uniqueResult();
            //courses = (TakeOrder)session.get(TakeOrder.class, pID);
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public boolean update(TakeOrder pTakeOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pTakeOrder);
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
    public boolean delete(TakeOrder pTakeOrder){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pTakeOrder);
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
    
    //Search TakeOrder
    public List<TakeOrder> getSearchTakeOrderList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<TakeOrder> courses = null;
        try{
            String str = "from TakeOrder where "
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
    
    //Get TakeOrders to report
    public List<TakeOrder> getTakeOrderList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<TakeOrder> courses = null;
        
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
                    sql = "from TakeOrder where customerID='"+pCustomer+"' order by takeOrderDate";
                else
                    sql = "from TakeOrder where customerID='"+pCustomer+"' "
                            + " and cast (takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by takeOrderDate";
            }
            else
                
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0)
                    sql = "from TakeOrder where creater='"+pStaff+"' order by takeOrderDate";
                else
                    sql = "from TakeOrder where creater='"+pStaff+"' "
                            + " and cast (takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by takeOrderDate";

            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0)
                    sql = "select to from TakeOrder as to, Staff as st where "
                            + " st.id = to.creater and st.manager = '" +pManagerID +"'"
                            + " order by to.takeOrderDate";
                else
                    sql = "select to from TakeOrder as to, Staff as st where "
                            + " st.id = to.creater and st.manager = '" +pManagerID +"'"
                            + " and cast (to.takeOrderDate as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"' "
                            + " order by to.takeOrderDate";
                
                    
            }
            else{
                if(datefinal.compareTo("") == 0)
                    sql = "from TakeOrder order by takeOrderDate";
                else
                    sql = "from TakeOrder where  "
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
