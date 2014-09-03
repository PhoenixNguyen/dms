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

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View map detail</title>
        <sx:head />

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
                var Customers = [
            <s:iterator value="listCustomer" status="status">
                    {
                        mXCoordinates: <s:property value="coordinateX"/>,
                                mYCoordinates: <s:property value="coordinateY"/>,
                        mMaDoiTuong: '<s:property value="maDoiTuong:"/>'

                    },
            </s:iterator>
                ];
                console.log("__ "+Customers[0].mXCoordinates + Customers[0].mYCoordinates);
                var x = 21.030336;
                var y = 105.85814 ;
                if(Customers[0].mXCoordinates > 0){
                    x = Customers[0].mXCoordinates;
                    y = Customers[0].mYCoordinates;
                }
                var myOptions = {
                    zoom: 14,
                    center: new google.maps.LatLng(x, y),
                    mapTypeId: google.maps.MapTypeId.ROADMAP
                };

                var map = new google.maps.Map(document.getElementById("googleMap"), myOptions);

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
        </script>

        <script>

            $(document).ready(function() {
                $('.category-wrapper').click(function() {
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
            function getLoadStaff(x) {
//            _url = location.href;
//            _url += (_url.split('?')[1] ? '&':'?') + param;
//            return _url;
//            
//            window.location.search = jQuery.query.set("rows", 10);

                var giamdocId = x !== "--select--" ? x : "nullid";//$('#giamDoc').val();
                gdID = giamdocId;
                console.log("Ma giam doc: " + giamdocId);
                $.getJSON('filterGiamDoc.action', {'giamdocId': giamdocId},
                function(data) {

                    var divisionList = (data.userListStaff);
                    console.log("log: " + divisionList);
                    var options = $("#staff");
                    options.find('option')
                            .remove()
                            .end();
                    options.append($("<option />").val("-1").text("--select--"));
                    $.each(divisionList, function(k, v) {

                        options.append($("<option />").val(k).text(v));
                    });
                }
                );
            }

            //load customer list
            function getLoadCustomer(x) {
                var staffId = x !== "--select--" ? x : "nullid";//$('#giamDoc').val();
                //gdID = giamdocId;
                console.log("Ma nhan vien (map): " + staffId);
                $.getJSON('filterStaff.action', {'nhanvienId': staffId},
                function(data) {

                    var divisionList = (data.userListStaff);
                }
                );
            }

        </script>
    </head>

    <body>

        <div id="head" style="height:60px;">

            <div id="topbar-placeholder">

                <div id="topbar-placeholder">
                    <style type="text/css">#topbar-widget,#topbar-widget li,#topbar-widget a{line-height:28px;height:28px}#topbar-widget[hidden]{display:none}#topbar-widget{font:12px Arial,Helvetica,sans-serif;position:relative;background:#4C4C4C;color:#fff}#topbar-widget .topbar-title,#topbar-widget button{display:none}#topbar-widget ul{background:#4C4C4C;list-style:none;white-space:nowrap;margin:0;padding:0}#topbar-widget .topbar-active,#topbar-widget a{padding:0 20px}#topbar-widget li,#topbar-widget a{display:inline-block;*display:inline;*zoom:1;color:#FFF;text-decoration:none;vertical-align:top;-webkit-transition:background-color 50ms;-moz-transition:background-color 50ms;-ms-transition:background-color 50ms;transition:background-color 50ms}#topbar-widget a:hover,#topbar-widget .topbar-active{background-color:#3F3F3F;filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#3f3f3f',endColorstr='#3f3f3f',GradientType=0);position:relative}#topbar-widget .topbar-active{cursor:default}#topbar-widget .topbar-arrow{display:block;position:absolute;overflow:hidden;top:100%;left:50%;width:0;height:0;content:' ';border-left:4px solid transparent;border-right:4px solid transparent;border-bottom:3px solid #fff;margin-top:-3px;margin-left:-3px;font-size:0}#topbar-widget.topbar-widget-mobile .topbar-title{font-size:18px;padding:0 50px 0 15px;line-height:50px;overflow:hidden;white-space:nowrap;text-overflow:ellipsis}#topbar-widget.topbar-widget-mobile,#topbar-widget.topbar-widget-mobile li{line-height:50px;height:50px}#topbar-widget.topbar-widget-mobile .topbar-title{display:block}#topbar-widget.topbar-widget-mobile button{display:block;width:32px;height:30px;background:#454545 url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAMCAYAAABr5z2BAAAAGUlEQVQokWP4TyFgoNiAYQBGA5EKYMADEQDcJn6QVVN+LQAAAABJRU5ErkJggg==') no-repeat 50% 50%;border:1px solid #3E3E3E;border-radius:2px;right:9px;top:9px;position:absolute}#topbar-widget.topbar-widget-mobile .topbar-active{display:none}#topbar-widget.topbar-widget-mobile a:hover{background-color:transparent}#topbar-widget.topbar-widget-mobile .topbar-link,#topbar-widget.topbar-widget-mobile .topbar-link a{font-size:15px;line-height:32px;height:32px;display:block}#topbar-widget.topbar-widget-mobile .topbar-link{border-top:1px solid #3B3B3B;background:#4C4C4C}#topbar-widget.topbar-widget-mobile .topbar-link a{border-top:1px solid #535353}#topbar-widget.topbar-widget-mobile .topbar-link a:hover{background:#3F3F3F;border-color:#3F3F3F}#topbar-widget.topbar-widget-mobile ul{overflow:hidden;position:absolute;-webkit-transition:height 150ms;top:100%;left:0;right:0;z-index:2147483647;height:0}#topbar-widget.topbar-widget-mobile button.expanded+ul{height:135px}</style>
                    <div id="topbar-widget" class="">
                        <span class="topbar-title">Quay lại</span>
                        <button onclick="this.className = this.className === 'expanded' ? '' : 'expanded';"></button>
                        <ul>
                            <li class="topbar-link"><a href="/DMS/customer-list" title="Quay lại" index="0">Quay lại</a></li>
                            <!--                    <li class="topbar-active" title=""><span class="topbar-arrow"></span></li>
                                                <li class="topbar-link"><a href="" title="" index="2"></a></li>
                                                <li class="topbar-link"><a href="" title="" index="3"></a></li>
                                                <li class="topbar-link"><a href="" title="" index="4"></a></li>-->
                        </ul>
                    </div>
                </div>
            </div>

            <!--SEARCH-->
            <div id="header" class="clearfix">
                <h1 class="logo">
                    Vị trí khách hàng
                    <!--                    <a href="" class="hide-text" target="">DMS</a> 
                                        <span>
                                            Quản lý khách hàng			
                                        </span>-->

                </h1>
                <br/>
                <div class="right-app">
                    <a href="" class="android-app" target=""></a>
                </div>

<!--                <p><sx:autocompleter label="Nhập mã khách hàng" list="{1, 2, 3}" /></p>-->
                <div class="searchs">

                    <form action="showMap.action?page=0" method="post" name="search-poi">
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
                                                        </li>
                                                        
                                                        <li class="advance-text clear">Tìm kiếm nâng cao</li>-->
                            <s:push value="pushInfo">
                            <li class="category-wrapper" data-rel="#callback-form"><a href="#">Giám đốc</a>
                                <s:set id="giamdid" value="giamdocId"/>
                                <s:select name="pushInfo.managerID" list="userListGiamDoc" id="giamDoc"   
                                          onchange="getLoadStaff(options[selectedIndex].text)"  headerKey="0" headerValue="--select--" />
                                <!--sx:autocompleter size="1"  list="userListGiamDoc" keyValue="mID"name="mID"-->
                                </action>
                            </li>
                            <li class="category-wrapper" data-rel="#enquiry-form"><a href="#">Nhân viên</a>
                                <s:select name="pushInfo.staffID"  list="userListStaff" id="staff" 
                                          onchange="getLoadCustomer(options[selectedIndex].text)" headerKey="0" headerValue="--select--" />
                            </li>
                            </s:push>
                            <li class="category-wrapper" data-rel="#enquiry-form"><a href=""></a>
                                <input type="submit" name="finds"  value="Lọc" style="width: 100px;
                                       height: 30px;margin-top: -28px;margin-left: 20px;position: absolute;">
                            </li>
                        </ul>
                    </form>
                </div>
            </div>

            <!--FINISH SEARCH-->
        </div>
        <div id="googleMap" style="width:1000px;height:510px;"></div>

        <div id="info" >

            <div id="left-panel">
                <div id="show-left-panel"></div>
                <div class="scroll-left-panel">
                    <div id="left-panel-content">
                        <ul class="left-poi-list">

                            <%
                                int page2 = Integer.parseInt(request.getParameter("page")) * 20;
                                pageContext.setAttribute("first", page2);
                            %>
                            <s:subset source="listCustomer" start="%{#attr.first}"  count="20">
                                
                                <s:iterator  status="status" >
                                    <li data-poi-id="18299">
                                        <div class="poi-content">
                                            <div class="poi-photo">

                                                <a href="customerDetail.action?page=0&customer_id=<s:property value="maDoiTuong"/>">
                                                    <img src="../db_customers/<s:property value="maDoiTuong"/>/1.jpg"  width="64" height="64" >
                                                </a>

                                            </div>
                                            <h2 class="poi-title">
                                                <a href="customerDetail.action?page=0&customer_id=<s:property value="maDoiTuong"/>"><s:property value="doiTuong"/></a></h2>
                                            <div class="poi-infos">

                                                <strong>Mã khách hàng:</strong> <s:property value="maDoiTuong"/> <br/>
                                                <strong>Địa chỉ:</strong> <s:property value="diaChi"/>

                                                <br><strong>Điện thoại:</strong> <s:property value="dienThoai"/>

                                            </div>
                                        </div>
                                    </li>
                                </s:iterator>
                                    
                            </s:subset>

                        </ul>
                        <div id="left-content-pagination">

                            <ul class="pagination-block">
                                <li data-page="1" class="goto-page"><a href="?page=<%=Integer.parseInt(request.getParameter("page")) - 1%>">&lt;</a></li>
                                    <s:iterator  value="listCustomer" status="status" >
                                        <s:if test="#status.index < (listCustomer.size() -1)/20+1">
                                        <li data-page="0" class="goto-page active"><a href="?page=<s:property value="#status.index"/>"><s:property value="#status.index"/></a></li>
                                        </s:if>
                                    </s:iterator>
                                <li data-page="1" class="goto-page"><a href="?page=<%=Integer.parseInt(request.getParameter("page")) + 1%>">&gt;</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="article-share">
<!--                        <span>Chia sẻ:</span>
                        <a target="_blank" class="facebook hide-text" rel="facebook" href="">Facebook</a>
                        <a target="_blank" class="twitter hide-text" rel="twitter" href="">Twitter</a>
                        <a target="_blank" class="zingme hide-text" rel="zingme" href="">Zing me</a>-->
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>