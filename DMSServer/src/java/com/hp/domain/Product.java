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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotEmpty;
import org.hibernate.validator.Pattern;
import org.hibernate.validator.Valid;

/**
 *
 * @author HP
 */
@Entity
@Table(name="tb_sanpham")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
    
    @Id
    @GeneratedValue
    
    @Column(name="sanpham_stt")
    private Integer serial;
    
    @NotEmpty(message = "Mã sản phẩm không được trống")
//    @Pattern(regex="[A-Za-z0-9\\._]+",message="Mã sản phẩm chỉ chứa a-z, A-Z, 0-9, ., _")
//    @Length(min = 3 , max = 255 ,message= "Mã sản phẩm phải có độ dài 3-255 ký tự")
    @Column(name="sanpham_ma_san_pham")
    private String productID;
    @Column(name="sanpham_ma_vach")
    private String barcode;
    @Column(name="sanpham_ten_hang_hoa")
    private String productName;
    @Column(name="sanpham_thuong_hieu")
    private String brand;
    @Column(name="sanpham_xuat_xu")
    private String origin;
    @Column(name="sanpham_quy_cach_packing")
    private String packingSpecifications;
    @Column(name="sanpham_dinh_luong")
    private String quantification;
    @Column(name="sanpham_thue")
    private Float vatTax;
    @Column(name="sanpham_gia_nhap")
    private Float importPrices;
    @Column(name="sanpham_gia_ban")
    private Float exportPrices;
    @Column(name="sanpham_nha_cung_cap")
    private String provider;
    @Column(name="sanpham_mo_ta")
    private String description;
    @Column(name="sanpham_anh_san_pham")
    private String productImage;

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackingSpecifications() {
        return packingSpecifications;
    }

    public void setPackingSpecifications(String packingSpecifications) {
        this.packingSpecifications = packingSpecifications;
    }

    public String getQuantification() {
        return quantification;
    }

    public void setQuantification(String quantification) {
        this.quantification = quantification;
    }

    public Float getVatTax() {
        return vatTax;
    }

    public void setVatTax(Float vatTax) {
        this.vatTax = vatTax;
    }

    public Float getImportPrices() {
        return importPrices;
    }

    public void setImportPrices(Float importPrices) {
        this.importPrices = importPrices;
    }

    public Float getExportPrices() {
        return exportPrices;
    }

    public void setExportPrices(Float exportPrices) {
        this.exportPrices = exportPrices;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    
}
