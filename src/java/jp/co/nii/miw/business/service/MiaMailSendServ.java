package jp.co.nii.miw.business.service;

import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.business.Email;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>タイトル: 変更受付メール</p>
 * <p>説明: 変更受付メールを送信する</p>
 * <p>著作権: Copyright (c) 2006</p>
 * <p>会社名: 日本情報産業株式会社</p>
 */
public final class MiaMailSendServ {
    //業務コード

    private static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    //Mail関連プロパティ
    private static final String MAIL_SERVER = PropertyUtility.getProperty(BUSINESS_CODE + "mail_server");
    private static final String MAIL_SERVER_BACKUP = PropertyUtility.getProperty(BUSINESS_CODE + "mail_server_backup");
    private static final String MAIL_FROM = PropertyUtility.getProperty(BUSINESS_CODE + "mail_from");
    private static final String MAIL_REPLY = PropertyUtility.getProperty(BUSINESS_CODE + "mail_reply");
    private static final String MAIL_TEL = PropertyUtility.getProperty(BUSINESS_CODE + "mail_tel");
    private static final String MAIL_FAX = PropertyUtility.getProperty(BUSINESS_CODE + "mail_fax");

    /**
     * 付完了メールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param registrantafter 変更後データ<BR>
     * @return boolean true  変更受付メール送信成功<BR>
     *                 false 変更受付メール送信失敗<BR>
     */
    public boolean sendIdShutokuResuMail(JznToroku jznTorokuJoho) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    jznTorokuJoho.getMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込用ID発行のお知らせ",
                    //メール本文
                    new MiaMailContets().getCntIdShutokuResu(jznTorokuJoho),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * 団体登録・出願完了メールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param registrantafter 変更後データ<BR>
     * @return boolean true  変更受付メール送信成功<BR>
     *                 false 変更受付メール送信失敗<BR>
     */
    public boolean sendDntResuMail(DntInf dntinfo) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    dntinfo.getDantaiJimuTantoshaMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　団体申込出願受付のお知らせ",
                    //メール本文
                    new MiaMailContets().getCntDntResu(dntinfo),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * 団体出願完了メールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param registrantafter 変更後データ<BR>
     * @return boolean true  変更受付メール送信成功<BR>
     *                 false 変更受付メール送信失敗<BR>
     */
    public boolean sendDntMskResuMail(DntInf dntinfo) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    dntinfo.getDantaiJimuTantoshaMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　団体申込出願受付のお知らせ",
                    //メール本文
                    new MiaMailContets().getCntDntMskResu(dntinfo),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * 団体申込完了メールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param registrantafter 変更後データ<BR>
     * @return boolean true  変更受付メール送信成功<BR>
     *                 false 変更受付メール送信失敗<BR>
     */
    public boolean sendMskResuMail(MskToroku mskTorokuJoho) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    mskTorokuJoho.getMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　団体申込者登録受付のお知らせ",
                    //メール本文
                    new MiaMailContets().getCntMskResu(mskTorokuJoho),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * 申込完了時のメールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param mskToroku 申込情報<BR>
     * @return boolean true  メール送信成功<BR>
     *                 false  メール送信失敗<BR>
     */
    public boolean sendShutsuganResuMail(MskToroku mskTorokuJoho) {

        boolean status = false;
        try {
            String title = "";
            String text = "";

            if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
                //カード決済
                title = "診療報酬請求事務能力認定試験インターネット申込　申込完了のお知らせ ";
                text = new MiaMailContets().getShutsuganResu(mskTorokuJoho);
            } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
                //ペイジー決済
                title = "診療報酬請求事務能力認定試験インターネット申込　申込受付のお知らせ";
                text = new MiaMailContets().getShutsuganPayeasyResu(mskTorokuJoho);
            } else {
                //コンビニ決済
                title = "診療報酬請求事務能力認定試験インターネット申込　申込受付のお知らせ";
                text = new MiaMailContets().getShutsuganCvsResu(mskTorokuJoho);
            }

            //冗長化されたメールサーバ経由のメール送信
            new Email(MAIL_SERVER, MAIL_SERVER_BACKUP, MAIL_FROM).sendViaRedundantServer(
                    //to
                    mskTorokuJoho.getMailAddress(),
                    //subject
                    title,
                    //メール本文
                    text,
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * 申込（コンビニ・ペイジー入金）完了時のメールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param moshikomi 申込情報<BR>
     * @return boolean true  メール送信成功<BR>
     *                 false  メール送信失敗<BR>
     */
    public boolean sendShutsuganResuMailKessai(Moshikomi boMoshikomi, Moshikomisha boMoshikomisha) {

        boolean status = false;
        try {
            String text = "";

            text = new MiaMailContets().getShutsuganResuKessai(boMoshikomi, boMoshikomisha);

            //冗長化されたメールサーバ経由のメール送信
            new Email(MAIL_SERVER, MAIL_SERVER_BACKUP, MAIL_FROM).sendViaRedundantServer(
                    //to
                    boMoshikomisha.getMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　申込完了のお知らせ ",
                    //メール本文
                    text,
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * キャンセル時のメールを送信するメソッド<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param moshikomi 申込情報<BR>
     * @return boolean true  メール送信成功<BR>
     *                 false  メール送信失敗<BR>
     */
    public boolean sendShutsuganCancel(Moshikomi boMoshikomi, Moshikomisha boMoshikomisha) {

        boolean status = false;
        try {
            String text = "";

            text = new MiaMailContets().getShutsuganCancel(boMoshikomi, boMoshikomisha);

            //決済方法
            String kessaiHohoKbnName = CodeValueName.getKessaiHohoKbnName(boMoshikomi.getKessaiHohoKbn());

            //冗長化されたメールサーバ経由のメール送信
            new Email(MAIL_SERVER, MAIL_SERVER_BACKUP, MAIL_FROM).sendViaRedundantServer(
                    //to
                    MiwConstants.TANTOSHA_MAIL_ADDRESS,
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　" + kessaiHohoKbnName + "キャンセルのお知らせ ",
                    //メール本文
                    text,
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * パスワード記載メールを送信するメソッド（マイページ）<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param moshikomisha 申込者データ<BR>
     * @return boolean true  メール送信成功<BR>
     *                 false メール送信失敗<BR>
     */
    public boolean sendPasswordTsuchiMail(Moshikomisha moshikomisha) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    moshikomisha.getMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　パスワード通知",
                    //メール本文
                    new MiaMailContets().getPasswordTsuchiContent(moshikomisha),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }

    /**
     * パスワード記載メールを送信するメソッド（団体情報）<BR>
     * メール本文編集をする<BR>
     * メール本文内容に関しては別クラスで取得<BR>
     * 冗長化されたメールサーバ経由のメール送信を行う<BR>
     * @param dantai 団体データ<BR>
     * @return boolean true  メール送信成功<BR>
     *                 false メール送信失敗<BR>
     */
    public boolean sendPasswordTsuchiMail(Dantai dantai) {

        boolean status = false;
        try {
            //冗長化されたメールサーバ経由のメール送信
            new Email(
                    MAIL_SERVER,
                    MAIL_SERVER_BACKUP,
                    MAIL_FROM).sendViaRedundantServer(
                    //to
                    dantai.getDantaiJimuTantoshaMailAddress(),
                    //subject
                    "診療報酬請求事務能力認定試験インターネット申込　パスワード通知",
                    //メール本文
                    new MiaMailContets().getPasswordTsuchiContent(dantai),
                    //reply-to
                    MAIL_REPLY);

            status = true;

        } catch (Exception ex) {
            status = false;

        } finally {
            return status;
        }

    }
}
