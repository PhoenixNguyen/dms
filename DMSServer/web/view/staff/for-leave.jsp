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
                                                Xin nghỉ phép
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
                        <table class="small reportGenerateTable" align="center" cellpadding="5" cellspacing="0" width="95%" border=0>
                            <tr>
                                <td align=center class=small>
                                    <form id="sub_form" method="get" action="filter-result-forleave">
                                    <table border=0 cellspacing=0 cellpadding=0 width=80%>
                                        <tr>
                                            <td align=left class=small><b>Chọn Giám đốc</b></td><td class=small>&nbsp;</td>
                                            <td align=left class=small><b>Chọn Nhân viên </b></td><td class=small>&nbsp;</td>
                                            <td align=left class=small><b>Ngày bắt đầu </b></td><td class=small>&nbsp;</td>
                                            <td align=left class=small><b>Ngày kết thúc </b>
                                        </tr>
                                        <tr>
                                            <s:push value="pushInfo">
                                            <td align="left" width="20%">
                                                <select name="pushInfo.managerID"  class="small" style="width:98%" onchange="onClickManager(options[selectedIndex].text , 'take');">
                                                    <option value="">--select--</option>
                                                    <s:iterator value="userListGiamDoc" status="index" >
                                                        <s:if test="pushInfo.managerID == userListGiamDoc.get(#index.index)">
                                                            <option value="<s:property />" selected="selected"><s:property /></option>
                                                        </s:if>
                                                        <s:else>
                                                            <option value="<s:property />"><s:property /></option>
                                                        </s:else>
                                                    </s:iterator>
                                                </select>
                                            </td>
                                            <td class=small>&nbsp;</td>
                                            
                                            <td align="left" width="20%">
                                                <select name="pushInfo.staffID" class="small" style="width:98%" onchange="onClickStaff(options[selectedIndex].text , 'take');" id="staff">
                                                    <option value="">--select--</option>
                                                    <s:iterator value="userListStaff" status="index" >
                                                        
                                                        <s:if test="pushInfo.staffID == userListStaff.get(#index.index)">
                                                            <option value="<s:property />" selected="selected"><s:property /></option>
                                                        </s:if>
                                                        <s:else>
                                                            <option value="<s:property />"><s:property /></option>
                                                        </s:else>

                                                        
                                                    </s:iterator>
                                                </select>
                                            </td>
                                            <td class=small>&nbsp;</td>
                                            
                                            </s:push>
                                            <td align=left width="20%">
                                                <table border=0 cellspacing=0 cellpadding=2>
                                                    <tr>
                                                        <s:date format="dd-MM-yyyy" id="dateconverted" name="startDate"/>
                                                        <td align=left><input name="startDate" id="jscal_field_date_start" type="text" size="10" class="importBox" style="width:70px;" value="<s:property value="startDate"/>"></td>
                                                        <td valign=absmiddle align=left>
                                                            <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_date_start">
                                                            <font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                                            <script type="text/javascript">
                                                                Calendar.setup({
                                                                    inputField: "jscal_field_date_start", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_date_start", singleClick: true, step: 1
                                                                });
                                                                
                                                            </script>

                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                            <td align=left class=small>&nbsp;</td>
                                            <td align=left width=20%>
                                                <table border=0 cellspacing=0 cellpadding=2>
                                                    <tr>
                                                        <td align=left><input name="endDate" id="jscal_field_date_end" type="text" size="10" class="importBox" style="width:70px;" value="<s:property value="endDate"/>"></td>
                                                        <td valign=absmiddle align=left><img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_date_end"><font size="1"><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                                            <script type="text/javascript">
                                                                Calendar.setup({
                                                                    inputField: "jscal_field_date_end", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_date_end", singleClick: true, step: 1
                                                                });
                                                            </script>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="center" colspan="8" style="padding:5px"><input name="generatenw" value=" Xem báo cáo " class="crmbutton small create" type="submit" ></td>
                                        </tr>
                                    </table>
                                    </form>
                                </td>
                            </tr>
                        </table>


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
                                                                Xin nghỉ phép
                                                            </span><br>
                                                        </td>
                                                        <td align="right" width="25%">
                                                            <span class="genHeaderGray">Tổng số : <span id='_reportrun_total'><s:property value="forLeaveList.size()"/></span>  Bản ghi</span>
                                                        </td>
                                                    </tr>
                                                    <tr><td id="report_info" align="left" colspan="2">&nbsp;</td></tr>
                                                    <tr><td colspan="2">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="2">

                                                            <table cellpadding="5" cellspacing="0" align="center" class="rptTable" width="80%">
                                                                <tr>
                                                                    <td class='rptCellLabel'>Stt</td>
                                                                    <td class='rptCellLabel'>Mã nhân viên</td>
                                                                    <td class='rptCellLabel'>Tên nhân viên</td>
                                                                    
                                                                    <td class='rptCellLabel'>Ngày</td>
                                                                    <td class='rptCellLabel'>Nội dung</td>
                                                                    <td class='rptCellLabel'>Ngày tạo</td>
                                                                </tr>
                                                                
                                                                
                                                                <s:iterator value="forLeaveList" status="index">
                                                                <s:date id="timeAt" name="timeAt" format="dd-MM-yyyy"/>
                                                                <s:date id="createdTime" name="createdTime" format="dd-MM-yyyy HH:mm:ss"/>
                                                                <tr>
                                                                    <td class='rptData'><s:property value="%{#index.index + 1}"/></td>
                                                                    <td class='rptData'><s:property value="staff.getId()"/></td>
                                                                    <td class='rptData'><s:property value="staff.name"/></td>
                                                                    
                                                                    <td class='rptData'><s:property value="%{timeAt}"/></td>
                                                                    <td class='rptData'><s:property value="content"/></td>
                                                                    <td class='rptData'><s:property value="%{createdTime}"/></td>
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
