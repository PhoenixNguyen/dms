<%-- 
    Document   : home
    Created on : Apr 9, 2014, 10:11:04 PM
    Author     : HP
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html>

<html>
    <head>
        <title>admin - Trang chủ - Trang chủ - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
        <!--        <link rel="stylesheet" type="text/css" media="all" href="themes/softed/style.css">-->
        <link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { position:absolute; }</style>
        <![endif]-->

        <!-- End -->

    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">
        <a name="top"></a>
        <!-- header -->
        <!-- header-vtiger crm name & RSS -->
        <script language="JavaScript" type="text/javascript" src="include/js/json.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/general.js"></script>
        <!-- vtlib customization: Javascript hook --> 
        <script language="JavaScript" type="text/javascript" src="include/js/vtlib.js"></script>
        <!-- END -->
        <script language="JavaScript" type="text/javascript" src="include/js/vn.lang.js?"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/QuickCreate.js"></script>
        <script language="javascript" type="text/javascript" src="include/scriptaculous/prototype.js"></script>
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

        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'home'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Trang chủ<a class="hdrLink" href=""></a></td>
                    <td width="100%" nowrap="">


                    </td>
                </tr>
                <tr><td style="height:2px"></td></tr>
            </tbody>
        </table>                                

        <!-- END TOOLS -->

        <div id="searchingUI" style="display:none;">
            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                <tbody><tr>
                        <td align="center">
                            <img src="themes/images/searching.gif" alt="Đang tìm... hãy đợi" title="Đang tìm... hãy đợi">
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>

        <table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                    <td class="showPanelBg" valign="top" width="100%" style="padding:10px;">
                        <!-- SIMPLE SEARCH -->
                                       

                        <!-- PUBLIC CONTENTS STARTS-->
                        <div id="ListViewContents" class="small" style="width:100%;">
                            <script language="JavaScript" type="text/javascript" src="include/js/ListView.js"></script>
                            <form name="massdelete" method="POST" id="massdelete" onsubmit="VtigerJS_DialogBox.block();">
                                <input name="search_url" id="search_url" type="hidden" value="">

                                <!-- List View Master Holder starts -->
                                <table border="0" cellspacing="1" cellpadding="0" width="100%" class="lvtBg" style="font-size: 15px;">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <!-- List View's Buttons and Filters starts -->
                                                <table border="0" cellspacing="0" cellpadding="2" width="100%" class="small">
                                                    <!--                                            
                                                                                            </table>
                                                    <!-- List View's Buttons and Filters ends -->

                                                    <div>
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                            <!-- Table Headers -->
                                                            <tbody>
                                                                <tr>
                                                                    Hệ thống quản lý phân phối - Thông báo:
                                                                </tr>
                                                                
                                                                <!-- Table Contents -->
                                                                


                                                            </tbody>
                                                        </table>
                                                        <br>
                                                        <s:iterator value="announcementList" status="index">
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                            <!-- Table Headers -->
                                                            <tbody>
                                                                <tr>
                                                            <b><s:property value="%{#index.index + 1}"/>. </b> <span style="color: #0070BA"><s:property value="content" /></span><br>
                                                            <span style="font-size: 13px"><s:property value="date" /> - Từ: <b><i><s:property value="creater" /></i></b> <br></span>
                                                                
<!--                                                                    Chọn menu <b>Nhân viên</b> để quản lý nhân viên, các chức năng chính: <br>-->
<!--                                                                    <p style="margin-left: 20px;">1.1. Thêm, sửa xóa nhân viên, thêm nhân viên bằng file excel (số lượng lớn)<br>
                                                                       1.2. Xem kế hoạch dự kiến của nhân viên theo thời gian<br>
                                                                       1.3. Xem hành trình của nhân viên theo ngày (kiểm soát nhân viên)<br>
                                                                       1.4. Xem hình ảnh của nhân viên chụp tại cửa hàng của khách hàng<br>
                                                                       1.5. Xem lịch sử của nhân viên chăm sóc khách hàng<br>
                                                                    </p>-->
                                                                </tr>
                                                                
                                                            </tbody>
                                                        </table>
                                                        <br>
                                                        </s:iterator>
<!--                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                             Table Headers 
                                                            <tbody>
                                                                <tr>
                                                            <b>2.</b> Chọn menu <b>khách hàng</b> để quản lý khách hàng, các chức năng chính: <br>
                                                                    <p style="margin-left: 20px;">2.1. Thêm, sửa xóa khách hàng, thêm khách hàng bằng file excel (số lượng lớn)<br>
                                                                       2.2. Xem vị trí của khách hàng trên bản đồ số<br>
                                                                       
                                                                    </p>
                                                                </tr>
                                                                
                                                                 Table Contents 
                                                                


                                                            </tbody>
                                                        </table>
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                             Table Headers 
                                                            <tbody>
                                                                <tr>
                                                            <b>3.</b> Chọn menu <b>Tồn kho</b> để quản lý thông tin sản phẩm, các chức năng chính: <br>
                                                                    <p style="margin-left: 20px;">3.1. Thêm, sửa xóa sản phẩm, nhà cung cấp, thêm bằng file excel (số lượng lớn)<br>
                                                                       3.2. Xem tồn kho tại thời điểm kiêm kê của khách hàng<br>
                                                                       
                                                                    </p>
                                                                </tr>
                                                                
                                                                 Table Contents 
                                                                


                                                            </tbody>
                                                        </table>
                                                        
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                             Table Headers 
                                                            <tbody>
                                                                <tr>
                                                            <b>4.</b> Chọn menu <b>Bán hàng</b> để quản lý thông tin Bán hàng, các chức năng chính: <br>
                                                                    <p style="margin-left: 20px;">4.1. Xem chi tiết đơn đặt hàng và bán hàng của nhân viên<br>
                                                                       4.2. Sửa xóa đơn đặt hàng và bán hàng<br>
                                                                       
                                                                    </p>
                                                                </tr>
                                                                
                                                                 Table Contents 
                                                                


                                                            </tbody>
                                                        </table>
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                             Table Headers 
                                                            <tbody>
                                                                <tr>
                                                            <b>5.</b> Chọn menu <b>Phân tích</b> để tạo báo cáo bán hàng, các chức năng chính: <br>
                                                                    <p style="margin-left: 20px;">5.1. Báo cáo chi tiết đơn đặt hàng<br>
                                                                       5.2. Báo cáo bán hàng theo hóa đơn<br>
                                                                       5.3. Báo cáo bán hàng theo sản phẩm<br>
                                                                    </p>
                                                                </tr>
                                                                
                                                                 Table Contents 
                                                                


                                                            </tbody>
                                                        </table>
                                                        
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                             Table Headers 
                                                            <tbody>
                                                                <tr>
                                                            <b>6.</b> Chọn menu <b>Thiết lập</b> để quản lý thông tin quản trị viên, các chức năng chính: <br>
                                                                    <p style="margin-left: 20px;">6.1. Thêm sửa xóa thông tin quản trị viên, người quản lý bán hàng<br>
                                                                       6.2. Theo dõi vết đăng nhập đăng xuất của người quản trị trên hệ thống<br>
                                                                       6.3. Tạo thông báo cho các người dùng khác trên đầu trang web<br>
                                                                    </p>
                                                                </tr>
                                                                
                                                                 Table Contents 
                                                                


                                                            </tbody>
                                                        </table>-->
                                                    </div>

                                                    <table border="0" cellspacing="0" cellpadding="2" width="100%">

                                                    </table>
                                            </td>
                                        </tr>
                                    </tbody>

                                </table>

                            </form>  

                        </div>

                    </td>
                    <td valign="top"><img src="themes/softed/images/showPanelTopRight.gif"></td>
                </tr>
            </tbody>
        </table>
        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>


    </body>
</html>

