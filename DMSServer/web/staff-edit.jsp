<%-- 
    Document   : add-customers
    Created on : Apr 10, 2014, 12:28:22 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>admin - Nhân viên - Sửa - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'staff'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Nhân viên &gt; <a class="hdrLink" href="">Nhân viên</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-staff"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mói Nhân viên..." title="Tạo mói Nhân viên..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-staff"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Nhân viên" title="Nhập dữ liệu Nhân viên" border="0"></a></td>  
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
                                                    

                                                </tr>
                                            </tbody></table>  
                                    </td>
                                    <td style="width:20px;">&nbsp;</td>
                                    <td class="small">
                                        <!-- All Menu -->
                                        <table border="0" cellspacing="0" cellpadding="5">
                                            <tbody>
                                                <tr>
                                                    <!--                                            <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                                                                                <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>-->
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr><td style="height:2px"></td></tr>
            </tbody>
        </table>                                

        <!-- END TOOLS -->


        <!-- TAO MOI KH -->

        <table border=0 cellspacing=0 cellpadding=0 width=98% align=center>
            <tr>
                <td valign=top>
                    <img src="themes/softed/images/showPanelTopLeft.gif">
                </td>
                <td class="showPanelBg" valign=top width=100%>
                    <div class="small" style="padding:20px">
                        <span class="lvtHeaderText">Sửa Nhân viên</span> <br>
                        <hr noshade size=1>
                        <br> 
                        <form name="EditView" method="POST" action="update-staff" id="sub_form" onsubmit="">

                            <input type="hidden" name="staff.stt" value="<s:property value="staff.stt"/>">

                            <table border=0 cellspacing=0 cellpadding=0 width=95% align=center>
                                <tr>
                                    <td>
                                        <table border=0 cellspacing=0 cellpadding=3 width=100% class="small">
                                            <tr>
                                                <td class="dvtTabCache" style="width:10px" nowrap>&nbsp;</td>
                                                <td width=75 style="width:15%" align="center" nowrap class="dvtSelectedCell" id="bi" onclick="fnLoadValues('bi', 'mi', 'basicTab', 'moreTab', 'normal', 'Accounts')"><b>Thông tin</b></td>
                                                <!--                                                <td class="dvtUnSelectedCell" style="width: 100px;" align="center" nowrap id="mi" onclick="fnLoadValues('mi', 'bi', 'moreTab', 'basicTab', 'normal', 'Accounts')"><b>Thêm Thông tin </b></td>-->
                                                <td class="dvtTabCache" style="width:65%" nowrap>&nbsp;</td>
                                            <tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign=top align=left >

                                        <!-- Basic Information Tab Opened -->
                                        <div id="basicTab">

                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="dvtContentSpace">
                                                <tr>
                                                    <td align=left>
                                                        <!-- content cache -->

                                                        <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                                            <tr>
                                                                <td id ="autocom"></td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding:10px">
                                                                    <!-- General details -->
                                                                    <table border=0 cellspacing=0 cellpadding=0 width="100%" class="small">
                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="
                                                                                        if(validate(this.form)){
                                                                                            //alert('hello');
                                                                                            document.getElementById('sub_form').submit();
                                                                                        }
                                                                                        else {
                                                                                            //alert('hello2');
                                                                                            return false;
                                                                                        }
                                                                                           
                                                                                           " type="button" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                </div>
                                                                            </td>
                                                                        </tr>

                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin Nhân viên</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Here we should include the uitype handlings-->


                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">
                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Tên Nhân viên 			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="staff.name" tabindex="" value="<s:property value="staff.name"/>" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                            <!-- Non Editable field, only configured value will be loaded -->

                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red">*</font>Mã số Nhân viên </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input readonly="" type="text" name="staff.id" tabindex="" value="<s:property value="staff.id"/>" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red">*</font>Mật khẩu			
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                &nbsp;&nbsp;
                                                                                <input  style="width:74%;" class = 'detailedViewTextBox' id="pword" type="password" tabindex="" name="pw" style="border:1px solid #bababa;" size="27" onFocus="this.className = 'detailedViewTextBoxOn'"onBlur="this.className = 'detailedViewTextBox'" onkeyup="validateUrl('website');" value="<s:property value="staff.pw"/>">
                                                                            </td>
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red">*</font>Quyền hạn	
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <!--                                                                        <input type="radio" tabindex="" name="assigntype" checked value="U" onclick="toggleAssignType(this.value)" >&nbsp;Người dùng
                                                                                                                                                        <input type="radio" name="assigntype"  value="T" onclick="toggleAssignType(this.value)">&nbsp;Nhóm-->
                                                                                <span id="assign_user" style="display:block">
                                                                                    <select name="staff.permission" class="small" >
                                                                                        <s:if test="staff.permission == 1">
                                                                                            <option value="1" selected >Quản lý</option>
                                                                                            <option value="2" >Nhân viên bán hàng</option>
                                                                                            <option value="3" >Nhân viên cập nhật vị trí</option>
                                                                                        </s:if>
                                                                                        <s:if test="staff.permission == 2">
                                                                                            <option value="1"  >Quản lý</option>
                                                                                            <option value="2" selected>Nhân viên bán hàng</option>
                                                                                            <option value="3" >Nhân viên cập nhật vị trí</option>
                                                                                        </s:if>
                                                                                        <s:if test="staff.permission == 3">
                                                                                            <option value="1"  >Quản lý</option>
                                                                                            <option value="2" >Nhân viên bán hàng</option>
                                                                                            <option value="3" selected>Nhân viên cập nhật vị trí</option>
                                                                                        </s:if>


                                                                                    </select>
                                                                                </span>


                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px">
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Điện thoại </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="staff.phone" tabindex="" id ="tickersymbol" value="<s:property value="staff.phone"/>" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn';" onBlur="this.className = 'detailedViewTextBox';
                                                                                        sensex_info()">
                                                                                <span id="vtbusy_info" style="display:none;">
                                                                                    <img src="themes/images/vtbusy.gif" border="0"></span>
                                                                            </td>
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Chức vụ </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" tabindex="" name="staff.job" id ="fax" value="<s:property value="staff.job"/>" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>

                                                                        <tr style="height:25px">
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red">*</font>Người quản lý		
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <!--                                                                        <input type="radio" tabindex="" name="assigntype" checked value="U" onclick="toggleAssignType(this.value)" >&nbsp;Người dùng
                                                                                                                                                        <input type="radio" name="assigntype"  value="T" onclick="toggleAssignType(this.value)">&nbsp;Nhóm-->
                                                                                <span id="assign_user" style="display:block">
                                                                                    <select name="staff.manager" class="small" >
                                                                                        <s:iterator value="usersList" status="index">
                                                                                            <s:if test="usersList.get(#index.index) == staff.manager">
                                                                                                <option value="<s:property value="usersList.get(#index.index)"/>" selected><s:property value="usersList.get(#index.index)"/></option>
                                                                                            </s:if>
                                                                                            <s:else>
                                                                                                <option value="<s:property value="usersList.get(#index.index)"/>" ><s:property value="usersList.get(#index.index)"/></option>
                                                                                            </s:else>
                                                                                        </s:iterator>

                                                                                    </select>
                                                                                </span>

                                                                                <span id="assign_team" style="display:none">
                                                                                    <select name="assigned_group_id" class="small">';
                                                                                        <option value="2" >Đội sales</option>
                                                                                        <option value="4" >Nh&oacute;m hỗ trợ</option>
                                                                                        <option value="7" >Nh&oacute;m kỹ thuật</option>
                                                                                        <option value="3" >Nh&oacute;m Marketing</option>
                                                                                    </select>
                                                                                </span>
                                                                            </td>

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Trạng thái			
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <s:if test="staff.status == true">
                                                                                    <input type="checkbox" tabindex="" name="staff.status" checked  value="true">
                                                                                </s:if>
                                                                                <s:else>
                                                                                    <input type="checkbox" tabindex="" name="staff.status"  value="true" >
                                                                                </s:else>
                                                                                

                                                                            </td>

                                                                        </tr>

                                                                        <tr style="height:25px">
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Ngày gia nhập	
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">

                                                                                <s:date format="dd-MM-yyyy" id="dateconverted" name="staff.date"/>
                                                                                <input name="startDate"  tabindex="" id="jscal_field_cf_607" type="text" style="border:1px solid #bababa;" size="11" maxlength="10" value="<s:property value="%{dateconverted}"/>">
                                                                                <img src="themes/softed/images/btnL3Calendar.gif" id="jscal_trigger_cf_607">
                                                                                <br><font size=1><em old="(yyyy-mm-dd)">(dd-mm-yyyy)</em></font>
                                                                                <script type="text/javascript" id='massedit_calendar_cf_607'>
                                                                                    Calendar.setup({
                                                                                        inputField: "jscal_field_cf_607", ifFormat: "%d-%m-%Y", showsTime: false, button: "jscal_trigger_cf_607", singleClick: true, step: 1
                                                                                    })
                                                                                </script>
                                                                            </td>

                                                                        </tr>

                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        <tr>
                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                <b>Thông tin địa chỉ</b></td>
                                                                            
                                                                        </tr>
                                                                        <!-- Here we should include the uitype handlings-->

                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">
                                                                            <!-- In Add Comment are we should not display anything -->
                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font> 
                                                                                Địa chỉ 			
                                                                            </td>
                                                                            <td colspan=3>
                                                                                <textarea class="detailedViewTextBox" tabindex="" onFocus="this.className = 'detailedViewTextBoxOn'" name="staff.adress"  onBlur="this.className = 'detailedViewTextBox'" cols="90" rows="8"><s:property value="staff.adress"/></textarea>
                                                                            </td>
                                                                        </tr>


                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" LANGUAGE=javascript onclick="
                                                                                        //alert('hello');
                                                                                        if(validate(this.form)){
                                                                                            //alert('hello');
                                                                                            document.getElementById('sub_form').submit();
                                                                                        }
                                                                                        else {
                                                                                            //alert('hello2');
                                                                                            return false;
                                                                                        }
//                                                                                this.form.action.value = 'Save';
//                                                                                    if (formValidate())
//                                                                                        AjaxDuplicateValidate('Accounts', 'accountname', this.form);
                                                                                           " type="button" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
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
                                        <!-- Basic Information Tab Closed -->

                                    </td>
                                </tr>
                            </table>
                            
            </form>

             <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                   
                    var pw_length = form.pw.value.length;
                    var pw_value = form.pw.value;
                    
                    //alert(form.pw.value.length);
                    if(pw_length < 3 || pw_length > 20){
                        alert("Mật khẩu phải từ 3-20 ký tự.");
                        return false;
                    }

//                    for(int i = 0; i < pw_length; i++ ){
//                        if(pw_value.charAt(i) != )
//                    }

                    if (trim(pw_value) == "") {
                        alert("Hãy nhập mật khẩu mới của cho nhân viên.");
                        return false;
                    }

                    return true;
                }
            </script>                                                               
            
            <br><br><br>

            <!--    Footer-->
            <s:include value="footer.jsp"></s:include>

        </body>
</html>

