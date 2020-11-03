package jp.co.nii.miw.presentation.common;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.common.KaijoJoho;
import jp.co.nii.miw.business.service.common.KaijoServ;
import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author h-katayama
 */
public class KaijoListAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //返却するフォワード名
        String ret = "";

        try {
            //会場用エンティティ作成
            List<KaijoJoho> kaijoJohoList = new ArrayList<KaijoJoho>();

            KaijoJoho kaijoJoho = new KaijoJoho();

            //サービスクラス作成
            MiaServ miaServ = new MiaServ();
            KaijoServ kaijoServ = new KaijoServ();

            //年度・回数を取得
            MenuControl menuMsk = miaServ.selectNendoKaisu();

            kaijoJoho.setNendo(menuMsk.getNendo());
            kaijoJoho.setKaisu(menuMsk.getKaisu());
            kaijoJoho.setEventCode(menuMsk.getEventCode());

            // 試験地と会場名をセット
            kaijoJohoList = kaijoServ.setKaijoList(kaijoJoho);

            request.setAttribute("KaijoJoho", kaijoJohoList);
            ret = FWD_NM_SUCCESS;

        } catch (Exception e) {
            ret = FWD_NM_EXCEPTION;
        }

        return ret;
    }
}
