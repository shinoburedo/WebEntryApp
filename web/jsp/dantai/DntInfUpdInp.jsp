<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfUpdInp"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/myp.css" type="text/css" rel="stylesheet">
    </head>
    <body onload="setScrollTop();">
        <div id="container">
            <html:form action="/dantai/Inf/DntInfUpdInpAct.do" onsubmit="disableSubmit(this)">
                <html:hidden property="scrollTop" />
                <html:hidden property="yubinFlg" />
                <div id="title_header"></div>
                <table class="tableNoFrame" cellpadding="0">
                    <tr>
                        <td class="TDright TDmiddle">
                            <b><bean:write name="DntInfoInp" property="dantaiName" />代表者&nbsp;様のページ&nbsp;&nbsp;</b>
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
                        <td class="right"><bean:message key="title.Dantai_InfUpdInp"/></td>
                    </tr>
                </table>
                <br>
                <br>
                <table class="tableNoFrame">
                    <logic:messagesPresent>
                        <tr>
                            <td><html:errors/><td>
                        </tr>
                    </logic:messagesPresent>
                    <logic:messagesPresent message="true">
                        <tr>
                            <td>
                                <hr>
                                <font class="txtAttention">
                                <html:messages id="msg" message="true" property="noupd">
                                    ！<bean:write name="msg" />
                                </html:messages >
                                </font>
                                <hr>
                            <td>
                        </tr>
                    </logic:messagesPresent>
                </table>
                <br>
                <table class="tableExBox" cellpadding="4">
                    <tr>
                        <td class="headline" colspan="2">注意事項</td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter" width="3%"><b>１</b></td>
                        <td width="97%">ブラウザの「戻る」「進む」「更新」(「再読み込み」)ボタンは使用しないでください。</td>
                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>２</b></td>
                        <td>
                            （<font class="txtAttention">必須</font>）印が付いている項目は、必ず入力してください。
                        </td>

                    </tr>
                    <tr>
                        <td class="TDtop TDcenter"><b>３</b></td>
                        <td>
                            入力できない項目は、変更できません。
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame" cellpadding="4" cellspacing="0">
                    <tr>
                        <td>
                            上記の注意事項を確認のうえ、以下の登録事項を入力し、「次へ」ボタンを押してください。<br>
                            日本語入力の切替（ＯＮ・ＯＦＦ）は自動的に行われますので、そのまま入力してください。
                        </td>
                    </tr>
                </table>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>申込手続状況</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="30%"></td>
                        <td width="70%"></td>
                    </tr>
                    <tr>
                        <th>団体コード</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="dantaiCode" />
                        </td>
                    </tr>
                    <tr>
                        <th>団体申込者登録用ID</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="dantaiMoshikomiUketsukeNo" />
                        </td>
                    </tr>
                    <tr>
                        <th>登録日</th>
                        <td class="basic">
                            <bean:write name="DntInfo" property="torokuDateDispSlash" />
                        </td>
                    </tr>
                    <tr>
                        <th>受付日</th>
                        <td class="basic">
                            <logic:empty name="DntInfo" property="kariUketsukeBi">
                                -
                            </logic:empty>
                            <logic:notEmpty name="DntInfo" property="kariUketsukeBi">
                                <bean:write name="DntInfo" property="kariUketsukeBiDispSlash"/>
                            </logic:notEmpty>
                        </td>
                    </tr>
                    <tr>
                        <th>申込完了日</th>
                        <td class="basic">
                            <logic:empty name="DntInfo" property="moshikomiFinishBi">
                                -
                            </logic:empty>
                            <logic:notEmpty name="DntInfo" property="moshikomiFinishBi">
                                <bean:write name="DntInfo" property="moshikomiFinishBiDispSlash"/>
                            </logic:notEmpty>
                        </td>
                    </tr>
                    <tr>
                        <th>決済状況</th>
                        <td class="basic">
                            <bean:write name="DntInfoInp" property="kessaiJokyoKbnDisp"/>
                        </td>
                    </tr>
                    <tr>
                        <th>受付状況</th>
                        <td class="basic">
                            <bean:write name="DntInfoInp" property="tetsudukiJokyoKbnDisp"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>団体情報</b></td>
                    </tr>
                </table>
                <table class="tableMain" cellpadding="5" cellspacing="0">
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
                        <td class="<bean:write name="DntInfoInp" property="validateCss(1)"/>" colspan="2">
                            <html:text name="DntInfoInp" property="dantaiName" size="50" maxlength="20" style="ime-mode:active"/>&nbsp;<br>
                            例）○○専門学校<br/>
                            使用する文字は「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」「英数字」を全角で入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            団体等の名称フリガナ<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(2)"/>" colspan="2">
                            <html:text name="DntInfoInp" property="dantaiNameKana" size="70" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）○○センモンガッコウ（全角カタカナ）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="7" >
                            所在地<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <th colspan="2">郵便番号</th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(3)"/>" colspan="2">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="TDmiddle">
                                        <html:text name="DntInfoInp" property="yubinNo1" size="10" maxlength="3" style="ime-mode:disabled"/>　-　
                                        <html:text name="DntInfoInp" property="yubinNo2" size="10" maxlength="4" style="ime-mode:disabled" />　<br/>
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
                        <th class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            都道府県
                        </th>
                        <td colspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            <html:select name="DntInfoInp" property="todofuken">
                                <html:options collection="TodofukenList" property="value" labelProperty="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >市区町村・郡</th>
                        <td colspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            <html:text name="DntInfoInp" property="jusho1" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >町名</th>
                        <td colspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            <html:text name="DntInfoInp" property="jusho2" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >丁目・番地</th>
                        <td colspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            <html:text name="DntInfoInp" property="jusho3" size="60" maxlength="20" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <th rowspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >ビル・建物名、部屋番号　など</th>
                        <td colspan="2" class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" >
                            <html:text name="DntInfoInp" property="jusho4" size="60" maxlength="25" style="ime-mode:active"/></td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(4)"/>" colspan="2">
                            例）東京都　千代田区　内神田　２－５－３　児谷ビル<br>
                            「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。<br>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            担当者名<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(5)"/>" colspan="2">
                            姓&nbsp;<html:text name="DntInfoInp" property="dantaiJimuTantoshaShimeiSei" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;
                            名&nbsp;<html:text name="DntInfoInp" property="dantaiJimuTantoshaShimeiMei" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）姓：試験　名：太郎<br/>
                            使用する文字は「JIS第一水準・第二水準」とし、「漢字」「ひらがな」「カタカナ」を全角で入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">
                            担当者名フリガナ<br/>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(6)"/>" colspan="2">
                            姓&nbsp;<html:text name="DntInfoInp" property="dantaiJimuTantoshaShimeiSeiKana" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;
                            名&nbsp;<html:text name="DntInfoInp" property="dantaiJimuTantoshaShimeiMeiKana" size="30" maxlength="50" style="ime-mode:active"/>&nbsp;<br/>
                            例）姓：シケン　名：タロウ（全角カタカナ）
                        </td>
                    </tr>

                    <tr>
                        <th colspan="3">
                            電話番号<br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(7)"/>" colspan="2">
                            <table>
                                <tr>
                                    <td style="border: none">
                                        <html:text name="DntInfoInp" property="dantaiJimuTantoshaTelNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                                        （半角数字、記号） <br/>
                                    </td>
                                    <td style="border: none">
                                        内線&nbsp;<html:text name="DntInfoInp" property="extNo" size="14" maxlength="10" style="ime-mode:disabled"/><br/>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;（半角数字、記号） 
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
                        <td class="<bean:write name="DntInfoInp" property="validateCss(8)"/>" colspan="2">
                            <html:text name="DntInfoInp" property="dantaiJimuTantoshaFaxNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                            例）03-1234-5678<br/>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2" colspan="3">
                            メールアドレス<br>
                            <font class="txtAttention">（必須）</font>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(9)"/>" colspan="2">
                            <html:text name="DntInfoInp" property="dantaiJimuTantoshaMailAddress" size="80" maxlength="50" style="ime-mode:disabled"/><br/>
                            例）xxx@xxx.xxx
                        </td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(10)"/>" colspan="2">
                            確認のため再度入力してください。<br>
                            <div onpaste="return false">
                                <html:text name="DntInfoInp" property="dantaiJimuTantoshaMailAddressKakunin" size="80" maxlength="50" style="ime-mode:disabled"/><br/>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <th rowspan="2" colspan="3">
                            受験申込者数<br>
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="true">
                                <font class="txtAttention">（必須）</font>
                            </logic:equal>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(11)"/>" colspan="2">
                            医科&nbsp;：&nbsp;
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="true">
                                <html:text name="DntInfoInp" property="moshikomishaSuIka" size="10" maxlength="2" style="ime-mode:disabled"/>
                            </logic:equal>
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="false">
                                <bean:write name="DntInfo" property="moshikomishaSuIka"/>
                            </logic:equal>
                            &nbsp;名<br>
                        </td>
                    </tr>
                    <tr>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(12)"/>" colspan="2">
                            歯科&nbsp;：&nbsp;
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="true">
                                <html:text name="DntInfoInp" property="moshikomishaSuShika" size="10" maxlength="2" style="ime-mode:disabled"/>
                            </logic:equal>
                            <logic:equal name="DntInfo" property="isTetsudukiJokyoShoninMae" value="false">
                                <bean:write name="DntInfo" property="moshikomishaSuShika"/>
                            </logic:equal>
                            &nbsp;名
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">発送物の送付先<font class="txtAttention"><br/>（必須）</font></th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(13)"/>" colspan="2">
                            <html:radio name="DntInfoInp" property="hassosakiKbn" value="1" style="ime-mode:disabled"/>団体所在地<br>
                            <html:radio name="DntInfoInp" property="hassosakiKbn" value="0" style="ime-mode:disabled"/>各受験申込者の住所<br>
                            <font size="-2"><br></font>
                            ※発送物は、受験票や合否通知等になります。
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3"><%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法
                            <logic:equal name="DntInfo" property="isKessaiJokyoMi" value="true">
                                <font class="txtAttention"><br/>（必須）</font>
                            </logic:equal>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(21)"/>" colspan="2">
                            <logic:equal name="DntInfo" property="isKessaiJokyoMi" value="true">
                                <html:radio name="DntInfoInp" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_POST%>" style="ime-mode:disabled"/><%=MiwConstants.POST_NAME%><br>
                                <html:radio name="DntInfoInp" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_BANK%>" style="ime-mode:disabled"/><%=MiwConstants.BANK_NAME%>
                            </logic:equal>
                            <logic:equal name="DntInfo" property="isKessaiJokyoMi" value="false">
                                <bean:write name="DntInfo" property="kessaiHohoKbnDisp"/>
                            </logic:equal>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">通信欄<br>
                            <font size="-1">全角で２００字以内。</font><br>
                        </th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(20)"/>" colspan="2">
                            <html:textarea name="DntInfoInp" property="moshikomiMemo" cols="50" rows="4" style="ime-mode:active" onblur="offenter()" onfocus="onenter()"/>
                        </td>
                    </tr>                    
                </table>
                <br>
                <br>
                <table class="tableNoFrame"  style="width: 640px;">
                    <tr>
                        <td><b>パスワード確認用の質問・回答</b></td>
                    </tr>
                    <tr>
                        <td>パスワード確認用の質問とその答えはパスワードを忘れたときに必要になります。</td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <th width="197">質問<font class="txtAttention"><br/>（必須）</font></th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(16)"/>" colspan="2">
                            <html:select name="DntInfoInp" property="passwdQuestionCode1">
                                <html:options collection="PswdConfList" property="value" labelProperty="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th>回答<font class="txtAttention"><br/>（必須）</font></th>
                        <td class="<bean:write name="DntInfoInp" property="validateCss(17)"/>" colspan="2">
                            <html:text name="DntInfoInp" property="passwdAnswer1" maxlength="20" size="45" style="ime-mode:active"/><Br>
                            例）カレーライス<br>
                            「漢字」「ひらがな」「カタカナ」「英数字」は全角で入力してください。
                        </td>
                    </tr>
                </table>

                <br>
                <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <font class="txtAttention">※</font>「次へ」ボタンを押しても変更は確定されません。
                        </td>
                    </tr>
                    <tr>
                        <td height="15">
                        </td>
                    </tr>
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="next" value="次へ" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" styleClass="buttonNext" style="width:100px"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDleft">
                            <html:submit property="back" value="申込内容確認に戻る" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()"/>
                        </td>
                        <td class="TDright">
                            <a href="#">▲ページ先頭へ</a>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>