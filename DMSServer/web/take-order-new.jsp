<%-- 
    Document   : take-order-new
    Created on : Apr 10, 2014, 1:03:22 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Tồn kho - Đặt hàng - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link rel="SHORTCUT ICON" href="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
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

        <img src="themes/softed/images/layerPopupBg.gif" style="display: none;">

        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="hdrNameBg">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/vtiger-crm.gif" alt="HOSCO-MANAGEMENT" title="HOSCO-MANAGEMENT" border="0"></td>
                    <td width="100%" align="center">
            <marquee id="rss" direction="left" scrolldelay="10" scrollamount="3" behavior="scroll" class="marStyle" onmouseover="javascript:stop();" onmouseout="javascript:start();">&nbsp;admin :  Thông báo: Ngày 27/10. Bắt đầu làm đặc tả phần mềm cho MEDIC   </marquee>

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
                        <td style="padding-left:10px;padding-right:10px" class="small" nowrap=""> <a href="logout">Thoát</a> (admin)</td>
                    </tr>
                </tbody></table>
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
                            <td class="tabSeperator"><img src="themes/images/spacer.gif" width="2px" height="28px"></td>    
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'My Home Page_sub');" onmouseout="fnHideDrop('My Home Page_sub');" align="center" nowrap=""><a href="">Trang chủ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Tools_sub');" onmouseout="fnHideDrop('Tools_sub');" align="center" nowrap=""><a href="">Nhân viên</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabSelected" onmouseover="fnDropDown(this, 'Marketing_sub');" onmouseout="fnHideDrop('Marketing_sub');" align="center" nowrap=""><a href="">Khách hàng</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Sales_sub');" onmouseout="fnHideDrop('Sales_sub');" align="center" nowrap=""><a href="">Bán hàng</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Support_sub');" onmouseout="fnHideDrop('Support_sub');" align="center" nowrap=""><a href="">Hỗ trợ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Analytics_sub');" onmouseout="fnHideDrop('Analytics_sub');" align="center" nowrap=""><a href="">Phân tích</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Inventory_sub');" onmouseout="fnHideDrop('Inventory_sub');" align="center" nowrap=""><a href="">Tồn kho</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>

                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
                            <td class="tabUnSelected" onmouseover="fnDropDown(this, 'Settings_sub');" onmouseout="fnHideDrop('Settings_sub');" align="center" nowrap=""><a href="">Thiết lập</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
                            <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>

                        </tr>

                    </tbody>
                </table>
            </td>
            <td align="right" style="padding-right:10px" nowrap="">
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
</td>

</tr>
</tbody>
</table>

<!-- - level 2 tabs starts-->
<table border="0" cellspacing="0" cellpadding="2" width="100%" class="level2Bg">
    <tbody><tr>
            <td>
                <table border="0" cellspacing="0" cellpadding="0">
                    <tbody><tr>

                            <td class="level2UnSelTab" nowrap=""> <a href="">Chiến dịch</a> </td>
                            <td class="level2SelTab" nowrap=""><a href="">Khách hàng</a></td>
                            <td class="level2UnSelTab" nowrap=""> <a href="">Liên hệ</a> </td>
                            <td class="level2UnSelTab" nowrap=""> <a href="">Webmails</a> </td>
                            <td class="level2UnSelTab" nowrap=""> <a href="">Đầu mối</a> </td>
                            <td class="level2UnSelTab" nowrap=""> <a href="">Lịch</a> </td>
                            <td class="level2UnSelTab" nowrap=""> <a href="home.jsp?module=Documents&amp;action=index&amp;parenttab=">Tài liệu</a> </td>
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
            <tr><td><a href="/DMS" class="drop_down">Trang chủ</a></td></tr>

        </tbody>
    </table>
</div>
<div class="drop_mnu" id="Marketing_sub" onmouseout="fnHideDrop('Marketing_sub')" onmouseover="fnShowDrop('Marketing_sub')" style="left: 143px; top: 75px; display: none;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr><td><a href="" class="drop_down">Chiến dịch</a></td></tr>
            <tr><td><a href="customer-list" class="drop_down">Khách hàng</a></td></tr>
            <tr><td><a href="map/showMap?page=0" class="drop_down">Vị trí</a></td></tr>
        </tbody>
    </table>
</div>
<div class="drop_mnu" id="Sales_sub" onmouseout="fnHideDrop('Sales_sub')" onmouseover="fnShowDrop('Sales_sub')" style="left: 244px; top: 75px; display: none;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr><td><a href="" class="drop_down">Đầu mối</a></td></tr>
            <tr><td><a href="take-order" class="drop_down">Đặt hàng</a></td></tr>
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
            <tr><td><a href="" class="drop_down">Báo cáo</a></td></tr>
            <tr><td><a href="" class="drop_down">Biểu đồ</a></td></tr>
        </tbody>
    </table>
</div>
<div class="drop_mnu" id="Inventory_sub" onmouseout="fnHideDrop('Inventory_sub')" onmouseover="fnShowDrop('Inventory_sub')" style="left: 488px; top: 75px; display: none;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr><td><a href="product-list" class="drop_down">Sản phẩm</a></td></tr>
            <tr><td><a href="provider-list" class="drop_down">Nhà cung cấp</a></td></tr>
            <tr><td><a href="" class="drop_down">Bảng giá</a></td></tr>
            <tr><td><a href="" class="drop_down">Nhập hàng</a></td></tr>
            <tr><td><a href="" class="drop_down">Đặt hàng</a></td></tr>
            <tr><td><a href="" class="drop_down">Báo giá</a></td></tr>

        </tbody>
    </table>
</div>
<div class="drop_mnu" id="Tools_sub" onmouseout="fnHideDrop('Tools_sub')" onmouseover="fnShowDrop('Tools_sub')" style="left: 567px; top: 75px; display: none;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody>
            <tr><td><a href="staff-list" class="drop_down">Nhân viên</a></td></tr>
            <tr><td><a href="map/showSchedule?page=0" class="drop_down">Kế hoạch dự kiến</a></td></tr>
            <tr><td><a href="map/staff-roads" class="drop_down">Hành trình</a></td></tr>

        </tbody>
    </table>
</div>
<div class="drop_mnu" id="Settings_sub" onmouseout="fnHideDrop('Settings_sub')" onmouseover="fnShowDrop('Settings_sub')" style="left: 647px; top: 75px; display: none;">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tbody><tr>
                <td><a href="" class="drop_down">Thiết lập</a></td>
            </tr>
            <tr>
                <td><a href="" class="drop_down">Quản lý phân hệ</a></td>
            </tr>
        </tbody>
    </table>
</div>

<!-- END Drop Down Menu in the Main Tab -->

<div id="status" style="position:absolute;display:none;left:850px;top:95px;height:27px;white-space:nowrap;"><img src="themes/softed/images/status.gif"></div>

<!--END MENU-->

<!-- TOOLS -->
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
    <tbody>
        <tr><td style="height:2px"></td></tr>
        <tr>

            <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Marketing &gt; <a class="hdrLink" href="">Khách hàng</a></td>
            <td width="100%" nowrap="">

                <table border="0" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="sep1" style="width:1px;"></td>
                            <td class="small">
                                <!-- Add and Search -->
                                <table border="0" cellspacing="0" cellpadding="0">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <table border="0" cellspacing="0" cellpadding="5">
                                                    <tbody>
                                                        <tr>
                                                            <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/btnL3Add.gif" alt="Tạo Khách hàng..." title="Tạo Khách hàng..." border="0"></a></td>

                                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc'); searchshowhide('searchAcc', 'advSearch'); mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Khách hàng..." title="Tìm kiếm trong Khách hàng..." border="0"></a></td>

                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                            <td style="width:20px;">&nbsp;</td>
                            <td class="small">

                            </td>
                            <td style="width:20px;">&nbsp;</td>
                            <td class="small">
                                <!-- Import / Export -->
                                <table border="0" cellspacing="0" cellpadding="5">
                                    <tbody>
                                        <tr>
                                            <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Khách hàng" title="Nhập dữ liệu Khách hàng" border="0"></a></td>  
                                            <td style="padding-right:10px"><a name="export_link" href="javascript:void(0)" onclick="return selectedRecords('Accounts', 'Marketing')"><img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Khách hàng" title="Xuất dữ liệu Khách hàng" border="0"></a></td>


                                            <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup'); mergeshowhide('mergeDup'); searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>
                                        </tr>
                                    </tbody></table>  
                            </td>
                            <td style="width:20px;">&nbsp;</td>
                            <td class="small">
                                <!-- All Menu -->
                                <table border="0" cellspacing="0" cellpadding="5">
                                    <tbody>
                                        <tr>
                                            <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                            <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </td>
        </tr>
        <tr><td style="height:2px"></td></tr>
    </tbody>
</table>                                

<!-- END TOOLS -->

<!--TAKEORDER-->
<table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
    <tbody><tr>
            <td valign="top">
                <img src="themes/softed/images/showPanelTopLeft.gif">
            </td>

            <td class="showPanelBg" valign="top" width="100%">

                <!-- (id="frmEditView") content added to form tag and new hidden field added,  -->
                <form id="frmEditView" name="EditView" method="POST" action="index.php" onsubmit="settotalnoofrows(); calcTotal(); VtigerJS_DialogBox.block();">
                    <input type="hidden" name="hidImagePath" id="hidImagePath" value="themes/softed/images/">
                    <!-- End of code added -->

                    <input type="hidden" name="convertmode">


                    <input type="hidden" name="pagenumber" value="">
                    <input type="hidden" name="module" value="SalesOrder">
                    <input type="hidden" name="record" value="">
                    <input type="hidden" name="mode" value="">
                    <input type="hidden" name="action">
                    <input type="hidden" name="parenttab" value="Inventory">
                    <input type="hidden" name="return_module" value="SalesOrder">
                    <input type="hidden" name="return_id" value="">
                    <input type="hidden" name="return_action" value="DetailView">
                    <input type="hidden" name="return_viewname" value="">	     <div class="small" style="padding:20px">

                        <span class="lvtHeaderText">Tạo mới Đặt hàng</span> <br>

                        <hr noshade="" size="1">
                        <br> 



                        <table border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                            <tbody><tr>
                                    <td>
                                        <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                            <tbody><tr>
                                                    <td class="dvtTabCache" style="width:10px" nowrap="">&nbsp;</td>


                                                    <td width="75" style="width:15%" align="center" nowrap="" class="dvtSelectedCell" id="bi" onclick="fnLoadValues('bi', 'mi', 'basicTab', 'moreTab', 'inventory', 'SalesOrder')"><b>Cơ bản Thông tin</b></td>
                                                    <td class="dvtUnSelectedCell" style="width: 100px;" align="center" nowrap="" id="mi" onclick="fnLoadValues('mi', 'bi', 'moreTab', 'basicTab', 'inventory', 'SalesOrder')"><b>Thêm Thông tin </b></td>
                                                    <td class="dvtTabCache" style="width:65%" nowrap="">&nbsp;</td>
                                                </tr><tr>
                                                </tr></tbody></table>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" align="left">

                                        <!-- Basic and More Information Tab Opened -->
                                        <div id="basicTab">

                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace">
                                                <tbody><tr>
                                                        <!--this td is to display the entity details -->
                                                        <td align="left">
                                                            <!-- content cache -->

                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                                <tbody><tr>
                                                                        <td id="autocom"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="padding:10px">
                                                                            <!-- General details -->
                                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                <tbody><tr>
                                                                                        <td colspan="4" style="padding:5px">
                                                                                            <div align="center">
                                                                                                <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save'; return validateInventory('SalesOrder')" type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                                                <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td colspan="4" class="detailedViewHeader">
                                                                                            <b>Thông tin Đặt hàng</b>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <!-- Here we should include the uitype handlings-->


                                                                                    <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>Tiêu đề 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input type="text" name="subject" tabindex="" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                        </td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Tên Cơ hội 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input name="potential_name" readonly="" type="text" style="border:1px solid #bababa;" value=""><input name="potential_id" type="hidden" value="">&nbsp;<img tabindex="" src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="selectPotential()" align="absmiddle" style="cursor:hand;cursor:pointer">&nbsp;<input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.potential_id.value = ''; this.form.potential_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                        </td>

                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Khách hàng thứ </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="customerno" id="customerno" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <!-- Non Editable field, only configured value will be loaded -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mã Đặt hàng </td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input readonly="" type="text" tabindex="" name="salesorder_no" id="salesorder_no" value="AUTO GEN ON SAVE" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Tên Báo giá 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input name="quote_name" readonly="" type="text" style="border:1px solid #bababa;" value=""><input name="quote_id" type="hidden" value="">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="selectQuote()" align="absmiddle" style="cursor:hand;cursor:pointer">&nbsp;<input type="image" tabindex="" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.quote_id.value = ''; this.form.quote_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                        </td>





                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Nhập hàng </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="vtiger_purchaseorder" id="vtiger_purchaseorder" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Tên Liên hệ 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input name="contact_name" readonly="" type="text" style="border:1px solid #bababa;" value=""><input name="contact_id" type="hidden" value="">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="selectContact( & quot; false & quot; , & quot; general & quot; , document.EditView)" align="absmiddle" style="cursor:hand;cursor:pointer">&nbsp;<input type="image" tabindex="" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.contact_id.value = ''; this.form.contact_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                        </td>





                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Ngày liên quan 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">

                                                                                            <input name="duedate" tabindex="" id="jscal_field_duedate" type="text" style="border:1px solid #bababa;" size="11" maxlength="10" value="09-04-2014">
                                                                                            <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_duedate">




                                                                                            <br><font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>

                                                                                            <script type="text/javascript" id="massedit_calendar_duedate">
                                                                                                Calendar.setup ({
                                                                                                inputField : "jscal_field_duedate", ifFormat : "%d-%m-%Y", showsTime : false, button : "jscal_trigger_duedate", singleClick : true, step : 1
                                                                                                })
                                                                                            </script>


                                                                                        </td>

                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>
                                                                                            Công ty vận chuyển 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <select name="carrier" tabindex="" class="small">
                                                                                                <option value="FedEx">
                                                                                                    FedEx
                                                                                                </option>
                                                                                                <option value="UPS">
                                                                                                    UPS
                                                                                                </option>
                                                                                                <option value="USPS">
                                                                                                    USPS
                                                                                                </option>
                                                                                                <option value="DHL">
                                                                                                    DHL
                                                                                                </option>
                                                                                                <option value="BlueDart">
                                                                                                    BlueDart
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Lý do chưa thực hiện </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="pending" id="pending" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>
                                                                                            Tình trạng 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <select name="sostatus" tabindex="" class="small">
                                                                                                <option value="Created">
                                                                                                    Đã tạo
                                                                                                </option>
                                                                                                <option value="Approved">
                                                                                                    Đã chấp nhận
                                                                                                </option>
                                                                                                <option value="Delivered">
                                                                                                    Đã giao
                                                                                                </option>
                                                                                                <option value="Cancelled">
                                                                                                    Hủy bỏ
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Tiền hoa hồng </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="salescommission" id="salescommission" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Thuế hàng hóa </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="exciseduty" id="exciseduty" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>Tên Khách hàng 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input readonly="" name="account_name" id="single_accountid" type="text" value=""><input name="account_id" type="hidden" value="">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="return window.open( & quot; index.php?module = Accounts & amp; action = Popup & amp; popuptype = specific_account_address & amp; form = TasksEditView & amp; form_submit = false & amp; fromlink = & quot; , & quot; test & quot; , & quot; width = 640, height = 602, resizable = 0, scrollbars = 0 & quot; );" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                            <input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.account_id.value = ''; this.form.account_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                        </td>

                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>Gán cho 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">


                                                                                            <input type="radio" tabindex="" name="assigntype" checked="" value="U" onclick="toggleAssignType(this.value)">&nbsp;Người dùng

                                                                                            <input type="radio" name="assigntype" value="T" onclick="toggleAssignType(this.value)">&nbsp;Nhóm

                                                                                            <span id="assign_user" style="display:block">
                                                                                                <select name="assigned_user_id" class="small">
                                                                                                    <option value="1" selected="">admin</option>
                                                                                                    <option value="2730">binhnt</option>
                                                                                                    <option value="6">cancv</option>
                                                                                                    <option value="10">cuongtq</option>
                                                                                                    <option value="2729">ducpl</option>
                                                                                                    <option value="18">hungnv</option>
                                                                                                    <option value="20">huylm</option>
                                                                                                    <option value="2731">karofi</option>
                                                                                                    <option value="26">quandm</option>
                                                                                                    <option value="25">quyennt</option>
                                                                                                    <option value="21">sangch</option>
                                                                                                    <option value="24">thamnh</option>
                                                                                                    <option value="2725">ThaoDT</option>
                                                                                                    <option value="2726">ThaoNTT</option>
                                                                                                    <option value="5">thoant</option>
                                                                                                    <option value="2727">thomlt</option>
                                                                                                    <option value="2721">trangnt</option>
                                                                                                    <option value="28">trattp</option>
                                                                                                    <option value="2728">TuyetVT</option>
                                                                                                    <option value="15">vuidt</option>
                                                                                                    <option value="27">yennt</option>
                                                                                                </select>
                                                                                            </span>

                                                                                            <span id="assign_team" style="display:none">
                                                                                                <select name="assigned_group_id" class="small">';
                                                                                                    <option value="2">Đội sales</option>
                                                                                                    <option value="4">Nhóm hỗ trợ</option>
                                                                                                    <option value="7">Nhóm kỹ thuật</option>
                                                                                                    <option value="3">Nhóm Marketing</option>
                                                                                                </select>
                                                                                            </span>
                                                                                        </td>




                                                                                    </tr>

                                                                                    <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                                    <tr>
                                                                                        <td colspan="4" class="detailedViewHeader">
                                                                                            <b>Thông tin định kỳ</b>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <!-- Here we should include the uitype handlings-->


                                                                                    <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Bật chức năng định kỳ 			</td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <input name="enable_recurring" tabindex="" type="checkbox">
                                                                                        </td>




                                                                                        <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>
                                                                                            Mức độ thường xuyên 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <select name="recurring_frequency" tabindex="" class="small">
                                                                                                <option value="--None--">
                                                                                                    --Chưa chọn--
                                                                                                </option>
                                                                                                <option value="Daily">
                                                                                                    Hàng ngày
                                                                                                </option>
                                                                                                <option value="Weekly">
                                                                                                    Hàng tuần
                                                                                                </option>
                                                                                                <option value="Monthly">
                                                                                                    Hàng tháng
                                                                                                </option>
                                                                                                <option value="Quarterly">
                                                                                                    Hàng qúy
                                                                                                </option>
                                                                                                <option value="Yearly">
                                                                                                    Hàng năm
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Thời gian bắt đầu 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">

                                                                                            <input name="start_period" tabindex="" id="jscal_field_start_period" type="text" style="border:1px solid #bababa;" size="11" maxlength="10" value="09-04-2014">
                                                                                            <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_start_period">




                                                                                            <br><font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>

                                                                                            <script type="text/javascript" id="massedit_calendar_start_period">
                                                                                                Calendar.setup ({
                                                                                                inputField : "jscal_field_start_period", ifFormat : "%d-%m-%Y", showsTime : false, button : "jscal_trigger_start_period", singleClick : true, step : 1
                                                                                                })
                                                                                            </script>


                                                                                        </td>





                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>Thời gian kết thúc 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">

                                                                                            <input name="end_period" tabindex="" id="jscal_field_end_period" type="text" style="border:1px solid #bababa;" size="11" maxlength="10" value="09-04-2014">
                                                                                            <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_end_period">




                                                                                            <br><font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>

                                                                                            <script type="text/javascript" id="massedit_calendar_end_period">
                                                                                                Calendar.setup ({
                                                                                                inputField : "jscal_field_end_period", ifFormat : "%d-%m-%Y", showsTime : false, button : "jscal_trigger_end_period", singleClick : true, step : 1
                                                                                                })
                                                                                            </script>


                                                                                        </td>

                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red"></font>
                                                                                            Thời gian thanh toán 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <select name="payment_duration" tabindex="" class="small">
                                                                                                <option value="Net 30 days">
                                                                                                    Trong vòng 30 ngày
                                                                                                </option>
                                                                                                <option value="Net 45 days">
                                                                                                    Trong vòng 45 ngày
                                                                                                </option>
                                                                                                <option value="Net 60 days">
                                                                                                    Trong vòng 60 ngày
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>




                                                                                        <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>
                                                                                            Trạng thái hóa đơn 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <select name="invoicestatus" tabindex="" class="small">
                                                                                                <option value="AutoCreated">
                                                                                                    Tạo tự động
                                                                                                </option>
                                                                                                <option value="Created">
                                                                                                    Đã tạo
                                                                                                </option>
                                                                                                <option value="Approved">
                                                                                                    Đã chấp nhận
                                                                                                </option>
                                                                                                <option value="Sent">
                                                                                                    Đã gửi
                                                                                                </option>
                                                                                                <option value="Credit Invoice">
                                                                                                    Hóa đơn tín dụng
                                                                                                </option>
                                                                                                <option value="Paid">
                                                                                                    Đã thanh toán
                                                                                                </option>
                                                                                            </select>
                                                                                        </td>
                                                                                    </tr>

                                                                                    <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                                    <tr>
                                                                                        <td colspan="2" class="detailedViewHeader">
                                                                                            <b>Thông tin địa chỉ</b></td>
                                                                                        <td class="detailedViewHeader">
                                                                                            <input name="cpy" onclick="return copyAddressLeft(EditView)" type="radio"><b>Sao chép địa chỉ vận chuyển</b></td>
                                                                                        <td class="detailedViewHeader">
                                                                                            <input name="cpy" onclick="return copyAddressRight(EditView)" type="radio"><b>Sao chép địa chỉ thanh toán</b></td>

                                                                                    </tr>

                                                                                    <!-- Here we should include the uitype handlings-->


                                                                                    <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>
                                                                                            Địa chỉ thanh toán 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <textarea value="" name="bill_street" tabindex="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'" rows="2"></textarea>
                                                                                        </td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right">
                                                                                            <font color="red">*</font>
                                                                                            Địa chỉ vận chuyển 			</td>
                                                                                        <td width="30%" align="left" class="dvtCellInfo">
                                                                                            <textarea value="" name="ship_street" tabindex="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'" rows="2"></textarea>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Hộp thư thanh toán </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="bill_pobox" id="bill_pobox" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Hộp thư vận chuyển </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="ship_pobox" id="ship_pobox" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Thanh toán tại thành phố </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="bill_city" id="bill_city" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Vận chuyển tới thành phố </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="ship_city" id="ship_city" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Thanh toán tại Tỉnh/Bang </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="bill_state" id="bill_state" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Vận chuyển tới Tỉnh/Bang </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="ship_state" id="ship_state" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>
                                                                                    <tr style="height:25px">




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mã vùng thanh toán </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="bill_code" id="bill_code" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>




                                                                                        <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mã vùng vận chuyển </td>

                                                                                        <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="ship_code" id="ship_code" value="" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                    </tr>

                                                                                    <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                                    <!-- This if is added to restrict display in more tab-->
                                                                                    <!-- Added to display the product details -->
                                                                                    <!-- This if is added when we want to populate product details from the related entity  for ex. populate product details in new SO page when select Quote -->

                                                                                <script type="text/javascript" src="include/js/Inventory.js"></script>
                                                                                <script type="text/javascript" src="modules/Services/Services.js"></script><script type="text/javascript" src="modules/Products/multifile.js"></script><script type="text/javascript" src="include/js/Merge.js"></script>
                                                                                <script>
                                                                                    if(!e)
                                                                                    window.captureEvents(Event.MOUSEMOVE);

                                                                                    //  window.onmousemove= displayCoords;
                                                                                    //  window.onclick = fnRevert;

                                                                                    function displayCoords(currObj,obj,mode,curr_row) 
                                                                                    {
                                                                                    if(mode != 'discount_final' && mode != 'sh_tax_div_title' && mode != 'group_tax_div_title')
                                                                                    {
                                                                                    var curr_productid = document.getElementById("hdnProductId"+curr_row).value;
                                                                                    if(curr_productid == '')
                                                                                    {
                                                                                    alert("Vui lòng chọn một mặt hàng");
                                                                                    return false;
                                                                                    }

                                                                                    var curr_quantity = document.getElementById("qty"+curr_row).value;
                                                                                    if(curr_quantity == '')
                                                                                    {
                                                                                    alert("Hãy điền số lượng");
                                                                                    return false;
                                                                                    }
                                                                                    }

                                                                                    //Set the Header value for Discount
                                                                                    if(mode == 'discount')
                                                                                    {
                                                                                    document.getElementById("discount_div_title"+curr_row).innerHTML = '<b>Thiết lập Khấu trừ cho  '+document.getElementById("productTotal"+curr_row).innerHTML+'</b>';
                                                                                    }
                                                                                    else if(mode == 'tax')
                                                                                    {
                                                                                    document.getElementById("tax_div_title"+curr_row).innerHTML = "<b>Thiết lập Thuế cho "+document.getElementById("totalAfterDiscount"+curr_row).innerHTML+'</b>';
                                                                                    }
                                                                                    else if(mode == 'discount_final')
                                                                                    {
                                                                                    document.getElementById("discount_div_title_final").innerHTML = '<b>Thiết lập Khấu trừ cho '+document.getElementById("netTotal").innerHTML+'</b>';
                                                                                    }
                                                                                    else if(mode == 'sh_tax_div_title')
                                                                                    {
                                                                                    document.getElementById("sh_tax_div_title").innerHTML = '<b>Thiết lập Thuế Giao nhận cho  '+document.getElementById("shipping_handling_charge").value+'</b>';
                                                                                    }
                                                                                    else if(mode == 'group_tax_div_title')
                                                                                    {
                                                                                    var net_total_after_discount = eval(document.getElementById("netTotal").innerHTML)-eval(document.getElementById("discountTotal_final").innerHTML);
                                                                                    document.getElementById("group_tax_div_title").innerHTML = '<b>Thiết lập Nhóm Thuế cho  '+net_total_after_discount+'</b>';
                                                                                    }

                                                                                    fnvshobj(currObj,'tax_container');
                                                                                    if(document.all)
                                                                                    {
                                                                                    var divleft = document.getElementById("tax_container").style.left;
                                                                                    var divabsleft = divleft.substring(0,divleft.length-2);
                                                                                    document.getElementById(obj).style.left = eval(divabsleft) - 120;

                                                                                    var divtop = document.getElementById("tax_container").style.top;
                                                                                    var divabstop =  divtop.substring(0,divtop.length-2);
                                                                                    document.getElementById(obj).style.top = eval(divabstop) - 200;
                                                                                    }else
                                                                                    {
                                                                                    document.getElementById(obj).style.left =  document.getElementById("tax_container").left;
                                                                                    document.getElementById(obj).style.top = document.getElementById("tax_container").top;
                                                                                    }
                                                                                    document.getElementById(obj).style.display = "block";

                                                                                    }

                                                                                    function doNothing(){
                                                                                    }

                                                                                    function fnHidePopDiv(obj){
                                                                                    document.getElementById(obj).style.display = 'none';
                                                                                    }
                                                                                </script>

                                                                                <!-- Added this file to display and hanld the Product Details in Inventory module  -->

                                                                                <tr>
                                                                                    <td colspan="4" align="left">



                                                                                        <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0" class="crmTable" id="proTab">
                                                                                            <tbody><tr>
                                                                                                    <td colspan="3" class="dvInnerHeader">
                                                                                                        <b>Chi tiết mặt hàng</b>
                                                                                                    </td>

                                                                                                    <td class="dvInnerHeader" align="center" colspan="2">
                                                                                                        <input type="hidden" value="1" id="prev_selected_currency_id">
                                                                                                        <b>Tiền tệ</b>&nbsp;&nbsp;
                                                                                                        <select class="small" id="inventory_currency" name="inventory_currency" onchange="updatePrices();">
                                                                                                            <option value="1" selected="">Vietnam, Dong (₫)</option>
                                                                                                        </select>
                                                                                                    </td>

                                                                                                    <td class="dvInnerHeader" align="center" colspan="2">
                                                                                                        <b>Loại thuế</b>&nbsp;&nbsp;
                                                                                                        <select id="taxtype" name="taxtype" onchange="decideTaxDiv(); calcTotal();">
                                                                                                            <option value="individual" selected="">Khác biệt</option>
                                                                                                            <option value="group">Nhóm</option>
                                                                                                        </select>
                                                                                                    </td>
                                                                                                </tr>


                                                                                                <!-- Header for the Product Details -->
                                                                                                <tr valign="top">
                                                                                                    <td width="5%" valign="top" class="lvtCol" align="right"><b>Công cụ</b></td>
                                                                                                    <td width="40%" class="lvtCol"><font color="red">*</font><b>Tên mặt hàng</b></td>
                                                                                                    <td width="10%" class="lvtCol"><b>Tồn kho</b></td>
                                                                                                    <td width="10%" class="lvtCol"><b>Số lượng</b></td>
                                                                                                    <td width="10%" class="lvtCol" align="right"><b>Đơn giá</b></td>
                                                                                                    <td width="12%" nowrap="" class="lvtCol" align="right"><b>Tổng số</b></td>
                                                                                                    <td width="13%" valign="top" class="lvtCol" align="right"><b>Thành tiền</b></td>
                                                                                                </tr>






                                                                                                <!-- Following code is added for form the first row. Based on these we should form additional rows using script -->

                                                                                                <!-- Product Details First row - Starts -->
                                                                                                <tr valign="top" id="row1">

                                                                                                    <!-- column 1 - delete link - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop">&nbsp;
                                                                                                        <input type="hidden" id="deleted1" name="deleted1" value="0">
                                                                                                    </td>
                                                                                                    <!-- column 1 - delete link - ends -->

                                                                                                    <!-- column 2 - Product Name - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop">
                                                                                                        <table width="100%" border="0" cellspacing="0" cellpadding="1">
                                                                                                            <tbody><tr>
                                                                                                                    <td class="small">
                                                                                                                        <input type="text" id="productName1" name="productName1" class="small" style="width:70%" value="" readonly="">
                                                                                                                        <input type="hidden" id="hdnProductId1" name="hdnProductId1" value="">
                                                                                                                        <input type="hidden" id="lineItemType1" name="lineItemType1" value="Products">
                                                                                                                        &nbsp;<img id="searchIcon1" title="Products" src="themes/images/products.gif" style="cursor: pointer;" align="absmiddle" onclick="productPickList(this, 'SalesOrder', 1)">
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td class="small">
                                                                                                                        <input type="hidden" value="" id="subproduct_ids1" name="subproduct_ids1">
                                                                                                                        <span id="subprod_names1" name="subprod_names1" style="color:#C0C0C0;font-style:italic;"> </span>
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr valign="bottom">
                                                                                                                    <td class="small" id="setComment">
                                                                                                                        <textarea id="comment1" name="comment1" class="small" style="width:70%;height:40px"></textarea>
                                                                                                                        <img src="themes/images/clear_field.gif" onclick="$('comment1').value = ''" ;="" style="cursor:pointer;">
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                            </tbody></table>
                                                                                                    </td>
                                                                                                    <!-- column 2 - Product Name - ends -->

                                                                                                    <!-- column 3 - Quantity in Stock - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop"><span id="qtyInStock1"></span></td>
                                                                                                    <!-- column 3 - Quantity in Stock - ends -->


                                                                                                    <!-- column 4 - Quantity - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop">
                                                                                                        <input id="qty1" name="qty1" type="text" class="small " style="width:50px" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="settotalnoofrows(); calcTotal(); loadTaxes_Ajax(1); setDiscount(this, '1'); calcTotal();" value=""><br><span id="stock_alert1"></span>
                                                                                                    </td>
                                                                                                    <!-- column 4 - Quantity - ends -->


                                                                                                    <!-- column 5 - List Price with Discount, Total After Discount and Tax as table - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop" align="right">
                                                                                                        <table width="100%" cellpadding="0" cellspacing="0">
                                                                                                            <tbody><tr>
                                                                                                                    <td align="right">
                                                                                                                        <input id="listPrice1" name="listPrice1" value="" type="text" class="small " style="width:70px" onblur="calcTotal(); setDiscount(this, '1'); callTaxCalc(1); calcTotal();">&nbsp;<img src="themes/images/pricebook.gif" onclick="priceBookPickList(this, 1)">
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td align="right" style="padding:5px;" nowrap="">
                                                                                                                        (-)&nbsp;<b><a href="javascript:doNothing();" onclick="displayCoords(this, 'discount_div1', 'discount', '1')">Chiết khấu</a> : </b>
                                                                                                                        <div class="discountUI" id="discount_div1">
                                                                                                                            <input type="hidden" id="discount_type1" name="discount_type1" value="">
                                                                                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0" class="small">
                                                                                                                                <tbody><tr>
                                                                                                                                        <td id="discount_div_title1" nowrap="" align="left"></td>
                                                                                                                                        <td align="right"><img src="themes/images/close.gif" border="0" onclick="fnHidePopDiv('discount_div1')" style="cursor:pointer;"></td>
                                                                                                                                    </tr>
                                                                                                                                    <tr>
                                                                                                                                        <td align="left" class="lineOnTop"><input type="radio" name="discount1" checked="" onclick="setDiscount(this, 1); callTaxCalc(1); calcTotal();">&nbsp; Khấu trừ ít nhất</td>
                                                                                                                                        <td class="lineOnTop">&nbsp;</td>
                                                                                                                                    </tr>
                                                                                                                                    <tr>
                                                                                                                                        <td align="left"><input type="radio" name="discount1" onclick="setDiscount(this, 1); callTaxCalc(1); calcTotal();">&nbsp; % trong giá bán</td>
                                                                                                                                        <td align="right"><input type="text" class="small" size="5" id="discount_percentage1" name="discount_percentage1" value="0" style="visibility:hidden" onblur="setDiscount(this, 1); callTaxCalc(1); calcTotal();">&nbsp;%</td>
                                                                                                                                    </tr>
                                                                                                                                    <tr>
                                                                                                                                        <td align="left" nowrap=""><input type="radio" name="discount1" onclick="setDiscount(this, 1); callTaxCalc(1); calcTotal();">&nbsp;Quản lý giảm giá</td>
                                                                                                                                        <td align="right"><input type="text" id="discount_amount1" name="discount_amount1" size="5" value="0" style="visibility:hidden" onblur="setDiscount(this, 1); callTaxCalc(1); calcTotal();"></td>
                                                                                                                                    </tr>
                                                                                                                                </tbody></table>
                                                                                                                        </div>
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td align="right" style="padding:5px;" nowrap="">
                                                                                                                        <b>Giá sau khi chiết khấu :</b>
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr id="individual_tax_row1" class="TaxShow">
                                                                                                                    <td align="right" style="padding:5px;" nowrap="">
                                                                                                                        (+)&nbsp;<b><a href="javascript:doNothing();" onclick="displayCoords(this, 'tax_div1', 'tax', '1')">Thuế </a> : </b>
                                                                                                                        <div class="discountUI" id="tax_div1">
                                                                                                                        </div>
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                            </tbody></table> 
                                                                                                    </td>
                                                                                                    <!-- column 5 - List Price with Discount, Total After Discount and Tax as table - ends -->


                                                                                                    <!-- column 6 - Product Total - starts -->
                                                                                                    <td class="crmTableRow small lineOnTop" align="right">
                                                                                                        <table width="100%" cellpadding="5" cellspacing="0">
                                                                                                            <tbody><tr>
                                                                                                                    <td id="productTotal1" align="right">&nbsp;</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td id="discountTotal1" align="right">0.00</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td id="totalAfterDiscount1" align="right">&nbsp;</td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td id="taxTotal1" align="right">0.00</td>
                                                                                                                </tr>
                                                                                                            </tbody></table>
                                                                                                    </td>
                                                                                                    <!-- column 6 - Product Total - ends -->


                                                                                                    <!-- column 7 - Net Price - starts -->
                                                                                                    <td valign="bottom" class="crmTableRow small lineOnTop" align="right"><span id="netPrice1"><b>&nbsp;</b></span></td>
                                                                                                    <!-- column 7 - Net Price - ends -->

                                                                                                </tr>
                                                                                                <!-- Product Details First row - Ends -->
                                                                                            </tbody></table>
                                                                                        <!-- Upto this has been added for form the first row. Based on these above we should form additional rows using script -->










                                                                                        <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0" class="crmTable">
                                                                                            <!-- Add Product Button -->
                                                                                            <tbody><tr>
                                                                                                    <td colspan="3">
                                                                                                        <input type="button" name="Button" class="crmbutton small create" value="Thêm sản phẩm" onclick="fnAddProductRow('SalesOrder', 'themes/softed/images/');">
                                                                                                        &nbsp;&nbsp;
                                                                                                        <input type="button" name="Button" class="crmbutton small create" value="Thêm dịch vụ" onclick="fnAddServiceRow('SalesOrder', 'themes/softed/images/');">
                                                                                                    </td>
                                                                                                </tr>




                                                                                                <!-- Product Details Final Total Discount, Tax and Shipping&Hanling  - Starts -->
                                                                                                <tr valign="top">
                                                                                                    <td width="88%" colspan="2" class="crmTableRow small lineOnTop" align="right"><b>Cộng tiền hàng</b></td>
                                                                                                    <td width="12%" id="netTotal" class="crmTableRow small lineOnTop" align="right">0.00</td>
                                                                                                </tr>

                                                                                                <tr valign="top">
                                                                                                    <td class="crmTableRow small lineOnTop" width="60%" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow small lineOnTop" align="right">
                                                                                                        (-)&nbsp;<b><a href="javascript:doNothing();" onclick="displayCoords(this, 'discount_div_final', 'discount_final', '1')">Chiết khấu</a>
                                                                                                            <!-- Popup Discount DIV -->
                                                                                                            <div class="discountUI" id="discount_div_final">
                                                                                                                <input type="hidden" id="discount_type_final" name="discount_type_final" value="">
                                                                                                                <table width="100%" border="0" cellpadding="5" cellspacing="0" class="small">
                                                                                                                    <tbody><tr>
                                                                                                                            <td id="discount_div_title_final" nowrap="" align="left"></td>
                                                                                                                            <td align="right"><img src="themes/images/close.gif" border="0" onclick="fnHidePopDiv('discount_div_final')" style="cursor:pointer;"></td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td align="left" class="lineOnTop"><input type="radio" name="discount_final" checked="" onclick="setDiscount(this, '_final'); calcGroupTax(); calcTotal();">&nbsp; Khấu trừ ít nhất</td>
                                                                                                                            <td class="lineOnTop">&nbsp;</td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td align="left"><input type="radio" name="discount_final" onclick="setDiscount(this, '_final'); calcGroupTax(); calcTotal();">&nbsp; % trong giá bán</td>
                                                                                                                            <td align="right"><input type="text" class="small" size="5" id="discount_percentage_final" name="discount_percentage_final" value="0" style="visibility:hidden" onblur="setDiscount(this, '_final'); calcGroupTax(); calcTotal();">&nbsp;%</td>
                                                                                                                        </tr>
                                                                                                                        <tr>
                                                                                                                            <td align="left" nowrap=""><input type="radio" name="discount_final" onclick="setDiscount(this, '_final'); calcGroupTax(); calcTotal();">&nbsp;Quản lý giảm giá</td>
                                                                                                                            <td align="right"><input type="text" id="discount_amount_final" name="discount_amount_final" size="5" value="0" style="visibility:hidden" onblur="setDiscount(this, '_final'); calcGroupTax(); calcTotal();"></td>
                                                                                                                        </tr>
                                                                                                                    </tbody></table>
                                                                                                            </div>
                                                                                                            <!-- End Div -->
                                                                                                        </b></td>
                                                                                                    <td id="discountTotal_final" class="crmTableRow small lineOnTop" align="right">0.00</td>
                                                                                                </tr>


                                                                                                <!-- Group Tax - starts -->
                                                                                                <tr id="group_tax_row" valign="top" class="TaxHide">
                                                                                                    <td class="crmTableRow small lineOnTop" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow small lineOnTop" align="right">
                                                                                                        (+)&nbsp;<b><a href="javascript:doNothing();" onclick="displayCoords(this, 'group_tax_div', 'group_tax_div_title', ''); calcGroupTax();">Thuế</a></b>
                                                                                                        <!-- Pop Div For Group TAX -->
                                                                                                        <div class="discountUI" id="group_tax_div">
                                                                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0" class="small">
                                                                                                                <tbody><tr>
                                                                                                                        <td id="group_tax_div_title" colspan="2" nowrap="" align="left"></td>
                                                                                                                        <td align="right"><img src="themes/images/close.gif" border="0" onclick="fnHidePopDiv('group_tax_div')" style="cursor:pointer;"></td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="5" name="tax1_group_percentage" id="group_tax_percentage1" value="10.000" onblur="calcTotal()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">VAT</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="6" name="tax1_group_amount" id="group_tax_amount1" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="5" name="tax2_group_percentage" id="group_tax_percentage2" value="10.000" onblur="calcTotal()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">Sales</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="6" name="tax2_group_amount" id="group_tax_amount2" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="5" name="tax3_group_percentage" id="group_tax_percentage3" value="12.500" onblur="calcTotal()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">Service</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="6" name="tax3_group_amount" id="group_tax_amount3" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="5" name="tax4_group_percentage" id="group_tax_percentage4" value="0.000" onblur="calcTotal()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">NoVAT</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="6" name="tax4_group_amount" id="group_tax_amount4" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                                <input type="hidden" id="group_tax_count" value="4">

                                                                                                                </tbody></table>

                                                                                                        </div>
                                                                                                        <!-- End Popup Div Group Tax -->

                                                                                                    </td>
                                                                                                    <td id="tax_final" class="crmTableRow small lineOnTop" align="right">0.00</td>
                                                                                                </tr>
                                                                                                <!-- Group Tax - ends -->


                                                                                                <tr valign="top">
                                                                                                    <td class="crmTableRow small" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow small" align="right">
                                                                                                        (+)&nbsp;<b>Chi phí vận chuyển và đóng gói </b>
                                                                                                    </td>
                                                                                                    <td class="crmTableRow small" align="right">
                                                                                                        <input id="shipping_handling_charge" name="shipping_handling_charge" type="text" class="small" style="width:40px" align="right" value="0.00" onblur="calcSHTax();">
                                                                                                    </td>
                                                                                                </tr>

                                                                                                <tr valign="top">
                                                                                                    <td class="crmTableRow small" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow small" align="right">
                                                                                                        (+)&nbsp;<b><a href="javascript:doNothing();" onclick="displayCoords(this, 'shipping_handling_div', 'sh_tax_div_title', ''); calcSHTax();">Thuế vận chuyển và đóng gói </a></b>

                                                                                                        <!-- Pop Div For Shipping and Handlin TAX -->
                                                                                                        <div class="discountUI" id="shipping_handling_div">
                                                                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0" class="small">
                                                                                                                <tbody><tr>
                                                                                                                        <td id="sh_tax_div_title" colspan="2" nowrap="" align="left"></td>
                                                                                                                        <td align="right"><img src="themes/images/close.gif" border="0" onclick="fnHidePopDiv('shipping_handling_div')" style="cursor:pointer;"></td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="3" name="shtax1_sh_percent" id="sh_tax_percentage1" value="4.500" onblur="calcSHTax()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">VAT</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="4" name="shtax1_sh_amount" id="sh_tax_amount1" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="3" name="shtax2_sh_percent" id="sh_tax_percentage2" value="10.000" onblur="calcSHTax()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">Sales</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="4" name="shtax2_sh_amount" id="sh_tax_amount2" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>


                                                                                                                    <tr>
                                                                                                                        <td align="left" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="3" name="shtax3_sh_percent" id="sh_tax_percentage3" value="12.500" onblur="calcSHTax()">&nbsp;%
                                                                                                                        </td>
                                                                                                                        <td align="center" class="lineOnTop">Service</td>
                                                                                                                        <td align="right" class="lineOnTop">
                                                                                                                            <input type="text" class="small" size="4" name="shtax3_sh_amount" id="sh_tax_amount3" style="cursor:pointer;" value="0.00" readonly="">
                                                                                                                        </td>
                                                                                                                    </tr>

                                                                                                                <input type="hidden" id="sh_tax_count" value="3">

                                                                                                                </tbody></table>
                                                                                                        </div>
                                                                                                        <!-- End Popup Div for Shipping and Handling TAX -->

                                                                                                    </td>
                                                                                                    <td id="shipping_handling_tax" class="crmTableRow small" align="right">0.00</td>
                                                                                                </tr>
                                                                                                <tr valign="top">
                                                                                                    <td class="crmTableRow small" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow small" align="right">
                                                                                                        Điều chỉnh thêm bớt
                                                                                                        <select id="adjustmentType" name="adjustmentType" class="small" onchange="calcTotal();">
                                                                                                            <option value="+">Thêm</option>
                                                                                                            <option value="-">Bớt</option>
                                                                                                        </select>
                                                                                                    </td>
                                                                                                    <td class="crmTableRow small" align="right">
                                                                                                        <input id="adjustment" name="adjustment" type="text" class="small" style="width:40px" align="right" value="0.00" onblur="calcTotal();">
                                                                                                    </td>
                                                                                                </tr>
                                                                                                <tr valign="top">
                                                                                                    <td class="crmTableRow big lineOnTop" style="border-right:1px #dadada;">&nbsp;</td>
                                                                                                    <td class="crmTableRow big lineOnTop" align="right"><b>Tổng tiền thanh toán</b></td>
                                                                                                    <td id="grandTotal" name="grandTotal" class="crmTableRow big lineOnTop" align="right">&nbsp;</td>
                                                                                                </tr>
                                                                                            </tbody></table>
                                                                                        <input type="hidden" name="totalProductCount" id="totalProductCount" value="">
                                                                                        <input type="hidden" name="subtotal" id="subtotal" value="">
                                                                                        <input type="hidden" name="total" id="total" value="">




                                                                                    </td>
                                                                                </tr>





                                                                                <tr>
                                                                                    <td colspan="4" style="padding:5px">
                                                                                        <div align="center">
                                                                                            <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save'; return validateInventory('SalesOrder')" type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                                            <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                            <input type="hidden" name="convert_from" value="">
                                                                                            <input type="hidden" name="duplicate_from" value="">
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                </tbody></table>
                                                            <!-- General details - end -->
                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                    </td>
                                </tr>
                            </tbody></table>

                    </div>
                    <!-- Basic and More Information Tab Opened -->
                    <div id="moreTab">

                        <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace">
                            <tbody><tr>
                                    <!--this td is to display the entity details -->
                                    <td align="left">
                                        <!-- content cache -->

                                        <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                            <tbody><tr>
                                                    <td id="autocom"></td>
                                                </tr>
                                                <tr>
                                                    <td style="padding:10px">
                                                        <!-- General details -->
                                                        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                            <tbody><tr>
                                                                    <td colspan="4" style="padding:5px">
                                                                        <div align="center">
                                                                            <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save'; return validateInventory('SalesOrder')" type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                            <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                        </div>
                                                                    </td>
                                                                </tr>

                                                                <tr>
                                                                    <td colspan="4" class="detailedViewHeader">
                                                                        <b>Quyền hạn và trách nhiệm</b>
                                                                    </td>
                                                                </tr>

                                                                <!-- Here we should include the uitype handlings-->


                                                                <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                <tr style="height:25px">




                                                                    <!-- In Add Comment are we should not display anything -->
                                                                    <td width="20%" class="dvtCellLabel" align="right">
                                                                        <font color="red"></font> 
                                                                        Điều khoản 			</td>
                                                                    <td colspan="3">
                                                                        <textarea class="detailedViewTextBox" tabindex="" onfocus="this.className = 'detailedViewTextBoxOn'" name="terms_conditions" onblur="this.className = 'detailedViewTextBox'" cols="90" rows="8"> - Unless otherwise agreed in writing by the supplier all invoices are payable within thirty (30) days of the date of invoice, in the currency of the invoice, drawn on a bank based in India or by such other method as is agreed in advance by the Supplier.

 - All prices are not inclusive of VAT which shall be payable in addition by the Customer at the applicable rate.</textarea>
                                                                    </td>
                                                                </tr>

                                                                <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                <tr>
                                                                    <td colspan="4" class="detailedViewHeader">
                                                                        <b>Thông tin mô tả</b>
                                                                    </td>
                                                                </tr>

                                                                <!-- Here we should include the uitype handlings-->


                                                                <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                <tr style="height:25px">




                                                                    <!-- In Add Comment are we should not display anything -->
                                                                    <td width="20%" class="dvtCellLabel" align="right">
                                                                        <font color="red"></font> 
                                                                        Mô tả 			</td>
                                                                    <td colspan="3">
                                                                        <textarea class="detailedViewTextBox" tabindex="" onfocus="this.className = 'detailedViewTextBoxOn'" name="description" onblur="this.className = 'detailedViewTextBox'" cols="90" rows="8"></textarea>
                                                                    </td>
                                                                </tr>

                                                                <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                <!-- This if is added to restrict display in more tab-->

                                                                <tr>
                                                                    <td colspan="4" style="padding:5px">
                                                                        <div align="center">
                                                                            <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save'; return validateInventory('SalesOrder')" type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                            <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                            <input type="hidden" name="convert_from" value="">
                                                                            <input type="hidden" name="duplicate_from" value="">
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </tbody></table>
                                                        <!-- General details - end -->
                                                    </td>
                                                </tr>
                                            </tbody></table>
                                    </td>
                                </tr>
                            </tbody></table>

                    </div>
            </td>
        </tr>
    </tbody></table>
</div>
</form></td>
<td align="right" valign="top"><img src="themes/softed/images/showPanelTopRight.gif"></td>
</tr>
</tbody></table>


<!-- This div is added to get the left and top values to show the tax details-->
<div id="tax_container" style="display:none; position:absolute; z-index:1px;"></div>

<script>



    var fieldname = new Array('salesorder_no','subject','potential_id','customerno','quote_id','vtiger_purchaseorder','contact_id','duedate','carrier','pending','sostatus','txtAdjustment','salescommission','exciseduty','hdnGrandTotal','hdnSubTotal','hdnTaxType','hdnDiscountPercent','hdnDiscountAmount','hdnS_H_Amount','account_id','assigned_user_id','currency_id','conversion_rate','bill_street','ship_street','bill_city','ship_city','bill_state','ship_state','bill_code','ship_code','bill_country','ship_country','bill_pobox','ship_pobox','description','terms_conditions','enable_recurring','recurring_frequency','start_period','end_period','payment_duration','invoicestatus')

    var fieldlabel = new Array('Mã Đặt hàng','Tiêu đề','Tên Cơ hội','Khách hàng thứ','Tên Báo giá','Nhập hàng','Tên Liên hệ','Ngày liên quan','Công ty vận chuyển','Lý do chưa thực hiện','Tình trạng','Điều chỉnh','Tiền hoa hồng','Thuế hàng hóa','Tổng cộng','Cộng tiền hàng','Kiểu thuế','Phần trăm giảm trừ','Số tiền giảm trừ','S&amp;H Amount','Tên Khách hàng','Gán cho','Currency','Conversion Rate','Địa chỉ thanh toán','Địa chỉ vận chuyển','Thanh toán tại thành phố','Vận chuyển tới thành phố','Thanh toán tại Tỉnh/Bang','Vận chuyển tới Tỉnh/Bang','Mã vùng thanh toán','Mã vùng vận chuyển','Thanh toán tại quốc gia','Vận chuyển tới quốc gia','Hộp thư thanh toán','Hộp thư vận chuyển','Mô tả','Terms &amp; Conditions','Bật chức năng định kỳ','Mức độ thường xuyên','Thời gian bắt đầu','Thời gian kết thúc','Thời gian thanh toán','Trạng thái hóa đơn')

    var fielddatatype = new Array('V~O','V~M','I~O','V~O','I~O','V~O','I~O','D~O','V~O','V~O','V~M','NN~O','N~O','N~O','N~O','N~O','V~O','N~O','N~O','N~O','I~M','V~M','I~O','N~O','V~M','V~M','V~O','V~O','V~O','V~O','V~O','V~O','V~O','V~O','V~O','V~O','V~O','V~O','C~O','V~O','D~O','D~O~OTH~G~start_period~Start Period','V~O','V~M')

    var product_labelarr = {CLEAR_COMMENT:'Xóa hết ý kiến',
    DISCOUNT:'Chiết khấu',
    TOTAL_AFTER_DISCOUNT:'Giá sau khi chiết khấu',
    TAX:'Thuế',
    ZERO_DISCOUNT:'Khấu trừ ít nhất',
    PERCENT_OF_PRICE:'trong giá bán',
    DIRECT_PRICE_REDUCTION:'Quản lý giảm giá'};

</script>

<!-- vtlib customization: Help information assocaited with the fields -->
<!-- END --><!-- stopscrmprint --><style>
    .bggray
    {
        background-color: #dfdfdf;
    }
    .bgwhite
    {
        background-color: #FFFFFF;
    }
    .copy
    {
        font-size:9px;
        font-family: Verdana, Arial, Helvetica, Sans-serif;
    }
</style>
<script language="javascript">
    function LogOut(e)
    {
    var nav4 = window.Event ? true : false;
    var iX,iY;
    if (nav4)
    {
    iX = e.pageX;
    iY = e.pageY;
    }
    else
    {
    iX = event.clientX + document.body.scrollLeft;
    iY = event.clientY + document.body.scrollTop;

    }
    if (iX <= 30 && iY < 0 )
    {
    w=window.open("index.php?action=Logout&module=Users");
    w.close();
    }
    }
    //window.onunload=LogOut
</script>
<script language="JavaScript" type="text/javascript" src="include/js/popup.js"></script><br><br><br><table border="0" cellspacing="0" cellpadding="5" width="100%" class="settingsSelectedUI"><tbody><tr><td class="small" align="left"><span style="color: rgb(153, 153, 153);">HOSCO-CRM</span></td><td class="small" align="right"><span style="color: rgb(153, 153, 153);">© 2014 <a href="http://www.hosgroup.com.vn" target="_blank">hosgroup.com.vn</a></span> </td></tr></tbody></table>		<script>
    var userDateFormat = "dd-mm-yyyy";
    var default_charset = "UTF-8";
</script>
<script type="text/javascript">if(typeof(ActivityReminderCallback) != 'undefined') window.setTimeout(function(){
    ActivityReminderCallback();
    },29000);</script><!--end body panes-->





</body></html>
