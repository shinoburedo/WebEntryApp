<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiaServ" class="jp.co.nii.miw.business.service.MiaServ" scope="request" />
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfUpdConf"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/myp.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body onload="setScrollTop();">
        <div id="container">
            <html:form action="/dantai/Inf/DntInfUpdConfAct.do" onsubmit="disableSubmit(this)">
                <div id="title_header"></div>
                <table class="tableNoFrame" cellpadding="0">
                    <tr>
                        <td class="TDright TDmiddle">
                            <b><bean:write name="DntInfo" property="dantaiName" />代表者&nbsp;様のページ&nbsp;&nbsp;</b>
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
                        <td class="right"><bean:message key="title.Dantai_InfUpdConf"/></td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDcenter">
                            <font size="+1">
                            <b>
                                この時点では、まだ変更は<font class="txtAttention">完了していません。</font><br>
                                内容を確認し、よろしければ「変更」ボタンを押してください。
                            </b>
                            </font>
                        </td>
                    </tr>
                </table>
                <br>
                    <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                        <logic:messagesPresent>
                            <tr>
                                <td><html:errors/><td>
                            </tr>
                        </logic:messagesPresent>
                    </table>                    
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>申込手続状況</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>団体コード</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="dantaiCode" />
                        </td>
                    </tr>
                    <tr>
                        <th>団体申込者登録用ID</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="dantaiMoshikomiUketsukeNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>登録日</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="torokuDateDispSlash" />
                        </td>
                    </tr>
                    <tr>
                        <th>受付日</th>
                        <td class="basic">
                            <logic:empty name="DntInfo" property="kariUketsukeBi">
                                -
                            </logic:empty>
                            <logic:notEmpty name="DntInfo" property="kariUketsukeBi">
                                <bean:write name="DntInfo" property="kariUketsukeBiDispSlash"/>
                            </logic:notEmpty>
                        </td>
                    </tr>
                    <tr>
                        <th>申込完了日</th>
                        <td class="basic">
                            <logic:empty name="DntInfo" property="moshikomiFinishBi">
                                -
                            </logic:empty>
                            <logic:notEmpty name="DntInfo" property="moshikomiFinishBi">
                                <bean:write name="DntInfo" property="moshikomiFinishBiDispSlash"/>
                            </logic:notEmpty>
                        </td>
                    </tr>
                    <tr>
                        <th>決済状況</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="kessaiJokyoKbnDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th>受付状況</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="tetsudukiJokyoKbnDisp"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>団体情報</b></td>
                    </tr>
                </table>
                <table class="tableMain" cellpadding="4" cellspacing="0">
                    <tr>
                        <th width="34%">団体等の名称</th>
                        <td class="basic" width="66%" colspan="2">
                            <bean:define id="dantaiName" name="DntInfo" property="dantaiName" type="String"/>
                            <bean:define id="dantaiNameInp" name="DntInfoInp" property="dantaiName" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiName, dantaiNameInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th>団体等の名称フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:define id="dantaiNameKana" name="DntInfo" property="dantaiNameKana" type="String"/>
                            <bean:define id="dantaiNameKanaInp" name="DntInfoInp" property="dantaiNameKana" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiNameKana, dantaiNameKanaInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            所在地
                        </th>
                        <td class="basic" colspan="2">
                            <bean:define id="yubinno1" name="DntInfo" property="yubinNo1" type="String"/>
                            <bean:define id="yubinno1Inp" name="DntInfoInp" property="yubinNo1" type="String"/>
                            <bean:define id="yubinno2" name="DntInfo" property="yubinNo2" type="String"/>
                            <bean:define id="yubinno2Inp" name="DntInfoInp" property="yubinNo2" type="String"/>
                            <%=MiaServ.getUpdStringConvert(yubinno1, yubinno1Inp)%>-
                            <%=MiaServ.getUpdStringConvert(yubinno2, yubinno2Inp)%><br>
                            <bean:define id="dantaiJushoDisp" name="DntInfo" property="dantaiJushoDisp" type="String"/>
                            <bean:define id="dantaiJushoDispInp" name="DntInfoInp" property="dantaiJushoDisp" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJushoDisp, dantaiJushoDispInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th width="34%">担当者名</th>
                        <td class="basic" width="66%" colspan="2">
                            <bean:define id="dantaiJimuTantoshaShimeiSei" name="DntInfo" property="dantaiJimuTantoshaShimeiSei" type="String"/>
                            <bean:define id="dantaiJimuTantoshaShimeiSeiInp" name="DntInfoInp" property="dantaiJimuTantoshaShimeiSei" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaShimeiSei, dantaiJimuTantoshaShimeiSeiInp)%>&nbsp;&nbsp;
                            <bean:define id="dantaiJimuTantoshaShimeiMei" name="DntInfo" property="dantaiJimuTantoshaShimeiMei" type="String"/>
                            <bean:define id="dantaiJimuTantoshaShimeiMeiInp" name="DntInfoInp" property="dantaiJimuTantoshaShimeiMei" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaShimeiMei, dantaiJimuTantoshaShimeiMeiInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th>担当者名フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:define id="dantaiJimuTantoshaShimeiSeiKana" name="DntInfo" property="dantaiJimuTantoshaShimeiSeiKana" type="String"/>
                            <bean:define id="dantaiJimuTantoshaShimeiSeiKanaInp" name="DntInfoInp" property="dantaiJimuTantoshaShimeiSeiKana" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaShimeiSeiKana, dantaiJimuTantoshaShimeiSeiKanaInp)%>&nbsp;&nbsp;
                            <bean:define id="dantaiJimuTantoshaShimeiMeiKana" name="DntInfo" property="dantaiJimuTantoshaShimeiMeiKana" type="String"/>
                            <bean:define id="dantaiJimuTantoshaShimeiMeiKanaInp" name="DntInfoInp" property="dantaiJimuTantoshaShimeiMeiKana" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaShimeiMeiKana, dantaiJimuTantoshaShimeiMeiKanaInp)%>&nbsp;&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <th>電話番号</th>
                        <td class="basic" style="border: none">
                            <bean:define id="telNo" name="DntInfo" property="dantaiJimuTantoshaTelNo" type="String"/>
                            <bean:define id="telNoInp" name="DntInfoInp" property="dantaiJimuTantoshaTelNo" type="String"/>
                            <%=MiaServ.getUpdStringConvert(telNo, telNoInp)%>
                        </td>
                        <td class="basic" style="border-left-style: none"> 
                            <bean:define id="extNo" name="DntInfo" property="extNo" type="String"/>
                            <bean:define id="extNoInp" name="DntInfoInp" property="extNo" type="String"/>
                            <logic:equal name="DntInfoInp" property="isExtNo" value ="true">
                                <logic:equal name="DntInfoInp" property="isExtNo" value="true">
                                    内線&nbsp;<%=MiaServ.getUpdStringConvert(extNo, extNoInp)%>
                                </logic:equal>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <th>ＦＡＸ番号</th>
                        <td class="basic" colspan="2">
                            <bean:define id="dantaiJimuTantoshaFaxNo" name="DntInfo" property="dantaiJimuTantoshaFaxNo" type="String"/>
                            <bean:define id="dantaiJimuTantoshaFaxNoInp" name="DntInfoInp" property="dantaiJimuTantoshaFaxNo" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaFaxNo, dantaiJimuTantoshaFaxNoInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th>メールアドレス</th>
                        <td class="basic" colspan="2">
                            <bean:define id="dantaiJimuTantoshaMailAddress" name="DntInfo" property="dantaiJimuTantoshaMailAddress" type="String"/>
                            <bean:define id="dantaiJimuTantoshaMailAddressInp" name="DntInfoInp" property="dantaiJimuTantoshaMailAddress" type="String"/>
                            <%=MiaServ.getUpdStringConvert(dantaiJimuTantoshaMailAddress, dantaiJimuTantoshaMailAddressInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th  rowspan="2">受験申込者数</th>
                        <td class="basic" width="420" colspan="2">
                            医科　：&nbsp;&nbsp;
                            <logic:equal name="DntInfoInp" property="isTetsudukiJokyoShoninMae" value="true">
                                <bean:define id="moshikomishaSuIka" name="DntInfo" property="moshikomishaSuIka" type="String"/>
                                <bean:define id="moshikomishaSuIkaInp" name="DntInfoInp" property="moshikomishaSuIka" type="String"/>
                                <%=MiaServ.getUpdStringConvert(moshikomishaSuIka, moshikomishaSuIkaInp)%>
                            </logic:equal>
                            <logic:equal name="DntInfoInp" property="isTetsudukiJokyoShoninMae" value="false">
                                <bean:write name="DntInfo" property="moshikomishaSuIka"/>
                            </logic:equal>
                            名
                        </td>
                    </tr>
                    <tr>
                        <td class="basic" width="420" colspan="2">
                            歯科　：&nbsp;&nbsp;
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="true">
                                <bean:define id="moshikomishaSuShika" name="DntInfo" property="moshikomishaSuShika" type="String"/>
                                <bean:define id="moshikomishaSuShikaInp" name="DntInfoInp" property="moshikomishaSuShika" type="String"/>
                                <%=MiaServ.getUpdStringConvert(moshikomishaSuShika, moshikomishaSuShikaInp)%>
                            </logic:equal>
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="false">
                                <bean:write name="DntInfo" property="moshikomishaSuShika"/>
                            </logic:equal>
                            名
                        </td>
                    </tr>
                    <tr>
                        <th width="220">発送物の送付先</th>
                        <td class="basic" width="420" colspan="2">
                            <bean:define id="hassosakiKbnDisp" name="DntInfo" property="hassosakiKbnDisp" type="String"/>
                            <bean:define id="hassosakiKbnDispInp" name="DntInfoInp" property="hassosakiKbnDisp" type="String"/>
                            <%=MiaServ.getUpdStringConvert(hassosakiKbnDisp, hassosakiKbnDispInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th width="220"><%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法</th>
                        <td class="basic" width="420" colspan="2">
                            <logic:equal name="DntInfo" property="isKessaiJokyoMi" value="true">
                                <bean:define id="kessaiHohoKbnDisp" name="DntInfo" property="kessaiHohoKbnDisp" type="String"/>
                                <bean:define id="kessaiHohoKbnDispInp" name="DntInfoInp" property="kessaiHohoKbnDisp" type="String"/>
                                <%=MiaServ.getUpdStringConvert(kessaiHohoKbnDisp, kessaiHohoKbnDispInp)%>
                            </logic:equal>
                            <logic:equal name="DntInfo" property="isKessaiJokyoMi" value="false">
                                <bean:write name="DntInfo" property="kessaiHohoKbnDisp"/>
                            </logic:equal>
                        </td>
                    </tr>

                    <tr>
                        <th width="220">団体パスワード<br>
                            <font size="-2">（団体代表者が使用するパスワードです。）</font>
                        </th>
                        <td class="basic" width="420" colspan="2">
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th width="220">団体申込者登録用パスワード<br>
                            <font size="-2">（団体申込者が使用するパスワードです。）</font>
                        </th>
                        <td class="basic" width="420" colspan="2">
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th width="220">通信欄</th>
                        <td class="basic" width="420" colspan="2">
                            <bean:define id="moshikomiMemoDisp" name="DntInfo" property="moshikomiMemoDisp" type="String"/>
                            <bean:define id="moshikomiMemoDispInp" name="DntInfoInp" property="moshikomiMemoDisp" type="String"/>
                            <%=MiaServ.getUpdStringConvert(moshikomiMemoDisp, moshikomiMemoDispInp)%>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td><b>団体代表者様確認用の質問・回答</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>質問</th>
                        <td class="basic">
                            <bean:define id="passwdQuestionCode1Disp" name="DntInfo" property="passwdQuestionCode1Disp" type="String"/>
                            <bean:define id="passwdQuestionCode1DispInp" name="DntInfoInp" property="passwdQuestionCode1Disp" type="String"/>
                            <%=MiaServ.getUpdStringConvert(passwdQuestionCode1Disp, passwdQuestionCode1DispInp)%>
                        </td>
                    </tr>
                    <tr>
                        <th>回答</th>
                        <td class="basic">
                            <bean:define id="passwdAnswer1" name="DntInfo" property="passwdAnswer1" type="String"/>
                            <bean:define id="passwdAnswer1Inp" name="DntInfoInp" property="passwdAnswer1" type="String"/>
                            <%=MiaServ.getUpdStringConvert(passwdAnswer1, passwdAnswer1Inp)%>

                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="change" value="変更" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this);" styleClass="buttonNext" style="width:100px"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDleff">
                            <html:submit property="back" value="入力画面に戻る" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this);"/>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>