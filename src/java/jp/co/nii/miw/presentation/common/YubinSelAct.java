package jp.co.nii.miw.presentation.common;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.business.domain.YubinBangoJisho;
import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * タイトル: 郵便番号検索共通
 * 説明:　自動入力を押した場合のアクション
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-eahra
 */
public class YubinSelAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 返却するフォワード名
        String ret = "";
        // サービスクラスの作成
        MiaServ miaServ = new MiaServ();
        
        // 住所検索
        List<YubinBangoJisho> jushoList = new LinkedList();
        jushoList = miaServ.selectJusho(request.getParameter("yubinNo1"), request.getParameter("yubinNo2"));
        
        request.setAttribute("YubinList", jushoList);
        ret = FWD_NM_SUCCESS;

        return ret;
    }
}