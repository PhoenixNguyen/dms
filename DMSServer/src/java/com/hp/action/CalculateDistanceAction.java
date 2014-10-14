/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.LocationDistance;
import com.hp.domain.PushInfo;
import com.hp.domain.RoadManagement;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class CalculateDistanceAction extends ActionSupport{
    public PushInfo pushInfo = new PushInfo();
    User user = new User();
    
    private UserDAO userDAO = new UserDAOImpl();
    private RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    
    private List<LocationDistance> locationDistanceList = new ArrayList<LocationDistance>();

    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();
    
    private String startDate;
    private String endDate;
    
    public String calculateDistance(){
    
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        userListGiamDoc = userDAO.getListUser(2);
        
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
        
        
        List<List<RoadManagement>> lists = new ArrayList<List<RoadManagement>>();
        lists = roadManagementDAO.getRoad(pushInfo.getManagerID(), pushInfo.getStaffID(), "", startDate, endDate);
        
        System.out.println("lists: " + lists.size());
        
        if(lists!=null && lists.size() > 0){
            for(int i = 0; i < lists.size(); i++){
                LocationDistance locationDistance = new LocationDistance();
                locationDistance.setStaff(staffDAO.loadStaff(lists.get(i).get(0).getMaNhanVien()));
                
                float distance = 0;
                float interval = 0;
                List<RoadManagement> list = lists.get(i);
                for(int j = 0; j < (list.size() - 1); j ++){
                    RoadManagement location1 = list.get(j);
                    RoadManagement location2 = list.get(j+1);
                    
                    distance += calculateDistance(location1.getViDo(), location1.getKinhDo(), location2.getViDo(), location2.getKinhDo());
                    interval += (location2.getThoiGian().getTime() - location1.getThoiGian().getTime());
                    
                    System.out.println("Y= " + list.get(j).getThoiGian() + " Time(" + j +"): " + interval);
                }
                
                locationDistance.setDistance(distance);
                locationDistance.setInterval(interval/(1000*60 *60*1f));
                
                locationDistanceList.add(locationDistance);
            }
        }
        
        return SUCCESS;
    }
    
    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
    public float calculateDistance(double userLat, double userLng,
      double venueLat, double venueLng) {

        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
          + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
          * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float)(AVERAGE_RADIUS_OF_EARTH * c);
    }

    public PushInfo getPushInfo() {
        return pushInfo;
    }

    public void setPushInfo(PushInfo pushInfo) {
        this.pushInfo = pushInfo;
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
    
    public List<LocationDistance> getLocationDistanceList() {
        return locationDistanceList;
    }

    public void setLocationDistanceList(List<LocationDistance> locationDistance) {
        this.locationDistanceList = locationDistance;
    }
}
