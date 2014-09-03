<%-- 
    Document   : customers-import-commit
    Created on : Apr 10, 2014, 12:50:27 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html><head>
        <title>admin - Nhà cung cấp - Xác nhận - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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

            function importProviders() {
                console.log('GET');
                jQuery.ajax({
                    url: 'add-provider',
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

                        console.log(data.providersTotal);//jsonData
                        var total = 0;
                        if (data.providersTotal != null)
                            total = (data.providersTotal);

                        var option = $("#info");
                        //option.find('span').remove();
                        var alert = "Có " + total + " Nhà cung cấp được thêm!";
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
            <s:param name="page_param" value="'inventory'" />
        </s:include>



        <!-- TOOLS -->
        <table border="0" cellspacing="0" cellpadding="0" width="100%" class="small">
            <tbody>
                <tr><td style="height:2px"></td></tr>
                <tr>

                    <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap="">Nhà cung cấp &gt; <a class="hdrLink" href="">Xác nhận</a></td>
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
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="new-provider"><img src="themes/softed/images/btnL3Add.gif" alt="Tạo mới Nhà cung câp ..." title="Tạo mới Nhà cung cấp ..." border="0"></a></td>
                                                                    <td style="padding-right:0px;padding-left:10px;"><a href="import-provider"><img src="themes/softed/images/tbarImport.gif" alt="Nhập dữ liệu Nhà cung cấp" title="Nhập dữ liệu Nhà cung cấp" border="0"></a></td>  

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
                                            </tbody>
                                        </table>  
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
                                                    <td class="mailClientBg genHeaderSmall" height="50" valign="middle" align="left">Nhập dữ liệu Nhà cung cấp</td>
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
                                                                importProviders();

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
        

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>



    </body></html>
