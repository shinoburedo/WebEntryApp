package jp.co.nii.miw.business.service.dantai;

import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.service.*;

/**
 * タイトル: 団体情報確認メニュー
 * 説明: 団体情報確認メニューデータbean
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 * @author r.ehara
 */
public class DntInfMenu {

    /** 団体情報の変更期限 **/
    private String dntUpdKigen;
    /** 団体情報の変更期限 **/
    private String dntUpdKigenTime;
    /** 団体情報の確認期限 **/
    private String dntConfKigen;
    /** 団体情報の確認期限 **/
    private String dntConfKigenTime;
    /** 団体申込者情報の確認期限 **/
    private String dntResKigen;
    /** 団体申込者情報の確認期限 **/
    private String dntResKigenTime;
    /** 団体申込者情報の変更期限 **/
    private String dntResUpdKigen;
    /** 団体申込者情報の変更期限 **/
    private String dntResUpdKigenTime;
    /** 団体パスワード変更期限 **/
    private String dntPswUpdKigen;
    /** 団体パスワード変更期限 **/
    private String dntPswUpdKigenTime;
    /** 団体申込パスワード変更期限 **/
    private String dntJknPswUpdKigen;
    /** 団体申込パスワード変更期限 **/
    private String dntJknPswUpdKigenTime;
    /** 団体情報の確認・変更期限フラグ  **/
    private String dntUpdKigenFlg;
    /** 団体情報の確認・変更期限フラグ  **/
    private String dntConfKigenFlg;
    /** 団体申込者情報の確認期限フラグ **/
    private String dntResKigenFlg;
    /** 団体申込者情報の変更期限フラグ **/
    private String dntResUpdKigenFlg;
    /** 団体パスワード変更期限フラグ **/
    private String dntPswUpdKigenFlg;
    /** 団体申込パスワード変更期限 **/
    private String dntJknPswUpdKigenFlg;
    /** 団体情報の確認・変更ボタン使用可否フラグ  **/
    private String dntUpdLinkFlg;
    /** 団体申込者の確認・変更ボタン使用可否フラグ  **/
    private String dntResLinkFlg;

    /**
     * 団体情報の変更期限を設定する
     * @param dntUpdKigen
     */
    public void setDntUpdKigen(String dntUpdKigen) {
        this.dntUpdKigen = dntUpdKigen;
    }

    /**
     * 団体情報の変更期限を取得する
     * @return dntUpdKigen
     */
    public String getDntUpdKigen() {
        return dntUpdKigen;
    }

    /**
     * 団体情報の変更期限
     * 表示用 年月日を取得する
     * @return 団体情報の確認・変更期限 表示用 年月日
     */
    public String getDntUpdKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntUpdKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
        return MiwStringUtility.formatDateSlash(dntUpdKigen) + " " + MiwStringUtility.formatTimeColon(dntUpdKigenTime);
    }

    /**
     * 団体情報の確認期限を設定する
     * @param dntConfKigen
     */
    public void setDntConfKigen(String dntConfKigen) {
        this.dntConfKigen = dntConfKigen;
    }

    /**
     * 団体情報の確認期限を取得する
     * @return dntConfKigen
     */
    public String getDntConfKigen() {
        return dntConfKigen;
    }

    /**
     * 団体情報の確認期限
     * 表示用 年月日を取得する
     * @return 団体情報の確認期限 表示用 年月日
     */
    public String getDntConfKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntConfKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
//        return ret;
        return MiwStringUtility.formatDateSlash(dntConfKigen) + " " + MiwStringUtility.formatTimeColon(dntConfKigenTime);
    }

    /**
     * 団体申込者情報の確認期限を設定する
     * @param dntResKigen
     */
    public void setDntResKigen(String dntResKigen) {
        this.dntResKigen = dntResKigen;
    }

    /**
     * 団体申込者情報の確認期限を取得する
     * @return dntResKigen
     */
    public String getDntResKigen() {
        return dntResKigen;
    }

    /**
     * 団体申込者情報の確認期限
     * 表示用 年月日を取得する
     * @return 団体申込者情報の確認期限 表示用 年月日）
     */
    public String getDntResKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntResKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
//        return ret;
        return MiwStringUtility.formatDateSlash(dntResKigen) + " " + MiwStringUtility.formatTimeColon(dntResKigenTime);
    }

    /**
     * 団体申込者情報の変更期限を設定する
     * @param dntResKigen
     */
    public void setDntResUpdKigen(String dntResUpdKigen) {
        this.dntResUpdKigen = dntResUpdKigen;
    }

    /**
     * 団体申込者情報の変更期限を取得する
     * @return dntResKigen
     */
    public String getDntResUpdKigen() {
        return dntResUpdKigen;
    }

    /**
     * 団体申込者情報の変更期限
     * 表示用 年月日を取得する
     * @return 団体申込者情報の変更期限 表示用 年月日）
     */
    public String getDntResUpdKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntResUpdKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
//        return ret;
        return MiwStringUtility.formatDateSlash(dntResUpdKigen) + " " + MiwStringUtility.formatTimeColon(dntResUpdKigenTime);
    }

    /**
     * 団体パスワード変更期限を設定する
     * @param dntPswUpdKigen
     */
    public void setDntPswUpdKigen(String dntPswUpdKigen) {
        this.dntPswUpdKigen = dntPswUpdKigen;
    }

    /**
     * 団体パスワード変更期限を取得する
     * @return dntPswUpdKigen
     */
    public String getDntPswUpdKigen() {
        return dntPswUpdKigen;
    }

    /**
     * 団体パスワード変更期限(表示用 年月日)を取得する
     * @return 団体パスワード変更期限(表示用 年月日)
     */
    public String getDntPswUpdKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntPswUpdKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
//        return ret;
        return MiwStringUtility.formatDateSlash(dntPswUpdKigen) + " " + MiwStringUtility.formatTimeColon(dntPswUpdKigenTime);
    }

    /**
     * 団体申込パスワード変更期限設定する
     * @param dntJknPswUpdKigen
     */
    public void setDntJknPswUpdKigen(String dntJknPswUpdKigen) {
        this.dntJknPswUpdKigen = dntJknPswUpdKigen;
    }

    /**
     * 団体申込パスワード変更期限を取得する
     * @return mskKigen
     */
    public String getDntJknPswUpdKigen() {
        return dntJknPswUpdKigen;
    }

    /**
     * 団体申込パスワード変更期限(表示用 年月日)を取得する
     * @return 団体申込パスワード変更期限(表示用 年月日)
     */
    public String getDntJknPswUpdKigenDisp() {
//        String ret = "";
//
//        MenuServ menuServ = new MenuServ();
//        String ymd[] = menuServ.convertDate(dntJknPswUpdKigen);
//
//        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
//        return ret;
        return MiwStringUtility.formatDateSlash(dntJknPswUpdKigen) + " " + MiwStringUtility.formatTimeColon(dntJknPswUpdKigenTime);
    }

    /**
     * 団体情報変更期限フラグを設定する
     * @param dntUpdKigenFlg
     */
    public void setDntUpdKigenFlg(String dntUpdKigenFlg) {
        this.dntUpdKigenFlg = dntUpdKigenFlg;
    }

    /**
     *  団体情報変更期限フラグを取得する
     * @return dntUpdKigenFlg
     */
    public String getDntUpdKigenFlg() {
        return dntUpdKigenFlg;
    }

    /**
     * 団体情報確認期限フラグを設定する
     * @param dntConfkigenFlg
     */
    public void setDntConfKigenFlg(String dntConfKigenFlg) {
        this.dntConfKigenFlg = dntConfKigenFlg;
    }

    /**
     *  団体情報確認期限フラグを取得する
     * @return dntConfKigenFlg
     */
    public String getDntConfKigenFlg() {
        return dntConfKigenFlg;
    }

    /**
     * 団体申込者情報の確認期限フラグを設定する
     * @param dntResKigenFlg
     */
    public void setDntResKigenFlg(String dntResKigenFlg) {
        this.dntResKigenFlg = dntResKigenFlg;
    }

    /**
     * 団体申込者情報の確認期限フラグを取得する
     * @return dntMskKigenFlg
     */
    public String getDntResKigenFlg() {
        return dntResKigenFlg;
    }

    /**
     * 団体申込者情報の変更期限フラグを設定する
     * @param dntResUpdKigenFlg
     */
    public void setDntResUpdKigenFlg(String dntResUpdKigenFlg) {
        this.dntResUpdKigenFlg = dntResUpdKigenFlg;
    }

    /**
     * 団体申込者情報の変更期限フラグを取得する
     * @return dntMskUpdKigenFlg
     */
    public String getDntResUpdKigenFlg() {
        return dntResUpdKigenFlg;
    }

    /**
     * 団体パスワード変更期限フラグを設定する
     * @param dntPswUpdKigenFlg
     */
    public void setDntPswUpdKigenFlg(String dntPswUpdKigenFlg) {
        this.dntPswUpdKigenFlg = dntPswUpdKigenFlg;
    }

    /**
     * 団体パスワード変更期限フラグを取得する
     * @return dntPswUpdKigenFlg
     */
    public String getDntPswUpdKigenFlg() {
        return dntPswUpdKigenFlg;
    }

    /**
     * 団体申込パスワード変更期限フラグを設定する
     * @param dntJknPswUpdKigenFlg
     */
    public void setDntJknPswUpdKigenFlg(String dntJknPswUpdKigenFlg) {
        this.dntJknPswUpdKigenFlg = dntJknPswUpdKigenFlg;
    }

    /**
     * 団体申込パスワード変更期限フラグを取得する
     * @return dntJknPswUpdKigenFlg
     */
    public String getDntJknPswUpdKigenFlg() {
        return dntJknPswUpdKigenFlg;
    }

    /**
     * 団体情報の確認・変更ボタン使用可否フラグを設定する
     * @param dntJknPswUpdKigenFlg
     */
    public void setDntUpdLinkFlg(String dntUpdLinkFlg) {
        this.dntUpdLinkFlg = dntUpdLinkFlg;
    }

    /**
     * 団体情報の確認・変更ボタン使用可否フラグを取得する
     * @return dntJknPswUpdKigenFlg
     */
    public String getDntUpdLinkFlg() {
        return dntUpdLinkFlg;
    }

    /**
     * 団体申込者の確認・変更ボタン使用可否フラグを設定する
     * @param dntJknPswUpdKigenFlg
     */
    public void setDntResLinkFlg(String dntResLinkFlg) {
        this.dntResLinkFlg = dntResLinkFlg;
    }

    /**
     * 団体情報の確認・変更ボタン使用可否フラグを取得する
     * @return dntJknPswUpdKigenFlg
     */
    public String getDntResLinkFlg() {
        return dntResLinkFlg;
    }

    public String getDntUpdKigenTime() {
        return dntUpdKigenTime;
    }

    public void setDntUpdKigenTime(String dntUpdKigenTime) {
        this.dntUpdKigenTime = dntUpdKigenTime;
    }

    public String getDntConfKigenTime() {
        return dntConfKigenTime;
    }

    public void setDntConfKigenTime(String dntConfKigenTime) {
        this.dntConfKigenTime = dntConfKigenTime;
    }

    public String getDntResKigenTime() {
        return dntResKigenTime;
    }

    public void setDntResKigenTime(String dntResKigenTime) {
        this.dntResKigenTime = dntResKigenTime;
    }

    public String getDntResUpdKigenTime() {
        return dntResUpdKigenTime;
    }

    public void setDntResUpdKigenTime(String dntResUpdKigenTime) {
        this.dntResUpdKigenTime = dntResUpdKigenTime;
    }

    public String getDntPswUpdKigenTime() {
        return dntPswUpdKigenTime;
    }

    public void setDntPswUpdKigenTime(String dntPswUpdKigenTime) {
        this.dntPswUpdKigenTime = dntPswUpdKigenTime;
    }

    public String getDntJknPswUpdKigenTime() {
        return dntJknPswUpdKigenTime;
    }

    public void setDntJknPswUpdKigenTime(String dntJknPswUpdKigenTime) {
        this.dntJknPswUpdKigenTime = dntJknPswUpdKigenTime;
    }
    
}
