package jp.co.nii.sew.integration.kanshi;

import java.sql.Connection;
import jp.co.nii.sew.business.domain.kanshi.KanshiDao;
import jp.co.nii.sew.integration.AbstractDao;

/**
 * 監視 DAO実装クラス<br>
 * @author nii
 */
public class KanshiDaoImpl extends AbstractDao implements KanshiDao {

    /**
     * インスタンスを生成する。<br>
     * データソースを決定
     * @param datasource データソース名
     */
    public KanshiDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.mia.business.domain.KanshiDao#create(jp.co.nii.mia.business.domain.KanshiDao)
     */
    @Override
    public boolean checkDb() {
        Connection con = null;
        try {
            con = getConnection();
            return true;
        } catch(Exception ex){
            return false;
        } finally {
            close(con,null);
        }
    }


}
