<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_MskInp"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script language="JavaScript" type="text/JavaScript">
            $(document).ready(function(){ 
                $('#showcharacters').click(function() { //チェックボックスがクリックされたとき
                    if ($(this).attr('checked')) { //もし、チェック状態であれば
                        $('#dantaiMoshikomiPasswd').replaceWith(
                         '<input type="text" name="dantaiMoshikomiPasswd" id="dantaiMoshikomiPasswd" size="30"  style="ime-mode:disabled" value="' + $('#dantaiMoshikomiPasswd').attr('value') + '" />');
                         //パスワードを表示
                    } else { //チェックがなければ
                        $('#dantaiMoshikomiPasswd').replaceWith(
                         '<input type="password" name="dantaiMoshikomiPasswd" id="dantaiMoshikomiPasswd" size="25" value="' + $('#dantaiMoshikomiPasswd').attr('value') + '" />');
                         //パスワードを隠す
                        }
                });
             });
    </script>
    </head>
    <body onload="setScrollTop();">
        <div id="container">
            <html:form action="/dantai/Msk/DntMskInpAct.do" styleId="frm" onsubmit="disableSubmit(this)">
                <html:hidden property="scrollTop" />
                <html:hidden property="yubinFlg" />

                <div id="title_header"></div>
                <table class="header" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <br/>
                            <img src="../../image/nv_re_dnt3.gif" alt="団体申込出願フロー">
                        </td>
                    </tr>
                </table>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">３．<bean:message key="title.Dantai_MskInp"/></td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="headline" colspan="2"><b>入力にあたってのご注意</b></td>
                    </tr>
                </table>

                <table class="table1">
                    <tr>
                        <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                        <td>
                                    　日本語入力の切り替えは自動的に行われますので、そのまま入力してください。 「半角英数字」「全角」など指定された文字以外を入力した場合は、エラーが表示され訂正を求められます。
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>２</b></td>
                        <td>
                                    　セキュリティ保護のため、登録作業は３０分以内に終えてください。３０分を超過した場合は、お手数ですがもう一度はじめから登録を行ってください。
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
                <table class="tableMain"  cellpadding="5" cellspacing="0">
                    <tr>
                        <td width="10%"></td>
                        <td width="8%"></td>
                        <td width="12%"></td>
                        <td width="35%"></td>
                        <td width="35%"></td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            団体等の名称<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(1)"/>" colspan="2">
                            <html:text name="DntInfo" property="dantaiName" size="50" maxlength="20" style="ime-mode:active"/>&nbsp;<br>
                            例）○○専門学校<br/>
                            使用する文字は「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」「英数字」を全角で入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            団体等の名称フリガナ<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(2)"/>" colspan="2">
                            <html:text name="DntInfo" property="dantaiNameKana" size="70" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）○○センモンガッコウ（全角カタカナ）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="7" >
                            所在地<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <th colspan="2">郵便番号</th>
                        <td class="<bean:write name="DntInfo" property="validateCss(3)"/>" colspan="2">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="TDmiddle">
                                        <html:text name="DntInfo" property="yubinNo1" size="10" maxlength="3" style="ime-mode:disabled"/>　-　
                                        <html:text name="DntInfo" property="yubinNo2" size="10" maxlength="4" style="ime-mode:disabled" />　<br/>
                                        例）101-0047（半角数字）
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        郵便番号がわからない場合は
                                        <a href="http://www.post.japanpost.jp/zipcode/" target="_blank">[日本郵政-郵便番号検索]</a>
                                        をご利用ください。<br>
                                        郵便番号を入力して「住所自動入力」ボタンを押すと、自動的に入力されます。<br>
                                        <input type="button" name="jushoSearch" value ="住所自動入力" onblur="offenter()" onfocus="onenter()"  onclick="linkOpenWin('../../YubinSelAct.do');saveScrollTop()"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="6">住所</th>
                        <th class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            都道府県
                        </th>
                        <td colspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            <html:select name="DntInfo" property="todofuken">
                                <html:options collection="TodofukenList" property="value" labelProperty="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfo" property="validateCss(4)"/>" >市区町村・郡</th>
                        <td colspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            <html:text name="DntInfo" property="jusho1" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfo" property="validateCss(4)"/>" >町名</th>
                        <td colspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            <html:text name="DntInfo" property="jusho2" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfo" property="validateCss(4)"/>" >丁目・番地</th>
                        <td colspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            <html:text name="DntInfo" property="jusho3" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th rowspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >ビル・建物名、部屋番号　など</th>
                        <td colspan="2" class="<bean:write name="DntInfo" property="validateCss(4)"/>" >
                            <html:text name="DntInfo" property="jusho4" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfo" property="validateCss(4)"/>" colspan="2">
                            例）東京都　千代田区　内神田　２－５－３　児谷ビル<br>
                            「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。<br>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            担当者名<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(5)"/>" colspan="2">
                            姓&nbsp;<html:text name="DntInfo" property="dantaiJimuTantoshaShimeiSei" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;
                            名&nbsp;<html:text name="DntInfo" property="dantaiJimuTantoshaShimeiMei" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）姓：試験　名：太郎<br/>
                            使用する文字は「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」を全角で入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            担当者名フリガナ<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(6)"/>" colspan="2">
                            姓&nbsp;<html:text name="DntInfo" property="dantaiJimuTantoshaShimeiSeiKana" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;
                            名&nbsp;<html:text name="DntInfo" property="dantaiJimuTantoshaShimeiMeiKana" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）姓：シケン　名：タロウ（全角カタカナ）
                        </td>
                    </tr>

                    <tr>
                        <th colspan="3">
                            電話番号<br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(7)"/>" colspan="2">
                            <table class="tableNoFrame">
                                <tr>
                                    <td style="border: none">
                                        <html:text name="DntInfo" property="dantaiJimuTantoshaTelNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                                        例）03-1234-5678<br/>
                                    </td>
                                    <td style="border: none">
                                        内線&nbsp;<html:text name="DntInfo" property="extNo" size="14" maxlength="10" style="ime-mode:disabled"/><br/>
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
                        <th colspan="3">
                            ＦＡＸ番号
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(8)"/>" colspan="2">
                            <html:text name="DntInfo" property="dantaiJimuTantoshaFaxNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                            例）03-1234-5678<br/>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2" colspan="3">
                            メールアドレス<br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(9)"/>" colspan="2">
                            <html:text name="DntInfo" property="dantaiJimuTantoshaMailAddress" size="80" maxlength="50" style="ime-mode:disabled"/><br/>
                            例）xxx@xxx.xxx
                        </td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfo" property="validateCss(10)"/>" colspan="2">
                            確認のため再度入力してください。<br>
                            <div onpaste="return false">
                                <html:text name="DntInfo" property="dantaiJimuTantoshaMailAddressKakunin" size="80" maxlength="50" style="ime-mode:disabled"/><br/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <th rowspan="2" colspan="3">
                            受験申込者数<br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(11)"/>" colspan="2">
                            医科&nbsp;：&nbsp;<html:text name="DntInfo" property="moshikomishaSuIka" size="10" maxlength="4" style="ime-mode:disabled"/>&nbsp;名<br>
                        </td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfo" property="validateCss(12)"/>" colspan="2">
                            歯科&nbsp;：&nbsp;<html:text name="DntInfo" property="moshikomishaSuShika" size="10" maxlength="4" style="ime-mode:disabled"/>&nbsp;名
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">発送物の送付先<font class="txtAttention"><br/>（必須）</font></th>
                        <td class="<bean:write name="DntInfo" property="validateCss(13)"/>" colspan="2">
                            <html:radio name="DntInfo" property="hassosakiKbn" value="1" style="ime-mode:disabled"/>団体所在地<br>
                            <html:radio name="DntInfo" property="hassosakiKbn" value="0" style="ime-mode:disabled"/>各受験申込者の住所<br>
                            <font size="-2"><br></font>
                            ※発送物は、受験票や合否通知等になります。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法<font class="txtAttention"><br/>（必須）</font></th>
                        <td class="<bean:write name="DntInfo" property="validateCss(21)"/>" colspan="2">
                            <html:radio name="DntInfo" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_POST%>" style="ime-mode:disabled"/><%=MiwConstants.POST_NAME%><br>
                            <html:radio name="DntInfo" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_BANK%>" style="ime-mode:disabled"/><%=MiwConstants.BANK_NAME%>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="3" colspan="3">
                            団体申込者登録用パスワード<br>
                            <font size="-2" class="blue">（団体申込者が使用するパスワードです。）</font><br>
                            <font size="-1">半角英数字で８字。</font><br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="basic" colspan="2">
                            パスワードは必ず控えておいてください（照会には応じられません）。
                        </td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfo" property="validateCss(18)"/>" colspan="2">
                            <html:password name="DntInfo" styleId="dantaiMoshikomiPasswd" property="dantaiMoshikomiPasswd" maxlength="8" size="25"/>
                            <input id="showcharacters" name="showcharacters" type="checkbox" /> 確認する<br>
                            例）AaBbCc12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;! # $ % 等は使用できません。
                        </td>
                    </tr>

                    <tr>
                        <td class="<bean:write name="DntInfo" property="validateCss(18)"/>" colspan="2">
                            確認のため再度入力してください。<br>
                            <div onpaste="return false">
                                <html:password name="DntInfo" property="dantaiMoshikomiPasswdKakunin" maxlength="8" size="25"/><br>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2" colspan="2">パスワード確認用の質問・回答<font class="txtAttention"><br/>（必須）</font></th>
                        <th>質問</th>
                        <td class="<bean:write name="DntInfo" property="validateCss(16)"/>" colspan="2">
                            <html:select name="DntInfo" property="passwdQuestionCode1">
                                <html:options collection="PswdConfList" property="value" labelProperty="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th>回答</th>
                        <td class="<bean:write name="DntInfo" property="validateCss(17)"/>" colspan="2">
                            <html:text name="DntInfo" property="passwdAnswer1" maxlength="20" size="45" style="ime-mode:active"/><Br>
                            例）カレーライス<br>
                            「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">通信欄<br>
                            <font size="-1">全角で２００字以内。</font><br>
                        </th>
                        <td class="<bean:write name="DntInfo" property="validateCss(20)"/>" colspan="2">
                            <html:textarea name="DntInfo" property="moshikomiMemo" cols="50" rows="4" style="ime-mode:active" onblur="offenter()" onfocus="onenter()"/>
                        </td>
                    </tr>
                </table>
                <br>
                <br>
                <table class="table1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="TDcenter">
                            <b><font size="+1">
                                入力した項目をもう一度確認してください。<br/>
                                入力内容に間違いがなければ、「上記内容で団体申込出願する」をクリックしてください。

                                </font></b>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="back" value="戻る" onblur="offenter()" onfocus="onenter()" style="width:100px" onclick="setHiddenValue(this);"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <html:submit property="submit" value="上記内容で団体申込出願する" onblur="offenter()" onfocus="onenter()" style="width:200px" onclick="setHiddenValue(this);"/>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>