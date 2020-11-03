<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.DntMskAgr"/></title>
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
            <!--heder-->
            <div id="title_header"></div>
            <div align="center">
                <table class="header" cellpadding="0" cellspacing="0">
                    <tr>
                        <td><img src="../../image/nv_dnt_msk2.gif" alt="試験申込フロー"></td>
                    </tr>
                </table>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">２．<bean:message key="title.DntMskAgr"/></td>
                    </tr>
                </table>
                <br>
                <table cellpadding="15" class="table1">
                    <tr>
                        <td>
                            <table cellpadding="1" cellspacing="0">
                                <tr>
                                    <td colspan="2 class="TDleft">
                                        <font class="txtNotice">
                                            　認定試験のインターネット申込に際して、 以下の内容をよくお読みの上、同意される場合は「同意する」のボタンをクリックして、 情報入力画面にお進みください。
                                        </font>
                                    </td>
                                </tr>
                                <tr><td colspan="2">　</td></tr>
                                <tr>
                                    <td class="TDtop" colspan="2">
                                        <font size="-1">
                                        申込期間
                                        </font>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="TDright TDtop">・</td>
                                    <td>
                                        <font size="-1">
                                        <bean:write name="MskTorokuJoho" property="shutsuganStartDateDisp"/><bean:write name="MskTorokuJoho" property="shutsuganStartTimeDisp"/>から&nbsp;
                                        <bean:write name="MskTorokuJoho" property="shutsuganEndDateDisp"/><bean:write name="MskTorokuJoho" property="shutsuganEndTimeDisp"/>まで
                                        </font>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <table cellpadding="15" class="table2">
                    <tr>
                        <td>
                            <table cellpadding="1" cellspacing="0">
                                <tr>
                                    <td colspan="2" class="TDcenter" ><font class="headline">申込規約</font><br>　</td>
                                </tr>
                                <tr>
                                    <td class="TDright TDtop"　width = "5%">※</td>
                                    <td>
                                        申込の前に<a href="<%=MiwConstants.URL_JUKEN_ANNAI%>" target="_blank">受験案内</a>をよくご確認ください。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        <font class="subHeadline">
                                        【認定試験申込・受験に際しての諸注意】
                                        </font>
                                        <br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;<%=MiwConstants.JUKEN_CHARGE_NAME%><br/>
                                        <bean:write name="MskTorokuJoho" property="kenteiryoDisp" />円（受験申込者1人あたり）<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;申込後の受験希望地変更は、インターネット申込の場合、所定の期日まで「団体情報確認」から団体代表者が行うことができます。 ただし、期日以降の受験希望地変更は一切受け付けられません。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;受験票の到着をもって最終的な受験申込受付の確認とさせていただきます。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;受験票の未着問合せ期間にお問合せがなく受験できなかった場合、当協会では一切の責任を負いません。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        <font class="subHeadline">
                                        【インターネット申込に関する諸注意】
                                        </font>
                                        <br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                       ◆&nbsp;文字情報の入力に際しては、「ＪＩＳ第一水準・第二水準」の文字を使用してください。氏名、住所等に既定以外の文字が含まれている場合は、置き換え可能な文字で入力してください。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                       ◆&nbsp;【Windows Vista 以降のパソコンをご利用の方へ】<br/>
                                        当システムはJIS90字形(WindowsXP以前)の文字を利用しており、JIS2004字形(Vista以降)の場合、一部文字表記が異なります。そのためVista以降のパソコンをご利用の場合は、ウェブ上の画面と送付される印刷物の文字表記に相違が生じる場合があります（申込に問題はありません）。あらかじめご了承下さい。詳しくは、Microsoft社ホームページ（<a href="http://www.microsoft.com/ja-jp/windows/products/windowsvista/jp_font/default.aspx" target="_blank">こちら</a>）にてご確認下さい。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;ＩＤ及びパスワードの管理は申込者ご本人が行ってください。ＩＤ及びパスワードの漏えい、違法利用などから生じた損害に対して当協会は責任を負いません。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;当協会は、本サービスを提供するための保守その他工事をする場合には、利用者への通知を行わず本サービスを一時的に停止する場合があります。<br/>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;当協会は次に挙げる事項によって生じたお客さまの損害に対しては、その責任を負いません。<br/>
                                        <br/>
                                        <table>
                                            <tr>
                                                <td class="TDTop" width="2%">・</td>
                                                <td width="98%">
                                                    通信機器、通信回線及びコンピューター等のシステム機器（申込者ご本人、プロバイダー、通信事業者、当協会のそれぞれのハードウェア、ソフトウェア及びそれぞれをつなぐ通信回線のすべてを含みます）の障害
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="TDTop" width="2%">・</td>
                                                <td width="98%">
                                                    第三者による妨害、侵入、情報改変等により、インターネット申込ができなくなった場合
                                                </td>
                                            </tr>
                                        </table>
                                        <br/>
                                    </td>
                                </tr>
                                <tr>
                                    <td  class="TDleft" colspan="2">
                                        ◆&nbsp;<a href="<%=MiwConstants.URL_PRIVACY_POLICY%>" target="_blank">当協会の個人情報の取扱いについて</a><br/>
                                        <br/>
                                        <br/>
                                    </td>
                                </tr>                        
                            </table>
                </table>
                <br>
                <br>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="TDcenter">
                                <html:form action="/dantai/Msk/MskAgrAct" onsubmit="disableSubmit(this)">
                                    <html:submit property="back"value="同意しない" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" style="width:100px"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <html:submit property="submit"value="同意する" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" style="width:100px"/>
                                </html:form>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <table  class="table1" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="TDright TDtop">
                                <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="backMenuDantai('<bean:write name="menuUrl"/>')"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br/>
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
            </div>
        </div>
    </body>
</html:html>
