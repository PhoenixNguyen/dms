<%-- 
    Document   : view-locations
    Created on : May 14, 2014, 12:07:11 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Xem vị trí khách hàng</title>
        <link REL="SHORTCUT ICON" HREF="/DMS/themes/images/vtigercrm_icon.ico">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">

        </style>
        <!--    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
            </script>-->
        <script language="JavaScript" type="text/javascript" src="/DMS/js/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/DMS/js/wz_jsgraphics.js"></script>

        <script language="JavaScript" type="text/javascript" src="/DMS/js/ajax_option.js"></script>
        
<!--        <script type='text/javascript' src="http://www.walterzorn.de/en/scripts/wz_jsgraphics.js"></script>-->
        
        <link rel="stylesheet" type="text/css" href="/DMS/js/maps/map.css">
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGEhKh7kMB8aYZpl2nY6jAFo9td-iVA7k&sensor=true">
        </script>
        
        <script type="text/javascript">
            var arr = new Array();
            var map;
            function initialize() {
                var i;
                var Customers = [
            <s:iterator value="listCustomer" status="status">
                    {
                        mXCoordinates: <s:property value="coordinateX"/>,
                                mYCoordinates: <s:property value="coordinateY"/>,
                        mMaDoiTuong: '<s:property value="maDoiTuong:"/>'

                    },
            </s:iterator>
                ];
                //console.log("__ "+Customers[0].mXCoordinates + Customers[0].mYCoordinates);
                var x = 21.030336;
                var y = 105.85814 ;
                if(Customers.length > 0){
                    if(Customers[0].mXCoordinates > 0){
                        x = Customers[0].mXCoordinates;
                        y = Customers[0].mYCoordinates;
                    }
                }
                var myOptions = {
                    zoom: 14,
                    center: new google.maps.LatLng(x, y),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);

                var infowindow = new google.maps.InfoWindow({
                    content: ''
                });

                var contentString = [
            <s:iterator value="listCustomer" status="status">
                    '<div id="boxShow"> <a href="customerDetail.action?page=0&customer_id=<s:property value="maDoiTuong"/>">\n\
                                        <img class= "ImageWrap" border="0" src="../customer/<s:property value="maDoiTuong"/>/1.jpg"  ></a>' +
                            '<p class= "TextWrap">\n\
                                <b><a href="customerDetail.action?page=0&customer_id=<s:property value="maDoiTuong"/>">Khách hàng: <s:property value="doiTuong"/></a></b>' + '<br/><br/>' +
                            'Mã khách hàng: <s:property value="maDoiTuong"/>' + '<br/>' +
                            'Tỉnh thành: <s:property value="tinhThanh"/>' + '<br/>' +
                            'Địa chỉ: <s:property value="diaChi"/>' + '<br/>' +
                            'Điện thoại: <s:property value="dienThoai"/>' + '<br/>' +
                            'Fax: <s:property value="fax"/>' + '<br/>\n\
                             Tọa độ X: <s:property value="coordinateX"/> <br/> \n\
                             Tọa độ Y: <s:property value="coordinateY"/> <br/>   </p></div>',
            </s:iterator>
                ];

                for (i = 0; i < Customers.length; i++) {
                    size = 15;
                    var img = new google.maps.MarkerImage('../images/marker.jpg',
                            new google.maps.Size(size, 2 * size),
                            new google.maps.Point(0, 0),
                            new google.maps.Point(size / 2, size / 2)
                            );

                    var marker = new google.maps.Marker({
                        map: map,
                        title: Customers[i].title,
                        position: new google.maps.LatLng(Customers[i].mXCoordinates, Customers[i].mYCoordinates)
                        //icon: img
                    });

                    bindInfoWindow(marker, map, infowindow, contentString[i], Customers[i].mMaDoiTuong);

                }

            }

            function bindInfoWindow(marker, map, infowindow, html, Ltitle) {
                google.maps.event.addListener(marker, 'click', function() {
                    infowindow.setContent(html);
                    infowindow.open(map, marker);

                });
                google.maps.event.addListener(marker, 'mouseout', function() {
                    //infowindow.close();

                });
            }
            google.maps.event.addDomListener(window, 'load', initialize);
            
            function toPixel(x , y){
                var end = new google.maps.LatLng(x, y);
                overlay = new google.maps.OverlayView();
                overlay.draw = function() {};
                overlay.setMap(map);
                var point = overlay.getProjection().fromLatLngToContainerPixel(end); 
                return point;
            }
            
        </script>
        
        <script>
            //Draw line
//            $(document).ready(function() {
//                $(".gallery > li").hover(function(x) {
//                    drawLine(x.clientX, x.clientY);
//                    //bounce();
//                }, function() {
//                    //toggleBounce();
//                    clearLine();
//                });
//            });

            function onGetLocation(x , l1, l2){
                console.log("l1: " + l1);
                //drawLine(x.clientX, x.clientY, toPixel(l1, l2));
                //drawLine(x.offsetLeft + 100, x.offsetTop, toPixel(l1, l2));
                drawLine(f (x).x + 100, f (x).y, toPixel(l1, l2));
            }
            
            function out(){
                clearLine();
            }
            
            function f (el) {
                var offset = (function(el) {
                  if(el && el.parentNode) {
                    var x = 0;
                    var y = 0;

                    while(el) {
                      x += el.offsetLeft;
                      y += el.offsetTop;
                      el = el.offsetParent;
                    }

                    return {x: x, y: y};
                  }

                  return null;
                })(el);

                var scroll = (function(el) {
                  if(el && el.parentNode) {
                    var x = 0;
                    var y = 0;

                    while(el) {
                      if(el && el.parentNode) {
                        x -= el.scrollLeft;
                        y -= el.scrollTop;
                        el = el.parentNode;
                      } else {
                        el = null;
                      }
                    }

                    return {x: x, y: y};
                  }

                  return null;
                })(el);

                return { x: offset.x + scroll.x, y: offset.y + scroll.y }
              }
        </script>


    </head>
    <body >
        
        
        <div id="canvas">

            <div id="map-canvas">

            </div>

            <div id="filter">
                <form id="sub_form" method="POST" action="filter-result?page=0">
                <table border=0 cellspacing=0 cellpadding=0 width="200px">
                                            
                    <tr align=left class=small><b>Chọn Giám đốc</b></tr>
                        
                    <s:push value="pushInfo">
                    
                    <tr align="left" width="20%">
                        <select name="pushInfo.managerID"  class="small" style="width:98%" onchange="onClickManager(options[selectedIndex].text , 'take');">
                            <option value="--select--">--select--</option>
                            <s:iterator value="userListGiamDoc" status="index" >
                                <s:if test="pushInfo.managerID == userListGiamDoc.get(#index.index)">
                                    <option value="<s:property />" selected="selected"><s:property /></option>
                                </s:if>
                                <s:else>
                                    <option value="<s:property />"><s:property /></option>
                                </s:else>
                            </s:iterator>
                        </select>
                    </tr>
                   
                    
                    <tr align=left class=small><b>Chọn Nhân viên </b></tr>
                    <tr align="left" width="20%">
                        <select name="pushInfo.staffID" class="small" style="width:98%" onchange="onClickStaff(options[selectedIndex].text , 'take');" id="staff">
                            <option value="--select--">--select--</option>
                            <s:iterator value="userListStaff" status="index" >

                                <s:if test="pushInfo.staffID == userListStaff.get(#index.index)">
                                    <option value="<s:property />" selected="selected"><s:property /></option>
                                </s:if>
                                <s:else>
                                    <option value="<s:property />"><s:property /></option>
                                </s:else>


                            </s:iterator>
                        </select>
                    </tr>
                    
                    <tr align=left class=small><b>Chọn Khách hàng </b></tr>
                    <tr align="left" width="20%">
                        <select name="pushInfo.customerID" class="small" style="width:98%" onchange="onClickCustomer(options[selectedIndex].text , 'take');" id="customer">
                            <option value="--select--">--select--</option>
                            <s:iterator value="userListCustomer" status="index" >
                                <s:if test="pushInfo.customerID == userListCustomer.get(#index.index)">
                                    <option value="<s:property />" selected="selected"><s:property /></option>
                                </s:if>
                                <s:else>
                                    <option value="<s:property />"><s:property /></option>
                                </s:else>
                            </s:iterator>
                        </select>
                    </tr>
                    
                    </s:push>
<!--                    <tr align=left class=small><b>Ngày bắt đầu </b></tr>
                    <tr align=left width="20%">
                        <table border=0 cellspacing=0 cellpadding=2>
                            <tr>
                                <s:date format="dd-MM-yyyy" id="dateconverted" name="startDate"/>
                                <td align=left><input name="startDate" id="jscal_field_date_start" type="text" size="10" class="importBox" style="width:70px;" value="<s:property value="startDate"/>"></td>
                                <td valign=absmiddle align=left>
                                    <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_date_start">
                                    <font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "jscal_field_date_start", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_date_start", singleClick: true, step: 1
                                        });

                                    </script>

                                </td>
                            </tr>
                        </table>
                    </tr>
                   
                    <tr align=left class=small><b>Ngày kết thúc </b></tr>
                    <tr align=left width=20%>
                        <table border=0 cellspacing=0 cellpadding=2>
                            <tr>
                                <td align=left><input name="endDate" id="jscal_field_date_end" type="text" size="10" class="importBox" style="width:70px;" value="<s:property value="endDate"/>"></td>
                                <td valign=absmiddle align=left><img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_date_end"><font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "jscal_field_date_end", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_date_end", singleClick: true, step: 1
                                        });
                                    </script>
                                </td>
                            </tr>
                        </table>
                    </tr>-->
                
                <tr>
                    <td  ><input name="generatenw" value=" Lọc kết quả " class="crmbutton small create" type="submit" ></td>
                    <td  ><input name="generatenw" value=" Quay lại " class="crmbutton small create" type="button" onclick="window.location.href='/DMS/customer-list'"></td>
                </tr>
                </table>
            </form>
            </div>
            <div id="info">
                <div id="page">
                    Trang
                    <ul class="frag">
                        <%
                            int min = 0;
                            if(request.getParameter("page") != null){
                                int currPage = Integer.parseInt(request.getParameter("page"));
                                session.setAttribute("currPage", currPage);
                            }
                        %>
                        <li><a href="?page=<s:property value="#attr.currPage - 1"/>">&lt;</a></li>
                        <s:iterator  value="listCustomer" status="status" >
                            <s:if test="#status.index < (listCustomer.size() -1)/30+1">
                            <li ><a href="?page=<s:property value="#status.index"/>">
                                    
                                    <s:if test="#attr.currPage == #status.index">
                                    [<s:property value="#status.index"/>]
                                    </s:if>
                                    <s:else>
                                        <s:property value="#status.index"/>
                                    </s:else>
                                    
                                </a></li>
                            </s:if>
                        </s:iterator>    
                        <li ><a href="?page=<s:property value="#attr.currPage + 1"/>">&gt;</a></li>    
                       
                    </ul>
                </div>

                <div id="detail">
                    <ul class="gallery">
                            <%
                            if(request.getParameter("page") != null){
                                int page2 = Integer.parseInt(request.getParameter("page")) * 30;
                                pageContext.setAttribute("first", page2);
                            }
                            %>
                            <s:subset source="listCustomer" start="%{#attr.first}"  count="30">
                                
                                <s:iterator  status="status" >
                
                                    <li onmouseover="onGetLocation(this, <s:property value="coordinateX"/> , <s:property value="coordinateY"/>);" onmouseout="out();">
                                        <s:set var = "breakLoop" value = "false" />
                                        <s:iterator value="listCustomerImage" status="status2" >
<!--                                            Neu co se break-->
                                            <s:if test="#breakLoop == false">
                                                <s:if test="maDoiTuong == customerID " >
                                                    <img src="/DMS/db_customers/<s:property value="customerID"/>/<s:property value="name"/>"  title="<s:property value="name"/>"/>
                                                    <s:set var = "breakLoop" value = "true" />
                                                </s:if>
                                                
                                            </s:if>    
                                        </s:iterator>
<!--                                        Neu ko co se gan mac dinh-->
                                        <s:if test="#breakLoop == false">
                                            <img class="bgr" src="/DMS/js/maps/map.jpg" title="ảnh mặc định">
                                            
                                        </s:if>
                                    <!--                                    <h2><span></span></h2>--><br>
<!--                                    <span class="name"></span>-->
                                    <p id="name"><a style="color: white" href="filter-result?page=0&customer_id=<s:property value="maDoiTuong"/>"><s:property value="doiTuong"/></a></p>
                                </li>
                                
                                </s:iterator>
                                    
                            </s:subset>
                        
                        
                    </ul>
                </div>
            </div>

        </div>

        <script>
            var jg ;//= new jsGraphics("canvas");
            
            //var img1 = document.getElementById("cl1");
            function drawLine(x , y , p) {
                console.log(x);
                jg = new jsGraphics("canvas");
                
                var point = p;//toPixel();
                jg.setColor("#000000");
                jg.setStroke(2);
                jg.setPrintable(true);
                jg.drawLine(x, y, point.x, point.y);
                //point.x, point.y
                //img1.offsetLeft + 20 , img1.offsetTop + 20
                //jg.drawLine(0, 0, 500, 600);
                jg.paint();
            }

            function clearLine() {
                jg.clear();
            }
        </script>
    </body>
</html>
