package jp.co.nii.miw.business.domain;

/**
 * 生成された 申込 DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedMoshikomiDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "MOSHIKOMI";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "申込";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedMoshikomi bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedMoshikomi find(GeneratedMoshikomi bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedMoshikomi bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedMoshikomi bo);
}
