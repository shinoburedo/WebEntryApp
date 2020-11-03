package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.PropertyUtility;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.StringUtility;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * <p>タイトル: Email</p>
 * <p>説明: メール関連の処理を提供する。</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author N.Machida-ixapより移植、メールサーバ冗長化対応した
 */
public class Email {

    /**業務コード */
    private static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    private static String smtp_server;
    private static String smtp_server_backup;
    private static String smtp_port;
//    private static String pop3_server;
    private static String pop3_user;
    private static String pop3_password;
    private static String from;
    private static final String SMTP_CHARSET = "ISO-2022-JP";
    private static final int pop3_port = 110;
    //メール送信元日本語名(全機能共通)
    private static final String from_name_jp = PropertyUtility.getProperty(BUSINESS_CODE + "mail_fromname");

    /**
     * コンストラクタ<br>
     * メールサーバを使用しないとき
     */
    public Email() {
    }

    /**
     * コンストラクタ　メールサーバシングル構成版<br>
     * メールサーバがシングル構成のときのコンストラクタ<br>
     * @param _smtp_server
     * @param _from
     */
    public Email(String _smtp_server, String _from) throws Exception {
        smtp_server = _smtp_server;
        smtp_server_backup = null;
        from = _from;
        Store store = null;
        try {

//            pop3_server = PropertyUtility.getProperty(BUSINESS_CODE + "mail_server_pop3");
            pop3_user = PropertyUtility.getProperty(BUSINESS_CODE + "mail_user");
            pop3_password = PropertyUtility.getProperty(BUSINESS_CODE + "mail_pass");
            Properties props = new Properties();
            props.put("mail.smtp.host", smtp_server);

            Session sess = Session.getInstance(props, null);
            store = sess.getStore("pop3");
            store.connect(smtp_server, pop3_port, pop3_user, pop3_password);
//            store.connect(pop3_server, pop3_port, pop3_user, pop3_password);
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (store != null) {
                store.close();
            }
        }

    }

    /**
     * コンストラクタ　メールサーバ冗長化版<br>
     * メールサーバが冗長化されているときのコンストラクタ<br>
     * RFC5322に準拠した仕様ではありません
     * （ドット二つが連続したアカウント名でもOKとなる、ダブルコーテーションが使えない、等）
     * @param _smtp_server
     * @param _smtp_server_backup
     * @param _from
     */
    public Email(String _smtp_server, String _smtp_server_backup, String _from)
            throws Exception {
        smtp_server = _smtp_server;
        smtp_server_backup = _smtp_server_backup;
        from = _from;
//        pop3_server = PropertyUtility.getProperty(BUSINESS_CODE + "mail_server_pop3");
        pop3_user = PropertyUtility.getProperty(BUSINESS_CODE + "mail_user");
        pop3_password = PropertyUtility.getProperty(BUSINESS_CODE + "mail_pass");
    }

    /**
     * Emailの形式か否かをチェック
     * @param str String 検査対象の文字列
     * @return boolean
     * true  Emailの形式
     * false Emailの形式ではない
     */
    public final boolean isEmail(final String str) {

        boolean ret = true;
        int atCount = 0;

        for (int i = 0; i < str.length(); i++) {

            if ((i == 0) || (i == (str.length() - 1))) {

                // 文字チェック（文字列の最初または最後の場合）
                if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9')
                        || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                        || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                        || (str.charAt(i) == '-')
                        || (str.charAt(i) == '_'))) {
                    ret = false;
                    break;
                }

            } else {

                // 文字チェック（最初または最後以外の場合）
                if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9')
                        || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
                        || (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
                        || (str.charAt(i) == '-')
                        || (str.charAt(i) == '_')
                        || (str.charAt(i) == '@')
                        || (str.charAt(i) == '.'))) {
                    ret = false;
                    break;
                }

                // @の有無チェック
                if (str.charAt(i) == '@') {
                    atCount++;
                }
            }
        }

        // @が1つあるか
        if (atCount != 1) {
            ret = false;
        } else {

            //@以下に１つ以上.があるか
            int atmarkIdx = str.indexOf("@") + 1;
            String domain = str.substring(atmarkIdx); //"@"以降を取得
            boolean atmarkChk = false;
            for (int i = 0; i < domain.length(); i++) {
                if (domain.charAt(i) == '.') {
                    atmarkChk = true;
                }
            }

            //@以下に1つ以上.がない場合
            if (!atmarkChk) {
                ret = false;
//                } else {

                //2009.5.7 トップレベルドメインチェック削除
//                    int topLvlDomIdx = str.lastIndexOf(".") + 1;
//                    String topLvlDom = str.substring(topLvlDomIdx);

//                    final int TOP_LVL_DOM_LEN = topLvlDom.length();

                //トップレベルドメインが2文字か3文字になっているか
//                    if (!((TOP_LVL_DOM_LEN == 2) || (TOP_LVL_DOM_LEN == 3))) {
//                        ret = false;
//                    }
            }
        }
        return ret;
    }

    /**
     * Emailの形式か否かをチェック
     * @param str String 検査対象の文字列
     * @return boolean
     * true  Emailの形式
     * false Emailの形式ではない
     */
    public final boolean isEmail2(final String str) {

        try {
            InternetAddress iaddr = new InternetAddress(str);
        } catch (AddressException e) {
            return false;
        }
        return true;

    }

    /**
     * 携帯のメールアドレスか否かをチェック
     * @param str 検査対象の文字列
     * @return boolean 検査結果
     * true  携帯のアドレス。既知の携帯キャリアのドメインで終わっている
     * false 携帯のアドレスではない。既知の携帯キャリアのドメインで終わっていない
     */
    public final boolean isMobilePhoneAddress(final String str) {

        boolean state = false;

        //携帯のアドレスのときtrueを返却
        if ((str.endsWith("docomo.ne.jp"))
                || (str.endsWith("docomo-camera.ne.jp"))
                || (str.endsWith("ebilling.ne.jp"))
                || (str.endsWith("docomo-bill.ne.jp"))
                || (str.endsWith("mail.visualnet.mopera.ne.jp"))
                || (str.endsWith("d.vodafone.ne.jp"))
                || (str.endsWith("h.vodafone.ne.jp"))
                || (str.endsWith("t.vodafone.ne.jp"))
                || (str.endsWith("c.vodafone.ne.jp"))
                || (str.endsWith("k.vodafone.ne.jp"))
                || (str.endsWith("r.vodafone.ne.jp"))
                || (str.endsWith("n.vodafone.ne.jp"))
                || (str.endsWith("s.vodafone.ne.jp"))
                || (str.endsWith("q.vodafone.ne.jp"))
                || (str.endsWith("jp-d.ne.jp"))
                || (str.endsWith("jp-h.ne.jp"))
                || (str.endsWith("jp-t.ne.jp"))
                || (str.endsWith("jp-c.ne.jp"))
                || (str.endsWith("jp-r.ne.jp"))
                || (str.endsWith("jp-k.ne.jp"))
                || (str.endsWith("jp-n.ne.jp"))
                || (str.endsWith("jp-s.ne.jp"))
                || (str.endsWith("jp-q.ne.jp"))
                || (str.endsWith("ezweb.ne.jp"))
                || (str.endsWith("ido.ne.jp"))
                || (str.endsWith("sky.tkk.ne.jp"))
                || (str.endsWith("sky.tkc.ne.jp"))
                || (str.endsWith("sky.tu-ka.ne.jp"))
                || (str.endsWith("pdx.ne.jp"))) {

            state = true;
        }

        return state;
    }

    /**
     * 冗長化されたメールサーバ経由のメール送信<BR>
     * 送信先メールアドレス、件名、本文、返信先メールアドレスを受け取りメールを送信する。<BR>
     * メインのメールサーバへ送信を行い、失敗したときはバックアップのメールサーバへ送信を行う。<BR>
     * Subject と本文は－,～が文字化けしないように変換
     * @param _to String   From 送信先メールアドレス
     * @param _subject String   Subject 件名
     * @param _content String  Content 本文
     * @param _replyto String   Reply-To 返信先メールアドレス
     */
    public void sendViaRedundantServer(
            String _to,
            String _subject,
            String _content,
            String _replyto)
            throws Exception {

        try {
            this.send(_to, _subject, _content, _replyto, smtp_server);
            // Mail_1

        } catch (Exception ex1) {
            LogGenerate.errorOutput(_to + " へのメインメールサーバ経由の送信が失敗しました。");
            try {
                if (smtp_server_backup != null) {
                    this.send(
                            _to,
                            _subject,
                            _content,
                            _replyto,
                            smtp_server_backup);
                    // Mail_2
                    LogGenerate.infoOutput(
                            _to + " へのバックアップメールサーバ経由の送信が成功しました。");
                } else {
                    throw ex1;
                }
            } catch (Exception ex2) {
                LogGenerate.errorOutput(
                        _to + " へのバックアップメールサーバ経由の送信が失敗しました。");
                throw ex2;
            }
        }
    }

    /**
     * メール送信<BR>
     * 送信先メールアドレス、件名、本文、返信先メールアドレスを受け取りメールを送信する。<BR>
     * @param _to String 送信先メールアドレス
     * @param _subject String 件名
     * @param _content String 本文
     * @param _replyto String 返信先メールアドレス
     * @param _smtpserver String 使用メールサーバ
     */
    public void sendPbs(
            String _to,
            String _subject,
            String _content,
            String _replyto,
            String _smtpserver)
            throws Exception {

        LogGenerate.debugOutput("smtp_server:" + _smtpserver);
        LogGenerate.debugOutput("from :" + from);
        LogGenerate.debugOutput("reply-to:" + _replyto);
        LogGenerate.debugOutput("to:" + _to);
        LogGenerate.debugOutput("subject:" + _subject);
        LogGenerate.debugOutput("contents:" + _content);
        Store store = null;
        try {
            //  初期設定
            Properties props = new Properties();
            props.put("mail.smtp.host", _smtpserver);
            Session sess = Session.getInstance(props, null);

            store = sess.getStore("pop3");
//            store.connect(pop3_server, pop3_port, pop3_user, pop3_password);
            store.connect(smtp_server, pop3_port, pop3_user, pop3_password);

            MimeMessage msg = new MimeMessage(sess);

            // 送信元指定
            msg.setFrom(new InternetAddress(from));
            // 返信先指定
            msg.setHeader("Reply-To", _replyto);
            // 送信先指定
            InternetAddress[] toAddress = {new InternetAddress(_to)};
            msg.setRecipients(Message.RecipientType.TO, toAddress);

            // 送信日付指定
            msg.setSentDate(new Date());

            //  Subject と本文設定 －,～対応
            msg.setSubject(
                    StringUtility.convertHyphenEuc(_subject),
                    SMTP_CHARSET);
            msg.setText(
                    StringUtility.convertHyphenEuc(_content),
                    SMTP_CHARSET);

            //  送信
            Transport.send(msg);
            LogGenerate.debugOutput("メールの送信が完了しました。");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (store != null) {
                //Storeのクローズ
                store.close();
            }

        }

    }

    /**
     * メール送信<BR>
     * 送信先メールアドレス、件名、本文、返信先メールアドレスを受け取りメールを送信する。<BR>
     * @param _to String 送信先メールアドレス
     * @param _subject String 件名
     * @param _content String 本文
     * @param _replyto String 返信先メールアドレス
     * @param _smtpserver String 使用メールサーバ
     */
    private void send(
            String _to,
            String _subject,
            String _content,
            String _replyto,
            String _smtpserver)
            throws Exception {

        LogGenerate.debugOutput("smtp_server:" + _smtpserver);
        LogGenerate.debugOutput("from :" + from);
        LogGenerate.debugOutput("reply-to:" + _replyto);
        LogGenerate.debugOutput("to:" + _to);
        LogGenerate.debugOutput("subject:" + _subject);
        LogGenerate.debugOutput("contents:" + _content);
        Store store = null;
        try {
            //  初期設定
            Properties props = new Properties();
            props.put("mail.smtp.host", _smtpserver);
            Session sess = Session.getInstance(props, null);

            store = sess.getStore("pop3");
//            store.connect(pop3_server, pop3_port, pop3_user, pop3_password);
            store.connect(_smtpserver, pop3_port, pop3_user, pop3_password);

            MimeMessage msg = new MimeMessage(sess);

            // 送信元指定(アドレス、日本語名)
            msg.setFrom(new InternetAddress(from, from_name_jp, SMTP_CHARSET));
            // 返信先指定
            msg.setHeader("Reply-To", _replyto);
            // 送信先指定
            InternetAddress[] toAddress = {new InternetAddress(_to)};
            msg.setRecipients(Message.RecipientType.TO, toAddress);

            // 送信日付指定
            msg.setSentDate(new Date());

            //  Subject と本文設定 －,～対応
            msg.setSubject(
                    StringUtility.convertHyphenEuc(_subject),
                    SMTP_CHARSET);
            msg.setText(
                    StringUtility.convertHyphenEuc(_content),
                    SMTP_CHARSET);

            //  送信
            Transport.send(msg);
            LogGenerate.debugOutput("メールの送信が完了しました。");

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (store != null) {
                //Storeのクローズ
                store.close();
            }

        }

    }
}