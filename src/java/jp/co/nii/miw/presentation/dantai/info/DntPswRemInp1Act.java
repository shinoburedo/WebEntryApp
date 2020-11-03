package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.service.MenuDntInf;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.Validate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.CheckUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

/**
 * タイトル:DntPswRemInp1Act
 * 説明:DntPswRemInp1のアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author k-hirao
 */
public class DntPswRemInp1Act extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        //保持した情報を取得する
        MenuDntInf menuJoho = (MenuDntInf) session.getAttribute("MenuJoho");
        //セッションにない場合、取得
        if (menuJoho == null) {
            menuJoho = new MenuServ().getMenuDntInf();
        }
        if (menuJoho == null || 
                MiwConstants.FLG_OFF.equals(menuJoho.getDntLoginKigenFlg())) {
            //団体代表者ログイン期間外エラー
            return FWD_NM_KIKAN;
        }

        //保持した情報を取得する
        String loginUrl = (String) session.getAttribute("loginUrl");
        if (CheckUtility.isBlank(loginUrl)) {
            loginUrl = MiwConstants.URL_DANTAI_KAKUNIN;
            // ログインへ戻る場合のUrlをセット
            session.setAttribute("loginUrl", loginUrl);
        }

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            DynaActionForm dntPswRemFrm = (DynaActionForm) form;
            //BO生成。登録者用データソースセット
            Dantai dantai = new Dantai(MiwConstants.DS_REGISTRANT);
            //フォームの内容をセッションBeanへコピー
            BeanUtils.copyProperties(dantai, dntPswRemFrm);
            //値のスペースを削除する
            deleteSpace(dantai);
            //入力値チェック
            boolean state = this.ValidationCaller(request, dantai);

            if (state) {
                //入力項目をキーに団体テーブル検索
                Dantai newDantai = new DntInfServ().getDntInfForPswRem(dantai);
                if (newDantai == null) {
                    //存在しない場合、エラーメッセージ表示
                    ActionMessages errors = new ActionMessages();
                    errors.add("0", new ActionMessage("errors.RemSearch_NoDantai"));
                    saveErrors(request, errors);
                    ret = FWD_NM_ERROR;
                } else {
                    //取得したメニュー情報をセッションに保存
                    session.setAttribute("MenuJoho", menuJoho);
                    //取得した団体データをセッションに保存
                    session.setAttribute("Dantai", newDantai);
                    //存在する場合、本人確認入力２へ
                    ret = FWD_NM_SUCCESS;
                }
            } else {
                //入力エラー
                ret = FWD_NM_ERROR;
            }
        } else {
            ret = FWD_NM_SESSION;
        }

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, Dantai dantai) {
        ActionMessages errors = new ActionMessages();

        //ＩＤのチェック
        Validate.validateRequired(dantai.getDantaiCode(), errors, "1", "団体コード");
        Validate.validateAlphabetOrNumber(dantai.getDantaiCode(), errors, "1", "団体コード");
        Validate.validateEqualLength(dantai.getDantaiCode(), MiwConstants.LENGTH_DANTAI_ID, errors, "1", "団体コード");

        //フリガナ姓のチェック
        Validate.validateRequired(dantai.getDantaiJimuTantoshaShimeiSeiKana(), errors, "2", "担当者カナ氏名の姓");
        Validate.validateKatakana(dantai.getDantaiJimuTantoshaShimeiSeiKana(), errors, "2", "担当者カナ氏名の姓");
        //フリガナ名のチェック
        Validate.validateRequired(dantai.getDantaiJimuTantoshaShimeiMeiKana(), errors, "2", "担当者カナ氏名の名");
        Validate.validateKatakana(dantai.getDantaiJimuTantoshaShimeiMeiKana(), errors, "2", "担当者カナ氏名の名");
        //フリガナのチェック
        String kana = dantai.getDantaiJimuTantoshaShimeiSeiKana() + "　" + dantai.getDantaiJimuTantoshaShimeiMeiKana();
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "2", "担当者カナ氏名");

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }

    ////////////////////////////////////////////
    //値整形メソッド
    ///////////////////////////////////////////
    /**
     * 各値のスペースを削除する
     */
    public void deleteSpace(Dantai dantai) {
        dantai.setDantaiCode(StringUtility.removeSpace(dantai.getDantaiCode()));
        dantai.setDantaiJimuTantoshaShimeiSeiKana(StringUtility.removeSpace(dantai.getDantaiJimuTantoshaShimeiSeiKana()));
        dantai.setDantaiJimuTantoshaShimeiMeiKana(StringUtility.removeSpace(dantai.getDantaiJimuTantoshaShimeiMeiKana()));
    }
}