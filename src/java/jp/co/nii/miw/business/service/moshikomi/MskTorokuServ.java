package jp.co.nii.miw.business.service.moshikomi;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import jp.co.nii.miw.business.service.payment.PayToroku;
import java.util.List;
import java.util.LinkedList;
import java.util.Calendar;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.SEWException;

import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.StringUtility;

import jp.co.nii.miw.business.MiwValidate;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.domain.Kaijo;
import jp.co.nii.miw.business.domain.Event;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Saiban;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.domain.KessaiLog;
import jp.co.nii.miw.business.domain.ShikenchiDao;
import jp.co.nii.miw.integration.ShikenchiDaoImpl;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfSearch;
import jp.co.nii.miw.utility.CodeValueName;
import org.apache.struts.upload.FormFile;

/**
 * <p>タイトル: 申込サービス</p> <p>説明: 申込サービスの実装クラス</p> <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 *
 * @author t.yamaguchi
 */
public class MskTorokuServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public MskTorokuServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * ＩＤ・パスワードでSELECTする
     *
     * @param MskTorokuJoho
     * @throws Exception
     */
    public Moshikomisha selectMoshikomisha(MskToroku mskTorokuJoho) {

        Moshikomisha ret = null;

        try {
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            //ＩＤ・パスワードで検索
            ret = boMoshikomisha.findLogin(mskTorokuJoho.getUserId().toString(), mskTorokuJoho.getPasswd().toString(), TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("申込ログイン処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * ＩＤでSELECTする（２重登録確認用）
     *
     * @param MskTorokuJoho
     * @throws Exception
     */
    public Moshikomisha selectMoshikomishaDouble(MskToroku mskTorokuJoho, String moshikomiAriFlg) {

        Moshikomisha ret = null;

        try {
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            //ＩＤ・パスワードで検索
            ret = boMoshikomisha.findDouble(mskTorokuJoho.getUserId().toString(), moshikomiAriFlg, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("二重登録確認処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 氏名・生年月日・電話番号・申込ありフラグでSELECTする（２重登録確認用）
     *
     * @param MskTorokuJoho
     * @throws Exception
     */
    public Moshikomisha selectMoshikomishaDoubleForShimeiEtc(MskToroku mskTorokuJoho) {

        Moshikomisha ret = null;

        try {
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            // カナ氏名・生年月日・申込ありフラグで検索
            ret = boMoshikomisha.findDoubleForShimeiEtc(mskTorokuJoho, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("二重登録確認処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * ＩＤでSELECTする
     *
     * @param Moshikomisha
     * @throws Exception
     */
    public Moshikomisha selectMoshikomisha(Moshikomi moshikomi) {

        Moshikomisha ret = null;

        try {
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            //ＩＤで検索
            ret = boMoshikomisha.find(moshikomi.getUserId());

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("申込者取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * ２重登録のチェック用にSELECTする
     *
     * @param MskTorokuJoho
     * @throws Exception
     */
    public boolean selectMoshikomi(MskToroku mskTorokuJoho) {

        boolean ret = false;

        try {
            Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);

            //既に登録されていないか検索
            boMoshikomi = boMoshikomi.findDoubleCheck(
                    mskTorokuJoho.getNendo().toString(),
                    mskTorokuJoho.getKaisu().toString(),
                    mskTorokuJoho.getUserId().toString(),
                    TransactionUtility.NO_LOCK);

            if (boMoshikomi == null) {
                ret = true;
            } else {
                ret = false;
            }

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomi()", e);
            throw new SEWException("２重登録チェック処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 再申込チェック用にSELECTする
     *
     * @param MskToroku
     * @throws Exception
     */
    public Moshikomi selectMoshikomiSaishutsugan(MskToroku mskTorokuJoho) {

        Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);

        //既に登録されていないか検索
        boMoshikomi = boMoshikomi.findSaishutsugan(
                mskTorokuJoho.getNendo().toString(),
                mskTorokuJoho.getKaisu().toString(),
                mskTorokuJoho.getUserId().toString(),
                TransactionUtility.NO_LOCK);

        return boMoshikomi;
    }

    /**
     * 受験地の一覧を取得しListを返す
     *
     * @throws Exception
     */
    public List selectShikenchi(String nendo, String ki) {

        List ret = new LinkedList();

        ShikenchiDao daoShikenchi = new ShikenchiDaoImpl(DATA_SOURCE_NAME);

        //受験地を全件取得
        ret = daoShikenchi.findList(nendo, ki, MiwConstants.EVENT_CODE_ALL, TransactionUtility.NO_LOCK);

        return ret;
    }

    /**
     * 受験地名称を返す
     *
     * @param nendo 年度
     * @param ki 期
     * @param shikenchiCode
     * @throws Exception
     */
    public String selectShikenchiName(String nendo, String ki, String shikenchiCode) {

        String ret = "";

        try {
            ShikenchiDao daoShikenchi = new ShikenchiDaoImpl(DATA_SOURCE_NAME);

            //受験地を全件取得
            ret = daoShikenchi.findName(nendo, ki, MiwConstants.EVENT_CODE_ALL, shikenchiCode, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectShikenchi()", e);
            throw new SEWException("開催地区名称の取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 準会場コードで地名を取得する
     *
     * @param junkaijoCode 準会場コード
     * @throws Exception
     */
    public String selectChimei(String nendo, String ki, String junkaijoCode) {

        String ret = null;

        try {
            Kaijo boKaijo = new Kaijo(DATA_SOURCE_NAME);

            //準会場コードで検索
            ret = boKaijo.findJunkaijo(nendo, ki, junkaijoCode, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("団体名確認処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 申込者情報をMskTorokuにセットする
     *
     * @param bo
     * @param rs
     * @throws Exception
     */
    public MskToroku moveMoshikomisha(MskToroku bo, Moshikomisha rs) throws IllegalAccessException, InvocationTargetException {

        BeanUtils.copyProperties(bo, rs);

        /* 生年月日 */
        String birth = rs.getBirthday();
        bo.setBirthdayEra(birth.substring(0, 1));
        bo.setBirthdayYy(birth.substring(1, 3));
        bo.setBirthdayMm(birth.substring(3, 5));
        bo.setBirthdayDd(birth.substring(5));

        /* 郵便番号 */
        String yubinNo = rs.getYubinNo();
        if (yubinNo.length() > 0) {
            bo.setYubinNo1(yubinNo.substring(0, 3));
            bo.setYubinNo2(yubinNo.substring(3, 7));
        }

        /* 住所（国内） */
        if (rs.getJusho().length() > 0) {
            String[] jushos = rs.getJusho().split(MiwConstants.JUSHO_SPLIT_STRING, -1);
            try {
                bo.setTodofuken(jushos[0]);
                bo.setJusho1(jushos[1]);
                bo.setJusho2(jushos[2]);
                bo.setJusho3(jushos[3]);
                bo.setJusho4(jushos[4]);
            } catch (Exception e) {
                bo.setJusho1(rs.getJusho());
            }
        }

        /* 確認用メールアドレス */
        bo.setMailAddressKakunin(rs.getMailAddress());
        /* 電話番号 */
        bo.setTelNo(MiwStringUtility.splitConcateWithParenthesis(rs.getTelNo())[0]);
        /* 内線番号 */
        bo.setExtNo(MiwStringUtility.splitConcateWithParenthesis(rs.getTelNo())[1]);

        return bo;
    }

    public String[] divisionString(String moji, String kigo, int divCnt) {
        String ret[] = new String[divCnt];
        int st = 0;
        int ed = 0;
        int i;

        if (moji.indexOf(kigo) == -1) {
            //記号が含まれない場合
            ret[0] = moji;
            for (i = 1; i < divCnt - 1; i++) {
                ret[i] = "";
            }

        } else {
            //希望が含まれる（分割可能な）場合
            for (i = 1; i < divCnt; i++) {
                ed = moji.indexOf(kigo, st);
                if (ed == -1) {
                    //該当文字なし
                    ret[i - 1] = "";
                } else {
                    //該当文字あり
                    ret[i - 1] = moji.substring(st, ed);
                    st = ed + 1;
                }
            }
            ret[i - 1] = moji.substring(st);
        }

        return ret;
    }

    //申込画面共通の入力チェック
    public ActionMessages publicValidationCaller(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {

        ActionMessages errors = new ActionMessages();

        //不正値チェックのためにvalue値を配列に入れる
        //受験科目
        String[] listKyu = StringUtility.getValueList(MiwConstants.DISP_JUKEN_KAMOKU);

        //性別
        String[] listSexCode = StringUtility.getValueList(MiwConstants.DISP_SEX_CODE);
        //生年月日元号
        String[] listEraCode = StringUtility.getValueList(MiwConstants.DISP_BIRTHDAY_ERA_COD);
        //都道府県
        String[] listTodofukenCode = StringUtility.getValueList(MiwConstants.DISP_TODOFUKEN_CODE);
        //ご本人様確認用の回答・質問　回答
        String[] listQuestion = StringUtility.getValueList(MiwConstants.DISP_PASSWD_QUESTION_COD);
        // 医療事務の受講経験
        String[] listJukoKeikenCode = StringUtility.getValueList(MiwConstants.DISP_JUKO_KEIKEN_CODE);
        // 医療事務の実務経験
        String[] listJitsumuKeikenCode = StringUtility.getValueList(MiwConstants.DISP_JITSUMU_KEIKEN_CODE);

        /* 受験科目 */
        String kyu = mskTorokuJoho.getEventCode();

        Validate.validateRequired(kyu, errors, "1", "受験科目");
        Validate.validatePermissionSelect(kyu, listKyu, errors, "1", "受験科目");

        /* 受験希望地 */
        String shikenchiCode1 = mskTorokuJoho.getShikenchiCode();

        if (Validate.validateRequired(shikenchiCode1, errors, "9", "受験希望地")) {
            //試験コード検索
            Shikenchi shikenchi = new Shikenchi(DATA_SOURCE_NAME).find(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), MiwConstants.EVENT_CODE_ALL, shikenchiCode1);
            if (shikenchi == null) {
                errors.add("9", new ActionMessage("errors.permission", "受験希望地"));
            }
        }

        /* 氏名姓 */
        String sei = mskTorokuJoho.getShimeiSei();
        Validate.validateRequired(sei, errors, "12", "氏名の姓");
        Validate.validateMojiCode1(sei, errors, "12", "氏名の姓");
        Validate.validateMaxLength(sei, MiwConstants.MAX_LEN_SHIMEI_SEI, errors, "12", "氏名の姓");
        /* 氏名名 */
        String mei = mskTorokuJoho.getShimeiMei();
        Validate.validateRequired(mei, errors, "12", "氏名の名");
        Validate.validateMojiCode1(mei, errors, "12", "氏名の名");
        Validate.validateMaxLength(mei, MiwConstants.MAX_LEN_SHIMEI_MEI, errors, "12", "氏名の名");

        /* フリガナ姓 */
        String kanaSei = mskTorokuJoho.getShimeiSeiKana();
        Validate.validateRequired(kanaSei, errors, "13", "フリガナの姓");
        Validate.validateKatakana(kanaSei, errors, "13", "フリガナの姓");
        /* フリガナ名 */
        String kanaMei = mskTorokuJoho.getShimeiMeiKana();
        Validate.validateRequired(kanaMei, errors, "13", "フリガナの名");
        Validate.validateKatakana(kanaMei, errors, "13", "フリガナの名");

        /* フリガナ */
        String kana = kanaSei + "　" + kanaMei;
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "13", "フリガナ");

        /* 性別 */
        String sexCode = mskTorokuJoho.getSexCode();
        Validate.validateRequired(sexCode, errors, "15", "性別");
        Validate.validatePermissionSelect(sexCode, listSexCode, errors, "15", "性別");
        /* 生年月日 */
        String eraCode = mskTorokuJoho.getBirthdayEra();
        String birthYyyy = mskTorokuJoho.getBirthdayYy();
        String birthMm = mskTorokuJoho.getBirthdayMm();
        String birthDd = mskTorokuJoho.getBirthdayDd();
        //元号
        Validate.validateRequired(eraCode, errors, "16", "生年月日の元号");
        Validate.validatePermissionSelect(eraCode, listEraCode, errors, "16", "生年月日の元号");
        //年
        Validate.validateRequired(birthYyyy, errors, "16", "生年月日の年");
        Validate.validateNumber(birthYyyy, errors, "16", "生年月日の年");

        //月
        Validate.validateRequired(birthMm, errors, "16", "生年月日の月");
        Validate.validateNumber(birthMm, errors, "16", "生年月日の月");

        //日
        Validate.validateRequired(birthDd, errors, "16", "生年月日の日");
        Validate.validateNumber(birthDd, errors, "16", "生年月日の日");

        //生年月日のエラーが無かったら
        if (!errors.get("16").hasNext()) {
            //生年月日の不正日付チェック
            String birthYMD = mskTorokuJoho.getBirthday();
            MiwValidate.validateWarekiDate(birthYMD, errors, "16", "生年月日");
        }

        /* 郵便番号 */
        String yubinNo1 = mskTorokuJoho.getYubinNo1();
        String yubinNo2 = mskTorokuJoho.getYubinNo2();
        Validate.validateRequired(yubinNo1, errors, "18", "郵便番号の前３桁");
        Validate.validateNumber(yubinNo1, errors, "18", "郵便番号の前３桁");
        Validate.validateEqualLength(yubinNo1, 3, errors, "18", "郵便番号の前３桁");

        Validate.validateRequired(yubinNo2, errors, "18", "郵便番号の後４桁");
        Validate.validateNumber(yubinNo2, errors, "18", "郵便番号の後４桁");
        Validate.validateEqualLength(yubinNo2, 4, errors, "18", "郵便番号の後４桁");

        /* 住所 */
        String todofuken = mskTorokuJoho.getTodofuken();
        String jusho1 = mskTorokuJoho.getJusho1();
        String jusho2 = mskTorokuJoho.getJusho2();
        String jusho3 = mskTorokuJoho.getJusho3();
        String jusho4 = mskTorokuJoho.getJusho4();

        Validate.validateRequired(todofuken, errors, "19", "都道府県");
        Validate.validatePermissionSelect(todofuken, listTodofukenCode, errors, "19", "都道府県");
        Validate.validateMojiCode3(jusho1, errors, "19", "市区町村・郡");
        Validate.validateRequired(jusho1, errors, "19", "市区町村・郡");
//        Validate.validateMaxLength(jusho1, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "19", "市区町村・郡");
        Validate.validateMojiCode3(jusho2, errors, "19", "町名");
        Validate.validateRequired(jusho2, errors, "19", "町名");
//        Validate.validateMaxLength(jusho2, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "19", "町名");
        Validate.validateMojiCode3(jusho3, errors, "19", "丁目・番地");
        Validate.validateRequired(jusho3, errors, "19", "丁目・番地");
//        Validate.validateMaxLength(jusho3, MiwConstants.LENGTH_JUSHO_KOKUNAI, errors, "19", "丁目・番地");
        Validate.validateMojiCode3(jusho4, errors, "19", "ビル・建物名、部屋番号　など");
//        Validate.validateMaxLength(jusho4, MiwConstants.LENGTH_JUSHO_KOKUNAI_ETC, errors, "19", "ビル・建物名、部屋番号　など");

        String jusho = todofuken + jusho1 + jusho2 + jusho3 + jusho4;
        Validate.validateMaxLength(jusho, MiwConstants.LENGTH_JUSHO_KOKUNAI_ALL, errors, "19", "住所");

        /* 電話番号 / 日中必ず連絡がとれる連絡先 */
        String tel = mskTorokuJoho.getTelNo();
        String keitai = mskTorokuJoho.getCellphoneNo();

        if ((tel + keitai).length() == 0) {
            //電話番号どちらも入力無
            errors.add("21", new ActionMessage("errors.required2", "電話番号"));
        } else {
            if (tel.length() > 0) {
                //電話番号のチェック
                MiwValidate.validateTelephone(tel, errors, "21", "ＴＥＬ");
//                Validate.validateMaxLength(tel, MiwConstants.MAX_LEN_TEL, errors, "21", "電話番号");

                tel = tel.replace("-", "");
                tel = tel.replace("+", "");
                tel = tel.replace(" ", "");
                if (tel.length() < MiwConstants.MIN_LEN_TEL) {
                    //数字のみで９桁未満の場合
                    //コンビニ決済でエラーとなるためチェックしています
                    errors.add("21", new ActionMessage("errors.tellengthmin", "ＴＥＬ", MiwConstants.MIN_LEN_TEL));
                }
            }
            if (keitai.length() > 0) {
                //日中必ず連絡がとれるご連絡先のチェック
                MiwValidate.validateTelephone(keitai, errors, "21", "携帯電話");
                Validate.validateMaxLength(keitai, MiwConstants.MAX_LEN_TEL, errors, "21", "携帯電話");

                keitai = keitai.replace("-", "");
                keitai = keitai.replace("+", "");
                keitai = keitai.replace(" ", "");
                if (keitai.length() < MiwConstants.MIN_LEN_TEL) {
                    //数字のみで９桁未満の場合
                    //コンビニ決済でエラーとなるためチェックしています
                    errors.add("21", new ActionMessage("errors.tellengthmin", "携帯電話", MiwConstants.MIN_LEN_TEL));
                }
            }
            //内線番号のチェック
            MiwValidate.validateTelephone(mskTorokuJoho.getExtNo(), errors, "21", "内線番号");
//            Validate.validateMaxLength(mskTorokuJoho.getExtNo(), MiwConstants.MAX_LEN_EXT_TEL, errors, "21", "内線番号");

            /* 電話番号＋内線番号のチェック */
            if (CheckUtility.isBlank(mskTorokuJoho.getExtNo())) {
                Validate.validateMaxLength(mskTorokuJoho.getTelNo(), MiwConstants.MAX_LEN_TEL, errors, "21", "ＴＥＬ");
            } else {
                String telExtNo = mskTorokuJoho.getTelNo() + mskTorokuJoho.getExtNo();
                Validate.validateSumMaxLength(telExtNo, MiwConstants.MAX_LEN_TEL - 2, errors, "21", "ＴＥＬと内線番号");
            }

        }

        //メールアドレスのチェック
        Validate.validateRequired(mskTorokuJoho.getMailAddress(), errors, "7", "メールアドレス");
        Validate.validateEmail(mskTorokuJoho.getMailAddress(), errors, "7", "メールアドレス");
        Validate.validateMaxLength(mskTorokuJoho.getMailAddress(), MiwConstants.MAX_LEN_MAIL, errors, "7", "メールアドレス");

        //メールアドレス（確認用）のチェック
        MiwValidate.validateKakuninInp(mskTorokuJoho.getMailAddress(), mskTorokuJoho.getMailAddressKakunin(), errors, "8", "メールアドレスの確認入力");

        /* 医療事務の受講経験 */
        String jukoKeikenCode = mskTorokuJoho.getJukoKeiken();
        Validate.validateRequired(jukoKeikenCode, errors, "17", "医療事務の受講経験");
        Validate.validatePermissionSelect(jukoKeikenCode, listJukoKeikenCode, errors, "17", "医療事務の受講経験");

        /* 医療事務の実務経験 */
        String jitsumuKeikenCode = mskTorokuJoho.getJitsumuKeiken();
        Validate.validateRequired(jitsumuKeikenCode, errors, "20", "医療事務の実務経験");
        Validate.validatePermissionSelect(jitsumuKeikenCode, listJitsumuKeikenCode, errors, "20", "医療事務の実務経験");

        /* ご本人様確認用の質問 */
        String passwdQuestion = mskTorokuJoho.getPasswdQuestionCode1();
        Validate.validateRequired(passwdQuestion, errors, "23", "ご本人様確認用の質問");
        Validate.validatePermissionSelect(passwdQuestion, listQuestion, errors, "23", "ご本人様確認用の質問");
        /* ご本人様確認用の回答 */
        String passwdAnswer = mskTorokuJoho.getPasswdAnswer1();
        Validate.validateRequired(passwdAnswer, errors, "24", "ご本人様確認用の質問の回答");
        Validate.validateMojiCode4(passwdAnswer, errors, "24", "ご本人様確認用の質問の回答");
        Validate.validateMaxLength(passwdAnswer, MiwConstants.MAX_LEN_PASSWD_ANSWER, errors, "24", "ご本人様確認用の質問の回答");

        return errors;
    }

    //団体申込画面のみの入力チェック
    public void dantaiMskValidationCaller(ActionMessages errors, HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        /* パスワード */
        Validate.validateAlphabetOrNumber(mskTorokuJoho.getPasswd(), errors, "25", "パスワード");
        Validate.validateRequired(mskTorokuJoho.getPasswd(), errors, "25", "パスワード");
        Validate.validateRangeLength(mskTorokuJoho.getPasswd(), MiwConstants.MIN_LEN_PASSWD, MiwConstants.MAX_LEN_PASSWD, errors, "25", "パスワード");
        /* パスワード（確認用）のチェック */
        MiwValidate.validateKakuninInp(mskTorokuJoho.getPasswd(), mskTorokuJoho.getPasswdKakunin(), errors, "26", "パスワードの確認入力");
    }

    /**
     * イベントコードから金額を取得する
     *
     * @param eventCode イベントコード
     * @throws Exception
     */
    public String[] selectKingaku(String eventCode) {

        String ret[] = new String[3];

        try {
            Event event = new Event(DATA_SOURCE_NAME);

            //ＩＤ・パスワードで検索
            event = event.find(eventCode);

            if (event == null) {
                ret[0] = "0";
                ret[1] = "0";
                ret[2] = "0";
            } else {
                ret[0] = event.getEventRyo();
                ret[1] = event.getCreditcardKessaiJimuTesuryo();
                ret[2] = event.getConvenienceKessaiJimuTesuryo();
            }
        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("金額取得処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * MskTorokuからPayTorokuに値をセットする
     *
     * @param mskTorokuJoho 申込登録情報
     * @param payTorokuJoho 決済情報
     * @throws Exception
     */
    public PayToroku setPayTorokuJoho(MskToroku mskTorokuJoho, PayToroku payTorokuJoho) {
        MiaServ miaServ = new MiaServ();
        payTorokuJoho.setSid(getSid(mskTorokuJoho));

        String name1 = convertKessaiString(mskTorokuJoho.getShimeiSei(), 10);
        String name2 = convertKessaiString(mskTorokuJoho.getShimeiMei(), 10);
        String kana1 = convertKessaiString(mskTorokuJoho.getShimeiSeiKana(), 10);
        String kana2 = convertKessaiString(mskTorokuJoho.getShimeiMeiKana(), 10);
        String tel = "";
        if (mskTorokuJoho.getTelNo().length() > 0) {
            //電話番号入力有
            tel = mskTorokuJoho.getTelNo();
        } else {
            //電話番号入力なし
            tel = mskTorokuJoho.getCellphoneNo();
        }
        tel = tel.replace("-", "");
        tel = tel.replace("+", "");
        tel = tel.replace(" ", "");
        tel = convertKessaiString(tel, 11);

        payTorokuJoho.setName1(name1);
        payTorokuJoho.setName2(name2);
        payTorokuJoho.setKana1(kana1);
        payTorokuJoho.setKana2(kana2);
        payTorokuJoho.setYubin1(mskTorokuJoho.getYubinNo1());
        payTorokuJoho.setYubin2(mskTorokuJoho.getYubinNo2());
        payTorokuJoho.setTel(tel);

        String jusho = mskTorokuJoho.getJusho();
        if (jusho.length() > 25) {
            payTorokuJoho.setAdr1(jusho.substring(0, 25));
            payTorokuJoho.setAdr2(jusho.substring(25));
        } else {
            payTorokuJoho.setAdr1(jusho);
            payTorokuJoho.setAdr2("");
            payTorokuJoho.setAdr3("");
            payTorokuJoho.setAdr4("");
        }

        payTorokuJoho.setMail(mskTorokuJoho.getMailAddress());
        payTorokuJoho.setFuka("miw");
        payTorokuJoho.setN1(MiwConstants.SHOHIN_NAME);
        payTorokuJoho.setK1(mskTorokuJoho.getKessaiKingaku());

        if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
            //カード決済
            payTorokuJoho.setIp(MiwConstants.KESSAI_IP_CARD);
            payTorokuJoho.setKakutei(MiwConstants.KESSAI_KAKUTEI_CODE);
            payTorokuJoho.setStore(MiwConstants.KESSAI_SHUBETSU_CARD);
            payTorokuJoho.setCardno(mskTorokuJoho.getCardNo());
            payTorokuJoho.setExp(mskTorokuJoho.getExpYy() + mskTorokuJoho.getExpMm());

        } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
            //ペイジー決済
            payTorokuJoho.setIp(MiwConstants.KESSAI_IP_PAYEASY);
            payTorokuJoho.setStore(MiwConstants.KESSAI_SHUBETSU_PAYEASY);
            payTorokuJoho.setKigen(miaServ.getKigen(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), false));

        } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CVS)) {
            //コンビニ決済
            payTorokuJoho.setIp(MiwConstants.KESSAI_IP_CONVENI);
            payTorokuJoho.setStore(mskTorokuJoho.getKessaiConvenienceShubetsu());
            payTorokuJoho.setKigen(miaServ.getKigen(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), false));
            payTorokuJoho.setTax("0");
        }

        return payTorokuJoho;
    }

    /**
     * 取引コードを取得する
     *
     * @throws Exception
     */
    public String getSid(MskToroku mskTorokuJoho) {

        //決済IDを作成
        SystemTime systime = new SystemTime();

        String kessaiId = mskTorokuJoho.getKessaiHohoKbn() //決済方法区分
                + mskTorokuJoho.getUserId().toString() //ユーザID８ケタ
                + systime.getymd1().substring(2, 4) //西暦下２ケタ
                + toBase62String(Long.parseLong(systime.getymd1().substring(4, 6)))//月(62x)
                + toBase62String(Long.parseLong(systime.getymd1().substring(6, 8)))//日(62x)
                + toBase62String(Long.parseLong(systime.gethms1().substring(0, 2)))//時(62x)
                + toBase62String(Long.parseLong(systime.gethms1().substring(2, 4)))//分(62x)
                + toBase62String(Long.parseLong(systime.gethms1().substring(4, 6)));//秒(62x)

        return kessaiId;
    }

    /**
     * 数字を62進数にして返す（取引コード作成用） 0-9 a-z A-Z
     *
     * @param long 変換前数字
     * @return str 変換後文字列
     */
    public static String toBase62String(final long value) {
        long val = value;

        StringBuilder sb = new StringBuilder(7);
        if (val > 0) {
            while (val > 0) {
                int mod = (int) (val % 62);
                if (mod < 10) {
                    // 数字
                    sb.append(mod);
                } else if (mod < 36) {
                    // 英小文字 a = 97
                    // mod = mod - 10 + 97
                    mod += 87;
                    sb.append((char) mod);
                } else {
                    // 英大文字 A = 65
                    // mod = mod - 36 + 65
                    mod += 29;
                    sb.append((char) mod);
                }
                val = val / 62;
            }

            return new String(sb.reverse());
        } else {
            String val_o = "0";
            return val_o;
        }
    }

    /**
     * マイページ更新期限を取得する
     *
     * @param nendo 年度
     * @param ki 季
     * @throws Exception
     * @return [0]期限日、[1]期限時刻
     */
    public String[] getMypageKigen(String nendo, String ki) {
        String[] ret = new String[2];

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_UPD_KOJIN);

            ret[0] = menuControl.getShuryoDate();
            ret[1] = menuControl.getShuryoTime();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getMypageKigen()", e);
            throw new SEWException("マイページ更新期限取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

//    /**
//     * 受験票発送予定日（国内）を取得する
//     *
//     * @param nendo 年度
//     * @param ki 季
//     * @throws Exception
//     */
//    public String getJukenhyoHassoYoteibiKokunai(String nendo, String ki) {
//        String ret = "";
//
//        try {
//            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
//            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_JUKENHYO_HASSOBI_KOKUNAI);
//
//            ret = menuControl.getKaishiDate();
//
//        } catch (Exception e) {
//            LogGenerate.errWrite(CLASS_NAME, ".getJukenhyoHassoYoteibiKokunai()", e);
//            throw new SEWException("受験票発送予定日（国内）取得で例外が発生した。", e);
//        } finally {
//        }
//
//        return ret;
//    }
    /**
     * 受験票発送予定日（免除）／１次試験結果通知日を取得する
     *
     * @param mskTorokuJoho 検索条件が入ったMskToroku
     * @throws Exception
     */
    public String getJukenhyoHassoYoteibiMenjo(String nendo, String ki) {
        String ret = "";

//        try {
//            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
//            menuControl = menuControl.find(nendo, ki, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_GOHATSU_S1_J2_ICHIJI);
//
//            ret = menuControl.getKaishiDate();
//
//        } catch (Exception e) {
//            LogGenerate.errWrite(CLASS_NAME, ".getJukenhyoHassoYoteibiKokugai()", e);
//            throw new SEWException("受験票発送予定日（免除）取得で例外が発生した。", e);
//        } finally {
//        }

        return ret;
    }

    /**
     * 団体コードから団体情報を取得する
     *
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
     *
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
     * メニュー制御から申込用日付を取得する
     *
     * @param mskTorokuJoho 検索条件が入ったMskToroku
     * @param interval session-timedoutの時間（秒）
     * @throws Exception
     */
    public MskToroku setShutsuganDate(MskToroku mskTorokuJoho, int interval) {

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MSK);

            if (menuControl != null) {
                mskTorokuJoho.setShutsuganStartDate(menuControl.getKaishiDate());
                mskTorokuJoho.setShutsuganStartTime(menuControl.getKaishiTime());
                mskTorokuJoho.setShutsuganEndDate(menuControl.getShuryoDate());
                mskTorokuJoho.setShutsuganEndTime(menuControl.getShuryoTime());

                //入力完了日時計算（終了日時＋session.timeout時間）
                int year = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(0, 4));
                int month = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(4, 6));
                int date = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(6));
                int hour = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(0, 2));
                int minute = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(2, 4));
                int second = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(4));

                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(year, month - 1, date, hour, minute, second);

                calendar.add(calendar.SECOND, interval);

                DecimalFormat df2 = new DecimalFormat("00");
                DecimalFormat df4 = new DecimalFormat("0000");

                String ymd = df4.format(calendar.get(calendar.YEAR))
                        + df2.format(calendar.get(calendar.MONTH) + 1)
                        + df2.format(calendar.get(calendar.DATE));

                String hms = df2.format(calendar.get(calendar.HOUR_OF_DAY))
                        + df2.format(calendar.get(calendar.MINUTE))
                        + df2.format(calendar.get(calendar.SECOND));

                mskTorokuJoho.setShutsuganCmpDate(ymd);
                mskTorokuJoho.setShutsuganCmpTime(hms);
            }

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".setShutsuganDate()", e);
            throw new SEWException("申込日付取得で例外が発生した。", e);
        } finally {
        }

        return mskTorokuJoho;
    }

    /**
     * 日付の前ゼロを半角スペースに変換する
     *
     * @param date 日付項目（8桁 yyyymmdd）
     * @throws Exception
     */
    public String[] convertDate(String ymd) {
        String ret[] = new String[3];

        String year = ymd.substring(0, 4);
        String month = ymd.substring(4, 6);
        String date = ymd.substring(6);

        if (month.substring(0, 1).equals("0")) {
            month = " " + month.substring(1);
        }
        if (date.substring(0, 1).equals("0")) {
            date = " " + date.substring(1);
        }

        ret[0] = year;
        ret[1] = month;
        ret[2] = date;

        return ret;
    }

    /**
     * 時刻の前ゼロを半角スペースに変換する
     *
     * @param hms 時刻項目（6桁 hhmmss）
     * @throws Exception
     */
    public String[] convertTime(String hms) {
        String ret[] = new String[3];

        String hour = hms.substring(0, 2);
        String minute = hms.substring(2, 4);
        String second = hms.substring(4);

        if (hour.substring(0, 1).equals("0")) {
            hour = " " + hour.substring(1);
        }
        if (minute.substring(0, 1).equals("0")) {
            minute = " " + minute.substring(1);
        }
        if (second.substring(0, 1).equals("0")) {
            second = " " + second.substring(1);
        }

        ret[0] = hour;
        ret[1] = minute;
        ret[2] = second;

        return ret;
    }

    /**
     * 各テーブルに入金（キャンセル）通知データを登録、更新する
     *
     * @param sid 取引コード
     * @param message メッセージ本文
     * @param kingaku 決済金額
     * @param messageShubetsu メッセージ種別
     * @return false:失敗 true:成功
     */
    public Moshikomi updateKessaiData(String sid, String message, String kingaku, String messageShubetsu) {

        Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Moshikomi retMoshikomi = null;

            if (messageShubetsu.equals(MiwConstants.MESSAGE_SHUBETSU_RECEIPT)) {
                //入金通知のとき
                /* ----- 申込テーブルの該当取引コードレコードを検索しロックする Start ----- */
                retMoshikomi = boMoshikomi.findKessaiRetry(sid, kingaku, messageShubetsu);
                /* ----- 申込テーブルの該当取引コードレコードを検索しロックする  End  ----- */
            } else {
                //キャンセルのとき
                /* ----- 申込テーブルの該当取引コードレコードを検索する Start ----- */
                retMoshikomi = boMoshikomi.findKessai(sid, kingaku, messageShubetsu, TransactionUtility.NO_LOCK);
                /* ----- 申込テーブルの該当取引コードレコードを検索する  End  ----- */
            }

            if (retMoshikomi == null) {
                //該当レコードなし
                LogGenerate.errWrite("コンビニ・ペイジー決済通知に該当する申込情報が存在しませんでした。SID=" + sid
                        + ",KINGAKU=" + kingaku + ",SHUBETSU=" + messageShubetsu + ",MSG=" + message);
                retMoshikomi = new Moshikomi();
//                決済ログテーブル登録
                insertKessaiLog(retMoshikomi, message, messageShubetsu);
                boMoshikomi = null;

            } else {
                BeanUtils.copyProperties(boMoshikomi, retMoshikomi);
//                決済ログテーブル登録
                insertKessaiLog(retMoshikomi, message, messageShubetsu);
                if (messageShubetsu.equals(MiwConstants.MESSAGE_SHUBETSU_RECEIPT)) {
                    //入金通知のとき
                /* ----- 申込更新 Start ----- */
                    boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO);
                    boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_KAKUNIN);
                    boMoshikomi.setMoshikomiFinishBi(sysTim.getymd1());
                    boMoshikomi.setMoshikomiFinishTime(sysTim.gethms1());
                    boMoshikomi.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
                    boMoshikomi.setKoshinDate(sysTim.getymd1());
                    boMoshikomi.setKoshinTime(sysTim.gethms1());
                    boMoshikomi.setKoshinUserId(boMoshikomi.getUserId());

                    boMoshikomi.update();
                    /* ----- 申込更新  End  ----- */
                }
            }

            commitTransaction();

        } catch (Exception e) {
            boMoshikomi = null;
            rollbackTransaction();

            LogGenerate.errWrite(CLASS_NAME, ".insertKessaiLog()", e);
            throw new SEWException("決済ログ作成処理で例外が発生した。SID=" + sid
                        + ",KINGAKU=" + kingaku + ",SHUBETSU=" + messageShubetsu + ",MSG=" + message, e);
        } finally {
        }

        return boMoshikomi;
    }

    /**
     * 決済ログテーブルに入金通知データをInsertする
     *
     * @param boMoshikomi 申込BO
     * @param message メッセージ本文
     * @param kingaku 決済金額
     * @param messageShubetsu メッセージ種別
     * @return false:失敗 true:成功
     */
    public void insertKessaiLog(Moshikomi boMoshikomi, String message, String messageShubetsu) throws IllegalAccessException, InvocationTargetException {

        SystemTime sysTim = new SystemTime();
        Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);
        KessaiLog boKessaiLog = new KessaiLog(DATA_SOURCE_NAME);
        /* ----- 採番更新(決済SEQ) Start ----- */
        //採番から決済ＳＥＱの通し番号用の一件を検索しロックする
        Saiban retSaiban = boSaiban.findRetry(MiwConstants.NO_ID_KESSAI_SEQ);
        //発行番号最新を取得し、+1する
        int iKessaiSeq = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;
        //SEQをゼロ埋め(決済ログ用)
        String strKessaiSeq = StringUtility.padLeft(Integer.toString(iKessaiSeq), "0", MiwConstants.LENGTH_KESSAI_SEQ);

        //取得した採番情報をコピー
        BeanUtils.copyProperties(boSaiban, retSaiban);
        boSaiban.setGenzaiNo(Integer.toString(iKessaiSeq));
        boSaiban.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        boSaiban.setKoshinDate(sysTim.getymd1());
        boSaiban.setKoshinTime(sysTim.gethms1());
        boSaiban.setKoshinUserId("KessaiReceipt");

        //現在番号を更新
        boSaiban.update();
        /* ----- 採番更新(決済SEQ)  End  ----- */

        /* ----- 決済ログ登録 Start ----- */
        //KessaiLog型にデータを移す
        boKessaiLog.setSoujushinDate(sysTim.getymd1());
        boKessaiLog.setSoujushinTime(sysTim.gethms1());
        boKessaiLog.setSeq(strKessaiSeq);
        boKessaiLog.setMoshikomiUketsukeNo(boMoshikomi.getMoshikomiUketsukeNo());
        boKessaiLog.setNendo(boMoshikomi.getNendo());
        boKessaiLog.setKaisu(boMoshikomi.getKaisu());
        boKessaiLog.setEventCode(boMoshikomi.getEventCode());
        boKessaiLog.setUserId(boMoshikomi.getUserId());
        boKessaiLog.setKessaiDaikouKaishaCode(MiwConstants.KESSAI_DAIKOU_KAISHA_CODE_DC);
        boKessaiLog.setMessageShubetsu(messageShubetsu);
        boKessaiLog.setMessageHontai(message);
        boKessaiLog.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
        boKessaiLog.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        boKessaiLog.setTorokuDate(sysTim.getymd1());
        boKessaiLog.setTorokuTime(sysTim.gethms1());
        boKessaiLog.setTorokuUserId("KessaiReceipt");
        boKessaiLog.setKoshinDate(sysTim.getymd1());
        boKessaiLog.setKoshinTime(sysTim.gethms1());
        boKessaiLog.setKoshinUserId("KessaiReceipt");

        boKessaiLog.create();
        /* ----- 決済ログ登録  End  ----- */
    }

    /**
     *
     * @param c
     * @param len
     * @return
     */
    private String convertKessaiString(String c, int len) {
        if (c.length() > len) {
            return c.substring(0, len);
        } else {
            return c;
        }
    }

    /**
     * 決済ログテーブルに決済要求データ（送信値）をInsertする
     *
     * @param mskTorokuJoho 申込情報
     * @param payTorokuJoho 決済情報
     * @param message メッセージ本体
     * @param kbn メッセージ種別
     * @return true:成功 false:失敗
     */
    public boolean insertKessaiRequest(MskToroku mskTorokuJoho, PayToroku payTorokuJoho, String message, String kbn) {

        boolean ret = false;

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);
            KessaiLog boKessaiLog = new KessaiLog(DATA_SOURCE_NAME);

            /* ----- 採番更新(決済SEQ) Start ----- */
            //採番から決済ＳＥＱの通し番号用の一件を検索しロックする
            Saiban retSaiban = boSaiban.findRetry(MiwConstants.NO_ID_KESSAI_SEQ);
            //発行番号最新を取得し、+1する
            int iKessaiSeq = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;
            //SEQをゼロ埋め(決済ログ用)
            String strKessaiSeq = StringUtility.padLeft(Integer.toString(iKessaiSeq), "0", MiwConstants.LENGTH_KESSAI_SEQ);

            //取得した採番情報をコピー
            BeanUtils.copyProperties(boSaiban, retSaiban);
            boSaiban.setGenzaiNo(Integer.toString(iKessaiSeq));
            boSaiban.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            boSaiban.setKoshinDate(sysTim.getymd1());
            boSaiban.setKoshinTime(sysTim.gethms1());
            boSaiban.setKoshinUserId(mskTorokuJoho.getUserId());

            //現在番号を更新
            boSaiban.update();
            /* ----- 採番更新(決済SEQ)  End  ----- */

            /* ----- 決済ログ登録 Start ----- */
            //KessaiLog型にデータを移す
            boKessaiLog.setSoujushinDate(sysTim.getymd1());
            boKessaiLog.setSoujushinTime(sysTim.gethms1());
            boKessaiLog.setSeq(strKessaiSeq);
            boKessaiLog.setMoshikomiUketsukeNo(mskTorokuJoho.getMoshikomiUketsukeNo());
            boKessaiLog.setNendo(mskTorokuJoho.getNendo());
            boKessaiLog.setKaisu(mskTorokuJoho.getKaisu());
            boKessaiLog.setEventCode(mskTorokuJoho.getEventCode());
            boKessaiLog.setUserId(mskTorokuJoho.getUserId());
            boKessaiLog.setKessaiDaikouKaishaCode(MiwConstants.KESSAI_DAIKOU_KAISHA_CODE_DC);
            boKessaiLog.setMessageShubetsu(kbn);
            boKessaiLog.setMessageHontai(message);
            boKessaiLog.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
            boKessaiLog.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            boKessaiLog.setTorokuDate(sysTim.getymd1());
            boKessaiLog.setTorokuTime(sysTim.gethms1());
            boKessaiLog.setTorokuUserId(mskTorokuJoho.getUserId());
            boKessaiLog.setKoshinDate(sysTim.getymd1());
            boKessaiLog.setKoshinTime(sysTim.gethms1());
            boKessaiLog.setKoshinUserId(mskTorokuJoho.getUserId());

            boKessaiLog.create();
            /* ----- 決済ログ登録  End  ----- */

            commitTransaction();

            ret = true;

        } catch (Exception e) {
            mskTorokuJoho = null;
            rollbackTransaction();

            LogGenerate.errWrite(CLASS_NAME, ".insertKessaiLog()", e);
            throw new SEWException("決済ログ作成処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    public String createMessage(MskToroku mskTorokuJoho, PayToroku payTorokuJoho) {

        String msgHontai = "";

        msgHontai = payTorokuJoho.getIp() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getSid() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getName1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getName2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getKana1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getKana2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getYubin1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getYubin2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getTel() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getAdr1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getAdr2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getAdr3() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getAdr4() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getMail() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getFuka() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK1() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK2() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN3() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK3() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN4() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK4() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN5() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK5() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getN6() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getK6() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                + payTorokuJoho.getStore() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR;

        if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
            //カード決済
            msgHontai += payTorokuJoho.getKakutei() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + payTorokuJoho.getCardno().substring(0, 12) + "****" + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + payTorokuJoho.getExp() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR;
        } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
            //ペイジー決済
            msgHontai += MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + payTorokuJoho.getKigen() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR;
        } else {
            //コンビニ決済
            msgHontai += MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + payTorokuJoho.getKigen() + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                    + payTorokuJoho.getTax();
        }

        return msgHontai;
    }

    /**
     * DCのエラーメッセージ（エラーコード付き）より表示用メッセージを取得する
     *
     * @param errMsg
     * @return 表示用メッセージ
     */
    public String kessaiErrorHenkan(String kessaiHoho, String errMsg) {

        String ret = "";

        try {
            //エラーコードのみ抜き出す
            String errCode = errMsg.substring(6, 12);

            if (errCode.equals(MiwConstants.KESSAI_ERROR_MENTE)) {
                //メンテナンスエラー
                if (kessaiHoho.equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
                    //カード決済
                    ret = "errors.kessaimente_crd";
                } else if (kessaiHoho.equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
                    //ペイジー決済
                    ret = "errors.kessaimente_payeasy";
                } else {
                    //コンビニ決済
                    ret = "errors.kessaimente_cvs";
                }
            } else if (CodeValueName.getKessaiErrorNinsho(errCode).equals(MiwConstants.FLG_ON)) {
                //カード認証エラー
                ret = "errors.kessaininsho";
            } else if (CodeValueName.getKessaiErrorConnect(errCode).equals(MiwConstants.FLG_ON)) {
                //接続エラー
                if (kessaiHoho.equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
                    //カード決済
                    ret = "errors.kessaiconnect_crd";
                } else if (kessaiHoho.equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
                    //ペイジー決済
                    ret = "errors.kessaiconnect_payeasy";
                } else {
                    //コンビニ決済
                    ret = "errors.kessaiconnect_cvs";
                }
            } else {
                //システムエラー
                ret = "errors.kessaisystem";
            }

        } catch (Exception e) {
            //システムエラー
            ret = "errors.kessaisystem";
        }

        return ret;
    }

    /**
     * 画像ＩＤを取得する。
     *
     * @return
     */
    public String gazoIdIssue() {
        String gazoId = "";
        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);

            /* ----- 採番更新(決済SEQ) Start ----- */
            //採番から決済ＳＥＱの通し番号用の一件を検索しロックする
            Saiban retSaiban = boSaiban.findRetry(MiwConstants.NO_ID_GAZO_ID);
            //発行番号最新を取得し、+1する
            int iKessaiSeq = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;

            //画像IDを設定する
            //接頭文字列を取得
            String setto_string = retSaiban.getSettoString();
            gazoId = setto_string + StringUtility.padLeft(Integer.toString(iKessaiSeq), "0", 8);//8桁になるまで先頭を0で埋める

            //取得した採番情報をコピー
            BeanUtils.copyProperties(boSaiban, retSaiban);
            boSaiban.setGenzaiNo(Integer.toString(iKessaiSeq));
            boSaiban.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            boSaiban.setKoshinDate(sysTim.getymd1());
            boSaiban.setKoshinTime(sysTim.gethms1());
            boSaiban.setKoshinUserId(gazoId);

            //現在番号を更新
            boSaiban.update();

            commitTransaction();

            return gazoId;

        } catch (Exception e) {
            gazoId = null;
            rollbackTransaction();

            LogGenerate.errWrite(CLASS_NAME, ".gazoIdIssue()", e);
            throw new SEWException("画像ＩＤ作成処理で例外が発生した。", e);
        } finally {
//            return gazoId;
        }
    }

    /**
     * ファイルの未入力チェック
     *
     * @param file
     * @return
     */
    public String checkFileRequired(FormFile file) {
        String ret = "";
        // ファイル名が入力されていない場合
        if (file == null || file.getFileName().toString().equals("")) {
            ret = "errors.fileRequired";
            // エラー処理
        }
        return ret;
    }

    /**
     * ファイルの存在チェック ファイルの大きさが0バイトだったらエラー ファイルが存在しなかったらエラー
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public String checkFileSonzai(FormFile file) throws IOException {
        String ret = "";
        // またはファイルサイズが0バイトの場合
        if (file.getFileSize() == 0) {
            ret = "errors.fileSonzai";
            // エラー処理
        }

        // 存在チェック
        if (file.getInputStream().read() == -1) {
            ret = "errors.fileSonzai";
        }
        return ret;
    }

    /**
     * ファイルのサイズが指定数を超えるとエラー(KB計算)
     *
     * @param file
     * @param max_size
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String checkFileSize(FormFile file, int max_size) {
        String ret = "";
        // ファイルサイズの取得
        int size = file.getFileSize();

        LogGenerate.infoOutput(CLASS_NAME + ".checkFileSize()　このファイルのサイズは、 " + size + "KBです");

        if (size > max_size) {
            ret = "errors.fileByteExcess";
        }
        return ret;
    }

    /**
     * ファイルのサイズが指定数を超えるとエラー(KB計算)
     *
     * @param file
     * @param min_size
     * @param max_size
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String checkFileSize(FormFile file, int min_size, int max_size) {
        String ret = "";
        // ファイルサイズの取得
        double size = (double) file.getFileSize() / 1024;

        LogGenerate.infoOutput(CLASS_NAME + ".checkFileSize()　このファイルのサイズは、 " + size + "KBです");

        if (size < min_size || size > max_size) {
            ret = "errors.fileByteExcess";
        }
        return ret;
    }

    /**
     * 拡張子チェック 配列にＯＫな拡張子を入れる
     *
     * @param file
     * @param fixedSuffix
     * @return
     */
    public String checkFileSuffix(FormFile file, String[] fixedSuffix) {
        String ret = "errors.fileExtension";

        //ファイル名
        String fileName = file.getFileName();
        //拡張子
        String suffix = getSuffix(fileName);

        LogGenerate.infoOutput(CLASS_NAME + ".checkFileSuffix()  このファイルの拡張子は、 " + suffix + " です");

        for (int i = 0; i < fixedSuffix.length; i++) {
            if (fixedSuffix[i].equals(suffix)) {
                ret = "";
                return ret;
            }
        }
        return ret;
    }

    /**
     * 拡張子の取得
     *
     * @param filename 対象ファイル
     * @return 拡張子
     */
    public String getSuffix(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }

    /**
     * 画像の縦*横のピクセル数を取得し、サイズのチェックを行う
     *
     * @param file
     * @param max_height
     * @param max_width
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String checkHeightWidth(FormFile file, int max_height, int max_width, int min_height, int min_width)
            throws FileNotFoundException, IOException {
        String ret = "";

        //画像を読み込む
        BufferedImage image = ImageIO.read(file.getInputStream());
        //画像の縦の長さを取得
        int height = image.getHeight();
        //画像の横の長さを取得
        int width = image.getWidth();

        LogGenerate.infoOutput(CLASS_NAME + ".checkHeightWidth()  この画像は、縦：" + height + "　横：" + width + "です");

        if (height > max_height || width > max_width || height < min_height || width < min_width) {
            ret = "errors.filePixelExcess";
        }
        return ret;
    }

    /**
     * 画像を圧縮して指定したファイルパスに出力する(jpg形式)
     *
     * @param formFile
     * @param filePath
     * @throws Exception
     */
    public boolean imageCompressionOutput(FormFile formFile, String filePath) throws Exception {
        boolean state = false;
        ImageWriter writer = null;
        ImageOutputStream ios = null;
        try {
            BufferedImage image = ImageIO.read(formFile.getInputStream());


            Iterator iter = ImageIO.getImageWritersByFormatName("jpg");
            if (iter.hasNext()) {
                writer = (ImageWriter) iter.next();
            }

            File file = new File(filePath);
            //ファイルがすでに存在しても、上書きされない場合があるので削除しておく
            if (file.exists()) {
                file.delete();
            }
            ios = ImageIO.createImageOutputStream(file);
            writer.setOutput(ios);
            JPEGImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
            iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //(x.xxf)で圧縮率を設定。
            //0から1の間で、1に近いほど圧縮率が低い
            iwparam.setCompressionQuality(0.5f);

            writer.write(null, new IIOImage(image, null, null), iwparam);
            writer.dispose();
            state = true;

        } catch (Exception e) {
            throw e;
        } finally {
            if (writer != null) {
                writer.dispose();
            }
            if (ios != null) {
                ios.flush();
                ios.close();
            }
        }
        return state;
    }

    /**
     * ファイルのディレクトリを変更する
     *
     * @param fromDir
     * @param toDir
     * @param gazoId
     * @return
     */
    public boolean changeDirectory(String fromDir, String toDir, String fileName) {

        String fromFilePath = fromDir + fileName + MiwConstants.SUFFIX;
        String toFilePath = toDir + fileName + MiwConstants.SUFFIX;

        File fromFile = new File(fromFilePath);
        File toFile = new File(toFilePath);

        //移動元の画像が存在しなかったら
        if (!fromFile.exists()) {
            LogGenerate.infoOutput(CLASS_NAME + "移動元のファイルが存在しません");
            return false;
        }

        //移動先に同盟のファイルが存在したら削除する
        if (toFile.exists()) {
            toFile.delete();
        }
        boolean state = fromFile.renameTo(toFile);

        return state;
    }

    /**
     * long型の日時をString型のyyyyMMddHHmmss形式(14桁)で返す
     *
     * @param date
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String longToString(long date) {

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int days = cal.get(Calendar.DAY_OF_MONTH);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        String yyyy = StringUtility.padLeft(String.valueOf(year), "0", 4);
        String MM = StringUtility.padLeft(String.valueOf(month + 1), "0", 2);
        String dd = StringUtility.padLeft(String.valueOf(days), "0", 2);
        String HH = StringUtility.padLeft(String.valueOf(hours), "0", 2);
        String mm = StringUtility.padLeft(String.valueOf(min), "0", 2);
        String ss = StringUtility.padLeft(String.valueOf(second), "0", 2);

        return yyyy + MM + dd + HH + mm + ss;
    }

    /**
     * 指定したファイルパスにファイルをアップロードする
     *
     * @param fileUp 画面に入力したFormFile型のファイル
     * @param filePath ファイルをアップロードするパス
     * @return
     * @throws Exception
     */
    public boolean fileUpload(FormFile fileUp, String filePath)
            throws Exception {
        boolean state = false;
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            //getInputStreamメソッドを使用し、入力ストリームを取得
            InputStream is = fileUp.getInputStream();

            //入力ストリームをバッファリング
            in = new BufferedInputStream(is);

            FileOutputStream fos = new FileOutputStream(filePath);

            //出力ストリームをバッファリング
            out = new BufferedOutputStream(fos);

            int contents = 0;

            //入力データがなくなるまで入出力処理を実行
            while ((contents = in.read()) != -1) {
                out.write(contents);
            }

            state = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.flush();
                out.close();
            }
            //一時領域のアップロードデータを削除
            fileUp.destroy();
        }
        return state;
    }

    /**
     * 出願処理を行う
     *
     * @param mskTorokuJoho insert内容が入ったMskToroku
     * @param kigen 支払期限日
     * @throws Exception
     */
    public boolean shutsugan(MskToroku mskTorokuJoho, String kigen) {
        boolean ret = false;

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            /* ----- 申込登録 Start ----- */
            //Moshikomi型にデータを移す
            boMoshikomi.setMoshikomiUketsukeNo(mskTorokuJoho.getUserId());
            boMoshikomi.setNendo(mskTorokuJoho.getNendo());
            boMoshikomi.setKaisu(mskTorokuJoho.getKaisu());

            //仮受付日はクレジット / コンビニ問わずセット（検索用）
            boMoshikomi.setKariUketsukeBi(sysTim.getymd1());
            boMoshikomi.setKariUketsukeTime(sysTim.gethms1());

            boMoshikomi.setKessaiHohoKbn(mskTorokuJoho.getKessaiHohoKbn());
            boMoshikomi.setKessaiKameitenCode(mskTorokuJoho.getKessaiKameitenCode());
            boMoshikomi.setKessaiTorihikiCode(mskTorokuJoho.getKessaiTorihikiCode());
            boMoshikomi.setKessaiKingaku(mskTorokuJoho.getKessaiKingaku());

            // 画像ID
            boMoshikomi.setGazoId(mskTorokuJoho.getGazoId());

            boMoshikomi.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            boMoshikomi.setKoshinDate(sysTim.getymd1());
            boMoshikomi.setKoshinTime(sysTim.gethms1());
            boMoshikomi.setKoshinUserId(mskTorokuJoho.getUserId());

            if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CRD)) {
                //カード決済
                boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO);
                boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_KAKUNIN);

                boMoshikomi.setMoshikomiFinishBi(sysTim.getymd1());
                boMoshikomi.setMoshikomiFinishTime(sysTim.gethms1());

            } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_PAYEASY)) {
                //ペイジー決済
                boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_KARI);
                boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_MI);
                boMoshikomi.setKessaiKigenBi(kigen);
                boMoshikomi.setKessaiConvenienceHaraikomihyoUrl(mskTorokuJoho.getKessaiConvenienceHaraikomihyoUrl());
            } else if (mskTorokuJoho.getKessaiHohoKbn().equals(MiwConstants.KESSAI_HOHO_KBN_CVS)) {
                //コンビニ決済
                boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_KARI);
                boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_MI);
                boMoshikomi.setKessaiKigenBi(kigen);
                boMoshikomi.setKessaiConvenienceShubetsu(mskTorokuJoho.getKessaiConvenienceShubetsu());
                boMoshikomi.setKessaiConvenienceHaraikomihyoUrl(mskTorokuJoho.getKessaiConvenienceHaraikomihyoUrl());
            }
            boMoshikomi.updateSaishutsuganAfterKessai();
            /* ----- 申込登録  End  ----- */

            /* ----- 申込者更新 Start ----- */
            //Moshikomisha型にデータを写す
            boMoshikomisha.setUserId(mskTorokuJoho.getUserId());
            boMoshikomisha.setMoshikomiAriFlg(MiwConstants.FLG_ON);
            boMoshikomisha.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            boMoshikomisha.setKoshinDate(sysTim.getymd1());
            boMoshikomisha.setKoshinTime(sysTim.gethms1());
            boMoshikomisha.setKoshinUserId(mskTorokuJoho.getUserId());

            //更新
            boMoshikomisha.updateAfterKessai();
            /* ----- 申込者更新  End  ----- */

            commitTransaction();

            ret = true;

        } catch (Exception e) {
            rollbackTransaction();
            LogGenerate.errWrite(CLASS_NAME, ".shutsugan()", e);
//            throw new SEWException("出願処理で例外が発生した。", e);
            ret = false;
        } finally {
        }

        return ret;
    }

    /**
     * 申込仮受付(申込情報(追加or更新)、申込者情報更新)
     * 申込者：申込有フラグ オフ，申込：手続状況区分：決済処理前
     * @param mskTorokuJoho
     */
    public void kariUketsuke(MskToroku mskTorokuJoho) {

        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);

            //申込情報を検索してロックを掛ける
            Moshikomi findMoshikomi = boMoshikomi.lockFind(mskTorokuJoho.getMoshikomiUketsukeNo(), mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu());

            // 申込情報が存在する場合
            if (findMoshikomi != null) {
                mskTorokuJoho.setSaishutsuganFlg(MiwConstants.FLG_ON);
            } else {
                mskTorokuJoho.setSaishutsuganFlg(MiwConstants.FLG_OFF);
            }

            /* ----- 申込登録 Start ----- */
            //Moshikomi型にデータを移す
            boMoshikomi.setMoshikomiUketsukeNo(mskTorokuJoho.getUserId());
            boMoshikomi.setNendo(mskTorokuJoho.getNendo());
            boMoshikomi.setKaisu(mskTorokuJoho.getKaisu());
            boMoshikomi.setEventCode(mskTorokuJoho.getEventCode());
            boMoshikomi.setMoshikomiBaitaiKbn(MiwConstants.MOSHIKOMI_BAITAI_KBN_INT);
            boMoshikomi.setKojinDantaiKbn(MiwConstants.KOJIN_DANTAI_KBN_KOJIN);
            boMoshikomi.setUserId(mskTorokuJoho.getUserId());
            boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_UKETSUKE_MAE);

            boMoshikomi.setShikenchiCode(mskTorokuJoho.getShikenchiCode());

            boMoshikomi.setJukoKeiken(mskTorokuJoho.getJukoKeiken());
            boMoshikomi.setJitsumuKeiken(mskTorokuJoho.getJitsumuKeiken());

            boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_MI);

            /* 補正依頼区分は初期値をセット */
            boMoshikomi.setHoseiIraiKbn(MiwConstants.HOSEI_IRAI_KBN_MI);

            /* 決済督促メール送信フラグは初期値(OFF)をセット */
            boMoshikomi.setKessaiTokusokuMailSoshinFlg(MiwConstants.FLG_OFF);

            boMoshikomi.setRonriSakujoFlg(MiwConstants.FLG_OFF);

            boMoshikomi.setKoshinDate(sysTim.getymd1());
            boMoshikomi.setKoshinTime(sysTim.gethms1());
            boMoshikomi.setKoshinUserId(mskTorokuJoho.getUserId());

            if (mskTorokuJoho.getSaishutsuganFlg().equals(MiwConstants.FLG_OFF)) {
                //新規の場合
                boMoshikomi.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
                boMoshikomi.setTorokuDate(sysTim.getymd1());
                boMoshikomi.setTorokuTime(sysTim.gethms1());
                boMoshikomi.setTorokuUserId(mskTorokuJoho.getUserId());
                //Insert処理
                boMoshikomi.create();
            } else {
                //更新の場合
                boMoshikomi.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
                boMoshikomi.updateSaishutsugan();
            }
            /* ----- 申込登録  End  ----- */

            /* ----- 申込者更新 Start ----- */
            //申込者からユーザＩＤのレコード一件を検索しロックする
            Moshikomisha retMoshikomisha = boMoshikomisha.lockNoWait(mskTorokuJoho.getUserId());
            //取得した申込者情報をコピー
            BeanUtils.copyProperties(boMoshikomisha, retMoshikomisha);

            //Moshikomisha型にデータを写す
            // 申込有フラグはオフ
            boMoshikomisha.setMoshikomiAriFlg(MiwConstants.FLG_OFF);
            boMoshikomisha.setShimeiSeiKana(mskTorokuJoho.getShimeiSeiKana());
            boMoshikomisha.setShimeiMeiKana(mskTorokuJoho.getShimeiMeiKana());
            boMoshikomisha.setShimeiSei(mskTorokuJoho.getShimeiSei());
            boMoshikomisha.setShimeiMei(mskTorokuJoho.getShimeiMei());
            boMoshikomisha.setMailAddress(mskTorokuJoho.getMailAddress());
            boMoshikomisha.setBirthday(mskTorokuJoho.getBirthday());
            boMoshikomisha.setSexCode(mskTorokuJoho.getSexCode());
            boMoshikomisha.setTelNo(MiwStringUtility.getConcateWithParenthesis(mskTorokuJoho.getTelNo(), mskTorokuJoho.getExtNo()));
            boMoshikomisha.setCellphoneNo(mskTorokuJoho.getCellphoneNo());
            boMoshikomisha.setYubinNo(mskTorokuJoho.getYubinNo());
            boMoshikomisha.setJusho(mskTorokuJoho.getJusho());

            boMoshikomisha.setPasswdQuestionCode1(mskTorokuJoho.getPasswdQuestionCode1());
            boMoshikomisha.setPasswdAnswer1(mskTorokuJoho.getPasswdAnswer1());
            boMoshikomisha.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            boMoshikomisha.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            boMoshikomisha.setKoshinDate(sysTim.getymd1());
            boMoshikomisha.setKoshinTime(sysTim.gethms1());
            boMoshikomisha.setKoshinUserId(mskTorokuJoho.getUserId());

            //更新
            boMoshikomisha.update();
            /* ----- 申込者更新  End  ----- */

            commitTransaction();

        } catch (Exception e) {
            rollbackTransaction();
            LogGenerate.errWrite(CLASS_NAME, ".shutsugan()", e);
            throw new SEWException("仮申込処理で例外が発生した。", e);
        } finally {
        }

    }

    /**
     * メニュー制御から申込用日付を取得する（団体申込の場合）
     *
     * @param mskTorokuJoho 検索条件が入ったMskToroku
     * @param interval session-timedoutの時間（秒）
     * @throws Exception
     */
    public MskToroku setShutsuganDateDantai(MskToroku mskTorokuJoho, int interval) {

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu(), MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_DNT_MSK);

            if (menuControl != null) {
                mskTorokuJoho.setShutsuganStartDate(menuControl.getKaishiDate());
                mskTorokuJoho.setShutsuganStartTime(menuControl.getKaishiTime());
                mskTorokuJoho.setShutsuganEndDate(menuControl.getShuryoDate());
                mskTorokuJoho.setShutsuganEndTime(menuControl.getShuryoTime());

                //入力完了日時計算（終了日時＋session.timeout時間）
                int year = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(0, 4));
                int month = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(4, 6));
                int date = Integer.parseInt(mskTorokuJoho.getShutsuganEndDate().substring(6));
                int hour = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(0, 2));
                int minute = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(2, 4));
                int second = Integer.parseInt(mskTorokuJoho.getShutsuganEndTime().substring(4));

                Calendar calendar = Calendar.getInstance();
                calendar.clear();
                calendar.set(year, month - 1, date, hour, minute, second);

                calendar.add(calendar.SECOND, interval);

                DecimalFormat df2 = new DecimalFormat("00");
                DecimalFormat df4 = new DecimalFormat("0000");

                String ymd = df4.format(calendar.get(calendar.YEAR))
                        + df2.format(calendar.get(calendar.MONTH) + 1)
                        + df2.format(calendar.get(calendar.DATE));

                String hms = df2.format(calendar.get(calendar.HOUR_OF_DAY))
                        + df2.format(calendar.get(calendar.MINUTE))
                        + df2.format(calendar.get(calendar.SECOND));

                mskTorokuJoho.setShutsuganCmpDate(ymd);
                mskTorokuJoho.setShutsuganCmpTime(hms);
            }

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".setShutsuganDateDantai()", e);
            throw new SEWException("団体申込日付取得で例外が発生した。", e);
        } finally {
        }

        return mskTorokuJoho;
    }

    /**
     * マイページ更新期限を取得する
     *
     * @param nendo 年度
     * @param kaisu 回数
     * @throws Exception
     * @return [0]期限日、[1]期限時刻
     */
    public String[] getMypageKigenDantai(String nendo, String kaisu) {
        String[] ret = new String[2];

        try {
            MenuControl menuControl = new MenuControl(DATA_SOURCE_NAME);
            menuControl = menuControl.find(nendo, kaisu, MiwConstants.EVENT_CODE_ALL, MiwConstants.MENU_CODE_MYP_UPD_DNT);

            ret[0] = menuControl.getShuryoDate();
            ret[1] = menuControl.getShuryoTime();

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".getMypageKigenDantai()", e);
            throw new SEWException("団体マイページ更新期限取得で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 団体申込により申込テーブル・申込者テーブルに入力情報を追加
     *
     * @param mskTorokuJoho
     */
    public boolean createDantaiMoshikomi(MskToroku mskTorokuJoho) {
        boolean ret = false;
        try {
            //トランサクション開始
            getTransaction();
            beginTransaction();

            SystemTime sysTim = new SystemTime();
            Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);
            Moshikomisha boMoshikomisha = new Moshikomisha(DATA_SOURCE_NAME);
            Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);

            /* ----- ユーザーID・申込受付番号 採番 ---- */
            Saiban retSaiban = boSaiban.findRetry(MiwConstants.NO_ID_USER_ID_I);
            //発行番号最新を取得し、+1する
            int genzaiNo = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;
            //シーケンスを0埋めする
            String seq = StringUtility.padLeft(Integer.toString(genzaiNo), "0", MiwConstants.LENGTH_USER_ID_SEQ);
            //接頭文字列＋シーケンスでユーザIDを作成
            String userId = retSaiban.getSettoString() + seq;
            //ユーザIDを保持
            mskTorokuJoho.setUserId(userId);
            //申込受付番号を保持
            mskTorokuJoho.setMoshikomiUketsukeNo(userId);
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

            /* ----- 申込者登録 Start ----- */
            //Moshikomisha型にデータを写す
//            BeanUtils.copyProperties(boMoshikomisha, mskTorokuJoho);
            boMoshikomisha.setUserId(mskTorokuJoho.getUserId());
            boMoshikomisha.setMailAddress(mskTorokuJoho.getMailAddress());
            boMoshikomisha.setPasswd(mskTorokuJoho.getPasswd());
            boMoshikomisha.setShimeiSei(mskTorokuJoho.getShimeiSei());
            boMoshikomisha.setShimeiMei(mskTorokuJoho.getShimeiMei());
            boMoshikomisha.setShimeiSeiKana(mskTorokuJoho.getShimeiSeiKana());
            boMoshikomisha.setShimeiMeiKana(mskTorokuJoho.getShimeiMeiKana());
            boMoshikomisha.setBirthday(mskTorokuJoho.getBirthday());
            boMoshikomisha.setBirthdayEra(mskTorokuJoho.getBirthdayEra());
            boMoshikomisha.setBirthdayYy(mskTorokuJoho.getBirthdayYy());
            boMoshikomisha.setBirthdayMm(mskTorokuJoho.getBirthdayMm());
            boMoshikomisha.setBirthdayDd(mskTorokuJoho.getBirthdayDd());
            boMoshikomisha.setSexCode(mskTorokuJoho.getSexCode());
            boMoshikomisha.setCellphoneNo(mskTorokuJoho.getCellphoneNo());
            boMoshikomisha.setYubinNo(mskTorokuJoho.getYubinNo());
            boMoshikomisha.setPasswdQuestionCode1(mskTorokuJoho.getPasswdQuestionCode1());
            boMoshikomisha.setPasswdAnswer1(mskTorokuJoho.getPasswdAnswer1());
            boMoshikomisha.setTelNo(MiwStringUtility.getConcateWithParenthesis(mskTorokuJoho.getTelNo(), mskTorokuJoho.getExtNo()));
            boMoshikomisha.setMoshikomiAriFlg(MiwConstants.FLG_ON);
            boMoshikomisha.setJusho(mskTorokuJoho.getJusho());
            boMoshikomisha.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            boMoshikomisha.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
            boMoshikomisha.setTorokuDate(sysTim.getymd1());
            boMoshikomisha.setTorokuTime(sysTim.gethms1());
            boMoshikomisha.setTorokuUserId(mskTorokuJoho.getUserId());
            boMoshikomisha.setKoshinDate(sysTim.getymd1());
            boMoshikomisha.setKoshinTime(sysTim.gethms1());
            boMoshikomisha.setKoshinUserId(mskTorokuJoho.getUserId());
            //Insert処理
            boMoshikomisha.create();
            /* ----- 申込者更新  End  ----- */

            /* ----- 申込登録 Start ----- */
            //Moshikomi型にデータを移す
//            BeanUtils.copyProperties(boMoshikomi, mskTorokuJoho);
            boMoshikomi.setMoshikomiUketsukeNo(mskTorokuJoho.getUserId());
            boMoshikomi.setUserId(mskTorokuJoho.getUserId());
            boMoshikomi.setNendo(mskTorokuJoho.getNendo());
            boMoshikomi.setKaisu(mskTorokuJoho.getKaisu());
            boMoshikomi.setEventCode(mskTorokuJoho.getEventCode());
            boMoshikomi.setDantaiCode(mskTorokuJoho.getDantaiCode());
            boMoshikomi.setDantaiMoshikomiUketsukeNo(mskTorokuJoho.getDantaiMoshikomiUketsukeNo());
            boMoshikomi.setShikenchiCode(mskTorokuJoho.getShikenchiCode());
            boMoshikomi.setJukoKeiken(mskTorokuJoho.getJukoKeiken());
            boMoshikomi.setJitsumuKeiken(mskTorokuJoho.getJitsumuKeiken());
            boMoshikomi.setGazoId(mskTorokuJoho.getGazoId());
            boMoshikomi.setMoshikomiBaitaiKbn(MiwConstants.MOSHIKOMI_BAITAI_KBN_INT);
            boMoshikomi.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE);
            boMoshikomi.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_MI);
            boMoshikomi.setKessaiHohoKbn(MiwConstants.KESSAI_HOHO_KBN_DNT);
            boMoshikomi.setKojinDantaiKbn(MiwConstants.KOJIN_DANTAI_KBN_DANTAI);
            boMoshikomi.setHoseiIraiKbn(MiwConstants.HOSEI_IRAI_KBN_MI);
            boMoshikomi.setKessaiTokusokuMailSoshinFlg(MiwConstants.FLG_OFF);
            boMoshikomi.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            boMoshikomi.setKoshinDate(sysTim.getymd1());
            boMoshikomi.setKoshinTime(sysTim.gethms1());
            boMoshikomi.setKoshinUserId(mskTorokuJoho.getUserId());
            boMoshikomi.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
            boMoshikomi.setTorokuDate(sysTim.getymd1());
            boMoshikomi.setTorokuTime(sysTim.gethms1());
            boMoshikomi.setTorokuUserId(mskTorokuJoho.getUserId());
            //Insert処理
            boMoshikomi.create();
            /* ----- 申込登録  End  ----- */

            commitTransaction();
            BeanUtils.copyProperties(mskTorokuJoho, boMoshikomisha);
            BeanUtils.copyProperties(mskTorokuJoho, boMoshikomi);

            ret = true;

        } catch (Exception e) {
            rollbackTransaction();
            LogGenerate.errWrite(CLASS_NAME, ".createDantaiMoshikomi()", e, " *****団体申込登録処理失敗***** ユーザーID : " + mskTorokuJoho.getUserId()
                    + " カナ氏名 : " + mskTorokuJoho.getShimeiSeiKana() + " " + mskTorokuJoho.getShimeiMeiKana()
                    + " 電話番号 : " + mskTorokuJoho.getTelNo() + " 申込受付番号 : " + mskTorokuJoho.getMoshikomiUketsukeNo());
            throw new SEWException("団体申込処理で例外が発生した。", e);
        } finally {
        }
        return ret;
    }

    /**
     * 団体情報より申込者一覧を取得する
     *
     * @param dntInfo
     * @param page
     * @param sort
     * @return mskList
     */
    public DntInfSearch selectMskListFromDntInfo(DntInf dntInfo, int page, String sort) {
        DntInfSearch dntSearch = new DntInfSearch();
        try {
            int offset = (page - 1) * MiwConstants.PAGE_KENSU;

            Moshikomi moshikomi = new Moshikomi(DATA_SOURCE_NAME);
            dntSearch = moshikomi.selectMskListFromDntInfo(dntInfo.getNendo(), dntInfo.getKaisu(), dntInfo.getDantaiCode(), offset, MiwConstants.PAGE_KENSU, sort);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMskListFromDntInfo()", e);
            throw new SEWException("申込者一覧取得で例外が発生した。", e);
        } finally {
        }
        return dntSearch;
    }
}
