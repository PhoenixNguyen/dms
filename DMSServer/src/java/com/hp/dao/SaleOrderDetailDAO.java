/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.ReportSaleWithProduct;
import com.hp.domain.SaleOrderDetail;
import java.util.List;

/**
 *
 * @author HP
 */
public interface SaleOrderDetailDAO {
    public boolean saveOrUpdate(SaleOrderDetail pSaleOrder);
    
    //GET
    public List<SaleOrderDetail> getDetailSaleOrdersList(String pID);
    public SaleOrderDetail getSaleOrderDetail(int pSaleOrderDetail);
    public boolean update(SaleOrderDetail pSaleOrderDetail);
    
    //delete
    public boolean delete(SaleOrderDetail pSaleOrderDetail);
    //delete where order id
    public boolean delete(String pID);
    
    //Get report Sale order with products
    public List<ReportSaleWithProduct> getProductReportList(String pManagerID, String pStaff, String pCustomer, String pDate, String pToDate);
}
