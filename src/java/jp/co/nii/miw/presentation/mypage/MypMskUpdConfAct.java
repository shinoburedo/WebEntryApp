package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskInfServ;
import jp.co.nii.miw.business.service.mypage.MypMskUpdServ;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import jp.co.nii.sew.SEWException;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:MypMskUpdConfAct
 * 説明:MypMskUpdConfのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MypMskUpdConfAct extends AbstractAction {

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
            MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");

            //年度を取得
            String nendo = mypageJoho.getNendo();
            //回数を取得
            String kaisu = mypageJoho.getKaisu();

            //再度入力チェックを行う
            boolean state = this.ValidationCaller(request, moshikomiJohoInp, nendo, kaisu);

            if (state) {

                //申込情報を更新する
                mypMskUpdServ.updateMoshikomiJoho(nendo, kaisu, moshikomiJohoInp, moshikomiJoho, moshikomiJoho.getUserId());

                //更新後の申込情報を取得する
                MypMskInf moshikomiJohoUpd = mypMskInfServ.getMskInf(moshikomiJohoInp.getMoshikomiUketsukeNo(), nendo, kaisu, moshikomiJohoInp.getEventCode());

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

                //マイページ情報の氏名をセットする
                mypageJoho.setShimeiSei(moshikomiJohoUpd.getShimeiSei());
                mypageJoho.setShimeiMei(moshikomiJohoUpd.getShimeiMei());

                session.setAttribute("MoshikomiJoho", moshikomiJohoUpd);
                //入力申込情報Beanを削除
                session.removeAttribute("MoshikomiJohoInp");
                
                //更新完了メッセージ
                ActionMessages msgs = new ActionMessages();
                msgs.add("0", new ActionMessage("messages.updateMskInf"));
                saveMessages(request, msgs);

                ret = FWD_NM_SUCCESS;
            } else {
                throw new SEWException("更新前入力再チェックでエラーが起きました。");
            }

        } else if (request.getParameter("revise") != null) {

            //保持している情報を取得
            MypMskInf moshikomiJohoInp = (MypMskInf) session.getAttribute("MoshikomiJohoInp");

            MskTorokuServ mskTorokuServ = new MskTorokuServ();

            //申込情報を更新する(補正依頼関連項目のみ)
            mypMskUpdServ.updateHoseiIraiSts(moshikomiJohoInp);

            //更新後の申込情報を取得する
            MypMskInf moshikomiJohoUpd = mypMskInfServ.getMskInf(moshikomiJohoInp.getMoshikomiUketsukeNo(),moshikomiJohoInp.getNendo(),moshikomiJohoInp.getKaisu(), moshikomiJohoInp.getEventCode());

            session.setAttribute("MoshikomiJoho", moshikomiJohoUpd);
                //入力申込情報Beanを削除
                session.removeAttribute("MoshikomiJohoInp");

            //作業用フォルダの画像を保存用フォルダへ
            mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_TMP,
                    MiwConstants.PICTURE_PATH_HOZON, moshikomiJohoInp.getGazoId());

            //作業用縮小フォルダの画像を保管用縮小フォルダへ
            mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_SMALL_TMP,
                    MiwConstants.PICTURE_PATH_SMALL_HOZON, moshikomiJohoInp.getGazoId());

            ret = FWD_NM_SUCCESS;

        } else if (request.getParameter("back_inp") != null) {
            ret = FWD_NM_BACK;
        } else if (request.getParameter("back_pic") != null) {
            ret = FWD_NW_REVISE;
        } else if (request.getParameter("logout") != null) {
            ret = FWD_NW_LOGOUT;
        } else {
            ret = FWD_NM_SESSION;
        }



        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MypMskInf moshikomiJohoInp, String nendo, String kaisu) throws Exception {

        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();
        ActionMessages errors = new ActionMessages();

        //再度入力チェックを行う
        mypMskUpdServ.publicValidationCaller(errors, moshikomiJohoInp, nendo, kaisu);

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}