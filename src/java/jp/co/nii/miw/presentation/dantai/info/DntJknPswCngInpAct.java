package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.MiwValidate;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.Validate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

/**
 * タイトル:DntPswCngInpAct
 * 説明:DntPswCngInpActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntJknPswCngInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            DynaActionForm dntPswCngFrm = (DynaActionForm) form;
            //フォームの内容取得
            String password = StringUtility.removeSpace(dntPswCngFrm.getString("password"));
            String passwordNew1 = StringUtility.removeSpace(dntPswCngFrm.getString("passwordNew1"));
            String passwordNew2 = StringUtility.removeSpace(dntPswCngFrm.getString("passwordNew2"));

            //入力値チェック
            boolean state = this.ValidationCaller(request, password, passwordNew1, passwordNew2);

            if (state) {
                //保持している情報を取得
                DntInf dntInf = (DntInf) session.getAttribute("DntInfo");
                //サービス作成
                DntInfServ dntInfServ = new DntInfServ();
                //申込団体テーブルを検索し、現在のパスワードが正しいか確認
                MoshikomiDantai dantai = new MoshikomiDantai(MiwConstants.DS_REGISTRANT).find(dntInf);
                if (password.equals(dantai.getDantaiMoshikomiPasswd())) {
                    //パスワード更新
                    dntInfServ.updatePassword(dantai,passwordNew1);
                    //完了画面へ遷移
                    ret = FWD_NM_SUCCESS;
                } else {
                    //現在のパスワードエラー
                    ActionMessages errors = new ActionMessages();
                    errors.add("", new ActionMessage("errors.kakuninInp", "現在のパスワード"));
                    saveErrors(request, errors);
                    ret = FWD_NM_ERROR;
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

    private boolean ValidationCaller(HttpServletRequest request, String password, String passwordNew1, String passwordNew2) throws Exception {
        ActionMessages errors = new ActionMessages();

        //現在のパスワード
        //必須チェック
        Validate.validateRequired(password, errors, "1", "現在のパスワード");
        //新しいパスワード
        //必須チェック
        Validate.validateRequired(passwordNew1, errors, "1", "新しいパスワード");
        Validate.validateAlphabetOrNumber(passwordNew1, errors, "1", "新しいパスワード");
        Validate.validateRangeLength(passwordNew1, MiwConstants.MIN_LEN_PASSWD, MiwConstants.MAX_LEN_PASSWD, errors, "1", "新しいパスワード");

        //パスワード（確認用）のチェック
        MiwValidate.validateKakuninInp(passwordNew1, passwordNew2, errors, "9", "新しいパスワードの確認入力");

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}