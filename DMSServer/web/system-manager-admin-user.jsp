<%-- 
    Document   : system-manager-admin-user
    Created on : May 16, 2014, 4:06:29 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Thiết lập -  - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">	
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
            <s:param name="page_param" value="'setup'" />
        </s:include>

        <!-- TOOLS -->
        <br>
        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
                        <form action="index.php" method="post" name="EditView" id="form" onsubmit="VtigerJS_DialogBox.block();">
                            <input type='hidden' name='module' value='Users'>
                            <input type='hidden' name='action' value='EditView'>
                            <input type='hidden' name='return_action' value='ListView'>
                            <input type='hidden' name='return_module' value='Users'>
                            <input type='hidden' name='parenttab' value='Settings'>

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
                                                            <td class="settingsTabList" nowrap>
                                                                <a href="index.php?module=Administration&amp;action=index&amp;parenttab=Settings">
                                                                    Kinh doanh
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="settingsTabList" nowrap>
                                                                <a href="index.php?module=Settings&amp;action=listroles&amp;parenttab=Settings">
                                                                    Vai trò
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="settingsTabSelected" nowrap>
                                                                <a href="index.php?module=Settings&amp;action=ListProfiles&amp;parenttab=Settings">
                                                                    Quản trị
                                                                </a>
                                                            </td>
                                                        </tr>


                                                        <tr>
                                                            <td class="settingsTabHeader" nowrap>
                                                                Truy cập
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="settingsTabList" nowrap>
                                                                <a href="index.php?module=Settings&amp;action=ModuleManager&amp;parenttab=Settings">
                                                                    Lịch sử đăng nhập của người sử dụng
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="settingsTabList" nowrap>
                                                                <a href="index.php?module=Settings&amp;action=ModuleManager&amp;parenttab=Settings">
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

                                                        </script>				<!-- DISPLAY -->
                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="settingsSelUITopLine">
                                                            <tr>
                                                                <td width=50 rowspan=2 valign=top><img src="themes/images/ico-users.gif" alt="Người sử dụng" width="48" height="48" border=0 title="Người sử dụng"></td>
                                                                <td class=heading2 valign=bottom><b><a href="index.php?module=Settings&action=index&parenttab=Settings">Thiết lập</a> > Người sử dụng</b></td>
                                                            </tr>
                                                            <tr>
                                                                <td valign=top class="small">Quản lý những người sử dụng có thể truy cập vào hệ thống CRM</td>
                                                            </tr>
                                                        </table>

                                                        <br>
                                                        <table border=0 cellspacing=0 cellpadding=10 width=100% >
                                                            <tr>
                                                                <td>
                                                                    <div id="ListViewContents">
                                                                        <script language="JavaScript" type="text/javascript" src="include/js/ListView.js"></script>
                                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="tableHeading">
                                                                            <tr>
                                                                                <td class="big"><strong>Danh sách người sử dụng</strong></td>
                                                                                <td class="small" align=right>&nbsp;</td>
                                                                            </tr>
                                                                        </table>

                                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="listTableTopButtons">
                                                                            <tr>
                                                                                <td class="small" nowrap align="left">
                                                                                    Hiển thị Bản ghi 1 - 20 trên 21
                                                                                </td>
                                                                                <!-- Page Navigation -->
                                                                                <td nowrap width="30%" align="center">
                                                                                    <table border=0 cellspacing=0 cellpadding=0 class="small">
                                                                                        <tr><td align="right" style="padding: 5px;"><img src="themes/images/start_disabled.gif" border="0" align="absmiddle">&nbsp;<img src="themes/images/previous_disabled.gif" border="0" align="absmiddle">&nbsp;<input class='small' name='pagenum' type='text' value='1'
                                                                                                                                                                                                        style='width: 3em;margin-right: 0.7em;' onchange="getListViewEntries_js('Users', 'parenttab=Settings&start=' + this.value + '');"
                                                                                                                                                                                                        onkeypress="return VT_disableFormSubmit(event);"><span name='Users_listViewCountContainerName' class='small' style='white-space: nowrap;'>trên 2</span><a href="javascript:;" onClick="getListViewEntries_js('Users', 'parenttab=Settings&start=2');" alt="Tiếp" title="Tiếp"><img src="themes/images/next.gif" border="0" align="absmiddle"></a>&nbsp;<a href="javascript:;" onClick="getListViewEntries_js('Users', 'parenttab=Settings&start=2');" alt="Cuối cùng" title="Cuối cùng"><img src="themes/images/end.gif" border="0" align="absmiddle"></a>&nbsp;</td></tr>
                                                                                    </table>
                                                                                </td>
                                                                                <td class=small width="30%" align="right"><input title="Người sử dụng mới [Alt+N]" accessyKey="N" type="submit" name="button" value="Người sử dụng mới" class="crmButton create small"></td>
                                                                            </tr>

                                                                        </table>

                                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="listTable">
                                                                            <tr>
                                                                                <td class="colHeader small" valign=top>#</td>
                                                                                <td class="colHeader small" valign=top>Công cụ</td>
                                                                                <td class="colHeader small" valign=top><a href='javascript:;' onClick='getListViewEntries_js("Users", "parenttab=Settings&order_by=user_name&start=1&sorder=ASC");' class='listFormHeaderLinks'>Mã người sử dụng, Tên và Vai trò</a></td>
                                                                                <td class="colHeader small" valign=top><a href='javascript:;' onClick='getListViewEntries_js("Users", "parenttab=Settings&order_by=email1&start=1&sorder=ASC");' class='listFormHeaderLinks'>Email</a></td>
                                                                                <td class="colHeader small" valign=top><a href='javascript:;' onClick='getListViewEntries_js("Users", "parenttab=Settings&order_by=phone_work&start=1&sorder=ASC");' class='listFormHeaderLinks'>Điện thoại</a></td>
                                                                                <td class="colHeader small" valign=top><a href='javascript:;' onClick='getListViewEntries_js("Users", "parenttab=Settings&order_by=is_admin&start=1&sorder=ASC");' class='listFormHeaderLinks'>Người quản trị</a></td>
                                                                                <td class="colHeader small" valign=top><a href='javascript:;' onClick='getListViewEntries_js("Users", "parenttab=Settings&order_by=status&start=1&sorder=ASC");' class='listFormHeaderLinks'>Trạng thái</a></td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td class="listTableRow small" valign=top>1</td>
                                                                                <td class="listTableRow small" nowrap valign=top><a href="index.php?action=EditView&return_action=ListView&return_module=Users&module=Users&parenttab=Settings&record=5"><img src="themes/images/editfield.gif" alt="Sửa" title="Sửa" border="0"></a>
                                                                                    <img src="themes/images/delete.gif" onclick="deleteUser(this, '5')" border="0"  alt="Xóa" title="Xóa" style="cursor:pointer;"/>
                                                                                </td>
                                                                                <td class="listTableRow small" valign=top><b><a href="index.php?module=Users&action=DetailView&parenttab=Settings&record=5"> thoant <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='user_name' vtmodule='Users' style='display:none;'></span> </a></b><br><a href="index.php?module=Users&action=DetailView&parenttab=Settings&record=5"> <a href="index.php?action=DetailView&module=Users&record=5&parenttab=Settings">Nguyễn Thị Thoa</a> <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='last_name' vtmodule='Users' style='display:none;'></span>  <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='first_name' vtmodule='Users' style='display:none;'></span></a> (<a href="index.php?action=RoleDetailView&module=Settings&parenttab=Settings&roleid=H10">Software Man</a> <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='roleid' vtmodule='Users' style='display:none;'></span>)</td>
                                                                                <td class="listTableRow small" valign=top><a href="javascript:InternalMailer(5,463,'email1','Users','record_id');">sales@hosgroup.com.vn</a> <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='email1' vtmodule='Users' style='display:none;'></span>&nbsp;</td>
                                                                                <td class="listTableRow small" valign=top>0466839286 <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='phone_work' vtmodule='Users' style='display:none;'></span>&nbsp;</td>
                                                                                <td class="listTableRow small" valign=top>off <span type='vtlib_metainfo' vtrecordid='5' vtfieldname='is_admin' vtmodule='Users' style='display:none;'></span>&nbsp;</td>
                                                                                <td class="listTableRow small active" valign=top>Hoạt động</td>


                                                                            </tr>
                                                                            
                                                                        </table>
                                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% >
                                                                            <tr><td class="small" nowrap align=right><a href="#top">[Quay về đầu trang]</a></td></tr>
                                                                        </table>
                                                                    </div>	
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
                </tr>
            </tbody>
        </form>
    </table>

    <div id="tempdiv" style="display:block;position:absolute;left:350px;top:200px;"></div>
    <br><br><br>

    <!--    Footer-->
    <s:include value="footer.jsp"></s:include>
</body>
</html>

