package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MypLogoutAct
 * 説明:MypLogoutのアクションクラス
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class MypLogoutAct extends AbstractAction {

  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //返却するフォワード名
        String ret = "";
        
        //セッションを破棄する
        request.getSession(true).invalidate();
        
        ret = FWD_NM_SUCCESS;
  
        return ret;
    }
}