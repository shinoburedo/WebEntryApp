<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntJknPswCngEnd" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css">
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="../../js/jquery.updnWatermark.js"></script>
        <script type="text/javascript" src="../../js/jquery.exresize.0.1.0.js"></script>
        <script language=JavaScript  type="text/Javascript">
            <!--
            $(function(){ watermark() });
            //-->
        </script>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <table class="tableNoFrame" cellpadding="0">
                <tr>
                    <td class="TDright TDmiddle">
                        <b><bean:write name="DntInfo" property="dantaiName"/>代表者&nbsp;様のページ&nbsp;&nbsp;</b>
                    </td>
                    <td class="TDright TDmiddle" width="70">
                        <input type="button" value = "団体情報確認メニュー" onblur="offenter()" onfocus="onenter()" onclick="location.href='./DntInfMenu.do'"/>
                    </td>
                    <td class="TDright TDmiddle" width="70">
                        <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='./DntInfLogoutAct.do'"/>
                    </td>
                </tr>
            </table>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right"><bean:message key="title.DntJknPswCngEnd" /></td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame" cellpadding="40">
                <tr>
                    <td class="TDcenter TDmiddle"><font size="3"><b>団体申込者登録用パスワードを変更しました。</b></font></td>
                </tr>
            </table>
            <br>        
        </div>
    </body>
</html:html>
