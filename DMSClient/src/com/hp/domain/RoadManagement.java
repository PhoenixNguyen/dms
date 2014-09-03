package com.hp.domain;

import java.sql.Timestamp;

public class RoadManagement {

	private int stt;
	
	private String maNhanVien;
	private String tenNhanVien;
    private Timestamp thoiGian;
    private float viDo;
    private float kinhDo;
    private String ghiChu;
    
    public RoadManagement(String pMaNhanVien, String pTenNhanVien, Timestamp pThoiGian, 
    		float pViDo, float pKinhdo, String pGhiChu){
    	this.maNhanVien = pMaNhanVien;
    	this.tenNhanVien = pTenNhanVien;
    	this.thoiGian = pThoiGian;
    	this.viDo = pViDo;
    	this.kinhDo = pKinhdo;
    	this.ghiChu = pGhiChu;
    }
    
    public int getStt() {
		return stt;
	}

	public void setStt(int stt) {
		this.stt = stt;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public Timestamp getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}

	public float getViDo() {
		return viDo;
	}

	public void setViDo(float viDo) {
		this.viDo = viDo;
	}

	public float getKinhDo() {
		return kinhDo;
	}

	public void setKinhDo(float kinhDo) {
		this.kinhDo = kinhDo;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	
}
