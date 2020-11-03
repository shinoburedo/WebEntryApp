package jp.co.nii.miw.business.service.mypage;

import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.utility.DateUtility;

/**
 * 管理者 BOクラス
 * @author DB管理ツール
 */
public class MypageJoho {

    /** 年度 */
    private String nendo;
    /** 季 */
    private String ki;
    /** 氏名（姓） */
    private String shimeiSei;
    /** 氏名（名） */
    private String shimeiMei;
    /** 画像データ **/
    private File gazoData;
    /** ログイン開始日 */
    private String kikanLoginKaishiDate;
    /** ログイン開始時刻 */
    private String kikanLoginKaishiTime;
    /** ログイン終了日 */
    private String kikanLoginShuryoDate;
    /** ログイン終了時刻 */
    private String kikanLoginShuryoTime;
    /** 申込情報確認開始日 */
    private String kikanConfKaishiDate;
    /** 申込情報確認開始時刻 */
    private String kikanConfKaishiTime;
    /** 申込情報確認終了日 */
    private String kikanConfShuryoDate;
    /** 申込情報確認終了時刻 */
    private String kikanConfShuryoTime;
    /** 申込情報変更開始日 */
    private String kikanUpdKaishiDate;
    /** 申込情報変更開始時刻 */
    private String kikanUpdKaishiTime;
    /** 申込情報変更終了日 */
    private String kikanUpdShuryoDate;
    /** 申込情報変更終了時刻 */
    private String kikanUpdShuryoTime;
    /** 合否結果閲覧開始日 */
    private String kikanGohiEtsuranKaishiDate;
    /** 合否結果閲覧開始時刻 */
    private String kikanGohiEtsuranKaishiTime;
    /** 合否結果閲覧終了日 */
    private String kikanGohiEtsuranShuryoDate;
    /** 合否結果閲覧終了時刻 */
    private String kikanGohiEtsuranShuryoTime;

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
     * 顔写真を取得する
     * @return the gazoData
     */
    public File getGazoData() {
        return gazoData;
    }

    /**
     * 顔写真を設定する
     * @param gazoData 設定するgazoDataのファイル
     */
    public void setGazoData(File gazoData) {
        this.gazoData = gazoData;
    }

    /**
     * @return the kikanLoginKaishiDate
     */
    public String getKikanLoginKaishiDate() {
        return kikanLoginKaishiDate;
    }

    /**
     * @param kikanLoginKaishiDate the kikanLoginKaishiDate to set
     */
    public void setKikanLoginKaishiDate(String kikanLoginKaishiDate) {
        this.kikanLoginKaishiDate = kikanLoginKaishiDate;
    }

    /**
     * @return the kikanLoginKaishiTime
     */
    public String getKikanLoginKaishiTime() {
        return kikanLoginKaishiTime;
    }

    /**
     * @param kikanLoginKaishiTime the kikanLoginKaishiTime to set
     */
    public void setKikanLoginKaishiTime(String kikanLoginKaishiTime) {
        this.kikanLoginKaishiTime = kikanLoginKaishiTime;
    }

    /**
     * @return the kikanLoginShuryoDate
     */
    public String getKikanLoginShuryoDate() {
        return kikanLoginShuryoDate;
    }

    /**
     * @param kikanLoginShuryoDate the kikanLoginShuryoDate to set
     */
    public void setKikanLoginShuryoDate(String kikanLoginShuryoDate) {
        this.kikanLoginShuryoDate = kikanLoginShuryoDate;
    }

    /**
     * @return the kikanLoginShuryoTime
     */
    public String getKikanLoginShuryoTime() {
        return kikanLoginShuryoTime;
    }

    /**
     * @param kikanLoginShuryoTime the kikanLoginShuryoTime to set
     */
    public void setKikanLoginShuryoTime(String kikanLoginShuryoTime) {
        this.kikanLoginShuryoTime = kikanLoginShuryoTime;
    }

    /**
     * @return the kikanConfKaishiDate
     */
    public String getKikanConfKaishiDate() {
        return kikanConfKaishiDate;
    }

    /**
     * @param kikanConfKaishiDate the kikanConfKaishiDate to set
     */
    public void setKikanConfKaishiDate(String kikanConfKaishiDate) {
        this.kikanConfKaishiDate = kikanConfKaishiDate;
    }

    /**
     * @return the kikanConfKaishiTime
     */
    public String getKikanConfKaishiTime() {
        return kikanConfKaishiTime;
    }

    /**
     * @param kikanConfKaishiTime the kikanConfKaishiTime to set
     */
    public void setKikanConfKaishiTime(String kikanConfKaishiTime) {
        this.kikanConfKaishiTime = kikanConfKaishiTime;
    }

    /**
     * @return the kikanConfShuryoDate
     */
    public String getKikanConfShuryoDate() {
        return kikanConfShuryoDate;
    }

    /**
     * @param kikanConfShuryoDate the kikanConfShuryoDate to set
     */
    public void setKikanConfShuryoDate(String kikanConfShuryoDate) {
        this.kikanConfShuryoDate = kikanConfShuryoDate;
    }

    /**
     * @return the kikanConfShuryoTime
     */
    public String getKikanConfShuryoTime() {
        return kikanConfShuryoTime;
    }

    /**
     * @param kikanConfShuryoTime the kikanConfShuryoTime to set
     */
    public void setKikanConfShuryoTime(String kikanConfShuryoTime) {
        this.kikanConfShuryoTime = kikanConfShuryoTime;
    }

    /**
     * @return the kikanUpdKaishiDate
     */
    public String getKikanUpdKaishiDate() {
        return kikanUpdKaishiDate;
    }

    /**
     * @param kikanUpdKaishiDate the kikanUpdKaishiDate to set
     */
    public void setKikanUpdKaishiDate(String kikanUpdKaishiDate) {
        this.kikanUpdKaishiDate = kikanUpdKaishiDate;
    }

    /**
     * @return the kikanUpdKaishiTime
     */
    public String getKikanUpdKaishiTime() {
        return kikanUpdKaishiTime;
    }

    /**
     * @param kikanUpdKaishiTime the kikanUpdKaishiTime to set
     */
    public void setKikanUpdKaishiTime(String kikanUpdKaishiTime) {
        this.kikanUpdKaishiTime = kikanUpdKaishiTime;
    }

    /**
     * @return the kikanUpdShuryoDate
     */
    public String getKikanUpdShuryoDate() {
        return kikanUpdShuryoDate;
    }

    /**
     * @param kikanUpdShuryoDate the kikanUpdShuryoDate to set
     */
    public void setKikanUpdShuryoDate(String kikanUpdShuryoDate) {
        this.kikanUpdShuryoDate = kikanUpdShuryoDate;
    }

    /**
     * @return the kikanUpdShuryoTime
     */
    public String getKikanUpdShuryoTime() {
        return kikanUpdShuryoTime;
    }

    /**
     * @param kikanUpdShuryoTime the kikanUpdShuryoTime to set
     */
    public void setKikanUpdShuryoTime(String kikanUpdShuryoTime) {
        this.kikanUpdShuryoTime = kikanUpdShuryoTime;
    }

    /**
     * @return the kikanGohiEtsuranKaishiDate
     */
    public String getKikanGohiEtsuranKaishiDate() {
        return kikanGohiEtsuranKaishiDate;
    }

    /**
     * @param kikanGohiEtsuranKaishiDate the kikanGohiEtsuranKaishiDate to set
     */
    public void setKikanGohiEtsuranKaishiDate(String kikanGohiEtsuranKaishiDate) {
        this.kikanGohiEtsuranKaishiDate = kikanGohiEtsuranKaishiDate;
    }

    /**
     * @return the kikanGohiEtsuranKaishiTime
     */
    public String getKikanGohiEtsuranKaishiTime() {
        return kikanGohiEtsuranKaishiTime;
    }

    /**
     * @param kikanGohiEtsuranKaishiTime the kikanGohiEtsuranKaishiTime to set
     */
    public void setKikanGohiEtsuranKaishiTime(String kikanGohiEtsuranKaishiTime) {
        this.kikanGohiEtsuranKaishiTime = kikanGohiEtsuranKaishiTime;
    }

    /**
     * @return the kikanGohiEtsuranShuryoDate
     */
    public String getKikanGohiEtsuranShuryoDate() {
        return kikanGohiEtsuranShuryoDate;
    }

    /**
     * @param kikanGohiEtsuranShuryoDate the kikanGohiEtsuranShuryoDate to set
     */
    public void setKikanGohiEtsuranShuryoDate(String kikanGohiEtsuranShuryoDate) {
        this.kikanGohiEtsuranShuryoDate = kikanGohiEtsuranShuryoDate;
    }

    /**
     * @return the kikanGohiEtsuranShuryoTime
     */
    public String getKikanGohiEtsuranShuryoTime() {
        return kikanGohiEtsuranShuryoTime;
    }

    /**
     * @param kikanGohiEtsuranShuryoTime the kikanGohiEtsuranShuryoTime to set
     */
    public void setKikanGohiEtsuranShuryoTime(String kikanGohiEtsuranShuryoTime) {
        this.kikanGohiEtsuranShuryoTime = kikanGohiEtsuranShuryoTime;
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 氏名（姓+名）の表示を返す
     * @return 
     */
    public String getShimeiDisp() {
        return this.shimeiSei + "　" + this.shimeiMei;
    }

    /**
     * 年度・季の表示を返す
     * @return 
     */
    public String getNendoKiDisp() {
        return this.nendo + "年度";
    }

    /**
     * ログイン開始日を
     * YYYY/MM/DD形式で返す
     * @return 
     */
    public String getKikanLoginKaishiDateDisp() {
        try {
            String y = this.kikanLoginKaishiDate.substring(0, 4);
            String m = this.kikanLoginKaishiDate.substring(4, 6);
            String d = this.kikanLoginKaishiDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ログイン開始時刻を
     * HH:mm:ss形式で返す
     * @return 
     */
    public String getKikanLoginKaishiTimeDisp() {
        try {
            String h = this.kikanLoginKaishiTime.substring(0, 2);
            String m = this.kikanLoginKaishiTime.substring(2, 4);
            String s = this.kikanLoginKaishiTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ログイン終了日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanLoginShuryoDateDisp() {
        try {
            String y = this.kikanLoginShuryoDate.substring(0, 4);
            String m = this.kikanLoginShuryoDate.substring(4, 6);
            String d = this.kikanLoginShuryoDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ログイン終了時刻を
     * HH:mm:ss形式で返す
     * @return 
     */
    public String getKikanLoginShuryoTimeDisp() {
        try {
            String h = this.kikanLoginShuryoTime.substring(0, 2);
            String m = this.kikanLoginShuryoTime.substring(2, 4);
            String s = this.kikanLoginShuryoTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報確認開始日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanConfKaishiDateDisp() {
        try {
            String y = this.kikanConfKaishiDate.substring(0, 4);
            String m = this.kikanConfKaishiDate.substring(4, 6);
            String d = this.kikanConfKaishiDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報確認開始時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanConfKaishiTimeDisp() {
        try {
            String h = this.kikanConfKaishiTime.substring(0, 2);
            String m = this.kikanConfKaishiTime.substring(2, 4);
            String s = this.kikanConfKaishiTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報確認終了日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanConfShuryoDateDisp() {
        try {
            String y = this.kikanConfShuryoDate.substring(0, 4);
            String m = this.kikanConfShuryoDate.substring(4, 6);
            String d = this.kikanConfShuryoDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報確認終了時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanConfShuryoTimeDisp() {
        try {
            String h = this.kikanConfShuryoTime.substring(0, 2);
            String m = this.kikanConfShuryoTime.substring(2, 4);
            String s = this.kikanConfShuryoTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報変更開始日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanUpdKaishiDateDisp() {
        try {
            String y = this.kikanUpdKaishiDate.substring(0, 4);
            String m = this.kikanUpdKaishiDate.substring(4, 6);
            String d = this.kikanUpdKaishiDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報変更開始時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanUpdKaishiTimeDisp() {
        try {
            String h = this.kikanUpdKaishiTime.substring(0, 2);
            String m = this.kikanUpdKaishiTime.substring(2, 4);
            String s = this.kikanUpdKaishiTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報変更終了日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanUpdShuryoDateDisp() {
        try {
            String y = this.kikanUpdShuryoDate.substring(0, 4);
            String m = this.kikanUpdShuryoDate.substring(4, 6);
            String d = this.kikanUpdShuryoDate.substring(6, 8);
            return y + "/" + m + "/" + d + " " + MiwStringUtility.formatTimeColon(kikanUpdShuryoTime);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込情報変更終了時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanUpdShuryoTimeDisp() {
        try {
            String h = this.kikanUpdShuryoTime.substring(0, 2);
            String m = this.kikanUpdShuryoTime.substring(2, 4);
            String s = this.kikanUpdShuryoTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 合否結果閲覧開始日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanGohiEtsuranKaishiDateDisp() {
        try {
            String y = this.kikanGohiEtsuranKaishiDate.substring(0, 4);
            String m = this.kikanGohiEtsuranKaishiDate.substring(4, 6);
            String d = this.kikanGohiEtsuranKaishiDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 合否結果閲覧開始時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanGohiEtsuranKaishiTimeDisp() {
        try {
            String h = this.kikanGohiEtsuranKaishiTime.substring(0, 2);
            String m = this.kikanGohiEtsuranKaishiTime.substring(2, 4);
            String s = this.kikanGohiEtsuranKaishiTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 合否結果閲覧終了日を
     * YYYY/MM/DD形式で返す
     * @return
     */
    public String getKikanGohiEtsuranShuryoDateDisp() {
        try {
            String y = this.kikanGohiEtsuranShuryoDate.substring(0, 4);
            String m = this.kikanGohiEtsuranShuryoDate.substring(4, 6);
            String d = this.kikanGohiEtsuranShuryoDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 合否結果閲覧終了時刻を
     * HH:mm:ss形式で返す
     * @return
     */
    public String getKikanGohiEtsuranShuryoTimeDisp() {
        try {
            String h = this.kikanGohiEtsuranShuryoTime.substring(0, 2);
            String m = this.kikanGohiEtsuranShuryoTime.substring(2, 4);
            String s = this.kikanGohiEtsuranShuryoTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 現在の日時がログイン可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanLogin() {
        String fromDate = this.kikanLoginKaishiDate + this.kikanLoginKaishiTime;
        String toDate = this.kikanLoginShuryoDate + this.kikanLoginShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * 現在の日時が申込情報確認可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanConf() {
        String fromDate = this.kikanConfKaishiDate + this.kikanConfKaishiTime;
        String toDate = this.kikanConfShuryoDate + this.kikanConfShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * 現在の日時が申込情報変更可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanUpd() {
        String fromDate = this.kikanUpdKaishiDate + this.kikanUpdKaishiTime;
        String toDate = this.kikanUpdShuryoDate + this.kikanUpdShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * 現在の日時が合否結果閲覧可能期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsKikanGohiEtsuran() {
        String fromDate = this.kikanGohiEtsuranKaishiDate + this.kikanGohiEtsuranKaishiTime;
        String toDate = this.kikanGohiEtsuranShuryoDate + this.kikanGohiEtsuranShuryoTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }
}
