package jp.co.nii.miw.business.domain;

/**
 * 開催会場 DAOインターフェース
 * @author DB管理ツール
 */
public interface KaijoDao extends GeneratedKaijoDao {
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Kaijo find(Kaijo bo, String lockMode);
    
    /**
     * 準会場を検索する。
     * @param junkaijoCode 準会場コード
     * @param lockMode ロック方法
     */
    public String findJunkaijo(String nendo, String ki, String junkaijoCode, String lockMode);
    
}
