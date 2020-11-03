package jp.co.nii.miw.business.service.mypage;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.service.AbstractService;

/**
 * <p>タイトル: マイページサービス</p>
 * <p>説明: マイページサービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2012</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author nii
 */
public class MypServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public MypServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 本人確認項目をキーに申込者データを取得する
     * @param moshikomisha
     * @return 取得した申込者データ。存在しない場合はnull。
     * @throws Exception
     */
    public Moshikomisha getMoshikomishaForPswRem(Moshikomisha moshikomisha) {

        //入力項目以外の条件セット
        //申込が完了
        moshikomisha.setMoshikomiAriFlg(MiwConstants.FLG_ON);
        //論理削除されてない
        moshikomisha.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        //生年月日編集
        moshikomisha.setBirthday(moshikomisha.getBirthdayEra()
                + StringUtility.padLeft(moshikomisha.getBirthdayYy(), "0", 2)
                + StringUtility.padLeft(moshikomisha.getBirthdayMm(), "0", 2)
                + StringUtility.padLeft(moshikomisha.getBirthdayDd(), "0", 2));
        Moshikomisha bo = moshikomisha.findForPswRem();

        return bo;

    }
}
