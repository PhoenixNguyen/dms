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

public class Stock {
   
    private int serial;

    private String stockID;
    private String productID;

    private String name;
    private Float price;
    private Float beginningInventory;
    private Float input;
    private Float lastInventory;
    private Float banRa;
    private Float order;
    private Float duyet;
    private Float totalInventory;
    private String note;

    
    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getBeginningInventory() {
        return beginningInventory;
    }

    public void setBeginningInventory(Float beginningInventory) {
        this.beginningInventory = beginningInventory;
    }

    public Float getInput() {
        return input;
    }

    public void setInput(Float input) {
        this.input = input;
    }

    public Float getLastInventory() {
        return lastInventory;
    }

    public void setLastInventory(Float lastInventory) {
        this.lastInventory = lastInventory;
    }

    public Float getBanRa() {
        return banRa;
    }

    public void setBanRa(Float banRa) {
        this.banRa = banRa;
    }

    public Float getOrder() {
        return order;
    }

    public void setOrder(Float order) {
        this.order = order;
    }

    public Float getDuyet() {
        return duyet;
    }

    public void setDuyet(Float duyet) {
        this.duyet = duyet;
    }

    public Float getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(Float totalInventory) {
        this.totalInventory = totalInventory;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    

    public String getmProductID() {
        return productID;
    }

    public void setmProductID(String pProductID) {
        this.productID = pProductID;
    }
    public Stock(String pStockID, String pProductID, String pName, float pPrice,
			float pBeginningInventory, float pInput, float pLastInventory,
			float pBanRa, float pOrder, float pDuyet, float pTotalInventory,
			String pNote) {
		super();
		this.stockID = pStockID;
		this.productID = pProductID;
		this.name = pName;
		this.price = pPrice;
		this.beginningInventory = pBeginningInventory;
		this.input = pInput;
		this.lastInventory = pLastInventory;
		this.banRa = pBanRa;
		this.order = pOrder;
		this.duyet = pDuyet;
		this.totalInventory = pTotalInventory;
		this.note = pNote;
	}

    public Stock(){
    	
    }
    
    
}
