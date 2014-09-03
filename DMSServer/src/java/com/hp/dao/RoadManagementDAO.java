/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.RoadManagement;
import java.util.List;

/**
 *
 * @author HP
 */
public interface RoadManagementDAO {
    public List<List<RoadManagement>> getRoad(String pGiamDoc, String pNhanVien, String pMaKhachHang, String pDate);
    public boolean saveOrUpdate(RoadManagement pRoadManagement);
}
