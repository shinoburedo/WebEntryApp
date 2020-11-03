package jp.co.nii.miw.business.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.common.KaijoJoho;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 開催地区 BOクラス
 * @author DB管理ツール
 */
public class Shikenchi extends GeneratedShikenchi {

    /**
     * インスタンスを生成する。
     */
    public Shikenchi() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Shikenchi(String connectionUser) {
        super(connectionUser);
    }
    
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param ichijiNijiKbn １次２次区分
     * @param shikenchiCode 開催地区コード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Shikenchi find(String nendo, String ki, String eventCode, String shikenchiCode) {
        return find(nendo, ki, eventCode, shikenchiCode, TransactionUtility.NO_LOCK);
    }
    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 条件に論理削除フラグOFFを含める
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param shikenchiCode 開催地区コード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    @Override
    public Shikenchi find(String nendo, String ki, String eventCode, String shikenchiCode, String lockMode) {
        Shikenchi bo = new Shikenchi();
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
        bo.setShikenchiCode(shikenchiCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Shikenchi) dao.find(bo, lockMode);
        return bo;
    }
    
    /**
     * リスト検索する。
     * @param nendo 年度
     * @param ki 季
     */
    public List findList(String nendo, String ki, String eventCode, String ji, String lockMode) {
        List ret = new LinkedList();
        
        ret = dao.findList(nendo, ki, eventCode, lockMode);
        
        return ret;
    }
    
    /**
     * １次または２次開催地区一覧を検索する。
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param ichijiNijiKbn １次２次区分
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public ArrayList<Shikenchi> findList(String nendo, String ki, String eventCode, String ichijiNijiKbn) {
        Shikenchi bo = new Shikenchi();
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
//        bo.setIchijiNijiKbn(ichijiNijiKbn);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return dao.findList(bo, TransactionUtility.NO_LOCK);
    }

    /**
     * 年度、回数を元に試験地と会場名のリストを取得
     * @param kaijoJoho
     * @return 
     */
    public List<KaijoJoho> selectShikenKaijo(KaijoJoho kaijoJoho) {
        List<KaijoJoho> retList = new ArrayList<KaijoJoho>();
        retList = dao.selectShikenKaijo(kaijoJoho);
        return retList;
    }
}
