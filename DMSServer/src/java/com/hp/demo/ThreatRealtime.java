/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.demo;

/**
 *
 * @author HP
 */

public class ThreatRealtime implements Runnable{
	
	public void run() {

	      Thread t = Thread.currentThread();
	      System.out.print(t.getName());
	      //checks if this thread is alive
	      System.out.println(", status = " + t.isAlive());
	   }

	   public static void main(String args[]) throws Exception {

	      Thread t = new Thread(new ThreatRealtime());
	      // this will call run() function
	      t.start();
	      // waits for this thread to die
	      t.join();
	      System.out.print(t.getName());
	      //checks if this thread is alive
	      System.out.println(", status = " + t.isAlive());
	   }

}
