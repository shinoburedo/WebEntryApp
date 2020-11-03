<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MypMskInf" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../js/common.js"></script>
        <script type="text/JavaScript" src="../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../css/myp.css">
        <link type="text/css" rel="stylesheet" href="../css/common.css">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="page_top"></div>
        <div id="container">
            <div align="center">
                <html:form action="/Myp/MypMskInfAct" onsubmit="disableSubmit(this)">
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="tableNoFrame" cellpadding="0">
                        <tr>
                            <td class="TDright TDmiddle">
                                <b><bean:write name="MoshikomiJoho" property="shimeiDisp"/>&nbsp;様のページ&nbsp;&nbsp;</b>
                            </td>
                            <td class="TDright TDmiddle" width="70">
                                <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='./MypLogoutAct.do'"/>
                            </td>
                        </tr>
                    </table>
                    <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                        <logic:messagesPresent>
                            <tr>
                                <td><html:errors/><td>
                            </tr>
                        </logic:messagesPresent>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">申込内容確認</td>
                        </tr>
                    </table>
                    <br>
                    <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                        <html:messages id="msg" message="true">
                            <tr>
                                <td class="TDCenter">
                                    <font class="txtMessage">
                                        <bean:write name="msg" /><br>
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td height="15"></td>
                            </tr>
                        </html:messages>
                    </table>
                    <table class="tableNoFrame">
                        <%-- 個人申込かつ未支払いの場合 --%>
                        <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_KOJIN%>">
                            <logic:equal name="MoshikomiJoho" property="isKessaiHaraikomiFinish" value="false">
                                <tr></tr>                                  
                                <tr>
                                    <td class="TDleft">
                                        <font class="txtAttention"><b>
                                            <logic:equal name="MoshikomiJoho" property="isConveniKessai" value="true">
                                                コンビニでのお支払の確認ができていないため、お申込は完了していません。<br>
                                            </logic:equal>
                                            <logic:equal name="MoshikomiJoho" property="isPayeasyKessai" value="true">
                                                ペイジーでのお支払の確認ができていないため、お申込は完了していません。<br>
                                            </logic:equal>
                                            <bean:write name="MoshikomiJoho" property="kessaiKigenBiDisp"/>までにお支払ください。<br><br>
                                        </b></font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="TDleft"><b>
                                            入金の確認に１～２時間を要する場合があります。すでに入金された方は、後日改めてご確認ください。
                                            お支払に必要な番号をお忘れの方は、「払込情報表示」ボタンを押してください。<br><br>
                                        </b>
                                    </td>
                                </tr>
                                <tr></tr>
                            </logic:equal>
                            <tr>
                                <td class="TDleft"><b>
                                        受験申込内容の変更を行う場合は、画面下部にある「申込内容の変更」ボタンを押してください。<br><br>
                                    </b>
                                </td>
                            </tr>
                        </logic:equal>
                        <tr>
                            <td class="TDleft"><b>
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
                                申込内容の変更は<font class="txtAttention"><bean:write name="MypageJoho" property="kikanUpdShuryoDateDisp"/></font>まで可能です。期限後の変更はできません。
                            </td>
                        </tr>
                    </table>
                    <br>
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
                            <th>ＩＤ</th>
                            <td class="basic">
                                <bean:write name="MoshikomiJoho" property="userId"/>
                            </td>
                        </tr>
                        <tr>
                            <th>受付日</th>
                            <td class="basic">
                                <logic:empty name="MoshikomiJoho" property="kariUketsukeBi">
                                    -
                                </logic:empty>
                                <logic:notEmpty name="MoshikomiJoho" property="kariUketsukeBi">
                                    <bean:write name="MoshikomiJoho" property="kariUketsukeBiDisp"/>
                                </logic:notEmpty>
                            </td>
                        </tr>
                        <tr>
                            <th>申込完了日</th>
                            <td class="basic">
                                <logic:empty name="MoshikomiJoho" property="moshikomiFinishBi">
                                    -
                                </logic:empty>
                                <logic:notEmpty name="MoshikomiJoho" property="moshikomiFinishBi">
                                    <bean:write name="MoshikomiJoho" property="moshikomiFinishBiDisp"/>
                                </logic:notEmpty>
                            </td>
                        </tr>
                        <tr>
                            <th>出願区分</th>
                            <td class="basic">
                                <bean:write name="MoshikomiJoho" property="kojinDantaiKbnDisp"/>
                            </td>
                        </tr>
                        <tr>
                            <th>決済状況</th>
                            <td class="basic">
                                <bean:write name="MoshikomiJoho" property="kessaiJokyoKbnDisp"/>
                            </td>
                        </tr>
                        <tr>
                            <th>受付状況</th>
                            <td class="basic">
                                <bean:write name="MoshikomiJoho" property="tetsudukiJokyoKbnDisp"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <logic:equal name="MoshikomiJoho" property="isDantaiMoshikomi" value="true">
                        <table class="tableNoFrame"> 
                            <tr>
                                <td><b>申込団体</b></td>
                            </tr>
                        </table>
                        <table class="tableMain">
                            <tr>
                                <td width="34%"></td>
                                <td width="66%"></td>
                            </tr>
                            <tr>
                                <th>団体申込者登録用ID</th>
                                <td class="basic">
                                    <bean:write name="MoshikomiJoho" property="dantaiMoshikomiUketsukeNo"/>
                                </td>
                            </tr>
                            <tr>
                                <th>団体名称</th>
                                <td class="basic">
                                    <bean:write name="MoshikomiJoho" property="dantaiName"/>
                                </td>
                            </tr>
                        </table> 
                        <br>
                    </logic:equal>
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
                                〒<bean:write name="MoshikomiJoho" property="yubinNoDisp"/><br>
                                <bean:write name="MoshikomiJoho" property="jushoDisp"/>
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
                                                <img src="../PictureAction.do?gazoId=<bean:write name="MoshikomiJoho" property="gazoId" />&param=2"  width="128" height="172">
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
                                                            <%-- 個人申込の場合 --%>
                                                            <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_KOJIN%>">
                                                                画面下部にある「申込内容の変更」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                            </logic:equal>

                                                            <%-- 団体申込の場合 --%>
                                                            <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_DANTAI%>">
                                                                画面下部にある「顔写真の再提出」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                            </logic:equal>
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
                                                            <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_KOJIN%>">
                                                                画面下部にある「申込内容の変更」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                            </logic:equal>

                                                            <%-- 団体申込の場合 --%>
                                                            <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_DANTAI%>">
                                                                画面下部にある「顔写真の再提出」ボタンをクリックして、正しい顔写真ファイルを再アップロードしてください。
                                                            </logic:equal>
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
                    <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_KOJIN%>">
                        <table class="tableNoFrame">
                            <tr>
                                <td><b>決済情報</b></td>
                            </tr>
                        </table>
                        <table class="tableMain">
                            <tr>
                                <td width="17%"></td>
                                <td width="17%"></td>
                                <td width="66%"></td>
                            </tr>

                            <tr>
                                <th colspan="2">決済方法</th>
                                <td class="basic">
                                    <table width="99%">
                                        <tr>
                                            <td>
                                                <bean:write name="MoshikomiJoho" property="kessaiHohoKbnDisp"/>
                                            </td>
                                            <logic:equal name="MoshikomiJoho" property="isCreditKessai" value="false">
                                                <td class="TDright">
                                                    <input type="button" value="払込情報表示" onblur="offenter()" onfocus="onenter()" onclick="window.open('<bean:write name="MoshikomiJoho" property="kessaiConvenienceHaraikomihyoUrl"/>')" />
                                                </td>
                                            </logic:equal>
                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <tr>
                                <th colspan="2">ご請求金額</th>
                                <td class="basic">
                                    <bean:write name="MoshikomiJoho" property="kessaiKingakuDisp"/>円
                                </td>
                            </tr>
                            <logic:equal name="MoshikomiJoho" property="isConveniKessai" value="true">
                                <tr>
                                    <th colspan="2">ご利用コンビニ</th>
                                    <td class="basic">
                                        <bean:write name="MoshikomiJoho" property="kessaiConvenienceShubetsuDisp"/>
                                    </td>
                                </tr>
                            </logic:equal>
                            <logic:equal name="MoshikomiJoho" property="isCreditKessai" value="false">
                            <tr>
                                <th colspan="2">お支払期限日</th>
                                <td class="basic">
                                    <bean:write name="MoshikomiJoho" property="kessaiKigenBiDispSlash" />
                                </td>
                            </tr>
                            </logic:equal>
                        </table>
                        <br>
                    </logic:equal>   
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
                                <%-- 更新可能期間のみボタンを表示する --%>
                                <logic:equal name="MypageJoho" property="isKikanUpd" value="true">
                                    <%-- 個人申込の場合 --%>
                                    <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_KOJIN%>">
                                        <html:submit property="submit" value="申込内容の変更" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                    </logic:equal>

                                    <%-- 団体申込の場合 --%>
                                    <logic:equal name="MoshikomiJoho" property="kojinDantaiKbn" value="<%=MiwConstants.KOJIN_DANTAI_KBN_DANTAI%>">
                                        <%-- 補正依頼区分　補正依頼 --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_IRAI%>">
                                            <html:submit property="revise" value="顔写真の再提出" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                        </logic:equal>

                                        <%-- 補正依頼区分　補正済み --%>
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_HOSEI%>">
                                            <html:submit property="revise" value="顔写真の再提出" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                        </logic:equal>
                                    </logic:equal>
                                </logic:equal>
                            </td>
                            <td width="20">
                            </td>
                            <td class="TDcenter">
                                <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='./MypLogoutAct.do'"/>
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
                </html:form>
            </div>
        </div>
    </body>
</html:html>
