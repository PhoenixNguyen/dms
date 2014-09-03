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
        <title>Quản lý hành trình</title>
        <link REL="SHORTCUT ICON" HREF="/DMS/themes/images/vtigercrm_icon.ico">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <style type="text/css">

        </style>
        <!--    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js">
            </script>-->
        <script type="text/javascript" src="/DMS/jscalendar/calendar.js"></script>
        <script type="text/javascript" src="/DMS/jscalendar/calendar-setup.js"></script>
        <script type="text/javascript" src="/DMS/jscalendar/lang/calendar-vn.js"></script>
        
        <script language="JavaScript" type="text/javascript" src="/DMS/include/js/general.js"></script>
        
        <script language="JavaScript" type="text/javascript" src="/DMS/js/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="/DMS/js/wz_jsgraphics.js"></script>

        <script language="JavaScript" type="text/javascript" src="/DMS/js/ajax_option.js"></script>
        
        
        <link rel="stylesheet" type="text/css" href="/DMS/jscalendar/calendar-win2k-cold-1.css">
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
                //For journey
                var Points = [
                    <s:iterator value="listRoad" status="status">
                    <s:iterator value="listRoad.get(#status.index)" >
                            {
                                mXCoordinates: <s:property value="viDo"/>,
                                        mYCoordinates: <s:property value="kinhDo"/>,
                                mMaDoiTuong: '<s:property value="thoiGian"/>'

                            },
                    </s:iterator>
                    </s:iterator>
                ];

                var x = 21.030336;
                var y = 105.85814 ;
                if(Points.length > 0){
                    if(Points[0].mYCoordinates > 0){
                        x = Points[0].mXCoordinates;
                        y = Points[0].mYCoordinates;
                    }
                }
                var myOptions = {
                    zoom: 13,
                    center: new google.maps.LatLng(x, y),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                map = new google.maps.Map(document.getElementById("map-canvas"), myOptions);

                var infowindow = new google.maps.InfoWindow({
                    content: ''
                });

                var contentString = [
                    <s:iterator value="listRoad" status="status">
                    <s:iterator value="listRoad.get(#status.index)" status="index">
                    <s:date name="thoiGian" id="createdDateId" format="HH:mm:ss dd-MM-yyyy "/>
                            'Mã Nhân viên: <s:property value="maNhanVien"/> <br/>\
                            Thời gian:<br/><s:property value="%{createdDateId}"/> <br/>\
                            Thứ tự di chuyển: <s:property value="%{#index.index + 1}"/>',
                    </s:iterator>
                    </s:iterator>
                ];

                for (i = 0; i < Points.length; i++) {
                    size = 15;
                    var img = new google.maps.MarkerImage('../db_images/marker.jpg',
                            new google.maps.Size(size, 2 * size),
                            new google.maps.Point(0, 0),
                            new google.maps.Point(size / 2, size / 2)
                            );

                    var marker = new google.maps.Marker({
                        map: map,
                        title: Points[i].title,
                        position: new google.maps.LatLng(Points[i].mXCoordinates, Points[i].mYCoordinates),
                        icon: img
                    });

                    bindInfoWindow(marker, map, infowindow, contentString[i], Points[i].mMaDoiTuong);

                }
                
                //For schedule ---------------------------------------------------------------------------------------------------------
                var Points2 = [
                    <s:iterator value="listSchedules" status="status">
                        <s:iterator value="listScheduleAndCustomer" status="index">
                            <s:iterator value="listScheduleAndCustomer.get(#index.index)"   status="index2">
                                <s:if test="listSchedules.get(#status.index).getMaKH() == maDoiTuong and coordinateX > 0">
                            {
                                mXCoordinates: <s:property value="coordinateX"/>,
                                        mYCoordinates: <s:property value="coordinateY"/>,
                                mMaDoiTuong: '<s:property value="maDoiTuong"/>'

                            },
                            </s:if>
                            </s:iterator>
                        </s:iterator>
                    </s:iterator>
                ];
                var contentString2 = [
                    <s:iterator value="listSchedules" status="status">
                        
                        <s:iterator value="listScheduleAndCustomer" status="index">
                            <s:iterator value="listScheduleAndCustomer.get(#index.index)"   status="index2">
                            <s:if test="listSchedules.get(#status.index).getMaKH() == maDoiTuong and coordinateX > 0">
                            
                                <s:date name="listSchedules.get(#status.index).getTime()" id="createdDateId" format="HH:mm:ss dd-MM-yyyy "/>
                                
                                
                                    'Thời gian : <s:property value="%{createdDateId}"/> <br/>\
                                    Mã khách hàng: <s:property value="listSchedules.get(#status.index).getMaKH()"/> <br/>\
                                    Tên khách hàng: <s:property value="doiTuong"/> <br/>\
                                    Mã nhân viên: <s:property value="listSchedules.get(#status.index).getMaNV()"/> <br/>\
                                    Thứ tự lịch trình: <s:property value="%{#index2.index +1 }"/>',
                                   
                            </s:if>
                            </s:iterator>
                        </s:iterator>
                    </s:iterator>
                                            
                ];
                
                for (i = 0; i < Points2.length; i++) {
                    size = 15;
//                    var img = new google.maps.MarkerImage('../images/marker.jpg',
//                            new google.maps.Size(size, 2 * size),
//                            new google.maps.Point(0, 0),
//                            new google.maps.Point(size / 2, size / 2)
//                            );

                    var marker = new google.maps.Marker({
                        map: map,
                        title: Points2[i].title,
                        position: new google.maps.LatLng(Points2[i].mXCoordinates, Points2[i].mYCoordinates)
                        
                    });

                    bindInfoWindow(marker, map, infowindow, contentString2[i], Points2[i].mMaDoiTuong);

                }
                
                //multi polyline - line -------------------------------------------------------------------------------------------------
                var multi = [
                    //Road
                    <s:iterator value="listRoad" status="status">
                    
                    [
                        <s:iterator value="listRoad.get(#status.index)" >
                                new google.maps.LatLng(<s:property value="viDo"/>, <s:property value="kinhDo"/>),
                        </s:iterator>
                        
                    ],
                    </s:iterator>  
                
                    //Schedule =================================================================================
                    <s:iterator value="listScheduleAndCustomer" status="status">
                    
                    [
                        <s:iterator value="listScheduleAndCustomer.get(#status.index)"   status="index">
                            <s:if test="coordinateX > 0">
                                new google.maps.LatLng(<s:property value="coordinateX"/>, <s:property value="coordinateY"/>),
                            </s:if>
                        </s:iterator>
                        
                    ],
                    </s:iterator> 
                                        
                ];
                var color =  ["#FF0000", "#00FFFF", "#000000", "#006400","#FF8C00", "#8FBC8F", "#9400D3", "#FF1493"
                               ,"#FF0023", "#00FFEE", "#000033", "#006455","#FF8C66", "#8FBC9E", "#9400E4", "#FF14AA"
                             ];
                console.log("multi: " + multi.length);
                for (i = 0; i < multi.length; i++) {
                    var flightPath1 = new google.maps.Polyline({
                    path: multi[i],
                    geodesic: true,
                    strokeColor: color[Math.floor(Math.random() * color.length)] ,
                    strokeOpacity: 1.0,
                    strokeWeight: 2
                  });

                  flightPath1.setMap(map);

                }
                

            }
            function getListToShowPolyline(){
                var list = [{},{}];
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

            <div id="filter" style="margin-top: -17%;">
                <form id="sub_form" method="POST" action="filter-result-journey">
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
                    <tr align=left class=small><b>Chọn ngày </b></tr>
                    <tr align=left width="20%">
                        <table border=0 cellspacing=0 cellpadding=2>
                            <tr>
                                
                                <s:date format="dd-MM-yyyy" id="dateconverted" name="date"/>
                                <td align=right><input name="date" id="jscal_field_date_start" type="text" size="10" class="importBox" style="width:70px;margin-left: 30px;" value="<s:property value="date"/>"></td>
                                <td valign=absmiddle align=right>
                                    <img style="margin-left: -110px;margin-top: -5px;position: absolute;
                                            "src="/DMS/themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_date_start">
                                    <font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                    <script type="text/javascript">
                                        Calendar.setup({
                                            inputField: "jscal_field_date_start", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_date_start", singleClick: true, step: 1
                                        });

                                    </script>

                                </td>
                                
                            </tr>
                        </table>
                    </tr><!--
                   
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
                    <td>
                        <input name="generatenw" value=" Lọc kết quả " class="crmbutton small create" type="button" 
                                 onclick="
                                            if(validate(this.form)){
                                                //alert('hello');
                                                document.getElementById('sub_form').submit();
                                            }
                                            else {
                                                //alert('hello2');
                                                return false;
                                            }
                                            " name="button" >
                    </td>
                    <td  ><input name="generatenw" value=" Quay lại " class="crmbutton small create" type="button" onclick="window.location.href='/DMS/customer-list'"></td>
                </tr>
                </table>
            </form>
            </div>
            <div id="info">
                <div id="page">
                    Danh sách vị trí lần lượt
                </div>

                <div id="detail">
                    <ul class="gallery">
                            <s:iterator value="listRoad" status="status">
                                <s:iterator value="listRoad.get(#status.index)" >
                                    <s:date name="thoiGian" id="createdDateId" format="HH:mm:ss dd-MM-yyyy "/>
                
                                <li onmouseover="onGetLocation(this, <s:property value="viDo"/> , <s:property value="kinhDo"/>);" onmouseout="out();">
                                    <img class="bgr" src="/DMS/js/maps/map.jpg" >
                                    <!--                                    <h2><span></span></h2>--><br>
<!--                                    <span class="name"></span>-->
                                    <p id="name"><s:property value="maNhanVien"/> : <s:property value="%{createdDateId}"/></p>
                                </li>
                                
                                </s:iterator>
                            </s:iterator>
                        
                        
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
        
        <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                   
                    //2. ID
                    var date_length = form.date.value.length;
                    var date_value = form.date.value;
                    
                   
                    if (trim(date_value) == "") {
                        alert("Hãy chọn ngày để xem.");
                        return false;
                    }
               
                   
                    return true;
                }
            </script>
    </body>
</html>
