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
@Table(name="tb_chitietdontrahang")

public class ReturnOrderDetail {
    @Id
    @GeneratedValue
    
    @Column(name="chitietdontrahang_stt")
    private Integer serial;

    @Column(name="chitietdontrahang_ma_hoa_don")
    private String takeOrderID;
    @Column(name="chitietdontrahang_dong")
    private Integer line;
    @Column(name="chitietdontrahang_ma_hang")
    private String productID;
    @Column(name="chitietdontrahang_ma_vach")
    private String barcode;
    @Column(name="chitietdontrahang_ten_san_pham")
    private String productName;
    @Column(name="chitietdontrahang_don_gia_sau_thue", nullable = true)
    private Float afterOrderPrice;
    @Column(name="chitietdontrahang_don_gia_truoc_thue", nullable = true)
    private Float beforeOrderPrice;
    @Column(name="chitietdontrahang_thue", nullable = true)
    private Float tax;
    @Column(name="chitietdontrahang_giam_gia" , nullable = true)
    private Integer discount;
    @Column(name="chitietdontrahang_thanh_tien", nullable = true)
    private Float priceTotal;
    @Column(name="chitietdontrahang_ma_kho", nullable = true)
    private String stockID;
    @Column(name="chitietdontrahang_so_luong")
    private Integer number;
    @Column(name="chitietdontrahang_don_vi_tinh")
    private String unit;
    @Column(name="chitietdontrahang_ty_gia", nullable = true)
    private Float exchangeRate;
    @Column(name="chitietdontrahang_ghi_chu")
    private String note;
    @Column(name="chitietdontrahang_hang_khuyen_mai")
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
   
    public ReturnOrderDetail(){
        
    }
    public ReturnOrderDetail(SaleOrderDetail pSaleOrderDetail){
        this.takeOrderID = pSaleOrderDetail.getTakeOrderID();
        this.line = pSaleOrderDetail.getLine();
        this.productID = pSaleOrderDetail.getProductID();
        this.barcode = pSaleOrderDetail.getBarcode();
        this.productName = pSaleOrderDetail.getProductName();
        this.afterOrderPrice = pSaleOrderDetail.getAfterOrderPrice();
        this.beforeOrderPrice = pSaleOrderDetail.getBeforeOrderPrice();
        this.tax = pSaleOrderDetail.getTax();
        this.discount = pSaleOrderDetail.getDiscount();
        this.priceTotal = pSaleOrderDetail.getPriceTotal();
        this.stockID = pSaleOrderDetail.getStockID();
        this.number = pSaleOrderDetail.getNumber();
        this.unit = pSaleOrderDetail.getUnit();
        this.exchangeRate = pSaleOrderDetail.getExchangeRate();
    }
    
    public ReturnOrderDetail(String mTakeOrderID, Integer mLine, String mProductID, String mBarcode, 
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
}
