package com.hp.schedule;
//NOT USE

import java.sql.Timestamp;

public class ListViewSchedules {
	private String id;
	private String time;
	
	private String name;
	
	public ListViewSchedules(String id, String name, String time){
		this.id = id;
		this.name = name;
		this.time = time;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
