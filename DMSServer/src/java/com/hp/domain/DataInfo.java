package com.hp.domain;

public class DataInfo {
	private String nhanVien;
	private String khachHang;
	private String noiDung;
	private String ghiChu;
        private String tenKhachHang;

    
	
	public DataInfo(){
		
	}
	
	public DataInfo(String nhanVien, String khachHang, String noiDung,
			String ghiChu) {
		super();
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.noiDung = noiDung;
		this.ghiChu = ghiChu;
	}
	
	public String getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(String nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getKhachHang() {
		return khachHang;
	}
	public void setKhachHang(String khachHang) {
		this.khachHang = khachHang;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	
	public String getTenKhachHang() {
            return tenKhachHang;
        }

        public void setTenKhachHang(String tenKhachHang) {
            this.tenKhachHang = tenKhachHang;
        }
}
