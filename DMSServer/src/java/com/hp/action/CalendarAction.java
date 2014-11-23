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
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.media.sound.InvalidFormatException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
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
    
    public FileInputStream orderFile;
    String outputFile;

    public String displayCalendars(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //calendarList = calendarDAO.getCalendarList();
        userListGiamDoc = userDAO.getListUser(2);
        
        return SUCCESS;
    }
    
    public String filterResult() throws ParsePropertyException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        
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
        
        session.setAttribute("calendarList", calendarList);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        
        String action = StringUtils.trimToEmpty(request.getParameter("action"));
        return SUCCESS;
    }
    public String exportCalendarList(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //GET DATA
        calendarList = (List<Calendar>)session.getAttribute("calendarList");
        
        if(calendarList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");
        String start = (String)session.getAttribute("startDate");
        String end = (String)session.getAttribute("endDate");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Kế hoạch công tác");
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
                    8  //last column  (0-based)
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
            cell0.setCellValue("Kế hoạch công tác");
            
            //Row date
            Row row1 = sheet.createRow(i+1);
            //row1.setHeight((short)500);
            Cell cell1 = row1.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i+1, //first row (0-based)
                    i+1, //last row  (0-based)
                    0, //first column (0-based)
                    8  //last column  (0-based)
            ));
            CellStyle cellAlign = workBook.createCellStyle();
            cellAlign.setAlignment(CellStyle.ALIGN_CENTER);
            cell1.setCellStyle(cellAlign);
            
            
            if(start == null)
                start = "";
            if(end == null)
                end = "";
            cell1.setCellValue("Từ ngày: " + start+ " - Đến ngày: " + end);
            
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
        for(int i = 0; i < calendarList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(calendarList.get(i))){
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
        
        outputFile = "BaoCaoLichCongTac"+start+" - "+end+".xls";
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
            "Tên nhân viên",
            "Ngày",
            "Tỉnh thành",
            "Thành phố đã đi",
            "Nội dung",
            "Nhiệm vụ",
            "Báo cáo",
            "Cộng tác viên",
            "Hỗ trợ",
            "Trạng thái",
            "Ngày tạo"
            
        };
    }
    
    public Object[] objectArray(Calendar calendar){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        String status = "";
        switch(calendar.getStatus()){
            case 0:
                status = "Tạo kế hoạch";
                break;
            case 1:
                status = "Đề nghị hoàn thành";
                break;
            case 2:
                status = "Hoàn thành";
                break;
        }
       
        return new Object[]{
            calendar.getStaff().getId(),
            calendar.getStaff().getName(),
            df.format(calendar.getCalendarDate()),
            calendar.getProvince(),
            calendar.getNote(),
            calendar.getContent(),
            calendar.getMission(),
            calendar.getReport(),
            calendar.getContributor(),
            calendar.getSupport(),
            status,
            calendar.getCreatedTime() == null?"":df2.format(calendar.getCreatedTime())
        };
    }
    
    public String edit() throws IOException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        
        HttpSession session = request.getSession();
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("text/html; charset=UTF-8");
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        String id = StringUtils.trimToEmpty(request.getParameter("id"));
        String contributor = StringUtils.trimToEmpty(request.getParameter("contributor"));
        String support = StringUtils.trimToEmpty(request.getParameter("support"));
        String mission = StringUtils.trimToEmpty(request.getParameter("mission"));
        String report = StringUtils.trimToEmpty(request.getParameter("report"));
        String statusParam = StringUtils.trimToEmpty(request.getParameter("status"));
        
        int stt = 0;
        int status = -1;
        try{
            stt = Integer.parseInt(id);
            status = Integer.parseInt(statusParam);
            
        }catch(Exception e){
            e.printStackTrace();
            response.getOutputStream().write("Lỗi không xác định, hãy thử lại sau!".getBytes("UTF-8"));
            return null;
        }
        
        Calendar calendar = null;
        if(stt > 0)
            calendar = calendarDAO.getCalendar(stt);
        
        if(calendar != null && calendar.getStatus() != 2){
            calendar.setContributor(contributor);
            calendar.setMission(mission);
            calendar.setReport(report);
            calendar.setSupport(support);
            
            calendar.setUpdatedTime(Timestamp.valueOf(df.format(new Date())));
            
            if(status != -1)
                calendar.setStatus(status);
            
            if(calendarDAO.update(calendar)){
                response.getOutputStream().write("Cập nhật lịch công tác thành công".getBytes("UTF-8"));
                return null;
            }
        }
        
        response.getOutputStream().write("Cập nhật lịch công tác thất bại, hãy thử lại sau!".getBytes("UTF-8"));
        
        return null;
    }
    
    public String delete() throws IOException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE);
        
        HttpSession session = request.getSession();
        
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("text/html; charset=UTF-8");
        
        String id = StringUtils.trimToEmpty(request.getParameter("id"));
        
        int stt = 0;
        try{
            stt = Integer.parseInt(id);
            
        }catch(Exception e){
            e.printStackTrace();
            response.getOutputStream().write("Lỗi không xác định, hãy thử lại sau!".getBytes("UTF-8"));
            return null;
        }
        
        Calendar calendar = null;
        if(stt > 0)
            calendar = calendarDAO.getCalendar(stt);
        
        if(calendar != null && calendar.getStatus() != 2){
            
            if(calendarDAO.delete(calendar)){
                response.getOutputStream().write("Xóa lịch công tác thành công".getBytes("UTF-8"));
                return null;
            }
        }
        
        response.getOutputStream().write("Xóa lịch công tác thất bại, hãy thử lại sau!".getBytes("UTF-8"));
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
