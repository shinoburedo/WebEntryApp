<%@ page contentType="text/html;charset=Windows-31J" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntMskPicConf" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript">
            <!--
            //-->
        </script>
        <style type="text/css">
            <!--
            -->
        </style>
    </head>
    <body class="body">
        <div id="container">
            <html:form action="/dantai/Msk/MskPicConfAct" enctype="multipart/form-data"  onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <div align="center">
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_dnt_msk5.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <br>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">５．<bean:message key="title.DntMskPicConf" /></td>
                        </tr>
                    </table>
                    <logic:messagesPresent>
                        <tr>
                            <td colspan="2"> 
                                <html:errors />
                            </td>
                        </tr>
                    </logic:messagesPresent>
                    <br>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="td1">
                                <font class="font12b">顔写真が正しく表示されていることを確認し、｢次へ」ボタンを押してください。<br>顔写真が正しく表示されていない場合は、「戻る」ボタンを押して前画面に戻り、再度顔写真ファイルを選択し直してください。</font><br>
                            </td>
                        </tr>
                    </table>
                    <br>

                    <table class="tableMain" cellspacing="0" cellpadding="10">
                        <tr>
                        <tr>
                            <th width="160" class="Table1Col1"><font class="font14">顔写真ファイル&nbsp;</font></th>
                            <td class="basic">
                                <div class="divimg">
                                    <img src="../../PictureAction.do?gazoId=<bean:write name="MskTorokuJoho" property="gazoId" />&param=1"  width="128" height="172">
                                </div> 
                            </td>
                            <td class="basic">
                                <table cellspacing="5" cellpadding="1"  >
                                    <tr>
                                        <td>【参考：郵送申込者の適正な写真サイズ】</td>
                                    </tr>
                                    <tr>
                                        <td>・縦4.5cm×横3.5cm（パスポートサイズ）</td>
                                    </tr>
                                    <tr>
                                        <td>・顔の寸法は、頭頂からあごまでが<br>　3.2cm以上3.6cm以下のもの</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="td1">
                                <font class="txtAttention">
                                ※この顔写真は試験日当日、試験監督員による受験者の本人確認に使用します。<br>
                                &nbsp;&nbsp;&nbsp;この顔写真と受験者が同一人物と判断できない場合、再度本人確認に必要な書類を提出していただく場合があります。
                                </font><br>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="td2">
                                <html:submit property="back" value="戻　る"style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                            </td>
                        </tr>
                    </table>
                    <table  class="table1" cellspacing="0" cellpadding="0">
                        <tbody>
                            <tr>
                                <td class="TDright TDtop">
                                    <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="backMenuConfirmDantai('<bean:write name="menuUrl"/>')"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </html:form>
            </div>
    </body>
</html:html>