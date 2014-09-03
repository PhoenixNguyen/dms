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
@Table(name="tb_userhistory")
public class History {
    @Id
    @GeneratedValue
    
    @Column(name="history_stt")
    private int stt;
    @Column(name="history_user")
    private String user;
    @Column(name="history_ip")
    private String ip;
    @Column(name="history_login")
    private Timestamp login;
    @Column(name="history_logout")
    private Timestamp logout;
    @Column(name="history_note")
    private String note;

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Timestamp getLogin() {
        return login;
    }

    public void setLogin(Timestamp login) {
        this.login = login;
    }

    public Timestamp getLogout() {
        return logout;
    }

    public void setLogout(Timestamp logout) {
        this.logout = logout;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
