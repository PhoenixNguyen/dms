/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.StaffHistoryDAO;
import com.hp.dao.StaffHistoryDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Customer;
import com.hp.domain.PushInfo;
import com.hp.domain.StaffHistory;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class StaffHistoryAction extends ActionSupport implements ModelDriven{
     public Object getModel(){
        return pushInfo;
    }

    private UserDAO userDAO = new UserDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private StaffHistoryDAO staffHistoryDAO = new StaffHistoryDAOImpl();
    
    public PushInfo pushInfo = new PushInfo();
    User user = new User();

    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();

    private List<StaffHistory> listStaffHistory = new ArrayList<StaffHistory>();

    
    
    private String startDate;
    private String endDate;
    
    private List<Customer> customerList = new ArrayList<Customer>();

    private StaffHistory staffHistory = new StaffHistory();
    
    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<StaffHistory> getListStaffHistory() {
        return listStaffHistory;
    }

    public void setListStaffHistory(List<StaffHistory> listStaffHistory) {
        this.listStaffHistory = listStaffHistory;
    }
    
    public List<String> getUserListGiamDoc() {
        return userListGiamDoc;
    }

    public void setUserListGiamDoc(List<String> userListGiamDoc) {
        this.userListGiamDoc = userListGiamDoc;
    }

    public List<String> getUserListStaff() {
        return userListStaff;
    }

    public void setUserListStaff(List<String> userListStaff) {
        this.userListStaff = userListStaff;
    }

    public List<String> getUserListCustomer() {
        return userListCustomer;
    }

    public void setUserListCustomer(List<String> userListCustomer) {
        this.userListCustomer = userListCustomer;
    }
    
    public PushInfo getPushInfo() {
        return pushInfo;
    }

    public void setPushInfo(PushInfo pushInfo) {
        this.pushInfo = pushInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String refresh(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
//        if(user.getPermission() == 1)
            userListGiamDoc = userDAO.getListUser(2);
//        if(user.getPermission() == 2)
//            userListStaff = staffDAO.getListUser(user.getId());
        
        return SUCCESS;
    }
    
    public String filterResult(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        pushInfo.setManagerID((String)session.getAttribute("giamdocId"));
        pushInfo.setStaffID((String)session.getAttribute("staffId"));
        pushInfo.setCustomerID((String)session.getAttribute("khachhangId"));
        
        
        userListGiamDoc = userDAO.getListUser(2);
        userListStaff = staffDAO.getListUser(pushInfo.getManagerID());
        userListCustomer = customerDAO.getListCustomer(pushInfo.getStaffID());
        
        customerList = customerDAO.getListCustomer();
                
        listStaffHistory = staffHistoryDAO.getStaffHistoryList(pushInfo.getManagerID(), pushInfo.getStaffID(), pushInfo.getCustomerID(), startDate, endDate);
//        listSchedules = scheduleDAO.getSchedulesListForSchedules(pushInfo.getManagerID(), pushInfo.getStaffID(), 
//                pushInfo.getCustomerID(), startDate, endDate);
        
        return SUCCESS;
    }
}
