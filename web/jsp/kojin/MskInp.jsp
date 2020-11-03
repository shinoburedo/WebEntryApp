<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskInp"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
    </head>
    <body onload="setScrollTop();openYubin();">
        <div id="container">
            <div align="center">
                <html:form action="/kojin/Msk/MskInpAct">
                    <html:hidden property="scrollTop" />
                    <html:hidden property="yubinFlg" />
                    <html:hidden property="chimeiFlg" />
                    <html:hidden property="dantaiFlg" />
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k3.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">３．申込情報入力</td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1">
                        <tr>
                            <td>
                                以下の点にご注意の上、全ての項目を入力し、「次へ」ボタンを押してください。<br/>
                                メインメニューに戻りたい場合は「メインメニューに戻る」ボタンを押してください。
                            </td>
                        </tr>
                    </table>
                    <br/>

                    <table class="table1" cellpadding="4">
                        <tr>
                            <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                            <td width="97%">ブラウザの「戻る」「進む」「更新」(「再読み込み」)ボタンは使用しないでください。 </td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>２</b></td>
                            <td>日本語入力の切り替えは自動的に行われますので、そのまま入力してください。「半角英数字」「全角」など指定された文字以外を入力した場合は、エラーが表示され訂正を求められます。</td>
                        </tr>
                        <tr>
                            <td class="TDtop TDcenter"><b>３</b></td>
                            <td>
                                セキュリティ保護のため、入力作業は３０分以内に終えてください。 ３０分を超過した場合は、お手数ですがログインからやり直してください。
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
                    <br>

                    <table class="tableMain" cellpadding="5" cellspacing="0">
                        <tr>
                            <td width="10%"></td>
                            <td width="8%"></td>
                            <td width="12%"></td>
                            <td width="35%"></td>
                            <td width="35%"></td>
                        </tr>
                        <tr>
                            <th colspan="3">受験科目<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(1)"/>" colspan="2">
                                <logic:iterate id="item" name="EventCodeList">
                                    <html:radio name="MskTorokuJoho" property="eventCode" idName="item" value="value"/>
                                    <bean:write name="item" property="label"/>
                                </logic:iterate>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="3">受験希望地<br><font class="txtAttention">（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(9)"/>" colspan="2">
                                <html:select name="MskTorokuJoho" property="shikenchiCode">
                                    <html:options collection="ShikenchiList" property="value" labelProperty="label" />
                                </html:select>
                                <font size="-1"><a href="../../pdf/KaijoList.pdf" target="_blank">会場一覧はこちら</a></font>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="3">氏名<br><font class="txtAttention">（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(12)"/>" colspan="2">
                                <table cellspacing="0" cellpadding="1" width="99%">
                                    <tr>
                                        <td class="TDmiddle">
                                            姓　<html:text name="MskTorokuJoho" property="shimeiSei" size="25" maxlength="50" style="ime-mode:active"/>　
                                            名　<html:text name="MskTorokuJoho" property="shimeiMei" size="25" maxlength="50" style="ime-mode:active"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            例）姓：試験　名：太郎<br>
                                            使用する文字は、「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」
                                            を全角で入力してください。<br>
                                            氏名に規定以外の文字が含まれている場合は置き換え可能な文字で入力し、試験日当日に担当試験官まで変更の旨を連絡してください。
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>

                        <tr>
                            <th colspan="3">フリガナ<br><font class="txtAttention">（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(13)"/>" colspan="2">
                                <table cellspacing="0" cellpadding="1" width="99%">
                                    <tr>
                                        <td class="TDmiddle">
                                            姓　<html:text name="MskTorokuJoho" property="shimeiSeiKana" size="25" maxlength="50" style="ime-mode:active"/>　
                                            名　<html:text name="MskTorokuJoho" property="shimeiMeiKana" size="25" maxlength="50" style="ime-mode:active"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            例）姓：シケン　名：タロウ（全角カタカナ）<br>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="3">性　別<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(15)"/>" colspan="2">
                                <html:radio name="MskTorokuJoho" property="sexCode" value="1" style="ime-mode:disabled"/>男性&nbsp;&nbsp;
                                <html:radio name="MskTorokuJoho" property="sexCode" value="2" style="ime-mode:disabled"/>女性
                            </td>
                        </tr>

                        <tr>
                            <th colspan="3">生年月日<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(16)"/>" colspan="2">
                                <html:select name="MskTorokuJoho" property="birthdayEra">
                                    <html:options collection="BirthEraList" property="value" labelProperty="label" />
                                </html:select>
                                <html:text name="MskTorokuJoho" property="birthdayYy" size="10" maxlength="2" style="ime-mode:disabled" /> 年
                                <html:text name="MskTorokuJoho" property="birthdayMm" size="10" maxlength="2" style="ime-mode:disabled" /> 月
                                <html:text name="MskTorokuJoho" property="birthdayDd" size="10" maxlength="2" style="ime-mode:disabled" /> 日
                            </td>
                        </tr>

                        <tr>
                            <th rowspan="7" >
                                現住所<br/>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <th colspan="2">郵便番号</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(18)"/>" colspan="2">
                                <table cellspacing="0" cellpadding="1" width="99%">
                                    <tr>
                                        <td class="TDmiddle">
                                            <html:text name="MskTorokuJoho" property="yubinNo1" size="10" maxlength="3" style="ime-mode:disabled"/>　-　
                                            <html:text name="MskTorokuJoho" property="yubinNo2" size="10" maxlength="4" style="ime-mode:disabled" />　<br/>
                                        例）101-0047（半角数字）
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            郵便番号がわからない場合は
                                            <a href="http://www.post.japanpost.jp/zipcode/" target="_blank">[日本郵政-郵便番号検索]</a>
                                            をご利用ください。<br>
                                            郵便番号を入力して「住所自動入力」ボタンを押すと、自動的に入力されます。<br>
                                            <INPUT type="button" property="jushoSearch" value ="住所自動入力"onblur="offenter()" onfocus="onenter()"  onclick="linkOpenWin('../../YubinSelAct.do');saveScrollTop()"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="6">住所</th>
                            <th class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                都道府県
                            </th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                <html:select name="MskTorokuJoho" property="todofuken">
                                    <html:options collection="TodofukenList" property="value" labelProperty="label" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <th class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >市区町村・郡</th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                <html:text name="MskTorokuJoho" property="jusho1" size="60" maxlength="20" style="ime-mode:active"/></td>
                        </tr>
                        <tr>
                            <th class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >町名</th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                <html:text name="MskTorokuJoho" property="jusho2" size="60" maxlength="20" style="ime-mode:active"/></td>
                        </tr>
                        <tr>
                            <th class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >丁目・番地</th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                <html:text name="MskTorokuJoho" property="jusho3" size="60" maxlength="20" style="ime-mode:active"/></td>
                        </tr>
                        <tr>
                            <th rowspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >ビル・建物名、部屋番号　など</th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" >
                                <html:text name="MskTorokuJoho" property="jusho4" size="60" maxlength="25" style="ime-mode:active"/><br>
                                ※表札とご本人名字が異なる場合は◯◯様方と入力してください。
                            </td>
                        </tr>
                        <tr>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(19)"/>" colspan="2">
                            例）東京都　千代田区　内神田　２－５－３　児谷ビル<br>
                                「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。<br>
                            </td>
                        </tr>

                        <tr>
                            <th rowspan="2" colspan="2">
                                電話番号<br>
                                <font class="txtAttention">（いずれかの入力必須）</font>
                            </th>
                            <th>ＴＥＬ</th>
                            <td colspan="2" class="<bean:write name="MskTorokuJoho" property="validateCss(21)"/>">
                                <table class="tableNoFrame" width="100%">
                                    <tr>
                                        <td style="border: none">
                                            <html:text name="MskTorokuJoho" property="telNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                                            例）03-1234-5678<br/>
                                        </td>
                                        <td style="border: none">
                                           内線&nbsp;<html:text name="MskTorokuJoho" property="extNo" size="14" maxlength="10" style="ime-mode:disabled"/><br/>
                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;例)1234
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            ※ご連絡時に内線番号が必要な場合は、内線番号をご入力ください。
                                        </td>    
                                    </tr>    
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <th>携帯電話</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(21)"/>" colspan="2">
                                <html:text name="MskTorokuJoho" property="cellphoneNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                                例）080-1234-5678<br/>
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="2" colspan="3">
                                メールアドレス<br>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(7)"/>" colspan="2">
                                <html:text name="MskTorokuJoho" property="mailAddress" size="80" maxlength="50" style="ime-mode:disabled" /><br>
                                例）xxx@xxx.xxx
                            </td>
                        </tr>
                        <tr>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(8)"/>" colspan="2">
                                確認のため再度入力してください。<br>
                                <div onpaste="return false">
                                    <html:text name="MskTorokuJoho" property="mailAddressKakunin" size="80" maxlength="50" style="ime-mode:disabled" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="3">医療保険事務の受講経験<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(17)"/>" colspan="2">
                                <html:radio name="MskTorokuJoho" property="jukoKeiken" value="1" style="ime-mode:disabled"/>あり&nbsp;&nbsp;
                                <html:radio name="MskTorokuJoho" property="jukoKeiken" value="2" style="ime-mode:disabled"/>なし
                            </td>
                        </tr>

                        <tr>
                            <th colspan="3">医療保険事務の実務経験<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(20)"/>" colspan="2">
                                <html:radio name="MskTorokuJoho" property="jitsumuKeiken" value="1" style="ime-mode:disabled"/>あり&nbsp;&nbsp;
                                <html:radio name="MskTorokuJoho" property="jitsumuKeiken" value="2" style="ime-mode:disabled"/>なし
                            </td>
                        </tr>

                        <tr>
                            <th rowspan="2" colspan="2">ご本人様確認用の質問・回答<font class="txtAttention"><br/>（必須）</font></th>
                            <th>質問</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(23)"/>" colspan="2">
                                <html:select name="MskTorokuJoho" property="passwdQuestionCode1">
                                    <html:options collection="PasswdQuestionList" property="value" labelProperty="label" />
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <th>回答</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(24)"/>" colspan="2">
                                <html:text name="MskTorokuJoho" property="passwdAnswer1" size="45" maxlength="20" style="ime-mode:active"/><Br>
                                例）カレーライス<br>
                                「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。
                            </td>
                        </tr>


                    </table>
                    <br>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tbody>
                            <tr>
                                <td class="TDcenter">
                                    <html:submit property="back" value="戻　る" style="width:100px" onblur="offenter()" onfocus="onenter()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table  class="table1" cellspacing="0" cellpadding="0">
                        <tbody>
                            <tr>
                                <td class="TDright">
                                    <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="backMenuConfirmFirst()"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </html:form>
            </div>
        </div>
    </body>
</html:html>
