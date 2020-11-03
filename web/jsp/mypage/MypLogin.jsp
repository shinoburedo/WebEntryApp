<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv=Content-Type content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MypLogin" /></title>
        <meta http-equiv=Content-Script-Type content="text/javascript">
        <meta http-equiv=Content-Style-Type content="text/css">
        <script type="text/javascript" src="../js/myp.js"></script>
        <script type="text/javascript" src="../js/common.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/myp.css" >
        <link type="text/css" rel="stylesheet" href="../css/common.css">
        <script language=JavaScript  type="text/Javascript">
        </script>
        <style type="text/css">
            <!--
            /**ログインページのテーブル*/
            .tableLogin{width: 320px;}
            .tableLogin th{text-align: left;
                           background-color: #e9e9e9;
                           border:1px solid #ffffff;
                           color: #000000;
                           font-weight: 600;
                           padding-left: 10px;
            }
            .tableLogin input{width: 165px;}

            -->
        </style>
    </head>
    <body>
        <div id="container">
            <div align="center">
                <!--heder-->
                <div id="title_header"></div>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right"><bean:message key="title.MypLogin" /></td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" style="border-color: #000000" cellspacing="0" cellpadding="10">
                    <tbody>
                        <tr>
                            <td class="TDcenter">
                                申込内容確認へのログインには、ＩＤとパスワードが必要となります。<br>
                                また、インターネット申込がお済みでない方はログインできません。<br>
                                お申込をされる方は<a href="../index.html">トップページ</a>より行ってください。<br>
                                パスワードを忘れた場合は、<a href="./MypPswRemInitAct.do">こちら</a>
                                から送信することができます。
                            </td>
                        </tr>
                    </tbody>
                </table>
                <logic:messagesPresent>
                    <table class="tableNoFrame" cellspacing="0" cellpadding="10">
                        <tr>
                            <td class="TDcenter">
                                <html:errors/>
                            </td>
                        </tr>
                    </table>
                </logic:messagesPresent>
                <br>
                <html:form action="/Myp/MypLoginAct" focus="userid" onsubmit="disableSubmit(this)">
                    <table class="tableLogin">
                        <tr>
                            <td width="40%"></td>
                            <td width="60%"></td>
                        </tr>
                        <tr>
                            <th>ＩＤ</th>
                            <td>
                                <html:text property="userid" maxlength="8" size="30" styleClass="ime_off"/>
                            </td>
                        </tr>
                        <tr>
                            <th>パスワード</th>
                            <td>
                                <html:password property="passwd" maxlength="15" size="30" redisplay="false" styleClass="ime_off" onblur="offenter()" onfocus="onenter()"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="submit"value="ロ&nbspグ&nbspイ&nbspン" style="width:100px" styleClass="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                            </td>
                        </tr>
                    </table>
                </html:form>
                <br>
                <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="TDright">
                                <input type="button" value="トップページへ" onblur="offenter()" onfocus="onenter()" onclick="location.href='../index.html'"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html:html>