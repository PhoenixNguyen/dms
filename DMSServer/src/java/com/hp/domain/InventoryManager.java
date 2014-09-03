/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
@Table(name="tb_kiemkho")
public class InventoryManager {
    
    @Id
    @GeneratedValue
    
    @Column(name="kiemkho_stt")
    private Integer serial;

    @Column(name="kiemkho_ma_hoa_don")
    private String id;
    @Column(name="kiemkho_ngay_dat_hang")
    private Timestamp takeOrderDate;
    @Column(name="kiemkho_ngay_giao_hang_du_kien")
    private Timestamp deliveryDate;
    @Column(name="kiemkho_ma_khach_hang")
    private String customerID;
    @Column(name="kiemkho_ten_khach_hang")
    private String customerName;
    @Column(name="kiemkho_dia_chi")
    private String customerAddress;
    @Column(name="kiemkho_so_dien_thoai")
    private String phoneNumber;
    @Column(name="kiemkho_dia_chi_giao_hang")
    private String deliveryAddress;
    @Column(name="kiemkho_hinh_thuc_van_chuyen")
    private String shippingVehicle;
    @Column(name="kiemkho_thue" , nullable = true)
    private Float tax;
    @Column(name="kiemkho_tien_truoc_thue")
    private Float beforePrice;
    @Column(name="kiemkho_tien_sau_thue")
    private Float afterPrivate;
    @Column(name="kiemkho_giam_gia")
    private Float discount;
    @Column(name="kiemkho_trang_thai_don_hang")
    private Integer orderStatus;
    @Column(name="kiemkho_ngay_tao_hoa_don")
    private Timestamp orderEstablishDate;
    @Column(name="kiemkho_ngay_sua_hoa_don")
    private Timestamp orderEditDate;
    @Column(name="kiemkho_nguoi_tao")
    private String creater;
    @Column(name="kiemkho_nguoi_sua")
    private String editer;

    @Column(name="kiemkho_ghi_chu")
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
    
    public Object[] toArray(){
        return new Object[]{
            id,
            takeOrderDate,
            deliveryDate,
            customerID,
            customerName,
            customerAddress,
            phoneNumber,
            deliveryAddress,
            shippingVehicle,
            tax,
            beforePrice,
            afterPrivate,
            discount,
            orderStatus,
            orderEstablishDate,
            orderEditDate,
            creater,
            editer
        };
    }
    public Object[] toTitleArray(){
        return new Object[]{
            "Mã hóa đơn",
            "Ngày tạo",
            "Ngày giao",
            "Mã khách hàng",
            "Tên khách hàng",
            "Địa chỉ",
            "Số điện thoại",
            "Địa chỉ giao",
            "Phương tiện",
            "Thuế",
            "Giá trước thuế",
            "Giá sau thuế",
            "Giảm giá",
            "Trạng thái",
            "Ngày lập",
            "Ngày sửa",
            "Người tạo",
            "Người sửa"
        };
    }
    public InventoryManager(){
        
    }
    
            
}
