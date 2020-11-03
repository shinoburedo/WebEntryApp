<%@ page contentType="text/html; charset=Windows-31J" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested"%>
<jsp:useBean id="MiwConstants" class="jp.co.nii.miw.business.MiwConstants" scope="request" />
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=Shift_JIS">
        <title><bean:message key="title.Dantai_MskEnd"/></title>
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
                    <td><img src="../../image/nv_re_dnt5.gif" alt="団体申込出願フロー"></td>
                </tr>
            </table>
            <br>
            <table class="title"  border="1" cellpadding="2" cellspacing="0">
                <tr>
                    <td class="left" cellpadding="0"></td>
                    <td class="right">５．<bean:message key="title.Dantai_MskEnd"/></td>
                </tr>
            </table>
            <br>
            <br>
            <table class="table1" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        診療報酬請求事務能力認定試験 団体申込出願の手続きをしていただき誠にありがとうございます。<br/>
                        <br/>
                        団体申込出願が完了しましたので、登録されたメールアドレスに団体申込者登録用ID通知メールを送信しました。<br/>
                        通知メールにはインターネット申込に必要な「団体コード」と「団体申込者登録用ID」が記載されています。<br/><br/>
                        <b>「団体申込者登録用ID」</b>と<b>「団体申込者登録用パスワード」</b>は、団体代表者又は団体申込者が、団体申込者登録をする際、ログインするために使用します。<br>
                        <br/>
                        団体申込者登録を各受験者が行う場合、団体代表者の方は<b>「団体申込者登録用ID」</b>と<b>「団体申込者登録用パスワード」</b>を各受験者へ通知してください。<br>
                        その際、<u><b>「団体コード」</b>と<b>「団体パスワード」</b>は団体代表者用の情報となりますので、各受験者へ通知しないようにご注意ください。</u><br>
                <br>
                団体コード、団体申込者登録用ID情報は必ず保存や記録を行ってください。<br/>
                特に<b>「団体コード」</b>と<b>「団体パスワード」</b>は、次回以降の認定試験インターネット申込の際にも使用しますのでご留意ください。<br/>
                <br/>
                <table class="table1" border="solid" cellpadding="10">
                    <tr>
                        <td width="40%" bgcolor="#EEEEEE">
                            <b>団体コード、<br>団体パスワード</b>
                        </td>
                        <td width="60%">
                            <b>【使用方法】</b><br/>
                            ・「団体情報確認」でのログイン時に入力<br>
                            ・団体代表者が保管<br/>
                            ・次回以降の認定試験申込時にも使用<br/><br/>
                            <br/>
                        </td>
                    </tr>
                    <tr>
                        <td width="40%" bgcolor="#EEEEEE">
                            <b>団体申込者登録用ID、<br>団体申込者登録用パスワード</b>
                        </td>
                        <td width="60%">
                            <b>【使用方法】</b><br/>
                            ・「団体申込者登録」でのログイン時に入力<br>
                            ・団体代表者が保管、団体の各受験者にも通知<br/>
                            ・今回の試験申込に限り有効<br/><br/>
                            <br/>
                        </td>
                    </tr>
                </table>
                <br>
                通知メールは自動的に送信していますので、通常は登録直後に受信できます。<br/>
                １０&#x301C;１５分待っても受信できない場合は、メールアドレスの誤り等と考えられます。お手数ですが、もう一度最初から団体申込出願を行ってください。</br>
                </td>
                </tr>
            </table>
            <br>
            <table class="table1" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="TDcenter">
                        <input type="button" value="メインメニューに戻る" onclick="location.href='../MenuDaihyoshaAct.do'" style="width:150px"/>
                    </td>
                </tr>
            </table>
            <br>
        </div>
    </body>
</html:html>