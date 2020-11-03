package jp.co.nii.miw.presentation.dantai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MenuDntInf;
import jp.co.nii.miw.business.service.MenuServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.sew.business.SystemTime;

import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MenuDaihyoshaAct 説明:MenuDaihyosha.jspへの遷移を行うアクションクラス
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class MenuDaihyoshaAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッションが残っている場合があるので破棄しておく
        request.getSession(true).invalidate();
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        // 団体代表者メニュー用期間情報を取得する
        MenuDntInf menuJoho = new MenuServ().getMenuDntInf();

        session.setAttribute("MenuJoho", menuJoho);
        ret = FWD_NM_SUCCESS;

        return ret;
    }
}