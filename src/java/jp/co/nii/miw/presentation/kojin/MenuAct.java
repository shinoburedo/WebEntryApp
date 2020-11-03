package jp.co.nii.miw.presentation.kojin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.service.MenuJoho;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.sew.business.SystemTime;

import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MenuAct 説明:Menu.jspへの遷移を行うアクションクラス 著作権: Copyright (c) 2012 会社名:
 * 日本情報産業株式会社
 *
 * @author --k.narita
 */
public class MenuAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッションが残っている場合があるので破棄しておく
        request.getSession(true).invalidate();
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        //Menu用エンティティ作成
        MenuJoho menuJoho = new MenuJoho();

        //サービスクラス作成
        MenuServ menuServ = new MenuServ();

        //各日付項目取得
        String nendo = menuServ.selectNendo();
        String ki = menuServ.selectKaisu();

        String idKikan[] = menuServ.getKikan(nendo, ki, MiwConstants.MENU_CODE_JZN); //ＩＤ取得期間
        String shutsuganKikan[] = menuServ.getKikan(nendo, ki, MiwConstants.MENU_CODE_MSK); //申込期間

        //マイページ用期間取得
        String loginKikan[] = menuServ.getKikan(nendo, ki, MiwConstants.MENU_CODE_MYP_LOGIN_KOJIN); //マイページログイン期間
        String henkoKikan[] = menuServ.getKikan(nendo, ki, MiwConstants.MENU_CODE_MYP_UPD_KOJIN); //マイページ更新期間
        String kakuninKikan[] = menuServ.getKikan(nendo, ki, MiwConstants.MENU_CODE_MYP_CONF_KOJIN); //マイページ確認期間

        menuJoho.setIdKigen(idKikan[2]);
        menuJoho.setIdKigenTime(idKikan[3]);
        menuJoho.setShutsuganKigen(shutsuganKikan[2]);
        menuJoho.setShutsuganKigenTime(shutsuganKikan[3]);
        menuJoho.setLoginKigen(loginKikan[2]);
        menuJoho.setLoginKigenTime(loginKikan[3]);
        menuJoho.setHenkoKigen(henkoKikan[2]);
        menuJoho.setHenkoKigenTime(henkoKikan[3]);
        menuJoho.setKakuninKigen(kakuninKikan[2]);
        menuJoho.setKakuninKigenTime(kakuninKikan[3]);

        //各期間フラグを取得
        // 現在の日時
        SystemTime systime = new SystemTime();

        //現在の日時を取得
        String date = systime.getymd1() + systime.gethms1();
        long lngDate = Long.parseLong(date);

        //ＩＤ取得期間
        String dateStId = idKikan[0] + idKikan[1];
        String dateEdId = idKikan[2] + idKikan[3];
        long lngDateStId = Long.parseLong(dateStId);
        long lngDateEdId = Long.parseLong(dateEdId);

        if (lngDateStId <= lngDate && lngDate <= lngDateEdId) {
            menuJoho.setIdKikanFlg(MiwConstants.FLG_ON);
        } else {
            menuJoho.setIdKikanFlg(MiwConstants.FLG_OFF);
        }

        //申込期間
        String dateStShutsugan = shutsuganKikan[0] + shutsuganKikan[1];
        String dateEdShutsugan = shutsuganKikan[2] + shutsuganKikan[3];
        long lngDateStShutsugan = Long.parseLong(dateStShutsugan);
        long lngDateEdShutsugan = Long.parseLong(dateEdShutsugan);

        if (lngDateStShutsugan <= lngDate && lngDate <= lngDateEdShutsugan) {
            //申込期間中
            menuJoho.setShutsuganKikanFlg(MiwConstants.FLG_ON);
        } else {
            menuJoho.setShutsuganKikanFlg(MiwConstants.FLG_OFF);
        }

        //マイページログイン期間
        String dateStLogin = loginKikan[0] + loginKikan[1];
        String dateEdLogin = loginKikan[2] + loginKikan[3];
        String dateStHenko = henkoKikan[0] + henkoKikan[1];
        String dateEdHenko = henkoKikan[2] + henkoKikan[3];
        String dateStKakunin = kakuninKikan[0] + kakuninKikan[1];
        String dateEdKakunin = kakuninKikan[2] + kakuninKikan[3];

        long lngDateStLogin = Long.parseLong(dateStLogin);
        long lngDateEdLogin = Long.parseLong(dateEdLogin);
        long lngDateStHenko = Long.parseLong(dateStHenko);
        long lngDateEdHenko = Long.parseLong(dateEdHenko);
        long lngDateStKakunin = Long.parseLong(dateStKakunin);
        long lngDateEdKakunin = Long.parseLong(dateEdKakunin);

        if (lngDateStKakunin <= lngDate && lngDate <= lngDateEdKakunin) {
            //マイページ確認期間中
            menuJoho.setMypageCnfKikanFlg(MiwConstants.FLG_ON);
        } else {
            menuJoho.setMypageCnfKikanFlg(MiwConstants.FLG_OFF);
        }

        if (lngDateStHenko <= lngDate && lngDate <= lngDateEdHenko) {
            //マイページ変更期間中
            menuJoho.setMypageUpdKikanFlg(MiwConstants.FLG_ON);
        } else {
            menuJoho.setMypageUpdKikanFlg(MiwConstants.FLG_OFF);
        }

//        //マイページボタンの使用可否セット(確認・変更のどちらかの期間内であれば使用可）
//        if ((lngDateStKakunin <= lngDate && lngDate <= lngDateEdKakunin) || (lngDateStHenko <= lngDate && lngDate <= lngDateEdHenko)) {
//            //使用可
//            menuJoho.setMypageButtonFlg(MiwConstants.FLG_ON);
//        } else {
//            menuJoho.setMypageButtonFlg(MiwConstants.FLG_OFF);
//        }


        //マイページボタンの使用可否セット
        if (lngDateStLogin <= lngDate && lngDate <= lngDateEdLogin) {
            //ログイン可能期間
            menuJoho.setMypageButtonFlg(MiwConstants.FLG_ON);
        } else {
            //ログイン期間外
            menuJoho.setMypageButtonFlg(MiwConstants.FLG_OFF);
        }

        //変更期間
//        menuJoho.setMypageKikanName("変更・確認期限");
//        menuJoho.setMypageKigen(menuJoho.getHenkoKigenDisp());

        session.setAttribute("MenuJoho", menuJoho);
        ret = FWD_NM_SUCCESS;

        return ret;
    }
}