package jp.co.nii.miw.business.service;

import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.service.AbstractService;

/**
 * <p>タイトル: マイページサービス</p>
 * <p>説明: マイページサービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2012</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author nii
 */
public class MenuServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public MenuServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 日付の前ゼロを半角スペースに変換する
     * @param date 日付項目（8桁 yyyymmdd）
     * @throws Exception
     */
    public String[] convertDate(String ymd) {
        String ret[] = new String[3];
        
        String year = ymd.substring(0, 4);
        String month = ymd.substring(4, 6);
        String date = ymd.substring(6);
        
        if (month.substring(0, 1).equals("0")) {
            month = " " + month.substring(1);
        }
        if (date.substring(0, 1).equals("0")) {
            date = " " + date.substring(1);
        }
        
        ret[0] = year;
        ret[1] = month;
        ret[2] = date;
        
        return ret;
    }
    
    /**
     * 時間の前ゼロを半角スペースに変換する
     * @param time 時間項目（6桁 hhMMss）
     * @throws Exception
     */
    public String[] convertTime(String hms) {
        String ret[] = new String[3];
        
        String hour = hms.substring(0, 2);
        String mimutes = hms.substring(2, 4);
        String s = hms.substring(4);
        
        if (hour.substring(0, 1).equals("0")) {
            hour = " " + hour.substring(1);
        }
        if (mimutes.substring(0, 1).equals("0")) {
            mimutes = " " + mimutes.substring(1);
        }
        if (s.substring(0, 1).equals("0")) {
            s = " " + mimutes.substring(1);
        }
        
        ret[0] = hour;
        ret[1] = mimutes;
        ret[2] = s;
        
        return ret;
    }
    
    /**
     * 日付の期間を取得する
     * @param nendo 年度
     * @param kaisuu 回数
     * @param menuCode メニューコード
     * @return [0]開始日 [1]開始時間 [2]終了日 [3]終了時間。存在しない場合は""。
     * @throws Exception
     */
    public String[] getKikan(String nendo, String kaisuu, String menuCode) {

        String ret[] = new String[4];
        
        MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
        menuControl = menuControl.findMenuFroKikanNendo(nendo, kaisuu, menuCode);
        
        if (menuControl == null) {
            ret[0] = "";
            ret[1] = "";
            ret[2] = "";
            ret[3] = "";
        } else {
            ret[0] = menuControl.getKaishiDate();
            ret[1] = menuControl.getKaishiTime();
            ret[2] = menuControl.getShuryoDate();
            ret[3] = menuControl.getShuryoTime();
        }
        
        return ret;
    }
    
    /**
     * 年度を取得する
     * @return 年度 存在しない場合""
     * @throws Exception
     */
    public String selectNendo() {
        
        String ret = "";
        
        MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
        menuControl = menuControl.selectNendo();
        
        if (menuControl == null) {
            ret = "";
        } else {
            ret = menuControl.getNendo();
        }
        
        return ret;
    }
    
    /**
     * 回数を取得する
     * @return 回数 存在しない場合""
     * @throws Exception
     */
    public String selectKaisu() {
        
        String ret = "";
        
        MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
        menuControl = menuControl.selectNendo();
        
        if (menuControl == null) {
            ret = "";
        } else {
            ret = menuControl.getKaisu();
        }
        
        return ret;
    }
        
    /**
     * 年度・回数を取得する
     * @return メニュー情報
     * @throws Exception
     */
    public MenuControl selectNendoKaisu() {
        
        MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
        menuControl = menuControl.selectNendo();
        
        return menuControl;
    }

    /**
     * 団体代表者メニュー用期間情報を取得する
     * @return 取得した団体Menu用エンティティ。取得エラーの場合はnull。
     * @throws Exception
     */
    public MenuDntInf getMenuDntInf() {

        //団体Menu用エンティティ作成
        MenuDntInf menuJoho = new MenuDntInf();
        
        try {

            //年度・回数取得
            MenuControl menuControl = selectNendoKaisu();
            String nendo = menuControl.getNendo();
            String kaisu = menuControl.getKaisu();

            //各日付項目取得
            String dntGetKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_TOROKU); //団体申込出願（団体コード未取得）
            String dntMskKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_TOROKU_RE); //団体申込出願（団体申込出願済）
            String dntLoginKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_LOGIN); //団体代表者ログイン
            String dntCnfKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_CONF); //団体情報確認期間
            String dntUpdKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_UPD); //団体情報変更期間
            String mskKikan[] = getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_MSK); //団体申込者登録期間

            menuJoho.setDntGetKigen(dntGetKikan[2]);
            menuJoho.setDntGetKigenTime(dntGetKikan[3]);
            menuJoho.setDntMskKigen(dntMskKikan[2]);
            menuJoho.setDntMskKigenTime(dntMskKikan[3]);
            menuJoho.setDntCnfKigen(dntCnfKikan[2]);
            menuJoho.setDntCnfKigenTime(dntCnfKikan[3]);
            menuJoho.setDntUpdKigen(dntUpdKikan[2]);
            menuJoho.setDntUpdKigenTime(dntUpdKikan[3]);
            menuJoho.setMskKigen(mskKikan[2]);
            menuJoho.setMskKigenTime(mskKikan[3]);

            //各期間フラグを取得
            // 現在の日時
            SystemTime systime = new SystemTime();

            //現在の日時を取得
            String date = systime.getymd1() + systime.gethms1();
            long lngDate = Long.parseLong(date);

            //団体申込出願（団体コード未取得）
            String dateStDantaiTrk = dntGetKikan[0] + dntGetKikan[1];
            String dateEdDantaiTrk = dntGetKikan[2] + dntGetKikan[3];
            long lngDateStDantaiTrk = Long.parseLong(dateStDantaiTrk);
            long lngDateEdDantaiTrk = Long.parseLong(dateEdDantaiTrk);

            if (lngDateStDantaiTrk <= lngDate && lngDate <= lngDateEdDantaiTrk) {
                //期間中
                menuJoho.setDntGetKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setDntGetKigenFlg(MiwConstants.FLG_OFF);
            }

            //団体申込出願（団体申込出願済）
            String dateStDantaiMsk = dntMskKikan[0] + dntMskKikan[1];
            String dateEdDantaiMsk = dntMskKikan[2] + dntMskKikan[3];
            long lngDateStDantaiMsk = Long.parseLong(dateStDantaiMsk);
            long lngDateEdDantaiMsk = Long.parseLong(dateEdDantaiMsk);

            if (lngDateStDantaiMsk <= lngDate && lngDate <= lngDateEdDantaiMsk) {
                //期間中
                menuJoho.setDntMskKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setDntMskKigenFlg(MiwConstants.FLG_OFF);
            }

            //団体代表者ログイン
            String dateStDantaiLogin = dntLoginKikan[0] + dntLoginKikan[1];
            String dateEdDantaiLogin = dntLoginKikan[2] + dntLoginKikan[3];
            long lngDateStDantaiLogin = Long.parseLong(dateStDantaiLogin);
            long lngDateEdDantaiLogin = Long.parseLong(dateEdDantaiLogin);

            //団体代表者ログイン
            if (lngDateStDantaiLogin <= lngDate && lngDate <= lngDateEdDantaiLogin) {
                 //期間中
                menuJoho.setDntLoginKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setDntLoginKigenFlg(MiwConstants.FLG_OFF);
            }

            //団体情報確認期間
            String dateStDantaiCnf = dntCnfKikan[0] + dntCnfKikan[1];
            String dateEdDantaiCnf = dntCnfKikan[2] + dntCnfKikan[3];
            long lngDateStDantaiCnf = Long.parseLong(dateStDantaiCnf);
            long lngDateEdDantaiCnf = Long.parseLong(dateEdDantaiCnf);

            if (lngDateStDantaiCnf <= lngDate && lngDate <= lngDateEdDantaiCnf) {
                //期間中
                menuJoho.setDntCnfKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setDntCnfKigenFlg(MiwConstants.FLG_OFF);
            }

            //団体情報変更期間
            String dateStDantaiUpd = dntUpdKikan[0] + dntUpdKikan[1];
            String dateEdDantaiUpd = dntUpdKikan[2] + dntUpdKikan[3];
            long lngDateStDantaiUpd = Long.parseLong(dateStDantaiUpd);
            long lngDateEdDantaiUpd = Long.parseLong(dateEdDantaiUpd);

            if (lngDateStDantaiUpd <= lngDate && lngDate <= lngDateEdDantaiUpd) {
                //期間中
                menuJoho.setDntUpdKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setDntUpdKigenFlg(MiwConstants.FLG_OFF);
            }

            //団体申込者登録期間
            String dateStShutsugan = mskKikan[0] + mskKikan[1];
            String dateEdShutsugan = mskKikan[2] + mskKikan[3];
            long lngDateStShutsugan = Long.parseLong(dateStShutsugan);
            long lngDateEdShutsugan = Long.parseLong(dateEdShutsugan);

            if (lngDateStShutsugan <= lngDate && lngDate <= lngDateEdShutsugan) {
                //期間中
                menuJoho.setMskKigenFlg(MiwConstants.FLG_ON);
            } else {
                menuJoho.setMskKigenFlg(MiwConstants.FLG_OFF);
            }


            //団体情報確認リンク表示有無(確認・変更のどちらかの期間内であればリンクあり）
            if ((lngDateStDantaiCnf <= lngDate && lngDate <= lngDateEdDantaiCnf) || (lngDateStDantaiUpd <= lngDate && lngDate <= lngDateEdDantaiUpd)) {
                //リンク表示有
                menuJoho.setDntUpdLinkFlg(MiwConstants.FLG_ON);
            } else {
                //リンク表示無
                menuJoho.setDntUpdLinkFlg(MiwConstants.FLG_OFF);
            }

        } catch (Exception e) {
            menuJoho = null;
        }
        
        return menuJoho;
    }
    
}
