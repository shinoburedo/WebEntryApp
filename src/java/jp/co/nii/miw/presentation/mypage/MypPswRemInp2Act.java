package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

/**
 * タイトル:MypPswRemInp2Act
 * 説明:MypPswRemInp2のアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class MypPswRemInp2Act extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            DynaActionForm mypPswRemFrm = (DynaActionForm) form;
            //保持している情報を取得
            Moshikomisha moshikomisha = (Moshikomisha) session.getAttribute("Moshikomisha");
            if (moshikomisha == null) {
                // 情報がない場合、セッションエラー
                return FWD_NM_SESSION;
            }
            //フォームの内容取得
            String passwdAnswer = StringUtility.removeSpace(mypPswRemFrm.getString("passwdAnswer1"));
            //入力値チェック
            boolean state = this.ValidationCaller(request, passwdAnswer, moshikomisha.getPasswdAnswer1());

            if (state) {

                boolean isSuccess = true;
                if (MiwConstants.STRESS_MODE.equals(MiwConstants.STRESS_MODE_HONBAN)) {
                    //パスワード通知メール送信
                    isSuccess = new MiaMailSendServ().sendPasswordTsuchiMail(moshikomisha);
                }

                if (isSuccess) {
                    //セッションを切る
                    session.invalidate();
                    //完了画面へ遷移
                    ret = FWD_NM_SUCCESS;
                } else {
                    //送信失敗
                    LogGenerate.errorOutput("ユーザＩＤ：" + moshikomisha.getUserId()
                            + "のパスワード通知メール送信が失敗しました。エラー画面に遷移します。");
                    throw new Exception("ユーザＩＤ：" + moshikomisha.getUserId()
                            + " メールアドレス：" + moshikomisha.getMailAddress() + " メール送信失敗");
                }

            } else {
                //入力エラー
                ret = FWD_NM_ERROR;
            }
        } else {
            ret = FWD_NM_SESSION;
        }
        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, String passwdAnswer, String passwdAnswerDB) {
        ActionMessages errors = new ActionMessages();

        Validate.validateRequired(passwdAnswer, errors, "1", "質問に対する回答");

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            //DBの値と同じであるかチェック
            if (passwdAnswer.equals(passwdAnswerDB)) {
                return true;
            } else {
                errors.add("1", new ActionMessage("errors.RemQuestionUnmatch"));
                saveErrors(request, errors);
                return false;
            }
        }
    }
}