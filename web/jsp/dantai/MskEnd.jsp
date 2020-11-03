<%@ page contentType="text/html; charset=Windows-31J" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntMskEnd" /></title>
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
                <!--heder-->
                <div id="title_header"></div>
                <table class="header" cellpadding="0" cellspacing="0">
                    <tr>
                        <td><img src="../../image/nv_dnt_msk7.gif" alt="試験申込フロー"></td>
                    </tr>
                </table>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">７．<bean:message key="title.DntMskEnd" /></td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellpadding="1" cellspacing="0">
                    <tr>
                        <td class="TDcenter" style="line-height: 190%;">
                            <font size="+1"><b>
                                <font class="txtAttention">
                                インターネットによる団体申込者登録手続きを受け付けました。<br/>
                                ご登録いただいたメールアドレス宛に確認メールを送信しました。<br/>
                                <br/>
                                お申込はまだ完了していません。<br/>
                                団体申込ご担当者様による<%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払をもって、申込完了とさせていただきます。
                                </font>
                                <br/>
                                ※入金確認後、ご登録いただいたメールアドレスに申込完了の<br/>
                                確認メールが届きます。<br/>
                            </b>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellpadding="1" cellspacing="0">
                    <tr>
                        <td><hr></td>
                    </tr>
                    <tr>
                        <td class="TDcenter">
                            <font size="+1"><b>この画面の内容を<font class="txtAttention">必ず保存又は印刷</font>し、大切に保管してください。</b></font><br>
                        </td>
                    </tr>
                    <tr>
                        <td><hr></td>
                    </tr>
                </table>
                <br>
                <table class="table1">
                    <tr>
                        <td class="headline">留意事項</td>
                    </tr>
                </table>
                <table class="table1" cellpadding="4">
                    <tr>
                        <td colspan="3">
                            &nbsp;申込情報は、インターネットでもご確認いただけます。<br/>
                            &nbsp;認定試験インターネット団体申込者メインメニューの「申込内容確認」よりログインし、申込内容確認画面でご覧ください。<br/>
                            &nbsp;なお、ログインにはＩＤとパスワードが必要です。ＩＤとパスワードは必ず保存や記録を行ってください。
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableMain" cellspacing="0" cellpadding="8">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th>ＩＤ </th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="moshikomiUketsukeNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>申込日 </th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="moshikomibiDisp" />
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableMain" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="17%"></td>
                        <td width="17%"></td>
                        <td width="33%"></td>
                        <td width="33%"></td>
                    </tr>
                    <tr>
                        <th colspan="2">受験科目</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="eventCodeDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">受験希望地</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="shikenchiCodeDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">氏　　名</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="shimeiSei" />　
                            <bean:write name="MskTorokuJoho" property="shimeiMei" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="shimeiSeiKana" />　
                            <bean:write name="MskTorokuJoho" property="shimeiMeiKana" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">性　　別</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="sexCodeDisp" />
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">生年月日</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="birthdayEraDisp" />
                            <bean:write name="MskTorokuJoho" property="birthdayYy" />年
                            <bean:write name="MskTorokuJoho" property="birthdayMm" />月
                            <bean:write name="MskTorokuJoho" property="birthdayDd" />日
                        </td>
                    </tr>

                    <tr>
                        <th rowspan="2">現住所</th>
                        <th>郵便番号</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="yubinNo1" />-
                            <bean:write name="MskTorokuJoho" property="yubinNo2" />
                        </td>
                    </tr>
                    <tr>
                        <th>住所</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="todofuken" /><br/>
                            <bean:write name="MskTorokuJoho" property="jusho1" /><br/>
                            <bean:write name="MskTorokuJoho" property="jusho2" /><br/>
                            <bean:write name="MskTorokuJoho" property="jusho3" /><br/>
                            <bean:write name="MskTorokuJoho" property="jusho4" />
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2">電話番号</th>
                        <th>ＴＥＬ</th>
                        <td class="basic" style="border: none">
                            <logic:equal name="MskTorokuJoho" property="telNo" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MskTorokuJoho" property="telNo" value="">
                                <bean:write name="MskTorokuJoho" property="telNo" />
                            </logic:notEqual>
                        </td>
                        <td class="basic" style="border-left-style: none"> 
                            <logic:equal name="MskTorokuJoho" property="isExtNo" value ="true">
                                <logic:equal name="MskTorokuJoho" property="isExtNo" value="true">
                                    内線&nbsp;<bean:write name="MskTorokuJoho" property="extNo"/>
                                </logic:equal>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <th>携帯電話</th>
                        <td class="basic" colspan="2">
                            <logic:equal name="MskTorokuJoho" property="cellphoneNo" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MskTorokuJoho" property="cellphoneNo" value="">
                                <bean:write name="MskTorokuJoho" property="cellphoneNo" />
                            </logic:notEqual>
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">メールアドレス</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="mailAddress" />
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">医療保険事務の受講経験</th>
                        <td class="basic" colspan="2">
                            <logic:notEmpty name="MskTorokuJoho" property="jukoKeiken">
                                <bean:write name="MskTorokuJoho" property="jukoKeikenDisp" />
                            </logic:notEmpty>
                            <logic:empty name="MskTorokuJoho" property="jukoKeiken">
                                -&nbsp;
                            </logic:empty>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">医療保険事務の実務経験</th>
                        <td class="basic" colspan="2">
                            <logic:notEmpty name="MskTorokuJoho" property="jitsumuKeiken">
                                <bean:write name="MskTorokuJoho" property="jitsumuKeikenDisp" />
                            </logic:notEmpty>
                            <logic:empty name="MskTorokuJoho" property="jitsumuKeiken">
                                -&nbsp;
                            </logic:empty>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">パスワード</th>
                        <td class="basic" colspan="2"> 
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2">ご本人様<br/>確認用の<br/>質問・回答</th>
                        <th>質問</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="passwdQuestionCode1Disp" />
                        </td>
                    </tr>
                    <tr>
                        <th>回答</th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="passwdAnswer1" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">顔写真ファイル</th>
                        <td class="basic" colspan="2">
                            <img src="../../PictureAction.do?gazoId=<bean:write name="MskTorokuJoho" property="gazoId" />&param=2"  width="128" height="172">
                        </td>
                    </tr>    
                </table>
                <br>
                <br><br>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <input type="button" value="印　刷"  class="submit"  onclick="window.print()" onblur="offenter()" onfocus="onenter()" >
                        </td>
                    </tr>
                </table>
                <table  class="table1" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="TDright TDtop">
                                <input type="button" value="メインメニューに戻る" class="buttonNext" onclick="backMenuDantai('<bean:write name="menuUrl"/>')"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html:html>