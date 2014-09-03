<%-- 
    Document   : menu
    Created on : May 1, 2014, 4:06:57 PM
    Author     : HP
--%>

<%@page import="com.hp.domain.Announcement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
<!--    include-->
        <a name="top"></a>
        
        <!-- header-vtiger crm name & RSS -->
<!--        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>-->
        <script language="JavaScript" type="text/javascript" src="js/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="js/ajax_option.js"></script>
        <script language="JavaScript" type="text/javascript" src="js/md5.js"></script>
        
        <script language="JavaScript" type="text/javascript" src="include/js/json.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/general.js"></script>
        
        <!-- vtlib customization: Javascript hook --> 
        <script language="JavaScript" type="text/javascript" src="include/js/vtlib.js"></script>
        <!-- END -->
        <script language="JavaScript" type="text/javascript" src="include/js/vn.lang.js?"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/QuickCreate.js"></script>
<!--        <script language="javascript" type="text/javascript" src="include/scriptaculous/prototype.js"></script>-->
        <script language="JavaScript" type="text/javascript" src="include/js/menu.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/calculator/calc.js"></script>
        <script language="JavaScript" type="text/javascript" src="modules/Calendar/script.js"></script>
        <script language="javascript" type="text/javascript" src="include/scriptaculous/dom-drag.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/notificationPopup.js"></script>
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>
        <script type="text/javascript" src="jscalendar/lang/calendar-vn.js"></script>

        <!-- asterisk Integration -->
        <!-- END -->

        <!-- Custom Header Script -->
        <script type="text/javascript" src="modules/Tooltip/TooltipHeaderScript.js"></script>
        <script type="text/javascript" src="modules/SMSNotifier/SMSNotifierCommon.js"></script>
        <script type="text/javascript" src="modules/ModComments/ModCommentsCommon.js"></script>
        <!-- END -->
        
<!--        Header-->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="hdrNameBg">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/vtiger-crm.gif" alt="HOSCO-MANAGEMENT" title="HOSCO-MANAGEMENT" border="0"></td>
                    <td width="100%" align="center">
                        <%
                            Announcement announcement = (Announcement)session.getAttribute("announcement");
                            if(announcement != null){
                                session.setAttribute("creater", announcement.getCreater());
                                session.setAttribute("time", announcement.getDate());
                                session.setAttribute("content", announcement.getContent());
                            }
                            
                        %>
                        <marquee id="rss" direction="left" scrolldelay="10" scrollamount="3" behavior="scroll" class="marStyle" onmouseover="javascript:stop();" 
                                 onmouseout="javascript:start();">&nbsp;<s:property value="%{#attr.creater}"/> : [<s:property value="%{#attr.time}"/>]  <b>Thông báo:</b> <s:property value="%{#attr.content}"/></marquee>

                    </td>
                    <td class="small" nowrap="">
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tbody><tr>


                                    <!-- gmailbookmarklet customization -->
                                    <!--<td style="padding-left:10px;padding-right:10px" class=small nowrap>
                                    <a href='javascript:(function()%7Bvar%20doc=top.document;var%20bodyElement=document.body;doc.vtigerURL%20=%22http://hosgroup.com.vn/hoscomng/%22;var%20scriptElement=document.createElement(%22script%22);scriptElement.type=%22text/javascript%22;scriptElement.src=doc.vtigerURL+%22modules/Emails/GmailBookmarkletTrigger.js%22;bodyElement.appendChild(scriptElement);%7D)();'>Gmail Bookmarklet</a>
                                    </td> -->
                                    <!-- END -->
                                    <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="javascript:void(0);" onclick="vtiger_news(this)">Tin HOSCO</a></td> -->
                                    <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="javascript:void(0);" onclick="vtiger_feedback();">Phản hồi</a></td> -->

                                    <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="home.jsp?module=Users&action=DetailView&record=1&modechk=prefview">Thiết lập cá nhân</a></td>-->
                                    <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap><a href="http://wiki.hosgroup.com.vn/home.jsp/Main_Page" target="_blank">Hướng dẫn sử dụng online</a></td> -->
            <!--                        <td style="padding-left:10px;padding-right:10px" class=small nowrap><a href="javascript:;" onClick="openwin();">Giới thiệu</a></td>-->
                                    <td style="padding-left:10px;padding-right:10px" class="small" nowrap=""> <a href="logout">Thoát</a> (<b><s:property value="#session['user_name']"/></b>)</td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>

        <div id="miniCal" style="width:300px; position:absolute; display:none; left:100px; top:100px; z-index:100000">
        </div>
        
        <!-- MENU header - master tabs -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="hdrTabBg">
            <tbody>
                <tr>
                    <td style="width:50px" class="small">&nbsp;</td>
                    <td class="small" nowrap=""> 
                        <table border="0" cellspacing="0" cellpadding="0">

                            <tbody>
                                <tr>
                                    <s:set  name="page"  >
                                        ${param.page_param}
                                    </s:set>
                                    
                                    <s:if test="#attr.PERMISSION != 0">
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif" width="2px" height="28px"></td>    
                                    <td <s:if test="#page == 'home'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'My Home Page_sub');" onmouseout="fnHideDrop('My Home Page_sub');" align="center" nowrap=""><a href="home">Trang chủ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'staff'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Tools_sub');" onmouseout="fnHideDrop('Tools_sub');" align="center" nowrap=""><a href="staff-list">Nhân viên</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'customer'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Marketing_sub');" onmouseout="fnHideDrop('Marketing_sub');" align="center" nowrap="customer-list"><a href="customer-list">Khách hàng</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'sale'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Sales_sub');" onmouseout="fnHideDrop('Sales_sub');" align="center" nowrap=""><a href="take-order">Bán hàng</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
<!--                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'help'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Support_sub');" onmouseout="fnHideDrop('Support_sub');" align="center" nowrap=""><a href="">Hỗ trợ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>-->
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'report'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Analytics_sub');" onmouseout="fnHideDrop('Analytics_sub');" align="center" nowrap=""><a href="report">Phân tích</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'inventory'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Inventory_sub');" onmouseout="fnHideDrop('Inventory_sub');" align="center" nowrap=""><a href="product-list">Tồn kho</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>

                                    
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'setup'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Settings_sub_user');" onmouseout="fnHideDrop('Settings_sub');" align="center" nowrap=""><a href="admin-detail?id_admin=<s:property value="%{#attr.STT}"/>">Thiết lập</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    
                                    </s:if>
                                    <s:else>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif" width="2px" height="28px"></td>    
                                    <td <s:if test="#page == 'home'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'My Home Page_sub');" onmouseout="fnHideDrop('My Home Page_sub');" align="center" nowrap=""><a href="home">Trang chủ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    <td <s:if test="#page == 'setup'"> class="tabSelected" </s:if> <s:else>class="tabUnSelected"</s:else> onmouseover="fnDropDown(this, 'Settings_sub');" onmouseout="fnHideDrop('Settings_sub');" align="center" nowrap=""><a href="system-manager">Thiết lập</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                                    <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                                    </s:else>
                                </tr>

                            </tbody>
                        </table>
                    </td>
        <!--            <td align="right" style="padding-right:10px" nowrap="">
                        <table border="0" cellspacing="0" cellpadding="0" id="search" style="border:1px solid #999999;background-color:white">
                            <tbody>
                                <tr>
                            <form name="UnifiedSearch" method="post" action="home.jsp" style="margin:0px" onsubmit="VtigerJS_DialogBox.block();"></form>
                            <td style="height:19px;background-color:#ffffef" nowrap="">
                                <a href="javascript:void(0);" onclick="UnifiedSearch_SelectModuleForm(this);"><img src="themes/images/settings_top.gif" align="left" border="0"></a>
                                <input type="hidden" name="action" value="UnifiedSearch" style="margin:0px">
                                <input type="hidden" name="module" value="Home" style="margin:0px">
                                <input type="hidden" name="parenttab" value="Marketing" style="margin:0px">
                                <input type="hidden" name="search_onlyin" value="--USESELECTED--" style="margin:0px">
                                <input type="text" name="query_string" value="Tìm kiếm..." class="searchBox" onfocus="this.value = ''">
                            </td>
                            <td style="background-color:#cccccc">
                                <input type="submit" class="searchBtn" value="Tìm" alt="Tìm" title="Tìm">
                            </td>

                </tr>
            </tbody>
        </table>
        </td>-->

        </tr>
        </tbody>
        </table>

        <!-- - level 2 tabs starts-->
        <table border="0" cellspacing="0" cellpadding="2" width="100%" class="level2Bg">
            <tbody><tr>
                    <td>
                        <table border="0" cellspacing="0" cellpadding="0">
                            <tbody><tr>

                                    <td class="level2UnSelTab" nowrap=""> <a href=""></a> </td>
        <!--                            <td class="level2SelTab" nowrap=""><a href="">Khách hàng</a></td>
                                    <td class="level2UnSelTab" nowrap=""> <a href="">Liên hệ</a> </td>
                                    <td class="level2UnSelTab" nowrap=""> <a href="">Webmails</a> </td>
                                    <td class="level2UnSelTab" nowrap=""> <a href="">Đầu mối</a> </td>
                                    <td class="level2UnSelTab" nowrap=""> <a href="">Lịch</a> </td>
                                    <td class="level2UnSelTab" nowrap=""> <a href="">Tài liệu</a> </td>-->
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>    
        <!-- Level 2 tabs ends -->

        <!-- Drop Down Menu in the Main Tab -->
        <div class="drop_mnu" id="My Home Page_sub" onmouseout="fnHideDrop('My Home Page_sub')" onmouseover="fnShowDrop('My Home Page_sub')">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td><a href="home" class="drop_down">Trang chủ</a></td></tr>

                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Marketing_sub" onmouseout="fnHideDrop('Marketing_sub')" onmouseover="fnShowDrop('Marketing_sub')" style="left: 143px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
        <!--            <tr><td><a href="" class="drop_down">Chiến dịch</a></td></tr>-->
                    <tr><td><a href="customer-list" class="drop_down">Khách hàng</a></td></tr>
                    <tr><td><a href="/DMS/maps/view-locations" class="drop_down">Xem vị trí</a></td></tr>
                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Sales_sub" onmouseout="fnHideDrop('Sales_sub')" onmouseover="fnShowDrop('Sales_sub')" style="left: 244px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
        <!--            <tr><td><a href="" class="drop_down">Đầu mối</a></td></tr>-->
                    <tr><td><a href="take-order" class="drop_down">Đặt hàng</a></td></tr>
                    <tr><td><a href="sale-order" class="drop_down">Bán hàng</a></td></tr>
                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Support_sub" onmouseout="fnHideDrop('Support_sub')" onmouseover="fnShowDrop('Support_sub')" style="left: 331px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td><a href="" class="drop_down">Trợ giúp</a></td></tr>
                    <tr><td><a href="" class="drop_down">Liên hệ</a></td></tr>
                </tbody></table>
        </div>
        <div class="drop_mnu" id="Analytics_sub" onmouseout="fnHideDrop('Analytics_sub')" onmouseover="fnShowDrop('Analytics_sub')" style="left: 401px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td><a href="report" class="drop_down">Báo cáo</a></td></tr>
<!--                    <tr><td><a href="" class="drop_down">Biểu đồ</a></td></tr>-->
                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Inventory_sub" onmouseout="fnHideDrop('Inventory_sub')" onmouseover="fnShowDrop('Inventory_sub')" style="left: 488px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td><a href="product-list" class="drop_down">Sản phẩm</a></td></tr>
                    <tr><td><a href="provider-list" class="drop_down">Nhà cung cấp</a></td></tr>
                    <tr><td><a href="inventory-manager" class="drop_down">Tồn kho</a></td></tr>
        <!--            <tr><td><a href="" class="drop_down">Bảng giá</a></td></tr>
                    <tr><td><a href="" class="drop_down">Nhập hàng</a></td></tr>
                    <tr><td><a href="" class="drop_down">Đặt hàng</a></td></tr>
                    <tr><td><a href="" class="drop_down">Báo giá</a></td></tr>-->

                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Tools_sub" onmouseout="fnHideDrop('Tools_sub')" onmouseover="fnShowDrop('Tools_sub')" style="left: 567px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody>
                    <tr><td><a href="staff-list" class="drop_down">Nhân viên</a></td></tr>
                    <tr><td><a href="report-schedules" class="drop_down">Kế hoạch dự kiến</a></td></tr>
                    <tr><td><a href="maps/view-journeys" class="drop_down">Quản lý hành trình</a></td></tr>
                    <tr><td><a href="report-images" class="drop_down">Quản lý hình ảnh</a></td></tr>
                    <tr><td><a href="report-staff-history" class="drop_down">Quản lý chăm sóc khách hàng</a></td></tr>

                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Settings_sub" onmouseout="fnHideDrop('Settings_sub')" onmouseover="fnShowDrop('Settings_sub')" style="left: 647px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody><tr>
                        <td><a href="system-manager" class="drop_down">Thiết lập</a></td>
                    </tr>
                    <tr>
        <!--                <td><a href="" class="drop_down">Quản lý phân hệ</a></td>-->
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="drop_mnu" id="Settings_sub_user" onmouseout="fnHideDrop('Settings_sub_user')" onmouseover="fnShowDrop('Settings_sub_user')" style="left: 647px; top: 75px; display: none;">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tbody><tr>
                        <td><a href="admin-detail?id_admin=<s:property value="%{#attr.STT}"/>" class="drop_down">Thiết lập</a></td>
                    </tr>
                    <tr>
        <!--                <td><a href="" class="drop_down">Quản lý phân hệ</a></td>-->
                    </tr>
                </tbody>
            </table>
        </div>
        <!-- END Drop Down Menu in the Main Tab -->

        <div id="status" style="position:absolute;display:none;left:850px;top:95px;height:27px;white-space:nowrap;"><img src="themes/softed/images/status.gif"></div>

        <!--END MENU-->
    </body>
</html>
