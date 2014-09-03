/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.SaleOrderDAO;
import com.hp.dao.SaleOrderDAOImpl;
import com.hp.dao.SaleOrderDetailDAO;
import com.hp.dao.SaleOrderDetailDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.SaleOrder;
import com.hp.domain.SaleOrderDetail;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.UnsupportedEncodingException;
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
public class SaleOrderAction extends ActionSupport implements ModelDriven{
    private UserDAO userDAO = new UserDAOImpl();
    
    SaleOrder saleOrder = new SaleOrder();

    SaleOrderDAO saleOrderDAO = new SaleOrderDAOImpl();
    SaleOrderDetailDAO saleOrderDetailDAO = new SaleOrderDetailDAOImpl();
    
    List<SaleOrderDetail> saleOrderDetailList = new ArrayList<SaleOrderDetail>();
    List<SaleOrder> saleOrderList = new ArrayList<SaleOrder>();

    public List<SaleOrder> getSaleOrderList() {
        return saleOrderList;
    }

    public void setSaleOrderList(List<SaleOrder> saleOrderList) {
        this.saleOrderList = saleOrderList;
    }
    
    public Object getModel(){
        return saleOrder;
    }
    
    private boolean deleteStatus;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }
    
    
    public SaleOrder getSaleOrder() {
        return saleOrder;
    }

    public void setSaleOrder(SaleOrder saleOrder) {
        this.saleOrder = saleOrder;
    }

    public List<SaleOrderDetail> getSaleOrderDetailList() {
        return saleOrderDetailList;
    }

    public void setSaleOrderDetailList(List<SaleOrderDetail> saleOrderDetailList) {
        this.saleOrderDetailList = saleOrderDetailList;
    }
    
    public String loadSaleOrders(){
        saleOrderList = saleOrderDAO.getSaleOrdersList();
        return SUCCESS;
    }
    
    public String loadSaleOrderDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String order_id = request.getParameter("id_so");
        System.out.println(order_id);
        
        if(order_id == null)
            return INPUT;
        
        saleOrder = saleOrderDAO.getSaleOrder(order_id);
        saleOrderDetailList = saleOrderDetailDAO.getDetailSaleOrdersList(order_id);
        return SUCCESS;
    }
    
    public String updateSaleOrder() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF8");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        System.out.println("OKto " + saleOrder.getId() +" : " +saleOrder.getCustomerAddress() );//doc.getFileFileName()
              
//        TakeOrder t = new TakeOrder();
//        t.setMID("t1233imjtt");
//        t.setMCustomerID(null);
//        t.setMCreater(null);
//        t.setMEditer(null);
        if(saleOrder.getDiscount() == null)
            saleOrder.setDiscount(0f);
        
        saleOrder.setAfterPrivate(saleOrder.getBeforePrice() - saleOrder.getBeforePrice() * saleOrder.getDiscount()/100);
        saleOrder.setOrderEditDate(Timestamp.valueOf(time));
        
        boolean status = saleOrderDAO.update(saleOrder);// update(takeOrder);
        System.out.println(status+" ________________________");
        if(status){
            return SUCCESS;
        }
        System.out.println(status+" ________________________");
        
        return INPUT;
    }
    
    public String deleteSaleOrder(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        selected = true;
                
        String para =  request.getParameter("id_so");
        
        //int id_pdct = ValidateHandle.getInteger(para);
        if(para.compareTo("") != 0){
            saleOrder = saleOrderDAO.getSaleOrder(para);
            
            boolean detailStatus = saleOrderDetailDAO.delete(saleOrder.getId());
            if(detailStatus){
                deleteStatus = saleOrderDAO.delete(saleOrder);
                
                saleOrderList = saleOrderDAO.getSaleOrdersList();
                return SUCCESS;
            }
            else
                return INPUT;
            
            
        }
        else 
            return INPUT;
    }
    
    public String searchSale(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        saleOrderList = saleOrderDAO.getSearchSaleList(para);
        
        return SUCCESS;
    }
}
