package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.action.ActionMessages;
import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:JznAgrAct
 * 説明:JznAgrのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class JznAgrAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        
        try {
            JznToroku jznTorokuJoho = (JznToroku) session.getAttribute("JznTorokuJoho");

            if (request.getParameter("submit") != null) {
                //同意する
                //エラー初期化
                jznTorokuJoho.setErrors(new ActionMessages());
                
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_EXCEPTION;
            }
            
            session.setAttribute("JznTorokuJoho", jznTorokuJoho);
        } catch (Exception e) {
            ret = FWD_NM_EXCEPTION;
        }
        
        return ret;
    }

}