package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル:DntMskLoginAct 
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class DntMskLoginAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();

        if (request.getParameter("submit") != null) {
            // Formオブジェクトの作成
            DntMskLoginFrm mskLoginFrm = (DntMskLoginFrm) form;
            // 保持している情報を取得
            DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");

            // session項目に入力値をセット
            BeanUtils.copyProperties(dntInfo, mskLoginFrm);
            // 値のスペースを削除する
            deleteSpace(dntInfo);

            boolean state = ValidationCaller(request, dntInfo);

            if (state) {
                // 団体テーブル検索
                // サービスオブジェクト作成
                DntInfServ dntInfServ = new DntInfServ();

                // ログイン用団体テーブル検索
                Dantai retSearch = dntInfServ.selectDantai(dntInfo);

                if (retSearch == null) {
                    //該当なし
                    errors.add("0", new ActionMessage("errors.dntlogincheck"));
                    saveErrors(request, errors);

                    ret = FWD_NM_ERROR;
                } else {
                    // 同一年度内に再出願していないか確認(イベントコード9999）
                    MoshikomiDantai retMdntSearch = dntInfServ.selectMoshikomiDantaiForDntCode(dntInfo);
                    if (retMdntSearch == null) {
                        // BOをBEANへコピーする
                        BeanUtils.copyProperties(dntInfo, retSearch);
                        // コピーできないBOの項目をBEANへセットする
                        dntInfServ.setDantaiInfo(dntInfo, retSearch);
                        // 受験手数料をセット
                        MiaServ miaServ = new MiaServ();
                        String kingaku = miaServ.getKingaku();
                        dntInfo.setKenteiryo(kingaku);

                        ret = FWD_NM_SUCCESS;

                    } else {
                        // 既に出願済
                        errors.add("0", new ActionMessage("errors.dntMsklogincheck", retSearch.getDantaiName()));
                        saveErrors(request, errors);

                        ret = FWD_NM_ERROR;
                    }

                }
            } else {
                ret = FWD_NM_ERROR;
            }

            session.setAttribute("DntInfo", dntInfo);
        } else {
            ret = FWD_NM_BACK;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, DntInf dntInfo) throws Exception {
        ActionMessages errors = new ActionMessages();

        //団体コードのチェック
        String dntCode = dntInfo.getDantaiCode();
        Validate.validateRequired(dntCode, errors, "1", "団体コード");
        Validate.validateAlphabetOrNumber(dntCode, errors, "1", "団体コード");
        Validate.validateEqualLength(dntCode, MiwConstants.LENGTH_DANTAI_ID, errors, "1", "団体コード");

        //パスワードのチェック
        String password = dntInfo.getDantaiPasswd();
        Validate.validateRequired(password, errors, "2", "パスワード");
        Validate.validateAlphabetOrNumber(password, errors, "2", "パスワード");

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 各値のスペースを削除する
     */
    private void deleteSpace(DntInf dntInfo) {
        dntInfo.setDantaiCode(StringUtility.removeSpace(dntInfo.getDantaiCode()));
        dntInfo.setDantaiPasswd(StringUtility.removeSpace(dntInfo.getDantaiPasswd()));
    }
}
