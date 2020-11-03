<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head><meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title>試験地予定一覧</title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <script type="text/javascript" src='<%=request.getContextPath() + "/js/common.js"%>'></script>
        <link href='<%=request.getContextPath() + "/css/common.css"%>' type="text/css" rel="stylesheet">
        <link href='<%=request.getContextPath() + "/css/msk.css"%>' type="text/css" rel="stylesheet">
    </head>
    <body>
        <div id="container">
            <div align="center">
                <br>
                <table border="0" width="640">
                    <tr>
                        <td class="TDcenter ">
                            <logic:iterate id="kaijoJohoList" name="KaijoJoho" length="1">
                                <font size="+1"><b>第<bean:write name="kaijoJohoList" property="kaisu"/>回 診療報酬請求事務能力認定試験 試験地予定一覧</b></font><br><br>
                            </logic:iterate>
                        </td>
                    </tr>
                </table>
                <table class="tableMain" cellpadding="7" cellspacing="0" border="1">
                    <tr>
                        <th class="TDcenter" width="30%">試　験　地</th><th class="TDcenter" width="70%">会　　場　　名</th>
                    </tr>
                    <logic:iterate id="kaijoJohoList" name="KaijoJoho">
                        <tr>
                            <td class="TDcenter"><bean:write name="kaijoJohoList" property="shikenchiName"/></td>
                            <td class="TDcenter"><bean:write name="kaijoJohoList" property="kaijoName"/></td>
                        </tr>    
                    </logic:iterate>
                </table>
                <br>
                <table border="0">
                    <tr><td><font size="-1">注）</font></td><td><font size="-1">上記の会場は、現時点で予定されている会場となります。</font></td></tr>
                    <tr><td></td><td><font size="-1">最終的な試験会場は後日お送りする受験票にて必ずご確認願います。</font></td></tr>    
                </table>    
                <br/>
                <br/>
                <table cellspacing="0" cellpadding="3" width="400" >
                    <tr>
                        <td>
                            <div align="center">
                                <button onclick="window.close('');">　閉じる　</button>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html:html>

