package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskInfServ;
import jp.co.nii.miw.business.service.mypage.MypMskUpdServ;
import jp.co.nii.sew.SEWException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:DntInfMskUpdConfAct
 * 説明:DntInfMskUpdConfActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntInfMskUpdConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //サービスオブジェクト作成
        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();
        MypMskInfServ mypMskInfServ = new MypMskInfServ();

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

        if (request.getParameter("submit") != null) {
            //保持している情報を取得
            MypMskInf moshikomiJohoInp = (MypMskInf) session.getAttribute("MoshikomiJohoInp");
            MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");
            DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");

            //再度入力チェックを行う
            boolean state = this.ValidationCaller(request, moshikomiJohoInp, moshikomiJoho.getNendo(), moshikomiJoho.getKaisu());

            if (state) {

                //申込情報を更新する
                mypMskUpdServ.updateMoshikomiJoho(moshikomiJoho.getNendo(), moshikomiJoho.getKaisu(), moshikomiJohoInp, moshikomiJoho, dntInfo.getDantaiCode());

                //更新後の申込情報を取得する
                MypMskInf moshikomiJohoUpd = mypMskInfServ.getMskInf(moshikomiJohoInp.getMoshikomiUketsukeNo(), moshikomiJoho.getNendo(), moshikomiJoho.getKaisu(), moshikomiJohoInp.getEventCode());

                // 画像が更新されている？
                if (MiwConstants.FLG_ON.equals(moshikomiJohoInp.getGazoUpdFlg())) {

                    MskTorokuServ mskTorokuServ = new MskTorokuServ();

                    //作業用フォルダの画像を保存用フォルダへ
                    mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_TMP,
                            MiwConstants.PICTURE_PATH_HOZON, moshikomiJohoInp.getGazoId());

                    //作業用縮小フォルダの画像を保管用縮小フォルダへ
                    mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_SMALL_TMP,
                            MiwConstants.PICTURE_PATH_SMALL_HOZON, moshikomiJohoInp.getGazoId());

                }

                session.setAttribute("MoshikomiJoho", moshikomiJohoUpd);
                //入力申込情報Beanを削除
                session.removeAttribute("MoshikomiJohoInp");

                ret = FWD_NM_SUCCESS;
            } else {
                throw new SEWException("更新前再入力チェックでエラーが起きました。");
            }

        } else if (request.getParameter("back_inp") != null) {

            ret = FWD_NM_BACK;

        }  else {
            ret = FWD_NM_SESSION;
        }



        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MypMskInf moshikomiJohoInp, String nendo, String ki) throws Exception {

        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();
        ActionMessages errors = new ActionMessages();

        //再度入力チェックを行う
        mypMskUpdServ.publicValidationCaller(errors, moshikomiJohoInp, nendo, ki);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}