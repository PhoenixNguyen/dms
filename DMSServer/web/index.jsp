<%-- 
    Document   : index
    Created on : Apr 9, 2014, 10:11:04 PM
    Author     : HP
--%>


<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN">
<html>
    <head>
        <link REL="SHORTCUT ICON" HREF="themes/images/vtigercrm_icon.ico">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOSCO-MANAGEMENT</title>

        <style type="text/css">@import url("include/style.css");</style>
        <style type="text/css">@import url("themes/softed/style.css");</style>

        <script language="JavaScript" type="text/javascript" src="js/jquery.min.js"></script>
        <script language="JavaScript" type="text/javascript" src="js/md5.js"></script>
        
        <%
            if (session.getAttribute("authorized") != null && (Boolean) session.getAttribute("authorized")) {


        %>
        <jsp:forward page="home.jsp"></jsp:forward>
        <%            }

        %>

    </head>
    <body onload=set_focus() style="padding:0; margin:0;"><!-- startscrmprint --><!--Added to display the footer in the login page by Dina-->
        <script type="text/javascript" language="JavaScript">
<!-- Begin -->
            function set_focus() {
                if (document.DetailView.user_name.value != '') {
                    document.DetailView.user_password.focus();
                    document.DetailView.user_password.select();
                }
                else
                    document.DetailView.user_name.focus();
            }
<!-- End -->
        </script>


        <br><br>
        <div align="center">	
            <table border="0" cellpadding="0" cellspacing="0" width="700">
                <tr>
                    <!--			<td align="right"><img src="themes/images/honestCRMTop.gif"></td>-->
                </tr>
            </table>
            <!-- key to check session_out in Ajax key=s18i14i22a19 -->
            <!-- Login Starts -->
            <table border="0" cellspacing="0" cellpadding="0" width=700>
                <tr>
                    <td class="bg" width="50%">
                        <img src="themes/images/hoscoName.gif" alt="HOSCO-MANAGEMENT" title="HOSCO-MANAGEMENT">
                    </td>
                    <td class="bg" align="right" width="50%">
                    </td>
                </tr>
                <tr>
                    <td class="small z1" align="center">
                        <img src="themes/images/bullets.gif"> 
                    </td>
                    <td class="small z2" align="center">
                        <!-- Sign in form -->
                        <br>
                        <form action="login" method="post" name="DetailView" id="form">
                            <input type="hidden" name="module" value="Users">
                            <input type="hidden" name="action" value="Authenticate">
                            <input type="hidden" name="return_module" value="Users">
                            <input type="hidden" name="return_action" value="Login">
                            <table border="0" cellpadding="0" cellspacing="0" width="80%">
                                <tr>
                                    <td class="signinHdr"><!--<img src="themes/images/signin.gif" alt="Sign in" title="Sign in">-->ĐĂNG NHẬP</td>
                                </tr>
                                <tr>
                                    <td class="small">
                                        <!-- form elements -->
                                        <br>
                                        <table border="0" cellpadding="5" cellspacing="0" width="100%">
                                            <tr bgcolor="#f5f5f5">
                                                <td class="small" align="right" width="30%">Tên đăng nhập<!--Người dùng--></td>
                                                <td class="small" align="left" width="70%"><input class="small" type="text" name="user_name"  tabindex="1"></td>
                                            </tr>
                                            <tr bgcolor="#f5f5f5">
                                                <td class="small" align="right" width="30%">Mật khẩu<!--Mật khẩu--></td>
                                                <td class="small" align="left" width="70%">
                                                    <input class="small" type="password" size='20' name="user_password"  tabindex="2">
                                                </td>
                                            </tr>
                                            <!--                                            <tr bgcolor="#f5f5f5">
                                                                                            <td class="small" align="right" width="30%">Hiển thịMàu nền</td>
                                                                                            <td class="small" align="left" width="70%"><select class="small" name='login_theme' style="width:70%" tabindex="3">
                                                                                                     
                                                                                                    <OPTION value='alphagrey'>alphagrey</OPTION>
                                                                                                    <OPTION value='bluelagoon'>bluelagoon</OPTION>
                                                                                                    <OPTION selected value='softed'>softed</OPTION>
                                                                                                    <OPTION value='woodspice'>woodspice</OPTION> 
                                                                                                    <option selected="" value="softed">HOSCO-THEME</option>
                                                                                                </select></td>
                                                                                        </tr>
                                                                                        <tr bgcolor="#f5f5f5">
                                                                                            <td class="small" align="right" width="30%">Ngôn ngữNgôn ngữ</td>
                                                                                            <td class="small" align="left" width="70%"><select class="small" name='login_language' style="width:70%" tabindex="4">
                                                                                                     vtlib Customization 
                                                                                                     
                                                                                                        <OPTION value='en_us'>US English</OPTION>
                                                                                                        <OPTION selected value='vn'>Vietnamese</OPTION> 
                                                                                                    <option value="vn">Tiếng Việt</option>
                                                                                                </select></td>		
                                                                                        </tr>-->

                                            <tr  >
                                            <div id="status" style="display : none;"><font color="red"> Tên đăng nhập hoặc mật khẩu không hợp lệ</font>
                                            </div>
                                            <!--                                                <td class="small" align="left" width="70%"><select class="small" name='login_language' style="width:70%" tabindex="4">
                                                                                                     vtlib Customization 
                                                                                                     
                                                                                                        <OPTION value='en_us'>US English</OPTION>
                                                                                                        <OPTION selected value='vn'>Vietnamese</OPTION> 
                                                                                                    <option value="vn">Tiếng Việt</option>
                                                                                                </select></td>		-->
                                </tr>
                                <tr>
                                    <td class="small">&nbsp;</td>
                                    <td class="small">
                                        <input title="Đăng nhập [Alt+L]" alt="Đăng nhập [Alt+L]" accesskey="Đăng nhập [Alt+L]" src="themes/images/btnSignInNEW.gif" type="image" name="Login" value="  Đăng nhập  "  tabindex="5"
                                               onclick="
                                                    if(encode(this.form)){
                                                        //alert('hello');
                                                        document.getElementById('form').submit();
                                                    }
                                                    else {
                                                        //alert('hello2');
                                                        return false;
                                                    }
                                                    "
                                               >
                                    </td>
                                </tr>
                            </table>
                            <br><br>
                            </td>
                            </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
        </div>


        <script>
            console.log("status ");

            //var user = <s:property value="%{#attr.user_name}"/>;

            var status = false;
            var click = false;
            status = <s:property value="%{#attr.authorized}"/>;

            click = <s:property value="%{#attr.click}"/>;
            console.log(" status " + status + click);

            if (click) {
                console.log(" passed1 ");
                if (status === "false") {
                    console.log(" passed2 ");
                    document.getElementById("status").style.display = "block";

                }
            }

        </script>

        <script type='text/javascript' language='JavaScript'>

            function encode(form) {

//                    var user_password_length = form.user_password.value.length;
                var user_password_value = form.user_password.value;

                var passhash = CryptoJS.MD5(user_password_value).toString(); 
                form.user_password.value = passhash;
                //console.log(passhash);

                return true;
            }
        </script>  
        <br><br><br>
        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>

    </body>
</html>
