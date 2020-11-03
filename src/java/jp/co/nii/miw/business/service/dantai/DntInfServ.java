package jp.co.nii.miw.business.service.dantai;

import java.beans.Beans;
import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.business.MiwStringUtility;
import jp.co.nii.miw.business.MiwValidate;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.MoshikomiDantai;
import jp.co.nii.miw.business.domain.Saiban;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.sew.SEWException;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.business.service.transaction.TransactionUtility;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * <p>タイトル: 団体情報サービス</p>
 * <p>説明: 団体情報サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2012</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author nii
 */
public class DntInfServ extends AbstractService {

    /**
     * DB接続のデータソース
     */
    private static String DATA_SOURCE_NAME;

    //DB接続時のユーザーを決定
    public DntInfServ() {
        super();
        DATA_SOURCE_NAME = MiwConstants.DS_REGISTRANT;
    }

    /**
     * 団体情報入力画面共通チェック
     * @param request
     * @param dntinfo
     * @param regist  
     *  true   団体パスワードチェックあり
     *  false  団体パスワードチェックなし 
     * @param upd     
     *  true   団体申込者登録用パスワードチェックあり
     *  false  団体申込者登録用パスワードチェックなし 
     * @return ActionMessages
     * @throws Exception 
     */
    public ActionMessages publicValidationCaller(HttpServletRequest request, DntInf dntInfo, boolean regist, boolean upd) throws Exception {
        ActionMessages errors = new ActionMessages();

        // 不正値チェックのためにvalue値を配列に入れる
        // 都道府県名称
        String[] listTodoufuken = StringUtility.getValueList(MiwConstants.DISP_TODOFUKEN_CODE);
        // 発送物の送付先
        String[] listSoufusaki = StringUtility.getValueList(MiwConstants.DISP_HASSOSAKI_KBN);
        // 団体パスワード確認用の回答・質問　回答
        String[] listQuestion = StringUtility.getValueList(MiwConstants.DISP_PASSWD_QUESTION_COD);
        // 決済方法区分
        String[] listKessai = StringUtility.getValueList(MiwConstants.DISP_KESSAI_HOHO_KBN);

        /* 団体等の名称 */
        Validate.validateRequired(dntInfo.getDantaiName(), errors, "1", "団体等の名称");
        Validate.validateMojiCode3(dntInfo.getDantaiName(), errors, "1", "団体等の名称");
        Validate.validateMaxLength(dntInfo.getDantaiName(), MiwConstants.MAX_LEN_DNT_NAME, errors, "1", "団体等の名称");

        /* 団体名称フリガナ */
        Validate.validateRequired(dntInfo.getDantaiNameKana(), errors, "2", "団体名称フリガナ");
        Validate.validateKatakana(dntInfo.getDantaiNameKana(), errors, "2", "団体名称フリガナ");
        Validate.validateMaxLength(dntInfo.getDantaiNameKana(), MiwConstants.MAX_LEN_DNT_NAME_KANA, errors, "2", "団体名称フリガナ");

        /* 郵便番号 */
        Validate.validateRequired(dntInfo.getYubinNo1(), errors, "3", "郵便番号の前３桁");
        Validate.validateNumber(dntInfo.getYubinNo1(), errors, "3", "郵便番号の前３桁");
        Validate.validateEqualLength(dntInfo.getYubinNo1(), 3, errors, "3", "郵便番号の前３桁");

        Validate.validateRequired(dntInfo.getYubinNo2(), errors, "3", "郵便番号の後４桁");
        Validate.validateNumber(dntInfo.getYubinNo2(), errors, "3", "郵便番号の後４桁");
        Validate.validateEqualLength(dntInfo.getYubinNo2(), 4, errors, "3", "郵便番号の後４桁");

        /* 都道府県名称 */
        Validate.validateRequired(dntInfo.getTodofuken(), errors, "4", "都道府県名称");
        Validate.validatePermissionSelect(dntInfo.getTodofuken(), listTodoufuken, errors, "4", "都道府県名称");

        /* 住所 */
        Validate.validateMojiCode3(dntInfo.getJusho1(), errors, "4", "市区町村・郡");
        Validate.validateRequired(dntInfo.getJusho1(), errors, "4", "市区町村・郡");
        Validate.validateMaxLength(dntInfo.getJusho1(), MiwConstants.MAX_LEN_DNT_JYUSHO, errors, "4", "市区町村・郡");

        Validate.validateMojiCode3(dntInfo.getJusho2(), errors, "4", "町名");
        Validate.validateRequired(dntInfo.getJusho2(), errors, "4", "町名");
        Validate.validateMaxLength(dntInfo.getJusho2(), MiwConstants.MAX_LEN_DNT_JYUSHO, errors, "4", "町名");

        Validate.validateMojiCode3(dntInfo.getJusho3(), errors, "4", "丁目・番地");
        Validate.validateRequired(dntInfo.getJusho3(), errors, "4", "丁目・番地");
        Validate.validateMaxLength(dntInfo.getJusho3(), MiwConstants.MAX_LEN_DNT_JYUSHO, errors, "4", "丁目・番地");

        Validate.validateMojiCode3(dntInfo.getJusho4(), errors, "4", "ビル・建物名、部屋番号　など");
        Validate.validateMaxLength(dntInfo.getJusho4(), MiwConstants.MAX_LEN_DNT_JYUSHO_ETC, errors, "4", "ビル・建物名、部屋番号　など");

        String jusho = dntInfo.getTodofuken() + dntInfo.getJusho1() + dntInfo.getJusho2() + dntInfo.getJusho3() + dntInfo.getJusho4();
        Validate.validateMaxLength(jusho, MiwConstants.MAX_LEN_DNT_JYUSHO_ALL, errors, "4", "住所");

        /* 姓 */
        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaShimeiSei(), errors, "5", "担当者の姓");
        Validate.validateMojiCode1(dntInfo.getDantaiJimuTantoshaShimeiSei(), errors, "5", "担当者の姓");
        Validate.validateMaxLength(dntInfo.getDantaiJimuTantoshaShimeiSei(), MiwConstants.MAX_LEN_SHIMEI_SEI, errors, "5", "担当者の姓");

        /* 名 */
        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaShimeiMei(), errors, "5", "担当者の名");
        Validate.validateMojiCode1(dntInfo.getDantaiJimuTantoshaShimeiMei(), errors, "5", "担当者の名");
        Validate.validateMaxLength(dntInfo.getDantaiJimuTantoshaShimeiMei(), MiwConstants.MAX_LEN_SHIMEI_MEI, errors, "5", "担当者の名");

        /* フリガナ姓 */
        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaShimeiSeiKana(), errors, "6", "担当者名フリガナの姓");
        Validate.validateKatakana(dntInfo.getDantaiJimuTantoshaShimeiSeiKana(), errors, "6", "担当者名フリガナの姓");

        /* フリガナ名 */
        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaShimeiMeiKana(), errors, "6", "担当者名フリガナの名");
        Validate.validateKatakana(dntInfo.getDantaiJimuTantoshaShimeiMeiKana(), errors, "6", "担当者名フリガナの名");

        /* フリガナ */
        String kana = dntInfo.getDantaiJimuTantoshaShimeiSeiKana() + "　" + dntInfo.getDantaiJimuTantoshaShimeiMeiKana();
        Validate.validateMaxLength(kana, MiwConstants.MAX_LEN_SHIMEI_KANA, errors, "6", "担当者名フリガナ");

        /* 電話番号  */
        String tel = dntInfo.getDantaiJimuTantoshaTelNo();
        MiwValidate.validateTelephone(tel, errors, "7", "電話番号");
//        Validate.validateMaxLength(tel, MiwConstants.MAX_LEN_TEL, errors, "7", "電話番号");
        Validate.validateRequired(tel, errors, "7", "電話番号");
//        tel = tel.replace("-", "");
//        tel = tel.replace("+", "");
//        tel = tel.replace(" ", "");
//        if (tel.length() < MiwConstants.MIN_LEN_TEL) {
//            //数字のみで９桁未満の場合
//            //コンビニ決済でエラーとなるためチェックしています
//            errors.add("7", new ActionMessage("errors.tellengthmin", "電話番号", MiwConstants.MIN_LEN_TEL));
//        }

        //内線番号のチェック
        MiwValidate.validateTelephone(dntInfo.getExtNo(), errors, "7", "内線番号");
//        Validate.validateMaxLength(dntInfo.getExtNo(), MiwConstants.MAX_LEN_EXT_TEL, errors, "7", "内線番号");
        
        /* 電話番号＋内線番号のチェック */
        if (CheckUtility.isBlank(dntInfo.getExtNo())) {
            Validate.validateMaxLength(tel, MiwConstants.MAX_LEN_TEL, errors, "7", "電話番号");
        } else {
            String telExtNo = dntInfo.getDantaiJimuTantoshaTelNo() + dntInfo.getExtNo();
            Validate.validateSumMaxLength(telExtNo, MiwConstants.MAX_LEN_TEL - 2, errors, "7", "電話番号と内線番号");
        }


        /* FAX番号  */
        MiwValidate.validateTelephone(dntInfo.getDantaiJimuTantoshaFaxNo(), errors, "8", "FAX番号");
        Validate.validateMaxLength(dntInfo.getDantaiJimuTantoshaFaxNo(), MiwConstants.MAX_LEN_TEL, errors, "8", "FAX番号");
//        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaFaxNo(), errors, "8", "FAX番号");

        /* メールアドレスのチェック */
        Validate.validateRequired(dntInfo.getDantaiJimuTantoshaMailAddress(), errors, "9", "メールアドレス");
        Validate.validateEmail(dntInfo.getDantaiJimuTantoshaMailAddress(), errors, "9", "メールアドレス");
        Validate.validateMaxLength(dntInfo.getDantaiJimuTantoshaMailAddress(), MiwConstants.MAX_LEN_MAIL, errors, "9", "メールアドレス");

        /* メールアドレス（確認用）のチェック */
        MiwValidate.validateKakuninInp(dntInfo.getDantaiJimuTantoshaMailAddress(), dntInfo.getDantaiJimuTantoshaMailAddressKakunin(), errors, "10", "メールアドレスの確認入力");

        /* 受験申込者数 */
        if ((dntInfo.getIsTetsudukiJokyoShoninMae()) || (dntInfo.getTetsudukiJokyoKbn() == null)) {
            Validate.validateRequired(dntInfo.getMoshikomishaSuIka(), errors, "11", "受験申込者数医科");
            Validate.validateNumber(dntInfo.getMoshikomishaSuIka(), errors, "11", "受験申込者数医科");
            Validate.validateMaxLength(dntInfo.getMoshikomishaSuIka(), MiwConstants.MAX_LEN_DNT_MOSHIKOMISHA_SU, errors, "11", "受験申込者数医科");

            Validate.validateRequired(dntInfo.getMoshikomishaSuShika(), errors, "12", "受験申込者数歯科");
            Validate.validateNumber(dntInfo.getMoshikomishaSuShika(), errors, "12", "受験申込者数歯科");
            Validate.validateMaxLength(dntInfo.getMoshikomishaSuShika(), MiwConstants.MAX_LEN_DNT_MOSHIKOMISHA_SU, errors, "12", "受験申込者数歯科");

            if ("0".equals(dntInfo.getMoshikomishaSuIka()) && "0".equals(dntInfo.getMoshikomishaSuShika())) {
                //申込者数がいずれも0の場合
                errors.add("11", new ActionMessage("errors.sum", "受験申込者数"));
            }
        }

        /* 発送物の送付先 */
        Validate.validateRequired(dntInfo.getHassosakiKbn(), errors, "13", "発送物の送付先");
        Validate.validatePermissionSelect(dntInfo.getHassosakiKbn(), listSoufusaki, errors, "13", "発送物の送付先");

        if (regist) {
            /* 団体パスワード */
            Validate.validateAlphabetOrNumber(dntInfo.getDantaiPasswd(), errors, "14", "団体パスワード");
            Validate.validateRequired(dntInfo.getDantaiPasswd(), errors, "14", "団体パスワード");
            Validate.validateRangeLength(dntInfo.getDantaiPasswd(), MiwConstants.MIN_LEN_PASSWD, MiwConstants.MAX_LEN_PASSWD, errors, "14", "団体パスワード");

            /* 団体パスワード（確認用）のチェック */
            MiwValidate.validateKakuninInp(dntInfo.getDantaiPasswd(), dntInfo.getDantaiPasswdKakunin(), errors, "15", "団体パスワードの確認入力");
        }

        /* 団体パスワード確認用の質問 */
        Validate.validateRequired(dntInfo.getPasswdQuestionCode1(), errors, "16", "団体パスワード確認用の質問");
        Validate.validatePermissionSelect(dntInfo.getPasswdQuestionCode1(), listQuestion, errors, "16", "団体パスワード確認用の質問");

        /* 団体パスワード確認用の回答 */
        Validate.validateRequired(dntInfo.getPasswdAnswer1(), errors, "17", "団体パスワード確認用の質問の回答");
        Validate.validateMojiCode4(dntInfo.getPasswdAnswer1(), errors, "17", "団体パスワード確認用の質問の回答");
        Validate.validateMaxLength(dntInfo.getPasswdAnswer1(), MiwConstants.MAX_LEN_PASSWD_ANSWER, errors, "17", "団体パスワード確認用の質問の回答");

        if (upd) {
            /* 団体申込者登録用パスワード */
            Validate.validateAlphabetOrNumber(dntInfo.getDantaiMoshikomiPasswd(), errors, "18", "団体申込者登録用パスワード");
            Validate.validateRequired(dntInfo.getDantaiMoshikomiPasswd(), errors, "18", "団体申込者登録用パスワード");
            Validate.validateRangeLength(dntInfo.getDantaiMoshikomiPasswd(), MiwConstants.MIN_LEN_PASSWD, MiwConstants.MAX_LEN_PASSWD, errors, "19", "団体申込者登録用パスワード");

            /* 団体申込者登録用パスワード（確認用）のチェック */
            MiwValidate.validateKakuninInp(dntInfo.getDantaiMoshikomiPasswd(), dntInfo.getDantaiMoshikomiPasswdKakunin(), errors, "19", "団体申込者登録用パスワードの確認入力");
        }

        /* 通信欄 */
        Validate.validateMojiCode4(MiwStringUtility.getNewLineReplaceBlank(dntInfo.getMoshikomiMemo()), errors, "20", "通信欄");
        Validate.validateMaxLength(MiwStringUtility.getNewLineReplaceBlank(dntInfo.getMoshikomiMemo()), MiwConstants.MAX_LEN_MOSHIKOMI_MEMO, errors, "20", "通信欄");

        /* 受験手数料のお支払方法 */
        if (dntInfo.getIsKessaiJokyoMi() || (dntInfo.getTetsudukiJokyoKbn() == null)) {
            Validate.validateRequired(dntInfo.getKessaiHohoKbn(), errors, "21", MiwConstants.JUKEN_CHARGE_NAME + "のお支払方法");
            Validate.validatePermissionSelect(dntInfo.getKessaiHohoKbn(), listKessai, errors, "21", MiwConstants.JUKEN_CHARGE_NAME + "のお支払方法");
        }
        return errors;
    }

    // 承認前に受験申込者数の変更があるか
    public void dntMoshikoShasuValidation(ActionMessages errors, DntInf before, DntInf after) throws Exception {
        // 受験申込者数医科に変更がある場合
        if (!before.getMoshikomishaSuIka().equals(after.getMoshikomishaSuIka())) {
            if (!MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE.equals(after.getTetsudukiJokyoKbn())) {
                // 承認前以外の場合エラー
                errors.add("11", new ActionMessage("errors.dntInfshonincheck", "受験申込者数医科"));
            }
            // 受験申込者数歯科に変更がある場合
        } else if (!before.getMoshikomishaSuShika().equals(after.getMoshikomishaSuShika())) {
            if (!MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE.equals(after.getTetsudukiJokyoKbn())) {
                // 承認前以外の場合エラー
                errors.add("12", new ActionMessage("errors.dntInfshonincheck", "受験申込者数歯科"));
            }

        }
    }

    /**
     * 団体情報を取得・編集する
     * @param dntInfo
     * @return 取得した団体情報。存在しない場合はnull。
     * @throws Exception
     */
    public DntInf getDantaiInfo(DntInf dntInfo) throws Exception {

        DntInf ret = new DntInf();

        // ログイン用団体テーブル検索
        Dantai retSearch = selectDantai(dntInfo);

        if (retSearch == null) {
            //該当なし
            ret = null;
        } else {
            // 保持情報をBEANへコピーする
            BeanUtils.copyProperties(ret, dntInfo);
            // 団体BOをBEANへコピーする
            BeanUtils.copyProperties(ret, retSearch);
            
            // 今年度申込出願しているか確認
            MoshikomiDantai retMdantaiSearch = selectMoshikomiDantaiForDntCode(dntInfo);
            if (retMdantaiSearch == null) {
                // 出願していない場合
            } else {
                // コピーできないBOの項目をBEANへセットする
                setDantaiInfo(ret, retSearch);

                // 申込団体BOをBEANへコピーする
                BeanUtils.copyProperties(ret, retMdantaiSearch);
                // 登録者数をセットする
                setDantaiTorokushaSu(ret);
            }
        }

        return ret;

    }

    public void setDantaiInfo(DntInf dntInfo, Dantai retSearch) throws Exception {
        // 郵便番号を分割してセット
        dntInfo.setYubinNo1(retSearch.getDantaiYubinNo().substring(0, 3));
        dntInfo.setYubinNo2(retSearch.getDantaiYubinNo().substring(3, 7));

        // 住所を分割してセット
        String[] jusho = fixJusho(retSearch.getDantaiJusho().split(MiwConstants.JUSHO_SPLIT_STRING, -1));
        dntInfo.setTodofuken(jusho[0]);
        dntInfo.setJusho1(jusho[1]);
        dntInfo.setJusho2(jusho[2]);
        dntInfo.setJusho3(jusho[3]);
        dntInfo.setJusho4(jusho[4]);

        // メールアドレスの確認をセット
        dntInfo.setDantaiJimuTantoshaMailAddressKakunin(retSearch.getDantaiJimuTantoshaMailAddress());

        /* 電話番号 */
        dntInfo.setDantaiJimuTantoshaTelNo(MiwStringUtility.splitConcateWithParenthesis(retSearch.getDantaiJimuTantoshaTelNo())[0]);
        /* 内線番号 */
        dntInfo.setExtNo(MiwStringUtility.splitConcateWithParenthesis(retSearch.getDantaiJimuTantoshaTelNo())[1]);
    }

    /**
     * 団体申込登録者数を検索し、セットする
     * @param dntInfo
     */
    public void setDantaiTorokushaSu(DntInf dntInfo) {
        try {
            Moshikomi boMoshikomi = new Moshikomi(DATA_SOURCE_NAME);

            //年度・回数・申込受付番号で検索
            String[] dantaiTorokushaSu = boMoshikomi.findTorokushaSu(dntInfo.getNendo(), dntInfo.getKaisu(), dntInfo.getDantaiMoshikomiUketsukeNo());

            dntInfo.setTorokushaSuIka(dantaiTorokushaSu[0]);
            dntInfo.setTorokushaSuShika(dantaiTorokushaSu[1]);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectDantaiTorokushaSu()", e);
            throw new SEWException("団体申込登録者数の検索処理で例外が発生した。", e);
        } finally {
        }
    }

    /**
     * 団体テーブル・申込団体へ入力情報を追加する
     * @param dntInfo 
     */
    public void registDantai(DntInf dntInfo) {
        try {
            // トランサクション開始
            getTransaction();
            beginTransaction();

            // 採番を行う
            String dntCode = saibanUpdate(MiwConstants.NO_ID_DANTAI_ID);
            // 団体コードを保持
            dntInfo.setDantaiCode(dntCode);

            // 団体へInsert
            Dantai boDantai = new Dantai(DATA_SOURCE_NAME);
            SystemTime sysTim = new SystemTime();
            // BEANをBOへコピー
            BeanUtils.copyProperties(boDantai, dntInfo);
            // 郵便番号をセット
            boDantai.setDantaiYubinNo(dntInfo.getDantaiYubinNo());
            // 住所をセット
            boDantai.setDantaiJusho(dntInfo.getDantaiJusho());
            // 電話番号をセット
            boDantai.setDantaiJimuTantoshaTelNo(MiwStringUtility.getConcateWithParenthesis(dntInfo.getDantaiJimuTantoshaTelNo(), dntInfo.getExtNo()));
            // 論理削除フラグをセット
            boDantai.setRonriSakujoFlg(MiwConstants.FLG_OFF);
            // 処理区分をセット
            boDantai.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
            // 登録日付をセット
            boDantai.setTorokuDate(sysTim.getymd1());
            // 登録時刻をセット
            boDantai.setTorokuTime(sysTim.gethms1());
            // 登録ユーザーIDをセット
            boDantai.setTorokuUserId(dntCode);
            // 更新日付をセット
            boDantai.setKoshinDate(sysTim.getymd1());
            // 更新時刻をセット
            boDantai.setKoshinTime(sysTim.gethms1());
            // 更新ユーザーIDをセット
            boDantai.setKoshinUserId(dntCode);

            // 団体テーブルへ入力された情報をINSERTする
            boDantai.create();

            // 採番を行う
            String dntJukenCode = saibanUpdate(MiwConstants.NO_ID_DANTAI_JUKEN_ID);
            // 団体申込者登録用IDを保持
            dntInfo.setDantaiMoshikomiUketsukeNo(dntJukenCode);
            // 申込団体へInsert
            insertMdantai(dntInfo);

            //トランザクションをコミットする
            commitTransaction();
            LogGenerate.infoOutput(CLASS_NAME + "*****団体申込出願完了***** 団体コード：" + dntCode + " 団体申込者登録用ID：" + dntJukenCode);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".registDantai()", e, " ****団体申込出願失敗****  団体コード : " + dntInfo.getDantaiCode()
                    + " 団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo() + " 団体名称 : " + dntInfo.getDantaiName() + " 電話番号 : " + dntInfo.getDantaiJimuTantoshaTelNo());
            //ロールバック
            rollbackTransaction();
            throw new SEWException("団体コード登録処理で例外が発生した。", e);
        } finally {
        }

    }

    /**
     * 入力情報を団体に更新、申込団体へ追加する
     * @param dntInfo 
     */
    public void shutuganDantai(DntInf dntInfo) {
        try {
            // トランサクション開始
            getTransaction();
            beginTransaction();
            // SELECT用boオブジェクト作成
            Dantai boDantaiSelect = new Dantai(DATA_SOURCE_NAME);
            // 更新するレコードをロック
            boDantaiSelect = boDantaiSelect.findRetry(dntInfo.getDantaiCode());
            // UPDATE用boオブジェクト作成
            Dantai boDantaiUpdate = new Dantai(DATA_SOURCE_NAME);
            // 取得した情報をコピー
            BeanUtils.copyProperties(boDantaiUpdate, boDantaiSelect);

            // 団体テーブルを更新する
            updateDantai(boDantaiUpdate, dntInfo);
            // 採番を行う
            String dntJukenCode = saibanUpdate(MiwConstants.NO_ID_DANTAI_JUKEN_ID);
            // 団体申込者登録用IDを保持
            dntInfo.setDantaiMoshikomiUketsukeNo(dntJukenCode);
            // 申込団体テーブルへInsert
            insertMdantai(dntInfo);

            //トランザクションをコミットする
            commitTransaction();
            LogGenerate.infoOutput(CLASS_NAME + "*****団体申込者登録用ID取得完了**** 団体申込者登録用ID：" + dntJukenCode);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".shutuganDantai()", e, " ****団体申込再出願失敗**** 団体コード : " + dntInfo.getDantaiCode()
                    + " 団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo() + " 団体名称 : " + dntInfo.getDantaiName() + " 電話番号 : " + dntInfo.getDantaiJimuTantoshaTelNo());
            //ロールバック
            rollbackTransaction();
            throw new SEWException("団体申込再出願処理で例外が発生した。", e);
        } finally {
        }

    }

    /**
     * 入力情報を団体・申込団体へ更新
     * @param dntInfo 
     */
    public void modifyDantai(DntInf dntInfo, DntInf dntInfoInp) {
        try {
            // トランサクション開始
            getTransaction();
            beginTransaction();
            // SELECT用boオブジェクト作成
            Dantai boDantaiSelect = new Dantai(DATA_SOURCE_NAME);
            MoshikomiDantai boMdantaiSelect = new MoshikomiDantai(DATA_SOURCE_NAME);
            // 更新するレコードをロック
            boDantaiSelect = boDantaiSelect.findRetry(dntInfoInp.getDantaiCode());
            boMdantaiSelect = boMdantaiSelect.findRetry(dntInfoInp);
            // 更新用boオブジェクト作成
            Dantai boDantaiUpdate = new Dantai(DATA_SOURCE_NAME);
            MoshikomiDantai boMdantaiUpdate = new MoshikomiDantai(DATA_SOURCE_NAME);

            // 取得した情報をコピー
            BeanUtils.copyProperties(boDantaiUpdate, boDantaiSelect);
            BeanUtils.copyProperties(boMdantaiUpdate, boMdantaiSelect);

            // 団体テーブルを更新する
            updateDantai(boDantaiUpdate, dntInfoInp);
            // 申込団体テーブルを更新する
            updateMdantai(boMdantaiUpdate, dntInfoInp);

            // 取得した情報をコピー
            BeanUtils.copyProperties(dntInfo, boDantaiUpdate);
            // コピーできないBOの項目をBEANへセットする
            setDantaiInfo(dntInfo, boDantaiUpdate);
            BeanUtils.copyProperties(dntInfo, boMdantaiUpdate);

            //トランザクションをコミットする
            commitTransaction();
            LogGenerate.infoOutput(CLASS_NAME + "*****団体情報更新完了**** 団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo());

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, "modifyDantai()", e, " ****団体情報更新失敗**** 団体コード : " + dntInfo.getDantaiCode()
                    + " 団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo() + " 団体名称 : " + dntInfo.getDantaiName() + " 電話番号 : " + dntInfo.getDantaiJimuTantoshaTelNo());
            //ロールバック
            rollbackTransaction();
            throw new SEWException("団体情報更新処理で例外が発生した。", e);
        } finally {
        }

    }

    /**
     * 採番を行う
     * @param  id　        番号ID 
     * @return dntJknCode  団体申込者登録用ID
     */
    private String saibanUpdate(String id) throws Exception {
        SystemTime sysTim = new SystemTime();
        Saiban boSaiban = new Saiban(DATA_SOURCE_NAME);

        // 採番から申込団体コードSEQの通し番号用の一件を検索しロックする
        Saiban retSaiban = boSaiban.findRetry(id);

        // 発行番号最新を取得し、+1する
        int genzaiNo = Integer.parseInt(retSaiban.getGenzaiNo()) + 1;

        // シーケンスを0埋めする
        String seq = StringUtility.padLeft(Integer.toString(genzaiNo), "0", MiwConstants.LENGTH_DANTAI_JUKEN_ID_SEQ);
        // 接頭文字列＋シーケンスでコードを作成
        String dntJknCode = retSaiban.getSettoString() + seq;

        // 取得した採番情報をコピー
        BeanUtils.copyProperties(boSaiban, retSaiban);
        // 発行番号最新に+1したものをセット
        boSaiban.setGenzaiNo(Integer.toString(genzaiNo));
        // 処理区分をセット
        boSaiban.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        // 更新日付をセット
        boSaiban.setKoshinDate(sysTim.getymd1());
        // 更新時間をセット
        boSaiban.setKoshinTime(sysTim.gethms1());
        // 更新ユーザーＩＤをセット
        boSaiban.setKoshinUserId(dntJknCode);

        // 現在番号を更新
        boSaiban.update();

        return dntJknCode;

    }

    /**
     * 団体パスワードを更新する。<br>
     * @param dantai   団体BO
     * @param password 新パスワード
     * @param kosinUserId 更新者
     */
    public void updatePassword(Dantai dantai, String password, String kosinUserId) {
        try {
            SystemTime sysTim = new SystemTime();
            Dantai dantaiBo = new Dantai(DATA_SOURCE_NAME);
            BeanUtils.copyProperties(dantaiBo, dantai);
            dantaiBo.setDantaiPasswd(password);
            dantaiBo.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            dantaiBo.setKoshinDate(sysTim.getymd1());
            dantaiBo.setKoshinTime(sysTim.gethms1());
            dantaiBo.setKoshinUserId(kosinUserId);
            dantaiBo.update();
            LogGenerate.infoOutput(CLASS_NAME + "*****団体パスワード更新完了**** 団体コード：" + dantai.getDantaiCode());

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, "updatePassword()", e, " ****団体パスワード更新失敗**** 団体コード : " + dantai.getDantaiCode());
            throw new SEWException("団体パスワード更新処理で例外が発生した。", e);
        } finally {
        }
    }

    /**
     * 団体申込パスワードを更新する。<br>
     * @param dantai   申込団体BO
     * @param password 新パスワード
     */
    public void updatePassword(MoshikomiDantai dantai, String password) {
        try {
            SystemTime sysTim = new SystemTime();
            MoshikomiDantai dantaiMoshikomiBo = new MoshikomiDantai(DATA_SOURCE_NAME);
            BeanUtils.copyProperties(dantaiMoshikomiBo, dantai);
            dantaiMoshikomiBo.setDantaiMoshikomiPasswd(password);
            dantaiMoshikomiBo.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
            dantaiMoshikomiBo.setKoshinDate(sysTim.getymd1());
            dantaiMoshikomiBo.setKoshinTime(sysTim.gethms1());
            dantaiMoshikomiBo.setKoshinUserId(dantaiMoshikomiBo.getDantaiMoshikomiUketsukeNo());
            dantaiMoshikomiBo.update();
            LogGenerate.infoOutput(CLASS_NAME + "*****団体申込パスワード更新完了**** 団体申込者登録用ID：" + dantai.getDantaiMoshikomiUketsukeNo());

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, "updatePassword()", e, " ****団体申込パスワード更新失敗**** 団体申込者登録用ID：" + dantai.getDantaiMoshikomiUketsukeNo());
            throw new SEWException("団体申込パスワード更新処理で例外が発生した。", e);
        } finally {
        }
    }

    /**
     * 申込団体をINSERT
     * @param dntInfo　団体情報データBean 
     */
    private void insertMdantai(DntInf dntInfo) throws Exception {
        SystemTime sysTim = new SystemTime();
        MoshikomiDantai boMdantai = new MoshikomiDantai(DATA_SOURCE_NAME);
        MiaServ miaServ = new MiaServ();

        // BEANをBOへコピー
        BeanUtils.copyProperties(boMdantai, dntInfo);
        // 決済督促メール送信フラグ
        boMdantai.setKessaiTokusokuMailSoshinFlg(MiwConstants.FLG_OFF);
        // 手続き状況区分
        boMdantai.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE);
        // 決済状況区分
        boMdantai.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_MI);
        // 決済金額
        boMdantai.setKessaiKingaku(calcKessaiKingaku(dntInfo, miaServ));
        // 決済期限日
        boMdantai.setKessaiKigenBi(miaServ.getKigenForDantai(dntInfo.getNendo(), dntInfo.getKaisu()));
        // 論理削除フラグをセット
        boMdantai.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        // 処理区分をセット
        boMdantai.setShoriKbn(MiwConstants.SHORI_KBN_INSERT);
        // 登録日付をセット
        boMdantai.setTorokuDate(sysTim.getymd1());
        // 登録時刻をセット
        boMdantai.setTorokuTime(sysTim.gethms1());
        // 登録ユーザーIDをセット
        boMdantai.setTorokuUserId(dntInfo.getDantaiMoshikomiUketsukeNo());
        // 更新日付をセット
        boMdantai.setKoshinDate(sysTim.getymd1());
        // 更新時刻をセット
        boMdantai.setKoshinTime(sysTim.gethms1());
        // 更新ユーザーIDをセット
        boMdantai.setKoshinUserId(dntInfo.getDantaiMoshikomiUketsukeNo());

        // 申込団体テーブルへ情報をINSERTする
        boMdantai.create();

    }

    /**
     * 申込団体をUPDATE
     * @param dntInfo　団体情報データBean 
     */
    private void updateMdantai(MoshikomiDantai boMdantai, DntInf dntInfo) throws Exception {
        SystemTime sysTim = new SystemTime();
        MiaServ miaServ = new MiaServ();

        if (dntInfo.getIsTetsudukiJokyoShoninMae()) {
            // 受験申込者数
            boMdantai.setMoshikomishaSuIka(dntInfo.getMoshikomishaSuIka());
            boMdantai.setMoshikomishaSuShika(dntInfo.getMoshikomishaSuShika());
            // 決済金額
            boMdantai.setKessaiKingaku(calcKessaiKingaku(dntInfo, miaServ));
        }
        // 決済方法区分
        if (MiwConstants.KESSAI_JOKYO_KBN_MI.equals(boMdantai.getKessaiJokyoKbn())) {
            boMdantai.setKessaiHohoKbn(dntInfo.getKessaiHohoKbn());
        }
        // 通信欄
        boMdantai.setMoshikomiMemo(dntInfo.getMoshikomiMemo());
        // 論理削除フラグをセット
        boMdantai.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        // 処理区分をセット
        boMdantai.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        // 更新日付をセット
        boMdantai.setKoshinDate(sysTim.getymd1());
        // 更新時刻をセット
        boMdantai.setKoshinTime(sysTim.gethms1());
        // 更新ユーザーIDをセット
        boMdantai.setKoshinUserId(dntInfo.getDantaiMoshikomiUketsukeNo());

        // 申込団体テーブルをUPDATEする
        boMdantai.update();

    }

    /**
     * 決済金額の合計を算出
     * @param dntInfo
     * @param miaServ
     * @return
     * @throws Exception 
     */
    private String calcKessaiKingaku(DntInf dntInfo, MiaServ miaServ) throws Exception {
        // 医科の合計決済金額
        int ikaSum = Integer.parseInt(dntInfo.getMoshikomishaSuIka())
                * Integer.parseInt(miaServ.getKingaku());

        // 歯科の合計決済金額
        int shikaSum = Integer.parseInt(dntInfo.getMoshikomishaSuShika())
                * Integer.parseInt(miaServ.getKingaku());

        return String.valueOf(ikaSum + shikaSum);

    }

    /**
     * 団体情報をUPDATE
     * @param dntInfo　団体情報データBean 
     */
    private void updateDantai(Dantai boDantaiUpdate, DntInf dntInfo) throws Exception {
        SystemTime sysTim = new SystemTime();

        // 団体名称をセット
        boDantaiUpdate.setDantaiName(dntInfo.getDantaiName());
        // 団体名称フリガナをセット
        boDantaiUpdate.setDantaiNameKana(dntInfo.getDantaiNameKana());
        // 郵便番号をセット
        boDantaiUpdate.setDantaiYubinNo(dntInfo.getDantaiYubinNo());
        // 住所をセット
        boDantaiUpdate.setDantaiJusho(dntInfo.getDantaiJusho());
        // 担当者名をセット
        boDantaiUpdate.setDantaiJimuTantoshaShimeiSei(dntInfo.getDantaiJimuTantoshaShimeiSei());
        boDantaiUpdate.setDantaiJimuTantoshaShimeiMei(dntInfo.getDantaiJimuTantoshaShimeiMei());
        // 担当者名フリガナをセット
        boDantaiUpdate.setDantaiJimuTantoshaShimeiSeiKana(dntInfo.getDantaiJimuTantoshaShimeiSeiKana());
        boDantaiUpdate.setDantaiJimuTantoshaShimeiMeiKana(dntInfo.getDantaiJimuTantoshaShimeiMeiKana());
        // TELをセット
        boDantaiUpdate.setDantaiJimuTantoshaTelNo(MiwStringUtility.getConcateWithParenthesis(dntInfo.getDantaiJimuTantoshaTelNo(), dntInfo.getExtNo()));
        // FAXをセット
        boDantaiUpdate.setDantaiJimuTantoshaFaxNo(dntInfo.getDantaiJimuTantoshaFaxNo());
        // MAIL
        boDantaiUpdate.setDantaiJimuTantoshaMailAddress(dntInfo.getDantaiJimuTantoshaMailAddress());
        // 発送先
        boDantaiUpdate.setHassosakiKbn(dntInfo.getHassosakiKbn());
        // パスワードの質問
        boDantaiUpdate.setPasswdQuestionCode1(dntInfo.getPasswdQuestionCode1());
        // パスワードの回答
        boDantaiUpdate.setPasswdAnswer1(dntInfo.getPasswdAnswer1());
        // 処理区分をセット
        boDantaiUpdate.setShoriKbn(MiwConstants.SHORI_KBN_UPDATE);
        // 論理削除フラグをセット
        boDantaiUpdate.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        // 更新日付をセット
        boDantaiUpdate.setKoshinDate(sysTim.getymd1());
        // 更新時刻をセット
        boDantaiUpdate.setKoshinTime(sysTim.gethms1());
        // 更新ユーザーIDをセット
        boDantaiUpdate.setKoshinUserId(dntInfo.getDantaiCode());

        // 団体テーブルの情報をUPDATEする
        boDantaiUpdate.update();

    }

    /**
     * 団体を検索する
     * @param dntInfo
     * @return 
     */
    public Dantai selectDantai(DntInf dntInfo) {
        Dantai ret = null;

        try {
            Dantai boDantai = new Dantai(DATA_SOURCE_NAME);

            //団体コード・団体パスワードで検索
            ret = boDantai.findLogin(dntInfo.getDantaiCode(), dntInfo.getDantaiPasswd(), TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("団体ログイン処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 申込団体を検索する
     * @param dntInfo
     * @return 
     */
    public MoshikomiDantai selectMoshikomiDantai(String djCd, String djPsw, String nendo, String kaisu) {
        MoshikomiDantai ret = null;

        try {
            MoshikomiDantai boDantai = new MoshikomiDantai(DATA_SOURCE_NAME);

            //団体申込者登録用ID・年度・回数・団体申込者登録用パスワードで検索
            ret = boDantai.findLogin(djCd, djPsw, nendo, kaisu, TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("団体申込ログイン処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 申込団体を検索する（同一年度内に同一団体コード）
     * @param dntInfo
     * @return 
     */
    public MoshikomiDantai selectMoshikomiDantaiForDntCode(DntInf dntInfo) {
        MoshikomiDantai ret = null;

        try {
            MoshikomiDantai boMdantai = new MoshikomiDantai(DATA_SOURCE_NAME);

            //年度・回数・団体コードで検索
            ret = boMdantai.selectMoshikomiDantaiForDntCode(dntInfo.getNendo(), dntInfo.getKaisu(), dntInfo.getDantaiCode(), TransactionUtility.NO_LOCK);

        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, ".selectMoshikomisha()", e);
            throw new SEWException("団体ログイン処理で例外が発生した。", e);
        } finally {
        }

        return ret;
    }

    /**
     * 団体確認項目をキーに団体データを取得する
     * @param dantai
     * @return 取得した団体データ。存在しない場合はnull。
     * @throws Exception
     */
    public Dantai getDntInfForPswRem(Dantai dantai) {

        //入力項目以外の条件セット
        //論理削除されてない
        dantai.setRonriSakujoFlg(MiwConstants.FLG_OFF);
        Dantai bo = dantai.findForPswRem();

        return bo;

    }

    /**
     * 団体情報変更中に管理者より承認されているかチェックする
     * @param dntInf
     * @return 承認されている場合true
     */
    public boolean isDantaiAccepted(DntInf dntInfBefore, DntInf dntInfAfter) {
        
        boolean ret = false;

        //手続状況が「承認前」で申込者数が変更されている場合、再度テーブル検索し、最新の手続状況を取得
        if (MiwConstants.TETSUDUKI_JOKYO_SHONIN_MAE.equals(dntInfBefore.getTetsudukiJokyoKbn())){
            MoshikomiDantai bo = new MoshikomiDantai(DATA_SOURCE_NAME).find(dntInfBefore);
            if (!bo.getMoshikomishaSuIka().equals(dntInfAfter.getMoshikomishaSuIka()) ||
                    !bo.getMoshikomishaSuShika().equals(dntInfAfter.getMoshikomishaSuShika())){
                //申込者数が変更されている
                if (MiwConstants.TETSUDUKI_JOKYO_KBN_KARI.equals(bo.getTetsudukiJokyoKbn())){
                    //管理者より承認されている
                    ret = true;
                }
            }
        }
        return ret;
    }

    /**
     * 住所の配列がおかしい場合に空をセット
     * @param jusho
     * @return 
     */
    public String[] fixJusho(String[] jusho) {
        String[] blankJusho = new String[5];
        for (int i = 0; i < 5; i++) {
            blankJusho[i] = jusho.length == 5 ? jusho[i] : "";
        }
        return blankJusho;
    }

    /**
     * 入力に変更があるかどうか
     * @param before
     * @param after
     * @param error
     * @return 
     */
    public boolean checkUpd(DntInf before, DntInf after, ActionMessages error) {
        if (!before.getDantaiName().equals(after.getDantaiName())) {
            return true;
        }
        if (!before.getDantaiNameKana().equals(after.getDantaiNameKana())) {
            return true;
        }
        if (!before.getDantaiJusho().equals(after.getDantaiJusho())) {
            return true;
        }
        if (!before.getDantaiYubinNo().equals(after.getDantaiYubinNo())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaShimeiSei().equals(after.getDantaiJimuTantoshaShimeiSei())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaShimeiMei().equals(after.getDantaiJimuTantoshaShimeiMei())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaShimeiSeiKana().equals(after.getDantaiJimuTantoshaShimeiSeiKana())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaShimeiMeiKana().equals(after.getDantaiJimuTantoshaShimeiMeiKana())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaTelNo().equals(after.getDantaiJimuTantoshaTelNo())) {
            return true;
        }
        // 内線番号
        if (!before.getExtNo().equals(after.getExtNo())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaFaxNo().equals(after.getDantaiJimuTantoshaFaxNo())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaMailAddress().equals(after.getDantaiJimuTantoshaMailAddress())) {
            return true;
        }
        if (!before.getDantaiJimuTantoshaMailAddressKakunin().equals(after.getDantaiJimuTantoshaMailAddressKakunin())) {
            return true;
        }
        if (before.getIsTetsudukiJokyoShoninMae()) {
            if (!before.getMoshikomishaSuIka().equals(after.getMoshikomishaSuIka())) {
                return true;
            }
            if (!before.getMoshikomishaSuShika().equals(after.getMoshikomishaSuShika())) {
                return true;
            }
        }
        if (!before.getHassosakiKbn().equals(after.getHassosakiKbn())) {
            return true;
        }
        if (!before.getPasswdQuestionCode1().equals(after.getPasswdQuestionCode1())) {
            return true;
        }
        if (!before.getPasswdAnswer1().equals(after.getPasswdAnswer1())) {
            return true;
        }
        if (!before.getMoshikomiMemo().equals(after.getMoshikomiMemo())) {
            return true;
        }
        if (before.getIsKessaiJokyoMi()) {
            if (!before.getKessaiHohoKbn().equals(after.getKessaiHohoKbn())) {
                return true;
            }
        }

        return false;
    }
}
