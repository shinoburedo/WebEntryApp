<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <title><bean:message key="title.MypPswRemInp" /></title>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <script type="text/JavaScript" src="../js/common.js"></script>
        <script type="text/JavaScript" src="../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/myp.css" >
        <link type="text/css" rel="stylesheet" href="../css/common.css">
        <script language=JavaScript  type="text/Javascript">
        </script>
    </head>
    <body>
        <div id="container">
            <html:form action="/Myp/MypPswRemInp1Act" focus="userId" onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right"><bean:message key="title.MypPswRemInp" /></td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                    <tr>
                        <td>
                            ご本人であることを確認します。
                        </td>
                    </tr>
                    <tr>
                        <td>
                            申込完了メールに記載されているＩＤと、申込時に登録した生年月日、カナ氏名を入力してください。
                        </td>
                    </tr>
                    <logic:messagesPresent>
                        <tr>
                            <td colspan="2"> 
                                <html:errors />
                            </td>
                        </tr>
                    </logic:messagesPresent>
                </table>
                <br>
                <table class="tableMain" cellpadding="5" cellspacing="0">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>ＩＤ</th>
                        <td class="basic">
                            <html:text name="MypPswRemFrm" property="userId" size="20" maxlength="8" styleClass="ime_off"/>
                        </td>
                    </tr>
                    <tr>
                        <th>生年月日</th>
                        <td class="basic">
                            <html:select name="MypPswRemFrm" property="birthdayEra">
                                <html:options collection="BirthEraList" property="value" labelProperty="label" />
                            </html:select>
                            <html:text name="MypPswRemFrm" property="birthdayYy" size="8" maxlength="2" styleClass="ime_off" styleId="year" /> 年
                            <html:text name="MypPswRemFrm" property="birthdayMm" size="8" maxlength="2" styleClass="ime_off" styleId="month" />月
                            <html:text name="MypPswRemFrm" property="birthdayDd" size="8" maxlength="2" styleClass="ime_off" styleId="day" />日
                        </td>
                    </tr>
                    <tr>
                        <th>カナ氏名</th>
                        <td class="basic">
                            姓<html:text name="MypPswRemFrm" property="shimeiSeiKana" size="30" maxlength="50" styleClass="ime_on" styleId="knSei" />&nbsp;&nbsp;
                            名<html:text name="MypPswRemFrm" property="shimeiMeiKana" size="30" maxlength="50" styleClass="ime_on" styleId="knMei" />　
                        </td>
                    </tr>
                </table>
                <br>        
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="submit" value="次へ" styleClass="buttonNext" style="width: 100px;" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDright">
                            <input type="button" value="ログインへ" onblur="offenter()" onfocus="onenter()" onclick="location.href='../html/MypLogin.html'"/>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>
