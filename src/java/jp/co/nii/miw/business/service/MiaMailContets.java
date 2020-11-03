package jp.co.nii.miw.business.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Dantai;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.moshikomi.JznToroku;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>
 * タイトル: 定型メール本文
 * </p>
 * <p>
 * 説明: 定型化されたメール本文を取得
 * </p>
 * <p>
 * 著作権: Copyright (c) 2011
 * </p>
 * <p>
 * 会社名: 日本情報産業株式会社
 * </p>
 *
 * @author T.Yamaguchi
 */
public class MiaMailContets {

    //業務コード
    private static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    // 本文中で使用するリンクURL
    private static final String MAIL_URL_MAIN_MENU = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_main");
    private static final String MAIL_URL_SHUTSUGAN_LOGIN = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_login");
    private static final String MAIL_URL_DANTAIDAIHYO_MENU = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_dantai");
//    private static final String MAIL_URL_DANTAI_MSK = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_dantai_msk");
//    private static final String MAIL_URL_DANTAI_KAKUNIN = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_dantai_kakunin");
    private static final String MAIL_URL_MYPAGE = PropertyUtility.getProperty(BUSINESS_CODE + "mail_url_mypage");

    public MiaMailContets() {
    }

    /**
     * ＩＤ取得完了メールの本文
     * @param jznToroku
     * @return 
     */
    public String getCntIdShutokuResu(JznToroku jznToroku) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println("※このメールは、診療報酬請求事務能力認定試験のＩＤを取得された方が");
        pw.println("登録されたメールアドレスに自動的に送信しています。");
        pw.println("発信専用のサーバーから送信していますので、ご返信いただいてもご回答できません。");
        pw.println("また、ご登録にお心当たりがない場合は、お手数ですが本メールを削除してください。");
        pw.println("");
        pw.println("");
        pw.println(jznToroku.getShimeiSei() + "　" + jznToroku.getShimeiMei() + " 様");
        pw.println("");
        pw.println("　診療報酬請求事務能力認定試験事務局です。");
        pw.println("");
        pw.println("　このたびはご登録いただきありがとうございます。");
        pw.println("　診療報酬請求事務能力認定試験申込用のID取得が完了しました。");
        pw.println("");
        pw.println("**************************************************");
        pw.println("　ご登録情報");
        pw.println("　氏名：" + jznToroku.getShimeiSei() + "　" + jznToroku.getShimeiMei());
        pw.println("　診療報酬請求事務能力認定試験申込用ＩＤ：" + jznToroku.getUserId());
        pw.println("**************************************************");
        pw.println("");
        pw.println("　ＩＤ通知メールの再送信や、ＩＤの照会は承っておりませんので、");
        pw.println("　ご登録情報は必ず保存してください");
        pw.println("　ＩＤを取得しただけでは、診療報酬請求事務能力認定試験の申込手続きは完了していません。");
        pw.println("　必ず下記ＵＲＬからログインして本申込手続きへ進んでください。");
        pw.println("");
        pw.println("　インターネットで診療報酬請求事務能力認定試験にお申込いただくと");
        pw.println("「申込内容確認」からお支払方法の手順やお申込内容の確認のほか、");
        pw.println("受験科目・受験希望地・住所等の変更機能がご利用いただけます。");
        pw.println("");
        pw.println("");
        pw.println("　【診療報酬請求事務能力認定試験インターネット申込　ログインページ】");
        pw.println("　" + MAIL_URL_SHUTSUGAN_LOGIN);
        pw.println("　お申込にはＩＤと、ＩＤ取得時に設定したパスワードが必要です。");
        pw.println("");
        pw.println("");
        pw.println("");
        pw.println("");


        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 団体コード取得完了メールの本文
     * @param dntInfo
     * @return 
     */
    public String getCntDntResu(DntInf dntInfo) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println("※このメールは、診療報酬請求事務能力認定試験の団体申込出願をされた方が");
        pw.println("登録されたメールアドレスに自動的に送信しています。");
        pw.println("発信専用のサーバーから送信していますので、ご返信いただいてもご回答できません。");
        pw.println("また、ご登録にお心当たりがない場合は、お手数ですが本メールを削除してください。");
        pw.println("");
        pw.println("");
        pw.println(dntInfo.getDantaiJimuTantoshaShimeiSei() + "　" + dntInfo.getDantaiJimuTantoshaShimeiMei() + " 様");
        pw.println("");
        pw.println("　診療報酬請求事務能力認定試験事務局です。");
        pw.println("");
        pw.println("　このたびはご登録いただきありがとうございます。");
        pw.println("　診療報酬請求事務能力認定試験の団体申込出願が完了しました。");
        pw.println("");
        pw.println("　**************************************************");
        pw.println("　ご登録情報");
        pw.println("　団体名称：" + dntInfo.getDantaiName());
        pw.println("　団体コード：" + dntInfo.getDantaiCode());
        pw.println("　団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo());
        pw.println("　**************************************************");
        pw.println("");
        pw.println("　この通知メールの再送信や、団体コードや団体申込者登録用IDの照会は承っておりませんので、");
        pw.println("　ご登録情報は必ず保存してください");
        pw.println("");
        pw.println("　団体コードは、団体代表者が団体登録情報、団体申込者情報の確認・変更をする際、ログインするために使用します。");
        pw.println("　団体コード、団体パスワードは次回の認定試験申込にも使用しますと、団体情報登録作業を省略できますので、今回の申込完了後も保管するようにしてください。");
        pw.println("");
        pw.println("　団体申込者登録用IDは、団体代表者又は団体申込者が、団体申込者登録をする際、ログインするために使用します。");
        pw.println("　団体申込者登録用ID、団体申込者登録用パスワードは、今回の申込に限り有効です。");
        pw.println("");
        pw.println("　団体コードを取得しただけでは、診療報酬請求事務能力認定試験の申込手続きは完了していません。");
        pw.println("　各団体受験者または団体代表者が「団体申込者登録」からログインして、申込手続きへ進んでください。");
        pw.println("　**************************************************");
        pw.println("　【団体申込者登録ログインに必要な情報】");
        pw.println("　団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo());
        pw.println("　団体申込者登録用パスワード：団体申込出願時に設定した「団体申込者登録用パスワード」");
        pw.println("　URL：" + MAIL_URL_DANTAIDAIHYO_MENU);
        pw.println("　   　「団体申込者登録」からログインしてください。");
        pw.println("　**************************************************");
        pw.println("");
        pw.println("");
        pw.println("　また、団体代表者は「団体情報確認」からログインして、団体情報登録内容の確認や変更が可能です。");
        pw.println("　**************************************************");
        pw.println("　【団体情報確認ログインに必要な情報】");
        pw.println("　団体コード：" + dntInfo.getDantaiCode());
        pw.println("　団体パスワード：団体申込出願時に設定した「団体パスワード」");
        pw.println("　URL：" + MAIL_URL_DANTAIDAIHYO_MENU);
        pw.println("　   　「団体情報確認」からログインしてください。");
        pw.println("　**************************************************");
        pw.println("");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 団体申込出願完了メールの本文
     * @param jznToroku
     * @return 
     */
    public String getCntDntMskResu(DntInf dntInfo) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(dntInfo.getDantaiJimuTantoshaShimeiSei() + "　" + dntInfo.getDantaiJimuTantoshaShimeiMei() + " 様");
        pw.println("");
        pw.println("");
        pw.println("　診療報酬請求事務能力認定試験事務局です。");
        pw.println("");
        pw.println("　このたびはご登録いただきありがとうございます。");
        pw.println("　診療報酬請求事務能力認定試験の団体申込出願が完了しました。");
        pw.println("");
        pw.println("　**************************************************");
        pw.println("　ご登録情報");
        pw.println("　団体名称：" + dntInfo.getDantaiName());
        pw.println("　団体コード：" + dntInfo.getDantaiCode());
        pw.println("　団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo());
        pw.println("　**************************************************");
        pw.println("");
        pw.println("　この通知メールの再送信や、団体コードや団体申込者登録用IDの照会は承っておりませんので、");
        pw.println("　ご登録情報は必ず保存してください");
        pw.println("");
        pw.println("　団体コードは、団体代表者が団体登録情報、団体申込者情報の確認・変更をする際、ログインするために使用します。");
        pw.println("　また団体コード、団体パスワードは次回の認定試験申込にも使用しますと、団体情報登録作業を省略できますので、今回の申込完了後も保管するようにしてください。");
        pw.println("");
        pw.println("　団体申込者登録用IDは、団体代表者又は団体申込者が、団体申込者登録をする際、ログインするために使用します。");
        pw.println("");
        pw.println("　団体申込者登録用IDを取得しただけでは、診療報酬請求事務能力認定試験の申込手続きは完了していません。");
        pw.println("　各団体受験者または団体代表者が「団体申込者登録」からログインして、申込手続きへ進んでください。");
        pw.println("　**************************************************");
        pw.println("　【団体申込者登録ログインに必要な情報】");
        pw.println("　団体申込者登録用ID：" + dntInfo.getDantaiMoshikomiUketsukeNo());
        pw.println("　団体申込者登録用パスワード：団体申込出願時に設定した「団体申込者登録用パスワード」");
        pw.println("　URL：" + MAIL_URL_DANTAIDAIHYO_MENU);
        pw.println("　   　「団体申込者登録」からログインしてください。");
        pw.println("　**************************************************");
        pw.println("");
        pw.println("");
        pw.println("　また、団体代表者は「団体情報確認」からログインして、団体情報登録内容の確認や変更が可能です。");
        pw.println("　**************************************************");
        pw.println("　【団体情報確認ログインに必要な情報】");
        pw.println("　団体コード：" + dntInfo.getDantaiCode());
        pw.println("　団体パスワード：団体申込出願時に設定した「団体パスワード」");
        pw.println("　URL：" + MAIL_URL_DANTAIDAIHYO_MENU);
        pw.println("　   　「団体情報確認」からログインしてください。");
        pw.println("　**************************************************");
        pw.println("");
        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }
    
    /**
     * 団体申込完了メールの本文
     * @param mskToroku
     * @return 
     */
    public String getCntMskResu(MskToroku mskTorokuJoho) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様（ID：" + mskTorokuJoho.getMoshikomiUketsukeNo() + "）");
        pw.println("");
        pw.println("");
        pw.println("診療報酬請求事務能力認定試験事務局です。");
        pw.println("お申込いただき誠にありがとうございます。");
        pw.println("");

        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様のお申込を受け付けました。");
        pw.println("お申込の内容は、団体申込者　申込内容確認からご確認ください。");
        pw.println("");
        pw.println("団体申込者　申込内容確認へのログインにはＩＤとパスワードが必要です。");
        pw.println("");
        pw.println("【申込内容確認】");
        pw.println(MAIL_URL_MYPAGE);
        pw.println("");
        pw.println("申込団体の入金確認後、申込完了のメールをお送りします。");
        pw.println("申込団体でお支払を終えたにもかかわらず申込完了のメールが届かない場合は、入金確");
        pw.println("認に時間がかかっていること等が考えられます。");
        pw.println("申込内容確認から「受付状況」をご覧ください。");
        pw.println("受付状況が「受付完了」と表示されていれば、申込は完了しています。");
        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 申込仮受付メールの本文
     * @param mskToroku
     * @return 
     */
    public String getShutsuganCvsResu(MskToroku mskTorokuJoho) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様（ID：" + mskTorokuJoho.getMoshikomiUketsukeNo() + "）");
        pw.println("");
        pw.println("");
        pw.println("診療報酬請求事務能力認定試験事務局です。");
//        pw.println("この度は" + mskTorokuJoho.getNendo() + "年度" + mskTorokuJoho.getKiDisp() + "季実用診療報酬請求事務能力認定試験に");
        pw.println("お申込いただき誠にありがとうございます。");
        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様のお申込を受け付けました。");
        pw.println("お申込時に選択されたコンビニで" + MiwConstants.JUKEN_CHARGE_NAME + "をお支払ください。");
        pw.println("下記のお支払期限までにご入金いただけない場合、申込はキャンセルされますのでご注意ください。");
        pw.println("");
        pw.println("-----------------------------------------------------------------");
        if (mskTorokuJoho.getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_FAMI)) {
            pw.println(MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI1+ "：" + mskTorokuJoho.getKessaiUketsukeNo1());
            pw.println(MiwConstants.KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI2+ "：" + mskTorokuJoho.getKessaiUketsukeNo2());
        } else {
            pw.println(mskTorokuJoho.getKessaiConvenienceShubetsuNoName() + "：" + mskTorokuJoho.getKessaiUketsukeNo());
        }
        pw.println("ご利用コンビニ：" + CodeValueName.getKessaiConvenienceShubetsuName(mskTorokuJoho.getKessaiConvenienceShubetsu()));

        if (mskTorokuJoho.getKessaiConvenienceShubetsu().equals(MiwConstants.KESSAI_CONVENIENCE_SHUBETSU_LAWSON)) {
            //ローソン・セイコーマート時のみ電話番号を表示する
            pw.println("電話番号　： " + mskTorokuJoho.getKessaiTel());
        }

        pw.println("お支払金額：" + mskTorokuJoho.getKessaiKingakuDisp() + "円（" + MiwConstants.JUKEN_CHARGE_NAME + "）");
        pw.println("お支払期限：" + mskTorokuJoho.getKessaiKigenDisp() + " 23時59分");
        pw.println("お支払情報：" + mskTorokuJoho.getKessaiConvenienceHaraikomihyoUrl());
        pw.println("-----------------------------------------------------------------");
        pw.println("");
        pw.println("コンビニでの入金確認後、申込完了のメールをお送りします。");
        pw.println("コンビニでお支払を終えたにもかかわらず申込完了のメールが届かない場合は、入金確");
        pw.println("認に時間がかかっていること等が考えられます。");
        pw.println("診療報酬請求事務能力認定試験インターネット申込メインメニューの「申込内容確認」にログインしてく");
        pw.println("ださい。受付状況が「受付完了」と表示されていれば、申込は完了しています。");
        pw.println("");
        pw.println("");
        pw.println("【申込内容確認】");
        pw.println(MAIL_URL_MYPAGE);
        pw.println("");
        pw.println("");
        pw.println("申込内容確認へのログインにはＩＤとパスワードが必要です。");
        pw.println("申込内容確認では、お支払方法の手順やお申込内容の確認のほか、");
        pw.println("受験科目・受験希望地・住所等の変更が可能です。");
        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 申込仮受付メールの本文（ペイジー用）
     * @param mskToroku
     * @return 
     */
    public String getShutsuganPayeasyResu(MskToroku mskTorokuJoho) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様（ID：" + mskTorokuJoho.getMoshikomiUketsukeNo() + "）");
        pw.println("");
        pw.println("");
        pw.println("診療報酬請求事務能力認定試験事務局です。");
//        pw.println("この度は" + mskTorokuJoho.getNendo() + "年度" + mskTorokuJoho.getKiDisp() + "季実用診療報酬請求事務能力認定試験に");
        pw.println("お申込いただき誠にありがとうございます。");
        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様のお申込を受け付けました。");
        pw.println("ペイジー決済で" + MiwConstants.JUKEN_CHARGE_NAME + "をお支払ください。");
        pw.println("下記のお支払期限までにご入金いただけない場合、申込はキャンセルされますのでご注意ください。");
        pw.println("");
        pw.println("-----------------------------------------------------------------");
        pw.println("払込番号：" + mskTorokuJoho.getKessaiUketsukeNo());
        pw.println("　　　　　（収納機関番号-お客様番号-確認番号）");

        pw.println("お支払金額：" + mskTorokuJoho.getKessaiKingakuDisp() + "円（" + MiwConstants.JUKEN_CHARGE_NAME + "）");
        pw.println("お支払期限：" + mskTorokuJoho.getKessaiKigenDisp() + " 23時59分");
        pw.println("お支払情報：" + mskTorokuJoho.getKessaiConvenienceHaraikomihyoUrl());
        pw.println("-----------------------------------------------------------------");
        pw.println("");
        pw.println("ペイジー決済での入金確認後、申込完了のメールをお送りします。");
        pw.println("ペイジー決済でお支払を終えたにもかかわらず申込完了のメールが届かない場合は、入金確");
        pw.println("認に時間がかかっていること等が考えられます。");
        pw.println("診療報酬請求事務能力認定試験インターネット申込メインメニューの「申込内容確認」にログインしてくだ");
        pw.println("ださい。受付状況が「受付完了」と表示されていれば、申込は完了しています。");
        pw.println("");
        pw.println("");
        pw.println("【申込内容確認】");
        pw.println(MAIL_URL_MYPAGE);
        pw.println("");
        pw.println("");
        pw.println("申込内容確認へのログインにはＩＤとパスワードが必要です。");
        pw.println("申込内容確認では、お支払方法の手順やお申込内容の確認のほか、");
        pw.println("受験科目・受験希望地・住所等の変更が可能です。");
        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 申込完了メール（カード用）の本文
     * @param mskToroku
     * @return 
     */
    public String getShutsuganResu(MskToroku mskTorokuJoho) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様（ID：" + mskTorokuJoho.getMoshikomiUketsukeNo() + "）");
        pw.println("");
        pw.println("");
        pw.println("診療報酬請求事務能力認定試験事務局です。");
//        pw.println("この度は" + mskTorokuJoho.getNendo() + "年度" + mskTorokuJoho.getKiDisp() + "季実用診療報酬請求事務能力認定試験に");
        pw.println("お申込いただき誠にありがとうございます。");
        pw.println("");

        pw.println(mskTorokuJoho.getShimeiSei() + "　" + mskTorokuJoho.getShimeiMei() + " 様のインターネットによる診療報酬請求事務能力認定試験受験のお申込が完了しました。");
        pw.println("お申込の内容は、「申込内容確認」でご確認ください。");
        pw.println("");
        pw.println("また、" + mskTorokuJoho.getMypageKigenDisp() + "まで、申込内容の確認及び受験科目・受験希望地・住所等の");
        pw.println("変更が可能です。");
        pw.println("申込内容確認へのログインにはＩＤとパスワードが必要です。");
        pw.println("");
        pw.println("【申込内容確認】");
        pw.println(MAIL_URL_MYPAGE);
        pw.println("");

//        pw.println("受験票は" + mskTorokuJoho.getJukenhyoHassobiKokunaiDisp() + "に発送する予定です。");
//        pw.println("受験票の到着をもって受付の確認とさせていただきます。");
        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 申込完了メール（コンビニ決済・ペイジー決済）の本文
     * @param boMoshikmi
     * @return 
     */
    public String getShutsuganResuKessai(Moshikomi boMoshikomi, Moshikomisha boMoshikomisha) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        //各種日付を取得する
        //マイページ更新期限
        String[] mypageKigen = mskTorokuServ.getMypageKigen(boMoshikomi.getNendo(), boMoshikomi.getKaisu());
        String ymd[] = mskTorokuServ.convertDate(mypageKigen[0]);
        String hms[] = mskTorokuServ.convertTime(mypageKigen[1]);
        String mypageKigenbi = ymd[1] + "月" + ymd[2] + "日　" + hms[0] + "時" + hms[1] + "分";
//        //受験票発送予定日（国内）
//        String jukenhyoKokunai = mskTorokuServ.getJukenhyoHassoYoteibiKokunai(boMoshikomi.getNendo(), boMoshikomi.getKaisu());
//        ymd = mskTorokuServ.convertDate(jukenhyoKokunai);
//        jukenhyoKokunai = ymd[1] + "月" + ymd[2] + "日";
        //決済方法略称
        String kessaiHohoKbnNameEx = CodeValueName.getKessaiHohoKbnNameEx(boMoshikomi.getKessaiHohoKbn());

        pw.println("");
        pw.println(boMoshikomisha.getShimeiSei() + "　" + boMoshikomisha.getShimeiMei() + " 様　（ID：" + boMoshikomi.getMoshikomiUketsukeNo() + "）");
        pw.println("");
        pw.println("");
        pw.println("診療報酬請求事務能力認定試験事務局です。");
//        pw.println("この度は" + boMoshikomi.getNendo() + "年度" + ki + "診療報酬請求事務能力認定試験に");
        pw.println("お申込いただき誠にありがとうございます。");
        pw.println("");

        pw.println(kessaiHohoKbnNameEx + "でのお支払が確認できましたので、");
        pw.println(boMoshikomisha.getShimeiSei() + "　" + boMoshikomisha.getShimeiMei() + " 様のインターネットによる診療報酬請求事務能力認定試験受験のお申込が完了しました。");
        pw.println("お申込の内容は、申込内容確認でご確認ください。");
        pw.println("");
        pw.println("また、" + mypageKigenbi + "まで、申込内容の確認及び受験科目・受験希望地・住所等の");
        pw.println("変更が可能です。");
        pw.println("申込内容確認へのログインにはＩＤとパスワードが必要です。");
        pw.println("");
        pw.println("【申込内容確認】");
        pw.println(MAIL_URL_MYPAGE);
        pw.println("");
//        pw.println("受験票は" + jukenhyoKokunai + "に発送する予定です。");
//        pw.println("受験票の到着をもって受付の確認とさせていただきます。");
//        pw.println("");
        pw.println("※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * コンビニ決済・ペイジー決済キャンセル時のメール本文
     * @param boMoshikmi
     * @param boMoshikmisha
     * @return 
     */
    public String getShutsuganCancel(Moshikomi boMoshikomi, Moshikomisha boMoshikomisha) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        //決済方法
        String kessaiHohoKbnName = CodeValueName.getKessaiHohoKbnName(boMoshikomi.getKessaiHohoKbn());

        pw.println("");
        pw.println("以下の申込者の" + kessaiHohoKbnName + "で、キャンセルが発生しました。");
        pw.println("申込者データの更新は行っていません。");
        pw.println("更新を行う場合は作業が必要となります。確認してください。");
        pw.println("");
//        pw.println(boMoshikomi.getNendo() + "年度");
        pw.println("申込受付番号：" + boMoshikomi.getMoshikomiUketsukeNo());
        pw.println("氏名：" + boMoshikomisha.getShimeiSei() + "　" + boMoshikomisha.getShimeiMei() + " 様");

        pw.println("");
        pw.println("");
        pw.println("");
        pw.println("");

        pw.close();

        return sw.toString();
    }

    /**
     * パスワード通知メールの本文（マイページ）
     * @param moshikomisha
     * @return 
     */
    public String getPasswordTsuchiContent(Moshikomisha moshikomisha) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(moshikomisha.getShimeiSei() + "　" + moshikomisha.getShimeiMei() + " 様");
        pw.println("");
        pw.println("　こちらは、診療報酬請求事務能力認定試験事務局です。");
        pw.println("　平素は格別のご高配を賜り、厚く御礼申し上げます。");
        pw.println("");
        pw.println("　申込内容確認へのログインに必要なパスワードをご連絡します。");
        pw.println("");
        pw.println("　あなたのパスワードは「" + moshikomisha.getPasswd() + "」です。");
        pw.println("");
        pw.println("　※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");


        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * パスワード通知メールの本文（団体情報）
     * @param dantai
     * @return 
     */
    public String getPasswordTsuchiContent(Dantai dantai) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("");
        pw.println(dantai.getDantaiJimuTantoshaShimeiSei() + "　" + dantai.getDantaiJimuTantoshaShimeiMei() + " 様");
        pw.println("");
        pw.println("　こちらは、診療報酬請求事務能力認定試験事務局です。");
        pw.println("　平素は格別のご高配を賜り、厚く御礼申し上げます。");
        pw.println("");
        pw.println("　団体情報確認へのログインに必要なパスワードをご連絡します。");
        pw.println("");
        pw.println("　あなたのパスワードは「" + dantai.getDantaiPasswd() + "」です。");
        pw.println("");
        pw.println("　より安全にご利用いただくために、団体情報確認へログインされましたら");
        pw.println("　「パスワード変更」からパスワードを変更してください。");
        pw.println("");
        pw.println("　※このご案内は、発信専用のサーバーから送信していますので、");
        pw.println("　ご返信いただいてもご回答できません。");
        pw.println("");
        pw.println("");


        pw.close();

        return sw.toString() + getCntFooter();
    }

    /**
     * 申込完了メールのフッターを取得
     * @return
     */
    public String getCntFooter() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        pw.println("----------------------------------------------------------------");
        pw.println("◎お申込・操作方法に関するお問合せは：");
        pw.println(MiwConstants.CALL_CENTER_NAME);
        pw.println("----------------------------------------------------------------");
        pw.println("Tel ： " + MiwConstants.CALL_CENTER_TEL + "　　Fax ： " + MiwConstants.CALL_CENTER_FAX);
//        pw.println("開設期間 ： " + MiwConstants.CALL_CENTER_KIKAN);
//        pw.println("受付時間 ： " + MiwConstants.CALL_CENTER_JIKAN);
        pw.println("----------------------------------------------------------------");
        pw.println("");
        pw.close();

        return sw.toString();
    }
}
