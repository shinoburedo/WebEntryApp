<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMenu"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <link href="../../css/myp.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <table class="tableNoFrame" cellpadding="0"  cellspacing="0">
                <tr>
                    <td class="TDright TDmiddle">
                        <b><bean:write name="DntInfo" property="dantaiName" />代表者&nbsp;様のページ&nbsp;&nbsp;</b>
                    </td>
                    <td class="TDright TDmiddle" width="70">
                        <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='../../dantai/Inf/DntInfLogoutAct.do'"/>
                    </td>
                </tr>
            </table>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right"><bean:message key="title.Dantai_InfMenu"/></td>
                </tr>
            </table>
            <br/>
            <table class="tableNoFrame tableMenu">
                <tr>
                    <th class="TDtop" width="35%">
                        <logic:equal name="DntInfMenu" property="dntUpdLinkFlg" value="1">
                            <input type="button" name="updInf" value="団体情報の確認・変更" onclick="location.href='./DntInfConfInitAct.do'" onblur="offenter()" onfocus="onenter()"/>
                        </logic:equal>
                        <logic:notEqual name="DntInfMenu" property="dntUpdLinkFlg" value="1">
                            <input type="button" name="updInf" value="団体情報の確認・変更"  onblur="offenter()" onfocus="onenter()" disabled="disabled"/>
                        </logic:notEqual>
                    </th>
                    <td class="TDtop" width="30%">
                        団体情報の確認・変更を行うことができます。
                        ただし受付状況が「仮受付」となった後は、受験申込者数は変更できません。<br>
                        <font class="txtAttention">
                        <%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法についてはこちらから確認することができます。</font>
                    </td>
                    <td class="TDtop" width="35%">
                        利用期間<br>
                        確認：<font class="txtAttention"><bean:write name="DntInfMenu" property="dntConfKigenDisp" /></font> まで<br>
                        変更：<font class="txtAttention"><bean:write name="DntInfMenu" property="dntUpdKigenDisp" /></font> まで
                    </td>
                </tr>
                <tr>
                    <th class="TDtop">
                        <logic:equal name="DntInfMenu" property="dntResLinkFlg" value="1">
                            <input type="submit" name="resInf" value="申込者情報の確認・変更" onclick="location.href='./DntInfMskListInitAct.do'" onblur="offenter()" onfocus="onenter()" />
                        </logic:equal>
                        <logic:notEqual name="DntInfMenu" property="dntResLinkFlg" value="1">
                            <input type="submit" name="resInf" value="申込者情報の確認・変更" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" disabled="disabled" />
                        </logic:notEqual>
                    </th>
                    <td class="TDtop">
                        団体申込者情報の確認・変更を行います。
                    </td>
                    <td class="TDtop">
                        利用期間<br>
                        確認：<font class="txtAttention"><bean:write name="DntInfMenu" property="dntResKigenDisp" /></font> まで<br>
                        変更：<font class="txtAttention"><bean:write name="DntInfMenu" property="dntResUpdKigenDisp" /></font> まで
                    </td>
                </tr>
                <tr>
                    <th class="TDtop">
                        <logic:equal name="DntInfMenu" property="dntPswUpdKigenFlg" value="1">
                            <input type="submit" name="pswInf" value="団体パスワード変更" onclick="location.href='./DntPswCngInpInitAct.do'" onblur="offenter()" onfocus="onenter()" />
                        </logic:equal>
                        <logic:notEqual name="DntInfMenu" property="dntPswUpdKigenFlg" value="1">
                            <input type="submit" name="pswInf" value="団体パスワード変更" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" disabled="disabled" />
                        </logic:notEqual>
                    </th>
                    <td class="TDtop">
                        団体パスワードの変更を行います。
                    </td>
                    <td class="TDtop">
                        利用期間<br>
                        <font class="txtAttention"><bean:write name="DntInfMenu" property="dntPswUpdKigenDisp" /></font> まで
                    </td>
                </tr>
                <tr>
                    <th class="TDtop">
                        <logic:equal name="DntInfMenu" property="dntJknPswUpdKigenFlg" value="1">
                            <input type="submit" name="jknPswInf" value="団体申込者登録用パスワード変更" onclick="location.href='./DntJknPswCngInpInitAct.do'" onblur="offenter()" onfocus="onenter()" />
                        </logic:equal>
                        <logic:notEqual name="DntInfMenu" property="dntJknPswUpdKigenFlg" value="1">
                            <input type="submit" name="jknPswInf" value="団体申込者登録用パスワード変更" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" disabled="disabled" />
                        </logic:notEqual>
                    </th>
                    <td class="TDtop">
                        団体申込者登録用パスワードの変更を行います。
                    </td>
                    <td class="TDtop">
                        利用期間<br>
                        <font class="txtAttention"><bean:write name="DntInfMenu" property="dntJknPswUpdKigenDisp" /></font> まで
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html:html>