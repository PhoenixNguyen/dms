/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * variabe in Object compatable with TakeOrder
 */

package com.hp.domain;

import java.sql.Timestamp;
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
@Table(name="tb_hoadontrahang")
public class ReturnOrder {
    
    @Id
    @GeneratedValue
    
    @Column(name="hoadontrahang_stt")
    private Integer serial;

    @Column(name="hoadontrahang_ma_hoa_don")
    private String id;

    
    @Column(name="hoadontrahang_ngay_tra_hang")
    private Timestamp takeOrderDate;
    @Column(name="hoadontrahang_ngay_giao_hang_du_kien")
    private Timestamp deliveryDate;
    @Column(name="hoadontrahang_ma_khach_hang")
    private String customerID;
    @Column(name="hoadontrahang_ten_khach_hang")
    private String customerName;
    @Column(name="hoadontrahang_dia_chi")
    private String customerAddress;
    @Column(name="hoadontrahang_so_dien_thoai")
    private String phoneNumber;
    @Column(name="hoadontrahang_dia_chi_giao_hang")
    private String deliveryAddress;
    @Column(name="hoadontrahang_hinh_thuc_van_chuyen")
    private String shippingVehicle;
    @Column(name="hoadontrahang_thue" , nullable = true)
    private Float tax;
    @Column(name="hoadontrahang_tien_truoc_thue")
    private Float beforePrice;
    @Column(name="hoadontrahang_tien_sau_thue")
    private Float afterPrivate;
    @Column(name="hoadontrahang_giam_gia")
    private Float discount;
    @Column(name="hoadontrahang_trang_thai_don_hang")
    private Integer orderStatus;
    @Column(name="hoadontrahang_ngay_tao_hoa_don")
    private Timestamp orderEstablishDate;
    @Column(name="hoadontrahang_ngay_sua_hoa_don")
    private Timestamp orderEditDate;
    @Column(name="hoadontrahang_nguoi_tao")
    private String creater;
    @Column(name="hoadontrahang_nguoi_sua")
    private String editer;

    @Column(name="hoadontrahang_ghi_chu")
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
    
    
    public ReturnOrder(){
        
    }
    
    public ReturnOrder(SaleOrder pSaleOrder){
        this.id = pSaleOrder.getId();
        this.takeOrderDate = pSaleOrder.getTakeOrderDate();
        this.deliveryDate = pSaleOrder.getDeliveryDate();
        this.customerID = pSaleOrder.getCustomerID();
        this.customerName = pSaleOrder.getCustomerName();
        this.customerAddress = pSaleOrder.getCustomerAddress();
        this.phoneNumber = pSaleOrder.getPhoneNumber();
        this.deliveryAddress = pSaleOrder.getDeliveryAddress();
        this.shippingVehicle = pSaleOrder.getShippingVehicle();
        this.tax = pSaleOrder.getTax();
        this.beforePrice = pSaleOrder.getBeforePrice();
        this.afterPrivate = pSaleOrder.getAfterPrivate();
        this.discount = pSaleOrder.getDiscount();
        this.orderStatus = pSaleOrder.getOrderStatus();
        this.orderEstablishDate = pSaleOrder.getOrderEstablishDate();
        this.orderEditDate = pSaleOrder.getOrderEditDate();
        this.creater = pSaleOrder.getCreater();
        this.editer = pSaleOrder.getEditer();
    }
    
            
    public ReturnOrder(String mID, Timestamp mTakeOrderDate, Timestamp mDeliveryDate, String mCustomerID, String mCustomerName, String mCustomerAddress, String mPhoneNumber, String mDeliveryAddress, String mShippingVehicle, Float mTax, Float mBeforePrice, Float mAfterPrivate, Float mDiscount, Integer mOrderStatus, Timestamp mOrderEstablishDate, Timestamp mOrderEditDate, String mCreater, String mEditer) {
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
