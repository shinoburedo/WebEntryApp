package jp.co.nii.miw.presentation.kojin.moshikomi;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.presentation.AbstractAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.StringUtility;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

/**
 * タイトル:MypMskUpdInpAct
 * 説明:MypMskUpdInpのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class MskInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //Formオブジェクトの作成
        MskInpFrm mskInpFrm = (MskInpFrm) form;
        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");
        // トークン発行
        saveToken(request);

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(mskTorokuJoho, mskInpFrm);
        //値のスペースを削除する
        deleteSpace(mskTorokuJoho);

        //このアクション内で
        //上記内容で次へボタンを押されたら
        if (request.getParameter("submit") != null) {

            //入力チェック
            boolean state = this.ValidationCaller(request, mskTorokuJoho);
            if (state) {
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_ERROR;
            }

            //フォームのスクロール位置を初期化
            mskInpFrm.set("scrollTop", "0");

            //session保存
            session.setAttribute("MskTorokuJoho", mskTorokuJoho);


        }
        else if(request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        } else {
            ret = FWD_NM_ERROR;

        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        errors = mskTorokuServ.publicValidationCaller(request, mskTorokuJoho);

        //エラーをセッションBeanに格納
        mskTorokuJoho.setErrors(errors);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 各値のスペースを削除する
     */
    public void deleteSpace(MskToroku mskToroku) {
        mskToroku.setShimeiSei(StringUtility.removeSpace(mskToroku.getShimeiSei()));
        mskToroku.setShimeiMei(StringUtility.removeSpace(mskToroku.getShimeiMei()));
        mskToroku.setShimeiSeiKana(StringUtility.removeSpace(mskToroku.getShimeiSeiKana()));
        mskToroku.setShimeiMeiKana(StringUtility.removeSpace(mskToroku.getShimeiMeiKana()));
        mskToroku.setBirthday(StringUtility.removeSpace(mskToroku.getBirthday()));
        mskToroku.setBirthdayYy(StringUtility.removeSpace(mskToroku.getBirthdayYy()));
        mskToroku.setBirthdayMm(StringUtility.removeSpace(mskToroku.getBirthdayMm()));
        mskToroku.setBirthdayDd(StringUtility.removeSpace(mskToroku.getBirthdayDd()));
        mskToroku.setYubinNo(StringUtility.removeSpace(mskToroku.getYubinNo()));
        mskToroku.setYubinNo1(StringUtility.removeSpace(mskToroku.getYubinNo1()));
        mskToroku.setYubinNo2(StringUtility.removeSpace(mskToroku.getYubinNo2()));
        mskToroku.setTodofuken(StringUtility.removeSpace(mskToroku.getTodofuken()));
        mskToroku.setJusho1(StringUtility.removeSpace(mskToroku.getJusho1()));
        mskToroku.setJusho2(StringUtility.removeSpace(mskToroku.getJusho2()));
        mskToroku.setJusho3(StringUtility.removeSpace(mskToroku.getJusho3()));
        mskToroku.setJusho4(StringUtility.removeSpace(mskToroku.getJusho4()));
        mskToroku.setMailAddress(StringUtility.removeSpace(mskToroku.getMailAddress()));
        mskToroku.setMailAddressKakunin(StringUtility.removeSpace(mskToroku.getMailAddressKakunin()));
        mskToroku.setTelNo(StringUtility.removeSpace(mskToroku.getTelNo()));
        mskToroku.setCellphoneNo(StringUtility.removeSpace(mskToroku.getCellphoneNo()));
        mskToroku.setPasswdAnswer1(StringUtility.removeSpace(mskToroku.getPasswdAnswer1()));
    }
}