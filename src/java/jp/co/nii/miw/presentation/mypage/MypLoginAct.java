package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.mypage.MypLoginServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskInfServ;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import jp.co.nii.sew.business.Validate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:MypLoginAct
 * 説明:MypLoginのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MypLoginAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッションを破棄する
        request.getSession(true).invalidate();
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();
        //Formオブジェクトの作成
        MypLoginFrm mypLoginFrm = (MypLoginFrm) form;

        boolean state = this.ValidationCaller(request, mypLoginFrm);

        if (state) {
            MypageJoho mypageJoho = new MypageJoho();
            MypLoginServ mypLoginServ = new MypLoginServ();
            //年度・季を取得する
            MiaServ serv = new MiaServ();
            MenuControl bo = serv.selectNendoKaisu();
            
            if (bo != null) {
                mypageJoho.setNendo(bo.getNendo());
                mypageJoho.setKaisu(bo.getKaisu());
                //ログインチェック
                Moshikomisha moshikomisha = mypLoginServ.authenticate(mypageJoho.getNendo(),
                        mypageJoho.getKaisu(),
                        mypLoginFrm.getString("userid"),
                        mypLoginFrm.getString("passwd"));

                if (moshikomisha != null) {

                    //申込テーブルからレコードを取得する
                    Moshikomi moshikomi = mypLoginServ.getMoshikomiForUserId(mypageJoho.getNendo(),
                            mypageJoho.getKaisu(),
                            mypLoginFrm.getString("userid"),
                            mypLoginFrm.getString("passwd"));

                    if (moshikomi != null && !moshikomi.getTetsudukiJokyoKbn().equals(MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI)) {
                        //手続状況区分が受付取消ではない
                        
                        //マイページの各機能の利用期間を取得する
                        mypLoginServ.getMypageKikan(mypageJoho.getNendo(), mypageJoho.getKaisu(), mypageJoho,moshikomi);
//                        利用期間チェック
                        if (!mypageJoho.getIsKikanLogin()){
                            //ログイン可能期間ではない
                            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.loginkikan"));
                            saveErrors(request, errors);
                            return FWD_NM_ERROR;
                        }
                        //申込情報を取得する
                        MypMskInf moshikomiJoho = new MypMskInfServ().getMskInf(moshikomi.getMoshikomiUketsukeNo(), moshikomi.getNendo(), moshikomi.getKaisu(), moshikomi.getEventCode());
                        

                        //受験地一覧を取得する
//                        ArrayList<Shikenchi> jukenchiList1 = mypLoginServ.getShikenchiList(mypageJoho.getNendo(), mypageJoho.getKaisu(), MiwConstants.JI_1);
//                        ArrayList<Shikenchi> jukenchiList2 = mypLoginServ.getShikenchiList(mypageJoho.getNendo(), mypageJoho.getKaisu(), MiwConstants.JI_2);

//                        session.setAttribute("JukenchiList1", jukenchiList1);
//                        session.setAttribute("JukenchiList2", jukenchiList2);
                        session.setAttribute("MypageJoho", mypageJoho);
                        session.setAttribute("MoshikomiJoho", moshikomiJoho);
                        ret = FWD_NM_SUCCESS;
                    } else {
                        //手続状況区分が受付取消の場合エラー
                        errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.logincheck"));
                        saveErrors(request, errors);
                        ret = FWD_NM_ERROR;
                    }
                } else {
                    //ユーザＩＤが存在しない、またはパスワードが一致しない
                    errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.logincheck"));
                    saveErrors(request, errors);
                    ret = FWD_NM_ERROR;
                }
            } else {
                //ログイン可能期間ではない
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.loginkikan"));
                saveErrors(request, errors);
                ret = FWD_NM_ERROR;
            }
        } else {
            ret = FWD_NM_ERROR;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MypLoginFrm mypLoginFrm) throws Exception {
        ActionMessages errors = new ActionMessages();
        //ＩＤの未入力チェック
        Validate.validateRequired(mypLoginFrm.getString("userid"), errors, "1", "ＩＤ");
        //パスワードの未入力チェック
        Validate.validateRequired(mypLoginFrm.getString("passwd"), errors, "2", "パスワード");

        //ＩＤ、パスワードの文字数チェック（エラーメッセージが一緒）
        if (!GenericValidator.maxLength((String) mypLoginFrm.get("userid"), MiwConstants.LENGTH_USER_ID)
                || !GenericValidator.maxLength((String) mypLoginFrm.get("passwd"), MiwConstants.MAX_LEN_PASSWD)) {
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.logincheck"));
        }

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}