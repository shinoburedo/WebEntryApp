package jp.co.nii.miw.business.service.moshikomi;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;
import java.text.DecimalFormat;
import java.text.ParseException;


import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.utility.DateUtility;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル: 申込
 * 説明: 申込のデータbean
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 * @author n-ikezawa
 */
public class MskToroku {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 再申込フラグ **/
    private String saishutsuganFlg;
    /** 年度 **/
    private String nendo;
    /** 回数 **/
    private String kaisu;
    /** ユーザーＩＤ **/
    private String userId;
    /** 申込有フラグ */
    private String moshikomiAriFlg;
    /** 申込区分 **/
    private String moshikomiKbn;
    /** 団体コード **/
    private String dantaiCode;
    /** 団体申込者登録用ID **/
    private String dantaiMoshikomiUketsukeNo;
    /** 団体申込者登録用パスワード **/
    private String dantaiMoshikomiPasswd;
    /** 受験科目（表示） **/
    private String eventCode;
    /** 受験科目（内部） **/
    private String eventCodeNaibu;
    /** 受験希望地 **/
    private String shikenchiCode;
    /** 地名 **/
    private String chimei;
    /** フリガナ姓 **/
    private String shimeiSeiKana;
    /** フリガナ名 **/
    private String shimeiMeiKana;
    /** 姓 **/
    private String shimeiSei;
    /** 名 **/
    private String shimeiMei;
    /** 性別コード **/
    private String sexCode;
    /** 生年月日 **/
    private String birthday;
    /** 生年月日＿元号 **/
    private String birthdayEra;
    /** 生年月日＿年 **/
    private String birthdayYy;
    /** 生年月日＿月 **/
    private String birthdayMm;
    /** 生年月日＿日 **/
    private String birthdayDd;
    /** 郵便番号 **/
    private String yubinNo;
    /** 郵便番号１ **/
    private String yubinNo1;
    /** 郵便番号２ **/
    private String yubinNo2;
    /** 都道府県コード **/
    private String todofuken;
    /** 住所 **/
    private String jusho;
    /** 住所１ **/
    private String jusho1;
    /** 住所２ **/
    private String jusho2;
    /** 住所３ **/
    private String jusho3;
    /** 住所４ **/
    private String jusho4;
    /** 電話番号 **/
    private String telNo;
    /** 内線番号 **/
    private String extNo;
    /** 携帯電話 **/
    private String cellphoneNo;
    /** パスワード質問コード１ **/
    private String passwdQuestionCode1;
    /** ご本人様確認用の質問・回答　回答 **/
    private String passwdAnswer1;
    /** 受講経験 **/
    private String jukoKeiken;
    /** 実務経験 **/
    private String jitsumuKeiken;
    /** パスワード **/
    private String passwd;
    /** パスワード確認 **/
    private String passwdKakunin;
    /** メールアドレス **/
    private String mailAddress;
    /** メールアドレス確認用 **/
    private String mailAddressKakunin;
    /** 画像データ **/
    private File gazoData;
    /** 画像ＩＤ **/
    private String gazoId;
    /** 顔写真更新日 **/
    private String kaojashinKoshinDate;
    /** 顔写真更新時刻 **/
    private String kaojashinKoshinTime;
    /** 決済方法区分 **/
    private String kessaiHohoKbn;
    /** 決済状況区分 **/
    private String kessaiJokyoKbn;
    /** 決済加盟店コード **/
    private String kessaiKameitenCode;
    /** 決済取引コード **/
    private String kessaiTorihikiCode;
    /** コンビニ種別 **/
    private String kessaiConvenienceShubetsu;
    /** コンビニ確認画面ＵＲＬ **/
    private String kessaiConvenienceHaraikomihyoUrl;
    /** 決済金額 **/
    private String kessaiKingaku;
    /** 検定料 **/
    private String kenteiryo;
    /** 事務手数料 **/
    private String jimuTesuryo;
    /** カード番号 **/
    private String cardNo;
    /** カード番号１ **/
    private String cardNo1;
    /** カード番号２ **/
    private String cardNo2;
    /** カード番号３ **/
    private String cardNo3;
    /** カード番号４ **/
    private String cardNo4;
    /** 有効期限年 **/
    private String expYy;
    /** 有効期限月 **/
    private String expMm;
    /** ＩＤ **/
    private String moshikomiUketsukeNo;
    /** 手続状況区分(受付状況区分) **/
    private String tetsudukiJokyoKbn;
    //** 日付項目 **/
    /** 申込開始日 **/
    private String shutsuganStartDate;
    /** 申込開始時間 **/
    private String shutsuganStartTime;
    /** 申込終了日 **/
    private String shutsuganEndDate;
    /** 申込終了時間 **/
    private String shutsuganEndTime;
    /** 申込入力完了日 **/
    private String shutsuganCmpDate;
    /** 申込入力完了時間 **/
    private String shutsuganCmpTime;
    /** マイページ更新期限日 **/
    private String mypageKigen;
    /** マイページ更新期限日 **/
    private String mypageKigenTime;
    /** お支払受付番号 **/
    private String kessaiUketsukeNo;
    /** お支払期限日 **/
    private String kessaiKigen;
    /** 受験票発送予定日 **/
    private String jukenhyoHassobiKokunai;
    /** 申込日 **/
    private String moshikomibi;
    /** 決済期限７日後フラグ **/
    private String kessaiKigenFlg;
    /** 決済ログ本文 */
    private String log;
    /** 住所リスト **/
    private List jushoList;

    /**
     * 再申込フラグを設定する
     * @param saishutsuganFlg 設定するsaishutsuganFlgの値
     */
    public void setSaishutsuganFlg(String saishutsuganFlg) {
        this.saishutsuganFlg = saishutsuganFlg;
    }

    /**
     * 再申込フラグを取得する
     * @return saishutsuganFlg
     */
    public String getSaishutsuganFlg() {
        return saishutsuganFlg;
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
    public void setKaisu(String ki) {
        this.kaisu = ki;
    }

    /**
     * 回数を取得する
     * @return kaisu
     */
    public String getKaisu() {
        return kaisu;
    }

    /**
     * ユーザーＩＤを設定する
     * @param userId 設定するuserIdの値
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーＩＤを取得する
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 申込有フラグ を取得する。
     * @return 申込有フラグ
     */
    public String getMoshikomiAriFlg() {
        return moshikomiAriFlg;
    }

    /**
     * 申込有フラグ をセットする。
     * @param moshikomiAriFlg 申込有フラグ
     */
    public void setMoshikomiAriFlg(String moshikomiAriFlg) {
        this.moshikomiAriFlg = moshikomiAriFlg;
    }

    /**
     * 申込区分を設定する
     * @param moshikomiKbn 設定するmoshikomiKbnの値
     */
    public void setMoshikomiKbn(String moshikomiKbn) {
        this.moshikomiKbn = moshikomiKbn;
    }

    /**
     * 申込区分を取得する
     * @return moshikomiKbn
     */
    public String getMoshikomiKbn() {
        return moshikomiKbn;
    }

    /**
     * 申込区分名称を取得する
     * @return 申込区分名称
     */
    public String getMoshikomiKbnDisp() {
        String ret = "";
        ret = CodeValueName.getKojinDantaiKbnName(moshikomiKbn);

        return ret;
    }

    /**
     * 団体コードを設定する
     * @param dantaiCode 設定するdantaiCodeの値
     */
    public void setDantaiCode(String moshikomiDantaiCode) {
        this.dantaiCode = moshikomiDantaiCode;
    }

    /**
     * 団体コードを取得する
     * @return dantaiCode
     */
    public String getDantaiCode() {
        return dantaiCode;
    }
//
//    /**
//     * 団体名を設定する
//     * @param dantaiName 設定するdantaiNameの値
//     */
//    public void setDantaiName(String dantaiName) {
//        this.dantaiName = dantaiName;
//    }
//
//    /**
//     * 団体名を取得する
//     * @return dantaiName
//     */
//    public String getDantaiName() {
//        return dantaiName;
//    }

    /**
     * 受験科目を設定する
     * @param eventCode 設定するeventCodeの値
     */
    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    /**
     * 受験科目を取得する
     * @return eventCode
     */
    public String getEventCode() {
        return eventCode;
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

//    /**
//     * 受験科目（内部）を設定する
//     * @param eventCodeNaibu 設定するeventCodeNaibuの値
//     */
//    public void setEventCodeNaibu(String eventCodeNaibu) {
//        this.eventCodeNaibu = eventCodeNaibu;
//    }
//
//    /**
//     * 受験科目（内部）を取得する
//     * @return eventCodeNaibu
//     */
//    public String getEventCodeNaibu() {
//        return eventCodeNaibu;
//    }
    /**
     * 受験希望地を設定する
     * @param shikenchiCode 設定するshikenchiCode1の値
     */
    public void setShikenchiCode(String shikenchiCode) {
        this.shikenchiCode = shikenchiCode;
    }

    /**
     * 受験希望地を取得する
     * @return shikenchiCode
     */
    public String getShikenchiCode() {
        return shikenchiCode;
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

//    /**
//     * 地名を設定する
//     * @param chimei 設定するchimeiの値
//     */
//    public void setChimei(String chimei) {
//        this.chimei = chimei;
//    }
//
//    /**
//     * 地名を取得する
//     * @return chimei
//     */
//    public String getChimei() {
//        return chimei;
//    }
    /**
     * フリガナ姓を設定する
     * @param shimeiSeiKana 設定するshimeiSeiKanaの値
     */
    public void setShimeiSeiKana(String shimeiSeiKana) {
        this.shimeiSeiKana = shimeiSeiKana;
    }

    /**
     * フリガナ姓を取得する
     * @return shimeiSeiKana
     */
    public String getShimeiSeiKana() {
        return shimeiSeiKana;
    }

    /**
     * フリガナ名を設定する
     * @param shimeiMeiKana 設定するshimeiMeiKanaの値
     */
    public void setShimeiMeiKana(String shimeiMeiKana) {
        this.shimeiMeiKana = shimeiMeiKana;
    }

    /**
     * フリガナ名を取得する
     * @return shimeiMeiKana
     */
    public String getShimeiMeiKana() {
        return shimeiMeiKana;
    }

    /**
     * 姓を設定する
     * @param shimeiSei 設定するshimeiSeiの値
     */
    public void setShimeiSei(String shimeiSei) {
        this.shimeiSei = shimeiSei;
    }

    /**
     * 姓を取得する
     * @return shimeiSei
     */
    public String getShimeiSei() {
        return shimeiSei;
    }

    /**
     * 名を設定する
     * @param shimeiMei 設定するshimeiMeiの値
     */
    public void setShimeiMei(String shimeiMei) {
        this.shimeiMei = shimeiMei;
    }

    /**
     * 名を取得する
     * @return shimeiMei
     */
    public String getShimeiMei() {
        return shimeiMei;
    }

    /**
     * 性別コードを設定する
     * @param sexCode 設定するsexCodeの値
     */
    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    /**
     * 性別コードを取得する
     * @return sexCode
     */
    public String getSexCode() {
        return sexCode;
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
     * 生年月日を設定する
     * @param birthday 設定するbirthdayの値
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 生年月日を取得する
     * @return birthday
     */
    public String getBirthday() {
        return birthdayEra
                + StringUtility.padLeft(birthdayYy, "0", 2)
                + StringUtility.padLeft(birthdayMm, "0", 2)
                + StringUtility.padLeft(birthdayDd, "0", 2);
    }

    /**
     * 生年月日(元号)を取得する
     * @return birthdayEra
     */
    public String getBirthdayEra() {
        return birthdayEra;
    }

    /**
     * 生年月日(元号)を設定する
     * @param birthdayEra birthdayEra
     */
    public void setBirthdayEra(String birthdayEra) {
        this.birthdayEra = birthdayEra;
    }

    /**
     * 生年月日（元号）名称を取得する
     * @return 生年月日（元号）名称
     */
    public String getBirthdayEraDisp() {
        String ret = "";
        ret = CodeValueName.getBirthdayEraCodeName(birthdayEra);
        return ret;
    }

    /**
     * 生年月日＿年を設定する
     * @param birthdayYy 設定するbirthdayYyyyの値
     */
    public void setBirthdayYy(String birthdayYy) {
        this.birthdayYy = birthdayYy;
    }

    /**
     * 生年月日＿年を取得する
     * @return birthdayYy
     */
    public String getBirthdayYy() {
        return birthdayYy;
    }

    /**
     * 生年月日＿月を設定する
     * @param birthdayMm 設定するbirthdayMmの値
     */
    public void setBirthdayMm(String birthdayMm) {
        this.birthdayMm = birthdayMm;
    }

    /**
     * 生年月日＿月を取得する
     * @return birthdayMm
     */
    public String getBirthdayMm() {
        return birthdayMm;
    }

    /**
     * 生年月日＿日を設定する
     * @param birthdayDd 設定するbirthdayDdの値
     */
    public void setBirthdayDd(String birthdayDd) {
        this.birthdayDd = birthdayDd;
    }

    /**
     * 生年月日＿日を取得する
     * @return birthdayDd
     */
    public String getBirthdayDd() {
        return birthdayDd;
    }

    /**
     * 郵便番号を設定する
     * @param yubinNo 設定するyubinNoの値
     */
    public void setYubinNo(String yubinNo) {
        this.yubinNo = yubinNo;
    }

    /**
     * 郵便番号を取得する
     * @return yubinNo
     */
    public String getYubinNo() {
        return yubinNo1 + yubinNo2;
    }

    /**
     * 郵便番号１を設定する
     * @param yubinNo1 設定するyubinNo1の値
     */
    public void setYubinNo1(String yubinNo1) {
        this.yubinNo1 = yubinNo1;
    }

    /**
     * 郵便番号１を取得する
     * @return yubinNo1
     */
    public String getYubinNo1() {
        return yubinNo1;
    }

    /**
     * 郵便番号２を設定する
     * @param yubinNo2 設定するyubinNo2の値
     */
    public void setYubinNo2(String yubinNo2) {
        this.yubinNo2 = yubinNo2;
    }

    /**
     * 郵便番号２を取得する
     * @return yubinNo2
     */
    public String getYubinNo2() {
        return yubinNo2;
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

//    /**
//     * 都道府県名称を取得する
//     * @return 都道府県名称
//     */
//    public String getTodofukenDisp() {
//        String ret = "";
//        ret = CodeValueName.getTodofukenCodeName(todofukenCode);
//        return ret;
//    }
    /**
     * 住所を設定する
     * @param jusho 設定するjushoの値
     */
    public void setJusho(String jusho) {
        this.jusho = jusho;
    }

    /**
     * 住所を取得する
     * @return jusho
     */
    public String getJusho() {
        return todofuken + MiwConstants.JUSHO_SPLIT_STRING
                + jusho1 + MiwConstants.JUSHO_SPLIT_STRING
                + jusho2 + MiwConstants.JUSHO_SPLIT_STRING
                + jusho3 + MiwConstants.JUSHO_SPLIT_STRING
                + jusho4;
    }

    /**
     * 住所１を設定する
     * @param jusho1 設定するjusho1の値
     */
    public void setJusho1(String jusho1) {
        this.jusho1 = jusho1;
    }

    /**
     * 住所１を取得する
     * @return jusho1
     */
    public String getJusho1() {
        return jusho1;
    }

    /**
     * 住所２を設定する
     * @param jusho2 設定するjusho2の値
     */
    public void setJusho2(String jusho2) {
        this.jusho2 = jusho2;
    }

    /**
     * 住所２を取得する
     * @return jusho2
     */
    public String getJusho2() {
        return jusho2;
    }

    /**
     * 住所３を設定する
     * @param jusho3 設定するjusho3の値
     */
    public void setJusho3(String jusho3) {
        this.jusho3 = jusho3;
    }

    /**
     * 住所３を取得する
     * @return jusho3
     */
    public String getJusho3() {
        return jusho3;
    }

    /**
     * 住所４を設定する
     * @param jusho3 設定するjusho3の値
     */
    public void setJusho4(String jusho4) {
        this.jusho4 = jusho4;
    }

    /**
     * 住所４を取得する
     * @return jusho3
     */
    public String getJusho4() {
        return jusho4;
    }

    /**
     * 電話番号を設定する
     * @param telNo 設定するtelNoの値
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * 電話番号を取得する
     * @return telNo
     */
    public String getTelNo() {
        return telNo;
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
     * 携帯電話番号を設定する
     * @param cellphoneNo 設定するrenrakusakiの値
     */
    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    /**
     * 携帯電話番号を取得する
     * @return cellphoneNo
     */
    public String getCellphoneNo() {
        return cellphoneNo;
    }

    /**
     * ご本人様確認用の質問・回答　質問を設定する
     * @param passwdQuestionCode 設定するpasswdQuestionCodeの値
     */
    public void setPasswdQuestionCode1(String passwdQuestionCode1) {
        this.passwdQuestionCode1 = passwdQuestionCode1;
    }

    /**
     * ご本人様確認用の質問・回答　質問を取得する
     * @return passwdQuestionCode
     */
    public String getPasswdQuestionCode1() {
        return passwdQuestionCode1;
    }

    /**
     * ご本人様確認用の質問・回答　質問の名称
     * @return ご本人様確認用の質問・回答　質問の名称
     */
    public String getPasswdQuestionCode1Disp() {
        String ret = "";
        ret = CodeValueName.getPasswdQuestionCodeName(passwdQuestionCode1);
        return ret;
    }

    /**
     * ご本人様確認用の質問・回答　回答を設定する
     * @param passwdAnswer 設定するpasswdAnswerの値
     */
    public void setPasswdAnswer1(String passwdAnswer1) {
        this.passwdAnswer1 = passwdAnswer1;
    }

    /**
     * ご本人様確認用の質問・回答　回答を取得する
     * @return passwdAnswer
     */
    public String getPasswdAnswer1() {
        return passwdAnswer1;
    }

    /**
     * パスワードを設定する
     * @param passwd 設定するpasswdの値
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    /**
     * パスワードを取得する
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * パスワード確認を設定する
     * @param passwd 設定するpasswdの値
     */
    public void setPasswdKakunin(String passwd) {
        this.passwdKakunin = passwd;
    }

    /**
     * パスワード確認を取得する
     * @return passwd
     */
    public String getPasswdKakunin() {
        return passwdKakunin;
    }

    /**
     * メールアドレスを設定する
     * @param mailAddress 設定するmailAddressの値
     */
    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    /**
     * メールアドレスを取得する
     * @return mailAddress
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * メールアドレス（確認用）を取得する
     * @return mailAddressKakunin
     */
    public String getMailAddressKakunin() {
        return mailAddressKakunin;
    }

    /**
     * メールアドレス（確認用）を設定する
     * @param mailAddressKakunin 設定するmailAddressの値
     */
    public void setMailAddressKakunin(String mailAddressKakunin) {
        this.mailAddressKakunin = mailAddressKakunin;
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
     * 顔写真更新日を取得する
     * @return gazoId
     */
    public String getKaojashinKoshinDate() {
        return kaojashinKoshinDate;
    }

    /**
     * 顔写真更新日を設定する
     * @param kaojashinKoshinDate 設定するkaojashinKoshinDateの値
     */
    public void setKaojashinKoshinDate(String kaojashinKoshinDate) {
        this.kaojashinKoshinDate = kaojashinKoshinDate;
    }

    /**
     * 顔写真更新時刻を取得する
     * @return gazoId
     */
    public String getKaojashinKoshinTime() {
        return kaojashinKoshinTime;
    }

    /**
     * 顔写真更新時刻を設定する
     * @param kaojashinKoshinTime 設定するkaojashinKoshinTimeの値
     */
    public void setKaojashinKoshinTime(String kaojashinKoshinTime) {
        this.kaojashinKoshinTime = kaojashinKoshinTime;
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
     * 決済方法区分を設定する
     * @param kessaiHohoKbn 設定するkessaiHohoKbnの値
     */
    public void setKessaiHohoKbn(String kessaiHohoKbn) {
        this.kessaiHohoKbn = kessaiHohoKbn;
    }

    /**
     * 決済方法区分を取得する
     * @return kessaiHohoKbn
     */
    public String getKessaiHohoKbn() {
        return kessaiHohoKbn;
    }

    /**
     * 決済状況区分を設定する
     * @param kessaiJokyoKbn 設定するkessaiJokyoKbnの値
     */
    public void setKessaiJokyoKbn(String kessaiJokyoKbn) {
        this.kessaiJokyoKbn = kessaiJokyoKbn;
    }

    /**
     * 決済状況区分を取得する
     * @return kessaiJokyoKbn
     */
    public String getKessaiJokyoKbn() {
        return kessaiJokyoKbn;
    }

    /**
     * 決済状況区分(表示用)を取得する
     * @return kessaiJokyoKbnDisp
     */
    public String getKessaiJokyoKbnDisp() {
        String ret = "";
        ret = CodeValueName.getKessaiJokyoKbnName(kessaiJokyoKbn);
        return ret;
    }

    /**
     * 手続状況区分(受付状況区分)を設定する
     * @param tetsudukiJokyoKbn 設定するtetsudukiJokyoKbnの値
     */
    public void setTetsudukiJokyoKbn(String tetsudukiJokyoKbn) {
        this.tetsudukiJokyoKbn = tetsudukiJokyoKbn;
    }

    /**
     * 手続状況区分(受付状況区分)を取得する
     * @return tetsudukiJokyoKbn
     */
    public String getTetsudukiJokyoKbn() {
        return tetsudukiJokyoKbn;
    }

    /**
     * 手続状況区分(受付状況区分)(表示用)を取得する
     * @return tetsudukiJokyoKbnDisp
     */
    public String getTetsudukiJokyoKbnDisp() {
        String ret = "";
        ret = CodeValueName.getTetsudukiJokyoKbnName(tetsudukiJokyoKbn);
        return ret;
    }

    /**
     * 加盟店コードを設定する
     * @param kessaiKameitenCode 設定するkessaiKameitenCodeの値
     */
    public void setKessaiKameitenCode(String kessaiKameitenCode) {
        this.kessaiKameitenCode = kessaiKameitenCode;
    }

    /**
     * 加盟店コードを取得する
     * @return kessaiKameitenCode
     */
    public String getKessaiKameitenCode() {
        return kessaiKameitenCode;
    }

    /**
     * 取引コードを設定する
     * @param kessaiTorihikiCode 設定するkessaiTorihikiCodeの値
     */
    public void setKessaiTorihikiCode(String kessaiTorihikiCode) {
        this.kessaiTorihikiCode = kessaiTorihikiCode;
    }

    /**
     * 取引コードを取得する
     * @return kessaiTorihikiCode
     */
    public String getKessaiTorihikiCode() {
        return kessaiTorihikiCode;
    }

    /**
     * コンビニ種別を設定する
     * @param kessaiConvenienceShubetsu 設定するkessaiConvenienceShubetsuの値
     */
    public void setKessaiConvenienceShubetsu(String kessaiConvenienceShubetsu) {
        this.kessaiConvenienceShubetsu = kessaiConvenienceShubetsu;
    }

    /**
     * コンビニ種別を取得する
     * @return kessaiConvenienceShubetsu
     */
    public String getKessaiConvenienceShubetsu() {
        return kessaiConvenienceShubetsu;
    }

    /**
     * コンビニ種別名称を取得する
     * @return kessaiConvenienceShubetsu
     */
    public String getKessaiConvenienceShubetsuDisp() {
        String ret = "";
        ret = CodeValueName.getKessaiConvenienceShubetsuName(kessaiConvenienceShubetsu);
        return ret;
    }

    /**
     * コンビニ種別支払い番号名称を取得する
     * @return 支払い番号の名称
     */
    public String getKessaiConvenienceShubetsuNoName() {
        String ret = "";

        if (getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_SEVEN)) {
            //セブンイレブン
            ret = MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_SEVEN;
        } else if (getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_LAWSON)) {
            //ローソン / セイコーマート
            ret = MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_LAWSON;
        } else if (getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_FAMI)) {
            //ファミマ
            ret = MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI;
        } else if (getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_CIRCLE)) {
            //オンライン決済
            ret = MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_CIRCLE;
        }

        return ret;
    }

    /**
     * 確認画面ＵＲＬを設定する
     * @param kessaiConvenienceHaraikomihyoUrl 設定するkessaiConvenienceHaraikomihyoUrlの値
     */
    public void setKessaiConvenienceHaraikomihyoUrl(String kessaiConvenienceHaraikomihyoUrl) {
        this.kessaiConvenienceHaraikomihyoUrl = kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * 確認画面ＵＲＬを取得する
     * @return kessaiConvenienceHaraikomihyoUrl
     */
    public String getKessaiConvenienceHaraikomihyoUrl() {
        return kessaiConvenienceHaraikomihyoUrl;
    }

    /**
     * 決済金額を設定する
     * @param kessaiKingaku 設定するkessaiKingakuの値
     */
    public void setKessaiKingaku(String kessaiKingaku) {
        this.kessaiKingaku = kessaiKingaku;
    }

    /**
     * 決済金額を取得する
     * @return kessaiKingaku
     */
    public String getKessaiKingaku() {
        return kessaiKingaku;
    }

    /**
     * 決済金額表示用を取得する
     * @return kessaiKingaku
     */
    public String getKessaiKingakuDisp() {
        String ret = "";
        int kingaku = Integer.parseInt(kessaiKingaku);
        DecimalFormat exFormat = new DecimalFormat("###,##0");

        ret = exFormat.format(kingaku);

        return ret;
    }

    /**
     * 検定料を設定する
     * @param kenteiryo 設定するkenteiryoの値
     */
    public void setKenteiryo(String kenteiryo) {
        this.kenteiryo = kenteiryo;
    }

    /**
     * 検定料を取得する
     * @return kenteiryo
     */
    public String getKenteiryo() {
        return kenteiryo;
    }

    /**
     * 検定料表示用を取得する
     * @return kenteiryo
     */
    public String getKenteiryoDisp() {
        String ret = "";
        int kingaku = Integer.parseInt(kenteiryo);
        DecimalFormat exFormat = new DecimalFormat("###,##0");

        ret = exFormat.format(kingaku);

        return ret;
    }

    /**
     * 事務手数料を設定する
     * @param jimuTesuryo 設定するjimuTesuryoの値
     */
    public void setJimuTesuryo(String jimuTesuryo) {
        this.jimuTesuryo = jimuTesuryo;
    }

    /**
     * 事務手数料を取得する
     * @return jimuTesuryo
     */
    public String getJimuTesuryo() {
        return jimuTesuryo;
    }

    /**
     * 事務手数料表示用を取得する
     * @return jimuTesuryo
     */
    public String getJimuTesuryoDisp() {
        String ret = "";
        int kingaku = Integer.parseInt(jimuTesuryo);
        DecimalFormat exFormat = new DecimalFormat("###,##0");

        ret = exFormat.format(kingaku);

        return ret;
    }

    /**
     * カード番号を設定する
     * @param cardNo 設定するcardNoの値
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * カード番号を取得する
     * @return cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * カード番号１を設定する
     * @param cardNo1 設定するcardNo1の値
     */
    public void setCardNo1(String cardNo1) {
        this.cardNo1 = cardNo1;
    }

    /**
     * カード番号１を取得する
     * @return cardNo1
     */
    public String getCardNo1() {
        return cardNo1;
    }

    /**
     * カード番号２を設定する
     * @param cardNo2 設定するcardNo2の値
     */
    public void setCardNo2(String cardNo2) {
        this.cardNo2 = cardNo2;
    }

    /**
     * カード番号２を取得する
     * @return cardNo2
     */
    public String getCardNo2() {
        return cardNo2;
    }

    /**
     * カード番号３を設定する
     * @param cardNo3 設定するcardNo2の値
     */
    public void setCardNo3(String cardNo3) {
        this.cardNo3 = cardNo3;
    }

    /**
     * カード番号３を取得する
     * @return cardNo3
     */
    public String getCardNo3() {
        return cardNo3;
    }

    /**
     * カード番号４を設定する
     * @param cardNo2 設定するcardNo2の値
     */
    public void setCardNo4(String cardNo4) {
        this.cardNo4 = cardNo4;
    }

    /**
     * カード番号４を取得する
     * @return cardNo4
     */
    public String getCardNo4() {
        return cardNo4;
    }

    /**
     * 有効期限年を設定する
     * @param expYy 設定するexpYyの値
     */
    public void setExpYy(String expYy) {
        this.expYy = expYy;
    }

    /**
     * 有効期限年を取得する
     * @return expYy
     */
    public String getExpYy() {
        return expYy;
    }

    /**
     * 有効期限月を設定する
     * @param expYy 設定するexpYyの値
     */
    public void setExpMm(String expMm) {
        this.expMm = expMm;
    }

    /**
     * 有効期限月を取得する
     * @return expMm
     */
    public String getExpMm() {
        return expMm;
    }

    /**
     * 申込受付番号を設定する
     * @param moshikomiUketsukeNo 設定するmoshikomiUketsukeNoの値
     */
    public void setMoshikomiUketsukeNo(String moshikomiUketsukeNo) {
        this.moshikomiUketsukeNo = moshikomiUketsukeNo;
    }

    /**
     * 申込受付番号を取得する
     * @return moshikomiUketsukeNo
     */
    public String getMoshikomiUketsukeNo() {
        return moshikomiUketsukeNo;
    }

    /**
     * お支払受付番号を設定する
     * @param kessaiUketsukeNo 設定するkessaiUketsukeNoの値
     */
    public void setKessaiUketsukeNo(String kessaiUketsukeNo) {
        this.kessaiUketsukeNo = kessaiUketsukeNo;
    }

    /**
     * お支払受付番号を取得する
     * @return kessaiUketsukeNo
     */
    public String getKessaiUketsukeNo() {
        return kessaiUketsukeNo;
    }

    /**
     * 収納機関コードを取得する（ペイジー）<br>
     * 第１番号（ファミマ）
     * @return kessaiUketsukeNo
     */
    public String getKessaiUketsukeNo1() {
        return getKessaiUketsukeNo(0);
    }

    /**
     * お客様番号を取得する（ペイジー）<br>
     * 第２番号（ファミマ）
     * @return kessaiUketsukeNo
     */
    public String getKessaiUketsukeNo2() {
        return getKessaiUketsukeNo(1);
    }

    /**
     * 確認番号を取得する（ペイジー）
     * @return kessaiUketsukeNo
     */
    public String getKessaiUketsukeNo3() {
        return getKessaiUketsukeNo(2);
    }

    /**
     * お支払受付番号を取得する（ペイジー）
     * @return kessaiUketsukeNo
     */
    public String getKessaiUketsukeNo(int idx) {
        String[] kessaiUketsukeNoArray = kessaiUketsukeNo.split("-", 3);
        return kessaiUketsukeNoArray[idx];
    }

    /**
     * お支払期限日を設定する
     * @param kessaiKigen 設定するkessaiKigenの値
     */
    public void setKessaiKigen(String kessaiKigen) {
        this.kessaiKigen = kessaiKigen;
    }

    /**
     * お支払期限日を取得する
     * @return kessaiKigen
     */
    public String getKessaiKigen() {
        return kessaiKigen;
    }

    /**
     * お支払期限日（表示用 月日）を取得する
     * @return お支払期限日（表示用 月日）
     */
    public String getKessaiKigenDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(kessaiKigen);

        ret = ymd[1] + "月" + ymd[2] + "日";
        return ret;
    }

    /**
     * お支払期限日（表示用　年月日）を取得する
     * @return お支払期限日（表示用 年月日）
     */
    public String getKessaiKigenYmdDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(kessaiKigen);

        ret = ymd[0] + "年"
                + ymd[1] + "月"
                + ymd[2] + "日";

        return ret;
    }

    /**
     * マイページ利用期限を設定する
     * @param mypageKigen 設定するmypageKigenの値
     */
    public void setMypageKigen(String mypageKigen) {
        this.mypageKigen = mypageKigen;
    }

    /**
     * マイページ利用期限を取得する
     * @return mypageKigen
     */
    public String getMypageKigen() {
        return mypageKigen;
    }

    public String getMypageKigenTime() {
        return mypageKigenTime;
    }

    public void setMypageKigenTime(String mypageKigenTime) {
        this.mypageKigenTime = mypageKigenTime;
    }
    
    /**
     * マイページ利用期限（表示用）を取得する
     * @return マイページ利用期限（表示用）
     */
    public String getMypageKigenDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(mypageKigen);
        String hms[] = mskTorokuServ.convertTime(mypageKigenTime);

        ret = ymd[1] + "月" + ymd[2] + "日　" + hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * 受験票発送予定日（国内）を設定する
     * @param jukenhyoHassobiKokunai 設定するjukenhyoHassobiKokunaiの値
     */
    public void setJukenhyoHassobiKokunai(String jukenhyoHassobiKokunai) {
        this.jukenhyoHassobiKokunai = jukenhyoHassobiKokunai;
    }

    /**
     * 受験票発送予定日（国内）を取得する
     * @return jukenhyoHassobiKokunai
     */
    public String getJukenhyoHassobiKokunai() {
        return jukenhyoHassobiKokunai;
    }

    /**
     * 受験票発送予定日（国内）表示用を取得する
     * @return 受験票発送予定日（国内）表示用
     */
    public String getJukenhyoHassobiKokunaiDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(jukenhyoHassobiKokunai);

        ret = ymd[1] + "月" + ymd[2] + "日";
        return ret;
    }

    /**
     * 申込開始日を設定する
     * @param shutsuganStartDate 設定するshutsuganStartDateの値
     */
    public void setShutsuganStartDate(String shutsuganStartDate) {
        this.shutsuganStartDate = shutsuganStartDate;
    }

    /**
     * 申込開始日を取得する
     * @return shutsuganStartDate
     */
    public String getShutsuganStartDate() {
        return shutsuganStartDate;
    }

    /**
     * 申込開始日（表示用）を取得する
     * @return 申込開始日（表示用）
     */
    public String getShutsuganStartDateDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(shutsuganStartDate);

        ret = ymd[1] + "月" + ymd[2] + "日";
        return ret;
    }

    /**
     * 申込開始時間を設定する
     * @param shutsuganStartTime 設定するshutsuganStartTimeの値
     */
    public void setShutsuganStartTime(String shutsuganStartTime) {
        this.shutsuganStartTime = shutsuganStartTime;
    }

    /**
     * 申込開始時間を取得する
     * @return shutsuganStartTime
     */
    public String getShutsuganStartTime() {
        return shutsuganStartTime;
    }

    /**
     * 申込開始日（表示用）を取得する
     * @return 申込開始日（表示用）
     */
    public String getShutsuganStartTimeDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String hms[] = mskTorokuServ.convertTime(shutsuganStartTime);

        ret = hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * 申込終了日を設定する
     * @param shutsuganEndDate 設定するshutsuganEndDateの値
     */
    public void setShutsuganEndDate(String shutsuganEndDate) {
        this.shutsuganEndDate = shutsuganEndDate;
    }

    /**
     * 申込終了日を取得する
     * @return shutsuganEndDate
     */
    public String getShutsuganEndDate() {
        return shutsuganEndDate;
    }

    /**
     * 申込終了日（表示用）を取得する
     * @return 申込終了日（表示用）
     */
    public String getShutsuganEndDateDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(shutsuganEndDate);
        ret = ymd[1] + "月" + ymd[2] + "日";

        return ret;
    }

    /**
     * 申込終了時間を設定する
     * @param shutsuganEndTime 設定するshutsuganEndTimeの値
     */
    public void setShutsuganEndTime(String shutsuganEndTime) {
        this.shutsuganEndTime = shutsuganEndTime;
    }

    /**
     * 申込終了時間を取得する
     * @return shutsuganStartTime
     */
    public String getShutsuganEndTime() {
        return shutsuganEndTime;
    }

    /**
     * 申込終了日（表示用）を取得する
     * @return 申込終了日（表示用）
     */
    public String getShutsuganEndTimeDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String hms[] = mskTorokuServ.convertTime(shutsuganEndTime);

        ret = hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * 申込完了日を設定する
     * @param shutsuganCmpDate 設定するshutsuganCmpDateの値
     */
    public void setShutsuganCmpDate(String shutsuganCmpDate) {
        this.shutsuganCmpDate = shutsuganCmpDate;
    }

    /**
     * 申込完了日を取得する
     * @return shutsuganCmpDate
     */
    public String getShutsuganCmpDate() {
        return shutsuganCmpDate;
    }

    /**
     * 申込完了日（表示用）を取得する
     * @return 申込完了日
     */
    public String getShutsuganCmpDateDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(shutsuganCmpDate);

        ret = ymd[1] + "月" + ymd[2] + "日";
        return ret;
    }

    /**
     * 申込完了時間を設定する
     * @param shutsuganCmpTime 設定するshutsuganCmpTimeの値
     */
    public void setShutsuganCmpTime(String shutsuganCmpTime) {
        this.shutsuganCmpTime = shutsuganCmpTime;
    }

    /**
     * 申込完了時間を取得する
     * @return shutsuganCmpTime
     */
    public String getShutsuganCmpTime() {
        return shutsuganCmpTime;
    }

    /**
     * 申込完了時間（表示用）を取得する
     * @return 申込完了時間
     */
    public String getshutsuganCmpTimeDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String hms[] = mskTorokuServ.convertTime(shutsuganCmpTime);

        ret = hms[0] + "時" + hms[1] + "分";
        return ret;
    }

    /**
     * 申込日を設定する
     * @param moshikomibi 設定するmoshikomibiの値
     */
    public void setMoshikomibi(String moshikomibi) {
        this.moshikomibi = moshikomibi;
    }

    /**
     * 申込日を取得する
     * @return moshikomibi
     */
    public String getMoshikomibi() {
        return moshikomibi;
    }

    /**
     * 申込日（表示用）を取得する
     * @return moshikomibi
     */
    public String getMoshikomibiDisp() {
        String ret = "";

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        String ymd[] = mskTorokuServ.convertDate(moshikomibi);

        ret = ymd[0] + "年" + ymd[1] + "月" + ymd[2] + "日";
        return ret;
    }

    /**
     * 決済期限フラグを設定する
     * @param kessaiKigenFlg 設定するkessaiKigenFlgの値
     */
    public void setKessaiKigenFlg(String kessaiKigenFlg) {
        this.kessaiKigenFlg = kessaiKigenFlg;
    }

    /**
     * 決済期限フラグを取得する
     * @return kessaiKigenFlg
     */
    public String getKessaiKigenFlg() {
        return kessaiKigenFlg;
    }

    /**
     * 決済ログ本文を設定する
     * @param log 設定するlogの値
     */
    public void setLog(String log) {
        this.log = log;
    }

    /**
     * 決済ログ本文を取得する
     * @return log
     */
    public String getLog() {
        return log;
    }

    /**
     * 画面名称を取得する。
     * @return 画面名称
     */
    public String getScreenName() {
        return "";
    }

    /**
     * 住所リストを設定する
     * @param jushoList 設定するjushoListの値
     */
    public void setJushoList(List jushoList) {
        this.jushoList = jushoList;
    }

    /**
     * 住所リストを取得する。
     * @return 住所リスト
     */
    public List getJushoList() {
        return jushoList;
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 生年月日を
     * YYYYMMDD形式で返す
     * @return 
     */
    public String getBirthdayYMD() {
        return birthdayEra
                + StringUtility.padLeft(birthdayYy, "0", 2)
                + StringUtility.padLeft(birthdayMm, "0", 2)
                + StringUtility.padLeft(birthdayDd, "0", 2);
    }

    /**
     * 生年月日を
     * YYYY年M月D日形式で返す
     * @return 
     */
    public String getBirthdayDisp() {
        int iYear = Integer.parseInt(birthdayYy);
        int iMonth = Integer.parseInt(birthdayMm);
        int iday = Integer.parseInt(birthdayDd);

        return CodeValueName.getBirthdayEraCodeName(birthdayEra)
                + Integer.toString(iYear) + "年"
                + Integer.toString(iMonth) + "月"
                + Integer.toString(iday) + "日";

    }
    //=======================================================================//
    // リストボックス
    //=======================================================================//
    /** 受験科目 */
    private LinkedHashMap<String, String> eventCodeList;
    /** 免除区分 */
    private HashMap menjoKbnList;
    /** 受験地 */
    private HashMap shikenchiList;
//    /** 職業 */
//    private HashMap shokugyoList;
//    /** 学校区分 */
//    private HashMap gakkoKbnList;

    public LinkedHashMap<String, String> getEventCodeList() {
        return eventCodeList;
    }

    public void setEventCodeList(LinkedHashMap<String, String> eventCodeList) {
        this.eventCodeList = eventCodeList;
    }

    public HashMap getMenjoKbnList() {
        return menjoKbnList;
    }

    public void setMenjoKbnList(HashMap menjoKbnList) {
        this.menjoKbnList = menjoKbnList;
    }

    public HashMap getShikenchiList() {
        return shikenchiList;
    }

    public void setShikenchiList(HashMap shikenchiList) {
        this.shikenchiList = shikenchiList;
    }

    /*
     * 団体申込者登録用IDを取得する
     */
    public String getDantaiMoshikomiUketsukeNo() {
        return dantaiMoshikomiUketsukeNo;
    }

    /*
     * 団体申込者登録用パスワードを設定する
     */
    public void setDantaiMoshikomiUketsukeNo(String dantaiMoshikomiUketsukeNo) {
        this.dantaiMoshikomiUketsukeNo = dantaiMoshikomiUketsukeNo;
    }

    /*
     * 団体申込者登録用パスワードを取得する
     */
    public String getDantaiMoshikomiPasswd() {
        return dantaiMoshikomiPasswd;
    }

    /*
     * 団体申込者登録用IDを設定する
     */
    public void setDantaiMoshikomiPasswd(String dantaiMoshikomiPasswd) {
        this.dantaiMoshikomiPasswd = dantaiMoshikomiPasswd;
    }
//    public HashMap getShokugyoList() {
//        return shokugyoList;
//    }
//
//    public void setShokugyoList(HashMap shokugyoList) {
//        this.shokugyoList = shokugyoList;
//    }
//
//    public HashMap getGakkoKbnList() {
//        return gakkoKbnList;
//    }
//
//    public void setGakkoKbnList(HashMap gakkoKbnList) {
//        this.gakkoKbnList = gakkoKbnList;
//    }
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
     * コンビニ説明ページURLを取得
     * @return 
     */
    public String getConvenienceSevenPc() {
        return MiwConstants.CONVENI_URL_SEVEN_PC;
    }

    public String getConvenienceFamimaPc() {
        return MiwConstants.CONVENI_URL_FAMIMA_PC;
    }

    public String getConvenienceLawsonPc() {
        return MiwConstants.CONVENI_URL_LAWSON_PC;
    }

    public String getConvenienceSeicoPc() {
        return MiwConstants.CONVENI_URL_SEICO_PC;
    }

    public String getConvenienceMinistopPc() {
        return MiwConstants.CONVENI_URL_MINISTOP_PC;
    }

    public String getConvenienceOnlinePc() {
        return MiwConstants.CONVENI_URL_ONLINE_PC;
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
     * 申込日から支払期限日の日数を返す
     * @return 
     */
    public String getKessaiNisu() {
        return MiwConstants.KESSAI_KIGEN;
    }

    /**
     * 現在の日時が申込期間内の場合true
     * そうでない場合falseを返す
     * @return 
     */
    public boolean getIsShutsuganKikan() {
        String fromDate = this.shutsuganStartDate + this.shutsuganStartTime;
        String toDate = this.shutsuganCmpDate + this.shutsuganCmpTime;
        try {
            return DateUtility.isRangeDate(fromDate, toDate);
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * コンビニで入力する電話番号を取得する
     * @return 
     */
    public String getKessaiTel() {
        String ret = "";

        if (this.telNo.length() > 0) {
            ret = this.telNo;
        } else {
            ret = this.cellphoneNo;
        }
        ret = ret.replace("-", "");
        ret = ret.replace("+", "");
        ret = ret.replace(" ", "");
        if (ret.length() > 11) {
            ret = ret.substring(0, 11);
        }

        return ret;
    }
}
