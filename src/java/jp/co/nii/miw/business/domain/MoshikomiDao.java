package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.service.dantai.DntInfSearch;

/**
 * 申込 DAOインターフェース
 * @author DB管理ツール
 */
public interface MoshikomiDao extends GeneratedMoshikomiDao {
    
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi find(Moshikomi bo, String lockMode);
    
    /**
     * 1件検索を行う。再申込の更新用<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi SaishutsuganFind(Moshikomi bo, String lockMode);
    
    /**
     * 更新用にロックして検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi lockFind(Moshikomi bo, String lockMode);
    
    /**
     * 年度・季・ユーザーＩＤで検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi findForUserId(Moshikomi bo, String lockMode);
    
    /**
     * ２重登録チェック用の検索。<br>
     * 検索キーとしてＩＤを指定する。
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findDoubleCheck(String nendo, String ki, String id, String lockMode);
    
    /**
     * 再申込チェック用の検索。<br>
     * @param nendo 年度
     * @param ki 期
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findSaishutsugan(String nendo, String ki, String id, String lockMode);
    
    /**
     * 1件更新を行う。
     * イベントコード自体も更新するため、SELECT時のイベントコードを引数に追加
     * 
     * @param bo 更新内容を詰めたBusinessObject
     */
    void update(Moshikomi bo,String selEventCode);
    
    /**
     * 1件更新を行う。（再申込用）
     * @param bo 更新内容を詰めたBusinessObject
     */
    void updateSaishutsugan(Moshikomi bo);
    
    /**
     * 1件更新を行う。（クレジット決済処理後）
     * @param bo 更新内容を詰めたBusinessObject
     */
    void updateForMskByCrd(Moshikomi bo);
    
    /**
     * 1件更新を行う。（コンビニ・ペイジー決済処理後）
     * @param bo 更新内容を詰めたBusinessObject
     */
    void updateForMskByCvsPayEasy(Moshikomi bo);
    
    /**
     * 入金/キャンセル通知時の検索。<br>
     * @param sid 取引コード
     * @param kingaku 金額
     * @param messageShubetsu メッセージ種別
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findKessai(String sid, String kingaku, String messageShubetsu, String lockMode);
    
    /**
     * 1件検索を行う。<br>
     * ロック取得エラー時は5回までリトライする。<br>
     * ヒットしない場合はnullを返す。
     * 
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi findKessaiRetry(String sid, String kingaku, String messageShubetsu);
    
    /**
     * 1件検索を行う。<br>
     * ロックを取得する。取得エラー時はリトライする。
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomi findRetry(Moshikomi bo);
    
    /**
     * 一覧検索を行う。
     * @param bo
     * @param offset
     * @param limit
     * @return 
     */
    DntInfSearch selectMskListFromDntInfo(Moshikomi bo, int offset, int limit, String sort);

    /**
     * 団体の申込者の受験科目ごとの件数を配列で取得する
     * 
     * @param bo 検索の条件を格納したオブジェクト
     * @return 
     */
    public String[] findTorokushaSu(Moshikomi bo);
    
}
