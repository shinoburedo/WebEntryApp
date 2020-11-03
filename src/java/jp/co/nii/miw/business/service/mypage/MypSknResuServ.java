package jp.co.nii.miw.business.service.mypage;

import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.domain.Event;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.sew.business.service.AbstractService;

/**
 * <p>タイトル: 申込サービス</p>
 * <p>説明: 申込サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public class MypSknResuServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    /**
     * DB接続時のユーザーを決定する
     */
    public MypSknResuServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }


    /**
     * １次試験受験地、または２次試験受験地の情報を取得する
     * @param nendo 年度
     * @param ki 季
     * @param ichiNijiKbn １次２次区分
     * @param shikenchiCode 開催地区コード
     * @return
     * @throws Exception 
     */
    public Shikenchi getShikenchiJoho(String nendo, String ki, String ichiNijiKbn, String shikenchiCode) throws Exception {
        //boオブジェクト作成
        Shikenchi bo = new Shikenchi(DATA_SOURCE_NAME);

        //開催地区情報を取得
        return bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, ichiNijiKbn, shikenchiCode);

    }

    /**
     * メニュー制御情報を取得する
     * @param nendo
     * @param ki
     * @param eventCode
     * @param menuCode
     * @return 
     */
    public MenuControl getMenuControlJoho(String nendo, String ki, String eventCode, String menuCode) {

        MenuControl bo = new MenuControl(DATA_SOURCE_NAME);

        return  bo.find(nendo, ki, eventCode, menuCode);
    }

    /**
     * イベント情報を取得する
     * @param eventCode
     * @return 
     */
    public Event getEventJoho(String eventCode) {

        Event bo = new Event(DATA_SOURCE_NAME);

        return  bo.find(eventCode);
    }

}
