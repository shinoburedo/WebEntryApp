package jp.co.nii.miw.integration;


import jp.co.nii.miw.business.domain.KanrishaDao;

/**
 * 管理者 DAO実装クラス
 * @author DB管理ツール
 */
public class KanrishaDaoImpl extends GeneratedKanrishaDaoImpl implements KanrishaDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public KanrishaDaoImpl(String datasource) {
        super(datasource);
    }
}
