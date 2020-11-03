package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInfMenu;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;


import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:DntJknPswCngInpInitAct
 * 説明:DntJknPswCngInpInitActの初期化アクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntJknPswCngInpInitAct extends AbstractAction {

  
    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        // 保持している情報を取得
        DntInfMenu dntInfoMenu = (DntInfMenu) session.getAttribute("DntInfMenu");
        if (MiwConstants.FLG_OFF.equals(dntInfoMenu.getDntJknPswUpdKigenFlg())){
            // 期間外のためセッションを切る
            session.invalidate();
            ret = FWD_NM_SESSION;
        }else{
            ret = FWD_NM_SUCCESS;
        }
        return ret;
    }

}