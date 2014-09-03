/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ValidateHandle;
import com.hp.dao.InventoryManagerDAO;
import com.hp.dao.InventoryManagerDAOImpl;
import com.hp.dao.InventoryManagerDetailDAO;
import com.hp.dao.InventoryManagerDetailDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.InventoryManager;
import com.hp.domain.InventoryManagerDetail;
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
public class InventoryManagerDetailAction extends ActionSupport implements ModelDriven{
    @Override
    public Object getModel() {
        return inventoryManagerDetail;
    }
    
    private UserDAO userDAO = new UserDAOImpl();
    InventoryManagerDetail inventoryManagerDetail = new InventoryManagerDetail();
    InventoryManagerDetailDAO inventoryManagerDetailDAO = new InventoryManagerDetailDAOImpl();
    
    InventoryManager inventoryManager = new InventoryManager();
    InventoryManagerDAO inventoryManagerDAO = new InventoryManagerDAOImpl();
    
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }
    
    public InventoryManagerDetail getInventoryManagerDetail() {
        return inventoryManagerDetail;
    }

    public void setInventoryManagerDetail(InventoryManagerDetail inventoryManagerDetail) {
        this.inventoryManagerDetail = inventoryManagerDetail;
    }
    
    public String editInventoryManagerDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_invdt");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            inventoryManagerDetail = inventoryManagerDetailDAO.getInventoryManagerDetail(id_pdct);
            return SUCCESS;
        }
        else 
            return INPUT;
        
    }
    
    public String updateInventoryManagerDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        System.out.println("OKto " + inventoryManagerDetail.getProductID());
        //takeOrder.setmEditer("0"); 
        
        inventoryManagerDetail.setPriceTotal(inventoryManagerDetail.getBeforeOrderPrice() 
                 * inventoryManagerDetail.getNumber());
        
        boolean status = inventoryManagerDetailDAO.update(inventoryManagerDetail);
        System.out.println(status);
        
        //UPdate TakeOrder
        List<InventoryManagerDetail> list = new ArrayList<InventoryManagerDetail>();
        
        list = inventoryManagerDetailDAO.getInventoryManagerDetailList(inventoryManagerDetail.getTakeOrderID());
        float priceTotal = 0;
        for(int i = 0; i < list.size(); i++){
            priceTotal += list.get(i).getPriceTotal();
        }
        
        inventoryManager = inventoryManagerDAO.getInventoryManager(inventoryManagerDetail.getTakeOrderID());
        inventoryManager.setBeforePrice(priceTotal);
        inventoryManager.setAfterPrivate(priceTotal);
        
        inventoryManager.setOrderEditDate(Timestamp.valueOf(time));        
        
        boolean st2 = inventoryManagerDAO.update(inventoryManager);
        
        if(status){
            
            return SUCCESS;
        }
        else
            return INPUT;
        //takeOrdersList = takeOrderDAO.getTakeOrdersList();
        
        
    }
    public String deleteInventoryManagerDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String para =  request.getParameter("id_invdt");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            inventoryManagerDetail = inventoryManagerDetailDAO.getInventoryManagerDetail(id_pdct);
            boolean st = inventoryManagerDetailDAO.delete(inventoryManagerDetail);
            if(st){
                //UPdate TakeOrder
                List<InventoryManagerDetail> list = new ArrayList<InventoryManagerDetail>();

                list = inventoryManagerDetailDAO.getInventoryManagerDetailList(inventoryManagerDetail.getTakeOrderID());
                float priceTotal = 0;
                for(int i = 0; i < list.size(); i++){
                    priceTotal += list.get(i).getPriceTotal();
                }
        
                inventoryManager = inventoryManagerDAO.getInventoryManager(inventoryManagerDetail.getTakeOrderID());
                inventoryManager.setBeforePrice(priceTotal);
                inventoryManager.setAfterPrivate(priceTotal);
                boolean st2 = inventoryManagerDAO.update(inventoryManager);
                return SUCCESS;
            }
            else
                return INPUT;
        }
        else 
            return INPUT;
    }
}
