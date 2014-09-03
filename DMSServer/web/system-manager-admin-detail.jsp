<%-- 
    Document   : system-manager-admin-detail
    Created on : May 16, 2014, 2:44:43 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Thiết lập - Người sử dụng - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
        <link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { position:absolute; }</style>
        <![endif]-->

        <!-- End -->
        <script type='text/javascript' language='JavaScript'>
            
            
            var selected = <s:property value="selected"/>;
            console.log("deleteStatus: " + selected);
            
            var status = <s:property value="changePWStatus"/>
            if(status == "true"){
                alert('Đã cập nhật');
                
            }
            if(selected){
                var deleteStatus = <s:property value="deleteStatus"/>
                console.log("deleteStatus: " + deleteStatus);
                if(!deleteStatus){
                    alert('không thể xóa tài khoản này');

                }
            }
        </script>
    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">

        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'setup'" />
        </s:include>

        <!-- TOOLS -->


        <br>
        <!-- Shadow table -->
        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tr>
                <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
                    <br>
                    <div align=center>

                        <table border=0 cellspacing=0 cellpadding=20 width="99%" class="settingsUI">
                            <tr>
                                <td valign=top>
                                    <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                        <tr>
                                            
                                            <s:if test="#attr.PERMISSION == 0">
                                            <td valign=top id="settingsSideMenu" width="10%" >
                                                <!--Left Side Navigation Table-->
                                                <table border=0 cellspacing=0 cellpadding=0 width="100%">
                                                            <tr>
                                                                <td class="settingsTabHeader" nowrap>
                                                                    Quản lý người sử dụng
                                                                </td>
                                                            </tr>
                                                                                                                        
                                                            <tr>
                                                                <td class="settingsTabSelected" nowrap>
                                                                    <a href="system-manager">
                                                                        Quản trị viên
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="settingsTabList" nowrap>
                                                                    <a href="user-history">
                                                                        Lịch sử đăng nhập 
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="settingsTabList" nowrap>
                                                                    <a href="user-notify">
                                                                        Thông báo
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>
                                                <!-- Left side navigation table ends -->

                                            </td>
                                            <td width="8px" valign="top"> 
                                                <img src="themes/images/panel-left.png" title="Hide Menu" id="hideImage" style="display:inline;cursor:pointer;" onclick="toggleShowHide_panel('showImage', 'settingsSideMenu');
                                                        toggleShowHide_panel('showImage', 'hideImage');" />
                                                <img src="themes/images/panel-right.png" title="Show Menu" id="showImage" style="display:none;cursor:pointer;" onclick="toggleShowHide_panel('settingsSideMenu', 'showImage');
                                                        toggleShowHide_panel('hideImage', 'showImage');"/>
                                            </td>
                                            </s:if>
                                            <td class="small settingsSelectedUI" valign=top align=left>
                                                <script type="text/javascript">

                                                    function toggleShowHide_panel(showid, hideid) {
                                                        var show_ele = document.getElementById(showid);
                                                        var hide_ele = document.getElementById(hideid);
                                                        if (show_ele != null) {
                                                            show_ele.style.display = "";
                                                        }
                                                        if (hide_ele != null)
                                                            hide_ele.style.display = "none";
                                                    }

                                                </script>						
                                                <table width="100%"  border="0" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td class="padTab" align="left">
                                                            <form name="DetailView" method="POST" action="" ENCTYPE="multipart/form-data" id="form" style="margin:0px" onsubmit="VtigerJS_DialogBox.block();">
                                                                <input type="hidden" name="module" value="Users" style="margin:0px">
                                                                

                                                                <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                                    <tr>
                                                                        <td colspan=2>
                                                                            <!-- Heading and Icons -->
                                                                            <table width="100%" cellpadding="5" cellspacing="0" border="0" class="settingsSelUITopLine">
                                                                                <tr>
                                                                                    <td width=50 rowspan="2"><img src="themes/images/ico-users.gif" align="absmiddle"></td>	
                                                                                    <td>
                                                                                        <span class="heading2">
                                                                                            <b><a href="">Thiết lập </a> &gt; <s:property value="user.id"/> - <s:property value="user.hoTen"/> </b></span>
                                                                                        <span id="vtbusy_info" style="display:none;" valign="bottom"><img src="themes/images/vtbusy.gif" border="0"></span>					
                                                                                    </td>

                                                                                </tr>
                                                                                <tr>
                                                                                    <td>Thông tin chi tiết tài khoản <b><s:property value="user.id"/></b></td>
                                                                                </tr>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                    <tr><td colspan="2">&nbsp;</td></tr>
                                                                    <tr>
                                                                        <td colspan="2" nowrap align="right">
                                                                            <script>
                                                                                var str = "delete-admin?id_admin=<s:property value="user.stt"/>";
                                                                            </script>
                                                                            
                                                                            <input title='Sửa [Alt+E]' accessKey='E' class='crmButton small edit' onclick="
                                                                                window.location.href = 'admin-edit?id_admin=<s:property value="user.stt"/>'
                                                                                   " type='button' name='Edit' value='  Sửa  '>
                                                                            <s:if test="#attr.PERMISSION == 0">
                                                                            <input type="button" onclick="confirmdelete('' + str);" class="crmButton small cancel" value="Xóa"></input>
                                                                            </s:if>
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td colspan="2" align=left>
                                                                            <!-- User detail blocks -->
                                                                            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%">
                                                                                <tr>
                                                                                    <td align="left" valign="top">
                                                                                        <br>
                                                                                        <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                                            <tr>
                                                                                                <td class="big"><strong>Thông tin tài khoản</strong></td><td class="small" align="right">&nbsp;</td>
                                                                                            </tr>
                                                                                        </table>

                                                                                        <table border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                                            <tr >

                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Họ tên</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width=25%">&nbsp;<s:property value="user.hoTen"/></td>


                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Chức danh</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--CheckBox for is admin-->

                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Người quản trị" onMouseOver="hndMouseOver(156, 'Người quản trị');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Người quản trị"><s:property value="user.chucDanh"/>&nbsp;</span>
                                                                                                    
                                                                                                </td>    


                                                                                            </tr>
                                                                                            <tr >

                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Mật khẩu</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!-- Password Field-->
                                                                                                <%
                                                                                                    String username = (String)session.getAttribute("user_name");
                                                                                                    session.setAttribute("logined_user", username.toLowerCase());
                                                                                                %>
                                                                                                <td width=25% class="dvtCellInfo" align="left">
                                                                                                    <s:if test="#attr.logined_user == user.id.toLowerCase()">
                                                                                                    <input title='Thay đổi mật khẩu [Alt+P]' accessKey='P' class='crmButton password small' LANGUAGE=javascript onclick='return window.open("admin-change-password");' type='button' name='password' value='Thay đổi mật khẩu'>
                                                                                                    </s:if>
                                                                                                </td>	


                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Email</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--Email-->
                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Email" onmouseover="hndMouseOver(104, 'Email');" onmouseout="fnhide('crmspanid');"><span id="dtlview_Email">
                                                                                                        <a href="javascript:InternalMailer(5,463,'email1','Users','record_id');"><s:property value="user.email"/></a>
                                                                                                    </span>
                                                                                                    
                                                                                                </td>

                                                                                            </tr>
                                                                                            <tr >

                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Số điện thoại</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--ComboBox Status edit only for admin Users-->
                                                                                                <td width=25% class="dvtCellInfo" align="left"><s:property value="user.sdt"/></td>


                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Địa chỉ</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Họ" onmouseover="hndMouseOver(1, 'Họ');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;<span id="dtlview_Họ"><s:property value="user.diaChi"/></span>
                                                                                                    
                                                                                                </td>

                                                                                            </tr>
                                                                                            <tr >

                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Ngày tham gia</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--ComboBox currency id edit only for admin Users-->
                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Tiền tệ" onmouseover="hndMouseOver(117, 'Tiền tệ');" onmouseout="fnhide('crmspanid');">&nbsp;<span id="dtlview_Tiền tệ"><s:property value="user.ngayThamGia"/></span>
                                                                                                    
                                                                                                </td>


                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Quyền</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--TextBox-->
                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Tên" onmouseover="hndMouseOver(2, 'Tên');" onmouseout="fnhide('crmspanid');" valign="top">

                                                                                                    &nbsp;&nbsp;
                                                                                                    <span id="dtlview_Tên">
                                                                                                        <s:if test="user.permission == 1">Người dùng</s:if>
                                                                                                        <s:if test="user.permission == 2">Nhóm kinh doanh</s:if>
                                                                                                        <s:if test="user.permission == 0">Quản trị hệ thống</s:if>
                                                                                                    </span>
                                                                                                    
                                                                                                </td>

                                                                                            </tr>
                                                                                            <tr >

                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Trạng thái</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                                <!--ComboBox-->

                                                                                                <td width=25% class="dvtCellInfo" align="left" id="mouseArea_Xem Đầu mối mặc định" onmouseover="hndMouseOver(16, 'Xem Đầu mối mặc định');" onmouseout="fnhide('crmspanid');">
                                                                                                    <span id="dtlview_Xem Đầu mối mặc định">
                                                                                                        <font color="">
                                                                                                        <s:if test="user.status == true">Đang hoạt động</s:if>
                                                                                                        <s:if test="user.status == false">Dừng hoạt động</s:if>
                                                                                                        
                                                                                                        </font>
                                                                                                </span>
                                                                                                    
                                                                                                </td>


                                                                                                <td class="dvtCellLabel" align=right width=25%><input type="hidden" id="hdtxt_IsAdmin" value=1></input>Ghi chú</td>

                                                                                                <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                                <td class="dvtCellInfo" align="left" width=25%">&nbsp;<a href=""><s:property value="user.ghiChu"/></a></td>

                                                                                            </tr>
                                                                                            
                                                                                        </table>

                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                            <!-- User detail blocks ends -->

                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td colspan=2 class="small"><div align="right"><a href="#top"></a></div></td>
                                                                    </tr>
                                                                </table>

                                                            </form>

                                                        </td>
                                                    </tr>
                                                </table>


                                                </div>
                                            </td>

                                        </tr>
                                    </table>

                                </td>
                            </tr>
                        </table>

                </td>
                <td valign="top"><img src="themes/softed/images/showPanelTopRight.gif"></td>			
            </tr>
        </table>




        <br>

        <div id="tempdiv" style="display:block;position:absolute;left:350px;top:200px;"></div>
        <!-- added for validation -->
        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>
    </body>
</html>

