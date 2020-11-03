<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_InfMskList"/></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src="../../js/common.js"></script>
        <script type="text/javascript" src="../../js/msk.js"></script>
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <link href="../../css/msk.css" type="text/css" rel="stylesheet">
        <link href="../../css/manager.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="../../js/jquery-1.3.2.js"></script>
        <script type="text/javascript" src="../../js/jquery.updnWatermark.js"></script>
        <script type="text/javascript" src="../../js/jquery.exresize.0.1.0.js"></script>
        <script language=JavaScript  type="text/Javascript">
            <!--
            $(function(){ watermark() });
            
            //-->
        </script>
        <style type="text/css">
            .tablePageNo{width: 640px;}
            .tablePageNo font.link{font-size: 12px;}
            .tablePageNo font.nolink{font-size: 16px;font-weight: bold;}
            .tablePageNo font.click{font-size: 16px;}
            .tablePageNo font.noclick{color:#949494; font-size: 16px;}
            -->

        </style>
    </head>
    <body>
        <div id="container">
            <!--heder-->
            <div id="title_header"></div>
            <html:form action="/dantai/Inf/DntInfMskListAct" onsubmit="disableSubmit(this)">
                <html:hidden name="DntInfMskListFrm" property="sortFlg"/>
                <table class="tableNoFrame" cellpadding="0"  cellspacing="0">
                    <tr>
                        <td class="TDright TDmiddle">
                            <b><bean:write name="DntInfo" property="dantaiName" />代表者&nbsp;様のページ&nbsp;&nbsp;</b>
                        </td>
                        <td class="TDright TDmiddle" width="70">
                            <input type="button" value = "団体情報確認メニュー" onblur="offenter()" onfocus="onenter()" onclick="location.href='./DntInfMenu.do'"/>
                        </td>
                        <td class="TDright TDmiddle" width="70">
                            <input type="button" value = "ログアウト" onblur="offenter()" onfocus="onenter()" onclick="location.href='../../dantai/Inf/DntInfLogoutAct.do'"/>
                        </td>
                    </tr>
                </table>
                <br>
                <table class="title"  border="1" cellpadding="2" cellspacing="0" >
                    <tr>
                        <td style="background-color: #6495ed;width:5px;" cellpadding="0"></td>
                        <td class="right"><bean:message key="title.Dantai_InfMskList"/></td>
                    </tr>
                </table>
                <br/>
                <br>
                <logic:messagesPresent>
                    <table class="tableNoFrame" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="TDleft">
                                <font class="txtAttention">
                                <html:messages id="msg" message="false">
                                    <b>！</b><bean:write name="msg" filter="false" ignore="true" /><br>
                                </html:messages>
                                </font>
                            </td>
                        </tr>
                    </table>
                </logic:messagesPresent>
                <logic:notEmpty name="DntMskSearch" property="mskTorokuBeanList">
                    <table class="tableNoFrame" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="TDleft">
                                <bean:write name="DntMskSearch" property="cntDisp"/>件/<bean:write name="DntMskSearch" property="cntResuDisp"/>件中
                            </td>
                            <td class="TDright">
                                並び順：
                                <html:select name="DntMskSearch" property="sortCode" styleClass="ime_off" onchange="document.forms[0].sortFlg.value='1';document.forms[0].submit();">
                                    <html:optionsCollection name="sortCodes" value="value" label="label" />
                                </html:select>
                            </td>
                        </tr>
                    </table>
                </logic:notEmpty>
                <logic:notEmpty name="DntMskSearch" property="mskTorokuBeanList" >
                    <table class="tableResu" >
                        <tr>
                            <td width="10%"></td>
                            <td width="18%"></td>
                            <td width="18%"></td>
                            <td width="18%"></td> 
                            <td width="12%"></td> 
                            <td width="12%"></td>
                        </tr>
                        <tr>
                            <th class="TDcenter">ＩＤ</th><th class="TDcenter">氏名</th><th class="TDcenter">フリガナ</th>
                            <th class="TDcenter">ＴＥＬ</th><th class="TDcenter">受験科目</th>
                            <th class="TDcenter">受験<br>希望地</th>
                        </tr>
                        <logic:iterate name="DntMskSearch" property="mskTorokuBeanList" id="list" indexId="idx">
                            <tr>
                                <bean:define id="tetsudukiJokyoKbn" name="list" property="tetsudukiJokyoKbn" type="String"/>
                                <%
                                    String strIdx = idx.toString();
                                    String className = "";
                                    if (Integer.parseInt(strIdx) % 2 == 0) {
                                        className = "basic1";
                                    } else {
                                        className = "basic2";
                                    }
                                    if (MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI.equals(tetsudukiJokyoKbn)) {
                                        className = "cancel";                                            
                                    }
                                %>
                                <td class="<%=className%> TDcenter">
                                    <logic:equal name="list" property="tetsudukiJokyoKbn" value="<%=MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI%>">
                                        (取消済)<br>
                                    </logic:equal>
                                    <html:link action="/dantai/Inf/DntInfMskInfInitAct" transaction="true">
                                        <bean:define id="uketsukeNo" name="list" property="moshikomiUketsukeNo" type="String"/>
                                        <bean:define id="eventCode" name="list" property="eventCode" type="String"/>
                                        <bean:write name="list" property="userId"/>&nbsp;
                                        <html:param name="no" value="<%=uketsukeNo%>" />
                                        <html:param name="event" value="<%=eventCode%>" />
                                    </html:link>
                                </td>
                                <td class="<%=className%> TDcenter">
                                    <bean:write name="list" property="shimeiSei"/>　
                                    <bean:write name="list" property="shimeiMei"/> &nbsp;
                                </td>
                                <td class="<%=className%> TDcenter">
                                    <bean:write name="list" property="shimeiSeiKana"/>　
                                    <bean:write name="list" property="shimeiMeiKana"/> &nbsp;
                                </td>
                                <td class="<%=className%> TDcenter">
                                    <logic:notEmpty name="list" property="telNo">
                                        <bean:write name="list" property="telNo"/>&nbsp;
                                    </logic:notEmpty>
                                    <logic:empty name="list" property="telNo">
                                        <logic:notEmpty name="list" property="cellphoneNo">
                                            <bean:write name="list" property="cellphoneNo"/>&nbsp;
                                        </logic:notEmpty>
                                        <logic:empty name="list" property="cellphoneNo">
                                            -&nbsp;
                                        </logic:empty>
                                    </logic:empty>
                                </td>
                                <td class="<%=className%> TDcenter">
                                    <bean:write name="list" property="eventCodeDisp"/>&nbsp;
                                </td>
                                <td class="<%=className%> TDcenter">
                                    <logic:notEmpty name="list" property="shikenchiCode">
                                        <bean:write name="list" property="shikenchiCodeDisp"/>
                                    </logic:notEmpty>
                                    <logic:empty name="list" property="shikenchiCode">
                                        -&nbsp;
                                    </logic:empty>
                                </td>
                            </tr>
                        </logic:iterate>        
                    </table>
                    <table class="tablePageNo" cellspacing="0" cellpadding="0">
                        <tr>
                            <td height="10" colspan="3"></td>
                        </tr>
                        <tr>
                            <td class="TDcenter">
                                <logic:notEmpty  name="DntMskSearch" property="backPage">
                                    <html:link action="/dantai/Inf/DntInfMskListAct" transaction="true">
                                        <bean:define id="back" name="DntMskSearch" property="backPage" type="String"/>
                                        <font class="click">前ページへ</font><html:param name="page" value="<%=back%>" />
                                    </html:link>
                                </logic:notEmpty>
                                <logic:empty  name="DntMskSearch" property="backPage">
                                    <font class="noclick">前ページへ</font>
                                </logic:empty>
                                <font class="click">｜　</font>
                                <logic:equal name="DntMskSearch" property="isFirstPageInList" value="false">
                                    <html:link action="/dantai/Inf/DntInfMskListAct" transaction="true" >
                                        <font class="link">&nbsp;1&nbsp;</font><html:param name="page" value="1"/>
                                    </html:link>
                                    <logic:equal name="DntMskSearch" property="isListMinPage2" value="false">
                                        <font class="nolink" color="#239133">&nbsp;...&nbsp;</font>
                                    </logic:equal>
                                </logic:equal>
                                <logic:iterate id="pageList" name="DntMskSearch" property="pageList" indexId="listNo">
                                    <bean:define id="pageNo" name="pageList" type="String"/>
                                    <logic:equal name="DntMskSearch" property="nowPage" value="<%=pageNo%>">
                                        <font class="nolink">&nbsp;<%=pageNo%>&nbsp;</font>
                                    </logic:equal>
                                    <logic:notEqual name="DntMskSearch" property="nowPage" value="<%=pageNo%>">
                                        <html:link action="/dantai/Inf/DntInfMskListAct" transaction="true" >
                                            <font class="link">&nbsp;<%=pageNo%>&nbsp;</font><html:param name="page" value="<%=pageNo%>" />
                                        </html:link>
                                    </logic:notEqual>
                                </logic:iterate>
                                <logic:equal name="DntMskSearch" property="isLastPageInList" value="false">
                                    <font class="nolink" color="#239133">&nbsp;...&nbsp;</font>
                                </logic:equal>
                                <font class="click">　｜</font>
                                <logic:notEmpty  name="DntMskSearch" property="nextPage">
                                    <html:link action="/dantai/Inf/DntInfMskListAct" transaction="true">
                                        <bean:define id="next" name="DntMskSearch" property="nextPage" type="String"/>
                                        <font class="click">次ページへ</font><html:param name="page" value="<%=next%>" />
                                    </html:link>
                                </logic:notEmpty>
                                <logic:empty  name="DntMskSearch" property="nextPage">
                                    <font class="noclick">次ページへ</font>
                                </logic:empty>
                            </td>
                        </tr>
                    </table>
                </logic:notEmpty>
                <logic:empty name="DntMskSearch" property="mskTorokuBeanList" >
                    <table class="tableNoFrame">
                        <tr>
                            <td height="15"></td>
                        </tr>
                        <tr>
                            <td class="TDcenter"><font class="txtAttention">データが1件も存在しません。</font></td>
                        </tr>
                    </table>
                </logic:empty>

                <br>
                <table class="tableNoFrame">
                    <tr>
                        <td class="TDright">
                            <a href="#page_top">▲ページ先頭へ</a>
                        </td>
                    </tr>
                </table>
            </html:form>
        </div>
    </body>
</html:html>