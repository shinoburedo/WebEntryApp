package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * タイトル:DntTopAct 
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class DntTopAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(true);
        // 返却するフォワード名
        String ret = "";
        // 団体情報
        DntInf dntInfo = new DntInf();

        // 年度,期を取得
        MiaServ dafServ = new MiaServ();
        MenuControl menuControl = dafServ.selectNendoKaisu(MiwConstants.MENU_CODE_DNT_TOROKU);
        String nendo = menuControl.getNendo();
        String ki = menuControl.getKaisu();

        dntInfo.setNendo(nendo);
        dntInfo.setKaisu(ki);

        // 受験手数料をセット
        MiaServ miaServ = new MiaServ();
        String kingaku = miaServ.getKingaku();
        dntInfo.setKenteiryo(kingaku);

        session.setAttribute("DntInfo", dntInfo);
        ret = FWD_NM_SUCCESS;

        return ret;

    }
}
