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
        <title>admin - Tồn kho - Nhà cung cấp - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
                    alert("Không thể xóa do Nhà cung cấp đã được sử dụng cho nội dung khác");
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

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Tồn kho &gt; <a class="hdrLink" href="">Nhà cung cấp</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-provider"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Nhà cung cấp..." title="Tạo mới Nhà cung cấp..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-provider"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Nhà cung cấp" title="Nhập dữ liệu Nhà cung cấp" border="0"></a></td>  

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

                                            <span class="lvtHeaderText"><font color="purple">[ <s:property value="provider.id"/> ] </font><s:property value="provider.name"/> -  Thông tin nhà cung cấp</span>&nbsp;&nbsp;&nbsp;
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

                                                        <td class="dvtSelectedCell" align="center" nowrap="">Thông tin nhà cung cấp</td>	
                                                        <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCache" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javascript:window.location.href = 'edit-provider?id_pvd=<s:property value="provider.serial"/>';
                                                                    return false;

                                                                   " type="button" name="Edit" value="&nbsp;Sửa&nbsp;">&nbsp;
     
                                                            <script>
                                                                var str = "delete-provider?id_pvd=<s:property value="provider.serial"/>";
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
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinsảnphẩm','aidThôngtinsảnphẩm','themes/softed/images/');"><img id="aidThôngtinsảnphẩm" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Thông tin Nhà cung cấp</b></div></td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinsảnphẩm">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">

                                                                                        <tbody>
                                                                                            <tr style="height:25px">

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Tên Nhà cung cấp</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Tên sản phẩm" onmouseover="hndMouseOver(2, 'Tên sản phẩm');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Tên sản phẩm"><s:property value="provider.name"/></span>

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Mã Nhà cung cấp</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="provider.id"/></td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Địa chỉ</td>
                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--CheckBox--> 
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Có bán sản phẩm" onmouseover="hndMouseOver(56, 'Có bán sản phẩm');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Có bán sản phẩm"><s:property value="provider.address"/>&nbsp;</span>
                                                                                                </td>    

                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Điện thoại</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Phần Số" onmouseover="hndMouseOver(1, 'Phần Số');" onmouseout="fnhide('crmspanid');" valign="top">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Phần Số"><s:property value="provider.phoneNumber"/></span>

                                                                                                </td>
                                                                                            </tr>	
                                                                                            <tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Fax</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <td width="25%" class="dvtCellInfo" align="left" id="mouseArea_Ngày bắt đầu bán" onmouseover="hndMouseOver(5, 'Ngày bắt đầu bán');" onmouseout="fnhide('crmspanid');">
                                                                                                    &nbsp;&nbsp;<span id="dtlview_Ngày bắt đầu bán">
                                                                                                        <s:property value="provider.fax"/>
                                                                                                    </span>

                                                                                                </td>
                                                                                                <!-- Avoid to display the label Tax Class -->

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
                                                                                            <td colspan="4" class="dvInnerHeader"><div style="float:left;font-weight:bold;"><div style="float:left;"><a href="javascript:showHideStatus('tblThôngtinchitiết','aidThôngtinchitiết','themes/softed/images/');"><img id="aidThôngtinchitiết" src="themes/images/activate.gif" style="border: 0px solid #000000;" alt="Hide" title="Hide"></a></div><b>&nbsp;Ghi chú</b></div></td>
                                                                                        </tr>
                                                                                    </tbody>
                                                                                </table>
                                                                                <div style="width:auto;display:block;" id="tblThôngtinchitiết">
                                                                                    <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                        <tbody><tr style="height:25px">
                                                                                                <!-- Avoid to display the label Tax Class -->
                                                                                                <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Ghi chú</td>

                                                                                                <td width="100%" colspan="3" class="dvtCellInfo" align="left" id="mouseArea_Mô tả" onmouseover="hndMouseOver(19, 'Mô tả');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Mô tả">
                                                                                                        <s:property value="provider.note"/>
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
                                                            <br>
                                                            <table width="100%" border="0" cellpadding="5" cellspacing="0">
                                                                <tbody><tr>
                                                                        <td>&nbsp;</td>
                                                                    </tr>

                                                                    <!-- This if condition is added to avoid display Tools heading because now there is no options in Tools. -->
                                                                    <tr>
                                                                        <!--                                                <td align="left" class="genHeaderSmall">Hoạt động</td>-->
                                                                    </tr>

                                                                    <tr>
                                                                        <td>
                                                                        </td>
                                                                    </tr>
                                                                    
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

                                                        <td class="dvtSelectedCellBottom" align="center" nowrap="">Thông tin Nhà cung cấp</td>	
                                                        <td class="dvtTabCacheBottom" style="width:10px">&nbsp;</td>
                                                        <td class="dvtTabCacheBottom" align="right" style="width:100%">
                                                            <input title="Sửa [Alt+E]" accesskey="E" class="crmbutton small edit" onclick="
                                                                    javascript:window.location.href = 'edit-provider?id_pvd=<s:property value="provider.serial"/>';
                                                                    return false;

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

<!--    Footer-->
<s:include value="footer.jsp"></s:include>


</body>
</html>
