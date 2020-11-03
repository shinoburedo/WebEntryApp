package jp.co.nii.sew.utility;

import java.io.UnsupportedEncodingException;


/**
 * <p>タイトル: チェック共通クラス</p>
 * <p>説明: 入力値のチェックを行う。</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 *
 */
public class CheckUtility {

    /**
     * 全角文字チェック<BR>
     * <b>解説：引数1にチェック対象の文字列を取り込み全文字が全角
     * （２バイト文字）をチェックする。適切ならばtrueが返る。</b>
     * @param str String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public final boolean isZenkakuWindows_31J(final String str) {

        boolean flg = false;

        try {

            /* Fukuda 2004.03.05 追加*/
            if (str != null) {

                int iLenB = 0;
                int lenByt = 0;

                iLenB = str.getBytes("Windows-31J").length;
                lenByt = str.length() * 2;

                if (iLenB == lenByt) {

                    flg = true;
                }
            }

        } catch (Exception ex) {

            flg = false;
        }

        return flg;
    }

    /** 指定文字数超過チェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 引数2に上限バイト数を指定して上限Byteを超えていないかチェックする。
     * 上限Byteと同等あるいは小さければtrueが返る。</b>
     * @param str String   チェック対象文字列
     * @param maxByte int   指定文字数
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public final boolean withinNumberOfCharacters(
            final String str,
            final int maxByte) {

        boolean ret = false;

        // 文字列のバイト数を求める
        int strByte = str.getBytes().length;

        // 文字列の長さをチェックする
        if (strByte <= maxByte) {

            ret = true;
        }

        return ret;
    }

    /**
     * 半角文字チェック\uFFFD<BR>
     * <b>解説：引数1にチェック対象の文字列を取り込み
     * 全文字が半角文字かをチェックする。
     * 適切ならばtrueが返る。全角文字を含む場合false。</b>
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isHankaku(String value) {

        try {
            int lenStr = value == null ? 0 : value.length();
            int lenByt = value.getBytes("Windows-31J").length;
            if (lenStr == lenByt) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 全角文字チェック<BR>
     * <b>解説：</b>引数1にチェック対象の文字列を取り込み全文字が全角（２バイト文字）をチェックする。
     * 適切ならばtrueが返る。
     * @param value String 検査対象の文字列（Shift_JIS変換前のバイト列）
     * @return boolean
     */
    public static final boolean isZenkaku(String value) {

        try {
            int lenStr = value == null ? 0 : value.length();
            int lenByt = value.getBytes("Windows-31J").length;
            if (lenStr * 2 == lenByt) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 配列に無い値チェック<br>
     * <b>解説：配列の中に値があればtrueが返る。</b>
     * @param value String　入力値
     * @param select String[]　配列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isPermissionSelect(String value, String[] select) {
        for (int i = 0; i < select.length; i++) {
            if (select[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文字列チェック<BR>
     * <b>解説：引数が文字列かどうかチェックする。
     * 文字列のみで構成されている場合trueが返る。</b>
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isChar(String value) {

        boolean blnReturn = true;

        if (value != null) {

            //Stringをchar配列に変換
            char[] charArray = value.toCharArray();

            for (int i = 0; i < value.length(); i++) {
                char c = charArray[i];
                int intByte = getSJISByte(c);

                if (!Character.isLetter(intByte)) {

                    blnReturn = false;
                    break;
                }
            }

        } else {

            blnReturn = false;
        }

        return blnReturn;
    }

    /**
     * 文字列が全てアルファベット(A-Z,a-z)または数字(0-9)かどうかを調べる
     * @param value .String 調べる文字列
     * @return boolean 全てアルファベットまたは数字だった場合true
     *
     */
    public static final boolean isAlphabetOrNumber(String strInput) {
        boolean ret = true;

        if (strInput == null) {
            return false;
        }

        for (int i = 0; i < strInput.length(); i++) {
            if (strInput.charAt(i) < 'A' || (strInput.charAt(i) > 'Z' && strInput.charAt(i) < 'a') || strInput.charAt(i) > 'z') {
                if (strInput.charAt(i) < '0' || strInput.charAt(i) > '9') {
                    ret = false;
                    break;
                }
            }
        }

        return ret;
    }

    /**
     * 数字のみかをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、文字列が数値のみかチェックする。
     * 1文字でも数値以外があればfalseが返る。</b>
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isNumber(String value) {

        boolean ret = true;

        for (int i = 0; i < value.length(); i++) {

            if (value.charAt(i) < '0' || value.charAt(i) > '9') {

                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * 数字またはハイフンかをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、文字列が数値またはハイフンかチェックする。</b>
     * @param str String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public final static boolean isNumberOrHyphen(String value) {

        boolean ret = true;

        for (int i = 0; i < value.length(); i++) {

            if (!((value.charAt(i) >= '0' && value.charAt(i) <= '9')
                    || value.charAt(i) == '-')) {

                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * 指定文字数超過チェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 引数2に上限文字数を指定して上限文字数を超えていないかチェックする。
     * 上限文字数と同等あるいは小さければtrueが返る。</b>
     * @param value String   チェック対象文字列
     * @param max int   指定文字数
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMaxLength(String value, int max) {
        if (value.length() > max) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 指定文字数未満チェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 引数2に下限文字数を指定して下限文字数未満でないかチェックする。
     * 加減文字数と同等あるいは大きければtrueが返る。</b>
     * @param value String   チェック対象文字列
     * @param max int   指定文字数
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMinLength(String value, int min) {
        if (value.length() < min) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 指定文字数範囲チェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 引数2に下限文字数、引数3に上限文字数を指定して、その範囲内かチェックする。
     * 範囲内であれば、trueが返る。</b>
     * @param value String   チェック対象文字列
     * @param max int   指定文字数
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isRangeLength(String value, int min, int max) {
        if (value.length() < min || value.length() > max) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 指定文字数と一致するか否かをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 引数2に文字数を指定して文字数と同等かチェックする。
     * 指定文字数と同等ならばtrueが返る。</b>
     * 作成日 : (2000/04/24 9:27:26)
     * @param value String   チェック対象文字列
     * @param len int   指定文字数
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isEqualsLength(String value,
            int len) {

        if (value.length() == len) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * シングルクォーテーションの有無チェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 文字列中にシングルクォーテーションが存在するかチェックする。
     * 1文字でもシングルクォーテーションが存在すればtrueが返る。</b>
     * @param str String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean hasQuote(String str) {

        boolean ret = false;

        for (int i = 0; i < str.length(); i++) {

            // シングルクォーテーションの場合
            if (str.charAt(i) == '\'') {

                ret = true;
                break;
            }
        }

        return ret;
    }

    /**
     * 小文字が含まれるかをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、小文字を含むかチェックする。
     * 1文字でも小文字があればtrueが返る。</b>
     * @param str String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean hasLowerCase(String str) {

        boolean ret = false;

        if (str != null) {

            ret = !str.equals(str.toUpperCase());
        }

        return ret;
    }

    /**
     * ブランクが含まれているか否かをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、
     * 先頭・末尾の空白を削除した上でブランクかチェックする。<br>
     * ブランクであればtrueが返る。</b>
     * @param str String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isBlank(String str) {

        boolean ret = false;

        if (str != null) {

            // 先頭・末尾の空白を削除
            String trimStr = str.trim();

            if (trimStr.equals("")) {

                ret = true;
            }

        } else {

            ret = true;
        }

        return ret;
    }

    /**
     * 不正文字が入力されていないかをチェック<BR>
     * <b>解説：引数に不正文字が入力されていないかチェックする。
     * 不正文字が入力されている場合trueが返る。</b>
     * @param str   チェック対象文字列
     * @return boolean   チェック結果（不正文字が入力されているときtrueが返る）
     */
    public static final boolean isWrongChar(String value) {

        boolean ret = false;

        for (int i = 0; i < value.length(); i++) {

            if ((value.charAt(i) == '&')
                    || (value.charAt(i) == ';')
                    || (value.charAt(i) == '\'')
                    || (value.charAt(i) == '\\')
                    || (value.charAt(i) == '\"')
                    || (value.charAt(i) == '|')
                    || (value.charAt(i) == '*')
                    || (value.charAt(i) == '?')
                    || (value.charAt(i) == '~')
                    || (value.charAt(i) == '<')
                    || (value.charAt(i) == '>')
                    || (value.charAt(i) == '^')
                    || (value.charAt(i) == '(')
                    || (value.charAt(i) == ')')
                    || (value.charAt(i) == '[')
                    || (value.charAt(i) == ']')
                    || (value.charAt(i) == '{')
                    || (value.charAt(i) == '}')
                    || (value.charAt(i) == '$')
                    || (value.charAt(i) == '\n')
                    || (value.charAt(i) == '#') //20050415add
                    || (value.charAt(i) == '%')) {

                ret = true;
                break;
            }

        }

        return ret;
    }

    /**
     * 文字列が全て平仮名かどうかを調べる
     * @param strInput String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isHiragana(String value) {
        String targetHex;
        boolean state = true;

        if (isZenkaku(value)) {
            //Stringをchar配列に変換
            char[] charArray = value.toCharArray();

            //全角２バイト文字だったら、文字コードで範囲チェック
            //全角ひらがな以外はエラー
            for (int i = 0; i < value.length(); i++) {
                char c = charArray[i];
                targetHex = Integer.toHexString(getSJISByte(c));

                //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                if ((targetHex.compareToIgnoreCase("829f") < 0
                        || targetHex.compareToIgnoreCase("82f1") > 0)) {
                    if ((targetHex.compareToIgnoreCase("815b") != 0) && //'ー'はOK
                            (targetHex.compareToIgnoreCase("8140") != 0)) {//'　'(スペース)はOK
                        state = false;
                    }
                }

            }
        } else { //全角２バイト文字でないときfalse
            state = false;
        }

        return state;
    }

    /**
     * 文字列が全て全角カタカナかどうかを調べる
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    //@deprecated
    public static final boolean isKatakana(String value) {
        boolean state;
        String targetHex = new String();
        state = true;

        try {
            if (isZenkaku(value)) {
                //Stringをchar配列に変換
                char[] charArray = value.toCharArray();

                //全角２バイト文字だったら、文字コードで範囲チェック
                //全角カタカナ以外はエラー
                for (int i = 0; i < value.length(); i++) {
                    char c = charArray[i];
                    targetHex = Integer.toHexString(getSJISByte(c));
                    //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("8340") < 0)
                            || (targetHex.compareToIgnoreCase("8396") > 0)) {
                        if ((targetHex.compareToIgnoreCase("815b") != 0) && //'ー'はOK
                                (targetHex.compareToIgnoreCase("8140") != 0)) {//'　'(スペース)はOK
                            state = false;
                        }
                    }
                }
            } else { //全角２バイト文字でないときfalse
                state = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            state = false;
        }

        return state;
    }

    /**
     * 文字列の文字コードをチェックする
     * 全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
     * @param value String   チェック対象文字列
     * @return boolean   チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMojicode1(String value) {

        String targetHex;
        boolean state = true;
        if (isZenkaku(value)) {
            //Stringをchar配列に変換
            char[] charArray = value.toCharArray();

            //全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
            for (int i = 0; i < value.length(); i++) {
                char c = charArray[i];
                targetHex = Integer.toHexString(getSJISByte(c));

                //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                if ((targetHex.compareToIgnoreCase("829F") < 0)
                        || ((targetHex.compareToIgnoreCase("8396") > 0)
                        && (targetHex.compareToIgnoreCase("889F") < 0))
                        || (targetHex.compareToIgnoreCase("EAA4") > 0)) {

                    // '々','ー''　'はOK
                    if ((targetHex.compareToIgnoreCase("8158") != 0)
                            && (targetHex.compareToIgnoreCase("815b") != 0)
                            && (targetHex.compareToIgnoreCase("8140") != 0)) {

                        state = false;
                    }
                }
            }
        } else { //全角２バイト文字でないときfalse
            state = false;
        }
        return state;
    }

    /**
     * 文字列がの文字コードをチェックする
     * 記号（一部）、全角数字、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMojicode2(String value) {

        String targetHex;
        boolean state = true;

        if (isZenkaku(value)) {

            // Stringをchar配列に変換
            char[] charArray = value.toCharArray();

            // 記号（一部）、全角数字、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
            for (int i = 0; i < value.length(); i++) {
                char c = charArray[i];
                targetHex = Integer.toHexString(getSJISByte(c));

                // １６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                if (((targetHex.compareToIgnoreCase("824f") < 0) || (targetHex.compareToIgnoreCase("8258") > 0))
                        && ((targetHex.compareToIgnoreCase("829F") < 0) || (targetHex.compareToIgnoreCase("8396") > 0))
                        && ((targetHex.compareToIgnoreCase("889F") < 0) || (targetHex.compareToIgnoreCase("EAA4") > 0))) {
                    if ((targetHex.compareToIgnoreCase("8140") != 0) && // ' 'はOK
                            (targetHex.compareToIgnoreCase("8143") != 0) && // '，'はOK
                            (targetHex.compareToIgnoreCase("8144") != 0) && // '．'はOK
                            (targetHex.compareToIgnoreCase("8145") != 0) && // '・'はOK
                            (targetHex.compareToIgnoreCase("8146") != 0) && // '：'はOK
                            (targetHex.compareToIgnoreCase("8147") != 0) && // '；'はOK
                            (targetHex.compareToIgnoreCase("8148") != 0) && // '？'はOK
                            (targetHex.compareToIgnoreCase("8149") != 0) && // '！'はOK
                            (targetHex.compareToIgnoreCase("814a") != 0) && // '゛'はOK
                            (targetHex.compareToIgnoreCase("814b") != 0) && // '゜'はOK
                            (targetHex.compareToIgnoreCase("8151") != 0) && // '＿'はOK
                            (targetHex.compareToIgnoreCase("8155") != 0) && // 'ゞ'はOK--20050425追加
                            (targetHex.compareToIgnoreCase("8158") != 0) && // '々'はOK
                            (targetHex.compareToIgnoreCase("815b") != 0) && // 'ー'はOK
                            (targetHex.compareToIgnoreCase("815e") != 0) && // '／'はOK
                            (targetHex.compareToIgnoreCase("8166") != 0) && // '’'はOK
                            (targetHex.compareToIgnoreCase("8168") != 0) && // '”'はOK
                            (targetHex.compareToIgnoreCase("8169") != 0) && // '（'はOK
                            (targetHex.compareToIgnoreCase("816a") != 0) && // '）'はOK
                            (targetHex.compareToIgnoreCase("817b") != 0) && // '＋'はOK
                            (targetHex.compareToIgnoreCase("817c") != 0) && // '?'はOK
                            (targetHex.compareToIgnoreCase("8181") != 0) && // '＝'はOK
                            (targetHex.compareToIgnoreCase("8183") != 0) && // '＜'はOK
                            (targetHex.compareToIgnoreCase("8184") != 0) && // '＞'はOK
                            (targetHex.compareToIgnoreCase("818f") != 0) && // '￥'はOK
                            (targetHex.compareToIgnoreCase("8193") != 0) && // '％'はOK
                            (targetHex.compareToIgnoreCase("8194") != 0) && // '＃'はOK
                            (targetHex.compareToIgnoreCase("8195") != 0) && // '＆'はOK
                            (targetHex.compareToIgnoreCase("8196") != 0) && // '＊'はOK
                            (targetHex.compareToIgnoreCase("8197") != 0)) { // '＠'はOK

                        state = false;
                    }
                }

            }
        } else { //全角２バイト文字でないときfalse
            state = false;
        }

        return state;
    }

    /**
     * 文字列がの文字コードをチェックする
     * 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMojicode3(String value) {
        boolean state;
        String targetHex = new String();
        state = true;

        try {
            if (isZenkaku(value)) {
                //Stringをchar配列に変換
                char[] charArray = value.toCharArray();

                //全角２バイト文字だったら、文字コードで範囲チェック
                // 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
                for (int i = 0; i < value.length(); i++) {
                    char c = charArray[i];
                    targetHex = Integer.toHexString(getSJISByte(c));
                    //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("824f") < 0)
                            || ((targetHex.compareToIgnoreCase("8396") > 0)
                            && (targetHex.compareToIgnoreCase("889F") < 0))
                            || (targetHex.compareToIgnoreCase("EAA4") > 0)) {
                        if ((targetHex.compareToIgnoreCase("8140") != 0) && //'　'はOK
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
            } else { //全角２バイト文字でないときfalse
                state = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            state = false;
        }
        return state;
    }

    /**
     * 文字列がの文字コードをチェックする
     * 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
     * 文章に対応（「。」「、」も可になります。
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    public static final boolean isMojicode4(String value) {
        boolean state;
        String targetHex = new String();
        state = true;

        try {
            if (isZenkaku(value)) {
                //Stringをchar配列に変換
                char[] charArray = value.toCharArray();

                //全角２バイト文字だったら、文字コードで範囲チェック
                // 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
                for (int i = 0; i < value.length(); i++) {
                    char c = charArray[i];
                    targetHex = Integer.toHexString(getSJISByte(c));
                    //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("824f") < 0)
                            || ((targetHex.compareToIgnoreCase("8396") > 0)
                            && (targetHex.compareToIgnoreCase("889F") < 0))
                            || (targetHex.compareToIgnoreCase("EAA4") > 0)) {
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
            } else { //全角２バイト文字でないときfalse
                state = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            state = false;
        }
        return state;
    }

    /**
     * 文字列が全て第二水準以内の文字かどうかを調べる
     *
     * @param value String チェック対象文字列
     * @return boolean チェック結果（HOSTで使用できない文字がふくまれるときfalse）
     */
    public static final boolean isMojicode5(String value) {
        boolean state = true;
        String targetHex = null;
        try {
            // 全角チェック
            if (isZenkaku(value)) {
                // Stringをchar配列に変換
                char[] charArray = value.toCharArray();


                // 全角２バイト文字だったら、文字コードで範囲チェック
                // 記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
                for (int i = 0; i < value.length(); i++) {
                    char c = charArray[i];
                    targetHex = Integer.toHexString(getSJISByte(c));

                    // １６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("824f") < 0)
                            || ((targetHex.compareToIgnoreCase("8396") > 0)
                            && (targetHex.compareToIgnoreCase("889F") < 0))
                            || (targetHex.compareToIgnoreCase("EAA4") > 0)) {

                        if ((targetHex.compareToIgnoreCase("8140") != 0) && //'　'はOK
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
                                (targetHex.compareToIgnoreCase("8197") != 0)) {//'＠'はOK

                            state = false;
                        }
                    }
                }

                // 全角２バイト文字でないときfalse
            } else {

                state = false;
            }

        } catch (Exception ex) {

            ex.printStackTrace();
            state = false;
        }

        return state;
    }

    /**
     * 文字列が全て第二水準以内の漢字を調べる
     * @param value String チェック対象文字列
     * @return String 第二水準の漢字以外の文字
     */
    public String pickGaiji(String value) {
        String targetHex;
        String strNewValue = "";

        //Stringをchar配列に変換
        char[] charArray = value.toCharArray();

        //全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
        for (int i = 0; i < value.length(); i++) {
            char c = charArray[i];
            targetHex = Integer.toHexString(getSJISByte(c));

            //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
            if ((targetHex.compareToIgnoreCase("829F") < 0)
                    || ((targetHex.compareToIgnoreCase("8396") > 0)
                    && (targetHex.compareToIgnoreCase("889F") < 0))
                    || (targetHex.compareToIgnoreCase("EAA4") > 0)) {

                // '々', 'ー'はOK
                if ((targetHex.compareToIgnoreCase("8158") != 0)
                        && (targetHex.compareToIgnoreCase("815b") != 0)) {

                    try {
                        String strExecEnv =
                                PropertyUtility.getProperty("ixap.common.exec_env");

                        if (strExecEnv.equals("server")) {

                            // ?対応
                            strNewValue =
                                    StringUtility.convertHyphenEuc(value);

                        } else {

                            strNewValue = value;

                        }

                        strNewValue =
                                new String(
                                strNewValue.getBytes("8859_1"),
                                "JISAutoDetect");

                    } catch (UnsupportedEncodingException ex) {

                        strNewValue = value;
                    }

                    strNewValue = strNewValue.substring(i / 2, i / 2 + 1);

                }
            }
        }

        return strNewValue;
    }

    /**
     * 文字列が全て全角カタカナ(含む記号)かどうかを調べる
     * @param value String チェック対象文字列
     * @return boolean チェック結果（適切ならばtrueが返る）
     */
    public static final boolean checkKatakana_easy(String value) {
        boolean state;

        String targetHex = new String();

        state = true;

        try {
            if (isZenkaku(value)) {
                //Stringをchar配列に変換
                char[] charArray = value.toCharArray();

                //全角２バイト文字だったら、文字コードで範囲チェック
                //全角カタカナ以外はエラー
                for (int i = 0; i < value.length(); i++) {
                    char c = charArray[i];
                    targetHex = Integer.toHexString(getSJISByte(c));
                    //１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
                    if ((targetHex.compareToIgnoreCase("8340") < 0)
                            || (targetHex.compareToIgnoreCase("8396") > 0)) {
                        if ((targetHex.compareToIgnoreCase("8754") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8755") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8756") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8757") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8758") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8759") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("875a") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("875b") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("875c") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("875d") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa40") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa41") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa42") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa43") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa44") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa45") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa46") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa47") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa48") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("fa49") != 0) && //'?'はOK--20090216追加
                                (targetHex.compareToIgnoreCase("8140") != 0) && //'　'はOK
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
                                (targetHex.compareToIgnoreCase("8197") != 0) && //'＠'はOK
                                (targetHex.compareToIgnoreCase("824f") != 0) && //'０'はOK
                                (targetHex.compareToIgnoreCase("8250") != 0) && //'１'はOK
                                (targetHex.compareToIgnoreCase("8251") != 0) && //'２'はOK
                                (targetHex.compareToIgnoreCase("8252") != 0) && //'３'はOK
                                (targetHex.compareToIgnoreCase("8253") != 0) && //'４'はOK
                                (targetHex.compareToIgnoreCase("8254") != 0) && //'５'はOK
                                (targetHex.compareToIgnoreCase("8255") != 0) && //'６'はOK
                                (targetHex.compareToIgnoreCase("8256") != 0) && //'７'はOK
                                (targetHex.compareToIgnoreCase("8257") != 0) && //'８'はOK
                                (targetHex.compareToIgnoreCase("8258") != 0) //'９'はOK
                                ) {

                            state = false;
                        }
                    }
                }
            } else { //全角２バイト文字でないときfalse
                state = false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            state = false;
        }

        return state;
    }

    /**
     * Shift-JISでバイトに変換する.
     * @param c 変換対象文字
     * @return 変換後文字
     */
    public static int getSJISByte(char c) {

        try {
            //Windows-31Jでbyteに変換
            byte[] bArray = String.valueOf(c).getBytes("Windows-31J");

            int targetChar;
            if (bArray.length == 1) {
                //1バイト文字
                targetChar = bArray[0] & 0xFF;

            } else {
                //2バイト文字
                targetChar = ((bArray[0] & 0xFF) << 8) | (bArray[1] & 0xFF);

            }
            return targetChar;

        } catch (Exception e) {
            return 0;
        }
    }
}