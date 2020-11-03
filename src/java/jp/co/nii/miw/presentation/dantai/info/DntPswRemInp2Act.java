package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Dantai;
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
 * タイトル:DntPswRemInp2Act
 * 説明:DntPswRemInp2のアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author k-hirao
 */
public class DntPswRemInp2Act extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            DynaActionForm dntPswRemFrm = (DynaActionForm) form;
            //保持している情報を取得
            Dantai dantai = (Dantai) session.getAttribute("Dantai");
            if (dantai == null) {
                // 情報がない場合、セッションエラー
                return FWD_NM_SESSION;
            }
            //フォームの内容取得
            String passwdAnswer = StringUtility.removeSpace(dntPswRemFrm.getString("passwdAnswer1"));
            //入力値チェック
            boolean state = this.ValidationCaller(request, passwdAnswer, dantai.getPasswdAnswer1());

            if (state) {

                boolean isSuccess = true;
                if (MiwConstants.STRESS_MODE.equals(MiwConstants.STRESS_MODE_HONBAN)) {
                    //パスワード通知メール送信
                    isSuccess = new MiaMailSendServ().sendPasswordTsuchiMail(dantai);
                }

                if (isSuccess) {
                    //セッションより団体情報除去
                    session.removeAttribute("Dantai");
                    //完了画面へ遷移
                    ret = FWD_NM_SUCCESS;
                } else {
                    //送信失敗
                    LogGenerate.errorOutput("団体コード：" + dantai.getDantaiCode()
                            + "のパスワード通知メール送信が失敗しました。エラー画面に遷移します。");
                    throw new Exception("団体コード：" + dantai.getDantaiCode()
                            + " メールアドレス：" + dantai.getDantaiJimuTantoshaMailAddress() + " メール送信失敗");
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