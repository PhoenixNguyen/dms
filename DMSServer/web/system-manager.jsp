<%-- 
    Document   : setup
    Created on : May 15, 2014, 11:01:05 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Thiết lập - Thiết lập - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
                        
            if(selected == "true"){
                var deleteStatus = <s:property value="deleteStatus"/>
                if(status == "true"){
                    alert('Đã xóa tài khoản');

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
        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
                        <form action="index.php" method="post" name="new" id="form" onsubmit="VtigerJS_DialogBox.block();">
                            <input type="hidden" name="module" value="Users">
                            <input type="hidden" name="mode" value="create">
                            <input type="hidden" name="action" value="CreateProfile">
                            <input type="hidden" name="parenttab" value="Settings">

                            <br>
                            <div align=center>

                                <table border=0 cellspacing=0 cellpadding=20 width="99%" class="settingsUI">
                                    <tr>
                                        <td valign=top>
                                            <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                                <tr>
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
                                                        <!-- DISPLAY -->
                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="settingsSelUITopLine">
                                                            <tr>
                                                                <td width=50 rowspan=2 valign=top><img src="themes/images/ico-profile.gif" alt="Lý lịch" width="48" height="48" border=0 title="Danh sách quản trị"></td>
                                                                <td class=heading2 valign=bottom><b><a href="">Danh sách quản trị</a>   </b></td>
                                                            </tr>
                                                            <tr>
                                                                <td valign=top class="small">Danh sách quản trị viên của hệ thống</td>
                                                            </tr>
                                                        </table>


                                                        <table border=0 cellspacing=0 cellpadding=10 width=100% >
                                                            <tr>
                                                                <td valign=top>

                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% class="tableHeading">
                                                                        <tr>
                                                                            <td class="big"><strong>Danh sách quản trị</strong></td>
                                                                            <td class="small" align=right>Tổng  quản trị viên </td>
                                                                        </tr>
                                                                    </table>



                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% class="listTableTopButtons">
                                                                        <tr>
                                                                            <td class=small align=right><input type="button" value="Tạo mới" title="Tạo mới" class="crmButton create small" onclick="window.location.href='add-user'"></td>
                                                                        </tr>
                                                                    </table>

                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% class="listTable">
                                                                        <tr>
                                                                            <td class="colHeader small" valign=top width=2%>#</td>
                                                                            <td class="colHeader small" valign=top width=8%>Công cụ</td>
                                                                            <td class="colHeader small" valign=top width=30%>Mã quản trị</td>
                                                                            <td class="colHeader small" valign=top width=20%>Tên</td>
                                                                            <td class="colHeader small" valign=top width=20%>Chức danh</td>
                                                                            <td class="colHeader small" valign=top width=20%>Trạng thái</td>
                                                                        </tr>
                                                                        
                                                                        <s:iterator value="userList" status="index">
                                                                        <tr>
                                                                            <td class="listTableRow small" valign=top><s:property value="%{#index.index + 1}"/></td>
                                                                            <td class="listTableRow small" valign=top nowrap>
                                                                                <a href=""><img src="themes/images/editfield.gif" alt="Sửa" title="Sửa" border="0" align="absmiddle"></a>

                                                                            </td>
                                                                            <td class="listTableRow small" valign=top><a href="admin-detail?id_admin=<s:property value="stt"/>"><b><s:property value="id"/></b></a></td>
                                                                            <td class="listTableRow small" valign=top><s:property value="hoTen"/></td>
                                                                            <td class="listTableRow small" valign=top>
                                                                                <s:if test="permission == 1">Người dùng</s:if>
                                                                                <s:if test="permission == 2">Nhóm kinh doanh</s:if>
                                                                                <s:if test="permission == 0">Quản trị hệ thống</s:if>
                                                                                
                                                                                
                                                                            </td>
                                                                            <td class="listTableRow small" valign=top>
                                                                                <s:if test="status == true">
                                                                                Đang hoạt động
                                                                                </s:if>
                                                                                <s:else>
                                                                                    Dừng hoạt động
                                                                                </s:else>
                                                                        </tr>
                                                                        </s:iterator>


                                                                    </table>
                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% >
                                                                        <tr><td class="small" nowrap align=right><a href="#top">[Quay về đầu trang]</a></td></tr>
                                                                    </table>







                                                                </td>
                                                            </tr>
                                                        </table>



                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                            </div>
                    </td>
                    <td valign="top"><img src="themes/softed/images/showPanelTopRight.gif"></td>
                    </form>
                </tr>
            </tbody>
        </table>
        <div id="tempdiv" style="display:block;position:absolute;left:350px;top:200px;"></div>
        
        <br><br><br>

<!--    Footer-->
<s:include value="footer.jsp"></s:include>
</body>
</html>

