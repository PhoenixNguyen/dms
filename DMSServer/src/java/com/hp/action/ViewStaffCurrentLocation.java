/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.City;
import com.hp.common.HttpHelper;
import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Customer;
import com.hp.domain.LastLocation;
import com.hp.domain.RoadManagement;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class ViewStaffCurrentLocation extends ActionSupport{

    public String city;
    public String date_time = "";
    
    User user = new User();
    
    private UserDAO userDAO = new UserDAOImpl();
    private RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    
    private List<Customer> listCustomer = new ArrayList();
    
    List<RoadManagement> currLocations = new ArrayList<RoadManagement>();
    List<String> cities = new ArrayList<String>();

    List<LastLocation> lastLocations = new ArrayList<LastLocation>();

    public List<LastLocation> getLastLocations() {
        return lastLocations;
    }

    public void setLastLocations(List<LastLocation> lastLocations) {
        this.lastLocations = lastLocations;
    }
    
    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
    
    public String displayCurrLocation(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        return SUCCESS;
    }
    
     public String filterResult() throws ParseException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        cities = City.getAllCityOfVietNam();
        
        Date date = null;
        if(!date_time.equalsIgnoreCase("")){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            try{
            date = df.parse(date_time);
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("date: " + date);

        }
        
        currLocations = roadManagementDAO.findLocationByTime(date);
        
        List<RoadManagement> lastRoads = roadManagementDAO.findLocationByTime(null);
        
        if(currLocations != null && currLocations.size() > 0){
             //System.out.println("currLocations: " + currLocations.size());
            for(RoadManagement rm : currLocations){
                System.out.println("currLocations: " + rm.getMaNhanVien() + " " + rm.getThoiGian());
                for(RoadManagement last : lastRoads){
                    if(last.getMaNhanVien().equalsIgnoreCase(rm.getMaNhanVien())){
                        lastLocations.add(new LastLocation(rm.getMaNhanVien(), getAddress(rm), getAddress(last), last.getThoiGian()));
                        break;
                     }
                }
            }
        }
        
        listCustomer = customerDAO.loadCustomersWithLocations();
        return SUCCESS;
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
                   //System.out.println("CityName _______________________________ --->" + City + "");

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
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
    
    public List<RoadManagement> getCurrLocations() {
        return currLocations;
    }

    public void setCurrLocations(List<RoadManagement> currLocations) {
        this.currLocations = currLocations;
    }
    
    public List<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
    
}
