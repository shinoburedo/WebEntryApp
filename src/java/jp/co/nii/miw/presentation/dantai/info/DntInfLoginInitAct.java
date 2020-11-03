package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.MenuDntInf;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:DntInfLoginInitAct
 * 説明:DntInfLoginInitAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntInfLoginInitAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(true);
        // 返却するフォワード名
        String ret = "";
        // サービス取得
        MenuServ menuServ = new MenuServ();
        // 団体代表者メニュー用期間情報を取得する
        MenuDntInf dntMenu = menuServ.getMenuDntInf();
        // 団体情報期限内
        if (MiwConstants.FLG_ON.equals(dntMenu.getDntLoginKigenFlg())) {
            // 団体情報
            DntInf dntInfo = new DntInf();
            // 年度,回数を取得
            MiaServ miaServ = new MiaServ();
            MenuControl menuControl = miaServ.selectNendoKaisu();
            String nendo = menuControl.getNendo();
            String kaisu = menuControl.getKaisu();

            // 団体情報に年度・回数をセット
            dntInfo.setNendo(nendo);
            dntInfo.setKaisu(kaisu);

            session.setAttribute("DntInfo", dntInfo);
            String loginUrl = MiwConstants.URL_DANTAI_KAKUNIN;
            // ログインへ戻る場合のUrlをセット
            session.setAttribute("loginUrl", loginUrl);
            ret = FWD_NM_SUCCESS;
        } else {
            ret = FWD_NM_SESSION;
        }
        return ret;

    }
}