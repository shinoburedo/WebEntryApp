package jp.co.nii.miw.business.service.mypage;

import java.text.ParseException;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.DateUtility;

/**
 * タイトル: MskInf
 * 説明: 申込者情報のデータbean
 * 著作権: Copyright (c) 2011
 * 会社名: 日本情報産業株式会社
 * @author t.yamaguchi
 */
public class MypSknResu {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 季 */
    private String ki;
    /** 級 */
    private String kyu;
    /** １次免除 */
    private boolean isIchijiMenjo;
    /** １次閲覧開始日 */
    private String ichijiEtsuranKaishiDate;
    /** １次閲覧開始時刻 */
    private String ichijiEtsuranKaishiTime;
    /** １次閲覧開始日 */
    private String ichijiEtsuranShuryoDate;
    /** １次閲覧開始時刻 */
    private String ichijiEtsuranShuryoTime;
    /** １次合否結果 */
    private String ichijiGohiKekka;
    /** １次得点 */
    private String ichijiTokuten;
    /** １次満点 */
    private String ichijiManten;
    /** １次基準点 */
    private String ichijiKijunten;
    /** １次合格率 */
    private String ichijiGokakuRitsu;
    /** 最終閲覧開始日 */
    private String saishuEtsuranKaishiDate;
    /** 最終閲覧開始時刻 */
    private String saishuEtsuranKaishiTime;
    /** 最終閲覧開始日 */
    private String saishuEtsuranShuryoDate;
    /** 最終閲覧開始時刻 */
    private String saishuEtsuranShuryoTime;
    /** 最終合否結果 */
    private String saishuGohiKekka;
    /** 最終得点*/
    private String saishuTokuten;
    /** 最終満点*/
    private String saishuManten;
    /** 最終基準点 */
    private String saishuKijunten;
    /** 最終合格率 */
    private String saishuGokakuRitsu;
    
    public MypSknResu() {
        this.ki = "";
        this.kyu = "";
        this.ichijiEtsuranKaishiDate = "";
        this.ichijiEtsuranKaishiTime = "";
        this.ichijiEtsuranShuryoDate = "";
        this.ichijiEtsuranShuryoTime = "";
        this.ichijiGohiKekka = "";
        this.ichijiTokuten = "";
        this.ichijiManten = "";
        this.ichijiKijunten = "";
        this.ichijiGokakuRitsu = "";
        
        this.saishuEtsuranKaishiDate = "";
        this.saishuEtsuranKaishiTime = "";
        this.saishuEtsuranShuryoDate = "";
        this.saishuEtsuranShuryoTime = "";
        this.saishuGohiKekka = "";
        this.saishuTokuten = "";
        this.saishuManten = "";
        this.saishuKijunten = "";
        this.saishuGokakuRitsu = "";
        
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
     * @return the kyu
     */
    public String getKyu() {
        return kyu;
    }

    /**
     * @param kyu the kyu to set
     */
    public void setKyu(String kyu) {
        this.kyu = kyu;
    }

    /**
     * @return the isIchijiMenjo
     */
    public boolean isIsIchijiMenjo() {
        return isIchijiMenjo;
    }

    /**
     * @param isIchijiMenjo the isIchijiMenjo to set
     */
    public void setIsIchijiMenjo(boolean isIchijiMenjo) {
        this.isIchijiMenjo = isIchijiMenjo;
    }

    /**
     * @return the ichijiEtsuranKaishiDate
     */
    public String getIchijiEtsuranKaishiDate() {
        return ichijiEtsuranKaishiDate;
    }

    /**
     * @param ichijiEtsuranKaishiDate the ichijiEtsuranKaishiDate to set
     */
    public void setIchijiEtsuranKaishiDate(String ichijiEtsuranKaishiDate) {
        this.ichijiEtsuranKaishiDate = ichijiEtsuranKaishiDate;
    }

    /**
     * @return the ichijiEtsuranKaishiTime
     */
    public String getIchijiEtsuranKaishiTime() {
        return ichijiEtsuranKaishiTime;
    }

    /**
     * @param ichijiEtsuranKaishiTime the ichijiEtsuranKaishiTime to set
     */
    public void setIchijiEtsuranKaishiTime(String ichijiEtsuranKaishiTime) {
        this.ichijiEtsuranKaishiTime = ichijiEtsuranKaishiTime;
    }

    /**
     * @return the ichijiEtsuranShuryoDate
     */
    public String getIchijiEtsuranShuryoDate() {
        return ichijiEtsuranShuryoDate;
    }

    /**
     * @param ichijiEtsuranShuryoDate the ichijiEtsuranShuryoDate to set
     */
    public void setIchijiEtsuranShuryoDate(String ichijiEtsuranShuryoDate) {
        this.ichijiEtsuranShuryoDate = ichijiEtsuranShuryoDate;
    }

    /**
     * @return the ichijiEtsuranShuryoTime
     */
    public String getIchijiEtsuranShuryoTime() {
        return ichijiEtsuranShuryoTime;
    }

    /**
     * @param ichijiEtsuranShuryoTime the ichijiEtsuranShuryoTime to set
     */
    public void setIchijiEtsuranShuryoTime(String ichijiEtsuranShuryoTime) {
        this.ichijiEtsuranShuryoTime = ichijiEtsuranShuryoTime;
    }

    /**
     * @return the ichijiGohiKekka
     */
    public String getIchijiGohiKekka() {
        return ichijiGohiKekka;
    }

    /**
     * @param ichijiGohiKekka the ichijiGohiKekka to set
     */
    public void setIchijiGohiKekka(String ichijiGohiKekka) {
        this.ichijiGohiKekka = ichijiGohiKekka;
    }

    /**
     * @return the ichijiTokuten
     */
    public String getIchijiTokuten() {
        return ichijiTokuten;
    }

    /**
     * @param ichijiTokuten the ichijiTokuten to set
     */
    public void setIchijiTokuten(String ichijiTokuten) {
        this.ichijiTokuten = ichijiTokuten;
    }

    /**
     * @return the ichijiManten
     */
    public String getIchijiManten() {
        return ichijiManten;
    }

    /**
     * @param ichijiManten the ichijiManten to set
     */
    public void setIchijiManten(String ichijiManten) {
        this.ichijiManten = ichijiManten;
    }

    /**
     * @return the ichijiKijunten
     */
    public String getIchijiKijunten() {
        return ichijiKijunten;
    }

    /**
     * @param ichijiKijunten the ichijiKijunten to set
     */
    public void setIchijiKijunten(String ichijiKijunten) {
        this.ichijiKijunten = ichijiKijunten;
    }

    /**
     * @return the ichijiGokakuRitsu
     */
    public String getIchijiGokakuRitsu() {
        return ichijiGokakuRitsu;
    }

    /**
     * @param ichijiGokakuRitsu the ichijiGokakuRitsu to set
     */
    public void setIchijiGokakuRitsu(String ichijiGokakuRitsu) {
        this.ichijiGokakuRitsu = ichijiGokakuRitsu;
    }

    /**
     * @return the saishuEtsuranKaishiDate
     */
    public String getSaishuEtsuranKaishiDate() {
        return saishuEtsuranKaishiDate;
    }

    /**
     * @param saishuEtsuranKaishiDate the saishuEtsuranKaishiDate to set
     */
    public void setSaishuEtsuranKaishiDate(String saishuEtsuranKaishiDate) {
        this.saishuEtsuranKaishiDate = saishuEtsuranKaishiDate;
    }

    /**
     * @return the saishuEtsuranKaishiTime
     */
    public String getSaishuEtsuranKaishiTime() {
        return saishuEtsuranKaishiTime;
    }

    /**
     * @param saishuEtsuranKaishiTime the saishuEtsuranKaishiTime to set
     */
    public void setSaishuEtsuranKaishiTime(String saishuEtsuranKaishiTime) {
        this.saishuEtsuranKaishiTime = saishuEtsuranKaishiTime;
    }

    /**
     * @return the saishuEtsuranShuryoDate
     */
    public String getSaishuEtsuranShuryoDate() {
        return saishuEtsuranShuryoDate;
    }

    /**
     * @param saishuEtsuranShuryoDate the saishuEtsuranShuryoDate to set
     */
    public void setSaishuEtsuranShuryoDate(String saishuEtsuranShuryoDate) {
        this.saishuEtsuranShuryoDate = saishuEtsuranShuryoDate;
    }

    /**
     * @return the saishuEtsuranShuryoTime
     */
    public String getSaishuEtsuranShuryoTime() {
        return saishuEtsuranShuryoTime;
    }

    /**
     * @param saishuEtsuranShuryoTime the saishuEtsuranShuryoTime to set
     */
    public void setSaishuEtsuranShuryoTime(String saishuEtsuranShuryoTime) {
        this.saishuEtsuranShuryoTime = saishuEtsuranShuryoTime;
    }

    /**
     * @return the saishuGohiKekka
     */
    public String getSaishuGohiKekka() {
        return saishuGohiKekka;
    }

    /**
     * @param saishuGohiKekka the saishuGohiKekka to set
     */
    public void setSaishuGohiKekka(String saishuGohiKekka) {
        this.saishuGohiKekka = saishuGohiKekka;
    }

    /**
     * @return the saishuTokuten
     */
    public String getSaishuTokuten() {
        return saishuTokuten;
    }

    /**
     * @param saishuTokuten the saishuTokuten to set
     */
    public void setSaishuTokuten(String saishuTokuten) {
        this.saishuTokuten = saishuTokuten;
    }

    /**
     * @return the saishuManten
     */
    public String getSaishuManten() {
        return saishuManten;
    }

    /**
     * @param saishuManten the saishuManten to set
     */
    public void setSaishuManten(String saishuManten) {
        this.saishuManten = saishuManten;
    }

    /**
     * @return the saishuKijunten
     */
    public String getSaishuKijunten() {
        return saishuKijunten;
    }

    /**
     * @param saishuKijunten the saishuKijunten to set
     */
    public void setSaishuKijunten(String saishuKijunten) {
        this.saishuKijunten = saishuKijunten;
    }

    /**
     * @return the saishuGokakuRitsu
     */
    public String getSaishuGokakuRitsu() {
        return saishuGokakuRitsu;
    }

    /**
     * @param saishuGokakuRitsu the saishuGokakuRitsu to set
     */
    public void setSaishuGokakuRitsu(String saishuGokakuRitsu) {
        this.saishuGokakuRitsu = saishuGokakuRitsu;
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    
    /**
     * １次試験得点の表示を返す
     * @return
     */
    public String getIchijiTokutenDisp() {
        if (!CheckUtility.isBlank(ichijiTokuten)) {
            return ichijiTokuten + "点";
        } else {
            return "-";
        }
    }

    /**
     * １次試験満点の表示を返す
     * @return
     */
    public String getIchijiMantenDisp() {
        if (!CheckUtility.isBlank(ichijiManten)) {
            return ichijiManten + "点";
        } else {
            return "-";
        }
    }

    /**
     * １次試験基準点の表示を返す
     * @return
     */
    public String getIchijiKijuntenDisp() {
        if (!CheckUtility.isBlank(ichijiKijunten)) {
            return ichijiKijunten + "点";
        } else {
            return "-";
        }
    }

    /**
     * １次試験合格率の表示を返す
     * @return
     */
    public String getIchijiGokakuRitsuDisp() {
        if (!CheckUtility.isBlank(ichijiGokakuRitsu)) {
            return ichijiGokakuRitsu + "％";
        } else {
            return "-";
        }
    }

    /**
     * 最終結果欄得点の表示を返す
     * @return
     */
    public String getSaishuTokutenDisp() {
        if (!CheckUtility.isBlank(saishuTokuten)) {
            return saishuTokuten + "点";
        } else {
            return "-";
        }
    }

    /**
     * 最終結果欄満点の表示を返す
     * @return
     */
    public String getSaishuMantenDisp() {
        if (!CheckUtility.isBlank(saishuManten)) {
            return saishuManten + "点";
        } else {
            return "-";
        }
    }

    /**
     * 最終結果欄基準点の表示を返す
     * @return
     */
    public String getSaishuKijuntenDisp() {
        if (!CheckUtility.isBlank(saishuKijunten)) {
            return saishuKijunten + "点";
        } else {
            return "-";
        }
    }

    /**
     * 最終結果欄合格率の表示を返す
     * @return
     */
    public String getSaishuGokakuRitsuDisp() {
        if (!CheckUtility.isBlank(saishuGokakuRitsu)) {
            return saishuGokakuRitsu + "％";
        } else {
            return "-";
        }
    }

    /**
     * 現在の日時が１次試験結果閲覧可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanIchijiEtsuran() {
        String fromDate = this.ichijiEtsuranKaishiDate + this.ichijiEtsuranKaishiTime;
        String toDate = this.ichijiEtsuranShuryoDate + this.ichijiEtsuranShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * 現在の日時が最終結果閲覧可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanSaishuEtsuran() {
        String fromDate = this.saishuEtsuranKaishiDate + this.saishuEtsuranKaishiTime;
        String toDate = this.saishuEtsuranShuryoDate + this.saishuEtsuranShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * １次結果閲覧開始日（表示）を返す
     * @return 
     */
    public String getIchijiEtsuranKaishiDateDisp() {
        try {
            String y = this.ichijiEtsuranKaishiDate.substring(0, 4);
            String M = this.ichijiEtsuranKaishiDate.substring(4, 6);
            String d = this.ichijiEtsuranKaishiDate.substring(6, 8);
            return y + "/" + M + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * １次結果閲覧終了日（表示）を返す
     * @return 
     */
    public String getIchijiEtsuranShuryoDateDisp() {
        try {
            String y = this.ichijiEtsuranShuryoDate.substring(0, 4);
            String M = this.ichijiEtsuranShuryoDate.substring(4, 6);
            String d = this.ichijiEtsuranShuryoDate.substring(6, 8);
            return y + "/" + M + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 最終結果閲覧開始日（表示）を返す
     * @return 
     */
    public String getSaishuEtsuranKaishiDateDisp() {
        try {
            String y = this.saishuEtsuranKaishiDate.substring(0, 4);
            String M = this.saishuEtsuranKaishiDate.substring(4, 6);
            String d = this.saishuEtsuranKaishiDate.substring(6, 8);
            return y + "/" + M + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 最終結果閲覧終了日（表示）を返す
     * @return 
     */
    public String getSaishuEtsuranShuryoDateDisp() {
        try {
            String y = this.saishuEtsuranShuryoDate.substring(0, 4);
            String M = this.saishuEtsuranShuryoDate.substring(4, 6);
            String d = this.saishuEtsuranShuryoDate.substring(6, 8);
            return y + "/" + M + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 現在の日時が一次閲覧開始前だったらtrueを返す
     * @return 
     */
    public boolean getIsKikanIchijiBefore() {
        String fromDate = this.ichijiEtsuranKaishiDate + this.ichijiEtsuranKaishiTime;
        
        int ret;
        try {
            ret = DateUtility.dateCompare(fromDate);
        } catch (ParseException ex) {
            return false;
        }
        
        if (ret < 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 現在の日時が最終閲覧開始前だったらtrueを返す
     * @return 
     */
    public boolean getIsKikanSaishuBefore() {
        String fromDate = this.saishuEtsuranKaishiDate + this.saishuEtsuranKaishiTime;
        
        int ret;
        try {
            ret = DateUtility.dateCompare(fromDate);
        } catch (ParseException ex) {
            return false;
        }
        
        if (ret < 0) {
            return true;
        } else {
            return false;
        }
    }


}
