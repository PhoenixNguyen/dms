<%-- 
    Document   : customers-import
    Created on : Apr 10, 2014, 12:48:48 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <title>admin - Sản phẩm - Nhập dữ liệu - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'inventory'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Sản phẩm &gt; <a class="hdrLink" href="">Nhập liệu</a></td>
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
                                                    
<!--                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:alert('Chức năng chưa được xây dựng!')" onclick="return selectedRecords('Accounts', 'Marketing')"<img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Sản phẩm" title="Xuất dữ liệu Sản phẩm" border="0"></a></td>-->


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

        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%" class="small">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" valign="top" width="100%">

                        <!-- Import UI Starts -->
                        <table cellpadding="0" cellspacing="0" width="100%" border="0">
                            <tbody><tr>
                                    <td width="75%" valign="top">
                                        <!--                                <form enctype="multipart/form-data" name="Import" method="POST"  onsubmit="">-->
                                        <input type="hidden" name="module" value="Accounts">


                                        <!-- IMPORT LEADS STARTS HERE  -->
                                        <br>
                                        <table align="center" cellpadding="5" cellspacing="0" width="80%" class="mailClient importLeadUI small" border="0">
                                            <tbody>
                                                <tr>
                                                    <td colspan="2" height="50" valign="middle" align="left" class="mailClientBg  genHeaderSmall">Nhập dữ liệu Sản phẩm</td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="left" valign="top" style="padding-left:40px;">
                                                        <br>

                                                        <span class="genHeaderGray">Bước 1 of 2  </span>&nbsp; 
                                                        <span class="genHeaderSmall">Chọn file .xls</span> 
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" align="left" valign="top" style="padding-left:40px;">
                                                        Phần mềm hỗ trợ nhập các bản ghi từ file .xls. Để bắt đầu nhập, chọn file .xls và ấn Next để tiếp tục. <a href="product-template">(Lấy file mẫu)</a>
                                                    </td>
                                                </tr>
                                                <tr><td align="left" valign="top" colspan="2">&nbsp;</td></tr>
                                                <tr>
                                                    <td align="right" valign="top" width="25%" class="small"><b><u>Vị trí Tập tin:</u></b></td>
                                                    <td align="left" valign="top" width="75%">
                                                        <s:actionerror />
                                                        <s:form action="upload-product" onsubmit="" id="upload_form" enctype="multipart/form-data" validate="true"
                                                                >  
                                                            <s:file type="file" name="document.file" id="upfile"/>
                                                            <br/>
                                                            <br/>
                                                            <br/>
                                                        </s:form>    

                                                        <!--                                                    <input type="file" name="userfile" size="65" class="small" onchange="validateFilename(this);">&nbsp;
                                                                                                            <input type="hidden" name="userfile_hidden" value=""><br>-->
                                                        <!--                                                    <br><b>Có phần đầu</b>&nbsp;<input type="checkbox" name="has_header" checked="">
                                                                                                            &nbsp;&nbsp;&nbsp;&nbsp;<b>Dấu phân cách</b>&nbsp;
                                                                                                            <select name="delimiter" class="small" style="font-family:Times;">
                                                                                                                <option value=",">,</option>
                                                                                                                <option value=";">;</option>
                                                                                                            </select>-->
                                                        <br>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;<b>Định dạng</b>&nbsp;
                                                        <select name="format" class="small">
                                                            <!-- value must be a known format for mb_convert_encoding() -->
                                                            <option value="UTF-8">UTF-8</option>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr><td colspan="2" height="50">&nbsp;</td></tr>
                                                <tr>
                                                    <td colspan="2" align="right" style="padding-right:40px;" class="reportCreateBottom">
                                                        <input title="Tiếp" accesskey="" class="crmButton small save" type="submit" name="button" value="  Tiếp › " onclick="
                                                                document.getElementById('upload_form').submit();
//                                                            this.form.action.value = 'Import';
//                                                            this.form.step.value = '2';
//                                                            return validateFile(this.form);
                                                               ">
                                                        &nbsp;
                                                        <input title="Hủy bỏ" accesskey="" class="crmButton small cancel" type="button" name="button" value="Hủy bỏ" onclick="window.history.back()">

                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <br>
                                        <!--                                </form>-->
                                        <!-- IMPORT LEADS ENDS HERE -->
                                    </td>
                                </tr>
                            </tbody></table>

                    </td>
                    <td valign="top"><img src="themes/softed/images/showPanelTopRight.gif"></td>
                </tr>
            </tbody></table>
        <br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>

    </body>
</html>
