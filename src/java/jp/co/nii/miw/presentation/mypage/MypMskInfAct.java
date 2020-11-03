package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:MypMskInfAct
 * 説明:MypMskInfのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MypMskInfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //トークン発行
        saveToken(request);
        //返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();
        if (request.getParameter("submit") != null) {
            //保持している情報を取得
            MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");
            MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");

            if (!mypageJoho.getIsKikanUpd()) {
                //申込情報変更可能期間ではない
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.kikan"));
                saveErrors(request, errors);
                ret = FWD_NM_ERROR;
            } else {
                //入力用の申込情報Beanオブジェクトを作成
                MypMskInf moshikomiJohoInp = new MypMskInf();

                //登録された申込情報Beanを入力用申込情報Beanへコピー
                BeanUtils.copyProperties(moshikomiJohoInp, moshikomiJoho);

                session.setAttribute("MypageJoho", mypageJoho);
//                session.setAttribute("MoshikomiJohoInp", moshikomiJoho);
                session.setAttribute("MoshikomiJoho", moshikomiJoho);
                session.setAttribute("MoshikomiJohoInp", moshikomiJohoInp);

                ret = FWD_NM_SUCCESS;
            }
        } else if (request.getParameter("revise") != null) {
            //保持している情報を取得
            MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");
            MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");

            if (!mypageJoho.getIsKikanUpd()) {
                //申込情報変更可能期間ではない
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.kikan"));
                saveErrors(request, errors);
                ret = FWD_NM_ERROR;
            } else {
                //入力用の申込情報Beanオブジェクトを作成
                MypMskInf moshikomiJohoInp = new MypMskInf();

                //登録された申込情報Beanを入力用申込情報Beanへコピー
                BeanUtils.copyProperties(moshikomiJohoInp, moshikomiJoho);

                session.setAttribute("MypageJoho", mypageJoho);
                session.setAttribute("MoshikomiJoho", moshikomiJoho);
                session.setAttribute("MoshikomiJohoInp", moshikomiJohoInp);

                ret = FWD_NW_REVISE;
            }
        } else if (request.getParameter("logout") != null) {
            ret = FWD_NW_LOGOUT;
        } else {
            ret = FWD_NM_SESSION;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request) throws Exception {
        ActionMessages errors = new ActionMessages();

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}