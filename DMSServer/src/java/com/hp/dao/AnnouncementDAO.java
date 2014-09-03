/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.dao;

import com.hp.domain.Announcement;
import java.util.List;

/**
 *
 * @author HP
 */
public interface AnnouncementDAO {
    public boolean saveOrUpdate(Announcement pAnnouncement);
    
    public int updateStatus();
    
    public Announcement getAnnouncement();
    
    //List
    public List<Announcement> loadAnnouncementList();
}
