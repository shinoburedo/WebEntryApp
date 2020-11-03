package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 申込団体 BOクラス
 * @author DB管理ツール
 */
public class MoshikomiDantai extends GeneratedMoshikomiDantai {

    /**
     * インスタンスを生成する。
     */
    public MoshikomiDantai() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public MoshikomiDantai(String connectionUser) {
        super(connectionUser);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param moshikomiDantaiCode 申込団体コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai find(String moshikomiDantaiCode) {
        return find(moshikomiDantaiCode, TransactionUtility.NO_LOCK);
    }

    /**
     * パスワードを更新する。<br>
     * @param password 新パスワード
     * @param kosinUserId 更新者
     */
    public void updatePassword(String password, String kosinUserId) {
        SystemTime sysTim = new SystemTime();
        this.setDantaiMoshikomiPasswd(password);
        this.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        this.setKoshinDate(sysTim.getymd1());
        this.setKoshinTime(sysTim.gethms1());
        this.setKoshinUserId(kosinUserId);
        dao.update(this);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 条件に論理削除フラグOFFを含める
     * @param moshikomiDantaiCode 申込団体コード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected MoshikomiDantai find(String moshikomiDantaiCode, String lockMode) {
        MoshikomiDantai bo = new MoshikomiDantai();
//        bo.setMoshikomiDantaiCode(moshikomiDantaiCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (MoshikomiDantai) dao.find(bo, lockMode);
        return bo;
    }

    /**
     * ロックして検索する。
     * ロック取得エラー時はリトライする。
     * @param dntInfo 申込団体BEAN
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai findRetry(DntInf dntInfo) {
        MoshikomiDantai bo = new MoshikomiDantai();
        bo.setDantaiMoshikomiUketsukeNo(dntInfo.getDantaiMoshikomiUketsukeNo());
        bo.setNendo(dntInfo.getNendo());
        bo.setKaisu(dntInfo.getKaisu());
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return (MoshikomiDantai) dao.findRetry(bo);
    }

    /**
     * 検索する。
     * @param dntInfo 申込団体BEAN
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai find(DntInf dntInfo) {
        MoshikomiDantai bo = new MoshikomiDantai();
        bo.setDantaiMoshikomiUketsukeNo(dntInfo.getDantaiMoshikomiUketsukeNo());
        bo.setNendo(dntInfo.getNendo());
        bo.setKaisu(dntInfo.getKaisu());
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return (MoshikomiDantai) dao.find(bo, TransactionUtility.NO_LOCK);
    }

    /**
     * ログイン用検索をする
     * @param dntInfo
     * @param lockmode
     * @return 
     */
    public MoshikomiDantai findLogin(String djCd, String djPsw, String nendo, String kaisu, String lockmode) {
        MoshikomiDantai bo = new MoshikomiDantai();
        bo.setDantaiMoshikomiUketsukeNo(djCd);
        bo.setDantaiMoshikomiPasswd(djPsw);
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        return dao.findLogin(bo, lockmode);
    }

    /**
     * 検索する。<br>
     * 検索キーとして年度・回数・イベントコード・団体コードを指定する。
     * @param nendo   年度
     * @param kaisu   回数
     * @param dntCode 団体コード
     * @param lock    ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai selectMoshikomiDantaiForDntCode(String nendo, String kaisu, String dntCode, String lock) {
        return dao.selectMoshikomiDantaiForDntCode(nendo, kaisu, dntCode, lock);
    }
}
