package jp.co.nii.miw.business.domain;

import java.util.ArrayList;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInfSearch;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * 申込 BOクラス
 * @author DB管理ツール
 */
public class Moshikomi extends GeneratedMoshikomi {

    /**
     * インスタンスを生成する。
     */
    public Moshikomi() {
        super();
    }

    /**
     * インスタンスを生成する。
     * @param connectionUser データソース名
     */
    public Moshikomi(String connectionUser) {
        super(connectionUser);
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi find(String moshikomiUketsukeNo, String nendo, String ki, String eventCode) {
        return find(moshikomiUketsukeNo, nendo, ki, eventCode, TransactionUtility.NO_LOCK);
    }

    /**
     * 再申込更新用に検索する。<br>
     * 検索キーとして主キーを指定する。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param ki 期
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi SaishutsuganFind(String moshikomiUketsukeNo, String nendo, String ki) {
        Moshikomi bo = new Moshikomi();
        bo.setMoshikomiUketsukeNo(moshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo = (Moshikomi) dao.findRetry(bo);
        return bo;
    }

    /**
     * 検索する。<br>
     * 検索キーとして主キーを指定する。
     * 検索条件に論理削除フラグOFFを含める
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    protected Moshikomi find(String moshikomiUketsukeNo, String nendo, String ki, String eventCode, String lockMode) {
        Moshikomi bo = new Moshikomi();
        bo.setMoshikomiUketsukeNo(moshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = (Moshikomi) dao.find(bo, lockMode);
        return bo;
    }

    /**
     * 更新用にロックして検索する。
     * 検索条件に論理削除フラグOFFを含める
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param ki 期
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi lockFind(String moshikomiUketsukeNo, String nendo, String ki) {
        Moshikomi bo = new Moshikomi();
        bo.setMoshikomiUketsukeNo(moshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo = (Moshikomi) dao.lockFind(bo, TransactionUtility.LOCK_NOWAIT);
        return bo;
    }

    /**
     * 年度・季・ユーザーＩＤで検索を行う。
     * 検索条件に論理削除フラグOFFを含める
     * @param nendo 年度
     * @param ki 期
     * @param lockMode ロックの有無
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findForUserId(String nendo, String ki, String userId) {
        Moshikomi bo = new Moshikomi();
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setUserId(userId);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        bo = dao.findForUserId(bo, TransactionUtility.NO_LOCK);
        return bo;
    }

    /**
     * ２重登録チェック用の検索。<br>
     * 検索キーとしてＩＤを指定する。
     * @param nendo 年度
     * @param ki 期
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findDoubleCheck(String nendo, String ki, String id, String lockMode) {
        Moshikomi bo = new Moshikomi();
        bo = (Moshikomi) dao.findDoubleCheck(nendo, ki, id, lockMode);
        return bo;
    }

    /**
     * 再申込チェック用の検索。<br>
     * @param nendo 年度
     * @param ki 期
     * @param id ＩＤ
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findSaishutsugan(String nendo, String ki, String id, String lockMode) {
        Moshikomi bo = new Moshikomi();
        bo = (Moshikomi) dao.findSaishutsugan(nendo, ki, id, lockMode);
        return bo;
    }

    /**
     * 更新する。
     * イベントコード自体も更新するため、SELECT時のイベントコードを引数に追加
     */
    public void update(String selEventCode) {
        dao.update(this, selEventCode);
    }

    /**
     * 更新する。（再申込用）
     */
    public void updateSaishutsugan() {
        dao.updateSaishutsugan(this);
    }

    /**
     * 更新する。（決済後用）
     */
    public void updateSaishutsuganAfterKessai() {
        if (MiwConstants.KESSAI_HOHO_KBN_CRD.equals(getKessaiHohoKbn())){
            dao.updateForMskByCrd(this);
        } else if (MiwConstants.KESSAI_HOHO_KBN_CVS.equals(getKessaiHohoKbn()) ||
                MiwConstants.KESSAI_HOHO_KBN_PAYEASY.equals(getKessaiHohoKbn())){
            dao.updateForMskByCvsPayEasy(this);
        }
    }

    /**
     * 入金通知時の検索。<br>
     * ロックを取得する。取得エラー時はリトライする。
     * @param sid 取引コード
     * @param kingaku 金額
     * @param messageShubetsu メッセージ種別
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findKessaiRetry(String sid, String kingaku, String messageShubetsu) {
        Moshikomi bo = new Moshikomi();
        bo = (Moshikomi) dao.findKessaiRetry(sid, kingaku, messageShubetsu);
        return bo;
    }

    /**
     * 入金通知時の検索。<br>
     * @param sid 取引コード
     * @param kingaku 金額
     * @param messageShubetsu メッセージ種別
     * @param lockMode ロックモード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findKessai(String sid, String kingaku, String messageShubetsu, String lockMode) {
        Moshikomi bo = new Moshikomi();
        bo = (Moshikomi) dao.findKessai(sid, kingaku, messageShubetsu, lockMode);
        return bo;
    }

    /**
     * 後で更新するために行ロックして検索する。<br>
     * 検索キーとして主キーを指定する。<br>
     * ロックされているときロック解除を待つ。
     * @param moshikomiUketsukeNo 申込受付番号
     * @param nendo 年度
     * @param ki 期
     * @param eventCode イベントコード
     * @return 検索したデータ（このクラスのインスタンス）<br>
     *         該当するデータが存在しない場合はnullを返す。
     */
    public Moshikomi findRetry(String moshikomiUketsukeNo, String nendo, String ki, String eventCode) {
        Moshikomi bo = new Moshikomi();
        bo.setMoshikomiUketsukeNo(moshikomiUketsukeNo);
        bo.setNendo(nendo);
        bo.setKaisu(ki);
        bo.setEventCode(eventCode);
        bo.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        return dao.findRetry(bo);
    }

    /**
     * 団体情報より申込者一覧を取得する
     * @param nendo   年度
     * @param kaisu   回数
     * @param dntCode 団体コード
     * @param offset  開始位置
     * @param limit   取得件数
     * @param sort    ソートKey
     * @return 
     */
    public DntInfSearch selectMskListFromDntInfo(String nendo, String kaisu, String dntCode, int offset, int limit, String sort) {
        Moshikomi bo = new Moshikomi();
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        bo.setDantaiCode(dntCode);
        return dao.selectMskListFromDntInfo(bo, offset, limit, sort);
    }
    
    /**
     * 団体の申込者の受験科目ごとの件数を配列で取得する
     * 
     * @param nendo 年度
     * @param kaisu 回数
     * @param dantaiMoshikomiUketsukeNo 団体申込受付番号
     * @return 
     */
    public String[] findTorokushaSu(String nendo,String kaisu, String dantaiMoshikomiUketsukeNo){
        Moshikomi bo = new Moshikomi();
        bo.setNendo(nendo);
        bo.setKaisu(kaisu);
        //団体申込受付番号
        bo.setDantaiMoshikomiUketsukeNo(dantaiMoshikomiUketsukeNo);
        return dao.findTorokushaSu(bo);
    }
}
