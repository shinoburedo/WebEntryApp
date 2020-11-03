package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 開催会場 BOクラス
 * @author DB管理ツール
 */
public class Kaijo extends GeneratedKaijo {

    /**
     * インスタンスを生成する。
     */
    public Kaijo() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Kaijo(String connectionUser) {
        super(connectionUser);
    }
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param shikenchiCode 開催地区コード
     * @param kaisaiKaijoCode 開催会場コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Kaijo find(String nendo, String ki, String eventCode, String shikenchiCode, String kaisaiKaijoCode) {
        return find(nendo, ki, eventCode, shikenchiCode, kaisaiKaijoCode, TransactionUtility.NO_LOCK);
    }
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 条件に論理削除フラグOFFを含める
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param shikenchiCode 開催地区コード
     * @param kaisaiKaijoCode 開催会場コード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Kaijo find(String nendo, String ki, String eventCode, String shikenchiCode, String kaisaiKaijoCode, String lockMode) {
        Kaijo bo = new Kaijo();
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
        bo.setShikenchiCode(shikenchiCode);
        bo.setKaijoCode(kaisaiKaijoCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Kaijo) dao.find(bo, lockMode);
        return bo;
    }
    
    /**
     * 準会場を検索する。
     * @param junkaijoCode 準会場コード
     * @param lockMode ロック方法
     */
    public String findJunkaijo(String nendo, String ki, String junkaijoCode, String lockMode) {
        String ret = "";
        ret = dao.findJunkaijo(nendo, ki, junkaijoCode, lockMode);
        return ret;
    }
    
}
