package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * タイトル:DntInpAct
 * 説明:DntInpAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntMskConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
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

        // サービスオブジェクト作成
        DntInfServ dntServ = new DntInfServ();
        MiaMailSendServ mailSendServ = new MiaMailSendServ();

        if (request.getParameter("submit") != null) {

            // 保持している情報を取得
            DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");

            boolean state = this.ValidationCaller(request, dntInfo);
            if (state) {
                // 団体Update・申込団体Insert
                dntServ.shutuganDantai(dntInfo);
                // 団体申込者登録用ID取得完了メールを送信する
                if (mailSendServ.sendDntMskResuMail(dntInfo)) {
                    //メール送信成功
                    // リクエストに情報を保存
                    request.setAttribute("DntInfo", dntInfo);
                    // セッションの情報を破棄する
                    session.removeAttribute("DntInfo");

                } else {
                    // メール送信失敗
                    LogGenerate.errorOutput("団体申込者登録用ID："
                            + dntInfo.getDantaiMoshikomiUketsukeNo() + "の団体申込者登録用ID取得完了メール送信が失敗しました。エラー画面には遷移しません。");
//                    throw new Exception("団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo() + "メール送信失敗");
                }
                ret = FWD_NM_SUCCESS;
            } else {
                LogGenerate.errWrite("複数タブを使用した可能性があるためExceptionとしました。");
                ret = FWD_NM_EXCEPTION;
            }

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
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