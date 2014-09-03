/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ValidateHandle;
import com.hp.dao.AnnouncementDAO;
import com.hp.dao.AnnouncementDAOImpl;
import com.hp.dao.HistoryDAO;
import com.hp.dao.HistoryDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Announcement;
import com.hp.domain.History;
import com.hp.domain.User;
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
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class SystemManagerAction extends ActionSupport implements ModelDriven{
    public Object getModel(){
        return user;
    }
    
   
    private UserDAO userDAO = new UserDAOImpl();
    private AnnouncementDAO announcementDAO = new AnnouncementDAOImpl();
    private HistoryDAO historyDAO = new HistoryDAOImpl();
    
    User user = new User();

    private List<User> userList = new ArrayList<User>();

    private String new_password;
    private boolean changePWStatus;
    private boolean deleteStatus;
    private boolean selected;

    private String notify;

    private Announcement announcement = new Announcement();

    private List<String> userStringList = new ArrayList<String>();

    private String userID;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    private List<History> historyList = new ArrayList<History>();
    private History history = new History();

    public List<History> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<History> historyList) {
        this.historyList = historyList;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
    
    public List<String> getUserStringList() {
        return userStringList;
    }

    public void setUserStringList(List<String> userStringList) {
        this.userStringList = userStringList;
    }
    
    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }
    
    
    
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
    
    public boolean isChangePWStatus() {
        return changePWStatus;
    }

    public void setChangePWStatus(boolean changePWStatus) {
        this.changePWStatus = changePWStatus;
    }
    
    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
    
    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
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
        
        userStringList = userDAO.getListUser();
        
        userList = userDAO.getUserList();
        return SUCCESS;
    }
    
    public String adminDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String para =  request.getParameter("id_admin");
        
        int id_cus = ValidateHandle.getInteger(para);
        if(id_cus > -1){
            user = userDAO.getUser(id_cus);
            return SUCCESS;
        }
        else
            return INPUT;
    }
    
    public String changePassword(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        if(new_password == null){
            return INPUT;
        }
        
        changePWStatus = true;
        System.out.println(new_password);
        user.setPw(new_password);
        
        changePWStatus = userDAO.updateUser(user);
        if(changePWStatus){
            session.setAttribute("user_password", new_password);
            session.setAttribute("USER", userDAO.getUser(user.getId()));
            return SUCCESS;
        }
        else
            return INPUT;
    }
    
    public String updateAdmin(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        
        changePWStatus = userDAO.updateUser(user);
        if(changePWStatus){
            user = userDAO.getUser(user.getStt());
            return SUCCESS;
        }
        else
            return INPUT;
    }

    public String saveUser(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        user.setNgayThamGia(new Date());
        if(userDAO.saveUser(user)){
            user = userDAO.getUser(user.getStt());
            return SUCCESS;
        }
        else
            return INPUT;
    }
    public String deleteAdmin(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        selected = true;
        deleteStatus = false;
        
        String para =  request.getParameter("id_admin");
        
        int id_cus = ValidateHandle.getInteger(para);
        if(id_cus > -1){
            user = userDAO.getUser(id_cus);
            
            //Do not delete supermanager
            if(user.getPermission() != 1){
                deleteStatus = userDAO.deleteUser(user);
                if(deleteStatus){
                    userList = userDAO.getUserList();
                    return SUCCESS;
                }
                else
                    return INPUT;
            }
            else
                return INPUT;
        }
        else
            return INPUT;
        
        
    }
    
    public String updateNotify(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        user = userDAO.getUser((String)session.getAttribute("user_name"));
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        
        announcement.setCreater(user.getId());
        announcement.setContent(notify);
        announcement.setDate(Timestamp.valueOf(sdf2.format(cal.getTime())));
        announcement.setStatus(true);
        
        announcementDAO.updateStatus();
        if(announcementDAO.saveOrUpdate(announcement)){
            
            session.setAttribute("announcement", announcement);
            return SUCCESS;
        }
        else
            return INPUT;
    }
    
    public String displayHistory(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        historyList = historyDAO.getHistoryList(userID);
        userStringList = userDAO.getListUser();
        
        return SUCCESS;
    }
}


