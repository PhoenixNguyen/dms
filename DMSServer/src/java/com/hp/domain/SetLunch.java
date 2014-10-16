/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_setlunch")
public class SetLunch {
    @Id
    @GeneratedValue
    
    @Column(name="stt")
    private int stt;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "nhanvien_ma_nhan_vien")
    private Staff staff;
    
    @Column(name="time_at")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeAt;
    @Column(name="created_time")
    private Timestamp createdTime;

    @Column(name="content")
    private String content;
    
    @Column(name="note")
    private String note;

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

    public Date getTimeAt() {
        return timeAt;
    }

    public void setTimeAt(Date timeAt) {
        this.timeAt = timeAt;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
