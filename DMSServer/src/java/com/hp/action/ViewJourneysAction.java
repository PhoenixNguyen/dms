/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.ScheduleDAO;
import com.hp.dao.ScheduleDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Customer;
import com.hp.domain.PushInfo;
import com.hp.domain.RoadManagement;
import com.hp.domain.Schedule;
import com.hp.domain.User;
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
public class ViewJourneysAction extends ActionSupport implements ModelDriven{
    public Object getModel(){
        return pushInfo;
    }
    
    private UserDAO userDAO = new UserDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    private RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    
    public PushInfo pushInfo = new PushInfo();
    User user = new User();
    private List<Customer> listCustomer = new ArrayList();

    private List<List<RoadManagement>> listRoad = new ArrayList<List<RoadManagement>>();
    private List<Schedule> listSchedules = new ArrayList();

    private String date;

    private List<List<Customer>> listScheduleAndCustomer = new ArrayList<List<Customer>>();

    public List<List<Customer>> getListScheduleAndCustomer() {
        return listScheduleAndCustomer;
    }

    public void setListScheduleAndCustomer(List<List<Customer>> listScheduleAndCustomer) {
        this.listScheduleAndCustomer = listScheduleAndCustomer;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public List<Schedule> getListSchedules() {
        return listSchedules;
    }

    public void setListSchedules(List<Schedule> listSchedules) {
        this.listSchedules = listSchedules;
    }
    
    public List<List<RoadManagement>> getListRoad() {
        return listRoad;
    }

    public void setListRoad(List<List<RoadManagement>> listRoad) {
        this.listRoad = listRoad;
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

    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();
    
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

    public List<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    
    public String refresh(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        
//        if(user.getPermission() == 1)
            userListGiamDoc = userDAO.getListUser(2);
//        if(user.getPermission() == 2)
//            userListStaff = staffDAO.getListUser(user.getId());
//        
        return SUCCESS;
    }
    
    public String filterResult(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        pushInfo.setManagerID((String)session.getAttribute("giamdocId"));
        pushInfo.setStaffID((String)session.getAttribute("staffId"));
        pushInfo.setCustomerID((String)session.getAttribute("khachhangId"));
        
        userListGiamDoc = userDAO.getListUser(2);
        userListStaff = staffDAO.getListUser(pushInfo.getManagerID());
        userListCustomer = customerDAO.getListCustomer(pushInfo.getStaffID());
        
        listCustomer = customerDAO.loadCustomersWithLocations(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                pushInfo.getCustomerID());
        
        listRoad = roadManagementDAO.getRoad(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                pushInfo.getCustomerID(),
                        date);
        listSchedules = scheduleDAO.getSchedulesList(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                pushInfo.getCustomerID(), date);
        
        listScheduleAndCustomer = customerDAO.customerScheduleList(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                pushInfo.getCustomerID(), date);
        return SUCCESS;
    }
}
