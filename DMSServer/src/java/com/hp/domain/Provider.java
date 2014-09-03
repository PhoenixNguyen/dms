/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

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
@Table(name="tb_nhacungcap")
public class Provider {
    @Id
    @GeneratedValue
    
    @Column(name="nhacungcap_stt")
    private int serial;

    @Column(name="nhacungcap_id")
    private String id;
    @Column(name="nhacungcap_ten")
    private String name;
    @Column(name="nhacungcap_dia_chi")
    private String address;
    @Column(name="nhacungcap_dien_thoai")
    private String phoneNumber;
    @Column(name="nhacungcap_fax")
    private String fax;
    @Column(name="nhacungcap_ghi_chu")
    private String note;
    
    
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
