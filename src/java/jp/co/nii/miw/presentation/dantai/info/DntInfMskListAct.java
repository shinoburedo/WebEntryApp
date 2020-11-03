package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfMenu;
import jp.co.nii.miw.business.service.dantai.DntInfSearch;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionMessage;

/**
 * タイトル:DntInfMskListAct
 * 説明:DntInfMskListActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntInfMskListAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        // 保持している情報を取得
        DntInfSearch srchInfo = (DntInfSearch) session.getAttribute("DntMskSearch");
        DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");
        DntInfMenu dntInfMenu = (DntInfMenu) session.getAttribute("DntInfMenu");

        // 申込者情報確認期間内
        if (MiwConstants.FLG_ON.equals(dntInfMenu.getDntResKigenFlg())) {
            // form
            DntInfMskListFrm srchFrm = (DntInfMskListFrm) form;

            // ソート順を選択した場合
            if (MiwConstants.FLG_ON.equals(srchFrm.getString("sortFlg"))) {
                // 申込者検索
                ret = searchMskList(srchInfo, dntInfo, srchFrm, srchInfo.getNowPage(), request, session, true);
                srchFrm.set("sortFlg", MiwConstants.FLG_OFF);

            } else if (request.getParameter("page") != null) {
                // 次ページ等リンクをクリックした場合    
                // 申込者検索
                ret = searchMskList(srchInfo, dntInfo, srchFrm, Integer.parseInt(request.getParameter("page")), request, session, false);

            } else {
                ret = FWD_NM_SESSION;
            }
        } else {
            ret = FWD_NM_SESSION;
        }


        return ret;
    }

    /**
     * 申込者の一覧検索
     * @param srchInfo
     * @param dntInfo
     * @param page
     * @param request
     * @param session
     * @return
     * @throws Exception 
     */
    private String searchMskList(DntInfSearch srchInfo, DntInf dntInfo, DntInfMskListFrm srchFrm, int page, HttpServletRequest request, HttpSession session, Boolean changeSort) throws Exception {
        // サービスオブジェクト作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        // 返却
        String ret = "";
        // 現在のページ
        int pageNo = changeSort ? srchInfo.getNowPage() : Integer.parseInt(request.getParameter("page"));
        // ソートコード
        String srtCd = changeSort ? srchFrm.getString("sortCode") : srchInfo.getSortCode();
        // 申込者検索
        srchInfo = mskTorokuServ.selectMskListFromDntInfo(dntInfo, page, srtCd);
        // 検索条件でのレコードが一件以上あったら
        if (!srchInfo.getMskTorokuBeanList().isEmpty()) {
            srchInfo.setNowPage(pageNo);
            srchInfo.setSortCode(srtCd);
            // session に保存
            session.setAttribute("DntMskSearch", srchInfo);

            ret = FWD_NM_SUCCESS;
        } else {
            // 検索条件でのレコードが一件も無い場合エラー
            ActionMessages errors = new ActionMessages();
            errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nodata"));
            saveErrors(request, errors);
            // session に保存
            session.setAttribute("DntMskSearch", srchInfo);

            ret = FWD_NM_ERROR;
        }

        return ret;
    }
}