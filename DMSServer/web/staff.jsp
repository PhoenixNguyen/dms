<%-- 
    Document   : home
    Created on : Apr 9, 2014, 10:11:04 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
    <head>
        <title>admin - Nhân viên - Nhân viên - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link rel="SHORTCUT ICON" href="/DMS/themes/images/vtigercrm_icon.ico">  
        <style type="text/css">@import url("/DMS/themes/softed/style.css");</style>
        <link rel="stylesheet" type="text/css" media="all" href="/DMS/jscalendar/calendar-win2k-cold-1.css">
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

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Nhân viên &gt; <a class="hdrLink" href="">Danh sách</a></td>
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
<!--                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc'); searchshowhide('searchAcc', 'advSearch'); mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Khách hàng..." title="Tìm kiếm trong Khách hàng..." border="0"></a></td>-->

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

        <!-- KHACH HANG -->
        <table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                    <td class="showPanelBg" valign="top" width="100%" style="padding:10px;">
                        <!-- SIMPLE SEARCH -->
                        <div id="searchAcc" style="display: block;position:relative;">
                            <form name="basicSearch" method="GET" action="search-staff" id="sub_form">
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

                                <!-- List View Master Holder starts -->
                                <table border="0" cellspacing="1" cellpadding="0" width="100%" class="lvtBg">
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
                                                                    <td width="5%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = accountname & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Stt</a></td>
                                                                    <td width="10%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = accountname & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Tên Nhân viên</a></td>
                                                                    <td width="10%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = accountname & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Mã Nhân viên</a></td>
<!--                                                                    <td width="10%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = smownerid & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Mật khẩu</a></td>-->
                                                                    <td width="20%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = createdtime & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Địa chỉ</a></td>
                                                                    <td width="6%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = cf_607 & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Điện thoại</a></td>
                                                                    <td width="6%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = phone & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Chức vụ</a></td>
                                                                    <td width="8%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = phone & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Quyền hạn</a></td>
                                                                    <td width="10%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = phone & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Người quản lý</a></td>
                                                                    <td width="5%" class="lvtCol"><a href="javascript:;" onclick="getListViewEntries_js( & quot; Accounts & quot; , & quot; parenttab = Marketing & amp; foldername = Default & amp; order_by = rating & amp; start = 1 & amp; sorder = ASC & amp; viewname = 66 & quot; );" class="listFormHeaderLinks">Trạng thái</a></td>
                                                                    <td width="10%" class="lvtCol">Hoạt động</td>
                                                                </tr>
                                                                <!-- Table Contents -->
                                                                <s:iterator value="staffsList" status="index">
                                                                    <tr bgcolor="white" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" id="row_137" class="lvtColData">
                                                                        <td onmouseover=""><s:property value="%{#index.index + 1}"/><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="accountname" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><a href="staff-detail?id_st=<s:property value="stt"/>" title="Accounts"><s:property value="name"/></a> <span type="vtlib_metainfo" vtrecordid="137" vtfieldname="accountname" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><a href="staff-detail?id_st=<s:property value="stt"/>" title="Accounts"><s:property value="id"/></a> <span type="vtlib_metainfo" vtrecordid="137" vtfieldname="accountname" vtmodule="Accounts" style="display:none;"></span></td>
<!--                                                                        <td onmouseover=""><s:property value="pw"/><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="assigned_user_id" vtmodule="Accounts" style="display:none;"></span></td>-->
                                                                        <td onmouseover=""><s:property value="adress"/><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="createdtime" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><s:property value="phone"/><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="cf_607" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><s:property value="job"/><a href="javascript:;" onclick="startCall( & quot; 0915166889 & quot; , & quot; 137 & quot; )"></a> <span type="vtlib_metainfo" vtrecordid="137" vtfieldname="phone" vtmodule="Accounts" style="display:none;"></span></td>
                                                                            
                                                                            <td onmouseover="">
                                                                                <s:if test="permission == 1">Quản lý</s:if><s:if test="permission == 2">Nhân viên bán hàng</s:if><s:if test="permission == 3">Nhân viên cập nhật vị trí</s:if>
                                                                                <a href="javascript:;" onclick="startCall( & quot; 0915166889 & quot; , & quot; 137 & quot; )"></a> <span type="vtlib_metainfo" vtrecordid="137" vtfieldname="phone" vtmodule="Accounts" style="display:none;"></span></td>
                                                                            
                                                                            
                                                                        <td onmouseover=""><s:property value="manager"/><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="rating" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><s:if test="status == true">Hoạt động</s:if><s:else>Không hoạt động</s:else><span type="vtlib_metainfo" vtrecordid="137" vtfieldname="rating" vtmodule="Accounts" style="display:none;"></span></td>
                                                                        <td onmouseover=""><a href="edit-staff?id_st=<s:property value="stt"/>">Sửa</a>  | <a href='javascript:confirmdelete("delete-staff?id_st=<s:property value="stt"/>")'>Xóa</a></td>
                                                                    </tr>
                                                                </s:iterator>


                                                            </tbody>
                                                        </table>
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

        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>

    </body>
</html>