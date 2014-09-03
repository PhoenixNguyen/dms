<%-- 
    Document   : system-manager-admin-history
    Created on : May 16, 2014, 3:53:36 AM
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
    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">

        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'setup'" />
        </s:include>

        <!-- TOOLS -->

        <!-- divs for asterisk integration :: end--><!-- startscrmprint -->
        <script language="JAVASCRIPT" type="text/javascript" src="include/js/smoothscroll.js"></script>
        <script src="include/scriptaculous/prototype.js" type="text/javascript"></script>
        <script src="include/scriptaculous/scriptaculous.js" type="text/javascript"></script>
        <script language="javascript" type="text/javascript" src="include/js/general.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/dtlviewajax.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/menu.js"></script>

        <br>
        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
                        
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
                                                                    <a href="system-manager">
                                                                        Quản trị viên
                                                                    </a>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td class="settingsTabSelected" nowrap>
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

                                                        </script>				<!-- DISPLAY -->
                                                        <table border=0 cellspacing=0 cellpadding=5 width=100% class="settingsSelUITopLine">
                                                            <tr>
                                                                <td width=50 rowspan=2 valign=top><img src="themes/images/set-IcoLoginHistory.gif" alt="Theo dõi tài khoản" width="48" height="48" border=0 title="Lịch sử đăng nhập của người sử dụng"></td>
                                                                <td class=heading2 valign=bottom><b><a href="">Lịch sử đăng nhập của người sử dụng</a>  </b></td>
                                                            </tr>
                                                            <tr>
                                                                <td valign=top class="small">Hiện lịch sử đăng nhập của người dùng</td>
                                                            </tr>
                                                        </table>

                                                        <br>
                                                        <table border=0 cellspacing=0 cellpadding=10 width=100% >
                                                            <tr>
                                                                <td>

                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% class="tableHeading">
                                                                        <tr>
                                                                            <td class="big" height="30px;"><strong>Lịch sử đăng nhập của người sử dụng</strong></td>
                                                                            <td class="small" align="left">&nbsp;</td>
                                                                        </tr>
                                                                    </table>

                                                                    <table border=0 cellspacing=0 cellpadding=0 width=100% class="listRow">
                                                                        <tr>
                                                                            <td class="small" valign=top >
                                                                                <form action="display-user-history" method="get">
                                                                                    <table width="100%"  border="0" cellspacing="0" cellpadding="5">

                                                                                        <tr valign="top">
                                                                                            <td nowrap width="18%" class="small cellLabel"><strong>Chọn người sử dụng</strong></td>
                                                                                            <td class="small cellText">
                                                                                                <select name="userID" id="user_list" onchange="fetchlogin_js();">

                                                                                                    <option value="none" selected="true">--Chưa chọn--</option>
                                                                                                    <s:iterator value="userStringList" status="index">
                                                                                                        <s:if test="userStringList.get(#index.index) == userID">
                                                                                                            <option value="<s:property />" selected=""><s:property /></option>
                                                                                                        </s:if>
                                                                                                        <s:else>
                                                                                                            <option value="<s:property />" ><s:property /></option>
                                                                                                        </s:else>
                                                                                                    </s:iterator>

                                                                                                </select>	
                                                                                            </td>
                                                                                            <td nowrap width="50%" class="small cellLabel">
                                                                                                <input value="Chấp nhận" type="submit" >
                                                                                            </td>
                                                                                        </tr>

                                                                                    </table>
                                                                                </form>
                                                                            </td>
                                                                        </tr>
                                                                        <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                            <tr>
                                                                                <td class="big">	
                                                                                    <strong>Dấu vết đăng nhập</strong>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                        <table border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                            <tbody><tr><td align="left"><div id="login_history_cont">
                                                                                            <table width="100%" border="0">
                                                                                                <tbody><tr>
                                                                                                        <td align="right">
                                                                                                            Tổng truy cập <s:property value="historyList.size()"/> lần
                                                                                                        </td>
                                                                                                        <!--                                                                                                        <td align="right" style="padding: 5px;"><img src="themes/images/start_disabled.gif" border="0" align="absmiddle">&nbsp;<img src="themes/images/previous_disabled.gif" border="0" align="absmiddle">&nbsp;<input class="small" name="pagenum" type="text" value="1" style="width: 3em;margin-right: 0.7em;" onchange="getListViewEntries_js('Users', 'parenttab=&amp;start=' + this.value + '');" onkeypress="return VT_disableFormSubmit(event);">
                                                                                                                                                                                                        <span name="Users_listViewCountContainerName" class="small" style="white-space: nowrap;">trên 210</span><a href="javascript:;" onclick="getListViewEntries_js('Users', 'parenttab=&amp;start=2');" alt="Tiếp" title="Tiếp"><img src="themes/images/next.gif" border="0" align="absmiddle"></a>&nbsp;<a href="javascript:;" onclick="getListViewEntries_js('Users', 'parenttab=&amp;start=210');" alt="Cuối cùng" title="Cuối cùng"><img src="themes/images/end.gif" border="0" align="absmiddle"></a>&nbsp;
                                                                                                                                                                                                        </td>-->
                                                                                                    </tr>
                                                                                                </tbody></table>
                                                                                            <table border="0" cellspacing="1" cellpadding="3" width="100%" style="background-color:#cccccc;" class="small">

                                                                                                <tbody>
                                                                                                    <tr style="background-color:#efefef">
                                                                                                        <td class="lvtCol">Tên người dùng</td>
                                                                                                        <td class="lvtCol">IP của người dùng</td>
                                                                                                        <td class="lvtCol">Thời điểm đăng nhập</td>
                                                                                                        <td class="lvtCol">Thời điểm thoát</td>
                                                                                                        <td class="lvtCol">Ghi chú</td>
                                                                                                    </tr>
                                                                                                    <s:iterator value="historyList" status="index">
                                                                                                    <tr bgcolor="white" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" class="lvtColData">
                                                                                                        <td><s:property value="user"/></td>
                                                                                                        <td><s:property value="ip"/></td>
                                                                                                        <td><s:property value="login"/></td>
                                                                                                        <td><s:property value="logout"/></td>
                                                                                                        <td><s:property value="note"/></td>
                                                                                                    </tr>
                                                                                                    </s:iterator>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>

                                                                                    </td>
                                                                                    <td>

                                                                                    </td>
                                                                                </tr>	
                                                                            </tbody>
                                                                        </table>	
                                                                        <br>	
                                                                    </table>	
                                                                    <table border=0 cellspacing=0 cellpadding=5 width=100% >
                                                                        <tr>
                                                                            <td class="small" nowrap align=right><a href="#top">[Quay về đầu trang]</a></td>
                                                                        </tr>
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
                </tr>
            </tbody>
        </form>
    </table>

    <br><br><br>

    <!--    Footer-->
    <s:include value="footer.jsp"></s:include>
</body>
</html>

