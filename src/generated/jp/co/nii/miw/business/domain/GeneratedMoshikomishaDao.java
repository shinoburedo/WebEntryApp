package jp.co.nii.miw.business.domain;

/**
 * 生成された 申込者 DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedMoshikomishaDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "MOSHIKOMISHA";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "申込者";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedMoshikomisha bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedMoshikomisha find(GeneratedMoshikomisha bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedMoshikomisha bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedMoshikomisha bo);
}
