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
        <title>Xem vị trí Nhân viên</title>
        <link REL="SHORTCUT ICON" HREF="${pageContext.request.contextPath}/themes/images/vtigercrm_icon.ico">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">

        </style>
        <!--    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
            </script>-->
        <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/wz_jsgraphics.js"></script>

        <script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/js/ajax_option.js"></script>
        
        <script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.datetimepicker.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/jquery.datetimepicker.css" />
        
<!--        <script type='text/javascript' src="http://www.walterzorn.de/en/scripts/wz_jsgraphics.js"></script>-->
        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/maps/map.css">
        <script type="text/javascript"
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCGEhKh7kMB8aYZpl2nY6jAFo9td-iVA7k&sensor=true">
        </script>
        
        <script type="text/javascript">
            
            var arr = new Array();
            var map;
            function initialize() {
                var i;
                var Customers = [
                    <s:iterator value="currLocations" status="status">
                            {
                                mXCoordinates: <s:property value="viDo"/>,
                                        mYCoordinates: <s:property value="kinhDo"/>,
                                mMaDoiTuong: '<s:property value="maNhanVien"/>'

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
            <s:iterator value="currLocations" status="status">
                    <s:date name="thoiGian" id="createdDateId" format="dd-MM-yyyy HH:mm:ss"/>
                    '<div style="width:250px;">Mã nhân viên: <s:property value="maNhanVien"/>' + '<br/>' +
                    'Tên nhân viên: <s:property value="tenNhanVien"/>' + '<br/>' +
                    'Thời gian: <s:property value="%{createdDateId}"/>' + '<br/>' +
                    'Ghi chú: <s:property value="ghiChu"/>' + '<br/>' +
                    'Tọa độ X: <s:property value="viDo"/> <br/> \n'+
                    'Tọa độ Y: <s:property value="kinhDo"/> <br/>   </div>',
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
                <form id="sub_form" method="get" action="current-location-filter?page=0">
                <table border=0 cellspacing=0 cellpadding=0 width="200px">
<!--                    <select style="padding: 5px; width: 92%;">
                        <s:iterator value="cities">
                            <option><s:property/></option>
                        </s:iterator>
                    </select>-->
                    <input id="datetimepicker" type="text" style="padding: 5px; width: 85%;" >
                    <script type="text/javascript">
                        
                        jQuery('#datetimepicker').datetimepicker();
                        
                    </script>
                <tr>
                    <td  ><input name="generatenw" value=" Lọc kết quả " class="crmbutton small create" type="submit" ></td>
                    <td  ><input name="generatenw" value=" Quay lại " class="crmbutton small create" type="button" onclick="window.location.href='${pageContext.request.contextPath}/customer-list'"></td>
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
                        <s:iterator  value="currLocations" status="status" >
                            <s:if test="#status.index < (currLocations.size() -1)/30+1">
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
                            <s:subset source="currLocations" start="%{#attr.first}"  count="30">
                                
                                <s:iterator  status="status" >
                
                                    <li onmouseover="onGetLocation(this, <s:property value="viDo"/> , <s:property value="kinhDo"/>);" onmouseout="out();">
                                        <s:set var = "breakLoop" value = "false" />
                                        
<!--                                    Neu co se break-->
                                        <s:if test="#breakLoop == false">
                                            <s:if test="maDoiTuong == customerID " >
                                                <img src="${pageContext.request.contextPath}/db_customers/<s:property value="customerID"/>/<s:property value="name"/>"  title="<s:property value="name"/>"/>
                                                <s:set var = "breakLoop" value = "true" />
                                            </s:if>

                                        </s:if>    
<!--                                        Neu ko co se gan mac dinh-->
                                        <s:if test="#breakLoop == false">
<!--                                            <img class="bgr" src="${pageContext.request.contextPath}/js/maps/map.jpg" title="ảnh mặc định">-->
                                            
                                        </s:if>
                                    <!--                                    <h2><span></span></h2>--><br>
<!--                                    <span class="name"></span>-->
                                    <p id="name"><a style="color: white" href="filter-result?page=0&customer_id=<s:property value="maNhanVien"/>"><s:property value="maNhanVien"/></a></p>
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
