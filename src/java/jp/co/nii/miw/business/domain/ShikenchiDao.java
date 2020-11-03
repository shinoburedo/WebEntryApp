package jp.co.nii.miw.business.domain;

import java.util.ArrayList;
import java.util.List;
import jp.co.nii.miw.business.service.common.KaijoJoho;

/**
 * 開催地区 DAOインターフェース
 * @author DB管理ツール
 */
public interface ShikenchiDao extends GeneratedShikenchiDao {

    /**
     * 1件検索を行う。<br>
     * ヒットしない場合はnullを返す。
     * 条件に論理削除フラグOFFを含める
     * @param bo 検索条件を詰めたBusinessObject
     * @param lockMode 行ロックとロック待ちの有無
     * @return 検索結果を詰めたBusinessObject
     */
    public Shikenchi find(Shikenchi bo, String lockMode);

    /**
     * 入力欄表示用開催地区名をArrayList形式で取得
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード(受験科目)
     * @param lockMode ロック方法
     */
    public List findList(String nendo, String kaisu, String eventCode, String lockMode);

    /**
     * 開催地区名を取得
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード(試験種別)
     * @param shikenchiCode 開催地区コード
     * @param lockMode ロック方法
     */
    public String findName(String nendo, String kaisu, String eventCode, String shikenchiCode, String lockMode);

    /**
     * 受験希望地一覧を検索する。
     * @param bo
     * @param lockMode
     * @return 
     */
    public ArrayList<Shikenchi> findList(Shikenchi bo, String lockMode);

    /**
     * 年度、回数を元に試験地と会場名のリストを取得
     * @param kaijoJoho
     * @return 
     */
    public List<KaijoJoho> selectShikenKaijo(KaijoJoho kaijoJoho);
}
