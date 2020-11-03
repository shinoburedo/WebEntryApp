package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * タイトル:MskPicConfAct
 * 説明:MskPicConfのアクションクラス
 * 画面で「次へ」ボタンが押されたらMskKijSelへ遷移する
 * 画面で「戻る」ボタンが押されたらMskPicSelへ遷移する
 * 著作権:   Copyright (c) 2010
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MskPicConfAct extends AbstractAction {

    /**ログ出力用の画像ID*/
    private String GAZO_ID = "";
    /** ログに出力するクラス名 */
    private static String CLASS_NAME;

    public MskPicConfAct() {
        CLASS_NAME = this.getClass().getName();
    }

    /**
     * 各処理を呼び出し、その結果によって遷移先を決定する
     * @param mapping マッピング
     * @param form フォーム
     * @param request リクエスト
     * @param response レスポンス
     * @return ActionForward 遷移先<BR>
     */
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = "";
        MskToroku mskTorokuJoho = null;
        //セッション開始
        HttpSession session = request.getSession(false);

        try {
            //保持した情報を取得する
            mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");

            //ログ出力用の画像IDを取得
            GAZO_ID = mskTorokuJoho.getGazoId();

            if (request.getParameter("submit") != null) {//「次　へ」ボタンが押下されたら

                session.setAttribute("MskTorokuJoho", mskTorokuJoho);

                ret = FWD_NM_SUCCESS;

            } else if (request.getParameter("back") != null) {//「戻　る」ボタンが押下されたら

                ret = FWD_NM_BACK;
            } else {
                throw new Exception();
            }



        } catch (Exception ex) {
            //ログ出力
            LogGenerate.errWrite(CLASS_NAME, "execute", ex, request, GAZO_ID);
            //セッションを切る
            session.invalidate();

        }
        //情報を保持する
        session.setAttribute("MskTorokuJoho", mskTorokuJoho);

        // 結果により画面遷移情報をActionへ返却
        return ret;
    }
}
