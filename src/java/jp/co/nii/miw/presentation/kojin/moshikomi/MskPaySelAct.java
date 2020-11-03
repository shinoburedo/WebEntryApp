package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.utility.StringUtility;

/**
 * タイトル:MypMskUpdInpAct
 * 説明:MypMskUpdInpのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MskPaySelAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //Formオブジェクトの作成
        MskPaySelFrm mskPaySelFrm = (MskPaySelFrm) form;

        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(mskTorokuJoho, mskPaySelFrm);

        //サービスクラス作成
        MiaServ miaServ = new MiaServ();

        //このアクション内で
        //上記内容で次へボタンを押されたら
        if (request.getParameter("submit") != null) {

            boolean state = this.ValidationCaller(request, mskTorokuJoho);

            if (state) {
//                決済関連情報クリア
                clearKessaiInpInf(mskTorokuJoho);
                if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
                    //クレジットカード決済
                    ret = FWD_NM_CARD;
                } else if(mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CVS)){
                    //コンビニ決済
                    //お支払期限日（表示用）を取得
                    mskTorokuJoho.setKessaiKigen(miaServ.getKigen(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), false));
                    ret = FWD_NM_CONVENI;
                } else if(mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
                    //ペイジー決済
                    //トークン保存
                    saveToken(request);
                    //お支払期限日（表示用）を取得
                    mskTorokuJoho.setKessaiKigen(miaServ.getKigen(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), false));
                    ret = FWD_NM_PAYEASY;
                }
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
        String[] listKessaiHoho = StringUtility.getValueList(MiwConstants.DISP_KESSAI_HOHO_KBN);

        Validate.validateRequired(mskTorokuJoho.getKessaiHohoKbn(), errors, "0", "決済方法");
        Validate.validatePermissionSelect(mskTorokuJoho.getKessaiHohoKbn(), listKessaiHoho, errors, "0", "決済方法");

        //エラーをセッションBeanに格納
        mskTorokuJoho.setErrors(errors);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }

    private void clearKessaiInpInf(MskToroku mskTorokuJoho) throws Exception {
        
        mskTorokuJoho.setKessaiConvenienceShubetsu("");
        mskTorokuJoho.setKessaiKigen("");
        mskTorokuJoho.setCardNo("");
        mskTorokuJoho.setCardNo1("");
        mskTorokuJoho.setCardNo2("");
        mskTorokuJoho.setCardNo3("");
        mskTorokuJoho.setCardNo4("");
        mskTorokuJoho.setExpYy("");
        mskTorokuJoho.setExpMm("");
    }
    
}