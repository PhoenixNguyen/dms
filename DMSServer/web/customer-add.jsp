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
        <title>admin - Marketing - Khách hàng - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'customer'" />
        </s:include>

        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Khách hàng &gt; <a class="hdrLink" href="">Khách hàng</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-customer"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Khách hàng..." title="Tạo mới Khách hàng..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-customer"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Khách hàng" title="Nhập dữ liệu Khách hàng" border="0"></a></td>  
<!--                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc'); searchshowhide('searchAcc', 'advSearch'); mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Khách hàng..." title="Tìm kiếm trong Khách hàng..." border="0"></a></td>-->

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
                                                    
<!--                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:alert('Chức năng chưa được xây dựng!')" onclick="return selectedRecords('Accounts', 'Marketing')"<img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Nhân viên" title="Xuất dữ liệu Nhân viên" border="0"></a></td>-->


                                                    <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
        <!--                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup'); mergeshowhide('mergeDup'); searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>-->
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
                        <span class="lvtHeaderText">Tạo mới Khách hàng</span> <br>
                        <hr noshade size=1>
                        <br> 
                        <form name="EditView" method="POST" action="save-customer" id="sub_form" onsubmit="">

                            <input type="hidden" name="customer.stt" value="0">
                            <input type="hidden" name="customer.coordinateX" value="">
                            <input type="hidden" name="customer.coordinateY" value="">
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
                                                                                <b>Thông tin Khách hàng</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Here we should include the uitype handlings-->


                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">
                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Tên Khách hàng 			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="customer.doiTuong" tabindex="" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                            <!-- Non Editable field, only configured value will be loaded -->
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red">*</font>Mã Khách hàng </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="maDoiTuong" tabindex="" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px">

<!--                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Website 			
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                &nbsp;&nbsp;http://
                                                                                <input style="width:74%;" class = 'detailedViewTextBox' type="text" tabindex="" name="website" style="border:1px solid #bababa;" size="27" onFocus="this.className = 'detailedViewTextBoxOn'"onBlur="this.className = 'detailedViewTextBox'" onkeyup="validateUrl('website');" value="">
                                                                            </td>-->
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Điện thoại </td>

                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="customer.dienThoai" id ="phone" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Fax </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" tabindex="" name="customer.fax" id ="fax" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>

                                                                        <tr style="height:25px">
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red">*</font>Nhân viên		
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
        <!--                                                                        <input type="radio" tabindex="" name="assigntype" checked value="U" onclick="toggleAssignType(this.value)" >&nbsp;Người dùng
                                                                                <input type="radio" name="assigntype"  value="T" onclick="toggleAssignType(this.value)">&nbsp;Nhóm-->
                                                                                <span id="assign_user" style="display:block">
                                                                                    <select name="customer.maNhanVien" class="small" >
                                                                                        <s:iterator value="staffsList" status="index">
                                                                                        <option value="<s:property value="staffsList.get(#index.index)"/>" ><s:property value="staffsList.get(#index.index)"/></option>
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

                                                                        </tr>
        
                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        <tr>
                                                                            <td colspan=2 class="detailedViewHeader">
                                                                                <b>Thông tin địa chỉ</b></td>
        
                                                                        </tr>
                                                                        <!-- Here we should include the uitype handlings-->

                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Địa chỉ 			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <textarea value="" name="customer.diaChi" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'" rows=2></textarea>
                                                                            </td>

                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Tỉnh thành			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <textarea value="" name="customer.tinhThanh" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'" rows=2></textarea>
                                                                            </td>
                                                                        </tr>

                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin mô tả</b>
                                                                            </td>
                                                                        </tr>
                                                                        <!-- Here we should include the uitype handlings-->
                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">
                                                                            <!-- In Add Comment are we should not display anything -->
                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font> 
                                                                                Mô tả 			</td>
                                                                            <td colspan=3>
                                                                                <textarea class="detailedViewTextBox" tabindex="" onFocus="this.className = 'detailedViewTextBoxOn'" name="customer.ghiChu"  onBlur="this.className = 'detailedViewTextBox'" cols="90" rows="8"></textarea>
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>

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
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>

                                        </div>
                                        <!-- Basic Information Tab Closed -->

                                        <!-- More Information Tab Opened -->
<!--                                        <div id="moreTab">
                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="dvtContentSpace">
                                                <tr>
                                                    <td align=left>

                                                        <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                                            <tr>
                                                                <td id ="autocom"></td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding:10px">
                                                                     General details 
                                                                    <table border=0 cellspacing=0 cellpadding=0 width=100% class="small">
                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="
                                                                                        document.getElementById('sub_form').submit();
        //                                                                                this.form.action.value = 'Save';
        //                                                                                    if (formValidate())
        //                                                                                        AjaxDuplicateValidate('Accounts', 'accountname', this.form);
                                                                                            " type="button" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                </div>
                                                                            </td>
                                                                        </tr>

                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin Khách hàng</b>
                                                                            </td>
                                                                        </tr>

                                                                         Here we should include the uitype handlings
                                                                         Added this file to display the fields in Create Entity page based on ui types  
                                                                        <tr style="height:25px">
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Điện thoại khác </td>
                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="otherphone" id ="otherphone" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>

                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Số nhân viên </td>

                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="employees" id ="employees" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>
                                                                        </tr>
                                                                        <tr style="height:25px">
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Email khác </td>
                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="email2" id ="email2" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Người sở hữu </td>
                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="ownership" id ="ownership" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Ngành nghề 			</td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <select name="industry" tabindex="" class="small">
                                                                                    <option value="--None--" >
                                                                                        --Chưa chọn--
                                                                                    </option>
                                                                                    
                                                                                </select>
                                                                            </td>

                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Đánh giá 			</td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <select name="rating" tabindex="" class="small">
                                                                                    <option value="--None--" >
                                                                                        --Chưa chọn--
                                                                                    </option>
                                                                                    <option value="Acquired" >
                                                                                        Đạt kết quả
                                                                                    </option>
                                                                                    <option value="Active" >
                                                                                        Hoạt động
                                                                                    </option>
                                                                                    <option value="Market Failed" >
                                                                                        Thất bại
                                                                                    </option>
                                                                                    <option value="Project Cancelled" >
                                                                                        Hủy bỏ
                                                                                    </option>
                                                                                    <option value="Shutdown" >
                                                                                        Ngừng hoạt động
                                                                                    </option>
                                                                                </select>
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Loại 			</td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <select name="accounttype" tabindex="" class="small">
                                                                                    <option value="--None--" >
                                                                                        --Chưa chọn--
                                                                                    </option>
                                                                                    <option value="Analyst" >
                                                                                        Nhà phân tích
                                                                                    </option>
                                                                                    <option value="Competitor" >
                                                                                        Đối thủ
                                                                                    </option>
                                                                                    <option value="Customer" >
                                                                                        Khách hàng
                                                                                    </option>
                                                                                    <option value="Integrator" >
                                                                                        Người liên kết
                                                                                    </option>
                                                                                    <option value="Investor" >
                                                                                        Nhà đầu tư
                                                                                    </option>
                                                                                    <option value="Partner" >
                                                                                        Đối tác
                                                                                    </option>
                                                                                    <option value="Press" >
                                                                                        Báo chí
                                                                                    </option>
                                                                                    <option value="Prospect" >
                                                                                        Triển vọng
                                                                                    </option>
                                                                                    <option value="Reseller" >
                                                                                        Đại lý bán hàng
                                                                                    </option>
                                                                                    <option value="Other" >
                                                                                        Khác
                                                                                    </option>
                                                                                </select>
                                                                            </td>

                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Mã ngành </td>

                                                                            <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="siccode" id ="siccode" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Từ chối email 			</td>

                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <input name="emailoptout" tabindex="" type="checkbox" >
                                                                            </td>

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Doanh thu hàng năm: (₫) 			</td>
                                                                            <td width="30%" align=left class="dvtCellInfo">				
                                                                                <input name="annual_revenue" tabindex="" type="text" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"  value="0">
                                                                            </td>

                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Thông báo khi dữ liệu này thay đổi 			</td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <input name="notify_owner" tabindex="" type="checkbox" >
                                                                            </td>
                                                                        </tr>

                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save';
                                                                                            if (formValidate())
                                                                                                AjaxDuplicateValidate('Accounts', 'accountname', this.form);" type="button" name="button" value="  Lưu  " style="width:70px" >
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
                                        </div>-->

                                    </td>
                                </tr>
                            </table>
                            <!--            </div>
                                    </td>
                                    <td align=right valign=top><img src="themes/softed/images/showPanelTopRight.gif"></td>
                                </tr>
                            </table>-->
                        </form>

            <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                   
                    //2. ID
                    var id_length = form.maDoiTuong.value.length;
                    var id_value = form.maDoiTuong.value;
                    
                    if(id_length < 3 || id_length > 20){
                        alert("Mã khách hàng phải từ 3-20 ký tự.");
                        return false;
                    }

//                    for(int i = 0; i < pw_length; i++ ){
//                        if(pw_value.charAt(i) != )
//                    }

                    if (trim(id_value) == "") {
                        alert("Hãy nhập Mã tài khoản cho nhân viên.");
                        return false;
                    }
               
                    if ( /[^A-Za-z\d\_]/.test(id_value)) {
                        alert("Mã khách hàng không được chứa ký tự đặc biệt");
                        //document.formname.txt.focus();
                        return (false);
                    }

                    return true;
                }
            </script>  
                        <br><br><br>
                        <!--    Footer-->
                        <s:include value="footer.jsp"></s:include>
                        
                </td>
            </tr>
            
        </table>
</body>
</html>

