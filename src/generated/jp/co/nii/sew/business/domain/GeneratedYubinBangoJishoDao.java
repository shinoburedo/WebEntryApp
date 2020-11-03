package jp.co.nii.sew.business.domain;

import jp.co.nii.sew.business.domain.GeneratedYubinBangoJisho;

/**
 * 生成された 郵便番号辞書 DAOインターフェース<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
public interface GeneratedYubinBangoJishoDao {

    /** テーブルの物理名 */
    public static final String TABLE_NAME = "YUBIN_BANGO_JISHO";
    /** テーブルの論理名 */
    public static final String TABLE_LOGICAL_NAME = "郵便番号辞書";

    /**
     * 1件登録を行う。
     * 
     * @param bo 登録内容を詰めたBusinessObject
     */
    void create(GeneratedYubinBangoJisho bo);

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedYubinBangoJisho find(GeneratedYubinBangoJisho bo, String lockMode);

    /**
     * 1件更新を行う。
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(GeneratedYubinBangoJisho bo);

    /**
     * 1件削除を行う。
     * 
     * @param bo 削除条件を詰めたBusinessObject
     */
    void remove(GeneratedYubinBangoJisho bo);
}
