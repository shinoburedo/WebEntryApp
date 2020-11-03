package jp.co.nii.miw.business;

/**
 * 定数クラス<br>
 * @author
 * @history T.Yamaguchi
 */
public class MypConstants {

    /** 発送物種別　全ての各種案内・通知*/
    public static final String HASSOBUTSU_SHUBETSU_ALL = "9";
    /** 発送物種別　受験票*/
    public static final String HASSOBUTSU_SHUBETSU_JUKENHYO = "1";
    /** 発送物種別　１次試験結果通知*/
    public static final String HASSOBUTSU_SHUBETSU_ICHIJI = "2";
    /** 発送物種別　最終結果通知*/
    public static final String HASSOBUTSU_SHUBETSU_SAISHU = "3";
    /** 発送先選択　現住所*/
    public static final String HASSOSAKI_SELECT_GENJUSHO = "1";
    /** 発送先選択　以下の住所*/
    public static final String HASSOSAKI_SELECT_IKA = "2";
    
    /** 発送物種別（２次あり）　表示*/
    public static final String[][] DISP_HASSOBUTSU_SHUBETSU_NIJI_ARI = {
        {HASSOBUTSU_SHUBETSU_JUKENHYO, "受験票"},
        {HASSOBUTSU_SHUBETSU_ICHIJI, "１次試験結果通知"},
        {HASSOBUTSU_SHUBETSU_SAISHU, "最終結果通知"},
        {HASSOBUTSU_SHUBETSU_ALL, "全ての各種案内・通知"}};
    
    /** 発送物種別（２次なし）　表示*/
    public static final String[][] DISP_HASSOBUTSU_SHUBETSU_NIJI_NASHI = {
        {HASSOBUTSU_SHUBETSU_JUKENHYO, "受験票"},
        {HASSOBUTSU_SHUBETSU_SAISHU, "最終結果通知"},
        {HASSOBUTSU_SHUBETSU_ALL, "全ての各種案内・通知"}};
    
}