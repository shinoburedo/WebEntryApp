package jp.co.nii.miw.business.domain;

/**
 * 生成された 試験地 DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedShikenchiDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "SHIKENCHI";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "試験地";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedShikenchi bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedShikenchi find(GeneratedShikenchi bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedShikenchi bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedShikenchi bo);
}
