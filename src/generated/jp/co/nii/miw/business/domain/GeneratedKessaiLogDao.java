package jp.co.nii.miw.business.domain;

/**
 * 生成された 決済ログ DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedKessaiLogDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "KESSAI_LOG";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "決済ログ";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedKessaiLog bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedKessaiLog find(GeneratedKessaiLog bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedKessaiLog bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedKessaiLog bo);
}
