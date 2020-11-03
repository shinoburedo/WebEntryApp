package jp.co.nii.miw.business.service.mypage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.MiwValidate;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.domain.Kaijo;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>タイトル: 申込サービス</p>
 * <p>説明: 申込サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public class MypMskUpdServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    /**
     * DB接続時のユーザーを決定する
     */
    public MypMskUpdServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 申込内容変更の入力チェックを行う
     * @param errors
     * @param moshikomiJohoInp
     * @param nendo
     * @param ki
     * @return
     * @throws Exception 
     */
    public boolean publicValidationCaller(ActionMessages errors, MypMskInf moshikomiJohoInp, String nendo, String ki) throws Exception {

        //受験科目
        String[] listKyu = StringUtility.getValueList(MiwConstants.DISP_JUKEN_KAMOKU);
        //性別の不正文字チェックのためにvalue値を配列に入れる
        String[] listSexCode = StringUtility.getValueList(MiwConstants.DISP_SEX_CODE);
        //生年月日元号
        String[] listEraCode = StringUtility.getValueList(MiwConstants.DISP_BIRTHDAY_ERA_COD);
        //都道府県
        String[] listTodofuken = StringUtility.getValueList(MiwConstants.DISP_TODOFUKEN_CODE);
        // 医療事務の受講経験
        String[] listJukoKeikenCode = StringUtility.getValueList(MiwConstants.DISP_JUKO_KEIKEN_CODE);
        // 医療事務の実務経験
        String[] listJitsumuKeikenCode = StringUtility.getValueList(MiwConstants.DISP_JITSUMU_KEIKEN_CODE);
        //質問コード
        String[] listQuestion = StringUtility.getValueList(MiwConstants.DISP_PASSWD_QUESTION_COD);

        //個人団体区分のチェック
//        Validate.validateRequired(moshikomiJohoInp.getKojinDantaiKbn(), errors, "27", "申込区分");
//        Validate.validatePermissionSelect(moshikomiJohoInp.getKojinDantaiKbn(), listKojinDantaiKbn, errors, "27", "申込区分");

        //受験科目のチェック
        String kamoku = moshikomiJohoInp.getEventCode();
        Validate.validateRequired(kamoku, errors, "1", "受験科目");
        Validate.validatePermissionSelect(kamoku, listKyu, errors, "1", "受験科目");

        // 受験希望地のチェック
        String shikenchiCode1 = moshikomiJohoInp.getShikenchiCode();

        if (Validate.validateRequired(shikenchiCode1, errors, "2", "受験希望地")) {
            //試験コード検索
            Shikenchi shikenchi = new Shikenchi(DATA_SOURCE_NAME).find(moshikomiJohoInp.getNendo(), moshikomiJohoInp.getKaisu(),MiwConstants.EVENT_CODE_ALL, shikenchiCode1);
            if (shikenchi == null) {
                errors.add("2", new ActionMessage("errors.permission", "受験希望地"));
            }
        }

        //氏名姓のチェック
        Validate.validateRequired(moshikomiJohoInp.getShimeiSei(), errors, "5", "氏名の姓");
        Validate.validateMojiCode1(moshikomiJohoInp.getShimeiSei(), errors, "5", "氏名の姓");
        Validate.validateMaxLength(moshikomiJohoInp.getShimeiSei(), MiwConstants.MAX_LEN_SHIMEI_SEI, errors, "5", "氏名の姓");

        //氏名名のチェック
        Validate.validateRequired(moshikomiJohoInp.getShimeiMei(), errors, "5", "氏名の名");
        Validate.validateMojiCode1(moshikomiJohoInp.getShimeiMei(), errors, "5", "氏名の名");
        Validate.validateMaxLength(moshikomiJohoInp.getShimeiMei(), MiwConstants.MAX_LEN_SHIMEI_MEI, errors, "5", "氏名の名");

        // 漢字氏名の最大桁数チェック
//        String shimei = moshikomiJohoInp.getShimeiSei() + moshikomiJohoInp.getShimeiMei();
//        Validate.validateMaxLength(shimei, MiwConstants.MAX_LEN_SHIMEI, errors, "5", "氏名");

        //フリガナ姓のチェック
        Validate.validateRequired(moshikomiJohoInp.getShimeiSeiKana(), errors, "6", "フリガナの姓");
        Validate.validateKatakana(moshikomiJohoInp.getShimeiSeiKana(), errors, "6", "フリガナの姓");
//        Validate.validateMaxLength(moshikomiJohoInp.getShimeiSeiKana(), MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "6", "フリガナの姓");

        //フリガナ名のチェック
        Validate.validateRequired(moshikomiJohoInp.getShimeiMeiKana(), errors, "6", "フリガナの名");
        Validate.validateKatakana(moshikomiJohoInp.getShimeiMeiKana(), errors, "6", "フリガナの名");
//        Validate.validateMaxLength(moshikomiJohoInp.getShimeiMeiKana(), MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "6", "フリガナの名");

        //フリガナの最大桁数チェック
        String kana = moshikomiJohoInp.getShimeiSeiKana() + "　" + moshikomiJohoInp.getShimeiMeiKana();
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "6", "フリガナ");

        //性別のチェック
        Validate.validateRequired(moshikomiJohoInp.getSexCode(), errors, "8", "性別");
        Validate.validatePermissionSelect(moshikomiJohoInp.getSexCode(), listSexCode, errors, "8", "性別");

        //生年月日
        String eraCode = moshikomiJohoInp.getBirthdayEra();
        String birthYy = moshikomiJohoInp.getBirthdayYear();
        String birthMm = moshikomiJohoInp.getBirthdayMonth();
        String birthDd = moshikomiJohoInp.getBirthdayDay();

        //元号
        Validate.validateRequired(eraCode, errors, "9", "生年月日の元号");
        Validate.validatePermissionSelect(eraCode, listEraCode, errors, "9", "生年月日の元号");
        //年
        Validate.validateRequired(birthYy, errors, "9", "生年月日の年");
        Validate.validateNumber(birthYy, errors, "9", "生年月日の年");

        //月
        Validate.validateRequired(birthMm, errors, "9", "生年月日の月");
        Validate.validateNumber(birthMm, errors, "9", "生年月日の月");

        //日
        Validate.validateRequired(birthDd, errors, "9", "生年月日の日");
        Validate.validateNumber(birthDd, errors, "9", "生年月日の日");

        //生年月日のエラーが無かったら
        if (!errors.get("9").hasNext()) {
            //生年月日の不正日付チェック
            String birthYMD = moshikomiJohoInp.getBirthday();
            MiwValidate.validateWarekiDate(birthYMD, errors, "9", "生年月日");
        }

        //郵便番号の前３桁のチェック
        Validate.validateRequired(moshikomiJohoInp.getYubinNo1(), errors, "10", "郵便番号の前３桁");
        Validate.validateNumber(moshikomiJohoInp.getYubinNo1(), errors, "10", "郵便番号の前３桁");
        Validate.validateEqualLength(moshikomiJohoInp.getYubinNo1(), MiwConstants.LENGTH_YUBIN_1, errors, "10", "郵便番号の前３桁");
        //郵便番号の後４桁）のチェック
        Validate.validateRequired(moshikomiJohoInp.getYubinNo2(), errors, "10", "郵便番号の後４桁");
        Validate.validateNumber(moshikomiJohoInp.getYubinNo2(), errors, "10", "郵便番号の後４桁");
        Validate.validateEqualLength(moshikomiJohoInp.getYubinNo2(), MiwConstants.LENGTH_YUBIN_2, errors, "10", "郵便番号の後４桁");

        String todofuken = moshikomiJohoInp.getTodofuken();
        String jusho1 = moshikomiJohoInp.getJusho1();
        String jusho2 = moshikomiJohoInp.getJusho2();
        String jusho3 = moshikomiJohoInp.getJusho3();
        String jusho4 = moshikomiJohoInp.getJusho4();

        Validate.validateRequired(todofuken, errors, "11", "都道府県");
        Validate.validatePermissionSelect(todofuken, listTodofuken, errors, "11", "都道府県");
        Validate.validateMojiCode3(jusho1, errors, "11", "市区町村・郡");
        Validate.validateRequired(jusho1, errors, "11", "市区町村・郡");
//        Validate.validateMaxLength(jusho1, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "11", "市区町村・郡");
        Validate.validateMojiCode3(jusho2, errors, "11", "町名");
        Validate.validateRequired(jusho2, errors, "11", "町名");
//        Validate.validateMaxLength(jusho2, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "11", "町名");
        Validate.validateMojiCode3(jusho3, errors, "11", "丁目・番地");
        Validate.validateRequired(jusho3, errors, "11", "丁目・番地");
//        Validate.validateMaxLength(jusho3, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "11", "丁目・番地");
        Validate.validateMojiCode3(jusho4, errors, "11", "ビル・建物名、部屋番号　など");
//        Validate.validateMaxLength(jusho4, MiwConstants.LENGTH_JUSHO_KOKUNAI_ETC, errors, "11", "ビル・建物名、部屋番号　など");

        String jusho = todofuken + jusho1 + jusho2 + jusho3 + jusho4;
        Validate.validateMaxLength(jusho, MiwConstants.LENGTH_JUSHO_KOKUNAI_ALL, errors, "11", "住所");

        //ＴＥＬのチェック
        MiwValidate.validateTelephone(moshikomiJohoInp.getTelNo(), errors, "12", "ＴＥＬ");
//        Validate.validateMaxLength(moshikomiJohoInp.getTelNo(), MiwConstants.MAX_LEN_TEL, errors, "12", "ＴＥＬ");
//        Validate.validateMinLength(moshikomiJohoInp.getTelNo(), MiwConstants.MIN_LEN_TEL, errors, "12", "ＴＥＬ");

        //内線番号のチェック
        MiwValidate.validateTelephone(moshikomiJohoInp.getExtNo(), errors, "12", "内線番号");
//        Validate.validateMaxLength(moshikomiJohoInp.getExtNo(), MiwConstants.MAX_LEN_EXT_TEL, errors, "12", "内線番号");
        
        /* 電話番号＋内線番号のチェック */
        if (CheckUtility.isBlank(moshikomiJohoInp.getExtNo())) {
            Validate.validateMaxLength(moshikomiJohoInp.getTelNo(), MiwConstants.MAX_LEN_TEL, errors, "12", "ＴＥＬ");
        } else {
            String telExtNo = moshikomiJohoInp.getTelNo() + moshikomiJohoInp.getExtNo();
            Validate.validateSumMaxLength(telExtNo, MiwConstants.MAX_LEN_TEL - 2, errors, "12", "ＴＥＬと内線番号");
        }

        //携帯電話
        MiwValidate.validateTelephone(moshikomiJohoInp.getCellphoneNo(), errors, "12", "携帯電話");
        Validate.validateMaxLength(moshikomiJohoInp.getCellphoneNo(), MiwConstants.MAX_LEN_TEL, errors, "12", "携帯電話");
//        Validate.validateMinLength(moshikomiJohoInp.getCellphoneNo(), MiwConstants.MIN_LEN_TEL, errors, "12", "携帯電話");

        //ＴＥＬ、携帯電話どちらか必須チェック
        MiwValidate.validateRequiredTel(moshikomiJohoInp.getTelNo() + moshikomiJohoInp.getCellphoneNo(), errors, "12", "連絡先");

        //メールアドレスのチェック
        Validate.validateRequired(moshikomiJohoInp.getMailAddress(), errors, "14", "メールアドレス");
        Validate.validateEmail(moshikomiJohoInp.getMailAddress(), errors, "14", "メールアドレス");
        Validate.validateMaxLength(moshikomiJohoInp.getMailAddress(), MiwConstants.MAX_LEN_MAIL, errors, "14", "メールアドレス");

        //メールアドレス（確認用）のチェック
        MiwValidate.validateKakuninInp(moshikomiJohoInp.getMailAddress(), moshikomiJohoInp.getMailAddressKakunin(), errors, "14", "メールアドレスの確認入力");

        //医療事務の受講経験
        String jukoKeikenCode = moshikomiJohoInp.getJukoKeiken();
        Validate.validateRequired(jukoKeikenCode, errors, "20", "医療事務の受講経験");
        Validate.validatePermissionSelect(jukoKeikenCode, listJukoKeikenCode, errors, "20", "医療事務の受講経験");

        //医療事務の実務経験
        String jitsumuKeikenCode = moshikomiJohoInp.getJitsumuKeiken();
        Validate.validateRequired(jitsumuKeikenCode, errors, "21", "医療事務の実務経験");
        Validate.validatePermissionSelect(jitsumuKeikenCode, listJitsumuKeikenCode, errors, "21", "医療事務の実務経験");

        /* ご本人様確認用の質問 */
        Validate.validateRequired(moshikomiJohoInp.getPasswdQuestionCode1(), errors, "23", "ご本人様確認用の質問");
        Validate.validatePermissionSelect(moshikomiJohoInp.getPasswdQuestionCode1(), listQuestion, errors, "23", "ご本人様確認用の質問");
        /* ご本人様確認用の回答 */
        Validate.validateRequired(moshikomiJohoInp.getPasswdAnswer1(), errors, "24", "ご本人様確認用の質問の回答");
        Validate.validateMojiCode4(moshikomiJohoInp.getPasswdAnswer1(), errors, "24", "ご本人様確認用の質問の回答");
        Validate.validateMaxLength(moshikomiJohoInp.getPasswdAnswer1(), MiwConstants.MAX_LEN_PASSWD_ANSWER, errors, "24", "ご本人様確認用の質問の回答");

        if (!errors.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 画像の入力チェックを行う
     * @param errors
     * @param moshikomiJohoInp
     * @param nendo
     * @param ki
     * @return
     * @throws Exception 
     */
    public boolean publicPicValidationCaller(ActionMessages errors, FormFile fileUp) throws IOException {

        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        
        //ファイル存在チェック
        String ret = mskTorokuServ.checkFileSonzai(fileUp);
        if (!"".equals(ret)) {
            errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(ret));
        } else {

            //ファイルのサイズチェック（バイト）
            ret = mskTorokuServ.checkFileSize(fileUp, MiwConstants.MAX_PHOTO_FILE_SIZE);
            if (!"".equals(ret)) {
                errors.add(ActionMessages.GLOBAL_MESSAGE,
                        new ActionMessage(ret));
            } else {
                //ファイルの拡張子チェック
                ret = mskTorokuServ.checkFileSuffix(fileUp, MiwConstants.PHOTO_FILE_FIXED_SUFFIX);
                if (!"".equals(ret)) {
                    errors.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage(ret));
                } else {
                    //ファイルの上限サイズチェック（ピクセル）
                    ret = mskTorokuServ.checkHeightWidth(fileUp, MiwConstants.MAX_PHOTO_HEIGHT, MiwConstants.MAX_PHOTO_WIDTH, MiwConstants.MIN_PHOTO_HEIGHT, MiwConstants.MIN_PHOTO_WIDTH);
                    if (!"".equals(ret)) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage(ret));
                    }
                }
            }
        }

        if (!errors.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    /**
     * 改行コードをブランクに置き換えた文字列を取得する
     * @param value
     * @return 
     */
    private static String getNewLineReplaceBlank(String value) {
        String retStr = value;
        for (int i = 0; i < MiwConstants.NEW_LINE_CODES.length; i++) {
            retStr = retStr.replace(MiwConstants.NEW_LINE_CODES[i], "");
        }
        return retStr;
    }

    /**
     * LabelValueBeanのリストからvalue値の配列を返す
     * @param lvlist LabelValueBeanのリスト
     * @param value
     * @return 
     */
    private String[] getValueList(List<LabelValueBean> lvlist) {
        String[] valueList = new String[lvlist.size()];
        for (int i = 0; i < valueList.length; i++) {
            valueList[i] = lvlist.get(i).getValue();
        }
        return valueList;
    }

    /**
     * １次試験受験地、または２次試験受験地の情報を取得する
     * @param nendo 年度
     * @param ki 季
     * @param ichiNijiKbn １次２次区分
     * @param shikenchiCode 開催地区コード
     * @return
     * @throws Exception 
     */
    public Shikenchi getShikenchiJoho(String nendo, String ki, String ichiNijiKbn, String shikenchiCode) throws Exception {
        //boオブジェクト作成
        Shikenchi bo = new Shikenchi(DATA_SOURCE_NAME);

        //開催地区情報を取得
        return bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, ichiNijiKbn, shikenchiCode);

    }

//    /**
//     * 学校コードより学校情報を取得する
//     * @param gakkoCode
//     * @throws Exception
//     */
//    public Gakko getGakkoJoho(String gakkoCode) {
//
//        Gakko bo = new Gakko(DATA_SOURCE_NAME);
//
//        //学校コードで検索
//        return bo.findLike(gakkoCode);
//    }
    /**
     * 入力した情報が、登録されている情報から変更があるかチェックする
     * １箇所でも変更箇所があればtrue
     * 変更が無い場合false
     * @param moshikomiJoho
     * @param moshikomiJohoInp
     * @return
     * @throws Exception 
     */
    public boolean checkUpdPoint(MypMskInf moshikomiJoho, MypMskInf moshikomiJohoInp) throws Exception {

        // 受験科目
        if (!moshikomiJoho.getEventCode().equals(moshikomiJohoInp.getEventCode())) {
            return true;
        }

        // 受験希望地
        if (!moshikomiJoho.getShikenchiCode().equals(moshikomiJohoInp.getShikenchiCode())) {
            return true;
        }

        // 漢字氏名（姓）
        if (!moshikomiJoho.getShimeiSei().equals(moshikomiJohoInp.getShimeiSei())) {
            return true;
        }

        // 漢字氏名（名）
        if (!moshikomiJoho.getShimeiMei().equals(moshikomiJohoInp.getShimeiMei())) {
            return true;
        }

        // カナ氏名（姓）
        if (!moshikomiJoho.getShimeiSeiKana().equals(moshikomiJohoInp.getShimeiSeiKana())) {
            return true;
        }

        // カナ氏名（名）
        if (!moshikomiJoho.getShimeiMeiKana().equals(moshikomiJohoInp.getShimeiMeiKana())) {
            return true;
        }

        // 性別
        if (!moshikomiJoho.getSexCode().equals(moshikomiJohoInp.getSexCode())) {
            return true;
        }

        // 生年月日（元号）
        if (!moshikomiJoho.getBirthdayEra().equals(moshikomiJohoInp.getBirthdayEra())) {
            return true;
        }

        // 生年月日（年）
        if (!moshikomiJoho.getBirthdayYear().equals(moshikomiJohoInp.getBirthdayYear())) {
            return true;
        }

        // 生年月日（月）
        if (!moshikomiJoho.getBirthdayMonth().equals(moshikomiJohoInp.getBirthdayMonth())) {
            return true;
        }

        // 生年月日（日）
        if (!moshikomiJoho.getBirthdayDay().equals(moshikomiJohoInp.getBirthdayDay())) {
            return true;
        }

        // 郵便番号（3桁）
        if (!moshikomiJoho.getYubinNo1().equals(moshikomiJohoInp.getYubinNo1())) {
            return true;
        }

        // 郵便番号（4桁）
        if (!moshikomiJoho.getYubinNo2().equals(moshikomiJohoInp.getYubinNo2())) {
            return true;
        }

        // 都道府県コード
        if (!moshikomiJoho.getTodofuken().equals(moshikomiJohoInp.getTodofuken())) {
            return true;
        }

        // 住所
        if (!moshikomiJoho.getJusho().equals(moshikomiJohoInp.getJusho())) {
            return true;
        }

        // 連絡先
        if (!moshikomiJoho.getTelNo().equals(moshikomiJohoInp.getTelNo())) {
            return true;
        }
        
        // 内線番号
        if (!moshikomiJoho.getExtNo().equals(moshikomiJohoInp.getExtNo())) {
            return true;
        }
        
        // 携帯電話
        if (!moshikomiJoho.getCellphoneNo().equals(moshikomiJohoInp.getCellphoneNo())) {
            return true;
        }

        // メールアドレス
        if (!moshikomiJoho.getMailAddress().equals(moshikomiJohoInp.getMailAddress())) {
            return true;
        }

        // 医療事務の受講経験
        if (!moshikomiJoho.getJukoKeiken().equals(moshikomiJohoInp.getJukoKeiken())) {
            return true;
        }

        // 医療事務の実務経験
        if (!moshikomiJoho.getJitsumuKeiken().equals(moshikomiJohoInp.getJitsumuKeiken())) {
            return true;
        }

        // 本人確認用質問
        if (!moshikomiJoho.getPasswdQuestionCode1().equals(moshikomiJohoInp.getPasswdQuestionCode1())) {
            return true;
        }

        // 本人確認用質問の答え
        if (!moshikomiJoho.getPasswdAnswer1().equals(moshikomiJohoInp.getPasswdAnswer1())) {
            return true;
        }
        return false;
    }

    public void updateMoshikomiJoho(String nendo, String kaisu, MypMskInf moshikomiJohoInp, MypMskInf moshikomiJoho, String userId) throws Exception {

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();

            //SELECT用boオブジェクト作成
            Moshikomisha boMoshikomishaSelect = new Moshikomisha(DATA_SOURCE_NAME);
            Moshikomi boMoshikomiSelect = new Moshikomi(DATA_SOURCE_NAME);

            //更新するレコードをロック
            boMoshikomishaSelect = boMoshikomishaSelect.findRetry(moshikomiJohoInp.getUserId());
            boMoshikomiSelect = boMoshikomiSelect.findRetry(moshikomiJohoInp.getMoshikomiUketsukeNo(), nendo, kaisu, moshikomiJoho.getEventCode());

            //UPDATE用boオブジェクト作成
            Moshikomisha boMoshikomishaUpdate = new Moshikomisha(DATA_SOURCE_NAME);
            Moshikomi boMoshikomiUpdate = new Moshikomi(DATA_SOURCE_NAME);

            //取得した情報をコピー
            BeanUtils.copyProperties(boMoshikomishaUpdate, boMoshikomishaSelect);
            BeanUtils.copyProperties(boMoshikomiUpdate, boMoshikomiSelect);


            //氏名（姓）をセット
            boMoshikomishaUpdate.setShimeiSei(moshikomiJohoInp.getShimeiSei());
            //氏名（名）をセット
            boMoshikomishaUpdate.setShimeiMei(moshikomiJohoInp.getShimeiMei());
            //氏名（姓）カナをセット
            boMoshikomishaUpdate.setShimeiSeiKana(moshikomiJohoInp.getShimeiSeiKana());
            //氏名（名）カナをセット
            boMoshikomishaUpdate.setShimeiMeiKana(moshikomiJohoInp.getShimeiMeiKana());
            //性別をセット
            boMoshikomishaUpdate.setSexCode(moshikomiJohoInp.getSexCode());
            //生年月日をセット
            boMoshikomishaUpdate.setBirthday(moshikomiJohoInp.getBirthday());
            //郵便番号をセット
            boMoshikomishaUpdate.setYubinNo(moshikomiJohoInp.getYubinNo());
            //住所をセット
            boMoshikomishaUpdate.setJusho(moshikomiJohoInp.getJusho());
            //TELをセット
            boMoshikomishaUpdate.setTelNo(MiwStringUtility.getConcateWithParenthesis(moshikomiJohoInp.getTelNo(), moshikomiJohoInp.getExtNo()));
            //携帯電話をセット
            boMoshikomishaUpdate.setCellphoneNo(moshikomiJohoInp.getCellphoneNo());
            //メールアドレスをセット
            boMoshikomishaUpdate.setMailAddress(moshikomiJohoInp.getMailAddress());
            //本人様確認用質問
            boMoshikomishaUpdate.setPasswdQuestionCode1(moshikomiJohoInp.getPasswdQuestionCode1());
            //本人様確認用回答
            boMoshikomishaUpdate.setPasswdAnswer1(moshikomiJohoInp.getPasswdAnswer1());
            //論理削除フラグをセット
            boMoshikomishaUpdate.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            //処理区分をセット
            boMoshikomishaUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //更新日付をセット
            boMoshikomishaUpdate.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomishaUpdate.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomishaUpdate.setKoshinUserId(userId);


            //イベントコードをセット
            boMoshikomiUpdate.setEventCode(moshikomiJohoInp.getEventCode());
            //試験地をセット
            boMoshikomiUpdate.setShikenchiCode(moshikomiJohoInp.getShikenchiCode());
            //医療事務の受講経験をセット
            boMoshikomiUpdate.setJukoKeiken(moshikomiJohoInp.getJukoKeiken());
            //医療事務の実務経験をセット
            boMoshikomiUpdate.setJitsumuKeiken(moshikomiJohoInp.getJitsumuKeiken());

            //画像更新がある場合
            if (MiwConstants.FLG_ON.equals(moshikomiJohoInp.getGazoUpdFlg())) {
                //画像補正依頼区分を「補正済み」に
                boMoshikomiUpdate.setHoseiIraiKbn(moshikomiJohoInp.getHoseiIraiKbn());
                //補正対応日をセット
                boMoshikomiUpdate.setHoseiTaioBi(moshikomiJohoInp.getHoseiTaioBi());
                //補正対応時刻をセット
                boMoshikomiUpdate.setHoseiTaioTime(moshikomiJohoInp.getHoseiTaioTime());
            }

//            //受付完了で更新しようとするデータの申込完了日時がブランクの場合
//            //現在の日時で更新する
//            if (moshikomiJohoInp.getTetsudukiJokyoKbn().equals(MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO)
//                    && CheckUtility.isBlank(boMoshikomiSelect.getMoshikomiFinishBi())) {
//                boMoshikomiUpdate.setMoshikomiFinishBi(sysTim.getymd1());
//                boMoshikomiUpdate.setMoshikomiFinishTime(sysTim.gethms1());
//            }

            //論理削除フラグをセット
            boMoshikomiUpdate.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            //処理区分をセット
            boMoshikomiUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //更新日付をセット
            boMoshikomiUpdate.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomiUpdate.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomiUpdate.setKoshinUserId(userId);

            //情報を更新する
            boMoshikomishaUpdate.update();
            boMoshikomiUpdate.update(boMoshikomiSelect.getEventCode());

            //トランザクションをコミットする
            commitTransaction();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".updateMoshikomiJoho()", e, " *****申込情報更新処理失敗***** ユーザーID : " + moshikomiJohoInp.getUserId() +
                    " カナ氏名 : " + moshikomiJohoInp.getShimeiSeiKana() + " " + moshikomiJohoInp.getShimeiMeiKana() +
                    " 電話番号 : " + moshikomiJohoInp.getTelNo() + " 申込受付番号 : " + moshikomiJohoInp.getMoshikomiUketsukeNo());
            //ロールバック
            rollbackTransaction();
            throw new SEWException("申込者情報更新処理で例外が発生した。", e);
        } finally {
        }
    }

    /**
     * 申込取り消しUPDATEする
     */
    public void cancelMoshikomi(MypMskInf mskTorokuJoho, String userId) {
        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();

            //SELECT用boオブジェクト作成
            Moshikomisha boMoshikomishaSelect = new Moshikomisha(DATA_SOURCE_NAME);
            Moshikomi boMoshikomiSelect = new Moshikomi(DATA_SOURCE_NAME);

            //更新するレコードをロック
            boMoshikomishaSelect = boMoshikomishaSelect.findRetry(mskTorokuJoho.getUserId());
            boMoshikomiSelect = boMoshikomiSelect.findRetry(mskTorokuJoho.getMoshikomiUketsukeNo(), mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), mskTorokuJoho.getEventCode());

            //UPDATE用boオブジェクト作成
            Moshikomisha boMoshikomishaUpdate = new Moshikomisha(DATA_SOURCE_NAME);
            Moshikomi boMoshikomiUpdate = new Moshikomi(DATA_SOURCE_NAME);

            //取得した情報をコピー
            BeanUtils.copyProperties(boMoshikomishaUpdate, boMoshikomishaSelect);
            BeanUtils.copyProperties(boMoshikomiUpdate, boMoshikomiSelect);

            //処理区分をセット
            boMoshikomiUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //手続状況区分
            boMoshikomiUpdate.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_TORIKESHI); //受付取消 
            //更新日付をセット
            boMoshikomiUpdate.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomiUpdate.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomiUpdate.setKoshinUserId(userId);

            //処理区分をセット
            boMoshikomishaUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //申込ありフラグ
            boMoshikomishaUpdate.setMoshikomiAriFlg(MiwConstants.FLG_OFF);
            //更新日付をセット
            boMoshikomishaUpdate.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomishaUpdate.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomishaUpdate.setKoshinUserId(userId);

            //情報を更新する
            boMoshikomishaUpdate.update();
            boMoshikomiUpdate.update(boMoshikomiSelect.getEventCode());

            //トランザクションをコミットする
            commitTransaction();
            
            //更新情報をコピーする
            BeanUtils.copyProperties(mskTorokuJoho, boMoshikomiUpdate);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".cancelMoshikomi()", e, " ****申込取消処理失敗****  ユーザID : " + mskTorokuJoho.getUserId() +
                    " カナ氏名 : " + mskTorokuJoho.getShimeiSeiKana() + " " + mskTorokuJoho.getShimeiMeiKana() + 
                    " 電話番号 : " + mskTorokuJoho.getTelNo() + " 申込受付番号 : " + mskTorokuJoho.getMoshikomiUketsukeNo());
            //ロールバック
            rollbackTransaction();
            throw new SEWException("団体申込者受付取消処理で例外が発生した。", e);
        } finally {
        }
    }

    /**
     * 団体コードから団体情報を取得する
     * @param dantaiCode
     * @return
     * @throws Exception 
     */
    public MoshikomiDantai getMoshikomiDantaiJoho(String dantaiCode) throws Exception {
        //boオブジェクト作成
        MoshikomiDantai bo = new MoshikomiDantai(DATA_SOURCE_NAME);

        //申込団体情報を取得
        return bo.find(dantaiCode);
    }

    /**
     * 準会場コードから開催会場情報を取得する
     * @param dantaiCode
     * @return
     * @throws Exception 
     */
    public Kaijo getJunKaijoJoho(String nendo, String ki, String junkaijoCode) throws Exception {
        //boオブジェクト作成
        Kaijo bo = new Kaijo(DATA_SOURCE_NAME);
        //開催地区コードを取得
        String shikenchiCode = junkaijoCode.substring(0, 3);
        //準会場情報を取得
        return bo.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, shikenchiCode, junkaijoCode);
    }

    /**
     * 更新箇所のにフォントを指定して返す
     * @param str
     * @param updStr
     * @return 
     */
    public static String getUpdStringConvert(String str, String updStr) {
        if (!str.equals(updStr)) {
            if (CheckUtility.isBlank(updStr)) {
                return "<font color='#ff4500'>-</font>";
            } else {
                return "<font color='#ff4500'>" + updStr + "</font>";
            }
        } else {
            if (CheckUtility.isBlank(updStr)) {
                return "-";
            } else {
                return updStr;
            }
        }
    }

    /**
     * 申込情報の画像補正関連項目のみ更新する
     * @param moshikomiJoho 
     */
    public void updateHoseiIraiSts(MypMskInf moshikomiJohoInp) {
        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();

            //SELECT用boオブジェクト作成
            Moshikomi boMoshikomiSelect = new Moshikomi(DATA_SOURCE_NAME);

            //更新するレコードをロック
            boMoshikomiSelect = boMoshikomiSelect.findRetry(moshikomiJohoInp.getMoshikomiUketsukeNo(), moshikomiJohoInp.getNendo(), moshikomiJohoInp.getKaisu(), moshikomiJohoInp.getEventCode());

            //UPDATE用boオブジェクト作成
            Moshikomi boMoshikomiUpdate = new Moshikomi(DATA_SOURCE_NAME);

            //取得した情報をコピー
            BeanUtils.copyProperties(boMoshikomiUpdate, boMoshikomiSelect);


            //画像更新がある場合
            if (MiwConstants.FLG_ON.equals(moshikomiJohoInp.getGazoUpdFlg())) {
                //画像補正依頼区分を「補正済み」に
                boMoshikomiUpdate.setHoseiIraiKbn(moshikomiJohoInp.getHoseiIraiKbn());
                //補正対応日をセット
                boMoshikomiUpdate.setHoseiTaioBi(moshikomiJohoInp.getHoseiTaioBi());
                //補正対応時刻をセット
                boMoshikomiUpdate.setHoseiTaioTime(moshikomiJohoInp.getHoseiTaioTime());
            }

            //受付完了で更新しようとするデータの申込完了日時がブランクの場合
            //現在の日時で更新する
            if (moshikomiJohoInp.getTetsudukiJokyoKbn().equals(MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO)
                    && CheckUtility.isBlank(boMoshikomiSelect.getMoshikomiFinishBi())) {
                boMoshikomiUpdate.setMoshikomiFinishBi(sysTim.getymd1());
                boMoshikomiUpdate.setMoshikomiFinishTime(sysTim.gethms1());
            }

            //論理削除フラグをセット
            boMoshikomiUpdate.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            //処理区分をセット
            boMoshikomiUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            //更新日付をセット
            boMoshikomiUpdate.setKoshinDate(sysTim.getymd1());
            //更新時刻をセット
            boMoshikomiUpdate.setKoshinTime(sysTim.gethms1());
            //更新ユーザーIDを保持
            boMoshikomiUpdate.setKoshinUserId(moshikomiJohoInp.getUserId());

            //情報を更新する
            boMoshikomiUpdate.update(boMoshikomiSelect.getEventCode());

            //トランザクションをコミットする
            commitTransaction();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".insertMoshikomisha()", e);
            //ロールバック
            rollbackTransaction();
            throw new SEWException("画像補正区分更新処理で例外が発生した。", e);
        } finally {
        }
    }
}
