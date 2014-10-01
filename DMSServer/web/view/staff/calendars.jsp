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
                                                Kế hoạch công tác tuần
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
                                    <form id="sub_form" method="get" action="calendar-filter-result">
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
                            <table align="center" border="0" cellpadding="5" cellspacing="0" width="100%" class="mailSubHeader" >
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
                                                            
                                                            <table cellpadding="5" cellspacing="0" align="center" class="rptTable" width="80%">
                                                                <tr>
                                                                    <td class='rptCellLabel'>Stt</td>
                                                                    <td class='rptCellLabel'>Mã nhân viên</td>
                                                                    <td class='rptCellLabel'>Tên nhân viên</td>
                                                                    <td class='rptCellLabel'>Ngày</td>
                                                                    <td class='rptCellLabel'>Tỉnh thành</td>
                                                                    <td class='rptCellLabel'>Nội dung</td>
                                                                    <td class='rptCellLabel'>Báo cáo</td>
                                                                    <td class='rptCellLabel'>Cộng tác viên</td>
                                                                    
                                                                    <td class='rptCellLabel'>Trạng thái</td>
                                                                    <td class='rptCellLabel'>Hành động</td>
                                                                </tr>
                                                                
                                                                
                                                                <s:iterator value="calendarList" status="index">
                                                                <s:date id="dateconverted" name="calendarDate" format="dd-MM-yyyy"/>
                                                                <s:date id="createdTimeConverted" name="createdTime" format="dd-MM-yyyy HH:mm:ss"/>
                                                                <tr>
                                                                    <td class='rptData'><s:property value="%{#index.index + 1}"/></td>
                                                                    <td class='rptData'>${staff.id}</td>
                                                                    <td class='rptData'><s:property value="staff.name"/></td>
                                                                    <td class='rptData'><s:property value="%{dateconverted}"/></td>
                                                                    <td class='rptData'><s:property value="province"/></td>
                                                                    <td class='rptData'><s:property value="content"/></td>
                                                                    <td class='rptData'><s:property value="report"/></td>
                                                                    <td class='rptData'><s:property value="contributor"/></td>
                                                                    
                                                                    
                                                                    <td class='rptData' style="text-align: center">
                                                                        <s:if test="status == 0">
                                                                            <img src="${pageContext.request.contextPath}/themes/images/exclamation.png" title="Khởi tạo" >
                                                                        </s:if>
                                                                        <s:if test="status == 1">
                                                                            <img src="${pageContext.request.contextPath}/themes/images/request.png" title="Đã đề nghị" >
                                                                        </s:if>
                                                                        <s:if test="status == 2">
                                                                            <img src="${pageContext.request.contextPath}/themes/images/completed.png" title="Hoàn thành" >
                                                                        </s:if>
                                                                    </td>
                                                                    
                                                                    <td class='rptData'>
                                                                        <a href="#edit_calendar_popup" class="edit_calendar_popup" rel="leanModal" title="Sửa"
                                                                           id="${stt}" staffID="${staff.id}" staffName="${staff.name}" date="${dateconverted}" province="${province}" content="${content}"
                                                                           contributor="${contributor}" support="${support}" mission="${mission}" report="${report}" createdTime="${createdTimeConverted}"
                                                                           status="${status}"
                                                                           >
                                                                            <img src="${pageContext.request.contextPath}/themes/images/edit.png" title="" >
                                                                        </a>
                                                                        <a href="javascript:void(0)" class="delete_popup" id="${stt}" status="${status}" title="Xóa">
                                                                            <img src="${pageContext.request.contextPath}/themes/images/delete.png" title="" >
                                                                        </a>
                                                                        
                                                                    </td>
                                                                </tr>
                                                                
                                                                </s:iterator>
                                                                
                                                                
                                                            </table>
                                                            <script type="text/javascript">
                                                                $(document).ready(function(){
                                                                    //$(".edit_calendar_popup").leanModal();
                                                                    $('a[rel*=leanModal]').leanModal({ top : 200, closeButton: ".modal_close" });	
                                                                    
                                                                    $('.edit_calendar_popup').live('click', function(){
                                                                       //alert($(this).attr('createdTime')) ;
                                                                       //$('form[name=edit_calendar]').trigger('reset');
                                                                       
                                                                       $('form[name=edit_calendar] .createdTime').html($(this).attr('createdTime'));
                                                                       $('form[name=edit_calendar] .staffId').html($(this).attr('staffId'));
                                                                       $('form[name=edit_calendar] .staffName').html($(this).attr('staffName'));
                                                                       $('form[name=edit_calendar] .calendarDate').html($(this).attr('date'));
                                                                       $('form[name=edit_calendar] .province').html($(this).attr('province'));
                                                                       $('form[name=edit_calendar] .content').html($(this).attr('content'));
                                                                       
                                                                       $('form[name=edit_calendar] input[name=id]').val($(this).attr('id'));
                                                                       
                                                                       $('form[name=edit_calendar] input[name=contributor]').val($(this).attr('contributor'));//
                                                                       $('form[name=edit_calendar] input[name=support]').val($(this).attr('support'));
                                                                       $('form[name=edit_calendar] input[name=mission]').val($(this).attr('mission'));
                                                                       $('form[name=edit_calendar] textarea[name=report]').val($(this).attr('report'));
                                                                       $('form[name=edit_calendar] select[name=status]').val($(this).attr('status'));
                                                                       
                                                                       $('form[name=edit_calendar] .action').html('Cập nhật &raquo;');
                                                                       $('form[name=edit_calendar] button').prop('type', 'submit');
                                                                       $('form[name=edit_calendar] .action').attr('onclick', null);
                                                                       
                                                                       if($(this).attr('status') == 2){
                                                                            $('form[name=edit_calendar] .action').html('Đóng &raquo;');
                                                                            $('form[name=edit_calendar] button').prop('type', 'button');
                                                                            $('form[name=edit_calendar] .action').attr('onclick', '$("#edit_calendar_popup .modal_close").click();');
                                                                        }
                                                                    });
                                                                    
                                                                    $('form[name=edit_calendar]').submit(function(){
                                                                        if(!confirm('Bạn chắc chắn muốn cập nhật'))
                                                                            return;
                                                                        $.ajax({
                                                                           url: $(this).attr('action'),
                                                                           type: 'POST',
                                                                           data : $(this).serializeArray(),
                                                                           
                                                                           success: function (data) {
                                                                               alert(data);
                                                                               if(data.indexOf('thành công') !== -1){
                                                                                   location.reload();
                                                                               }
                                                                           }
                                                                           
                                                                        });
                                                                        return false;
                                                                    });
                                                                    
                                                                    $('.delete_popup').live('click', function(){
                                                                        var id = $(this).attr('id');
                                                                        
                                                                        if($(this).attr('status') == 2){
                                                                            alert('Không thể xóa lịch làm việc, lịch này đã hoàn thành.');
                                                                            return;
                                                                        }
                                                                        
                                                                        if(!confirm('Bạn chắc chắn muốn xóa!'))
                                                                            return;
                                                                        
                                                                        $.ajax({
                                                                           url: 'delete-calendar-ajax',
                                                                           type: 'POST',
                                                                           data : 'id=' + id,
                                                                           
                                                                           success: function (data) {
                                                                               alert(data);
                                                                               if(data.indexOf('thành công') !== -1){
                                                                                   location.reload();
                                                                               }
                                                                           }
                                                                           
                                                                        });
                                                                        return false;
                                                                    });
                                                                });
                                                            </script>    
                                                            <style>
                                                                
                                                                #edit_calendar_popup{
                                                                    width: 804px;
                                                                    padding-bottom: 2px;

                                                                    display:none;


                                                                    background: #FFF;
                                                                    border-radius: 5px; -moz-border-radius: 5px; -webkit-border-radius: 5px;
                                                                    box-shadow: 0px 0px 4px rgba(0,0,0,0.7); -webkit-box-shadow: 0 0 4px rgba(0,0,0,0.7); -moz-box-shadow: 0 0px 4px rgba(0,0,0,0.7);

                                                                }
                                                            </style>
                                                            
                                                            <div id="edit_calendar_popup">
                                                                <div id="popup-header">
                                                                    <h2>Cập nhật lịch công tác</h2>
                                                                    <a class="modal_close" href="#"></a>
                                                                </div>
                                                                <form method="post" action="edit-calendar-ajax" name="edit_calendar">
                                                                    <input type="hidden" name="id" value=""/>
                                                                    <table width="100%">
                                                                        <tr>
                                                                            <td style="width: 35%; vertical-align: top" >
                                                                                <div id="popup-body">
                                                                                    <label for="">Ngày lập</label>
                                                                                    <label class="createdTime" for=""></label>
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Mã nhân viên</label>
                                                                                    <label class="staffId" for="">ba_dinh</label>
                                                                                </div>
                                                                                <div id="popup-body">
                                                                                    <label for="">Tên nhân viên</label>
                                                                                    <label class="staffName" for="">Lương Quốc</label>
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Lịch ngày</label>
                                                                                    <label class="calendarDate" for="">26-04-2014</label>
                                                                                </div>
                                                                                <div id="popup-body">
                                                                                    <label for="">Tỉnh thành</label>
                                                                                    <label class="province" for="">Hà Nội</label>
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Nội dung</label>
    <!--                                                                                <label for="">Đưa đón khách hàng đi du lịch tại hạ long</label>-->
                                                                                    <span class="content" style="text-align: left; color: #222; font-size: 1.3em; padding-top: 8px; display: block; background-color: #F7F7F7; float: left;">
                                                                                       Đưa đón khách hàng đi du lịch tại hạ long Đưa đón khách hàng đi du lịch tại hạ long
                                                                                    </span>
                                                                                </div>
                                                                            </td>
                                                                            <td style="vertical-align: top">
                                                                                <div id="popup-body">
                                                                                    <label for="">Cộng tác viên</label>
                                                                                    <input name="contributor" type="text" />
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Hỗ trợ</label>
                                                                                    <input name="support" type="text" />
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Công tác</label>
                                                                                    <input name="mission" type="text" />
                                                                                </div>

                                                                                <div id="popup-body">
                                                                                    <label for="">Báo cáo</label>
                                                                                    <textArea name="report" cols="5" rows="5" ></textarea>
                                                                                </div>
                                                                                
                                                                                <div id="popup-body">
                                                                                    <label for="">Trạng thái</label>
                                                                                    <select name="status">
                                                                                        <option value="0">Khởi tạo</option>
                                                                                        <option value="1">Đề nghị</option>
                                                                                        <option value="2">Hoàn thành</option>
                                                                                    </select>
                                                                                </div>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                
                                                                <div id="popup-footer">
                                                                    <button class="action" type="submit" >Cập nhật &raquo;</button>
                                                                </div>
                                                                    
                                                                </form>
                                                            </div>
                                                            
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

