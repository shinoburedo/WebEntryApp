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
    <body>
        <div id="container">
            <div align="center">
                <html:form action="/kojin/Msk/MskPayCvsInpAct" onsubmit="disableSubmit(this)">
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="TDheader" cellpadding="0" cellspacing="0">
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
                    <table class="table1">
                        <tr>
                            <td>
                                以下の点にご注意の上、お支払コンビニを選択し、「次へ」ボタンを押してください。<br/>
                                決済方法選択画面に戻りたい場合は「戻る」ボタンを押してください。
                            </td>
                        </tr>
                    </table>
                    <br/>
                    <table class="table1" cellpadding="4">
                        <tr>
                            <td class="TDtop TDcenter"><b>１</b></td>
                            <td>お支払方法の説明は、各詳細にてご確認ください。</td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>２</b></td>
                            <td>
                                お支払期限は<bean:write name="MskTorokuJoho" property="kessaiKigenDisp"/>までです。お支払のない場合には、申込はキャンセルとなりますのでご注意ください。<br/>
                                <br/>
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

                    <table class="table2" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                <table cellspacing="5" cellpadding="1">
                                    <tr>
                                        <td width="50"></td>
                                        <td width="250"></td>
                                        <td width="310"></td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div align="right"><html:radio name="MskTorokuJoho" property="kessaiConvenienceShubetsu" value="02"  style="ime-mode:disabled"/></div>
                                        </td>
                                        <td>
                                            <img src="../../image/logo_seven.gif"><br/>
                                        </td>
                                        <td>
                                            <b>セブン-イレブン </b><br/>
                                            <a href=<bean:write name="MskTorokuJoho" property="convenienceSevenPc" /> target="_blank">詳細</a>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div align="right"><html:radio name="MskTorokuJoho" property="kessaiConvenienceShubetsu" value="03"  style="ime-mode:disabled"/></div>
                                        </td>
                                        <td>
                                            <img src="../../image/logo_familymart.gif"><br/>
                                        </td>
                                        <td>
                                            <b>ファミリーマート </b><br/>
                                            <a href=<bean:write name="MskTorokuJoho" property="convenienceFamimaPc" /> target="_blank">詳細</a>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div align="right"><html:radio name="MskTorokuJoho" property="kessaiConvenienceShubetsu" value="01"  style="ime-mode:disabled"/></div>
                                        </td>
                                        <td>
                                            <img src="../../image/logo_lawson.gif">
                                            <img src="../../image/logo_seicomart.gif">
                                            <img src="../../image/logo_ministop.gif"/>
                                        </td>
                                        <td>
                                            <b>ローソン</b><a href=<bean:write name="MskTorokuJoho" property="convenienceLawsonPc" /> target="_blank">詳細</a><br/>
                                            <b>セイコーマート</b><font size="-1">（北海道・関東地区のみ）</font><a href=<bean:write name="MskTorokuJoho" property="convenienceSeicoPc" /> target="_blank">詳細</a><br/>
                                            <b>ミニストップ</b><a href=<bean:write name="MskTorokuJoho" property="convenienceMinistopPc" /> target="_blank">詳細</a><br/>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <div align="right"><html:radio name="MskTorokuJoho" property="kessaiConvenienceShubetsu" value="73"  style="ime-mode:disabled"/></div>
                                        </td>
                                        <td>
                                            <img src="../../image/logo_circlek.gif"/>
                                            <img src="../../image/logo_sunkus.gif"/><br/>
                                            <img src="../../image/logo_yamazakidailysore.gif"/>
                                            <img src="../../image/logo_dailyyamazaki.gif"/>
                                            <img src="../../image/logo_threef.gif"/>
                                        </td>
                                        <td>
                                            <b>
                                                サークルＫ・サンクス<br/>
                                                ヤマザキデイリーストアー・デイリーヤマザキ・<br>
                                                スリーエフ<br/>
                                            </b>
                                            <a href=<bean:write name="MskTorokuJoho" property="convenienceOnlinePc" /> target="_blank">詳細</a>
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                    </table>

                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="戻　る" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                            </td>
                        </tr>
                    </table>
                    <table  class="table1" cellspacing="0" cellpadding="0">
                        <tbody>
                            <tr>
                                <td class="TDright">
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