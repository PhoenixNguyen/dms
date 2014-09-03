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

public class TakeOrderDetail {
    
    private Integer serial;

    private String takeOrderID;
    private Integer line;
    private String productID;
    private String barcode;
    private String productName;
    private Float afterOrderPrice;
    private Float beforeOrderPrice;
    private Float tax;
    private Integer discount;
    private Float priceTotal;
    private String stockID;
    private Integer number;
    private String unit;
    private Float exchangeRate;
    private String note;
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
    

	public TakeOrderDetail(String mTakeOrderID, int mLine, String mProductID,
			String mBarcode, String mProductName, float mAfterOrderPrice,
			float mBeforeOrderPrice, float mTax, int mDiscount,
			float mPriceTotal, String mStockID, int mNumber, String mUnit,
			float mExchangeRate, String mNote, int mPromotionalProductMount) {
		super();
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
		this.note = mNote;
		this.promotionalProductMount = mPromotionalProductMount;
	}

    public TakeOrderDetail(){
    	
    }
    
    
}
