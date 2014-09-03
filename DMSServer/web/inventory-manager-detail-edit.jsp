<%-- 
    Document   : take-order-edit
    Created on : Apr 10, 2014, 1:06:49 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <title>admin - Tồn kho - Kiểm kê - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link rel="SHORTCUT ICON" href="themes/images/vtigercrm_icon.ico">	
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
            <s:param name="page_param" value="'inventory'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Tồn kho &gt; <a class="hdrLink" href="">Kiểm kê</a></td>
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
<!--                                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/btnL3Add.gif" alt="Tạo Hóa đơn..." title="Tạo Hóa đơn..." border="0"></a></td>

                                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('searchAcc');
                                                                            searchshowhide('searchAcc', 'advSearch');
                                                                            mergehide('mergeDup')"><img src="themes/softed/images/btnL3Search.gif" alt="Tìm kiếm trong Hóa đơn..." title="Tìm kiếm trong Hóa đơn..." border="0"></a></td>-->

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
<!--                                                    <td style="padding-right:0px;padding-left:10px;"><a href=""><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Hóa đơn" title="Nhập dữ liệu Hóa đơn" border="0"></a></td>  
                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:void(0)" onclick="return selectedRecords('Accounts', 'Marketing')"><img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Hóa đơn" title="Xuất dữ liệu Hóa đơn" border="0"></a></td>-->


                                                    <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
<!--                                                    <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup');
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
<!--                                                <tr>
                                                    <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onclick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>
                                                    <td style="padding-left:10px;"><a href=""><img src="themes/softed/images/settingsBox.png" alt="Khách hàng Thiết lập" title="Khách hàng Thiết lập" border="0"></a></td>
                                                </tr>-->
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


        <!--TAKEORDER-->
        <table border="0" cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>

                    <td class="showPanelBg" valign="top" width="100%">
                        <div class="small" style="padding:20px">



                            <span class="lvtHeaderText"><font color="purple">[ <s:property value="inventoryManagerDetail.getTakeOrderID()"/> ] </font>Tồn kho - Sửa Chi tiết Thông tin Kiểm kê</span> <br>
                            <!--                    Cập nhật 1086 ngày trước (19 Tháng 4 2011)	 -->

                            <hr noshade="" size="1">
                            <br> 


                            <!-- (id="frmEditView") content added to form tag and new hidden field added,  -->
                            <form id="frmEditView" name="EditView" method="POST" action="update-inventory-manager-detail" id="sub_form" >

                                <input type="hidden" name="inventoryManagerDetail.serial" value="<s:property value="inventoryManagerDetail.getSerial()"/>">

                                <table border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                                    <tbody><tr>
                                            <td>
                                                <table border="0" cellspacing="0" cellpadding="3" width="100%" class="small">
                                                    <tbody><tr>
                                                            <td class="dvtTabCache" style="width:10px" nowrap="">&nbsp;</td>
                                                            <td class="dvtSelectedCell" align="center" nowrap="">Thông tin Kiểm kê Chi tiết</td>
                                                            <td class="dvtTabCache" style="width:10px">&nbsp;</td>
                                                            <td class="dvtTabCache" style="width:100%">&nbsp;</td>
                                                        </tr>
                                                    </tbody></table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td valign="top" align="left">
                                                <table border="0" cellspacing="0" cellpadding="3" width="100%" class="dvtContentSpace">
                                                    <tbody><tr>

                                                            <td align="left" style="padding:10px;border-right:1px #CCCCCC;" width="80%">

                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                                    <tbody><tr>
                                                                            <td id="autocom"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td style="padding:10px">
                                                                                <!-- General details -->
                                                                                <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
                                                                                    <tbody><tr>
                                                                                            <td colspan="4" style="padding:5px">
                                                                                                <div align="center">
                                                                                                    <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="
                                                                                                        if(validate(this.form)){
                                                                                                            //alert('hello');
                                                                                                            document.getElementById('sub_form').submit();
                                                                                                        }
                                                                                                        else {
                                                                                                            //alert('hello2');
                                                                                                            return false;
                                                                                                        }
                                                                                                           " type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                                                    <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="Hủy bỏ  " style="width:70px">
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <!-- included to handle the edit fields based on ui types -->
                                                                                        <tr>
                                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                                <b>Thông tin Đặt hàng</b>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <!-- Handle the ui types display -->
                                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                        <tr style="height:25px">

                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red">*</font>Mã kiểm kê			
                                                                                            </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input type="text" readonly="" name="inventoryManagerDetail.takeOrderID" tabindex="" value="<s:property value="inventoryManagerDetail.getTakeOrderID()"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                            </td>

                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red"></font>Dòng
                                                                                            </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">

                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.line" id="exciseduty" value="<s:property value="inventoryManagerDetail.line"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                                <!--                                                                                        <input name="potential_id" type="hidden" value="0">&nbsp;
                                                                                                                                                                                        <img tabindex="" src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="selectPotential()" align="absmiddle" style="cursor:hand;cursor:pointer">&nbsp;
                                                                                                                                                                                        <input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.potential_id.value = ''; this.form.potential_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">-->
                                                                                            </td>

                                                                                        </tr>
                                                                                        <tr style="height:25px">

                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mã sản phẩm</td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.productID" id="customerno" value="<s:property value="inventoryManagerDetail.productID"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                            </td>
                                                                                            <!-- Non Editable field, only configured value will be loaded -->
                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Tên sản phẩm </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.productName" id="salesorder_no" value="<s:property value="inventoryManagerDetail.productName"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                        </tr>
                                                                                        <tr style="height:25px">

                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red"></font>Mã kho			
                                                                                            </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.stockID" id="exciseduty" value="<s:property value="inventoryManagerDetail.stockID"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                                <!--                                                                                        <input name="quote_id" type="hidden" value="0">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="selectQuote()" align="absmiddle" style="cursor:hand;cursor:pointer">&nbsp;
                                                                                                                                                                                        <input type="image" tabindex="" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.quote_id.value = ''; this.form.quote_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">-->
                                                                                            </td>

                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Đơn vị</td>

                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input type="text" readonly="" tabindex="" name="inventoryManagerDetail.unit" id="vtiger_purchaseorder" value="<s:property value="inventoryManagerDetail.unit"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                        </tr>
                                                                                        
                                                                                        
                                                                                        
                                                                                        <tr style="height:25px">

                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Giá gốc </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.beforeOrderPrice" id="exciseduty" value="<s:property value="getText('{0,number,#,##0.00}',{inventoryManagerDetail.beforeOrderPrice})"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red"></font>Giá bán	
                                                                                            </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.afterOrderPrice" id="exciseduty" value="<s:property value="getText('{0,number,#,##0.00}',{inventoryManagerDetail.afterOrderPrice})"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                                <!--                                                                                        <input name="account_id" type="hidden" value="139">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="return window.open( & quot; index.php?module = Accounts & amp; action = Popup & amp; popuptype = specific_account_address & amp; form = TasksEditView & amp; form_submit = false & amp; fromlink = & quot; , & quot; test & quot; , & quot; width = 640, height = 602, resizable = 0, scrollbars = 0 & quot; );" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                                                                                                                        <input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.account_id.value = ''; this.form.account_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">-->
                                                                                            </td>

                                                                                        </tr>

                                                                                        <tr style="height:25px">

                                                                                            <!-- Avoid to display the label Tax Class -->
                                                                                            <td class="dvtCellLabel" align="right" width="25%">
                                                                                                <font color="blue"> + </font>
                                                                                                <input type="hidden" id="hdtxt_IsAdmin" value="1">Số lượng
                                                                                            </td>


                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input type="text"  tabindex="" name="number" id="salescommission" value="<s:property value="inventoryManagerDetail.number"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                            </td>
            <!--                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="takeOrder.getCreater()"/></td>-->

                                                                                            <!-- Avoid to display the label Tax Class -->
                                                                                            <td class="dvtCellLabel" align="right" width="25%"><input type="hidden" id="hdtxt_IsAdmin" value="1">Tổng giá trị</td>


                                                                                            <!-- This file is used to display the fields based on the ui type in detailview -->

                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <input readonly="" type="text" tabindex="" name="inventoryManagerDetail.priceTotal" id="exciseduty" value="<s:property value="getText('{0,number,#,##0.00}',{inventoryManagerDetail.priceTotal})"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                                                <!--                                                                                        <input name="account_id" type="hidden" value="139">&nbsp;<img src="themes/softed/images/select.gif" alt="Chọn" title="Chọn" language="javascript" onclick="return window.open( & quot; index.php?module = Accounts & amp; action = Popup & amp; popuptype = specific_account_address & amp; form = TasksEditView & amp; form_submit = false & amp; fromlink = & quot; , & quot; test & quot; , & quot; width = 640, height = 602, resizable = 0, scrollbars = 0 & quot; );" align="absmiddle" style="cursor:hand;cursor:pointer">
                                                                                                                                                                                        <input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.account_id.value = ''; this.form.account_name.value = ''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">-->
                                                                                            </td>
            <!--                                                                                <td class="dvtCellInfo" align="left" width="25%&quot;">&nbsp;<s:property value="takeOrder.getOrderEstablishDate()"/></td>-->

                                                                                        </tr>	

                                                                                        
                                                                                        <tr>
                                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                                <b>Thông tin mô tả</b>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <!-- Handle the ui types display -->


                                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                        <tr style="height:25px">




                                                                                            <!-- In Add Comment are we should not display anything -->
                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="blue"> + </font> 
                                                                                                Mô tả 			
                                                                                            </td>
                                                                                            <td colspan="3">
                                                                                                <textarea class="detailedViewTextBox" tabindex="" onfocus="this.className = 'detailedViewTextBoxOn'" name="inventoryManagerDetail.note" onblur="this.className = 'detailedViewTextBox'" cols="90" rows="8"><s:property value="inventoryManagerDetail.note"/></textarea>
                                                                                            </td>
                                                                                        </tr>

                                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                                        <tr>
                                                                                            <td colspan="4" style="padding:5px">
                                                                                                <div align="center">
                                                                                                    <input title="Lưu [Alt+S]" accesskey="S" class="crmbutton small save" onclick="
                                                                                                        if(validate(this.form)){
                                                                                                            //alert('hello');
                                                                                                            document.getElementById('sub_form').submit();
                                                                                                        }
                                                                                                        else {
                                                                                                            //alert('hello2');
                                                                                                            return false;
                                                                                                        }

                                                                                                           " type="submit" name="button" value="  Lưu  " style="width:70px">
                                                                                                    <input title="Hủy bỏ [Alt+X]" accesskey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </tbody></table>
                                                                            </td>
                                                                        </tr>
                                                                    </tbody></table>
                                                            </td>
                                                            <!-- Inventory Actions - ends -->
                                                        </tr>
                                                    </tbody></table>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </td>
                </tr>
            </tbody></table>
        <input name="search_url" id="search_url" type="hidden" value="">


        <!-- This div is added to get the left and top values to show the tax details-->
        <div id="tax_container" style="display:none; position:absolute; z-index:1px;"></div>
        <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                   
                    //2. number
                    var number_length = form.number.value.length;
                    var number_value = form.number.value;
                    
                    if (trim(number_value) == "") {
                        alert("Hãy nhập Số lượng.");
                        return false;
                    }
                    
                    if ( /[^\d]/.test(number_value)) {
                        alert("Số lượng phải là kiểu số");
                        //document.formname.txt.focus();
                        return (false);
                    }
                    
                    return true;
                }
            </script>

        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>

    </body>
</html>
