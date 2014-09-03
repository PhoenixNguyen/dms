/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_user")
public class User {
    @Id
    @GeneratedValue
    
    @Column(name="user_stt")
    private int stt;

    @Column(name="user_id")
    private String id;
    @Column(name="user_pw")
    private String pw;

    @Column(name="user_chuc_danh")
    private String chucDanh;
    @Column(name="user_ho_ten")
    private String hoTen;
    @Column(name="user_email")
    private String email;
    @Column(name="user_so_dien_thoai")
    private String sdt;
    @Column(name="user_dia_chi")
    private String diaChi;
    @Column(name="user_ghi_chu")
    private String ghiChu;
    @Column(name="user_ngay_tham_gia")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayThamGia;
    @Column(name="user_permission")
    private int permission;
    @Column(name="user_status")
    private boolean status;

    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
    
    public String getChucDanh() {
        return chucDanh;
    }

    public void setChucDanh(String chucDanh) {
        this.chucDanh = chucDanh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayThamGia() {
        return ngayThamGia;
    }

    public void setNgayThamGia(Date ngayThamGia) {
        this.ngayThamGia = ngayThamGia;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
