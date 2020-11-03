package jp.co.nii.miw.business.domain;

/**
 * 生成された 申込団体 DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedMoshikomiDantaiDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "MOSHIKOMI_DANTAI";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "申込団体";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedMoshikomiDantai bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedMoshikomiDantai find(GeneratedMoshikomiDantai bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedMoshikomiDantai bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedMoshikomiDantai bo);
}
