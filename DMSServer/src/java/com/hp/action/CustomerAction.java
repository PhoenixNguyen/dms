/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ConfigFile;
import com.hp.common.ValidateHandle;
import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Customer;
import com.hp.domain.Demo;
import com.hp.domain.Demo2;
import com.hp.domain.Document;
import com.hp.domain.Staff;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
//import com.opensymphony.xwork2.util.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.ServletActionContext;
import org.hibernate.validator.Valid;

/**
 *
 * @author HP
 */
public class CustomerAction extends ActionSupport implements ModelDriven{
    
    private UserDAO userDAO = new UserDAOImpl();
    //Init Customer
    //private Customer customer = new Customer();
    
    //Init customerDAO
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
        
    private String customerID = new String();
    private int customerSTT;
    public Demo demo = new Demo();
    
    @Valid
    public Customer customer = new Customer();
    public Customer customer2 = new Customer();
    public Staff st = new Staff();
//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }
    
    @Valid
    private Document document = new Document();
    private List<Customer> customersList = new ArrayList<Customer>();

    private int customersTotal;

    private List<String> staffsList = new ArrayList<String>();

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
    
    public List<String> getStaffsList() {
        return staffsList;
    }

    public void setStaffsList(List<String> staffsList) {
        this.staffsList = staffsList;
    }
    
    public int getCustomersTotal() {
        return customersTotal;
    }

    public void setCustomersTotal(int customersTotal) {
        this.customersTotal = customersTotal;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public List<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(List<Customer> customersList) {
        this.customersList = customersList;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    @Override
    public Object getModel() {
        return customer;
    }
    
    public String saveFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        staffsList = staffDAO.getListUser(null);
        
        String saveName = document.getFileFileName();
        try {
            
            System.out.println(document.getFileContentType());
            String filePath = ServletActionContext.getServletContext().getRealPath("/db_inputs/");
            System.out.println("Server path:" + filePath);
            
            File fileToCreate = new File(filePath, saveName);
            FileUtils.copyFile(document.getFile(), fileToCreate);
            
            //set name file
            session.setAttribute("upload-name-file", saveName);
            
        } catch (IOException ex) {
            Logger.getLogger(CustomerAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
        return SUCCESS;
    }
    //access Excel files and import them into database
    public String addCustomerFromExcelFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String saveName = (String)session.getAttribute("upload-name-file");
        System.out.println("Get Attribute file name: "+saveName);
        if(saveName == null)
            return SUCCESS;
        
        int total = 0;
        //Import data
        try {
            String fileInput = ServletActionContext.getServletContext().getRealPath("/db_inputs/"+saveName+"/");
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fileInput));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getLastRowNum() + 1; //getPhysicalNumberOfRows();
            System.out.println("ROWs number" + rows);
            
            int cols = 0; // No of columns (max)
            int temp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    temp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(temp > cols) 
                        cols = temp;
                }
            }

            for(int i = 1; i < rows; i++){
                
                row = sheet.getRow(i);
                System.out.println("__ Rows: " +(i+1));
                if(row != null){
                    
                    System.out.println("__ Row: " +(i+1)+" ,Cell number: " + row.getPhysicalNumberOfCells());
                    //If the customer id null
                    if(row.getCell(1) == null ||
                            row.getCell(1).getStringCellValue().compareTo("") == 0

                            ){
                        continue;   
                    }
                    
                    //Init Customer Object
                    Customer customer = new Customer();
//                    customer.setmStt((int)row.getCell(tmp++).getNumericCellValue());
                    
                    try{
                        if(row.getCell(1) != null)
                            customer.setMaDoiTuong(row.getCell(1).getStringCellValue());
                        if(row.getCell(2) != null)
                            customer.setDoiTuong(row.getCell(2).getStringCellValue());
                        if(row.getCell(3) != null)
                            customer.setTinhThanh(row.getCell(3).getStringCellValue());
                        if(row.getCell(4) != null)
                            customer.setX(row.getCell(4).getStringCellValue());
                        if(row.getCell(5) != null)
                            customer.setDiaChi(row.getCell(5).getStringCellValue());
                        if(row.getCell(6) != null)
                            customer.setTuyenBanHangThu(row.getCell(6).getStringCellValue());

                        if(row.getCell(7) != null)
                            customer.setDienThoai(row.getCell(7).getStringCellValue());
                        if(row.getCell(8) != null)
                            customer.setFax(row.getCell(8).getStringCellValue());
                        if(row.getCell(9) != null)
                            customer.setMaNhanVien(row.getCell(9).getStringCellValue());

                        if(row.getCell(10) != null)
                            customer.setGhiChu(row.getCell(10).getStringCellValue());
                        
                        
                        if(row.getCell(11) != null){
                            if(row.getCell(11).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                customer.setCoordinateX(row.getCell(11).getNumericCellValue());
                            else
                                customer.setCoordinateX(Double.parseDouble(row.getCell(12).getStringCellValue()));
                        }
                        
                        if(row.getCell(12) != null){
                            if(row.getCell(12).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                customer.setCoordinateY(row.getCell(12).getNumericCellValue());
                            else
                                customer.setCoordinateY(Double.parseDouble(row.getCell(12).getStringCellValue()));
                        }
                        
                        
                        //Add to database
                        if(customerDAO.saveOrUpdate(customer)){
                            System.out.println("Add Object " + i);
                            total++;
                            customersTotal = total;
                        }
                        else
                            continue;
                    }
                    catch(Exception e){
                        e.printStackTrace();
                        continue;
                    }
                    
                    
                }
            }
            
        } catch(Exception ioe) {
            ioe.printStackTrace();
            return SUCCESS;
        }
        customersTotal = total;
        return SUCCESS;
    }
    
    public String displayCustomers(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        customersList = customerDAO.getListCustomer();
        staffsList = staffDAO.getListUser(null);
        return SUCCESS;
    }


    public Demo getDemo() {
        return demo;
    }

    public void setDemo(Demo demo) {
        this.demo = demo;
    }
    
    public int getCustomerSTT() {
        return customerSTT;
    }

    public void setCustomerSTT(int customerSTT) {
        this.customerSTT = customerSTT;
    }
    
    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
    
    public String editCustomer() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF8");
        staffsList = staffDAO.getListUser(null);
        
        String stt = request.getParameter("id_cus");
        int id_cus = ValidateHandle.getInteger(stt);
        if(id_cus > -1){
            customersList = customerDAO.getListCustomer();
            customer = customerDAO.loadCustomer(id_cus);
            return SUCCESS;
        }
        else
            return INPUT;
        
        
            
    }
    
    public String updateCustomer() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF8");
        staffsList = staffDAO.getListUser(null);
        
        String test = request.getParameter("mTinhThanh");
        String name  = customer.getDienThoai();
        
        String pw = demo.getmDienThoai();
        Float y = demo.getmYCoordinates();
   
        System.out.println("__" + pw + "__ " +y +" PARA: " + test);
        System.out.println("__" + name);
        
        // NEW
        if(customer.getStt() <= 0){
            customer.setCoordinateX(0d);
            customer.setCoordinateY(0d);
            boolean status = customerDAO.saveOrUpdate(customer);
            customersList = customerDAO.getListCustomer();
            
            if(status)
                return SUCCESS;
            else
                return INPUT;
        }     
        
        //UPDATE
        boolean status = customerDAO.update(customer);
        customersList = customerDAO.getListCustomer();
        
        if(status)
            return SUCCESS;
        else
            return INPUT;
    }
    
    Demo2 demo2 = new Demo2();

    public Demo2 getDemo2() {
        return demo2;
    }

    public void setDemo2(Demo2 demo2) {
        this.demo2 = demo2;
    }
    
    public String redirect(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        staffsList = staffDAO.getListUser(null);
        return SUCCESS;
    }
    
    public String showDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String para =  request.getParameter("id_cus");
        
        int id_cus = ValidateHandle.getInteger(para);
        //if(id_cus > -1){
            customer = customerDAO.loadCustomer(id_cus);
            if(customer != null){
                
                return SUCCESS;
            }
            else
                return ERROR;
//        }
//        else
//            return INPUT;
    }
    
    public String deleteCustomer(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        selected = true;
        String para =  request.getParameter("id_cus");
        
        int id_cus = ValidateHandle.getInteger(para);
        if(id_cus > -1){
            customer = customerDAO.loadCustomer(id_cus);
            deleteStatus = customerDAO.delete(customer);
            System.out.println("__" + deleteStatus);
            
            customersList = customerDAO.getListCustomer();
        
            if(deleteStatus)                
                return SUCCESS;
            else 
                return INPUT;
        }
        else
            return INPUT;
    }
    
    private FileInputStream customerTemplate;

    public FileInputStream getCustomerTemplate() {
        return customerTemplate;
    }

    public void setCustomerTemplate(FileInputStream customerTemplate) {
        this.customerTemplate = customerTemplate;
    }

    
    
    public String getTemplate(){
        
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_templates/");
                
        try{
                      
            customerTemplate = new FileInputStream(new File(fileInput +"\\template_import_khach_hang.xls"));
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return INPUT;
        } catch (IOException e) {
            
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }
    
    public String searchCustomers(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        customersList = customerDAO.getSearchCustomerList(para);
        
        return SUCCESS;
    }
}
