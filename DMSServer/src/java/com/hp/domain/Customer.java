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
import org.hibernate.annotations.Type;
import org.hibernate.validator.Length;
import org.hibernate.validator.Max;
import org.hibernate.validator.Min;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;
import org.hibernate.validator.Valid;

/**
 *
 * @author HP
 */
@Entity
@Table(name="tb_khachhang")

public class Customer {
    @Id
    @GeneratedValue
    
    @Column(name="khachhang_stt")
    private int stt;

    @Column(name="khachhang_tinh_thanh")
    private String tinhThanh;
    @Column(name="khachhang_tuyen_ban_hang_thu")
    private String tuyenBanHangThu;
    @Column(name="khachhang_ma_nv")
    private String maNhanVien;
    @Column(name="khachhang_x")
    private String x;
      
    @NotEmpty(message = "Mã khách hàng không được trống")
//    @Pattern(regex="[A-Za-z0-9\\._]+",message="Mã khách hàng chỉ chứa a-z, A-Z, 0-9, .")
//    @Length(min = 3 , max = 255 ,message= "Mã khách hàng phải có độ dài 3-255 ký tự")
    @Column(name="khachhang_ma_dt")
    @Valid
    private String maDoiTuong;
    @Column(name="khachhang_doi_tuong")
    private String doiTuong;
    
    @Column(name="khachhang_diachi")
    private String diaChi;
    @Column(name="khachhang_dien_thoai")
    private String dienThoai;
    @Column(name="khachhang_fax")
    private String fax;
    @Column(name="khachhang_ghi_chu")
    private String ghiChu;
    
    @Column(name="khachhang_toa_do_x")
    private Double coordinateX;
    @Column(name="khachhang_toa_do_y")
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
}
