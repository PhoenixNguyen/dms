/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.rest;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.ProductDAO;
import com.hp.dao.ProductDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.domain.Customer;
import com.hp.domain.Product;
import com.hp.domain.Staff;
import java.io.IOException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author HP
 */

@Path("input")
public class InputResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of OrdersHandle
     */
    public InputResource() {
    }

    /**
     * Retrieves representation of an instance of com.hp.rest.GenericResource
     * @return an instance of java.lang.String
     */
    @Path("/vd")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getXml() {
        return "Hello";
    }
    
    @POST
    @Path("/insertCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertCustomer(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        try {
//			File jsonFile = new File(jsonFilePath);
                customer = mapper.readValue(pData, Customer.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        CustomerDAO customerDAO = new CustomerDAOImpl();
        boolean st = customerDAO.saveOrUpdate(customer);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    @POST
    @Path("/updateCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        try {
//			File jsonFile = new File(jsonFilePath);
                customer = mapper.readValue(pData, Customer.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        CustomerDAO customerDAO = new CustomerDAOImpl();
        boolean st = customerDAO.update(customer);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    @POST
    @Path("/deleteCustomer")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = new Customer();
        try {
//			File jsonFile = new File(jsonFilePath);
                customer = mapper.readValue(pData, Customer.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        CustomerDAO customerDAO = new CustomerDAOImpl();
        boolean st = customerDAO.delete(customer);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    @POST
    @Path("/insertProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertProduct(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product();
        try {
//			File jsonFile = new File(jsonFilePath);
                product = mapper.readValue(pData, Product.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        ProductDAO productDAO = new ProductDAOImpl();
        boolean st = productDAO.saveOrUpdate(product);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    @POST
    @Path("/updateProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product();
        try {
//			File jsonFile = new File(jsonFilePath);
                product = mapper.readValue(pData, Product.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
                
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        ProductDAO productDAO = new ProductDAOImpl();
        boolean st = productDAO.update(product);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    @POST
    @Path("/deleteProduct")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteProduct(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Product product = new Product();
        try {
//			File jsonFile = new File(jsonFilePath);
                product = mapper.readValue(pData, Product.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
                
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        ProductDAO productDAO = new ProductDAOImpl();
        boolean st = productDAO.delete(product);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
    
    
    
    @POST
    @Path("/updateStaff")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStaff(String pData) { //String customerID
        
       // pair to object
        ObjectMapper mapper = new ObjectMapper();
        Staff staff = new Staff();
        try {
//			File jsonFile = new File(jsonFilePath);
                staff = mapper.readValue(pData, Staff.class);
                //System.out.println(track.getmMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
                
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        StaffDAO staffDAO = new StaffDAOImpl();
        boolean st = staffDAO.update(staff);
        System.out.println("____ " + st + "___ " );       
        return Response.status(200).entity(st+"").build();
        
    }
}
