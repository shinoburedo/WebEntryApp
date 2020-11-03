package jp.co.nii.miw.business.domain;

/**
 * 生成された メニュー DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedMenuDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "MENU";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "メニュー";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedMenu bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedMenu find(GeneratedMenu bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedMenu bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedMenu bo);
}
