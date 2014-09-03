<%-- 
    Document   : edit-customer
    Created on : Apr 10, 2014, 12:33:32 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

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
<!--                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc');
                                                                            searchshowhide('searchAcc', 'advSearch');
                                                                            mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Khách hàng..." title="Tìm kiếm trong Khách hàng..." border="0"></a></td>-->

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
                                                    <!--                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup');
                                                                                                        mergeshowhide('mergeDup');
                                                                                                        searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>-->
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

        <!--CUSTOMER-->
        <table border=0 cellspacing=0 cellpadding=0 width=98% align=center>
            <tr>
                <td valign=top><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                <td class="showPanelBg" valign=top width=100%>
                    <div class="small" style="padding:20px">



                        <span class="lvtHeaderText"><font color="purple">[ <s:property value="customer.maDoiTuong"/> ] </font><s:property value="customer.doiTuong"/> - Sửa Thông tin Khách hàng </span> <br>


                        <hr noshade size=1>
                        <br> 


                        <form name="EditView" method="POST" action="update-customer" id="sub_form" >
                            <s:push value="customer" >
                                <input type="hidden" name="customer.stt" value="<s:property value="customer.stt"/>">
                                <input type="hidden" name="customer.coordinateX" value="<s:property value="customer.coordinateX"/>">
                                <input type="hidden" name="customer.coordinateY" value="<s:property value="customer.coordinateY"/>">

                                <table border=0 cellspacing=0 cellpadding=0 width=95% align=center>
                                    <tr>
                                        <td>
                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="small">
                                                <tr>
                                                    <td class="dvtTabCache" style="width:10px" nowrap>&nbsp;</td>
                                                    <td class="dvtSelectedCell" align=center nowrap> Khách hàng Thông tin</td>
                                                    <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                    <td class="dvtTabCache" style="width:100%">&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td valign=top align=left >
                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="dvtContentSpace">
                                                <tr>

                                                    <td align=left>

                                                        <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                                            <tr>
                                                                <td id ="autocom"></td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding:10px">
                                                                    <!-- General details -->
                                                                    <table border=0 cellspacing=0 cellpadding=0 width=100% class="small">
                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="
                                                                                            document.getElementById('sub_form').submit();

//                                                                                this.form.action.value = 'Save';
//                                                                                displaydeleted();
//                                                                                if (formValidate()) {
//                                                                                    if (AjaxDuplicateValidate('Accounts', 'accountname', this.form)) {
//                                                                                        AddressSync(this.form, 137);
//                                                                                    }
//                                                                                }
                                                                                           "  type="button" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="Hủy bỏ  " style="width:70px">
                                                                                </div>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- included to handle the edit fields based on ui types -->
                                                                        <!-- This is added to display the existing comments -->
                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin Khách hàng</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Handle the ui types display -->


                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>Tên Khách hàng 			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="customer.doiTuong" value="<s:property value="doiTuong"/>" tabindex=""  tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>

                                                                            <!-- Non Editable field, only configured value will be loaded -->
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red">*</font>Mã Khách hàng </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input readonly type="text" tabindex="" name="customer.maDoiTuong" value="<s:property value="maDoiTuong"/>" id ="account_no"    class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
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

                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" tabindex="" name="customer.dienThoai" id ="phone" value="<s:property value="dienThoai"/>" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Fax </td>

                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" tabindex="" name="customer.fax" id ="fax" value="<s:property value="fax"/>" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>
<!--                                                                        <tr style="height:25px">

                                                                            <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Mã chứng khoán </td>

                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <input type="text" name="tickersymbol" tabindex="" id ="tickersymbol" value="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn';" onBlur="this.className = 'detailedViewTextBox';
                                                                                        sensex_info()">
                                                                                <span id="vtbusy_info" style="display:none;">
                                                                                    <img src="themes/images/vtbusy.gif" border="0"></span>
                                                                            </td>

                                                                            
                                                                        </tr>-->

                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                <font color="red">*</font>Được gán cho 			
                                                                            </td>
                                                                            <td width="30%" align=left class="dvtCellInfo">
                                                                                <!--                                                                    <input type="radio" tabindex="" name="assigntype" checked value="U" onclick="toggleAssignType(this.value)" >&nbsp;Người dùng
                                                                                                                                                    <input type="radio" name="assigntype"  value="T" onclick="toggleAssignType(this.value)">&nbsp;Nhóm-->
                                                                                <span id="assign_user" style="display:block">
                                                                                    <select name="customer.maNhanVien" class="small" >
                                                                                        <s:iterator value="staffsList" status="index">
                                                                                            <s:if test="staffsList.get(#index.index) == customer.getMaNhanVien()">
                                                                                                <option value="<s:property value="staffsList.get(#index.index)"/>" selected><s:property value="staffsList.get(#index.index)"/></option>
                                                                                            </s:if>
                                                                                            <s:else>
                                                                                                <option value="<s:property value="staffsList.get(#index.index)"/>" ><s:property value="staffsList.get(#index.index)"/></option>
                                                                                            </s:else>
                                                                                        </s:iterator>

                                                                                    </select>
                                                                                </span>

                                                                                <!--                                                                    <span id="assign_team" style="display:none">
                                                                                                                                                        <select name="assigned_group_id" class="small">';
                                                                                                                                                            <option value="2" >Đội sales</option>
                                                                                                                                                            <option value="4" >Nh&oacute;m hỗ trợ</option>
                                                                                                                                                            <option value="7" >Nh&oacute;m kỹ thuật</option>
                                                                                                                                                            <option value="3" >Nh&oacute;m Marketing</option>
                                                                                                                                                        </select>
                                                                                                                                                    </span>-->
                                                                            </td>

                                                                            <!--                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                                                <font color="red"></font>Thông báo khi dữ liệu này thay đổi 			
                                                                                                                                            </td>-->
                                                                            <!--                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                                                <input name="notify_owner" type="checkbox" tabindex="" checked>
                                                                                                                                            </td>-->
                                                                        </tr>


                                                                        <!-- This is added to display the existing comments -->

                                                                        <tr>
                                                                            <td colspan=2 class="detailedViewHeader">
                                                                                <b>Thông tin địa chỉ</b>
                                                                            </td>
                                                                            <!--                                                                <td class="detailedViewHeader">
                                                                                                                                                <input name="cpy" onclick="return copyAddressLeft(EditView)" type="radio"><b>Sao chép địa chỉ vận chuyển</b></td>
                                                                                                                                            <td class="detailedViewHeader">
                                                                                                                                                <input name="cpy" onclick="return copyAddressRight(EditView)" type="radio"><b>Sao chép địa chỉ thanh toán</b></td>
                                                                                                                                            </td>-->
                                                                        </tr>

                                                                        <!-- Handle the ui types display -->
                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Địa chỉ 			
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <textarea value="<s:property value="diaChi"/>" name="customer.diaChi" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'" rows=2></textarea>
                                                                            </td>

                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font>
                                                                                Tỉnh thành
                                                                            </td>
                                                                            <td width=30% align=left class="dvtCellInfo">
                                                                                <textarea value="<s:property value="tinhThanh"/>" name="customer.tinhThanh" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'" rows=2></textarea>
                                                                            </td>
                                                                        </tr>
                                                                        
                                                                        <!-- This is added to display the existing comments -->
                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin mô tả</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Handle the ui types display -->


                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">
                                                                            <!-- In Add Comment are we should not display anything -->
                                                                            <td width=20% class="dvtCellLabel" align=right>
                                                                                <font color="red"></font> 
                                                                                Mô tả 			
                                                                            </td>
                                                                            <td colspan=3>
                                                                                <textarea  class="detailedViewTextBox" tabindex="" onFocus="this.className = 'detailedViewTextBoxOn'" name="customer.ghiChu"  onBlur="this.className = 'detailedViewTextBox'" cols="90" rows="8"/><s:property value="ghiChu"/></textarea>
                                                                            </td>
                                                                        </tr>
                                                                        <!-- Added to display the Product Details in Inventory-->

                                                                        <tr>
                                                                            <td  colspan=4 style="padding:5px">
                                                                                <div align="center">
                                                                                    <input type='hidden'  name='address_change' value='no'>
                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="
                                                                                            document.getElementById('sub_form').submit();
//                                                                                this.form.action.value = 'Save';
//                                                                                displaydeleted();
//                                                                                if (formValidate()) {
//                                                                                    if (AjaxDuplicateValidate('Accounts', 'accountname', this.form)) {
//                                                                                        AddressSync(this.form, 137);
//                                                                                    }
//                                                                                }
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
                                        </td>
                                    </tr>
                                </table>

                                <!--                        </td>
                                                        <td align=right valign=top><img src="themes/softed/images/showPanelTopRight.gif"></td>
                                                        </tr>
                                                        
                                                        </table>-->
                                <!--added to fix 4600-->
                                <input name='search_url' id="search_url" type='hidden' value=''>
                            </form>
                        </s:push>    



                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                </td>
            </tr>
        </table>

        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>
        
    </body>
</html>

