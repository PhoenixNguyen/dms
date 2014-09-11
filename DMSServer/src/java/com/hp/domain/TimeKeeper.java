/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_timekeeper")
public class TimeKeeper implements Serializable{
    /**
     * Timekeeper for staff
     * 
     */
    
    @Id
    @GeneratedValue
    
    @Column(name="stt")
    private int stt;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "nhanvien_ma_nhan_vien")
    private Staff staff;
    
    @Column(name="time_at")
    private Timestamp timeAt;
    @Column(name="province")
    private String province;
    @Column(name="time_between")
    private float timeBetween;

    @Column(name="note")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
        
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Timestamp getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(Timestamp timeAt) {
        this.timeAt = timeAt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public float getTimeBetween() {
        return timeBetween;
    }

    public void setTimeBetween(float timeBetween) {
        this.timeBetween = timeBetween;
    }
            
}
