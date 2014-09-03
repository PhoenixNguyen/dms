<%-- 
    Document   : take-order
    Created on : Apr 10, 2014, 12:58:52 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Tồn kho - Kiểm kê - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link rel="SHORTCUT ICON" href="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
        <link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { position:absolute; }</style>
        <![endif]-->

        <!-- End -->
        <script>
            var selected = <s:property value="selected"/>;
                    console.log(selected);
                    if (selected){
            var status = false;
                    status = <s:property value="deleteStatus"/>;
                    console.log("status " + status);
                    if (status == "true")
                    alert("Xóa thành công");
                    else
                    alert("Không thể xóa do kiểm kê này đã được sử dụng cho nội dung khác");
            }
        </script>
    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">
        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'inventory'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Hàng tồn &gt; <a class="hdrLink" href="">Kiểm kê</a></td>
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
<!--                                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/btnL3Add.gif" alt="Tạo Kiểm kê..." title="Tạo Kiểm kê..." border="0"></a></td>

                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc'); searchshowhide('searchAcc', 'advSearch'); mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Kiểm kê..." title="Tìm kiếm trong Kiểm kê..." border="0"></a></td>-->

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
<!--                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Kiểm kê" title="Nhập dữ liệu Đặt hàng" border="0"></a></td>  
                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:void(0)" onclick="return selectedRecords('Accounts', 'Marketing')"><img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Đặt hàng" title="Xuất dữ liệu Đặt hàng" border="0"></a></td>
-->

                                                    <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
                                                    <!--                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup'); mergeshowhide('mergeDup'); searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>-->
                                                </tr>
                                            </tbody></table>  
                                    </td>
                                    <td style="width:20px;">&nbsp;</td>
                                    <td class="small">
                                        <!-- All Menu -->
                                        <table border="0" cellspacing="0" cellpadding="5">
                                            <tbody>
                                                <!--                                        <tr>
                                                                                            <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                                                                            <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>
                                                                                        </tr>-->
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
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                    <td class="showPanelBg" valign="top" width="100%" style="padding:10px;">
                        <!-- SIMPLE SEARCH -->
                        <div id="searchAcc" style="display: block;position:relative;">
                            <form name="basicSearch" method="GET" action="search-inventory" id="sub_form">
                                <table width="80%" cellpadding="5" cellspacing="0" class="searchUIBasic small" align="center" border="0">
                                    <tbody>
                                        <tr>
                                            <td class="searchUIName small" nowrap="" align="left">
                                                <span class="moduleName">Tìm kiếm</span><br>
                                                <!-- <img src="themes/images/basicSearchLens.gif" align="absmiddle" alt="Tìm kiếm cơ bản" title="Tìm kiếm cơ bản" border=0>&nbsp;&nbsp;-->
                                            </td>
                                            <%
                                            String str = "";
                                            if(request.getParameter("search_text")== null){
                                                str = "";
                                                session.setAttribute("search_text", str);
                                            }
                                            else{
                                                session.setAttribute("search_text", request.getParameter("search_text"));
                                            }
                                            %>
                                            <td class="small" ><input type="text" class="txtBox" style="width:400px" name="search_text" value="<s:property value="%{#attr.search_text}"/>"></td>

                                            <td class="small" nowrap="" width="40%">
                                                <input name="submit" type="submit" class="crmbutton small create" onclick="
                                                    document.getElementById('sub_form').submit();
                                                    //alert('hêlo');
                                                    " value=" Thực hiện tìm kiếm ">&nbsp;
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="7" align="center" class="small">
                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                    <tbody><tr>
                                                            <td class="searchAlph" id="alpha_1" align="center" 
                                                                onclick=""></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>  

                        <!-- PUBLIC CONTENTS STARTS-->
                        <div id="ListViewContents" class="small" style="width:100%;">
                            <script language="JavaScript" type="text/javascript" src="include/js/ListView.js"></script>
                            <form name="massdelete" method="POST" id="massdelete" onsubmit="VtigerJS_DialogBox.block();">
                                <input name="search_url" id="search_url" type="hidden" value="">
                                <input name="idlist" id="idlist" type="hidden">
                                <input name="change_owner" type="hidden">
                                <input name="change_status" type="hidden">
                                <input name="action" type="hidden">
                                <input name="where_export" type="hidden" value="">
                                <input name="step" type="hidden">
                                <input name="allids" type="hidden" id="allids" value="">
                                <input name="selectedboxes" id="selectedboxes" type="hidden" value="">
                                <input name="allselectedboxes" id="allselectedboxes" type="hidden" value="">
                                <input name="current_page_boxes" id="current_page_boxes" type="hidden" value="201;611;793">
                                <!-- List View Master Holder starts -->
                                <table border="0" cellspacing="1" cellpadding="0" width="100%" class="lvtBg">
                                    <tbody><tr>
                                            <td>

                                                <!-- List View's Buttons and Filters ends -->

                                                <div>
                                                    <table border="0" cellspacing="1" cellpadding="3" width="100%" class="lvt small">
                                                        <!-- Table Headers -->
                                                        <tbody>
                                                            <tr>
                                                                <td class="lvtCol">Stt</td>
                                                                <td class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; SalesOrder & quot; , & quot; parenttab = Inventory & amp; foldername = Default & amp; order_by = salesorder_no & amp; start = 1 & amp; sorder = ASC & amp; viewname = 26 & quot; );" class="listFormHeaderLinks">Mã Kiểm kê</a></td>
                                                                <td class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; SalesOrder & quot; , & quot; parenttab = Inventory & amp; foldername = Default & amp; order_by = subject & amp; start = 1 & amp; sorder = ASC & amp; viewname = 26 & quot; );" class="listFormHeaderLinks">Ngày Kiểm kê</a></td>
<!--                                                                <td class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; SalesOrder & quot; , & quot; parenttab = Inventory & amp; foldername = Default & amp; order_by = subject & amp; start = 1 & amp; sorder = ASC & amp; viewname = 26 & quot; );" class="listFormHeaderLinks">Ngày giao hàng</a></td>-->
                                                                <td class="lvtCol">Tên Khách hàng</td>
                                                                <td class="lvtCol">Mã Khách hàng</td>
                                                                <td class="lvtCol">Địa chỉ khách hàng</td>
                                                                <td class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; SalesOrder & quot; , & quot; parenttab = Inventory & amp; foldername = Default & amp; order_by = total & amp; start = 1 & amp; sorder = ASC & amp; viewname = 26 & quot; );" class="listFormHeaderLinks">Tổng giá trị</a></td>
                                                                <td class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; SalesOrder & quot; , & quot; parenttab = Inventory & amp; foldername = Default & amp; order_by = smownerid & amp; start = 1 & amp; sorder = ASC & amp; viewname = 26 & quot; );" class="listFormHeaderLinks">Gán cho</a></td>
                                                                <td class="lvtCol">Hoạt động</td>
                                                            </tr>
                                                            <!-- Table Contents -->
                                                            <s:iterator value="inventoryManagerList" status="index" var="order">
                                                                <tr bgcolor="white" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" id="row_201" class="lvtColData">
                                                                    <td onmouseover=""><s:property value="%{#index.index +1}"/> <span type="vtlib_metainfo" vtrecordid="201" vtfieldname="subject" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><a href="inventory-manager-detail?id_inv=<s:property value="id"/>" title="Accounts"><s:property value="id" /></a><span type="vtlib_metainfo" vtrecordid="201" vtfieldname="salesorder_no" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><s:property value="takeOrderDate"/> <span type="vtlib_metainfo" vtrecordid="201" vtfieldname="subject" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><s:property value="customerName"/><span type="vtlib_metainfo" vtrecordid="201" vtfieldname="quote_id" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><a href="" title="Accounts"><s:property value="customerID"/> </a><span type="vtlib_metainfo" vtrecordid="201" vtfieldname="hdnGrandTotal" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><s:property value="customerAddress"/> <span type="vtlib_metainfo" vtrecordid="201" vtfieldname="assigned_user_id" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover="">₫<s:property value="getText('{0,number,#,##0.00}',{afterPrivate})"/> <span type="vtlib_metainfo" vtrecordid="201" vtfieldname="hdnGrandTotal" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><a href="" title="Accounts"><s:property value="creater"/> </a><span type="vtlib_metainfo" vtrecordid="201" vtfieldname="assigned_user_id" vtmodule="SalesOrder" style="display:none;"></span></td>
                                                                    <td onmouseover=""><a href="inventory-manager-edit?id_inv=<s:property value="id"/>">Sửa</a>  | <a href='javascript:confirmdelete("delete-inventory-manager?id_inv=<s:property value="id"/>")'>Xóa</a></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </tbody>
                                                    </table>
                                                </div>


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

        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>


    </body>
</html>
