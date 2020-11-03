package jp.co.nii.miw.business.service;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.sew.SEWException;

import jp.co.nii.sew.business.domain.YubinBangoJisho;
import jp.co.nii.sew.utility.LogGenerate;
import java.util.List;
import java.util.LinkedList;
import jp.co.nii.miw.business.domain.Event;
import jp.co.nii.sew.utility.CheckUtility;



import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;

/**
 * <p>タイトル: 共通サービス</p>
 * <p>説明: 共通サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2012</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author r.ehara
 */
public class MiaServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public MiaServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 年度・回数の入った情報をメニュー制御テーブルより取得する
     * @param menuCode メニューコード
     * @throws Exception
     */
    public MenuControl selectNendoKaisu(String menuCode) {

        MenuControl bo;
        try {
            bo = new MenuControl(DATA_SOURCE_NAME);

            //メニューコードで検索
            bo = bo.findMenuFroKikan(menuCode);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectNendo()", e);
            throw new SEWException("年度取得処理で例外が発生した。", e);
        } finally {
        }

        return bo;
    }

    /**
     * 年度・回数の入った情報をメニュー制御テーブルより取得する
     * @throws Exception
     */
    public MenuControl selectNendoKaisu() {

        MenuControl bo;
        try {
            bo = new MenuControl(DATA_SOURCE_NAME);

            bo = bo.selectNendo();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectNendo()", e);
            throw new SEWException("年度取得処理で例外が発生した。", e);
        } finally {
        }

        return bo;
    }

    /**
     * 年齢計算(比較日：本日)
     * @param ymd
     */
    public static String getAge(String ymd) {
        String ret = "";

        int y, m, d;
        //生年月日を分割
        if (ymd.length() == MiwConstants.LENGTH_BIRTHDAY) {
            y = Integer.parseInt(ymd.substring(0, 4));
            m = Integer.parseInt(ymd.substring(4, 6));
            d = Integer.parseInt(ymd.substring(6));
        } else {
            return "";
        }

        Calendar cal = Calendar.getInstance();
        int today_y = cal.get(Calendar.YEAR);
        int today_m = cal.get(Calendar.MONTH) + 1;
        int today_d = cal.get(Calendar.DATE);

        ret = Integer.toString(today_y - y);
        // 誕生日前だったら１引く
        if (m > today_m || (m == today_m && d > today_d)) {
            ret = Integer.toString(Integer.parseInt(ret) - 1);
        }

        return ret;
    }

    /**
     * 郵便番号で住所を取得する
     *
     * @param yubinNo1 郵便番号１
     * @param yubinNo2 郵便番号２
     * @throws Exception
     */
    public List selectJusho(String yubinNo1, String yubinNo2) {

        List ret = new LinkedList();
        YubinBangoJisho bo = new YubinBangoJisho(DATA_SOURCE_NAME);

        try {
            //郵便番号で検索
            ret = bo.findYubinBango(yubinNo1 + yubinNo2, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectJusho()", e);
            throw new SEWException("住所取得処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 決済期限日を取得する（個人申込）
     *
     * @param nendo 年度
     * @param ki 回数
     * @param hyoji false:実際に決済に使用する日付を取得 true:表示用を取得（最終日の場合のみ影響）
     * @throws Exception
     */
    public String getKigen(String nendo, String ki, boolean hyoji) {
        String ret = "";
        Calendar now = Calendar.getInstance();

        now.add(Calendar.DATE, Integer.parseInt(MiwConstants.KESSAI_KIGEN));

        DecimalFormat df2 = new DecimalFormat("00");
        DecimalFormat df4 = new DecimalFormat("0000");

        ret = df4.format(now.get(now.YEAR))
                + df2.format(now.get(now.MONTH) + 1)
                + df2.format(now.get(now.DATE));

        String kessaiEnd = getKessaiSaishuKigen(nendo, ki);

        if (Long.parseLong(ret) > Long.parseLong(kessaiEnd)) {
            //決済締切日を支払期限日とする。
            ret = kessaiEnd;
        }

        return ret;
    }

    /**
     * 決済期限日を取得する（団体申込）
     *
     * @param nendo 年度
     * @param ki 回数
     * @throws Exception
     */
    public String getKigenForDantai(String nendo, String ki) {
        String ret = "";
        
//        メニュー制御テーブルの決済期限日を取得
        String kessaiEnd = getKessaiSaishuKigen(nendo, ki);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date targetDate = sdf.parse(kessaiEnd);
            Calendar now = Calendar.getInstance();
            now.setTime(targetDate);
            now.add(Calendar.DATE, -Integer.parseInt(MiwConstants.KESSAI_KIGEN_DANTAI));
            DecimalFormat df2 = new DecimalFormat("00");
            DecimalFormat df4 = new DecimalFormat("0000");

            ret = df4.format(now.get(now.YEAR))
                    + df2.format(now.get(now.MONTH) + 1)
                    + df2.format(now.get(now.DATE));
            
        } catch (ParseException ex) {
            throw new SEWException("決済期限日取得でエラー発生", ex);
        }

        return ret;
    }

    /**
     * 決済最終締切日を取得する
     *
     * @param nendo 年度
     * @param ki 回数
     * @throws Exception
     */
    public String getKessaiSaishuKigen(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_KESSAI_SAISHU_KIGEN);

            ret = menuControl.getKaishiDate();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getJukenhyoHassoYoteibiKokugai()", e);
            throw new SEWException("決済最終締切日取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 申込期限を取得する
     *
     * @param nendo 年度
     * @param ki 回数
     * @throws Exception
     */
    public String getMoshikomiKigen(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MSK);

            ret = menuControl.getShuryoDate();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getMoshikomiKigen()", e);
            throw new SEWException("申込期限取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 受験手数料から金額を取得する
     *
     * @throws Exception
     */
    public String getKingaku() {
        String ret = "";
        try {
            Event event = new Event(DATA_SOURCE_NAME);

            //ＩＤ・パスワードで検索
            event = event.find(MiwConstants.EVENT_CODE_ALL);

            if (event == null) {
                ret = "0";
            } else {
                ret = event.getEventRyo();
            }
        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getKingaku()", e);
            throw new SEWException("金額取得処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }
    
    /**
     * 更新箇所のにフォントを指定して返す
     * @param str
     * @param updStr
     * @return 
     */
    public static String getUpdStringConvert(String str, String updStr) {
        if (!str.equals(updStr)) {
            if (CheckUtility.isBlank(updStr)) {
                return "<font color='#ff4500'>-</font>";
            } else {
                return "<font color='#ff4500'>" + updStr + "</font>";
            }
        } else {
            if (CheckUtility.isBlank(updStr)) {
                return "-";
            } else {
                return updStr;
            }
        }
    }

}
