/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory;
import static com.googlecode.s2hibernate.struts2.plugin.util.HibernateSessionFactory.getSessionFactory;
import com.hp.domain.ReportSaleWithProduct;
import com.hp.domain.SaleOrder;
import com.hp.domain.SaleOrderDetail;
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
public class SaleOrderDetailDAOImpl implements SaleOrderDetailDAO{
    public boolean saveOrUpdate(SaleOrderDetail pSaleOrder){
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
    
    public List<SaleOrderDetail> getDetailSaleOrdersList(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<SaleOrderDetail> courses = null;
        try{
                courses = session.createQuery("from SaleOrderDetail where takeOrderID='"+pID+"'").list();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
    
     public SaleOrderDetail getSaleOrderDetail(int pSaleOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        SaleOrderDetail courses = null;
        try{
                courses = (SaleOrderDetail)session.get(SaleOrderDetail.class, pSaleOrderDetail);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
     
     @Override
    public boolean update(SaleOrderDetail pSaleOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.update(pSaleOrderDetail);
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
    public boolean delete(SaleOrderDetail pSaleOrderDetail){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            
            session.delete(pSaleOrderDetail);
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
    public boolean delete(String pID){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        try{
            Query q = session.createQuery("delete SaleOrderDetail where takeOrderID='"+pID+"'");
            q.executeUpdate();
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
    
    //Get report Sale order with products
    public List<ReportSaleWithProduct> getProductReportList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate){
        Session session = getSessionFactory().openSession();
        Transaction transaction;
        transaction = session.beginTransaction();
        
        List<ReportSaleWithProduct> courses = null;
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
            String sql = "";
            
            if(pCustomer != null){
                if(datefinal.compareTo("") == 0){
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                            "            chitiet.chitietdonbanhang_ma_hang as id, \n" +
                            "		 chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                            "            chitiet.chitietdonbanhang_thue as tax,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                            "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                            "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                            "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang\n" +
                            "	     where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                            "			and banhang.hoadonbanhang_ma_khach_hang = '"+pCustomer+"'\n" +
                            "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                            "            chitiet.chitietdonbanhang_thue\n" +
                            "                \n" +
                            "        order by chitiet.chitietdonbanhang_ma_hang";
                }
                else{
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                            "		 chitiet.chitietdonbanhang_ma_hang as id, \n" +
                            "		 chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                            "            chitiet.chitietdonbanhang_thue as tax,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                            "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                            "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                            "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang\n" +
                            "	     where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                            "			and banhang.hoadonbanhang_ma_khach_hang = '"+pCustomer+"'\n" +
                            "			and cast(banhang.hoadonbanhang_ngay_ban_hang as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'\n" +
                            "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                            "            chitiet.chitietdonbanhang_thue\n" +
                            "                \n" +
                            "        order by chitiet.chitietdonbanhang_ma_hang";
                }
            }
            else
                
            if(pStaff != null)
            {
                if(datefinal.compareTo("") == 0){
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                            "            chitiet.chitietdonbanhang_ma_hang as id, \n" +
                            "		 chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                            "            chitiet.chitietdonbanhang_thue as tax,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                            "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                            "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                            "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                            "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang\n" +
                            "	     where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                            "			and banhang.hoadonbanhang_nguoi_tao = '"+pStaff+"'\n" +
                            "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                            "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                            "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                            "            chitiet.chitietdonbanhang_thue\n" +
                            "                \n" +
                            "        order by chitiet.chitietdonbanhang_ma_hang";
                }
                else{
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                        "	     chitiet.chitietdonbanhang_ma_hang as id, \n" +
                        "	     chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                        "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                        "            chitiet.chitietdonbanhang_thue as tax,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                        "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                        "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                        "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                        "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                        "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang\n" +
                        "	 where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                        "			and banhang.hoadonbanhang_nguoi_tao = '"+pStaff+"'\n" +
                        "			and cast(banhang.hoadonbanhang_ngay_ban_hang as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'\n" +
                        "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                        "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                        "            chitiet.chitietdonbanhang_thue\n" +
                        "                \n" +
                        "        order by chitiet.chitietdonbanhang_ma_hang";
                }
            }
            else 
            if(pManagerID != null){
                if(datefinal.compareTo("") == 0){
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                    "		 chitiet.chitietdonbanhang_ma_hang as id, \n" +
                    "		 chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                    "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                    "            chitiet.chitietdonbanhang_thue as tax,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                    "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                    "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                    "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                    "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                    "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang, tb_nhanvien as nv\n" +
                    "	     where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                    "			and nv.nhanvien_ma_nhan_vien = banhang.hoadonbanhang_nguoi_tao\n" +
                    "			and nv.nhanvien_nguoi_quan_ly = '" +pManagerID +"'\n" +
                    "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                    "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                    "            chitiet.chitietdonbanhang_thue\n" +
                    "                \n" +
                    "        order by chitiet.chitietdonbanhang_ma_hang";
                }
                else{
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                    "		 chitiet.chitietdonbanhang_ma_hang as id, \n" +
                    "		 chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                    "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                    "            chitiet.chitietdonbanhang_thue as tax,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                    "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                    "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                    "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                    "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                    "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang, tb_nhanvien as nv\n" +
                    "	     where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                    "			and nv.nhanvien_ma_nhan_vien = banhang.hoadonbanhang_nguoi_tao\n" +
                    "			and nv.nhanvien_nguoi_quan_ly = '" +pManagerID +"'\n" +
                    "			and cast(banhang.hoadonbanhang_ngay_ban_hang as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'\n" +
                    "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                    "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                    "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                    "            chitiet.chitietdonbanhang_thue\n" +
                    "                \n" +
                    "        order by chitiet.chitietdonbanhang_ma_hang";
                }
            }
            else{
                if(datefinal.compareTo("") == 0){
                    sql = "select  ROW_NUMBER() OVER (ORDER BY chitietdonbanhang_ma_hang) as serial,"+ 
                        "       chitietdonbanhang_ma_hang as id, chitietdonbanhang_ten_san_pham as name, \n" +
                        "	chitietdonbanhang_don_vi_tinh as unit,\n" +
                        "	chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                        "	chitietdonbanhang_thue as tax,\n" +
                        "	chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                        "	sum(chitietdonbanhang_so_luong) as quantity,\n" +
                        "	sum(chitietdonbanhang_don_gia_sau_thue * chitietdonbanhang_so_luong) as amount,\n" +
                        "	sum(chitietdonbanhang_don_gia_sau_thue * chitietdonbanhang_giam_gia * chitietdonbanhang_so_luong/100) as discount,\n" +
                        "	sum(chitietdonbanhang_thanh_tien) as revenue \n" +
                        "from tb_chitietdonbanhang \n" +
                        "group by chitietdonbanhang_ma_hang, chitietdonbanhang_ten_san_pham, \n" +
                        "	chitietdonbanhang_don_vi_tinh,chitietdonbanhang_don_gia_sau_thue,\n" +
                        "	chitietdonbanhang_don_gia_truoc_thue,\n" +
                        "	chitietdonbanhang_thue\n" +
                        " \n" +
                        "order by chitietdonbanhang_ma_hang";
                }
                else
                    sql = "select ROW_NUMBER() OVER (ORDER BY chitiet.chitietdonbanhang_ma_hang) as serial, \n" +
                        "	     chitiet.chitietdonbanhang_ma_hang as id, \n" +
                        "	     chitiet.chitietdonbanhang_ten_san_pham as name, \n" +
                        "            chitiet.chitietdonbanhang_don_vi_tinh as unit,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_truoc_thue as preTax,\n" +
                        "            chitiet.chitietdonbanhang_thue as tax,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_sau_thue as afterTax,\n" +
                        "            sum(chitiet.chitietdonbanhang_so_luong) as quantity,\n" +
                        "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_so_luong) as amount,\n" +
                        "            sum(chitiet.chitietdonbanhang_don_gia_sau_thue * chitiet.chitietdonbanhang_giam_gia * chitiet.chitietdonbanhang_so_luong/100) as discount,\n" +
                        "            sum(chitiet.chitietdonbanhang_thanh_tien) as revenue \n" +
                        "        from tb_chitietdonbanhang as chitiet, tb_hoadonbanhang as banhang\n" +
                        "        where banhang.hoadonbanhang_ma_hoa_don = chitiet.chitietdonbanhang_ma_hoa_don\n" +
                        "			\n" +
                        "			and cast(banhang.hoadonbanhang_ngay_ban_hang as date) BETWEEN '"+datefinal+"' and '"+toDateFinal+"'\n" +
                        "        group by chitiet.chitietdonbanhang_ma_hang, chitiet.chitietdonbanhang_ten_san_pham, \n" +
                        "            chitiet.chitietdonbanhang_don_vi_tinh,chitiet.chitietdonbanhang_don_gia_sau_thue,\n" +
                        "            chitiet.chitietdonbanhang_don_gia_truoc_thue,\n" +
                        "            chitiet.chitietdonbanhang_thue\n" +
                        "                \n" +
                        "        order by chitiet.chitietdonbanhang_ma_hang";
            }
            
            courses = session.createSQLQuery(sql).addEntity(ReportSaleWithProduct.class).list();
            
            //courses = (List<ReportSaleWithProduct>)query.list();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        
        return courses;
    }
}
