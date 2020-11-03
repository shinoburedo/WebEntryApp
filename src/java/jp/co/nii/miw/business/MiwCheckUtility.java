package jp.co.nii.miw.business;

import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.business.*;

import java.io.UnsupportedEncodingException;

import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>タイトル: チェック共通クラス</p>
 * <p>説明: 入力値のチェックを行う。</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: AAA株式会社</p>
 * @author ＷＥＢ開発チーム
 *
 */
public class MiwCheckUtility {

    /**
     * 文字列の文字コードをチェックする
     * 半角英字大文字、半角スペース以外はエラー
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMojicodeRomaji(String value) {

        for (int i = 0; i < value.length(); i++) {
            if (((value.charAt(i) < 'A') || (value.charAt(i) > 'Z')) && value.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字のバイト数をチェックする
     * ２バイトの場合エラー
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean is1Byte(String value) {

        for (int i = 0; i < value.length(); i++) {
            String moji = value.substring(i, i + 1);
            if (moji.getBytes().length == 1) {
                //１バイト文字
            } else {
                //２バイト文字
                return false;
            }
        }
        return true;
    }

    /**
     * 住所（海外）の文字コードチェック
     * 大文字アルファベット、記号以外はエラー
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isUpperAlphabetOrKigo(String value) {

        for (int i = 0; i < value.length(); i++) {
            if ((value.charAt(i) >= ' ') && (value.charAt(i) <= '~')) {
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列の文字コードをチェックする
     * 半角英字小文字が含まれる場合エラー
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isUpper(String value) {

        for (int i = 0; i < value.length(); i++) {
            if ((value.charAt(i) >= 'a') && (value.charAt(i) <= 'z')) {
                return false;
            }
        }
        return true;
    }

    /**
     * 文字列がの文字コードをチェックする
     * 半角英数字記号、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
     * 「。」「、」も可能
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isHankakuAndZenkaku(String value) {
        boolean state;
        String targetHex = new String();
        state = true;

        try {
            //Stringをchar配列に変換
            char[] charArray = value.toCharArray();

            //全角２バイト文字だったら、文字コードで範囲チェック
            // 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
            for (int i = 0; i < value.length(); i++) {
                if (CheckUtility.isHankaku(String.valueOf(value.charAt(i)))) {
                    //半角だったら
                    if ((value.charAt(i) < ' ') || (value.charAt(i) > '~')) {
                        return false;
                    }
                } else {
                    //全角だったら
                    char c = charArray[i];
                    targetHex = Integer.toHexString(CheckUtility.getSJISByte(c));
                    //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("20") < 0
                            || targetHex.compareToIgnoreCase("7E") > 0)
                            && (targetHex.compareToIgnoreCase("824f") < 0
                            || targetHex.compareToIgnoreCase("8396") > 0)
                            && (targetHex.compareToIgnoreCase("889F") < 0
                            || targetHex.compareToIgnoreCase("EAA4") > 0)) {
                        if ((targetHex.compareToIgnoreCase("8140") != 0) && //'　'はOK
                                (targetHex.compareToIgnoreCase("8141") != 0) && //'、'はOK
                                (targetHex.compareToIgnoreCase("8142") != 0) && //'。'はOK
                                (targetHex.compareToIgnoreCase("8143") != 0) && //'，'はOK
                                (targetHex.compareToIgnoreCase("8143") != 0) && //'，'はOK
                                (targetHex.compareToIgnoreCase("8144") != 0) && //'．'はOK
                                (targetHex.compareToIgnoreCase("8145") != 0) && //'・'はOK
                                (targetHex.compareToIgnoreCase("8146") != 0) && //'：'はOK
                                (targetHex.compareToIgnoreCase("8147") != 0) && //'；'はOK
                                (targetHex.compareToIgnoreCase("8148") != 0) && //'？'はOK
                                (targetHex.compareToIgnoreCase("8149") != 0) && //'！'はOK
                                (targetHex.compareToIgnoreCase("814a") != 0) && //'゛'はOK
                                (targetHex.compareToIgnoreCase("814b") != 0) && //'゜'はOK
                                (targetHex.compareToIgnoreCase("8151") != 0) && //'＿'はOK
                                (targetHex.compareToIgnoreCase("8155") != 0) && //'ゞ'はOK
                                (targetHex.compareToIgnoreCase("8158") != 0) && //'々'はOK
                                (targetHex.compareToIgnoreCase("815b") != 0) && //'ー'はOK
                                (targetHex.compareToIgnoreCase("815e") != 0) && //'／'はOK
                                (targetHex.compareToIgnoreCase("8166") != 0) && //'’'はOK
                                (targetHex.compareToIgnoreCase("8168") != 0) && //'”'はOK
                                (targetHex.compareToIgnoreCase("8169") != 0) && //'（'はOK
                                (targetHex.compareToIgnoreCase("816a") != 0) && //'）'はOK
                                (targetHex.compareToIgnoreCase("817b") != 0) && //'＋'はOK
                                (targetHex.compareToIgnoreCase("817c") != 0) && //'?'はOK
                                (targetHex.compareToIgnoreCase("8181") != 0) && //'＝'はOK
                                (targetHex.compareToIgnoreCase("8183") != 0) && //'＜'はOK
                                (targetHex.compareToIgnoreCase("8184") != 0) && //'＞'はOK
                                (targetHex.compareToIgnoreCase("818f") != 0) && //'￥'はOK
                                (targetHex.compareToIgnoreCase("8193") != 0) && //'％'はOK
                                (targetHex.compareToIgnoreCase("8194") != 0) && //'＃'はOK
                                (targetHex.compareToIgnoreCase("8195") != 0) && //'＆'はOK
                                (targetHex.compareToIgnoreCase("8196") != 0) && //'＊'はOK
                                (targetHex.compareToIgnoreCase("8197") != 0) //'＠'はOK
                                ) {
                            state = false;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            state = false;
        }
        return state;
    }
}