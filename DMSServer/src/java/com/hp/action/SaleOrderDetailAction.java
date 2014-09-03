/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ValidateHandle;
import com.hp.dao.SaleOrderDAO;
import com.hp.dao.SaleOrderDAOImpl;
import com.hp.dao.SaleOrderDetailDAO;
import com.hp.dao.SaleOrderDetailDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.SaleOrder;
import com.hp.domain.SaleOrderDetail;
import com.hp.domain.TakeOrderDetail;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class SaleOrderDetailAction extends ActionSupport implements ModelDriven{
    @Override
    public Object getModel() {
        return saleOrderDetail;
    }
    
    
    private UserDAO userDAO = new UserDAOImpl();
    
    SaleOrder saleOrder = new SaleOrder();
    SaleOrderDetail saleOrderDetail = new SaleOrderDetail();

    SaleOrderDAO saleOrderDAO = new SaleOrderDAOImpl();
    SaleOrderDetailDAO saleOrderDetailDAO = new SaleOrderDetailDAOImpl();
    
    
    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public SaleOrderDetail getSaleOrderDetail() {
        return saleOrderDetail;
    }

    public void setSaleOrderDetail(SaleOrderDetail saleOrderDetail) {
        this.saleOrderDetail = saleOrderDetail;
    }
     
     public String editSaleOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_sod");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            saleOrderDetail = saleOrderDetailDAO.getSaleOrderDetail(id_pdct);
            return SUCCESS;
        }
        else 
            return INPUT;
        
//        String order_id = request.getParameter("id_take_order_detail");
//        System.out.println(order_id);
//        int st;
//        if(order_id ==null){
//            return INPUT;
//        }
//        st = Integer.parseInt(order_id);
//        takeOrderDetail = takeOrderDetailDAO.getTakeOrderDetail(st);
        
//        return SUCCESS;
    }
     
     public String updateSaleOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        System.out.println("OKto " + saleOrderDetail.getProductID());
        //takeOrder.setmEditer("0");
        
        saleOrderDetail.setPriceTotal((saleOrderDetail.getBeforeOrderPrice() - 
                saleOrderDetail.getBeforeOrderPrice()* saleOrderDetail.getDiscount()/100) * saleOrderDetail.getNumber());
        
        boolean status = saleOrderDetailDAO.update(saleOrderDetail);
        System.out.println(status);
        
        //UPdate TakeOrder
        List<SaleOrderDetail> list = new ArrayList<SaleOrderDetail>();
        
        list = saleOrderDetailDAO.getDetailSaleOrdersList(saleOrderDetail.getTakeOrderID());
        float priceTotal = 0;
        for(int i = 0; i < list.size(); i++){
            priceTotal += list.get(i).getPriceTotal();
        }
        
        saleOrder = saleOrderDAO.getSaleOrder(saleOrderDetail.getTakeOrderID());
        saleOrder.setBeforePrice(priceTotal);
        saleOrder.setAfterPrivate(priceTotal - priceTotal*saleOrder.getDiscount()/100);
        
        saleOrder.setOrderEditDate(Timestamp.valueOf(time));
        
        boolean st2 = saleOrderDAO.update(saleOrder);
        
        if(status){
            
            return SUCCESS;
        }
        else
            return INPUT;
        //takeOrdersList = takeOrderDAO.getTakeOrdersList();
        
        
    }
     
     public String deleteSaleOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String para =  request.getParameter("id_sod");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            saleOrderDetail = saleOrderDetailDAO.getSaleOrderDetail(id_pdct);
            boolean st = saleOrderDetailDAO.delete(saleOrderDetail);
            if(st){
                //UPdate TakeOrder
                List<SaleOrderDetail> list = new ArrayList<SaleOrderDetail>();

                list = saleOrderDetailDAO.getDetailSaleOrdersList(saleOrderDetail.getTakeOrderID());
                float priceTotal = 0;
                for(int i = 0; i < list.size(); i++){
                    priceTotal += list.get(i).getPriceTotal();
                }
        
                saleOrder = saleOrderDAO.getSaleOrder(saleOrderDetail.getTakeOrderID());
                saleOrder.setBeforePrice(priceTotal);
                saleOrder.setAfterPrivate(priceTotal - priceTotal*saleOrder.getDiscount()/100);
                boolean st2 = saleOrderDAO.update(saleOrder);
                return SUCCESS;
            }
            else
                return INPUT;
        }
        else 
            return INPUT;
    }
}
