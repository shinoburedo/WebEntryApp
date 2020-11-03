package jp.co.nii.miw.presentation.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.MiwValidate;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MenuServ;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import jp.co.nii.miw.business.service.mypage.MypLoginServ;
import jp.co.nii.miw.business.service.mypage.MypServ;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.Validate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


import jp.co.nii.sew.presentation.AbstractAction;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

/**
 * タイトル:MypPswRemInp1Act
 * 説明:MypPswRemInp1のアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class MypPswRemInp1Act extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(true);
        //返却するフォワード名
        String ret = "";

        if (request.getParameter("submit") != null) {
            //Formオブジェクトの作成
            DynaActionForm mypPswRemFrm = (DynaActionForm) form;
            //BO生成。登録者用データソースセット
            Moshikomisha bo = new Moshikomisha(MiwConstants.DS_REGISTRANT);
            //フォームの内容をセッションBeanへコピー
            BeanUtils.copyProperties(bo, mypPswRemFrm);
            //値のスペースを削除する
            deleteSpace(bo);
            //入力値チェック
            boolean state = this.ValidationCaller(request, bo);

            if (state) {
                //入力項目をキーに申込者テーブル検索
                Moshikomisha moshikomisha = new MypServ().getMoshikomishaForPswRem(bo);
                if (moshikomisha == null) {
                    //存在しない場合、エラーメッセージ表示
                    ActionMessages errors = new ActionMessages();
                    errors.add("0", new ActionMessage("errors.RemSearch_NoMoshikomisha"));
                    saveErrors(request, errors);
                    ret = FWD_NM_ERROR;
                } else {
                    //年度・回数取得
                    MenuControl menuControl = new MenuServ().selectNendoKaisu();
                    String nendo = menuControl.getNendo();
                    String kaisu = menuControl.getKaisu();
                    //申込検索
                    Moshikomi moshikomi = new Moshikomi(MiwConstants.DS_REGISTRANT)
                            .findForUserId(nendo, kaisu, moshikomisha.getUserId());
                    //マイページ情報取得
                    MypageJoho mypageJoho = new MypageJoho();
                    new MypLoginServ().getMypageKikan(nendo, kaisu, mypageJoho, moshikomi);
                    
                    if (mypageJoho.getIsKikanLogin()) {
                        //取得したマイページ情報をセッションに保存
                        session.setAttribute("MypageJoho", mypageJoho);
                        //取得した申込者データをセッションに保存
                        session.setAttribute("Moshikomisha", moshikomisha);
                        //存在する場合、本人確認入力２へ
                        ret = FWD_NM_SUCCESS;                        
                    } else {
                        //マイページログイン期間外エラー
                        ret = FWD_NM_KIKAN;
                    }
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

    private boolean ValidationCaller(HttpServletRequest request, Moshikomisha moshikomisha) {
        ActionMessages errors = new ActionMessages();

        //生年月日元号
        String[] listEraCode = StringUtility.getValueList(MiwConstants.DISP_BIRTHDAY_ERA_COD);

        //ＩＤのチェック
        Validate.validateRequired(moshikomisha.getUserId(), errors, "1", "ＩＤ");
        Validate.validateAlphabetOrNumber(moshikomisha.getUserId(), errors, "1", "ＩＤ");
        Validate.validateEqualLength(moshikomisha.getUserId(), MiwConstants.LENGTH_USER_ID, errors, "1", "ＩＤ");

        //生年月日（年号）のチェック
        Validate.validateRequired(moshikomisha.getBirthdayEra(), errors, "2", "生年月日の年号");
        Validate.validatePermissionSelect(moshikomisha.getBirthdayEra(), listEraCode, errors, "2", "生年月日の元号");
        //生年月日（年）のチェック
        Validate.validateRequired(moshikomisha.getBirthdayYy(), errors, "2", "生年月日の年");
        Validate.validateNumber(moshikomisha.getBirthdayYy(), errors, "2", "生年月日の年");
        //生年月日（月）のチェック
        Validate.validateRequired(moshikomisha.getBirthdayMm(), errors, "2", "生年月日の月");
        Validate.validateNumber(moshikomisha.getBirthdayMm(), errors, "2", "生年月日の月");
        //生年月日（日）のチェック
        Validate.validateRequired(moshikomisha.getBirthdayDd(), errors, "2", "生年月日の日");
        Validate.validateNumber(moshikomisha.getBirthdayDd(), errors, "2", "生年月日の日");
        //生年月日のエラーが無かったら
        if (!errors.get("2").hasNext()) {
            //生年月日の不正日付チェック
            String birthYMD =moshikomisha.getBirthdayEra()
                + StringUtility.padLeft(moshikomisha.getBirthdayYy(), "0", 2)
                + StringUtility.padLeft(moshikomisha.getBirthdayMm(), "0", 2)
                + StringUtility.padLeft(moshikomisha.getBirthdayDd(), "0", 2);
            MiwValidate.validateWarekiDate(birthYMD, errors, "2", "生年月日");
        }

        //フリガナ姓のチェック
        Validate.validateRequired(moshikomisha.getShimeiSeiKana(), errors, "3", "カナ氏名の姓");
        Validate.validateKatakana(moshikomisha.getShimeiSeiKana(), errors, "3", "カナ氏名の姓");
        //フリガナ名のチェック
        Validate.validateRequired(moshikomisha.getShimeiMeiKana(), errors, "3", "カナ氏名の名");
        Validate.validateKatakana(moshikomisha.getShimeiMeiKana(), errors, "3", "カナ氏名の名");
        //フリガナの最大桁数チェック
        String kana = moshikomisha.getShimeiSeiKana() + "　" + moshikomisha.getShimeiMeiKana();
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "3", "カナ氏名");

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
    public void deleteSpace(Moshikomisha moshikomisha) {
        moshikomisha.setUserId(StringUtility.removeSpace(moshikomisha.getUserId()));
        moshikomisha.setBirthdayYy(StringUtility.removeSpace(moshikomisha.getBirthdayYy()));
        moshikomisha.setBirthdayMm(StringUtility.removeSpace(moshikomisha.getBirthdayMm()));
        moshikomisha.setBirthdayDd(StringUtility.removeSpace(moshikomisha.getBirthdayDd()));
        moshikomisha.setShimeiSeiKana(StringUtility.removeSpace(moshikomisha.getShimeiSeiKana()));
        moshikomisha.setShimeiMeiKana(StringUtility.removeSpace(moshikomisha.getShimeiMeiKana()));
    }
}