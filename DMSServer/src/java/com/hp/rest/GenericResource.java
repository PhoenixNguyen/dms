/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hp.rest;

import com.hp.dao.CustomerDAO;
import com.hp.dao.CustomerDAOImpl;
import com.hp.dao.CustomerImageDAO;
import com.hp.dao.CustomerImageDAOImpl;
import com.hp.dao.ProductDAO;
import com.hp.dao.ProductDAOImpl;
import com.hp.dao.ProviderDAO;
import com.hp.dao.ProviderDAOImpl;
import com.hp.dao.RoadManagementDAO;
import com.hp.dao.RoadManagementDAOImpl;
import com.hp.dao.ScheduleDAO;
import com.hp.dao.ScheduleDAOImpl;
import com.hp.dao.StaffDAO;
import com.hp.dao.StaffDAOImpl;
import com.hp.dao.StaffHistoryDAO;
import com.hp.dao.StaffHistoryDAOImpl;
import com.hp.dao.TakeOrderDAO;
import com.hp.dao.TakeOrderDAOImpl;
import com.hp.dao.TakeOrderDetailDAO;
import com.hp.dao.TakeOrderDetailDAOImpl;
import com.hp.datahandle.DataConvert;
import com.hp.domain.Customer;
import com.hp.domain.CustomerImage;
import com.hp.domain.DataInfo;
import com.hp.domain.Product;
import com.hp.domain.Provider;
import com.hp.domain.RoadManagement;
import com.hp.domain.Schedule;
import com.hp.domain.Staff;
import com.hp.domain.StaffHistory;
import com.hp.domain.TakeOrder;
import com.hp.domain.TakeOrderDetail;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * REST Web Service
 *
 * @author HP
 */
@Path("generic")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }

    /**
     * Retrieves representation of an instance of com.hp.rest.GenericResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
    
    //__________________________________________________________________________
    @Path("/hello")
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }
    
    @Path("/getData")
    @GET
    @Produces("text/plain")
    public String getData(){
        return "Hello HP Great!!";
    }
    
    // This method is called if XML is request
    @Path("/getXml")
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
      return "<?xml version=\"1.0\"?> " + ""
              + "<root>"
                + "<student id=\"101\"> "
                      + "<name>Nguyễn Hoàng Phượng</name> "
                      + "<shsv>20092071</shsv> "
                + "</student> "
              + "</root> ";
    }

    // This method is called if HTML is request
    @Path("/getHtml")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
      return "<html> " + "<title>" + "Hello Jersey" + "</title>"
          + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }
    
    //=============================== GET ==========================================
    // This method is called if XML is request
//    @Path("/getCustomer")
//    @GET
//    @Produces(MediaType.TEXT_XML)
//    public String getCustomerToXML(@QueryParam("username") String pUsername,
//                                    @QueryParam("password") String pPassword) {
//        //Check permission
//        StaffDAO staffDAO = new StaffDAOImpl();
//        if(pUsername != null && pPassword != null && staffDAO.authenticate(pUsername, pPassword)){
//         
//            CustomerDAO customerDAO = new CustomerDAOImpl();
//            List<Customer> customerList = new ArrayList<Customer>();
//
//            customerList = customerDAO.loadCustomersWithLocations(null, pUsername, null);
//            System.out.println("LIST: " + customerList.get(0).getMMaDoiTuong());
//            String xml = "<?xml version=\"1.0\"?> "
//                    + "<root>";
//
//            for(int i = 0; i < customerList.size(); i++){
//                xml += "<customer id=\""+customerList.get(i).getMMaDoiTuong()+"\"> " 
//                            + "<name>"+customerList.get(i).getMDoiTuong()+"</name> " 
//                            + "<address>"+customerList.get(i).getMDiaChi()+"</address> " 
//                            + "<phone>"+customerList.get(i).getMDienThoai()+"</phone> " 
//                            + "<x>"+customerList.get(i).getMXCoordinates()+"</x>" 
//                            + "<y>"+customerList.get(i).getMYCoordinates()+"</y>"
//                            + "<staffid>"+customerList.get(i).getMMaNhanVien()+"</staffid>"
//                      + "</customer> ";
//            }
//             xml += "</root> ";
//
//             return xml;
//        }else
//            return null;
//    }
        
    @GET
    @Path("/getCustomerList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomerList() {

        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> customerList = new ArrayList<Customer>();

        //Check is Admin (manager)
        StaffDAO staffDAO = new StaffDAOImpl();
        
        customerList = customerDAO.getListCustomer();
        return customerList;
    }
    
    //Update journey
    @Path("/putJourney")
    @POST
    @Produces(MediaType.TEXT_XML)
    public String updateJourney(@FormParam("data") String str) {
        RoadManagementDAO roadManagementDAO = new RoadManagementDAOImpl();
        //Get current time
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        
        String pXML = "";
        try {
                pXML = new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
        }    
        
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(pXML)));


            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("road");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                    //init customer
                    Customer customer = new Customer();

                    Node nNode = nList.item(temp);

                    System.out.println("\nCurrent Element :" + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                            Element eElement = (Element) nNode;

                            String customerid = eElement.getAttribute("id");
                            
                            float x = Float.parseFloat(eElement.getElementsByTagName("x").item(0).getTextContent());
                            float y = Float.parseFloat(eElement.getElementsByTagName("y").item(0).getTextContent());
                            String staffid = eElement.getElementsByTagName("staffid").item(0).getTextContent();
                            
                            System.out.println("customer id : " + customerid);
                            System.out.println("staff id : " + staffid);
                            System.out.println("X : " + x);
                            System.out.println("Y : " + y);
                            System.out.println("TIME : " + "");
                            
                            //UPDATE into Database
                            RoadManagement roadManagement = new RoadManagement();
                            roadManagement.setMaNhanVien(staffid);
                            roadManagement.setTenNhanVien(customerid);
                            roadManagement.setViDo(x);
                            roadManagement.setKinhDo(y);
                            roadManagement.setThoiGian(Timestamp.valueOf(dateFormat.format(cal.getTime())));
                            
                            roadManagementDAO.saveOrUpdate(roadManagement);
                            
                    }
            }

            return "OK";
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "ERROR";
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "ERROR";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "ERROR";
        }
    }
    
    @Path("/getSchedule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Schedule> getSchedule(String pTemp){
        String[] total = pTemp.split("::");
        String staff = total[0];
        String date = total[1];
        
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        List<Schedule> scheduleList = new ArrayList<Schedule>();

        //Check is Admin (manager)
        StaffDAO staffDAO = new StaffDAOImpl();
        
        scheduleList = scheduleDAO.getScheduleList(staff, date, staffDAO.adminAuthenticate(staff));
        
//        Schedule sc = new Schedule();
//        sc.setMMaKH("fdfd");
//        sc.setMMaNV("1234fdfd");
        return scheduleList;
    }
    
    @POST
    @Path("/putLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putLocation( String pTrack ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        RoadManagement track = new RoadManagement();
        try {
//			File jsonFile = new File(jsonFilePath);
                track = mapper.readValue(pTrack, RoadManagement.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update location
        CustomerDAO customerDAO = new CustomerDAOImpl();
        int st = customerDAO.update(track.getTenNhanVien(), track.getViDo(), track.getKinhDo());
        
//            String output = pTrack.toString();
            System.out.println("____ " + pTrack + "___ " + st);
            return Response.status(200).entity(st+"").build();
    }
    
    @Path("/getCustomersListSchedule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomerToXML(String pStaffDate) {
         
        String[] total = pStaffDate.split("::");
        String staff = total[0];
        String date = total[1];
        
        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> customerList = new ArrayList<Customer>();

        //Check is Admin (manager)
        StaffDAO staffDAO = new StaffDAOImpl();
        
        customerList = customerDAO.getListCustomerSchedule(staff, date, staffDAO.adminAuthenticate(staff));

        return customerList;
    }
    
    @POST
    @Path("/putSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putSchedule( String pSchedule ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        List<Schedule> schedulesList = null;
        try {
//			File jsonFile = new File(jsonFilePath);
                schedulesList = mapper.readValue(pSchedule, TypeFactory.defaultInstance().constructCollectionType(List.class,
					Schedule.class));
                //System.out.println(schedulesList.get(0).getMMaKH());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update location
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        int temp = 0;
        for(int i = 0; i< schedulesList.size(); i++){
            if(scheduleDAO.saveOrUpdate(schedulesList.get(i)))
                temp++;
        }
            
            return Response.status(200).entity(temp+"").build();
    }
    
    @POST
    @Path("/getStaff")
    @Consumes(MediaType.APPLICATION_JSON)
    public Staff getStaff( String pStaff ) {

        String[] total = pStaff.split("::");
        
        String name = total[0];
        String pw = total[1];
        
        StaffDAO staffDAO = new StaffDAOImpl();
        Staff staff = new Staff();
        staff = staffDAO.authenticate(name, pw);
        
        
        return staff;

    }
    
    @POST
    @Path("/getCustomersListStart")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomersListStart( String pStaff ) {

        CustomerDAO customerDAO = new CustomerDAOImpl();
        List<Customer> customerList = new ArrayList<Customer>();

        //Check is Admin (manager)
        StaffDAO staffDAO = new StaffDAOImpl();
        
        customerList = customerDAO.getListCustomers(pStaff, staffDAO.adminAuthenticate(pStaff));
        return customerList;
    }
    
    @POST
    @Path("/deleteSchedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteSchedule( String pTmp ) {
        String[] total = pTmp.split("::");
        String staff = total[0];
        String date = total[1];
        
        ScheduleDAO scheduleDAO = new ScheduleDAOImpl();
        int st = scheduleDAO.deletechedule(staff, date);
        return Response.status(200).entity("status:"+ st).build();
    }
    
    @GET
    @Path("/getProvidersIDList")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Provider> getProvidersIDList() {
        List<Provider> list = new ArrayList<Provider>();
        
        ProviderDAO providerDAO = new ProviderDAOImpl();
        list = providerDAO.getProvidersList();
        
        return list;
        
    }
    
    @POST
    @Path("/getProductsList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Product> getProvidersIDList(String pProvider) {
        List<Product> list = new ArrayList<Product>();
        
        ProductDAO productDAO = new ProductDAOImpl();
        list = productDAO.getProductList(pProvider);
        
        return list;
        
    }
    
    @POST
    @Path("/putTakeOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putTakeOrder( String pTakeOrder ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        TakeOrder takeOrder = new TakeOrder();
        try {
//			File jsonFile = new File(jsonFilePath);
                takeOrder = mapper.readValue(pTakeOrder, TakeOrder.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update location
        TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        boolean st = takeOrderDAO.saveOrUpdate(takeOrder);
//            String output = pTrack.toString();
            System.out.println("pTakeOrder: " + st +"____ " + pTakeOrder);
            return Response.status(200).entity(st+"").build();
    }
    
    @POST
    @Path("/putOrdersDetailList")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putOrdersDetailList( String pList ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        List<TakeOrderDetail> detailList = null;
        try {
//			File jsonFile = new File(jsonFilePath);
                detailList = mapper.readValue(pList
                        , TypeFactory.defaultInstance().constructCollectionType(List.class
                        , TakeOrderDetail.class));
                //System.out.println(schedulesList.get(0).getMMaKH());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update 
        int count = 0;
        TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
        for(int i = 0; i< detailList.size(); i++){
            if(takeOrderDetailDAO.saveOrUpdate(detailList.get(i)))
                count ++;
            
        }
        System.out.println("pTakeOrderDetail: " + count +"____ " + pList);
        return Response.status(200).entity(count+"").build();
    }
    
    @POST
    @Path("/getTakeOrderList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TakeOrder> getTakeOrderList(String pData) {
        List<TakeOrder> list = new ArrayList<TakeOrder>();
        
        TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        
        //Check is Admin (manager)
        StaffDAO staffDAO = new StaffDAOImpl();
        
        list = takeOrderDAO.getTakeOrdersList(pData, staffDAO.adminAuthenticate(pData));
        
        return list;
        
    }
    
    @POST
    @Path("/getTakeOrderDetailList")
    @Consumes(MediaType.APPLICATION_JSON)
    public List<TakeOrderDetail> getTakeOrderDetailList(String pData) {
        List<TakeOrderDetail> list = new ArrayList<TakeOrderDetail>();
        
        TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
        list = takeOrderDetailDAO.getDetailTakeOrdersList(pData);
        
        return list;
        
    }
    
    
    @POST
    @Path("/putImage")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putImage( String pData ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        DataInfo data = new DataInfo();
        try {
//			File jsonFile = new File(jsonFilePath);
                data = mapper.readValue(pData, DataInfo.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        Date today = new Date();
        
        String name = data.getNhanVien() +"-"+ df.format(today) +"-"
                + today.getHours()+"-"+today.getMinutes()+"-"+today.getSeconds() + ".jpg";
        
        String path = ServletActionContext.getServletContext()
                .getRealPath("/db_customers/" );
        
        //Create new folder
        File file = new File(path + "/" + data.getKhachHang());
	if (!file.exists()) {
		if (file.mkdir()) {
			System.out.println("Directory is created!");
		} else {
			System.out.println("Failed to create directory!");
		}
	}
        
        System.out.println(path+ "/" + data.getKhachHang()+"/"+ name);
        //Save
        saveImage(data.getNoiDung(), (path + "/" + data.getKhachHang()+"/"+ name) );
        
        //Save in database
        CustomerImageDAO customerImageDAO = new CustomerImageDAOImpl();
        CustomerImage customerImage = new CustomerImage();
        customerImage.setId(name);
        customerImage.setName(name);
        customerImage.setCustomerID(data.getKhachHang());
        customerImage.setStaffID(data.getNhanVien());
        customerImage.setTime(Timestamp.valueOf(df2.format(today)));
        customerImage.setStatus(false);
        
        boolean status = customerImageDAO.saveOrUpdate(customerImage);
        
        //save track staff 
        StaffHistoryDAO staffHistoryDAO = new StaffHistoryDAOImpl();
        StaffHistory staffHistory = new StaffHistory();
        staffHistory = staffHistoryDAO.getStaffHistory(data.getKhachHang(), df.format(today));
        
        if(staffHistory == null){
            byte[] b = data.getTenKhachHang().getBytes(Charset.forName("UTF-8"));
            String str = new String(b);
            
            System.out.println(data.getTenKhachHang() + "\n"+ str);
            staffHistory = new StaffHistory();
            staffHistory.setStaff(data.getNhanVien());
            staffHistory.setCustomer(data.getKhachHang());
            staffHistory.setCustomerName(str);
            staffHistory.setStartTime(Timestamp.valueOf(df2.format(today)));
            //staffHistory.setNote();
            
            staffHistoryDAO.saveOrUpdate(staffHistory);
        }
        
//            String output = pTrack.toString();
        System.out.println( status + " ____ " + data.getNhanVien()+ "___ " + data.getKhachHang());
        return Response.status(200).entity("______ Success").build();
    }
    
    public void saveImage(String input, String output) {
        try {           
                byte[] imageByteArray = DataConvert.decodeImage(input);

                // Write a image byte array into file system
                FileOutputStream imageOutFile = new FileOutputStream(output);
                imageOutFile.write(imageByteArray);
                
                imageOutFile.close();

                System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        
    }
    
    @POST
    @Path("/updateDetailOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDetailOrder( String pTakeOrder ) {

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        
        Calendar cal = Calendar.getInstance();
        String time = sdf2.format(cal.getTime());
        
        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        TakeOrderDetail takeOrderDetail = new TakeOrderDetail();
        try {
//			File jsonFile = new File(jsonFilePath);
                takeOrderDetail = mapper.readValue(pTakeOrder, TakeOrderDetail.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update location
        TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
        boolean st = takeOrderDetailDAO.update(takeOrderDetail);
        if(!st)
            return Response.status(200).entity(st+"").build();
        
        //Update the order
        List<TakeOrderDetail> list = new ArrayList<TakeOrderDetail>();
        
        list = takeOrderDetailDAO.getDetailTakeOrdersList(takeOrderDetail.getTakeOrderID());
        float priceTotal = 0;
        for(int i = 0; i < list.size(); i++){
            priceTotal += list.get(i).getPriceTotal();
        }
        
        TakeOrder takeOrder = new TakeOrder();
        TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        
        takeOrder = takeOrderDAO.getTakeOrder(takeOrderDetail.getTakeOrderID());
        takeOrder.setAfterPrivate(priceTotal - priceTotal*takeOrder.getDiscount()/100);
        
        takeOrder.setOrderEditDate(Timestamp.valueOf(time));
        
        boolean st2 = takeOrderDAO.update(takeOrder);
//            String output = pTrack.toString();
        System.out.println("____ " + pTakeOrder + "___ " + st);
        return Response.status(200).entity(st2+"").build();
    }
    
    @POST
    @Path("/deleteDetailOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteDetailOrder( String pTakeOrder ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        TakeOrderDetail takeOrderDetail = new TakeOrderDetail();
        try {
//			File jsonFile = new File(jsonFilePath);
                takeOrderDetail = mapper.readValue(pTakeOrder, TakeOrderDetail.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update 
        TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
        boolean st = takeOrderDetailDAO.delete(takeOrderDetail);
        if(!st)
            return Response.status(200).entity(st+"").build();
        
        //Update the order
        List<TakeOrderDetail> list = new ArrayList<TakeOrderDetail>();
        
        list = takeOrderDetailDAO.getDetailTakeOrdersList(takeOrderDetail.getTakeOrderID());
        float priceTotal = 0;
        for(int i = 0; i < list.size(); i++){
            priceTotal += list.get(i).getPriceTotal();
        }
        
        TakeOrder takeOrder = new TakeOrder();
        TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        
        takeOrder = takeOrderDAO.getTakeOrder(takeOrderDetail.getTakeOrderID());
        takeOrder.setAfterPrivate(priceTotal  - priceTotal*takeOrder.getDiscount()/100);
        boolean st2 = takeOrderDAO.update(takeOrder);
//            String output = pTrack.toString();
        System.out.println("____ " + pTakeOrder + "___ " + st);
        return Response.status(200).entity(st2+"").build();
    }
    
    @POST
    @Path("/deleteOrder")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteOrder( String pTakeOrder ) {

        // pair to object
        ObjectMapper mapper = new ObjectMapper();
        TakeOrder takeOrder = new TakeOrder();
        try {
//			File jsonFile = new File(jsonFilePath);
                takeOrder = mapper.readValue(pTakeOrder, TakeOrder.class);
                //System.out.println(track.getMMaKhachHang());
        } catch (JsonGenerationException e) {
                e.printStackTrace();
        } catch (JsonMappingException e) {
                e.printStackTrace();
        } catch (IOException e) {
                e.printStackTrace();
        }
        
        //Update 
        TakeOrderDetailDAO takeOrderDetailDAO = new TakeOrderDetailDAOImpl();
        boolean st = takeOrderDetailDAO.delete(takeOrder.getId());
        if(!st)
            return Response.status(200).entity(st+"").build();
        
        //Delete Order
        TakeOrderDAO takeOrderDAO = new TakeOrderDAOImpl();
        boolean st2 = takeOrderDAO.delete(takeOrder);
        
        System.out.println("____ " + pTakeOrder + "___ " + st);
        return Response.status(200).entity(st2+"").build();
    }
}
