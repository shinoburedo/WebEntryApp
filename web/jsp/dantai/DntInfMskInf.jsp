<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMskInf"/></title>
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
            <html:form action="/dantai/Inf/DntInfMskInfAct.do" onsubmit="disableSubmit(this)">
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
                        <td class="right"><bean:message key="title.Dantai_InfMskInf"/></td>
                    </tr>
                </table>
                <br>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDleft"><b>
                                申込内容の変更を行う場合は、画面下部にある「申込者情報の変更」ボタンを押してください。<br><br>
                                「顔写真ファイル」が「補正依頼あり」になっている方は、正しい顔写真ファイルを再度アップロードしてください。
                            </b>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableExBox" cellpadding="4">
                    <tr>
                        <td class="headline" colspan="2">注意事項</td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>１</b></td>
                        <td>
                            申込内容の変更は<font class="txtAttention"><bean:write name="DntInfMenu" property="dntResUpdKigenDisp" /></font>まで可能です。期限後の変更はできません。
                        </td>
                    </tr>
                </table>
                <logic:messagesPresent message="true">
                    <table class="tableNoFrame" cellpadding="4" cellspacing="4">
                        <tr><td height="15"></td></tr>
                        <tr>
                            <td class="TDcenter">
                                <font class="txtAttention">
                                <html:messages id="msg" message="true" property="cancel">
                                    <b>！</b><bean:write name="msg" filter="false" ignore="true" /><br>
                                </html:messages>
                                </font>
                            </td>
                        </tr>
                    </table>
                </logic:messagesPresent>
                <br>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>受験情報</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>受験科目</th>
                        <td class="basic">
                            <bean:write name="MoshikomiJoho" property="eventCodeDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th>受験希望地</th>
                        <td class="basic">
                            <bean:write name="MoshikomiJoho" property="shikenchiCodeDisp"/>
                        </td>
                    </tr>
                </table> 
                <br>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>基本情報</b></td>
                    </tr>
                </table>
                <table class="tableMain" style="table-layout:fixed;">
                    <tr>
                        <td width="17%"></td>
                        <td width="17%"></td>
                        <td width="33%"></td>
                        <td width="33%"></td>
                    </tr>
                    <tr>
                        <th colspan="2">ＩＤ</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MoshikomiJoho" property="userId"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">氏名</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MoshikomiJoho" property="shimeiSei"/>
                            <bean:write name="MoshikomiJoho" property="shimeiMei"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MoshikomiJoho" property="shimeiSeiKana"/>
                            <bean:write name="MoshikomiJoho" property="shimeiMeiKana"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">性別</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MoshikomiJoho" property="sexCodeDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">生年月日</th>
                        <td  class="basic" colspan="2">
                            <bean:write name="MoshikomiJoho" property="birthdayDisp2"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">現住所</th>
                        <td class="basic" colspan="2">

                            <table cellpadding="0" cellspacing="0" width="100%">
                                <tr>
                                    <td>
                                        〒<bean:write name="MoshikomiJoho" property="yubinNoDisp"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td style="padding-top: 5px;">
                                        <bean:write name="MoshikomiJoho" property="jushoDisp"/>
                                    </td>
                                </tr>
                            </table>


                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">ＴＥＬ</th>
                        <td class="basic" style="border: none">
                            <logic:equal name="MoshikomiJoho" property="telNo" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MoshikomiJoho" property="telNo" value="">
                                <bean:write name="MoshikomiJoho" property="telNo" />
                            </logic:notEqual>
                        </td>
                        <td class="basic" style="border-left-style: none"> 
                            <logic:equal name="MoshikomiJoho" property="isExtNo" value ="true">
                                <logic:equal name="MoshikomiJoho" property="isExtNo" value="true">
                                    内線&nbsp;<bean:write name="MoshikomiJoho" property="extNo"/>
                                </logic:equal>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">携帯電話</th>
                        <td class="basic" colspan="2">
                            <logic:notEmpty name="MoshikomiJoho" property="cellphoneNo">
                                <bean:write name="MoshikomiJoho" property="cellphoneNo" />
                            </logic:notEmpty>
                            <logic:empty name="MoshikomiJoho" property="cellphoneNo">
                                -&nbsp;
                            </logic:empty>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">メールアドレス</th>
                        <td class="basic" style="word-wrap: break-word;" colspan="2">
                            <bean:write name="MoshikomiJoho" property="mailAddress"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">顔写真ファイル</th>
                        <td class="basic" style="word-wrap: break-word;" colspan="2">
                            <table style="margin-left: 0em;">
                                <tr>
                                    <td><div class="divimg">
                                            <img src="../../PictureAction.do?gazoId=<bean:write name="MoshikomiJoho" property="gazoId" />&param=2"  width="128" height="172">
                                        </div>
                                    </td>
                                    <td class="TDbottom" >
                                        <%-- 補正依頼区分　依頼なし →表示無し --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_MI%>">

                                        </logic:equal>

                                        <%-- 補正依頼区分　補正依頼 →補正依頼区分名、期限日、「画面下にある～」表示 --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_IRAI%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJoho" property="hoseiIraiKbnDisp"/><br />
                                                        期限日：<bean:write name="MoshikomiJoho" property="hoseiIraiKigenBiDisp"/><br></font>
                                                        画面下部にある「申込内容の変更」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                    </td>
                                                </tr>
                                            </table>
                                        </logic:equal>

                                        <%-- 補正依頼区分　補正済み →補正依頼区分名、写真更新日(補正対応日)、「画面下にある～」表示 --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_HOSEI%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJoho" property="hoseiIraiKbnDisp"/><br />
                                                        写真更新日：<bean:write name="MoshikomiJoho" property="hoseiTaioDateTimeDisp"/><br></font>
                                                        <%-- 個人申込の場合 --%>
                                                        画面下部にある「申込内容の変更」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                    </td>
                                                </tr>
                                            </table>
                                        </logic:equal>

                                        <%-- 補正依頼区分　補正確認済み →補正依頼区分名、写真確認日、表示 --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_KAKUNIN%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJoho" property="hoseiIraiKbnDisp"/><br />
                                                        写真確認日：<bean:write name="MoshikomiJoho" property="hoseiFinishDateTimeDisp"/></font>
                                                    </td>
                                                </tr>
                                            </table>
                                        </logic:equal>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableMain">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>医療保険事務の受講経験</th>
                        <td class="basic">
                            <logic:notEmpty name="MoshikomiJoho" property="jukoKeiken">
                                <bean:write name="MoshikomiJoho" property="jukoKeikenDisp" />
                            </logic:notEmpty>
                            <logic:empty name="MoshikomiJoho" property="jukoKeiken">
                                -&nbsp;
                            </logic:empty>
                        </td>
                    </tr>
                    <tr>
                        <th>医療保険事務の実務経験</th>
                        <td class="basic">
                            <logic:notEmpty name="MoshikomiJoho" property="jitsumuKeiken">
                                <bean:write name="MoshikomiJoho" property="jitsumuKeikenDisp" />
                            </logic:notEmpty>
                            <logic:empty name="MoshikomiJoho" property="jitsumuKeiken">
                                -&nbsp;
                            </logic:empty>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td><b>ご本人様確認用の質問・回答</b></td>
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
                            <bean:write name="MoshikomiJoho" property="passwdQuestionCodeDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th>回答</th>
                        <td class="basic">
                            <bean:write name="MoshikomiJoho" property="passwdAnswerDisp"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <input type="button" value = "申込者一覧に戻る" onblur="offenter()" onfocus="onenter()" onclick="location.href='./DntInfMskListAct.do?page=<bean:write name="DntMskSearch" property="nowPage"/>'" class="buttonNext"/>
                        </td>
                        <td class="TDcenter">
                            <logic:equal name="DntInfMenu" property="dntResUpdKigenFlg" value="1">
                                <logic:equal name="MoshikomiJoho" property="tetsudukiJokyoKbn" value="<%=MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI%>">
                                    <input type="button" value="申込者情報の変更" disabled="disabled" class="buttonNext"/>
                                </logic:equal>
                                <logic:notEqual name="MoshikomiJoho" property="tetsudukiJokyoKbn" value="<%=MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI%>">
                                    <html:submit property="submit" value="申込者情報の変更" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this);" styleClass="buttonNext"/>
                                </logic:notEqual>
                            </logic:equal>
                            <logic:notEqual name="DntInfMenu" property="dntResUpdKigenFlg" value="1">
                                <input type="button" value="申込者情報の変更" disabled="disabled" class="buttonNext"/>
                            </logic:notEqual>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDright">
                            <a href="#">▲ページ先頭へ</a>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>