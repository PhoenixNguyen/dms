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
import com.hp.domain.TimeKeeper;
import com.hp.domain.User;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.LOGIN;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
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
    
    private String exceptionFrom;
    private String exceptionTo;
    
    public FileInputStream orderFile;
    String outputFile;
    
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
        
        //System.out.println("lists: " + lists.size());
        
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
                    if(exceptionFrom != null && !exceptionFrom.equals("") && exceptionTo != null && !exceptionTo.equals("")){
                        System.out.println("exceptionFrom: " + exceptionFrom + "exceptionTo: " + exceptionTo);
                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                        Date from = null;
                        Date to = null;
                        try{
                            from = df.parse(exceptionFrom);
                            to = df.parse(exceptionTo);
                            
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                        
                        if(from!=null && to != null){
                            if(location1.getThoiGian().after(from) && location1.getThoiGian().before(to))
                                continue;
                        }
                    }
                    
                    distance += calculateDistance(location1.getViDo(), location1.getKinhDo(), location2.getViDo(), location2.getKinhDo());
                    interval += (location2.getThoiGian().getTime() - location1.getThoiGian().getTime());
                    
                    //System.out.println("Y= " + list.get(j).getThoiGian() + " Time(" + j +"): " + interval);
                }
                
                locationDistance.setDistance(distance);
                locationDistance.setInterval(interval/(1000*60 *60*1f));
                
                if(distance >= 20)
                    locationDistanceList.add(locationDistance);
            }
        }
        
        session.setAttribute("locationDistanceList", locationDistanceList);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        
        return SUCCESS;
    }
    
    public String filterDetail(){
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
        Date fromDate = null;
        Date toDate = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            System.out.println("1: " +startDate);
            
            fromDate = sdf.parse(startDate);
            toDate = sdf.parse(endDate);
            
        } catch (ParseException ex) {
            Logger.getLogger(CalculateDistanceAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(fromDate != null && toDate != null)
        while(fromDate.before(toDate)){
            System.out.println("1: " +fromDate);
            
            String date = sdf.format(fromDate);
            
            //Get list value
            List<List<RoadManagement>> lists = new ArrayList<List<RoadManagement>>();
            lists = roadManagementDAO.getRoad(pushInfo.getManagerID(), pushInfo.getStaffID(), "", date, date);

            System.out.println("fromDate: " + fromDate + " " + date);

            if(lists!=null && lists.size() > 0){
                for(int i = 0; i < lists.size(); i++){
                    LocationDistance locationDistance = new LocationDistance();
                    locationDistance.setStaff(staffDAO.loadStaff(lists.get(i).get(0).getMaNhanVien()));
                    locationDistance.setCurrentDate(fromDate);
                            
                    float distance = 0;
                    float interval = 0;
                    List<RoadManagement> list = lists.get(i);
                    for(int j = 0; j < (list.size() - 1); j ++){
                        RoadManagement location1 = list.get(j);
                        RoadManagement location2 = list.get(j+1);
                        if(exceptionFrom != null && !exceptionFrom.equals("") && exceptionTo != null && !exceptionTo.equals("")){
                            System.out.println("exceptionFrom: " + exceptionFrom + "exceptionTo: " + exceptionTo);
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                            Date from = null;
                            Date to = null;
                            try{
                                from = df.parse(exceptionFrom);
                                to = df.parse(exceptionTo);

                            }catch(Exception e){
                                e.printStackTrace();
                            }

                            if(from!=null && to != null){
                                if(location1.getThoiGian().after(from) && location1.getThoiGian().before(to))
                                    continue;
                            }
                        }

                        distance += calculateDistance(location1.getViDo(), location1.getKinhDo(), location2.getViDo(), location2.getKinhDo());
                        interval += (location2.getThoiGian().getTime() - location1.getThoiGian().getTime());

                        //System.out.println("Y= " + list.get(j).getThoiGian() + " Time(" + j +"): " + interval);
                    }

                    locationDistance.setDistance(distance);
                    locationDistance.setInterval(interval/(1000*60 *60*1f));

                    if(distance >= 20)
                        locationDistanceList.add(locationDistance);
                }
            }
            //End Get list value
            //next day
            Calendar c = Calendar.getInstance();
            c.setTime(fromDate);
            c.add(Calendar.DAY_OF_MONTH, 1);
            
            fromDate = c.getTime();
        }
        
        
        session.setAttribute("locationDistanceDetailList", locationDistanceList);
        session.setAttribute("startDateDetail", startDate);
        session.setAttribute("endDateDetail", endDate);
        return SUCCESS;
    }
    
    public String exportExcel(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //GET DATA
        locationDistanceList = (List<LocationDistance>)session.getAttribute("locationDistanceList");
        
        if(locationDistanceList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");
        String start = (String)session.getAttribute("startDate");
        String end = (String)session.getAttribute("endDate");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Quãng đường");
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
                    4  //last column  (0-based)
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
            cell0.setCellValue("Báo cáo Quãng đường");
            
            //Row date
            Row row1 = sheet.createRow(i+1);
            //row1.setHeight((short)500);
            Cell cell1 = row1.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i+1, //first row (0-based)
                    i+1, //last row  (0-based)
                    0, //first column (0-based)
                    4  //last column  (0-based)
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
        for(int i = 0; i < locationDistanceList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(locationDistanceList.get(i))){
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
        
        outputFile = "BaoCaoTinhQuangDuong"+start+" - "+end+".xls";
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
            "Khoảng cách (km)",
            "Thời gian (giờ)"
        };
    }
    
    public Object[] objectArray(LocationDistance locationDistance){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        return new Object[]{
            locationDistance.getStaff().getId(),
            locationDistance.getStaff().getName(),
            Math.round(locationDistance.getDistance()*100.0)/100.0,
            Math.round(locationDistance.getInterval()*100.0)/100.0
                
        };
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

        float result = (float)(AVERAGE_RADIUS_OF_EARTH * c);
        return result > 0.2 ? result : 0;
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
    
    public String getExceptionFrom() {
        return exceptionFrom;
    }

    public void setExceptionFrom(String exceptionFrom) {
        this.exceptionFrom = exceptionFrom;
    }

    public String getExceptionTo() {
        return exceptionTo;
    }

    public void setExceptionTo(String exceptionTo) {
        this.exceptionTo = exceptionTo;
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
    
    public String exportExcelDetail(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //GET DATA
        locationDistanceList = (List<LocationDistance>)session.getAttribute("locationDistanceDetailList");
        
        if(locationDistanceList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");
        String start = (String)session.getAttribute("startDateDetail");
        String end = (String)session.getAttribute("endDateDetail");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Quãng đường chi tiết");
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
                    5  //last column  (0-based)
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
            cell0.setCellValue("Báo cáo chi tiết Quãng đường ");
            
            //Row date
            Row row1 = sheet.createRow(i+1);
            //row1.setHeight((short)500);
            Cell cell1 = row1.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i+1, //first row (0-based)
                    i+1, //last row  (0-based)
                    0, //first column (0-based)
                    5  //last column  (0-based)
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
            
            for(Object obj : titleArrayDetail()){
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
        for(int i = 0; i < locationDistanceList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArrayDetail(locationDistanceList.get(i))){
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
        
        outputFile = "BaoCaoTinhChiTietQuangDuong"+start+" - "+end+".xls";
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

    public Object[] titleArrayDetail(){
        return new Object[]{
            "Stt",
            "Ngày",
            "Mã nhân viên",
            "Tên nhân viên",
            "Khoảng cách (km)",
            "Thời gian (giờ)"
        };
    }
    
    public Object[] objectArrayDetail(LocationDistance locationDistance){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        return new Object[]{
            df.format(locationDistance.getCurrentDate()),
            locationDistance.getStaff().getId(),
            locationDistance.getStaff().getName(),
            Math.round(locationDistance.getDistance()*100.0)/100.0,
            Math.round(locationDistance.getInterval() *100.0)/100.0
                
        };
    }
}
