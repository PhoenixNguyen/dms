<%-- 
    Document   : demo_validate_user
    Created on : Jun 3, 2014, 11:14:20 AM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><head>
	<title>admin - Thiết lập - Người sử dụng - Phần mềm quản lý HOSCO-MANAGEMENT</title>
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
	<a name="top"></a>
	<!-- header -->
	<!-- header-vtiger crm name & RSS -->
	<script language="JavaScript" type="text/javascript" src="include/js/json.js"></script>
	<script language="JavaScript" type="text/javascript" src="include/js/general.js"></script>
	<!-- vtlib customization: Javascript hook -->	
	<script language="JavaScript" type="text/javascript" src="include/js/vtlib.js"></script>
	<!-- END -->
	<script language="JavaScript" type="text/javascript" src="include/js/vn.lang.js?"></script>
	<script language="JavaScript" type="text/javascript" src="include/js/QuickCreate.js"></script>
	<script language="javascript" type="text/javascript" src="include/scriptaculous/prototype.js"></script>
	<script language="JavaScript" type="text/javascript" src="include/js/menu.js"></script>
	<script language="JavaScript" type="text/javascript" src="include/calculator/calc.js"></script>
	<script language="JavaScript" type="text/javascript" src="modules/Calendar/script.js"></script>
	<script language="javascript" type="text/javascript" src="include/scriptaculous/dom-drag.js"></script>
	<script language="JavaScript" type="text/javascript" src="include/js/notificationPopup.js"></script>
        <script type="text/javascript" src="jscalendar/calendar.js"></script>
        <script type="text/javascript" src="jscalendar/calendar-setup.js"></script>
        <script type="text/javascript" src="jscalendar/lang/calendar-vn.js"></script>
        
    <!-- asterisk Integration -->
        <!-- END -->

				<!-- Custom Header Script -->
					<script type="text/javascript" src="modules/Tooltip/TooltipHeaderScript.js"></script>
					<script type="text/javascript" src="modules/SMSNotifier/SMSNotifierCommon.js"></script>
					<script type="text/javascript" src="modules/ModComments/ModCommentsCommon.js"></script>
				<!-- END -->
			
	    <img src="themes/softed/images/layerPopupBg.gif" style="display: none;">
    
	<table border="0" cellspacing="0" cellpadding="0" width="100%" class="hdrNameBg">
	<tbody><tr>
		<td valign="top"><img src="themes/softed/images/vtiger-crm.gif" alt="HOSCO-MANAGEMENT" title="HOSCO-MANAGEMENT" border="0"></td>
		<td width="100%" align="center">
		                <marquee id="rss" direction="left" scrolldelay="10" scrollamount="3" behavior="scroll" class="marStyle" onmouseover="javascript:stop();" onmouseout="javascript:start();">&nbsp;admin :  Thông báo: Ngày 27/10. Bắt đầu làm đặc tả phần mềm cho MEDICC   </marquee>
                		
		</td>
		<td class="small" nowrap="">
			<table border="0" cellspacing="0" cellpadding="0">
			 <tbody><tr>
			
												
			<!-- gmailbookmarklet customization -->
			 <!--<td style="padding-left:10px;padding-right:10px" class=small nowrap>
				<a href='javascript:(function()%7Bvar%20doc=top.document;var%20bodyElement=document.body;doc.vtigerURL%20=%22http://hosgroup.com.vn/hoscomng/%22;var%20scriptElement=document.createElement(%22script%22);scriptElement.type=%22text/javascript%22;scriptElement.src=doc.vtigerURL+%22modules/Emails/GmailBookmarkletTrigger.js%22;bodyElement.appendChild(scriptElement);%7D)();'>Gmail Bookmarklet</a>
			 </td> -->
			 <!-- END -->
			  			 <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="javascript:void(0);" onclick="vtiger_news(this)">Tin HOSCO</a></td> -->
			 <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="javascript:void(0);" onclick="vtiger_feedback();">Phản hồi</a></td> -->
			 
			 <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap> <a href="index.php?module=Users&action=DetailView&record=1&modechk=prefview">Thiết lập cá nhân</a></td>-->
			 <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap><a href="http://wiki.hosgroup.com.vn/index.php/Main_Page" target="_blank">Hướng dẫn sử dụng online</a></td> -->
			 <!-- <td style="padding-left:10px;padding-right:10px" class=small nowrap><a href="javascript:;" onClick="openwin();">Giới thiệu</a></td> -->
			 <td style="padding-left:10px;padding-right:10px" class="small" nowrap=""> <a href="index.php?module=Users&amp;action=Logout">Thoát</a> (admin)</td>
			 </tr>
			</tbody></table>
		</td>
	</tr>
	</tbody></table>

<div id="miniCal" style="width:300px; position:absolute; display:none; left:100px; top:100px; z-index:100000">
</div>

<!-- header - master tabs -->
<table border="0" cellspacing="0" cellpadding="0" width="100%" class="hdrTabBg">
<tbody><tr>
	<td style="width:50px" class="small">&nbsp;</td>
	<td class="small" nowrap=""> 
		<table border="0" cellspacing="0" cellpadding="0">

		<tbody><tr>
			<td class="tabSeperator"><img src="themes/images/spacer.gif" width="2px" height="28px"></td>		
											  <td class="tabUnSelected" onmouseover="fnDropDown(this,'My Home Page_sub');" onmouseout="fnHideDrop('My Home Page_sub');" align="center" nowrap=""><a href="index.php?module=Home&amp;action=index&amp;parenttab=My Home Page">Trang chủ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Marketing_sub');" onmouseout="fnHideDrop('Marketing_sub');" align="center" nowrap=""><a href="index.php?module=Campaigns&amp;action=index&amp;parenttab=Marketing">Marketing</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Sales_sub');" onmouseout="fnHideDrop('Sales_sub');" align="center" nowrap=""><a href="index.php?module=Leads&amp;action=index&amp;parenttab=Sales">Bán hàng</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Support_sub');" onmouseout="fnHideDrop('Support_sub');" align="center" nowrap=""><a href="index.php?module=HelpDesk&amp;action=index&amp;parenttab=Support">Hỗ trợ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Analytics_sub');" onmouseout="fnHideDrop('Analytics_sub');" align="center" nowrap=""><a href="index.php?module=Reports&amp;action=index&amp;parenttab=Analytics">Phân tích</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Inventory_sub');" onmouseout="fnHideDrop('Inventory_sub');" align="center" nowrap=""><a href="index.php?module=Products&amp;action=index&amp;parenttab=Inventory">Tồn kho</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabUnSelected" onmouseover="fnDropDown(this,'Tools_sub');" onmouseout="fnHideDrop('Tools_sub');" align="center" nowrap=""><a href="index.php?module=Rss&amp;action=index&amp;parenttab=Tools">Công cụ</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
															  <td class="tabSelected" onmouseover="fnDropDown(this,'Settings_sub');" onmouseout="fnHideDrop('Settings_sub');" align="center" nowrap=""><a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings">Thiết lập</a><img src="themes/softed/images/menuDnArrow.gif" border="0" style="padding-left:5px"></td>
				  <td class="tabSeperator"><img src="themes/images/spacer.gif"></td>
										<td style="padding-left:10px" nowrap="">
									<select class="small" id="qccombo" style="width:110px" onchange="QCreate(this);">
						<option value="none">Khởi tạo nhanh...</option>
                                                <option value="Accounts">Tạo&nbsp;Khách hàng</option>
                                                <option value="Assets">Tạo&nbsp;SINGLE_Assets</option>
                                                <option value="Calendar">Tạo&nbsp;Tác vụ</option>
                                                <option value="Campaigns">Tạo&nbsp;Chiến dịch</option>
                                                <option value="ModComments">Tạo&nbsp;SINGLE_ModComments</option>
                                                <option value="Contacts">Tạo&nbsp;Liên hệ</option>
                                                <option value="Documents">Tạo&nbsp;Tài liệu</option>
                                                <option value="Events">Tạo&nbsp;Sự kiện</option>
                                                <option value="HelpDesk">Tạo&nbsp;Thẻ</option>
                                                <option value="Leads">Tạo&nbsp;Đầu mối</option>
                                                <option value="Potentials">Tạo&nbsp;Cơ hội</option>
                                                <option value="PriceBooks">Tạo&nbsp;Bảng giá</option>
                                                <option value="Products">Tạo&nbsp;Sản phẩm</option>
                                                <option value="ProjectMilestone">Tạo&nbsp;SINGLE_ProjectMilestone</option>
                                                <option value="ProjectTask">Tạo&nbsp;SINGLE_ProjectTask</option>
                                                <option value="ServiceContracts">Tạo&nbsp;Hợp đồng dịch vụ</option>
                                                <option value="Services">Tạo&nbsp;Dịch vụ</option>
                                                <option value="Vendors">Tạo&nbsp;Nhà cung cấp</option>
                        					</select>
					
			</td>
		</tr>

		</tbody></table>
	</td>
	<td align="right" style="padding-right:10px" nowrap="">
		<table border="0" cellspacing="0" cellpadding="0" id="search" style="border:1px solid #999999;background-color:white">
		   <tbody><tr>
			<form name="UnifiedSearch" method="post" action="index.php" style="margin:0px" onsubmit="VtigerJS_DialogBox.block();"></form>
			<td style="height:19px;background-color:#ffffef" nowrap="">
				<a href="javascript:void(0);" onclick="UnifiedSearch_SelectModuleForm(this);"><img src="themes/images/settings_top.gif" align="left" border="0"></a>
				<input type="hidden" name="action" value="UnifiedSearch" style="margin:0px">
				<input type="hidden" name="module" value="Home" style="margin:0px">
				<input type="hidden" name="parenttab" value="Settings" style="margin:0px">
				<input type="hidden" name="search_onlyin" value="--USESELECTED--" style="margin:0px">
				<input type="text" name="query_string" value="Tìm kiếm..." class="searchBox" onfocus="this.value=''">
			</td>
			<td style="background-color:#cccccc">
				<input type="submit" class="searchBtn" value="Tìm" alt="Tìm" title="Tìm">
			</td>
			
		   </tr>
		</tbody></table>
	</td>

</tr>
</tbody></table>
<!-- - level 2 tabs starts-->
<table border="0" cellspacing="0" cellpadding="2" width="100%" class="level2Bg">
<tbody><tr>
	<td>
		<table border="0" cellspacing="0" cellpadding="0">
		<tbody><tr>
			<!-- ASHA: Avoid using this as it gives module name instead of module label. 
			Now Using the same array QUICKACCESS that is used to show drop down menu
			(which gives both module name and module label)-->
			<!--																																																																      					 
      						 
      																		<td class="level2UnSelTab" nowrap> <a href="index.php?module=Settings&action=index&parenttab=Settings">Thiết lập</a> </td>
							
												-->
			
																																																																				   					
	   												   							
	   									
													<td class="level2UnSelTab" nowrap=""> <a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings">Thiết lập</a> </td>
							
												   					
	   												   						   							   							
	   									
													<td class="level2UnSelTab" nowrap=""> <a href="index.php?module=Settings&amp;action=ModuleManager&amp;parenttab=Settings">Quản lý phân hệ</a> </td>
							
														</tr>
		</tbody></table>
	</td>
</tr>
</tbody></table>		
<!-- Level 2 tabs ends -->
<div id="calculator_cont" style="position:absolute; z-index:10000"></div>
	

<div id="wclock" style="z-index: 10000001; left: 0px; top: 0px;" class="layerPopup">
	<table class="mailClientBg" align="center" border="0" cellpadding="5" cellspacing="0" width="100%">
	<tbody><tr style="cursor:move;">
		<td style="text-align:left;" id="Handle"><b>Đồng hồ</b></td>
		<td align="right">
			<a href="javascript:;">
				<img src="themes/images/close.gif" border="0" onclick="fninvsh('wclock')" hspace="5" align="absmiddle">
			</a>
		</td>
	</tr>
	</tbody></table>
	<table class="hdrNameBg" align="center" border="0" cellpadding="2" cellspacing="0" width="100%">
	<tbody><tr>
	<td nowrap="nowrap" colspan="2">
	<div style="background-image: url(http://hosgroup.com.vn/hoscomng/themes/images/clock_bg.gif); background-position: 4px 38px; background-repeat: no-repeat;" id="theClockLayer">
<div id="theCities" class="citystyle" style="top: 7px; left: 5px;">
<form action="" name="frmtimezone">
<input name="PHPSESSID" value="162c0ab587f6c555aaaa30d681b61f7c" type="hidden">
<select name="clockcity" size="1" class="importBox small" id="clockcity" style="width:125px;" onchange="lcl(this.selectedIndex,this.options[0].selected)">
<option value="0" selected="selected">Local time</option>
<option value="4.30">Afghanistan</option>
<option value="1">Algeria</option>
<option value="-3">Argentina</option>
<option value="9.30">Australia - Adelaide</option>
<option value="8">Australia - Perth</option>
<option value="10">Australia - Sydney</option>
<option value="1">Austria</option>
<option value="3">Bahrain</option>
<option value="6">Bangladesh</option>
<option value="1">Belgium</option>
<option value="-4">Bolivia</option>
<option value="-5">Brazil - Andes</option>
<option value="-3">Brazil - East</option>
<option value="-4">Brazil - West</option>
<option value="2">Bulgaria</option>
<option value="6.30">Burma (Myanmar)</option>
<option value="-5">Chile</option>
<option value="-7">Canada - Calgary</option>
<option value="-3.30">Canada - Newfoundland</option>
<option value="-4">Canada - Nova Scotia</option>
<option value="-5">Canada - Toronto</option>
<option value="-8">Canada - Vancouver</option>
<option value="-6">Canada - Winnipeg</option>
<option value="8">China - Mainland</option>
<option value="8">China - Taiwan</option>
<option value="-5">Colombia</option>
<option value="-5">Cuba</option>
<option value="1">Denmark</option>
<option value="-5">Ecuador</option>
<option value="2">Egypt</option>
<option value="12">Fiji</option>
<option value="2">Finland</option>
<option value="1">France</option>
<option value="1">Germany</option>
<option value="0">Ghana</option>
<option value="2">Greece</option>
<option value="-3">Greenland</option>
<option value="1">Hungary</option>
<option value="5.30">India</option>
<option value="8">Indonesia - Bali, Borneo</option>
<option value="9">Indonesia - Irian Jaya</option>
<option value="7">Indonesia - Sumatra, Java</option>
<option value="3.30">Iran</option>
<option value="3">Iraq</option>
<option value="2">Israel</option>
<option value="1">Italy</option>
<option value="-5">Jamaica</option>
<option value="9">Japan</option>
<option value="3">Kenya</option>
<option value="9">Korea (North &amp; South)</option>
<option value="3">Kuwait</option>
<option value="1">Libya</option>
<option value="8">Malaysia</option>
<option value="5">Maldives</option>
<option value="1">Mali</option>
<option value="4">Mauritius</option>
<option value="-6">Mexico</option>
<option value="0">Morocco</option>
<option value="5.45">Nepal</option>
<option value="1">Netherlands</option>
<option value="12">New Zealand</option>
<option value="1">Nigeria</option>
<option value="1">Norway</option>
<option value="4">Oman</option>
<option value="5">Pakistan</option>
<option value="-5">Peru</option>
<option value="8">Philippines</option>
<option value="1">Poland</option>
<option value="1">Portugal</option>
<option value="3">Qatar</option>
<option value="2">Romania</option>
<option value="11">Russia - Kamchatka</option>
<option value="3">Russia - Moscow</option>
<option value="9">Russia - Vladivostok</option>
<option value="4">Seychelles</option>
<option value="3">Saudi Arabia</option>
<option value="8">Singapore</option>
<option value="2">South Africa</option>
<option value="1">Spain</option>
<option value="3">Syria</option>
<option value="5.30">Sri Lanka</option>
<option value="1">Sweden</option>
<option value="1">Switzerland</option>
<option value="7">Thailand</option>
<option value="12">Tonga</option>
<option value="2">Turkey</option>
<option value="3">Ukraine</option>
<option value="5">Uzbekistan</option>
<option value="7">Vietnam</option>
<option value="4">UAE</option>
<option value="0">UK</option>
<option value="-9">USA - Alaska</option>
<option value="-9">USA - Arizona</option>
<option value="-6">USA - Central</option>
<option value="-5">USA - Eastern</option>
<option value="-10">USA - Hawaii</option>
<option value="-5">USA - Indiana East</option>
<option value="-7">USA - Mountain</option>
<option value="-8">USA - Pacific</option>
<option value="3">Yemen</option>
<option value="1">Yugoslavia</option>
<option value="2">Zambia</option>
<option value="2">Zimbabwe</option>
</select>
</form>
</div>
<script type="text/javascript">
        var theme = "softed";
</script>
<script type="text/javascript" src="include/js/clock.js"></script><div id="theDate" class="datestyle" style="color: rgb(0, 0, 0); top: 142px; left: 0px;">June 3, 2014 11:17 AM</div><div id="amOrPm" class="ampmstyle" style="color: rgb(153, 153, 153); top: 87px; left: 52px;">AM</div><div id="theFace0" class="facestyle" style="color: rgb(0, 0, 0); top: 81px; left: 96px;">3</div><div id="theFace1" class="facestyle" style="color: rgb(0, 0, 0); top: 102px; left: 90.37306695894642px;">4</div><div id="theFace2" class="facestyle" style="color: rgb(0, 0, 0); top: 117.37306695894642px; left: 75px;">5</div><div id="theFace3" class="facestyle" style="color: rgb(0, 0, 0); top: 123px; left: 54px;">6</div><div id="theFace4" class="facestyle" style="color: rgb(0, 0, 0); top: 117.37306695894644px; left: 33.000000000000014px;">7</div><div id="theFace5" class="facestyle" style="color: rgb(0, 0, 0); top: 102px; left: 17.62693304105357px;">8</div><div id="theFace6" class="facestyle" style="color: rgb(0, 0, 0); top: 81px; left: 12px;">9</div><div id="theFace7" class="facestyle" style="color: rgb(0, 0, 0); top: 59.999999999999986px; left: 17.626933041053576px;">10</div><div id="theFace8" class="facestyle" style="color: rgb(0, 0, 0); top: 44.62693304105359px; left: 32.99999999999997px;">11</div><div id="theFace9" class="facestyle" style="color: rgb(0, 0, 0); top: 39px; left: 53.99999999999999px;">12</div><div id="theFace10" class="facestyle" style="color: rgb(0, 0, 0); top: 44.626933041053576px; left: 75px;">1</div><div id="theFace11" class="facestyle" style="color: rgb(0, 0, 0); top: 59.99999999999997px; left: 90.37306695894641px;">2</div><div id="H0" class="handsanddotsstyle" style="top: 87px; left: 60px; background-color: rgb(0, 0, 0);"></div><div id="H1" class="handsanddotsstyle" style="top: 80.48707702412582px; left: 57.434491412929916px; background-color: rgb(0, 0, 0);"></div><div id="H2" class="handsanddotsstyle" style="top: 73.97415404825166px; left: 54.86898282585983px; background-color: rgb(0, 0, 0);"></div><div id="H3" class="handsanddotsstyle" style="top: 67.46123107237749px; left: 52.30347423878974px; background-color: rgb(0, 0, 0);"></div><div id="M0" class="handsanddotsstyle" style="top: 87px; left: 60px; background-color: rgb(0, 0, 0);"></div><div id="M1" class="handsanddotsstyle" style="top: 88.45538183572431px; left: 66.84703320513664px; background-color: rgb(0, 0, 0);"></div><div id="M2" class="handsanddotsstyle" style="top: 89.91076367144863px; left: 73.69406641027328px; background-color: rgb(0, 0, 0);"></div><div id="M3" class="handsanddotsstyle" style="top: 91.36614550717294px; left: 80.54109961540992px; background-color: rgb(0, 0, 0);"></div><div id="M4" class="handsanddotsstyle" style="top: 92.82152734289726px; left: 87.38813282054656px; background-color: rgb(0, 0, 0);"></div><div id="S0" class="handsanddotsstyle" style="top: 87px; left: 60px; background-color: rgb(255, 0, 0);"></div><div id="S1" class="handsanddotsstyle" style="top: 93.84703320513664px; left: 61.45538183572432px; background-color: rgb(255, 0, 0);"></div><div id="S2" class="handsanddotsstyle" style="top: 100.69406641027328px; left: 62.91076367144863px; background-color: rgb(255, 0, 0);"></div><div id="S3" class="handsanddotsstyle" style="top: 107.54109961540992px; left: 64.36614550717294px; background-color: rgb(255, 0, 0);"></div><div id="S4" class="handsanddotsstyle" style="top: 114.38813282054656px; left: 65.82152734289726px; background-color: rgb(255, 0, 0);"></div><div id="S5" class="handsanddotsstyle" style="top: 121.23516602568321px; left: 67.27690917862157px; background-color: rgb(255, 0, 0);"></div>

<div id="theFace0" class="facestyle" style="color: rgb(0, 0, 0); top: 81px; left: 96px;">3</div>
<div id="theFace1" class="facestyle" style="color: rgb(0, 0, 0); top: 102px; left: 90.3731px;">4</div>
<div id="theFace2" class="facestyle" style="color: rgb(0, 0, 0); top: 117.373px; left: 75px;">5</div>
<div id="theFace3" class="facestyle" style="color: rgb(0, 0, 0); top: 123px; left: 54px;">6</div>
<div id="theFace4" class="facestyle" style="color: rgb(0, 0, 0); top: 117.373px; left: 33px;">7</div>
<div id="theFace5" class="facestyle" style="color: rgb(0, 0, 0); top: 102px; left: 17.6269px;">8</div>
<div id="theFace6" class="facestyle" style="color: rgb(0, 0, 0); top: 81px; left: 12px;">9</div>
<div id="theFace7" class="facestyle" style="color: rgb(0, 0, 0); top: 60px; left: 17.6269px;">10</div>
<div id="theFace8" class="facestyle" style="color: rgb(0, 0, 0); top: 44.6269px; left: 33px;">11</div>
<div id="theFace9" class="facestyle" style="color: rgb(0, 0, 0); top: 39px; left: 54px;">12</div>
<div id="theFace10" class="facestyle" style="color: rgb(0, 0, 0); top: 44.6269px; left: 75px;">1</div>
<div id="theFace11" class="facestyle" style="color: rgb(0, 0, 0); top: 60px; left: 90.3731px;">2</div>
</div></td>
</tr>
</tbody>
</table>
</div>
<script>
	var theHandle = document.getElementById("Handle");
	var theRoot   = document.getElementById("wclock");
	Drag.init(theHandle, theRoot);
</script>


<div id="qcform" style="position:absolute;width:700px;top:80px;left:450px;z-index:100000;"></div>

<!-- Unified Search module selection feature -->
<div id="UnifiedSearch_moduleformwrapper" style="position:absolute;width:400px;z-index:100002;display:none;"></div>
<script type="text/javascript">

function UnifiedSearch_SelectModuleForm(obj) {
	if($('UnifiedSearch_moduleform')) {
		// If we have loaded the form already.
		UnifiedSearch_SelectModuleFormCallback(obj);
	} else {
		$('status').show();
		new Ajax.Request(
		'index.php',
		{queue: {position: 'end', scope: 'command'},
			method: 'post',
			postBody: 'module=Home&action=HomeAjax&file=UnifiedSearchModules&ajax=true',
			onComplete: function(response) {
				$('status').hide();
				$('UnifiedSearch_moduleformwrapper').innerHTML = response.responseText;
				UnifiedSearch_SelectModuleFormCallback(obj);
			}
		});
	}
}
function UnifiedSearch_SelectModuleFormCallback(obj) {
	fnvshobj(obj, 'UnifiedSearch_moduleformwrapper');
}
function UnifiedSearch_SelectModuleToggle(flag) {
	Form.getElements($('UnifiedSearch_moduleform')).each(
		function(element) {
			if(element.type == 'checkbox') {
				element.checked = flag;
			}
		}
	);
}
function UnifiedSearch_SelectModuleCancel() {
	$('UnifiedSearch_moduleformwrapper').hide();
}
function UnifiedSearch_SelectModuleSave() {
	var UnifiedSearch_form = document.forms.UnifiedSearch;
	UnifiedSearch_form.search_onlyin.value = Form.serialize($('UnifiedSearch_moduleform')).replace(/search_onlyin=/g, '').replace(/&/g,',');
	UnifiedSearch_SelectModuleCancel();
}

</script>
<!-- End -->

<script>
var gVTModule = 'Users';
function fetch_clock()
{
	new Ajax.Request(
		'index.php',
		{queue: {position: 'end', scope: 'command'},
			method: 'post',
			postBody: 'module=Utilities&action=UtilitiesAjax&file=Clock',
			onComplete: function(response)
				    {
					$("clock_cont").innerHTML=response.responseText;
					execJS($('clock_cont'));
				    }
		}
	);

}

function fetch_calc()
{
	new Ajax.Request(
		'index.php',
		{queue: {position: 'end', scope: 'command'},
			method: 'post',
			postBody: 'module=Utilities&action=UtilitiesAjax&file=Calculator',
			onComplete: function(response)
					{
						$("calculator_cont").innerHTML=response.responseText;
						execJS($('calculator_cont'));
					}
		}
	);
}
</script>

<script>

function QCreate(qcoptions){
	var module = qcoptions.options[qcoptions.options.selectedIndex].value;
	if(module != 'none'){
		$("status").style.display="inline";
		if(module == 'Events'){
			module = 'Calendar';
			var urlstr = '&activity_mode=Events';
		}else if(module == 'Calendar'){
			module = 'Calendar';
			var urlstr = '&activity_mode=Task';
		}else{
			var urlstr = '';
		}
		new Ajax.Request(
			'index.php',
				{queue: {position: 'end', scope: 'command'},
				method: 'post',
				postBody: 'module='+module+'&action='+module+'Ajax&file=QuickCreate'+urlstr,
				onComplete: function(response){
					$("status").style.display="none";
					$("qcform").style.display="inline";
					$("qcform").innerHTML = response.responseText;
					// Evaluate all the script tags in the response text.
					var scriptTags = $("qcform").getElementsByTagName("script");
					for(var i = 0; i< scriptTags.length; i++){
						var scriptTag = scriptTags[i];
						eval(scriptTag.innerHTML);
					}
                    eval($("qcform"));
                    posLay(qcoptions, "qcform");
				}
			}
		);
	}else{
		hide('qcform');
	}
}

function getFormValidate(divValidate)
{
  var st = document.getElementById('qcvalidate');
  eval(st.innerHTML);
  for (var i=0; i<qcfieldname.length; i++) {
		var curr_fieldname = qcfieldname[i];	
		if(window.document.QcEditView[curr_fieldname] != null)
		{
			var type=qcfielddatatype[i].split("~")
			var input_type = window.document.QcEditView[curr_fieldname].type;	
			if (type[1]=="M") {
					if (!qcemptyCheck(curr_fieldname,qcfieldlabel[i],input_type))
						return false
				}
			switch (type[0]) {
				case "O"  : break;
				case "V"  : break;
				case "C"  : break;
				case "DT" :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{	 
						if (type[1]=="M")
							if (!qcemptyCheck(type[2],qcfieldlabel[i],getObj(type[2]).type))
								return false
						if(typeof(type[3])=="undefined") var currdatechk="OTH"
						else var currdatechk=type[3]

						if (!qcdateTimeValidate(curr_fieldname,type[2],qcfieldlabel[i],currdatechk))
							return false
						if (type[4]) {
							if (!dateTimeComparison(curr_fieldname,type[2],qcfieldlabel[i],type[5],type[6],type[4]))
								return false

						}
					}		
				break;
				case "D"  :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{	
						if(typeof(type[2])=="undefined") var currdatechk="OTH"
						else var currdatechk=type[2]

							if (!qcdateValidate(curr_fieldname,qcfieldlabel[i],currdatechk))
								return false
									if (type[3]) {
										if (!qcdateComparison(curr_fieldname,qcfieldlabel[i],type[4],type[5],type[3]))
											return false
									}
					}	
				break;
				case "T"  :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{	 
						if(typeof(type[2])=="undefined") var currtimechk="OTH"
						else var currtimechk=type[2]

							if (!timeValidate(curr_fieldname,qcfieldlabel[i],currtimechk))
								return false
									if (type[3]) {
										if (!timeComparison(curr_fieldname,qcfieldlabel[i],type[4],type[5],type[3]))
											return false
									}
					}
				break;
				case "I"  :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{	
						if (window.document.QcEditView[curr_fieldname].value.length!=0)
						{
							if (!qcintValidate(curr_fieldname,qcfieldlabel[i]))
								return false
							if (type[2]) {
								if (!qcnumConstComp(curr_fieldname,qcfieldlabel[i],type[2],type[3]))
									return false
							}
						}
					}
				break;
				case "N"  :
					case "NN" :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{
						if (window.document.QcEditView[curr_fieldname].value.length!=0)
						{
							if (typeof(type[2])=="undefined") var numformat="any"
							else var numformat=type[2]

								if (type[0]=="NN") {

									if (!numValidate(curr_fieldname,qcfieldlabel[i],numformat,true))
										return false
								} else {
									if (!numValidate(curr_fieldname,qcfieldlabel[i],numformat))
										return false
								}
							if (type[3]) {
								if (!numConstComp(curr_fieldname,qcfieldlabel[i],type[3],type[4]))
									return false
							}
						}
					}
				break;
				case "E"  :
					if (window.document.QcEditView[curr_fieldname] != null && window.document.QcEditView[curr_fieldname].value.replace(/^\s+/g, '').replace(/\s+$/g, '').length!=0)
					{
						if (window.document.QcEditView[curr_fieldname].value.length!=0)
						{
							var etype = "EMAIL"
								if (!qcpatternValidate(curr_fieldname,qcfieldlabel[i],etype))
									return false
						}
					}
				break;
			}
		}
	}
       //added to check Start Date & Time,if Activity Status is Planned.//start
        for (var j=0; j<qcfieldname.length; j++)
		{
			curr_fieldname = qcfieldname[j];
			if(window.document.QcEditView[curr_fieldname] != null)
			{
				if(qcfieldname[j] == "date_start")
				{
					var datelabel = qcfieldlabel[j]
						var datefield = qcfieldname[j]
						var startdatevalue = window.document.QcEditView[datefield].value.replace(/^\s+/g, '').replace(/\s+$/g, '')
				}
				if(qcfieldname[j] == "time_start")
				{
					var timelabel = qcfieldlabel[j]
						var timefield = qcfieldname[j]
						var timeval=window.document.QcEditView[timefield].value.replace(/^\s+/g, '').replace(/\s+$/g, '')
				}
				if(qcfieldname[j] == "eventstatus" || qcfieldname[j] == "taskstatus")
				{
					var statusvalue = window.document.QcEditView[curr_fieldname].options[window.document.QcEditView[curr_fieldname].selectedIndex].value.replace(/^\s+/g, '').replace(/\s+$/g, '')
					var statuslabel = qcfieldlabel[j++]
				}
			}
		}
	if(statusvalue == "Planned")
        {
               var dateelements=splitDateVal(startdatevalue)
	       var hourval=parseInt(timeval.substring(0,timeval.indexOf(":")))
               var minval=parseInt(timeval.substring(timeval.indexOf(":")+1,timeval.length))
               var dd=dateelements[0]
               var mm=dateelements[1]
               var yyyy=dateelements[2]

               var chkdate=new Date()
               chkdate.setYear(yyyy)
               chkdate.setMonth(mm-1)
               chkdate.setDate(dd)
	       chkdate.setMinutes(minval)
               chkdate.setHours(hourval)
		if(!comparestartdate(chkdate)) return false;
		

	 }//end
	return true;
}
</script>


<div id="allMenu" onmouseout="fninvsh('allMenu');" onmouseover="fnvshNrm('allMenu');" style="width:650px;z-index: 10000001;display:none;">
	<table border="0" cellpadding="5" cellspacing="0" class="allMnuTable">
	<tbody><tr>
		<td valign="top">
							<span class="allMnuHdr">Trang chủ</span>
			       		
			
												<a href="index.php?module=Home&amp;action=index&amp;parenttab=My Home Page" class="allMnu">Trang chủ</a>
			       		
			
												<a href="index.php?module=Calendar&amp;action=index&amp;parenttab=My Home Page" class="allMnu">Lịch</a>
			       		
			
													<a href="index.php?module=Webmails&amp;action=index&amp;parenttab=My Home Page" class="allMnu">Webmails</a>
								<span class="allMnuHdr">Marketing</span>
			       		
			
												<a href="index.php?module=Campaigns&amp;action=index&amp;parenttab=Marketing" class="allMnu">Chiến dịch</a>
			       		
			
												<a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Marketing" class="allMnu">Khách hàng</a>
			       		
			
												<a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Marketing" class="allMnu">Liên hệ</a>
			       		
			
												<a href="index.php?module=Webmails&amp;action=index&amp;parenttab=Marketing" class="allMnu">Webmails</a>
			       		
			
												<a href="index.php?module=Leads&amp;action=index&amp;parenttab=Marketing" class="allMnu">Đầu mối</a>
			       		
			
												<a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Marketing" class="allMnu">Lịch</a>
			       		
			
												<a href="index.php?module=Documents&amp;action=index&amp;parenttab=Marketing" class="allMnu">Tài liệu</a>
								<span class="allMnuHdr">Bán hàng</span>
			       		
			
												<a href="index.php?module=Leads&amp;action=index&amp;parenttab=Sales" class="allMnu">Đầu mối</a>
			       		
			
												<a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Sales" class="allMnu">Khách hàng</a>
			       		
			
												<a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Sales" class="allMnu">Liên hệ</a>
			       		
			
												<a href="index.php?module=Potentials&amp;action=index&amp;parenttab=Sales" class="allMnu">Cơ hội</a>
			       		
			
										</td><td valign="top">
									<a href="index.php?module=Quotes&amp;action=index&amp;parenttab=Sales" class="allMnu">Báo giá</a>
			       		
			
												<a href="index.php?module=SalesOrder&amp;action=index&amp;parenttab=Sales" class="allMnu">Đặt hàng</a>
			       		
			
												<a href="index.php?module=Invoice&amp;action=index&amp;parenttab=Sales" class="allMnu">Hóa đơn</a>
			       		
			
												<a href="index.php?module=PriceBooks&amp;action=index&amp;parenttab=Sales" class="allMnu">Bảng giá</a>
			       		
			
												<a href="index.php?module=Documents&amp;action=index&amp;parenttab=Sales" class="allMnu">Tài liệu</a>
			       		
			
												<a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Sales" class="allMnu">Lịch</a>
								<span class="allMnuHdr">Hỗ trợ</span>
			       		
			
												<a href="index.php?module=HelpDesk&amp;action=index&amp;parenttab=Support" class="allMnu">Trợ giúp</a>
			       		
			
												<a href="index.php?module=Faq&amp;action=index&amp;parenttab=Support" class="allMnu">Câu hỏi thường gặp</a>
			       		
			
												<a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Support" class="allMnu">Khách hàng</a>
			       		
			
												<a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Support" class="allMnu">Liên hệ</a>
			       		
			
												<a href="index.php?module=Documents&amp;action=index&amp;parenttab=Support" class="allMnu">Tài liệu</a>
			       		
			
												<a href="index.php?module=Webmails&amp;action=index&amp;parenttab=Support" class="allMnu">Webmails</a>
			       		
			
												<a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Support" class="allMnu">Lịch</a>
			       		
			
												<a href="index.php?module=ServiceContracts&amp;action=index&amp;parenttab=Support" class="allMnu">Hợp đồng dịch vụ</a>
			       		
			
												<a href="index.php?module=ProjectMilestone&amp;action=index&amp;parenttab=Support" class="allMnu">ProjectMilestone</a>
			       		
			
										</td><td valign="top">
									<a href="index.php?module=ProjectTask&amp;action=index&amp;parenttab=Support" class="allMnu">ProjectTask</a>
								<span class="allMnuHdr">Phân tích</span>
			       		
			
												<a href="index.php?module=Reports&amp;action=index&amp;parenttab=Analytics" class="allMnu">Báo cáo</a>
			       		
			
												<a href="index.php?module=Dashboard&amp;action=index&amp;parenttab=Analytics" class="allMnu">Biểu đồ</a>
								<span class="allMnuHdr">Tồn kho</span>
			       		
			
												<a href="index.php?module=Products&amp;action=index&amp;parenttab=Inventory" class="allMnu">Sản phẩm</a>
			       		
			
												<a href="index.php?module=Vendors&amp;action=index&amp;parenttab=Inventory" class="allMnu">Nhà cung cấp</a>
			       		
			
												<a href="index.php?module=PriceBooks&amp;action=index&amp;parenttab=Inventory" class="allMnu">Bảng giá</a>
			       		
			
												<a href="index.php?module=PurchaseOrder&amp;action=index&amp;parenttab=Inventory" class="allMnu">Nhập hàng</a>
			       		
			
												<a href="index.php?module=SalesOrder&amp;action=index&amp;parenttab=Inventory" class="allMnu">Đặt hàng</a>
			       		
			
												<a href="index.php?module=Quotes&amp;action=index&amp;parenttab=Inventory" class="allMnu">Báo giá</a>
			       		
			
												<a href="index.php?module=Invoice&amp;action=index&amp;parenttab=Inventory" class="allMnu">Hóa đơn</a>
			       		
			
												<a href="index.php?module=Services&amp;action=index&amp;parenttab=Inventory" class="allMnu">Dịch vụ</a>
			       		
			
												<a href="index.php?module=Assets&amp;action=index&amp;parenttab=Inventory" class="allMnu">Assets</a>
								<span class="allMnuHdr">Công cụ</span>
			       		
			
												<a href="index.php?module=Rss&amp;action=index&amp;parenttab=Tools" class="allMnu">Tin nhanh</a>
			       		
			
												<a href="index.php?module=Portal&amp;action=index&amp;parenttab=Tools" class="allMnu">Trang Web</a>
			       		
			
												<a href="index.php?module=Documents&amp;action=index&amp;parenttab=Tools" class="allMnu">Tài liệu</a>
			       		
			
										</td><td valign="top">
									<a href="index.php?module=PBXManager&amp;action=index&amp;parenttab=Tools" class="allMnu">Quản lý PBX</a>
			       		
			
												<a href="index.php?module=SMSNotifier&amp;action=index&amp;parenttab=Tools" class="allMnu">SMSNotifier</a>
			       		
			
												<a href="index.php?module=RecycleBin&amp;action=index&amp;parenttab=Tools" class="allMnu">Thùng rác</a>
			       		
			
												<a href="index.php?module=ModComments&amp;action=index&amp;parenttab=Tools" class="allMnu">Comments</a>
								<span class="allMnuHdr">Thiết lập</span>
			       		
			
												<a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings" class="allMnu">Thiết lập</a>
			       		
			
												<a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings" class="allMnu">Quản lý phân hệ</a>
							</td>
	</tr>
</tbody></table>
</div>

<!-- Drop Down Menu in the Main Tab -->
<div class="drop_mnu" id="My Home Page_sub" onmouseout="fnHideDrop('My Home Page_sub')" onmouseover="fnShowDrop('My Home Page_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Home&amp;action=index&amp;parenttab=My Home Page" class="drop_down">Trang chủ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Calendar&amp;action=index&amp;parenttab=My Home Page" class="drop_down">Lịch</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Webmails&amp;action=index&amp;parenttab=My Home Page" class="drop_down">Webmails</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Marketing_sub" onmouseout="fnHideDrop('Marketing_sub')" onmouseover="fnShowDrop('Marketing_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Campaigns&amp;action=index&amp;parenttab=Marketing" class="drop_down">Chiến dịch</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Marketing" class="drop_down">Khách hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Marketing" class="drop_down">Liên hệ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Webmails&amp;action=index&amp;parenttab=Marketing" class="drop_down">Webmails</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Leads&amp;action=index&amp;parenttab=Marketing" class="drop_down">Đầu mối</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Marketing" class="drop_down">Lịch</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Documents&amp;action=index&amp;parenttab=Marketing" class="drop_down">Tài liệu</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Sales_sub" onmouseout="fnHideDrop('Sales_sub')" onmouseover="fnShowDrop('Sales_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Leads&amp;action=index&amp;parenttab=Sales" class="drop_down">Đầu mối</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Sales" class="drop_down">Khách hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Sales" class="drop_down">Liên hệ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Potentials&amp;action=index&amp;parenttab=Sales" class="drop_down">Cơ hội</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Quotes&amp;action=index&amp;parenttab=Sales" class="drop_down">Báo giá</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=SalesOrder&amp;action=index&amp;parenttab=Sales" class="drop_down">Đặt hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Invoice&amp;action=index&amp;parenttab=Sales" class="drop_down">Hóa đơn</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=PriceBooks&amp;action=index&amp;parenttab=Sales" class="drop_down">Bảng giá</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Documents&amp;action=index&amp;parenttab=Sales" class="drop_down">Tài liệu</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Sales" class="drop_down">Lịch</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Support_sub" onmouseout="fnHideDrop('Support_sub')" onmouseover="fnShowDrop('Support_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=HelpDesk&amp;action=index&amp;parenttab=Support" class="drop_down">Trợ giúp</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Faq&amp;action=index&amp;parenttab=Support" class="drop_down">Câu hỏi thường gặp</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Accounts&amp;action=index&amp;parenttab=Support" class="drop_down">Khách hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Contacts&amp;action=index&amp;parenttab=Support" class="drop_down">Liên hệ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Documents&amp;action=index&amp;parenttab=Support" class="drop_down">Tài liệu</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Webmails&amp;action=index&amp;parenttab=Support" class="drop_down">Webmails</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Calendar&amp;action=index&amp;parenttab=Support" class="drop_down">Lịch</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=ServiceContracts&amp;action=index&amp;parenttab=Support" class="drop_down">Hợp đồng dịch vụ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=ProjectMilestone&amp;action=index&amp;parenttab=Support" class="drop_down">ProjectMilestone</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=ProjectTask&amp;action=index&amp;parenttab=Support" class="drop_down">ProjectTask</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Analytics_sub" onmouseout="fnHideDrop('Analytics_sub')" onmouseover="fnShowDrop('Analytics_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Reports&amp;action=index&amp;parenttab=Analytics" class="drop_down">Báo cáo</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Dashboard&amp;action=index&amp;parenttab=Analytics" class="drop_down">Biểu đồ</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Inventory_sub" onmouseout="fnHideDrop('Inventory_sub')" onmouseover="fnShowDrop('Inventory_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Products&amp;action=index&amp;parenttab=Inventory" class="drop_down">Sản phẩm</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Vendors&amp;action=index&amp;parenttab=Inventory" class="drop_down">Nhà cung cấp</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=PriceBooks&amp;action=index&amp;parenttab=Inventory" class="drop_down">Bảng giá</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=PurchaseOrder&amp;action=index&amp;parenttab=Inventory" class="drop_down">Nhập hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=SalesOrder&amp;action=index&amp;parenttab=Inventory" class="drop_down">Đặt hàng</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Quotes&amp;action=index&amp;parenttab=Inventory" class="drop_down">Báo giá</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Invoice&amp;action=index&amp;parenttab=Inventory" class="drop_down">Hóa đơn</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Services&amp;action=index&amp;parenttab=Inventory" class="drop_down">Dịch vụ</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Assets&amp;action=index&amp;parenttab=Inventory" class="drop_down">Assets</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Tools_sub" onmouseout="fnHideDrop('Tools_sub')" onmouseover="fnShowDrop('Tools_sub')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Rss&amp;action=index&amp;parenttab=Tools" class="drop_down">Tin nhanh</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Portal&amp;action=index&amp;parenttab=Tools" class="drop_down">Trang Web</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=Documents&amp;action=index&amp;parenttab=Tools" class="drop_down">Tài liệu</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=PBXManager&amp;action=index&amp;parenttab=Tools" class="drop_down">Quản lý PBX</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=SMSNotifier&amp;action=index&amp;parenttab=Tools" class="drop_down">SMSNotifier</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=RecycleBin&amp;action=index&amp;parenttab=Tools" class="drop_down">Thùng rác</a></td></tr>
						
					   			
		<tr><td><a href="index.php?module=ModComments&amp;action=index&amp;parenttab=Tools" class="drop_down">Comments</a></td></tr>
			</tbody></table>
</div>
<div class="drop_mnu" id="Settings_sub" onmouseout="fnHideDrop('Settings_sub')" onmouseover="fnShowDrop('Settings_sub')" style="left: 637px; top: 75px; display: none;">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
						
					   			
		<tbody><tr><td><a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings" class="drop_down">Thiết lập</a></td></tr>
						
					   		   			   			
		<tr><td><a href="index.php?module=Settings&amp;action=ModuleManager&amp;parenttab=Settings" class="drop_down">Quản lý phân hệ</a></td></tr>
			</tbody></table>
</div>


<div id="status" style="position:absolute;display:none;left:850px;top:95px;height:27px;white-space:nowrap;"><img src="themes/softed/images/status.gif"></div>
<script>
function openwin()
{
            window.open("index.php?module=Users&action=about_us","aboutwin","height=520,width=515,top=200,left=300")
}

</script>


<div id="tracker" style="display: none; position: absolute; z-index: 100000001; left: 0px; top: 0px;" class="layerPopup">

	<table border="0" cellpadding="5" cellspacing="0" width="200">
	<tbody><tr style="cursor:move;">
		<td colspan="2" class="mailClientBg small" id="Track_Handle"><strong>Xem gần nhất</strong></td>
		<td align="right" style="padding:5px;" class="mailClientBg small">
		<a href="javascript:;"><img src="themes/images/close.gif" border="0" onclick="fninvsh('tracker')" hspace="5" align="absmiddle"></a>
		</td></tr>
	</tbody></table>
	<table border="0" cellpadding="5" cellspacing="0" width="200" class="hdrNameBg">
		<tbody><tr>
		<td class="trackerListBullet small" align="center" width="12">1</td>
		<td class="trackerList small"> <a href="index.php?module=Leads&amp;action=DetailView&amp;record=196&amp;parenttab=Settings">anh Tân </a> </td><td class="trackerList small">&nbsp;</td></tr>
		<tr>
		<td class="trackerListBullet small" align="center" width="12">2</td>
		<td class="trackerList small"> <a href="index.php?module=SalesOrder&amp;action=DetailView&amp;record=201&amp;parenttab=Settings">Đặt hàng 1</a> </td><td class="trackerList small">&nbsp;</td></tr>
		<tr>
		<td class="trackerListBullet small" align="center" width="12">3</td>
		<td class="trackerList small"> <a href="index.php?module=Contacts&amp;action=DetailView&amp;record=140&amp;parenttab=Settings">Khanh Trần Thị</a> </td><td class="trackerList small">&nbsp;</td></tr>
		<tr>
		<td class="trackerListBullet small" align="center" width="12">4</td>
		<td class="trackerList small"> <a href="index.php?module=Invoice&amp;action=DetailView&amp;record=4004&amp;parenttab=Settings">Phần mềm quản lý ...</a> </td><td class="trackerList small">&nbsp;</td></tr>
		</tbody></table>
</div>
	
<script>
	var THandle = document.getElementById("Track_Handle");
	var TRoot   = document.getElementById("tracker");
	Drag.init(THandle, TRoot);
</script>		

<!-- vtiger Feedback -->
<script type="text/javascript">

function vtiger_feedback() {
	window.open("http://www.hosgroup.com.vn/products/crm/feedback.php?uid=777ddc572adcbcebafc476bcc1f627c6","feedbackwin","height=300,width=515,top=200,left=300")
}

</script>
<!-- hosco news -->
<script type="text/javascript">

function vtiger_news(obj) {
	$('status').style.display = 'inline';
	new Ajax.Request(
		'index.php',
		{queue: {position: 'end', scope: 'command'},
			method: 'post',
			postBody: 'module=Home&action=HomeAjax&file=HomeNews',
			onComplete: function(response) {
				$("vtigerNewsPopupLay").innerHTML=response.responseText;
				fnvshobj(obj, 'vtigerNewsPopupLay');
				$('status').style.display = 'none';
			}
		}
	);
		
}

</script>
<div class="lvtCol fixedLay1" id="vtigerNewsPopupLay" style="display: none; height: 250px; bottom: 2px; padding: 2px; z-index: 12; font-weight: normal;" align="left">
</div>
<!-- END -->

<!-- ActivityReminder Customization for callback -->
<div class="lvtCol fixedLay1" id="ActivityRemindercallback" style="border: 0px; right: 0px; bottom: 2px; display: block; padding: 2px; z-index: 10; font-weight: normal;" align="left">
</div>
<!-- End -->

<!-- divs for asterisk integration -->
<div class="lvtCol fixedLay1" id="notificationDiv" style="float: right;  padding-right: 5px; overflow: hidden; border-style: solid; right: 0px; border-color: rgb(141, 141, 141); bottom: 0px; display: none; padding: 2px; z-index: 10; font-weight: normal;" align="left">
</div>

<div id="OutgoingCall" style="display: none;position: absolute;z-index:200;" class="layerPopup">
	<table border="0" cellpadding="5" cellspacing="0" width="100%">
		<tbody><tr style="cursor:move;">
			<td class="mailClientBg small" id="outgoing_handle">
				<b>Các cuộc gọi đi</b>
			</td>
		</tr>
	</tbody></table>
	<table border="0" cellpadding="0" cellspacing="0" width="100%" class="hdrNameBg">
		
		<tbody><tr><td style="padding:10px;" colspan="2">
			Pick up the extensions receiver to dial the number
		</td></tr>
	</tbody></table>
</div>
<!-- divs for asterisk integration :: end--><!-- startscrmprint --><script language="JavaScript" type="text/javascript" src="include/js/menu.js"></script>
<script language="JavaScript" type="text/javascript" src="include/js/ColorPicker2.js"></script>
<script language="JAVASCRIPT" type="text/javascript" src="include/js/smoothscroll.js"></script>

<script language="JavaScript" type="text/javascript">

 	var cp2 = new ColorPicker('window');
	
function pickColor(color)
{
	ColorPicker_targetInput.value = color;
        ColorPicker_targetInput.style.backgroundColor = color;
}	

function openPopup(){
		window.open("index.php?module=Users&action=UsersAjax&file=RolePopup&parenttab=Settings","roles_popup_window","height=425,width=640,toolbar=no,menubar=no,dependent=yes,resizable =no");
	}	
</script>	

<script language="javascript">
function check_duplicate()
{
	var user_name = window.document.EditView.user_name.value;
	var status = CharValidation(user_name,'name');
	
	VtigerJS_DialogBox.block();
	
        if(status)
	{
	new Ajax.Request(
                'index.php',
                {queue: {position: 'end', scope: 'command'},
                        method: 'post',
                        postBody: 'module=Users&action=UsersAjax&file=Save&ajax=true&dup_check=true&userName='+user_name,
                        onComplete: function(response) {
							if(response.responseText.indexOf('SUCCESS') > -1)
							{
							//	$('user_status').disabled = false;
						                document.EditView.submit();
							}
			       				else {
			       						VtigerJS_DialogBox.unblock();
						                alert(response.responseText);
						        }
			            }
                }
        );
	}
	else
            alert(alert_arr.NO_SPECIAL+alert_arr.IN_USERNAME)
}

</script>

<br>
<table align="center" border="0" cellpadding="0" cellspacing="0" width="98%">
<tbody><tr>
        <td valign="top"><img src="themes/softed/images/showPanelTopLeft.gif"></td>
        <td class="showPanelBg" style="padding: 10px;" valign="top" width="100%">
        <br>

	<div align="center">
			
<table border="0" cellspacing="0" cellpadding="20" width="99%" class="settingsUI">
	<tbody><tr>
		<td valign="top">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tbody><tr>
					<td valign="top" id="settingsSideMenu" width="10%">
						<!--Left Side Navigation Table-->
						<table border="0" cellspacing="0" cellpadding="0" width="100%">
													<tbody><tr>
								<td class="settingsTabHeader" nowrap="">
									Quản lý người sử dụng và truy cập
								</td>
							</tr>
																				<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Administration&amp;action=index&amp;parenttab=Settings">
										Người sử dụng
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listroles&amp;parenttab=Settings">
										Vai trò
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=ListProfiles&amp;parenttab=Settings">
										Lý lịch
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listgroups&amp;parenttab=Settings">
										Nhóm
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=OrgSharingDetailView&amp;parenttab=Settings">
										Chia sẻ quyền truy cập
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=DefaultFieldPermissions&amp;parenttab=Settings">
										Quyền truy cập các trường
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=AuditTrailList&amp;parenttab=Settings">
										Theo dõi tài khoản
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=ListLoginHistory&amp;parenttab=Settings">
										Lịch sử đăng nhập của người sử dụng
									</a>
								</td>
							</tr>
																						<tr>
								<td class="settingsTabHeader" nowrap="">
									Studio
								</td>
							</tr>
																				<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=ModuleManager&amp;parenttab=Settings">
										Quản lý phân hệ
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=PickList&amp;action=PickList&amp;parenttab=Settings">
										Sửa danh sách chọn
									</a>
								</td>
							</tr>
																																<tr>
								<td class="settingsTabHeader" nowrap="">
									Các mẫu liên lạc
								</td>
							</tr>
																				<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listnotificationschedulers&amp;parenttab=Settings">
										Kế hoạch thông báo
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listinventorynotifications&amp;parenttab=Settings">
										Thông báo về hàng tồn kho
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listemailtemplates&amp;parenttab=Settings">
										Mẫu E-mail
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=OrganizationConfig&amp;parenttab=Settings">
										Giới thiệu công ty
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=listwordtemplates&amp;parenttab=Settings">
										Ghép Mail
									</a>
								</td>
							</tr>
																						<tr>
								<td class="settingsTabHeader" nowrap="">
									Các tùy chọn khác
								</td>
							</tr>
																				<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=CurrencyListView&amp;parenttab=Settings">
										Các loại tiền tệ
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=TaxConfig&amp;parenttab=Settings">
										Tính thuế
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=EmailConfig&amp;parenttab=Settings">
										Máy chủ gửi mail
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=ProxyServerConfig&amp;parenttab=Settings">
										Máy chủ Proxy
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=BackupServerConfig&amp;parenttab=Settings">
										Máy chủ sao lưu
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=Announcements&amp;parenttab=Settings">
										Thông báo
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=ListModuleOwners&amp;parenttab=Settings">
										Gán chủ quyền cho Phân hệ
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=DefModuleView&amp;parenttab=Settings">
										Xem Phân hệ mặc định
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=OrganizationTermsandConditions&amp;parenttab=Settings">
										Hàng tồn kho : Quyền hạn và trách nhiệm
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=CustomModEntityNo&amp;parenttab=Settings">
										Tùy chỉnh mã số bản ghi
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=Settings&amp;action=MailScanner&amp;parenttab=Settings">
										Bộ quét email
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=com_vtiger_workflow&amp;action=workflowlist&amp;parenttab=Settings">
										Workflows
									</a>
								</td>
							</tr>
																											<tr>
								<td class="settingsTabList" nowrap="">
									<a href="index.php?module=CustomerPortal&amp;action=index&amp;parenttab=Settings">
										Cổng người dùng
									</a>
								</td>
							</tr>
																</tbody></table>
						<!-- Left side navigation table ends -->
		
					</td>
					<td width="8px" valign="top"> 
						<img src="themes/images/panel-left.png" title="Hide Menu" id="hideImage" style="display:inline;cursor:pointer;" onclick="toggleShowHide_panel('showImage','settingsSideMenu'); toggleShowHide_panel('showImage','hideImage');">
						<img src="themes/images/panel-right.png" title="Show Menu" id="showImage" style="display:none;cursor:pointer;" onclick="toggleShowHide_panel('settingsSideMenu','showImage'); toggleShowHide_panel('hideImage','showImage');">
					</td>
					<td class="small settingsSelectedUI" valign="top" align="left">
						<script type="text/javascript">

							function toggleShowHide_panel(showid, hideid){
								var show_ele = document.getElementById(showid);
								var hide_ele = document.getElementById(hideid);
								if(show_ele != null){ 
									show_ele.style.display = "";
									}
								if(hide_ele != null) 
									hide_ele.style.display = "none";
							}

						</script>	
		<form name="EditView" method="POST" action="index.php" enctype="multipart/form-data" onsubmit="VtigerJS_DialogBox.block();">
		<input type="hidden" name="module" value="Users">
		<input type="hidden" name="record" value="">
		<input type="hidden" name="mode" value="create">
		<input type="hidden" name="parenttab" value="Settings">
		<input type="hidden" name="activity_mode" value="">
		<input type="hidden" name="action">
		<input type="hidden" name="return_module" value="Users">
		<input type="hidden" name="return_id" value="">
		<input type="hidden" name="return_action" value="ListView">			
		<input type="hidden" name="tz" value="Europe/Berlin">			
		<input type="hidden" name="holidays" value="de,en_uk,fr,it,us,">			
		<input type="hidden" name="workdays" value="0,1,2,3,4,5,6,">			
		<input type="hidden" name="namedays" value="">			
		<input type="hidden" name="weekstart" value="1">
		<input type="hidden" name="hour_format" value="">
		<input type="hidden" name="start_hour" value="">
		<input type="hidden" name="form_token" value="2245">

	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="settingsSelUITopLine">
	<tbody><tr><td align="left">
		<table class="settingsSelUITopLine" border="0" cellpadding="5" cellspacing="0" width="100%">
		<tbody><tr>
			<td rowspan="2" width="50"><img src="themes/images/ico-users.gif" align="absmiddle"></td>
			<td>	
				<span class="lvtHeaderText">
					
				<b><a href="index.php?module=Settings&amp;action=index&amp;parenttab=Settings">Thiết lập </a> &gt; <a href="index.php?module=Administration&amp;action=index&amp;parenttab=Settings">Người sử dụng</a> &gt; 
																	Tạo người sử dụng mới
																</b></span>
							</td>
			<td rowspan="2" nowrap="">&nbsp;
			</td>
	 	</tr>
		<tr>
											<td><b class="small">Tạo người sử dụng mới</b>
										</td>
                </tr>
		</tbody></table>
	</td>
	</tr>
	<tr><td>&nbsp;</td></tr>
	<tr>
		<td nowrap="" align="right">
				<input title="Lưu [Alt+S]" accesskey="S" class="small crmbutton save" name="button" value="  Lưu  " onclick="this.form.action.value='Save'; return verify_data(EditView)" style="width: 70px;" type="button">
				<input title="Hủy bỏ [Alt+X]" accesskey="X" class="small crmbutton cancel" name="button" value="  Hủy bỏ  " onclick="window.history.back()" style="width: 70px;" type="button">
						
		</td>
	</tr>
	<tr><td class="padTab" align="left">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">

		<tbody><tr><td colspan="2">
			<table align="center" border="0" cellpadding="0" cellspacing="0" width="99%">
			<tbody><tr>
			    <td align="left" valign="top">
			             <table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tbody><tr><td align="left">
												<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>1. Vai trò và Tình trạng Đăng nhập của người sử dụng</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red">*</font>Tên Khách hàng 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
								<input type="text" name="user_name" value="" tabindex="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'">
							</td>
																										 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>Người quản trị 			</td>
									<td width="30%" align="left" class="dvtCellInfo">
													<input name="is_admin" tabindex="" type="checkbox">
							
					</td>
							   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
												<td width="20%" class="dvtCellLabel" align="right">
					<font color="red">*</font>Mật khẩu 				</td>
				<td width="30%" align="left" class="dvtCellInfo">
					<input type="password" name="user_password" tabindex="" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'">
				</td>
																													 
	 	
							
										
				<!-- Mandatory Email Fields -->			
			 <td width="20%" class="dvtCellLabel" align="right">
			<font color="red">*</font>Email 			 </td>
    	     <td width="30%" align="left" class="dvtCellInfo"><input type="text" name="email1" id="email1" value="" tabindex="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
				   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
												<td width="20%" class="dvtCellLabel" align="right">
					<font color="red">*</font>Xác nhận mật khẩu 				</td>
				<td width="30%" align="left" class="dvtCellInfo">
					<input type="password" name="confirm_password" tabindex="" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'">
				</td>
																													 
	 	
							
										
				<!-- for Status field Disabled for nonadmin -->
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>Trạng thái 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
			   			   	<select id="user_status" name="status" tabindex="" class="small">
			    
				                                        <option value="Active">
                                                Hoạt động
                                        </option>
				                                        <option value="Inactive">
                                                Ngừng hoạt động
                                        </option>
							   </select>
			</td>
				   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Họ </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="first_name" id="first_name" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
				<!-- for currency in users details-->	
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>Tiền tệ 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
			   			   	<select name="currency_id" tabindex="" class="small">
			    

															<option value="1">Vietnam, Dong</option>
						<!-- code added to pass Currency field value, if Disabled for nonadmin -->
												<!--code ends -->
												   </select>
			<!-- code added to pass Currency field value, if Disabled for nonadmin -->
						<!--code ends -->
			</td>
				   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red">*</font>Tên 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
				<input type="text" name="last_name" tabindex="" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'">
			</td>
																									 
	 	
							
										
				 <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>
				Xem Đầu mối mặc định 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
							   		<select name="lead_view" tabindex="" class="small">
			   															<option value="Today">
                                                Ngày hôm nay
                                        </option>
																			<option value="Last 2 Days">
                                                2 ngày trước
                                        </option>
																			<option value="Last Week">
                                                Tuần trước
                                        </option>
												   </select>
			</td>
			   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
				<!-- Role Selection Popup -->		
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red">*</font>Vai trò 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
							<input name="role_name" id="role_name" readonly="" class="txtBox" tabindex="" value="" type="text">&nbsp;
				<a href="javascript:openPopup();"><img src="themes/softed/images/select.gif" align="absmiddle" border="0"></a>
				
			<input name="user_role" id="user_role" value="" type="hidden">
			</td>
																									 
	 	
							
										
				 <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>
				Xem lịch mặc định 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
							   		<select name="activity_view" tabindex="" class="small">
			   															<option value="Today">
                                                Ngày hôm nay
                                        </option>
																			<option value="This Week">
                                                Tuần này
                                        </option>
																			<option value="This Month">
                                                Tháng này
                                        </option>
																			<option value="This Year">
                                                Năm nay
                                        </option>
												   </select>
			</td>
			   </tr>
						</tbody></table>
											   						<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>2. Thông tin khác</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Tiêu đề </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="title" id="title" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Fax </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="phone_fax" id="phone_fax" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Phòng ban </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="department" id="department" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Email khác </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="email2" id="email2" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Điện thoại văn phòng </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="phone_work" id="phone_work" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Yahoo id </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="yahoo_id" id="yahoo_id" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Số di động </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="phone_mobile" id="phone_mobile" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
				<!-- for reportsto field USERS POPUP -->
				<td width="20%" class="dvtCellLabel" align="right">
			      <font color="red"></font>Báo cáo cho 	            </td>
				<td width="30%" align="left" class="dvtCellInfo">
					<input readonly="" name="reports_to_name" class="small" type="text" value="" tabindex="">
					<input name="reports_to_id" type="hidden" value="">&nbsp;<input title="Change [Alt+C]" accesskey="C" type="button" class="small" value="Thay đổi" name="btn1" language="javascript" onclick="return window.open(&quot;index.php?module=Users&amp;action=Popup&amp;form=UsersEditView&amp;form_submit=false&amp;fromlink=&amp;recordid=&quot;,&quot;test&quot;,&quot;width=640,height=603,resizable=0,scrollbars=0&quot;);">
	            	&nbsp;<input type="image" src="themes/images/clear_field.gif" alt="Làm sạch" title="Làm sạch" language="javascript" onclick="this.form.reports_to_id.value=''; this.form.reports_to_name.value=''; return false;" align="absmiddle" style="cursor:hand;cursor:pointer">
	            </td>
				   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Điện thoại nhà riêng </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="phone_home" id="phone_home" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Số điện thoại khác </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="phone_other" id="phone_other" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
				 <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>
				Kiểu ngày tháng 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
							   		<select name="date_format" tabindex="" class="small">
			   															<option value="dd-mm-yyyy">
                                                dd-mm-yyyy
                                        </option>
																			<option value="mm-dd-yyyy">
                                                mm-dd-yyyy
                                        </option>
																			<option value="yyyy-mm-dd">
                                                yyyy-mm-dd
                                        </option>
												   </select>
			</td>
																									 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
					<font color="red"></font>
				Chữ ký 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
				<textarea value="" name="signature" tabindex="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'" rows="2"></textarea>
			</td>
			   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
					<font color="red"></font>
				Tài liệu 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
				<textarea value="" name="description" tabindex="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'" rows="2"></textarea>
			</td>
																									 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>Soạn Email 			</td>

												<td width="30%" align="left" class="dvtCellInfo">
						<input name="internal_mailer" tabindex="" type="checkbox" checked="">
					</td>
										   </tr>
						</tbody></table>
											   						<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>3. Địa chỉ người sử dụng</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
					<font color="red"></font>
				Địa chỉ 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
				<textarea value="" name="address_street" tabindex="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'" rows="2"></textarea>
			</td>
																									 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Quốc gia </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="address_country" id="address_country" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Thành phố </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="address_city" id="address_city" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Mã vùng </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="address_postalcode" id="address_postalcode" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
						   </tr>
			<tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Tỉnh/Bang </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="address_state" id="address_state" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
					   </tr>
						</tbody></table>
											   						<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>4. Ảnh người sử dụng</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>Tải ảnh lên 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
									<input name="imagename" type="file" value="" tabindex="" onchange="validateFilename(this);"><br>(Chỉ chấp nhận các loại ảnh jpg, gif, bmp và png)
					<input name="imagename_hidden" type="hidden" value="">
									<input type="hidden" name="id" value="">
					
			</td>
																										 
	 	
							
										
					   </tr>
						</tbody></table>
											   						<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>5. Tùy chọn nâng cao cho người dùng</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
				 <!-- uitype 111 added for noneditable existing picklist values - ahmed -->
			<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font>
				Khoảng thời gian nhắc nhở 			</td>
			<td width="30%" align="left" class="dvtCellInfo">
							   		<select name="reminder_interval" tabindex="" class="small">
			   															<option value="None">
                                                None
                                        </option>
																			<option value="1 Minute">
                                                1 Minute
                                        </option>
																			<option value="5 Minutes">
                                                5 Minutes
                                        </option>
																			<option value="15 Minutes">
                                                15 Minutes
                                        </option>
																			<option value="30 Minutes">
                                                30 Minutes
                                        </option>
																			<option value="45 Minutes">
                                                45 Minutes
                                        </option>
																			<option value="1 Hour">
                                                1 Hour
                                        </option>
																			<option value="1 Day">
                                                1 Day
                                        </option>
												   </select>
			</td>
																									 
	 	
							
										
					   </tr>
						</tbody></table>
											   						<br>
		                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                		                <tbody><tr>
                                		    <td class="big"><strong>6. Asterisk Configuration</strong></td><td class="small" align="right">&nbsp;</td>
                		              	</tr>
                                		</tbody></table>
		                                <table border="0" cellpadding="5" cellspacing="0" width="100%">
						<!-- Handle the ui types display -->
							

<!-- Added this file to display the fields in Create Entity page based on ui types  -->
			<tbody><tr style="height:25px">
																								 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right"><font color="red"></font>Asterisk Extension </td>

							<td width="30%" align="left" class="dvtCellInfo"><input type="text" tabindex="" name="asterisk_extension" id="asterisk_extension" value="" class="detailedViewTextBox" onfocus="this.className='detailedViewTextBoxOn'" onblur="this.className='detailedViewTextBox'"></td>
																												 
	 	
							
										
							<td width="20%" class="dvtCellLabel" align="right">
				<font color="red"></font> Receive Incoming Calls 			</td>

												<td width="30%" align="left" class="dvtCellInfo">
						<input name="use_asterisk" tabindex="" type="checkbox" checked="">
					</td>
										   </tr>
						</tbody></table>
											   				<br>
			    	<table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
			    	<tbody><tr>
				     <td class="big">	
					<strong>7. Các thành phần trang chủ</strong>
				     </td>
				     <td class="small" align="right">&nbsp;</td>	
			        </tr>
			    	</tbody></table>
			    	<table border="0" cellpadding="5" cellspacing="0" width="100%">
									<tbody><tr><td class="dvtCellLabel" align="right" width="30%">Các hoạt động sắp tới</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="UA" value="UA" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="UA" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các hoạt động chưa tiến hành</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="PA" value="PA" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="PA" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các Khách hàng hàng đầu</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="ALVT" value="ALVT" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="ALVT" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Bảng thống kê trang chủ</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="HDB" value="HDB" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="HDB" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các Cơ hội hàng đầu</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="PLVT" value="PLVT" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="PLVT" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các báo giá nhiều nhất</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="QLTQ" value="QLTQ" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="QLTQ" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Danh sách các điều kiện lọc</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="CVLVT" value="CVLVT" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="CVLVT" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các thắc mắc nhiều nhất</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="HLT" value="HLT" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="HLT" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Dữ liệu chia sẻ trong nhóm</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="GRT" value="GRT" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="GRT" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các đơn bán hàng nhiều nhất</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="OLTSO" value="OLTSO" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="OLTSO" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các hóa đơn nhiều nhất</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="ILTI" value="ILTI" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="ILTI" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Đầu mối mới của bạn</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="MNL" value="MNL" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="MNL" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các Phiếu nhập hàng nhiều nhất</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="OLTPO" value="OLTPO" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="OLTPO" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
									<tr><td class="dvtCellLabel" align="right" width="30%">Các câu hỏi thường gặp mới</td>
					    					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="LTFAQ" value="LTFAQ" checked="" type="radio"></td><td class="dvtCellInfo" align="left" width="20%">Hiện</td> 		
					    	<td class="dvtCellInfo" align="center" width="5%">
					   	<input name="LTFAQ" value="" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td> 		
					    	
					</tr>			
							    	</tbody></table>
				<!-- Added for User Based TagCloud -->
                                <table class="tableHeading" border="0" cellpadding="5" cellspacing="0" width="100%">
                                <tbody><tr>
                                     <td class="big">
                                        <strong>8. Hiện Top từ khóa</strong>
                                     </td>
                                     <td class="small" align="right">&nbsp;</td>
                                </tr>
                                </tbody></table>
				<!-- End of Header -->
				<table border="0" cellpadding="5" cellspacing="0" width="100%">
                                        <tbody><tr><td class="dvtCellLabel" align="right" width="30%">Top từ khóa</td>
                                                                                            <td class="dvtCellInfo" align="center" width="5%">
                                                <input name="tagcloudview" value="true" checked="" type="radio"></td><td class="dvtCellInfo" align="left">Hiện</td>
                                                <td class="dvtCellInfo" align="center" width="5%">
                                                <input name="tagcloudview" value="false" type="radio"></td><td class="dvtCellInfo" align="left">Ẩn</td>
					    					</tr>
				</tbody></table>
				<!--end of Added for User Based TagCloud -->
				<br>
				</td></tr><tr><td colspan="4">&nbsp;</td></tr>
							
					        <tr>
					       		<td colspan="4" align="right">
							<input title="Lưu [Alt+S]" accesskey="S" class="small crmbutton save" name="button" value="  Lưu  " onclick="this.form.action.value='Save'; return verify_data(EditView)" style="width: 70px;" type="button">
							<input title="Hủy bỏ [Alt+X]" accesskey="X" class="small crmbutton cancel" name="button" value="  Hủy bỏ  " onclick="window.history.back()" style="width: 70px;" type="button">
							</td>
						</tr>
					    </tbody></table>
					 </td></tr>
					</tbody></table>
			  	   </td></tr>
				   </tbody></table>
				 <br>
				  </td></tr>
				<tr><td class="small"><div align="right"><a href="#top">[Quay về đầu trang]</a></div></td></tr>
				</tbody></table>
			</form></td>
			</tr>
			</tbody></table>
				
</td>
</tr>
</tbody></table>
</div></td></tr></tbody></table>
<br>

<script language="JavaScript" type="text/javascript" src="include/js/json.js"></script>
<script type="text/javascript" language="Javascript">
<!--  to hide script contents from old browsers
function set_fieldfocus(errorMessage,oMiss_field){
		alert("Thiếu trường" + errorMessage);
		oMiss_field.focus();	
}

function verify_data(form) {
        var isError = false;
	var errorMessage = "";
	
	//check if asterisk server details are set or not
	if(trim(form.asterisk_extension.value)!="" && "false" == "false"){
		errorMessage = "Thông tin chi tiết máy chủ chưa được thiết lập. Bạn phải thiết lập thông tin này đầu tiên.";
		alert(errorMessage);
		return false;
	}
	var extensions = [];
        if(form.asterisk_extension.value != "") {
            for(var userid in extensions){
                if(trim(form.asterisk_extension.value) == extensions[userid]) {
                    if(userid == false && false == false) {
                    } else {
                        alert("This extension has already been configured for another user. Please use another extension.");
                        return false;
                    }
                }
            }
        }
	//asterisk check ends
	
	if (trim(form.email1.value) == "") {
		isError = true;
		errorMessage += "\nE-Mail";
		oField_miss = form.email1;
	}
	if (trim(form.role_name.value) == "") {
		isError = true;
		errorMessage += "\nTên Vai trò";
		oField_miss =form.role_name;
	}
	if (trim(form.last_name.value) == "") {
		isError = true;
		errorMessage += "\nTên";
		oField_miss =form.last_name;
	}
	if(form.mode.value !='edit')
	{
		if (trim(form.user_password.value) == "") {
			isError = true;
			errorMessage += "\nMật khẩu";
			oField_miss =form.user_password;
		}
		if (trim(form.confirm_password.value) == "") {
			isError = true;
			errorMessage += "\nXác nhận mật khẩu";
			oField_miss =form.confirm_password;
		}
	}


	if (trim(form.user_name.value) == "") {
		isError = true;
		errorMessage += "\nTên người sử dụng";
		oField_miss =form.user_name;
	}

	if (isError == true) {
		set_fieldfocus(errorMessage,oField_miss);
		return false;
	}
	form.email1.value = trim(form.email1.value);
	if (form.email1.value != "" && !/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(form.email1.value)) {
		alert("Tài khoản thư  \'"+form.email1.value+"\' trong trường thư điện tử là không phải là địa chỉ email hợp lệ.");
		form.email1.focus();
		return false;
	}
	form.email2.value = trim(form.email2.value);
	if (form.email2.value != "" && !/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(form.email2.value)) {
		alert("Tài khoản thư  \'"+form.email2.value+"\' trong trường thư điện tử khác là không phải là địa chỉ email hợp lệ.");
		form.email2.focus();
		return false;
	}
	form.yahoo_id.value = trim(form.yahoo_id.value);
	if (form.yahoo_id.value != "" && !/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(form.yahoo_id.value) || (trim(form.yahoo_id.value) != "" && !(form.yahoo_id.value.indexOf('yahoo') > -1))) {
		alert("Tài khoản thư  \'"+form.yahoo_id.value+"\' trong trường thư điện tử Yahoo là không phải là địa chỉ email yahoo hợp lệ.");
		form.yahoo_id.focus();
		return false;
	}



	if(! upload_filter("imagename", "jpg|gif|bmp|png|JPG|GIF|BMP|PNG") )
	{
		form.imagename.focus();
		return false;
	}


	if(form.mode.value != 'edit')
	{
		if(trim(form.user_password.value) != trim(form.confirm_password.value))
		{
			set_fieldfocus("The password does't match",form.user_password);
			return false;
		}
		check_duplicate();
	}else
	{
	//	$('user_status').disabled = false;
		VtigerJS_DialogBox.block();
		form.submit();
	}
}

// end hiding contents from old browsers  -->
</script>
<!-- stopscrmprint --><style>
		.bggray
		{
			background-color: #dfdfdf;
		}
	.bgwhite
	{
		background-color: #FFFFFF;
	}
	.copy
	{
		font-size:9px;
		font-family: Verdana, Arial, Helvetica, Sans-serif;
	}
	</style>
		<script language="javascript">
		function LogOut(e)
		{
			var nav4 = window.Event ? true : false;
			var iX,iY;
			if (nav4)
			{
				iX = e.pageX;
				iY = e.pageY;
			}
			else
			{
				iX = event.clientX + document.body.scrollLeft;
				iY = event.clientY + document.body.scrollTop;

			}
			if (iX <= 30 && iY < 0 )
			{
				w=window.open("index.php?action=Logout&module=Users");
				w.close();
			}
		}
	//window.onunload=LogOut
	</script>
		<script language="JavaScript" type="text/javascript" src="include/js/popup.js"></script><br><br><br><table border="0" cellspacing="0" cellpadding="5" width="100%" class="settingsSelectedUI"><tbody><tr><td class="small" align="left"><span style="color: rgb(153, 153, 153);">HOSCO-CRM</span></td><td class="small" align="right"><span style="color: rgb(153, 153, 153);">© 2014 <a href="http://www.hosgroup.com.vn" target="_blank">hosgroup.com.vn</a></span> </td></tr></tbody></table>		<script>
			var userDateFormat = "dd-mm-yyyy";
			var default_charset = "UTF-8";
		</script>
<script type="text/javascript">if(typeof(ActivityReminderCallback) != 'undefined') ActivityReminderCallback();</script><!--end body panes-->





</body></html>