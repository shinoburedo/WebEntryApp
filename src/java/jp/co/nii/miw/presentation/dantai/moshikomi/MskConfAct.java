package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;

import jp.co.nii.sew.business.SystemTime;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * タイトル:MskConfAct
 * 説明:MskConfActのアクションクラス
 * 著作権:   Copyright (c) 2012
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
        MiaMailSendServ miwMailSendServ = new MiaMailSendServ();

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

            // 二重登録チェック
            if (MiwConstants.STRESS_MODE.equals(MiwConstants.STRESS_MODE_HONBAN)) {
                Moshikomisha retMoshikomisha = mskTorokuServ.selectMoshikomishaDoubleForShimeiEtc(mskTorokuJoho);

                if (retMoshikomisha != null) {
                    //二重登録
                    session.invalidate();
                    return FWD_NM_DOUBLE;
                }                
            }

            boolean state = this.ValidationCaller(request, mskTorokuJoho);

            if (state) {
                //申込日をセット
                SystemTime sysTim = new SystemTime();
                mskTorokuJoho.setMoshikomibi(sysTim.getymd1());
                // 申込者情報追加、申込情報追加
                if (mskTorokuServ.createDantaiMoshikomi(mskTorokuJoho)) {
                    //作業用フォルダの画像を保存用フォルダへ
                    mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_TMP,
                            MiwConstants.PICTURE_PATH_HOZON, mskTorokuJoho.getGazoId());

                    //作業用縮小フォルダの画像を保管用縮小フォルダへ
                    mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_SMALL_TMP,
                            MiwConstants.PICTURE_PATH_SMALL_HOZON, mskTorokuJoho.getGazoId());

                    //メール送信
                    if (miwMailSendServ.sendMskResuMail(mskTorokuJoho)) {
                        //メール送信成功
                        //リクエストに情報を保存
                        request.setAttribute("MskTorokuJoho", mskTorokuJoho);
                        request.setAttribute("menuUrl", session.getAttribute("menuUrl"));
                        //セッションの情報を破棄する
                        session.removeAttribute("MskTorokuJoho");
                        session.removeAttribute("menuUrl");
                        session.removeAttribute("loginUrl");
                        
                        ret = FWD_NM_SUCCESS;
                    } else {
                        //メール送信失敗
                        LogGenerate.errorOutput("ユーザＩＤ："
                                + mskTorokuJoho.getUserId() + "の申込完了メール送信が失敗しました。エラー画面に遷移しません。");
//                            throw new Exception("ユーザＩＤ：" + mskTorokuJoho.getUserId() + "メール送信失敗");
                    }
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

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        // サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        // 共通エラーチェック
        errors = mskTorokuServ.publicValidationCaller(request, mskTorokuJoho);
        // 共通以外のエラーチェック
        mskTorokuServ.dantaiMskValidationCaller(errors, request, mskTorokuJoho);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}