<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskPayInp" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body onmousewheel="return NoWheelCrdInp(document.activeElement)">
        <div id="container">
            <div align="center">
                <html:form action="/kojin/Msk/MskPayCrdInpAct" onsubmit="disableSubmit(this)">
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k8.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">８．決済情報入力</td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                以下の点にご注意の上、すべての項目を入力し「次へ」ボタンを押してください。<br>
                                決済方法選択画面に戻りたい場合は「戻る」ボタンを押してください。<br/>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <table class="table1" cellpadding="4">
                        <tr>
                            <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                            <td width="97%">ご利用いただけるクレジットカードは以下の通りです。</td>
                        </tr>
                        <tr>
                            <td class="TDcenter" colspan="2">
                                <img src="../../image/cardmark.gif" width="614" height="46" alt="jcb・diners・visa・master・amex">
                            </td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>２</b></td>
                            <td>お支払は<font class="txtAttention">１回払いのみ</font>です。</td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>３</b></td>
                            <td>申込者ご本人名義のクレジットカードをご指定ください。</td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>４</b></td>
                            <td>クレジットカード会社が発行するご利用明細書は<%=MiwConstants.JUKEN_CHARGE_NAME%>納入の控えとして大切に保管してください。</td>
                        </tr>
                        <logic:messagesPresent>
                            <tr>
                                <td colspan="2"> 
                                    <html:errors />
                                </td>
                            </tr>
                        </logic:messagesPresent>
                    </table>
                    <table class="tableMain" cellpadding="5">
                        <tr>
                            <td width="34%"></td>
                            <td width="66%"></td>
                        </tr>
                        <tr>
                            <th>クレジットカードの有効期限</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(1)"/>">
                                <html:text name="MskTorokuJoho" property="expMm" size="8" maxlength="2" style="ime-mode:disabled"/>
                                月&nbsp;&nbsp;20
                                <html:text name="MskTorokuJoho" property="expYy" size="8" maxlength="2" style="ime-mode:disabled"/>
                                年
                            </td>
                        </tr>
                        <tr>
                            <th>クレジットカードの番号</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(2)"/>">
                                <html:text name="MskTorokuJoho" property="cardNo1" size="8" maxlength="4" style="ime-mode:disabled"/>&nbsp;-&nbsp;
                                <html:text name="MskTorokuJoho" property="cardNo2" size="8" maxlength="4" style="ime-mode:disabled"/>&nbsp;-&nbsp;
                                <html:text name="MskTorokuJoho" property="cardNo3" size="8" maxlength="4" style="ime-mode:disabled"/>&nbsp;-&nbsp;
                                <html:text name="MskTorokuJoho" property="cardNo4" size="8" maxlength="4" style="ime-mode:disabled"/>
                                <br>
                                例） 9999-9999-9999-9999(半角数字)
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
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
                                    <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="backMenuConfirm()"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </html:form>
            </div>
        </div>
    </body>
</html:html>