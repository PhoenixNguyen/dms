/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;

/**
 *
 * @author HP
 */

public class TimeKeeper{
    /**
     * Timekeeper for staff
     * 
     */
    
    private int stt;
	private Staff staff;
    
    private Timestamp timeAt;
    private String province;
    private float timeBetween;

    private String note;

    public TimeKeeper(){
    	
    }
    public TimeKeeper(Staff staff, Timestamp timeAt, String province,
			float timeBetween, String note) {
		super();
		this.staff = staff;
		this.timeAt = timeAt;
		this.province = province;
		this.timeBetween = timeBetween;
		this.note = note;
	}
    
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
