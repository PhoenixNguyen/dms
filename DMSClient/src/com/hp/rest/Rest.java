package com.hp.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import com.hp.domain.Staff;
import com.sun.jersey.api.client.Client;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.spi.service.ServiceFinder;

public class Rest {

	public static Staff mStaff = new Staff();
	public static String mStaffJson = "";
	
	public Rest(String pUrl){
	}
	
	public static WebResource mService;
	public static void connectWebservices(){
		ServiceFinder.setIteratorProvider(new AndroidServiceIteratorProvider());

		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    //client.setConnectTimeout(10000);
	    //client.setReadTimeout(10000);
	    
	    mService = client.resource(getBaseURI());
	    // Fluent interfaces
//	    System.out.println(mService.path("webresources").path("getData").accept(MediaType.TEXT_PLAIN).get(ClientResponse.class).toString());
	    // Get plain text
//	    System.out.println("________________ "+
//	    mService.path("webresources").path("getData").accept(MediaType.TEXT_PLAIN).get(String.class));
	    
	    
	    // Get XML
	    //System.out.println(service.path("webresources").path("getData").accept(MediaType.TEXT_XML).get(String.class));
	    // The HTML
	    //System.out.println(service.path("webresources").path("getData").accept(MediaType.TEXT_HTML).get(String.class));
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri(mURL).build();
	}
	
	//public static String mURL = "http://123.16.242.0:8080/DMS/";
	public static String mURL = "http://192.168.1.104:8080/DMS";
	//public static String mURL = "http://dms.adiagri.com.vn:8484/DMS/";
	
}
