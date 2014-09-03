<%-- 
    Document   : input-page
    Created on : Feb 24, 2014, 12:59:04 AM
    Author     : HP
--%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page language="java" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lý thông tin khách hàng</title>
        
        <link type="text/css" rel="stylesheet" href="../css/admin/input-page.css"/>
        
<!--        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js" ></script>-->
        <script type="text/javascript" src="../js/jquery.min.js"></script>
        <script type="text/javascript" src="jscommon/jquery.min.js"></script>
        
        <script>
            
        </script>
        
        <%
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

        %>
        
        <script>
            function importStaffs(){
    console.log('GET');
    //$("#info").append('hello');
    $.getJSON('add-staff',
        function(data){
            console.log('GET2');
            var staffsTotal = (data.staffsTotal);
            console.log(staffsTotal);
            
            var option = $("#info");
            option.find('span').remove();
            var alert = "Có " + staffsTotal + " nhân viên được thêm!";
            option.append($("<span/>").append(alert));
            
        }
    );
}
        
        </script>
    </head>
    <body>
        <h1>Quản lý thông tin khách hàng</h1>
        <div id="head_data">
            <div id="slect_item">
                Chọn mục dữ liệu
                <s:select name="giamDocId" list="{'Khách hàng', 'Nhân viên', 'Sản phẩm'}" id="sele"  listKey="giamDocId" 
                          onchange="selectObject(selectedIndex)"  headerKey="0"  value="Khách hàng"/>
<!--                <select>
                    <option selected="1">Khách hàng</option>
                    <option>Nhân viên</option>
                    <option>Sản phẩm</option>
                </select>-->
            </div>
            <div id="load">
                <div>Thêm dữ liệu</div> 
                <div>
                    <s:actionerror />
                    <s:form action="upload" onsubmit="uploadData()" id="login_form" enctype="multipart/form-data" validate="true"
                            cssStyle="position: absolute;
                                   margin-top: -50px;
                                   margin-left: 60px;"
                            >  

                        <s:file type="file" name="document.file" id="upfile"/>
                        <br/>
                        <br/>
                        <s:submit                           
                               />

                    </s:form>       
                </div>
                    <div id="import">
                        <input type="button" id="imp" onClick="importStaffs()" value="Import" > 
                        <div id="info">STATUS: 
                            <span></span>
                        </div>    
                    </div>
<!--                <button>load</button>-->
            </div>
            <div id="txt">
                Sửa thông tin
            </div>
            
        </div>
        
        
    </body>
</html>
