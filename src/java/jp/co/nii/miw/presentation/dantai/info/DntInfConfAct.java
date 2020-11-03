package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfMenu;
import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.beanutils.BeanUtils;

/**
 * タイトル:DntInfConfAct
 * 説明:DntInfConfAct
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class DntInfConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(true);
        // セッションから団体メニュー情報を取得
        DntInfMenu dntInfoMenu = (DntInfMenu) session.getAttribute("DntInfMenu");
        // セッションから団体情報を取得
        DntInf dntJoho = (DntInf)session.getAttribute("DntInfo");
        // 入力用団体情報BEANを作成する
        DntInf dntInfoInp = new DntInf();
        // 団体情報BEAN⇒入力用団体情報BEANにCOPY
        BeanUtils.copyProperties(dntInfoInp, dntJoho);
        // セッションに入力用団体情報BEANを保存
        session.setAttribute("DntInfoInp", dntInfoInp);

        // 変更期間フラグがONならば次画面へ遷移、
        return MiwConstants.FLG_ON.equals(dntInfoMenu.getDntUpdKigenFlg()) ? FWD_NM_SUCCESS : FWD_NM_EXCEPTION;
    }
}