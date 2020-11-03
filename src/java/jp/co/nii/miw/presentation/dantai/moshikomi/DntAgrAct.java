package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル:DntAgrAct 
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class DntAgrAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        
        try {
            DntInf DntInfo = (DntInf) session.getAttribute("DntInfo");

            if (request.getParameter("submit") != null) {
                //同意する
                //エラー初期化
                DntInfo.setErrors(new ActionMessages());
                
                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_EXCEPTION;
            }
            
            session.setAttribute("DntInfo", DntInfo);
        } catch (Exception e) {
            ret = FWD_NM_EXCEPTION;
        }
        
        return ret;
        
        
    }
}
