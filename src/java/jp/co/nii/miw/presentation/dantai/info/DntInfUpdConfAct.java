package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.sew.SEWException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:DntInfUpdConfAct
 * 説明:DntInfUpdConfAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntInfUpdConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // サービス作成
        DntInfServ dntInfServ = new DntInfServ();
        // 返却するフォワード名
        String ret = "";
        // 前画面のトークンと比較する。
        if (!isTokenValid(request, true) && MiwConstants.STRESS_MODE.equals(MiwConstants.STRESS_MODE_HONBAN)) {

            ActionMessages amg = new ActionMessages();

            saveErrors(request, amg);
            //セッションを切る
            session.invalidate();
            // 前画面のトークンと異なる場合は画面遷移不正でエラー
            return FWD_NM_SESSION;
        } else {
            saveToken(request);
        }

        if (request.getParameter("change") != null) {
            // 保持している情報を取得
            DntInf dntJoho = (DntInf) session.getAttribute("DntInfo");
            DntInf dntJohoInp = (DntInf) session.getAttribute("DntInfoInp");

            // 再度入力チェックを行う
            boolean state = this.ValidationCaller(request, dntJohoInp);

            if (state) {
                if (dntInfServ.isDantaiAccepted(dntJoho, dntJohoInp)){
//                団体情報編集中に管理者より承認されていて、かつ、人数変更している場合はエラー
                    ActionMessages errors = new ActionMessages();
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.shoninUpdate"));
                    saveErrors(request, errors);
                    return FWD_NM_ERROR;
                }
                // 団体Update・申込団体update
                dntInfServ.modifyDantai(dntJoho, dntJohoInp);
                // sessionを保存
                session.setAttribute("DntInfo",dntJoho);
                // sessionを削除
                session.removeAttribute("DntInfoInp");
                ret = FWD_NM_SUCCESS;

            } else {
                throw new SEWException("更新前再入力チェックでエラーが起きました。");
            }

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;

        }
        return ret;

    }

    private boolean ValidationCaller(HttpServletRequest request, DntInf dntJohoInp) throws Exception {
        ActionMessages errors = new ActionMessages();

        DntInfServ dntServ = new DntInfServ();

        // 入力共通チェック
        errors = dntServ.publicValidationCaller(request, dntJohoInp, false, false);

        //エラーをセッションBeanに格納
        dntJohoInp.setErrors(errors);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}