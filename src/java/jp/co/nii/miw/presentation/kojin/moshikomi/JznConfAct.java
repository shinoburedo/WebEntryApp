package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import jp.co.nii.miw.business.service.moshikomi.JznTorokuServ;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:JznConfAct
 * 説明:JznConfTopのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class JznConfAct extends AbstractAction {

  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
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
        
        //サービスオブジェクト作成
        JznTorokuServ jznTorokuServ = new JznTorokuServ();
        MiaMailSendServ dafMailSendServ = new MiaMailSendServ();
        
        if (request.getParameter("submit") != null) {

            //保持している情報を取得
            JznToroku jznTorokuJoho = (JznToroku) session.getAttribute("JznTorokuJoho");
            
            boolean state = this.ValidationCaller(request, jznTorokuJoho);
            if (state) {
                //申込者テーブルにINSERTする
                jznTorokuServ.insertMoshikomisha(jznTorokuJoho);
                //ＩＤ取得完了メールを送信する
                if(dafMailSendServ.sendIdShutokuResuMail(jznTorokuJoho)){
                    //メール送信成功
                    //リクエストに情報を保存
                    request.setAttribute("JznTorokuJoho", jznTorokuJoho);
                    //セッションの情報を破棄する
                    session.removeAttribute("JznTorokuJoho");
                    
                    ret = FWD_NM_SUCCESS;
                }else{
                    //メール送信失敗
                    LogGenerate.errorOutput("ユーザＩＤ："+
                                    jznTorokuJoho.getUserId() + "のＩＤ取得完了メール送信が失敗しました。エラー画面に遷移します。");
                    throw new Exception("ユーザＩＤ："+jznTorokuJoho.getUserId() + "メール送信失敗");
                }
            } else {
                LogGenerate.errWrite("複数タブを使用した可能性があるためExceptionとしました。");
                ret = FWD_NM_EXCEPTION;
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