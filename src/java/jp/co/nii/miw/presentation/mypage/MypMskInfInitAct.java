package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MypMskInf
 * 説明:MypMskInfの初期化アクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MypMskInfInitAct extends AbstractAction {

  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //保持している情報を取得
        MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");
        if (!mypageJoho.getIsKikanConf()) {
            //セッションを切る
            session.invalidate();
            ret = FWD_NM_SESSION;
        }else{
            ret = FWD_NM_SUCCESS;
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