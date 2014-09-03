package com.hp.sale_order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class AppSectionsPagerAdapter extends FragmentPagerAdapter {

	private final String tab1 = "Xuất hàng";
	private final String tab2 = "Trả hàng";
	private final String tab3 = "Tổng kết";
	
	public static String selected_order;
	
    public AppSectionsPagerAdapter(FragmentManager fm, String selected_id) {
        super(fm);
        this.selected_order = selected_id;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                // The first section of the app is the most interesting -- it offers
                // a launchpad into the other demonstrations in this example application.
//                return new SaleOrderFragment();
            	Fragment sale = new SaleOrderFragment();
                Bundle args1 = new Bundle();
                args1.putString(SaleOrderFragment.ORDER_ID, selected_order);
                sale.setArguments(args1);
                return sale;
                
            case 1:
//            	return new ReturnOrderFragment();
            	Fragment sale2 = new ReturnOrderFragment();
                Bundle args2 = new Bundle();
                //ReturnOrderFragment.ORDER_ID = selected_order;
                args2.putString(ReturnOrderFragment.ORDER_ID, selected_order);
                sale2.setArguments(args2);
                return sale2;
            	
            default:
            	return new SaleValueFragment();
     
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
    	switch(position){
    	case 0:
    		return tab1;
    		
    	case 1:
    		return tab2;
    	case 2:
    		return tab3;
    	default:
    		return "Next";
    	}
    	        
    }
}
