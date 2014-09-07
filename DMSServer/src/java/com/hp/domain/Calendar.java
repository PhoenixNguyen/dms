/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_calendar")
public class Calendar implements Serializable{
    /**
     * Calendar for staff
     * status: 0: INIT
     *         1: Completed
     */
    @Id
    @GeneratedValue
    
    @Column(name="stt")
    private int stt;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "staff_id", referencedColumnName = "nhanvien_ma_nhan_vien")
    private Staff staff;
    @Column(name="calendar_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date calendarDate;
    @Column(name="province")
    private String province;
    @Column(name="content")
    private String content;
    
    @Column(name="report")
    private String report;
    @Column(name="contributor")
    private String contributor;
    @Column(name="mission")
    private String mission;
    @Column(name="support")
    private String support;
    
    @Column(name="status")
    private int status;
    @Column(name="created_time")
    private Timestamp createdTime;
    @Column(name="updated_time")
    private Timestamp updatedTime;
    
    @Column(name="coordinate_x")
    private float coordinateX;
    @Column(name="coordinate_y")
    private float coordinateY;
    
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

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Timestamp updatedTime) {
        this.updatedTime = updatedTime;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
}
