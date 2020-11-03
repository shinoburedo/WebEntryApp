<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntPswCngInp" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css">
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="../../js/jquery.updnWatermark.js"></script>
        <script type="text/javascript" src="../../js/jquery.exresize.0.1.0.js"></script>
        <script language="JavaScript" type="text/JavaScript">
            $(document).ready(function(){ 
                $('#showcharacters').click(function() { //チェックボックスがクリックされたとき
                    if ($(this).attr('checked')) { //もし、チェック状態であれば
                        $('#passwordNew1').replaceWith(
                         '<input type="text" name="passwordNew1" id="passwordNew1" size="30"  style="ime-mode:disabled" value="' + $('#passwordNew1').attr('value') + '" />');
                         //パスワードを表示
                    } else { //チェックがなければ
                        $('#passwordNew1').replaceWith(
                         '<input type="password" name="passwordNew1" id="passwordNew1" size="25" value="' + $('#passwordNew1').attr('value') + '" />');
                         //パスワードを隠す
                        }
                });
             });
            $(function(){ watermark() });
        </script>
    </head>
    <body>
        <div id="container">
            <html:form action="/dantai/Inf/DntPswCngInpAct" onsubmit="disableSubmit(this)">
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
                        <td class="right"><bean:message key="title.DntPswCngInp" /></td>
                    </tr>
                </table>
                <br>
                <logic:messagesPresent>
                    <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                        <tr>
                            <td colspan="2"> 
                                <html:errors />
                            </td>
                        </tr>
                    </table>
                </logic:messagesPresent>
                <table class="tableMain" cellpadding="5" cellspacing="0">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>現在のパスワード</th>
                        <td class="basic">
                            <html:password name="DntPswCngFrm" property="password" size="20" maxlength="8" styleClass="ime_off"/>
                        </td>
                    </tr>
                    <tr>
                        <th>新しいパスワード</th>
                        <td class="basic">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="TDmiddle">
                                        <html:password name="DntPswCngFrm" property="passwordNew1" styleId="passwordNew1" size="20" maxlength="8" styleClass="ime_off"/>
                                        <input id="showcharacters" name="showcharacters" type="checkbox" /> 確認する<br>
                                        半角英数字で８文字。&nbsp;&nbsp; ! # $ % 等は使用できません。
                                    </td>
                                </tr>
                                <tr>
                                    <td class="TDmiddle">
                                        <br>
                                        確認のためもう一度入力してください。<br>
                                        <div onPaste="return false">
                                            <html:password name="DntPswCngFrm" property="passwordNew2" size="20" maxlength="8" styleClass="ime_off"/><br>
                                        </div>

                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>        
                <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="submit" value="変更" styleClass="buttonNext" style="width: 100px;" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>
