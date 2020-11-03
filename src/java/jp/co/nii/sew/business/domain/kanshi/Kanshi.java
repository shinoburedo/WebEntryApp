package jp.co.nii.sew.business.domain.kanshi;

import jp.co.nii.sew.integration.DaoFactory;

/**
 * 郵便番号辞書 BOクラス
 * @author DB管理ツール
 */
public class Kanshi {

    /** DAO */
    protected KanshiDao dao;
    
    /**
     * インスタンスを生成する。
     */
    public Kanshi() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Kanshi(String connectionUser) {
        this();
        dao = new DaoFactory().getKanshiDao(connectionUser);
    }
    
    /**
     * 郵便番号で検索する。
     */
    public boolean checkDb() {
        return dao.checkDb();
    }

}
