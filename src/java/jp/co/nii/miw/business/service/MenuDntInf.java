package jp.co.nii.miw.business.service;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.utility.DateTimeUtility;

/**
 * タイトル: 団体メニュー
 * 説明: 団体メニューデータbean
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 * @author r.ehara
 */
public class MenuDntInf {
    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////

    /** 団体申込出願期限（団体コード未取得） **/
    private String dntGetKigen;
    /** 団体申込出願期限（団体コード未取得） **/
    private String dntGetKigenTime;
    /** 団体申込出願期限（団体コード取得済） **/
    private String dntMskKigen;
    /** 団体申込出願期限（団体コード取得済） **/
    private String dntMskKigenTime;
    /** 団体情報確認期限 **/
    private String dntCnfKigen;
    /** 団体情報確認期限 **/
    private String dntCnfKigenTime;
    /** 団体情報変更期限 **/
    private String dntUpdKigen;
    /** 団体情報変更期限 **/
    private String dntUpdKigenTime;
    /** 団体申込者期限 **/
    private String mskKigen;
    /** 団体申込者期限 **/
    private String mskKigenTime;
    /** 団体マイページ期限(確認期限) **/
    private String mypLoginKigen;
    /** 団体マイページ期限(確認期限) **/
    private String mypLoginKigenTime;
    /** 団体マイページ変更期限 **/
    private String mypUpdKigen;
    /** 団体マイページ変更期限 **/
    private String mypUpdKigenTime;
    /** 団体申込出願期限（団体コード未取得）フラグ  **/
    private String dntGetKigenFlg;
    /** 団体申込出願期限（団体コード取得済）フラグ **/
    private String dntMskKigenFlg;
    /** 団体代表者ログイン期限フラグ **/
    private String dntLoginKigenFlg;
    /** 団体情報確認期限フラグ **/
    private String dntCnfKigenFlg;
    /** 団体情報変更期限フラグ **/
    private String dntUpdKigenFlg;
    /** 団体情報変更リンク有無フラグ **/
    private String dntUpdLinkFlg;
    /** 団体申込者期限フラグ **/
    private String mskKigenFlg;
    /** 団体マイページ期限フラグ **/
    private String mypLoginKigenFlg;
    /** 団体マイページ変更期限フラグ **/
    private String mypUpdKigenFlg;
    /** 団体マイページリンク有無フラグ **/
    private String mypLinkFlg;

    /**
     * 団体申込出願期限（団体コード未取得）を設定する
     * @param dnGetKigen 設定するdnGetKigenの値
     */
    public void setDntGetKigen(String dntGetKigen) {
        this.dntGetKigen = dntGetKigen;
    }

    /**
     * 団体申込出願期限（団体コード未取得）を取得する
     * @return dnGetKigen
     */
    public String getDntGetKigen() {
        return dntGetKigen;
    }

    /**
     * 団体申込出願期限（団体コード未取得）
     * 表示用 年月日を取得する
     * @return 団体申込出願期限（団体コード未取得 表示用 年月日）
     */
    public String getDntGetKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(dntGetKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(dntGetKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体申込出願期限（団体コード取得済）を設定する
     * @param dntMskKigen 設定するdntMskKigenの値
     */
    public void setDntMskKigen(String dntMskKigen) {
        this.dntMskKigen = dntMskKigen;
    }

    /**
     * 団体申込出願期限（団体コード取得済）を取得する
     * @return dntMskKigen
     */
    public String getDntMskKigen() {
        return dntMskKigen;
    }

    /**
     * 団体申込出願期限（団体コード取得済）
     * 表示用 年月日を取得する
     * @return 団体申込出願期限（団体コード取得済 表示用 年月日）
     */
    public String getDntMskKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(dntMskKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(dntMskKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体情報確認期限を設定する
     * @param dntCnfKigen 設定するdntCnfKigenの値
     */
    public void setDntCnfKigen(String dntCnfKigen) {
        this.dntCnfKigen = dntCnfKigen;
    }

    /**
     * 団体情報確認期限を取得する
     * @return dntCnfKigen
     */
    public String getDntCnfKigen() {
        return dntCnfKigen;
    }

    /**
     * 団体情報確認期限(表示用 年月日)を取得する
     * @return 団体情報確認期限(表示用 年月日)
     */
    public String getDntCnfKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(dntCnfKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(dntCnfKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体情報変更期限を設定する
     * @param dntUpdKigen 設定するdntUpdKigenの値
     */
    public void setDntUpdKigen(String dntUpdKigen) {
        this.dntUpdKigen = dntUpdKigen;
    }

    /**
     * 団体情報変更期限を取得する
     * @return dntUpdKigen
     */
    public String getDntUpdKigen() {
        return dntUpdKigen;
    }

    /**
     * 団体情報変更期限(表示用 年月日)を取得する
     * @return 団体情報変更期限(表示用 年月日)
     */
    public String getDntUpdKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(dntUpdKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(dntUpdKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体申込者期限を設定する
     * @param mskKigen 設定するmskKigenの値
     */
    public void setMskKigen(String mskKigen) {
        this.mskKigen = mskKigen;
    }

    /**
     * 団体申込者期限を取得する
     * @return mskKigen
     */
    public String getMskKigen() {
        return mskKigen;
    }

    /**
     * 団体申込者期限(表示用 年月日)を取得する
     * @return 団体申込者期限(表示用 年月日)
     */
    public String getMskKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(mskKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(mskKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体マイページ期限を設定する(確認期限)
     * @param mypLoginKigen mypLoginKigen
     */
    public void setMypLoginKigen(String mypLoginKigen) {
        this.mypLoginKigen = mypLoginKigen;
    }

    /**
     * 団体マイページ期限を取得する(確認期限)
     * @return mypLoginKigen
     */
    public String getMypLoginKigen() {
        return mypLoginKigen;
    }

    /**
     * 団体マイページ期限(表示用 年月日)を取得する(確認期限)
     * @return 団体マイページ期限(表示用 年月日)
     */
    public String getMypLoginKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(mypLoginKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(mypLoginKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体マイページ変更期限を設定する
     * @param mypUpdKigen mypUpdKigen
     */
    public void setMypUpdKigen(String mypUpdKigen) {
        this.mypUpdKigen = mypUpdKigen;
    }

    /**
     * 団体マイページ変更期限を取得する
     * @return mypLoginKigen
     */
    public String getMypUpdKigen() {
        return mypUpdKigen;
    }

    /**
     * 団体マイページ変更期限(表示用 年月日)を取得する
     * @return 団体マイページ変更期限(表示用 年月日)
     */
    public String getMypUpdKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(mypUpdKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(mypUpdKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体申込出願期限（団体コード未取得）フラグを設定する
     * @param dnGetKigenFlg 設定するdnGetKigenFlgの値
     */
    public void setDntGetKigenFlg(String dnGetKigenFlg) {
        this.dntGetKigenFlg = dnGetKigenFlg;
    }

    /**
     *  団体申込出願期限（団体コード未取得）フラグを取得する
     * @return dnGetKigenFlg
     */
    public String getDntGetKigenFlg() {
        return dntGetKigenFlg;
    }

    /**
     * 団体申込出願期限（団体コード取得済）フラグを設定する
     * @param dntMskKigenFlg 設定するdntMskKigenFlgの値
     */
    public void setDntMskKigenFlg(String dntMskKigenFlg) {
        this.dntMskKigenFlg = dntMskKigenFlg;
    }

    /**
     * 団体申込出願期限（団体コード取得済）フラグを取得する
     * @return dntMskKigenFlg
     */
    public String getDntMskKigenFlg() {
        return dntMskKigenFlg;
    }

    /**
     * 団体情報確認期限フラグを設定する
     * @param dntCnfKigenFlg 設定するdntCnfKigenFlgの値
     */
    public void setDntCnfKigenFlg(String dntCnfKigenFlg) {
        this.dntCnfKigenFlg = dntCnfKigenFlg;
    }

    /**
     * 団体情報確認期限フラグを取得する
     * @return dntCnfKigenFlg
     */
    public String getDntCnfKigenFlg() {
        return dntCnfKigenFlg;
    }

    /**
     * 団体情報変更期限フラグを設定する
     * @param dntUpdKigenFlg 設定するdntUpdKigenFlgの値
     */
    public void setDntUpdKigenFlg(String dntUpdKigenFlg) {
        this.dntUpdKigenFlg = dntUpdKigenFlg;
    }

    /**
     * 団体情報変更期限フラグを取得する
     * @return dntUpdKigenFlg
     */
    public String getDntUpdKigenFlg() {
        return dntUpdKigenFlg;
    }

    /**
     * 団体申込者期限フラグを設定する
     * @param mskKigenFlg 設定するmskKigenFlgの値
     */
    public void setMskKigenFlg(String mskKigenFlg) {
        this.mskKigenFlg = mskKigenFlg;
    }

    /**
     * 団体申込者期限フラグを取得する
     * @return mskKigenFlg
     */
    public String getMskKigenFlg() {
        return mskKigenFlg;
    }

    /**
     * 団体マイページ期限フラグを設定する
     * @param mypLoginKigenFlg 設定するmypLoginKigenFlg
     */
    public void setMypLoginKigenFlg(String mypLoginKigenFlg) {
        this.mypLoginKigenFlg = mypLoginKigenFlg;
    }

    /**
     * 団体マイページ期限フラグを取得する
     * @return mypLoginKigenFlg
     */
    public String getMypLoginKigenFlg() {
        return mypLoginKigenFlg;
    }

    /**
     * 団体マイページ変更期限フラグを設定する
     * @param mypUpdKigenFlg 設定するmypUpdKigenFlg
     */
    public void setMypUpdKigenFlg(String mypUpdKigenFlg) {
        this.mypUpdKigenFlg = mypUpdKigenFlg;
    }

    /**
     * 団体マイページ変更期限フラグを取得する
     * @return mypLoginKigenFlg
     */
    public String getMypUpdKigenFlg() {
        return mypUpdKigenFlg;
    }

    /**
     * 団体マイページリンク有無フラグを設定する
     * @param mypLinkFlg 設定するmypLinkFlg
     */
    public void setMypLinkFlg(String mypLinkFlg) {
        this.mypLinkFlg = mypLinkFlg;
    }

    /**
     * 団体マイページリンク有無フラグを取得する
     * @return mypLoginKigenFlg
     */
    public String getMypLinkFlg() {
        return mypLinkFlg;
    }

    /**
     * 団体代表者ログイン期限フラグを設定する
     * @param dntLoginKigenFlg 設定する dntLoginKigenFlg
     */
    public void setDntLoginKigenFlg(String dntLoginKigenFlg) {
        this.dntLoginKigenFlg = dntLoginKigenFlg;
    }

    /**
     * 団体代表者ログイン期限フラグを取得する
     * @return dntLoginKigenFlg
     */
    public String getDntLoginKigenFlg() {
        return dntLoginKigenFlg;
    }

    /**
     * 団体情報変更・確認有無フラグを設定する
     * @param mypLinkFlg 設定するmypLinkFlg
     */
    public void setDntUpdLinkFlg(String dntUpdLinkFlg) {
        this.dntUpdLinkFlg = dntUpdLinkFlg;
    }

    /**
     * 団体情報変更・確認有無フラグを取得する
     * @return mypLoginKigenFlg
     */
    public String getDntUpdLinkFlg() {
        return dntUpdLinkFlg;
    }

    public String getDntGetKigenTime() {
        return dntGetKigenTime;
    }

    public void setDntGetKigenTime(String dntGetKigenTime) {
        this.dntGetKigenTime = dntGetKigenTime;
    }

    public String getDntMskKigenTime() {
        return dntMskKigenTime;
    }

    public void setDntMskKigenTime(String dntMskKigenTime) {
        this.dntMskKigenTime = dntMskKigenTime;
    }

    public String getDntCnfKigenTime() {
        return dntCnfKigenTime;
    }

    public void setDntCnfKigenTime(String dntCnfKigenTime) {
        this.dntCnfKigenTime = dntCnfKigenTime;
    }

    public String getDntUpdKigenTime() {
        return dntUpdKigenTime;
    }

    public void setDntUpdKigenTime(String dntUpdKigenTime) {
        this.dntUpdKigenTime = dntUpdKigenTime;
    }

    public String getMskKigenTime() {
        return mskKigenTime;
    }

    public void setMskKigenTime(String mskKigenTime) {
        this.mskKigenTime = mskKigenTime;
    }

    public String getMypLoginKigenTime() {
        return mypLoginKigenTime;
    }

    public void setMypLoginKigenTime(String mypLoginKigenTime) {
        this.mypLoginKigenTime = mypLoginKigenTime;
    }

    public String getMypUpdKigenTime() {
        return mypUpdKigenTime;
    }

    public void setMypUpdKigenTime(String mypUpdKigenTime) {
        this.mypUpdKigenTime = mypUpdKigenTime;
    }
    
}
