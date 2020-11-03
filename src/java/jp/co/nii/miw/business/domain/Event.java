package jp.co.nii.miw.business.domain;

import java.util.List;
import java.util.LinkedList;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * イベント BOクラス
 * @author DB管理ツール
 */
public class Event extends GeneratedEvent {

    /**
     * インスタンスを生成する。
     */
    public Event() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Event(String connectionUser) {
        super(connectionUser);
    }
    
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Event find(String eventCode) {
        return find(eventCode, TransactionUtility.NO_LOCK);
    }
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 条件に論理削除フラグOFFを追加する
     * @param eventCode イベントコード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Event find(String eventCode, String lockMode) {
        Event bo = new Event();
        bo.setEventCode(eventCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Event) dao.find(bo, lockMode);
        return bo;
    }
    
    
    /**
     * 検定料を取得する。<br>
     * @param eventCode イベントコード
     * @return 検定料
     */
    public int getKenteiryo(String eventCode) {
        int ret = 0;
        ret = dao.getKenteiryo(eventCode, TransactionUtility.NO_LOCK);
        return ret;
    }
    
}
