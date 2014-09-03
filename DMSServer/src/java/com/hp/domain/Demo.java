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
@Table(name="tb_khachhang")

public class Demo {
    
    private int mStt;
    private String mTinhThanh;
    private String mTuyenBanHangThu;
    private String mMaNhanVien;
    private String mX;
    private String mMaDoiTuong;
    private String mDoiTuong;
    private Float mNoDKy;
    private Float mCoDKy;
    private Float mNoTKy;
    private Float mTienBan;
    private Float mCoTKy;
    private Float mCKGG;
    private Float mNhapLai;
    private Float mNoCKy;
    private Float mCoCKy;
    private Float mDoanhThu;
    private Float mPhanTramNoChiaThu;
    private Float mNoToiDa;
    private String mDaiDien;
    private String mDiaChi;
    private String mDienThoai;
    private String mFax;
    private String mGhiChu;
    
    private Float mXCoordinates;
    private Float mYCoordinates;

    @Id
    @GeneratedValue
    
    @Column(name="khachhang_stt")
    public int getmStt() {
        return mStt;
    }

    public void setmStt(int mStt) {
        this.mStt = mStt;
    }

    @Column(name="khachhang_tinh_thanh")
    public String getmTinhThanh() {
        return mTinhThanh;
    }

    public void setmTinhThanh(String mTinhThanh) {
        this.mTinhThanh = mTinhThanh;
    }
    @Column(name="khachhang_tuyen_ban_hang_thu")
    public String getmTuyenBanHangThu() {
        return mTuyenBanHangThu;
    }

    public void setmTuyenBanHangThu(String mTuyenBanHangThu) {
        this.mTuyenBanHangThu = mTuyenBanHangThu;
    }
    @Column(name="khachhang_ma_nv")
    public String getmMaNhanVien() {
        return mMaNhanVien;
    }

    public void setmMaNhanVien(String mMaNhanVien) {
        this.mMaNhanVien = mMaNhanVien;
    }
    @Column(name="khachhang_x")
    public String getmX() {
        return mX;
    }

    public void setmX(String mX) {
        this.mX = mX;
    }
    @Column(name="khachhang_ma_dt")
    public String getmMaDoiTuong() {
        return mMaDoiTuong;
    }

    public void setmMaDoiTuong(String mMaDoiTuong) {
        this.mMaDoiTuong = mMaDoiTuong;
    }
    @Column(name="khachhang_doi_tuong")
    public String getmDoiTuong() {
        return mDoiTuong;
    }

    public void setmDoiTuong(String mDoiTuong) {
        this.mDoiTuong = mDoiTuong;
    }
    @Column(name="khachhang_no_dky")
    public Float getmNoDKy() {
        return mNoDKy;
    }

    public void setmNoDKy(Float mNoDKy) {
        this.mNoDKy = mNoDKy;
    }
    @Column(name="khachhang_co_dky")
    public Float getmCoDKy() {
        return mCoDKy;
    }

    public void setmCoDKy(Float mCoDKy) {
        this.mCoDKy = mCoDKy;
    }
    @Column(name="khachhang_no_tky")
    public Float getmNoTKy() {
        return mNoTKy;
    }

    public void setmNoTKy(Float mNoTKy) {
        this.mNoTKy = mNoTKy;
    }
    @Column(name="khachhang_tien_ban")
    public Float getmTienBan() {
        return mTienBan;
    }

    public void setmTienBan(Float mTienBan) {
        this.mTienBan = mTienBan;
    }
    @Column(name="khachhang_co_tky")
    public Float getmCoTKy() {
        return mCoTKy;
    }

    public void setmCoTKy(Float mCoTKy) {
        this.mCoTKy = mCoTKy;
    }
    @Column(name="khachhang_ck_gg")
    public Float getmCKGG() {
        return mCKGG;
    }

    public void setmCKGG(Float mCKGG) {
        this.mCKGG = mCKGG;
    }
    @Column(name="khachhang_nhap_lai")
    public Float getmNhapLai() {
        return mNhapLai;
    }

    public void setmNhapLai(Float mNhapLai) {
        this.mNhapLai = mNhapLai;
    }
    @Column(name="khachhang_no_cky")
    public Float getmNoCKy() {
        return mNoCKy;
    }

    public void setmNoCKy(Float mNoCKy) {
        this.mNoCKy = mNoCKy;
    }
    @Column(name="khachhang_co_cky")
    public Float getmCoCKy() {
        return mCoCKy;
    }

    public void setmCoCKy(Float mCoCKy) {
        this.mCoCKy = mCoCKy;
    }
    @Column(name="khachhang_doanh_thu")
    public Float getmDoanhThu() {
        return mDoanhThu;
    }

    public void setmDoanhThu(Float mDoanhThu) {
        this.mDoanhThu = mDoanhThu;
    }
    @Column(name="khachhang_ti_le_no_thu")
    public Float getmPhanTramNoChiaThu() {
        return mPhanTramNoChiaThu;
    }

    public void setmPhanTramNoChiaThu(Float mPhanTramNoChiaThu) {
        this.mPhanTramNoChiaThu = mPhanTramNoChiaThu;
    }
    @Column(name="khachhang_no_toi_da")
    public Float getmNoToiDa() {
        return mNoToiDa;
    }

    public void setmNoToiDa(Float mNoToiDa) {
        this.mNoToiDa = mNoToiDa;
    }
    @Column(name="khachhang_dai_dien")
    public String getmDaiDien() {
        return mDaiDien;
    }

    public void setmDaiDien(String mDaiDien) {
        this.mDaiDien = mDaiDien;
    }
    @Column(name="khachhang_diachi")
    public String getmDiaChi() {
        return mDiaChi;
    }

    public void setmDiaChi(String mDiaChi) {
        this.mDiaChi = mDiaChi;
    }
    @Column(name="khachhang_dien_thoai")
    public String getmDienThoai() {
        return mDienThoai;
    }

    public void setmDienThoai(String mDienThoai) {
        this.mDienThoai = mDienThoai;
    }
    @Column(name="khachhang_fax")
    public String getmFax() {
        return mFax;
    }

    public void setmFax(String mFax) {
        this.mFax = mFax;
    }
    @Column(name="khachhang_ghi_chu")
    public String getmGhiChu() {
        return mGhiChu;
    }

    public void setmGhiChu(String mGhiChu) {
        this.mGhiChu = mGhiChu;
    }
    @Column(name="khachhang_toa_do_x")         
    public Float getmXCoordinates() {
        return mXCoordinates;
    }

    public void setmXCoordinates(Float mXCoordinates) {
        this.mXCoordinates = mXCoordinates;
    }
    @Column(name="khachhang_toa_do_y")
    public Float getmYCoordinates() {
        return mYCoordinates;
    }

    public void setmYCoordinates(Float mYCoordinates) {
        this.mYCoordinates = mYCoordinates;
    }
   
}
