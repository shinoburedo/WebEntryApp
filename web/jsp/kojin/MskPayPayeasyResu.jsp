<%@ page contentType="text/html; charset=Windows-31J" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskPayKariResu" /></title>
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
                <form name="myForm">
                    <input type="hidden" value="false" name="flg"/>
                </form>
                <!--heder-->
                <div id="title_header"></div>
                <table class="header" cellpadding="0" cellspacing="0">
                    <tr>
                        <td><img src="../../image/nv_msk_k10.gif" alt="試験申込フロー"></td>
                    </tr>
                </table>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">１０．申込受付完了</td>
                    </tr>
                </table>
                <table class="table1" cellpadding="1" cellspacing="0">
                    <tr>
                        <td class="TDcenter">
                            <br/>
                            <font size="+1">
                            <b>
                                <font class="txtAttention">
                                インターネットによる申込手続きを受け付けました。<br/>
                                ご登録いただいたメールアドレス宛に確認メールを送信しました。<br/>
                                <br/>
                                お申込はまだ完了していません。<br/>
                                払込票を印刷又は払込番号をメモし、<bean:write name="MskTorokuJoho" property="kessaiKigenDisp" />までにＡＴＭ等で<br/>
                                <%=MiwConstants.JUKEN_CHARGE_NAME%>をお支払ください。<br/> 
                                </font>
                                <br/>
                                ※入金確認後、ご登録いただいたメールアドレスに申込完了の<br/>
                                確認メールが届きます。<br/>
                            </b>
                            </font>
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
                        <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                        <td colspan="2">
                            <%=MiwConstants.JUKEN_CHARGE_NAME%>はＡＴＭ等で必ず<bean:write name="MskTorokuJoho" property="kessaiKigenDisp" />までにお支払ください。<br/>
                            お支払のない場合、申込はキャンセルとなります。
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>２</b></td>
                        <td colspan="2">
                            ペイジー決済でお支払の際のご利用明細票は、控えとして大切に保管してください。
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>３</b></td>
                        <td colspan="2">
                            <b style="font-weight: 300;">ペイジー決済での入金が確認できるまで、申込完了のメールは送信されません。</b>
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>４</b></td>
                        <td colspan="2">
                            ペイジー決済でお支払を終えたにもかかわらず、申込完了のメールが届かない場合は、<br>
                        </td>
                    </tr>
                    <tr>
                        <td width="3%">　</td>
                        <td class="TDtop" width="3%">(1)</td>
                        <td width="94%">
                            入金の確認に時間がかかっている（２～３日かかる場合があります）
                        </td>
                    </tr>
                    <tr>
                        <td width="3%">　</td>
                        <td class="TDtop" width="3%">(2)</td>
                        <td width="94%">
                            入力されたメールアドレスが間違っている<br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="3%">　</td>
                        <td class="TDtop" width="3%">(3)</td>
                        <td width="94%">
                            迷惑メール対策でドメイン指定などによるメール着信拒否を行っていること等が考えられます。<br/>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            決済状況・受付状況は、インターネットでもご確認いただけます。<br/>
                            診療報酬請求事務能力認定試験インターネット申込メインメニューの「申込内容確認」よりログインし、「申込内容確認」画面でご覧ください。<br/>
                            なお、ログインにはＩＤとパスワードが必要です。
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableMain" cellpadding="5" cellspacing="0">
                    <tr>
                        <td width="34%" colspan="2"></td>
                        <td width="66%" colspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><b>払込情報</b></td>
                    </tr>
                    <tr>
                        <th rowspan="3"> 払込番号 </th>
                        <th> 収納機関番号 </th>
                        <td class="basic" width="33%">
                            <bean:write name="MskTorokuJoho" property="kessaiUketsukeNo1" />&nbsp;
                        </td>
                        <td class="basic" rowspan="3" width="33%">
                            <table width="99%" borderstyle="">
                                <tr>
                                    <td width="96%">
                                        <div align="center">
                                            <button onclick="window.open('<bean:write name="MskTorokuJoho" property="kessaiConvenienceHaraikomihyoUrl"/>', '', 'width=800,height=700');">払込情報詳細</button>
                                        </div>
                                    </td>
                                    <td width="4%">
                                        &nbsp;
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th> お客様番号 </th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="kessaiUketsukeNo2" />&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <th> 確認番号 </th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="kessaiUketsukeNo3" />&nbsp;
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"> <%=MiwConstants.JUKEN_CHARGE_NAME%> </th>
                        <td class="basic" colspan="2"><bean:write name="MskTorokuJoho" property="kenteiryoDisp" />&nbsp;円</td>
                    </tr>
                    <tr>
                        <th colspan="2"> お支払期限日 </th>
                        <td class="basic" colspan="2">
                            <bean:write name="MskTorokuJoho" property="kessaiKigenYmdDisp" />
                        </td>
                    </tr>
                    <tr>
                        <td class="TDcenter" colspan="4">
                            <b>払込票を印刷又は払込番号をメモし、ＡＴＭ等で<%=MiwConstants.JUKEN_CHARGE_NAME%>をお支払ください。</b><br/> 
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
                        <th>ＩＤ</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="moshikomiUketsukeNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>申込日</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="moshikomibiDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th>決済状況</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="kessaiJokyoKbnDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th>受付状況</th>
                        <td class="basic">
                            <bean:write name="MskTorokuJoho" property="tetsudukiJokyoKbnDisp" />
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
                            <logic:equal name="MskTorokuJoho" property="jukoKeiken" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MskTorokuJoho" property="jukoKeiken" value="">
                                <bean:write name="MskTorokuJoho" property="jukoKeikenDisp" />
                            </logic:notEqual>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">医療保険事務の実務経験</th>
                        <td class="basic" colspan="2">
                            <logic:equal name="MskTorokuJoho" property="jitsumuKeiken" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="MskTorokuJoho" property="jitsumuKeiken" value="">
                                <bean:write name="MskTorokuJoho" property="jitsumuKeikenDisp" />
                            </logic:notEqual>
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
                </table>
                <br>
                <br>
                <table class="tablePayJoho" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>
                            <div align="center">
                                <table width="50%" border="0" cellspacing="0" cellpadding="5">
                                    <tr>
                                        <td>　</td>
                                        <td>　</td>
                                    </tr>
                                    <tr>
                                        <td><b>お支払期限日</b></td>
                                        <td class="TDright"><b>
                                                <bean:write name="MskTorokuJoho" property="kessaiKigenYmdDisp" />
                                            </b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b><%=MiwConstants.JUKEN_CHARGE_NAME%></b></td>
                                        <td class="TDright">
                                            <b>
                                                <bean:write name="MskTorokuJoho" property="kenteiryoDisp" />&nbsp;円
                                            </b>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                    </tr>
                </table>
                <br><br>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <input type="button" value="印　刷"  style="width:100px"  onclick="window.print()" onblur="offenter()" onfocus="onenter()" >
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