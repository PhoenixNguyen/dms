<%-- 
    Document   : product
    Created on : Apr 10, 2014, 12:36:47 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Tồn kho - Sản phẩm - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">	
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-product"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới..." title="Tạo mới..." border="0"></a></td>
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
                                                    
<!--                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:alert('Chức năng chưa được xây dựng!')" onclick="return selectedRecords('Accounts', 'Marketing')"<img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Sản phẩm" title="Xuất dữ liệu Sản phẩm" border="0"></a></td>-->


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

        <!--PRODUCT-->
        <table border=0 cellspacing=0 cellpadding=0 width=98% align=center>
            <tr>
                <td valign=top><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                <td class="showPanelBg" valign="top" width=100% style="padding:10px;">
                    <!-- SIMPLE SEARCH -->
                    <div id="searchAcc" style="display: block;position:relative;">
                            <form name="basicSearch" method="GET" action="search-product" id="sub_form">
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
                            <input name='search_url' id="search_url" type='hidden' value=''>
                            <input name="idlist" id="idlist" type="hidden">
                            <input name="change_owner" type="hidden">
                            <input name="change_status" type="hidden">
                            <input name="action" type="hidden">
                            <input name="where_export" type="hidden" value="">
                            <input name="step" type="hidden">
                            <input name="allids" type="hidden" id="allids" value="">
                            <input name="selectedboxes" id="selectedboxes" type="hidden" value="">
                            <input name="allselectedboxes" id="allselectedboxes" type="hidden" value="">
                            <input name="current_page_boxes" id="current_page_boxes" type="hidden" value="142;187;188;712;714;716;718;2278;2314;2316;2322;6898;6899;6900;6901;6902;6903;6904;6906;6907">
                            <!-- List View Master Holder starts -->
                            <table border=0 cellspacing=1 cellpadding=0 width=100% class="lvtBg">
                                <tr>
                                    <td>

                                        <!-- List View's Buttons and Filters ends -->

                                        <div  >
                                            <table border=0 cellspacing=1 cellpadding=3 width=100% class="lvt small">
                                                <!-- Table Headers -->
                                                <tr>
                                                    <!--td class="lvtCol"><input type="checkbox"  name="selectall" onClick=toggleSelect_ListView(this.checked, "selected_id")></td-->
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=productname&start=&sorder=ASC");' class='listFormHeaderLinks'>Stt</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=productname&start=&sorder=ASC");' class='listFormHeaderLinks'>Tên sản phẩm</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=serialno&start=&sorder=ASC");' class='listFormHeaderLinks'>Mã sản phẩm</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=createdtime&start=&sorder=ASC");' class='listFormHeaderLinks'>Mã vạch</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=qtyinstock&start=&sorder=ASC");' class='listFormHeaderLinks'>Thương hiệu</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=qtyinstock&start=&sorder=ASC");' class='listFormHeaderLinks'>Nhà cung cấp</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=cf_628&start=&sorder=ASC");' class='listFormHeaderLinks'>Giá</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=cf_628&start=&sorder=ASC");' class='listFormHeaderLinks'>Đơn vị</a></td>
                                                    <td class="lvtCol"><a href='javascript:;' onClick='getListViewEntries_js("Products", "parenttab=Inventory&foldername=Default&order_by=reorderlevel&start=&sorder=ASC");' class='listFormHeaderLinks'>Mô tả</a></td>
                                                    <td class="lvtCol">Hoạt động</td>
                                                </tr>
                                                <!-- Table Contents -->
                                                <s:iterator value="productsList" status="index">
                                                    <tr bgcolor=white onMouseOver="this.className = 'lvtColDataHover'" onMouseOut="this.className = 'lvtColData'" id="row_142">
                                                        <!--td width="2%"><input type="checkbox" NAME="selected_id" id="142" value= '142' onClick="check_object(this)"></td-->

                                                        <td onmouseover=""><s:property value="%{#index.index + 1}"/> <span type='vtlib_metainfo' vtrecordid='142' vtfieldname='productname' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><a href='product-detail?id_pdct=<s:property value="serial"/>' title='Products'><s:property value="productName"/></a> <span type='vtlib_metainfo' vtrecordid='142' vtfieldname='productname' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="productID"/> <span type='vtlib_metainfo' vtrecordid='142' vtfieldname='serial_no' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="barcode"/> <span type='vtlib_metainfo' vtrecordid='142' vtfieldname='createdtime' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="brand"/><span type='vtlib_metainfo' vtrecordid='142' vtfieldname='qtyinstock' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="provider"/> <span type='vtlib_metainfo' vtrecordid='142' vtfieldname='cf_628' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="exportPrices"/><span type='vtlib_metainfo' vtrecordid='142' vtfieldname='reorderlevel' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="quantification"/><span type='vtlib_metainfo' vtrecordid='142' vtfieldname='reorderlevel' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><s:property value="description"/><span type='vtlib_metainfo' vtrecordid='142' vtfieldname='reorderlevel' vtmodule='Products' style='display:none;'></span></td>

                                                        <td onmouseover=""><a href="edit-product?id_pdct=<s:property value="serial"/>">Sửa</a>  | <a href='javascript:confirmdelete("delete-product?id_pdct=<s:property value="serial"/>")'>Xóa</a></td>
                                                    </tr>
                                                </s:iterator>
                                            </table>
                                        </div>


                                    </td>
                                </tr>
                            </table>

                        </form>	

                        <div id="basicsearchcolumns" style="display:none;">
                            <select name="search_field" id="bas_searchfield" class="txtBox" style="width:150px"><option label="Tên sản phẩm" value="productname">Tên sản phẩm</option>
                                <option label="Số hiệu" value="serial_no">Số hiệu</option>
                                <option label="Tạo lúc" value="createdtime">Tạo lúc</option>
                                <option label="Tồn kho" value="qtyinstock">Tồn kho</option>
                                <option label="Tên kho" value="cf_628">Tên kho</option>
                                <option label="Mức tồn kho chuẩn" value="reorderlevel">Mức tồn kho chuẩn</option>
                            </select>
                        </div>	  	
                    </div>

                </td>
                <td valign=top><img src="themes/softed/images/showPanelTopRight.gif"></td>
            </tr>
        </table>

        <!-- MassEdit Feature -->
        <div id="massedit" class="layerPopup" style="display:none;width:80%;">
            <table width="100%" border="0" cellpadding="3" cellspacing="0" class="layerHeadingULine">
                <tr>
                    <td class="layerPopupHeading" align="left" width="60%">Sửa nhanh - Nhiều dòng</td>
                    <td>&nbsp;</td>
                    <td align="right" width="40%"><img onClick="fninvsh('massedit');" title="Đóng" alt="Đóng" style="cursor:pointer;" src="themes/images/close.gif" align="absmiddle" border="0"></td>
                </tr>
            </table>
            <div id="massedit_form_div"></div>

        </div>
        <!-- END -->
        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>


    </body>
</html>

