/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.CalendarDAO;
import com.hp.dao.CalendarDAOImpl;
import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Calendar;
import com.hp.domain.PushInfo;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class CalendarAction extends ActionSupport{
    private CalendarDAO calendarDAO = new CalendarDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    
    private List<Calendar> calendarList = new ArrayList<Calendar>();

    public PushInfo pushInfo = new PushInfo();
    User user = new User();
    private String startDate;
    private String endDate;
    
    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();
    
    public String displayCalendars(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        calendarList = calendarDAO.getCalendarList();
        userListGiamDoc = userDAO.getListUser(2);
        
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
        
        pushInfo.setManagerID(request.getParameter("pushInfo.managerID"));
        pushInfo.setStaffID(request.getParameter("pushInfo.staffID"));
        pushInfo.setCustomerID(request.getParameter("pushInfo.customerID"));
        
        
        userListGiamDoc = userDAO.getListUser(2);
        userListStaff = staffDAO.getListUser(pushInfo.getManagerID());
        userListCustomer = customerDAO.getListCustomer(pushInfo.getStaffID());
        
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        Date start = null;
        Date end = null;
        try{
            if(!startDate.equals(""))
                start = df.parse(startDate);
            
            if(!endDate.equals("")){
                java.util.Calendar c = java.util.Calendar.getInstance();
                c.setTime(df.parse(endDate));
                c.add(java.util.Calendar.DAY_OF_MONTH, 1);
                
                end = df.parse(endDate);
            }
            
        }
        catch(Exception e){
            
        }
        
        System.err.println(start + " " + end);
        calendarList = calendarDAO.getCalendarList(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                start, end);
        
        return SUCCESS;
    }
    
    public String edit() throws IOException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        
        HttpSession session = request.getSession();
        
        String id = StringUtils.trimToEmpty(request.getParameter("id"));
        String contributor = StringUtils.trimToEmpty(request.getParameter("contributor"));
        String support = StringUtils.trimToEmpty(request.getParameter("support"));
        String mission = StringUtils.trimToEmpty(request.getParameter("mission"));
        String report = StringUtils.trimToEmpty(request.getParameter("report"));
        
        System.out.println("ID: "+id);
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("text/html; charset=UTF-8");
        response.getOutputStream().write("Cập nhật lịch công tác thành công".getBytes("UTF-8"));
        return null;
    }
    
    public List<Calendar> getCalendarList() {
        return calendarList;
    }

    public void setCalendarList(List<Calendar> calendarList) {
        this.calendarList = calendarList;
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
}
