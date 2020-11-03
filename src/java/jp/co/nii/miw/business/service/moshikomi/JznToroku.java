package jp.co.nii.miw.business.service.moshikomi;

import java.text.ParseException;

import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.utility.DateUtility;

import org.apache.struts.action.ActionMessages;

/**
 * タイトル: JznToroku
 * 説明: ID取得のデータbean
 * 著作権: Copyright (c) 2011
 * 会社名: 日本情報産業株式会社
 * @author t.yamaguchi
 */
public class JznToroku {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 年度 */
    private String nendo;
    /** 期 */
    private String ki;
    /** ユーザーＩＤ */
    private String userId;
    /** 氏名（姓）カナ */
    private String shimeiSeiKana;
    /** 氏名（名）カナ */
    private String shimeiMeiKana;
    /** 氏名（姓） */
    private String shimeiSei;
    /** 氏名（名） */
    private String shimeiMei;
    /** 性別 */
    private String sexCode;
    /** 生年月日（元号） */
    private String birthdayEra;
    /** 生年月日（年） */
    private String birthdayYear;
    /** 生年月日（月） */
    private String birthdayMonth;
    /** 生年月日（日） */
    private String birthdayDay;
    /** 電話番号 */
    private String telNo;
    /** 内線番号 */
    private String extNo;
    /** メールアドレス */
    private String mailAddress;
    /** メールアドレス（確認用） */
    private String mailAddressKakunin;
    /** パスワード */
    private String passwd;
    /** パスワード（確認用） */
    private String passwdKakunin;
    /** ＩＤ取得期間（開始日） */
    private String kikanDateFrom;
    /** ＩＤ取得期間（開始時間） */
    private String kikanTimeFrom;
    /** ＩＤ取得期間（終了日） */
    private String kikanDateTo;
    /** ＩＤ取得期間（終了時間） */
    private String kikanTimeTo;

    public JznToroku() {
        //情報を格納するフィールド・メソッド
        this.userId = "";
        this.shimeiSeiKana = "";
        this.shimeiMeiKana = "";
        this.shimeiSei = "";
        this.shimeiMei = "";
        this.sexCode = "";
        this.birthdayYear = "";
        this.birthdayMonth = "";
        this.birthdayDay = "";
        this.telNo = "";
        this.mailAddress = "";
        this.mailAddressKakunin = "";
        this.passwd = "";
        this.passwdKakunin = "";
        this.kikanDateFrom = "";
        this.kikanTimeFrom = "";
        this.kikanDateTo = "";
        this.kikanTimeTo = "";

        //エラー箇所表示用
        this.errors = new ActionMessages();
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the nendo
     */
    public String getNendo() {
        return nendo;
    }

    /**
     * @param nendo the nendo to set
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }

    /**
     * @return the ki
     */
    public String getKaisu() {
        return ki;
    }

    /**
     * @param ki the ki to set
     */
    public void setKaisu(String ki) {
        this.ki = ki;
    }

    /**
     * @return the shimeiSeiKana
     */
    public String getShimeiSeiKana() {
        return shimeiSeiKana;
    }

    /**
     * @param shimeiSeiKana the shimeiSeiKana to set
     */
    public void setShimeiSeiKana(String shimeiSeiKana) {
        this.shimeiSeiKana = shimeiSeiKana;
    }

    /**
     * @return the shimeiMeiKana
     */
    public String getShimeiMeiKana() {
        return shimeiMeiKana;
    }

    /**
     * @param shimeiMeiKana the shimeiMeiKana to set
     */
    public void setShimeiMeiKana(String shimeiMeiKana) {
        this.shimeiMeiKana = shimeiMeiKana;
    }

    /**
     * @return the shimeiSei
     */
    public String getShimeiSei() {
        return shimeiSei;
    }

    /**
     * @param shimeiSei the shimeiSei to set
     */
    public void setShimeiSei(String shimeiSei) {
        this.shimeiSei = shimeiSei;
    }

    /**
     * @return the shimeiMei
     */
    public String getShimeiMei() {
        return shimeiMei;
    }

    /**
     * @param shimeiMei the shimeiMei to set
     */
    public void setShimeiMei(String shimeiMei) {
        this.shimeiMei = shimeiMei;
    }

    /**
     * @return the sexCode
     */
    public String getSexCode() {
        return sexCode;
    }

    /**
     * @param sexCode the sexCode to set
     */
    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    /**
     * 性別コード名称を取得する
     * @return 性別コード名称
     */
    public String getSexCodeDisp() {
        String ret = "";
        ret = CodeValueName.getSexCodeName(sexCode);
        return ret;
    }

    /**
     * @return the birthdayEra
     */
    public String getBirthdayEra() {
        return birthdayEra;
    }

    /**
     * @param birthdayEra the birthdayEra to set
     */
    public void setBirthdayEra(String birthdayEra) {
        this.birthdayEra = birthdayEra;
    }

    /**
     * @return the birthdayYear
     */
    public String getBirthdayYear() {
        return birthdayYear;
    }

    /**
     * @param birthdayYear the birthdayYear to set
     */
    public void setBirthdayYear(String birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    /**
     * @return the birthdayMonth
     */
    public String getBirthdayMonth() {
        return birthdayMonth;
    }

    /**
     * @param birthdayMonth the birthdayMonth to set
     */
    public void setBirthdayMonth(String birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    /**
     * @return the birthdayDay
     */
    public String getBirthdayDay() {
        return birthdayDay;
    }

    /**
     * @param birthdayDay the birthdayDay to set
     */
    public void setBirthdayDay(String birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    /**
     * @return the telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * @param telNo the telNo to set
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the extNo
     */
    public String getExtNo() {
        return extNo;
    }

    /**
     * @param extNo the extNo to set
     */
    public void setExtNo(String extNo) {
        this.extNo = extNo;
    }

    /**
     * @return the mailAddress
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * @param mailAddress the mailAddress to set
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * @return the mailAddressKakunin
     */
    public String getMailAddressKakunin() {
        return mailAddressKakunin;
    }

    /**
     * @param mailAddressKakunin the mailAddressKakunin to set
     */
    public void setMailAddressKakunin(String mailAddressKakunin) {
        this.mailAddressKakunin = mailAddressKakunin;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * @return the passwdKakunin
     */
    public String getPasswdKakunin() {
        return passwdKakunin;
    }

    /**
     * @param passwdKakunin the passwdKakunin to set
     */
    public void setPasswdKakunin(String passwdKakunin) {
        this.passwdKakunin = passwdKakunin;
    }

    /**
     * @return the kikanDateFrom
     */
    public String getKikanDateFrom() {
        return kikanDateFrom;
    }

    /**
     * @param kikanDateFrom the kikanDateFrom to set
     */
    public void setKikanDateFrom(String kikanDateFrom) {
        this.kikanDateFrom = kikanDateFrom;
    }

    /**
     * @return the kikanTimeFrom
     */
    public String getKikanTimeFrom() {
        return kikanTimeFrom;
    }

    /**
     * @param kikanTimeFrom the kikanTimeFrom to set
     */
    public void setKikanTimeFrom(String kikanTimeFrom) {
        this.kikanTimeFrom = kikanTimeFrom;
    }

    /**
     * @return the kikanDateTo
     */
    public String getKikanDateTo() {
        return kikanDateTo;
    }

    /**
     * @param kikanDateTo the kikanDateTo to set
     */
    public void setKikanDateTo(String kikanDateTo) {
        this.kikanDateTo = kikanDateTo;
    }

    /**
     * @return the kikanTimeTo
     */
    public String getKikanTimeTo() {
        return kikanTimeTo;
    }

    /**
     * @param kikanTimeTo the kikanTimeTo to set
     */
    public void setKikanTimeTo(String kikanTimeTo) {
        this.kikanTimeTo = kikanTimeTo;
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 各値のスペースを削除する
     */
    public void deleteSpace() {
        this.userId = StringUtility.removeSpace(this.userId);
        this.shimeiSeiKana = StringUtility.removeEdgeSpace(this.shimeiSeiKana);
        this.shimeiMeiKana = StringUtility.removeEdgeSpace(this.shimeiMeiKana);
        this.shimeiSei = StringUtility.removeEdgeSpace(this.shimeiSei);
        this.shimeiMei = StringUtility.removeEdgeSpace(this.shimeiMei);
        this.birthdayYear = StringUtility.removeSpace(this.birthdayYear);
        this.birthdayMonth = StringUtility.removeSpace(this.birthdayMonth);
        this.birthdayDay = StringUtility.removeSpace(this.birthdayDay);
        this.telNo = StringUtility.removeEdgeSpace(this.telNo);
        this.mailAddress = StringUtility.removeSpace(this.mailAddress);
        this.mailAddressKakunin = StringUtility.removeSpace(this.mailAddressKakunin);
    }

    /**
     * 生年月日を
     * EYYMMDD形式で返す
     * @return 
     */
    public String getBirthdayYMD() {
        return birthdayEra
                + StringUtility.padLeft(birthdayYear, "0", 2)
                + StringUtility.padLeft(birthdayMonth, "0", 2)
                + StringUtility.padLeft(birthdayDay, "0", 2);
    }

    /**
     * 生年月日を
     * 和暦YY年M月D日形式で返す
     * @return 
     */
    public String getBirthdayDisp() {
        int iYear = Integer.parseInt(birthdayYear);
        int iMonth = Integer.parseInt(birthdayMonth);
        int iday = Integer.parseInt(birthdayDay);

        return CodeValueName.getBirthdayEraCodeName(birthdayEra)
                + Integer.toString(iYear) + "年"
                + Integer.toString(iMonth) + "月"
                + Integer.toString(iday) + "日";

    }

    /**
     * 内線番号がある場合はtrueを返す
     * @return 
     */
    public boolean getIsExtNo() {
        boolean ret = true;
        if (extNo == null || extNo.equals("")) {
            ret = false;
        }
        return ret;
    }
    ////////////////////////////////////////////
    //エラー箇所表示用
    ///////////////////////////////////////////
    /** エラー */
    private ActionMessages errors;

    /**
     * @return the errors
     */
    public ActionMessages getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(ActionMessages errors) {
        this.errors = errors;
    }

    /**
     * 正常な場合、エラーの場合のCSSを返す
     * @return 
     */
    public String getValidateCss(String errorsName) {
        if (errors.get(errorsName).hasNext()) {
            return MiwConstants.VALIDATE_CSS_NG;
        } else {
            return MiwConstants.VALIDATE_CSS_OK;
        }
    }

    /**
     * コールセンターの電話番号を返す
     * @return 
     */
    public String getCenterTel() {
        return MiwConstants.CALL_CENTER_TEL;
    }

    /**
     * コールセンターの名称を返す
     * @return 
     */
    public String getCenterName() {
        return MiwConstants.CALL_CENTER_NAME;
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
     * 現在の日時がＩＤ取得期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsIDKikan() {
        String fromDate = this.kikanDateFrom + this.kikanTimeFrom;
        String toDate = this.kikanDateTo + this.kikanTimeTo;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }
}
