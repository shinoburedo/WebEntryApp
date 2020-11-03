package jp.co.nii.miw.business.service;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.utility.DateTimeUtility;

/**
 * タイトル: 申込
 * 説明: 申込のデータbean
 * 著作権: Copyright (c) 2011
 * 会社名: 日本情報産業株式会社
 * @author t.yamaguchi
 */
public class MenuJoho {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** ＩＤ取得期限 **/
    private String idKigen;
    /** ＩＤ取得期限時間 **/
    private String idKigenTime;
    /** 申込期限 **/
    private String shutsuganKigen;
    /** 申込期限 **/
    private String shutsuganKigenTime;
    /** ログイン期限（個人、団体） **/
    private String loginKigen;
    /** ログイン期限（個人、団体） **/
    private String loginKigenTime;
    /** 変更期限（個人、団体） **/
    private String henkoKigen;
    /** 変更期限（個人、団体） **/
    private String henkoKigenTime;
    /** 確認期限（個人、団体） **/
    private String kakuninKigen;
    /** 確認期限（個人、団体） **/
    private String kakuninKigenTime;
    /** 団体申込者登録 **/
    private String dantaiMskKigen;
    /** 団体申込者登録 **/
    private String dantaiMskKigenTime;
    /** 団体情報確認ログイン期限 **/
    private String dantaiLoginKigen;
    /** 団体情報確認ログイン期限 **/
    private String dantaiLoginKigenTime;
    /** 団体情報変更期限 **/
    private String dantaiHenkoKigen;
    /** 団体情報変更期限 **/
    private String dantaiHenkoKigenTime;
    /** 団体情報確認期限 **/
    private String dantaiKakuninKigen;
    /** 団体情報確認期限 **/
    private String dantaiKakuninKigenTime;
    /** 団体登録期限 **/
    private String dantaiTrkKigen;
    /** 団体登録期限 **/
    private String dantaiTrkKigenTime;
    /** ＩＤ取得期間フラグ **/
    private String idKikanFlg;
    /** 申込期間フラグ **/
    private String shutsuganKikanFlg;
    /** マイページ期間フラグ **/
    private String mypageKikanFlg;
    /** マイページ確認期間フラグ **/
    private String mypageCnfKikanFlg;
    /** マイページ変更期間フラグ **/
    private String mypageUpdKikanFlg;
    /** 団体情報確認期間フラグ **/
    private String dantaiKakuninKikanFlg;
    /** 団体登録期間フラグ **/
    private String dantaiTrkKikanFlg;
    /** マイページボタン使用可能フラグ **/
    private String mypageButtonFlg;
    /** マイページ期間名称 **/
    private String mypageKikanName;
    /** マイページログイン期間 **/
    private String mypageKigen;
    /** マイページ確認期間 **/
    private String mypageCnfKigen;
    /** マイページ変更期間 **/
    private String mypageUpdKigen;

    /**
     * ＩＤ取得期限を設定する
     * @param idKigen 設定するidKigenの値
     */
    public void setIdKigen(String idKigen) {
        this.idKigen = idKigen;
    }

    /**
     * ＩＤ取得期限を取得する
     * @return idKigen
     */
    public String getIdKigen() {
        return idKigen;
    }

    /**
     * ＩＤ取得期限（表示用 年月日）を取得する
     * @return ＩＤ取得期限（表示用 年月日）
     */
    public String getIdKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(idKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(idKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 申込期限を設定する
     * @param shutsuganKigen 設定するshutsuganKigenの値
     */
    public void setShutsuganKigen(String shutsuganKigen) {
        this.shutsuganKigen = shutsuganKigen;
    }

    /**
     * 申込期限を取得する
     * @return shutsuganKigen
     */
    public String getShutsuganKigen() {
        return shutsuganKigen;
    }

    /**
     * 申込期限（表示用 年月日）を取得する
     * @return 申込期限（表示用 年月日）
     */
    public String getShutsuganKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(shutsuganKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(shutsuganKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * ログイン期限を設定する
     * @param loginKigen 設定するloginKigenの値
     */
    public void setLoginKigen(String loginKigen) {
        this.loginKigen = loginKigen;
    }

    /**
     * ログイン期限を取得する
     * @return loginKigen
     */
    public String getLoginKigen() {
        return loginKigen;
    }

    /**
     * ログイン期限（表示用 年月日）を取得する
     * @return ログイン期限（表示用 年月日）
     */
    public String getLoginKigenDisp() {
        String ret = "";

        MenuServ menuServ = new MenuServ();
        String ymd[] = menuServ.convertDate(loginKigen);
        String hms[] = menuServ.convertTime(loginKigenTime);

        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日　" +  hms[0] + "時" + hms[1] + "分";

        return ret;
    }

    /**
     * 変更期限を設定する
     * @param idKigen 設定するidKigenの値
     */
    public void setHenkoKigen(String henkoKigen) {
        this.henkoKigen = henkoKigen;
    }

    /**
     * 変更期限を取得する
     * @return henkoKigen
     */
    public String getHenkoKigen() {
        return henkoKigen;
    }

    /**
     * 変更期限（表示用 年月日）を取得する
     * @return 変更期限（表示用 年月日）
     */
    public String getHenkoKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(henkoKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(henkoKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 確認期限を設定する
     * @param idKigen 設定するidKigenの値
     */
    public void setKakuninKigen(String kakuninKigen) {
        this.kakuninKigen = kakuninKigen;
    }

    /**
     * 確認期限を取得する
     * @return henkoKigen
     */
    public String getKakuninKigen() {
        return kakuninKigen;
    }

    /**
     * 確認期限（表示用 年月日）を取得する
     * @return 確認期限（表示用 年月日）
     */
    public String getKakuninKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(kakuninKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(kakuninKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * １次合否閲覧期限を設定する
     * @param dantaiMskKigen 設定するgohiKigen1の値
     */
    public void setDantaiMskKigen(String dantaiMskKigen) {
        this.dantaiMskKigen = dantaiMskKigen;
    }

    /**
     * １次合否閲覧期限を取得する
     * @return dantaiMskKigen
     */
    public String getDantaiMskKigen() {
        return dantaiMskKigen;
    }

    /**
     * １次合否閲覧期限（表示用 年月日）を取得する
     * @return １次合否閲覧期限（表示用 年月日）
     */
    public String getDantaiMskKigenDisp() {
        try {
            return DateTimeUtility.formatToStringyyyyMMddSlashFromyyyyMMdd(dantaiMskKigen) + " "
                    + DateTimeUtility.formatToStringHHmmssColonFromHHmmss(dantaiMskKigenTime);
        } catch(Exception ex) {
            return "";
        }
    }

    /**
     * 団体情報確認ログイン期限を設定する
     * @param dantaiLoginKigen 設定するgohiKigen2の値
     */
    public void setDantaiLoginKigen(String dantaiLoginKigen) {
        this.dantaiLoginKigen = dantaiLoginKigen;
    }

    /**
     * 団体情報確認ログイン期限を取得する
     * @return dantaiLoginKigen
     */
    public String getDantaiLoginKigen() {
        return dantaiLoginKigen;
    }

    /**
     * 団体情報確認ログイン期限（表示用 年月日）を取得する
     * @return 団体情報確認ログイン期限（表示用 年月日）
     */
    public String getDantaiLoginKigenDisp() {
        String ret = "";

        MenuServ menuServ = new MenuServ();
        String ymd[] = menuServ.convertDate(dantaiLoginKigen);
        String hms[] = menuServ.convertTime(dantaiLoginKigenTime);

        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日　" +  hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * 団体情報変更期限を設定する
     * @param dantaiHenkoKigen 設定するgohiKigen2の値
     */
    public void setDantaiHenkoKigen(String dantaiHenkoKigen) {
        this.dantaiHenkoKigen = dantaiHenkoKigen;
    }

    /**
     * 団体情報変更期限を取得する
     * @return dantaiHenkoKigen
     */
    public String getDantaiHenkoKigen() {
        return dantaiHenkoKigen;
    }

    /**
     * 団体情報確認期限を設定する
     * @param dantaiKakuninKigen 設定するgohiKigen2の値
     */
    public void setDantaiKakuninKigen(String dantaiKakuninKigen) {
        this.dantaiKakuninKigen = dantaiKakuninKigen;
    }

    /**
     * 団体情報確認期限を取得する
     * @return dantaiKakuninKigen
     */
    public String getDantaiKakuninKigen() {
        return dantaiKakuninKigen;
    }

    /**
     * 団体登録期限を設定する
     * @param dantaiTrkKigen 設定するdantaiTrkKigenの値
     */
    public void setDantaiTrkKigen(String dantaiTrkKigen) {
        this.dantaiTrkKigen = dantaiTrkKigen;
    }

    /**
     * 団体登録期限を取得する
     * @return dantaiTrkKigen
     */
    public String getDantaiTrkKigen() {
        return dantaiTrkKigen;
    }

    /**
     * 団体登録期限（表示用 年月日）を取得する
     * @return 団体登録期限（表示用 年月日）
     */
    public String getDantaiTrkKigenDisp() {
        String ret = "";

        MenuServ menuServ = new MenuServ();
        String ymd[] = menuServ.convertDate(dantaiTrkKigen);
        String hms[] = menuServ.convertTime(dantaiTrkKigenTime);

        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日　" +  hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * コールセンター名称を取得する
     * @return コールセンター名称
     */
    public String getCenterName() {
        return MiwConstants.CALL_CENTER_NAME;
    }

    /**
     * コールセンター電話番号を取得する
     * @return コールセンター電話番号
     */
    public String getCenterTel() {
        return MiwConstants.CALL_CENTER_TEL;
    }

    /**
     * コールセンターFAX番号を取得する
     * @return コールセンターFAX番号
     */
    public String getCenterFax() {
        return MiwConstants.CALL_CENTER_FAX;
    }

    /**
     * コールセンター期間を取得する
     * @return コールセンター期間
     */
    public String getCenterKikan() {
        return MiwConstants.CALL_CENTER_KIKAN;
    }

    /**
     * コールセンター時間を取得する
     * @return コールセンター時間
     */
    public String getCenterJikan() {
        return MiwConstants.CALL_CENTER_JIKAN;
    }

    /**
     * ＩＤ取得期間フラグを設定する
     * @param idKikanFlg 設定するidKikanFlgの値
     */
    public void setIdKikanFlg(String idKikanFlg) {
        this.idKikanFlg = idKikanFlg;
    }

    /**
     * ＩＤ取得期間フラグを取得する
     * @return idKikanFlg
     */
    public String getIdKikanFlg() {
        return idKikanFlg;
    }

    /**
     * 申込期間フラグを設定する
     * @param shutsuganKikanFlg 設定するshutsuganKikanFlgの値
     */
    public void setShutsuganKikanFlg(String shutsuganKikanFlg) {
        this.shutsuganKikanFlg = shutsuganKikanFlg;
    }

    /**
     * 申込期間フラグを取得する
     * @return shutsuganKikanFlg
     */
    public String getShutsuganKikanFlg() {
        return shutsuganKikanFlg;
    }

    /**
     * マイページ期間フラグを設定する
     * @param mypageKikanFlg 設定するmypageKikanFlgの値
     */
    public void setMypageKikanFlg(String mypageKikanFlg) {
        this.mypageKikanFlg = mypageKikanFlg;
    }

    /**
     * マイページ期間フラグを取得する
     * @return mypageKikanFlg
     */
    public String getMypageKikanFlg() {
        return mypageKikanFlg;
    }

    /**
     * マイページ確認期間フラグを設定する
     * @param mypageKikanFlg 設定するmypageKikanFlgの値
     */
    public void setMypageCnfKikanFlg(String mypageCnfKikanFlg) {
        this.mypageCnfKikanFlg = mypageCnfKikanFlg;
    }

    /**
     * マイページ確認期間フラグを取得する
     * @return mypageKikanFlg
     */
    public String getMypageCnfKikanFlg() {
        return mypageCnfKikanFlg;
    }

    /**
     * マイページ変更期間フラグを設定する
     * @param mypageUpdKikanFlg 設定するmypageUpdKikanFlgの値
     */
    public void setMypageUpdKikanFlg(String mypageUpdKikanFlg) {
        this.mypageUpdKikanFlg = mypageUpdKikanFlg;
    }

    /**
     * マイページ変更期間フラグを取得する
     * @return mypageKikanFlg
     */
    public String getMypageUpdKikanFlg() {
        return mypageUpdKikanFlg;
    }

    /**
     * 団体情報確認期間フラグを設定する
     * @param dantaiKakuninKikanFlg dantaiKakuninKikanFlg
     */
    public void setDantaiKakuninKikanFlg(String dantaiKakuninKikanFlg) {
        this.dantaiKakuninKikanFlg = dantaiKakuninKikanFlg;
    }

    /**
     * 団体情報確認期間フラグを取得する
     * @return dantaiKakuninKikanFlg
     */
    public String getDantaiKakuninKikanFlg() {
        return dantaiKakuninKikanFlg;
    }

    /**
     * 団体登録期間フラグを設定する
     * @param dantaiTrkKikanFlg dantaiTrkKikanFlg
     */
    public void setDantaiTrkKikanFlg(String dantaiTrkKikanFlg) {
        this.dantaiTrkKikanFlg = dantaiTrkKikanFlg;
    }

    /**
     * 団体登録期間フラグを取得する
     * @return dantaiTrkKikanFlg
     */
    public String getDantaiTrkKikanFlg() {
        return dantaiTrkKikanFlg;
    }

    /**
     * マイページボタン使用可否フラグを設定する
     * @param mypageButtonFlg 設定するmypageButtonFlgの値
     */
    public void setMypageButtonFlg(String mypageButtonFlg) {
        this.mypageButtonFlg = mypageButtonFlg;
    }

    /**
     * マイページボタン使用可否フラグを取得する
     * @return mypageButtonFlg
     */
    public String getMypageButtonFlg() {
        return mypageButtonFlg;
    }

    /**
     * マイページ期間名を設定する
     * @param mypageKikanName 設定するmypageKikanNameの値
     */
    public void setMypageKikanName(String mypageKikanName) {
        this.mypageKikanName = mypageKikanName;
    }

    /**
     * マイページ期間名を取得する
     * @return mypageKikanName
     */
    public String getMypageKikanName() {
        return mypageKikanName;
    }

    /**
     * マイページ期限を設定する
     * @param mypageKigen 設定するmypageKigenの値
     */
    public void setMypageKigen(String mypageKigen) {
        this.mypageKigen = mypageKigen;
    }

    /**
     * マイページ期限を取得する
     * @return mypageKigen
     */
    public String getMypageKigen() {
        return mypageKigen;
    }

    public String getIdKigenTime() {
        return idKigenTime;
    }

    public void setIdKigenTime(String idKigenTime) {
        this.idKigenTime = idKigenTime;
    }

    public String getShutsuganKigenTime() {
        return shutsuganKigenTime;
    }

    public void setShutsuganKigenTime(String shutsuganKigenTime) {
        this.shutsuganKigenTime = shutsuganKigenTime;
    }

    public String getLoginKigenTime() {
        return loginKigenTime;
    }

    public void setLoginKigenTime(String loginKigenTime) {
        this.loginKigenTime = loginKigenTime;
    }

    public String getHenkoKigenTime() {
        return henkoKigenTime;
    }

    public void setHenkoKigenTime(String henkoKigenTime) {
        this.henkoKigenTime = henkoKigenTime;
    }

    public String getKakuninKigenTime() {
        return kakuninKigenTime;
    }

    public void setKakuninKigenTime(String kakuninKigenTime) {
        this.kakuninKigenTime = kakuninKigenTime;
    }

    public String getDantaiMskKigenTime() {
        return dantaiMskKigenTime;
    }

    public void setDantaiMskKigenTime(String dantaiMskKigenTime) {
        this.dantaiMskKigenTime = dantaiMskKigenTime;
    }

    public String getDantaiLoginKigenTime() {
        return dantaiLoginKigenTime;
    }

    public void setDantaiLoginKigenTime(String dantaiLoginKigenTime) {
        this.dantaiLoginKigenTime = dantaiLoginKigenTime;
    }

    public String getDantaiHenkoKigenTime() {
        return dantaiHenkoKigenTime;
    }

    public void setDantaiHenkoKigenTime(String dantaiHenkoKigenTime) {
        this.dantaiHenkoKigenTime = dantaiHenkoKigenTime;
    }

    public String getDantaiKakuninKigenTime() {
        return dantaiKakuninKigenTime;
    }

    public void setDantaiKakuninKigenTime(String dantaiKakuninKigenTime) {
        this.dantaiKakuninKigenTime = dantaiKakuninKigenTime;
    }

    public String getDantaiTrkKigenTime() {
        return dantaiTrkKigenTime;
    }

    public void setDantaiTrkKigenTime(String dantaiTrkKigenTime) {
        this.dantaiTrkKigenTime = dantaiTrkKigenTime;
    }
    
    
}
