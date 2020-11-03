package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.SEWException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * タイトル:JznConfAct
 * 説明:JznConfTopのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class MskConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        // サービスクラス
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        // 前の画面のトークンと比較する。
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


        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");

        if (request.getParameter("submit") != null) {

            boolean state = this.ValidationCaller(request, mskTorokuJoho);

            if (state) {
                // 申込者情報更新、申込情報追加
                mskTorokuServ.kariUketsuke(mskTorokuJoho);
                
    //            画像ＩＤ取得
                MskTorokuServ service = new MskTorokuServ();
                String gazoId = service.gazoIdIssue();
                mskTorokuJoho.setGazoId(gazoId);
                
                ret = FWD_NM_SUCCESS;
            } else {
                LogGenerate.errWrite(CLASS_NAME, "doProcess", new SEWException("申込仮登録前の入力チェックでエラー発生"));
                ret = FWD_NM_ERROR;
                return ret;
            }

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        }

        session.setAttribute("MskTorokuJoho", mskTorokuJoho);
        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        errors = mskTorokuServ.publicValidationCaller(request, mskTorokuJoho);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}