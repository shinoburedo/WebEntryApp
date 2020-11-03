package jp.co.nii.miw.presentation.dantai.moshikomi;
import jp.co.nii.miw.presentation.kojin.moshikomi.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MskAgrAct
 * 説明:MskAgrのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class MskAgrAct extends AbstractAction {
  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        
        if (request.getParameter("submit") != null) {

            boolean state = this.ValidationCaller(request);
            
            if (state) {
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_ERROR;
            }
     
        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        } else{
            ret = FWD_NM_EXCEPTION;
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