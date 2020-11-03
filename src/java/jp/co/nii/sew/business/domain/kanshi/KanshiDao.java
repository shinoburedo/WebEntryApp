package jp.co.nii.sew.business.domain.kanshi;

/**
 * 監視用 DAOインターフェース
 * @author DB管理ツール
 */
public interface KanshiDao {
    
    /**
     * DBに対してコネクションを取得する
     * 
     * @return 成功したらtrue、それ以外はfalse
     */
    boolean checkDb();
    
}
