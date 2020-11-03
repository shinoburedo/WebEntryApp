<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Kojin_JznConf" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <link href="../../css/jzn.css" type="text/css" rel="stylesheet">
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="../../js/jzn.js"></script>
        <script type="text/javascript" src="../../js/common.js"></script>
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <div id="title_header"></div>
            <div align="center">
                <html:form action="/kojin/Jzn/JznConfAct">
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_jzn3.gif" alt="ＩＤ取得フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">３．ＩＤ取得　情報確認</td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <table class="table1" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>ご登録内容を確認してください。</td>
                        </tr>
                    </table>

                    <table class="tableMain" cellpadding="4" cellspacing="0">
                        <tr>
                            <th width="34%">氏名</th>
                            <td class="basic" width="66%" colspan="2">
                                <bean:write name="JznTorokuJoho" property="shimeiSei"/>
                                &nbsp;<bean:write name="JznTorokuJoho" property="shimeiMei"/>
                            </td>
                        </tr>
                        <tr>
                            <th>フリガナ</th>
                            <td class="basic" colspan="2">
                                <bean:write name="JznTorokuJoho" property="shimeiSeiKana"/>
                                &nbsp;<bean:write name="JznTorokuJoho" property="shimeiMeiKana"/>
                            </td>
                        </tr>
                        <tr>
                            <th>性　　別</th>
                            <td class="basic" colspan="2">
                                <bean:write name="JznTorokuJoho" property="sexCodeDisp" />
                            </td>
                        </tr>
                        <tr>
                            <th>生年月日</th>
                            <td class="basic" colspan="2">
                                <bean:write name="JznTorokuJoho" property="birthdayDisp"/>
                            </td>
                        </tr>
                        <tr>
                            <th>電話番号</th>
                            <td class="basic" style="border: none">
                                <bean:write name="JznTorokuJoho" property="telNo"/>
                            </td>
                            <td class="basic" style="border-left-style: none">
                                <logic:equal name="JznTorokuJoho" property="isExtNo" value="true">
                                    内線&nbsp;<bean:write name="JznTorokuJoho" property="extNo"/>
                                </logic:equal>
                            </td>
                        </tr>
                        <tr>
                            <th>メールアドレス</th>
                            <td class="basic" colspan="2">
                                <bean:write name="JznTorokuJoho" property="mailAddress"/>
                            </td>
                        </tr>
                        <tr>
                            <th width="220">パスワード</th>
                            <td class="basic" width="420" colspan="2">
                                （表示しません）
                            </td>
                        </tr>
                    </table>

                    <br>
                    <table class="table1" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="TDcenter">
                                <b><font size="+1">
                                    間違いがなければ、「ＩＤ取得」ボタンをクリックしてください。
                                    </font></b>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="登録内容を修正する" style="width:200px" onblur="offenter()" onfocus="onenter()" />
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="Ｉ　Ｄ　取　得" style="width:200px"onblur="offenter()" onfocus="onenter()"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                </html:form>
            </div>
        </div>
    </body>
</html:html>