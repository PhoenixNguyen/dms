/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_khachhang_hinhanh")

public class CustomerImage {

    @Id
    @GeneratedValue
    
    @Column(name="hinhanh_stt")
    private int stt;
    @Column(name="hinhanh_id")
    private String id;
    @Column(name="hinhanh_name")
    private String name;
    @Column(name="hinhanh_ma_khach_hang")
    private String customerID;
    @Column(name="hinhanh_ma_nhan_vien")
    private String staffID;
    @Column(name="hinhanh_ghi_chu")
    private String note;
    @Column(name="hinhanh_time")
    private Timestamp time;

    @Column(name="hinhanh_trang_thai")
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
