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
@Table(name="tb_kho")
public class Stock {
    
    @Id
    @GeneratedValue
    
    @Column(name="kho_stt")
    private int serial;

    @Column(name="kho_ma_kho")
    private String stockID;
    @Column(name="kho_ma_san_pham")
    private String productID;

    @Column(name="kho_ten_san_pham")
    private String name;
    @Column(name="kho_gia")
    private Float price;
    @Column(name="kho_ton_dau")
    private Float beginningInventory;
    @Column(name="kho_nhap")
    private Float input;
    @Column(name="kho_ton_cuoi")
    private Float lastInventory;
    @Column(name="kho_ban_ra")
    private Float banRa;
    @Column(name="kho_order")
    private Float order;
    @Column(name="kho_duyet")
    private Float duyet;
    @Column(name="kho_ton_tong_kho")
    private Float totalInventory;
    @Column(name="kho_ghi_chu")
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
    
}
