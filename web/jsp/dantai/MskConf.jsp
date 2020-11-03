<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntMskConf" /></title>
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
            <html:form action="/dantai/Msk/MskConfAct"  onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <div align="center">
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_dnt_msk6.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <br>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">６．<bean:message key="title.DntMskConf" /></td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                &nbsp;以下の入力内容を確認し、誤りがない場合は「登録」ボタンを押してください。<br>
                                &nbsp;誤りがある場合は、「登録内容を修正する」ボタンを押し、正しい情報を入力し直してください。
                            </td>
                        </tr>
                    </table>
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
                                <logic:equal name="MskTorokuJoho" property="isExtNo" value="true">
                                    内線&nbsp;<bean:write name="MskTorokuJoho" property="extNo"/>
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
                                <bean:write name="MskTorokuJoho" property="jukoKeikenDisp" />
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">医療保険事務の実務経験</th>
                            <td class="basic" colspan="2">
                                <bean:write name="MskTorokuJoho" property="jitsumuKeikenDisp" />
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
                                <img src="../../PictureAction.do?gazoId=<bean:write name="MskTorokuJoho" property="gazoId" />&param=1"  width="128" height="172">
                            </td>
                        </tr>    
                    </table>

                    <br>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="登録内容を修正する"style="width:150px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="登　録" style="width:150px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
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
        </div>
    </body>
</html:html>
