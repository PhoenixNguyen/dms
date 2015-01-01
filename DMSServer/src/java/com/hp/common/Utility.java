/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.common;

import com.hp.domain.RoadManagement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author admin
 */
public class Utility {
    public static String getAddress(float lat, float lon) {
        if(lat == 0 || lon == 0){
            return "";
        }
        
        String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng="
		        + lat + "," + lon + "&sensor=false";
            JSONObject jsonObj;
            String City = "";
            String address = "";
         try {

          jsonObj = new JSONObject(HttpHelper.makeRequest(url));

          String Status = jsonObj.getString("status");
          if (Status.equalsIgnoreCase("OK")) {
                   JSONArray Results = jsonObj.getJSONArray("results");
                   JSONObject zero = Results.getJSONObject(0);

                   address = zero.getString("formatted_address").toString();

                   String[] long_name = address.split(",");

                   int number = long_name.length - 2;
                   City = long_name[number];
                   //System.out.println("CityName _______________________________ --->" + City + "");

                   //Toast.makeText(this, "CityName: " + City, Toast.LENGTH_SHORT).show();

                   if (!address.equals("")) {
                       return address;
                    //finish_service();
                   }
          }

         } catch (JSONException e) {
             e.printStackTrace();
             return "";
         }
         return "";
    }
}
