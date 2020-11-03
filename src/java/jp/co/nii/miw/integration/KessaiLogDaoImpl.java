package jp.co.nii.miw.integration;


import jp.co.nii.miw.business.domain.KessaiLogDao;

/**
 * 決済ログ DAO実装クラス
 * @author DB管理ツール
 */
public class KessaiLogDaoImpl extends GeneratedKessaiLogDaoImpl implements KessaiLogDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public KessaiLogDaoImpl(String datasource) {
        super(datasource);
    }
}
