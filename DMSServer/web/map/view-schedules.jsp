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
        <sx:head />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý kế hoạch của nhân viên</title>
        

        <link type="text/css" rel="stylesheet" href="../css/map/view-map.css"/>
        <link type="text/css" rel="stylesheet" href="../css/map/view-schedule.css"/>

        <script type="text/javascript" src="../js/jquery.min_1.js"></script>
        <script type="text/javascript" src="../js/view-data-script.js"></script>
        <script type="text/javascript" src="../js/view-map.js"></script>
        
        <script type="text/javascript" src="../js/jquery.tablesorter.min.js"></script>
        
        <link href="../css/table/style.css" rel="stylesheet" type="text/css" />
       
        
        <script>
            $(function() {
                $("#myDummyTable").tablesorter({widgets: ['zebra']});
            });
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
                $.getJSON('filterGiamDoc3.action', {'giamdocId': giamdocId},
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
                console.log("Ma nhan vien: " + staffId);
                $.getJSON('filterStaff3.action', {'nhanvienId': staffId},
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

                <div id="topbar-widget" class="">
                    <span class="topbar-title">Quản lý khách hàng</span>
                    <button onclick="this.className = this.className === 'expanded' ? '' : 'expanded';" class=""></button>
                    <ul>
                        <li class="topbar-link"><a href="/DMS/staff-list" title="Trang chủ" index="0">Quay lại</a></li>
<!--                        <li class="topbar-active" title=""><span class="topbar-arrow"></span></li>
                        <li class="topbar-link"><a href="" title="" index="2"></a></li>
                        <li class="topbar-link"><a href="" title="" index="3"></a></li>
                        <li class="topbar-link"><a href="" title="" index="4"></a></li>-->
                    </ul>
                </div>
            </div>

            <!--SEARCH-->

            <div id="header" class="clearfix">
                <!--                <h1 class="logo"><a href="" class="hide-text" target="">DMS</a> 
                                    <span>
                                        Quản lý khách hàng			
                                    </span>
                                    
                                </h1>-->
                                
                <h1>Quản lý kế hoạch của nhân viên</h1> <br/> 
                
                <div class="right-app">
                    <a href="" class="android-app" target=""></a>
                </div>


                <div class="searchs">
                    <form action="showSchedule.action?page=0" method="post" name="search-poi">
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


                                <s:select name="pushInfo.staffID"  list="userListStaff"  id="staff" 
                                          onchange="getLoadCustomer(options[selectedIndex].text)" headerKey="0" headerValue="--select--" />
                                <!--sx:autocompleter size="1"  list="userListStaff" keyValue="mID"name="mID">-->
                            </li>
                            </s:push>
                            <li class="category-wrapper" data-rel="#booknow-form"><a href="#">Từ ngày</a>
                                <sx:datetimepicker name="date" displayFormat="yyyy-MM-dd" valueNotifyTopics="/value" onchange="setDate(this);" id="setdate"/>
                                
                                
                            </li>
                            
                            <li class="category-wrapper" data-rel="#booknow-form"><a href="#">Đến ngày</a>
                                <sx:datetimepicker name="toDate" displayFormat="yyyy-MM-dd" valueNotifyTopics="/value" onchange="setDate(this);" id="settodate"/>
                                
                                
                            </li>
                            
                            <li class="category-wrapper" data-rel="#enquiry-form"><a href=""></a>


                                <input type="submit" name="finds"  value="Lọc" style="width: 100px;
                                       height: 30px;margin-top: -28px;margin-left: 20px;position: absolute;">

                                <!--sx:autocompleter size="1"  list="userListStaff" keyValue="mID"name="mID">-->
                            </li>
                        </ul>
                    </form>
                </div>
            </div>

            <!--FINISH SEARCH-->
        </div>
        <!--        <div id="googleMap" style="width:1000px;height:510px;"></div>-->

        <div id="info_schedule" >

            <div id="bottom_panel">
                <table id="myDummyTable" class="tablesorter" border="0" cellpadding="0" cellspacing="1">
                    <col width="5%">
                    <col width="30%">
                    <col width="15%">
                    <col width="35%">
                    <col width="15%">
                    <thead>
                        <tr>
                            <th>Stt</th>
                            <th>Ngày</th>
                            <th>Mã khách hàng</th>
                            <th>Tên khách hàng</th>
                            <th>Mã nhân viên</th>
                        </tr>
                    </thead>
                    <tr>
                        <%--
                        int page2 = Integer.parseInt(request.getParameter("page")) * 10;
                        pageContext.setAttribute("first", page2);
                        --%>
                        <%--s:subset source="listSchedules" start="%{#attr.first}"  count="10"--%>
                        <s:subset source="listSchedules" >
                        <s:iterator  status="status" >
                            <s:date id="dateconverted" name="time" format="HH:mm:ss dd-MM-yyyy"/>
                        <tr>
                            <td><s:property value="#status.index + 1"/></td>
                            <td><s:property value="%{dateconverted}"/></td>
                            <td><s:property value="maKH"/></td>
                            <td>
                            <s:iterator value="listCustomer" status="index">
                                <s:if test="maKH == listCustomer.get(#index.index).getMaDoiTuong()">
                                    <s:property value="listCustomer.get(#index.index).getDoiTuong()"/>
                                </s:if>
                            </s:iterator>
                            </td>
                            <td><s:property value="maNV"/></td>
                        </tr>
                        </s:iterator>
                        </s:subset>
                    </tr> 
                    
                </table>
                

            </div>
        </div>

    </body>
</html>