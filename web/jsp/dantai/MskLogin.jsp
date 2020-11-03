<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntMskLogin"/></title>
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
            <html:form action="/dantai/Msk/MskLoginAct" focus="dantaiMoshikomiUketsukeNo" onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <div align="center">
                    <br>
                    <table class="TDheader" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_dnt_msk1.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <br>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">１．<bean:message key="title.DntMskLogin"/></td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                <font class="subHeadline">
                                【団体代表者の方】</font><br>
                                &nbsp;&nbsp;団体コード通知メールに記載されている「団体申込者登録用ID」及び、
                                団体申込出願の際に設定した「団体申込者登録用パスワード」を入力しログインしてください。<br/>
                                <br/>
                                &nbsp;&nbsp;今期の団体申込出願がお済みでなく、初めて認定試験インターネット申込を行う団体の方は、<a href="../../dantai/Msk/DntTopAct.do">こちら</a><br/>
                                &nbsp;&nbsp;今期の団体申込出願がお済みでなく、過去に認定試験インターネット申込を行った事のある団体の方は、<a href="../../dantai/Msk/DntMskLoginInitAct.do">こちら</a><br/>
                                &nbsp;&nbsp;団体申込者登録用ID及び団体申込者登録用パスワードを忘れた場合は、<a href="../../dantai/Inf/DntInfLoginInitAct.do">団体情報確認</a>
                                から確認できます。<br><br>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <font class="subHeadline">
                                【団体申込者の方】</font><br>
                                &nbsp;&nbsp;各団体の申込担当者の方から通知された「団体申込者登録用ID」及び、
                                「団体申込者登録用パスワード」を入力しログインしてください。<br/>
                                &nbsp;&nbsp;団体申込者登録用パスワードを忘れた場合は、各団体の申込担当者にお問合せください。
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
                    <table class="tableLogin">
                        <tr>
                            <td width="50%"></td>
                            <td width="50%"></td>
                        </tr>
                        <tr>
                            <th>団体申込者登録用ID</th>
                            <td ><html:text property="dantaiMoshikomiUketsukeNo" maxlength="6" size="30" value="" style="ime-mode:disabled;"/></td>
                        </tr>
                        <tr>
                            <th>団体申込者登録用パスワード</th>
                            <td><html:password property="dantaiMoshikomiPasswd" size="30" value="" onblur="offenter()" onfocus="onenter()" style="ime-mode:disabled"/></td>
                        </tr>
                    </table>
                    <br/>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter" colspan="2">
                                <input type="button" value="戻る" onclick="backMenuDantai('<bean:write name="menuUrl"/>')" onblur="offenter()" onfocus="onenter()" style="width:100px"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="ログイン" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" style="width:150px"/>
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
                    </table>
                </div>
            </html:form>
        </div>
    </body>
</html:html>
