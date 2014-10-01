/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.HttpHelper;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.InterruptedTime;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class InterruptedTimeAction extends ActionSupport{
    public static long INTERRUPTED_TIME = 60*60*1000; //1h
    
    private UserDAO userDAO = new UserDAOImpl();
    private RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    
    public PushInfo pushInfo = new PushInfo();
    User user = new User();
    
    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();
    
    private String startDate;
    private String endDate;
    
    private List<InterruptedTime> interruptedTimeList = new ArrayList<InterruptedTime>();

    public String loadPage(){
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
    
    public String discoverInterruption(){
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
        
        if(lists != null && lists.size() > 0)
            for(int i = 0; i < lists.size(); i++){
                List<RoadManagement> list = lists.get(i);
                for(int j = 0; j < list.size(); j++){
                    if(list.size() > (j+1)){
                        RoadManagement last = list.get(j);
                        RoadManagement updated = list.get(j+1);
                        
                        long range = updated.getThoiGian().getTime() - last.getThoiGian().getTime();
                        //System.out.println("range " + range);
                        if(range > INTERRUPTED_TIME){
                            //getAddress(last);
                            interruptedTimeList.add(new InterruptedTime(last, updated, range/(1000*60 *60), "", ""));
                        }
                    }
                }
            }
        
        System.out.println("interruptedTimeList: " + interruptedTimeList.size());
        return SUCCESS;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    public List<InterruptedTime> getInterruptedTimeList() {
        return interruptedTimeList;
    }

    public void setInterruptedTimeList(List<InterruptedTime> interruptedTimeList) {
        this.interruptedTimeList = interruptedTimeList;
    }

    private String getAddress(RoadManagement last) {
        String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
		        + last.getViDo() + "," + last.getKinhDo() + "&sensor=false";
            JSONObject jsonObj;
            String City = "";
            String address = "";
         try {

          jsonObj = new JSONObject(HttpHelper.makeRequest(url));

          String Status = jsonObj.getString("status");
          if (Status.equalsIgnoreCase("OK")) {
                   JSONArray Results = jsonObj.getJSONArray("results");
                   JSONObject zero = Results.getJSONObject(0);

                   address = zero.getString("formatted_address").toString();

                   String[] long_name = address.split(",");

                   int number = long_name.length - 2;
                   City = long_name[number];
                   System.out.println("CityName _______________________________ --->" + City + "");

                   //Toast.makeText(this, "CityName: " + City, Toast.LENGTH_SHORT).show();

                   if (!address.equals("")) {
                       return address;
                    //finish_service();
                   }
          }

         } catch (JSONException e) {
             e.printStackTrace();
             return "";
         }
         return "";
    }
}
