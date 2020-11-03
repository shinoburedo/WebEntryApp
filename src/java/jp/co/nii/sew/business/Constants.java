package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.PropertyUtility;

/**
 * 定数クラス<br>
 * @author
 * @history　n-ikezawa
 */
public class Constants {

    /**業務コード */
    public static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    /** フラグオン */
    public static final String FLG_ON = "1";
    /** フラグオフ */
    public static final String FLG_OFF = "0";
    /** 論理削除フラグ（有効）*/
    public static final String RONRI_SAKUJO_FLG_YUKO = "0";
    /** 論理削除フラグ（論理削除）*/
    public static final String RONRI_SAKUJO_FLG_MUKO = "1";
    /** 処理区分（INSERT）*/
    public static final String SHORI_KBN_INSERT = "I";
    /** 処理区分（UPDATE）*/
    public static final String SHORI_KBN_UPDATE = "U";
    /** 処理区分（論理削除）*/
    public static final String SHORI_KBN_DELETE = "D";

}