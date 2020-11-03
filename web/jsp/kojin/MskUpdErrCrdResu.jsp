<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.syserr" /></title>
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
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">システムエラー</td>
                    </tr>
                </table>
                <br>
                <br>
                <table  class="table1" border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                        <tr>
                            <td valign="top"><font color="red" style="size: 20px"><b>！</b>
                                <b>システムエラーが発生しました。</b></font><br><br>
                                <b>申込内容確認がしばらくご利用できない状況になります。</b><br>
                                <b>復旧次第、下記メールアドレス宛にご連絡しますので、しばらくお待ちください。 </b><br>
                                <b>復旧には２、３日かかることがあります。 ご不明な点は下記までご連絡ください。 </b><br>
                                <b>クレジットカード会社が発行するご利用明細書は、控えとして大切に保管してください。</b><br>
                            </td>
                        </tr>
                    </tbody>
                </table>
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
                <table class="tableMain" cellspacing="0" cellpadding="5">
                    <tr>
                        <td width="17%"></td>
                        <td width="17%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <th colspan="2">受験科目</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="eventCodeDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">受験希望地</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="shikenchiCodeDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">氏　　名</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="shimeiSei" />　
                            <bean:write name="MskTorokuJoho" property="shimeiMei" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">フリガナ</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="shimeiSeiKana" />　
                            <bean:write name="MskTorokuJoho" property="shimeiMeiKana" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">性　　別</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="sexCodeDisp" />
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">生年月日</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="birthdayEraDisp" />
                            <bean:write name="MskTorokuJoho" property="birthdayYy" />年
                            <bean:write name="MskTorokuJoho" property="birthdayMm" />月
                            <bean:write name="MskTorokuJoho" property="birthdayDd" />日
                        </td>
                    </tr>

                    <tr>
                        <th rowspan="2">現住所</th>
                        <th>郵便番号</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="yubinNo1" />-
                            <bean:write name="MskTorokuJoho" property="yubinNo2" />
                        </td>
                    </tr>
                    <tr>
                        <th>住所</th>
                        <td class="basic">
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
                        <td class="basic">
                            <logic:equal name="MskTorokuJoho" property="telNo" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MskTorokuJoho" property="telNo" value="">
                                <bean:write name="MskTorokuJoho" property="telNo" />
                            </logic:notEqual>
                        </td>
                    </tr>
                    <tr>
                        <th>携帯電話</th>
                        <td class="basic">
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
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="mailAddress" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">医療保険事務の受講経験</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="jukoKeikenDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">医療保険事務の実務経験</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="jitsumuKeikenDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">パスワード</th>
                        <td class="basic">
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2">ご本人様<br/>確認用の<br/>質問・回答</th>
                        <th>質問</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="passwdQuestionCode1Disp" />
                        </td>
                    </tr>

                    <tr>
                        <th>回答</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="passwdAnswer1" />
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableMain" cellpadding="5" cellspacing="0">
                    <tr>
                        <td width="34%"></td>
                        <td width="66%"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><b>決済内容</b></td>
                    </tr>
                    <tr>
                        <th>決済方法</th>
                        <td class="basic">クレジットカード決済</td>
                    </tr>
                    <tr>
                        <th>クレジットカードの<br>有効期限</th>
                        <td class="basic">
                            20<bean:write name="MskTorokuJoho" property="expYy"/>年
                            <bean:write name="MskTorokuJoho" property="expMm"/>月
                        </td>
                    </tr>
                    <tr>
                        <th>クレジットカードの<br>番号</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="cardNo1"/>-
                            <bean:write name="MskTorokuJoho" property="cardNo2"/>-
                            <bean:write name="MskTorokuJoho" property="cardNo3"/>-
                            XXXX<br/>
                            (セキュリティ保護のため、カード番号の一部のみ表示しています。)
                        </td>
                    </tr>
                </table>

                <br>
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
                                        <td class="td3"><b>　<bean:write name="MskTorokuJoho" property="kenteiryoDisp"/>円</b></td>
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
                <table class="table1" cellspacing="0" cellpadding="0" border="0">
                    <tbody>
                        <tr>
                            <td class="TDcenter">
                                <IFRAME src="../../html/callcenter.html" class="iframeCall" height="200">
                                </IFRAME>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br>
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
                                <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="location.href='../MenuAct.do'"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html:html>
