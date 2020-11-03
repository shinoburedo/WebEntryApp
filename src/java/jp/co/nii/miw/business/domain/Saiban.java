package jp.co.nii.miw.business.domain;

import jp.co.nii.miw.business.MiwConstants;

/**
 * 採番 BOクラス
 * @author DB管理ツール
 */
public class Saiban extends GeneratedSaiban {

    /**
     * インスタンスを生成する。
     */
    public Saiban() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Saiban(String connectionUser) {
        super(connectionUser);
    }
    
    /**
     * ロックを取得して検索する。<br>
     * ロック取得エラー時は5回までリトラする。
     * 検索キーとして主キーを指定する。
     * @param noId 番号ＩＤ
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Saiban findRetry(String noId) {
        Saiban bo = new Saiban();
        bo.setNoId(noId);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Saiban) dao.findRetry(bo);
        return bo;
    }
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param noId 番号ＩＤ
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Saiban find(String noId, String lockMode) {
        Saiban bo = new Saiban();
        bo.setNoId(noId);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Saiban) dao.find(bo, lockMode);
        return bo;
    }
}
