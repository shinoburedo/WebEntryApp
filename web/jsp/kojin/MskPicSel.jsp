<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskPicSel" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body class="body" onload="putMessage()">
        <div id="container">
            <html:form action="/kojin/Msk/MskPicSelAct" enctype="multipart/form-data"  onsubmit="disableSubmit(this)">
                <!--heder-->
                <div id="title_header"></div>
                <div align="center">
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k5.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">５．顔写真アップロード</td>
                        </tr>
                    </table>
                    <logic:messagesPresent>
                        <tr>
                            <td colspan="2"> 
                                <html:errors />
                            </td>
                        </tr>
                    </logic:messagesPresent>
                    <br>
                    <table class="table1" cellpadding="4" cellspacing="0">
                        <tr>
                            <td>
                                「顔写真についての注意点」をよくお読みの上、事前にご準備いただいている顔写真ファイルを選択し、<br>
                                「次へ」ボタンを押してください。
                            </td>
                        </tr>
                    </table>
                    <br>
                    <table class="table3" cellspacing="0" cellpadding="15"><tr><td>
                                <table  cellspacing="0" cellpadding="3">
                                    <tr>
                                        <td>
                                            <table cellspacing="0" cellpadding="1">
                                                <tr>
                                                    <td><font class="font13red">顔写真についての注意点<br><br></font></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table cellspacing="0" cellpadding="1">
                                                <tr>
                                                    <td class="td6">１&nbsp;</td>
                                                    <td>指定の規格の写真のご準備ができていない場合は、画像切取ツールを入手し、写真を加工してください。<br>
                                                        <a href="#" onclick="openUrl('../../html/ToolDld.htm'); return false;">画像切取ツールのダウンロードはこちら。</a><br>
                                                        <a href="#" onclick="openUrl('../../html/Piccutconf.html'); return false;">画像切取ツールの利用マニュアルはこちら。</a><br><br>
                                                        必要な写真の規格については<a href="../../html/KaossnKikaku.htm" target="_blank">こちら</a>をご確認ください。<br/>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table cellspacing="0" cellpadding="1">
                                                <tr>
                                                    <td class="td6">２&nbsp;</td>
                                                    <td>顔写真ファイルが、次の画面(顔写真を確認する画面)において、下記の「受付できない顔写真ファイルの例」
                                                        のように、横長や縦長になっている場合や試験案内に規定している写真の内容(無帽、無背景、顔の大きさ等)に反している場合は、
                                                        上記１と同様に受付トップに戻って顔写真を作成した上で申込をやり直してください。<br>
                                                        そのまま送信されますと、申込を受理できませんので、後日撮り直しの指示をさせていただくことになります。</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td><font class="font6"><br>※顔写真ファイルは、受験時の本人確認に利用されます。</font></td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><font class="font13red"><br>適切な顔写真ファイルの例</font>
                                            <br>
                                            <table width="600">
                                                <tr>
                                                    <td width="100" class="td3">　　832ピクセル</td>
                                                    <td width="200" class="td2">縦横の長さが規格内の場合<br>
                                                        <img src="../../image/mskpic_kao1.gif"  alt="サンプル顔写真"><br>
                                                        640ピクセル
                                                    </td>
                                                    <td width="50" class="td3"><img src="../../image/mskpic_arrow.gif" width="30" height="30" alt=""></td>
                                                    <td width="200" class="td2 td6">顔が正しく表示されます<br>
                                                        <img src="../../image/mskpic_kao1.gif" width="69" height="80" alt="サンプル顔写真縮小">
                                                    </td>
                                                    <td width="50">
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td colspan="4">※この画像はサンプルのため、実際の画像ファイルの大きさとは異なりますのでご注意ください。</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><font class="font13red">受付できない顔写真ファイルの例</font><br><b>＜悪い例１＞</b><br>
                                            <br>
                                            <table width="600" >
                                                <tr>
                                                    <td width="100" class="td3">　　748ピクセル</td>
                                                    <td width="200" class="td2">横長の顔写真ファイルの場合<br>
                                                        <img src="../../image/mskpic_kao2.gif" alt="サンプル顔写真"><br>
                                                        786ピクセル
                                                    </td>
                                                    <td width="50" class="td3"><img src="../../image/mskpic_arrow.gif" width="30" height="30" alt=""></td>
                                                    <td width="200" class="td2 td6">顔が縦長に表示されます<br>
                                                        <img src="../../image/mskpic_kao2.gif"width="69" height="80" alt="サンプル顔写真縮小">
                                                    </td>
                                                    <td width="50">
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><b>＜悪い例２＞</b>
                                            <br><br>
                                            <table width="600"  >
                                                <tr>
                                                    <td width="100" class="td3">　1024ピクセル</td>
                                                    <td width="200" class="td2">縦長の顔写真ファイルの場合<br>
                                                        <img src="../../image/mskpic_kao3.gif"  alt="サンプル顔写真"><br>
                                                        574ピクセル
                                                    </td>
                                                    <td width="50" class="td3"><img src="../../image/mskpic_arrow.gif" width="30" height="30" alt=""></td>
                                                    <td width="200" class="td2 td6">顔が横長に表示されます<br>
                                                        <img src="../../image/mskpic_kao3.gif" width="69" height="80" alt="サンプル顔写真縮小">
                                                    </td>
                                                    <td width="50">
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                    </table>
                    <br>
                    <table class="tableMain" cellspacing="0" cellpadding="10">
                        <tr>
                            <th><font class="font14"><b>顔写真ファイル</b><br/>&nbsp;<font class="txtAttention">(必須)</font></font></th>
                            <td class="basic">
                                <br>
                                <html:file property="photoFile" size="60" onmouseout="putMessage()" onkeydown="return false"/><br>
                                <div id="message01"></div>
                                <font class="txtAttention">顔写真についての注意点をよくお読みの上、写真画像ファイルを選択してください。</font>
                                <%-- IEの仕様で「type="file"」のみだとEnter押下時にsubmitされてしまうため記述 --%>
                                <input type="text" value="" style="width:0px; border:none" readOnly="readonly">
                                <input type="text" value="" style="width:0px; border:none" readOnly="readonly">
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br><br>                
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="td2">

                                <html:submit property="back" value="戻　る"style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)"/>　　　
                                
                            </td>
                        </tr>
                    </table>
                    <table  class="table1" cellspacing="0" cellpadding="0">
                        <tbody>
                            <tr>
                                <td class="TDright TDtop">
                                    <input type="button" value="メインメニューに戻る" class="buttonNext" onblur="offenter()" onfocus="onenter()" onclick="backMenuConfirm()"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </html:form>
        </div>
    </body>
</html:html>