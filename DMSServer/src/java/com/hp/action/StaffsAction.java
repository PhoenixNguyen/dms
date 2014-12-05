/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ConfigFile;
import com.hp.common.ValidateHandle;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Staff;
import com.hp.domain.Demo;
import com.hp.domain.Demo2;
import com.hp.domain.Document;
import com.hp.domain.ForLeave;
import com.hp.domain.Staff;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.validator.Valid;

/**
 *
 * @author HP
 */
public class StaffsAction extends ActionSupport implements ModelDriven{
    
    
    //Init Staffs
    //private Staffs Staff = new Staffs();
    
    //Init StaffDAO
    private StaffDAO staffDAO = new StaffDAOImpl();
    private UserDAO userDAO = new UserDAOImpl();
        
    private String staffID = new String();
    @Valid
    public Staff staff = new Staff();

    @Valid
    private Document document = new Document();
    private List<Staff> staffsList = new ArrayList<Staff>();

    private int staffsTotal = 0;

    private List<String> usersList = new ArrayList<String>();

    private boolean deleteStatus;
    private boolean selected;
    
    private String startDate;

    public FileInputStream orderFile;
    String outputFile;
    
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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
    
    public List<String> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<String> usersList) {
        this.usersList = usersList;
    }
    
    public int getStaffsTotal() {
        return staffsTotal;
    }

    public void setStaffsTotal(int StaffsTotal) {
        this.staffsTotal = StaffsTotal;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    public List<Staff> getStaffsList() {
        return staffsList;
    }

    public void setStaffsList(List<Staff> staffsList) {
        this.staffsList = staffsList;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    @Override
    public Object getModel() {
        return staff;
    }
    
    public String saveFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        usersList = userDAO.getListUser(2);
                
        String saveName = document.getFileFileName();
        if(saveName == null)
            return INPUT;
        try {
            
            System.out.println(document.getFileContentType());
            String filePath = ServletActionContext.getServletContext().getRealPath("/db_inputs/");
            System.out.println("Server path:" + filePath);
            
            File fileToCreate = new File(filePath, saveName);
            FileUtils.copyFile(document.getFile(), fileToCreate);
            
            //set name file
            session.setAttribute("upload-name-file-staff", saveName);
            
        } catch (IOException ex) {
            Logger.getLogger(StaffsAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
        return SUCCESS;
    }
    //access Excel files and import them into database
    public String addStaffFromExcelFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String saveName = (String)session.getAttribute("upload-name-file-staff");
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
                    //If the staff id null
                    if(row.getCell(1) == null ||
                            row.getCell(1).getStringCellValue().compareTo("") == 0

                            ){
                        continue;   
                    }
                    
                    //Init Staff Object                   
                    Staff staff = new Staff();
                    try{
                        
                        if(row.getCell(1) != null)
                            staff.setId(row.getCell(1).getStringCellValue());
                        if(row.getCell(2) != null)
                            staff.setPw(row.getCell(2).getStringCellValue());
                        if(row.getCell(3) != null)
                            staff.setName(row.getCell(3).getStringCellValue());
                        if(row.getCell(4) != null)
                            staff.setAdress(row.getCell(4).getStringCellValue());
                        if(row.getCell(5) != null)
                        staff.setJob(row.getCell(5).getStringCellValue());
                        if(row.getCell(6) != null)
                        staff.setPhone(row.getCell(6).getStringCellValue());

                        if(row.getCell(7) != null && row.getCell(7).getStringCellValue().compareTo("") != 0){

                            staff.setDate(df.parse(row.getCell(7).getStringCellValue()));

                        }
                        if(row.getCell(8) != null)
                            staff.setManager(row.getCell(8).getStringCellValue());
                        if(row.getCell(9) != null){
                            if(row.getCell(9).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                staff.setStatus(row.getCell(9).getNumericCellValue() == 1 ? true:false);
                            else
                                staff.setStatus(row.getCell(9).getStringCellValue().compareTo("1") == 0 ? true:false);
                        }
                        
                        if(row.getCell(10) != null){
                            if(row.getCell(10).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                staff.setPermission((int)row.getCell(10).getNumericCellValue());
                            else
                                staff.setPermission(Integer.parseInt(row.getCell(10).getStringCellValue()));
                        }
                        
                        //Add to database
                        if(staffDAO.saveOrUpdate(staff)){
                            System.out.println("Add Object " + (i+1));
                            total++;
                            staffsTotal = total;
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
        
        staffsTotal = total;
        return SUCCESS;
    }
    
    public String displayStaffs(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        staffsList = staffDAO.getListStaff();
        
        usersList = userDAO.getListUser(2);
        
        session.setAttribute("staffsList", staffsList);
        return SUCCESS;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }
    
    public String editStaff() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF8");
        usersList = userDAO.getListUser(2);
        
        String para =  request.getParameter("id_st");
        
        int id_st = ValidateHandle.getInteger(para);
        if(id_st > -1){
            staff = staffDAO.loadStaff(id_st);
            return SUCCESS;
        }
        else
            return INPUT;
        
//        String stt = request.getParameter("id_staff");
//        int st;
//        if(stt ==null){
//            return INPUT;
//        }
//        st = Integer.parseInt(stt);
//        
//        staffsList = staffDAO.getListStaff();
//        staff = staffDAO.loadStaff(st);
            
    }
    
    private int staff_serial;

    public int getStaff_serial() {
        return staff_serial;
    }

    public void setStaff_serial(int staff_serial) {
        this.staff_serial = staff_serial;
    }
    
    public String updateStaff() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        request.setCharacterEncoding("UTF8");
        usersList = userDAO.getListUser(2);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        
        Date out = null;
        System.out.println("OK1" + staff.getId());

        try{
            out = sdf2.parse(startDate);

        }catch(Exception e){
            e.printStackTrace();
        }

        staff.setDate(out);
        
        //New
        if(staff.getStt() <= 0){
            
            boolean status = staffDAO.saveOrUpdate(staff);
            //staffsList = staffDAO.getListStaff();

            System.out.println("___ " + status);
            if(status)
                return SUCCESS;
            else
                return INPUT;
        }
        
        //UPdate
        System.out.println("OK" + staff.getId());
        
        boolean status = staffDAO.update(staff);
        if(status)            
            return SUCCESS;
        else
            return INPUT;
    }
    
    public String showDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_st");
        
        int id_st = ValidateHandle.getInteger(para);
        //if(id_st > -1){
            staff = staffDAO.loadStaff(id_st);
            if(staff != null){
                
                return SUCCESS;
            }
            else
                return ERROR;
        //}
//        else
//            return INPUT;
    }
    
    public String deleteStaff(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        selected = true;
        
        String para =  request.getParameter("id_st");
        
        int id_st = ValidateHandle.getInteger(para);
        if(id_st > -1){
            staff = staffDAO.loadStaff(id_st);
            
            deleteStatus = staffDAO.delete(staff);
            
            staffsList = staffDAO.getListStaff();
            
            System.out.println("_______ " +deleteStatus);
            if(deleteStatus)
                return SUCCESS;
            else
                return INPUT;
        }
        else
            return INPUT;
    }
    
    public String redirect(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //staffsList = staffDAO.getListUser(null);
        
        usersList = userDAO.getListUser(2);
        return SUCCESS;
    }
    
    private FileInputStream staffTemplate;

    public FileInputStream getStaffTemplate() {
        return staffTemplate;
    }

    public void setStaffTemplate(FileInputStream staffTemplate) {
        this.staffTemplate = staffTemplate;
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
                      
            staffTemplate = new FileInputStream(new File(fileInput +"\\template_import_nhan_vien.xls"));
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return INPUT;
        } catch (IOException e) {
            
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }
    
    public String searchStaff(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        staffsList = staffDAO.getSearchStaffList(para);
        
        return SUCCESS;
    }
    
    public String exportExcel(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //GET DATA
        staffsList = (List<Staff>)session.getAttribute("staffsList");
        
        if(staffsList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Nhân viên");
        //sheet.autoSizeColumn(200);
        sheet.setColumnWidth(0, 1000);
        sheet.setDefaultColumnWidth(20);
        
        //TakeOrder title
        for(int i = 1; i < 2; i++){
            //
            Row rowstart = sheet.createRow(0);
            
            //Row Title
            Row row0 = sheet.createRow(i);
            row0.setHeight((short)500);
            Cell cell0 = row0.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i, //first row (0-based)
                    i, //last row  (0-based)
                    0, //first column (0-based)
                    10  //last column  (0-based)
            ));
            //CellUtil.setAlignment(cell0, workBook, CellStyle.ALIGN_CENTER);
            CellStyle cellStyle = workBook.createCellStyle();
            cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            
            //font
            Font headerFont = workBook.createFont();
            headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
            headerFont.setFontHeight((short)250);
            cellStyle.setFont(headerFont);
            
            cell0.setCellStyle(cellStyle);
            cell0.setCellValue("Danh sách nhân viên");
            
            //Row date
            Row row1 = sheet.createRow(i+1);
            //row1.setHeight((short)500);
            Cell cell1 = row1.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i+1, //first row (0-based)
                    i+1, //last row  (0-based)
                    0, //first column (0-based)
                    10  //last column  (0-based)
            ));
            CellStyle cellAlign = workBook.createCellStyle();
            cellAlign.setAlignment(CellStyle.ALIGN_CENTER);
            cell1.setCellStyle(cellAlign);
            
            cell1.setCellValue("");
            
            //Row Header
            Row row = sheet.createRow(4);
            int cellnum = 0;
            
            for(Object obj : titleArray()){
                Cell cell = row.createCell(cellnum++);
                
                CellStyle style = workBook.createCellStyle();
                style.setFillForegroundColor(HSSFColor.YELLOW.index);
                style.setFillPattern(CellStyle.SOLID_FOREGROUND);

               cell.setCellStyle(style);
               
                if(obj instanceof Timestamp) 
                    cell.setCellValue((Timestamp)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Float)
                    cell.setCellValue((Float)obj);
            }
            
            
            
        }
        //Write TakeOrder
        for(int i = 0; i < staffsList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(staffsList.get(i))){
                Cell cell = row.createCell(cellnum++);
                
                if(obj instanceof Timestamp) 
                    cell.setCellValue((Timestamp)obj);
                else if(obj instanceof Boolean)
                    cell.setCellValue((Boolean)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Float){
                    
//                    CellStyle cellStyle = workBook.createCellStyle();
//                    DataFormat format = workBook.createDataFormat();
//                    cellStyle.setDataFormat(format.getFormat("#.#"));
//                    cell.setCellStyle(cellStyle);
                    
                    cell.setCellValue((Float)obj);
                }
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
            
            
        }
        
        outputFile = "DanhSachNhanVien.xls";
        try{
            FileOutputStream output = new FileOutputStream(new File(fileInput +"\\" + outputFile));
            
            
            workBook.write(output);
            output.close();
            System.out.println("Excel written successfully..");
            orderFile = new FileInputStream(new File(fileInput +"\\"+outputFile));
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return SUCCESS;
    }

    public Object[] titleArray(){
        return new Object[]{
            "Stt",
            "Mã nhân viên",
            "Mật khẩu",
            "Họ tên",
            "Địa chỉ",
            "Chức vụ",
            "Số điện thoại",
            "Ngày gia nhập",
            "Người quản lý",
            "Trạng thái",
            "Quyền hạn",
        };
    }
    
    public Object[] objectArray(Staff staff){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        int p = staff.getPermission();
        String permission = "";
        if(p == 1)
            permission = "Quản lý";
        else
            if(p == 2)
                permission = "Nhân viên bán hàng";
            else
                if(p == 3)
                    permission = "Nhân viên cập nhật vị trí";
        
        String status = "";
        if(staff.isStatus())
            status = "Đang hoạt động";
        else
            status = "Không hoạt động";
        
        return new Object[]{
            staff.getId(),
            staff.getPw(),
            staff.getName(),
            staff.getAdress(),
            staff.getJob(),
            staff.getPhone(),
            staff.getDate(),
            staff.getManager(),
            status,
            permission
                
        };
    }
    
    public String getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }
    
    public FileInputStream getOrderFile() {
        return orderFile;
    }

    public void setOrderFile(FileInputStream orderFile) {
        this.orderFile = orderFile;
    }
}
