<%-- 
    Document   : take-order-detail
    Created on : Apr 10, 2014, 1:09:25 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Hàng tồn - Kiểm kê - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Tồn kho &gt; <a class="hdrLink" href="">Kiểm kê</a></td>
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
<!--                                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới..." title="Tạo mới..." border="0"></a></td>

                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc');
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
<!--                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Đặt hàng" title="Nhập dữ liệu Đặt hàng" border="0"></a></td>  
                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:void(0)" onclick="return selectedRecords('Accounts', 'Marketing')"><img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Đặt hàng" title="Xuất dữ liệu Đặt hàng" border="0"></a></td>-->


                                                    <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
                                                    <!--                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup');
                                                                                                        mergeshowhide('mergeDup');
                                                                                                        searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>-->
                                                </tr>
                                            </tbody></table>  
                                    </td>
                                    <td style="width:20px;">&nbsp;</td>
                                    <!--                            <td class="small">
                                                                     All Menu 
                                                                    <table border="0" cellspacing="0" cellpadding="5">
                                                                        <tbody>
                                                                            <tr>
                                                                                <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                                                                <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </td>-->
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
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" valign="top" width="100%">
                        <!-- PUBLIC CONTENTS STARTS-->
                        <div class="small" style="padding:20px">

                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
                                <tbody><tr>
                                        <td>

                                            <span class="lvtHeaderText"><font color="purple">[ <s:property value="inventoryManager.id" /> ] </font>Hàng tồn -  Thông tin Kiểm kê </span>&nbsp;&nbsp;&nbsp;<span class="small"></span>&nbsp;<span id="vtbusy_info" style="display:none;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span><span id="vtbusy_info" style="visibility:hidden;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span>
                                        </td>
                                    </tr>
                                </tbody></table>
                            <br>

                            <!-- Entity and More information tabs -->
                            <table border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                                <tbody><tr>
                                        <td>						
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                                <tbody><tr>
                                                        <td class="dvtTabCache" style="width:10px" nowrap="">&nbsp;</td>

                                                        <td class="dvtSelectedCell" align="center" nowrap="">Thông tin kiểm kê</td>	
                                                        <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCache" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javacript:window.location.href = 'inventory-manager-edit?id_inv=<s:property value="inventoryManager.getId()"/>'

                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;

                                                            <script>
                                                                var str = "delete-inventory-manager?id_inv=<s:property value="inventoryManager.id"/>";
                                                            </script>
                                                            <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" onclick="
                                                                    confirmdelete('' + str);

                                                                   " type="button" name="Delete" value="Xóa">&nbsp;


                                                        </td>
                                                    </tr>
                                                </tbody></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left">
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace" style="border-bottom:0px;">
                                                <tbody><tr valign="top">

                                                        <td align="left" style="padding:10px;">
                                                            <!-- content cache -->
                                                            <form action="index.php" method="post" name="DetailView" id="form" onsubmit="VtigerJS_DialogBox.block();">
                                                                <input type="hidden" name="parenttab" value="Inventory">

                                                                <!-- Entity informations display - starts -->	
                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                                    <tbody><tr>
                                                                            <td style="padding:10px;border-right:1px dashed #CCCCCC;" width="80%">

                                                                                <!-- The following table is used to display the buttons -->
                                                                                <!-- Button displayed - finished-->


                                                                                <!-- Entity information(blocks) display - start -->
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinĐặthàng','aidThôngtinĐặthàng','themes/softed/images/');"><img id="aidThôngtinĐặthàng" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin Đặt hàng</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinĐặthàng">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">

                                                                                        <tbody><tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Tiêu đề</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tiêu đề" onmouseover="hndMouseOver(2, 'Tiêu đề');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Tiêu đề">Kiểm kê</span>

                                                                                                </td>


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã kiểm kê</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--PotentialPopup-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên Cơ hội">&nbsp;<a href=""><s:property value="inventoryManager.Id"/></a>
                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Ngày kiểm kê</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Khách hàng thứ" onmouseover="hndMouseOver(1, 'Khách hàng thứ');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Khách hàng thứ"><s:property value="inventoryManager.takeOrderDate"/></span>

                                                                                                </td>


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1"></td>

                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="takeOrder.deliveryDate"/></td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Tên Khách hàng</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--QuotePopup-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên Báo giá">&nbsp;
                                                                                                    <a href=""><s:property value="inventoryManager.customerName"/></a>
                                                                                                </td>


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã khách hàng</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Nhập hàng" onmouseover="hndMouseOver(1, 'Nhập hàng');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Nhập hàng"><s:property value="inventoryManager.customerID"/></span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Địa chỉ KH</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--ContactPopup-->
                                                                                                <!-- Ajax edit link not provided for contact - Reports To -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên Liên hệ">&nbsp;
                                                                                                    <a href=""><s:property value="inventoryManager.customerAddress"/> </a>
                                                                                                </td>


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Số điện thoại</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày liên quan" onmouseover="hndMouseOver(5, 'Ngày liên quan');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày liên quan">
                                                                                                        <s:property value="inventoryManager.phoneNumber"/>
                                                                                                    </span>

                                                                                                </td>

                                                                                            </tr>	

                                                                                            <tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Người tạo</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="inventoryManager.creater"/></td>

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Ngày tạo</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="inventoryManager.orderEstablishDate"/></td>

                                                                                            </tr>	

                                                                                            <tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Người sửa</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="inventoryManager.editer"/></td>

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Ngày sửa</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="inventoryManager.orderEditDate"/></td>

                                                                                            </tr>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->
                                                                                
<!--                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinđịachỉ','aidThôngtinđịachỉ','themes/softed/images/');"><img id="aidThôngtinđịachỉ" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin địa chỉ</b></div></td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>-->
                                                                                 
                                                                                
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinmôtả','aidThôngtinmôtả','themes/softed/images/');"><img id="aidThôngtinmôtả" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin mô tả</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinmôtả">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">

                                                                                        <tbody><tr style="height:25px">


                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mô tả</td>


                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextArea/Description-->
                                                                                                <!-- we will empty the value of ticket and faq comment -->
                                                                                                <!--  -->
                                                                                                <!-- -->
                                                                                                <td width="100%" colspan="3" class="dvtCellInfo" align="left" id="mouseArea_Mô tả" onmouseover="hndMouseOver(19, 'Mô tả');" onmouseout="fnhide('crmspanid');">&nbsp;
                                                                                                    <span id="dtlview_Mô tả">
                                                                                                        <s:property value="inventoryManager.note"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                            </tr>	

                                                                                        </tbody></table>
                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->

                                                                                <!-- Entity information(blocks) display - ends -->

                                                                                <br>

                                                                                <!-- Product Details informations -->


                                                                                <table width="100%" border="0" align="center" cellpadding="5" cellspacing="0" class="crmTable" id="proTab">
                                                                                    <tbody><tr valign="top">
                                                                                            <td colspan="2" class="dvInnerHeader"><b>Chi tiết mặt hàng</b></td>
                                                                                            <td class="dvInnerHeader" align="center" colspan="2"><b>Tiền tệ : </b>Vietnam, Dong (₫)
                                                                                            </td>
                                                                                            <td class="dvInnerHeader" align="center" colspan="2">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr valign="top">
                                                                                            <td width="10%" class="lvtCol"><b>Hoạt động</b></td>
                                                                                            <td width="5%" class="lvtCol"><b>Dòng</b></td>
                                                                                            <td width="20%" class="lvtCol"><font color="red">*</font>
                                                                                                <b>Tên mặt hàng</b>
                                                                                            </td>
                                                                                            <td width="10%" class="lvtCol"><b>Mã hàng</b></td>
<!--                                                                                            <td width="10%" class="lvtCol"><b>Khuyến mãi</b></td>-->

                                                                                            <td width="10%" class="lvtCol"><b>Số lượng</b></td>

                                                                                            <td width="10%" class="lvtCol" align="right"><b>Đơn giá</b></td>
                                                                                            <td width="20%" nowrap="" class="lvtCol" align="right"><b>Tổng số</b></td>
                                                                                            <td width="25%" valign="top" class="lvtCol" align="right"><b>Thành tiền</b></td>
                                                                                        </tr>

                                                                                        <s:set  var="sum" value="0"/>
                                                                                        <s:iterator value="inventoryManagerDetailList" status="index">
                                                                                            <tr valign="top">
                                                                                                <td class="crmTableRow small lineOnTop">
                                                                                                    <a href="edit-inventory-manager-detail?id_invdt=<s:property value="serial"/>">Sửa</a>  | <a href='javascript:confirmdelete("delete-inventory-manager-detail?id_invdt=<s:property value="serial"/>&id_inv=<s:property value="takeOrderID"/>")'>Xóa</a>				
                                                                                                    <br>
                                                                                                </td>
                                                                                                <td class="crmTableRow small lineOnTop">
                                                                                                    <s:property value="line"/>&nbsp; 				
                                                                                                    <br>
                                                                                                </td>
                                                                                                <td class="crmTableRow small lineOnTop"><s:property value="productName"/></td>
                                                                                                <td class="crmTableRow small lineOnTop"><s:property value="productID"/></td>
<!--                                                                                                <td class="crmTableRow small lineOnTop"><s:property value="promotionalProductMount"/> sản phẩm</td>-->
                                                                                                <td class="crmTableRow small lineOnTop"><s:property value="number"/></td>

                                                                                                <td class="crmTableRow small lineOnTop" align="right">
                                                                                                    <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                                                        <tbody>
                                                                                                            <tr>
                                                                                                                <td align="right"><s:property value="beforeOrderPrice"/></td>
                                                                                                            </tr>
<!--                                                                                                            <tr>
                                                                                                                <td align="right">(-)&nbsp;
                                                                                                                    <b><a href="javascript:;" onclick="alert('');
                                                                                                                          ">Thuế : </a>
                                                                                                                    </b>
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td align="right" nowrap="">Giá sau thuế : </td>
                                                                                                            </tr>
                                                                                                            <tr>
                                                                                                                <td align="right" nowrap="">(+)&nbsp;<b><a href="javascript:;" onclick="alert('');">Chiết khấu(giảm giá) : </a></b></td>
                                                                                                            </tr>-->
                                                                                                        </tbody>
                                                                                                    </table>
                                                                                                </td>
                                                                                                <td class="crmTableRow small lineOnTop" align="right">
                                                                                                    <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                                                        <tbody><tr><td align="right"><s:property value="getText('{0,number,#,##0.00}',{beforeOrderPrice * number})"/></td></tr>
<!--                                                                                                            <tr><td align="right"><s:property value="tax"/></td></tr>
                                                                                                            <tr><td align="right" nowrap=""><s:property value="getText('{0,number,#,##0.00}',{afterOrderPrice * number})"/></td></tr>
                                                                                                            <tr><td align="right" nowrap=""><s:property value="discount"/></td></tr>		   -->
                                                                                                        </tbody></table>
                                                                                                </td>
                                                                                                <td class="crmTableRow small lineOnTop" valign="bottom" align="right"><s:property value="getText('{0,number,#,##0.00}',{priceTotal})"/></td>
                                                                                                <s:set  var="sum" value="%{#sum + priceTotal}"/>
                                                                                            </tr>
                                                                                        </s:iterator>

                                                                                        <tr valign="top">
                                                                                            <td class="crmTableRow small lineOnTop">
                                                                                                <a href="new-takeorder-detail?id_to_d=<s:property value="serial"/>"></a> 			
                                                                                                <br>
                                                                                            </td>
                                                                                            <td class="crmTableRow small lineOnTop">

                                                                                            </td>
                                                                                            <td class="crmTableRow small lineOnTop"></td>
                                                                                            <td class="crmTableRow small lineOnTop"></td>
                                                                                            <td class="crmTableRow small lineOnTop"></td>
                                                                                            <td class="crmTableRow small lineOnTop"></td>

                                                                                            <td class="crmTableRow small lineOnTop" align="right">

                                                                                            </td>
                                                                                            <td class="crmTableRow small lineOnTop" align="right">

                                                                                            </td>
                                                                                            <td class="crmTableRow small lineOnTop" valign="bottom" align="right"></td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                                <table width="100%" border="0" cellspacing="0" cellpadding="5" class="crmTable">
                                                                                    <tbody>
                                                                                        <tr>
                                                                                            <td width="88%" class="crmTableRow small" align="right"><b>Tổng giá trị</b>
                                                                                            </td>
                                                                                            <td width="12%" class="crmTableRow small" align="right"><b><s:property value="getText('{0,number,#,##0.00}',{#sum})"/></b></td>
                                                                                        </tr>
<!--                                                                                        <tr>
                                                                                            <td align="right" class="crmTableRow small lineOnTop">(-)&nbsp;<b><a href="javascript:;" onclick="alert('Giảm giá = <s:property value="takeOrder.discount"/> %');">Chiết khấu (%)</a></b></td>
                                                                                            <td align="right" class="crmTableRow small lineOnTop"><s:property value="takeOrder.discount"/></td>
                                                                                        </tr>
                                                                                        

                                                                                        <tr>
                                                                                            <td align="right" class="crmTableRow small lineOnTop"><b>Tổng tiền thanh toán</b></td>
                                                                                            <td align="right" class="crmTableRow small lineOnTop"><s:property value="getText('{0,number,#,##0.00}',{takeOrder.afterPrivate})"/></td>
                                                                                        </tr>-->
                                                                                    </tbody>
                                                                                </table>

                                                                            </td>
                                                                            <!-- The following table is used to display the buttons -->
                                                                        </tr>
                                                                    </tbody>
                                                                </table>

                                                                <!-- Inventory Actions - ends -->	
                                                            </form>
                                                        </td>
                                                        <td width="22%" valign="top" style="padding:10px;">
                                                            <!-- right side InventoryActions -->

                                                            <!-- Avoid this actions display for PriceBook module-->


                                                            <!-- Added this file to display the Inventory Actions based on the Inventory Modules -->
                                                            <br>
                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                <tbody><tr>
                                                                        <td>&nbsp;</td>
                                                                    </tr>

                                                                    <!-- This if condition is added to avoid display Tools heading because now there is no options in Tools. -->
                                                                    <tr>
                                                                        <!--                                                <td align="left" class="genHeaderSmall">Hoạt động</td>-->
                                                                    </tr>



                                                                    <!-- Module based actions starts -->
                                                                    <!-- SO Actions starts -->
                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:10px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGenerateInvoice.gif" hspace="5" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Tạo Hóa đơn</a> 
                                                                                                                    </td>
                                                                                                                </tr>-->
                                                                    <!--
                                                                    <tr>
                                                                         <td align="left" style="padding-left:10px;">
                                                                                 <img src="themes/images/pointer.gif" hspace="5" align="absmiddle"/>
                                                                                 <a href="#" class="webMnu">List Linked Quotes</a> 
                                                                         </td>
                                                                    </tr>
                                                                    <tr>
                                                                         <td align="left" style="padding-left:10px;">
                                                                                 <img src="themes/images/pointer.gif" hspace="5" align="absmiddle"/>
                                                                                 <a href="#" class="webMnu">List Linked Invoices</a> 
                                                                         </td>
                                                                    </tr>
                                                                    -->
                                                                    <!-- SO Actions ends -->


                                                                    <!-- Module based actions ends -->



                                                                    <tr><td>

                                                                        </td></tr>
                                                                    <!-- Action links END -->



                                                                    <!-- Following condition is added to avoid the Tools section in Products and Vendors because we are not providing the Print and Email Now links throughout all the modules. when we provide these links we will remove this if condition -->

                                                                    <!--                                            <tr>
                                                                                                                    <td align="left">
                                                                                                                        <span class="genHeaderSmall">Công cụ</span><br> 
                                                                                                                    </td>
                                                                                                                </tr>-->




                                                                    <!-- To display the Export To PDF link for PO, SO, Quotes and Invoice - starts -->


                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:10px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGeneratePDF.gif" hspace="5" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Xuất ra Tập tin PDF</a>
                                                                                                                    </td>
                                                                                                                </tr>-->

                                                                    <!-- Added to give link to  send Invoice PDF through mail -->
                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:10px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/PDFMail.gif" hspace="5" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Gửi thư với PDF</a> 
                                                                                                                    </td>
                                                                                                                </tr>-->
                                                                    <!-- To display the Export To PDF link for PO, SO, Quotes and Invoice - ends -->



                                                                    <!-- The following links are common to all the inventory modules -->
                                                                    <!--   <tr>
                                                                            <td align="left" style="padding-left:10px;">
                                                                                    <img src="themes/images/pointer.gif" hspace="5" align="absmiddle"/>
                                                                                    <a href="#" class="webMnu">Print</a> 
                                                                            </td>
                                                                       </tr>
                                                                       <tr>
                                                                            <td align="left" style="padding-left:10px;">
                                                                                    <img src="themes/images/pointer.gif" hspace="5" align="absmiddle"/>
                                                                                    <a href="#" class="webMnu">Email Now </a> 
                                                                            </td>
                                                                       </tr>
                                                                    -->

                                                                    <!-- Above if condition is added to avoid the Tools section in Products and Vendors because we are not providing the Print and Email Now links throughout all the modules. when we provide these links we will remove this if condition -->




                                                                </tbody>
                                                            </table>


                                                            <br>
                                                            <!-- To display the Tag Clouds -->
                                                            <div>

                                                            </div>
                                                        </td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>						
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                                <tbody><tr>
                                                        <td class="dvtTabCacheBottom" style="width:10px" nowrap="">&nbsp;</td>

                                                        <td class="dvtSelectedCellBottom" align="center" nowrap="">Đặt hàng Thông tin</td>	
                                                        <td class="dvtTabCacheBottom" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCacheBottom" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javacript:window.location.href = 'inventory-manager-edit?id_inv=<s:property value="inventoryManager.getId()"/>'

                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;

                                                            <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" onclick="
                                                                    confirmdelete('' + str);

                                                                   " type="button" name="Delete" value="Xóa">&nbsp;


                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tbody></table>
                            <!-- PUBLIC CONTENTS STOPS-->
                        </div></td>
                    <td align="right" valign="top">
                        <img src="themes/softed/images/showPanelTopRight.gif">
                    </td>
                </tr>
            </tbody></table>

    </td>
</tr>
</tbody></table>
<!-- Contents - end -->

<br><br><br>

<!--    Footer-->
<s:include value="footer.jsp"></s:include>


</body>
</html>
