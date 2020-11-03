package jp.co.nii.miw.business.service.mypage;

import java.lang.reflect.InvocationTargetException;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.business.service.AbstractService;
import org.apache.commons.beanutils.BeanUtils;

/**
 * <p>タイトル: 申込サービス</p>
 * <p>説明: 申込サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public class MypMskInfServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    /**
     * DB接続時のユーザーを決定する
     */
    public MypMskInfServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 一件の申込情報を取得する。
     * @param moshikomiUketsukeNo
     * @param nendo
     * @param ki
     * @param eventCode
     * @return
     * @throws Exception 
     */
    public MypMskInf getMskInf(String moshikomiUketsukeNo, String nendo, String ki, String eventCode) throws Exception {
        MypMskInf mypMskInf = new MypMskInf(ki);
        //boオブジェクト作成
        Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);
        Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

        //申込を一件検索
        boMoshikomi = boMoshikomi.find(moshikomiUketsukeNo, nendo, ki, eventCode);
        //申込者を一件検索
        boMoshikomisha = boMoshikomisha.find(boMoshikomi.getUserId());

        mypMskInf = setMoshikomiInfo(boMoshikomi, boMoshikomisha);
        
        if (MiwConstants.KOJIN_DANTAI_KBN_DANTAI.equals(boMoshikomi.getKojinDantaiKbn())){
//            団体申込
            Dantai boDantai = new Dantai(DATA_SOURCE_NAME);
            String dantaiName = boDantai.find(boMoshikomi.getDantaiCode()).getDantaiName();
            mypMskInf.setDantaiName(dantaiName);
        }

        return mypMskInf;
    }

    /**
     * 申込情報をセットする
     * @param boMoshikomi
     * @param boMoshikomisha
     * @return 
     */
    private MypMskInf setMoshikomiInfo(Moshikomi boMoshikomi, Moshikomisha boMoshikomisha) throws InvocationTargetException, IllegalAccessException {

        MypMskInf mypMskInf = new MypMskInf(boMoshikomi.getKaisu());

        MenuControl boMenuControl = new MenuControl(DATA_SOURCE_NAME);

        BeanUtils.copyProperties(mypMskInf, boMoshikomi);
        BeanUtils.copyProperties(mypMskInf, boMoshikomisha);

        /* 生年月日 */
        if (!CheckUtility.isBlank(boMoshikomisha.getBirthday())) {
            mypMskInf.setBirthdayEra(boMoshikomisha.getBirthday().substring(0, 1));
            mypMskInf.setBirthdayYear(boMoshikomisha.getBirthday().substring(1, 3));
            mypMskInf.setBirthdayMonth(boMoshikomisha.getBirthday().substring(3, 5));
            mypMskInf.setBirthdayDay(boMoshikomisha.getBirthday().substring(5));
        }

        /* 郵便番号 */
        if (!CheckUtility.isBlank(boMoshikomisha.getYubinNo())) {
            mypMskInf.setYubinNo1(boMoshikomisha.getYubinNo().substring(0, 3));
            mypMskInf.setYubinNo2(boMoshikomisha.getYubinNo().substring(3, 7));
        }

        /* 住所 */
        if (!CheckUtility.isBlank(boMoshikomisha.getJusho())) {
            String[] jushos = boMoshikomisha.getJusho().split(MiwConstants.JUSHO_SPLIT_STRING, -1);
            try {
                mypMskInf.setTodofuken(jushos[0]);
                mypMskInf.setJusho1(jushos[1]);
                mypMskInf.setJusho2(jushos[2]);
                mypMskInf.setJusho3(jushos[3]);
                mypMskInf.setJusho4(jushos[4]);
            } catch (Exception e) {
                mypMskInf.setJusho1(boMoshikomisha.getJusho());
            }
        }

        /* 確認用メールアドレス */
        mypMskInf.setMailAddressKakunin(boMoshikomisha.getMailAddress());

        /* 画像補正期限 */
        mypMskInf.setHoseiIraiKigenBi(boMenuControl.find(boMoshikomi.getNendo(), boMoshikomi.getKaisu(), MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_GAZO_HOSEI_KIGEN).getKaishiDate());

        /* 電話番号 */
        mypMskInf.setTelNo(MiwStringUtility.splitConcateWithParenthesis(boMoshikomisha.getTelNo())[0]);
        /* 内線番号 */
        mypMskInf.setExtNo(MiwStringUtility.splitConcateWithParenthesis(boMoshikomisha.getTelNo())[1]);

        return mypMskInf;
    }
}
