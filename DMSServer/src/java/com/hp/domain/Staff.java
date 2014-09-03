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
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;
import org.hibernate.validator.Valid;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_nhanvien")
public class Staff {
    @Id
    @GeneratedValue
    
    @Column(name="nhanvien_stt")
    private Integer stt;

    @NotEmpty(message = "Mã Nhân viên không được trống")
    @Pattern(regex="[A-Za-z0-9\\._]+",message="Mã Nhân viên chỉ chứa a-z, A-Z, 0-9, .")
    @Length(min = 3 , max = 255 ,message= "Mã Nhân viên phải có độ dài 3-255 ký tự")
    @Column(name="nhanvien_ma_nhan_vien")
    @Valid
    private String id;
    
    @NotEmpty(message = "Mật khẩu không được trống")
    //@Pattern(regex="[A-Za-z0-9\\._]+",message="Mật khẩu chỉ chứa a-z, A-Z, 0-9, .")
    @Length(min = 3 , max = 255 ,message= "Mật khẩu phải có độ dài 3-255 ký tự")
    @Valid
    @Column(name="nhanvien_mat_khau")
    private String pw;

    @Column(name="nhanvien_ho_ten")
    private String name;
    @Column(name="nhanvien_dia_chi")
    private String adress;
    @Column(name="nhanvien_chuc_vu")
    private String job;
    @Column(name="nhanvien_so_dien_thoai")
    private String phone;
    @Column(name="nhanvien_ngay_gia_nhap")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    @Column(name="nhanvien_nguoi_quan_ly")
    private String manager;
    @Column(name="nhanvien_trang_thai")
    private boolean status;
    
    @Column(name="nhanvien_permission")
    private Integer permission;

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
    
    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

        
}
