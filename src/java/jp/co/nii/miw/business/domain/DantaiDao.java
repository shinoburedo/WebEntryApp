package jp.co.nii.miw.business.domain;

/**
 * 団体 DAOインターフェース
 * @author DB管理ツール
 */
public interface DantaiDao extends GeneratedDantaiDao {

    /**
     * ログイン用団体を検索
     * @param dntcd   団体コード
     * @param dntpswd 団体パスワード
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findLogin(String dntcd, String dntpswd, String lockMode);
    
    /**
     * ロックして検索する。<br>
     * ロック取得エラー時はリトライする。
     * @param dantai 団体BO
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findRetry(Dantai dantai);
    /**
     * 検索する。<br>
     * 検索キー：団体コード・団体事務担当者氏名カナ・論理削除フラグ
     * @param dantai 
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Dantai findForPswRem(Dantai dantai, String lockMode);
    
     /**
     * 申込出願用に団体を更新
     * @param damtai   団体BO
     */
    public void updateForShutugan(Dantai dantai);
}
