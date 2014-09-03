/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hp.action;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.RoadManagement;
import com.hp.domain.Customer;
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
public class ShowRoadAction extends ActionSupport{
    
    
    private List<Customer> listCustomer = new ArrayList();

    //Get Road for each customer
    private RoadManagementDAO mRoadManagementDAO = new RoadManagementDAOImpl();
    private List<RoadManagement> listRoad = new ArrayList();

    private CustomerDAO customerDAO = new CustomerDAOImpl();
    
    private String data = "Afghanistan, Zimbabwe, India, United States, Germany, China, Israel";

    
    private UserDAO userDAO = new UserDAOImpl();
    private List<String> userListGiamDoc = new ArrayList<String>();
    private User user = new User();
    
    private StaffDAO staffDAO = new StaffDAOImpl();
    private List<String> userListStaff = new ArrayList<String>();

    private String giamdocId;
    private String nhanvienId;

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
    
 
    public List<RoadManagement> getListRoad() {
        return listRoad;
    }

    public void setListRoad(List<RoadManagement> listRoad) {
        this.listRoad = listRoad;
    }
    
    public List<Customer> getListCustomer() {
        return listCustomer;
    }

    public void setListCustomer(List<Customer> listCustomer) {
        this.listCustomer = listCustomer;
    }
            
    public String showRoad(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Lay ve giam doc
        userListGiamDoc = userDAO.getListUser(2);
       System.out.println(" GD: ");
        System.out.println(" GD: "+getGiamdocId()+" STaff: "+ getNhanvienId()); 
        
        //Lay ve nhan vien cua giam doc
        
        if(getGiamdocId() == null && getNhanvienId() == null){
            //Tat ca nhan vien
            userListStaff = staffDAO.getListUser(null);
            //tat ca khach hang
            if(session.getAttribute("giamdocId") != null || session.getAttribute("staffId") != null){
                System.out.print(" Get something "); 
                //setGiamdocId((String)session.getAttribute("giamdocId"));
                listCustomer = customerDAO.loadCustomersWithLocations((String)session.getAttribute("giamdocId"),
                        (String)session.getAttribute("staffId"), null);

             }else{
                listCustomer = customerDAO.loadCustomersWithLocations();
            }
        }
        //AJAX
        else if(getGiamdocId() != null){
            userListStaff = staffDAO.getListUser(giamdocId);
            
            session.setAttribute("giamdocId", giamdocId);
            if(giamdocId.compareTo("nullid") == 0){
                session.setAttribute("giamdocId", null);
                System.out.println("giamdocid: " + session.getAttribute("giamdocId"));
            }
            setGiamdocId(null);
            System.out.print(" filter!! "); 
            return SUCCESS;
        }
        else
        if(getNhanvienId()!= null){
            session.setAttribute("staffId", nhanvienId);
            if(nhanvienId.compareTo("nullid") == 0){
                session.setAttribute("staffId", null);
                System.out.println("staffId: " + session.getAttribute("staffId"));
            }
            
            //Reset
            
        }
        
         
        
        System.out.print("Load Customer into map !!!!!!!!!!!!!!!!!!!!!!");
        
        return SUCCESS;
    }
 
    
}
