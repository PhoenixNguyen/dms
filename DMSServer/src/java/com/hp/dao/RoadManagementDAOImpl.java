/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.RoadManagement;
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
public class RoadManagementDAOImpl implements RoadManagementDAO{
    
    @Override
    public List<List<RoadManagement>> getRoad(String pGiamDoc, String pNhanVien, String pMaKhachHang, String pDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<List<RoadManagement>> result = new ArrayList<List<RoadManagement>>();
        try{
            String datefinal="";
            
            //System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                
                //System.out.println(" DATECONVERT: " + datefinal );
            }
            
//            if(pMaKhachHang != null){
//
//                List<RoadManagement> tmp = new ArrayList<RoadManagement>();
//                if(datefinal.compareTo("") == 0)
//                    tmp = session.createQuery("from RoadManagement where mMaKhachHang = '"+pMaKhachHang+"'").list();
//                else
//                    tmp = session.createQuery("from RoadManagement where mMaKhachHang = '"+pMaKhachHang+"'"
//                            + " and cast (mThoiGian as date) BETWEEN '"+datefinal+"' and '" +toDatefinal+"'").list();
//                 if(tmp != null)
//                     result.add(tmp);
//            }
            if(pNhanVien != null){

//                //Lay danh sach ID khach hang cua giam doc
//                List<String> khachhang = null;
//                khachhang = session.createQuery("select mMaDoiTuong "
//                        + "from Customer where mMaNhanVien='"+pNhanVien+"'").list();
//                for(int i= 0; i < khachhang.size(); i++){
                    List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+pNhanVien+"'").list();
                    else
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+pNhanVien+"'"
                                + "  and cast (thoiGian as date) = '"+datefinal+"' ").list();
                    
                    if(tmp.size() > 0)
                        result.add(tmp);
                //}
                
                return result;
            }
            else 
            if(pGiamDoc != null){
                //Lay danh sach ID nhan vien cua giam doc
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + "where manager ='"+pGiamDoc+"' "
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+nhanvien.get(i)+"'").list();
                    else
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+nhanvien.get(i)+"'"
                                + " and cast (thoiGian as date) = '"+datefinal+"' ").list();
                    
                    if(tmp.size() > 0)
                        result.add(tmp);
                }
                return result;
            }
            
            
            else{

                //for(int i= 0; i < khachhang.size(); i++){
                    List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("from RoadManagement ").list();
                    else
                        tmp = session.createQuery("select rm from RoadManagement as rm where "
                                + " cast (rm.thoiGian as date) = '"+datefinal+"'").list();
                    
                    if(tmp.size() > 0)
                        result.add(tmp);
                //}                
                return result;
            }
//            courses = session.createSQLQuery("select tb_quanlyduongdi.* from tb_quanlyduongdi as road,tb_nhanvien as nv  "
//                    + "where nv.nhanvien_ma_nhan_vien=road.quanlyduongdi_ma_nhan_vien and nv.mMaNhanVien = 'DVH'").addEntity(RoadManagement.class).list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return result;
    }
    
    public List<List<RoadManagement>> getRoad(String pGiamDoc, String pNhanVien, String pMaKhachHang, String pDate, String toDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<List<RoadManagement>> result = new ArrayList<List<RoadManagement>>();
        try{
            String datefinal="";
            String toDatefinal="";
            System.out.println(" DATE: " + pDate); 
            if(pDate != null && pDate.compareTo("")!= 0 ){
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                
                Date date = sdf.parse(pDate);
                datefinal = sdf2.format(date);
                
                Date to_date = sdf.parse(toDate);
                toDatefinal = sdf2.format(to_date);
                
                //System.out.println(" DATECONVERT: " + datefinal );
            }
            
            if(pNhanVien != null){

                //Lay danh sach ID khach hang cua giam doc
                List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                if(datefinal.compareTo("") == 0)
                    tmp = session.createQuery("from RoadManagement where maNhanVien='"+pNhanVien+"' order by thoiGian").list();
                else
                    tmp = session.createQuery("from RoadManagement where maNhanVien='"+pNhanVien+"'"
                            + "  and cast (thoiGian as date) BETWEEN '"+datefinal+"' and '" + toDatefinal + "' order by thoiGian").list();

                if(tmp != null && tmp.size() > 0)
                    result.add(tmp);
                
                return result;
            }
            else 
            if(pGiamDoc != null){
                //Lay danh sach ID nhan vien cua giam doc
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff "
                        + "where manager ='"+pGiamDoc+"' order by id"
                        ).list();
                for(int i= 0; i < nhanvien.size(); i++){
                    List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+nhanvien.get(i)+"' order by thoiGian").list();
                    else
                        tmp = session.createQuery("from RoadManagement where maNhanVien='"+nhanvien.get(i)+"'"
                                + " and cast (thoiGian as date) BETWEEN '"+datefinal+"' and '" + toDatefinal + "' order by thoiGian").list();
                    
                    if(tmp.size() > 0)
                        result.add(tmp);
                }
                return result;
            }
            
            
            else{
                List<String> nhanvien = null;
                nhanvien = session.createQuery("select id "
                        + "from Staff order by id"
                        ).list();
                
                    List<RoadManagement> tmp = new ArrayList<RoadManagement>();
                    if(datefinal.compareTo("") == 0)
                        tmp = session.createQuery("from RoadManagement order by maNhanVien, thoiGian").list();
                    else
                        tmp = session.createQuery("select rm from RoadManagement as rm where "
                                + " cast (rm.thoiGian as date) BETWEEN '"+datefinal+"' and '" + toDatefinal + "' order by rm.maNhanVien, rm.thoiGian").list();
                    
                    if(tmp.size() > 0)
                        for(String nv : nhanvien){
                            List<RoadManagement> rmNV = new ArrayList<RoadManagement>();
                            for(RoadManagement rm : tmp){
                                if( nv.equalsIgnoreCase(rm.getMaNhanVien()))
                                    rmNV.add(rm);
                            }
                            if(rmNV.size() > 0)
                                result.add(rmNV);
                        }
                        
                //}                
                return result;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return result;
    }
    
    //Add customer  
    @Override
    public boolean saveOrUpdate(RoadManagement pRoadManagement){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.saveOrUpdate(pRoadManagement);
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
    
    public List<RoadManagement> findLocationByTime(Date time){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        List<RoadManagement> results = null;
        try{
            String timeToCompare = "";
            if(time != null){
                
                timeToCompare = df.format(time);
                System.out.println("date: " + timeToCompare);
            }
            else{
                timeToCompare = df.format(new Date());
            }
            
            String sql = "SELECT a.* \n" +
                "From tb_quanlyduongdi \n" +
                "\n" +
                "as a \n" +
                "RIGHT JOIN (\n" +
                "	select rm.quanlyduongdi_ma_nhan_vien as nhan_vien, max(quanlyduongdi_thoi_gian) AS TIMET \n" +
                "	from dbo.tb_quanlyduongdi rm \n" +
                "	where quanlyduongdi_thoi_gian <= '"+timeToCompare+"' \n" +    
                "	group by quanlyduongdi_ma_nhan_vien \n" +
//                "	having max(quanlyduongdi_thoi_gian) < '"+timeToCompare+"' \n" +
                ") b ON a.quanlyduongdi_ma_nhan_vien = b.nhan_vien \n" +
                "AND a.quanlyduongdi_thoi_gian=b.TIMET "
                + "order by a.quanlyduongdi_ma_nhan_vien ";
            
//            String query = "select rm from RoadManagement where rm.thoiGian in ("
//                    + " select top 1 thoiGian "
//                    + " from RoadManagement"
//                    + " where cast (thoiGian as time) < '" + timeToCompare +"'  "
//                    + " group by maNhanVien order by thoiGian desc "
//                    + ")"; //order by rm.maNhanVien
             
            Query query = session.createSQLQuery(sql)
                .addEntity(RoadManagement.class);
            results = query.list();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        
        finally {
            session.close();
        }
        
        return results;
    }
}
