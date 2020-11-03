package jp.co.nii.miw.business;

/**
 * <p>タイトル: 文字に関する共通クラス</p>
 * <p>説明: 文字に関する変換、編集などを行う。</p>
 * <p>著作権: Copyright (c) 2012</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 *
 */
public class MiwStringUtility {

    /**
     * 改行コードを<br>に置き換えた文字列を取得する
     * @param value
     * @return 
     */
    public static String getNewLineReplaceBr(String value) {
        String retStr = value;
        for (int i = 0; i < MiwConstants.NEW_LINE_CODES.length; i++) {
            retStr = retStr.replace(MiwConstants.NEW_LINE_CODES[i], "<br>");
        }
        return retStr;
    }

    /**
     * 改行コードをブランクに置き換えた文字列を取得する
     * @param value
     * @return 
     */
    public static String getNewLineReplaceBlank(String value) {
        String retStr = value;
        for (int i = 0; i < MiwConstants.NEW_LINE_CODES.length; i++) {
            retStr = retStr.replace(MiwConstants.NEW_LINE_CODES[i], "");
        }
        return retStr;
    }

    /**
     * 文字列と文字列を（）で結合
     * @param str1
     * @param str2
     * @return
     */
    public static String getConcateWithParenthesis(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append(str1);
        if (!(str2 == null || str2.equals(""))) {
            sb.append("(");
            sb.append(str2);
            sb.append(")");
        }
        return sb.toString();
    }

    /**
     * ()で結合された文字列を分割する（）がない場合は空を返却
     * @param str1
     * @return 
     */
    public static String[] splitConcateWithParenthesis(String str1) {
        String[] value = new String[2];
        if (str1 == null || str1.equals("")) {
            for (int i = 0; i < value.length; i++) {
                value[i] = "";
            }
        } else {
            String[] tmp = str1.split(MiwConstants.JUSHO_SPLIT_STRING_KAKKO, -1);
            value[0] = tmp[0];
            value[1] = tmp.length == 1 ? "" : tmp[1].replace(")", "");
        }
        return value;

    }
    
    /**
     * 日付をスラッシュ編集する
     * @param ymd 日付項目（8桁 yyyyMMdd）
     * @throws Exception
     */
    public static String formatDateSlash(String ymd) {
        try {
            String y = ymd.substring(0, 4);
            String m = ymd.substring(4, 6);
            String d = ymd.substring(6, 8);
            return y + "/" + m + "/" + d;
        } catch (Exception e) {
            return "";
        }        
    }
    
    /**
     * 時間をスラッシュ編集する
     * @param hms 時間項目（6桁 hhMMss）
     * @throws Exception
     */
    public static String formatTimeColon(String hms) {
        try {
            String y = hms.substring(0, 2);
            String m = hms.substring(2, 4);
            String d = hms.substring(4, 6);
            return y + ":" + m + ":" + d;
        } catch (Exception e) {
            return "";
        }        
    }
}