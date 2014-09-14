package com.hp.menu;

import com.hp.map.CalendarManagerActivity;
import com.hp.map.CustomerListActivity;
import com.hp.map.InventoryManagerActivity;
import com.hp.map.LoginActivity;
import com.hp.map.ProductManagerActivity;
import com.hp.map.ProfileActivity;
import com.hp.map.R;
import com.hp.map.SaleManagerActivity;
import com.hp.map.SaleOrdersManagerActivity;
import com.hp.map.Schedule_CalendarActivity;
import com.hp.map.TakeOrdersManagerActivity;
import com.hp.map.TimeKeeperActivity;
public class DetailListData {
	public DetailListData(){
		
	}
	
	public static final DetailsList[] MENU_LIST = {
		new DetailsList("Hồ sơ cá nhân", 0, null),
		new DetailsList("Hồ sơ", R.drawable.ic_menu_contact, ProfileActivity.class),
		new DetailsList("Lịch công tác", R.drawable.effective_writing, CalendarManagerActivity.class),
		//new DetailsList("Lên kế hoạch", R.drawable.effective_writing, Schedule_CalendarActivity.class), <-- OLD
		new DetailsList("Chấm công", R.drawable.time_keeper, TimeKeeperActivity.class),
		
		new DetailsList("Đầu vào", 0, null),
		new DetailsList("Sản phẩm", R.drawable.ic_menu_products, ProductManagerActivity.class),
		new DetailsList("Khách hàng", R.drawable.ic_menu_customers, CustomerListActivity.class),
		
		new DetailsList("Hóa đơn", 0, null),
		new DetailsList("Hóa đơn đặt hàng", R.drawable.ic_menu_orders, TakeOrdersManagerActivity.class),
//		new DetailsList("Quản lý hàng tồn", R.drawable.ic_menu_database, InventoryManagerActivity.class),
//		new DetailsList("Quản lý bán hàng", R.drawable.ic_menu_database, SaleManagerActivity.class),
		
		
		new DetailsList("Thông tin", 0, null),
		new DetailsList("Phần mềm", R.drawable.ic_menu_about, null),
		new DetailsList("Thoát", R.drawable.icon_logout, LoginActivity.class),
		
	};
}
