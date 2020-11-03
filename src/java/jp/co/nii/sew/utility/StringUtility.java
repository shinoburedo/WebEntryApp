package jp.co.nii.sew.utility;

import java.security.SecureRandom;
import java.text.DecimalFormat;

/**
 * <p>タイトル: 文字に関する共通クラス</p>
 * <p>説明: 文字に関する変換、編集などを行う。</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class StringUtility {

    /** 改行文字 */
    public static final String NEW_LINE = System.getProperty("line.separator");
    /** タブ */
    public static final String TAB = "\t";
    /** BOの属性をテキストにするときの区切り文字 */
    public static final String SEW_DELIMITER = TAB;

    /**
     * 文字コードの変換 －、～<BR>
     * WindowsのunicodeからJIS X 0221 (ISO/IEC 646-IRV),Unicode Consortium,<BR>
     * Java (SJIS & EUCJIS),Java (JIS),MacOSのunicodeへ変換する。<BR>
     * サーブレットパラメータ、メールの件名・本文の文字などで使用する。<BR>
     * @param s 変換前文字列
     * @return String 変換後文字列
     */
    public static String convertHyphenEuc(final String s) {

        char c;
        String strNewValue = "";
        StringBuffer sb = new StringBuffer();

        if (s != null) {

            // 文字列置換
            for (int i = 0; i < s.length(); i++) {

                c = s.charAt(i);
                try {
                    switch (c) {

                        // FULLWIDTH TILDE ->
                        case 0xff5e:

                            // WAVE DASH
                            c = 0x301c;
                            break;

                        // FULLWIDTH HYPHEN-MINUS ->
                        case 0xff0d:

                            // MINUS SIGN
                            c = 0x2212;
                            break;
                    }

                    sb.append(c);
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

            strNewValue = new String(sb);

        }

        return strNewValue;
    }

    /**
     * 文字コードの変換 －、～SJIS対応(JSP表示用)<BR>
     * @param s 変換前文字列
     * @return String 変換後文字列
     */
    public static String convertHyphenSjis(final String s) {

        char c;
        String strNewValue = "";
        StringBuffer sb = new StringBuffer();

        if (s != null) {

            // 文字列置換
            for (int i = 0; i < s.length(); i++) {
                try {
                    c = s.charAt(i);

                    switch (c) {

                        // FULLWIDTH TILDE ->
                        case 0x301c:

                            // WAVE DASH
                            c = 0xff5e;
                            break;

                        // FULLWIDTH HYPHEN-MINUS ->
                        case 0x2212:

                            // MINUS SIGN
                            c = 0xff0d;
                            break;
                    }

                    sb.append(c);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            strNewValue = new String(sb);

        }

        return strNewValue;
    }

    //    /**
    //     * 文字コード－、～を文字列からはずす
    //     * @param s 変換前文字列
    //     * @return String 変換後文字列
    //     */
    //    public static String removeHyphen(final String s) {
    //
    //        char c;
    //        String strNewValue = "";
    //        StringBuffer sb = new StringBuffer();
    //
    //        if (s != null) {
    //
    //            String strExecEnv =
    //                PropertyUtility.getProperty("ixap.common.exec_env");
    //
    //            if (strExecEnv.equals("server")) {
    //
    //                // 文字列置換
    //                for (int i = 0; i < s.length(); i++) {
    //
    //                    c = s.charAt(i);
    //
    //                    if (!(c == 0x301c || c == 0x2212)) {
    //
    //                        sb.append(c);
    //
    //                    }
    //                }
    //
    //                strNewValue = new String(sb);
    //
    //            } else {
    //
    //                strNewValue = s;
    //            }
    //
    //        }
    //
    //        return strNewValue;
    //    }
    /**
     * Stringオブジェクトの左に指定した文字を埋める<BR>
     * <b>解説：引数1に文字列を引数2に埋めたい文字を、
     * 引数3に全体の文字数を指定し、値を返す。</b>
     * @param psValue String 編集前の文字列
     * @param pcSet String 埋めたい文字
     * @param piLen Integer 全体の文字数
     * @return String 編集後の文字列
     */
    public static String padLeft(
            final String psValue,
            final String pcSet,
            final int piLen) {

        StringBuffer lsValue = new StringBuffer(piLen);

        lsValue.append(psValue);

        while (lsValue.length() < piLen) {

            lsValue.insert(0, pcSet);
        }

        return lsValue.toString();
    }

    /**
     * Stringオブジェクトの右に指定した文字を埋める<BR>
     * <b>解説：</b>引数1に文字列を引数2に埋めたい文字を、
     * 引数3に全体の文字数を指定し、値を返す。
     * @param ps_value java.lang.String 編集前の文字列
     * @param pc_set java.lang.String 埋めたい文字
     * @param pi_len java.lang.Integer 全体の文字数
     * @return String 編集後の文字列
     */
    public static String padRight(
            final String ps_value,
            final String pc_set,
            final int pi_len) {

        StringBuffer ls_value = new StringBuffer(pi_len);

        ls_value.append(ps_value);

        while (ls_value.length() < pi_len) {

            ls_value.append(pc_set);

        }

        return ls_value.toString();
    }

    /**
     * Stringオブジェクトの両端のスペースを削除する(全角・半角)<BR>
     * <b>解説：</b>引数1に文字列を指定し、値を返す。nullのとき""を返す。
     * @param str String スペース(全角・半角)削除前の文字列
     * @return String スペース(全角・半角)削除後の文字列
     */
    public static String removeEdgeSpace(final String str) {
        if (str == null) {

            return "";

        } else {
            // パラメタ文字列左側（先頭）の全角半角空白を削除
            return str.replaceAll("^[\\s　]*", "").replaceAll("[\\s　]*$", "");
        }
    }

    /**
     * Stringオブジェクトの両端のスペースを削除する(全角スペース) <BR>
     * <b>解説：</b>引数1に文字列を指定し、値を返す。nullのとき""を返す
     * @param ps_value java.lang.String 全角スペース削除前の文字列
     * @return String 全角スペース削除後の文字列
     */
    public static final String trimSpace(final String str) {

        //strがnullのときreplaceメソッドでjava.lang.NullPointerExceptionが起こるので場合分けした
        if (str == null) {

            return "";

        } else {

            String str2 = str.replace('　', ' ');
            final int len = str2.trim().length(); //最終的な長さ

            if (len == 0) {

                return "";

            }

            //間のスペースは全角、半角は元のまま維持したいのでtrimは使わない
            int startPosition = 0;

            for (int i = 0; i < str2.length(); i++) {

                if (str2.charAt(i) != ' ') {

                    startPosition = i;
                    break;
                }
            }

            return str.substring(startPosition, startPosition + len);
        }
    }

    /**
     * ３桁毎にカンマを入れる
     * @param str フォーマット前のデータ
     * @return ３桁区切りフォーマット済みのデータ
     */
    public static String editComma(final String str) {

        DecimalFormat format;
        long l;
        String retStr;
        int idx = 1;

        if (str == null || str.equals("")) {

            retStr = " ";

        } else {

            l = 0;
            retStr = "";

            if ((idx = str.indexOf(".")) == -1) {

                l = Long.parseLong(str);
                format = new java.text.DecimalFormat("###,###,###,###,##0");
                retStr = format.format(l);

            } else {

                String head = str.substring(0, idx);
                String tail = str.substring(idx);
                l = Long.parseLong(head);
                format = new java.text.DecimalFormat("###,###,###,###,##0");
                retStr = format.format(l) + tail;
            }

        }

        return retStr;
    }

    /**
     * カンマを除去する<BR>
     * <b>解説：引数にカンマが含まれていれば、カンマを取り除いた文字列を返す</b>
     * @param strOrg String カンマ除去前の文字列
     * @return String カンマ除去後の文字列
     */
    public static String removeComma(final String strOrg) {

        String strNew = "";
        int iLen = strOrg.length();

        for (int i = 0; i < iLen; i++) {

            if (strOrg.charAt(i) != ',') {

                strNew += String.valueOf(strOrg.charAt(i));
            }
        }

        return strNew;
    }

    /**
     * 全角文字を半角文字に変換する
     * @param str 全角文字を含む文字列
     * @return 全角文字が半角に変換された文字列
     */
    public static String convertZenkakuToHankaku(final String str) {

        String ret = "";
        String flg = "";

        for (int i = 0; i < str.length(); i++) {

            int base;
            char code = str.charAt(i);

            for (int j = 0; j < 3; j++) {

                for (int k = 0; k < CHAR_TABLE.length; k++) {

                    if (code == CHAR_TABLE[k].charAt(j)) {

                        base = k;
                        ret = ret + CHAR_TABLE[base].charAt(1);

                        if (CHAR_TABLE[base].charAt(2) != '#') {

                            ret = ret + CHAR_TABLE[base].charAt(2);
                        }

                        flg = "*";
                        break;
                    }
                }
            }

            if (flg.equals("")) {

                ret = ret + code;
            }

            flg = "";
        }

        return ret;
    }

    /**
     * 半角文字列(数値のみ)を全角に変換する
     * @param str 半角文字列(数値のみ)
     * @return 全角文字列(数値のみ)
     */
    public static String convertNumberToZenkaku(final String str) {

        String full = "";
        String num = null;

        for (int i = 0; i < str.length(); i++) {
            char code = str.charAt(i);
            switch (code) {
                case '0':
                    num = "０";
                    break;
                case '1':
                    num = "１";
                    break;
                case '2':
                    num = "２";
                    break;
                case '3':
                    num = "３";
                    break;
                case '4':
                    num = "４";
                    break;
                case '5':
                    num = "５";
                    break;
                case '6':
                    num = "６";
                    break;
                case '7':
                    num = "７";
                    break;
                case '8':
                    num = "８";
                    break;
                case '9':
                    num = "９";
                    break;
                case ',':
                    num = "，";
                    break;
                default:
                    break;
            }
            //文字列を繋げる
            full = full + num;
        }
        return full;
    }

    /**
     * NULLを空文字""に変換する
     * @param String str 検査項目
     * @return String 変換後文字列
     */
    public static String convertNullToBlank(String str) {

        if (str == null) {

            str = "";

        }

        return str;
    }

    /**
     * 文字列を受け取り、null、空文字、スペースであったら "0" を返す<BR>
     * @param str 変換前の文字列
     * @return  String 変換後の文字列
     */
    public static String convertBlankToZero(final String str) {

        String retStr;
        CheckUtility checkUtility = new CheckUtility();

        if (checkUtility.isBlank(str)) {

            retStr = "0";

        } else {

            retStr = str;
        }

        return retStr;
    }

    /**
     * "'"(単一引用符)をダブらせる<BR>
     * <b>解説：</b>引数文字列内の "'" を "''" に置き換える
     * @param str String 編集前の文字列
     * @return String 編集後の文字列
     * @throws NullPointerException error
     */
    public static String convertSingleQuoteToSqlQuote(final String str)
            throws NullPointerException {

        if (str != null) {

            StringBuffer sb = new StringBuffer(str);
            int lastIndex = str.lastIndexOf('\'');

            while (lastIndex != -1) {

                sb.insert(lastIndex, '\'');
                String strWk = str.substring(0, lastIndex);
                lastIndex = strWk.lastIndexOf('\'');
            }

            return sb.toString();

        } else {

            throw new NullPointerException("toSqlQuote : parameter is null;");
        }

    }

    /**
     * カンマ連続の場合、データ認識しないためスペースを挿入する<BR>
     * <b>解説：</b>引数1に文字列、引数2に連続した場合にスペースを挿入したい文字を指定。
     * @return String
     * @param line
     * @param separator
     */
    public static String InsertSpace(String line, String separator) {
        int index;
        String first_half;
        String latter_half;

        String twoSeparator = separator + separator;
        do {
            index = line.indexOf(twoSeparator);
            if (index != -1) {
                first_half = line.substring(0, index + 1);
                latter_half = line.substring(index + 1, line.length());
                line = first_half + " " + latter_half;
            }
        } while (index >= 0);
        return line;
    }
    /**
     * convertZenkakuToHankakuで使用するテーブル
     */
    public static final String CHAR_TABLE[] = {
        "をｦ#",
        "ぁｧ#",
        "ぃｨ#",
        "ぅｩ#",
        "ぇｪ#",
        "ぉｵ#",
        "ゃｬ#",
        "ゅｭ#",
        "ょｮ#",
        "っｯ#",
        "あｱ#",
        "いｲ#",
        "うｳ#",
        "えｴ#",
        "おｵ#",
        "かｶ#",
        "きｷ#",
        "くｸ#",
        "けｹ#",
        "こｺ#",
        "さｻ#",
        "しｼ#",
        "すｽ#",
        "せｾ#",
        "そｿ#",
        "たﾀ#",
        "ちﾁ#",
        "つﾂ#",
        "てﾃ#",
        "とﾄ#",
        "なﾅ#",
        "にﾆ#",
        "ぬﾇ#",
        "ねﾈ#",
        "のﾉ#",
        "はﾊ#",
        "ひﾋ#",
        "ふﾌ#",
        "へﾍ#",
        "ほﾎ#",
        "まﾏ#",
        "みﾐ#",
        "むﾑ#",
        "めﾒ#",
        "もﾓ#",
        "やﾔ#",
        "ゆﾕ#",
        "よﾖ#",
        "らﾗ#",
        "りﾘ#",
        "るﾙ#",
        "れﾚ#",
        "ろﾛ#",
        "わﾜ#",
        "んﾝ#",
        "がｶﾞ",
        "ぎｷﾞ",
        "ぐｸﾞ",
        "げｹﾞ",
        "ごｺﾞ",
        "ざｻﾞ",
        "じｼﾞ",
        "ずｽﾞ",
        "ぜｾﾞ",
        "ぞｿﾞ",
        "だﾀﾞ",
        "ぢﾁﾞ",
        "づﾂﾞ",
        "でﾃﾞ",
        "どﾄﾞ",
        "ばﾊﾞ",
        "びﾋﾞ",
        "ぶﾌﾞ",
        "べﾍﾞ",
        "ぼﾎﾞ",
        "ぱﾊﾟ",
        "ぴﾋﾟ",
        "ぷﾌﾟ",
        "ぺﾍﾟ",
        "ぽﾎﾟ",
        "　 #",
        "，,#",
        "．.#",
        "・/#",
        "：:#",
        "；;#",
        "？?#",
        "！!#",
        "－-#",
        "ーｰ#",
        "／/#",
        "’'#",
        "”'#",
        "（(#",
        "）)#",
        "＋+#",
        "－-#",
        "＝=#",
        "＜<#",
        "＞>#",
        "￥\\#",
        "％%#",
        "＃##",
        "＆&#",
        "＊*#",
        "＠@#",
        "１1#",
        "２2#",
        "３3#",
        "４4#",
        "５5#",
        "６6#",
        "７7#",
        "８8#",
        "９9#",
        "０0#",
        "ＡA#",
        "ＢB#",
        "ＣC#",
        "ＤD#",
        "ＥE#",
        "ＦF#",
        "ＧG#",
        "ＨH#",
        "ＩI#",
        "ＪJ#",
        "ＫK#",
        "ＬL#",
        "ＭM#",
        "ＮN#",
        "ＯO#",
        "ＰP#",
        "ＱQ#",
        "ＲR#",
        "ＳS#",
        "ＴT#",
        "ＵU#",
        "ＶV#",
        "ＷW#",
        "ＸX#",
        "ＹY#",
        "ＺZ#",
        "ａa#",
        "ｂb#",
        "ｃc#",
        "ｄd#",
        "ｅe#",
        "ｆf#",
        "ｇg#",
        "ｈh#",
        "ｉi#",
        "ｊj#",
        "ｋk#",
        "ｌl#",
        "ｍm#",
        "ｎn#",
        "ｏo#",
        "ｐp#",
        "ｑq#",
        "ｒr#",
        "ｓs#",
        "ｔt#",
        "ｕu#",
        "ｖv#",
        "ｗw#",
        "ｘx#",
        "ｙy#",
        "ｚz#"};

    /**
     * ゆれ調整
     * @param str 全角カタカナ文字列
     * @return 濁音、半濁音、促音、吃音がある場合、それらを清音に置き換えた文字列
     */
    public static String adjustDiffer(final String str) {

        String ret = "";
        String flg = "";

        for (int i = 0; i < str.length(); i++) {

            int base;
            char code = str.charAt(i);

            for (int j = 0; j < 3; j++) {

                for (int k = 0; k < DIFFER_TABLE.length; k++) {

                    if (code == DIFFER_TABLE[k].charAt(j)) {

                        base = k;
                        ret = ret + DIFFER_TABLE[base].charAt(1);

                        if (DIFFER_TABLE[base].charAt(2) != '#') {

                            ret = ret + DIFFER_TABLE[base].charAt(2);
                        }

                        flg = "*";
                        break;
                    }
                }
            }

            if (flg.equals("")) {

                ret = ret + code;
            }

            flg = "";
        }

        return ret;
    }
    /**
     * adjustDifferで使用するテーブル
     */
    public static final String DIFFER_TABLE[] = {
        "ガカ#",
        "ギキ#",
        "グク#",
        "ゲケ#",
        "ゴコ#",
        "ザサ#",
        "ジシ#",
        "ズス#",
        "ゼセ#",
        "ゾソ#",
        "ダタ#",
        "ヂチ#",
        "ヅツ#",
        "デテ#",
        "ドト#",
        "バハ#",
        "ビヒ#",
        "ブフ#",
        "ベヘ#",
        "ボホ#",
        "パハ#",
        "ピヒ#",
        "プフ#",
        "ペヘ#",
        "ポホ#",
        "ァア#",
        "ィイ#",
        "ゥウ#",
        "ェエ#",
        "ォオ#",
        "ャヤ#",
        "ュユ#",
        "ョヨ#",
        "ッツ#"};

    /**
     * 文字列の間のスペース（全角・半角）を除去<BR>
     * @param s 入力文字列
     * @return String スペース（全角・半角）除去後文字列
     */
    public static String removeSpace(final String s) {

        char c;
        String strNewValue = "";
        StringBuffer sb = new StringBuffer();

        if (s != null) {

            // 文字列置換
            for (int i = 0; i < s.length(); i++) {
                try {
                    c = s.charAt(i);

                    if (c != ' ' && c != '　') {

                        sb.append(c);
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }

            strNewValue = new String(sb);

        }

        return strNewValue;
    }

    /**
     * コンストラクタ無効化
     */
    public StringUtility() {
    }

    /**
     * 文字列の置換<BR>
     * 注！JDK1.4以降はStringクラスにreplaceALLが<BR>
     * あるので　そちらを使うこと！<br>
     * JDK1.3の場合のみ使用
     * @param a 入力文字列全体
     * @param s 置換される文字列
     * @param ss 置換する文字列
     * @return String 置換後文字列
     * @author S.Matsumura
     */
    public static String Substitution(
            final String a,
            final String s,
            final String ss) {

        int t = 0;
        String strNewValue = convertNullToBlank(a);
        String Ret = "";
        while (strNewValue.length() > 0) {
            if (s != null && ss != null) {
                t = strNewValue.indexOf(s);
                if (t != -1) {
                    if (strNewValue.length() > t + s.length()) {
                        Ret = Ret + strNewValue.substring(0, t) + ss;
                        strNewValue = strNewValue.substring(t + s.length());

                    } else {
                        Ret = Ret + strNewValue.substring(0, t) + ss;
                        break;
                    }
                } else {
                    Ret = Ret + strNewValue;
                    break;
                }

            }
        }
        return Ret;
    }

    /**
     * Latin2SJIS変換
     * @param str unicode変換前
     * @return String str
     */
    public static String Latin2SJIS(String str) {
        String ret = "";
        try {
            ret = new String(str.getBytes("ISO-8859-1"), "Windows-31J");
            ret = convertHyphenSjis(ret);
        } catch (Exception e) {
            e.printStackTrace();
            ret = str;
        }
        return ret;
    }

    /**
     * ランダムな英数字からなる指定サイズの文字列を返す。
     * pttnが 1:英大小数字  2:数字  3:英大小文字
     *
     * @param size 文字列のサイズ
     * @return str 生成された文字列
     */
    public static String randomString(int size, String pttn) {

        SecureRandom random = new SecureRandom();
        char[] pass = new char[size];


        if (pttn.equals("1")) {//英大小数字のランダム生成
            for (int k = 0; k < pass.length; k++) {

                switch (random.nextInt(3)) {
                    case 0: // 'a' - 'z'
                        pass[k] = (char) (97 + random.nextInt(26));
                        break;

                    case 1: // 'A' - 'Z'
                        pass[k] = (char) (65 + random.nextInt(26));
                        break;

                    case 2: // '0' - '9'
                        pass[k] = (char) (48 + random.nextInt(10));
                        break;

                    default:
                        pass[k] = 'a';
                }
            }
        }

        if (pttn.equals("2")) {//数字のランダム生成
            for (int k = 0; k < pass.length; k++) {

                pass[k] = (char) (48 + random.nextInt(10));

            }
        }

        if (pttn.equals("3")) {//英大小文字のランダム生成
            for (int k = 0; k < pass.length; k++) {

                switch (random.nextInt(2)) {
                    case 0: // 'a' - 'z'
                        pass[k] = (char) (97 + random.nextInt(26));
                        break;

                    case 1: // 'A' - 'Z'
                        pass[k] = (char) (65 + random.nextInt(26));
                        break;

                    default:
                        pass[k] = 'a';
                }
            }
        }
        // 文字の配列を文字列に変換
        return new String(pass);

    }

    /**
     * 決済用コード変換処理
     *
     * @param str
     * @return
     */
    public static String ksiString(String str) {

        String CHAR_ENC = "Windows-31J";

        try {
            if (str == null) {
                return "";
            }
//            return new String(StringUtility.convertHyphenEuc(str).getBytes(CHAR_ENC), "ISO_8859_1");
            return new String(str.getBytes(CHAR_ENC), "ISO_8859_1");

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 二次元配列のリストからValue値のリストを取得する 二次元配列の[i][0]がValue値
     *
     * @param strList
     * @return
     */
    public static String[] getValueList(String[][] strList) {
        String[] valueList = new String[strList.length];
        for (int i = 0; i < valueList.length; i++) {
            valueList[i] = strList[i][0];
        }
        return valueList;
    }
}
