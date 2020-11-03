package jp.co.nii.miw.business.domain;

/**
 * 申込団体 DAOインターフェース
 * @author DB管理ツール
 */
public interface MoshikomiDantaiDao extends GeneratedMoshikomiDantaiDao {

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
//    MoshikomiDantai find(MoshikomiDantai bo, String lockMode);
    /**
     * ロックして検索する。<br>
     * ロック取得エラー時はリトライする。
     * @param dantai 申込団体BO
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai findRetry(MoshikomiDantai dantai);

    public MoshikomiDantai findLogin(MoshikomiDantai dantai, String lock);

    /**
     * 検索する。<br>
     * 検索キーとして年度・回数・イベントコード・団体コードを指定する。
     * @param nendo
     * @param kaisu
     * @param dntCode
     * @param lock
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *          該当するデータが存在しない場合はnullを返す。
     */
    public MoshikomiDantai selectMoshikomiDantaiForDntCode(String nendo, String kaisu, String dntCode, String lock);
}
