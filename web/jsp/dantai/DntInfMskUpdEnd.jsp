<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMskUpdEnd" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css">
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="page_top"></div>
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
                        <td class="right"><bean:message key="title.Dantai_InfMskUpdEnd" /></td>
                    </tr>
                </table>
                <br>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDcenter">
                            <font size="+2"><b>申込内容の変更が完了しました。</b></font><br>
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

                            <table cellpadding="0" cellspacing="0">
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
                            <bean:write name="MoshikomiJoho" property="cellphoneNo"/>
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
                                                            期限日：<bean:write name="MoshikomiJoho" property="hoseiIraiKigenBiDisp"/></font>
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
                                                            写真更新日：<bean:write name="MoshikomiJoho" property="hoseiTaioDateTimeDisp"/></font>
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
                            <bean:write name="MoshikomiJoho" property="jukoKeikenDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th>医療保険事務の実務経験</th>
                        <td class="basic">
                            <bean:write name="MoshikomiJoho" property="jitsumuKeikenDisp"/>
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
                <table cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <input type="button" value="申込内容確認に戻る" onblur="offenter()" onfocus="onenter()" onclick="location.href='./DntInfMskInfInitAct.do?no=<bean:write name="MoshikomiJoho" property="userId"/>&event=<bean:write name="MoshikomiJoho" property="eventCode"/>'" class="buttonNext" />
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDright">
                            <a href="#page_top">▲ページ先頭へ</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html:html>
