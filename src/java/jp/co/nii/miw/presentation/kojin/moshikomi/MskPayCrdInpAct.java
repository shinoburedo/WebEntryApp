package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.business.Validate;

/**
 * タイトル:MypMskUpdInpAct
 * 説明:MypMskUpdInpのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MskPayCrdInpAct extends AbstractAction {

  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        
        //トークン保存
        saveToken(request);
        
        //Formオブジェクトの作成
        MskPayCrdFrm mskPayCrdFrm = (MskPayCrdFrm) form;
        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");
        
        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(mskTorokuJoho, mskPayCrdFrm);
        
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
        
        String mm = mskTorokuJoho.getExpMm();
        Validate.validateRequired(mm, errors, "1", "有効期限の月");
        Validate.validateNumber(mm, errors, "1", "有効期限の月");
        Validate.validateEqualLength(mm, MiwConstants.EQUAL_LEN_EXP ,errors, "1", "有効期限の月");
        String yy = mskTorokuJoho.getExpYy();
        Validate.validateRequired(yy, errors, "1", "有効期限の年");
        Validate.validateNumber(yy, errors, "1", "有効期限の年");
        Validate.validateEqualLength(yy, MiwConstants.EQUAL_LEN_EXP ,errors, "1", "有効期限の年");
        
        if (mm.length() == MiwConstants.EQUAL_LEN_EXP
                && yy.length() == MiwConstants.EQUAL_LEN_EXP) {
            //日付チェック
            String ymd = "20" + yy + mm + "01";
            Validate.validateDate(ymd, errors, "1", "有効期限");
        }
        
        String cardNo1 = mskTorokuJoho.getCardNo1();
        Validate.validateRequired(cardNo1, errors, "2", "カード番号１");
        Validate.validateNumber(cardNo1, errors, "2", "カード番号１");
//        Validate.validateMaxLength(cardNo1, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号１");
        
        String cardNo2 = mskTorokuJoho.getCardNo2();
        Validate.validateRequired(cardNo2, errors, "2", "カード番号２");
        Validate.validateNumber(cardNo2, errors, "2", "カード番号２");
//        Validate.validateMaxLength(cardNo2, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号２");
        
        String cardNo3 = mskTorokuJoho.getCardNo3();
        Validate.validateRequired(cardNo3, errors, "2", "カード番号３");
        Validate.validateNumber(cardNo3, errors, "2", "カード番号３");
//        Validate.validateMaxLength(cardNo3, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号３");
        
        String cardNo4 = mskTorokuJoho.getCardNo4();
        Validate.validateRequired(cardNo4, errors, "2", "カード番号４");
        Validate.validateNumber(cardNo4, errors, "2", "カード番号４");
//        Validate.validateMaxLength(cardNo4, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号４");
        
//        クレジットカード桁数チェック13以上16以下
        String cardNo = cardNo1 + cardNo2 + cardNo3 + cardNo4;
        Validate.validateMaxLength(cardNo, MiwConstants.MAX_LEN_ALL_CARD_NO, errors, "2", "カード番号");
        Validate.validateMinLength(cardNo, MiwConstants.MIN_LEN_ALL_CARD_NO, errors, "2", "カード番号");
        
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