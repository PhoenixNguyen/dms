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
import org.hibernate.validator.Min;

/**
 *
 * @author HP
 */

@Entity
@Table(name="tb_hoadondathang")
public class TakeOrder {
    
    @Id
    @GeneratedValue
    
    @Column(name="hoadondathang_stt")
    private Integer serial;

    @Column(name="hoadondathang_ma_hoa_don")
    private String id;
    @Column(name="hoadondathang_ngay_dat_hang")
    private Timestamp takeOrderDate;
    @Column(name="hoadondathang_ngay_giao_hang_du_kien")
    private Timestamp deliveryDate;
    @Column(name="hoadondathang_ma_khach_hang")
    private String customerID;
    @Column(name="hoadondathang_ten_khach_hang")
    private String customerName;
    @Column(name="hoadondathang_dia_chi")
    private String customerAddress;
    @Column(name="hoadondathang_so_dien_thoai")
    private String phoneNumber;
    @Column(name="hoadondathang_dia_chi_giao_hang")
    private String deliveryAddress;
    @Column(name="hoadondathang_hinh_thuc_van_chuyen")
    private String shippingVehicle;
    
    @Column(name="hoadondathang_thue")
    private Float tax;
    @Column(name="hoadondathang_tien_truoc_thue")
    private Float beforePrice;
    @Column(name="hoadondathang_tien_sau_thue")
    private Float afterPrivate;
    
    
    @Column(name="hoadondathang_giam_gia")
    private Float discount;
    @Column(name="hoadondathang_trang_thai_don_hang")
    private Integer orderStatus;
    @Column(name="hoadondathang_ngay_tao_hoa_don")
    private Timestamp orderEstablishDate;
    @Column(name="hoadondathang_ngay_sua_hoa_don")
    private Timestamp orderEditDate;
    @Column(name="hoadondathang_nguoi_tao")
    private String creater;
    @Column(name="hoadondathang_nguoi_sua")
    private String editer;
    @Column(name="hoadondathang_ghi_chu")
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
    public TakeOrder(){
        
    }
            
}
