<%-- 
    Document   : customer-detail
    Created on : Apr 10, 2014, 4:33:05 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Nhân viên - Chi tiết - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            function showHideStatus(sId, anchorImgId, sImagePath)
            {
                oObj = eval(document.getElementById(sId));
                if (oObj.style.display == 'block')
                {
                    oObj.style.display = 'none';
                    eval(document.getElementById(anchorImgId)).src = 'themes/images/inactivate.gif';
                    eval(document.getElementById(anchorImgId)).alt = 'Display';
                    eval(document.getElementById(anchorImgId)).title = 'Display';
                }
                else
                {
                    oObj.style.display = 'block';
                    eval(document.getElementById(anchorImgId)).src = 'themes/images/activate.gif';
                    eval(document.getElementById(anchorImgId)).alt = 'Hide';
                    eval(document.getElementById(anchorImgId)).title = 'Hide';
                }
            }
        </script>

        <script>
            var selected = <s:property value="selected"/>;
            console.log(selected);

            if (selected) {
                var status = false;
                status = <s:property value="deleteStatus"/>;
                console.log("status " + status);
                if (status == "true")
                    alert("Xóa thành công");
                else
                    alert("Không thể xóa do nhân viên này đã được sử dụng cho nội dung khác");
            }
        </script>
    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">
        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'staff'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Nhân viên &gt; <a class="hdrLink" href="">Chi tiết</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-staff"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Nhân viên..." title="Tạo mới Nhân viên..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-staff"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Nhân viên" title="Nhập dữ liệu Nhân viên" border="0"></a></td>  
<!--                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc');
                                                                            searchshowhide('searchAcc', 'advSearch');
                                                                            mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Khách hàng..." title="Tìm kiếm trong Khách hàng..." border="0"></a></td>-->

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
                                                    
<!--                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:alert('Chức năng chưa được xây dựng!')" onclick="return selectedRecords('Accounts', 'Marketing')"<img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Nhân viên" title="Xuất dữ liệu Nhân viên" border="0"></a></td>-->


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
                                                <tr>
                                                    <!--                                            <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                                                                                <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>-->
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


        <!-- Contents -->
        <table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" valign="top" width="100%">
                        <!-- PUBLIC CONTENTS STARTS-->
                        <div class="small" style="padding:10px">

                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
                                <tbody>
                                    <tr>
                                        <td>		
                                            <span class="dvHeaderText">[ <s:property value="staff.id"/> ] </font><s:property value="staff.name"/> - Thông tin Nhân viên </span>&nbsp;&nbsp;&nbsp;<span class="small">Cập nhật 31 ngày trước (01 Tháng 03 2014)</span>&nbsp;<span id="vtbusy_info" style="display:none;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span><span id="vtbusy_info" style="visibility:hidden;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span>
                                        </td>
                                    </tr>
                                </tbody></table>			 
                            <br>

                            <!-- Account details tabs -->
                            <table border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                                <tbody>
                                    <tr>
                                        <td>
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                                <tbody>
                                                    <tr>
                                                        <td class="dvtTabCache" style="width:10px" nowrap="">&nbsp;</td>

                                                        <td class="dvtSelectedCell" align="center" nowrap="">Nhân viên Thông tin</td>	
                                                        <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCache" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javascript:window.location.href = 'edit-staff?id_st=<s:property value="staff.stt"/>';
                                                                    return false;
                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;
                                                            <!--                                                    <input title="Sao chép [Alt+U]" accesskey="U" class="crmbutton small create" onclick="" type="button" name="Duplicate" value="Sao chép">&nbsp;-->
                                                            <script>
                                                                var str = "delete-staff?id_st=<s:property value="staff.stt"/>";
                                                            </script>

                                                            <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" 
                                                                   onclick="
                                                                           //alert('Works!');


                                                                           confirmdelete('' + str);

                                                                   " type="button" name="Delete" value="Xóa">&nbsp;
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left">                
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace" style="border-bottom:0;">
                                                <tbody>
                                                    <tr valign="top">

                                                        <td align="left">
                                                            <!-- content cache -->


                                                            <div id="editlistprice" style="position:absolute;width:300px;"></div>
                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                                <tbody>
                                                                    <tr valign="top">
                                                                        <td style="padding:5px">

                                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                <tbody><tr>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td align="right">
                                                                                        </td>
                                                                                    </tr>

                                                                                    <!-- This is added to display the existing comments -->

                                                                                    <tr>
                                                                                        <td colspan="4" class="dvInnerHeader">
                                                                                            <div style="float:left;font-weight:bold;">
                                                                                                <div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinKháchhàng','aidThôngtinKháchhàng','themes/softed/images/');">
                                                                                                        <img id="aidThôngtinKháchhàng" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide">
                                                                                                    </a>
                                                                                                </div><b>&nbsp;Thông tin Nhân viên</b>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            <!--ThôngtinKháchhàng-->
                                                                            <div style="width:auto;display:block;" id="tblThôngtinKháchhàng">
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody>
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%">
                                                                                                <input type="hidden" id="hdtxt_IsAdmin" value="1">Tên Nhân viên
                                                                                            </td>
                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên Khách hàng" onmouseover="hndMouseOver(2, 'Tên Khách hàng');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Tên Khách hàng"><s:property value="staff.name"/></span>
                                                                                            </td>
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã Nhân viên</td>
                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="staff.id"/></td>
                                                                                        </tr>	
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mật khẩu</td>
                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--WebSite-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Website" onmouseover="hndMouseOver(17, 'Website');" onmouseout="fnhide('crmspanid');">&nbsp;
                                                                                                <span id="dtlview_Website"><a href="" target="_blank"></a>******</span>
                                                                                            </td>
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Quyền hạn</td>

                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Điện thoại" onmouseover="hndMouseOver(11, 'Điện thoại');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Điện thoại">
                                                                                                    <s:if test="staff.permission == 1" >
                                                                                                        Quản lý
                                                                                                    </s:if>
                                                                                                    <s:if test="staff.permission == 2" >
                                                                                                        Nhân viên bán hàng
                                                                                                    </s:if>
                                                                                                    <s:if test="staff.permission == 3" >
                                                                                                        Nhân viên cập nhật vị trí
                                                                                                    </s:if>

                                                                                                </span>
                                                                                            </td>
                                                                                        </tr>	
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Điện thoại</td>

                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Mã chứng khoán" onmouseover="hndMouseOver(1, 'Mã chứng khoán');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Mã chứng khoán"><s:property value="staff.phone"/></span>
                                                                                            </td>
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Chức vụ</td>
                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Fax" onmouseover="hndMouseOver(11, 'Fax');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Fax"><s:property value="staff.job"/></span>

                                                                                            </td>
                                                                                        </tr>	
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Người quản lý</td>

                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Mã chứng khoán" onmouseover="hndMouseOver(1, 'Mã chứng khoán');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Mã chứng khoán"><s:property value="staff.manager"/></span>
                                                                                            </td>
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Trạng thái</td>
                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Fax" onmouseover="hndMouseOver(11, 'Fax');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Fax">
                                                                                                    <s:if test="staff.status == true">
                                                                                                        Đang hoạt động
                                                                                                    </s:if>
                                                                                                    <s:else>
                                                                                                        Ngừng hoạt động
                                                                                                    </s:else>
                                                                                                    
                                                                                                </span>

                                                                                            </td>
                                                                                        </tr>	
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Ngày gia nhập</td>

                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextBox-->
                                                                                            <s:date format="dd-MM-yyyy" id="dateconverted" name="staff.date"/>
                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Mã chứng khoán" onmouseover="hndMouseOver(1, 'Mã chứng khoán');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                &nbsp;&nbsp;<span id="dtlview_Mã chứng khoán"><s:property value="%{dateconverted}"/></span>
                                                                                            </td>
                                                                                            <!--                                                                                    <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Trạng thái</td>
                                                                                                                                                                                 This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                                TextBox
                                                                                                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Fax" onmouseover="hndMouseOver(11, 'Fax');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                                                                                                    &nbsp;&nbsp;<span id="dtlview_Fax"><s:property value="staff."/></span>
                                                                                            
                                                                                                                                                                                </td>-->
                                                                                        </tr>	

                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </td>
                                                                    </tr>

                                                                    <!--DIA CHI-->                         
                                                                    <tr>
                                                                        <td style="padding:5px">

                                                                            <!-- Detailed View Code starts here-->
                                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                <tbody><tr>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td align="right">
<!--                                                                                            <input name="mapbutton" value="Bản đồ định vị" class="crmbutton small create" type="button" onclick="fnvshobj(this, 'locateMap');" onmouseout="fninvsh('locateMap');" title="Bản đồ định vị">-->
                                                                                        </td>
                                                                                    </tr>

                                                                                    <!-- This is added to display the existing comments -->

                                                                                    <tr><td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinđịachỉ','aidThôngtinđịachỉ','themes/softed/images/');"><img id="aidThôngtinđịachỉ" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Display" title="Display"></a></div><b>&nbsp;Thông tin địa chỉ</b></div></td>
                                                                                    </tr>
                                                                                </tbody></table>
                                                                            <div style="width:auto;display:block;" id="tblThôngtinđịachỉ">
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody>
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Địa chỉ </td>

                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <!--TextArea/Street-->
                                                                                            <td width="100%" class="dvtCellInfo" align="left" id="mouseArea_Địa chỉ thanh toán" onmouseover="hndMouseOver(21, 'Địa chỉ thanh toán');" onmouseout="fnhide('crmspanid');">&nbsp;
                                                                                                <span id="dtlview_Địa chỉ thanh toán"><s:property value="staff.adress"/></span>

                                                                                            </td>
                                                                                            <!--                                                                                    <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Địa chỉ vận chuyển</td>
                                                                                            
                                                                                                                                                                                 This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                                TextArea/Street
                                                                                                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Địa chỉ vận chuyển" onmouseover="hndMouseOver(21, 'Địa chỉ vận chuyển');" onmouseout="fnhide('crmspanid');">&nbsp;
                                                                                                                                                                                    <span id="dtlview_Địa chỉ vận chuyển"><s:property value="customer.diaChi"/></span>
                                                                                            
                                                                                                                                                                                </td>-->
                                                                                        </tr>	
                                                                                        <!--                                                                                <tr style="height:25px">
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Hộp thư thanh toán</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Hộp thư thanh toán" onmouseover="hndMouseOver(1, 'Hộp thư thanh toán');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Hộp thư thanh toán"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Hộp thư vận chuyển</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Hộp thư vận chuyển" onmouseover="hndMouseOver(1, 'Hộp thư vận chuyển');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Hộp thư vận chuyển"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>	
                                                                                                                                                                        <tr style="height:25px">
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thanh toán tại thành phố</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Thanh toán tại thành phố" onmouseover="hndMouseOver(1, 'Thanh toán tại thành phố');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Thanh toán tại thành phố"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Vận chuyển tới thành phố</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Vận chuyển tới thành phố" onmouseover="hndMouseOver(1, 'Vận chuyển tới thành phố');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Vận chuyển tới thành phố"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>	
                                                                                                                                                                        <tr style="height:25px">
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thanh toán tại Tỉnh/Bang</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Thanh toán tại Tỉnh//Bang" onmouseover="hndMouseOver(1, 'Thanh toán tại Tỉnh/Bang');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Thanh toán tại Tỉnh//Bang"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Vận chuyển tới Tỉnh/Bang</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Vận chuyển tới Tỉnh//Bang" onmouseover="hndMouseOver(1, 'Vận chuyển tới Tỉnh/Bang');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Vận chuyển tới Tỉnh//Bang"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>	
                                                                                                                                                                        <tr style="height:25px">
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã vùng thanh toán</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Mã vùng thanh toán" onmouseover="hndMouseOver(1, 'Mã vùng thanh toán');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Mã vùng thanh toán"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã vùng vận chuyển</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Mã vùng vận chuyển" onmouseover="hndMouseOver(1, 'Mã vùng vận chuyển');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Mã vùng vận chuyển"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>	
                                                                                                                                                                        <tr style="height:25px">
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thanh toán tại quốc gia</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Thanh toán tại quốc gia" onmouseover="hndMouseOver(1, 'Thanh toán tại quốc gia');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                        
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Thanh toán tại quốc gia"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Vận chuyển tới quốc gia</td>
                                                                                        
                                                                                                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                            TextBox
                                                                                                                                                                            <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Vận chuyển tới quốc gia" onmouseover="hndMouseOver(1, 'Vận chuyển tới quốc gia');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                                                                                                &nbsp;&nbsp;<span id="dtlview_Vận chuyển tới quốc gia"></span>
                                                                                        
                                                                                                                                                                            </td>
                                                                                                                                                                        </tr>	-->

                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </td>
                                                                    </tr>

                                                                    <!--Mo ta-->
                                                                    <tr>
<!--                                                                        <td style="padding:5px">

                                                                             Detailed View Code starts here
                                                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                <tbody><tr>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td>&nbsp;</td>
                                                                                        <td align="right">
                                                                                        </td>
                                                                                    </tr>

                                                                                     This is added to display the existing comments 

                                                                                    <tr>
                                                                                        <td colspan="4" class="dvInnerHeader">
                                                                                            <div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinmôtả','aidThôngtinmôtả','themes/softed/images/');">
                                                                                                        <img id="aidThôngtinmôtả" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin mô tả</b>
                                                                                            </div>
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody>
                                                                            </table>
                                                                            <div style="width:auto;display:block;" id="tblThôngtinmôtả">
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody>
                                                                                        <tr style="height:25px">
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mô tả</td>

                                                                                             This file is used to display the fields based on the ui type in detailview 
                                                                                            TextArea/Description
                                                                                             we will empty the value of ticket and faq comment 
                                                                                              
                                                                                             
                                                                                            <td width="100%" colspan="3" class="dvtCellInfo" align="left" id="mouseArea_Mô tả" onmouseover="hndMouseOver(19, 'Mô tả');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Mô tả">
                                                                                                    <s:property value="customer.ghiChu"/> <a href="" target="_blank"></a>
                                                                                                </span>

                                                                                            </td>
                                                                                        </tr>	

                                                                                    </tbody>
                                                                                </table>
                                                                            </div>
                                                                        </td>-->
                                                                    </tr>
                                                                    <tr>
                                                                        <td style="padding:5px">

                                                                        </td>
                                                                    </tr>
                                                                    <!-- LBL -->
                                                                <script>
                                                                    function OpenWindow(url)
                                                                    {
                                                                        openPopUp('xAttachFile', this, url, 'attachfileWin', 380, 375, 'menubar=no,toolbar=no,location=no,status=no,resizable=no');
                                                                    }
                                                                </script>
                                                </tbody>
                                            </table>

                                        </td>

                                        <!--Hoạt động-->
                                        <!--                                <td width="22%" valign="top" style="border-left:1px dashed #cccccc;padding:13px">
                                        
                                                                             right side relevant info 
                                                                             Action links for Event & Todo START-by Minnie 
                                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                                <tbody><tr><td>&nbsp;</td></tr>				
                                                                                    <tr><td align="left" class="genHeaderSmall">Hoạt động</td></tr>
                                        
                                                                                    <tr>
                                                                                        <td align="left" style="padding-left:10px;"> 
                                                                                            <input type="hidden" name="pri_email" value="khanhtt@vjl.com.vn">
                                                                                            <input type="hidden" name="sec_email" value="khanh@s-i-e.jp">
                                                                                            <a href="javascript:void(0);" class="webMnu" onclick="if (LTrim('khanhtt@vjl.com.vn') != '' || LTrim('khanh@s-i-e.jp') != '') {
                                                                                                        fnvshobj(this, 'sendmail_cont');
                                                                                                        sendmail('Accounts', 137)
                                                                                                    } else {
                                                                                                        OpenCompose('', 'create')
                                                                                                    }"><img src="themes/images/sendmail.png" hspace="5" align="absmiddle" border="0"></a>&nbsp;
                                                                                            <a href="javascript:void(0);" class="webMnu" onclick="if (LTrim('khanhtt@vjl.com.vn') != '' || LTrim('khanh@s-i-e.jp') != '') {
                                                                                                        fnvshobj(this, 'sendmail_cont');
                                                                                                        sendmail('Accounts', 137)
                                                                                                    } else {
                                                                                                        OpenCompose('', 'create')
                                                                                                    }">Gửi Mail</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" style="padding-left:10px;"> 
                                                                                            <a href="" class="webMnu"><img src="themes/images/AddEvent.gif" hspace="5" align="absmiddle" border="0"></a>
                                                                                            <a href="" class="webMnu">Thêm Sự kiện</a>
                                                                                        </td>
                                                                                    </tr>
                                        
                                                                                    <tr>
                                                                                        <td align="left" style="padding-left:10px;">
                                                                                            <a href="" class="webMnu"><img src="themes/images/AddToDo.gif" hspace="5" align="absmiddle" border="0"></a>
                                                                                            <a href="" class="webMnu">Thêm Tác vụ</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                     Start: Actions for Documents Module 
                                        
                                                                                     End: Actions for Documents Module 	
                                                                                </tbody></table>
                                        
                                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                                <tbody><tr>
                                                                                        <td align="left" style="padding-left:10px;">
                                                                                            <a class="webMnu" href=""><img hspace="5" align="absmiddle" border="0" src="themes/images/bookMark.gif"></a>
                                                                                            <a class="webMnu" href="">Thêm ghi chú</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" style="padding-left:10px;">
                                                                                            <a class="webMnu" href="">Hiển thị cấp bậc khách hàng</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" style="padding-left:10px;">
                                                                                            <a class="webMnu" href="">Send SMS</a>
                                                                                        </td>
                                                                                    </tr>
                                                                                </tbody></table>
                                        
                                                                             Action links END 
                                        
                                                                             Mail Merge
                                                                            <br>
                                                                            <form action="index.php" method="post" name="TemplateMerge" id="form">
                                                                                <input type="hidden" name="module" value="Accounts">
                                                                                <input type="hidden" name="parenttab" value="Sales">
                                                                                <input type="hidden" name="record" value="137">
                                                                                <input type="hidden" name="action">
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="rightMailMerge">
                                                                                    <tbody><tr>
                                                                                            <td class="rightMailMergeHeader"><b>Chọn mẫu để ghép nối Mail	</b></td>
                                                                                        </tr>
                                                                                        <tr style="height:25px">
                                                                                            <td class="rightMailMergeContent">
                                                                                                <a href="">Tạo mẫu trộn thư </a>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                            </form>
                                        
                                                                        </td>-->
                                    </tr>
                                </tbody>
                            </table>


                            <!-- PUBLIC CONTENTS STOPS-->
                    </td>
                </tr>
                <tr>
                    <td>			
                        <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                            <tbody><tr>
                                    <td class="dvtTabCacheBottom" style="width:10px" nowrap="">&nbsp;</td>

                                    <td class="dvtSelectedCellBottom" align="center" nowrap="">Nhân viên Thông tin</td>	
                                    <td class="dvtTabCacheBottom" style="width:10px">&nbsp;</td>
                                    <td class="dvtTabCacheBottom" align="right" style="width:100%">
                                        &nbsp;
                                        <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                javascript:window.location.href = 'edit-staff?id_st=<s:property value="staff.stt"/>';
                                                return false;
                                               " type="submit" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;
                                        <!--                                <input title="Sao chép [Alt+U]" accesskey="U" class="crmbutton small create" onclick="DetailView.return_module.value = 'Accounts';
                                                                                DetailView.return_action.value = 'DetailView';
                                                                                DetailView.isDuplicate.value = 'true';
                                                                                DetailView.module.value = 'Accounts';
                                                                                submitFormForAction('DetailView', 'EditView');" type="submit" name="Duplicate" value="Sao chép">&nbsp;-->
                                        <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" onclick="
                                                confirmdelete('' + str);
//                                    DetailView.return_module.value = 'Accounts';
//                                        DetailView.return_action.value = 'index';
//                                        var confirmMsg = 'Xóa Khách hàng đồng nghĩa xóa các Cơ hội và Báo giá liên quan. Bạn chắc chắn muốn xóa?';
//                                        submitFormForActionWithConfirmation('DetailView', 'Delete', confirmMsg);
                                               " type="button" name="Delete" value="Xóa">&nbsp;


                                    </td>
                                </tr>
                            </tbody></table>
                    </td>
                </tr>
            </tbody>
        </table>

        <br><br><br>
        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>



    </body>
</html>
