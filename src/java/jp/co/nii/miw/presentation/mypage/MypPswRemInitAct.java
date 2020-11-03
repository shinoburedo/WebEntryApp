package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MypPswRemInitAct
 * 説明:MypPswRemInitの初期化アクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class MypPswRemInitAct extends AbstractAction {

    @Override
   protected String doPreProcess(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response) throws Exception {

        //セッションが残っている場合があるので破棄しておく
        request.getSession(true).invalidate();
        return null;
    }
  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //返却するフォワード名
        String ret = "";

        ret = FWD_NM_SUCCESS;

        return ret;
    }

}