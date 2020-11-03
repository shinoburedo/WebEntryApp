package jp.co.nii.miw.business.service.moshikomi;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.SEWException;

import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.domain.Saiban;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.beanutils.BeanUtils;

import jp.co.nii.sew.business.Validate;
import jp.co.nii.miw.business.MiwValidate;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.sew.utility.CheckUtility;

/**
 * <p>タイトル: 事前登録サービス</p>
 * <p>説明: 事前登録サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author t.yamaguchi
 */
public class JznTorokuServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public JznTorokuServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 申込者テーブルへ入力された情報をINSERTする
     * @param jznTorokuJoho
     * @throws Exception
     */
    public void insertMoshikomisha(JznToroku jznTorokuJoho) {

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            //採番からユーザＩＤの通し番号用の一件を検索しロックする
            Saiban retSaiban = boSaiban.findRetry(MiwConstants.NO_ID_USER_ID_I);

            //発行番号最新を取得し、+1する
            int genzaiNo = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;

            //シーケンスを0埋めする
            String seq = StringUtility.padLeft(Integer.toString(genzaiNo), "0", MiwConstants.LENGTH_USER_ID_SEQ);
            //接頭文字列＋シーケンスでユーザIDを作成
            String userId = retSaiban.getSettoString() + seq;

            //ユーザIDを保持
            jznTorokuJoho.setUserId(userId);

            //取得した採番情報をコピー
            BeanUtils.copyProperties(boSaiban, retSaiban);
            //発行番号最新に+1したものをセット
            boSaiban.setGenzaiNo(Integer.toString(genzaiNo));
            //処理区分をセット
            boSaiban.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //更新日付をセット
            boSaiban.setKoshinDate(sysTim.getymd1());
            //更新時間をセット
            boSaiban.setKoshinTime(sysTim.gethms1());
            //更新ユーザーＩＤをセット
            boSaiban.setKoshinUserId(userId);

            //現在番号を更新
            boSaiban.update();


            //ユーザーＩＤをセット
            boMoshikomisha.setUserId(userId);
            //申込有りフラグをセット
            boMoshikomisha.setMoshikomiAriFlg(MiwConstants.FLG_OFF);
            //メールアドレスをセット
            boMoshikomisha.setMailAddress(jznTorokuJoho.getMailAddress());
            //パスワードをセット
            boMoshikomisha.setPasswd(jznTorokuJoho.getPasswd());
            //氏名（姓）カナをセット
            boMoshikomisha.setShimeiSeiKana(jznTorokuJoho.getShimeiSeiKana());
            //氏名（名）カナをセット
            boMoshikomisha.setShimeiMeiKana(jznTorokuJoho.getShimeiMeiKana());
            //氏名（姓）をセット
            boMoshikomisha.setShimeiSei(jznTorokuJoho.getShimeiSei());
            //氏名（名）をセット
            boMoshikomisha.setShimeiMei(jznTorokuJoho.getShimeiMei());
//            //氏名（姓）ローマ字をセット
//            boMoshikomisha.setShimeiSeiRomaji(jznTorokuJoho.getShimeiSeiRomaji());
//            //氏名（名）ローマ字をセット
//            boMoshikomisha.setShimeiMeiRomaji(jznTorokuJoho.getShimeiMeiRomaji());
            //性別をセット
            boMoshikomisha.setSexCode(jznTorokuJoho.getSexCode());
            //生年月日をセット
            boMoshikomisha.setBirthday(jznTorokuJoho.getBirthdayYMD());
            //電話番号をセット
//            boMoshikomisha.setTelNo(jznTorokuJoho.getTelNo());
            boMoshikomisha.setTelNo(MiwStringUtility.getConcateWithParenthesis(jznTorokuJoho.getTelNo(), jznTorokuJoho.getExtNo()));

            //論理削除フラグをセット
            boMoshikomisha.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            //処理区分をセット
            boMoshikomisha.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
            //登録日付をセット
            boMoshikomisha.setTorokuDate(sysTim.getymd1());
            //登録時刻をセット
            boMoshikomisha.setTorokuTime(sysTim.gethms1());
            //登録ユーザーIDをセット
            boMoshikomisha.setTorokuUserId(userId);
            //更新日付をセット
            boMoshikomisha.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomisha.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomisha.setKoshinUserId(userId);

//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  ユーザーID：" + boMoshikomisha.getUserId());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  申込有フラグ：" + boMoshikomisha.getMoshikomiAriFlg());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  メールアドレス：" + boMoshikomisha.getMailAddress());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  パスワード：" + boMoshikomisha.getPasswd());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（姓）カナ：" + boMoshikomisha.getShimeiSeiKana());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（名）カナ：" + boMoshikomisha.getShimeiMeiKana());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（姓）：" + boMoshikomisha.getShimeiSei());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（名）：" + boMoshikomisha.getShimeiMei());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（姓）ローマ字：" + boMoshikomisha.getShimeiSeiRomaji());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  氏名（名）ローマ字：" + boMoshikomisha.getShimeiMeiRomaji());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  生年月日：" + boMoshikomisha.getBirthday());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  電話番号：" + boMoshikomisha.getTelNo());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  登録日付：" + boMoshikomisha.getTorokuDate());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  登録時刻：" + boMoshikomisha.getTorokuTime());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  登録ユーザーID：" + boMoshikomisha.getTorokuUserId());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  更新日付：" + boMoshikomisha.getKoshinDate());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  更新時刻：" + boMoshikomisha.getKoshinTime());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  更新ユーザーID：" + boMoshikomisha.getKoshinUserId());
//            LogGenerate.infoOutput(CLASS_NAME + ".insertMoshikomisha()  論理削除フラグ：" + boMoshikomisha.getRonriSakujoFlg());

            //事前情報テーブルへ入力された情報をINSERTする
            boMoshikomisha.create();

            //トランザクションをコミットする
            commitTransaction();
            LogGenerate.infoOutput(CLASS_NAME + "*****ID取得完了***** ID：" + boMoshikomisha.getUserId());

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".insertMoshikomisha()", e);
            //ロールバック
            rollbackTransaction();
            throw new SEWException("申込者情報登録処理で例外が発生した。", e);
        } finally {
        }
    }

    //ＩＤ取得共通の入力チェック
    public ActionMessages publicValidationCaller(HttpServletRequest request, JznToroku jznTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        //性別
        String[] listSexCode = StringUtility.getValueList(MiwConstants.DISP_SEX_CODE);
        //生年月日元号
        String[] listEraCode = StringUtility.getValueList(MiwConstants.DISP_BIRTHDAY_ERA_COD);

        //氏名姓のチェック
        Validate.validateRequired(jznTorokuJoho.getShimeiSei(), errors, "1", "氏名の姓");
        Validate.validateMojiCode1(jznTorokuJoho.getShimeiSei(), errors, "1", "氏名の姓");
        Validate.validateMaxLength(jznTorokuJoho.getShimeiSei(), MiwConstants.MAX_LEN_SHIMEI_SEI, errors, "1", "氏名の姓");

        //氏名名のチェック
        Validate.validateRequired(jznTorokuJoho.getShimeiMei(), errors, "1", "氏名の名");
        Validate.validateMojiCode1(jznTorokuJoho.getShimeiMei(), errors, "1", "氏名の名");
        Validate.validateMaxLength(jznTorokuJoho.getShimeiMei(), MiwConstants.MAX_LEN_SHIMEI_MEI, errors, "1", "氏名の名");

        //フリガナ姓のチェック
        Validate.validateRequired(jznTorokuJoho.getShimeiSeiKana(), errors, "2", "フリガナの姓");
        Validate.validateKatakana(jznTorokuJoho.getShimeiSeiKana(), errors, "2", "フリガナの姓");

        //フリガナ名のチェック
        Validate.validateRequired(jznTorokuJoho.getShimeiMeiKana(), errors, "2", "フリガナの名");
        Validate.validateKatakana(jznTorokuJoho.getShimeiMeiKana(), errors, "2", "フリガナの名");

        //フリガナは姓名合わせて桁数チェックを行う
        String kana = jznTorokuJoho.getShimeiSeiKana()  + "　" + jznTorokuJoho.getShimeiMeiKana();
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "2", "フリガナ");

        //性別のチェック
        String sexCode = jznTorokuJoho.getSexCode();
        Validate.validateRequired(sexCode, errors, "4", "性別");
        Validate.validatePermissionSelect(sexCode, listSexCode, errors, "4", "性別");

        //生年月日（元号）のチェック
        String eraCode = jznTorokuJoho.getBirthdayEra();
        Validate.validateRequired(eraCode, errors, "5", "生年月日の元号");
        Validate.validatePermissionSelect(eraCode, listEraCode, errors, "5", "生年月日の元号");
        //生年月日（年）のチェック
        String birthYyyy = jznTorokuJoho.getBirthdayYear();
        Validate.validateRequired(birthYyyy, errors, "5", "生年月日の年");
        Validate.validateNumber(birthYyyy, errors, "5", "生年月日の年");
        //生年月日（月）のチェック
        String birthMm = jznTorokuJoho.getBirthdayMonth();
        Validate.validateRequired(birthMm, errors, "5", "生年月日の月");
        Validate.validateNumber(birthMm, errors, "5", "生年月日の月");
        //生年月日（日）のチェック
        String birthDd = jznTorokuJoho.getBirthdayDay();
        Validate.validateRequired(birthDd, errors, "5", "生年月日の日");
        Validate.validateNumber(birthDd, errors, "5", "生年月日の日");

        //生年月日のエラーが無かったら
        if (!errors.get("5").hasNext()) {
            //不正日付チェック
            MiwValidate.validateWarekiDate(jznTorokuJoho.getBirthdayYMD(), errors, "5", "生年月日");
        }

        //電話番号（欄１）のチェック
        Validate.validateRequired(jznTorokuJoho.getTelNo(), errors, "6", "電話番号");
        MiwValidate.validateTelephone(jznTorokuJoho.getTelNo(), errors, "6", "電話番号");
//        Validate.validateMaxLength(jznTorokuJoho.getTelNo(), MiwConstants.MAX_LEN_TEL, errors, "6", "電話番号");

        String tel = jznTorokuJoho.getTelNo();

        if (tel.length() > 0) {
            tel = tel.replace("-", "");
            tel = tel.replace("+", "");
            tel = tel.replace(" ", "");
            if (tel.length() < MiwConstants.MIN_LEN_TEL) {
                //数字のみで９桁未満の場合
                //コンビニ決済でエラーとなるためチェックしています
                errors.add("6", new ActionMessage("errors.tellengthmin", "電話番号", MiwConstants.MIN_LEN_TEL));
            }
        }

        //内線番号のチェック
        MiwValidate.validateTelephone(jznTorokuJoho.getExtNo(), errors, "6", "内線番号");
//        Validate.validateMaxLength(jznTorokuJoho.getExtNo(), MiwConstants.MAX_LEN_EXT_TEL, errors, "6", "内線番号");

        /* 電話番号＋内線番号のチェック */
        if (CheckUtility.isBlank(jznTorokuJoho.getExtNo())) {
            Validate.validateMaxLength(jznTorokuJoho.getTelNo(), MiwConstants.MAX_LEN_TEL, errors, "6", "電話番号");
        } else {
            String telExtNo = jznTorokuJoho.getTelNo() + jznTorokuJoho.getExtNo();
            Validate.validateSumMaxLength(telExtNo, MiwConstants.MAX_LEN_TEL - 2, errors, "6", "電話番号と内線番号");
        }

        //メールアドレスのチェック
        Validate.validateRequired(jznTorokuJoho.getMailAddress(), errors, "7", "メールアドレス");
        Validate.validateEmail(jznTorokuJoho.getMailAddress(), errors, "7", "メールアドレス");
        Validate.validateMaxLength(jznTorokuJoho.getMailAddress(), MiwConstants.MAX_LEN_MAIL, errors, "7", "メールアドレス");

        //メールアドレス（確認用）のチェック
        MiwValidate.validateKakuninInp(jznTorokuJoho.getMailAddress(), jznTorokuJoho.getMailAddressKakunin(), errors, "8", "メールアドレスの確認入力");

        //パスワードのチェック
        Validate.validateRequired(jznTorokuJoho.getPasswd(), errors, "9", "パスワード");
        Validate.validateAlphabetOrNumber(jznTorokuJoho.getPasswd(), errors, "9", "パスワード");
        Validate.validateEqualLength(jznTorokuJoho.getPasswd(), MiwConstants.LEN_PASSWD, errors, "9", "パスワード");

        //パスワード（確認用）のチェック
        MiwValidate.validateKakuninInp(jznTorokuJoho.getPasswd(), jznTorokuJoho.getPasswdKakunin(), errors, "10", "パスワードの確認入力");

        return errors;
    }

    /**
     * 事前登録開始日を取得する
     * @param nendo 年度
     * @param ki 季
     * @throws Exception
     */
    public String getJizenTorokuDateFrom(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_JZN);

            ret = menuControl.getKaishiDate();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getJizenTorokuDateFrom()", e);
            throw new SEWException("事前登録開始日取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 事前登録開始時間を取得する
     * @param nendo 年度
     * @param ki 季
     * @throws Exception
     */
    public String getJizenTorokuTimeFrom(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_JZN);

            ret = menuControl.getKaishiTime();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getJizenTorokuTimeFrom()", e);
            throw new SEWException("事前登録開始時間取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 事前登録終了日を取得する
     * @param nendo 年度
     * @param ki 季
     * @throws Exception
     */
    public String getJizenTorokuDateTo(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_JZN);

            ret = menuControl.getShuryoDate();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getJizenTorokuDateTo()", e);
            throw new SEWException("事前登録終了日取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 事前登録終了時間を取得する
     * @param nendo 年度
     * @param ki 季
     * @throws Exception
     */
    public String getJizenTorokuTimeTo(String nendo, String ki) {
        String ret = "";

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_JZN);

            ret = menuControl.getShuryoTime();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getJizenTorokuTimeTo()", e);
            throw new SEWException("事前登録終了時間取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }
}
