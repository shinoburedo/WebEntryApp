<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ page import="java.util.Calendar" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskPayConf" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <div align="center">
                <html:form action="/kojin/Msk/MskPayCrdConfAct" onsubmit="disableSubmit(this)">
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k9.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">９．決済内容確認</td>
                        </tr>
                    </table>
                    <br>      
                    <br>
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                以下の点にご注意の上、内容を確認し、よろしければ「申込を確定する」ボタンを押してください。カード情報を変更する場合は「戻る」ボタンを押し、前画面にて再入力してください。<br/>
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <table class="table1" cellpadding="4">
                        <tr>
                            <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                            <td width="97%">
                                申込確定後の支払方法の変更はできません。
                            </td>
                        </tr>

                        <tr>
                            <td class="TDtop TDcenter" width="3%"><b>２</b></td>
                            <td width="97%">
                                いったん納入された<%=MiwConstants.JUKEN_CHARGE_NAME%>は、理由の如何にかかわらず返金いたしません。また、次回への振替もできません。
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
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                ◆ 決済内容<br/>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tableMain" cellspacing="0" cellpadding="7">
                        <tr>
                            <th width="34%">決済方法</th>
                            <td class="basic">クレジットカード決済</td>
                        </tr>
                        <tr>
                            <td height="12"><td>
                        </tr>
                        <tr>
                            <th>クレジットカードの有効期限</th>
                            <td class="basic">
                                20<bean:write name="MskTorokuJoho" property="expYy" />年<bean:write name="MskTorokuJoho" property="expMm" />月
                            </td>
                        </tr>
                        <tr>
                            <th>クレジットカードの番号</th>
                            <td class="basic">
                                <bean:write name="MskTorokuJoho" property="cardNo1" />&nbsp;-
                                <bean:write name="MskTorokuJoho" property="cardNo2" />&nbsp;-
                                <bean:write name="MskTorokuJoho" property="cardNo3" />&nbsp;-
                                ****
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tablePayJoho" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <div align="center">
                                    <table width="50%" border="0" cellspacing="0" cellpadding="5">
                                        <tr>
                                            <td><br></td>
                                        </tr>
                                        <tr>
                                            <td><b><%=MiwConstants.JUKEN_CHARGE_NAME%></b></td>
                                            <td class="TDright">
                                                <b>
                                                    <bean:write name="MskTorokuJoho" property="kenteiryoDisp" />円
                                                </b>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><br></td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="戻　る"style="width:100px" onblur="offenter()" onfocus="onenter()"  onclick="setHiddenValue(this)"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="申込を確定する" style="width:100px" onblur="offenter()" onfocus="onenter()"  onclick="setHiddenValue(this)"/>
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
