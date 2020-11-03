package jp.co.nii.miw.business.service.dantai;

import java.text.DecimalFormat;
import java.util.List;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.sew.utility.StringUtility;

import org.apache.struts.action.ActionMessages;

/**
 * タイトル: DntInf
 * 説明: 団体申込出願のデータbean
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 * @author r-ehara
 */
public class DntInf {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 年度 */
    private String nendo;
    /** 回数 */
    private String kaisu;
    /** イベントコード */
    private String event;
    /** 団体申込者登録用ID */
    private String dantaiMoshikomiUketsukeNo;
    /** 団体コード */
    private String dantaiCode;
    /** 団体パスワード */
    private String dantaiPasswd;
    /** 団体パスワード確認 */
    private String dantaiPasswdKakunin;
    /** 団体名 */
    private String dantaiName;
    /** 団体名カナ */
    private String dantaiNameKana;
    /** 団体郵便番号１ */
    private String yubinNo1;
    /** 団体郵便番号２ */
    private String yubinNo2;
    /** 団体都道府県 */
    private String todofuken;
    /** 団体住所1(市区町村) */
    private String jusho1;
    /** 団体住所2(町名) */
    private String jusho2;
    /** 団体住所3(丁目番地)*/
    private String jusho3;
    /** 団体住所3(ビル名建物名)*/
    private String jusho4;
    /** 団体事務担当者（姓）カナ */
    private String dantaiJimuTantoshaShimeiSeiKana;
    /** 団体事務担当者（名）カナ */
    private String dantaiJimuTantoshaShimeiMeiKana;
    /** 団体事務担当者（姓） */
    private String dantaiJimuTantoshaShimeiSei;
    /** 団体事務担当者（名） */
    private String dantaiJimuTantoshaShimeiMei;
    /** 団体事務担当者電話番号 */
    private String dantaiJimuTantoshaTelNo;
    /** 内線番号 */
    private String extNo;
    /** 団体事務担当者ＦＡＸ番号 */
    private String dantaiJimuTantoshaFaxNo;
    /** 団体事務担当者メールアドレス */
    private String dantaiJimuTantoshaMailAddress;
    /** 団体事務担当者メールアドレス（確認用） */
    private String dantaiJimuTantoshaMailAddressKakunin;
    /** 申込者数(医科) */
    private String moshikomishaSuIka;
    /** 申込者数(歯科) */
    private String moshikomishaSuShika;
    /** 登録者数(医科) */
    private String torokushaSuIka;
    /** 登録者数(歯科) */
    private String torokushaSuShika;
    /** 発送物の送付先 */
    private String hassosakiKbn;
    /** 団体パスワード質問 */
    private String passwdQuestionCode1;
    /** 団体パスワード回答*/
    private String passwdAnswer1;
    /** 団体申込者登録用パスワード */
    private String dantaiMoshikomiPasswd;
    /** 団体申込者登録用パスワード（確認用） */
    private String dantaiMoshikomiPasswdKakunin;
    /** 団体申込出願登録日 */
    private String torokuDate;
    /** 仮受付日 */
    private String kariUketsukeBi = "";
    /** 受付完了日 */
    private String moshikomiFinishBi = "";
    /** 決済状況 */
    private String kessaiJokyoKbn;
    /** 決済期限日 */
    private String kessaiKigenBi;
    /** 手続き状況区分 */
    private String tetsudukiJokyoKbn;
    /** 決済方法区分 */
    private String kessaiHohoKbn;
    /** 決済金額 */
    private String kessaiKingaku;
    /** 住所リスト **/
    private List jushoList;
    /** 検定料 **/
    private String kenteiryo;
    /** 通信欄（申込メモ）**/
    private String moshikomiMemo;

    /**
     * @return dntpswd
     */
    public String getEvent() {
        return event;
    }

    /**
     * @param dntpswd 
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * @return dntpswd
     */
    public String getDantaiCode() {
        return dantaiCode;
    }

    /**
     * @param dntpswd 
     */
    public void setDantaiCode(String code) {
        this.dantaiCode = code;
    }

    /**
     * @return dntpswd
     */
    public String getDantaiMoshikomiUketsukeNo() {
        return dantaiMoshikomiUketsukeNo;
    }

    /**
     * @param dntpswd 
     */
    public void setDantaiMoshikomiUketsukeNo(String code) {
        this.dantaiMoshikomiUketsukeNo = code;
    }

    /**
     * @return dntpswd
     */
    public String getDantaiPasswd() {
        return dantaiPasswd;
    }

    /**
     * @param dntpswd 
     */
    public void setDantaiPasswd(String pswd) {
        this.dantaiPasswd = pswd;
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
     * @return kaisu
     */
    public String getKaisu() {
        return kaisu;
    }

    /**
     * @param kaisu
     */
    public void setKaisu(String kaisu) {
        this.kaisu = kaisu;
    }

    /**
     * @return dntpswdkakunin
     */
    public String getDantaiPasswdKakunin() {
        return dantaiPasswdKakunin;
    }

    /**
     * @param dntpswdkakunin
     */
    public void setDantaiPasswdKakunin(String dntpswdkakunin) {
        this.dantaiPasswdKakunin = dntpswdkakunin;
    }

    /**
     * @return dntname
     */
    public String getDantaiName() {
        return dantaiName;
    }

    /**
     * @param dntname
     */
    public void setDantaiName(String dntname) {
        this.dantaiName = dntname;
    }

    /**
     * @return dntnamekana
     */
    public String getDantaiNameKana() {
        return dantaiNameKana;
    }

    /**
     * @param dntnamekana
     */
    public void setDantaiNameKana(String dntnamekana) {
        this.dantaiNameKana = dntnamekana;
    }

    /**
     * @return yubinno
     */
    public String getDantaiYubinNo() {
        return getYubinNo1() + getYubinNo2();
    }

    /**
     * @return yubinno
     */
    public String getYubinNo1() {
        return yubinNo1;
    }

    /**
     * @param yubinno
     */
    public void setYubinNo1(String yubinno1) {
        this.yubinNo1 = yubinno1;
    }

    /**
     * @return yubinno
     */
    public String getYubinNo2() {
        return yubinNo2;
    }

    /**
     * @param yubinno
     */
    public void setYubinNo2(String yubinno2) {
        this.yubinNo2 = yubinno2;
    }

    /**
     * @return address
     */
    public String getDantaiJusho() {
        return todofuken + MiwConstants.JUSHO_SPLIT_STRING + jusho1 + MiwConstants.JUSHO_SPLIT_STRING
                + jusho2 + MiwConstants.JUSHO_SPLIT_STRING + jusho3 + MiwConstants.JUSHO_SPLIT_STRING + jusho4;
    }

    /**
     * @return address
     */
    public String getDantaiJushoDisp() {
        return todofuken + "　" + jusho1 + "　" + jusho2 + "　" + jusho3 + "　" + jusho4;
    }

    /**
     * @return todofuken
     */
    public String getTodofuken() {
        return todofuken;
    }

    /**
     * @param todofuken
     */
    public void setTodofuken(String todofuken) {
        this.todofuken = todofuken;
    }

//    /**
//     * 都道府県名の名称
//     * @return 都道府県名の名称
//     */
//    public String getTodofukenDisp() {
//        String ret = "";
//        ret = CodeValueName.getTodofukenCodeName(todofukenCode);
//        return ret;
//    }
    /**
     * @return address1
     */
    public String getJusho1() {
        return jusho1;
    }

    /**
     * @param address1
     */
    public void setJusho1(String address1) {
        this.jusho1 = address1;
    }

    /**
     * @return address2
     */
    public String getJusho2() {
        return jusho2;
    }

    /**
     * @param address2
     */
    public void setJusho2(String address2) {
        this.jusho2 = address2;
    }

    /**
     * @return address3
     */
    public String getJusho3() {
        return jusho3;
    }

    /**
     * @param address3
     */
    public void setJusho3(String address3) {
        this.jusho3 = address3;
    }

    /**
     * @return address4
     */
    public String getJusho4() {
        return jusho4;
    }

    /**
     * @param address4
     */
    public void setJusho4(String address4) {
        this.jusho4 = address4;
    }

    /**
     * @return dnttantokanasei
     */
    public String getDantaiJimuTantoshaShimeiSeiKana() {
        return dantaiJimuTantoshaShimeiSeiKana;
    }

    /**
     * @param dnttantokanasei
     */
    public void setDantaiJimuTantoshaShimeiSeiKana(String dnttantokanasei) {
        this.dantaiJimuTantoshaShimeiSeiKana = dnttantokanasei;
    }

    /**
     * @return dnttantokanamei
     */
    public String getDantaiJimuTantoshaShimeiMeiKana() {
        return dantaiJimuTantoshaShimeiMeiKana;
    }

    /**
     * @param dnttantokanamei
     */
    public void setDantaiJimuTantoshaShimeiMeiKana(String dnttantokanamei) {
        this.dantaiJimuTantoshaShimeiMeiKana = dnttantokanamei;
    }

    /**
     * @return dnttantoshimeisei
     */
    public String getDantaiJimuTantoshaShimeiSei() {
        return dantaiJimuTantoshaShimeiSei;
    }

    /**
     * @param dnttantoshimeisei
     */
    public void setDantaiJimuTantoshaShimeiSei(String dnttantoshimeisei) {
        this.dantaiJimuTantoshaShimeiSei = dnttantoshimeisei;
    }

    /**
     * @return dnttantoshimeimei
     */
    public String getDantaiJimuTantoshaShimeiMei() {
        return dantaiJimuTantoshaShimeiMei;
    }

    /**
     * @param dnttantoshimeimei
     */
    public void setDantaiJimuTantoshaShimeiMei(String dnttantoshimeimei) {
        this.dantaiJimuTantoshaShimeiMei = dnttantoshimeimei;
    }

    /**
     * @return telno
     */
    public String getDantaiJimuTantoshaTelNo() {
        return dantaiJimuTantoshaTelNo;
    }

    /**
     * @param telno
     */
    public void setDantaiJimuTantoshaTelNo(String telno) {
        this.dantaiJimuTantoshaTelNo = telno;
    }

    /**
     * @return faxno
     */
    public String getDantaiJimuTantoshaFaxNo() {
        return dantaiJimuTantoshaFaxNo;
    }

    /**
     * @param faxno
     */
    public void setDantaiJimuTantoshaFaxNo(String faxno) {
        this.dantaiJimuTantoshaFaxNo = faxno;
    }

    /**
     * @return the mailAddress
     */
    public String getDantaiJimuTantoshaMailAddress() {
        return dantaiJimuTantoshaMailAddress;
    }

    /**
     * @param mailAddress the mailAddress to set
     */
    public void setDantaiJimuTantoshaMailAddress(String mailAddress) {
        this.dantaiJimuTantoshaMailAddress = mailAddress;
    }

    /**
     * @return the mailAddressKakunin
     */
    public String getDantaiJimuTantoshaMailAddressKakunin() {
        return dantaiJimuTantoshaMailAddressKakunin;
    }

    /**
     * @param mailAddressKakunin the mailAddressKakunin to set
     */
    public void setDantaiJimuTantoshaMailAddressKakunin(String mailAddressKakunin) {
        this.dantaiJimuTantoshaMailAddressKakunin = mailAddressKakunin;
    }

    /**
     * @return moshikomishasu_ika
     */
    public String getMoshikomishaSuIka() {
        return moshikomishaSuIka;
    }

    /**
     * @param moshikomishasu_ika
     */
    public void setMoshikomishaSuIka(String moshikomishasu_ika) {
        this.moshikomishaSuIka = moshikomishasu_ika;
    }

    /**
     * @return moshikomishasu_sika
     */
    public String getMoshikomishaSuShika() {
        return moshikomishaSuShika;
    }

    /**
     * @param moshikomishasu_sika
     */
    public void setMoshikomishaSuShika(String moshikomishasu_sika) {
        this.moshikomishaSuShika = moshikomishasu_sika;
    }

    /**
     * @return torokushaSuIka
     */
    public String getTorokushaSuIka() {
        return torokushaSuIka;
    }

    /**
     * @param torokushaSuIka
     */
    public void setTorokushaSuIka(String torokushaSuIka) {
        this.torokushaSuIka = torokushaSuIka;
    }

    /**
     * @return torokushaSuShika
     */
    public String getTorokushaSuShika() {
        return torokushaSuShika;
    }

    /**
     * @param torokushaSuShika
     */
    public void setTorokushaSuShika(String torokushaSuShika) {
        this.torokushaSuShika = torokushaSuShika;
    }

    /**
     * @return soufusaki
     */
    public String getHassosakiKbn() {
        return hassosakiKbn;
    }

    /**
     * @param soufusaki
     */
    public void setHassosakiKbn(String soufusaki) {
        this.hassosakiKbn = soufusaki;
    }

    /**
     * 団体発送物の送付先を取得する
     * @return passwdQuestionCode
     */
    public String getHassosakiKbnDisp() {
        String ret = "";
        ret = CodeValueName.getHassosakiKbnName(hassosakiKbn);
        return ret;
    }

    /**
     * 団体パスワードの質問を設定する
     * @param dntpasswdQuestionCode 設定するdntpasswdQuestionCodeの値
     */
    public void setPasswdQuestionCode1(String passwdQuestionCode) {
        this.passwdQuestionCode1 = passwdQuestionCode;
    }

    /**
     * 団体パスワードの質問を取得する
     * @return passwdQuestionCode
     */
    public String getPasswdQuestionCode1() {
        return passwdQuestionCode1;
    }

    /**
     * 団体パスワードの質問の名称
     * @return 団体パスワードの質問の名称
     */
    public String getPasswdQuestionCode1Disp() {
        String ret = "";
        ret = CodeValueName.getPasswdQuestionCodeName(passwdQuestionCode1);
        return ret;
    }

    /**
     * 団体パスワードの回答を設定する
     * @param dntpasswdAnswer 設定するpasswdAnswerの値
     */
    public void setPasswdAnswer1(String passwdAnswer) {
        this.passwdAnswer1 = passwdAnswer;
    }

    /**
     * 団体パスワードの回答を取得する
     * @return dntpasswdAnswer
     */
    public String getPasswdAnswer1() {
        return passwdAnswer1;
    }

    /**
     * 団体パスワードの回答表示用を取得する
     * @return dntpasswdAnswer
     */
    public String getPasswdAnswer1Disp() {
        String retStr = "";
        String answer = getPasswdAnswer1();
        int ansLen = answer.length();
        for (int i = 0; i < ansLen; i++) {
            retStr += "＊";
        }
        return retStr;
    }

    /**
     * 団体申込者登録用パスワードを設定する
     * @param dntjyupasswd 設定するdntjyupasswdの値
     */
    public void setDantaiMoshikomiPasswd(String dntjyupasswd) {
        this.dantaiMoshikomiPasswd = dntjyupasswd;
    }

    /**
     * 団体申込者登録用パスワードを取得する
     * @return dntjyupasswd
     */
    public String getDantaiMoshikomiPasswd() {
        return dantaiMoshikomiPasswd;
    }

    /**
     * 団体申込者登録用パスワードの確認を設定する
     * @param dntjyupasswdkakunin 設定するdntjyupasswdの値
     */
    public void setDantaiMoshikomiPasswdKakunin(String dntjyupasswdkakunin) {
        this.dantaiMoshikomiPasswdKakunin = dntjyupasswdkakunin;
    }

    /**
     * 団体申込者登録用パスワードの確認を取得する
     * @return dntjyupasswd
     */
    public String getDantaiMoshikomiPasswdKakunin() {
        return dantaiMoshikomiPasswdKakunin;
    }

    /**
     * 団体申込出願登録日を設定する
     */
    public void setTorokuDate(String torokuDate) {
        this.torokuDate = torokuDate;
    }

    /**
     * 団体申込出願登録日を取得する
     */
    public String getTorokuDate() {
        return torokuDate;
    }

    /**
     * 団体申込出願登録日の表示用を取得する（スラッシュ区切り）
     * @return 
     */
    public String getTorokuDateDispSlash() {
        return MiwStringUtility.formatDateSlash(torokuDate);
    }

    /**
     * 仮受付日を設定する
     * @param kariUketsukeBi 
     */
    public void setKariUketsukeBi(String kariUketsukeBi) {
        this.kariUketsukeBi = kariUketsukeBi;
    }

    /**
     * 仮受付日を取得する
     * @return kariUketsukeBi
     */
    public String getKariUketsukeBi() {
        return kariUketsukeBi;
    }

    /**
     * 仮受付日の表示用を取得する（スラッシュ区切り）
     * @return 
     */
    public String getKariUketsukeBiDispSlash() {
        return MiwStringUtility.formatDateSlash(kariUketsukeBi);
    }

    /**
     * 受付完了日を設定する
     * @param moshikomiFinishBi 
     */
    public void setMoshikomiFinishBi(String moshikomiFinishBi) {
        this.moshikomiFinishBi = moshikomiFinishBi;
    }

    /**
     * 受付完了日を取得する
     * @return 
     */
    public String getMoshikomiFinishBi() {
        return moshikomiFinishBi;
    }

    /**
     * 受付完了日の表示用を取得する（スラッシュ区切り）
     * @return 
     */
    public String getMoshikomiFinishBiDispSlash() {
        return MiwStringUtility.formatDateSlash(moshikomiFinishBi);
    }

    /**
     * 決済状況区分を設定する
     * @param kessaiJokyoKbn 
     */
    public void setKessaiJokyoKbn(String kessaiJokyoKbn) {
        this.kessaiJokyoKbn = kessaiJokyoKbn;
    }

    /**
     * 決済状況区分を取得する
     * @return 
     */
    public String getKessaiJokyoKbn() {
        return kessaiJokyoKbn;
    }

    /**
     * 決済状況区分の名称
     * @return 
     */
    public String getKessaiJokyoKbnDisp() {
        String ret = "";
        ret = CodeValueName.getKessaiJokyoKbnName(kessaiJokyoKbn);
        return ret;
    }

    /**
     * 決済期限日を設定する
     * @param kessaiJokyoKbn 
     */
    public void setKessaiKigenBi(String kessaiKigenBi) {
        this.kessaiKigenBi = kessaiKigenBi;
    }

    /**
     * 決済期限日を取得する
     * @return 
     */
    public String getKessaiKigenBi() {
        return kessaiKigenBi;
    }

    /**
     * 決済期限日の表示用を取得する（スラッシュ区切り）
     * @return 
     */
    public String getKessaiKigenBiDispSlash() {
        return MiwStringUtility.formatDateSlash(kessaiKigenBi);
    }

    /**
     * 受付状況区分を設定する
     * @param kessaiJokyoKbn 
     */
    public void setTetsudukiJokyoKbn(String tetsudukiJokyoKbn) {
        this.tetsudukiJokyoKbn = tetsudukiJokyoKbn;
    }

    /**
     * 受付状況区分を取得する
     * @return 
     */
    public String getTetsudukiJokyoKbn() {
        return tetsudukiJokyoKbn;
    }

    /**
     * 受付状況区分の名称
     * @return 
     */
    public String getTetsudukiJokyoKbnDisp() {
        String ret = "";
        ret = CodeValueName.getTetsudukiJokyoKbnName(tetsudukiJokyoKbn);
        return ret;
    }

    /**
     * 決済方法区分を設定する
     * @param kessaiJokyoKbn 
     */
    public void setKessaiHohoKbn(String kessaiHohoKbn) {
        this.kessaiHohoKbn = kessaiHohoKbn;
    }

    /**
     * 決済方法区分を取得する
     * @return 
     */
    public String getKessaiHohoKbn() {
        return kessaiHohoKbn;
    }

    /**
     * 決済方法区分の名称
     * @return 
     */
    public String getKessaiHohoKbnDisp() {
        String ret = "";
        ret = CodeValueName.getKessaiHohoKbnName(kessaiHohoKbn);
        return ret;
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
     * 振込人名を取得する
     * @return 
     */
    public String getFurikomiName() {
        if (dantaiMoshikomiUketsukeNo.length() + dantaiNameKana.length() > 20) {
            return (dantaiMoshikomiUketsukeNo + dantaiNameKana).substring(0, 20);
        } else {
            return (dantaiMoshikomiUketsukeNo + dantaiNameKana).substring(0, dantaiMoshikomiUketsukeNo.length() + dantaiNameKana.length());
        }
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
     * 通信欄を設定する
     * @param moshikomiMemo 設定するmoshikomiMemoの値
     */
    public void setMoshikomiMemo(String moshikomiMemo) {
        this.moshikomiMemo = moshikomiMemo;
    }

    /**
     * 通信欄を取得する
     * @return moshikomiMemo
     */
    public String getMoshikomiMemo() {
        return moshikomiMemo;
    }

    /**
     * 通信欄の表示を返す
     * @return 
     */
    public String getMoshikomiMemoDisp() {
        return MiwStringUtility.getNewLineReplaceBr(this.moshikomiMemo);
    }

    /**
     * 銀行名を返す
     * @return 
     */
    public String getBankName() {
        String bank = "";
        if (MiwConstants.KESSAI_HOHO_KBN_BANK.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.BANK_NAME;
        } else if (MiwConstants.KESSAI_HOHO_KBN_POST.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.POST_NAME;
        }
        return bank;
    }

    /**
     * 振込先支店名を返す
     * @return 
     */
    public String getBranchName() {
        String bank = "";
        if (MiwConstants.KESSAI_HOHO_KBN_BANK.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.BANK_BRANCH_NAME;
        } else if (MiwConstants.KESSAI_HOHO_KBN_POST.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.POST_BRANCH_NAME;
        }
        return bank;
    }

    /**
     * 預金種類を返す
     * @return 
     */
    public String getDepositKind() {
        String bank = "";
        if (MiwConstants.KESSAI_HOHO_KBN_BANK.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.BANK_DEPOSIT_KIND;
        } else if (MiwConstants.KESSAI_HOHO_KBN_POST.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.POST_DEPOSIT_KIND;
        }
        return bank;
    }

    /**
     * 口座番号を返す
     * @return 
     */
    public String getAccountNum() {
        String bank = "";
        if (MiwConstants.KESSAI_HOHO_KBN_BANK.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.BANK_ACCOUNT_NUM;
        } else if (MiwConstants.KESSAI_HOHO_KBN_POST.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.POST_ACCOUNT_NUM;
        }
        return bank;
    }

    /**
     * 口座名義を返す
     * @return 
     */
    public String getAccountName() {
        String bank = "";
        if (MiwConstants.KESSAI_HOHO_KBN_BANK.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.BANK_ACCOUNT_NAME;
        } else if (MiwConstants.KESSAI_HOHO_KBN_POST.equals(this.kessaiHohoKbn)) {
            bank = MiwConstants.POST_ACCOUNT_NAME;
        }
        return bank;
    }

    /**
     * 決済状況区分が未確認だったらtrueを返す
     * @return 
     */
    public boolean getIsKessaiJokyoMi() {
        if (MiwConstants.KESSAI_JOKYO_KBN_MI.equals(this.kessaiJokyoKbn)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手続状況区分が承認前だったらtrueを返す
     * @return 
     */
    public boolean getIsTetsudukiJokyoShoninMae() {
        if (MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE.equals(this.tetsudukiJokyoKbn)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手続状況区分が仮受付だったらtrueを返す
     * @return 
     */
    public boolean getIsTetsudukiJokyoKariUketuke() {
        if (MiwConstants.TETSUDUKI_JOKYO_KBN_KARI.equals(this.tetsudukiJokyoKbn)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手続状況区分が受付完了だったらtrueを返す
     * @return 
     */
    public boolean getIsTetsudukiJokyoUketukeEnd() {
        if (MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO.equals(this.tetsudukiJokyoKbn)) {
            return true;
        } else {
            return false;
        }
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

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 各値のスペースを削除する
     */
    public void deleteSpace() {
        this.dantaiName = StringUtility.removeEdgeSpace(this.dantaiName);
        this.dantaiNameKana = StringUtility.removeEdgeSpace(this.dantaiNameKana);
        this.jusho1 = StringUtility.removeEdgeSpace(this.jusho1);
        this.jusho2 = StringUtility.removeEdgeSpace(this.jusho2);
        this.jusho3 = StringUtility.removeEdgeSpace(this.jusho3);
        this.jusho4 = StringUtility.removeEdgeSpace(this.jusho4);
        this.yubinNo1 = StringUtility.removeSpace(this.yubinNo1);
        this.yubinNo2 = StringUtility.removeSpace(this.yubinNo2);
        this.dantaiJimuTantoshaShimeiSeiKana = StringUtility.removeSpace(this.dantaiJimuTantoshaShimeiSeiKana);
        this.dantaiJimuTantoshaShimeiMeiKana = StringUtility.removeSpace(this.dantaiJimuTantoshaShimeiMeiKana);
        this.dantaiJimuTantoshaShimeiSei = StringUtility.removeSpace(this.dantaiJimuTantoshaShimeiSei);
        this.dantaiJimuTantoshaShimeiMei = StringUtility.removeSpace(this.dantaiJimuTantoshaShimeiMei);
        this.extNo = StringUtility.removeSpace(this.extNo);
        this.dantaiJimuTantoshaTelNo = StringUtility.removeSpace(this.dantaiJimuTantoshaTelNo);
        this.dantaiJimuTantoshaFaxNo = StringUtility.removeSpace(this.dantaiJimuTantoshaFaxNo);
        this.dantaiJimuTantoshaMailAddress = StringUtility.removeSpace(this.dantaiJimuTantoshaMailAddress);
        this.dantaiJimuTantoshaMailAddressKakunin = StringUtility.removeSpace(this.dantaiJimuTantoshaMailAddressKakunin);
        this.moshikomishaSuIka = StringUtility.removeSpace(this.moshikomishaSuIka);
        this.moshikomishaSuShika = StringUtility.removeSpace(this.moshikomishaSuShika);
        this.dantaiPasswd = StringUtility.removeSpace(this.dantaiPasswd);
        this.dantaiPasswdKakunin = StringUtility.removeSpace(this.dantaiPasswdKakunin);
        this.passwdAnswer1 = StringUtility.removeSpace(this.passwdAnswer1);
        this.dantaiMoshikomiPasswd = StringUtility.removeSpace(this.dantaiMoshikomiPasswd);
        this.dantaiMoshikomiPasswdKakunin = StringUtility.removeSpace(this.dantaiMoshikomiPasswdKakunin);
//        this.moshikomiMemo = StringUtility.removeEdgeSpace(this.moshikomiMemo);


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
