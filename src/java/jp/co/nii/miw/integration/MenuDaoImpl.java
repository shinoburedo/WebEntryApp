package jp.co.nii.miw.integration;


import jp.co.nii.miw.business.domain.MenuDao;

/**
 * メニュー DAO実装クラス
 * @author DB管理ツール
 */
public class MenuDaoImpl extends GeneratedMenuDaoImpl implements MenuDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public MenuDaoImpl(String datasource) {
        super(datasource);
    }
}
