package jp.co.nii.miw.business.service.common;

import jp.co.nii.sew.business.service.AbstractService;
import java.util.ArrayList;
import java.util.List;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 会場一覧作成用サービスクラス
 * @author h-katayama
 */
public class KaijoServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;
    
    //DB接続時のユーザーを決定
    public KaijoServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    public List<KaijoJoho> setKaijoList(KaijoJoho kaijoJoho) {
        List<KaijoJoho> list = new ArrayList<KaijoJoho>();

        try {
            Shikenchi boShikenchi = new Shikenchi(DATA_SOURCE_NAME);
            list = boShikenchi.selectShikenKaijo(kaijoJoho);
        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".createList()", e);
            throw new SEWException("リスト作成処理で例外が発生した。", e);
        } finally {
        }
        return list;
    }


}
