/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.common.ConfigFile;
import com.hp.common.ValidateHandle;
import com.hp.dao.ProductDAO;
import com.hp.dao.ProductDAOImpl;
import com.hp.dao.ProductDAO;
import com.hp.dao.ProductDAOImpl;
import com.hp.dao.ProviderDAO;
import com.hp.dao.ProviderDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Product;
import com.hp.domain.Demo;
import com.hp.domain.Demo2;
import com.hp.domain.Document;
import com.hp.domain.Product;
import com.hp.domain.Staff;
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
import java.math.BigDecimal;
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
public class ProductsAction extends ActionSupport implements ModelDriven{
    
    private UserDAO userDAO = new UserDAOImpl();
    //Init Products
    //private Products Product = new Products();
    
    //Init ProductDAO
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProviderDAO providerDAO = new ProviderDAOImpl();
        
    private String productID = new String();
    
    //@Valid
    public Product product = new Product();

    @Valid
    private Document document = new Document();
    
    @Valid
    private Document document2 = new Document();

    private List<String> providerIDList = new ArrayList<String>();

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
    
    public List<String> getProviderIDList() {
        return providerIDList;
    }

    public void setProviderIDList(List<String> providerIDList) {
        this.providerIDList = providerIDList;
    }
    
    public Document getDocument2() {
        return document2;
    }

    public void setDocument2(Document document2) {
        this.document2 = document2;
    }
    
    private List<Product> productsList = new ArrayList<Product>();

    private int productsTotal;

    private int id_product;

    private boolean deleteStatus;
    private boolean selected;

    private boolean changeIMStatus;

    public boolean isChangeIMStatus() {
        return changeIMStatus;
    }

    public void setChangeIMStatus(boolean changeIMStatus) {
        this.changeIMStatus = changeIMStatus;
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
    
    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }
    
    public int getProductsTotal() {
        return productsTotal;
    }

    public void setProductsTotal(int ProductsTotal) {
        this.productsTotal = ProductsTotal;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public List<Product> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Product> productsList) {
        this.productsList = productsList;
    }
    
    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
    @Override
    public Object getModel() {
        return product;
    }
    
    public String saveFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        providerIDList = providerDAO.getProvidersIDList();
        
        String saveName = document.getFileFileName();
        try {
            
            System.out.println(document.getFileContentType());
            String filePath = ServletActionContext.getServletContext().getRealPath("/db_inputs/");
            System.out.println("Server path:" + filePath + "/"+saveName);
            
            File fileToCreate = new File(filePath, saveName);
            FileUtils.copyFile(document.getFile(), fileToCreate);
            
            //set name file
            session.setAttribute("upload-name-file-product", saveName);
            
        } catch (IOException ex) {
            Logger.getLogger(ProductsAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
        return SUCCESS;
    }
    //access Excel files and import them into database
    public String addProductFromExcelFile(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        String saveName = (String)session.getAttribute("upload-name-file-product");
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
                    Product product = new Product();
//                  
                    try{
                        
                        if(row.getCell(1) != null){
                            if(row.getCell(1).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setProductID((new BigDecimal(row.getCell(1).getNumericCellValue())).toString());
                            else
                                product.setProductID(row.getCell(1).getStringCellValue());
                        }
                        if(row.getCell(2) != null){
                            if(row.getCell(2).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setBarcode((new BigDecimal(row.getCell(2).getNumericCellValue())).toString());
                            else
                                product.setBarcode(row.getCell(2).getStringCellValue());
                        }
                        
                        if(row.getCell(3) != null)
                            product.setProductName(row.getCell(3).getStringCellValue());
                        if(row.getCell(4) != null)
                            product.setBrand(row.getCell(4).getStringCellValue());
                        if(row.getCell(5) != null)
                            product.setOrigin(row.getCell(5).getStringCellValue());
                        if(row.getCell(6) != null){
                            if(row.getCell(6).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setPackingSpecifications(row.getCell(6).getNumericCellValue()+"");
                            else
                                product.setPackingSpecifications(row.getCell(6).getStringCellValue());
                                                        
                        }
                        if(row.getCell(7) != null){

                            product.setQuantification(row.getCell(7).getStringCellValue());

                        }
                        
                        if(row.getCell(8) != null){
                            if(row.getCell(8).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setVatTax((float)row.getCell(8).getNumericCellValue());
                            else
                                product.setVatTax(Float.parseFloat(row.getCell(8).getStringCellValue()));

                        }
                        
                        if(row.getCell(9) != null){
                            if(row.getCell(9).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setImportPrices((float)row.getCell(9).getNumericCellValue());
                            else
                                product.setImportPrices(Float.parseFloat(row.getCell(9).getStringCellValue()));

                        }
                        
                        if(row.getCell(10) != null){
                            if(row.getCell(10).getCellType() != HSSFCell.CELL_TYPE_STRING)
                                product.setExportPrices((float)row.getCell(10).getNumericCellValue());
                            else
                                product.setExportPrices(Float.parseFloat(row.getCell(10).getStringCellValue()));

                        }
                        if(row.getCell(11) != null)
                            product.setProvider(row.getCell(11).getStringCellValue());
                        
                        if(row.getCell(12) != null)
                            product.setDescription(row.getCell(12).getStringCellValue());
                        if(row.getCell(13) != null)
                            product.setProductImage(row.getCell(13).getStringCellValue());
                        
                        //product.setMProvider("nhacungcap1");
                        //Add to database
                        if(productDAO.saveOrUpdate(product)){
                            System.out.println("Add Object " + (i+1));
                            total++;
                            productsTotal = total;
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
        productsTotal = total;
        return SUCCESS;
    }
    
    public String displayProducts(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        productsList = productDAO.getProductList();
        providerIDList = providerDAO.getProvidersIDList();
        
        session.setAttribute("productsList", productsList);
        
        return SUCCESS;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public String editProduct() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //request.setCharacterEncoding("UTF-8");
        providerIDList = providerDAO.getProvidersIDList();
        
        request.setCharacterEncoding("UTF8");
        
        String stt = request.getParameter("id_product");
        int st;
        if(stt ==null){
            return INPUT;
        }
        st = Integer.parseInt(stt);
        
        productsList = productDAO.getProductList();
        product = productDAO.loadProduct(st);
            
        return SUCCESS;
    }
    
    public String removeProduct() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        //request.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF8");
        providerIDList = providerDAO.getProvidersIDList();
        
//        String stt = request.getParameter("id_product");
//        int st;
//        if(stt ==null){
//            return INPUT;
//        }
//        st = Integer.parseInt(stt);
        
        productsList = productDAO.getProductList();
        //product = productDAO.loadProduct(st);
            
        return SUCCESS;
    }
    
    public String updateProduct() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        request.setCharacterEncoding("UTF8");
        providerIDList = providerDAO.getProvidersIDList();
        
        System.out.println("id_product: "+product.getSerial() + " ma vach: " +product.getBarcode() + " vat= " +
                request.getParameter("product.vatTax"));
        
        //update price
        product.setExportPrices(product.getImportPrices() + product.getImportPrices()* product.getVatTax()/100);
        
        //new product
        if(product.getSerial() <= 0){
            
            System.out.println("OKsave" + product.getProductID());
            boolean status = productDAO.saveOrUpdate(product);
            productsList = productDAO.getProductList();
            
            if(status){
                return SUCCESS;
            }
            return INPUT;
        }
        else{
            System.out.println("OK" + product.getProductID());


            boolean status = productDAO.update(product);
            productsList = productDAO.getProductList();

            if(status){

                return SUCCESS;
            }

            return INPUT;
        }
    }
    
    public String uploadAnImage(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        providerIDList = providerDAO.getProvidersIDList();
        
        String stt = request.getParameter("id_product");
        int st;
        if(stt ==null){
            return INPUT;
        }
        st = Integer.parseInt(stt);
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String saveName = df.format(date)+"-"+document2.getFileFileName() ;
        
        try {
            //update the name
            System.out.println("OK" + product.getProductID());
            product = productDAO.loadProduct(st);
            
            System.out.println(document2.getFileContentType());
            String filePath = ServletActionContext.getServletContext().getRealPath("/db_products/"+ product.getProductID()+"/");
            System.out.println("Server path:" + filePath + "\\"+saveName);
            
            File fileToCreate = new File(filePath, saveName);
            FileUtils.copyFile(document2.getFile(), fileToCreate);
            
            
            product.setProductImage(saveName);
            boolean status = productDAO.update(product);
            //productsList = productDAO.getProductList();
            
            id_product = st;
            
        } catch (IOException ex) {
            Logger.getLogger(ProductsAction.class.getName()).log(Level.SEVERE, null, ex);
            return INPUT;
        }
        
        changeIMStatus = true;
        return SUCCESS;
    }
    
    public String displayProduct(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("id_pdct");
        
        int id_pdct = ValidateHandle.getInteger(para);
        //if(id_pdct > -1){
            product = productDAO.loadProduct(id_pdct);
            providerIDList = providerDAO.getProvidersIDList();
            if(product != null){
                
                return SUCCESS;
            }
            else
                return ERROR;
//        }
//        else
//            return INPUT;
    }
    
    public String redirect(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        
        providerIDList = providerDAO.getProvidersIDList();
        return SUCCESS;
    }
    
    public String deleteProduct(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
                
        selected = true;
        
        String para =  request.getParameter("id_pdct");
        
        int id_pdct = ValidateHandle.getInteger(para);
        if(id_pdct > -1){
            product = productDAO.loadProduct(id_pdct);
            deleteStatus = productDAO.delete(product);
            
            productsList = productDAO.getProductList();
            
            if(deleteStatus){
                return SUCCESS;
            }
            else
                return INPUT;
        }
        else
            return INPUT;
    }
    
    private FileInputStream productTemplate;

    public FileInputStream getProductTemplate() {
        return productTemplate;
    }

    public void setProductTemplate(FileInputStream productTemplate) {
        this.productTemplate = productTemplate;
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
                      
            productTemplate = new FileInputStream(new File(fileInput +"\\template_import_san_pham.xls"));
            
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return INPUT;
        } catch (IOException e) {
            
            e.printStackTrace();
            return INPUT;
        }
        return SUCCESS;
    }
    
    public String searchProducts() throws UnsupportedEncodingException{
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF8");
        
        //Authorize
        if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            return LOGIN;
        }
        
        String para =  request.getParameter("search_text");
        System.out.println("__" + para);
        productsList = productDAO.getSearchProductList(para);
        
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
        productsList = (List<Product>)session.getAttribute("productsList");
        
        if(productsList == null)
            return INPUT;
        
        String fileInput = ServletActionContext.getServletContext().getRealPath("/db_exports/");

        //
        //Write
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet("Sản phẩm");
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
                    12  //last column  (0-based)
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
            cell0.setCellValue("Danh sách sản phẩm");
            
            //Row date
            Row row1 = sheet.createRow(i+1);
            //row1.setHeight((short)500);
            Cell cell1 = row1.createCell(0);
            
            //Merge for title
            sheet.addMergedRegion(new CellRangeAddress(
                    i+1, //first row (0-based)
                    i+1, //last row  (0-based)
                    0, //first column (0-based)
                    12  //last column  (0-based)
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
        for(int i = 0; i < productsList.size(); i++){
            Row row = sheet.createRow(i+5);
            int cellnum = 0;
            
            //Cell 0 - stt
            Cell cell0 = row.createCell(cellnum++);
            cell0.setCellValue(i+1);
           
            //Set content
            for(Object obj : objectArray(productsList.get(i))){
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
        
        outputFile = "DanhSachSanPham.xls";
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
            "Mã sản phẩm",
            "Mã vạch",
            "Tên sản phẩm",
            "Thương hiệu",
            "Xuất xứ",
            "Quy cách",
            "Đơn vị",
            "Thuế",
            "Giá trước thuế",
            "Giá sau thuế",
            "Nhà cung cấp",
            "Mô tả",
        };
    }
    
    public Object[] objectArray(Product product){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        
        return new Object[]{
            product.getProductID(),
            product.getBarcode(),
            product.getProductName(),
            product.getBrand(),
            product.getOrigin(),
            product.getPackingSpecifications(),
            product.getQuantification(),
            product.getVatTax(),
            product.getImportPrices(),
            product.getExportPrices(),
            product.getProvider(),
            product.getDescription()
                
        };
    }
    
}
