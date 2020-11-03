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
 * タイトル:MgrMskSchInpAct
 * 説明:MgrMskSchInpのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntInfMskListInitAct extends AbstractAction {
    //初期ページ

    private final int INIT_PAGE = 1;

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        // 保持している情報を取得
        DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");
        DntInfMenu dntInfMenu = (DntInfMenu) session.getAttribute("DntInfMenu");
        //サービスオブジェクト作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        //申込者情報確認期間内
        if (MiwConstants.FLG_ON.equals(dntInfMenu.getDntResKigenFlg())) {


            //申込者検索
            DntInfSearch dntInfSearch = mskTorokuServ.selectMskListFromDntInfo(dntInfo, INIT_PAGE, MiwConstants.SORT_CODE_DEFAULT);
            //並び順：標準をセット
            dntInfSearch.setSortCode(MiwConstants.SORT_CODE_DEFAULT);
            //検索するページ数をセット
            dntInfSearch.setNowPage(INIT_PAGE);

            //検索条件でのレコードが一件以上あったら
            if (!dntInfSearch.getMskTorokuBeanList().isEmpty()) {

                ret = FWD_NM_SUCCESS;
            } else {
                //検索条件でのレコードが一件も無い場合エラー
                ActionMessages errors = new ActionMessages();
                errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("errors.nodata"));
                saveErrors(request, errors);
                ret = FWD_NM_ERROR;
            }
            //session に保存
            session.setAttribute("DntMskSearch", dntInfSearch);
        }else{
            ret = FWD_NM_SESSION;
        }


        return ret;
    }
}