<%-- 
    Document   : system-manager-admin-edit
    Created on : May 16, 2014, 3:18:04 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>admin - Thiết lập - Người sử dụng - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'setup'" />
        </s:include>

        <!-- TOOLS -->


        <br>
        <table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
            <tbody><tr>
                    <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                    <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
                        <br>

                        <div align=center>

                            <table border=0 cellspacing=0 cellpadding=20 width="99%" class="settingsUI">
                                <tr>
                                    <td valign=top>
                                        <table border=0 cellspacing=0 cellpadding=0 width=100%>
                                            <tr>
                                                <td valign=top id="settingsSideMenu" width="10%" >
                                                    <!--Left Side Navigation Table-->
                                                    <table border=0 cellspacing=0 cellpadding=0 width="100%">
                                                            <tr>
                                                                <td class="settingsTabHeader" nowrap>
                                                                    Quản lý người sử dụng
                                                                </td>
                                                            </tr>
                                                                                                                        
                                                            <tr>
                                                                <td class="settingsTabSelected" nowrap>
                                                                    <a href="system-manager">
                                                                        Quản trị viên
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            
                                                            <tr>
                                                                <td class="settingsTabList" nowrap>
                                                                    <a href="user-history">
                                                                        Lịch sử đăng nhập 
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="settingsTabList" nowrap>
                                                                    <a href="user-notify">
                                                                        Thông báo
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                            
                                                        </table>
                                                    <!-- Left side navigation table ends -->

                                                </td>
                                                <td width="8px" valign="top"> 
                                                    <img src="themes/images/panel-left.png" title="Hide Menu" id="hideImage" style="display:inline;cursor:pointer;" onclick="toggleShowHide_panel('showImage', 'settingsSideMenu');
                                                            toggleShowHide_panel('showImage', 'hideImage');" />
                                                    <img src="themes/images/panel-right.png" title="Show Menu" id="showImage" style="display:none;cursor:pointer;" onclick="toggleShowHide_panel('settingsSideMenu', 'showImage');
                                                            toggleShowHide_panel('hideImage', 'showImage');"/>
                                                </td>
                                                <td class="small settingsSelectedUI" valign=top align=left>
                                                    <script type="text/javascript">

                                                        function toggleShowHide_panel(showid, hideid) {
                                                            var show_ele = document.getElementById(showid);
                                                            var hide_ele = document.getElementById(hideid);
                                                            if (show_ele != null) {
                                                                show_ele.style.display = "";
                                                            }
                                                            if (hide_ele != null)
                                                                hide_ele.style.display = "none";
                                                        }

                                                    </script>	
                                                    <form name="EditView" method="GET" action="save-user" id="sub_form"   ">
                                                        
                                                        <input type="hidden" name="user.stt" value="">
                                                       
                                                        <input type="hidden" name="user.ngayThamGia" value="<s:property value="user.ngayThamGia"/>">

                                                        <table width="100%"  border="0" cellspacing="0" cellpadding="0" class="settingsSelUITopLine">
                                                            <tr><td align="left">
                                                                    <table class="settingsSelUITopLine" border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                        <tr>
                                                                            <td rowspan="2" width="50"><img src="themes/images/ico-users.gif" align="absmiddle"></td>
                                                                            <td>	
                                                                                <span class="lvtHeaderText">

                                                                                    <b><a href="">Quản trị viên </a> &gt;
                                                                                        Thêm Quản trị viên  
                                                                                        
                                                                                    </b></span>
                                                                            </td>
                                                                            <td rowspan="2" nowrap>&nbsp;
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td><b class="small">Thêm quản trị viên thông tin</b>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            <tr><td>&nbsp;</td></tr>
                                                            <tr>
                                                                <td nowrap align="right">
                                                                    <input title="Lưu [Alt+S]" accesskey="S" class="small crmbutton save"  name="button" value="  Lưu  "  onclick="
                                                                        if(validate(this.form)){
                                                                            //alert('hello');
                                                                            document.getElementById('sub_form').submit();
                                                                        }
                                                                        else {
                                                                            //alert('hello2');
                                                                            return false;
                                                                        }
                                                                        
                                                                        " style="width: 70px;" type="button" />
                                                                    <input title="Hủy bỏ [Alt+X]" accesskey="X" class="small crmbutton cancel" name="button" value="  Hủy bỏ  " onclick="window.history.back()" style="width: 70px;" type="button" />

                                                                </td>
                                                            </tr>
                                                            <tr><td class="padTab" align="left">
                                                                    <table width="100%" border="0" cellpadding="0" cellspacing="0">

                                                                        <tr><td colspan="2">
                                                                                <table align="center" border="0" cellpadding="0" cellspacing="0" width="99%">
                                                                                    <tr>
                                                                                        <td align="left" valign="top">
                                                                                            <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                                                                <tr><td align="left">
                                                                                                        <br>
                                                                                                        <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                                                            <tr>
                                                                                                                <td class="big"><strong>Thông tin quản trị viên</strong></td><td class="small" align="right">&nbsp;</td>
                                                                                                            </tr>
                                                                                                        </table>
                                                                                                        <table border="0" cellpadding="5" cellspacing="0" width="100%">
                                                                                                            <!-- Handle the ui types display -->


                                                                                                            <!-- Added this file to display the fields in Create Entity page based on ui types  -->
                                                                                                            <tr style="height:25px">

                                                                                                                <td width=20% class="dvtCellLabel" align=right>
                                                                                                                    <font color="red">*</font>Mã tài khoản		
                                                                                                                </td>
                                                                                                                <td width=30% align=left class="dvtCellInfo">
                                                                                                                    <input type="text"  name="id" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                                                                </td>

                                                                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                    <font color="red">*</font>Mật khẩu		
                                                                                                                </td>
                                                                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                    <input type="password"  name="pw" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">

                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr style="height:25px">

                                                                                                                <td width=20% class="dvtCellLabel" align=right>
                                                                                                                    <font color="red"></font>Họ tên			
                                                                                                                </td>
                                                                                                                <td width=30% align=left class="dvtCellInfo">
                                                                                                                    <input type="text"  name="user.hoTen" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                                                                </td>

                                                                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                    <font color="red"></font>Chức danh			
                                                                                                                </td>
                                                                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                    <input type="text"  name="user.chucDanh" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">

                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr style="height:25px">

                                                                                                                <!-- Mandatory Email Fields -->			
                                                                                                                <td width=20% class="dvtCellLabel" align=right>
                                                                                                                    <font color="red"></font>Email 			 
                                                                                                                </td>
                                                                                                                <td width=30% align=left class="dvtCellInfo">
                                                                                                                    <input type="text" name="user.email" id ="email1" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                                                                </td>

                                                                                                                <!-- for Status field Disabled for nonadmin -->
                                                                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                    <font color="red"></font>Số điện thoại			
                                                                                                                </td>
                                                                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                    <input type="text" name="user.sdt" id ="email1" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
<!--                                                                                                                    <select id="user_status" name="status" tabindex="" class="small">

                                                                                                                        <option value="Active" selected >
                                                                                                                            Hoạt động
                                                                                                                        </option>
                                                                                                                        <option value="Inactive"  >
                                                                                                                            Ngừng hoạt động
                                                                                                                        </option>
                                                                                                                    </select>-->
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr style="height:25px">

                                                                                                                <td width=20% class="dvtCellLabel" align=right><font color="red"></font>Địa chỉ</td>

                                                                                                                <td width=30% align=left class="dvtCellInfo"><input type="text" tabindex="" name="user.diaChi" id ="first_name" value="<s:property value="user.diaChi"/>" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'"></td>

                                                                                                                <!-- for currency in users details-->	
                                                                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                    <font color="red">*</font>Quyền			
                                                                                                                </td>
                                                                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                    <select name="user.permission" tabindex="" class="small">
                                                                                                                            <option value="0" selected>Quản trị hệ thống</option>
                                                                                                                            <option value="1" >Người dùng</option>
                                                                                                                            <option value="3" >Nhóm kinh doanh</option>
                                                                                                                        
                                                                                                                        <!-- code added to pass Currency field value, if Disabled for nonadmin -->
                                                                                                                        <!--code ends -->
                                                                                                                    </select>
                                                                                                                    <!-- code added to pass Currency field value, if Disabled for nonadmin -->
                                                                                                                    <!--code ends -->
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            <tr style="height:25px">

                                                                                                                <td width=20% class="dvtCellLabel" align=right>
                                                                                                                    <font color="red">*</font>Trạng thái			
                                                                                                                </td>
                                                                                                                <td width=30% align=left class="dvtCellInfo">
                                                                                                                    <select name="user.status" tabindex="" class="small">
                                                                                                                        
                                                                                                                            <option value="true" selected>Hoạt động</option>
                                                                                                                            <option value="false" >Không hoạt động</option>
                                                                                                                        
                                                                                                                        <!-- code added to pass Currency field value, if Disabled for nonadmin -->
                                                                                                                        <!--code ends -->
                                                                                                                    </select>
                                                                                                                </td>

                                                                                                                <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
                                                                                                                <td width="20%" class="dvtCellLabel" align=right>
                                                                                                                    <font color="red"></font>
                                                                                                                    Ghi chú			
                                                                                                                </td>
                                                                                                                <td width="30%" align=left class="dvtCellInfo">
                                                                                                                    <input type="text" name="user.ghiChu" id ="email1" value="" tabindex="" class=detailedViewTextBox onFocus="this.className = 'detailedViewTextBoxOn'" onBlur="this.className = 'detailedViewTextBox'">
                                                                                                                </td>
                                                                                                            </tr>
                                                                                                            
                                                                                                        </table>
                                                                                                        <br>


                                                                                                <tr>
                                                                                                    <td colspan=4 align="right">
                                                                                                        <input title="Lưu [Alt+S]" accesskey="S" class="small crmbutton save"  name="button" value="  Lưu  "  onclick="
                                                                                                            if(validate(this.form)){
                                                                                                                //alert('hello');
                                                                                                                document.getElementById('sub_form').submit();
                                                                                                            }
                                                                                                            else {
                                                                                                                //alert('hello2');
                                                                                                                return false;
                                                                                                            }
                                                                                                            
                                                                                                               " style="width: 70px;" type="button" />
                                                                                                        <input title="Hủy bỏ [Alt+X]" accesskey="X" class="small crmbutton cancel" name="button" value="  Hủy bỏ  " onclick="window.history.back()" style="width: 70px;" type="button" />
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </table>
                                                                                        </td></tr>
                                                                                </table>
                                                                            </td></tr>
                                                                    </table>
                                                                    <br>
                                                                </td></tr>
                                                            <tr><td class="small"><div align="right"><a href="#top"></a></div></td></tr>
                                                        </table>
                                                    </form>
                                                                                                                
                                                      
                                                </td>
                                            </tr>
                                        </table>
                                        </form>	
                                    </td>
                                </tr>
                            </table>
                    </td></tr>
        </table>
        <br>
        
        <script type='text/javascript' language='JavaScript'>

                function validate(form) {
                   //1. pw
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
                        alert("Hãy nhập mật khẩu mới cho người dùng.");
                        return false;
                    }

                    //2. ID
                    var id_length = form.id.value.length;
                    var id_value = form.id.value;
                    
                    if(id_length < 3 || id_length > 20){
                        alert("Tài khoản phải từ 3-20 ký tự.");
                        return false;
                    }

//                    for(int i = 0; i < pw_length; i++ ){
//                        if(pw_value.charAt(i) != )
//                    }

                    if (trim(id_value) == "") {
                        alert("Hãy nhập Mã tài khoản cho người dùng.");
                        return false;
                    }
               
                    if ( /[^A-Za-z\d\_]/.test(id_value)) {
                        alert("Mã tài khoản không được chứa ký tự đặc biệt");
                        //document.formname.txt.focus();
                        return (false);
                    }

                    var passhash = CryptoJS.MD5(pw_value).toString(); 
                    form.pw.value = passhash;
                    
                    return true;
                }
            </script>  
            
        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>
    </body>
</html>

