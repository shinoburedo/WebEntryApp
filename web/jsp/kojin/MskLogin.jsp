<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskLogin"/></title>
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
            <html:form action="/kojin/Msk/MskLoginAct" focus="userId" onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <div align="center">
                    <br>
                    <table class="TDheader" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k1.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">１．ログイン</td>
                        </tr>
                    </table>
                    <br>
                    <table class="tableLogin" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                ＩＤ通知メールに記載されている「ＩＤ」及び、<br/>
                                ＩＤ取得の際に設定した「パスワード」を入力しログインしてください。<br/>
                                <br/>
                                ＩＤの取得がお済みでない方は<a href="../Jzn/JznTopAct.do">こちら</a><br/>
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
                    <table width="500px" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter" colspan="2">
                                <table class="tableLogin">
                                    <tr>
                                        <td width="40%"></td>
                                        <td width="60%"></td>
                                    </tr>
                                    <tr>
                                        <th>ＩＤ</th>
                                        <td ><html:text property="userId" maxlength="8" size="30" style="ime-mode:disabled;" /></td>
                                    </tr>
                                    <tr>
                                        <th>パスワード</th>
                                        <td><html:password property="passwd" maxlength="15" size="30" redisplay="false" style="ime-mode:disabled" onblur="offenter()" onfocus="onenter()"/></td>
                                    </tr>
                                </table>
                                <br/>
                            </td>
                        </tr>
                        <tr>
                            <td class="TDcenter" colspan="2">
                                <input type="button" value="戻る" onclick="location.href='../MenuAct.do'" style="width:100px"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit value="ログイン" property="submit" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"  style="width:150px"/>
                                <br>
                                <br>
                                <br>
                            </td>
                        </tr>
                    </table>
                    <table width="500px" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                <font size="-1">
                                <font class="txtAttention">※</font>
                                ＩＤ又はパスワードをお忘れになった場合、お手数ですがＩＤ取得をやり直してください。<br/>

                                <font class="txtAttention">※</font>
                                一度申込に利用したＩＤを他の申込に再度利用することはできません。
                                </font>
                            </td>
                        </tr>
                    </table>
                    <br>
                </html:form>
            </div>
        </div>
    </body>
</html:html>
