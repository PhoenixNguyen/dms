/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class RetrieveFiles {
    public static void main(String [] arg){
        List<String> results = new ArrayList<String>();
        File[] files = new File("P:\\Users\\HP\\Desktop\\lib").listFiles();
        
        for(File file : files){
            if(file.isFile()){
                results.add(file.getName());
                System.out.println("__ " + file.getName());
            }
        }
    }
}
