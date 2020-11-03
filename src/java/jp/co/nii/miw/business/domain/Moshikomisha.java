package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 申込者 BOクラス
 * @author DB管理ツール
 */
public class Moshikomisha extends GeneratedMoshikomisha {

    /**
     * インスタンスを生成する。
     */
    public Moshikomisha() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Moshikomisha(String connectionUser) {
        super(connectionUser);
    }
    
    /** 生年月日（元号） */
    private String birthdayEra;
    /** 生年月日＿年 **/
    private String  birthdayYy;
    /** 生年月日＿月 **/
    private String  birthdayMm;
    /** 生年月日＿日 **/
    private String  birthdayDd;

    
//    /**
//     * 更新用にロックして検索する。
//     * @param userID ＩＤ
//     * @return 検索したデータ（このクラスのインスタンス）<br>
//     *         該当するデータが存在しない場合はnullを返す。
//     */
//    public Moshikomisha lockFind(String userId) {
//        Moshikomisha bo = new Moshikomisha();
//        bo = (Moshikomisha) dao.find(userId, TransactionUtility.LOCK_NOWAIT);
//        return bo;
//    }
//    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param userId ユーザーＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha find(String userId) {
        return find(userId, TransactionUtility.NO_LOCK);
    }
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 検索条件に論理削除フラグOFFを含める
     * @param userId ユーザーＩＤ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Moshikomisha find(String userId, String lockMode) {
        Moshikomisha bo = new Moshikomisha();
        bo.setUserId(userId);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Moshikomisha) dao.find(bo, lockMode);
        return bo;
    }
    /**
     * 検索する。<br>
     * 検索キーとしてＩＤ・パスワードを指定する。
     * @param userID ＩＤ
     * @param passwd パスワード
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findLogin(String userId, String passwd, String lockMode) {
        Moshikomisha bo = new Moshikomisha();
        bo = (Moshikomisha) dao.findLogin(userId, passwd, lockMode);
        return bo;
    }
    /**
     * 検索する。（二重登録確認用）<br>
     * 検索キーとしてＩＤ・申込有フラグを指定する。
     * @param userID ＩＤ
     * @param moshikomiAriFlg 申込有フラグ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findDouble(String userId, String moshikomiAriFlg, String lockMode) {
        Moshikomisha bo = new Moshikomisha();
        bo = (Moshikomisha) dao.findDouble(userId, moshikomiAriFlg, lockMode);
        return bo;
    }
    
    /**
     * 検索する。（二重登録確認用）<br>
     * 検索キーとしてカナ氏名・生年月日・申込有フラグを指定する。
     * @param mskInf
     * @return 
     */
    public Moshikomisha findDoubleForShimeiEtc(MskToroku mskInf,String lockMode){
        Moshikomisha bo = new Moshikomisha();
        bo = (Moshikomisha) dao.findDoubleForShimeiEtc(mskInf, lockMode);
        return bo;
    }
    
    /**
     * 検索する。<br>
     * 検索キー：ＩＤ・カナ氏名・生年月日・申込有フラグ・論理削除フラグ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findForPswRem() {
        return dao.findForPswRem(this, TransactionUtility.NO_LOCK);
    }
    
    /**
     * パスワードを更新する。<br>
     * @param password 新パスワード
     * @param kosinUserId 更新者
     */
    public void updatePassword(String password, String kosinUserId) {
        SystemTime sysTim = new SystemTime();
        this.setPasswd(password);
        this.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        this.setKoshinDate(sysTim.getymd1());
        this.setKoshinTime(sysTim.gethms1());
        this.setKoshinUserId(kosinUserId);
        dao.update(this);
    }
    
    /**
     * ロックして検索する。
     * ロック取得エラー時はリトライする。
     * @param userID ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findRetry(String userId) {
        Moshikomisha bo = new Moshikomisha();
        bo.setUserId(userId);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return (Moshikomisha) dao.findRetry(bo);
    }
    
    /**
     * 申込出願・決済処理後の更新
     */
    public void updateAfterKessai() {
            dao.updateAfterKessai(this);
    }
    
    /**
     * ご本人様確認用の質問・回答　質問の名称
     * @return ご本人様確認用の質問・回答　質問の名称
     */
    public String getPasswdQuestionCodeDisp() {
        String ret = "";
        ret = CodeValueName.getPasswdQuestionCodeName(this.getPasswdQuestionCode1());
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
     * 生年月日＿年を設定する
     * @param birthdayYy 設定するbirthdayYyの値
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
}
