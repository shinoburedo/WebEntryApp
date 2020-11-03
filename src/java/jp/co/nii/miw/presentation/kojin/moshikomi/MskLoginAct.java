package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.utility.StringUtility;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.business.Validate;

/**
 * タイトル:MypLoginAct
 * 説明:MypLoginのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MskLoginAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            MskLoginFrm mskLoginFrm = (MskLoginFrm) form;
            //保持している情報を取得
            MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");

            //session項目に入力値をセット
            BeanUtils.copyProperties(mskTorokuJoho, mskLoginFrm);
            //値のスペースを削除する
            deleteSpace(mskTorokuJoho);

            boolean state = ValidationCaller(request, mskTorokuJoho);

            if (state) {
                //moshikomishaテーブル検索
                //サービスオブジェクト作成
                MskTorokuServ mskTorokuServ = new MskTorokuServ();

                //ログイン用moshikomishaテーブル検索
                Moshikomisha retSearch = mskTorokuServ.selectMoshikomisha(mskTorokuJoho);

                if (retSearch == null) {
                    //該当なし
                    errors.add("0", new ActionMessage("errors.logincheck"));
                    saveErrors(request, errors);
                    //エラーをセッションBeanに格納
                    mskTorokuJoho.setErrors(errors);

                    ret = FWD_NM_ERROR;
                } else {
                    if (retSearch.getMoshikomiAriFlg().equals(MiwConstants.FLG_ON)) {
                        //既に登録済みの場合
                        errors.add("0", new ActionMessage("errors.doublecheck"));
                        saveErrors(request, errors);
                        //エラーをセッションBeanに格納
                        mskTorokuJoho.setErrors(errors);

                        ret = FWD_NM_ERROR;

                    } else {
                        MiaServ miaServ = new MiaServ();

                        //受験手数料を取得
                        String kingaku = miaServ.getKingaku();
                        mskTorokuJoho.setKenteiryo(kingaku);
                        mskTorokuJoho.setKessaiKingaku(kingaku);

                        //再申込かチェック(申込テーブルを読む)
                        mskTorokuJoho.setMoshikomiUketsukeNo(retSearch.getUserId());
                        Moshikomi boMoshikomi = mskTorokuServ.selectMoshikomiSaishutsugan(mskTorokuJoho);

                        if (boMoshikomi != null) {
                            //再申込の場合
                            mskTorokuJoho.setSaishutsuganFlg(MiwConstants.FLG_ON);
                            //申込テーブルの内容をセット
                            mskTorokuJoho.setEventCode(boMoshikomi.getEventCode());
                            mskTorokuJoho.setMoshikomiKbn(boMoshikomi.getKojinDantaiKbn());
                            mskTorokuJoho.setShikenchiCode(boMoshikomi.getShikenchiCode());

                            mskTorokuJoho.setJukoKeiken(boMoshikomi.getJukoKeiken());
                            mskTorokuJoho.setJitsumuKeiken(boMoshikomi.getJitsumuKeiken());

                        } else {
                            //再申込ではない場合
                            mskTorokuJoho.setSaishutsuganFlg(MiwConstants.FLG_OFF);
                            //初期値として「個人申込」をセット
                            mskTorokuJoho.setMoshikomiKbn(MiwConstants.KOJIN_DANTAI_KBN_KOJIN);
                        }

                        //申込者テーブルの内容をセット
                        mskTorokuJoho = mskTorokuServ.moveMoshikomisha(mskTorokuJoho, retSearch);

                        ret = FWD_NM_SUCCESS;
                    }
                }
            } else {
                ret = FWD_NM_ERROR;
            }

            session.setAttribute("MskTorokuJoho", mskTorokuJoho);
        } else {
            ret = FWD_NM_BACK;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        //ＩＤのチェック
        String id = mskTorokuJoho.getUserId();
        Validate.validateRequired(id, errors, "1", "ＩＤ");
        Validate.validateAlphabetOrNumber(id, errors, "1", "ＩＤ");
        Validate.validateEqualLength(id, MiwConstants.LENGTH_USER_ID, errors, "1", "ＩＤ");

        //パスワードのチェック
        String password = mskTorokuJoho.getPasswd();
        Validate.validateRequired(password, errors, "2", "パスワード");
        Validate.validateAlphabetOrNumber(password, errors, "2", "パスワード");

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
    private void deleteSpace(MskToroku mskToroku) {
        mskToroku.setUserId(StringUtility.removeSpace(mskToroku.getUserId()));
        mskToroku.setPasswd(StringUtility.removeSpace(mskToroku.getPasswd()));
    }
}