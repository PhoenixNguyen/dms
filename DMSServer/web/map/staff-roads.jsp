<%-- 
    Document   : index
    Created on : Jan 5, 2014, 3:28:43 PM
    Author     : HP
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<%

    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
            <sx:head />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Quản lý hành trình</title>

            <link type="text/css" rel="stylesheet" href="../css/map/view-map.css"/>
            
            <script type="text/javascript" src="../js/jquery.min.js"></script>
            <script type="text/javascript" src="../js/view-data-script.js"></script>
            <script type="text/javascript" src="../js/view-map.js"></script>
            <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyDY0kkJiTPVd2U7aTOAwhc9ySH6oHxOIYM&sensor=false">
            </script>
            
            <script type="text/javascript">
            var arr = new Array();
            
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

                var myOptions = {
                    zoom: 13,
                    center: new google.maps.LatLng(<s:property value="listRoad.get(0).get(0).getViDo()"/>, <s:property value="listRoad.get(0).get(0).getKinhDo()"/>),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map = new google.maps.Map(document.getElementById("googleMap"), myOptions);

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
        </script>
        
        <script>
            $(document).ready(function(){
                $('.category-wrapper').click(function () {
                   var currentId = '#' + $('.hide:visible').prop('id');
                   var newId = $(this).data('rel');
                   $('.hide').fadeOut();
                   if (currentId != newId) {
                       $(newId).fadeIn();
                   }
               });
             });
        </script>
        <script type="text/javascript">
        // load ajax
        var gdID;
        //load sales man list
        function getLoadStaff2(x){
//            _url = location.href;
//            _url += (_url.split('?')[1] ? '&':'?') + param;
//            return _url;
//            
//            window.location.search = jQuery.query.set("rows", 10);
            
            var giamdocId = x !== "--select--" ? x:"nullid";//$('#giamDoc').val();
            gdID = giamdocId;
            console.log("Ma giam doc: " + giamdocId);
             $.getJSON('filterGiamDoc2.action', {'giamdocId': giamdocId},
                 function(data) {

                        var divisionList = (data.userListStaff);
                             console.log("log: " + divisionList);
                             var options = $("#staff");
                             options.find('option')
                 .remove()
                 .end();
                  options.append($("<option />").val("-1").text("--select--"));
             $.each(divisionList, function(k , v) {

                 options.append($("<option />").val(k).text(v));
             });
                 }
             );
         }
         
         //load customer list 
         function getLoadCustomer2(x){
            var staffId = x !== "--select--" ? x:"nullid";//$('#giamDoc').val();
            //gdID = giamdocId;
            console.log("Ma nhan vien: " + staffId);
             $.getJSON('filterStaff2.action', {'nhanvienId': staffId},
                 function(data) {

                        var divisionList = (data.userListCustomer);
                             console.log("log: " + divisionList);
                             var options = $("#customer");
                             options.find('option')
                 .remove()
                 .end();
                  options.append($("<option />").val("-1").text("--select--"));
             $.each(divisionList, function(k , v) {

                 options.append($("<option />").val(k).text(v));
             });
                 }
             );
         }
         
         //Khi nhan vao chon khach hang
         function getLoadCustomerDetail(x){
            var staffId = x !== "--select--" ? x:"nullid";//$('#giamDoc').val();
            //gdID = giamdocId;
            console.log("Ma nhan vien: " + staffId);
             $.getJSON('filterCustomer.action', {'khachhangId': staffId},
                 function(data) {

                        var divisionList = (data.userListStaff);
                             //console.log("log: " + divisionList);
//                             var options = $("#staff");
//                             options.find('option')
//                 .remove()
//                 .end();
//                  options.append($("<option />").val("-1").text("--select--"));
//             $.each(divisionList, function(k , v) {
//
//                 options.append($("<option />").val(k).text(v));
//             });
                 }
             );
         }
        
         dojo.event.topic.subscribe("/value", function(textEntered, date, widget){
             //var date ;
             console.log("SetDate: " +date);
            //date = dojo.byId("setdate").value; 
//            $.getJSON('filterDate.action', {'date': date},
//                 function(data) {
//
//                        var divisionList = (data.date);
//                    }
//            );
        });
   </script>            
    </head>
    <body>

        <!-- End Alexa Certify Javascript -->
        <div id="topbar-placeholder">
            <style type="text/css">#topbar-widget,#topbar-widget li,#topbar-widget a{line-height:28px;height:28px}#topbar-widget[hidden]{display:none}#topbar-widget{font:12px Arial,Helvetica,sans-serif;position:relative;background:#4C4C4C;color:#fff}#topbar-widget .topbar-title,#topbar-widget button{display:none}#topbar-widget ul{background:#4C4C4C;list-style:none;white-space:nowrap;margin:0;padding:0}#topbar-widget .topbar-active,#topbar-widget a{padding:0 20px}#topbar-widget li,#topbar-widget a{display:inline-block;*display:inline;*zoom:1;color:#FFF;text-decoration:none;vertical-align:top;-webkit-transition:background-color 50ms;-moz-transition:background-color 50ms;-ms-transition:background-color 50ms;transition:background-color 50ms}#topbar-widget a:hover,#topbar-widget .topbar-active{background-color:#3F3F3F;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3f3f3f',endColorstr='#3f3f3f',GradientType=0);position:relative}#topbar-widget .topbar-active{cursor:default}#topbar-widget .topbar-arrow{display:block;position:absolute;overflow:hidden;top:100%;left:50%;width:0;height:0;content:' ';border-left:4px solid transparent;border-right:4px solid transparent;border-bottom:3px solid #fff;margin-top:-3px;margin-left:-3px;font-size:0}#topbar-widget.topbar-widget-mobile .topbar-title{font-size:18px;padding:0 50px 0 15px;line-height:50px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis}#topbar-widget.topbar-widget-mobile,#topbar-widget.topbar-widget-mobile li{line-height:50px;height:50px}#topbar-widget.topbar-widget-mobile .topbar-title{display:block}#topbar-widget.topbar-widget-mobile button{display:block;width:32px;height:30px;background:#454545 url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAMCAYAAABr5z2BAAAAGUlEQVQokWP4TyFgoNiAYQBGA5EKYMADEQDcJn6QVVN+LQAAAABJRU5ErkJggg==') no-repeat 50% 50%;border:1px solid #3E3E3E;border-radius:2px;right:9px;top:9px;position:absolute}#topbar-widget.topbar-widget-mobile .topbar-active{display:none}#topbar-widget.topbar-widget-mobile a:hover{background-color:transparent}#topbar-widget.topbar-widget-mobile .topbar-link,#topbar-widget.topbar-widget-mobile .topbar-link a{font-size:15px;line-height:32px;height:32px;display:block}#topbar-widget.topbar-widget-mobile .topbar-link{border-top:1px solid #3B3B3B;background:#4C4C4C}#topbar-widget.topbar-widget-mobile .topbar-link a{border-top:1px solid #535353}#topbar-widget.topbar-widget-mobile .topbar-link a:hover{background:#3F3F3F;border-color:#3F3F3F}#topbar-widget.topbar-widget-mobile ul{overflow:hidden;position:absolute;-webkit-transition:height 150ms;top:100%;left:0;right:0;z-index:2147483647;height:0}#topbar-widget.topbar-widget-mobile button.expanded+ul{height:135px}</style>
            <div id="topbar-widget" class="">
                <span class="topbar-title">Quay lại</span>
                <button onclick="this.className = this.className === 'expanded' ? '' : 'expanded';"></button>
                <ul>
                    <li class="topbar-link"><a href="/DMS/staff-list" title="Trang chủ" index="0">Quay lại</a></li>
<!--                    <li class="topbar-active" title=""><span class="topbar-arrow"></span></li>
                    <li class="topbar-link"><a href="" title="" index="2"></a></li>
                    <li class="topbar-link"><a href="" title="" index="3"></a></li>
                    <li class="topbar-link"><a href="" title="" index="4"></a></li>-->
                </ul>
            </div>
        </div>

        <div id="wrapper" class="has-topbar">
            <div id="header" class="clearfix">
<!--                <h1 class="logo"><a href="http://localhost:8080/DMSProject" class="hide-text" target="">Trang chủ</a> <span>
                        <a href="/">Thông tin khách hàng</a>			</span></h1>-->
                <h1>Quản lý hành trình</h1> <br/>       

                <div class="right-app">
                    <a href="" class="android-app" target="_blank"></a>
                </div>


                <div class="searchs">
                    <form action="staff-roads.action" method="post" name="search-poi">
                        <ul>
<!--                            <li>
                                <div id="keys">
                                    <a title="Tiếng Việt" id="btn-language" href="#">v</a>
                                    <input type="text" name="keys" class="keyboardInput" value="" autocomplete="off">
                                        <a href="#" class="clear-text hide">×</a>
                                </div>
                            </li>
                            <li>
                                <div><input type="submit" name="finds" value="Tìm kiếm"></div>
                            </li>-->
                            
<!--                            <li class="advance-text clear" >Lọc khách hàng</li>-->
                            <s:push value="pushInfo">
                            <li class="category-wrapper" data-rel="#callback-form"><a href="#">Giám đốc</a>

                                <s:select name="pushInfo.managerID" list="userListGiamDoc" 
                                          onchange="getLoadStaff2(options[selectedIndex].text)" headerKey="0" headerValue="--select--"  />
                            </li>
                            
                            <li class="category-wrapper" data-rel="#enquiry-form"><a href="#">Nhân viên</a>

                                <s:select name="pushInfo.staffID"  list="userListStaff" id="staff"
                                          onchange="getLoadCustomer2(options[selectedIndex].text)"  headerKey="0" headerValue="--select--" />
                            </li>
                            
                            <li class="category-wrapper" data-rel="#booknow-form"><a href="#">Khách hàng</a>
                                <s:select name="pushInfo.customerID"  list="userListCustomer" id="customer"
                                          onchange="getLoadCustomerDetail(options[selectedIndex].text)"  headerKey="0" headerValue="--select--" />
                                
                                
                            </li>
                                
                            <li class="category-wrapper" data-rel="#booknow-form"><a href="#">Ngày</a>
                                <sx:datetimepicker  name="date" displayFormat="yyyy-MM-dd" valueNotifyTopics="/value" onchange="setDate(this);" id="setdate"/>
                                
                                
                           
                                
                            </li>

                            </s:push>    
                            
                            <li class="category-wrapper" data-rel="#enquiry-form"><a href=""></a>
                                
                                
                                <input type="submit" name="finds"  value="Lọc" style="width: 100px;
                                        height: 30px;margin-top: -28px;margin-left: 20px;position: absolute;">
                                
                                <!--sx:autocompleter size="1"  list="userListStaff" keyValue="mID"name="mID">-->
                            </li>
                                                       
                        </ul>
                    </form>
                </div>
            </div>
           
            <div id="container" class="poi-detail-page clearfix" data-id="542591" data-hash="6896202108404922949" data-fe-category="1" data-coordinates="21.03063357,105.85507392996" data-icon="2.jpg">
                <div class="left-content">
                    <div class="article-top">
                        <div id="googleMap" style="width:100%;height:500px;"></div>
                    </div>
                    <div class="article-content">
                        <div class="article-title">
                            <h2>
                                <s:property value="listCustomer.get(0).getDoiTuong()"/>
                            </h2>
                        </div>
                        <div class="content">
                            <div class="poi-infos">
                                <p><strong>Địa chỉ:</strong>
                                    <s:property value="listCustomer.get(0).getDiaChi()"/></p>
                                <p><strong>Điện thoại:</strong>
                                    <s:property value="listCustomer.get(0).getDienThoai()"/></p>                      </p>
                                <p><strong>Fax</strong>
                                     <s:property value="listCustomer.get(0).getFax()"/> </p>
                                <p class="poi-website"><strong>Website:</strong> <a target="_blank" href="">
                                                         </a></p>
                            </div>
                        </div>
                        <div class="content">
                            <div class="poi-infos">
                                <div class="poi-rating"><strong>Rating:</strong>
                                    <div class="rate-wrapper">
                                        <div class="rate-value" style="width: 93.4%"></div>
                                    </div>
                                </div>
                                <div>
                                    <a href="" class="google-link" target="_blank"></a></div>
                            </div>
                        </div>
                        <div class="content clearfix">
<!--                            <div class="article-share">Chia sẻ:<br>
                                    <a target="_blank" class="facebook hide-text" rel="facebook" href="">Facebook</a>
                                    <a target="_blank" class="twitter hide-text" rel="twitter" href="">Twitter</a>
                                    <a target="_blank" class="zingme hide-text" rel="zingme" href="">Zing
                                        me</a>
                            </div>-->
                        </div>
                        
                        <div class="right-content">
                            <div class="article-title">
                                <h3>Danh sách ảnh tại nơi khách hàng:</h3>
                            </div>
                            <ul class="pois-list show-all">


                                        <s:iterator status="status" value="filesNameList" >
                                <li data-poi-id="2275">
                                    <div class="poi-content">
                                        <div class="poi-photo">
                                            <img src="../db_customers/<s:property value="listCustomer.get(0).getMaDoiTuong()"/>/<s:property value="filesNameList.get(#status.index)"/>" 
                                                 data-original="../db_customers/<s:property value="listCustomer.get(0).getMaDoiTuong()"/>/<s:property value="filesNameList.get(#status.index)"/>" width="64" height="64" alt="Pane e Vino">
                                        </div>
                                        <h2 class="poi-title"><a href="../db_customers/<s:property value="listCustomer.get(0).getMaDoiTuong()"/>/<s:property value="filesNameList.get(#status.index)"/>">Ảnh <s:property value="#status.index+1"/></a></h2>
                                        <div class="poi-infos">
                                            <strong>Tiêu đề: <s:property value="filesNameList.get(#status.index)"/>
                                            <div class="poi-rating"><strong>Rating:</strong><div class="rate-wrapper">
                                                    <div class="rate-value" style="width: 77.8%">

                                                    </div>

                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </li>
                                    </s:iterator>



                            </ul>
                            <div class="more-places"><!--<a href="#">Xem thêm...</a>--></div>
                        </div>
<!--                        <div class="content-bottom clearfix">
                            <div class="poi-infos">
                                <a href="" class="back-link">Quay lại</a>
                            </div>
                        </div>-->

                    </div>
                    <div class="article-photo"><img src="../db_customers/<s:property value="listCustomer.get(0).getMaDoiTuong()"/>/<s:property value="filesNameList.get(0)"/>" style="margin-top: -44.5px; " width="300" >
                    </div>

                </div>

                <div class="right-content-new">
                    <div class="article-title">
                        <h3>Chi tiết tọa độ:</h3>
                    </div>
                    <table class="tb_road">
                        <col width="15%">
                        <col width="25%">
                        <col width="30%">
                        <col width="15%">
                        <col width="15%">
                        <thead>
                            <tr >
                                <th >Mã nhân viên</th>
                                <th >Tên nhân viên</th>
                                <th >Thời gian</th>
                                <th >Vĩ độ</th>
                                <th >Kinh độ</th>
                            </tr>
                        </thead>
                        
                        <tbody>
                            <s:iterator value="listRoad" status="status">
                                <s:iterator value="listRoad.get(#status.index)" >
                                    <s:date name="thoiGian" id="createdDateId" format="HH:mm:ss dd-MM-yyyy "/>
                                <tr >
                                    <td><s:property value="maNhanVien"/></td>
                                    <td></td>
                                    <td><s:property value="%{createdDateId}"/></td>
                                    <td><s:property value="viDo"/></td>
                                    <td><s:property value="kinhDo"/></td>
                                </tr>
                                </s:iterator>
                            </s:iterator>
                            
                        </tbody>
                            
                    </table>
                </div>
            </div>

            
        </div>


    </body>
</html>