/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author HP
 */

public class SetLunch {
    
    private int stt;
	private Staff staff;
    private Date timeAt;
    private Timestamp createdTime;
    private String content;
    private String note;

    public SetLunch(){}
    public SetLunch(Staff staff, Date timeAt,
			String content, String note) {
		super();
		this.staff = staff;
		this.timeAt = timeAt;
		this.content = content;
		this.note = note;
	}
    
    @Override
	public String toString() {
		return "SetLunch [timeAt=" + timeAt + ", createdTime=" + createdTime
				+ ", content=" + content + ", note=" + note + "]";
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
