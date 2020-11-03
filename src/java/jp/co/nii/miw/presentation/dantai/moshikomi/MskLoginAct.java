package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル:MskLoginAct 
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class MskLoginAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        // Formオブジェクトの作成
        MskLoginFrm mskLoginFrm = (MskLoginFrm) form;

        ActionMessages errors = new ActionMessages();

        if (request.getParameter("submit") != null) {
            // 保持している情報を取得
            MskToroku mskInfo = (MskToroku) session.getAttribute("MskTorokuJoho");

            // session項目に入力値をセット
            BeanUtils.copyProperties(mskInfo, mskLoginFrm);
            // 値のスペースを削除する
            deleteSpace(mskInfo);

            boolean state = ValidationCaller(request, mskInfo);

            if (state) {
                // 申込団体テーブル検索
                // サービスオブジェクト作成
                DntInfServ dntInfServ = new DntInfServ();

                // ログイン用申込団体テーブル検索
                MoshikomiDantai retSearch = dntInfServ.selectMoshikomiDantai(mskInfo.getDantaiMoshikomiUketsukeNo(),
                        mskInfo.getDantaiMoshikomiPasswd(), mskInfo.getNendo(), mskInfo.getKaisu());

                if (retSearch == null) {
                    // 該当なし
                    errors.add("0", new ActionMessage("errors.msklogincheck"));
                    saveErrors(request, errors);

                    ret = FWD_NM_ERROR;
                } else {

                    if (!MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE.equals(retSearch.getTetsudukiJokyoKbn())) {
                        // 手続き状況区分が承認前以外の場合
                        errors.add("0", new ActionMessage("errors.msklogincheckShonin"));
                        saveErrors(request, errors);
                        ret = FWD_NM_ERROR;
                    } else {
                        // 共通サービスオブジェクト作成
                        MiaServ miaServ = new MiaServ();
                        // 受験手数料を取得
                        String kingaku = miaServ.getKingaku();
                        mskInfo.setKenteiryo(kingaku);
                        // 団体コードをセット
                        mskInfo.setDantaiCode(retSearch.getDantaiCode());
                        // 団体区分をセット 
                        mskInfo.setMoshikomiKbn(MiwConstants.KOJIN_DANTAI_KBN_DANTAI);

                        ret = FWD_NM_SUCCESS;
                    }
                }
            } else {
                ret = FWD_NM_ERROR;
            }

            session.setAttribute("MskTorokuJoho", mskInfo);
        } else {
            ret = FWD_NM_EXCEPTION;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MskToroku mskInfo) throws Exception {
        ActionMessages errors = new ActionMessages();

        //団体申込者登録用IDのチェック
        String dntCode = mskInfo.getDantaiMoshikomiUketsukeNo();
        Validate.validateRequired(dntCode, errors, "1", "団体申込者登録用ID");
        Validate.validateAlphabetOrNumber(dntCode, errors, "1", "団体申込者登録用ID");
        Validate.validateEqualLength(dntCode, MiwConstants.LENGTH_DANTAI_JUKEN_ID, errors, "1", "団体申込者登録用ID");

        //団体申込者登録用パスワードのチェック
        String password = mskInfo.getDantaiMoshikomiPasswd();
        Validate.validateRequired(password, errors, "2", "団体申込者登録用パスワード");
        Validate.validateAlphabetOrNumber(password, errors, "2", "団体申込者登録用パスワード");

        //エラーをセッションBeanに格納(エラーがない場合は初期化）
        mskInfo.setErrors(errors);

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
    private void deleteSpace(MskToroku mskInfo) {
        mskInfo.setDantaiMoshikomiUketsukeNo(StringUtility.removeSpace(mskInfo.getDantaiMoshikomiUketsukeNo()));
        mskInfo.setDantaiMoshikomiPasswd(StringUtility.removeSpace(mskInfo.getDantaiMoshikomiPasswd()));
    }
}
