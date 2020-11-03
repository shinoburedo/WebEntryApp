package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.beanutils.BeanUtils;

/**
 * タイトル:DntInpAct
 * 説明:DntInpAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntMskInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";

        // トークン発行
        saveToken(request);

        // Formオブジェクトの作成
        DntMskInpFrm dntInpFrm = (DntMskInpFrm) form;
        // 保持している情報を取得
        DntInf dntJoho = (DntInf) session.getAttribute("DntInfo");
        // フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(dntJoho, dntInpFrm);
        // 値のスペースを削除する
        dntJoho.deleteSpace();


        if (request.getParameter("submit") != null) {

            boolean state = this.ValidationCaller(request, dntJoho);

            if (state) {
                //フォームのスクロール位置を初期化
                dntInpFrm.set("scrollTop", "0");
                //session保存
                session.setAttribute("DntInfo", dntJoho);
                ret = FWD_NM_SUCCESS;

            } else {
                ret = FWD_NM_ERROR;
            }

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;

            // 住所自動入力    
        }
        return ret;

    }

    private boolean ValidationCaller(HttpServletRequest request, DntInf dntJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        DntInfServ dntServ = new DntInfServ();

        errors = dntServ.publicValidationCaller(request, dntJoho, false, true);

        //エラーをセッションBeanに格納
        dntJoho.setErrors(errors);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}