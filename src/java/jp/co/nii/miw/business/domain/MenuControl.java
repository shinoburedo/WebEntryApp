package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * メニュー制御 BOクラス
 * @author DB管理ツール
 */
public class MenuControl extends GeneratedMenuControl {

    /**
     * インスタンスを生成する。
     */
    public MenuControl() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public MenuControl(String connectionUser) {
        super(connectionUser);
    }
    
    
      /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param menuCode メニューコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public MenuControl find(String nendo, String ki, String eventCode, String menuCode) {
        return find(nendo, ki, eventCode, menuCode, TransactionUtility.NO_LOCK);
    }
    
    
     /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 検索条件に論理削除フラグOFFを含める
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param menuCode メニューコード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected MenuControl find(String nendo, String ki, String eventCode, String menuCode, String lockMode) {
        MenuControl bo = new MenuControl();
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
        bo.setMenuCode(menuCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (MenuControl) dao.find(bo, lockMode);
        return bo;
    }

    
   /**
     * メニューコードが一致し、
     * 現在日時が、開始日時と終了日時に含まれている情報を取得する。
     * 抽出結果が一件ではなかった場合NULLを返する。
     * @param menuCode メニューコード 
     */
    public MenuControl findMenuFroKikan(String menuCode){
        MenuControl bo = new MenuControl();
        bo.setMenuCode(menuCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return dao.findMenuForKikan(bo, TransactionUtility.NO_LOCK);
    }
    
   /**
     * 年度のベースレコードを取得する
     * 抽出結果がなかった場合NULLを返す。
     */
    public MenuControl selectNendo(){
        MenuControl bo = new MenuControl();
        return dao.selectNendo(bo, TransactionUtility.NO_LOCK);
    }
    
    
   /**
     * 年度・メニューコードが一致するレコードを検索
     * 抽出結果がなかった場合NULLを返する。
     * @param nendo 年度 
     * @param kaisuu 回数
     * @param menuCode メニューコード 
     */
    public MenuControl findMenuFroKikanNendo(String nendo, String kaisuu, String menuCode){
        MenuControl bo = new MenuControl();
        bo.setNendo(nendo);
        bo.setKaisu(kaisuu);
        bo.setMenuCode(menuCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return dao.findMenuForKikanNendo(bo, TransactionUtility.NO_LOCK);
    }
    
    
}
