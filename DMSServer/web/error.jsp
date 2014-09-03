<%-- 
    Document   : home
    Created on : Apr 9, 2014, 10:11:04 PM
    Author     : HP
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html>

<html>
    <head>
        <title>admin - Trang báo lỗi - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
        <!--        <link rel="stylesheet" type="text/css" media="all" href="themes/softed/style.css">-->
        <link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { position:absolute; }</style>
        <![endif]-->

        <!-- End -->

    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">
        <a name="top"></a>
        <!-- header -->
        <!-- header-vtiger crm name & RSS -->
        <script language="JavaScript" type="text/javascript" src="include/js/json.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/general.js"></script>
        <!-- vtlib customization: Javascript hook --> 
        <script language="JavaScript" type="text/javascript" src="include/js/vtlib.js"></script>
        <!-- END -->
        <script language="JavaScript" type="text/javascript" src="include/js/vn.lang.js?"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/QuickCreate.js"></script>
        <script language="javascript" type="text/javascript" src="include/scriptaculous/prototype.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/menu.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/calculator/calc.js"></script>
        <script language="JavaScript" type="text/javascript" src="modules/Calendar/script.js"></script>
        <script language="javascript" type="text/javascript" src="include/scriptaculous/dom-drag.js"></script>
        <script language="JavaScript" type="text/javascript" src="include/js/notificationPopup.js"></script>
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>
        <script type="text/javascript" src="jscalendar/lang/calendar-vn.js"></script>

        <!-- asterisk Integration -->
        <!-- END -->

        <!-- Custom Header Script -->
        <script type="text/javascript" src="modules/Tooltip/TooltipHeaderScript.js"></script>
        <script type="text/javascript" src="modules/SMSNotifier/SMSNotifierCommon.js"></script>
        <script type="text/javascript" src="modules/ModComments/ModCommentsCommon.js"></script>
        <!-- END -->

        <!--    Header & menu-->
        <s:include value="header.jsp" >
            <s:param name="page_param" value="'home'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Báo lỗi<a class="hdrLink" href=""></a></td>
                    <td width="100%" nowrap="">


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

        <table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                    <td class="showPanelBg" valign="top" width="100%" style="padding:10px;">
                        <!-- SIMPLE SEARCH -->
                                       

                        <!-- PUBLIC CONTENTS STARTS-->
                        <div id="ListViewContents" class="small" style="width:100%;">
                            <script language="JavaScript" type="text/javascript" src="include/js/ListView.js"></script>
                            <form name="massdelete" method="POST" id="massdelete" onsubmit="VtigerJS_DialogBox.block();">
                                <input name="search_url" id="search_url" type="hidden" value="">

                                <!-- List View Master Holder starts -->
                                <table border="0" cellspacing="1" cellpadding="0" width="100%" class="lvtBg" style="font-size: 15px;">
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
                                                                    Rất tiếc nội dung yêu cầu không tồn tại hoặc đã bị xóa!
                                                                </tr>
                                                                
                                                                <!-- Table Contents -->
                                                                


                                                            </tbody>
                                                        </table>
                                                        <br>
                                                        <table border="0" cellspacing="1" cellpadding="3" width="100%" height="400px" class="lvt small">
                                                            <!-- Table Headers -->
                                                            
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
        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>


    </body>
</html>

