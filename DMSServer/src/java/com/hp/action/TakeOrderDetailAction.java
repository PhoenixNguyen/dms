/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ValidateHandle;
import com.hp.dao.TakeOrderDAO;
import com.hp.dao.TakeOrderDAOImpl;
import com.hp.dao.TakeOrderDetailDAO;
import com.hp.dao.TakeOrderDetailDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
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
public class TakeOrderDetailAction extends ActionSupport implements ModelDriven{
    private UserDAO userDAO = new UserDAOImpl();
    
    private TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
    
    private List<TakeOrder> takeOrdersList = new ArrayList<TakeOrder>();
    private List<TakeOrderDetail> detailTakeOrdersList = new ArrayList<TakeOrderDetail>();

    public TakeOrderDetail takeOrderDetail = new TakeOrderDetail();

    TakeOrder takeOrder = new TakeOrder();

    TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        
    
    public TakeOrder getTakeOrder() {
        return takeOrder;
    }

    public void setTakeOrder(TakeOrder takeOrder) {
        this.takeOrder = takeOrder;
    }
    
    public TakeOrderDetail getTakeOrderDetail() {
        return takeOrderDetail;
    }

    public void setTakeOrderDetail(TakeOrderDetail takeOrderDetail) {
        this.takeOrderDetail = takeOrderDetail;
    }

    
    @Override
    public Object getModel() {
        return takeOrderDetail;
    }
    
    
    public List<TakeOrder> getTakeOrdersList() {
        return takeOrdersList;
    }

    public void setTakeOrdersList(List<TakeOrder> takeOrdersList) {
        this.takeOrdersList = takeOrdersList;
    }

    public List<TakeOrderDetail> getDetailTakeOrdersList() {
        return detailTakeOrdersList;
    }

    public void setDetailTakeOrdersList(List<TakeOrderDetail> detailTakeOrdersList) {
        this.detailTakeOrdersList = detailTakeOrdersList;
    }
    
    public String loadTakeOrders(){
        takeOrdersList = takeOrderDAO.getTakeOrdersList();
        return SUCCESS;
    }
    
    public String loadTakeOrdersDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String order_id = request.getParameter("id_takeorder");
        System.out.println(order_id);
        
        if(order_id == null)
            return INPUT;
        
        detailTakeOrdersList = takeOrderDetailDAO.getDetailTakeOrdersList(order_id);
        takeOrdersList = takeOrderDAO.getTakeOrdersList();
        return SUCCESS;
    }
    
    public String editTakeOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_to_d");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            takeOrderDetail = takeOrderDetailDAO.getTakeOrderDetail(id_pdct);
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
    
    public String updateTakeOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        System.out.println("OKto " + takeOrderDetail.getProductID());
        //takeOrder.setmEditer("0");
        
        takeOrderDetail.setPriceTotal((takeOrderDetail.getBeforeOrderPrice() - 
                takeOrderDetail.getBeforeOrderPrice()* takeOrderDetail.getDiscount()/100) * takeOrderDetail.getNumber());
        
        boolean status = takeOrderDetailDAO.update(takeOrderDetail);
        System.out.println(status);
        
        //UPdate TakeOrder
        List<TakeOrderDetail> list = new ArrayList<TakeOrderDetail>();
        
        list = takeOrderDetailDAO.getDetailTakeOrdersList(takeOrderDetail.getTakeOrderID());
        float priceTotal = 0;
        for(int i = 0; i < list.size(); i++){
            priceTotal += list.get(i).getPriceTotal();
        }
        
        takeOrder = takeOrderDAO.getTakeOrder(takeOrderDetail.getTakeOrderID());
        takeOrder.setBeforePrice(priceTotal);
        takeOrder.setAfterPrivate(priceTotal - priceTotal*takeOrder.getDiscount()/100);
        takeOrder.setOrderEditDate(Timestamp.valueOf(time));
        
        boolean st2 = takeOrderDAO.update(takeOrder);
        
        if(status){
            
            return SUCCESS;
        }
        else
            return INPUT;
        //takeOrdersList = takeOrderDAO.getTakeOrdersList();
        
        
    }
    
    public String deleteTakeOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String para =  request.getParameter("id_to_d");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            takeOrderDetail = takeOrderDetailDAO.getTakeOrderDetail(id_pdct);
            boolean st = takeOrderDetailDAO.delete(takeOrderDetail);
            if(st){
                //UPdate TakeOrder
                List<TakeOrderDetail> list = new ArrayList<TakeOrderDetail>();

                list = takeOrderDetailDAO.getDetailTakeOrdersList(takeOrderDetail.getTakeOrderID());
                float priceTotal = 0;
                for(int i = 0; i < list.size(); i++){
                    priceTotal += list.get(i).getPriceTotal();
                }
        
                takeOrder = takeOrderDAO.getTakeOrder(takeOrderDetail.getTakeOrderID());
                takeOrder.setBeforePrice(priceTotal);
                takeOrder.setAfterPrivate(priceTotal - priceTotal*takeOrder.getDiscount()/100);
                boolean st2 = takeOrderDAO.update(takeOrder);
                return SUCCESS;
            }
            else
                return INPUT;
        }
        else 
            return INPUT;
    }
    
}
