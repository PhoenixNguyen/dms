/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.domain;

import java.sql.Timestamp;


/**
 *
 * @author HP
 */


public class TakeOrder {
    
    private Integer serial;

    private String id;
    private Timestamp takeOrderDate;
    private Timestamp deliveryDate;
    private String customerID;
    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String deliveryAddress;
    private String shippingVehicle;
    
    private Float tax;
    private Float beforePrice;
    private Float afterPrivate;
    
    
    private Float discount;
    private Integer orderStatus;
    private Timestamp orderEstablishDate;
    private Timestamp orderEditDate;
    private String creater;
    private String editer;
    private String note;

    
    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getTakeOrderDate() {
        return takeOrderDate;
    }

    public void setTakeOrderDate(Timestamp takeOrderDate) {
        this.takeOrderDate = takeOrderDate;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShippingVehicle() {
        return shippingVehicle;
    }

    public void setShippingVehicle(String shippingVehicle) {
        this.shippingVehicle = shippingVehicle;
    }

    public Float getTax() {
        return tax;
    }

    public void setTax(Float tax) {
        this.tax = tax;
    }

    public Float getBeforePrice() {
        return beforePrice;
    }

    public void setBeforePrice(Float beforePrice) {
        this.beforePrice = beforePrice;
    }

    public Float getAfterPrivate() {
        return afterPrivate;
    }

    public void setAfterPrivate(Float afterPrivate) {
        this.afterPrivate = afterPrivate;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Timestamp getOrderEstablishDate() {
        return orderEstablishDate;
    }

    public void setOrderEstablishDate(Timestamp orderEstablishDate) {
        this.orderEstablishDate = orderEstablishDate;
    }

    public Timestamp getOrderEditDate() {
        return orderEditDate;
    }

    public void setOrderEditDate(Timestamp orderEditDate) {
        this.orderEditDate = orderEditDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getEditer() {
        return editer;
    }

    public void setEditer(String editer) {
        this.editer = editer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    
    @Override
	public String toString() {
		return customerID +" "+ customerName + " " + creater;
	}
    public TakeOrder(){
    	
    }
    
    public TakeOrder(String mID, Timestamp mTakeOrderDate,
			Timestamp mDeliveryDate, String mCustomerID, String mCustomerName,
			String mCustomerAddress, String mPhoneNumber,
			String mDeliveryAddress, String mShippingVehicle, float mTax,
			float mBeforePrice, float mAfterPrivate, float mDiscount,
			int mOrderStatus, Timestamp mOrderEstablishDate,
			Timestamp mOrderEditDate, String mCreater, String mEditer) {
		super();
		this.id = mID;
		this.takeOrderDate = mTakeOrderDate;
		this.deliveryDate = mDeliveryDate;
		this.customerID = mCustomerID;
		this.customerName = mCustomerName;
		this.customerAddress = mCustomerAddress;
		this.phoneNumber = mPhoneNumber;
		this.deliveryAddress = mDeliveryAddress;
		this.shippingVehicle = mShippingVehicle;
		this.tax = mTax;
		this.beforePrice = mBeforePrice;
		this.afterPrivate = mAfterPrivate;
		this.discount = mDiscount;
		this.orderStatus = mOrderStatus;
		this.orderEstablishDate = mOrderEstablishDate;
		this.orderEditDate = mOrderEditDate;
		this.creater = mCreater;
		this.editer = mEditer;
	}
    
    
            
}
