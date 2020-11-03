package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfMenu;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskInfServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:DntInfMskInfInitAct
 * 説明:DntInfMskInfInitActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntInfMskInfInitAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();
        if (request.getParameter("no") != null && request.getParameter("event") != null) {
            // 保持している情報を取得
            DntInfMenu dntInfMenu = (DntInfMenu) session.getAttribute("DntInfMenu");
            DntInf dntInfo = (DntInf)session.getAttribute("DntInfo");

            if (MiwConstants.FLG_OFF.equals(dntInfMenu.getDntResKigenFlg())) {
                // 団体申込情報確認可能期間ではない
                ret = FWD_NM_SESSION;
            } else {
                //申込情報を取得する
                MypMskInf moshikomiJoho = new MypMskInfServ().getMskInf(request.getParameter("no"),
                        dntInfo.getNendo(), dntInfo.getKaisu(), request.getParameter("event"));

                session.setAttribute("MoshikomiJoho", moshikomiJoho);

                ret = FWD_NM_SUCCESS;
            }

        } else {
            ret = FWD_NM_SESSION;
        }

        return ret;
    }

}