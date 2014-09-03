/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

//import javax.xml.bind.annotation.XmlElement;
//import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author HP
 */


public class Schedule {
    
    private int stt;

    private String maNV;
    private String maKH;
    private Timestamp time;
    private boolean status;

    private String tenKhachHang;
    private String tenNhanVien;

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }
    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp date) {
        this.time = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    

    @SuppressWarnings("deprecation")
	public Schedule(String pMaNV, String pMaKH, Timestamp pDate, boolean pStatus, 
			String tenKhachHang, String tenNhanVien){
    	this.maNV = pMaNV;
    	this.maKH = pMaKH;
    	this.status = pStatus;
    	    	
    	this.time = pDate;
    	
    	this.tenKhachHang = tenKhachHang;
    	this.tenNhanVien = tenNhanVien;
    }
    
    public Schedule(){
    	
    }
    
    
    
}
