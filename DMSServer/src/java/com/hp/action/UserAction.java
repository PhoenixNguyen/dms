/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.action;

import com.hp.dao.AnnouncementDAO;
import com.hp.dao.AnnouncementDAOImpl;
import com.hp.dao.HistoryDAO;
import com.hp.dao.HistoryDAOImpl;
import com.hp.dao.UserDAO;
import com.hp.dao.UserDAOImpl;
import com.hp.domain.Announcement;
import com.hp.domain.History;
import com.hp.domain.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author HP
 */
public class UserAction extends ActionSupport{
    
    private UserDAO userDAO = new UserDAOImpl();
    private HistoryDAO historyDAO = new HistoryDAOImpl();
    
    private AnnouncementDAO announcementDAO = new AnnouncementDAOImpl();
    
    private Announcement announcement = new Announcement();

    private History history = new History();
    private User user = new User();
    
    private List<Announcement> announcementList = new ArrayList<Announcement>();

    public List<Announcement> getAnnouncementList() {
        return announcementList;
    }

    public void setAnnouncementList(List<Announcement> announcementList) {
        this.announcementList = announcementList;
    }
    
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
    
    public Announcement getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(Announcement announcement) {
        this.announcement = announcement;
    }
    
    public String login(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        String username = request.getParameter("user_name");
        String password = request.getParameter("user_password");
        
        session.setAttribute("user_name", username);
        session.setAttribute("user_password", password);
        
        
        System.out.println("user: " + username + " pass: " + password);
        
        session.setAttribute("click", true);
        session.setAttribute("authorized", false);
        
        if(userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
            //save history
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
            Calendar cal = Calendar.getInstance();
            
            //get ip
            InetAddress ip = null;
            try {

                  ip = InetAddress.getLocalHost();
                  System.out.println("Current IP address : " + ip.getHostAddress());

            } catch (UnknownHostException e) {

                  e.printStackTrace();

            }
            
            String time = sdf2.format(cal.getTime());
            history.setUser(username);
            history.setIp(ip.getHostAddress().toString());
            history.setLogin(Timestamp.valueOf(time));
            
            historyDAO.saveOrUpdate(history);
            
            int id = historyDAO.getHistory(time).getStt();
            //////////////////////////////////////////////////////////////////////////
            announcement = announcementDAO.getAnnouncement();
            announcementList = announcementDAO.loadAnnouncementList();
            
            user = userDAO.getUser(username);
                    
            session.setAttribute("announcement", announcement);
            session.setAttribute("authorized", true);
            session.setAttribute("USER", user);
            session.setAttribute("PERMISSION", user.getPermission());
            session.setAttribute("STT", user.getStt());
            
            session.setAttribute("history", id);
            return SUCCESS;
        }
        else
            return INPUT;
    }
    
    public String loginStatus(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        if(session.getAttribute("user_name") != null && session.getAttribute("user_password") != null)
        
            return SUCCESS;
        else
            return INPUT;
    }
    
    public String logout(){
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
        HttpSession session = request.getSession();
        
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        int id = 0;
        if(session.getAttribute("history") != null)
            id = (Integer)session.getAttribute("history");
        
        history = historyDAO.getHistory(id);
        if(history != null){
            history.setLogout(Timestamp.valueOf(time));
            historyDAO.saveOrUpdate(history);
        }
        
        session.setAttribute("user_name", null);
        session.setAttribute("user_password", null);
        session.setAttribute("authorized", false);
        session.setAttribute("USER", null);
        
        return SUCCESS;
    }
    
    public String home(){
        try {
            HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
            HttpSession session = request.getSession();
            
            if(!userDAO.authorize((String)session.getAttribute("user_name"), (String)session.getAttribute("user_password"))){
                return LOGIN;
            }
            
            ex(null);
            
            announcementList = announcementDAO.loadAnnouncementList();
            return SUCCESS;
        } catch (ParsePropertyException ex) {
            Logger.getLogger(UserAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserAction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(UserAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return SUCCESS;
        
    }
    
    private void ex(HttpServletResponse response) throws ParsePropertyException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
        	List<Student> students = new ArrayList<Student>();
		Random r = new Random();
		for(int i = 0; i < 10; i++) {
			Student student = new Student();
			student.setName("Student "  + (i+1));
			student.setAge(r.nextInt(30));
			
			students.add(student);
		}
		
		export(response, students, "student", "C:\\Users\\anhhn\\Desktop/student.xls", "C:\\Users\\anhhn\\Desktop/student_list.xls");

    }
    private void export(HttpServletResponse response, List<?> dataList, String dataKey, String tempFilePath, String desFilePath) throws ParsePropertyException, org.apache.poi.openxml4j.exceptions.InvalidFormatException, FileNotFoundException, IOException {
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
        String fileName = desFilePath.substring(desFilePath.lastIndexOf("/") + 1);
//        Náº¿u download file excel
//        response.setContentType("application/vnd.ms-excel");
//        response.setHeader("Content-Disposition", "attachment; filename="+fileName);
//        OutputStream outputStream = response.getOutputStream();
        OutputStream outputStream = new FileOutputStream(desFilePath);
        
        workbook.setSheetName(0, fileName.substring(0, fileName.lastIndexOf(".")));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        
        System.out.println("Export is OK!");
	}
    
    public class Student implements Serializable {
		private String name;
		private int age;
		
		public String getName() {
			return name;
		} 
		public void setName(String name) {
			this.name = name;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
	}
}
