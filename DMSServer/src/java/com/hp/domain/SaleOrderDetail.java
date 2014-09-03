/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * variabe in Object compatable with TakeOrderDetail
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
@Table(name="tb_chitietdonbanhang")

public class SaleOrderDetail {
    @Id
    @GeneratedValue
    
    @Column(name="chitietdonbanhang_stt")
    private Integer serial;

    @Column(name="chitietdonbanhang_ma_hoa_don")
    private String takeOrderID;
    @Column(name="chitietdonbanhang_dong")
    private Integer line;
    @Column(name="chitietdonbanhang_ma_hang")
    private String productID;
    @Column(name="chitietdonbanhang_ma_vach")
    private String barcode;
    @Column(name="chitietdonbanhang_ten_san_pham")
    private String productName;
    @Column(name="chitietdonbanhang_don_gia_sau_thue", nullable = true)
    private Float afterOrderPrice;
    @Column(name="chitietdonbanhang_don_gia_truoc_thue", nullable = true)
    private Float beforeOrderPrice;
    @Column(name="chitietdonbanhang_thue", nullable = true)
    private Float tax;
    @Column(name="chitietdonbanhang_giam_gia" , nullable = true)
    private Integer discount;
    @Column(name="chitietdonbanhang_thanh_tien", nullable = true)
    private Float priceTotal;
    @Column(name="chitietdonbanhang_ma_kho", nullable = true)
    private String stockID;
    @Column(name="chitietdonbanhang_so_luong")
    private Integer number;
    @Column(name="chitietdonbanhang_don_vi_tinh")
    private String unit;
    @Column(name="chitietdonbanhang_ty_gia", nullable = true)
    private Float exchangeRate;
    @Column(name="chitietdonbanhang_ghi_chu")
    private String note;
    @Column(name="chitietdonbanhang_hang_khuyen_mai")
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
    
    
    public SaleOrderDetail(String mTakeOrderID, Integer mLine, String mProductID, String mBarcode, 
            String mProductName, Float mAfterOrderPrice, Float mBeforeOrderPrice, Float mTax, 
            Integer mDiscount, Float mPriceTotal, String mStockID, Integer mNumber, String mUnit, Float mExchangeRate) {
        this.takeOrderID = mTakeOrderID;
        this.line = mLine;
        this.productID = mProductID;
        this.barcode = mBarcode;
        this.productName = mProductName;
        this.afterOrderPrice = mAfterOrderPrice;
        this.beforeOrderPrice = mBeforeOrderPrice;
        this.tax = mTax;
        this.discount = mDiscount;
        this.priceTotal = mPriceTotal;
        this.stockID = mStockID;
        this.number = mNumber;
        this.unit = mUnit;
        this.exchangeRate = mExchangeRate;
    }
    
    public SaleOrderDetail(TakeOrderDetail pTakeOrderDetail){
        this.takeOrderID = pTakeOrderDetail.getTakeOrderID();
        this.line = pTakeOrderDetail.getLine();
        this.productID = pTakeOrderDetail.getProductID();
        this.barcode = pTakeOrderDetail.getBarcode();
        this.productName = pTakeOrderDetail.getProductName();
        this.afterOrderPrice = pTakeOrderDetail.getAfterOrderPrice();
        this.beforeOrderPrice = pTakeOrderDetail.getBeforeOrderPrice();
        this.tax = pTakeOrderDetail.getTax();
        this.discount = pTakeOrderDetail.getDiscount();
        this.priceTotal = pTakeOrderDetail.getPriceTotal();
        this.stockID = pTakeOrderDetail.getStockID();
        this.number = pTakeOrderDetail.getNumber();
        this.unit = pTakeOrderDetail.getUnit();
        this.exchangeRate = pTakeOrderDetail.getExchangeRate();
    }
    public SaleOrderDetail(){
        
    }
    
}
