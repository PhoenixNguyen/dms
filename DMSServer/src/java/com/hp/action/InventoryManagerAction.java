/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.InventoryManagerDAO;
import com.hp.dao.InventoryManagerDAOImpl;
import com.hp.dao.InventoryManagerDetailDAO;
import com.hp.dao.InventoryManagerDetailDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.InventoryManager;
import com.hp.domain.InventoryManagerDetail;
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
public class InventoryManagerAction extends ActionSupport implements ModelDriven{
    InventoryManager inventoryManager = new InventoryManager();
    
    @Override
    public Object getModel() {
        return inventoryManager;
    }
    
    
    InventoryManagerDAO inventoryManagerDAO = new InventoryManagerDAOImpl();
    
    InventoryManagerDetail inventoryManagerDetail = new InventoryManagerDetail();

    
    InventoryManagerDetailDAO inventoryManagerDetailDAO = new InventoryManagerDetailDAOImpl();
    
    List<InventoryManager> inventoryManagerList = new ArrayList<InventoryManager>();
    List<InventoryManagerDetail> inventoryManagerDetailList = new ArrayList<InventoryManagerDetail>();

    private UserDAO userDAO = new UserDAOImpl();
    
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
    
    public String refresh(){
        inventoryManagerList = inventoryManagerDAO.getInventoryManagersList();
        return SUCCESS;
    }

    
    public List<InventoryManagerDetail> getInventoryManagerDetailList() {
        return inventoryManagerDetailList;
    }

    public void setInventoryManagerDetailList(List<InventoryManagerDetail> inventoryManagerDetailList) {
        this.inventoryManagerDetailList = inventoryManagerDetailList;
    }
    
    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public void setInventoryManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public List<InventoryManager> getInventoryManagerList() {
        return inventoryManagerList;
    }

    public void setInventoryManagerList(List<InventoryManager> inventoryManagerList) {
        this.inventoryManagerList = inventoryManagerList;
    }
    
    public InventoryManagerDetail getInventoryManagerDetail() {
        return inventoryManagerDetail;
    }

    public void setInventoryManagerDetail(InventoryManagerDetail inventoryManagerDetail) {
        this.inventoryManagerDetail = inventoryManagerDetail;
    }
    
    public String loadInventoryManagerDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String order_id = request.getParameter("id_inv");
        System.out.println(order_id);
        
        if(order_id == null)
            return INPUT;
        
        inventoryManager = inventoryManagerDAO.getInventoryManager(order_id);
        inventoryManagerDetailList = inventoryManagerDetailDAO.getInventoryManagerDetailList(order_id);
        return SUCCESS;
    }
    
    public String updateInventoryManager() throws UnsupportedEncodingException{
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
        
        System.out.println("OKto " + inventoryManager.getId() +" : " +inventoryManager.getCustomerAddress() + " " );//doc.getFileFileName()
              
//        TakeOrder t = new TakeOrder();
//        t.setMID("t1233imjtt");
//        t.setMCustomerID(null);
//        t.setMCreater(null);
//        t.setMEditer(null);
//        takeOrder.setAfterPrivate(takeOrder.getBeforePrice() - takeOrder.getBeforePrice() * takeOrder.getDiscount()/100);
        
        inventoryManager.setOrderEditDate(Timestamp.valueOf(time));        
        
        boolean status = inventoryManagerDAO.update(inventoryManager);
        System.out.println(status+" ________________________");
        if(status){
            return SUCCESS;
        }
        System.out.println(status+" ________________________");
        
        return INPUT;
    }
    
    public String deleteInventoryManager(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        selected = true;
                
        String para =  request.getParameter("id_inv");
        
        //int id_pdct = ValidateHandle.getInteger(para);
        if(para.compareTo("") != 0){
            inventoryManager = inventoryManagerDAO.getInventoryManager(para);
            
            boolean detailStatus = inventoryManagerDetailDAO.delete(inventoryManager.getId());
            if(detailStatus){
                deleteStatus = inventoryManagerDAO.delete(inventoryManager);
                
                inventoryManagerList = inventoryManagerDAO.getInventoryManagersList();
                return SUCCESS;
            }
            else
                return INPUT;
            
            
        }
        else 
            return INPUT;
    }
    
    public String searchInventory(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        inventoryManagerList = inventoryManagerDAO.getSearchInventoryList(para);
        
        return SUCCESS;
    }
}
