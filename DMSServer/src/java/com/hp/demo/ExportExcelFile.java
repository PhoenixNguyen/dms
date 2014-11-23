/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author admin
 */
public class ExportExcelFile {
    public static void main(String[] args ) throws ParsePropertyException, IOException, InvalidFormatException{
        String log4jConfPath = "C:\\Users\\anhhn\\Desktop\\log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        List<Student> studentList = new ArrayList<Student>();
        
        for(int i=0; i < 10; i++){
            Student student = new Student();
            student.setName("Name is " +i );
            student.setOld( i * 10);
            
            studentList.add(student);
        }
        
        //String fileInput = ServletActionContext.getServletContext().getRealPath("/db_templates/") ;
        String templateFile = "C:\\Users\\anhhn\\Desktop\\report_calendar.xls";
        System.out.println("templateFile: " + templateFile);

        export(studentList, "model", templateFile, "C:\\Users\\anhhn\\Desktop", null, null);

    }
    
    private static void export(List<?> dataList, String dataKey, String tempFilePath, String desFilePath, HttpServletRequest request, HttpServletResponse response) throws ParsePropertyException, IOException, InvalidFormatException {
        
        Map<String, Object> beans = new HashMap<String, Object>();
        beans.put(dataKey, dataList);

        XLSTransformer transformer = new XLSTransformer();
        File tempFile = new File(tempFilePath);
        if(!tempFile.exists()) {
                System.out.println("Template file not found!");
                return;
        }

        Workbook workbook = transformer.transformXLS(new FileInputStream(tempFile), beans);
        //
        String fileName = "Bao Cao " + ".xls";
        //Download file
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
        //OutputStream outputStream = response.getOutputStream();
        //Write to hardware
        OutputStream outputStream = new FileOutputStream(desFilePath);

        workbook.setSheetName(0, fileName.substring(0, fileName.lastIndexOf(".")));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();

        System.out.println("Export is OK!");
    }
    
    public static class Student{
        public String name;
        public int old;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
        
    }
}
