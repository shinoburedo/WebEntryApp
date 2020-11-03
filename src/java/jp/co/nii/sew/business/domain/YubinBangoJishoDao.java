package jp.co.nii.sew.business.domain;

import jp.co.nii.sew.business.domain.GeneratedYubinBangoJishoDao;
import java.util.List;

/**
 * 郵便番号辞書 DAOインターフェース
 * @author DB管理ツール
 */
public interface YubinBangoJishoDao extends GeneratedYubinBangoJishoDao {
    
    /**
     * 郵便番号で検索する。
     * @param yubinBango 郵便番号７桁
     * @param lockMode ロック方法
     */
    public List findYubinBango(String yubinBango, String lockMode);
    
}
