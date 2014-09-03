/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

/**
 *
 * @author HP
 */

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_quanlyduongdi")

public class RoadManagement {
    @Id
    @GeneratedValue
    
    @Column(name="quanlyduongdi_stt")
    private int stt;

    @Column(name="quanlyduongdi_ma_nhan_vien")
    private String maNhanVien;
    @Column(name="quanlyduongdi_ten_nhan_vien")
    private String tenNhanVien;
    @Column(name="quanlyduongdi_thoi_gian")
    private Timestamp thoiGian;
    @Column(name="quanlyduongdi_vi_do")
    private Float viDo;
    @Column(name="quanlyduongdi_kinh_do")
    private Float kinhDo;
    @Column(name="quanlyduongdi_ghi_chu")
    private String ghiChu;

    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }
    
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Float getViDo() {
        return viDo;
    }

    public void setViDo(Float viDo) {
        this.viDo = viDo;
    }

    public Float getKinhDo() {
        return kinhDo;
    }

    public void setKinhDo(Float kinhDo) {
        this.kinhDo = kinhDo;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    public RoadManagement(String pMaNhanVien, String pMaKhachHang, Timestamp pThoiGian, 
    		Float pViDo, Float pKinhdo, String pGhiChu){
    	this.maNhanVien = pMaNhanVien;
    	this.tenNhanVien = pMaKhachHang;
    	this.thoiGian = pThoiGian;
    	this.viDo = pViDo;
    	this.kinhDo = pKinhdo;
    	this.ghiChu = pGhiChu;
    }
    
    public RoadManagement(){
        
    }
}
