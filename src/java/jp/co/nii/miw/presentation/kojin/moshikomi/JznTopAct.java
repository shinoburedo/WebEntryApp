package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import jp.co.nii.miw.business.service.moshikomi.JznTorokuServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:JznTopAct
 * 説明:JznTopのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author 
 */
public class JznTopAct extends AbstractAction {

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
        JznToroku jznTorokuJoho = new JznToroku();
        
        //サービスクラス作成
        JznTorokuServ jznTorokuServ = new JznTorokuServ();
        
        //年度,期を取得
        MiaServ dafServ = new MiaServ();
        MenuControl menuControl = dafServ.selectNendoKaisu(MiwConstants.MENU_CODE_JZN);
        String nendo = menuControl.getNendo();
        String ki = menuControl.getKaisu();
        
        jznTorokuJoho.setNendo(nendo);
        jznTorokuJoho.setKaisu(ki);
        jznTorokuJoho.setKikanDateFrom(jznTorokuServ.getJizenTorokuDateFrom(nendo, ki));
        jznTorokuJoho.setKikanTimeFrom(jznTorokuServ.getJizenTorokuTimeFrom(nendo, ki));
        jznTorokuJoho.setKikanDateTo(jznTorokuServ.getJizenTorokuDateTo(nendo, ki));
        jznTorokuJoho.setKikanTimeTo(jznTorokuServ.getJizenTorokuTimeTo(nendo, ki));
        
        session.setAttribute("JznTorokuJoho", jznTorokuJoho);
        ret = FWD_NM_SUCCESS;

        return ret;
    }

}