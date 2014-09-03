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
@Table(name="tb_chitietdondathang")

public class TakeOrderDetail {
    @Id
    @GeneratedValue
    
    @Column(name="chitietdondathang_stt")
    private Integer serial;

    @Column(name="chitietdondathang_ma_hoa_don")
    private String takeOrderID;
    @Column(name="chitietdondathang_dong")
    private Integer line;
    @Column(name="chitietdondathang_ma_hang")
    private String productID;
    @Column(name="chitietdondathang_ma_vach")
    private String barcode;
    @Column(name="chitietdondathang_ten_san_pham")
    private String productName;
    @Column(name="chitietdondathang_don_gia_sau_thue", nullable = true)
    private Float afterOrderPrice;
    @Column(name="chitietdondathang_don_gia_truoc_thue", nullable = true)
    private Float beforeOrderPrice;
    @Column(name="chitietdondathang_thue", nullable = true)
    private Float tax;
    @Column(name="chitietdondathang_giam_gia" , nullable = true)
    private Integer discount;
    @Column(name="chitietdondathang_thanh_tien", nullable = true)
    private Float priceTotal;
    @Column(name="chitietdondathang_ma_kho", nullable = true)
    private String stockID;
    @Column(name="chitietdondathang_so_luong")
    private Integer number;
    @Column(name="chitietdondathang_don_vi_tinh")
    private String unit;
    @Column(name="chitietdondathang_ty_gia", nullable = true)
    private Float exchangeRate;
    @Column(name="chitietdondathang_ghi_chu")
    private String note;
    @Column(name="chitietdondathang_hang_khuyen_mai")
    private int promotionalProductMount;

    
    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getTakeOrderID() {
        return takeOrderID;
    }

    public void setTakeOrderID(String takeOrderID) {
        this.takeOrderID = takeOrderID;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
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

    public Float getAfterOrderPrice() {
        return afterOrderPrice;
    }

    public void setAfterOrderPrice(Float afterOrderPrice) {
        this.afterOrderPrice = afterOrderPrice;
    }

    public Float getBeforeOrderPrice() {
        return beforeOrderPrice;
    }

    public void setBeforeOrderPrice(Float beforeOrderPrice) {
        this.beforeOrderPrice = beforeOrderPrice;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Float getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(Float priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Float exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPromotionalProductMount() {
        return promotionalProductMount;
    }

    public void setPromotionalProductMount(int promotionalProductMount) {
        this.promotionalProductMount = promotionalProductMount;
    }
        
    public Object[] toArray(){
        return new Object[] {
            takeOrderID,
            line,
            productID,
            barcode,
            productName,
            unit,
            afterOrderPrice,
            beforeOrderPrice,
            number,
            tax,
            exchangeRate,
            discount,
            priceTotal,
            stockID
                      
        };
    }
    
    public Object[] toTitleArray(){
        return new Object[] {
            "Mã hóa đơn",
            "Dòng",
            "Mã sản phẩm",
            "Mã vạch",
            "Tên sản phẩm",
            "Đơn vị",
            "Giá trước thuế",
            "Giá sau thuế",
            "Số lượng",
            "Thuế",
            "Tỷ giá",
            "Giảm giá",
            "Giá tổng",
            "Mã kho"
            
            
            
        };
    }
       
}
