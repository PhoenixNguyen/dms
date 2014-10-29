/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.util.Date;

/**
 *
 * @author admin
 */
public class LastLocation {
    private String maNhanVien;
    private String address;
    
    private String lastAddress;
    private Date timeLast;

    public LastLocation(String maNhanVien, String address, String lastAddress, Date timeLast) {
        this.maNhanVien = maNhanVien;
        this.address = address;
        this.lastAddress = lastAddress;
        this.timeLast = timeLast;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastAddress() {
        return lastAddress;
    }

    public void setLastAddress(String lastAddress) {
        this.lastAddress = lastAddress;
    }

    public Date getTimeLast() {
        return timeLast;
    }

    public void setTimeLast(Date timeLast) {
        this.timeLast = timeLast;
    }
    
}
