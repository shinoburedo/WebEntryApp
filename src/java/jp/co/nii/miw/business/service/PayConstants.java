package jp.co.nii.miw.business.service;

import jp.co.nii.sew.utility.PropertyUtility;


/**
 * 定数クラス<br>
 * @author
 * @history Han.研修
 */
public class PayConstants{

	/**業務コード */
	public static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
	/**テストモード */
	public static final String PAY_TEST = PropertyUtility.getProperty("pay_test_mode");

	/** 決済テスト用データソース */
	public final static String REGISTRANT = PropertyUtility.getProperty(BUSINESS_CODE + "db_dslookup_registrant");

	/** 決済テスト用　加盟店コード */
	public final static String IP_CONVENI = PropertyUtility.getProperty(BUSINESS_CODE + "pay_ip_conveni");
	public final static String IP_CREDIT = PropertyUtility.getProperty(BUSINESS_CODE + "pay_ip_credit");
        
        /**決済方法　値*/
        public static final String PAY_STORE_CREDIT = "51";
        
        
        
        
        /**決済区分　クレジットカード*/
        public static final String PAY_KUBUN_CREDIT = "1";
        /**決済区分　コンビニ*/
        public static final String PAY_KUBUN_CONVENI = "2";
        
        /**決済結果　ＯＫ*/
        public static final String PAY_RETURN_OK = "OK";
        
        /**決済　テストモード　テスト*/
        public static final String PAY_TEST_MODE_TEST = "1";
        /**決済　テストモード　本番*/
        public static final String PAY_TEST_MODE_POST = "0";
        
        public static final int INT_PAY_NAME_MAX_LENGTH = 10;
        public static final int INT_PAY_KANA_MAX_LENGTH = 10;
        public static final int INT_PAY_YUBIN1_MAX_LENGTH = 7;
        public static final int INT_PAY_TEL_MAX_LENGTH = 11;
        public static final int INT_PAY_TEL_MIN_LENGTH = 9;
        public static final int INT_PAY_ADR_MAX_LENGTH = 25;
        public static final int INT_PAY_MAIL_MAX_LENGTH = 100;
        public static final int INT_PAY_FUKA_MAX_LENGTH = 100;
        public static final int INT_PAY_N_MAX_LENGTH = 25;
        public static final int INT_PAY_K_MAX_LENGTH = 6;
        public static final int INT_PAY_TAX_MAX_LENGTH = 6;
        public static final int INT_PAY_CARDNO_MAX_LENGTH = 16;
        
        public static final int INT_PAY_YUBIN2_EQUAL_LENGTH = 4;        
        public static final int INT_PAY_EXP_EQUAL_LENGTH = 4;        
        public static final int INT_PAY_KIGEN_EQUAL_LENGTH = 8;

	// エラーメッセージ用の項目名
	public static final String PAY_KUBUN = "「決済方法」";
	public static final String PAY_NAME1 = "「購入者漢字氏名（姓）」";
	public static final String PAY_NAME2 = "「購入者漢字氏名（名）」";
	public static final String PAY_KANA1 = "「購入者カナ氏名（姓）」";
	public static final String PAY_KANA2 = "「購入者カナ氏名（名）」";
	public static final String PAY_YUBIN1 = "「購入者郵便番号１」";
	public static final String PAY_YUBIN2 = "「購入者郵便番号２」";
	public static final String PAY_TEL = "「購入者電話番号」";
	public static final String PAY_ADR1 = "「購入者住所１」";
	public static final String PAY_ADR2 = "「購入者住所２」";
	public static final String PAY_MAIL = "「購入者メールアドレス」";
	public static final String PAY_FUKA = "「付加情報」";
	public static final String PAY_N1 = "「商品名称１」";
	public static final String PAY_K1 = "「金額１」";
	public static final String PAY_N2 = "「商品名称２」";
	public static final String PAY_K2 = "「金額２」";
	public static final String PAY_N3 = "「商品名称３」";
	public static final String PAY_K3 = "「金額３」";
	public static final String PAY_N4 = "「商品名称４」";
	public static final String PAY_K4 = "「金額４」";
	public static final String PAY_N5 = "「商品名称５」";
	public static final String PAY_K5 = "「金額５」";
	public static final String PAY_N6 = "「商品名称６」";
	public static final String PAY_K6 = "「金額６」";
        
	public static final String PAY_STORE = "「お支払コンビニ」";
	public static final String PAY_KIGEN = "「支払期限」";
	public static final String PAY_TAX = "「内税額」";

	public static final String PAY_KAKUTEI = "「決済確定」";
	public static final String PAY_CARDNO = "「クレジットカード番号」";
	public static final String PAY_CARDNO1 = "「クレジットカード番号の１つ目」";
	public static final String PAY_CARDNO2 = "「クレジットカード番号の２つ目」";
	public static final String PAY_CARDNO3 = "「クレジットカード番号の３つ目」";
	public static final String PAY_CARDNO4 = "「クレジットカード番号の４つ目」";
	public static final String PAY_EXP = "「有効期限」";
	public static final String PAY_EXPYY = "「有効期限の年」";
	public static final String PAY_EXPMM = "「有効期限の月」";
        
       // エラーメッセージのグループ名
	public static final String ERR_GROUP_PAY_KUBUN = "payKbn";
        public static final String ERR_GROUP_PAY_NAME1 = "name1";
        public static final String ERR_GROUP_PAY_NAME2 = "name2";
        public static final String ERR_GROUP_PAY_KANA1 = "kana1";
        public static final String ERR_GROUP_PAY_KANA2 = "kana2";
        public static final String ERR_GROUP_PAY_YUBIN1 = "yubin1";
        public static final String ERR_GROUP_PAY_YUBIN2 = "yubin2";
        public static final String ERR_GROUP_PAY_TEL = "tel";
        public static final String ERR_GROUP_PAY_ADR1 = "adr1";
        public static final String ERR_GROUP_PAY_ADR2 = "adr2";
        public static final String ERR_GROUP_PAY_MAIL = "mail";
        public static final String ERR_GROUP_PAY_FUKA = "fuka";
        public static final String ERR_GROUP_PAY_S1 = "s1";
        public static final String ERR_GROUP_PAY_S2 = "s2";
        public static final String ERR_GROUP_PAY_S3 = "s2";
        public static final String ERR_GROUP_PAY_S4 = "s4";
        public static final String ERR_GROUP_PAY_S5 = "s5";
        public static final String ERR_GROUP_PAY_S6 = "s6";

        public static final String ERR_GROUP_PAY_STORE = "store";
        public static final String ERR_GROUP_PAY_KIGEN = "kigen";
        public static final String ERR_GROUP_PAY_TAX = "tax";
        
        public static final String ERR_GROUP_PAY_KAKUTEI = "kakutei";
        public static final String ERR_GROUP_PAY_CARDNO = "cardno";
        public static final String ERR_GROUP_PAY_EXP = "exp";
        
        public static final String ERR_GROUP_PAY_ALL = "all";

        
        
        
}