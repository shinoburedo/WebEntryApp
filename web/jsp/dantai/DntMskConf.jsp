<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_MskConf"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body onload="setScrollTop();">
        <div id="container">
            <html:form action="/dantai/Msk/DntMskConfAct.do" styleId="frm" onsubmit="disableSubmit(this)">
                <html:hidden property="scrollTop" />
                <html:hidden property="yubinFlg" />

                <div id="title_header"></div>
                <table class="header" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>
                            <br/>
                            <img src="../../image/nv_re_dnt4.gif" alt="団体申込出願フロー">
                        </td>
                    </tr>
                </table>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0">
                    <tr>
                        <td class="left" cellpadding="0"></td>
                        <td class="right">４．<bean:message key="title.Dantai_MskConf"/></td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td>ご登録内容を確認してください。</td>
                    </tr>
                </table>
                <table class="tableMain" cellpadding="4" cellspacing="0">
                    <colgroup span ="2"></colgroup>
                    <colgroup width="210"></colgroup>
                    <colgroup width="210"></colgroup>
                    <tr>
                        <th colspan="2">団体等の名称</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="dantaiName" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">団体等の名称フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="dantaiNameKana" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">
                            所在地
                        </th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="yubinNo1" />-
                            <bean:write name="DntInfo" property="yubinNo2" /><br>
                            <bean:write name="DntInfo" property="dantaiJushoDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">担当者名</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiSei" />&nbsp;&nbsp;
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiMei" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">担当者名フリガナ</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiSeiKana" />&nbsp;&nbsp;
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaShimeiMeiKana" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">電話番号</th>
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
                        <th colspan="2">ＦＡＸ番号</th>
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
                        <th colspan="2">メールアドレス</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="dantaiJimuTantoshaMailAddress" />
                        </td>
                    </tr>
                    <tr>
                        <th  rowspan="2" colspan="2">受験申込者数</th>
                        <td class="basic" colspan="2">
                            医科　：&nbsp;&nbsp;<bean:write name="DntInfo" property="moshikomishaSuIka" />&nbsp;&nbsp;名
                        </td>
                    </tr>
                    <tr>
                        <td class="basic" colspan="2">
                            歯科　：&nbsp;&nbsp;<bean:write name="DntInfo" property="moshikomishaSuShika" />&nbsp;&nbsp;名
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">発送物の送付先</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="hassosakiKbnDisp" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2"><%=MiwConstants.JUKEN_CHARGE_NAME%>のお支払方法</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="kessaiHohoKbnDisp" />
                        </td>
                    </tr>

                    <tr>
                        <th colspan="2">団体パスワード<br>
                            <font size="-2" class="blue">（団体代表者が使用するパスワードです。）</font>
                        </th>
                        <td class="basic" colspan="2">
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">団体申込者登録用パスワード<br>
                            <font size="-2" class="blue">（団体申込者が使用するパスワードです。）</font>
                        </th>
                        <td class="basic" colspan="2">
                            （表示しません）
                        </td>
                    </tr>
                    <tr>
                        <th rowspan="2">パスワード<br/>確認用の<br/>質問・回答</th>
                        <th>質問</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="passwdQuestionCode1Disp" />
                        </td>
                    </tr>

                    <tr>
                        <th>回答</th>
                        <td class="basic" colspan="2">
                            <bean:write name="DntInfo" property="passwdAnswer1" />
                        </td>
                    </tr>
                    <tr>
                        <th colspan="2">通信欄</th>
                        <td class="basic" colspan="2">
                            <logic:empty name="DntInfo" property="moshikomiMemo">
                                -
                            </logic:empty>
                            <logic:notEmpty name="DntInfo" property="moshikomiMemo">
                                <bean:write name="DntInfo" property="moshikomiMemoDisp" filter="false" />
                            </logic:notEmpty>
                        </td>
                    </tr>

                </table>

                <br>
                <table class="table1" cellpadding="0" cellspacing="0">
                    <tr>
                        <td class="TDcenter">
                            <b><font size="+1">
                                間違いがなければ、「団体申込出願」ボタンをクリックしてください。
                                </font></b>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="table1" cellspacing="0" cellpadding="0">
                    <tr>
                        <td class="TDcenter">
                            <html:submit property="back" value="登録内容を修正する" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this);" style="width:200px"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <html:submit property="submit" value="団体申込出願" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this);" style="width:200px"/>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>