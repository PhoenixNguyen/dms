/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ValidateHandle;
import com.hp.dao.ProviderDAO;
import com.hp.dao.ProviderDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Document;
import com.hp.domain.Product;
import com.hp.domain.Provider;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class ProviderAction extends ActionSupport implements ModelDriven{

    private Provider provider = new Provider();

    private ProviderDAO providerDAO = new ProviderDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
    
    private List<Provider> providerList = new ArrayList<Provider>();

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

    @Valid
    private Document document = new Document();

    private int providersTotal;

    public int getProvidersTotal() {
        return providersTotal;
    }

    public void setProvidersTotal(int providersTotal) {
        this.providersTotal = providersTotal;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    public List<Provider> getProviderList() {
        return providerList;
    }

    public void setProviderList(List<Provider> providerList) {
        this.providerList = providerList;
    }
    
    @Override
    public Object getModel() {
        return provider;
    }
    
    
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
    
    public String redirect(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        providerList = providerDAO.getProvidersList();
        return SUCCESS;
    }
    
    public String displayProvider(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_pvd");
        
        int id_pdct = ValidateHandle.getInteger(para);
        //if(id_pdct > -1){
            provider = providerDAO.loadProvider(id_pdct);
            if(provider != null){
                
                return SUCCESS;
            }
            else
                return ERROR;
//        }
//        else
//            return INPUT;
    }
   
    public String updateProvider() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        request.setCharacterEncoding("UTF8");
        
        //update price
        
        //new product
        if(provider.getSerial() <= 0){
            
            boolean status = providerDAO.saveOrUpdate(provider);
            
            if(status){
                return SUCCESS;
            }
            return INPUT;
        }
        else{
            System.out.println("OK" + provider.getId());


            boolean status = providerDAO.update(provider);

            if(status){

                return SUCCESS;
            }

            return INPUT;
        }
    }
    
    public String deleteProvider(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
                
        selected = true;
        
        String para =  request.getParameter("id_pvd");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            provider = providerDAO.loadProvider(id_pdct);
            deleteStatus = providerDAO.delete(provider);
            
            providerList = providerDAO.getProvidersList();
            
            if(deleteStatus){
                return SUCCESS;
            }
            else
                return INPUT;
        }
        else
            return INPUT;
    }
    
    public String saveFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
                
        String saveName = document.getFileFileName();
        try {
            
            System.out.println(document.getFileContentType());
            String filePath = ServletActionContext.getServletContext().getRealPath("/db_inputs/");
            System.out.println("Server path:" + filePath + "/"+saveName);
            
            File fileToCreate = new File(filePath, saveName);
            FileUtils.copyFile(document.getFile(), fileToCreate);
            
            //set name file
            session.setAttribute("upload-name-file-provider", saveName);
            
        } catch (IOException ex) {
            Logger.getLogger(ProviderAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
        return SUCCESS;
    }
    //access Excel files and import them into database
    public String addProviderFromExcelFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String saveName = (String)session.getAttribute("upload-name-file-provider");
        System.out.println("Get Attribute file name: "+saveName);
        if(saveName == null)
            return SUCCESS;
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
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
                    //If the product id null
                    if(row.getCell(1) == null ||
                            
                            ( row.getCell(1).getCellType() == HSSFCell.CELL_TYPE_STRING && row.getCell(1).getStringCellValue().compareTo("") == 0 ) ||
                            ( row.getCell(1).getCellType() != HSSFCell.CELL_TYPE_STRING && row.getCell(1).getNumericCellValue() <= 0 )
                          
                            ){
                        continue;   
                    }
                    
                    //Init Product Object
                    Provider provider = new Provider();
//                  
                    try{
                        
                        if(row.getCell(1) != null){
                            if(row.getCell(1).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                provider.setId((new BigDecimal(row.getCell(1).getNumericCellValue())).toString());
                            else
                                provider.setId(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2) != null){
                            if(row.getCell(2).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                provider.setName((new BigDecimal(row.getCell(2).getNumericCellValue())).toString());
                            else
                                provider.setName(row.getCell(2).getStringCellValue());
                        }
                        
                        if(row.getCell(3) != null)
                            provider.setAddress(row.getCell(3).getStringCellValue());
                        if(row.getCell(4) != null)
                            provider.setPhoneNumber(row.getCell(4).getStringCellValue());
                        if(row.getCell(5) != null)
                            provider.setFax(row.getCell(5).getStringCellValue());
                        if(row.getCell(6) != null){
                            if(row.getCell(6).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                provider.setNote(row.getCell(6).getNumericCellValue()+"");
                            else
                                provider.setNote(row.getCell(6).getStringCellValue());
                                                        
                        }
                        
                        //Add to database
                        if(providerDAO.saveOrUpdate(provider)){
                            System.out.println("Add Object " + (i+1));
                            total++;
                            providersTotal = total;
                        }
                        else{
                            int t = 0;
                            continue;
                        }
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
        providersTotal = total;
        return SUCCESS;
    }
    
    private FileInputStream providerTemplate;

    public FileInputStream getProviderTemplate() {
        return providerTemplate;
    }

    public void setProviderTemplate(FileInputStream providerTemplate) {
        this.providerTemplate = providerTemplate;
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
                      
            providerTemplate = new FileInputStream(new File(fileInput +"\\template_import_nha_cung_cap.xls"));
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return INPUT;
        } catch (IOException e) {
            
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }
    
    public String searchProvider(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        providerList = providerDAO.getSearchProviderList(para);
        
        return SUCCESS;
    }
}
