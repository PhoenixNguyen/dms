/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.Customer;
import com.hp.domain.Staff;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class CustomerDAOImpl implements CustomerDAO {
    
    //Add customer  
    @Override
    public boolean saveOrUpdate(Customer pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.save(pCustomer);
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
    public boolean update(Customer pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pCustomer);
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
    
    public boolean delete(Customer pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pCustomer);
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
    
    public List<Customer> getListCustomers(String pStaff, int pPermission){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            if(pPermission == 1)
                courses = session.createQuery("from Customer order by maDoiTuong  ").list(); 
            else
                
                courses = session.createQuery("from Customer where  " 
                        + " maNhanVien='"+pStaff+"' order by maDoiTuong ").list(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public List<Customer> loadCustomersWithLocations(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            String q = "from Customer ";
            Query query = session.createQuery("from Customer where coordinateX > 0");
                        
            courses = query.list();
            //courses = session.createQuery("from Customer where mXCoordinates = 0").list(); //where mXCoordinates is NOT NULL and  mYCoordinates is NOT NULL
            
            // order by  mXCoordinates desc
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    public List<Customer> loadCustomersWithLocations(String pManagerID, String pStaff, String pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        List<Customer> courses2 = null;
        List<Customer> courses3 = null;
        try{
            
            System.out.print(pManagerID);    
            if(pCustomer == null){
                if(pStaff == null)
                    courses = session.createQuery("select cus from Customer as cus, Staff as st where "
                            + "st.id = cus.maNhanVien and st.manager = '" +pManagerID
                            + "' and cus.coordinateX > 0  "  //and cus.mXCoordinates is NOT NULL
                            + " order by  cus.coordinateX desc  ").list(); //cus.mYCoordinates is NOT NULL
                else
                    courses = session.createQuery("from Customer where maNhanVien='"+pStaff+"' and coordinateX > 0 order by  coordinateX desc").list();
            }
            else
                courses = session.createQuery("from Customer where maDoiTuong='"+pCustomer+"' order by  coordinateX desc").list();
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    //Get List customer to ajax
    public List<String> getListCustomer(String pStaff){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<String> courses = null;
        try{
            if(pStaff == null)
                courses = session.createQuery("select maDoiTuong from Customer  ").list(); //where mXCoordinates is NOT NULL and  mYCoordinates is NOT NULL
            else
                courses = session.createQuery("select maDoiTuong from Customer where  " //mXCoordinates is NOT NULL
                        + " maNhanVien='"+pStaff+"'").list(); //and  mYCoordinates is NOT NULL and 
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    //Update customer location
    @Override
    public int update(String pID, float pX, float pY){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        int status = 0;
        try{
            String str = "update Customer set coordinateX ="+pX+ " "
                    + ", coordinateY="+pY+" where maDoiTuong='"+pID+ "'" ;
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
    
    //list customer for schedule
    public List<Customer> getListCustomerSchedule(String pStaff, String pDate, int pPermission){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(pDate);
            String str = "";
            if(pPermission == 1)
                str = "select cus1.* from tb_khachhang as cus1  "
                        + " Except "
                        + "(select cus.* from tb_khachhang as cus, tb_schedule as sc "
                        + "where cus.khachhang_ma_dt = sc.schedule_ma_khach_hang "
                        
                        + " and sc.schedule_date BETWEEN '"
                        + sdf.format(date) +"' and DATEADD(dd, 1, '"+sdf.format(date) +"') )";
            else  
            
                str = "select cus1.* from tb_khachhang as cus1 where lower(cus1.khachhang_ma_nv)='"+pStaff.toLowerCase()+"' "
                        + " Except "
                        + "(select cus.* from tb_khachhang as cus, tb_schedule as sc "
                        + "where cus.khachhang_ma_dt = sc.schedule_ma_khach_hang "
                        + "and lower(cus.khachhang_ma_nv)='"+pStaff.toLowerCase()+"' "
                        + "and sc.schedule_date BETWEEN '"
                        + sdf.format(date) +"' and DATEADD(dd, 1, '"+sdf.format(date) +"') )";      
            
            courses = session.createSQLQuery(str).addEntity(Customer.class).list();
    
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<Customer> loadCustomersWithLocationsForSchedule(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            courses = session.createQuery("select cus from Customer as cus, Schedule as sc "
                    + "where cus.maDoiTuong = sc.maKH "
                    + "and cus.coordinateX is NOT NULL and  cus.coordinateY is NOT NULL ").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    public List<Customer> loadCustomersWithLocationsForSchedule(String pManagerID, String pStaff){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        List<Customer> courses2 = null;
        List<Customer> courses3 = null;
        try{
            
            System.out.print(pManagerID);           
            if(pStaff == null)
                courses = session.createQuery("select cus from Customer as cus,Staff as st, Schedule as sc where "
                        + "cus.maDoiTuong = sc.maKH "
                        + "and st.id = cus.maNhanVien and st.manager = '" +pManagerID
                        + "' and cus.coordinateX is NOT NULL "
                        + "and  cus.coordinateY is NOT NULL ").list();
            else
                courses = session.createQuery("from Customer where maNhanVien='"+pStaff+"'").list();
          
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    @Override
    public List<Customer> loadCustomersDetail(String pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            courses = session.createQuery("from Customer where maDoiTuong='"+pCustomer+"'").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    //Get List customer to ajax
    public List<Customer> getListCustomer(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            courses = session.createQuery("from Customer order by maDoiTuong").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public Customer loadCustomer(String pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        Customer courses = null;
        try{
            //Query query = session.createQuery("from Customer where mMaDoiTuong='"+pCustomer+"'");
            //courses = (Customer)query;
                    
            courses = (Customer)session.get(Customer.class, 1);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public Customer loadCustomer(int pCustomer){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        Customer courses = null;
        try{
            //Query query = session.createQuery("from Customer where mMaDoiTuong='"+pCustomer+"'");
            //courses = (Customer)query;
                    
            courses = (Customer)session.get(Customer.class, pCustomer);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        finally {
            session.close();
        }
        
        return courses;
    }
    
    public List<Staff> loadStaffsWithLocationsForSchedule(){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Staff> courses = null;
        try{
            courses = session.createQuery("select st from Staff as st, Schedule as sc "
                    + "where st.id = sc.maNV "
                    + " ").list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
        
    }
    
    public List<List<Customer>> customerScheduleList(String pManagerID, String pStaff, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<List<Customer>> courses = new ArrayList<List<Customer>>();
        try{
            String datefinal="";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                
                System.out.println(" DATECONVERT: " + datefinal  );
            }
            
            if(pStaff != null)
            {
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +pStaff+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +pStaff+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                //}
                
                return courses;
            }
            else 
            if(pManagerID != null){
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + "where manager ='"+pManagerID+"' "
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                }
                return courses;
            }
            else{
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + " "
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                }
                return courses;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return courses;
    }
    
    public List<List<Customer>> customerScheduleList(String pManagerID, String pStaff, String pCustomer, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<List<Customer>> courses = new ArrayList<List<Customer>>();
        try{
            String datefinal="";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                
                System.out.println(" DATECONVERT: " + datefinal  );
            }
            
            if(pCustomer != null)
            {
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maKH='" +pCustomer+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maKH='" +pCustomer+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                //}
                
                return courses;
            }
            else 
                
            if(pStaff != null)
            {
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +pStaff+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +pStaff+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                //}
                
                return courses;
            }
            else 
            if(pManagerID != null){
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + "where manager ='"+pManagerID+"' "
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                }
                return courses;
            }
            else{
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + " "
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<Customer> tmp = new ArrayList<Customer>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + "where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' order by sc.maNV, sc.time").list();
                    else
                        tmp = session.createQuery("select cus from Schedule as sc, Customer as cus "
                                + " where sc.maKH = cus.maDoiTuong and sc.maNV='" +nhanvien.get(i)+"' "
                                + " and cast (sc.time as date) = '"+datefinal+"' "
                                + " order by sc.maNV, sc.time").list();
                                                                
                    if(tmp.size() > 0)
                        courses.add(tmp);
                }
                return courses;
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return courses;
    }
    //Search customers
    public List<Customer> getSearchCustomerList(String pText){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<Customer> courses = null;
        try{
            String str = "from Customer where "
                    + " lower(tinhThanh) like :pText "
                    + " or lower(tuyenBanHangThu) like :pText "
                    + " or lower(maNhanVien) like :pText "
                    + " or lower(x) like :pText "
                    + " or lower(maDoiTuong) like :pText "
                    + " or lower(doiTuong) like :pText "
                    + " or lower(diaChi) like :pText "
                    
                    + " or lower(dienThoai) like :pText "
                    + " or lower(fax) like :pText "
                    + " or lower(ghiChu) like :pText "
                    //+ " or lower(diaChi) like :pText "
                    
                    + " order by maDoiTuong";
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
