<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_Agr"/></title>
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
            <br>
            <table class="TDheader" cellpadding="0" cellspacing="0">
                <tr>
                    <td><img src="../../image/nv_dnt1.gif" alt="団体申込出願フロー"></td>
                </tr>
            </table>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right">１．<bean:message key="title.Dantai_Agr"/></td>
                </tr>
            </table>
            <br>
            <table class="table1" cellpadding="0" cellspacing="0" >
                <tr>
                    <td rowspan="3" width="10" class="bg3"></td>
                    <td rowspan="3" width="4"></td>
                    <td rowspan="3" width="2"  class="bg3"></td>
                    <td rowspan="3" width="15"></td>
                    <td rowspan="3"width="599" class="TDleft">
                        <font class="txtNotice">
                        以下の注意事項をよくお読みの上、「同意する」をクリックしてください。
                        </font>
                    </td>
                </tr>
            </table>

            <table cellpadding="15" class="table2">
                <tr>
                    <td>
                        <table cellpadding="1" cellspacing="0">
                            <tr>
                                <td colspan="2" class="TDcenter" ><font class="headline">ご利用上のご注意</font><br/>　</td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    <font class="subHeadline">
                                    【認定試験申込に際しての諸注意】
                                    </font>
                                    <br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ <%=MiwConstants.JUKEN_CHARGE_NAME%><br/>
                                    <bean:write name="DntInfo" property="kenteiryoDisp" />円（受験申込者1人あたり）<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ インターネット申込の場合、<%=MiwConstants.JUKEN_CHARGE_NAME%>の払込は団体の人数分の金額を一括してゆうちょ銀行又は三菱東京UFJ銀行へ振込となります。<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ 銀行振込に必要な手数料は申込団体が負担するものといたします。<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ いったん納入された<%=MiwConstants.JUKEN_CHARGE_NAME%>は、理由の如何にかかわらず一切返金いたしません。<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ 受験票の到着をもって最終的な受験申込受付の確認とさせていただきます。決済時のご利用明細は、受験票の到着まで、<%=MiwConstants.JUKEN_CHARGE_NAME%>納入の控えとして大切に保管してください。<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                    ◆ 受験票の未着問合せ期間にお問合せがなく受験できなかった場合、当協会では一切の責任を負いません。<br/>
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
                                    <font class="subHeadline">
                                    ◇メールアドレスについて
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 団体申込出願にはメールアドレスが必要です。日本語のメールアドレスはシステム未対応のため登録できません。<br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 携帯電話のメールアドレスや、フリーメールアドレス（無料メール）で登録された場合、メールの受信に遅延や障害が生じることがあります。 このような不具合があった場合の責任は負いかねます。<br/>
                                    <br/>
                                </td>
                            </tr>

                            <tr>
                                <td  class="TDleft" colspan="2">
                                    <font class="subHeadline">
                                    ◇入力にあたって
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 文字情報の入力に際しては、「ＪＩＳ第一水準・第二水準」の文字を使用してください。氏名、住所等に既定以外の文字が含まれている場合は、置き換え可能な文字で入力してください。<br/>
                                    <br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 【Windows Vista 以降のパソコンをご利用の方へ】<br/>
                                    当システムはJIS90字形(WindowsXP以前)の文字を利用しており、JIS2004字形(Vista以降)の場合、一部文字表記が異なります。そのためVista以降のパソコンをご利用の場合は、ウェブ上の画面と送付される印刷物の文字表記に相違が生じる場合があります（申込に問題はありません）。あらかじめご了承下さい。詳しくは、Microsoft社ホームページ（<a href="http://www.microsoft.com/ja-jp/windows/products/windowsvista/jp_font/default.aspx" target="_blank">こちら</a>）にてご確認下さい。<br/>
                                    <br/>
                                </td>
                            </tr>

                            <tr>
                                <td  class="TDleft" colspan="2">
                                    <font class="subHeadline">
                                    ◇個人情報の取扱いについて
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 団体コード及びパスワードの管理は団体代表者（団体担当者を含む。）にお願いいたします。団体コード及びパスワードの漏えい、違法利用などから生じた損害に対して当協会は責任を負いかねます。<br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 団体申込者登録用ＩＤ及び団体申込者登録用パスワードの管理は団体代表者（団体担当者を含む。）及び申込者ご本人にお願いいたします。団体申込者登録用ＩＤ及び団体申込者登録用パスワードの漏えい、違法利用などから生じた損害に対して当協会は責任を負いかねます。<br/>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 当協会の個人情報の取扱いについては<a href="../../html/PrivacyPolicy.html" target="_blank">こちら</a>をご覧ください。<br/>
                                    <br/>
                                </td>
                            </tr>

                            <tr>
                                <td  class="TDleft" colspan="2">
                                    <font class="subHeadline">
                                    ◇免責事項
                                    </font>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 当協会は次に挙げる事項によって生じた損害に対しては責任を負いません。<br/>
                                    <table>
                                        <tr>
                                            <td class="tdtop"width="2%">
                                            </td>
                                            <td width="95%">
                                                　通信機器、通信回線及びコンピューター等のシステム機器（申込者ご本人、プロバイダー、通信事業者、当協会のそれぞれのハードウェア、ソフトウェア及びそれぞれをつなぐ通信回線のすべてを含みます）の障害
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="2%">
                                            </td>
                                            <td width="95%">
                                                　第三者による妨害、侵入、情報改変等により、インターネット申込ができなくなった場合
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td  class="TDleft" colspan="2">
                                   ・ 当協会は、本サービスを提供するための保守その他工事をする場合には、利用者への通知を行わず本サービスを一時的に停止する場合があります。<br/>
                                    <br/>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
            <br>
            <br>

            <table class="table1" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td class="TDcenter">
                            <form name="NullFrm" method="post" action="../Msk/DntAgrAct.do" onsubmit="disableSubmit(this)">
                                <input type="button" value="メインメニューに戻る" onclick="location.href='../MenuDaihyoshaAct.do'" style="width:150px"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="submit" name="submit" value="同意する" onclick="setHiddenValue(this)" onblur="offenter()" onfocus="onenter()" style="width:150px">
                            </form>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br/>
            <br/>
            <table class="table1" cellspacing="0" cellpadding="0" border="0">
                <tbody>
                    <tr>
                        <td class="TDcenter">
                            <IFRAME src="../../html/callcenter.html" frameborder="0" class="iframeCall">
                            </IFRAME>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html:html>