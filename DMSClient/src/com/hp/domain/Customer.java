/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

/**
 *
 * @author HP
 */

public class Customer {
    
	private int stt;

    private String tinhThanh;
    private String tuyenBanHangThu;
    private String maNhanVien;
    private String x;
    private String maDoiTuong;
    private String doiTuong;
    private String diaChi;
    private String dienThoai;
    private String fax;
    private String ghiChu;
    private Double coordinateX;
    private Double coordinateY;

    
    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTinhThanh() {
        return tinhThanh;
    }

    public void setTinhThanh(String tinhThanh) {
        this.tinhThanh = tinhThanh;
    }

    public String getTuyenBanHangThu() {
        return tuyenBanHangThu;
    }

    public void setTuyenBanHangThu(String tuyenBanHangThu) {
        this.tuyenBanHangThu = tuyenBanHangThu;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getMaDoiTuong() {
        return maDoiTuong;
    }

    public void setMaDoiTuong(String maDoiTuong) {
        this.maDoiTuong = maDoiTuong;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(Double coordinateX) {
        this.coordinateX = coordinateX;
    }

    public Double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(Double coordinateY) {
        this.coordinateY = coordinateY;
    }
    

    public Customer(){
    	
    }
    
    public Customer(String pMaDoiTuong, String pDoiTuong, String pMaNhanVien,
			String pTinhThanh, String pTuyenBanHangThu, String pX,
			String pDiaChi, String pDienThoai, String pFax, String pGhiChu,
			Double pCoordinateX, Double pCoordinateY) {
		super();
		this.maDoiTuong = pMaDoiTuong;
		this.doiTuong = pDoiTuong;
		this.maNhanVien = pMaNhanVien;
		this.tinhThanh = pTinhThanh;
		this.tuyenBanHangThu = pTuyenBanHangThu;
		this.x = pX;
		this.diaChi = pDiaChi;
		this.dienThoai = pDienThoai;
		this.fax = pFax;
		this.ghiChu = pGhiChu;
		this.coordinateX = pCoordinateX;
		this.coordinateY = pCoordinateY;
	}


    public String toString(){
    	return maDoiTuong + " " + doiTuong + " " + maNhanVien;
    }
    

    
}
