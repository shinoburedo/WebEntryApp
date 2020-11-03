<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
<title>郵便番号住所変換候補</title>
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta http-equiv="Content-Style-Type" content="text/css">
<script type="text/javascript" src='<%=request.getContextPath()+"/js/common.js"%>'></script>
<link href='<%=request.getContextPath()+"/css/common.css"%>' type="text/css" rel="stylesheet">
<link href='<%=request.getContextPath()+"/css/msk.css"%>' type="text/css" rel="stylesheet">
<script language="JavaScript" type="text/JavaScript">
<!--
    function setSelectValue(todofuken,shikugun,choson){
        window.opener.document.forms[0].todofuken.value=todofuken; //都道府県をセット
	window.opener.document.forms[0].jusho1.value=shikugun; //市区郡をセット
	window.opener.document.forms[0].jusho2.value=choson; //町村をセット
	window.opener.document.forms[0].jusho3.focus();
	window.close();
    }
    //-->
</script>
</head>
<body>
	<div align="center">
		<br>
		<table cellspacing="0" cellpadding="3" width="400" >
			<tr>
				<td  class="TDcenter "><p class="subHeadline">郵便番号住所変換候補</p><br></td>
			</tr>
		</table>
		<table  cellpadding="3" width="400"  style="border: 1px solid #CCCCEE; border-collapse: separate; border-spacing: 1px;">
                        <logic:empty name="YubinList"> 
                      <TR> 
                        <TD class="txtAttention" colspan="5" height="17" bgcolor="#EEEEEE" nowrap>
						郵便番号に該当する住所は存在しません。
                        </TD>
                      </TR>
                      </logic:empty>
                        <logic:notEmpty name="YubinList"> 
			<tr>
				<td  bgcolor="#EEEEEE">
					該当する住所をクリックしてください。
				</td>
			</tr>
			<logic:iterate id="iteid" name="YubinList" indexId="index">
			<tr>
				<bean:define id="ken" name="iteid"  property="todofukenName" />
				<bean:define id="shiku" name="iteid"  property="shikuchosonName" />
				<bean:define id="cho"  name="iteid"  property="choikiName" />
				<%
				String strCallFunc = "setSelectValue('" + ken.toString()+ "','" + shiku.toString()+ "','" +cho.toString()+ "')";
				%>
				<td bgcolor="#EEEEEE"><html:link href="#" onclick="<%=strCallFunc%>" onfocus="onenter()" onblur="offenter()">
					<bean:write name="iteid"  property="todofukenName" />
					<bean:write name="iteid"  property="shikuchosonName" />
					<bean:write name="iteid"  property="choikiName" />
					</html:link>
				</td>
			</tr>
			</logic:iterate>
                      </logic:notEmpty>
		</table>
	</div>
</body>
</html:html>

