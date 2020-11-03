package jp.co.nii.miw.presentation.dantai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MenuDntInf;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.sew.business.SystemTime;

import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MenuMoshikomishaAct 説明:Menu.jspへの遷移を行うアクションクラス 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --k.narita
 */
public class MenuMoshikomishaAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッションが残っている場合があるので破棄しておく
        request.getSession(true).invalidate();
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        //団体Menu用エンティティ作成
        MenuDntInf menuJoho = new MenuDntInf();

        //サービスクラス作成
        MenuServ menuServ = new MenuServ();

        //各日付項目取得
        String nendo = menuServ.selectNendo();
        String kaisu = menuServ.selectKaisu();

        String mskKikan[] = menuServ.getKikan(nendo, kaisu, MiwConstants.MENU_CODE_DNT_MSK); //団体申込者登録期間
        String loginKikan[] = menuServ.getKikan(nendo, kaisu, MiwConstants.MENU_CODE_MYP_CONF_DNT); //団体マイページログイン期間（確認期間）
        String updKikan[] = menuServ.getKikan(nendo, kaisu, MiwConstants.MENU_CODE_MYP_UPD_DNT); //団体マイページ変更期間）

        menuJoho.setMskKigen(mskKikan[2]);
        menuJoho.setMskKigenTime(mskKikan[3]);
        menuJoho.setMypLoginKigen(loginKikan[2]);
        menuJoho.setMypLoginKigenTime(loginKikan[3]);
        menuJoho.setMypUpdKigen(updKikan[2]);
        menuJoho.setMypUpdKigenTime(updKikan[3]);

        //各期間フラグを取得
        // 現在の日時
        SystemTime systime = new SystemTime();

        //現在の日時を取得
        String date = systime.getymd1() + systime.gethms1();
        long lngDate = Long.parseLong(date);

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

        //団体マイページログイン(確認期間）
        String dateStLogin = loginKikan[0] + loginKikan[1];
        String dateEdLogin = loginKikan[2] + loginKikan[3];

        long lngDateStLogin = Long.parseLong(dateStLogin);
        long lngDateEdLogin = Long.parseLong(dateEdLogin);

        if (lngDateStLogin <= lngDate && lngDate <= lngDateEdLogin) {
            //ログイン期間中(確認期間）
            menuJoho.setMypLoginKigenFlg(MiwConstants.FLG_ON);
        } else {
            //ログイン期間外(確認期間）
            menuJoho.setMypLoginKigenFlg(MiwConstants.FLG_OFF);
        }

        //団体マイページ変更期間
        String dateStUpd = updKikan[0] + updKikan[1];
        String dateEdUpd = updKikan[2] + updKikan[3];

        long lngDateStUpd = Long.parseLong(dateStUpd);
        long lngDateEdUpd = Long.parseLong(dateEdUpd);

        if (lngDateStUpd <= lngDate && lngDate <= lngDateEdUpd) {
            //変更期間中
            menuJoho.setMypUpdKigenFlg(MiwConstants.FLG_ON);
        } else {
            //変更期間外
            menuJoho.setMypUpdKigenFlg(MiwConstants.FLG_OFF);
        }
        
        //団体マイページリンク表示有無(確認・変更のどちらかの期間内であればリンクあり）
        if ((lngDateStLogin <= lngDate && lngDate <= lngDateEdLogin) || (lngDateStUpd <= lngDate && lngDate <= lngDateEdUpd)) {
            //リンク表示有
            menuJoho.setMypLinkFlg(MiwConstants.FLG_ON);
        } else {
            //リンク表示無
            menuJoho.setMypLinkFlg(MiwConstants.FLG_OFF);
        }

        session.setAttribute("MenuJoho", menuJoho);
        ret = FWD_NM_SUCCESS;

        return ret;
    }
}