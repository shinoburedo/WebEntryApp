package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 団体 BOクラス
 * @author DB管理ツール
 */
public class Dantai extends GeneratedDantai {

    /**
     * インスタンスを生成する。
     */
    public Dantai() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Dantai(String connectionUser) {
        super(connectionUser);
    }

    /**
     * ログイン用団体を検索
     * @param dntcd   団体コード
     * @param dntpswd 団体パスワード
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findLogin(String dntcd, String dntpswd, String lockMode) {
        Dantai bo = new Dantai();
        bo = (Dantai) dao.findLogin(dntcd, dntpswd, lockMode);
        return bo;
    }

    /**
     * 検索する。<br>
     * 検索キー：団体コード・団体事務担当者氏名カナ・論理削除フラグ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findForPswRem() {
        return dao.findForPswRem(this, TransactionUtility.NO_LOCK);
    }

    /**
     * ロックして検索する。
     * ロック取得エラー時はリトライする。
     * @param dntCode 団体コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findRetry(String dntCode) {
        Dantai bo = new Dantai();
        bo.setDantaiCode(dntCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return (Dantai) dao.findRetry(bo);
    }

    /**
     * 更新する。<br>
     * キー：団体コード
     */
    public void updateForShutugan() {
        dao.updateForShutugan(this);
    }

//    /**
//     * パスワードを更新する。<br>
//     * @param password 新パスワード
//     * @param kosinUserId 更新者
//     */
//    public void updatePassword(String password, String kosinUserId) {
//        SystemTime sysTim = new SystemTime();
//        this.setDantaiPasswd(password);
//        this.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
//        this.setKoshinDate(sysTim.getymd1());
//        this.setKoshinTime(sysTim.gethms1());
//        this.setKoshinUserId(kosinUserId);
//        dao.update(this);
//    }

    /**
     * 団体確認用の質問・回答　質問の名称
     * @return 団体確認用の質問・回答　質問の名称
     */
    public String getPasswdQuestionCodeDisp() {
        String ret = "";
        ret = CodeValueName.getPasswdQuestionCodeName(this.getPasswdQuestionCode1());
        return ret;
    }
}
