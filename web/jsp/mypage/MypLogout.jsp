<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
    <head>
        <meta http-equiv=Content-Type content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MypLogout"/></title>
        <meta http-equiv=Content-Script-Type content="text/javascript">
        <meta http-equiv=Content-Style-Type content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/myp.css" >
        <link type="text/css" rel="stylesheet" href="../css/common.css">
        <script language="JavaScript" type="text/JavaScript"></script>
        <!--
        //-->
    </script>
    <style type="text/css">
        <!--
        -->
    </style>
</head>
<body>
    <div id="container">
        <div align="center">
            <!--heder-->
            <div id="title_header"></div>
            <br><br><br>
            <table class="tableNoFrame" cellpadding="40">
                <tr>
                    <td class="TDcenter TDmiddle"><font size="3"><b>ログアウトしました。</b></font></td>
                </tr>
            </table>
            <br><br><br><br>
            <table  class="tableNoFrame" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td class="TDright">
                            <input type="button" value="ログインへ" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="location.href='../html/MypLogin.html'"/>
                            <input type="button" value="メインメニューへ" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="location.href='../index.html'"/>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>