/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.excelhandle;

import com.hp.domain.Customer;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import com.hp.common.ConfigFile;

/**
 *
 * @author HP
 */
public class GetData {
        
    private String mFileInput;
    
    public GetData(){
        
    }
    
    public GetData(String pFileInput){
        this.mFileInput = pFileInput;
    }
    
    public void readExcel(){
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("database/customer.xls"));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getPhysicalNumberOfRows();

            int cols = 0; // No of columns (max)
            int tmp = 0;

            // This trick ensures that we get the data properly even if it doesn't start from first few rows
            for(int i = 0; i < 10 || i < rows; i++) {
                row = sheet.getRow(i);
                if(row != null) {
                    tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                    if(tmp > cols) 
                        cols = tmp;
                }
            }

//            for(int r = 0; r < rows; r++) {
//                row = sheet.getRow(r);
//                if(row != null) {
//                    for(int c = 0; c < cols; c++) {
//                        cell = row.getCell((short)c);
//                        if(cell != null) {
//                            // Your code here
//                        }
//                    }
//                }
//            }
            
            row = sheet.getRow(8);
            if(row != null){               
                cell = row.getCell(1);
                    if(cell != null)
                        System.out.println("Row: " + 9 + ", Data: " + cell.getStringCellValue());
            }
            
            
        } catch(Exception ioe) {
            ioe.printStackTrace();
        }
    }
    
    public ArrayList<Customer> loadCustomer(){
        ArrayList<Customer> listCustomer = new ArrayList();
        
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(mFileInput));
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row;
            HSSFCell cell;

            int rows; // No of rows
            rows = sheet.getLastRowNum() + 1; //getPhysicalNumberOfRows();
            System.out.println("ROWs number" + rows);
            System.out.println("Cell value: " + sheet.getRow(rows-1).getCell(0));
            
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

            for(int i = 8; i < rows; i++){
                
                row = sheet.getRow(i);
                if(row != null){
                    
                    //If the customer id null
                    if(row.getCell(ConfigFile.MA_DOI_TUONG_COL) == null ||
                            row.getCell(ConfigFile.X_COORDINATES_COL) == null ||
                            row.getCell(ConfigFile.Y_COORDINATES_COL) == null
                            ){
                        continue;   
                    }
                    
                    //Init Customer Object
                    Customer custumer = new Customer();
                    custumer.setCoordinateX(row.getCell(ConfigFile.X_COORDINATES_COL).getNumericCellValue());
                    custumer.setCoordinateY(row.getCell(ConfigFile.Y_COORDINATES_COL).getNumericCellValue());
                    
                    
                    int tmp = 0;
                    custumer.setStt((int)row.getCell(tmp++).getNumericCellValue());
                    custumer.setTinhThanh(row.getCell(tmp++).getStringCellValue());
                    custumer.setTuyenBanHangThu(row.getCell(tmp++).getStringCellValue());
                    custumer.setMaNhanVien(row.getCell(tmp++).getStringCellValue());
                    
                    custumer.setX(row.getCell(tmp++).getStringCellValue());
                    custumer.setMaDoiTuong(row.getCell(tmp++).getStringCellValue());
                    custumer.setDoiTuong(row.getCell(tmp++).getStringCellValue());
//                    custumer.setmNoDKy(row.getCell(tmp++).getNumericCellValue());
//                    
//                    custumer.setmCoDKy(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmNoTKy(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmTienBan(row.getCell(tmp++).getNumericCellValue());
//                    
//                    custumer.setmCoTKy(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmCKGG(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmNhapLai(row.getCell(tmp++).getNumericCellValue());
//                    
//                    custumer.setmNoCKy(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmCoCKy(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmDoanhThu(row.getCell(tmp++).getNumericCellValue());
//                    
//                    custumer.setmPhanTramNoChiaThu(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmNoToiDa(row.getCell(tmp++).getNumericCellValue());
//                    custumer.setmDaiDien(row.getCell(tmp++).getStringCellValue());
                    
                    custumer.setDiaChi(row.getCell(tmp++).getStringCellValue());
                    custumer.setDienThoai(row.getCell(tmp++).getStringCellValue());
                    custumer.setFax(row.getCell(tmp++).getStringCellValue());
                    
                    custumer.setGhiChu(row.getCell(tmp++).getStringCellValue());
                    
                    
                    listCustomer.add(custumer);
                    System.out.println("Add Object " + i);
                }
            }
            
        } catch(Exception ioe) {
            ioe.printStackTrace();
            return null;
        }
        
        return listCustomer;
    }
    
    public static void main(String [] arg){
        GetData data= new GetData("web/database/customer.xls");
        Customer cus = data.loadCustomer().get(0);
        
        System.out.println("Customer 1: "+ cus.getMaDoiTuong() +" X:" + cus.getCoordinateX() +", Y: "+ cus.getCoordinateY());
    }
}
