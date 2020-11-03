<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntPswRemInp" /></title>
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
            <html:form action="/dantai/Inf/DntPswRemInp2Act" focus="passwdAnswer1" onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right"><bean:message key="title.DntPswRemInp" /></td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                    <tr>
                        <td>
                            団体代表者様であることを確認します。
                        </td>
                    </tr>
                    <tr>
                        <td>
                            団体申込出願時に登録した質問に対する回答を入力してください。
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
                <table class="tableMain" cellpadding="5" cellspacing="0" style="width: 500px;">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th><bean:write name="Dantai" property="passwdQuestionCodeDisp"/></th>
                        <td class="basic">
                            <html:text name="DntPswRemFrm" property="passwdAnswer1" size="60" maxlength="20" styleClass="ime_on" styleId="ans"/>
                        </td>
                    </tr>
                </table>
                <br>        
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="submit" value="パスワード送信" styleClass="buttonNext" style="width: 150px;" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDright">
                            <input type="button" value="ログインへ" onblur="offenter()" onfocus="onenter()" onclick="location.href='<bean:write name="loginUrl"/>'"/>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>
