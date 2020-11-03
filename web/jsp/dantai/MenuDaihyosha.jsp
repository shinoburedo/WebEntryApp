<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_DaihyoshaMenu"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <script type="text/javascript" src='<%=request.getContextPath() + "/js/common.js"%>'></script>
        <script type="text/javascript" src='<%=request.getContextPath() + "/js/msk.js"%>'></script>
        <link href='<%=request.getContextPath() + "/css/common.css"%>' type="text/css" rel=stylesheet>
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right"><bean:message key="title.Dantai_DaihyoshaMenu"/></td>
                </tr>
            </table>
            <br/>
            <br/>
            <table class="table1">
                <tr>
                    <td>
                        ボタンをクリックしてご希望のページにお進みください。<br>
                        団体受験のお申込みの詳細については、 <a href='http://www.shaho.co.jp/iryojimu/nintei/opem_daihyou.pdf' target="_blank">「団体申込マニュアル」</a>をご覧ください。
                    </td>
                </tr>
            </table>
            <br/>
            <table class="table1">
                <tr>
                    <td width="25%"></td>
                    <td width="3%"></td>
                    <td width="34%"></td>
                    <td width="3%"></td>
                    <td width="35%"></td>
                </tr>

                <tr>
                    <td>
                        <br/>
                <center>
                    <logic:equal name="MenuJoho" property="dntGetKigenFlg" value="1">
                        <a href='<%=request.getContextPath() + "/html/dantai/DantaiTourokuTop.html"%>'>
                            <img src='<%=request.getContextPath() + "/image/dantai_cod_mi.gif"%>' alt="団体コード未取得" width="208" height="53" border="0" />
                        </a>
                    </logic:equal>
                    <logic:notEqual name="MenuJoho" property="dntGetKigenFlg" value="1">
                        <img src='<%=request.getContextPath() + "/image/dantai_cod_mi_off.gif"%>' alt="団体コード未取得" width="208" height="53" border="0" />
                    </logic:notEqual>
                </center>
                </td>
                <td></td>
                <td>
                    ・団体情報の登録<br>・団体申込出願
                </td>
                <td></td>
                <td>
                    団体申込出願期限 <br><font class="txtAttention"><bean:write name="MenuJoho" property="dntGetKigenDisp" /></font> まで
                </td>
                </tr>
                <tr>
                    <td colspan="5">
                        <font class="blue" size="-1">&nbsp;&nbsp;&nbsp;&nbsp;※初めて認定試験インターネット申込を行う団体の方は、こちらを選択してください。</font>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <!--                <tr>
                                    <td colspan="5"><hr></td>
                                </tr>-->
                <tr>
                    <td>
                        <br/>
                <center>
                    <logic:equal name="MenuJoho" property="dntMskKigenFlg" value="1">
                        <a href='<%=request.getContextPath() + "/html/dantai/DantaiMskLogin.html"%>'>
                            <img src='<%=request.getContextPath() + "/image/dantai_cod_sumi.gif"%>' alt="団体コード取得済" width="208" height="53" border="0" />
                        </a>
                    </logic:equal>
                    <logic:notEqual name="MenuJoho" property="dntMskKigenFlg" value="1">
                        <img src='<%=request.getContextPath() + "/image/dantai_cod_off.gif"%>' alt="団体コード取得済" width="208" height="53" border="0" />
                    </logic:notEqual>
                </center>
                </td>
                <td></td>
                <td>
                    ・団体申込出願
                </td>
                <td></td>
                <td>
                    団体申込出願期限 <br><font class="txtAttention"><bean:write name="MenuJoho" property="dntMskKigenDisp" /></font> まで
                </td>
                </tr>
                <tr>
                    <td colspan="5">
                        <font class="blue" size="-1">&nbsp;&nbsp;&nbsp;&nbsp;※過去に認定試験インターネット申込を行った事のある団体の方は、こちらを選択してください。</font>
                    </td>
                </tr>
                <tr>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="5"><hr></td>
                </tr>
                <tr>
                    <td>
                        <br/>                    
                <center>
                    <logic:equal name="MenuJoho" property="dntLoginKigenFlg" value= "0">
                        <img src='<%=request.getContextPath() + "/image/dantai_kakunin_off.gif"%>' alt="団体情報確認" width="208" height="53" border="0" />
                    </logic:equal>
                    <logic:equal name="MenuJoho" property="dntLoginKigenFlg" value = "1">
                        <a href='<%=request.getContextPath() + "/html/dantai/DantaiInfLogin.html"%>'>
                            <img src='<%=request.getContextPath() + "/image/dantai_kakunin.gif"%>' alt="団体情報確認" width="208" height="53" border="0" />
                        </a>
                    </logic:equal>
                </center>
                <br/>
                </td>
                <td></td>
                <td>
                    ・団体登録内容の確認・変更
                </td>
                <td></td>
                <td>
                    確認期限：<font class="txtAttention"><bean:write name="MenuJoho" property="dntCnfKigenDisp" /></font> まで<br>
                    変更期限：<font class="txtAttention"><bean:write name="MenuJoho" property="dntUpdKigenDisp" /></font> まで
                </td>
                </tr>
                <tr>
                    <td colspan="5"><hr></td>
                </tr>
                <tr>
                    <td>
                        <br/>                    
                <center>
                    <logic:equal name="MenuJoho" property="mskKigenFlg" value= "0">
                        <img src='<%=request.getContextPath() + "/image/dantai_moshikomi_off.gif"%>' alt="団体申込者登録" width="208" height="53" border="0" >
                    </logic:equal>
                    <logic:equal name="MenuJoho" property="mskKigenFlg" value = "1">
                        <a href='<%=request.getContextPath() + "/html/dantai/MskLogin_daihyo.html"%>'>
                            <img src='<%=request.getContextPath() + "/image/dantai_moshikomi.gif"%>' alt="団体申込者登録" width="208" height="53" border="0" >
                        </a>
                    </logic:equal>
                </center>
                <br/>
                </td>
                <td></td>
                <td>
                    ・団体申込で受験される方の情報登録
                </td>
                <td></td>
                <td>
                    申込期限
                    <font class="txtAttention"><bean:write name="MenuJoho" property="mskKigenDisp" /></font>&nbsp;まで
                </td>
                </tr>
            </table>
            <br/>
            <br/>
            <br>

            <table class="table1" cellspacing="0" cellpadding="0">
                <tbody>
                    <tr>
                        <td class="TDcenter">
                            <input type="button" value="団体申込トップページに戻る" onblur="offenter()" onfocus="onenter()" onclick="location.href='<%=request.getContextPath() + "/html/dantai/DantaiTop.html"%>'" style="width:200px"/>
                        </td>
                    </tr>
                </tbody>
            </table>
            <br>
            <table class="table1" cellspacing="0" cellpadding="0" border="0">
                <tbody>
                    <tr>
                        <td class="TDcenter">
                            <IFRAME src='<%=request.getContextPath() + "/html/callcenter.html"%>'  class="iframeCall">
                            </IFRAME>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

    </body>
</html:html>