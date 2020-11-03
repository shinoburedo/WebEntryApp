<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Kojin_Menu"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">  
        <script type="text/javascript" src="../js/common.js"></script>
        <script type="text/javascript" src="../js/msk.js"></script>
        <link href="../css/common.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <br>
            <div align="center">
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right"><bean:message key="title.Kojin_Menu"/></td>
                    </tr>
                </table>
                <br/>
                <table class="table1">
                    <tr>
                        <td>
                                　「インターネット申込の流れ」をご確認のうえ、画面下部のボタンをクリックしてご希望のページにお進みください。
                        </td>
                    </tr>
                </table>
                <br/>
                <table class="table1">
                    <tr>
                        <td colspan="3"  bgcolor="#EEEEEE">
                            <font size="+1"><b>◆インターネット申込に必要なもの</b></font><br/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop" width="3%">　</td>
                        <td class="TDtop" width="3%">●</td>
                        <td>
                            <b>顔写真ファイル(JPEG形式)</b><br/>
                            必要な写真の規格については<a href="../html/KaossnKikaku.htm" target="_blank">こちら</a>をご確認ください。<br/><br/>
                            ※「JPEG形式」とは静止画像のデジタルデータを圧縮する方式で、拡張子に「jpg」「jpeg」等が使用されています。<br/><br/>
                        </td>
                    </tr>
                    <tr>
                        <td class="TDtop" width="3%">　</td>
                        <td class="TDtop" width="3%">●</td>
                        <td>
                            <b>メールアドレス</b><br/>
                            日本語のメールアドレスはシステム未対応のため登録できません。<br>
                            携帯電話のメールアドレスや、フリーメールアドレス（無料メール）で登録された場合、
                            メールの受信に遅延や障害が生じることがあります。 
                        </td>
                    </tr>
                </table>
                <br>
                <table class="table1">
                    <tr>
                        <td class="TDtop" colspan="2" bgcolor="#EEEEEE">
                            <font size="+1"><b>◆インターネット申込の流れ</b></font><br/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>ＩＤ取得</b>
                        </td>
                        <td width="60%">
                                        　メールアドレスやパスワード等の登録を行います。
                            <br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="guideWhite">
                    インターネットでの認定試験受験のお申込にはメールアドレスが必要です。<br/>
                    メールアドレスをお持ちでない方は、郵送でのお申込をご利用ください。
                </div>
                <br>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>ＩＤ通知</b>
                        </td>
                        <td width="60%">
                                          　登録されたメールアドレスに宛てて「ＩＤ通知メール」をお送りします。<br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="guideWhite">
                    <font size="-1" color="#FF0000">
                    通知が届かない方は、メールアドレス誤入力の可能性があります。ＩＤ取得完了後、１０～１５分待ってもメールが届かない場合は、もう一度ＩＤ取得を行ってください。<br/>
                    </font>
                </div>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>認定試験申込：ログイン</b>
                        </td>
                        <td width="60%">
                                        　ＩＤ通知メールに記載されたＩＤと、ＩＤ取得時のパスワードを入力して、認定試験申込ページにログインします。<br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>申込情報入力</b>
                        </td>

                        <td width="60%">
                                        　申込者情報、受験科目、受験希望地など、申込に必要な情報を入力します。<br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>　顔写真アップロード</b>
                        </td>

                        <td width="60%">
                                       　受験者本人の顔写真ファイルを登録します。<br/><br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="guideWhite">
                    インターネットでの認定試験受験のお申込にはデジタルカメラなどで撮影された顔写真ファイルが必要です。<br/>
                    顔写真の規格については<a href="../html/KaossnKikaku.htm" target="_blank">こちら</a>をご確認ください。<br>
                    規格の写真が容易に作成できる画像切取ツール（Windows対応）の使用（ダウンロードは<a href="../html/ToolDld.htm" target="_blank">こちら</a>）をお勧めします。
                    <a href="../html/Piccutconf.html" target="_blank">画像切取ツールの利用マニュアル</a>をご用意しておりますので、ご活用ください。<br><br>
                    <font size="-1" color="#FF0000">
                    登録された顔写真が適切でない場合、「顔写真再提出依頼メール」をお送りします。このメールが届いたら、「申込内容確認」から顔写真の再提出を行ってください。<br><br>
                    </font>
                    顔写真ファイルは認定試験受験のお申込に必ず必要になります。
                    顔写真をファイルでご用意できない方は、郵送でのお申込をご利用ください。<br/>

                </div>
                <br>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>決済方法選択～<%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払</b>
                        </td>

                        <td width="60%">
                            <%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法を、クレジットカードの１回払い、コンビニでの現金払い、ペイジーでのお支払のいずれかからお選びください。<br/>
                            <%=MiwConstants.JUKEN_CHARGE_NAME%>は7,500円です。<br>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="tableNoFrameLeft">
                    <tr>
                        <td>
                            【<b>クレジット決済</b>（１回払い）】<br>
                                        　以下のクレジットカードがご利用いただけます。<br/>
                            <table cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="TDcenter">
                                        <img src="../image/cardmark.gif" alt="VISA・MASTER・JCB・AMEX・DINERS">
                                    </td>
                                </tr>
                            </table>
                                        　カード情報入力後、すぐにお支払が完了します。申込完了画面が表示された時点でお申込完了です。<br/>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            【<b>コンビニ決済</b>】<br>
                                        　以下のコンビニエンスストアのうちからご利用いただけます。<br/>
                            <table cellspacing="0" cellpadding="0" width="100%">
                                <tr>
                                    <td class="TDcenter">
                                        <img src="../image/logo_seven.gif"/>
                                        <img src="../image/logo_lawson.gif"/>
                                        <img src="../image/logo_seicomart.gif"/>
                                        <img src="../image/logo_ministop.gif"/>
                                        <img src="../image/logo_familymart.gif"/><br>
                                        <img src="../image/logo_circlek.gif"/>
                                        <img src="../image/logo_sunkus.gif"/>
                                        <img src="../image/logo_dailyyamazaki.gif"/>
                                        <img src="../image/logo_yamazakidailysore.gif"/>
                                        <img src="../image/logo_threef.gif"/><br>　
                                    </td>
                                </tr>
                            </table>
                                        　コンビニ決済を選択された場合、登録されたメールアドレスに「申込受付のお知らせ」をお送りしますが、受験のお申込はまだ完了していません。所定の期限日までにコンビニで<%=MiwConstants.JUKEN_CHARGE_NAME%>をお支払ください。<br/>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            【<b>ペイジー決済</b>】<br>
                                    　銀行・郵便局などの金融機関のATM、インターネットバンキング、モバイルバンキングが 利用できます（金融機関の窓口では、払込みはできません）。
                            ペイジーについての詳細は，日本マルチペイメントネットワーク推進協議会のホームページ（<a href="http://www.pay-easy.jp/" target="_blank">こちら</a>）をご覧ください。<br>
                                    　払込可能な金融機関は、 <a href="http://www.digitalcheck.co.jp/service/pay-easy_list_ari.pdf" target="_blank">こちら</a>で確認できます。<br>
                                    　ペイジー決済を選択された場合、登録されたメールアドレスに「申込受付のお知らせ」をお送りしますが、受験のお申込はまだ完了していません。所定の期限日までに<%=MiwConstants.JUKEN_CHARGE_NAME%>をお支払ください。<br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <div class="TDcenter"><img src="../image/yajirushi_shita.gif" alt="矢印" /></div>
                <br>
                <table class="tableOutFrameGrayDisp">
                    <tr>
                        <td class="tdMidashiGray">
                            <b>申込完了</b>
                        </td>

                        <td width="60%">
                            <%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払後、登録されたメールアドレスに「申込完了メール」をお送りします。<br/>
                        </td>
                    </tr>
                </table>
                <br>
                <div class="guideWhite">
                            　メールが届かない場合は、「申込内容確認」の申込内容確認画面で受付状況が「受付完了」と表示されていれば、申込は完了しています。<br/>
                            　なお、「申込内容確認」からは、申込完了後の登録情報の確認や変更が可能です（一部項目を除く）。<br/><br/>
                            　受験前に必ず<a href="<%=MiwConstants.URL_JUKEN_ANNAI%>" target="_blank">受験案内</a>と<a href="http://www.shaho.co.jp/iryojimu/index_guideline.html" target="_blank">ガイドライン</a>をご確認ください。
                </div>
                <br>
                <hr class="lineGray">
                <br>
                <table class="table1">
                    <tr>
                        <td>
                                　「インターネット申込の流れ」をご確認のうえ、ボタンをクリックしてご希望のページにお進みください。
                        </td>
                    </tr>
                </table>
                <table class="table1">
                    <tr>
                        <td width="25%"></td>
                        <td width="2%"></td>
                        <td width="34%"></td>
                        <td width="2%"></td>
                        <td width="37%"></td>
                    </tr>

                    <tr>
                        <td>
                            <br/>
                    <center>
                        <logic:equal name="MenuJoho" property="idKikanFlg" value="1">
                            <a href="../html/JznTop.html">
                                <img src="../image/kojin_id.gif" alt="ＩＤ取得" width="208" height="53" border="0" />
                            </a>
                        </logic:equal>
                        <logic:notEqual name="MenuJoho" property="idKikanFlg" value="1">
                            <img src="../image/kojin_id_off.gif" alt="ＩＤ取得" width="208" height="53" border="0" />
                        </logic:notEqual>
                    </center>
                    <br/>
                    </td>
                    <td></td>
                    <td>
                        ・お申込用ＩＤの取得
                    </td>
                    <td></td>
                    <td>
                        ＩＤ取得期限 <font class="txtAttention"><bean:write name="MenuJoho" property="idKigenDisp" /></font> まで
                    </td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>
                    <tr>
                        <td>
                            <br/>
                    <center>
                        <logic:equal name="MenuJoho" property="shutsuganKikanFlg" value="1">
                            <a href="../html/MskLogin.html">
                                <img src="../image/kojin_moshikomi.gif" alt="診療報酬請求事務能力認定試験申込" width="208" height="53" border="0" >
                            </a>
                        </logic:equal>
                        <logic:notEqual name="MenuJoho" property="shutsuganKikanFlg" value="1">
                            <img src="../image/kojin_moshikomi_off.gif" alt="診療報酬請求事務能力認定試験申込" width="208" height="53" border="0" />
                        </logic:notEqual>
                    </center>
                    <br/>
                    </td>
                    <td></td>
                    <td>
                        ・診療報酬請求事務能力認定試験受験のお申込
                    </td>
                    <td></td>
                    <td>
                        申込期限 <font class="txtAttention"><bean:write name="MenuJoho" property="shutsuganKigenDisp" /></font> まで
                    </td>
                    </tr>
                    <tr>
                        <td></td>
                    </tr>

                    <tr>
                        <td>
                            <br/>                    
                    <center>
                        <logic:equal name="MenuJoho" property="mypageButtonFlg" value= "0">
                            <img src="../image/kojin_mypage_off.gif" alt="申込内容確認" width="208" height="53" border="0" />
                        </logic:equal>
                        <logic:equal name="MenuJoho" property="mypageButtonFlg" value = "1">
                            <a href="../html/MypLogin.html">
                                <img src="../image/kojin_mypage.gif" alt="申込内容確認" width="208" height="53" border="0" />
                            </a>
                        </logic:equal>
                    </center>
                    <br/>
                    </td>
                    <td></td>
                    <td>
                        ・お申込内容の確認・変更
                    </td>
                    <td></td>
                    <td>
                        <%--<bean:write name="MenuJoho" property="mypageKikanName" />
                        <font class="txtAttention"><bean:write name="MenuJoho" property="mypageKigen" /></font>--%>
                        確認期限：<font class="txtAttention"><bean:write name="MenuJoho" property="kakuninKigenDisp" /></font>&nbsp;まで<br>
                        変更期限：<font class="txtAttention"><bean:write name="MenuJoho" property="henkoKigenDisp" /></font>&nbsp;まで
                        <br/>
                        <br/>
                    </td>
                    </tr>
                </table>
                <br/>


                <table class="table1" cellspacing="0" cellpadding="0">
                    <tbody>
                        <tr>
                            <td class="TDcenter">
                                <input type="button" value="トップページに戻る" onclick="location.href='../index.html'" style="width:150px"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br>
                <table class="table1" cellspacing="0" cellpadding="0" border="0">
                    <tbody>
                        <tr>
                            <td class="TDcenter">
                                <IFRAME src="../html/callcenter.html" class="iframeCall" height="200" scrolling="auto">
                                </IFRAME>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <br>
    </body>
</html:html>