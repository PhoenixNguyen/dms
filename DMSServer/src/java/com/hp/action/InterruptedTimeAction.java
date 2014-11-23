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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author HP
 */
public class InterruptedTimeAction extends ActionSupport{
    public static long INTERRUPTED_TIME = 15*60*1000; //15'
    
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
    
    public FileInputStream orderFile;
    String outputFile;
    
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
    
    public String discoverInterruption() throws ParseException{
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
                        
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                        DateFormat dfTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        if(j == 0){
                            
                            try {
                                Date startDate = df.parse(df.format(last.getThoiGian()));
                                long rangeStart = last.getThoiGian().getTime() - startDate.getTime();
                                if(rangeStart > INTERRUPTED_TIME){
                                    //getAddress(last);
                                    interruptedTimeList.add(
                                            new InterruptedTime( 
                                                    new RoadManagement(last.getMaNhanVien(), 
                                                            last.getTenNhanVien(), Timestamp.valueOf(dfTimestamp.format(startDate))),
                                                    last,
                                                    rangeStart/(1000*60), "", ""));
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(InterruptedTimeAction.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        
                        long range = updated.getThoiGian().getTime() - last.getThoiGian().getTime();
                        //System.out.println("range " + range);
                        if(range > INTERRUPTED_TIME){
                            //getAddress(last);
                            interruptedTimeList.add(new InterruptedTime(last, updated, range/(1000*60), "", ""));
                        }
                        
                        if(j == (list.size() -2)){
                            try {
                                Date endDate = df.parse(df.format(updated.getThoiGian()));
                                Calendar c = Calendar.getInstance();
                                c.setTime(endDate);
                                
                                c.add(Calendar.DAY_OF_MONTH, 1);
                                c.add(Calendar.SECOND, -1);
                                
                                endDate = c.getTime();
                                
                                long rangeEnd = endDate.getTime() - updated.getThoiGian().getTime();
                                //System.out.println("range " + range);
                                if(rangeEnd > INTERRUPTED_TIME){
                                    //getAddress(last);
                                    interruptedTimeList.add(new InterruptedTime(updated, 
                                                    new RoadManagement(updated.getMaNhanVien(), 
                                                            updated.getTenNhanVien(), Timestamp.valueOf(dfTimestamp.format(endDate))), 
                                            rangeEnd/(1000*60), "", ""));
                                } 
                            }
                                catch (ParseException ex) {
                                Logger.getLogger(InterruptedTimeAction.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        
        System.out.println("interruptedTimeList: " + interruptedTimeList.size());
        
        session.setAttribute("interruptedTimeList", interruptedTimeList);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        
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
        interruptedTimeList = (List<InterruptedTime>)session.getAttribute("interruptedTimeList");
        
        if(interruptedTimeList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");
        String start = (String)session.getAttribute("startDate");
        String end = (String)session.getAttribute("endDate");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Mất tín hiệu");
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
            cell0.setCellValue("Báo cáo mất tín hiệu");
            
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
        for(int i = 0; i < interruptedTimeList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(interruptedTimeList.get(i))){
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
        
        outputFile = "BaoCaoMatTinHieu"+start+" - "+end+".xls";
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
            "Thời gian trước khi mất",
            "Thời gian kết nối trở lại",
            "Khoảng thời gian"
        };
    }
    
    public Object[] objectArray(InterruptedTime interruptedTime){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat df2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        
        String interval = Math.round(interruptedTime.getRange()/60) + " giờ và " + Math.round(interruptedTime.getRange()%60) + " phút";
        
        return new Object[]{
            interruptedTime.getLastLocation().getMaNhanVien(),
            interruptedTime.getLastLocation().getTenNhanVien(),
            df2.format(interruptedTime.getLastLocation().getThoiGian()),
            df2.format(interruptedTime.getUpdatedLocation().getThoiGian()),
            interval
                
        };
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
