package jp.co.nii.miw.presentation.dantai.info;

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
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:DntInfUpdInpAct
 * 説明:DntInfUpdInpAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntInfUpdInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";

        // トークン発行
        saveToken(request);

        if (request.getParameter("next") != null) {
            // Formオブジェクトの作成
            DntInfUpdInpFrm dntInpFrm = (DntInfUpdInpFrm) form;
            // 保持している情報を取得
            DntInf dntJoho = (DntInf) session.getAttribute("DntInfo");
            DntInf dntInfoInp = (DntInf) session.getAttribute("DntInfoInp");

            // フォームの内容をセッションBeanへコピー
            BeanUtils.copyProperties(dntInfoInp, dntInpFrm);
            // 値のスペースを削除する
            dntJoho.deleteSpace();
            dntInfoInp.deleteSpace();

            boolean state = this.ValidationCaller(request, dntJoho, dntInfoInp);

            if (state) {
                // フォームのスクロール位置を初期化
                dntInpFrm.set("scrollTop", "0");

                ret = FWD_NM_SUCCESS;

            } else {
                ret = FWD_NM_ERROR;
            }
            // sessionに保存
            session.setAttribute("DntInfoInp", dntInfoInp);

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;

        }
        return ret;

    }

    private boolean ValidationCaller(HttpServletRequest request, DntInf dntJoho, DntInf dntJohoInp) throws Exception {
        ActionMessages errors = new ActionMessages();

        DntInfServ dntServ = new DntInfServ();

        // 変更なしかどうか
        if (!dntServ.checkUpd(dntJoho, dntJohoInp, errors)) {
            errors.add("noupd", new ActionMessage("errors.noupd"));
        } else {
            // 入力共通チェック
            errors = dntServ.publicValidationCaller(request, dntJohoInp, false, false);
            // 申込者数の承認前チェック
//            dntServ.dntMoshikoShasuValidation(errors, dntJoho, dntJohoInp);
        }

        //エラーをセッションBeanに格納
        dntJohoInp.setErrors(errors);

        if (!errors.isEmpty()) {
            if (errors.get("noupd").hasNext()) {
                saveMessages(request, errors);
            } else {
                saveErrors(request, errors);
            }
            return false;
        } else {
            return true;
        }

    }
}