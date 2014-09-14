package com.hp.gps;

import java.util.ArrayList;
import java.util.List;

public class MapLocation {
	
	public static List<String> getAllCityOfVietNam(){
		List<String> cities = new ArrayList<String>();
		
		cities.add("Hà Nội");
		cities.add("Thành phố Hồ Chí Minh");
		
		cities.add("Cần Thơ");
		cities.add("Đà Nẵng");
		cities.add("Hải Phòng");
		
		cities.add("An Giang");
		cities.add("Bà Rịa - Vũng Tàu");
		cities.add("Bắc Giang");
		cities.add("Bắc Kạn");
		cities.add("Bạc Liêu");
		cities.add("Bắc Ninh");
		cities.add("Bến Tre");
		cities.add("Bình Định");
		cities.add("Bình Dương");
		cities.add("Bình Phước");
		cities.add("Bình Thuận");
		cities.add("Cà Mau");
		cities.add("Cao Bằng");
		cities.add("Đắk Lắk");
		cities.add("Đắk Nông");
		cities.add("Điện Biên");
		cities.add("Đồng Nai");
		cities.add("Đồng Tháp");
		cities.add("Gia Lai");
		cities.add("Hà Giang");
		cities.add("Hà Nam");
		cities.add("Hà Tĩnh");
		cities.add("Hải Dương");
		cities.add("Hậu Giang");
		cities.add("Hòa Bình");
		cities.add("Hưng Yên");
		cities.add("Khánh Hòa");
		cities.add("Kiên Giang");
		cities.add("Kon Tum");
		cities.add("Lai Châu");
		cities.add("Lâm Đồng");
		cities.add("Lạng Sơn");
		cities.add("Lào Cai");
		cities.add("Long An");
		cities.add("Nam Định");
		cities.add("Nghệ An");
		cities.add("Ninh Bình");
		cities.add("Ninh Thuận");
		cities.add("Phú Thọ");
		cities.add("Quảng Bình");
		cities.add("Quảng Nam");
		cities.add("Quảng Ngãi");
		cities.add("Quảng Ninh");
		cities.add("Quảng Trị");
		cities.add("Sóc Trăng");
		cities.add("Sơn La");
		cities.add("Tây Ninh");
		cities.add("Thái Bình");
		cities.add("Thái Nguyên");
		cities.add("Thanh Hóa");
		cities.add("Thừa Thiên Huế");
		cities.add("Tiền Giang");
		cities.add("Trà Vinh");
		cities.add("Tuyên Quang");
		cities.add("Vĩnh Long");
		cities.add("Vĩnh Phúc");
		cities.add("Yên Bái");
		cities.add("Phú Yên");
		
		return cities;
	}
	public static String getCityNameStandard(String name){
		
		String nameLower = name.toLowerCase();
		
		if(nameLower.indexOf("angiang") != -1 || nameLower.indexOf("an giang") != -1 || nameLower.indexOf("an giang") != -1){
			return "An Giang";
		}
		if(nameLower.indexOf("bariavungtau") != -1 || nameLower.indexOf("ba ria vung tau") != -1 || nameLower.indexOf("ba ria - vung tau") != -1){
			return "Bà Rịa - Vũng Tàu";
		}
		if(nameLower.indexOf("hanoi") != -1 || nameLower.indexOf("ha noi") != -1 || nameLower.indexOf("hà nội") != -1){
			return "Hà Nội";
		}
		if(nameLower.indexOf("thaibinh") != -1 || nameLower.indexOf("thai binh") != -1 || nameLower.indexOf("thái bình") != -1){
			return "Thái Bình";
		}
		if(nameLower.indexOf("hanam") != -1 || nameLower.indexOf("ha nam") != -1 || nameLower.indexOf("hà nam") != -1){
			return "Hà Nam";
		}
		if(nameLower.indexOf("namdinh") != -1 || nameLower.indexOf("nam dinh") != -1 || nameLower.indexOf("nam định") != -1){
			return "Nam Định";
		}
		if(nameLower.indexOf("haiphong") != -1 || nameLower.indexOf("hai phong") != -1 || nameLower.indexOf("hải phòng") != -1){
			return "Hải Phòng";
		}
		
		return "";
	}
}
