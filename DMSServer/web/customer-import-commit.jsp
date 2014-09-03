<%-- 
    Document   : customers-import-commit
    Created on : Apr 10, 2014, 12:50:27 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html><head>
        <title>admin - Khách hàng - Xác nhận - Phần mềm quản lý HOSCO-MANAGEMENT</title>
        <link rel="SHORTCUT ICON" href="themes/images/vtigercrm_icon.ico">	
        <style type="text/css">@import url("themes/softed/style.css");</style>
        <link rel="stylesheet" type="text/css" media="all" href="jscalendar/calendar-win2k-cold-1.css">
        <!-- ActivityReminder customization for callback -->

        <style type="text/css">div.fixedLay1 { position:fixed; }</style>
        <!--[if lte IE 6]>
        <style type="text/css">div.fixedLay { position:absolute; }</style>
        <![endif]-->

        <!--        <script language="JavaScript" type="text/javascript" src="jscommon/input-page.js"></script>--> 
        <script language="JavaScript" type="text/javascript" src="jscommon/jquery.min.js"></script>
        <!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" ></script>-->

        <!-- End -->
        <script type="text/javascript">
            function importStaff() {
                console.log('GET');
                //$("#info").append('hello');
                $.getJSON('add-staff'
                        , function(data) {
                            console.log('GET2');
                            console.log('1: ' + data.demo);

                            var total = 0;
                            if (data.staffsTotal != null)
                                total = (data.staffsTotal);

                            console.log(total);
                            console.log('2: ' + data.staffsTotal);

                            var option = $("#info");
                            option.find('span').remove();
                            var alert = "Có " + total + " nhân viên được thêm!";
                            option.append($("<span/>").append(alert));

                        }
                );
            }

            function importCustomers() {
                console.log('GET');
                jQuery.ajax({
                    url: 'add-customer',
                    dataType: 'json',
                    type: 'GET',
                    //data: 'text=' + jQuery(this).val(),
//                        data : {
//                            'actionId': id,
//                            'actionName': name,
//                            'actionDescr':descr
//                        },
                    async: false,
                    contentType: 'application/json; charset=utf-8',
                    success: function(data) {

                        console.log(data.customersTotal);//jsonData
                        var total = 0;
                        if (data.customersTotal != null)
                            total = (data.customersTotal);

                        var option = $("#info");
                        option.find('span').remove();
                        var alert = "Có " + total + " khách hàng được thêm!";
                        option.append($("<span/>").append(alert));

                    },
                    error: function(e) {
                        alert('Error: ' + e);
                    }
                });
            }

        </script>
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

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Khách hàng &gt; <a class="hdrLink" href="">Xác nhận</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-customer"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Khách hàng ..." title="Tạo mới Khách hàng ..." border="0"></a></td>
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
                                                    
<!--                                                    <td style="padding-right:10px"><a name="export_link" href="javascript:alert('Chức năng chưa được xây dựng!')" onclick="return selectedRecords('Accounts', 'Marketing')"<img src="themes/softed/images/tbarExport.gif" alt="Xuất dữ liệu Khách hàng" title="Xuất dữ liệu Khách hàng" border="0"></a></td>-->


                                                    <!--<td style="padding-right:10px"><a href="home.jsp?module=Accounts&action=FindDuplicateRecords&button_view=true&list_view=true&parenttab=Marketing"><img src="themes/softed/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td> -->
                                                    <!--                                            <td style="padding-right:10px"><a href="javascript:;" onclick="moveMe('mergeDup'); mergeshowhide('mergeDup'); searchhide('searchAcc', 'advSearch');"><img src="themes/images/findduplicates.gif" alt="" title="Tìm kiếm trùng" border="0"></a></td>-->
                                                </tr>
                                            </tbody>
                                        </table>  
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

        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%" class="small">
            <tbody>
                <tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" valign="top" width="100%">
                        <table cellpadding="0" cellspacing="0" width="100%" class="small">
                            <tbody><tr>
                                    <td width="75%" valign="top">
                                        <!--				<form enctype="multipart/form-data" name="Import" method="POST" action="" onsubmit="VtigerJS_DialogBox.block();">-->
                                        <input type="hidden" name="module" value="Products">


                                        <!-- IMPORT LEADS STARTS HERE  -->
                                        <br>
                                        <table align="center" cellpadding="5" cellspacing="0" width="95%" class="mailClient importLeadUI small">
                                            <tbody><tr>
                                                    <td class="mailClientBg genHeaderSmall" height="50" valign="middle" align="left">Nhập dữ liệu Khách hàng</td>
                                                </tr>
                                                <tr>
                                                    <td>&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td align="left" style="padding-left:40px;">
                                                        <span class="genHeaderGray">Bước 2 of 2 </span>&nbsp;
                                                        <span class="genHeaderSmall"> </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left" style="padding-left:40px;"> 
                                                        Tên file đã tải lên: <s:property value="document.getFileFileName()"/>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td align="left" style="padding-left:40px;">
                                                        <div id="info">STATUS: 
                                                            <span></span>
                                                        </div>  
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td align="right" style="padding-right:40px;" class="reportCreateBottom">
                                                        <input type="submit" name="button" value=" &nbsp;‹ Quay lại &nbsp; " class="crmbutton small cancel" onclick="
                                                                window.history.back()
                                                                        //this.form.action.value='Import';this.form.step.value='1'; return true;
                                                               ">
                                                        &nbsp;&nbsp;
                                                        <input type="button" name="button" value=" &nbsp; Nhập dữ liệu › &nbsp; " class="crmbutton small save" onclick="
                                                                importCustomers();

                                                                //this.form.action.value='Import';this.form.step.value='3'; check_submit();

                                                               ">
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!--				</form>-->
                                        <!-- IMPORT LEADS ENDS HERE -->	
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </tbody>
        </table>
        <script language="javascript" type="text/javascript">
            function validate_import_map()
            {
                var tagName;
                var count = 0;
                var field_count = "1";
                var required_fields = new Array();
                var required_fields_name = new Array();
                var seq_string = '';
                required_fields[count] = "productname";
                required_fields_name[count] = "Tên sản phẩm";
                count = count + 1;
                required_fields[count] = "cf_628";
                required_fields_name[count] = "Tên kho";
                count = count + 1;

                for (loop_count = 0; loop_count < field_count; loop_count++)
                {
                    tagName = document.getElementById('colnum' + loop_count);
                    optionData = tagName.options[tagName.selectedIndex].value;

                    if (optionData != -1)
                    {
                        tmp = seq_string.indexOf("\"" + optionData + "\"");
                        if (tmp == -1)
                        {
                            seq_string = seq_string + "\"" + optionData + "\"";
                        }
                        else
                        {
                            //if a vtiger_field mapped more than once, alert the user and return
                            alert("'" + tagName.options[tagName.selectedIndex].text + " 'là ánh xạ nhiều hơn một lần. Xin vui lòng kiểm tra việc ánh xạ của bạn.");
                            return false;
                        }
                    }

                }

                //check whether the mandatory vtiger_fields have been mapped.
                for (inner_loop = 0; inner_loop < required_fields.length; inner_loop++)
                {
                    if (seq_string.indexOf(required_fields[inner_loop]) == -1)
                    {
                        alert('Xin vui lòng ánh xạ các trường bắt buộc "' + required_fields_name[inner_loop] + '"');
                        return false;
                    }
                }

                //This is to check whether the save map name has been given or not when save map check box is checked
                if (document.getElementById("save_map").checked == true)
                {
                    if (trim(document.getElementById("save_map_as").value) == '')
                    {
                        alert("Xin vui lòng nhập vào tên của việc ánh xạ");
                        return false;
                    }
                }

                return true;
            }
        </script>

        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>

    </body>
</html>
