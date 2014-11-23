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
        <title>admin - Phân tích - Báo cáo - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'report'" />
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
                                            Báo cáo Chăm sóc khách hàng
                                        </span>&nbsp;&nbsp;
                                        <!--                                            <input type="button" name="custReport" value="Sửa Báo cáo" class="crmButton small edit" onClick="editReport('20');">-->
                                        <br>
                                        <!--                                            <a href="report" class="reportMnu" style="border-bottom: 0px solid rgb(0, 0, 0);">&lt; Quay lại Báo cáo</a>-->
                                    </td>
                                </tr>
                            </tbody>
                        </table>

                        <!-- Generate Report UI Filter -->
                        <table class="small reportGenerateTable" align="center" cellpadding="5" cellspacing="0" width="95%" border=0>
                            <tr>
                                <td align=center class=small>
                                    <form id="sub_form" name="filterForm"  method="get" action="filter-result-staff-history">
                                        <table border=0 cellspacing=0 cellpadding=0 width=80%>
                                            <tr>
                                                <td align=left class=small><b>Chọn Giám đốc</b></td><td class=small>&nbsp;</td>
                                                <td align=left class=small><b>Chọn Nhân viên </b></td><td class=small>&nbsp;</td>
                                                <td align=left class=small><b>Chọn Khách hàng </b></td><td class=small>&nbsp;</td>
                                                <td align=left class=small><b>Ngày bắt đầu </b></td><td class=small>&nbsp;</td>
                                                <td align=left class=small><b>Ngày kết thúc </b>
                                            </tr>
                                            <tr>
                                                <s:push value="pushInfo">
                                                    <td align="left" width="20%">
                                                        <select name="pushInfo.managerID"  class="small" style="width:98%" onchange="onClickManager(options[selectedIndex].text, 'take');">
                                                            <option value="--select--">--select--</option>
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
                                                        <select name="pushInfo.staffID" class="small" style="width:98%" onchange="onClickStaff(options[selectedIndex].text, 'take');" id="staff">
                                                            <option value="--select--">--select--</option>
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
                                                    <td align="left" width="20%">
                                                        <select name="pushInfo.customerID" class="small" style="width:98%" onchange="onClickCustomer(options[selectedIndex].text, 'take');" id="customer">
                                                            <option value="--select--">--select--</option>
                                                            <s:iterator value="userListCustomer" status="index" >
                                                                <s:if test="pushInfo.customerID == userListCustomer.get(#index.index)">
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
                                                <td align="center" colspan="8" style="padding:5px"><input name="generatenw" value=" Tạo báo cáo " class="crmbutton small create" type="submit" ></td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>

                        <script type="text/javascript">
                            $(document).ready(function() {
                                var startDate = '<s:property value="%{#parameters.startDate}"/>';
                                var endDate = '<s:property value="%{#parameters.startDate}"/>';

                                var todayDate = new Date();
                                var today = todayDate.toString('dd-MM-yyyy');
                                if (startDate == '')
                                    $('form[name=filterForm] input[name=startDate]').val(today);
                                if (startDate == '')
                                    $('form[name=filterForm] input[name=endDate]').val(today);

                            });
                        </script>  
                        <div style="display: block;" id="Generate" align="center">


                            <br>
                            <table align="center" border="0" cellpadding="5" cellspacing="0" width="100%" class="mailSubHeader" >
                                <tbody><tr>
                                        <td align="left" nowrap >
                                            <input class="crmbutton small create" id="btnExport" name="btnExport" value="Xuất dữ liệu Excel" type="button" onClick="location.href='export-staff-history'" title="Xuất dữ liệu Excel" >
                                        </td>
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
                                                                Báo cáo chăm sóc khách hàng
                                                            </span><br>
                                                        </td>
                                                        <td align="right" width="25%">
                                                            <span class="genHeaderGray">Tổng số : <span id='_reportrun_total'><s:property value="listStaffHistory.size()"/></span>  Bản ghi</span>
                                                        </td>
                                                    </tr>
                                                    <tr><td id="report_info" align="left" colspan="2">&nbsp;</td></tr>
                                                    <tr><td colspan="2">&nbsp;</td></tr>
                                                    <tr>
                                                        <td colspan="2">

                                                            <table cellpadding="5" cellspacing="0" align="center" class="rptTable" width="80%">
                                                                <tr>
                                                                    <td class='rptCellLabel'>Nhân viên</td>
                                                                    <td class='rptCellLabel'>Mã Khách hàng</td>
                                                                    <td class='rptCellLabel'>Tên khách hàng</td>
                                                                    <td class='rptCellLabel'>Thời gian</td>
                                                                    <td class='rptCellLabel'>Ghi chú</td>

                                                                </tr>


                                                                <s:iterator value="listStaffHistory" status="index">
                                                                    <s:date id="dateconverted" name="startTime" format="HH:mm:ss dd-MM-yyyy"/>
                                                                    <tr>
                                                                        <td class='rptData'><s:property value="staff"/></td>
                                                                        <td class='rptData'><s:property value="customer"/></td>

                                                                        <s:iterator value="customerList" status="index">
                                                                            <s:if test="maDoiTuong == customer" >
                                                                                <td class='rptData'><s:property value="doiTuong"/></td>
                                                                            </s:if>
                                                                        </s:iterator>
                                                                        <td class='rptData'><s:property value="%{dateconverted}"/></td>
                                                                        <td class='rptData'><s:property value="note"/></td>

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

                            <table align="center" border="0" cellpadding="5" cellspacing="0" width="100%" class="mailSubHeader" >
                                <tbody><tr>
                                        <td align="left" nowrap >
                                            <input class="crmbutton small create" id="btnExport" name="btnExport" value="Xuất dữ liệu Excel" type="button" onClick="location.href='export-staff-history'" title="Xuất dữ liệu Excel" >
                                        </td>
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
        <s:include value="footer.jsp"></s:include>


    </body>
</html>

