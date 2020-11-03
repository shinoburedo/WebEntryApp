<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfLogin"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>

        <div id="container">
            <html:form action="/dantai/Inf/DntInfLoginAct" focus="dantaiCode" onsubmit="disableSubmit(this)">
            <!--heder-->
            <div id="title_header"></div>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right">１．<bean:message key="title.Dantai_InfLogin"/></td>
                </tr>
            </table>
            <br>
            <br>
             <table class="table1" cellpadding="4" cellspacing="0">
                    <tr>
                        <td>
                           &nbsp;&nbsp;団体コード通知メールに記載されている「団体コード」及び、<br/>
                            &nbsp;&nbsp;団体申込出願の際に設定した「団体パスワード」を入力しログインしてください。<br/>
                            <br/>
                            &nbsp;&nbsp;団体コードの取得がお済みでない方は<a href="../../dantai/Msk/DntTopAct.do">こちら</a><br/>
                            &nbsp;&nbsp;団体コードの取得済で今期の団体申込出願がお済みでない方は<a href="../../dantai/Msk/DntMskLoginInitAct.do">こちら</a><br/>
                            &nbsp;&nbsp;団体パスワードを忘れた場合は、<a href="../../dantai/Inf/DntPswRemInitAct.do">こちら</a>
                            から送信することができます。
                        </td>
                    </tr>
                    <logic:messagesPresent>
                        <tr>
                            <td class="TDleft">
                                <font class="txtAttention">
                                <html:messages id="msg" message="false">
                                    <b>！</b><bean:write name="msg" filter="false" ignore="true" /><br>
                                </html:messages>
                                </font>
                            </td>
                        </tr>
                    </logic:messagesPresent>
                </table>
                <br>
                <table class="tableLogin" >
                    <tr>
                        <td width="40%"></td>
                        <td width="60%"></td>
                    </tr>
                    <tr>
                        <th style="background-color: #e9e9e9;">団体コード</th>
                        <td ><html:text property="dantaiCode" maxlength="5" size="30" style="ime-mode:disabled;" /></td>
                    </tr>
                    <tr>
                        <th style="background-color: #e9e9e9;">団体パスワード</th>
                        <td><html:password property="dantaiPasswd" size="30" redisplay="false" style="ime-mode:disabled" onblur="offenter()" onfocus="onenter()"/></td>
                    </tr>
                </table>
                <br/>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter" colspan="2">
                            <html:submit value="ロ&nbspグ&nbspイ&nbspン" property="submit" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"  style="width:150px"/>
                        </td>
                    </tr>
                </table>
            <table class="tableNoFrame" cellspacing="0" cellpadding="0" style="width: 640px;">
                <tbody>
                    <tr>
                        <td class="TDright">
                            <input type="button" value="メインメニューへ" onblur="offenter()" onfocus="onenter()" onclick="location.href='../MenuDaihyoshaAct.do'"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            </html:form>
        </div>
    </body>
</html:html>