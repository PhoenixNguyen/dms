<%-- 
    Document   : system-manager-admin-change-password
    Created on : May 16, 2014, 3:12:47 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
    <HEAD>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phần mềm quản lý HOSCO-MANAGEMENT</title><style type="text/css">@import url("themes/softed/style.css"); </style>
        
        <script type='text/javascript' language='JavaScript'>
            var status = <s:property value="changePWStatus"/>
            if(status == "true"){
                alert('Đã lưu');
                window.close();
            }
//            if(status == "false")
//                alert('Thay đổi mật khẩu');
            
        </script>
    </HEAD>
    <BODY leftMargin="5" topMargin="5" MARGINHEIGHT="0" MARGINWIDTH="0">
        <script type='text/javascript' src="include/js/general.js"></script>
        <script language="JavaScript" type="text/javascript" src="js/md5.js"></script>
        
        <script type='text/javascript' language='JavaScript'>

            function set_password(form) {
                if (form.is_admin.value == 1 && trim(form.old_password.value) == "") {
                    alert("Hãy nhập mật khẩu cũ của bạn.");
                    return false;
                }
                if (trim(form.new_password.value) == "") {
                    alert("Hãy nhập mật khẩu mới của bạn.");
                    return false;
                }
                if (trim(form.confirm_new_password.value) == "") {
                    alert("Hãy xác nhận lại mật khẩu.");
                    return false;
                }

                if (trim(form.new_password.value) == trim(form.confirm_new_password.value)) {
                    if (form.is_admin.value == 1)
                        window.opener.document.DetailView.old_password.value = form.old_password.value;
                    
                    //Encode
                    var new_password_value = form.new_password.value;

                    var passhash = CryptoJS.MD5(new_password_value).toString(); 
                    form.new_password.value = passhash;
                    //console.log(passhash);
                    
                    window.opener.document.DetailView.changepassword.value = 'true';
                    return true;
                }
                else {
                    alert("Hãy nhập lại mật khẩu.  Giá trị của \"new password\" và \"confirm password\" không trùng khớp.");
                    return false;
                }
            }
        </script>

        <form name="ChangePassword" onsubmit="VtigerJS_DialogBox.block();">

            <table width='100%' cellspacing='0' cellpadding='5' border='0' class="small">
                <tr>
                    <td class="detailedViewHeader" colspan="2"><b>Thay đổi mật khẩu</b></td>
                </tr>
                <input name='old_password' type='hidden'><input name='is_admin' type='hidden' value='0'>
                <td width='40%' class='dvtCellLabel' nowrap align="right"><b>Mật khẩu mới</b></td>
                <td width='60%' class='dvtCellInfo'><input name='new_password' type='password' tabindex='1' size='15'></td>

                <tr>
                    <td width='40%' class='dvtCellLabel' nowrap align="right"><b>Xác nhận Mật khẩu</b></td>
                    <td width='60%' class='dvtCellInfo'><input name='confirm_new_password' type='password' tabindex='1' size='15'></td>
                </tr>
                <tr>
                    <td width='40%' class='dataLabel'></td>
                    <td width='60%' class='dvtCellInfo'></td>

                </tr>
            </table>
            <br>
            <table width='100%' cellspacing='0' cellpadding='1' border='0'>
                <tr>
                    <td align='right'><input title='Lưu [Alt+S]' accessKey='S' class='crmbutton small save' LANGUAGE=javascript onclick='if (set_password(this.form))
                        window.close();
                        else
                            return false;' type='submit' name='button' value='  Lưu  '>
                    </td>
                    <td align='left'><input title='Hủy bỏ [Alt+X]' accessyKey='X' class='crmbutton small cancel' LANGUAGE=javascript onclick='window.close()' type='submit' name='button' value='  Hủy bỏ  '></td>
                </tr>

                <script language="JavaScript">
                    document.ChangePassword.new_password.focus();
                </script>
            </table>
        </form>
        <br>

        <!-- stopscrmprint -->		
        <script>
            var userDateFormat = "dd-mm-yyyy";
            var default_charset = "UTF-8";
        </script>
    </BODY>
</HTML>
