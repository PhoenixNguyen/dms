/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.common;

/**
 *
 * @author HP
 */
public class ValidateHandle {
    
    //Return -1 if not numberic
    //return > -1 if is numberic
    public static int getInteger(String s)
    {
        int number = -1;
        try {
            number = Integer.parseInt(s);
            return number;
        } catch(NumberFormatException e) {
            return -1;
        }
    }
}
