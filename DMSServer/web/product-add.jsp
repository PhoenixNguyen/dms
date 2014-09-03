<%-- 
    Document   : product-add
    Created on : Apr 10, 2014, 12:38:12 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Tồn kho - Sản phẩm - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'inventory'" />
        </s:include>


        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Tồn kho &gt; <a class="hdrLink" href="">Sản phẩm</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-product"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Sản phẩm..." title="Tạo mới Sản phẩm..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-product"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Sản phẩm" title="Nhập dữ liệu Sản phẩm" border="0"></a></td>  

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


        <!--ADD PRODUCT-->
        <table border=0 cellspacing=0 cellpadding=0 width=98% align=center>
            <tr>
                <td valign=top>
                    <img src="themes/softed/images/showPanelTopLeft.gif">
                </td>

                <td class="showPanelBg" valign=top width=100%>

                    <form name="EditView" method="POST" id="sub_form" action="save-product" onsubmit="VtigerJS_DialogBox.block();">
                        <input type="hidden" name="product.serial" value="-1">

                        <input type="hidden" name="return_viewname" value="">	     
                        <div class="small" style="padding:20px">

                            <span class="lvtHeaderText">Tạo mới Sản phẩm</span> <br>

                            <hr noshade size=1>
                            <br> 
                            <table border=0 cellspacing=0 cellpadding=0 width=95% align=center>
                                <tr>
                                    <td>
                                        <table border=0 cellspacing=0 cellpadding=3 width=100% class="small">
                                            <tr>
                                                <td class="dvtTabCache" style="width:10px" nowrap>&nbsp;</td>


                                                <td width=75 style="width:15%" align="center" nowrap class="dvtSelectedCell" id="bi" onclick="fnLoadValues('bi', 'mi', 'basicTab', 'moreTab', 'inventory', 'Products')"><b>Thông tin</b></td>
<!--                                                <td class="dvtUnSelectedCell" style="width: 100px;" align="center" nowrap id="mi" onclick="fnLoadValues('mi', 'bi', 'moreTab', 'basicTab', 'inventory', 'Products')"><b>Thêm Thông tin </b></td>-->
                                                <td class="dvtTabCache" style="width:65%" nowrap>&nbsp;</td>
                                            <tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign=top align=left >

                                        <!-- Basic and More Information Tab Opened -->
                                        <div id="basicTab">

                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="dvtContentSpace">
                                                <tr>
                                                    <!--this td is to display the entity details -->
                                                    <td align=left>
                                                        <!-- content cache -->

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
                                                                                        if(validate(this.form)){
                                                                                            //alert('hello');
                                                                                            document.getElementById('sub_form').submit();
                                                                                        }
                                                                                        else {
                                                                                            //alert('hello2');
                                                                                            return false;
                                                                                        }

                                                                                            
                                                                                           " type="submit" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                </div>
                                                                            </td>
                                                                        </tr>

                                                                        <tr>
                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                <b>Thông tin sản phẩm</b>
                                                                            </td>
                                                                        </tr>
                                                                        <!-- Here we should include the uitype handlings-->
                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                Tên sản phẩm 			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <input type="text" name="product.productName" tabindex="" value="<s:property value="product.productName"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                            </td>

                                                                            <!-- Non Editable field, only configured value will be loaded -->
                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red">*</font>Mã sản phẩm </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <input  type="text" tabindex="" name="productID" id="product_no" value="<s:property value="product.productID"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font>Mã vạch 			
                                                                            </td>

                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <input  type="text" tabindex="" name="product.barcode" id="product_no" value="<s:property value="product.barcode"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'">
                                                                            </td>

                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Thương hiệu </td>

                                                                            <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="product.brand" id="productcode" value="<s:property value="product.brand"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                        </tr>
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font>Xuất xứ			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">

                                                                                <input name="product.origin" tabindex="" class="detailedViewTextBox"  type="text" style="border:1px solid #bababa;"  value="<s:property value="product.origin"/>">
                                                                                
                                                                            </td>
                                                                            <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red">*</font>
                                                                                Nhà cung cấp			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <select name="product.provider" tabindex="" class="small">
                                                                                    <s:iterator value="providerIDList" status="index">

                                                                                        <option value="<s:property value="providerIDList.get(#index.index)"/>" ><s:property value="providerIDList.get(#index.index)"/></option>

                                                                                    </s:iterator>
                                                                                </select>
                                                                            </td>
                                                                        </tr>
<!--                                                                        <tr style="height:25px">

                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font>
                                                                                Loại sản phẩm 			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <select name="productcategory" tabindex="" class="small">
                                                                                    <option value="--None--">
                                                                                        --Chưa chọn--
                                                                                    </option>
                                                                                    
                                                                                </select>
                                                                            </td>

                                                                            

                                                                        </tr>-->
                                                                        
                                                                        <tr style="height:25px">
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font>Đơn vị			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <input name="product.quantification" class="detailedViewTextBox"  type="text" style="border:1px solid #bababa;" value="<s:property value="product.quantification"/>">
                                                                                
                                                                            </td>
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font>Quy cách 			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                
                                                                                <input  class="detailedViewTextBox" type="text" tabindex="" name="product.packingSpecifications" size="27" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'" onkeyup="validateUrl('website');" value="<s:property value="product.packingSpecifications"/>">
                                                                            </td>

                                                                        </tr>
                                                                       

                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>

                                                                        <tr>
                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                <b>Thông tin Giá cả</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Handle the ui types display -->


                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red">*</font>Giá trước thuế: (₫) 			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">				
                                                                                <span id="multiple_currencies">
                                                                                    <input name="importPrices" class="detailedViewTextBox"  tabindex="" type="text" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox';
                                                                                            updateUnitPrice('unit_price', 'curname1');" value="<s:property value="product.importPrices"/>" >
                                                                                    &nbsp;
                                                                                    <!--                                                                        <a href="javascript:void(0);" onclick="updateUnitPrice('unit_price', 'curname1');
                                                                                                                                                                    toggleShowHide('currency_class', 'multiple_currencies');">thêm tiền tệ »</a>-->
                                                                                </span>
                                                                                <div id="currency_class" class="multiCurrencyEditUI" width="350">
                                                                                    <input type="hidden" name="base_currency" id="base_currency" value="curname1">
                                                                                    <input type="hidden" name="base_conversion_rate" id="base_currency" value="curname1">
                                                                                    <table width="100%" height="100%" class="small" cellpadding="5">
                                                                                        <tbody><tr class="detailedViewHeader">
                                                                                                <th colspan="4">
                                                                                                    <b>Giá sản phẩm</b>
                                                                                                </th>
                                                                                                <th align="right">
                                                                                                    <img border="0" style="cursor: pointer;" onclick="toggleShowHide('multiple_currencies', 'currency_class');" src="themes/images/close.gif">
                                                                                                </th>
                                                                                            </tr>
                                                                                            <tr class="detailedViewHeader">
                                                                                                <th>Tiền tệ</th>
                                                                                                <th>Giá</th>
                                                                                                <th>Chuyển đổi giá</th>
                                                                                                <th>Phục hồi giá</th>							
                                                                                                <th>Tiền tệ cơ bản</th>
                                                                                            </tr>
                                                                                            <tr>

                                                                                                <td align="right" class="dvtCellLabel">
                                                                                                    Vietnam, Dong (₫)
                                                                                                    <input type="checkbox" name="cur_1_check" id="cur_1_check" class="small" onclick="fnenableDisable(this, '1');
                                                                                                            updateCurrencyValue(this, 'curname1', 'curname1', '1');" checked="">
                                                                                                </td>
                                                                                                <td class="dvtCellInfo" align="left">
                                                                                                    <input type="text" size="10" class="small" name="curname1" id="curname1" value="0.00" onblur="updateUnitPrice('curname1', 'unit_price');
                                                                                                            fnpriceValidation('curname1');">
                                                                                                </td>
                                                                                                <td class="dvtCellInfo" align="left">
                                                                                                    <input disabled="true" type="text" size="10" class="small" name="cur_conv_rate1" value="1">
                                                                                                </td>
                                                                                                <td class="dvtCellInfo" align="center">
                                                                                                    <input type="button" class="crmbutton small edit" id="cur_reset1" onclick="updateCurrencyValue(this, 'curname1', 'curname1', '1');" value="Phục hồi">
                                                                                                </td>
                                                                                                <td class="dvtCellInfo">
                                                                                                    <input type="radio" class="detailedViewTextBox" id="base_currency1" name="base_currency_input" value="curname1" checked="" onchange="updateBaseCurrencyValue()">
                                                                                                </td>
                                                                                            </tr>
                                                                                        </tbody>
                                                                                    </table>
                                                                                </div>
                                                                            </td>
                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red">*</font>Thuế (%) </td>

                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <input type="text" tabindex="" name="vatTax" id="commissionrate" value="<s:property value="product.vatTax"/>" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                        </tr>
                                                                        
                                                                        
                                                                        <tr>
                                                                            <td colspan="2" class="dvtCellInfo">&nbsp;</td>
                                                                        </tr>
                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        
<!--                                                                        <tr>
                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                <b>Kho Hàng</b>
                                                                            </td>
                                                                        </tr>
                                                                         Handle the ui types display 

                                                                         Added this file to display the fields in Create Entity page based on ui types  
                                                                        <tr style="height:25px">

                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red">*</font>
                                                                                Tên kho 			
                                                                            </td>
                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                <select name="cf_628" tabindex="" class="small">
                                                                                    <option value="Kho Mien Bac - KMB">
                                                                                        Kho Mien Bac - KMB
                                                                                    </option>
                                                                                    
                                                                                </select>
                                                                            </td>

                                                                        </tr>-->
                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        
                                                                        <tr>
                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                <b>Thông tin chi tiết</b>
                                                                            </td>
                                                                        </tr>

                                                                        <!-- Handle the ui types display -->
                                                                        <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                        <tr style="height:25px">

                                                                            <!-- In Add Comment are we should not display anything -->
                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                <font color="red"></font> 
                                                                                Mô tả 			</td>
                                                                            <td colspan="3">
                                                                                <textarea class="detailedViewTextBox" tabindex="" onfocus="this.className = 'detailedViewTextBoxOn'" name="product.description" onblur="this.className = 'detailedViewTextBox'" cols="90" rows="8"><s:property value="product.description"/></textarea>
                                                                            </td>
                                                                        </tr>
                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                        <!-- This if is added to restrict display in more tab-->
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
                                                                                        " type="submit" name="button" value="  Lưu  " style="width:70px" >
                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                    <input type="hidden" name="convert_from" value="">
                                                                                    <input type="hidden" name="duplicate_from" value="">
                                                                                </div>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                    <!-- General details - end -->
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>

                                        </div>
                                        <!-- Basic and More Information Tab Opened -->
                                        <!--                <div id="moreTab">
                                        
                                                            <table border=0 cellspacing=0 cellpadding=3 width=100% class="dvtContentSpace">
                                                                <tr>
                                                                    this td is to display the entity details 
                                                                    <td align=left>
                                                                         content cache 
                                        
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
                                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save';
                                                                                                            return validateInventory('Products')" type="submit" name="button" value="  Lưu  " style="width:70px" >
                                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                        
                                                                                        <tr>
                                                                                            <td colspan="4" class="detailedViewHeader">
                                                                                                <b>Thông tin tồn kho</b>
                                                                                            </td>
                                                                                        </tr>
                                                                                         Handle the ui types display 
                                        
                                                                                         Added this file to display the fields in Create Entity page based on ui types  
                                                                                                                            <tr style="height:25px">
                                        
                                                                                             uitype 111 added for noneditable existing picklist values - ahmed 
                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red"></font>
                                                                                                Đơn vị sử dụng 			</td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <select name="usageunit" tabindex="" class="small">
                                                                                                    <option value="Box">
                                                                                                        Hộp
                                                                                                    </option>
                                        
                                                                                                </select>
                                                                                            </td>
                                        
                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Số lượng/Đơn vị </td>
                                        
                                                                                            <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="qty_per_unit" id="qty_per_unit" value="0.00" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                        </tr>
                                                                                        <tr style="height:25px">
                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Tồn kho </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="qtyinstock" id="qtyinstock" value="0" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mức tồn kho chuẩn </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="reorderlevel" id="reorderlevel" value="0" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                        </tr>
                                                                                        <tr style="height:25px">
                                        
                                                                                            <td width="20%" class="dvtCellLabel" align="right">
                                                                                                <font color="red"></font>Người quản lý 			
                                                                                            </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo">
                                                                                                <select name="assigned_user_id" tabindex="" class="small">
                                        
                                                                                                    <option value="1" selected="">admin</option>
                                        
                                                                                                </select>
                                                                                            </td>
                                                                                            <td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Số lượng yêu cầu </td>
                                                                                            <td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="qtyindemand" id="qtyindemand" value="0" class="detailedViewTextBox" onfocus="this.className = 'detailedViewTextBoxOn'" onblur="this.className = 'detailedViewTextBox'"></td>
                                                                                        </tr>
                                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                                        <tr>
                                                                                            <td colspan=4 class="detailedViewHeader">
                                                                                                <b>Thông tin hình ảnh sản phẩm</b>
                                                                                            </td>
                                                                                        </tr>
                                        
                                                                                         Here we should include the uitype handlings
                                                                                         Added this file to display the fields in Create Entity page based on ui types  
                                                                                        <tr style="height:25px">
                                        
                                                                                            <td width="20%" class="dvtCellLabel" align=right>
                                                                                                <font color="red"></font>Hình ảnh sản phẩm
                                                                                            </td>
                                                                                            <td colspan="3" width="30%" align=left class="dvtCellInfo">
                                                                                                <input name="del_file_list" type="hidden" value="">
                                                                                                <div id="files_list" style="border: 1px solid grey; width: 500px; padding: 5px; background: rgb(255, 255, 255) none repeat scroll 0%; -moz-background-clip: initial; -moz-background-origin: initial; -moz-background-inline-policy: initial; font-size: x-small">Số lượng Tập tin tối đa là 6
                                                                                                    <input id="my_file_element" type="file" name="file_1" tabindex=""  onchange="validateFilename(this)"/>
                                                                                                    input type="hidden" name="file_1_hidden" value=""/
                                                                                                </div>
                                        
                                                                                                <script>
                                                                                                    var multi_selector = new MultiSelector(document.getElementById('files_list'), 6);
                                                                                                    multi_selector.count = 0
                                                                                                    multi_selector.addElement(document.getElementById('my_file_element'));
                                                                                                </script>
                                                                                            </td>
                                                                                        </tr>
                                                                                        <tr style="height:25px"><td>&nbsp;</td></tr>
                                                                                         This if is added to restrict display in more tab
                                                                                        <tr>
                                                                                            <td  colspan=4 style="padding:5px">
                                                                                                <div align="center">
                                                                                                    <input title="Lưu [Alt+S]" accessKey="S" class="crmbutton small save" onclick="this.form.action.value = 'Save';
                                                                                                            return validateInventory('Products')" type="submit" name="button" value="  Lưu  " style="width:70px" >
                                                                                                    <input title="Hủy bỏ [Alt+X]" accessKey="X" class="crmbutton small cancel" onclick="window.history.back()" type="button" name="button" value="  Hủy bỏ  " style="width:70px">
                                                                                                    <input type="hidden" name="convert_from" value="">
                                                                                                    <input type="hidden" name="duplicate_from" value="">
                                                                                                </div>
                                                                                            </td>
                                                                                        </tr>
                                                                                    </table>
                                                                                     General details - end 
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
                        </div>

                    </form>

                </td></tr>
            <tr><td colspan="2" align="center">
                </td>
            </tr>
        </table>
        <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                    
                   //1. after_tax
                    var after_tax_length = form.importPrices.value.length;
                    var after_tax_value = form.importPrices.value;
                    
                    if (trim(after_tax_value) == "") {
                        alert("Hãy nhập giá trước thuế.");
                        return false;
                    }
                    if ( isNaN(after_tax_value)) {
                        alert("Giá trước thuế phải là kiểu số");
                        //document.formname.txt.focus();
                        return (false);
                    }
                    //2. tax
                    var tax_length = form.vatTax.value.length;
                    var tax_value = form.vatTax.value;
                    
                    if (trim(tax_value) == "") {
                        alert("Hãy nhập thuế giá trị gia tăng.");
                        return false;
                    }
                    if ( isNaN(tax_value)) {
                        alert("Thuế phải là kiểu số");
                        //document.formname.txt.focus();
                        return (false);
                    }
                    
                    //3. productID
                    var product_length = form.productID.value.length;
                    var product_value = form.productID.value;

                    if (trim(product_value) == "") {
                        alert("Hãy nhập mã sản phẩm.");
                        return false;
                    }
                    
                    if(product_length < 3 || product_length > 20){
                        alert("Mã sản phẩm phải từ 3-20 ký tự.");
                        return false;
                    }
                    
                    if ( /[^A-Za-z\d\_]/.test(product_value)) {
                        alert("Mã sản phẩm không được chứa ký tự đặc biệt");
                        //document.formname.txt.focus();
                        return (false);
                    }
                    
                    return true;
                }
            </script>                                                                      
<!--    Footer-->
        <s:include value="footer.jsp"></s:include>
                                                                    
    </body>
</html>

