package jp.co.nii.miw.business;

import jp.co.nii.sew.utility.PropertyUtility;

/**
 * 定数クラス<br>
 *
 * @author T.Yamaguchi
 */
public class MiwConstants {

    /**
     * 業務コード
     */
    public static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    /**
     * 登録者用データソース
     */
    public static final String DS_REGISTRANT = PropertyUtility.getProperty(BUSINESS_CODE + "db_dslookup_registrant");
    /**
     * 受験案内URL
     */
    public static final String URL_JUKEN_ANNAI = PropertyUtility.getProperty(BUSINESS_CODE + "url_juken_annai");
    /**
     * プライバシーポリシーURL
     */
    public static final String URL_PRIVACY_POLICY = PropertyUtility.getProperty(BUSINESS_CODE + "url_privacy_policy");
    /**
     * 団体情報確認ログインURL
     */
    public static final String URL_DANTAI_KAKUNIN = PropertyUtility.getProperty(BUSINESS_CODE + "url_dantai_kakunin");

    /**
     * 加盟店コード
     */
    public static final String KESSAI_IP_CARD = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_ip_card");
    public static final String KESSAI_IP_CONVENI = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_ip_conveni");
    public static final String KESSAI_IP_PAYEASY = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_ip_payeasy");
    /**
     * 決済テストモード
     */
    public static final String KESSAI_TEST_MODE = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_test_mode");
    /**
     * 決済プロキシサーバ１
     */
    public static final String KESSAI_URL_1 = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_proxy_url_1");
    /**
     * 決済プロキシサーバ２
     */
    public static final String KESSAI_URL_2 = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_proxy_url_2");
    /**
     * 決済手数料
     */
    public static final String KESSAI_TESURYO = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_tesuryo");
    /**
     * 支払期限日・個人（申込日からの日数）
     */
    public static final String KESSAI_KIGEN = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_kigen");
    /**
     * 支払期限日・団体（最終期限日のX日前）
     */
    public static final String KESSAI_KIGEN_DANTAI = PropertyUtility.getProperty(BUSINESS_CODE + "kessai_kigen_dantai");
    /**
     * コールセンター　名称
     */
    public static final String CALL_CENTER_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "call_center_name");
    /**
     * コールセンター　電話番号
     */
    public static final String CALL_CENTER_TEL = PropertyUtility.getProperty(BUSINESS_CODE + "call_center_tel");
    /**
     * コールセンター　FAX
     */
    public static final String CALL_CENTER_FAX = PropertyUtility.getProperty(BUSINESS_CODE + "call_center_fax");
    /**
     * コールセンター　期間
     */
    public static final String CALL_CENTER_KIKAN = PropertyUtility.getProperty(BUSINESS_CODE + "call_center_kikan");
    /**
     * コールセンター　時間
     */
    public static final String CALL_CENTER_JIKAN = PropertyUtility.getProperty(BUSINESS_CODE + "call_center_jikan");
    /**
     * 担当者メールアドレス（キャンセル通知受信時にメールが送信されます。 *
     */
    public static final String TANTOSHA_MAIL_ADDRESS = PropertyUtility.getProperty(BUSINESS_CODE + "tantosha_mail_address");
    /**
     * 負荷テストモード
     */
    public static final String STRESS_MODE = PropertyUtility.getProperty(BUSINESS_CODE + "stress_mode");
    /**
     * 銀行名
     */
    public static final String BANK_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "bank_name");
    /**
     * 支店名
     */
    public static final String BANK_BRANCH_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "bank_branch");
    /**
     * 預金種類
     */
    public static final String BANK_DEPOSIT_KIND = PropertyUtility.getProperty(BUSINESS_CODE + "bank_deposit");
    /**
     * 口座番号
     */
    public static final String BANK_ACCOUNT_NUM = PropertyUtility.getProperty(BUSINESS_CODE + "bank_accnum");
    /**
     * 口座名義
     */
    public static final String BANK_ACCOUNT_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "bank_accname");
    /**
     * ゆうちょ銀行名
     */
    public static final String POST_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "post_name");
    /**
     * 店名
     */
    public static final String POST_BRANCH_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "post_branch");
    /**
     * 口座種別
     */
    public static final String POST_DEPOSIT_KIND = PropertyUtility.getProperty(BUSINESS_CODE + "post_deposit");
    /**
     * 口座番号
     */
    public static final String POST_ACCOUNT_NUM = PropertyUtility.getProperty(BUSINESS_CODE + "post_accnum");
    /**
     * 口座名義
     */
    public static final String POST_ACCOUNT_NAME = PropertyUtility.getProperty(BUSINESS_CODE + "post_accname");
    /**
     * 負荷テストモード 本番
     */
    public static final String STRESS_MODE_HONBAN = "1";
    /**
     * 負荷テストモード 負荷テスト
     */
    public static final String STRESS_MODE_TEST = "2";
    /**
     * 入力チェック結果CSS 正常
     */
    public static final String VALIDATE_CSS_OK = "basic";
    /**
     * 入力チェック結果CSS エラー
     */
    public static final String VALIDATE_CSS_NG = "err";
    /**
     * 改行コード
     */
    public static final String[] NEW_LINE_CODES = {"\r\n", "\n", "\r"};
    /**
     * 番号ＩＤ　ユーザーＩＤ（インターネット）通し番号
     */
    public static final String NO_ID_USER_ID_I = "USER_ID_I";
    /**
     * 番号ＩＤ　画像ＩＤ
     */
    public static final String NO_ID_GAZO_ID = "GAZO_ID";
    /**
     * 番号ＩＤ　申込受付番号
     */
    public static final String NO_ID_UKETSUKE_I = "UKETSUKE_I";
    /**
     * 番号ＩＤ　決済SEQ
     */
    public static final String NO_ID_KESSAI_SEQ = "KESSAI_SEQ";
    /**
     * 番号ＩＤ　団体コード
     */
    public static final String NO_ID_DANTAI_ID = "DNT_ID";
    /**
     * 番号ＩＤ　団体申込者登録用ID
     */
    public static final String NO_ID_DANTAI_JUKEN_ID = "DNT_JKN_ID";
    /**
     * 住所区切り文字
     */
    public static final String JUSHO_SPLIT_STRING = "｜";
    /**
     * 区切り文字かっこ
     */
    public static final String JUSHO_SPLIT_STRING_KAKKO = "\\(";
    /**
     * 処理区分　INSERT
     */
    public static final String SHORI_KBN_INSERT = "I";
    /**
     * 処理区分　UPDATE
     */
    public static final String SHORI_KBN_UPDATE = "U";
    /**
     * 権限コード　全権限
     */
    public static final String KENGEN_CODE_ALL = "00";
    /**
     * 権限コード　更新処理可能
     */
    public static final String KENGEN_CODE_UPD_OK = "01";
    /**
     * 権限コード　更新処理不可
     */
    public static final String KENGEN_CODE_UPD_NG = "02";
    /**
     * フラグON
     */
    public static final String FLG_ON = "1";
    /**
     * フラグOFF
     */
    public static final String FLG_OFF = "0";
    /**
     * イベントコード　全受験科目共通
     */
    public static final String EVENT_CODE_ALL = "99999";
    /**
     * メニューコード　年度取得用レコード
     */
    public static final String MENU_CODE_NENDO = "00";
    /**
     * メニューコード　ID取得
     */
    public static final String MENU_CODE_JZN = "11";
    /**
     * メニューコード　申込
     */
    public static final String MENU_CODE_MSK = "12";
    /**
     * メニューコード　マイページログイン
     */
    public static final String MENU_CODE_MYP_LOGIN_KOJIN = "21";
    /**
     * メニューコード　マイページ確認
     */
    public static final String MENU_CODE_MYP_CONF_KOJIN = "22";
    /**
     * メニューコード　マイページ変更
     */
    public static final String MENU_CODE_MYP_UPD_KOJIN = "23";
    /**
     * メニューコード　団体申込出願（団体コード未取得）
     */
    public static final String MENU_CODE_DNT_TOROKU = "31";
    /**
     * メニューコード　団体申込出願（団体コード取得済）
     */
    public static final String MENU_CODE_DNT_TOROKU_RE = "32";
    /**
     * メニューコード　団体代表者ログイン
     */
    public static final String MENU_CODE_DNT_LOGIN = "33";
    /**
     * メニューコード　団体情報確認
     */
    public static final String MENU_CODE_DNT_CONF = "34";
    /**
     * メニューコード　団体情報変更
     */
    public static final String MENU_CODE_DNT_UPD = "35";
    /**
     * メニューコード　団体申込者情報の確認
     */
    public static final String MENU_CODE_DNTMSK_CONF = "36";
    /**
     * メニューコード　団体申込者情報の変更
     */
    public static final String MENU_CODE_DNTMSK_UPD = "37";
    /**
     * メニューコード　団体パスワード変更
     */
    public static final String MENU_CODE_DNT_PSW_UPD = "38";
    /**
     * メニューコード　団体申込者登録用パスワード変更
     */
    public static final String MENU_CODE_DNT_JKNPSW_UPD = "39";
    /**
     * メニューコード　団体申込者登録
     */
    public static final String MENU_CODE_DNT_MSK = "41";
    /**
     * メニューコード　団体マイページログイン
     */
    public static final String MENU_CODE_MYP_LOGIN_DNT = "51";
    /**
     * メニューコード　団体マイページ確認
     */
    public static final String MENU_CODE_MYP_CONF_DNT = "52";
    /**
     * メニューコード　団体マイページ変更
     */
    public static final String MENU_CODE_MYP_UPD_DNT = "53";
    /**
     * メニューコード　決済最終締切日
     */
    public static final String MENU_CODE_KESSAI_SAISHU_KIGEN = "71";
    /**
     * メニューコード　画像補正期限
     */
    public static final String MENU_CODE_GAZO_HOSEI_KIGEN = "72";
    /**
     * メニューコード　受験票発送予定日（国内）
     */
//    public static final String MENU_CODE_JUKENHYO_HASSOBI_KOKUNAI = "91";
    /**
     * 受験科目　医科
     */
    public static final String JUKEN_KAMOKU_IKA = "1";
    /**
     * 受験科目　歯科
     */
    public static final String JUKEN_KAMOKU_SHIKA = "2";
    /**
     * 個人団体区分　個人
     */
    public static final String KOJIN_DANTAI_KBN_KOJIN = "1";
    /**
     * 個人団体区分　団体
     */
    public static final String KOJIN_DANTAI_KBN_DANTAI = "2";
    /**
     * 申込媒体区分　インターネット
     */
    public static final String MOSHIKOMI_BAITAI_KBN_INT = "1";
    /**
     * 申込媒体区分　郵送
     */
    public static final String MOSHIKOMI_BAITAI_KBN_YUSO = "2";
    /**
     * 縮小画像保存関連プロパティ取得用 キー
     */
    public static final String PROPERTY_KEY_PICTURE_PATH_SMALL_HOZON = "picture_path_small_hozon";
    /**
     * 作業用画像関連プロパティ取得用 キー
     */
    public static final String PROPERTY_KEY_PICTURE_PATH_TMP = "picture_path_tmp";
    /**
     * 保存用画像関連プロパティ取得用 キー
     */
    public static final String PROPERTY_KEY_PICTURE_PATH_HOZON = "picture_path_hozon";
    /**
     * 作業用縮小画像関連プロパティ取得用 キー
     */
    public static final String PROPERTY_KEY_PICTURE_PATH_SMALL_TMP = "picture_path_small_tmp";
    /**
     * 保存用縮小画像ディレクトリ
     */
    public static final String PICTURE_PATH_SMALL_HOZON = PropertyUtility.getProperty(BUSINESS_CODE
            + PROPERTY_KEY_PICTURE_PATH_SMALL_HOZON);
    /**
     * 作業用画像ディレクトリ
     */
    public static final String PICTURE_PATH_TMP = PropertyUtility.getProperty(BUSINESS_CODE
            + PROPERTY_KEY_PICTURE_PATH_TMP);
    /**
     * 保存用画像ディレクトリ
     */
    public static final String PICTURE_PATH_HOZON = PropertyUtility.getProperty(BUSINESS_CODE
            + PROPERTY_KEY_PICTURE_PATH_HOZON);
    /**
     * 作業用縮小画像ディレクトリ
     */
    public static final String PICTURE_PATH_SMALL_TMP = PropertyUtility.getProperty(BUSINESS_CODE
            + PROPERTY_KEY_PICTURE_PATH_SMALL_TMP);
    /**
     * 顔写真ファイルの拡張子
     */
    public static final String SUFFIX = ".jpg";
    /**
     * 画像ファイルサイズの最大値(1MB)
     */
    public static final int MAX_PHOTO_FILE_SIZE = 1000000;
    /**
     * 画像ファイルサイズの最小値(KB)
     */
    public static final int MIN_PHOTO_FILE_SIZE = 16;
    /**
     * 画像ファイルの縦の長さの最大値
     */
    public static final int MAX_PHOTO_HEIGHT = 915;
    /**
     * 画像ファイルの縦の長さの最小値
     */
    public static final int MIN_PHOTO_HEIGHT = 749;
    /**
     * 画像ファイルの横の長さの最大値
     */
    public static final int MAX_PHOTO_WIDTH = 704;
    /**
     * 画像ファイルの横の長さの最小値
     */
    public static final int MIN_PHOTO_WIDTH = 576;
    /**
     * 画像ファイルで使用可能な拡張子
     */
    public static final String[] PHOTO_FILE_FIXED_SUFFIX = {"jpeg", "jpg", "jpe", "JPEG", "JPG", "JPE"};
    /**
     * 決済方法区分　クレジットカード決済
     */
    public static final String KESSAI_HOHO_KBN_CRD = "1";
    /**
     * 決済方法区分　コンビニ決済
     */
    public static final String KESSAI_HOHO_KBN_CVS = "2";
    /**
     * 決済方法区分　ペイジー決済
     */
    public static final String KESSAI_HOHO_KBN_PAYEASY = "3";
    /**
     * 決済方法区分　ゆうちょ銀行振込
     */
    public static final String KESSAI_HOHO_KBN_POST = "4";
    /**
     * 決済方法区分　三菱東京ＵＦＪ銀行振込
     */
    public static final String KESSAI_HOHO_KBN_BANK = "5";
    /**
     * 決済方法区分　団体決済
     */
    public static final String KESSAI_HOHO_KBN_DNT = "D";
    /**
     * 決済状況区分　払込未確認
     */
    public static final String KESSAI_JOKYO_KBN_MI = "0";
    /**
     * 決済状況区分　払込確認
     */
    public static final String KESSAI_JOKYO_KBN_KAKUNIN = "1";
    /**
     * 決済状況区分　不足入金
     */
    public static final String KESSAI_JOKYO_KBN_FUSOKU = "2";
    /**
     * 決済状況区分　過大入金
     */
    public static final String KESSAI_JOKYO_KBN_KADAI = "3";
    /**
     * 決済状況区分　決済取消
     */
    public static final String KESSAI_JOKYO_KBN_TORIKESI = "9";
    /**
     * 手続状況区分　受付前
     */
    public static final String TETSUDUKI_JOKYO_UKETSUKE_MAE = "0";
    /**
     * 手続状況区分　承認前
     */
    public static final String TETSUDUKI_JOKYO_SHONIN_MAE = "1";
    /**
     * 手続状況区分　仮受付
     */
    public static final String TETSUDUKI_JOKYO_KBN_KARI = "2";
    /**
     * 手続状況区分　受付完了
     */
    public static final String TETSUDUKI_JOKYO_KBN_KANRYO = "3";
    /**
     * 手続状況区分　受付取消
     */
    public static final String TETSUDUKI_JOKYO_KBN_TORIKESHI = "9";
    /**
     * 補正依頼区分　依頼なし
     */
    public static final String HOSEI_IRAI_KBN_MI = "0";
    /**
     * 補正依頼区分　補正依頼
     */
    public static final String HOSEI_IRAI_KBN_IRAI = "1";
    /**
     * 補正依頼区分　補正済み
     */
    public static final String HOSEI_IRAI_KBN_HOSEI = "2";
    /**
     * 補正依頼区分　補正確認済み
     */
    public static final String HOSEI_IRAI_KBN_KAKUNIN = "3";
    /**
     * 発送先区分　団体所在地発送
     */
    public static final String HASSOSAKI_KBN_DANTAI = "1";
    /**
     * 発送先区分　個人発送
     */
    public static final String HASSOSAKI_KBN_KOJIN = "0";
    /**
     * 決済種別　カード決済
     */
    public static final String KESSAI_SHUBETSU_CARD = "51";
    /**
     * 決済種別　ペイジー決済
     */
    public static final String KESSAI_SHUBETSU_PAYEASY = "84";
    /**
     * 決済コンビニ種別　ローソン・セイコーマート・ミニストップ
     */
    public static final String KESSAI_CONVENIENCE_SHUBETSU_LAWSON = "01";
    /**
     * 決済コンビニ種別　セブン-イレブン
     */
    public static final String KESSAI_CONVENIENCE_SHUBETSU_SEVEN = "02";
    /**
     * 決済コンビニ種別　ファミリーマート
     */
    public static final String KESSAI_CONVENIENCE_SHUBETSU_FAMI = "03";
    /**
     * 決済コンビニ種別　サークルＫ・サンクス・デイリーヤマザキ・ヤマザキデイリーストアー
     */
    public static final String KESSAI_CONVENIENCE_SHUBETSU_CIRCLE = "73";
    /**
     * 決済コンビニ支払い番号名称 セブン-イレブン
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_SEVEN = "払込票番号";
    /**
     * 決済コンビニ支払い番号名称 ローソン、ミニストップ、セイコーマート
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_LAWSON = "受付番号";
    /**
     * 決済コンビニ支払い番号名称 ファミリーマート
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI = "第１番号（企業コード）-第２番号（注文番号）";
    /**
     * 決済コンビニ支払い番号名称 ファミリーマート
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI1 = "第１番号（企業コード）";
    /**
     * 決済コンビニ支払い番号名称 ファミリーマート
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_FAMI2 = "第２番号（注文番号）";
    /**
     * 決済コンビニ支払い番号名称 オンライン決済
     */
    public static final String KESSAI_CONVENIENCE_SHIHARAI_NO_CIRCLE = "オンライン決済番号";
    /**
     * 決済ログメッセージ種別 決済要求（送信値）
     */
    public static final String MESSAGE_SHUBETSU_REQUEST_SEND = "1";
    /**
     * 決済ログメッセージ種別 決済要求（受信値）
     */
    public static final String MESSAGE_SHUBETSU_REQUEST_RET = "2";
    /**
     * 決済ログメッセージ種別 入金通知
     */
    public static final String MESSAGE_SHUBETSU_RECEIPT = "3";
    /**
     * 決済ログメッセージ種別 取消通知
     */
    public static final String MESSAGE_SHUBETSU_CANCEL = "9";
    /**
     * 決済ログメッセージ本体の区切り文字
     */
    public static final String KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR = "\t";
    /**
     * 決済確定フラグ
     */
    public static final String KESSAI_KAKUTEI_CODE = "1";
    /**
     * 商品名
     */
    public static final String SHOHIN_NAME = "受験手数料";
    /**
     * 決済代行会社コード　デジタルチェック
     */
    public static final String KESSAI_DAIKOU_KAISHA_CODE_DC = "DC";
    /**
     * 決済エラーコード　メンテナンス
     */
    public static final String KESSAI_ERROR_MENTE = "ERR003";
    /**
     * 決済エラーコード　カード認証エラー1（カード認証でエラー）
     */
    public static final String KESSAI_ERROR_CARD_NINSHO_1 = "CRE002";
    /**
     * 決済エラーコード　カード認証エラー2（不正なカード）
     */
    public static final String KESSAI_ERROR_CARD_NINSHO_2 = "CRE003";
    /**
     * 決済エラーコード　接続エラー（カード認証サーバー）
     */
    public static final String KESSAI_ERROR_CONNECT_CARD = "CRE001";
    /**
     * 決済エラーコード　接続エラー（ファミマ）
     */
    public static final String KESSAI_ERROR_CONNECT_FAMIMA_1 = "FMM001";
    public static final String KESSAI_ERROR_CONNECT_FAMIMA_2 = "FMM002";
    /**
     * 決済エラーコード　接続エラー（ローソン）
     */
    public static final String KESSAI_ERROR_CONNECT_LAWSON_1 = "LPP001";
    public static final String KESSAI_ERROR_CONNECT_LAWSON_2 = "LPP002";
    /**
     * 決済エラーコード　接続エラー（セブン）
     */
    public static final String KESSAI_ERROR_CONNECT_SEVEN_1 = "SVN001";
    public static final String KESSAI_ERROR_CONNECT_SEVEN_2 = "SVN002";
    /**
     * 決済エラーコード　接続エラー（オンライン）
     */
    public static final String KESSAI_ERROR_CONNECT_ONLINE_1 = "WNT001";
    public static final String KESSAI_ERROR_CONNECT_ONLINE_2 = "WNT002";
    /**
     * 決済エラーカード認証エラー配列
     */
    public static final String[][] DISP_KESSAI_ERROR_NINSHO = {
        {KESSAI_ERROR_CARD_NINSHO_1, "1"},
        {KESSAI_ERROR_CARD_NINSHO_2, "1"}
    };
    /**
     * 決済エラー接続エラー配列
     */
    public static final String[][] DISP_KESSAI_ERROR_CONNECT = {
        {KESSAI_ERROR_CONNECT_CARD, "1"},
        {KESSAI_ERROR_CONNECT_FAMIMA_1, "1"},
        {KESSAI_ERROR_CONNECT_FAMIMA_2, "1"},
        {KESSAI_ERROR_CONNECT_LAWSON_1, "1"},
        {KESSAI_ERROR_CONNECT_LAWSON_2, "1"},
        {KESSAI_ERROR_CONNECT_SEVEN_1, "1"},
        {KESSAI_ERROR_CONNECT_SEVEN_2, "1"},
        {KESSAI_ERROR_CONNECT_ONLINE_1, "1"},
        {KESSAI_ERROR_CONNECT_ONLINE_2, "1"}
    };
    /**
     * 一覧ソート順　標準
     */
    public static final String SORT_CODE_DEFAULT = "0";
    /**
     * 一覧ソート順　カナ昇順
     */
    public static final String SORT_CODE_KANA_ASC = "1";
    /**
     * 一覧ソート順　カナ降順
     */
    public static final String SORT_CODE_KANA_DESC = "2";
    /**
     * 一覧ソート順　表示
     */
    public static final String[][] DISP_SORT_CODE = {
        {"", "標準"},
        {SORT_CODE_KANA_ASC, "カナ氏名：昇順"},
        {SORT_CODE_KANA_DESC, "カナ氏名：降順"}};
    /**
     * １ページ　データ件数
     */
    public static final int PAGE_KENSU = 15;
    /**
     * 受験科目　表示
     */
    public static final String[][] DISP_JUKEN_KAMOKU = {
        {JUKEN_KAMOKU_IKA, "医科"},
        {JUKEN_KAMOKU_SHIKA, "歯科"}
    };
    /**
     * 生年月日年号　表示
     */
    public static final String[][] DISP_BIRTHDAY_ERA_COD = {{"3", "昭和"}, {"4", "平成"}};
    /**
     * 申込媒体区分　表示
     */
    public static final String[][] DISP_MOSHIKOMI_BAITAI_KBN = {{MOSHIKOMI_BAITAI_KBN_INT, "インターネット"}, {MOSHIKOMI_BAITAI_KBN_YUSO, "郵送"}};
    /**
     * 個人団体区分　表示
     */
    public static final String[][] DISP_KOJIN_DANTAI_KBN = {{KOJIN_DANTAI_KBN_KOJIN, "個人申込"}, {KOJIN_DANTAI_KBN_DANTAI, "団体申込"}};
    /**
     * 性別　表示
     */
    public static final String[][] DISP_SEX_CODE = {{"1", "男性"}, {"2", "女性"}};
    /**
     * 受講経験　表示
     */
    public static final String[][] DISP_JUKO_KEIKEN_CODE = {{"1", "あり"}, {"2", "なし"}};
    /**
     * 実務経験　表示
     */
    public static final String[][] DISP_JITSUMU_KEIKEN_CODE = {{"1", "あり"}, {"2", "なし"}};
    /**
     * 決済方法区分　表示
     */
    public static final String[][] DISP_KESSAI_HOHO_KBN = {{KESSAI_HOHO_KBN_CRD, "クレジットカード決済"}, {KESSAI_HOHO_KBN_CVS, "コンビニ決済"}, {KESSAI_HOHO_KBN_PAYEASY, "ペイジー決済"}, {KESSAI_HOHO_KBN_POST, "ゆうちょ銀行振込"}, {KESSAI_HOHO_KBN_BANK, "三菱東京ＵＦＪ銀行振込"}};
    /**
     * 決済方法区分略称　表示
     */
    public static final String[][] DISP_KESSAI_HOHO_KBN_EX = {{KESSAI_HOHO_KBN_CRD, "クレジット"}, {KESSAI_HOHO_KBN_CVS, "コンビニ"}, {KESSAI_HOHO_KBN_PAYEASY, "ペイジー"}, {KESSAI_HOHO_KBN_POST, "ゆうちょ振込"}, {KESSAI_HOHO_KBN_BANK, "銀行振込"}};
    /**
     * 決済状況区分　表示
     */
    public static final String[][] DISP_KESSAI_JOKYO_KBN = {{KESSAI_JOKYO_KBN_MI, "払込未確認"}, {KESSAI_JOKYO_KBN_KAKUNIN, "払込確認"}, {KESSAI_JOKYO_KBN_FUSOKU, "不足入金"}, {KESSAI_JOKYO_KBN_KADAI, "過大入金"}};
    /**
     * 手続状況区分　表示
     */
    public static final String[][] DISP_TETSUDUKI_JOKYO_KBN = {{TETSUDUKI_JOKYO_UKETSUKE_MAE, "受付前"}, {TETSUDUKI_JOKYO_SHONIN_MAE, "承認前"}, {TETSUDUKI_JOKYO_KBN_KARI, "仮受付"}, {TETSUDUKI_JOKYO_KBN_KANRYO, "受付完了"}, {TETSUDUKI_JOKYO_KBN_TORIKESHI, "受付取消"}};
    /**
     * 補正依頼区分　表示
     */
    public static final String[][] DISP_HOSEI_IRAI_KBN = {{HOSEI_IRAI_KBN_MI, "依頼なし"}, {HOSEI_IRAI_KBN_IRAI, "補正依頼"}, {HOSEI_IRAI_KBN_HOSEI, "補正済み"}, {HOSEI_IRAI_KBN_KAKUNIN, "補正確認済み"}};
    /**
     * 発送先区分　表示
     */
    public static final String[][] DISP_HASSOSAKI_KBN = {{HASSOSAKI_KBN_DANTAI, "団体所在地"}, {HASSOSAKI_KBN_KOJIN, "各受験申込者の住所"}};
    /**
     * 決済コンビニ種別　表示
     */
    public static final String[][] DISP_KESSAI_CONVENIENCE_SHUBETSU = {
        {KESSAI_CONVENIENCE_SHUBETSU_LAWSON, "ローソン・セイコーマート・ミニストップ"},
        {KESSAI_CONVENIENCE_SHUBETSU_SEVEN, "セブン-イレブン"},
        {KESSAI_CONVENIENCE_SHUBETSU_FAMI, "ファミリーマート"},
        {KESSAI_CONVENIENCE_SHUBETSU_CIRCLE, "サークルＫ・サンクス・デイリーヤマザキ・ヤマザキデイリーストアー"}};
    /**
     * パスワード質問コード　表示
     */
    public static final String[][] DISP_PASSWD_QUESTION_COD = {{"1", "好きな食べ物は？"}, {"2", "母親の旧姓は？"}, {"3", "ペットの名前は？"}, {"4", "得意な料理は？"}, {"5", "好きな映画は？"}};
    /**
     * フラグON/OFF　表示
     */
    public static final String[][] DISP_FLG = {{MiwConstants.FLG_OFF, "OFF"}, {MiwConstants.FLG_ON, "ON"}};
    /**
     * 都道府県名称
     */
    public static final String[][] DISP_TODOFUKEN_CODE = {
        {"北海道", "北海道"}, {"青森県", "青森県"}, {"岩手県", "岩手県"}, {"宮城県", "宮城県"}, {"秋田県", "秋田県"},
        {"山形県", "山形県"}, {"福島県", "福島県"}, {"茨城県", "茨城県"}, {"栃木県", "栃木県"}, {"群馬県", "群馬県"},
        {"埼玉県", "埼玉県"}, {"千葉県", "千葉県"}, {"東京都", "東京都"}, {"神奈川県", "神奈川県"}, {"新潟県", "新潟県"},
        {"富山県", "富山県"}, {"石川県", "石川県"}, {"福井県", "福井県"}, {"山梨県", "山梨県"}, {"長野県", "長野県"},
        {"岐阜県", "岐阜県"}, {"静岡県", "静岡県"}, {"愛知県", "愛知県"}, {"三重県", "三重県"}, {"滋賀県", "滋賀県"},
        {"京都府", "京都府"}, {"大阪府", "大阪府"}, {"兵庫県", "兵庫県"}, {"奈良県", "奈良県"}, {"和歌山県", "和歌山県"},
        {"鳥取県", "鳥取県"}, {"島根県", "島根県"}, {"岡山県", "岡山県"}, {"広島県", "広島県"}, {"山口県", "山口県"},
        {"徳島県", "徳島県"}, {"香川県", "香川県"}, {"愛媛県", "愛媛県"}, {"高知県", "高知県"}, {"福岡県", "福岡県"},
        {"佐賀県", "佐賀県"}, {"長崎県", "長崎県"}, {"熊本県", "熊本県"}, {"大分県", "大分県"}, {"宮崎県", "宮崎県"},
        {"鹿児島県", "鹿児島県"}, {"沖縄県", "沖縄県"}
    };
    /**
     * 受験手数料名称
     */
    public static final String JUKEN_CHARGE_NAME = "受験手数料";
    /**
     * 最大文字数　フリガナ
     */
    public static final int MAX_LEN_SHIMEI_KANA = 100;
    /**
     * 最大文字数　氏名
     */
    public static final int MAX_LEN_SHIMEI = 100;
    /**
     * 最大文字数　氏名姓
     */
    public static final int MAX_LEN_SHIMEI_SEI = 50;
    /**
     * 最大文字数　氏名名
     */
    public static final int MAX_LEN_SHIMEI_MEI = 50;
    /**
     * 最大文字数　電話番号
     */
    public static final int MAX_LEN_TEL = 20;
    /**
     * 最大文字数　内線番号
     */
    public static final int MAX_LEN_EXT_TEL = 10;
    /**
     * 最小文字数　電話番号（数字のみ）
     */
    public static final int MIN_LEN_TEL = 9;
    /**
     * 最大文字数　メールアドレス
     */
    public static final int MAX_LEN_MAIL = 50;
    /**
     * 最大文字数　パスワード
     */
    public static final int MAX_LEN_PASSWD = 8;
    /**
     * 最小文字数　パスワード
     */
    public static final int MIN_LEN_PASSWD = 8;
    /**
     * 文字数　パスワード
     */
    public static final int LEN_PASSWD = 8;
    /**
     * 最大文字数　ご本人様確認用の回答
     */
    public static final int MAX_LEN_PASSWD_ANSWER = 20;
    /**
     * 最大文字数　通信欄
     */
    public static final int MAX_LEN_MOSHIKOMI_MEMO = 200;
    /**
     * 最大文字数　勤務先名
     */
    public static final int MAX_LEN_KINMUSAKI_NAME = 30;
    /**
     * 最大文字数　学校名
     */
    public static final int MAX_LEN_GAKKO_NAME = 30;
    /**
     * 最大文字数　学校名・勤務先名
     */
    public static final int MAX_LEN_GAKKO_KINMUSAKI_NAME = 30;
    /**
     * 最大文字数　学年
     */
    public static final int MAX_LEN_GAKUNEN = 1;
    /**
     * 最大文字数　身障者配慮内容
     */
    public static final int MAX_LEN_SHINSHOSHA_HAIRYO_NAIYO = 150;
    /**
     * 最大文字数　アンケートその他内容
     */
    public static final int MAX_LEN_SONOTA = 100;
    /**
     * 最大文字数　カード番号（分割）
     */
    public static final int MAX_LEN_CARD_NO = 4;
    /**
     * 最大文字数　カード番号
     */
    public static final int MAX_LEN_ALL_CARD_NO = 16;
    /**
     * 最小文字数　カード番号
     */
    public static final int MIN_LEN_ALL_CARD_NO = 13;
    /**
     * 最大文字数　団体名
     */
    public static final int MAX_LEN_DNT_NAME = 20;
    /**
     * 最大文字数　団体名称フリガナ
     */
    public static final int MAX_LEN_DNT_NAME_KANA = 50;
    /**
     * 最大文字数　団体所在地（１項目）
     */
    public static final int MAX_LEN_DNT_JYUSHO = 20;
    /**
     * 最大文字数　団体所在地（ビル名・建物名・部屋番号等）
     */
    public static final int MAX_LEN_DNT_JYUSHO_ETC = 25;
    /**
     * 最大文字数　団体所在地（結合）
     */
    public static final int MAX_LEN_DNT_JYUSHO_ALL = 45;
    /**
     * 最大文字数　団体申込者数
     */
    public static final int MAX_LEN_DNT_MOSHIKOMISHA_SU = 4;
    /**
     * 指定文字数　生年月日（年）
     */
    public static final int EQUAL_LEN_BIRTH_YEAR = 2;
    /**
     * 指定文字数　生年月日（月）
     */
    public static final int EQUAL_LEN_BIRTH_MONTH = 2;
    /**
     * 指定文字数　生年月日（日）
     */
    public static final int EQUAL_LEN_BIRTH_DAY = 2;
    /**
     * 指定文字数　有効期限 (年月共通)
     */
    public static final int EQUAL_LEN_EXP = 2;
    /**
     * 指定文字数　団体コード
     */
    public static final int EQUAL_LEN_DANTAI_CODE = 5;
    /**
     * 指定文字数　受験番号
     */
    public static final int EQUAL_LEN_JUKEN_NO = 14;
    /**
     * 指定文字数　準会場コード
     */
    public static final int EQUAL_LEN_JUNKAIJO_CODE = 6;
    /**
     * 文字数　ユーザーＩＤ
     */
    public static final int LENGTH_USER_ID = 8;
    /**
     * 文字数　ユーザーＩＤのシーケンス
     */
    public static final int LENGTH_USER_ID_SEQ = 7;
    /**
     * 文字数　団体コード
     */
    public static final int LENGTH_DANTAI_ID = 5;
    /**
     * 文字数　団体コードのシーケンス
     */
    public static final int LENGTH_DANTAI_ID_SEQ = 4;
    /**
     * 文字数　団体申込者登録用ID
     */
    public static final int LENGTH_DANTAI_JUKEN_ID = 6;
    /**
     * 文字数　団体申込者登録用IDのシーケンス
     */
    public static final int LENGTH_DANTAI_JUKEN_ID_SEQ = 4;
    /**
     * 文字数　生年月日
     */
    public static final int LENGTH_BIRTHDAY = 8;
    /**
     * 文字数　郵便番号１
     */
    public static final int LENGTH_YUBIN_1 = 3;
    /**
     * 文字数　郵便番号２
     */
    public static final int LENGTH_YUBIN_2 = 4;
    /**
     * 文字数　住所（１項目）
     */
    public static final int LENGTH_JUSHO_KOKUNAI = 20;
    /**
     * 文字数　住所（ビル名・建物名・部屋番号）
     */
    public static final int LENGTH_JUSHO_KOKUNAI_ETC = 25;
    /**
     * 文字数　住所（結合）
     */
    public static final int LENGTH_JUSHO_KOKUNAI_ALL = 45;
    /**
     * 文字数　学校コード
     */
    public static final int LENGTH_GAKKO_CODE = 4;
    /**
     * 文字数　決済SEQ
     */
    public static final int LENGTH_KESSAI_SEQ = 9;
    /**
     * コンビニ決済説明ページURL
     */
    public static final String CONVENI_URL_SEVEN_PC = "http://www.digitalcheck.co.jp/service/guide/seven.html";
    public static final String CONVENI_URL_SEVEN_MOBILE = "http://0555.co.jp/seven_info.html";
    public static final String CONVENI_URL_FAMIMA_PC = "http://www.digitalcheck.co.jp/service/guide/famima.html";
    public static final String CONVENI_URL_FAMIMA_MOBILE = "http://0555.co.jp/fami_info.html";
    public static final String CONVENI_URL_LAWSON_PC = "http://www.digitalcheck.co.jp/service/guide/lawson.html";
    public static final String CONVENI_URL_LAWSON_MOBILE = "http://0555.co.jp/lawson_info.html";
    public static final String CONVENI_URL_SEICO_PC = "http://www.digitalcheck.co.jp/service/guide/seico.html";
    public static final String CONVENI_URL_SEICO_MOBILE = "http://0555.co.jp/seico_info.html";
    public static final String CONVENI_URL_MINISTOP_PC = "http://www.digitalcheck.co.jp/service/guide/ministop.html";
    public static final String CONVENI_URL_MINISTOP_MOBILE = "http://0555.co.jp/ministop_info.html";
    public static final String CONVENI_URL_ONLINE_PC = "http://www.digitalcheck.co.jp/service/guide/onlinekessai.html";
    public static final String CONVENI_URL_ONLINE_MOBILE = "http://0555.co.jp/online_info.html";
}
