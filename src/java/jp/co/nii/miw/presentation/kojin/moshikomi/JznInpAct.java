package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import jp.co.nii.miw.business.service.moshikomi.JznTorokuServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.beanutils.BeanUtils;

/**
 * タイトル:JznInpAct
 * 説明:JznInpのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class JznInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        //トークン発行
        saveToken(request);

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            JznInpFrm jznInpFrm = (JznInpFrm) form;
            //保持している情報を取得
            JznToroku jznTorokuJoho = (JznToroku) session.getAttribute("JznTorokuJoho");
            //フォームの内容をセッションBeanへコピー
            BeanUtils.copyProperties(jznTorokuJoho, jznInpFrm);
            //値のスペースを削除する
            jznTorokuJoho.deleteSpace();

            boolean state = this.ValidationCaller(request, jznTorokuJoho);

            if (state) {
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_ERROR;
            }
            
        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        }
        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, JznToroku jznTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();
        
        JznTorokuServ jznTorokuServ = new JznTorokuServ();
        
        errors = jznTorokuServ.publicValidationCaller(request, jznTorokuJoho);
        
        //エラーをセッションBeanに格納
        jznTorokuJoho.setErrors(errors);
        
        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}