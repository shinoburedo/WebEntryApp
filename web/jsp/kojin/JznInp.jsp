<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Kojin_JznInp" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <link href="../../css/jzn.css" type="text/css" rel="stylesheet">
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="../../js/jzn.js"></script>
        <script type="text/javascript" src="../../js/common.js" ></script>
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script language="JavaScript" type="text/JavaScript">
            $(document).ready(function(){ 
                $('#showcharacters').click(function() { //チェックボックスがクリックされたとき
                    if ($(this).attr('checked')) { //もし、チェック状態であれば
                        $('#passwd').replaceWith(
                         '<input type="text" name="passwd" id="passwd" size="30"  style="ime-mode:disabled" value="' + $('#passwd').attr('value') + '" />');
                         //パスワードを表示
                    } else { //チェックがなければ
                        $('#passwd').replaceWith(
                         '<input type="password" name="passwd" id="passwd" size="25" value="' + $('#passwd').attr('value') + '" />');
                         //パスワードを隠す
                        }
                });
             });
    </script>
    </head>
    <body>
        <div id="container">
            <div id="title_header"></div>
            <div align="center">
                <html:form action="/kojin/Jzn/JznInpAct">
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <img src="../../image/nv_jzn2.gif" alt="ＩＤ取得フロー">
                            </td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">２．ＩＤ取得　情報入力</td>
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
                            <td width="15%"></td>
                            <td width="20%"></td>
                            <td width="65%"></td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                氏名<br/>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(1)"/>">
                                姓&nbsp;<html:text name="JznTorokuJoho" property="shimeiSei" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;
                                名&nbsp;<html:text name="JznTorokuJoho" property="shimeiMei" size="30" maxlength="50" style="ime-mode:active" />&nbsp;<br/>
                                例）姓：試験　名：太郎<br/>
                                使用する文字は「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」を全角で入力してください。
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                フリガナ<br/>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(2)"/>">
                                姓&nbsp;<html:text name="JznTorokuJoho" property="shimeiSeiKana" size="30" maxlength="50" style="ime-mode:active" />&nbsp;
                                名&nbsp;<html:text name="JznTorokuJoho" property="shimeiMeiKana" size="30" maxlength="50" style="ime-mode:active" />&nbsp;<br/>
                                例）姓：シケン　名：タロウ（全角カタカナ）
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">性　別<font class="txtAttention"><br/>（必須）</font></th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(4)"/>">
                                <html:radio name="JznTorokuJoho" property="sexCode" value="1" style="ime-mode:disabled"/>男性&nbsp;&nbsp;
                                <html:radio name="JznTorokuJoho" property="sexCode" value="2" style="ime-mode:disabled"/>女性
                            </td>
                        </tr>

                        <tr>
                            <th colspan="2">
                                生年月日<br>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(5)"/>">
                                <html:select name="JznTorokuJoho" property="birthdayEra">
                                    <html:options collection="BirthEraList" property="value" labelProperty="label" />
                                </html:select>
                                <html:text name="JznTorokuJoho" property="birthdayYear" size="8" maxlength="2" style="ime-mode:disabled"/>　年　
                                <html:text name="JznTorokuJoho" property="birthdayMonth" size="8" maxlength="2" style="ime-mode:disabled"/>　月　
                                <html:text name="JznTorokuJoho" property="birthdayDay" size="8" maxlength="2" style="ime-mode:disabled"/>  日<br>
                                例）平成 4年 7月14日
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">
                                電話番号<br>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(6)"/>">
                                <table class="tableNoFrame" width="100%">
                                    <tr>
                                        <td style = "border: none">
                                            <html:text name="JznTorokuJoho" property="telNo" size="30" maxlength="20" style="ime-mode:disabled"/><br>
                                            例）03-1234-5678
                                        </td>
                                        <td style = "border: none">
                                            内線&nbsp;<html:text name="JznTorokuJoho" property="extNo" size="14" maxlength="10" style="ime-mode:disabled"/><br/>
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
                            <th rowspan="2" colspan="2">
                                メールアドレス<br>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(7)"/>">
                                <html:text name="JznTorokuJoho" property="mailAddress" size="80" maxlength="50" style="ime-mode:disabled" /><br>
                                例）xxx@xxx.xxx
                            </td>
                        </tr>
                        <tr>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(8)"/>">
                                確認のため再度入力してください。<br>
                                <div onpaste="return false">
                                    <html:text name="JznTorokuJoho" property="mailAddressKakunin" size="80" maxlength="50" style="ime-mode:disabled" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th rowspan="3" colspan="2">
                                パスワード<br>
                                <font size="-1">半角英数字で８字。</font><br>
                                <font class="txtAttention">（必須）</font>
                            </th>
                            <td class="basic">
                                <font class="txtAttention">※パスワードは必ず控えておいてください（照会には応じられません）。</font>
                            </td>
                        </tr>
                        <tr>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(9)"/>">
                                <html:password name="JznTorokuJoho" styleId="passwd" property="passwd" size="25" maxlength="8"/>
                                <input id="showcharacters" name="showcharacters" type="checkbox" /> 確認する<br>
                                例）AaBbCc12&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;! # $ % 等は使用できません。<br>
                            </td>
                        </tr>

                        <tr>
                            <td class="<bean:write name="JznTorokuJoho" property="validateCss(10)"/>">
                                確認のため再度入力してください。<br>
                                <div onpaste="return false">
                                    <html:password name="JznTorokuJoho" property="passwdKakunin" size="25" maxlength="8"/><br>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="TDcenter">
                                <b><font size="+1">
                                    入力した項目をもう一度確認してください。<br/>
                                    入力内容に間違いがなければ、<br/>
                                    「上記内容でＩＤ取得する」をクリックしてください。
                                    </font></b>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="戻る" style="width:100px" onblur="offenter()" onfocus="onenter()" />
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="上記内容でＩＤ取得する" style="width:200px"onblur="offenter()" onfocus="onenter()"/>
                            </td>
                        </tr>
                    </table>
                    <br>
                </html:form>
            </div>
        </div>
    </body>
</html:html>