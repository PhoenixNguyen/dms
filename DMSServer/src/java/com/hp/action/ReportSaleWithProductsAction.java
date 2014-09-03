/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.SaleOrderDAO;
import com.hp.dao.SaleOrderDAOImpl;
import com.hp.dao.SaleOrderDetailDAO;
import com.hp.dao.SaleOrderDetailDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.PushInfo;
import com.hp.domain.ReportSaleWithProduct;
import com.hp.domain.SaleOrder;
import com.hp.domain.SaleOrderDetail;
import com.hp.domain.User;
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellUtil;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class ReportSaleWithProductsAction extends ActionSupport implements ModelDriven{
    public Object getModel(){
        return pushInfo;
    }
    
    private UserDAO userDAO = new UserDAOImpl();
    private StaffDAO staffDAO = new StaffDAOImpl();
    private CustomerDAO customerDAO = new CustomerDAOImpl();
    private SaleOrderDAO saleOrderDAO = new SaleOrderDAOImpl();
    private SaleOrderDetailDAO saleOrderDetailDAO = new SaleOrderDetailDAOImpl();
    
    private List<String> userListGiamDoc = new ArrayList<String>();
    private List<String> userListStaff = new ArrayList<String>();
    private List<String> userListCustomer = new ArrayList<String>();

    public PushInfo pushInfo = new PushInfo();
    User user = new User();

    private String giamdocId;
    private String nhanvienId;
    private String khachhangId;

    private String startDate;
    private String endDate;

    private List<ReportSaleWithProduct> reportSaleWithProductList = new ArrayList<ReportSaleWithProduct>();

    public List<ReportSaleWithProduct> getReportSaleWithProductList() {
        return reportSaleWithProductList;
    }

    public void setReportSaleWithProductList(List<ReportSaleWithProduct> reportSaleWithProductList) {
        this.reportSaleWithProductList = reportSaleWithProductList;
    }
    
    public FileInputStream orderFile;
    String outputFile;

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
    
    public String getGiamdocId() {
        return giamdocId;
    }

    public void setGiamdocId(String giamdocId) {
        this.giamdocId = giamdocId;
    }

    public String getNhanvienId() {
        return nhanvienId;
    }

    public void setNhanvienId(String nhanvienId) {
        this.nhanvienId = nhanvienId;
    }

    public String getKhachhangId() {
        return khachhangId;
    }

    public void setKhachhangId(String khachhangId) {
        this.khachhangId = khachhangId;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    
    public String refresh(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
//        if(user.getPermission() == 1)
            userListGiamDoc = userDAO.getListUser(2);
//        if(user.getPermission() == 2)
//            userListStaff = staffDAO.getListUser(user.getId());
        
        return SUCCESS;
    }
    
    public String filter(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        if(giamdocId != null){
            userListStaff = staffDAO.getListUser(giamdocId);
            
            session.setAttribute("giamdocId", giamdocId);
//            session.setAttribute("staffId", null);
//            session.setAttribute("khachhangId", null);
            if(giamdocId.compareTo("nullid") == 0){
                session.setAttribute("giamdocId", null);
                System.out.println("giamdocid: " + session.getAttribute("giamdocId"));
            }
            setGiamdocId(null);
            System.out.print(" filter!! "); 
            return SUCCESS;
        }
        else
        if(nhanvienId!= null){
            userListCustomer = customerDAO.getListCustomer(nhanvienId);
            
            session.setAttribute("staffId", nhanvienId);
//            session.setAttribute("khachhangId", null);
            if(nhanvienId.compareTo("nullid") == 0){
                session.setAttribute("staffId", null);
                System.out.println("staffId: " + session.getAttribute("staffId"));
            }
            
            //Reset
            
        }
        else
        if(khachhangId!= null){
            session.setAttribute("khachhangId", khachhangId);

            if(khachhangId.compareTo("nullid") == 0){
                session.setAttribute("khachhangId", null);
                System.out.println("khachhangId: " + session.getAttribute("khachhangId"));
            }
                        
        }
        
        
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
        userListCustomer = customerDAO.getListCustomer(pushInfo.getStaffID());
        
        
        System.out.println("DATE: "+startDate);
        
        reportSaleWithProductList = saleOrderDetailDAO.getProductReportList(pushInfo.getManagerID(), pushInfo.getStaffID(), 
                pushInfo.getCustomerID(), startDate, endDate);
        
//        //Save it to export excel file
        session.setAttribute("reportSaleWithProductList", reportSaleWithProductList);
        session.setAttribute("startDate", startDate);
        session.setAttribute("endDate", endDate);
        
        return SUCCESS;
        
    }
    
    public String exportSaleOrderList(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        user = (User)session.getAttribute("USER");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password")) ){
            return LOGIN;
        }
        
        //GET DATA
        reportSaleWithProductList = (List<ReportSaleWithProduct>)session.getAttribute("reportSaleWithProductList");
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");
        String start = (String)session.getAttribute("startDate");
        String end = (String)session.getAttribute("endDate");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Sale Order with product");
        //sheet.autoSizeColumn(200);
        sheet.setColumnWidth(0, 1000);
        sheet.setDefaultColumnWidth(20);
        
        //SaleOrder title
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
            cell0.setCellValue("Báo cáo bán hàng theo sản phẩm");
            
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
        //Write SaleOrder
        for(int i = 0; i < reportSaleWithProductList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(reportSaleWithProductList.get(i))){
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
                    
                    
                    cell.setCellValue((Float)obj);
                }
                else if(obj instanceof Double)
                    cell.setCellValue((Double)obj);
            }
            
            //SUM ROW
            if( i == (reportSaleWithProductList.size()-1)){
                Row rowEnd = sheet.createRow(i+7);
                for(int j = (titleArray().length-3); j< (titleArray().length);j++){
                    Cell cell = rowEnd.createCell(j);
                    cell.setCellValue((Double) sumArray()[j - (titleArray().length-3)]);
                }
            }
        }
        
        outputFile = "BaoCaoBanHangTheoSanPham"+start+" - "+end+".xls";
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
            "Mã Sản phẩm",
            "Tên sản phẩm",
            "Đơn vị",
            "Giá trước thuế",
            "Thuế",
            "Giá sau thuế",
            "Số lượng",
            "Tổng cộng",
            "Chiết khấu mặt hàng",
            "Doanh thu"
            
            
        };
    }
    
    public Object[] sumArray(){
        return new Object[]{
            
            sum_amount.doubleValue(),
            sum_discount.doubleValue(),
            sum_amount.subtract(sum_discount).doubleValue()
            //sum_revenue.doubleValue()
            
        };
    }
    
    BigDecimal sum_amount = new BigDecimal(0);
    BigDecimal sum_discount = new BigDecimal(0);
    //BigDecimal sum_revenue = new BigDecimal(0);
    
    public Object[] objectArray(ReportSaleWithProduct reportSaleWithProduct){
        
        BigDecimal amount = new BigDecimal(0);
        BigDecimal discount = new BigDecimal(0);
        BigDecimal revenue = new BigDecimal(0);
        
        
        amount = new BigDecimal(reportSaleWithProduct.getAmount());
        discount = new BigDecimal(reportSaleWithProduct.getDiscount());
        //revenue = new BigDecimal(reportSaleWithProduct.getRevenue());
        
        
       
        //Save
        sum_amount = sum_amount.add(amount);
        sum_discount = sum_discount.add(discount);
        //sum_revenue = sum_revenue.add(revenue);
        //sum_total_all = sum_all.subtract(sum_discount_all).subtract(sum_discount_total_all);
       
        return new Object[]{
            //reportSaleWithProduct.getSerial(),
            reportSaleWithProduct.getId(),
            reportSaleWithProduct.getName(),
            reportSaleWithProduct.getUnit(),
            reportSaleWithProduct.getPreTax(),
            reportSaleWithProduct.getTax(),
            reportSaleWithProduct.getAfterTax(),
            reportSaleWithProduct.getQuantity(),
                                   
            amount.doubleValue(),
            discount.doubleValue(),
            amount.subtract(discount).doubleValue()
            //revenue.doubleValue()
            
            
            
        };
    }
}
