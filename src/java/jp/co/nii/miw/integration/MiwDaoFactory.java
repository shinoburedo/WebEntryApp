package jp.co.nii.miw.integration;

import jp.co.nii.sew.integration.YubinBangoJishoDaoImpl;
import jp.co.nii.miw.business.domain.DantaiDao;
import jp.co.nii.miw.business.domain.EventDao;
import jp.co.nii.miw.business.domain.ShikenchiDao;
import jp.co.nii.miw.business.domain.KaijoDao;
import jp.co.nii.miw.business.domain.KanrishaDao;
import jp.co.nii.miw.business.domain.KessaiLogDao;
import jp.co.nii.miw.business.domain.MenuControlDao;
import jp.co.nii.miw.business.domain.MenuDao;
import jp.co.nii.miw.business.domain.MoshikomiDantaiDao;
import jp.co.nii.miw.business.domain.MoshikomiDao;
import jp.co.nii.miw.business.domain.MoshikomishaDao;
import jp.co.nii.miw.business.domain.SaibanDao;


/**
 * DAOファクトリの実装クラス
 * @author 
 */
public class MiwDaoFactory {

    public MiwDaoFactory() {
    }
    

    /**
     * 団体Daoインスタンスを取得する
     * @param データソース名
     * @return 団体Daoインスタンス
     */
    public DantaiDao getDantaiDao(String datasource) {
        return new DantaiDaoImpl(datasource);
    }
    
    /**
     * 会場Daoインスタンスを取得する
     * @param データソース名
     * @return 会場Daoインスタンス
     */
    public KaijoDao getKaijoDao(String datasource) {
        return new KaijoDaoImpl(datasource);
    }
    
    /**
     * 試験地Daoインスタンスを取得する
     * @param データソース名
     * @return 試験地Daoインスタンス
     */
    public ShikenchiDao getShikenchiDao(String datasource) {
        return new ShikenchiDaoImpl(datasource);
    }
    
    /**
     * イベントDaoインスタンスを取得する
     * @param データソース名
     * @return イベントDaoインスタンス
     */
    public EventDao getEventDao(String datasource) {
        return new EventDaoImpl(datasource);
    }
    
    
    /**
     * 管理者Daoインスタンスを取得する
     * @param データソース名
     * @return 管理者Daoインスタンス
     */
    public KanrishaDao getKanrishaDao(String datasource) {
        return new KanrishaDaoImpl(datasource);
    }
    
    /**
     * 決済ログDaoインスタンスを取得する
     * @param データソース名
     * @return 決済ログDaoインスタンス
     */
    public KessaiLogDao getKessaiLogDao(String datasource) {
        return new KessaiLogDaoImpl(datasource);
    }
    
     /**
     * メニューDaoインスタンスを取得する
     * @param データソース名
     * @return メニューDaoインスタンス
     */
    public MenuDao getMenuDao(String datasource) {
        return new MenuDaoImpl(datasource);
    }
    
     /**
     * メニュー制御Daoインスタンスを取得する
     * @param データソース名
     * @return メニュー制御Daoインスタンス
     */
    public MenuControlDao getMenuControlDao(String datasource) {
        return new MenuControlDaoImpl(datasource);
    }
    
     /**
     * 申込Daoインスタンスを取得する
     * @param データソース名
     * @return 申込Daoインスタンス
     */
    public MoshikomiDao getMoshikomiDao(String datasource) {
        return new MoshikomiDaoImpl(datasource);
    }
    
    /**
     * 申込団体Daoインスタンスを取得する
     * @param データソース名
     * @return 申込団体Daoインスタンス
     */
    public MoshikomiDantaiDao getMoshikomiDantaiDao(String datasource) {
        return new MoshikomiDantaiDaoImpl(datasource);
    }
    
    /**
     * 申込者Daoインスタンスを取得する
     * @param データソース名
     * @return 申込者Daoインスタンス
     */
    public MoshikomishaDao getMoshikomishaDao(String datasource) {
        return new MoshikomishaDaoImpl(datasource);
    }
    
    /**
     * 採番Daoインスタンスを取得する
     * @param データソース名
     * @return 採番Daoインスタンス
     */
    public SaibanDao getSaibanDao(String datasource) {
        return new SaibanDaoImpl(datasource);
    }
    
}
