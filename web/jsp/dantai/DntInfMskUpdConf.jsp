<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ page import="jp.co.nii.miw.business.service.mypage.MypMskUpdServ" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMskUpd" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css" >
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="page_top"></div>
        <html:form action="/dantai/Inf/DntInfMskUpdConfAct" onsubmit="disableSubmit(this)">
            <div id="container">
                <div align="center">
                    <!--heder-->
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
                            <td class="right"><bean:message key="title.Dantai_InfMskUpd" /></td>
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
                                <bean:define id="eventCodeDisp" name="MoshikomiJoho" property="eventCodeDisp" type="String"/>
                                <bean:define id="eventCodeDispInp" name="MoshikomiJohoInp" property="eventCodeDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(eventCodeDisp, eventCodeDispInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th>受験希望地</th>
                            <td class="basic">
                                <bean:define id="shikenchiCodeDisp" name="MoshikomiJoho" property="shikenchiCodeDisp" type="String"/>
                                <bean:define id="shikenchiCodeDispInp" name="MoshikomiJohoInp" property="shikenchiCodeDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(shikenchiCodeDisp, shikenchiCodeDispInp)%>
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
                                <bean:write name="MoshikomiJohoInp" property="userId"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">氏名</th>
                            <td class="basic" colspan="2">
                                <bean:define id="shimeiSei" name="MoshikomiJoho" property="shimeiSei" type="String"/>
                                <bean:define id="shimeiSeiInp" name="MoshikomiJohoInp" property="shimeiSei" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(shimeiSei, shimeiSeiInp)%>

                                <bean:define id="shimeiMei" name="MoshikomiJoho" property="shimeiMei" type="String"/>
                                <bean:define id="shimeiMeiInp" name="MoshikomiJohoInp" property="shimeiMei" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(shimeiMei, shimeiMeiInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">フリガナ</th>
                            <td class="basic" colspan="2">
                                <bean:define id="shimeiSeiKana" name="MoshikomiJoho" property="shimeiSeiKana" type="String"/>
                                <bean:define id="shimeiSeiKanaInp" name="MoshikomiJohoInp" property="shimeiSeiKana" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(shimeiSeiKana, shimeiSeiKanaInp)%>

                                <bean:define id="shimeiMeiKana" name="MoshikomiJoho" property="shimeiMeiKana" type="String"/>
                                <bean:define id="shimeiMeiKanaInp" name="MoshikomiJohoInp" property="shimeiMeiKana" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(shimeiMeiKana, shimeiMeiKanaInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">性別</th>
                            <td class="basic" colspan="2">
                                <bean:define id="sexCodeDisp" name="MoshikomiJoho" property="sexCodeDisp" type="String"/>
                                <bean:define id="sexCodeDispInp" name="MoshikomiJohoInp" property="sexCodeDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(sexCodeDisp, sexCodeDispInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">生年月日</th>
                            <td  class="basic" colspan="2">
                                <bean:define id="birthdayDisp2" name="MoshikomiJoho" property="birthdayDisp2" type="String"/>
                                <bean:define id="birthdayDisp2Inp" name="MoshikomiJohoInp" property="birthdayDisp2" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(birthdayDisp2, birthdayDisp2Inp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">住所</th>
                            <td class="basic" colspan="2">
                                <table cellpadding="0" cellspacing="0">
                                    <tr>
                                        <td>
                                            〒
                                            <bean:define id="yubinNoDisp" name="MoshikomiJoho" property="yubinNoDisp" type="String"/>
                                            <bean:define id="yubinNoDispInp" name="MoshikomiJohoInp" property="yubinNoDisp" type="String"/>
                                            <%=MypMskUpdServ.getUpdStringConvert(yubinNoDisp, yubinNoDispInp)%>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="padding-top: 5px;">
                                            <bean:define id="jushoDisp" name="MoshikomiJoho" property="jushoDisp" type="String"/>
                                            <bean:define id="jushoDispInp" name="MoshikomiJohoInp" property="jushoDisp" type="String"/>
                                            <%=MypMskUpdServ.getUpdStringConvert(jushoDisp, jushoDispInp)%>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">ＴＥＬ</th>
                            <td class="basic" style="border: none">
                                <bean:define id="telNo" name="MoshikomiJoho" property="telNo" type="String"/>
                                <bean:define id="telNoInp" name="MoshikomiJohoInp" property="telNo" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(telNo, telNoInp)%>
                            </td>
                            <td class="basic" style="border-left-style: none"> 
                                <bean:define id="extNo" name="MoshikomiJoho" property="extNo" type="String"/>
                                <bean:define id="extNoInp" name="MoshikomiJohoInp" property="extNo" type="String"/>
                                <logic:equal name="MoshikomiJohoInp" property="isExtNo" value ="true">
                                    <logic:equal name="MoshikomiJohoInp" property="isExtNo" value="true">
                                        内線&nbsp;<%=MypMskUpdServ.getUpdStringConvert(extNo, extNoInp)%>
                                    </logic:equal>
                                </logic:equal>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">携帯電話</th>
                            <td class="basic" colspan="2">
                                <bean:define id="cellphoneNo" name="MoshikomiJoho" property="cellphoneNo" type="String"/>
                                <bean:define id="cellphoneNoInp" name="MoshikomiJohoInp" property="cellphoneNo" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(cellphoneNo, cellphoneNoInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">メールアドレス</th>
                            <td class="basic" style="word-wrap: break-word;" colspan="2">
                                <bean:define id="mailAddress" name="MoshikomiJoho" property="mailAddress" type="String"/>
                                <bean:define id="mailAddressInp" name="MoshikomiJohoInp" property="mailAddress" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(mailAddress, mailAddressInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">顔写真ファイル</th>
                            <td class="basic" colspan="2">
                                <div class="divimg">
                                    <logic:equal name="MoshikomiJohoInp" property="gazoUpdFlg" value="<%=MiwConstants.FLG_ON%>">
                                        <img src="../../PictureAction.do?gazoId=<bean:write name="MoshikomiJoho" property="gazoId" />&param=1"  width="128" height="172">
                                    </logic:equal>
                                    <logic:equal name="MoshikomiJohoInp" property="gazoUpdFlg" value="<%=MiwConstants.FLG_OFF%>">
                                        <img src="../../PictureAction.do?gazoId=<bean:write name="MoshikomiJoho" property="gazoId" />&param=2"  width="128" height="172">
                                    </logic:equal>
                                </div>
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
                                <bean:define id="jukoKeikenDisp" name="MoshikomiJoho" property="jukoKeikenDisp" type="String"/>
                                <bean:define id="jukoKeikenDispInp" name="MoshikomiJohoInp" property="jukoKeikenDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(jukoKeikenDisp, jukoKeikenDispInp)%>
                            </td>
                        </tr>
                        <tr>
                            <th>医療保険事務の実務経験</th>
                            <td class="basic">
                                <bean:define id="jitsumuKeikenDisp" name="MoshikomiJoho" property="jitsumuKeikenDisp" type="String"/>
                                <bean:define id="jitsumuKeikenDispInp" name="MoshikomiJohoInp" property="jitsumuKeikenDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(jitsumuKeikenDisp, jitsumuKeikenDispInp)%>
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
                                <bean:define id="passwdQuestionCodeDisp" name="MoshikomiJoho" property="passwdQuestionCodeDisp" type="String"/>
                                <bean:define id="passwdQuestionCodeDispInp" name="MoshikomiJohoInp" property="passwdQuestionCodeDisp" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(passwdQuestionCodeDisp, passwdQuestionCodeDispInp)%> 
                            </td>
                        </tr>
                        <tr>
                            <th>回答</th>
                            <td class="basic">
                                <bean:define id="passwdAnswer" name="MoshikomiJoho" property="passwdAnswer1" type="String"/>
                                <bean:define id="passwdAnswerInp" name="MoshikomiJohoInp" property="passwdAnswer1" type="String"/>
                                <%=MypMskUpdServ.getUpdStringConvert(passwdAnswer, passwdAnswerInp)%> 
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="submit" value="変更" styleClass="buttonNext" style="width: 100px;" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="tableNoFrame">
                        <tr>
                            <td class="TDleft">
                                <html:submit property="back_inp" value="入力画面に戻る" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                            </td>
                            <td class="TDright">
                                <a href="#page_top">▲ページ先頭へ</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </html:form>
    </body>
</html:html>
