<%-- 
    Document   : report-takeorder
    Created on : May 10, 2014, 11:21:27 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Nhân viên - Kế hoạch - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link REL="SHORTCUT ICON" HREF="${pageContext.request.contextPath}/themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("${pageContext.request.contextPath}/themes/softed/style.css");</style>
        <link rel="stylesheet" type="text/css" media="all" href="${pageContext.request.contextPath}/jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { poview/calendar/footer.jspsition:absolute; }</style>
        <![endif]-->

        <!-- End -->
    </head>
    <body leftmargin="0" topmargin="0" marginheight="0" marginwidth="0" class="small">

        <!--    Header & menu-->
        <s:include value="../../header.jsp" >
            <s:param name="page_param" value="'staff'" />
        </s:include>


        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tbody><tr>
                    <td valign="top"><img src="themes//images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">


                        <table class="small reportGenHdr mailClient mailClientBg" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">


                                <tbody>
                                    <tr>
                                        <td style="padding: 10px; text-align: left;" width="70%">
                                            <span class="moduleName">
                                                Kế hoạch
                                            </span>&nbsp;&nbsp;
                                            <!--                                            <input type="button" name="custReport" value="Sửa Báo cáo" class="crmButton small edit" onClick="editReport('20');">-->
                                            <br>
<!--                                            <a href="report" class="reportMnu" style="border-bottom: 0px solid rgb(0, 0, 0);">&lt; Quay lại Nhân viên</a>-->
                                        </td>
                                        <!--                                        <td style="border-left: 2px dotted rgb(109, 109, 109); padding: 10px;" width="30%">
                                                                                    <b>Chọn báo cáo khác : </b><br>
                                                                                    <select name="another_report" class="detailedViewTextBox" onChange="selectReport()">
                                        
                                        
                                                                                        <option value=20 selected>Báo cáo chi tiết Nhập hàng</option>
                                                                                    </select>&nbsp;&nbsp;
                                                                                </td>-->
                                    </tr>
                                </tbody>
                        </table>

                        <!-- Generate Report UI Filter -->
                        


                        <div style="display: block;" id="Generate" align="center">


                            <br>
                            <table align="center" border="0" cellpadding="5" cellspacing="0" width="100%" class="mailSubHeader">
                                <tbody><tr>
<!--                                        <td align="left" nowrap ><input class="crmbutton small create" id="btnExport" name="btnExport" value="Xuất dữ liệu Excel" type="button" onClick="window.location.href='export-takeorder-xls'" title="Xuất dữ liệu Excel" ></td>-->
                                    </tr>
                                </tbody>
                            </table>

                            <table style="border: 1px solid rgb(0, 0, 0);" align="center" cellpadding="0" cellspacing="0" width="100%">
                                <tbody><tr>
                                        <td style="background-repeat: repeat-y;" background="themes//images/report_btn.gif" width="16"></td>

                                        <td style="padding: 5px;" valign="top">
                                            <table cellpadding="0" cellspacing="0" width="100%">
                                                <tbody>
                                                    <tr>
                                                        <td align="left" width="75%">
                                                            <span class="genHeaderGray">
                                                                Kế hoạch công tác tuần
                                                            </span><br>
                                                        </td>
                                                        <td align="right" width="25%">
                                                            <span class="genHeaderGray">Tổng số : <span id='_reportrun_total'><s:property value="calendarList.size()"/></span>  Bản ghi</span>
                                                        </td>
                                                    </tr>
                                                    <tr><td id="report_info" align="left" colspan="2">&nbsp;</td></tr>
                                                    <tr><td colspan="2">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="2">

                                                            <table cellpadding="5" cellspacing="0" align="center" class="rptTable">
                                                                <tr>
                                                                    <td class='rptCellLabel'>Stt</td>
                                                                    <td class='rptCellLabel'>Mã nhân viên</td>
                                                                    <td class='rptCellLabel'>Tên nhân viên</td>
                                                                    <td class='rptCellLabel'>Ngày</td>
                                                                    <td class='rptCellLabel'>Tỉnh thành</td>
                                                                    <td class='rptCellLabel'>Nội dung</td>
                                                                    <td class='rptCellLabel'>Báo cáo</td>
                                                                    <td class='rptCellLabel'>Cộng tác viên</td>
                                                                </tr>
                                                                
                                                                
                                                                <s:iterator value="calendarList" status="index">
                                                                <s:date id="dateconverted" name="calendarDate" format="HH:mm:ss dd-MM-yyyy"/>
                                                                <tr>
                                                                    <td class='rptData'><s:property value="%{#index.index + 1}"/></td>
                                                                    <td class='rptData'><s:property value="staff.getId()"/></td>
                                                                    <td class='rptData'><s:property value="staff.name"/></td>
                                                                    <td class='rptData'><s:property value="%{dateconverted}"/></td>
                                                                    <td class='rptData'><s:property value="province"/></td>
                                                                    <td class='rptData'><s:property value="content"/></td>
                                                                    <td class='rptData'><s:property value="report"/></td>
                                                                    <td class='rptData'><s:property value="contributor"/></td>
                                                                    
                                                                </tr>
                                                                
                                                                </s:iterator>
                                                                
                                                                
                                                            </table>
                                                            <script type='text/javascript' id='__reportrun_directoutput_recordcount_script'>
                                                                if ($('_reportrun_total'))
                                                                    $('_reportrun_total').innerHTML = 3;
                                                            </script>					
                                                        </td>
                                                    </tr>
                                    <tr><td colspan="2">&nbsp;</td></tr>
                                    <tr><td colspan="2">&nbsp;</td></tr>
                                    <tr><td colspan="2">
                                        </td></tr>
                                    <tr><td colspan="2">&nbsp;</td></tr>
                                </tbody>
                            </table>
                    </td>
                    <td style="background-repeat: repeat-y;" background="themes//images/report_btn.gif" width="16"></td>
                </tr>

            </tbody>
        </table>

        <table align="center" border="0" cellpadding="5" cellspacing="0" width="100%" class="mailSubHeader">
            <tbody>
                <tr>
<!--                    <td align="left" nowrap ><input class="crmbutton small create" id="btnExport" name="btnExport" value="Xuất dữ liệu Excel" type="button" onClick="window.location.href='export-takeorder-xls'" title="Xuất dữ liệu Excel" ></td>-->
                </tr>
            </tbody>
        </table>
    </div>
    <br>

</td>
<td valign="top"><img src="themes//images/showPanelTopRight.gif"></td>
</tr>
</table>




<br><br><br>

<!--    Footer-->
<s:include value="../../footer.jsp"></s:include>


</body>
</html>

