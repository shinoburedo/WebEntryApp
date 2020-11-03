package jp.co.nii.miw.business.domain;

/**
 * 採番 DAOインターフェース
 * @author DB管理ツール
 */
public interface SaibanDao extends GeneratedSaibanDao {
    
    /**
     * 1件検索を行う。<br>
     * ロック取得エラー時は5回までリトライする。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Saiban findRetry(Saiban bo);
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    GeneratedSaiban find(GeneratedSaiban bo, String lockMode);
}
