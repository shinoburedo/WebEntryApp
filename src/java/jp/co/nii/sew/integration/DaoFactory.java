package jp.co.nii.sew.integration;

import jp.co.nii.sew.integration.kanshi.KanshiDaoImpl;
import jp.co.nii.sew.business.domain.kanshi.KanshiDao;
import jp.co.nii.sew.business.domain.YubinBangoJishoDao;


/**
 * DAOファクトリの実装クラス
 * @author 
 */
public class DaoFactory {

    public DaoFactory() {
    }

    /**
     * 郵便番号変換Daoインスタンスを取得する
     * @return 郵便番号変換Daoインスタンス
     */
    public YubinBangoJishoDao getYubinBangoJishoDao(String datasource) {
        return new YubinBangoJishoDaoImpl(datasource);
    }

    /**
     * 監視Daoインスタンスを取得する
     * @return 監視Daoインスタンス
     */
    public KanshiDao getKanshiDao(String datasource) {
        return new KanshiDaoImpl(datasource);
    }

}
