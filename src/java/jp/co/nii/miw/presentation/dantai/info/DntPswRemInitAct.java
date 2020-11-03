package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MenuDntInf;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.CheckUtility;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;



/**
 * タイトル:DntPswRemInitAct
 * 説明:DntPswRemInitの初期化アクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author k-hirao
 */
public class DntPswRemInitAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        //メニュー情報を取得する
        MenuDntInf menuJoho = new MenuServ().getMenuDntInf();
        if (menuJoho == null || 
                MiwConstants.FLG_OFF.equals(menuJoho.getDntLoginKigenFlg())) {
            //団体代表者ログイン期間外エラー
            return FWD_NM_KIKAN;
        }

        //保持した情報を取得する
        String loginUrl = (String) session.getAttribute("loginUrl");
        if (CheckUtility.isBlank(loginUrl)) {
            loginUrl = MiwConstants.URL_DANTAI_KAKUNIN;
            // ログインへ戻る場合のUrlをセット
            session.setAttribute("loginUrl", loginUrl);
        }

        session.setAttribute("MenuJoho", menuJoho);
        ret = FWD_NM_SUCCESS;

        return ret;
    }

}