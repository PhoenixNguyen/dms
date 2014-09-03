<%-- 
    Document   : product-detail
    Created on : Apr 11, 2014, 2:53:47 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Tồn kho - Sản phẩm - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
                    alert("Không thể xóa do sản phẩm đã được sử dụng cho nội dung khác");
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

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Tồn kho &gt; <a class="hdrLink" href="">Sản phẩm</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-product"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Sản phẩm..." title="Tạo mới Sản phẩm..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-product"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Sản phẩm" title="Nhập dữ liệu Sản phẩm" border="0"></a></td>  
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
                                                    
                                                </tr>
                                            </tbody></table>  
                                    </td>
                                    <td style="width:20px;">&nbsp;</td>
                                    <td class="small">
                                        <!-- All Menu -->
                                        <table border="0" cellspacing="0" cellpadding="5">
                                            <tbody>
                                                <tr>
                                                    
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
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" valign="top" width="100%">
                        <!-- PUBLIC CONTENTS STARTS-->
                        <div class="small" style="padding:20px">

                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="95%">
                                <tbody><tr>
                                        <td>

                                            <span class="lvtHeaderText"><font color="purple">[ <s:property value="product.productID"/> ] </font><s:property value="product.productName"/> -  Sản phẩm Thông tin</span>&nbsp;&nbsp;&nbsp;
                                            <span class="small">
                                                <!--                                        Cập nhật 1084 ngày trước (22 Tháng 4 2011)-->
                                            </span>&nbsp;<span id="vtbusy_info" style="display:none;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span><span id="vtbusy_info" style="visibility:hidden;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span>
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

                                                        <td class="dvtSelectedCell" align="center" nowrap="">Sản phẩm Thông tin</td>	
                                                        <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCache" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javascript:window.location.href = 'edit-product?id_pdct=<s:property value="product.serial"/>';
                                                                    return false;

                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;
                                                           
                                                            <script>
                                                                var str = "delete-product?id_pdct=<s:property value="product.serial"/>";
                                                            </script>
                                                            <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" onclick="
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
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace" style="border-bottom:0px;">
                                                <tbody><tr valign="top">

                                                        <td align="left" style="padding:10px;">
                                                            <!-- content cache -->
                                                            <form action="index.php" method="post" name="DetailView" id="form" onsubmit="VtigerJS_DialogBox.block();">
                                                                <input type="hidden" name="parenttab" value="Inventory">



                                                                <!-- Entity informations display - starts -->	
                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td style="padding:10px;border-right:1px dashed #CCCCCC;" width="80%">

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
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinsảnphẩm','aidThôngtinsảnphẩm','themes/softed/images/');"><img id="aidThôngtinsảnphẩm" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin sản phẩm</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinsảnphẩm">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">

                                                                                        <tbody>
                                                                                            <tr style="height:25px">

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Tên sản phẩm</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên sản phẩm" onmouseover="hndMouseOver(2, 'Tên sản phẩm');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Tên sản phẩm"><s:property value="product.productName"/></span>

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã sản phẩm</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="product.productID"/></td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã vạch</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--CheckBox--> 
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Có bán sản phẩm" onmouseover="hndMouseOver(56, 'Có bán sản phẩm');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Có bán sản phẩm"><s:property value="product.barcode"/>&nbsp;</span>
                                                                                                </td>    

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thương hiệu</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Phần Số" onmouseover="hndMouseOver(1, 'Phần Số');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Phần Số"><s:property value="product.brand"/></span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Xuất xứ</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày bắt đầu bán" onmouseover="hndMouseOver(5, 'Ngày bắt đầu bán');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày bắt đầu bán">
                                                                                                        <s:property value="product.origin"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Nhà cung cấp</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--ComboBox-->

                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Nhà sản xuất" onmouseover="hndMouseOver(15, 'Nhà sản xuất');" onmouseout="fnhide('crmspanid');"><span id="dtlview_Nhà sản xuất"><font color=""><s:property value="product.provider"/></font></span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
<!--                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Loại sản phẩm</td>
                                                                                                 This file is used to display the fields based on the ui type in detailview 
                                                                                                ComboBox

                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Loại sản phẩm" onmouseover="hndMouseOver(15, 'Loại sản phẩm');" onmouseout="fnhide('crmspanid');"><span id="dtlview_Loại sản phẩm"><font color=""></font></span>-->

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thuế</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày bắt đầu hỗ trợ" onmouseover="hndMouseOver(5, 'Ngày bắt đầu hỗ trợ');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày bắt đầu hỗ trợ">
                                                                                                        <s:property value="product.vatTax"/>
                                                                                                    </span>

                                                                                                </td>

                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Giá trước thuế</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày kết thúc bán" onmouseover="hndMouseOver(5, 'Ngày kết thúc bán');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày kết thúc bán">
                                                                                                        <s:property value="product.importPrices"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Giá sau thuế</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày hết hạn hỗ trợ" onmouseover="hndMouseOver(5, 'Ngày hết hạn hỗ trợ');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày hết hạn hỗ trợ">
                                                                                                        <s:property value="product.exportPrices"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Đơn vị</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--VendorPopup-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên nhà cung cấp">&nbsp;
                                                                                                    <s:property value="product.quantification"/>
                                                                                                </td>

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Quy cách</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--WebSite-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Website" onmouseover="hndMouseOver(17, 'Website');" onmouseout="fnhide('crmspanid');">&nbsp;
                                                                                                    <span id="dtlview_Website"><s:property value="product.packingSpecifications"/></span>

                                                                                                </td>
                                                                                            </tr>	


                                                                                        </tbody>
                                                                                    </table>
                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinGiácả','aidThôngtinGiácả','themes/softed/images/');"><img id="aidThôngtinGiácả" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin Giá cả</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinGiácả">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                        <tbody><tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <!--CurrencySymbol-->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Giá bán (₫)</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Giá đơn vị" onmouseover="hndMouseOver(71, 'Giá đơn vị');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Giá đơn vị"><s:property value="product.exportPrices"/></span>

                                                                                                </td>

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Thuế (%)</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tiền hoa hồng" onmouseover="hndMouseOver(9, 'Tiền hoa hồng (%)');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Tiền hoa hồng"><s:property value="product.vatTax"/></span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <!--                                                                                    <tr style="height:25px">
                                                                                                                                                                                     Avoid to display the label Tax Class 
                                                                                                                                                                                    <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">GTGT</td>
                                                                                                                                                                                     This file is used to display the fields based on the ui type in detailview 
                                                                                                                                                                                     Handle the Tax in Inventory 
                                                                                            
                                                                                                                                                                                </tr>	-->

                                                                                        </tbody>
                                                                                    </table>
                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->

                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinhìnhảnhsảnphẩm','aidThôngtinhìnhảnhsảnphẩm','themes/softed/images/');"><img id="aidThôngtinhìnhảnhsảnphẩm" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin hình ảnh sản phẩm</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinhìnhảnhsảnphẩm">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">

                                                                                        <tbody>
                                                                                            <tr style="height:25px">

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Hình ảnh sản phẩm</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!-- for Image Reflection -->
                                                                                                <s:if test="product.productImage != null">
                                                                                                    <td align="left" width="25%"><img src="db_products/<s:property value="product.productID"/>/<s:property value="product.productImage"/>" title="<s:property value="product.productImage"/>" width="100%" height="100"/></td>
                                                                                                </s:if>
                                                                                                <s:else>
                                                                                                <td align="left" width="25%"><img src="db_images/default.jpg" title="Ảnh mặc định" width="100%" height="100"/></td>
                                                                                                </s:else>
                                                                                                
                                                                                                <td class="dvtCellLabel" align="right" width="25%">
                                                                                                <input title='Thay đổi ảnh' accessKey='P' class='crmButton password small' LANGUAGE=javascript onclick='return window.open("change-image-product?id_pdct=<s:property value="product.serial"/>");' type='button' name='password' value='Thay đổi ảnh'>
                                                                                                </td>
                                                                                                <td class="dvtCellLabel" align="left" width="25%"></td>
                                                                                            </tr>	
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td>&nbsp;</td>
                                                                                            <td align="right">
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinchitiết','aidThôngtinchitiết','themes/softed/images/');"><img id="aidThôngtinchitiết" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin chi tiết</b></div></td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinchitiết">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                        <tbody><tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mô tả</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextArea/Description-->
                                                                                                <!-- we will empty the value of ticket and faq comment -->
                                                                                                <!--  -->
                                                                                                <!-- -->
                                                                                                <td width="100%" colspan="3" class="dvtCellInfo" align="left" id="mouseArea_Mô tả" onmouseover="hndMouseOver(19, 'Mô tả');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Mô tả">
                                                                                                        <s:property value="product.description"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                        </tbody>

                                                                                    </table>

                                                                                </div> <!-- Line added by SAKTI on 10th Apr, 2008 -->

                                                                                <!-- Entity information(blocks) display - ends -->

                                                                                <br>

                                                                                <!-- Product Details informations -->

                                                                            </td>
                                                                            <!-- The following table is used to display the buttons -->
                                                                        </tr></tbody>
                                                                </table>

                                                                <!-- Inventory Actions - ends -->	
                                                            </form></td><td width="22%" valign="top" style="padding:10px;">
                                                            <!-- right side InventoryActions -->

                                                            <!-- Avoid this actions display for PriceBook module-->


                                                            <!-- Added this file to display the Inventory Actions based on the Inventory Modules -->
                                                            <br><table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                <tbody><tr>
                                                                        <td>&nbsp;</td>
                                                                    </tr>

                                                                    <!-- This if condition is added to avoid display Tools heading because now there is no options in Tools. -->
                                                                    <tr>
                                                                        <!--                                                <td align="left" class="genHeaderSmall">Hoạt động</td>-->
                                                                    </tr>



                                                                    <!-- Module based actions starts -->
                                                                    <!-- Product/Services Actions starts -->
                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:5px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGenerateQuote.gif" hspace="2" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Tạo Báo giá</a> 
                                                                                                                    </td>
                                                                                                                </tr>
                                                                                                                <tr>
                                                                                                                    <td align="left" style="padding-left:5px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGenerateInvoice.gif" hspace="2" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Tạo Hóa đơn</a> 
                                                                                                                    </td>
                                                                                                                </tr>-->
                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:5px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGenerateSalesOrder.gif" hspace="2" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Tạo Đặt hàng</a> 
                                                                                                                    </td>
                                                                                                                </tr>-->
                                                                    <!--                                            <tr>
                                                                                                                    <td align="left" style="padding-left:5px;">
                                                                                                                        <a href="" class="webMnu"><img src="themes/images/actionGenPurchaseOrder.gif" hspace="2" align="absmiddle" border="0"></a>
                                                                                                                        <a href="" class="webMnu">Tạo Nhập hàng</a> 
                                                                                                                    </td>
                                                                                                                </tr>-->
                                                                    <!-- Module based actions ends -->
                                                                    <tr>
                                                                        <td>
                                                                        </td>
                                                                    </tr>
                                                                    <!-- Action links END -->
                                                                    <!-- Following condition is added to avoid the Tools section in Products and Vendors because we are not providing the Print and Email Now links throughout all the modules. when we provide these links we will remove this if condition -->
                                                                    <!-- Above if condition is added to avoid the Tools section in Products and Vendors because we are not providing the Print and Email Now links throughout all the modules. when we provide these links we will remove this if condition -->

                                                                </tbody>
                                                            </table>

                                                            <br>
                                                            <!-- To display the Tag Clouds -->
                                                            <div>

                                                            </div>
                                                        </td>
                                                    </tr>

                                                </tbody></table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>						
                                            <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                                <tbody><tr>
                                                        <td class="dvtTabCacheBottom" style="width:10px" nowrap="">&nbsp;</td>

                                                        <td class="dvtSelectedCellBottom" align="center" nowrap="">Sản phẩm Thông tin</td>	
                                                        <td class="dvtTabCacheBottom" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCacheBottom" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javascript:window.location.href = 'edit-product?id_pdct=<s:property value="product.serial"/>';
                                                                    return false;

                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;

                                                            <input title="Xóa [Alt+D]" accesskey="D" class="crmbutton small delete" onclick="
                                                                    confirmdelete('' + str);

                                                                   " type="button" name="Delete" value="Xóa">&nbsp;


                                                        </td>
                                                    </tr>
                                                </tbody></table>
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

<!--    Footer-->
<s:include value="footer.jsp"></s:include>


</body>
</html>
