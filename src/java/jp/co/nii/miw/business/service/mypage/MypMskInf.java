package jp.co.nii.miw.business.service.mypage;

import java.util.LinkedHashMap;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.utility.DateUtility;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル: MskInf
 * 説明: 申込者情報のデータbean
 * 著作権: Copyright (c) 2011
 * 会社名: 日本情報産業株式会社
 * @author t.yamaguchi
 */
public class MypMskInf {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 年度 **/
    private String nendo;
    /** 回数 **/
    private String kaisu;
    /** 季 */
    private String ki;
    /** 申込受付番号 */
    private String moshikomiUketsukeNo;
    /** ユーザーＩＤ */
    private String userId;
    /** 仮受付日 */
    private String kariUketsukeBi;
    /** 仮受付時刻 */
    private String kariUketsukeTime;
    /** 申込完了日 */
    private String moshikomiFinishBi;
    /** 申込完了時刻 */
    private String moshikomiFinishTime;
    /** 更新日付 */
    private String koshinDate;
    /** 更新時刻 */
    private String koshinTime;
    /** 更新ユーザーＩＤ */
    private String koshinUserId;
    /** 個人団体区分 */
    private String kojinDantaiKbn;
    /** 申込媒体区分 */
    private String moshikomiBaitaiKbn;
    /** 決済方法区分 */
    private String kessaiHohoKbn;
    /** 決済状況区分 */
    private String kessaiJokyoKbn;
    /** 手続状況区分 */
    private String tetsudukiJokyoKbn;
    /** 団体申込受付番号 */
    private String dantaiMoshikomiUketsukeNo;
    /** 団体名 */
    private String dantaiName;
    /** イベントコード */
    private String eventCode;
    /** 受験級 */
    private String jukenkyu;
    /** 試験地 */
    private String shikenchiCode;
    /** サンプル受験手数料申込フラグ */
    private String franceJukenryoMoshikomiFlg;
    /** 氏名（姓）カナ */
    private String shimeiSeiKana;
    /** 氏名（名）カナ */
    private String shimeiMeiKana;
    /** 氏名（姓） */
    private String shimeiSei;
    /** 氏名（名） */
    private String shimeiMei;
    /** 性別コード */
    private String sexCode;
    /** 生年月日（元号） */
    private String birthdayEra;
    /** 生年月日（年） */
    private String birthdayYear;
    /** 生年月日（月） */
    private String birthdayMonth;
    /** 生年月日（日） */
    private String birthdayDay;
    /** 郵便番号１ */
    private String yubinNo1;
    /** 郵便番号２ */
    private String yubinNo2;
    /** 都道府県 **/
    private String todofuken;
    /** 住所１ */
    private String jusho1;
    /** 住所２ */
    private String jusho2;
    /** 住所３ */
    private String jusho3;
    /** 住所４ */
    private String jusho4;
    /** TEL */
    private String telNo;
    /** 内線番号 */
    private String extNo;
    /** その他連絡先電話番号 */
    private String cellphoneNo;
    /** メールアドレス */
    private String mailAddress;
    /** 確認用メールアドレス */
    private String mailAddressKakunin;
    /** 画像ＩＤ **/
    private String gazoId;
    /* 画像補正依頼区分 */
    private String hoseiIraiKbn;
    /* 画像補正期限日 */
    private String hoseiIraiKigenBi;
    /** 補正対応日 */
    private String hoseiTaioBi;
    /** 補正対応時刻 */
    private String hoseiTaioTime;
    /** 補正完了日 */
    private String hoseiFinishBi;
    /** 補正完了時刻 */
    private String hoseiFinishTime;
    /* 画像更新有無フラグ */
    private String gazoUpdFlg;
    /** 受講経験 **/
    private String jukoKeiken;
    /** 実務経験 **/
    private String jitsumuKeiken;
    /** 決済金額 */
    private String kessaiKingaku;
    /** 決済コンビニ種別 */
    private String kessaiConvenienceShubetsu;
    /** 決済コンビニ払込票ＵＲＬ */
    private String kessaiConvenienceHaraikomihyoUrl;
    /** 決済期限日 */
    private String kessaiKigenBi;
    /** パスワード質問コード１ */
    private String passwdQuestionCode1;
    /** パスワード回答１ */
    private String passwdAnswer1;
    /** 管理メモ */
    private String kanriMemo;
    /** 受験科目 **/
    private LinkedHashMap<String, String> eventCodeList;
    
    public MypMskInf() {
        this.moshikomiUketsukeNo = "";
        this.userId = "";
        this.kariUketsukeBi = "";
        this.kariUketsukeTime = "";
        this.moshikomiFinishBi = "";
        this.moshikomiFinishTime = "";
        this.koshinDate = "";
        this.koshinTime = "";
        this.koshinUserId = "";
        this.kojinDantaiKbn = "";
        this.moshikomiBaitaiKbn = "";
        this.kessaiHohoKbn = "";
        this.kessaiJokyoKbn = "";
        this.tetsudukiJokyoKbn = "";
        this.dantaiMoshikomiUketsukeNo = "";
        this.dantaiName = "";
        this.eventCode = "";
        this.jukenkyu = "";
        this.shikenchiCode = "";
        this.franceJukenryoMoshikomiFlg = "";
        this.shimeiSeiKana = "";
        this.shimeiMeiKana = "";
        this.shimeiSei = "";
        this.shimeiMei = "";
        this.sexCode = "";
        this.birthdayEra = "";
        this.birthdayYear = "";
        this.birthdayMonth = "";
        this.birthdayDay = "";
        this.yubinNo1 = "";
        this.yubinNo2 = "";
        this.todofuken = "";
        this.jusho1 = "";
        this.jusho2 = "";
        this.jusho3 = "";
        this.telNo = "";
        this.cellphoneNo = "";
        this.mailAddress = "";
        this.mailAddressKakunin = "";
        this.kessaiKingaku = "";
        this.kessaiConvenienceShubetsu = "";
        this.kessaiConvenienceHaraikomihyoUrl = "";
        this.kessaiKigenBi = "";
        this.passwdQuestionCode1 = "";
        this.passwdAnswer1 = "";
        this.kanriMemo = "";

        //エラー箇所表示用
        this.errors = new ActionMessages();
    }

    public MypMskInf(String ki) {
        this();
        this.ki = ki;
    }

    /**
     * 年度を設定する
     * @param nendo 設定するidの値
     */
    public void setNendo(String nendo) {
        this.nendo = nendo;
    }

    /**
     * 年度を取得する
     * @return nendo
     */
    public String getNendo() {
        return nendo;
    }

    /**
     * 回数を設定する
     * @param kaisu 設定するkiの値
     */
    public void setKaisu(String kaisu) {
        this.kaisu = kaisu;
    }

    /**
     * 回数を取得する
     * @return kaisu
     */
    public String getKaisu() {
        return kaisu;
    }

    /**
     * @return the moshikomiUketsukeNo
     */
    public String getMoshikomiUketsukeNo() {
        return moshikomiUketsukeNo;
    }

    /**
     * @param moshikomiUketsukeNo the moshikomiUketsukeNo to set
     */
    public void setMoshikomiUketsukeNo(String moshikomiUketsukeNo) {
        this.moshikomiUketsukeNo = moshikomiUketsukeNo;
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
     * @return the kariUketsukeBi
     */
    public String getKariUketsukeBi() {
        return kariUketsukeBi;
    }

    /**
     * @param kariUketsukeBi the kariUketsukeBi to set
     */
    public void setKariUketsukeBi(String kariUketsukeBi) {
        this.kariUketsukeBi = kariUketsukeBi;
    }

    /**
     * @return the kariUketsukeTime
     */
    public String getKariUketsukeTime() {
        return kariUketsukeTime;
    }

    /**
     * @param kariUketsukeTime the kariUketsukeTime to set
     */
    public void setKariUketsukeTime(String kariUketsukeTime) {
        this.kariUketsukeTime = kariUketsukeTime;
    }

    /**
     * @return the moshikomiFinishBi
     */
    public String getMoshikomiFinishBi() {
        return moshikomiFinishBi;
    }

    /**
     * @param moshikomiFinishBi the moshikomiFinishBi to set
     */
    public void setMoshikomiFinishBi(String moshikomiFinishBi) {
        this.moshikomiFinishBi = moshikomiFinishBi;
    }

    /**
     * @return the moshikomiFinishTime
     */
    public String getMoshikomiFinishTime() {
        return moshikomiFinishTime;
    }

    /**
     * @param moshikomiFinishTime the moshikomiFinishTime to set
     */
    public void setMoshikomiFinishTime(String moshikomiFinishTime) {
        this.moshikomiFinishTime = moshikomiFinishTime;
    }

    /**
     * @return the koshinDate
     */
    public String getKoshinDate() {
        return koshinDate;
    }

    /**
     * @param koshinDate the koshinDate to set
     */
    public void setKoshinDate(String koshinDate) {
        this.koshinDate = koshinDate;
    }

    /**
     * @return the koshinTime
     */
    public String getKoshinTime() {
        return koshinTime;
    }

    /**
     * @param koshinTime the koshinTime to set
     */
    public void setKoshinTime(String koshinTime) {
        this.koshinTime = koshinTime;
    }

    /**
     * @return the koshinUserId
     */
    public String getKoshinUserId() {
        return koshinUserId;
    }

    /**
     * @param koshinUserId the koshinUserId to set
     */
    public void setKoshinUserId(String koshinUserId) {
        this.koshinUserId = koshinUserId;
    }

    /**
     * @return the kojinDantaiKbn
     */
    public String getKojinDantaiKbn() {
        return kojinDantaiKbn;
    }

    /**
     * @param kojinDantaiKbn the kojinDantaiKbn to set
     */
    public void setKojinDantaiKbn(String kojinDantaiKbn) {
        this.kojinDantaiKbn = kojinDantaiKbn;
    }

    /**
     * @return the moshikomiBaitaiKbn
     */
    public String getMoshikomiBaitaiKbn() {
        return moshikomiBaitaiKbn;
    }

    /**
     * @param moshikomiBaitaiKbn the moshikomiBaitaiKbn to set
     */
    public void setMoshikomiBaitaiKbn(String moshikomiBaitaiKbn) {
        this.moshikomiBaitaiKbn = moshikomiBaitaiKbn;
    }

    /**
     * @return the kessaiHohoKbn
     */
    public String getKessaiHohoKbn() {
        return kessaiHohoKbn;
    }

    /**
     * @param kessaiHohoKbn the kessaiHohoKbn to set
     */
    public void setKessaiHohoKbn(String kessaiHohoKbn) {
        this.kessaiHohoKbn = kessaiHohoKbn;
    }

    /**
     * @return the kessaiJokyoKbn
     */
    public String getKessaiJokyoKbn() {
        return kessaiJokyoKbn;
    }

    /**
     * @param kessaiJokyoKbn the kessaiJokyoKbn to set
     */
    public void setKessaiJokyoKbn(String kessaiJokyoKbn) {
        this.kessaiJokyoKbn = kessaiJokyoKbn;
    }

    /**
     * @return the tetsudukiJokyoKbn
     */
    public String getTetsudukiJokyoKbn() {
        return tetsudukiJokyoKbn;
    }

    /**
     * @param tetsudukiJokyoKbn the tetsudukiJokyoKbn to set
     */
    public void setTetsudukiJokyoKbn(String tetsudukiJokyoKbn) {
        this.tetsudukiJokyoKbn = tetsudukiJokyoKbn;
    }

    /**
     * @return the dantaiMoshikomiUketsukeNo
     */
    public String getDantaiMoshikomiUketsukeNo() {
        return dantaiMoshikomiUketsukeNo;
    }

    /**
     * @param dantaiMoshikomiUketsukeNo the dantaiMoshikomiUketsukeNo to set
     */
    public void setDantaiMoshikomiUketsukeNo(String dantaiMoshikomiUketsukeNo) {
        this.dantaiMoshikomiUketsukeNo = dantaiMoshikomiUketsukeNo;
    }

    /**
     * @return the dantaiName
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * @param dantaiName the dantaiName to set
     */
    public void setDantaiName(String dantaiName) {
        this.dantaiName = dantaiName;
    }

    /**
     * @return the eventCode
     */
    public String getEventCode() {
        return eventCode;
    }

    /**
     * @param eventCode the eventCode to set
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * 受験科目名称を取得する
     * @return 受験科目名称
     */
    public String getEventCodeDisp() {
        String ret = "";

        for (int i = 0; i < MiwConstants.DISP_JUKEN_KAMOKU.length; i++) {
            if (eventCode.equals(MiwConstants.DISP_JUKEN_KAMOKU[i][0])) {
                ret = MiwConstants.DISP_JUKEN_KAMOKU[i][1];
                break;
            }
        }
        return ret;
    }

    /**
     * @return the jukenkyu
     */
    public String getJukenkyu() {
        return jukenkyu;
    }

    /**
     * @param jukenkyu the jukenkyu to set
     */
    public void setJukenkyu(String jukenkyu) {
        this.jukenkyu = jukenkyu;
    }

    /**
     * @return the shikenchiCode
     */
    public String getShikenchiCode() {
        return shikenchiCode;
    }

    /**
     * @param shikenchiCode1 the shikenchiCode1 to set
     */
    public void setShikenchiCode(String shikenchiCode) {
        this.shikenchiCode = shikenchiCode;
    }

    /**
     * 受験希望地名称を取得する
     * @return 受験希望地名称
     */
    public String getShikenchiCodeDisp() {
        String ret = "";
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        ret = mskTorokuServ.selectShikenchiName(nendo, kaisu, shikenchiCode);

        return ret;
    }

    /**
     * @return the franceJukenryoMoshikomiFlg
     */
    public String getFranceJukenryoMoshikomiFlg() {
        return franceJukenryoMoshikomiFlg;
    }

    /**
     * @param franceJukenryoMoshikomiFlg the franceJukenryoMoshikomiFlg to set
     */
    public void setFranceJukenryoMoshikomiFlg(String franceJukenryoMoshikomiFlg) {
        this.franceJukenryoMoshikomiFlg = franceJukenryoMoshikomiFlg;
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
     * @return the yubinNo1
     */
    public String getYubinNo1() {
        return yubinNo1;
    }

    /**
     * @param yubinNo1 the yubinNo1 to set
     */
    public void setYubinNo1(String yubinNo1) {
        this.yubinNo1 = yubinNo1;
    }

    /**
     * @return the yubinNo2
     */
    public String getYubinNo2() {
        return yubinNo2;
    }

    /**
     * @param yubinNo2 the yubinNo2 to set
     */
    public void setYubinNo2(String yubinNo2) {
        this.yubinNo2 = yubinNo2;
    }

    /**
     * 都道府県を取得する
     * @return todofuken
     */
    public String getTodofuken() {
        return todofuken;
    }

    /**
     * 都道府県を設定する
     * @param todofuken todofuken
     */
    public void setTodofuken(String todofuken) {
        this.todofuken = todofuken;
    }

    /**
     * @return the jusho1
     */
    public String getJusho1() {
        return jusho1;
    }

    /**
     * @param jusho1 the jusho1 to set
     */
    public void setJusho1(String jusho1) {
        this.jusho1 = jusho1;
    }

    /**
     * @return the jusho2
     */
    public String getJusho2() {
        return jusho2;
    }

    /**
     * @param jusho2 the jusho2 to set
     */
    public void setJusho2(String jusho2) {
        this.jusho2 = jusho2;
    }

    /**
     * @return the jusho3
     */
    public String getJusho3() {
        return jusho3;
    }

    /**
     * @param jusho3 the jusho3 to set
     */
    public void setJusho3(String jusho3) {
        this.jusho3 = jusho3;
    }

    /**
     * @return the jusho4
     */
    public String getJusho4() {
        return jusho4;
    }

    /**
     * @param jusho4 the jusho4 to set
     */
    public void setJusho4(String jusho4) {
        this.jusho4 = jusho4;
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

    /**
     * @return the sonotaRenrakusakiTelNo
     */
    public String getCellphoneNo() {
        return cellphoneNo;
    }

    /**
     * @param sonotaRenrakusakiTelNo the sonotaRenrakusakiTelNo1 to set
     */
    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
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
     * 画像ＩＤを取得する
     * @return gazoId
     */
    public String getGazoId() {
        return gazoId;
    }

    /**
     * 画像ＩＤを設定する
     * @param gazoId 設定するgazoIdの値
     */
    public void setGazoId(String gazoId) {
        this.gazoId = gazoId;
    }

    /**
     * 画像補正依頼区分を取得する
     * @return gazoId
     */
    public String getHoseiIraiKbn() {
        return hoseiIraiKbn;
    }

    /**
     * 画像補正依頼区分を設定する
     * @param hoseiIraiKbn 設定するhoseiIraiKbnの値
     */
    public void setHoseiIraiKbn(String hoseiIraiKbn) {
        this.hoseiIraiKbn = hoseiIraiKbn;
    }

    /**
     * 画像補正依頼区分（表示用）を取得する
     * @return 
     */
    public String getHoseiIraiKbnDisp() {
        return CodeValueName.getHoseiIraiKbnName(getHoseiIraiKbn());
    }

    /**
     * 画像補正期限日を取得する
     * @return gazoId
     */
    public String getHoseiIraiKigenBi() {
        return hoseiIraiKigenBi;
    }

    /**
     * 画像補正期限日を設定する
     * @param hoseiIraiKigenDate 設定するhoseiIraiKigenDateの値
     */
    public void setHoseiIraiKigenBi(String hoseiIraiKigenBi) {
        this.hoseiIraiKigenBi = hoseiIraiKigenBi;
    }

    /**
     * 画像補正期限の表示を返す
     * @return 
     */
    public String getHoseiIraiKigenBiDisp() {
        try {
            String y = this.hoseiIraiKigenBi.substring(0, 4);
            String m = this.hoseiIraiKigenBi.substring(4, 6);
            String d = this.hoseiIraiKigenBi.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 補正対応日 を取得する。
     * @return 補正対応日
     */
    public String getHoseiTaioBi() {
        return hoseiTaioBi;
    }

    /**
     * 補正対応日 をセットする。
     * @param hoseiTaioBi 補正対応日
     */
    public void setHoseiTaioBi(String hoseiTaioBi) {
        this.hoseiTaioBi = hoseiTaioBi;
    }

    /**
     * 補正対応時刻 を取得する。
     * @return 補正対応時刻
     */
    public String getHoseiTaioTime() {
        return hoseiTaioTime;
    }

    /**
     * 補正対応時刻 をセットする。
     * @param hoseiTaioTime 補正対応時刻
     */
    public void setHoseiTaioTime(String hoseiTaioTime) {
        this.hoseiTaioTime = hoseiTaioTime;
    }

    /**
     * 補正対応日時の表示を返す
     * @return 
     */
    public String getHoseiTaioDateTimeDisp() {
        try {
            String y = this.hoseiTaioBi.substring(0, 4);
            String m = this.hoseiTaioBi.substring(4, 6);
            String d = this.hoseiTaioBi.substring(6, 8);

            String hh = this.hoseiTaioTime.substring(0, 2);
            String mm = this.hoseiTaioTime.substring(2, 4);
            String ss = this.hoseiTaioTime.substring(4, 6);

            return y + "/" + m + "/" + d + " " + hh + ":" + mm + ":" + ss;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 補正完了日 を取得する。
     * @return 補正完了日
     */
    public String getHoseiFinishBi() {
        return hoseiFinishBi;
    }

    /**
     * 補正完了日 をセットする。
     * @param hoseiFinishBi 補正完了日
     */
    public void setHoseiFinishBi(String hoseiFinishBi) {
        this.hoseiFinishBi = hoseiFinishBi;
    }

    /**
     * 補正完了時刻 を取得する。
     * @return 補正完了時刻
     */
    public String getHoseiFinishTime() {
        return hoseiFinishTime;
    }

    /**
     * 補正完了時刻 をセットする。
     * @param hoseiFinishTime 補正完了時刻
     */
    public void setHoseiFinishTime(String hoseiFinishTime) {
        this.hoseiFinishTime = hoseiFinishTime;
    }

    /**
     * 補正完了日時の表示を返す
     * @return 
     */
    public String getHoseiFinishDateTimeDisp() {
        try {
            String y = this.hoseiFinishBi.substring(0, 4);
            String m = this.hoseiFinishBi.substring(4, 6);
            String d = this.hoseiFinishBi.substring(6, 8);

            String hh = this.hoseiFinishTime.substring(0, 2);
            String mm = this.hoseiFinishTime.substring(2, 4);
            String ss = this.hoseiFinishTime.substring(4, 6);

            return y + "/" + m + "/" + d + " " + hh + ":" + mm + ":" + ss;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 画像更新有無フラグを取得する
     * @return jukoKeiken
     */
    public String getGazoUpdFlg() {
        return gazoUpdFlg;
    }

    /**
     * 画像更新有無フラグを設定する
     * @param gazoUpdFlg 設定するgazoUpdFlgの値
     */
    public void setGazoUpdFlg(String gazoUpdFlg) {
        this.gazoUpdFlg = gazoUpdFlg;
    }

    /**
     * 受講経験を取得する
     * @return jukoKeiken
     */
    public String getJukoKeiken() {
        return jukoKeiken;
    }

    /**
     * 受講経験を設定する
     * @param jukoKeiken 設定するjukoKeikenの値
     */
    public void setJukoKeiken(String jukoKeiken) {
        this.jukoKeiken = jukoKeiken;
    }

    /**
     * 受講経験名称を取得する
     * @return jukoKeiken
     */
    public String getJukoKeikenDisp() {
        String ret = "";
        ret = CodeValueName.getJukoKeikenCodeName(jukoKeiken);
        return ret;
    }

    /**
     * 実務経験を取得する
     * @return jitsumuKeiken
     */
    public String getJitsumuKeiken() {
        return jitsumuKeiken;
    }

    /**
     * 実務経験を設定する
     * @param jitsumuKeiken 設定するmailAddressの値
     */
    public void setJitsumuKeiken(String jitsumuKeiken) {
        this.jitsumuKeiken = jitsumuKeiken;
    }

    /**
     * 実務経験名称を取得する
     * @return jukoKeiken
     */
    public String getJitsumuKeikenDisp() {
        String ret = "";
        ret = CodeValueName.getJitsumuKeikenCodeName(jitsumuKeiken);
        return ret;
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
     * @return the kessaiKingaku
     */
    public String getKessaiKingaku() {
        return kessaiKingaku;
    }

    /**
     * @param kessaiKingaku the kessaiKingaku to set
     */
    public void setKessaiKingaku(String kessaiKingaku) {
        this.kessaiKingaku = kessaiKingaku;
    }

    /**
     * @return the kessaiConvenienceShubetsu
     */
    public String getKessaiConvenienceShubetsu() {
        return kessaiConvenienceShubetsu;
    }

    /**
     * @param kessaiConvenienceShubetsu the kessaiConvenienceShubetsu to set
     */
    public void setKessaiConvenienceShubetsu(String kessaiConvenienceShubetsu) {
        this.kessaiConvenienceShubetsu = kessaiConvenienceShubetsu;
    }

    /**
     * @return the kessaiConvenienceHaraikomihyoUrl
     */
    public String getKessaiConvenienceHaraikomihyoUrl() {
        return kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * @param kessaiConvenienceHaraikomihyoUrl the kessaiConvenienceHaraikomihyoUrl to set
     */
    public void setKessaiConvenienceHaraikomihyoUrl(String kessaiConvenienceHaraikomihyoUrl) {
        this.kessaiConvenienceHaraikomihyoUrl = kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * @return the kessaiKigenBi
     */
    public String getKessaiKigenBi() {
        return kessaiKigenBi;
    }

    /**
     * @param kessaiKigenBi the kessaiKigenBi to set
     */
    public void setKessaiKigenBi(String kessaiKigenBi) {
        this.kessaiKigenBi = kessaiKigenBi;
    }

    /**
     * @return the passwdQuestionCode1
     */
    public String getPasswdQuestionCode1() {
        return passwdQuestionCode1;
    }

    /**
     * @param passwdQuestionCode1 the passwdQuestionCode1 to set
     */
    public void setPasswdQuestionCode1(String passwdQuestionCode1) {
        this.passwdQuestionCode1 = passwdQuestionCode1;
    }

    /**
     * @return the passwdAnswer1
     */
    public String getPasswdAnswer1() {
        return passwdAnswer1;
    }

    /**
     * @param passwdAnswer1 the passwdAnswer1 to set
     */
    public void setPasswdAnswer1(String passwdAnswer1) {
        this.passwdAnswer1 = passwdAnswer1;
    }

    /**
     * @return the kanriMemo
     */
    public String getKanriMemo() {
        return kanriMemo;
    }

    /**
     * @param kanriMemo the kanriMemo to set
     */
    public void setKanriMemo(String kanriMemo) {
        this.kanriMemo = kanriMemo;
    }

    /**
     * 受験科目リスト取得
     * @return 
     */
    public LinkedHashMap<String, String> getEventCodeList() {
        return eventCodeList;
    }

    /**
     * 受験科目リストをセット
     * @param eventCodeList 
     */
    public void setEventCodeList(LinkedHashMap<String, String> eventCodeList) {
        this.eventCodeList = eventCodeList;
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 各値のスペースを削除する
     */
    public void deleteSpace() {
        this.setUserId(StringUtility.removeSpace(this.getUserId()));
        this.setShimeiSeiKana(StringUtility.removeSpace(this.getShimeiSeiKana()));
        this.setShimeiMeiKana(StringUtility.removeSpace(this.getShimeiMeiKana()));
        this.setShimeiSei(StringUtility.removeSpace(this.getShimeiSei()));
        this.setShimeiMei(StringUtility.removeSpace(this.getShimeiMei()));
        this.setTelNo(StringUtility.removeSpace(this.getTelNo()));
        this.setExtNo(StringUtility.removeSpace(this.getExtNo()));
        this.setCellphoneNo(StringUtility.removeSpace(this.getCellphoneNo()));
        this.setYubinNo1(StringUtility.removeSpace(this.getYubinNo1()));
        this.setYubinNo2(StringUtility.removeSpace(this.getYubinNo2()));
        this.setDantaiMoshikomiUketsukeNo(StringUtility.removeSpace(this.getDantaiMoshikomiUketsukeNo()));
        this.setJusho1(StringUtility.removeEdgeSpace(this.getJusho1()));
        this.setJusho2(StringUtility.removeEdgeSpace(this.getJusho2()));
        this.setJusho3(StringUtility.removeEdgeSpace(this.getJusho3()));
        this.setJusho4(StringUtility.removeEdgeSpace(this.getJusho4()));
        this.setMailAddress(StringUtility.removeSpace(this.getMailAddress()));
        this.setMailAddressKakunin(StringUtility.removeSpace(this.getMailAddressKakunin()));
        this.setPasswdAnswer1(StringUtility.removeSpace(this.getPasswdAnswer1())); 
    }

    /**
     * 氏名（姓+名）の表示を返す
     * @return 
     */
    public String getShimeiDisp() {
        return this.shimeiSei + "　" + this.shimeiMei;
    }

    /**
     * 郵便番号を
     * 0000000形式で返す
     * @return 
     */
    public String getYubinNo() {
        return getYubinNo1() + getYubinNo2();
    }

    /**
     * 郵便番号を
     * 000-0000形式で返す
     * @return 
     */
    public String getYubinNoDisp() {
        return getYubinNo1() + "-" + getYubinNo2();

    }

    /**
     * 住所の表示を返す
     * @return 
     */
    public String getJushoDisp() {
        return this.todofuken + "　" + this.jusho1 + "　" + this.jusho2 + "　" + this.jusho3 + "　" + this.jusho4;
    }

    /**
     * 住所を登録する形で取得
     * @return 
     */
    public String getJusho() {
        return getTodofuken() + MiwConstants.JUSHO_SPLIT_STRING + getJusho1() + MiwConstants.JUSHO_SPLIT_STRING + getJusho2() + MiwConstants.JUSHO_SPLIT_STRING + getJusho3() + MiwConstants.JUSHO_SPLIT_STRING + getJusho4();
    }

    /**
     * 生年月日を取得する
     * @return birthday
     */
    public String getBirthday() {
        return birthdayEra
                + StringUtility.padLeft(birthdayYear, "0", 2)
                + StringUtility.padLeft(birthdayMonth, "0", 2)
                + StringUtility.padLeft(birthdayDay, "0", 2);
    }

    /**
     * 生年月日を
     * YYYYMMDD形式で返す
     * @return 
     */
    public String getBirthdayYMD() {
        try {
            return StringUtility.padLeft(this.birthdayYear, "0", 4)
                    + StringUtility.padLeft(this.birthdayMonth, "0", 2)
                    + StringUtility.padLeft(this.birthdayDay, "0", 2);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 生年月日を
     * YYYY/MM/DD形式で返す
     * @return 
     */
    public String getBirthdayDisp() {
        try {
            return this.birthdayYear + "/"
                    + this.birthdayMonth + "/"
                    + this.birthdayDay;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 生年月日を
     * YYYY年M月D日形式で返す
     * @return 
     */
    public String getBirthdayDisp2() {
        try {
            String birthdayEra = CodeValueName.getBirthdayEraCodeName(getBirthdayEra());
            int iYear = Integer.parseInt(birthdayYear);
            int iMonth = Integer.parseInt(birthdayMonth);
            int iday = Integer.parseInt(birthdayDay);

            return birthdayEra
                    + Integer.toString(iYear) + "年"
                    + Integer.toString(iMonth) + "月"
                    + Integer.toString(iday) + "日";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 更新日を
     * YYYY/MM/DD形式で返す
     * @return 
     */
    public String getKoshinDateDisp() {
        try {
            String y = this.koshinDate.substring(0, 4);
            String m = this.koshinDate.substring(4, 6);
            String d = this.koshinDate.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 更新時刻を
     * HH:mm:ss形式で返す
     * @return 
     */
    public String getKoshinTimeDisp() {
        try {
            String h = this.koshinTime.substring(0, 2);
            String m = this.koshinTime.substring(2, 4);
            String s = this.koshinTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 仮受付日を
     * YYYY/MM/DD形式で返す
     * @return 
     */
    public String getKariUketsukeBiDisp() {
        try {
            String y = this.kariUketsukeBi.substring(0, 4);
            String m = this.kariUketsukeBi.substring(4, 6);
            String d = this.kariUketsukeBi.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 仮受付時刻を
     * HH:mm:ss形式で返す
     * @return 
     */
    public String getKariUketsukeTimeDisp() {
        try {
            String h = this.kariUketsukeTime.substring(0, 2);
            String m = this.kariUketsukeTime.substring(2, 4);
            String s = this.kariUketsukeTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 申込完了日を
     * YYYY/MM/DD形式で返す
     * @return 
     */
    public String getMoshikomiFinishBiDisp() {
        try {
            String y = this.moshikomiFinishBi.substring(0, 4);
            String m = this.moshikomiFinishBi.substring(4, 6);
            String d = this.moshikomiFinishBi.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 申込完了時刻を
     * HH:mm:ss形式で返す
     * @return 
     */
    public String getMoshikomiFinishTimeDisp() {
        try {
            String h = this.moshikomiFinishTime.substring(0, 2);
            String m = this.moshikomiFinishTime.substring(2, 4);
            String s = this.moshikomiFinishTime.substring(4, 6);
            return h + ":" + m + ":" + s;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 管理メモの表示を返す
     * @return 
     */
    public String getKanriMemoDisp() {
        return getNewLineReplaceBr(this.kanriMemo);
    }

    /**
     * 決済金額の表示を返す
     * @return 
     */
    public String getKessaiKingakuDisp() {
        return StringUtility.editComma(getKessaiKingaku());
    }

    /**
     * 受験級の名称を返す
     * @return 
     */
    public String getJukenkyuDisp() {
        return CodeValueName.getJukenKamokuName(getJukenkyu(), getKaisu());
    }

    /**
     * 性別コードの名称を返す
     * @return 
     */
    public String getSexCodeDisp() {
        return CodeValueName.getSexCodeName(getSexCode());
    }

    /**
     * 個人団体区分の名称を返す
     * @return 
     */
    public String getKojinDantaiKbnDisp() {
        return CodeValueName.getKojinDantaiKbnName(getKojinDantaiKbn());
    }

    /**
     * 申込媒体区分の名称を返す
     * @return 
     */
    public String getMoshikomiBaitaiKbnDisp() {
        return CodeValueName.getMoshikomiBaitaiKbnName(getMoshikomiBaitaiKbn());
    }

    /**
     * 決済方法区分の名称を返す
     * @return 
     */
    public String getKessaiHohoKbnDisp() {
        return CodeValueName.getKessaiHohoKbnName(getKessaiHohoKbn());
    }

//    /**
//     * 決済方法区分（略称）の名称を返す
//     * @return 
//     */
//    public String getKessaiHohoKbnExDisp() {
//        return CodeValueName.getKessaiHohoKbnNameEx(getKessaiHohoKbn());
//    }
    /**
     * 決済コンビニ種別の名称を返す
     * @return 
     */
    public String getKessaiConvenienceShubetsuDisp() {
        return CodeValueName.getKessaiConvenienceShubetsuName(getKessaiConvenienceShubetsu());
    }

    /**
     * 決済状況区分の名称を返す
     * @return 
     */
    public String getKessaiJokyoKbnDisp() {
        return CodeValueName.getKessaiJokyoKbnName(getKessaiJokyoKbn());
    }

    /**
     * 手続状況区分の名称を返す
     * @return 
     */
    public String getTetsudukiJokyoKbnDisp() {
        return CodeValueName.getTetsudukiJokyoKbnName(getTetsudukiJokyoKbn());
    }

    /**
     * 質問１の名称を返す
     * @return 
     */
    public String getPasswdQuestionCodeDisp() {
        return CodeValueName.getPasswdQuestionCodeName(getPasswdQuestionCode1());
    }

    /**
     * 回答１の表示を返す
     * @return 
     */
    public String getPasswdAnswerDisp() {
        String retStr = "";
        String answer = getPasswdAnswer1();
        int ansLen = answer.length();
        for (int i = 0; i < ansLen; i++) {
            retStr += "＊";
        }
        return retStr;
    }

    /**
     * 決済期限日の表示を返す
     * @return 
     */
    public String getKessaiKigenBiDisp() {
        try {
            return DateUtility.convertYMDToYMD(this.kessaiKigenBi) + "２３時５９分";
        } catch (Exception e) {
            return "";
        }
    }
    
    /**
     * 決済期限日の表示用を取得する（スラッシュ区切り）
     * @return 
     */
    public String getKessaiKigenBiDispSlash() {
        String ret = "";
        if (kessaiKigenBi != null) {
            MenuServ menuServ = new MenuServ();
            String ymd[] = menuServ.convertDate(kessaiKigenBi);
            ret = ymd[0] + "/" + ymd[1] + "/" + ymd[2];
        }
        return ret;
    }

    /**
     * 団体申込だったらtrueを返す
     * @return 
     */
    public boolean getIsDantaiMoshikomi() {
        if (this.kojinDantaiKbn.equals(MiwConstants.KOJIN_DANTAI_KBN_DANTAI)) {
            return true;
        } else {
            return false;
        }
    }

//    /**
//     * 1次試験免除の希望ありだったらtrueを返す
//     * @return 
//     */
//    public boolean getIsShikenMenjoKiboAri() {
//        if (this.shikenMenjoKiboFlg.equals(MiwConstants.FLG_ON)) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//    /**
//     * 選択した受験級の１次試験が全て免除かどうかを返す
//     * @return 
//     */
//    public boolean getIsMenjoAll() {
//        if (this.shikenMenjoKiboFlg.equals(MiwConstants.FLG_ON)) {
//            return MiaShikenCheckUtility.isAllMenjo(getJukenkyu(), getMenjoNendoKiKyu(), getMenjoNendoKiKyu2());
//        } else {
//            return false;
//        }
//    }
    /**
     * 払込が確認できていたらtrue
     * そうでなければfalse
     * @return 
     */
    public boolean getIsKessaiHaraikomiFinish() {
        if (this.kessaiJokyoKbn.equals(MiwConstants.KESSAI_JOKYO_KBN_KAKUNIN)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * クレジット決済だったらtrueを返す
     * @return 
     */
    public boolean getIsCreditKessai() {
        if (this.kessaiHohoKbn.equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * コンビニ決済だったらtrueを返す
     * @return 
     */
    public boolean getIsConveniKessai() {
        if (this.kessaiHohoKbn.equals(MiwConstants.KESSAI_HOHO_KBN_CVS)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * ペイジー決済だったらtrueを返す
     * @return 
     */
    public boolean getIsPayeasyKessai() {
        if (this.kessaiHohoKbn.equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 画像補正依頼
     * @return 
     */
    public boolean getIshoseiIrai() {
        if (this.kessaiHohoKbn.equals(MiwConstants.HOSEI_IRAI_KBN_IRAI)) {
            return true;
        } else {
            return false;
        }
    }

//    /**
//     * 級１の一次試験が免除かどうかを返す
//     * @return 
//     */
//    public boolean getIsKyu1Menjo() {
//        String kyu1 = jukenkyu.substring(0, 2);
//        if (MiaShikenCheckUtility.isKyuMenjo(kyu1, this.menjoNendoKiKyu)
//                || MiaShikenCheckUtility.isKyuMenjo(kyu1, this.menjoNendoKiKyu2)) {
//            //免除年度・季・級のどちらかに級１が含まれていれば
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     * 級２の一次試験が免除かどうかを返す
//     * @return 
//     */
//    public boolean getIsKyu2Menjo() {
//        String kyu2 = jukenkyu.substring(2, 4);
//        if (MiaShikenCheckUtility.isKyuMenjo(kyu2, this.menjoNendoKiKyu)
//                || MiaShikenCheckUtility.isKyuMenjo(kyu2, this.menjoNendoKiKyu2)) {
//            //免除年度・季・級のどちらかに級２が含まれていれば
//            return true;
//        } else {
//            return false;
//        }
//    }
    /**
     * 改行コードを<br>に置き換えた文字列を取得する
     * @param value
     * @return 
     */
    private static String getNewLineReplaceBr(String value) {
        String retStr = value;
        for (int i = 0; i < MiwConstants.NEW_LINE_CODES.length; i++) {
            retStr = retStr.replace(MiwConstants.NEW_LINE_CODES[i], "<br>");
        }
        return retStr;
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
}
