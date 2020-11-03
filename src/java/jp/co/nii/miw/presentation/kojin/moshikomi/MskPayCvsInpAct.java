package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.utility.StringUtility;

/**
 * タイトル:MypMskUpdInpAct
 * 説明:MypMskUpdInpのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MskPayCvsInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //Formオブジェクトの作成
        MskPayCvsFrm mskPayCvsFrm = (MskPayCvsFrm) form;
        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");
        
        //トークン保存
        saveToken(request);

        //サービスクラス作成
        MiaServ miaServ =new MiaServ();

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(mskTorokuJoho, mskPayCvsFrm);

        //このアクション内で
        //上記内容で事前登録するボタンを押されたら
        if (request.getParameter("submit") != null) {

            boolean state = this.ValidationCaller(request, mskTorokuJoho);

            if (state) {
                mskTorokuJoho.setErrors(new ActionMessages());
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_ERROR;
            }
        } else if (request.getParameter("back") != null) {

            ret = FWD_NM_BACK;
        }

        //session保存
        session.setAttribute("MskTorokuJoho", mskTorokuJoho);
        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {

        ActionMessages errors = new ActionMessages();

        //不正値チェック用
        String[] listConveni = StringUtility.getValueList(MiwConstants.DISP_KESSAI_CONVENIENCE_SHUBETSU);

        Validate.validateRequired(mskTorokuJoho.getKessaiConvenienceShubetsu(), errors, "1", "コンビニ種別");
        Validate.validatePermissionSelect(mskTorokuJoho.getKessaiConvenienceShubetsu(), listConveni, errors, "1", "コンビニ種別");

        //エラーをセッションBeanに格納
        mskTorokuJoho.setErrors(errors);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}