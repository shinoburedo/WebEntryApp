package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * タイトル:
 * 説明:
 * 著作権:   Copyright (c) 2010
 * 会社名:   日本情報産業株式会社
 * @author --
 */
public class MskGakkoSelAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //返却するフォワード名
        String ret = "";
        ret = FWD_NM_SUCCESS;
        return ret;
    }
}