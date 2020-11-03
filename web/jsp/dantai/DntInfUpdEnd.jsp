<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />


<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfUpdEnd"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/myp.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
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
                    <td class="right"><bean:message key="title.Dantai_InfUpdEnd"/></td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame">
                <tr>
                    <td class="TDcenter">
                        <font size="+2"><b>団体情報の変更が完了しました。</b></font><br>
                    </td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame"> 
                <tr>
                    <td><b>申込手続状況</b></td>
                </tr>
            </table>
            <table class="tableMain">
                <tr>
                    <td width="34%"></td>
                    <td width="66%"></td>
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
                        <bean:write name="DntInfo" property="kessaiJokyoKbnDisp"/>
                    </td>
                </tr>
                <tr>
                    <th>受付状況</th>
                    <td class="basic">
                        <bean:write name="DntInfo" property="tetsudukiJokyoKbnDisp"/>
                    </td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame"> 
                <tr>
                    <td><b>団体情報</b></td>
                </tr>
            </table>
            <table class="tableMain" cellpadding="4" cellspacing="0">
                <tr>
                    <th width="34%">団体等の名称</th>
                    <td class="basic" width="66%" colspan="2">
                        <bean:write name="DntInfo" property="dantaiName"/>
                    </td>
                </tr>
                <tr>
                    <th>団体等の名称フリガナ</th>
                    <td class="basic" colspan="2">
                        <bean:write name="DntInfo" property="dantaiNameKana"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        所在地
                    </th>
                    <td class="basic" colspan="2">
                        <bean:write name="DntInfo" property="yubinNo1" />-
                        <bean:write name="DntInfo" property="yubinNo2" /><br>
                        <bean:write name="DntInfo" property="dantaiJushoDisp" />
                    </td>
                </tr>
                <tr>
                    <th width="34%">担当者名</th>
                    <td class="basic" width="66%" colspan="2">
                        <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiSei" />&nbsp;&nbsp;
                        <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiMei" />
                    </td>
                </tr>
                <tr>
                    <th>担当者名フリガナ</th>
                    <td class="basic" colspan="2">
                        <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiSeiKana" />&nbsp;&nbsp;
                        <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiMeiKana" />
                    </td>
                </tr>
                <tr>
                    <th>電話番号</th>
                    <td class="basic" style="border: none">
                        <logic:equal name="DntInfo" property="dantaiJimuTantoshaTelNo" value="">
                            -
                        </logic:equal>
                        <logic:notEqual name="DntInfo" property="dantaiJimuTantoshaTelNo" value="">
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaTelNo" />
                        </logic:notEqual>
                    </td>
                    <td class="basic" style="border-left-style: none"> 
                        <logic:equal name="DntInfo" property="isExtNo" value="true">
                            内線&nbsp;<bean:write name="DntInfo" property="extNo"/>
                        </logic:equal>
                    </td>
                </tr>
                <tr>
                    <th>ＦＡＸ番号</th>
                    <td class="basic" colspan="2">
                            <logic:equal name="DntInfo" property="dantaiJimuTantoshaFaxNo" value="">
                                -
                            </logic:equal>
                            <logic:notEqual name="DntInfo" property="dantaiJimuTantoshaFaxNo" value="">
                                <bean:write name="DntInfo" property="dantaiJimuTantoshaFaxNo" />
                            </logic:notEqual>
                    </td>
                </tr>
                <tr>
                    <th>メールアドレス</th>
                    <td class="basic" colspan="2">
                        <bean:write name="DntInfo" property="dantaiJimuTantoshaMailAddress" />
                    </td>
                </tr>
                <tr>
                    <th  rowspan="2">受験申込者数</th>
                    <td class="basic" width="420" colspan="2">
                        医科　：&nbsp;&nbsp;<bean:write name="DntInfo" property="moshikomishaSuIka" />&nbsp;&nbsp;名
                    </td>
                </tr>
                <tr>
                    <td class="basic" width="420" colspan="2">
                        歯科　：&nbsp;&nbsp;<bean:write name="DntInfo" property="moshikomishaSuShika" />&nbsp;&nbsp;名
                    </td>
                </tr>
                <tr>
                    <th width="220">発送物の送付先</th>
                    <td class="basic" width="420" colspan="2">
                        <bean:write name="DntInfo" property="hassosakiKbnDisp" />
                    </td>
                </tr>
                <tr>
                    <th width="220"><%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法</th>
                    <td class="basic" width="420" colspan="2">
                        <bean:write name="DntInfo" property="kessaiHohoKbnDisp" />
                    </td>
                </tr>
                <tr>
                    <th width="220">団体パスワード<br>
                        <font size="-2">（団体代表者が使用するパスワードです。）</font>
                    </th>
                    <td class="basic" width="420" colspan="2">
                        （表示しません）
                    </td>
                </tr>
                <tr>
                    <th width="220">団体申込者登録用パスワード<br>
                        <font size="-2">（団体申込者が使用するパスワードです。）</font>
                    </th>
                    <td class="basic" width="420" colspan="2">
                        （表示しません）
                    </td>
                </tr>
                <tr>
                    <th width="220">通信欄</th>
                    <td class="basic" width="420" colspan="2">
                        <logic:notEmpty name="DntInfo" property="moshikomiMemo">
                            <bean:write name="DntInfo" property="moshikomiMemoDisp" filter="false" />
                        </logic:notEmpty>
                        <logic:empty name="DntInfo" property="moshikomiMemo">
                            -&nbsp;
                        </logic:empty>
                    </td>
                </tr>
            </table>
            <br>
            <%--手続き状況区分が「仮受付」「受付完了」の場合のみ表示--%>
            <c:choose>
                <c:when test="${DntInfo.isTetsudukiJokyoKariUketuke || DntInfo.isTetsudukiJokyoUketukeEnd}">
                    <table class="tableNoFrame">
                        <tr>
                            <td><b>決済情報</b></td>
                        </tr>
                    </table>
                    <table class="tableMain">
                        <tr>
                            <td width="17%"></td>
                            <td width="17%"></td>
                            <td width="66%"></td>
                        </tr>
                        <tr>
                            <th colspan="2">決済方法</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="kessaiHohoKbnDisp" />
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">ご請求金額</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="kessaiKingakuDisp" />円
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">振込先銀行名</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="bankName"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">振込先支店名</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="branchName"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">預金種類</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="depositKind"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">口座番号</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="accountNum"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">口座名義</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="accountName"/>
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">振込人名</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="furikomiName" />
                            </td>
                        </tr>
                        <tr>
                            <th colspan="2">振込期限</th>
                            <td class="basic">
                                <bean:write name="DntInfo" property="kessaiKigenBiDispSlash" />
                            </td>
                        </tr>
                    </table>
                </c:when>
            </c:choose>

            <br>
            <table class="tableNoFrame">
                <tr>
                    <td><b>団体代表者様確認用の質問・回答</b></td>
                </tr>
            </table>
            <table class="tableMain">
                <tr>
                    <td width="34%"></td>
                    <td width="66%"></td>
                </tr>
                <tr>
                    <th>質問</th>
                    <td class="basic">
                        <bean:write name="DntInfo" property="passwdQuestionCode1Disp" />
                    </td>
                </tr>
                <tr>
                    <th>回答</th>
                    <td class="basic">
                        <bean:write name="DntInfo" property="passwdAnswer1Disp" />
                    </td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="TDcenter">
                        <input type="button" value="団体情報確認に戻る" onblur="offenter()" onfocus="onenter()" onclick="location.href='../../dantai/Inf/DntInfConfInitAct.do'" class="buttonNext"/>
                    </td>
                    <td class="TDcenter">
                        <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='../../dantai/Inf/DntInfLoginInitAct.do'" class="buttonNext"/>
                    </td>
                </tr>
            </table>
            <br>
            <table class="tableNoFrame">
                <tr>
                    <td class="TDright">
                        <a href="#">▲ページ先頭へ</a>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html:html>
