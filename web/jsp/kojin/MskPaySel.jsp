<%@ page contentType="text/html; charset=Windows-31J"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.MskPaySel" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <script type="text/javascript">
        </script>
    </head>
    <body>
        <div id="container">
            <div align="center">
                <html:form action="/kojin/Msk/MskPaySelAct"onsubmit="disableSubmit(this)">
                    <!--heder-->
                    <div id="title_header"></div>
                    <table class="header" cellpadding="0" cellspacing="0">
                        <tr>
                            <td><img src="../../image/nv_msk_k7.gif" alt="試験申込フロー"></td>
                        </tr>
                    </table>
                    <table class="title"  border="1" cellpadding="2" cellspacing="0">
                        <tr>
                            <td class="left" cellpadding="0"></td>
                            <td class="right">７．決済方法選択</td>
                        </tr>
                    </table>
                    <br>
                    <br>
                    <table class="table1" cellspacing="0" cellpadding="4" >
                       <tr>
                            <td>
                            クレジットカード決済、コンビニ決済、ペイジー決済よりお選びいただけます。<br>
                            決済方法のいずれかを選択し、「次へ」ボタンを押してください。<br>
                            申込受付後に決済方法を変更することはできませんので予めご了承ください。
                            </td>
                        </tr>
                        <logic:messagesPresent>
                            <tr>
                                <td> 
                                    <html:errors />
                                </td>
                            </tr>
                        </logic:messagesPresent>
                    </table>
                    <table class="tableMain" cellspacing="0" cellpadding="10">
                        <tr>
                            <td width="34%"></td>
                            <td width="66%"></td>
                        </tr>
                        <tr>
                            <th>決済方法</th>
                            <td class="<bean:write name="MskTorokuJoho" property="validateCss(0)"/>">
                                <html:radio name="MskTorokuJoho" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_CRD%>" style="ime-mode:disabled" />クレジットカード決済<br>
                                <br>
                                <html:radio name="MskTorokuJoho" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_CVS%>" style="ime-mode:disabled" />コンビニ決済<br>
                                <br>
                                <html:radio name="MskTorokuJoho" property="kessaiHohoKbn" value="<%=MiwConstants.KESSAI_HOHO_KBN_PAYEASY%>" style="ime-mode:disabled" />ペイジー決済
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                    </tr>
                </table>
                <br>
                    <table class="table1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td class="TDcenter">
                                <html:submit property="back" value="戻　る" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <html:submit property="submit" value="次　へ" style="width:100px" onblur="offenter()" onfocus="onenter()" onclick="setHiddenValue(this)" />
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
                </html:form>
            </div>
        </div>
    </body>
</html:html>