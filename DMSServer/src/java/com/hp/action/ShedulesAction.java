/*
 * To change this template, choose Tools | Templates
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
import com.hp.domain.RoadManagement;
import com.hp.domain.Customer;
import com.hp.domain.PushInfo;
import com.hp.domain.Schedule;
import com.hp.domain.User;
import com.hp.excelhandle.GetData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
/**
 *
 * @author HP
 */
public class ShedulesAction extends ActionSupport implements ModelDriven{
    
    
    private List<Customer> listCustomer = new ArrayList();
    private List<Schedule> listSchedules = new ArrayList();
    
    //Get Road for each customer
    private RoadManagementDAO mRoadManagementDAO = new RoadManagementDAOImpl();
    private List<List<RoadManagement>> listRoad = new ArrayList<List<RoadManagement>>();

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    
    private String data = "Afghanistan, Zimbabwe, India, United States, Germany, China, Israel";

    
    private UserDAO userDAO = new UserDAOImpl();
    private List<String> userListGiamDoc = new ArrayList<String>();
    private User user = new User();
    
    private StaffDAO staffDAO = new StaffDAOImpl();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();
    
    private String giamdocId;
    private String nhanvienId;
    private String khachhangId;

    public PushInfo pushInfo = new PushInfo();
    
    private String date;
    private String toDate;
    
    @Override
    public Object getModel() {
        return pushInfo;
    }
    
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public PushInfo getPushInfo() {
        return pushInfo;
    }

    public void setPushInfo(PushInfo pushInfo) {
        this.pushInfo = pushInfo;
    }
    private ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
    
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

    public String getKhachhangId() {
        return khachhangId;
    }

    public void setKhachhangId(String khachhangId) {
        this.khachhangId = khachhangId;
    }
    
    public List<String> getUserListCustomer() {
        return userListCustomer;
    }

    public void setUserListCustomer(List<String> userListCustomer) {
        this.userListCustomer = userListCustomer;
    }
    
    public String getNhanvienId() {
        return nhanvienId;
    }

    public void setNhanvienId(String nhanvienId) {
        this.nhanvienId = nhanvienId;
    }

   
    

    public String getGiamdocId() {
        return giamdocId;
    }

    public void setGiamdocId(String giamdocId) {
        this.giamdocId = giamdocId;
    }
    public List<String> getUserListStaff() {
        return userListStaff;
    }

    public void setUserListStaff(List<String> userListStaff) {
        this.userListStaff = userListStaff;
    }
    
    public List<String> getUserListGiamDoc() {
        return userListGiamDoc;
    }

    public void setUserListGiamDoc(List<String> userListGiamDoc) {
        this.userListGiamDoc = userListGiamDoc;
    }
    
 
    
    public List<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
        
    public String showMap(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //Lay ve giam doc
        userListGiamDoc = userDAO.getListUser(2);
       System.out.println(" GD: ");
        System.out.println(" GD: "+getGiamdocId()+" vs "+session.getAttribute("giamdocId3")
                +", STaff: "+ getNhanvienId() +" vs " + session.getAttribute("staffId3")); 
        
        //Lay ve nhan vien cua giam doc
        if(getGiamdocId() == null && getNhanvienId() == null){
            //Tat ca nhan vien
            userListStaff = staffDAO.getListUser(null);
            //tat ca khach hang
            if(session.getAttribute("giamdocId3") != null || session.getAttribute("staffId3") != null){
                System.out.print(" Get something "); 
                //setGiamdocId((String)session.getAttribute("giamdocId"));
                listSchedules = scheduleDAO.getSchedulesListForSchedules((String)session.getAttribute("giamdocId3"),
                        (String)session.getAttribute("staffId3"), date, toDate);
                listCustomer = customerDAO.loadCustomersWithLocationsForSchedule((String)session.getAttribute("giamdocId3"),
                        (String)session.getAttribute("staffId3"));

                pushInfo.setManagerID((String)session.getAttribute("giamdocId3"));
                pushInfo.setStaffID((String)session.getAttribute("staffId3"));
                
                //get list object id
                userListStaff = staffDAO.getListUser((String)session.getAttribute("giamdocId3"));
             }else{
//                listSchedules = scheduleDAO.getSchedulesListForSchedules(null, null, date, toDate);
//                listCustomer = customerDAO.loadCustomersWithLocationsForSchedule();
                session.setAttribute("staffId3", null);
                session.setAttribute("giamdocId3", null);
                
//                pushInfo.setManagerID((String)session.getAttribute("giamdocId3"));
//                pushInfo.setStaffID((String)session.getAttribute("staffId3"));
            }
        }
        //AJAX
        else if(getGiamdocId() != null){
            userListStaff = staffDAO.getListUser(giamdocId);
            session.setAttribute("staffId3", null);
            session.setAttribute("giamdocId3", giamdocId);
            if(giamdocId.compareTo("nullid") == 0){
                session.setAttribute("giamdocId3", null);
                System.out.println("giamdocid: " + session.getAttribute("giamdocId3"));
            }
            setGiamdocId(null);
            System.out.print(" filter!! "); 
            return SUCCESS;
        }
        else
        if(getNhanvienId()!= null){
            session.setAttribute("staffId3", nhanvienId);
            if(nhanvienId.compareTo("nullid") == 0){
                session.setAttribute("staffId3", null);
                System.out.println("staffId: " + session.getAttribute("staffId3"));
            }
            
            //Reset
            
        }
        
         
        
        System.out.print("Load Customer into map !!!!!!!!!!!!!!!!!!!!!!");
        
        return SUCCESS;
    }
    
    
}
