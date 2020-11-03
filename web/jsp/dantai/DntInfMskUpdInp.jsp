<%@ page contentType="text/html; charset=WINDOWS-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMskUpd" /></title>
        <meta http-equiv="Content-Script-Type" content="text/JavaScript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/JavaScript" src="../../js/common.js"></script>
        <script type="text/JavaScript" src="../../js/msk.js"></script>
        <script type="text/JavaScript" src="../../js/myp.js"></script>
        <link type="text/css" rel="stylesheet" href="../../css/myp.css" >
        <link type="text/css" rel="stylesheet" href="../../css/common.css">
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="../../js/jquery.updnWatermark.js"></script>
        <script type="text/javascript" src="../../js/jquery.exresize.0.1.0.js"></script>
    </head>
    <body>
        <div id="page_top"></div>
        <div id="container">
            <html:form action="/dantai/Inf/DntInfMskUpdInpAct" enctype="multipart/form-data" onsubmit="disableSubmit(this)">

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
                        <td class="right"><bean:message key="title.Dantai_InfMskUpd"/></td>
                    </tr>
                </table>
                <br>
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
                            （<font class="txtAttention">*</font>）印が付いている項目は、必ず入力してください。
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
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>受験情報</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="30%"></td>
                        <td width="70%"></td>
                    </tr>
                    <tr>
                        <th>受験科目</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(1)"/>">
                            <logic:iterate id="item" name="EventCodeList">
                                <html:radio name="MoshikomiJohoInp" property="eventCode" idName="item" value="value"/>
                                <bean:write name="item" property="label"/>
                            </logic:iterate>   
                        </td>
                    </tr>
                    <tr>
                        <th>受験希望地<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(2)"/>">
                            <html:select name="MoshikomiJohoInp" property="shikenchiCode" onchange="chikuChange()">
                                <html:options collection="ShikenchiList" property="value" labelProperty="label" />
                            </html:select>
                            <br><font size="-1"><a href="../../pdf/KaijoList.pdf" target="_blank">会場一覧はこちら</a></font>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame"> 
                    <tr>
                        <td><b>基本情報</b></td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="7%"></td>
                        <td width="8%"></td>
                        <td width="15%"></td>
                        <td width="70%"></td>
                    </tr>
                    <tr>
                        <th colspan="3">ＩＤ</th>
                        <td class="basic">
                            <bean:write name="MoshikomiJoho" property="userId"/>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">氏名<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(5)"/>">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="middle">
                                        姓&nbsp;<html:text name="MoshikomiJohoInp" property="shimeiSei" size="22" maxlength="50" styleClass="ime_on" styleId="seiKj" title="(例) 診療報酬請求事務能力認定試験"/>　
                                        名&nbsp;<html:text name="MoshikomiJohoInp" property="shimeiMei" size="22" maxlength="50" styleClass="ime_on" styleId="meiKj" title="(例) 太郎"/>
                                        （全角）
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">フリガナ<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(6)"/>">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td>
                                        <html:messages id="msg" message="false" property="jushoChouson">
                                            <font class="txtAttention">
                                            <b>！</b><bean:write name="msg" filter="false" ignore="false" /><br>
                                            </font>
                                        </html:messages>
                                        姓&nbsp;<html:text name="MoshikomiJohoInp" property="shimeiSeiKana" size="22" maxlength="50" styleClass="ime_on" styleId="seiKn" title="(例) シケン"/>　
                                        名&nbsp;<html:text name="MoshikomiJohoInp" property="shimeiMeiKana" size="22" maxlength="50" styleClass="ime_on" styleId="meiiKn" title="(例) タロウ"/>
                                        （全角カタカナ）
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">性　別<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(8)"/>">
                            <html:radio name="MoshikomiJohoInp" property="sexCode" value="1" style="ime-mode:disabled"/>男性&nbsp;&nbsp;
                            <html:radio name="MoshikomiJohoInp" property="sexCode" value="2" style="ime-mode:disabled"/>女性
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">生年月日<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(9)"/>">
                            <html:select name="MoshikomiJohoInp" property="birthdayEra">
                                <html:options collection="BirthEraList" property="value" labelProperty="label" />
                            </html:select>
                            <html:text name="MoshikomiJohoInp" property="birthdayYear" size="10" maxlength="2" styleClass="ime_off" styleId="year" title="(例) 02"/> 年
                            <html:text name="MoshikomiJohoInp" property="birthdayMonth" size="10" maxlength="2" styleClass="ime_off" styleId="month" title="(例) 07"/>月
                            <html:text name="MoshikomiJohoInp" property="birthdayDay" size="10" maxlength="2" styleClass="ime_off" styleId="day" title="(例) 14"/>日
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="6">現住所</th>
                        <th colspan="2">郵便番号<span id="reqKokunaiDisp1"><font class="txtAttention">*</font></span></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(10)"/>">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="middle">
                                        <html:text name="MoshikomiJohoInp" property="yubinNo1" size="10" maxlength="3" styleClass="ime_off" styleId="yubin1" title="(例) 102"/>　-　
                                        <html:text name="MoshikomiJohoInp" property="yubinNo2" size="10" maxlength="4" styleClass="ime_off" styleId="yubin2" title="(例) 0073" />　
                                        <INPUT type="button" property="jushoSearch" value ="住所自動入力"onblur="offenter()" onfocus="onenter()"  onclick="linkOpenWin('../../YubinSelAct.do');saveScrollTop()"/>
                                        （半角数字）
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        郵便番号を入力して、「検索」ボタンを押してください。郵便番号がわからない場合は
                                        <a href="http://www.post.japanpost.jp/zipcode/" target="_blank">[日本郵政-郵便番号検索]</a>
                                        をご利用ください。<br>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="5">住所<span id="reqKokunaiDisp2"><font class="txtAttention">*</font></span></th>
                        <th class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            都道府県
                        </th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            <html:select name="MoshikomiJohoInp" property="todofuken">
                                <html:options collection="TodofokenList" property="value" labelProperty="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >市区町村・郡</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            <html:text name="MoshikomiJohoInp" property="jusho1" size="60" maxlength="20" styleClass="ime_on" styleId="jusho1" title="(例) 東京都　千代田区"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >町名</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            <html:text name="MoshikomiJohoInp" property="jusho2" size="60" maxlength="20" styleClass="ime_on" styleId="jusho2" title="(例)九段北 １－８－１"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >丁目・番地</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            <html:text name="MoshikomiJohoInp" property="jusho3" size="60" maxlength="20" styleClass="ime_on" styleId="jusho3" title="(例)九段１０１ビル"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >ビル・建物名、部屋番号　など</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(11)"/>" >
                            <html:text name="MoshikomiJohoInp" property="jusho4" size="60" maxlength="25" style="ime-mode:active"/></br>
                            （全角）<br>
                            ※表札とご本人名字が異なる場合は◯◯様方と入力してください。
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2">
                            連絡先<br> <font class="txtAttention">（いずれかの入力必須）</font>
                        </th>
                        <th colspan="2">ＴＥＬ</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(12)"/>">
                            <table>
                                <tr>
                                    <td style="border: none">
                                        <html:text name="MoshikomiJohoInp" property="telNo" size="30" maxlength="20" style="ime-mode:disabled"/><br/>
                                        （半角数字、記号） <br/>
                                    </td>
                                    <td style="border: none">
                                        内線&nbsp;<html:text name="MoshikomiJohoInp" property="extNo" size="14" maxlength="10" style="ime-mode:disabled"/><br/>
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
                        <th colspan="2">携帯電話</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(12)"/>">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="middle">
                                        <html:text name="MoshikomiJohoInp" property="cellphoneNo" size="30" maxlength="20" styleClass="ime_off" styleId="sonota" title="(例) 090-1234-5678"/><br>
                                        （半角数字、記号）
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">メールアドレス<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(14)"/>">
                            <table cellspacing="0" cellpadding="1" width="99%">
                                <tr>
                                    <td class="middle">
                                        <html:text name="MoshikomiJohoInp" property="mailAddress" size="70" maxlength="50" styleClass="ime_off" styleId="mail1" title="(例) xxxxx@xxxx.xx.xx"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="10"> </td>
                                </tr>
                                <tr>
                                    <td class="middle">
                                        確認のためもう一度入力してください。<br>
                                        <div onCopy="return false" onPaste="return false">
                                            <html:text name="MoshikomiJohoInp" property="mailAddressKakunin" size="70" maxlength="50" styleClass="ime_off" styleId="mail2" title="(例) xxxxx@xxxx.xx.xx"/>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <th colspan="3">顔写真ファイル</th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(19)"/>" >
                            <table style="margin-left: 0em;">
                                <tr>
                                    <td><div class="divimg">
                                            <img src="../../PictureAction.do?gazoId=<bean:write name="MoshikomiJoho" property="gazoId" />&param=2"  width="128" height="172">
                                        </div>
                                    </td>
                                    <td class="TDbottom" >
                                        <!-- 補正依頼区分　依頼なし →表示無し -->
                                        <logic:equal name="MoshikomiJohoInp" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_MI%>">

                                        </logic:equal>

                                        <!-- 補正依頼区分　補正依頼 →補正依頼区分名、期限日、ファイル選択ボタンなど表示 -->
                                        <logic:equal name="MoshikomiJohoInp" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_IRAI%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJohoInp" property="hoseiIraiKbnDisp"/><br />
                                                        期限日：<bean:write name="MoshikomiJohoInp" property="hoseiIraiKigenBiDisp"/><br></font>
                                                        <html:file property="photoFile" size="30" onmouseout="putMessage()" onkeydown="return false"/><br>
                                                        <div id="message01"></div><br>
                                                        事前に画像切取ツール等で準備をしていただいた写真ファイルを指定してください。「次へ」ボタンで画面を進めると、指定した顔写真を確認できます
                                                    </td>
                                                </tr>
                                            </table>
                                        </logic:equal>

                                        <!-- 補正依頼区分　補正済み →補正依頼区分名、写真更新日、ファイル選択ボタンなど表示 -->
                                        <logic:equal name="MoshikomiJohoInp" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_HOSEI%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJohoInp" property="hoseiIraiKbnDisp"/><br />
                                                        写真更新日：<bean:write name="MoshikomiJohoInp" property="hoseiTaioDateTimeDisp"/><br></font>
                                                        <html:file property="photoFile" size="30" onmouseout="putMessage()" onkeydown="return false"/><br>
                                                        <div id="message01"></div><br>
                                                        事前に画像切取ツール等で準備をしていただいた写真ファイルを指定してください。「次へ」ボタンで画面を進めると、指定した顔写真を確認できます
                                                    </td>
                                                </tr>
                                            </table>
                                        </logic:equal>

                                        <!-- 補正依頼区分　補正確認済み →補正依頼区分名、写真確認日　表示、ファイル選択ボタンなどは非表示 -->
                                        <logic:equal name="MoshikomiJoho" property="hoseiIraiKbn" value="<%=MiwConstants.HOSEI_IRAI_KBN_KAKUNIN%>">
                                            <table  cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="txtAttention">
                                                        <bean:write name="MoshikomiJohoInp" property="hoseiIraiKbnDisp"/><br />
                                                        写真確認日：<bean:write name="MoshikomiJohoInp" property="hoseiFinishDateTimeDisp"/></font>
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
                        <td width="30%"></td>
                        <td width="70%"></td>
                    </tr>
                    <tr>
                        <th>医療保険事務の受講経験<font class="txtAttention">*</font></th>
                        <td  class="<bean:write name="MoshikomiJohoInp" property="validateCss(20)"/>" >
                            <html:radio name="MoshikomiJohoInp" property="jukoKeiken" value="1" style="ime-mode:disabled"/>あり&nbsp;&nbsp;
                            <html:radio name="MoshikomiJohoInp" property="jukoKeiken" value="2" style="ime-mode:disabled"/>なし 
                        </td>
                    </tr>
                    <tr>
                        <th>医療保険事務の実務経験<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(21)"/>" >
                            <html:radio name="MoshikomiJohoInp" property="jitsumuKeiken" value="1" style="ime-mode:disabled"/>あり&nbsp;&nbsp;
                            <html:radio name="MoshikomiJohoInp" property="jitsumuKeiken" value="2" style="ime-mode:disabled"/>なし
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td><b>ご本人様確認用の質問・回答</b></td>
                    </tr>
                    <tr>
                        <td>ご本人様確認用の質問とその答えはパスワードを忘れたときに必要になります。</td>
                    </tr>
                </table>
                <table class="tableMain">
                    <tr>
                        <td width="30%"></td>
                        <td width="70%"></td>
                    </tr>
                    <tr>
                        <th>質問<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(23)"/>">
                            <html:select name="MoshikomiJohoInp" property="passwdQuestionCode1" styleClass="ime_off">
                                <html:optionsCollection name="passwdQuestionCode" value="value" label="label" />
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <th>回答<font class="txtAttention">*</font></th>
                        <td class="<bean:write name="MoshikomiJohoInp" property="validateCss(24)"/>">
                            <html:text name="MoshikomiJohoInp" property="passwdAnswer1" size="45" maxlength="20" styleClass="ime_on" styleId="ans" title="(例) クロワッサン"/>
                            （全角）
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
                            <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="30" height="30"></td>
                    </tr>
                    <tr>
                        <td class="TDcenter">
                            <logic:equal name="DntInfo" property="tetsudukiJokyoKbn" value="<%=MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE%>">
                                <html:submit property="cancel" value="申 込 取 消" style="width:100px" styleClass="buttonCancel" onblur="offenter()" onfocus="onenter()" onclick="return cancelConfirmHiddenValue(this)"/>
                            </logic:equal>
                            <logic:notEqual name="DntInfo" property="tetsudukiJokyoKbn" value="<%=MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE%>">
                                <input type="button" value="申 込 取 消" style="width:100px" disabled="disabled"/>
                            </logic:notEqual>
                        </td>
                    </tr>    
                </table>
                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDleft">
                            <html:submit property="back" value="申込者情報詳細に戻る" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                        </td>
                        <td class="TDright">
                            <a href="#page_top">▲ページ先頭へ</a>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>
