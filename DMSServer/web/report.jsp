<%-- 
    Document   : report
    Created on : May 10, 2014, 9:59:46 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>

<html>
    <head>
        <title>admin - Phân tích - Báo cáo - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
            <s:param name="page_param" value="'report'" />
        </s:include>

        
        <!-- Toolbar -->
        <TABLE border=0 cellspacing=0 cellpadding=0 width=100% class=small>
            <tr><td style="height:2px"></td></tr>
            <tr>
                <td class=small width="60%">

                    <table border=0 cellspacing=0 cellpadding=0>
                        <tr>
                            <td><script type="text/javascript" src="modules/Reports/Reports.js"></script>

                                <TABLE border=0 cellspacing=0 cellpadding=0 width=100% class=small>
                                    <tr><td style="height:2px"></td></tr>
                                    <tr>
                                        <td style="padding-left:10px;padding-right:50px" class="moduleName" nowrap>Phân tích > <a class="hdrLink" href="index.php?action=ListView&module=Reports&parenttab=Analytics">Báo cáo</a></td>
                                        <td width=100% nowrap>

                                            <table border="0" cellspacing="0" cellpadding="0" >
                                                <tr>
                                                    <td class="sep1" style="width:1px;"></td>
                                                    <td class=small >
                                                        <!-- Add and Search -->
                                                        <table border=0 cellspacing=0 cellpadding=0>
                                                            <tr>
                                                                <td>
                                                                    <table border=0 cellspacing=0 cellpadding=5>
                                                                        <tr>
<!--                                                                            <td style="padding-right:0px;padding-left:10px;"><img src="themes/images/btnL3Add-Faded.gif" border=0></td>	

                                                                            <td style="padding-right:10px"><img src="themes/images/btnL3Search-Faded.gif" border=0></td>-->

                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td style="width:20px;">&nbsp;</td>
                                                    <td class="small">
                                                        <!-- Calendar Clock Calculator and Chat -->
                                                        <table border=0 cellspacing=0 cellpadding=5>
                                                            <tr>

<!--
                                                                <td style="padding-right:0px;padding-left:10px;"><img src="themes/images/btnL3Calendar-Faded.gif"></td>
                                                                <td style="padding-right:0px"><a href="javascript:;"><img src="themes/softed/images/btnL3Clock.gif" alt="Hiện đồng hồ..." title="Hiện đồng hồ..." border=0 onClick="fnvshobj(this, 'wclock');"></a></a></td> 
                                                                <td style="padding-right:0px"><a href="#"><img src="themes/softed/images/btnL3Calc.gif" alt="Mở máy tính..." title="Mở máy tính..." border=0 onClick="fnvshobj(this, 'calculator_cont');
                                                                        fetch_calc();"></a></td> 
                                                                <td style="padding-right:0px"><a href="javascript:;" onClick='return window.open("index.php?module=Home&action=vtchat", "Chat", "width=600,height=450,resizable=1,scrollbars=1");'><img src="themes/softed/images/tbarChat.gif" alt="Tán gẫu..." title="Tán gẫu..." border=0></a> 
                                                                </td>
                                                                <td style="padding-right:10px"><img src="themes/softed/images/btnL3Tracker.gif" alt="Xem gần nhất" title="Xem gần nhất" border=0 onClick="fnvshobj(this, 'tracker');">
                                                                </td>	-->
                                                            </tr>
                                                        </table>
                                                    </td>
                                                    <td style="width:20px;">&nbsp;</td>
                                                    <td class="small">
                                                        <!-- Import / Export -->
                                                        <table border=0 cellspacing=0 cellpadding=5>
                                                            <tr>
<!--                                                                <td style="padding-right:0px;padding-left:10px;"><img src="themes/images/tbarImport-Faded.gif" border="0"></td>
                                                                <td style="padding-right:10px"><img src="themes/images/tbarExport-Faded.gif" border="0"></td>
                                                                <td style="padding-right:10px"><img src="themes/images/FindDuplicates-Faded.gif" border="0"></td>-->
                                                            </tr>
                                                        </table>	
                                                    <td style="width:20px;">&nbsp;</td>
                                                    <td class="small">
                                                        <!-- All Menu -->
                                                        <table border=0 cellspacing=0 cellpadding=5>
                                                            <tr>
<!--                                                                <td style="padding-left:10px;"><a href="javascript:;" onmouseout="fninvsh('allMenu');" onClick="fnvshobj(this, 'allMenu')"><img src="themes/softed/images/btnL3AllMenu.gif" alt="Mở tất cả Menu..." title="Mở tất cả Menu..." border="0"></a></td>-->
                                                            </tr>
                                                        </table>
                                                    </td>			
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </TABLE></td>		
                            <td style="width:20px">&nbsp;</td>
                            <td>
                                <table border=0 cellspacing=0 cellpadding=0>
                                    <tr>
<!--                                        <td style="padding-right:5px"><a href="javascript:;" onclick="gcurrepfolderid = 0;
                                        fnvshobj(this, 'reportLay');"><img src="themes/softed/images/reportsCreate.gif" alt="Tạo Báo cáo..." title="Tạo Báo cáo..." border=0></a></td>
                                        <td>&nbsp;</td>
                                        <td style="padding-right:5px"><a href="javascript:;" onclick="createrepFolder(this, 'orgLay');"><img src="themes/softed/images/reportsFolderCreate.gif" alt="Tạo thư mục mới..." title="Tạo thư mục mới..." border=0></a></td>
                                        <td>&nbsp;</td>
                                        <td style="padding-right:5px"><a href="javascript:;" onclick="fnvshobj(this, 'folderLay');"><img src="themes/softed/images/reportsMove.gif" alt="Di chuyển báo cáo..." title="Di chuyển báo cáo..." border=0></a></td>
                                        <td>&nbsp;</td>
                                        <td style="padding-right:5px"><a href="javascript:;" onClick="massDeleteReport();"><img src="themes/softed/images/reportsDelete.gif" alt="Xóa thư mục..." title="Xóa báo cáo..." border=0></a></td>-->
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>

                </td>
            </tr>
            <tr><td style="height:2px"></td></tr>
        </TABLE>


        <div id="reportContents">
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="99%"  class="showPanelBg">
                <tbody>
                    <tr>
                        <td valign=top><img src="themes/softed/images/showPanelTopLeft.gif"></td>
                        <td valign="top" width="50%" style="padding: 10px;border-right:1px dashed #CCCCCC">

                            <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="reportsListTable">
                                <tr>
                                    <td class="mailSubHeader"><b>

                                            Báo cáo về sản phẩm
                                        </b>
                                        <i><font color='#C0C0C0'>
                                            - Báo cáo về sản phẩm
                                            </font></i>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table  class="small" border="0" cellpadding="5" cellspacing="1" width="100%">
                                            <tbody>
                                                <tr>
                                                    <td class="lvtCol" width="5%">#</td>
                                                    <td class="lvtCol" width="35%">Tên báo cáo</td>
                                                    <td class="lvtCol" width="50%">Mô tả</td>
                                                </tr>
<!--                                                <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                    <td>1</td>
                                                    <td><a href="">Thông tin sản phẩm</a>
                                                    </td>
                                                    <td>Báo cáo chi tiết sản phẩm</td>
                                                    
                                                </tr>
                                                <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                    <td>2</td>
                                                    <td><a href="">Sản phẩm theo Liên hệ</a>
                                                    </td>
                                                    <td>Sản phẩm liên quan tới Liên hệ</td>
                                                    
                                                </tr>-->

                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br />
                            
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" align="center" class="reportsListTable">
                                <tr>
                                    <td class="mailSubHeader"><b>

                                            Báo cáo về Đặt hàng
                                        </b>
                                        <i><font color='#C0C0C0'>
                                            - Báo cáo về Đặt hàng
                                            </font></i>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table  class="small" border="0" cellpadding="5" cellspacing="1" width="100%">
                                            <tbody>
                                                <tr>
                                                    <td class="lvtCol" width="5%">#</td>
                                                    <td class="lvtCol" width="35%">Tên báo cáo</td>
                                                    <td class="lvtCol" width="50%">Mô tả</td>
                                                </tr>
                                                <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                    <td>1</td>
                                                    <td><a href="report-takeorder">Báo cáo chi tiết Đặt hàng</a>
                                                    </td>
                                                    <td>Báo cáo chi tiết Đặt hàng</td>
                                                    
                                                </tr>

                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                            <br />

                            <br />
                            <!-- Reports Table Ends Here  -->
                        </td>
                        <td style="padding:10px;" valign="top" align="center" width="50%">
                            <div id="customizedrep">
                                <!-- Customized Reports Table Starts Here  -->
                                <form>
                                    <input id="folder_ids" name="folderId" type="hidden" value='12,13,14'>

                                    <table class="reportsListTable" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">		
                                        <tr>
                                            <td class="mailSubHeader" align="left" colspan="3" style="font-weight:bold;">
                                                <span id='folder12'> B&aacute;o c&aacute;o về Tồn kho</span>
                                                <i><font color='#C0C0C0'>
                                                    - B&aacute;o c&aacute;o về Tồn kho
                                                    </font></i>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td  class="hdrNameBg" colspan="3" style="padding: 5px;" align="right" >
                                                <!-- Custom Report Group's Buttons -->
                                                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                                                    <tr>

                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <table  border="0" cellpadding="3" cellspacing="1" width="100%">
                                                    <tbody>
                                                        <tr>
                                                            <td class="lvtCol" width="5%">
                                                                #
                                                            </td>
                                                            <td class="lvtCol" align="left" width="35%">Tên báo cáo</td>
                                                            <td class="lvtCol" align="left" width="50%">Mô tả</td>
                                                        </tr>
<!--                                                        <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                            <td>
                                                                1
                                                            </td>
                                                            <td align="left"><a href="">B&aacute;o c&aacute;o tồn kho sản phẩm</a>
                                                            </td>
                                                            <td align="left"></td>
                                                            
                                                        </tr>-->
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br />

                                    <table class="reportsListTable" align="center" border="0" cellpadding="0" cellspacing="0" width="100%">		
                                        <tr>
                                            <td class="mailSubHeader" align="left" colspan="3" style="font-weight:bold;">
                                                <span id='folder13'> B&aacute;o c&aacute;o về Bán hàng</span>
                                                <i><font color='#C0C0C0'>
                                                    - B&aacute;o c&aacute;o về Bán hàng
                                                    </font>
                                                </i>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td  class="hdrNameBg" colspan="3" style="padding: 5px;" align="right" >
<!--                                                 Custom Report Group's Buttons -->
                                                <table width="100%" border="0" cellpadding="0" cellspacing="0"><tr>

                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <table  border="0" cellpadding="3" cellspacing="1" width="100%">
                                                    <tbody>
                                                        <tr>
                                                            <td class="lvtCol" width="5%">
                                                                #
                                                            </td>
                                                            <td class="lvtCol" align="left" width="35%">Tên báo cáo</td>
                                                            <td class="lvtCol" align="left" width="50%">Mô tả</td>
                                                        </tr>
                                                        <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                            <td>
                                                                1
                                                            </td>
                                                            <td align="left"><a href="report-saleorder">Báo cáo bán hàng theo hóa đơn</a>
                                                            </td>
                                                            <td align="left">Báo cáo bán hàng theo hóa đơn</td>
                                                            
                                                        </tr>
                                                        
                                                        <tr class="lvtColData" onmouseover="this.className = 'lvtColDataHover'" onmouseout="this.className = 'lvtColData'" bgcolor="white">
                                                            <td>
                                                                2
                                                            </td>
                                                            <td align="left"><a href="report-sale-products">Báo cáo bán hàng theo sản phẩm</a>
                                                            </td>
                                                            <td align="left">Báo cáo bán hàng theo sản phẩm</td>
                                                            
                                                        </tr>
                                                        
                                                    </tbody>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                    <br />

                                    <br />
                                </form>
                                
                            </div>
                        </td>
                        <td valign=top><img src="themes/softed/images/showPanelTopRight.gif"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">&nbsp;</td>
                    </tr>
                </tbody>
            </table>
        </div>
   
        <br><br><br>

        <!--    Footer-->
        <s:include value="footer.jsp"></s:include>




    </body>
</html>
