<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntPswRemResu" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css">
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script language=JavaScript  type="text/Javascript">
        </script>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right"><bean:message key="title.DntPswRemResu" /></td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame" cellpadding="40">
                <tr>
                    <td class="TDcenter TDmiddle"><font size="3"><b>登録されているメールアドレスへ<br>パスワードを送信しました。</b></font></td>
                </tr>
            </table>
            <br>        
            <table cellspacing="0" cellpadding="0">
                <tr>
                    <td class="TDcenter">
                        <input type="button" value="ログインへ" onblur="offenter()" onfocus="onenter()" onclick="location.href='<bean:write name="loginUrl"/>'"/>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html:html>
