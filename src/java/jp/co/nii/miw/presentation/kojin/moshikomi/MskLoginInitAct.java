package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:JznTopAct
 * 説明:JznTopのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class MskLoginInitAct extends AbstractAction {

   @Override
   protected String doPreProcess(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        //セッションが残っている場合があるので破棄しておく
        request.getSession(true).invalidate();
        return null;
    }
   
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";
        
        MskToroku mskTorokuJoho = new MskToroku();
        //年度,期を取得
        MiaServ dafServ = new MiaServ();
        MenuControl menuMsk = dafServ.selectNendoKaisu(MiwConstants.MENU_CODE_MSK);
        String nendo = menuMsk.getNendo();
        String ki = menuMsk.getKaisu();
        if (nendo != null) {
            mskTorokuJoho.setNendo(nendo);
        }
        if (ki != null) {
            mskTorokuJoho.setKaisu(ki);
        }
        
        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        
        //各日付項目の取得
        //session-timeout時間を取得
        int intervalTime = session.getMaxInactiveInterval();
        mskTorokuJoho = mskTorokuServ.setShutsuganDate(mskTorokuJoho, intervalTime);

        String[] mypageKigen = mskTorokuServ.getMypageKigen(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu());
//        String jukenhyoHassoYoteibiKokunai = mskTorokuServ.getJukenhyoHassoYoteibiKokunai(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu());

        mskTorokuJoho.setMypageKigen(mypageKigen[0]);
        mskTorokuJoho.setMypageKigenTime(mypageKigen[1]);
//        mskTorokuJoho.setJukenhyoHassobiKokunai(jukenhyoHassoYoteibiKokunai);
        
        session.setAttribute("MskTorokuJoho", mskTorokuJoho);
        
        ret = FWD_NM_SUCCESS;

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request) throws Exception {
        ActionMessages errors = new ActionMessages();

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }
}