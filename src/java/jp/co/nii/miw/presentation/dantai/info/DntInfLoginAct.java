package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfMenu;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル:DntInfLoginAct 
 * 著作権: Copyright (c) 2012
 * 会社名: 日本情報産業株式会社
 *
 * @author --r.ehara
 */
public class DntInfLoginAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        ActionMessages errors = new ActionMessages();

        if (request.getParameter("submit") != null) {
            // Formオブジェクトの作成
            DntInfLoginFrm mskLoginFrm = (DntInfLoginFrm) form;
            // 保持している情報を取得
            DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");
            // エラー初期化
            dntInfo.setErrors(new ActionMessages());

            // session項目に入力値をセット
            BeanUtils.copyProperties(dntInfo, mskLoginFrm);
            // 値のスペースを削除する
            deleteSpace(dntInfo);

            boolean state = ValidationCaller(request, dntInfo);

            if (state) {
                // サービスオブジェクト作成
                DntInfServ dntInfServ = new DntInfServ();
                DntInf retSearch = dntInfServ.getDantaiInfo(dntInfo);

                if (retSearch == null) {
                    //団体テーブル該当なし
                    errors.add("0", new ActionMessage("errors.dntlogincheck"));
                    saveErrors(request, errors);

                    ret = FWD_NM_ERROR;
                } else if (CheckUtility.isBlank(retSearch.getDantaiMoshikomiUketsukeNo())) {
                    // 今年度申込出願していない場合
                    errors.add("0", new ActionMessage("errors.dntUpdlogincheck", retSearch.getDantaiName()));
                    saveErrors(request, errors);

                    ret = FWD_NM_ERROR;
                } else {
                    // 検索結果をBEANへコピーする
                    BeanUtils.copyProperties(dntInfo, retSearch);

                    // Menu表示用データを取得してセット
                    session.setAttribute("DntInfMenu", getDntInfo(dntInfo));

                    ret = FWD_NM_SUCCESS;
                }
            } else {
                ret = FWD_NM_ERROR;
            }

            session.setAttribute("DntInfo", dntInfo);

        } else {
            ret = FWD_NM_EXCEPTION;
        }

        return ret;
    }

    private DntInfMenu getDntInfo(DntInf dntInfo) throws Exception {
        // 団体情報変更Menu
        DntInfMenu dntInfMenu = new DntInfMenu();
        // メニュー用サービスクラス
        MenuServ menuServ = new MenuServ();

        String dntUpdKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNT_UPD); //団体情報変更期限
        String dntConfKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNT_CONF); //団体情報確認期限
        String dntResKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNTMSK_CONF); //団体申込者情報の確認期間
        String dntResUpdKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNTMSK_UPD); //団体申込者情報の変更期間
        String dntPswUpdKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNT_PSW_UPD); //団体パスワード変更期間
        String dntJknPswUpdKigen[] = menuServ.getKikan(dntInfo.getNendo(), dntInfo.getKaisu(), MiwConstants.MENU_CODE_DNT_JKNPSW_UPD); //団体申込パスワード変更期間

        dntInfMenu.setDntUpdKigen(dntUpdKigen[2]);
        dntInfMenu.setDntUpdKigenTime(dntUpdKigen[3]);
        dntInfMenu.setDntConfKigen(dntConfKigen[2]);
        dntInfMenu.setDntConfKigenTime(dntConfKigen[3]);
        dntInfMenu.setDntResKigen(dntResKigen[2]);
        dntInfMenu.setDntResKigenTime(dntResKigen[3]);
        dntInfMenu.setDntResUpdKigen(dntResUpdKigen[2]);
        dntInfMenu.setDntResUpdKigenTime(dntResUpdKigen[3]);
        dntInfMenu.setDntPswUpdKigen(dntPswUpdKigen[2]);
        dntInfMenu.setDntPswUpdKigenTime(dntPswUpdKigen[3]);
        dntInfMenu.setDntJknPswUpdKigen(dntJknPswUpdKigen[2]);
        dntInfMenu.setDntJknPswUpdKigenTime(dntJknPswUpdKigen[3]);

        // 各期間フラグを取得
        // 現在の日時
        SystemTime systime = new SystemTime();

        // 現在の日時を取得
        String date = systime.getymd1() + systime.gethms1();
        long lngDate = Long.parseLong(date);

        // 団体情報変更
        String dateStDantaiUpd = dntUpdKigen[0] + dntUpdKigen[1];
        String dateEdDantaiUpd = dntUpdKigen[2] + dntUpdKigen[3];
        long lngDateStDantaiUpd = Long.parseLong(dateStDantaiUpd);
        long lngDateEdDantaiUpd = Long.parseLong(dateEdDantaiUpd);

        if (lngDateStDantaiUpd <= lngDate && lngDate <= lngDateEdDantaiUpd) {
            //期間中
            dntInfMenu.setDntUpdKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntUpdKigenFlg(MiwConstants.FLG_OFF);
        }

        // 団体情報確認
        String dateStDantaiCnf = dntConfKigen[0] + dntConfKigen[1];
        String dateEdDantaiCnf = dntConfKigen[2] + dntConfKigen[3];
        long lngDateStDantaiCnf = Long.parseLong(dateStDantaiCnf);
        long lngDateEdDantaiCnf = Long.parseLong(dateEdDantaiCnf);

        if (lngDateStDantaiCnf <= lngDate && lngDate <= lngDateEdDantaiCnf) {
            // 期間中
            dntInfMenu.setDntConfKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntConfKigenFlg(MiwConstants.FLG_OFF);
        }

        // 団体情報確認ボタン使用可否フラグ(確認・変更のどちらかの期間内であれば使用可）
        if ((lngDateStDantaiCnf <= lngDate && lngDate <= lngDateEdDantaiCnf) || (lngDateStDantaiUpd <= lngDate && lngDate <= lngDateEdDantaiUpd)) {
            // 使用可能
            dntInfMenu.setDntUpdLinkFlg(MiwConstants.FLG_ON);
        } else {
            // 使用不可
            dntInfMenu.setDntUpdLinkFlg(MiwConstants.FLG_OFF);
        }

        // 団体申込者情報の確認期間
        String dateStDantaiRes = dntResKigen[0] + dntResKigen[1];
        String dateEdDantaiRes = dntResKigen[2] + dntResKigen[3];
        long lngDateStDantaiRes = Long.parseLong(dateStDantaiRes);
        long lngDateEdDantaiRes = Long.parseLong(dateEdDantaiRes);

        if (lngDateStDantaiRes <= lngDate && lngDate <= lngDateEdDantaiRes) {
            // 期間中
            dntInfMenu.setDntResKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntResKigenFlg(MiwConstants.FLG_OFF);
        }

        // 団体申込者情報の変更期間
        String dateStDantaiResUpd = dntResUpdKigen[0] + dntResUpdKigen[1];
        String dateEdDantaiResUpd = dntResUpdKigen[2] + dntResUpdKigen[3];
        long lngDateStDantaiResUpd = Long.parseLong(dateStDantaiResUpd);
        long lngDateEdDantaiResUpd = Long.parseLong(dateEdDantaiResUpd);

        if (lngDateStDantaiResUpd <= lngDate && lngDate <= lngDateEdDantaiResUpd) {
            // 期間中
            dntInfMenu.setDntResUpdKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntResUpdKigenFlg(MiwConstants.FLG_OFF);
        }

        // 団体申込者情報確認ボタン使用可否フラグ(確認・変更のどちらかの期間内であれば使用可）
        if ((lngDateStDantaiRes <= lngDate && lngDate <= lngDateEdDantaiRes) || (lngDateStDantaiResUpd <= lngDate && lngDate <= lngDateEdDantaiResUpd)) {
            // 使用可能
            dntInfMenu.setDntResLinkFlg(MiwConstants.FLG_ON);
        } else {
            // 使用不可
            dntInfMenu.setDntResLinkFlg(MiwConstants.FLG_OFF);
        }

        // 団体パスワード変更期間
        String dateStDantaiPsw = dntPswUpdKigen[0] + dntPswUpdKigen[1];
        String dateEdDantaiPsw = dntPswUpdKigen[2] + dntPswUpdKigen[3];
        long lngDateStPsw = Long.parseLong(dateStDantaiPsw);
        long lngDateEdPsw = Long.parseLong(dateEdDantaiPsw);

        if (lngDateStPsw <= lngDate && lngDate <= lngDateEdPsw) {
            //期間中
            dntInfMenu.setDntPswUpdKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntPswUpdKigenFlg(MiwConstants.FLG_OFF);
        }

        // 団体申込パスワード変更期間
        String dateStDantaiJknPsw = dntJknPswUpdKigen[0] + dntJknPswUpdKigen[1];
        String dateEdDantaiJknPsw = dntJknPswUpdKigen[2] + dntJknPswUpdKigen[3];
        long lngDateStJknPsw = Long.parseLong(dateStDantaiJknPsw);
        long lngDateEdJknPsw = Long.parseLong(dateEdDantaiJknPsw);

        if (lngDateStJknPsw <= lngDate && lngDate <= lngDateEdJknPsw) {
            //期間中
            dntInfMenu.setDntJknPswUpdKigenFlg(MiwConstants.FLG_ON);
        } else {
            dntInfMenu.setDntJknPswUpdKigenFlg(MiwConstants.FLG_OFF);
        }

        return dntInfMenu;

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
