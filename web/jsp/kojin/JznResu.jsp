<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Kojin_JznResu" /></title>
        <meta http-equiv="Content-Script-Type" content="text/javascript">
        <meta http-equiv="Content-Style-Type" content="text/css">
        <link href="../../css/jzn.css" type="text/css" rel="stylesheet">
        <link href="../../css/common.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="../../js/jzn.js"/>
        <script type="text/javascript" src="../../js/common.js"></script>
        <script language="JavaScript" type="text/JavaScript"></script>
    </head>
    <body>
        <div id="container">
            <div id="title_header"></div>
            <div align="center">
            <table class="header" cellpadding="0" cellspacing="0">
                <tr>
                    <td><img src="../../image/nv_jzn4.gif" alt="ＩＤ取得フロー"></td>
                </tr>
            </table>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right">４．ＩＤ取得完了</td>
                </tr>
            </table>
            <br>
            <table class="table1" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        認定試験ＩＤの取得手続きをしていただき誠にありがとうございます。<br/>
                        <br/>
                        ＩＤ取得が完了しましたので、登録されたメールアドレスにＩＤ通知メールを送信しました。<br/>
                        通知メールにはインターネット申込に必要な「ＩＤ」が記載されています。<br/>
                        ＩＤ通知メールの再送信や、メール・お電話等によるＩＤの照会は承っておりませんので、ＩＤ情報は必ず保存や記録を行ってください。<br/>
                        <br/>
                        通知メールは自動的に送信していますので、通常は登録直後に受信できます。<br/>
                        １０～１５分待っても受信できない場合は、メールアドレスの誤り等と考えられます。お手数ですが、もう一度最初からＩＤ取得を行ってください。</br>
                    </td>
                </tr>
            </table>
            <br>
            <table class="table1" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="TDcenter">
                        <input type="button" value="メインメニューに戻る" onclick="location.href='../MenuAct.do'" style="width:150px"/>
                    </td>
                </tr>
            </table>
            <br>
            </div>
        </div>
    </body>
</html:html>