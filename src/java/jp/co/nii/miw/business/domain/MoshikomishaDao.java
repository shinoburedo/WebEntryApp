package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.service.moshikomi.MskToroku;

/**
 * 申込者 DAOインターフェース
 * @author DB管理ツール
 */
public interface MoshikomishaDao extends GeneratedMoshikomishaDao {
    
    
    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 検索条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    Moshikomisha find(Moshikomisha bo, String lockMode);
    
    /**
     * 更新用にロックして検索する。<br>
     * 検索キーとしてＩＤ・パスワードを指定する。
     * @param userID ＩＤ
     * @param passwd パスワード
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findLogin(String userId, String passwd, String lockMode);
    
    /**
     * 二重登録確認用にロックして検索する。<br>
     * 検索キーとしてＩＤ・申込有フラグを指定する。
     * @param userID ＩＤ
     * @param moshikomiAriFlg 申込有フラグ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findDouble(String userId, String moshikomiAriFlg, String lockMode);
    
    /**
     * 二重登録確認用に検索する。<br>
     * 検索キーとして氏名・生年月日・電話番号・申込有フラグを指定する。
     * @param mskInf
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findDoubleForShimeiEtc(MskToroku mskInf, String lockMode);
    /**
     * 更新ようにロックして検索する。<br>
     * @param userID ＩＤ
     * @param nendo 年度
     * @param ki 季
     * @param lockMode ロック方法
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha lockFind(String userId, String lockMode);
    
    /**
     * 検索する。<br>
     * 検索キー：ＩＤ・カナ氏名・生年月日・申込有フラグ・論理削除フラグ
     * @param moshikomisha 
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findForPswRem(Moshikomisha moshikomisha, String lockMode);
    
    /**
     * ロックして検索する。<br>
     * ロック取得エラー時はリトライする。
     * @param userID ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomisha findRetry(Moshikomisha moshikomisha);
    
    /**
     * 申込出願・決済後の更新<br>
     */
    public void updateAfterKessai(Moshikomisha moshikomisha);
    
    
}
