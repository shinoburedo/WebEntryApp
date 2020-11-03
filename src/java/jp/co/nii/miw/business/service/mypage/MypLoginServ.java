package jp.co.nii.miw.business.service.mypage;

import java.util.ArrayList;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.sew.business.service.AbstractService;

/**
 * <p>タイトル: 申込サービス</p>
 * <p>説明: 申込サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public class MypLoginServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    /**
     * DB接続時のユーザーを決定する
     */
    public MypLoginServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 入力されたＩＤ、パスワードでDBを検索し、ログイン可能かどうかチェックする
     * ログイン不可だった場合nullを返す
     * @param userId
     * @param password
     * @param strShikenShubetsuCode
     * @return 
     */
    public Moshikomisha authenticate(String nendo, String ki, String userId, String password) {

        Moshikomisha bo = new Moshikomisha(DATA_SOURCE_NAME);
        Moshikomisha moshikomisha = bo.find(userId);

        if (moshikomisha != null) {
            if (!moshikomisha.getPasswd().equals(password)) {
                //ＩＤは存在するがパスワードが一致しない
                moshikomisha = null;
            } else {
                //申込有りフラグがOFF
                if (moshikomisha.getMoshikomiAriFlg().equals(MiwConstants.FLG_OFF)) {
                    moshikomisha = null;
                }
            }
        }
        return moshikomisha;
    }

    /**
     * ユーザーＩＤより申込テーブルを一件取得する
     * @param nendo
     * @param ki
     * @param userId
     * @param password
     * @return 
     */
    public Moshikomi getMoshikomiForUserId(String nendo, String ki, String userId, String password) {

        Moshikomi bo = new Moshikomi(DATA_SOURCE_NAME);

        //ＩＤ、パスワードが一致する場合、申込情報を取得
        bo = bo.findForUserId(nendo, ki, userId);

        return bo;
    }

    /**
     * 試験の年度、季を取得する
     * @param kanrishaJoho
     * @return 
     */
    public MenuControl getNendoKaisu() {

        MenuControl bo = new MenuControl(DATA_SOURCE_NAME);
        bo = bo.findMenuFroKikan(MiwConstants.MENU_CODE_MYP_LOGIN_KOJIN);
        return bo;
    }

    /**
     * １次または２次の受験地一覧を取得する。
     * @param nendo
     * @param ki
     * @param ichijiNijiKbn
     * @return 
     */
    public ArrayList<Shikenchi> getShikenchiList(String nendo, String ki, String ichijiNijiKbn) {
        Shikenchi bo = new Shikenchi(DATA_SOURCE_NAME);
        return bo.findList(nendo, ki, MiwConstants.EVENT_CODE_ALL, ichijiNijiKbn);
    }

    /**
     * マイページの各利用期間を取得し、マイページ情報Beanオブジェクトにセットする
     * @param nendo
     * @param ki
     * @param mypageJoho 
     * @param moshikomi
     */
    public void getMypageKikan(String nendo, String ki, MypageJoho mypageJoho, Moshikomi moshikomi) {
        MenuControl bo = new MenuControl(DATA_SOURCE_NAME);

        MenuControl retLogin = new MenuControl();
        MenuControl retConf = new MenuControl();
        MenuControl retUpd = new MenuControl();

        if (MiwConstants.KOJIN_DANTAI_KBN_KOJIN.equals(moshikomi.getKojinDantaiKbn())) {
            //ログイン期間を取得
            retLogin = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_LOGIN_KOJIN);
            //情報確認期間を取得
            retConf = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_CONF_KOJIN);
            //情報変更期間を取得
            retUpd = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_UPD_KOJIN);
        } else if (MiwConstants.KOJIN_DANTAI_KBN_DANTAI.equals(moshikomi.getKojinDantaiKbn())) {
            //ログイン期間を取得
            retLogin = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_LOGIN_DNT);
            //情報確認期間を取得
            retConf = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_CONF_DNT);
            //情報変更期間を取得
            retUpd = bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_UPD_DNT);
        }

        //ログイン開始・終了日時をセット
        mypageJoho.setKikanLoginKaishiDate(retLogin.getKaishiDate());
        mypageJoho.setKikanLoginKaishiTime(retLogin.getKaishiTime());
        mypageJoho.setKikanLoginShuryoDate(retLogin.getShuryoDate());
        mypageJoho.setKikanLoginShuryoTime(retLogin.getShuryoTime());

        //申込情報確認開始・終了日時をセット
        mypageJoho.setKikanConfKaishiDate(retConf.getKaishiDate());
        mypageJoho.setKikanConfKaishiTime(retConf.getKaishiTime());
        mypageJoho.setKikanConfShuryoDate(retConf.getShuryoDate());
        mypageJoho.setKikanConfShuryoTime(retConf.getShuryoTime());

        //申込情報変更開始・終了日時をセット
        mypageJoho.setKikanUpdKaishiDate(retUpd.getKaishiDate());
        mypageJoho.setKikanUpdKaishiTime(retUpd.getKaishiTime());
        mypageJoho.setKikanUpdShuryoDate(retUpd.getShuryoDate());
        mypageJoho.setKikanUpdShuryoTime(retUpd.getShuryoTime());

    }
}
