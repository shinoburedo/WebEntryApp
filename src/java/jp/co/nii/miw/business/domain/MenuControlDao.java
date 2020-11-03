package jp.co.nii.miw.business.domain;

/**
 * メニュー制御 DAOインターフェース
 * @author DB管理ツール
 */
public interface MenuControlDao extends GeneratedMenuControlDao {
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    MenuControl find(MenuControl bo, String lockMode);
    
    
    
   /**
     * メニューコードが一致し、
     * 現在日時が、開始日時と終了日時に含まれている情報を取得する。
     * 抽出結果が一件ではなかった場合NULLを返する。
     * @param bo 
     * @param lockMode ロック方法
     */
    public MenuControl findMenuForKikan(MenuControl bo, String lockMode);

   /**
     * 年度のベースレコードを取得する
     * 抽出結果がなかった場合NULLを返す。
     * @param lockMode ロック方法
     */
    public MenuControl selectNendo(MenuControl bo, String lockMode);

   /**
     * 年度とメニューコードが一致するレコードを検索する、
     * 抽出結果がなかった場合NULLを返する。
     * @param bo 
     * @param lockMode ロック方法
     */
    public MenuControl findMenuForKikanNendo(MenuControl bo, String lockMode);
}
