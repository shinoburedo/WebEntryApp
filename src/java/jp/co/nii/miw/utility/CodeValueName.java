/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.co.nii.miw.utility;

import jp.co.nii.miw.business.MiwConstants;

/**
 *
 * @author T.Yamaguchi
 */
public class CodeValueName {

//    /**
//     * 都道府県名称を取得する
//     * @param value
//     * @return name
//     */
//    public static String getTodofukenCodeName(String value) {
//        String name = "";
//        for (int i = 0; i < MiwConstants.DISP_TODOFUKEN_CODE.length; i++) {
//            if (value.equals(MiwConstants.DISP_TODOFUKEN_CODE[i][0])) {
//                name = MiwConstants.DISP_TODOFUKEN_CODE[i][1];
//            }
//        }
//        return name;
//    }
//
//    /**
//     * 都道府県コードを取得する
//     * @param value
//     * @return name
//     */
//    public static String getTodofukenCode(String value) {
//        String name = "";
//        for (int i = 0; i < MiwConstants.DISP_TODOFUKEN_CODE.length; i++) {
//            if (value.equals(MiwConstants.DISP_TODOFUKEN_CODE[i][1])) {
//                name = MiwConstants.DISP_TODOFUKEN_CODE[i][0];
//            }
//        }
//        return name;
//    }
    
    /**
     * 受験科目名称を取得する。
     * @param value
     * @return 
     */
    public static String getJukenKamokuName(String value,String ki) {
        String name = "";
        for (int i = 0; i < MiwConstants.DISP_JUKEN_KAMOKU.length; i++) {
            if (value.equals(MiwConstants.DISP_JUKEN_KAMOKU[i][0])) {
                name = MiwConstants.DISP_JUKEN_KAMOKU[i][1];
            }
        }
        return name;
    }

    /**
     * 申込媒体区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getMoshikomiBaitaiKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_MOSHIKOMI_BAITAI_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_MOSHIKOMI_BAITAI_KBN[i][0])) {
                name = MiwConstants.DISP_MOSHIKOMI_BAITAI_KBN[i][1];
            }
        }
        return name;
    }
    
    /**
     * 個人団体区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getKojinDantaiKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_KOJIN_DANTAI_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_KOJIN_DANTAI_KBN[i][0])) {
                name = MiwConstants.DISP_KOJIN_DANTAI_KBN[i][1];
            }
        }
        return name;
    }
    
    /**
     * 生年月日元号コード名称を取得する。
     * @param value
     * @return 
     */
    public static String getBirthdayEraCodeName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_BIRTHDAY_ERA_COD.length; i++) {
            if (value.equals(MiwConstants.DISP_BIRTHDAY_ERA_COD[i][0])) {
                name = MiwConstants.DISP_BIRTHDAY_ERA_COD[i][1];
            }
        }
        return name;
    }
    
    /**
     * 性別コード名称を取得する。
     * @param value
     * @return 
     */
    public static String getSexCodeName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_SEX_CODE.length; i++) {
            if (value.equals(MiwConstants.DISP_SEX_CODE[i][0])) {
                name = MiwConstants.DISP_SEX_CODE[i][1];
            }
        }
        return name;
    }
    
    /**
     * 受講経験コード名称を取得する。
     * @param value
     * @return 
     */
    public static String getJukoKeikenCodeName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_JUKO_KEIKEN_CODE.length; i++) {
            if (value.equals(MiwConstants.DISP_JUKO_KEIKEN_CODE[i][0])) {
                name = MiwConstants.DISP_JUKO_KEIKEN_CODE[i][1];
            }
        }
        return name;
    }
    
    /**
     * 実務経験コード名称を取得する。
     * @param value
     * @return 
     */
    public static String getJitsumuKeikenCodeName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_JITSUMU_KEIKEN_CODE.length; i++) {
            if (value.equals(MiwConstants.DISP_JITSUMU_KEIKEN_CODE[i][0])) {
                name = MiwConstants.DISP_JITSUMU_KEIKEN_CODE[i][1];
            }
        }
        return name;
    }
    
    /**
     * 決済方法区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getKessaiHohoKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_HOHO_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_HOHO_KBN[i][0])) {
                name = MiwConstants.DISP_KESSAI_HOHO_KBN[i][1];
            }
        }
        return name;
    }
    
    /**
     * 決済方法区分名称（略称）を取得する。
     * @param value
     * @return 
     */
    public static String getKessaiHohoKbnNameEx(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_HOHO_KBN_EX.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_HOHO_KBN_EX[i][0])) {
                name = MiwConstants.DISP_KESSAI_HOHO_KBN_EX[i][1];
            }
        }
        return name;
    }
    
    /**
     * 決済状況区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getKessaiJokyoKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_JOKYO_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_JOKYO_KBN[i][0])) {
                name = MiwConstants.DISP_KESSAI_JOKYO_KBN[i][1];
            }
        }
        return name;
    }
    
    /**
     * 手続状況区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getTetsudukiJokyoKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_TETSUDUKI_JOKYO_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_TETSUDUKI_JOKYO_KBN[i][0])) {
                name = MiwConstants.DISP_TETSUDUKI_JOKYO_KBN[i][1];
            }
        }
        return name;
    }

    /**
     * 補正依頼区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getHoseiIraiKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_HOSEI_IRAI_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_HOSEI_IRAI_KBN[i][0])) {
                name = MiwConstants.DISP_HOSEI_IRAI_KBN[i][1];
            }
        }
        return name;
    }
    
    /**
     * 発送先区分名称を取得する。
     * @param value
     * @return 
     */
    public static String getHassosakiKbnName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_HASSOSAKI_KBN.length; i++) {
            if (value.equals(MiwConstants.DISP_HASSOSAKI_KBN[i][0])) {
                name = MiwConstants.DISP_HASSOSAKI_KBN[i][1];
            }
        }
        return name;
    }
    
     /**
     * 決済コンビニ種別名称を取得する。
     * @param value
     * @return 
     */
    public static String getKessaiConvenienceShubetsuName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_CONVENIENCE_SHUBETSU.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_CONVENIENCE_SHUBETSU[i][0])) {
                name = MiwConstants.DISP_KESSAI_CONVENIENCE_SHUBETSU[i][1];
            }
        }
        return name;
    }
    
    /**
     * パスワード質問コード名称を取得する。
     * @param value
     * @return 
     */
    public static String getPasswdQuestionCodeName(String value) {
        String name = "";

        for (int i = 0; i < MiwConstants.DISP_PASSWD_QUESTION_COD.length; i++) {
            if (value.equals(MiwConstants.DISP_PASSWD_QUESTION_COD[i][0])) {
                name = MiwConstants.DISP_PASSWD_QUESTION_COD[i][1];
            }
        }
        return name;
    }
    
    /**
     * 決済エラー（カード認証）かどうかを取得する。
     * @param value
     * @return 
     */
    public static String getKessaiErrorNinsho(String value) {
        String ret = "0";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_ERROR_NINSHO.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_ERROR_NINSHO[i][0])) {
                ret = MiwConstants.DISP_KESSAI_ERROR_NINSHO[i][1];
            }
        }
        return ret;
    }
    
    /**
     * 決済エラー（接続エラー）かどうかを取得する。
     * @param value
     * @return 
     */
    public static String getKessaiErrorConnect(String value) {
        String ret = "0";

        for (int i = 0; i < MiwConstants.DISP_KESSAI_ERROR_CONNECT.length; i++) {
            if (value.equals(MiwConstants.DISP_KESSAI_ERROR_CONNECT[i][0])) {
                ret = MiwConstants.DISP_KESSAI_ERROR_CONNECT[i][1];
            }
        }
        return ret;
    }
    
}
