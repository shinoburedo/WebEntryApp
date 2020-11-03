package jp.co.nii.miw.business.domain;

import java.util.List;

/**
 * イベント DAOインターフェース
 * @author DB管理ツール
 */
public interface EventDao extends GeneratedEventDao {
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Event find(Event bo, String lockMode);
    
    /**
     * 検定料を取得する。<br>
     * ヒットしない場合は0を返す。
     * @param eventCode イベントコード
     * @param lockMode 行ロックとロック待ちの有無
     * @return int型の検定料
     */
    int getKenteiryo(String eventCode, String lockMode);
    
}
