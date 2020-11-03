package jp.co.nii.sew.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日付時刻ユーティリティー
 * @author n-minegishi
 */
public class DateTimeUtility {

    // --- 日付時刻書式文字列 ---------------------------------------------------
    /** 日付時刻書式文字列 yyyyMMddHHmmssSSS */
    public static final String DATETIME_FORMAT_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    /** 日付時刻書式文字列 yyyyMMdd */
    public static final String DATETIME_FORMAT_yyyyMMdd = "yyyyMMdd";
    /** 日付時刻書式文字列 HHmmssSSS */
    public static final String DATETIME_FORMAT_HHmmssSSS = "HHmmssSSS";
    /** 日付時刻書式文字列 HHmmss */
    public static final String DATETIME_FORMAT_HHmmss = "HHmmss";
    /** 日付時刻書式文字列 yyyy/MM/dd HH:mm:ss.SSS */
    public static final String DATETIME_FORMAT_TIMESTAMP = "yyyy/MM/dd HH:mm:ss.SSS";
    /** 日付時刻書式文字列 yyyy/MM/dd HH:mm:ss */
    public static final String DATETIME_FORMAT_yyyyMMddSlashHHmmssColon = "yyyy/MM/dd HH:mm:ss";
//    /** 日付時刻書式文字列 yyyyMMddHHmmss */
//    public static final String DATETIME_FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
//    /** 日付時刻書式文字列 yyMMddHHmmss */
//    public static final String DATETIME_FORMAT_yyMMddHHmmss = "yyMMddHHmmss";
//    /** 日付時刻書式文字列 yyMMdd */
//    public static final String DATETIME_FORMAT_yyMMdd = "yyMMdd";
//    /** 日付時刻書式文字列 yyyyMM */
//    public static final String DATETIME_FORMAT_yyyyMM = "yyyyMM";
//    /** 日付時刻書式文字列 yyyy */
//    public static final String DATETIME_FORMAT_yyyy = "yyyy";
//    /** 日付時刻書式文字列 yy */
//    public static final String DATETIME_FORMAT_yy = "yy";
//    /** 日付時刻書式文字列 dd */
//    public static final String DATETIME_FORMAT_dd = "dd";
//    /** 1900年代、2000年代の境界日付（６桁） */
//    private static final int KYOKAI_NEN = 500000;

    // ---システム日時取得-----------------------------------------------------------------------
    /**
     * システム日時取得（ミリ秒）
     * @return システム日時（ミリ秒）
     * @author n-minegishi
     */
    public static long getSystemDateTimeMills() {
        return System.currentTimeMillis();
    }

    // ----日付時刻フォーマット変換（ミリ秒-->文字列）----------------------------------------------------------------------
    /**
     * 日付時刻フォーマット変換（ミリ秒 --> 指定書式）
     *
     * @param mills
     *            ミリ秒
     * @param format
     *            指定書式
     * @return 変換後文字列
     * @author n-minegishi
     */
    public static String formatByFormatFromMilliseconds(long mills,
            String format) {
        Date dte = new Date(mills);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(dte);
    }

    /**
     * 日付時刻フォーマット変換（ミリ秒 --> yyyyMMddHHmmssSSS）
     *
     * @param mills
     *            ミリ秒
     * @return 変換後文字列（yyyyMMddHHmmssSSS）
     * @author n-minegishi
     */
    public static String formatToStringyyyyMMddHHmmssSSSFromMilliSeconds(
            long mills) {
        return formatByFormatFromMilliseconds(mills, DATETIME_FORMAT_yyyyMMddHHmmssSSS);
    }

    /**
     * 日付フォーマット変換（ミリ秒 --> yyyyMMdd）
     *
     * @param mills
     *            ミリ秒
     * @return 変換後文字列（yyyyMMdd）
     * @author n-minegishi
     */
    public static String formatToStringyyyyMMddFromMilliSeconds(long mills) {
        return formatByFormatFromMilliseconds(mills, DATETIME_FORMAT_yyyyMMdd);
    }

    /**
     * 時刻フォーマット変換（ミリ秒 --> HHmmssSSS）
     *
     * @param mills
     *            ミリ秒
     * @return 変換後文字列（HHmmssSSS）
     * @author n-minegishi
     */
    public static String formatToStringHHmmssSSSFromMilliSeconds(long mills) {
        return formatByFormatFromMilliseconds(mills, DATETIME_FORMAT_HHmmssSSS);
    }

    /**
     * 時刻フォーマット変換（ミリ秒 --> HHmmss）
     *
     * @param mills
     *            ミリ秒
     * @return 変換後文字列（HHmmss）
     * @author n-minegishi
     */
    public static String formatToStringHHmmssFromMilliSeconds(long mills) {
        return formatByFormatFromMilliseconds(mills, DATETIME_FORMAT_HHmmss);
    }

    /**
     * 日付時刻フォーマット変換（ミリ秒 --> タイムスタンプ型 yyyy/MM/dd HH:mm:ss.SSS）
     *
     * @param mills
     *            ミリ秒
     * @return 変換後文字列（タイムスタンプ型 yyyy/MM/dd HH:mm:ss.SSS）
     * @author n-minegishi
     */
    public static String formatToStringTimestampFromMilliSeconds(long mills) {
        return formatByFormatFromMilliseconds(mills, DATETIME_FORMAT_TIMESTAMP);
    }

    /**
     * 時刻（HH:mm:ss）を取得する。
     *
     * @param HHmmss
     *            変換したい6桁の時刻を表す文字列
     * @return HH:mm:ssの形式に編集された文字列
     * @author n-minegishi
     */
    public static String formatToStringHHmmssColonFromHHmmss(String HHmmss) {
        return new StringBuilder().append(HHmmss.subSequence(0, 2)).append(
                ":").append(HHmmss.substring(2, 4)).append(":").append(
                HHmmss.substring(4, 6)).toString();
    }

    /**
     * 時刻（HH:mm:ss.SSS）を取得する。
     *
     * @param HHmmss
     *            変換したい6桁の時刻を表す文字列
     * @return HH:mm:ss.SSSの形式に編集された文字列
     * @author n-minegishi
     */
    public static String formatToStringHHmmssSSSColonFromHHmmssSSS(String HHmmssSSS) {
        return new StringBuilder().append(HHmmssSSS.subSequence(0, 2)).append(
                ":").append(HHmmssSSS.substring(2, 4)).append(":").append(
                HHmmssSSS.substring(4, 6)).append(".").append(HHmmssSSS.substring(6, 9)).toString();
    }

    /**
     * 時刻（HHmmss）を取得する。
     *
     * @param HHmmssColon
     *            変換したい6桁または9桁の時刻を表す文字列（HH:mm:ss、HH:mm:ss.SSS）
     * @return HHmmss、HHmmssSSSの形式に編集された文字列
     * @author n-minegishi
     */
    public static String formatToStringHHmmssFromHHmmssColon(String HHmmssColon) {
        return HHmmssColon.replace(":", "").replace(".", "");
    }

    /**
     * 日付（yyyy/MM/dd）を取得する。
     *
     * @param yyyyMMdd
     *            変換したい8桁の日付を表す文字列
     * @return yy/MM/ddの形式に編集された文字列
     * @author n-minegishi
     */
    public static String formatToStringyyyyMMddSlashFromyyyyMMdd(String yyyyMMdd) {
        return new StringBuilder().append(yyyyMMdd.substring(0, 4)).append("/").append(yyyyMMdd.substring(4, 6)).append("/").append(yyyyMMdd.substring(6, 8)).toString();
    }

    /**
     * 日付（yyyyMMdd）を取得する。
     *
     * @param yyyyMMddSlash
     *            変換したい8桁の日付を表す文字列（yyyy/MM/dd）
     * @return yyyyMMddの形式に編集された文字列
     * @author n-minegishi
     */
    public static String formatToStringyyyyMMddFromyyyyMMddSlash(String yyyyMMddSlash) {
        return yyyyMMddSlash.replace("/", "");
    }

    /**
     * 日付を加算・減算する
     * @param yyyyMMdd 日付文字列（yyyyMMdd）<br>
     * null、空白、形式に合わない場合は、例外が発生する<br>
     * @param field 加算する単位を表すカレンダー・フィールド<br>
     * 以下の定数を使用すること。<br>
     * 年：Calendar.YEAR<br>
     * 月：Calendar.MONTH<br>
     * 日：Calendar.DAY_OF_MONTH<br>
     * @param amount 加算数。減産する場合は負数。
     * @return 加算・減算した結果の日付文字列（yyyyMMdd）
     * @author n-minegishi
     */
    public static final String addDateFromyyyyMMdd(String yyyyMMdd, int field, int amount) {
        final Calendar calendar = getCalendarFromyyyyMMdd(yyyyMMdd);
        calendar.add(field, amount);
        final String newDate = formatToStringyyyyMMddFromMilliSeconds(calendar.getTimeInMillis());
        return newDate;
    }

    /**
     * 日付を表すカレンダーを取得する
     * @param yyyyMMdd 日付文字列（yyyyMMdd）<br>
     * null、空白、形式に合わない場合は、例外が発生する<br>
     * @return 指定日付を表すグレゴリオ・カレンダー
     * @author n-minegishi
     */
    private static final Calendar getCalendarFromyyyyMMdd(final String yyyyMMdd) {
        return new GregorianCalendar(Integer.valueOf(yyyyMMdd.substring(0, 4)), Integer.valueOf(yyyyMMdd.substring(4, 6)) - 1, Integer.valueOf(yyyyMMdd.substring(6, 8)));
    }

    /**
     * 日付を大小比較する<br>
     * @param yyyyMMdd1 日付文字列（yyyyMMdd）
     * @param yyyyMMdd2 日付文字列（yyyyMMdd）
     * @param operator 比較演算子（==,<,<=,>,>=）
     * @return 比較結果
     * @author n-minegishi
     */
    public static final boolean compareDateyyyyMMdd(final String yyyyMMdd1, final String yyyyMMdd2, final String operator) {
        boolean result = false;

        if (yyyyMMdd1 == null || yyyyMMdd2 == null || operator == null
                || !("==".equals(operator) || "<".equals(operator) || "<=".equals(operator) || ">".equals(operator) || ">=".equals(operator))) {
            throw new IllegalArgumentException("argument yyyyMMdd1=" + yyyyMMdd1 + ", yyyyMMdd2=" + yyyyMMdd2 + ", operator=" + operator);
        }

        // Unicode値に基づいて辞書的に比較
        final int value = yyyyMMdd1.compareTo(yyyyMMdd2);

        if ("==".equals(operator)) {
            result = (value == 0);
        } else if ("<".equals(operator)) {
            result = (value < 0);
        } else if ("<=".equals(operator)) {
            result = (value <= 0);
        } else if (">".equals(operator)) {
            result = (value > 0);
        } else if (">=".equals(operator)) {
            result = (value >= 0);
        }

        return result;
    }

    /**
     * 日付として正しいか判定する。
     *
     * @param yyyyMMdd 日付文字列（8桁）
     * @return 正しい場合true
     * @author j-fujizuka
     */
    public static boolean isCorrectDateyyyyMMdd(String stringDate) {
        // 変換用のSimpleDateFormatを作成
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        formatter.setLenient(false); // 日付時刻解析は、厳密に行う。

        // 日付（8桁）をDateに変換し、変換に失敗した場合は、日付として誤っているとみなす。
        try {
            formatter.parse(stringDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 西暦→和暦変換
     *
     * @param seireki 西暦
     * @return 和暦
     * @author m-takahashi
     */
    public static String convertSeirekiToWareki(String seireki) {
        if(seireki.equals("")){
            return "";
        }
        StringBuilder wareki = new StringBuilder();
        String[] gengo = convertSeirekiToGengo(seireki);

        wareki.append(gengo[0]);
        if (gengo[1].equals("01")) {
            wareki.append("元");
        } else {
            if (gengo[1].substring(0, 1).equals("0")) {
                wareki.append(String.format("%2s", gengo[1].substring(1, 2)));
            } else {
                wareki.append(String.format("%2s", gengo[1]));
            }
        }
        wareki.append("年");

        if (seireki.substring(4, 5).equals("0")) {
            wareki.append(String.format("%2s", seireki.substring(5, 6)));
        } else {
            wareki.append(String.format("%2s", seireki.substring(4, 6)));
        }
        wareki.append("月");

        if (seireki.substring(6, 7).equals("0")) {
            wareki.append(String.format("%2s", seireki.substring(7, 8)));
        } else {
            wareki.append(String.format("%2s", seireki.substring(6, 8)));
        }
        wareki.append("日");

        return wareki.toString();
    }

    /**
     * 西暦→元号変換
     *
     * @param seireki 西暦
     * @return 元号
     * @author m-takahashi
     */
    public static String[] convertSeirekiToGengo(String seireki) {
        String[] gengo = new String[2];
        int base = 0;
        boolean isErr = false;

        final String[] meiji = {"明治", "67", "18680908", "19120729"};
        final String[] taisho = {"大正", "11", "19120730", "19261224"};
        final String[] showa = {"昭和", "25", "19261225", "19890107"};
        final String[] heisei = {"平成", "88", "19890108", "99999999"};

        if (seireki.compareTo(heisei[2]) >= 0 && seireki.compareTo(heisei[3]) <= 0) {
            gengo[0] = heisei[0];
            base = Integer.parseInt(heisei[1]);
        } else if (seireki.compareTo(showa[2]) >= 0 && seireki.compareTo(showa[3]) <= 0) {
            gengo[0] = showa[0];
            base = Integer.parseInt(showa[1]);
        } else if (seireki.compareTo(taisho[2]) >= 0 && seireki.compareTo(taisho[3]) <= 0) {
            gengo[0] = taisho[0];
            base = Integer.parseInt(taisho[1]);
        } else if (seireki.compareTo(meiji[2]) >= 0 && seireki.compareTo(meiji[3]) <= 0) {
            gengo[0] = meiji[0];
            base = Integer.parseInt(meiji[1]);
        } else {
            isErr = true;
            gengo[0] = "";
            gengo[1] = "";
        }

        if (!isErr) {
            gengo[1] = Integer.toString(Integer.parseInt(seireki.substring(0, 4)) - base).substring(2, 4);
        }

        return gengo;
    }
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yyMMdd）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yyMMdd）
//     */
//    public static String formatToStringyyMMddFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_yyMMdd);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yyyyMM）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yyyyMM）
//     */
//    public static String formatToStringyyyyMMFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_yyyyMM);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yyyy）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yyyy）
//     */
//    public static String formatToStringyyyyFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_yyyy);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yy）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yy）
//     */
//    public static String formatToStringyyFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_yy);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> dd）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（dd）
//     */
//    public static String formatToStringddFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_dd);
//        return sdf.format(dte);
//    }
//
//
//    /**
//     * 日付時刻フォーマット変換（ミリ秒 --> yyyyMMddHHmmss）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yyyyMMddHHmmss）
//     */
//    public static String formatToStringyyyyMMddHHmmssFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(
//                DATETIME_FORMAT_yyyyMMddHHmmss);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 日付時刻フォーマット変換（ミリ秒 --> yyMMddHHmmss）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（yyMMddHHmmss）
//     */
//    public static String formatToStringyyMMddHHmmssFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(
//                DATETIME_FORMAT_yyMMddHHmmss);
//        return sdf.format(dte);
//    }
//
//    /**
//     * 時刻フォーマット変換（ミリ秒 --> HHmmssSS）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後文字列（HHmmssSS）
//     */
//    public static String formatToStringHHmmssSSFromMilliSeconds(long mills) {
//        Date dte = new Date(mills);
//        SimpleDateFormat sdf = new SimpleDateFormat(DATETIME_FORMAT_HHmmssSSS);
//        return (sdf.format(dte)).substring(0, 8);
//    }
//    // ----日付時刻フォーマット変換（ミリ秒-->int）----------------------------------------------------------------------
//    /**
//     * 日付時刻フォーマット変換（ミリ秒 --> yyyyMMddHHmmss）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（yyyyMMddHHmmss）
//     */
//    public static long formatToLongyyyyMMddHHmmssFromMilliSeconds(long mills) {
//        return Long.parseLong(new SimpleDateFormat(
//                DATETIME_FORMAT_yyyyMMddHHmmss).format(new Date(mills)));
//    }
//
//    /**
//     * 日付時刻フォーマット変換（ミリ秒 --> yyMMddHHmmss）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（yyMMddHHmmss）
//     */
//    public static long formatToLongyyMMddHHmmssFromMilliSeconds(long mills) {
//        return Long.parseLong(new SimpleDateFormat(DATETIME_FORMAT_yyMMddHHmmss).format(new Date(mills)));
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yyyyMMdd）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（yyyyMMdd）
//     */
//    public static int formatToIntyyyyMMddFromMilliSeconds(long mills) {
//        return Integer.parseInt(new SimpleDateFormat(DATETIME_FORMAT_yyyyMMdd).format(new Date(mills)));
//    }
//
//    /**
//     * 日付フォーマット変換（ミリ秒 --> yyMMdd）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（yyMMdd）
//     */
//    public static int formatToIntyyMMddFromMilliSeconds(long mills) {
//        return Integer.parseInt(new SimpleDateFormat(DATETIME_FORMAT_yyMMdd).format(new Date(mills)));
//    }
//
//    /**
//     * 時刻フォーマット変換（ミリ秒 --> HHmmssSSS）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（HHmmssSSS）
//     */
//    public static int formatToIntHHmmssSSSFromMilliSeconds(long mills) {
//        return Integer.parseInt(new SimpleDateFormat(DATETIME_FORMAT_HHmmssSSS).format(new Date(mills)));
//    }
//
//    /**
//     * 時刻フォーマット変換（ミリ秒 --> HHmmssSS）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（HHmmssSS）
//     */
//    public static int formatToIntHHmmssSSFromMilliSeconds(long mills) {
//        return Integer.parseInt((new SimpleDateFormat(DATETIME_FORMAT_HHmmssSSS).format(new Date(mills))).substring(0, 8));
//    }
//
//    /**
//     * 時刻フォーマット変換（ミリ秒 --> HHmmss）
//     *
//     * @param mills
//     *            ミリ秒
//     * @return 変換後数値（HHmmss）
//     */
//    public static int formatToIntHHmmssFromMilliSeconds(long mills) {
//        return Integer.parseInt(new SimpleDateFormat(DATETIME_FORMAT_HHmmss).format(new Date(mills)));
//    }
//
//    // ----日付時刻フォーマット変換（文字列-->文字列）----------------------------------------------------------------------
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyyMMdd）<br>
//     * （yyyyMMddHHmmss --> yyyyMMdd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（yyyyMMdd）
//     */
//    public static String formatToStringyyyyMMddFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(0, 8);
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyMMdd）<br>
//     * （yyyyMMddHHmmss --> yyMMdd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（yyMMdd）
//     */
//    public static String formatToStringyyMMddFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(2, 8);
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyyMM）<br>
//     * （yyyyMMddHHmmss --> yyyyMM）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（yyyyMM）
//     */
//    public static String formatToStringyyyyMMFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(0, 6);
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyy）<br>
//     * （yyyyMMddHHmmss --> yyyy）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（yyyy）
//     */
//    public static String formatToStringyyyyFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(0, 4);
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yy）<br>
//     * （yyyyMMddHHmmss --> yy）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（yy）
//     */
//    public static String formatToStringyyFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(2, 4);
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> dd）<br>
//     * （yyyyMMddHHmmss --> dd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（dd）
//     */
//    public static String formatToStringddFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(6, 8);
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmss）<br>
//     * （yyyyMMddHHmmss --> HHmmss）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（HHmmss）
//     */
//    public static String formatToStringHHmmssFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return yyyyMMddHHmmss.substring(8, 14);
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmssSS）<br>
//     *
//     * @param yyyyMMddHHmmssSSS
//     *            日付時刻文字列
//     * @return 変換後文字列（HHmmssSS）
//     */
//    public static String formatToStringHHmmssSSFromyyyyMMddHHmmssSSS(
//            String yyyyMMddHHmmssSSS) {
//        return yyyyMMddHHmmssSSS.substring(8, 16);
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmssSSS）<br>
//     *
//     * @param yyyyMMddHHmmssSSS
//     *            日付時刻文字列
//     * @return 変換後文字列（HHmmssSSS）
//     */
//    public static String formatToStringHHmmssSSSFromyyyyMMddHHmmssSSS(
//            String yyyyMMddHHmmssSSS) {
//        return yyyyMMddHHmmssSSS.substring(8, 17);
//    }
//
//    /**
//     * 日付時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> タイムスタンプ型 yyyy/MM/dd HH:mm:ss.SSS）<br>
//     * （yyyyMMddHHmmss --> タイムスタンプ型 yyyy/MM/dd HH:mm:ss）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後文字列（タイムスタンプ型 yyyy/MM/dd HH:mm:ss.SSS || yyyy/MM/dd HH:mm:ss）
//     */
//    public static String formatToStringTimestampFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//
//        int len = yyyyMMddHHmmss.length();
//
//        StringBuffer sb = new StringBuffer().append(
//                yyyyMMddHHmmss.substring(0, 4)).append("/").append(
//                yyyyMMddHHmmss.substring(4, 6)).append("/").append(
//                yyyyMMddHHmmss.substring(6, 8)).append(" ").append(
//                yyyyMMddHHmmss.substring(8, 10)).append(":").append(
//                yyyyMMddHHmmss.substring(10, 12)).append(":").append(
//                yyyyMMddHHmmss.substring(12, 14));
//
//        if (len >= 17) {
//            sb.append(".").append(yyyyMMddHHmmss.substring(14, 17));
//        }
//
//        return sb.toString();
//    }
//
//    // ----日付時刻フォーマット変換（文字列-->int）----------------------------------------------------------------------
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyyMMdd）<br>
//     * （yyyyMMddHHmmss --> yyyyMMdd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（yyyyMMdd）
//     */
//    public static int formatToIntyyyyMMddFromyyyyMMddHHmmss(
//            String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(0, 8));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyMMdd）<br>
//     * （yyyyMMddHHmmss --> yyMMdd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（yyMMdd）
//     */
//    public static int formatToIntyyMMddFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(2, 8));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyMMddHHmmssSSS --> yyMMdd）<br>
//     * （yyMMddHHmmss --> yyMMdd）
//     *
//     * @param yyMMddHHmmssSSS
//     *            日付時刻文字列
//     * @return 変換後数値（yyMMdd）
//     */
//    public static int formatToIntyyMMddFromyyMMddHHmmss(String yyMMddHHmmssSSS) {
//        return Integer.parseInt(yyMMddHHmmssSSS.substring(0, 6));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyyMM）<br>
//     * （yyyyMMddHHmmss --> yyyyMM）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（yyyyMM）
//     */
//    public static int formatToIntyyyyMMFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(0, 6));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yyyy）<br>
//     * （yyyyMMddHHmmss --> yyyy）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（yyyy）
//     */
//    public static int formatToIntyyyyFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(0, 4));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> yy）<br>
//     * （yyyyMMddHHmmss --> yy）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（yy）
//     */
//    public static int formatToIntyyFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(2, 4));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> MM）<br>
//     * （yyyyMMddHHmmss --> MM）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（MM）
//     */
//    public static int formatToIntmmFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(4, 6));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> dd）<br>
//     * （yyyyMMddHHmmss --> dd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（dd）
//     */
//    public static int formatToIntddFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(6, 8));
//    }
//
//    /**
//     * 日付フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> MMdd）<br>
//     * （yyyyMMddHHmmss --> MMdd）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（MMdd）
//     */
//    public static int formatToIntMMddFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(4, 8));
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmss）<br>
//     * （yyyyMMddHHmmss --> HHmmss）
//     *
//     * @param yyyyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（HHmmss）
//     */
//    public static int formatToIntHHmmssFromyyyyMMddHHmmss(String yyyyMMddHHmmss) {
//        return Integer.parseInt(yyyyMMddHHmmss.substring(8, 14));
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyMMddHHmmssSSS --> HHmmss）<br>
//     * （yyMMddHHmmss --> HHmmss）
//     *
//     * @param yyMMddHHmmss
//     *            日付時刻文字列
//     * @return 変換後数値（HHmmss）
//     */
//    public static int formatToIntHHmmssFromyyMMddHHmmss(String yyMMddHHmmss) {
//        return Integer.parseInt(yyMMddHHmmss.substring(6, 12));
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmssSSS）<br>
//     *
//     * @param yyyyMMddHHmmssSSS
//     *            日付時刻文字列
//     * @return 変換後数値（HHmmssSSS）
//     */
//    public static int formatToIntHHmmssSSSFromyyyyMMddHHmmssSSS(
//            String yyyyMMddHHmmssSSS) {
//        return Integer.parseInt(yyyyMMddHHmmssSSS.substring(8, 17));
//    }
//
//    /**
//     * 時刻フォーマット変換<br>
//     * （yyyyMMddHHmmssSSS --> HHmmssSS）<br>
//     *
//     * @param yyyyMMddHHmmssSSS
//     *            日付時刻文字列
//     * @return 変換後数値（HHmmssSS）
//     */
//    public static int formatToIntHHmmssSSFromyyyyMMddHHmmssSSS(
//            String yyyyMMddHHmmssSSS) {
//        return Integer.parseInt(yyyyMMddHHmmssSSS.substring(8, 16));
//    }
//
//    // ----日付時刻フォーマット変換（int -->
//    // int）----------------------------------------------------------------------
//    /**
//     * 日付フォーマット変換（yyyyMMdd --> yyMMdd）
//     *
//     * @param yyyyMMdd
//     *            日付時刻数字
//     * @return 変換後数値（yyMMdd）
//     */
//    public static int formatToIntyyMMddFromyyyyMMdd(int yyyyMMdd) {
//        return yyyyMMdd % 1000000;
//    }
//
//    /**
//     * 時刻フォーマット変換（HHmmssSSS --> HHmmss）
//     *
//     * @param HHmmssSSS
//     *            時刻数字
//     * @return 変換後数値（HHmmss）
//     */
//    public static int formatToIntHHmmssFromHHmmssSSS(int HHmmssSSS) {
//        return HHmmssSSS / 1000;
//    }
//
//    /**
//     * 時刻フォーマット変換（HHmmssSS --> HHmmss）
//     *
//     * @param HHmmssSS
//     *            時刻数字
//     * @return 変換後数値（HHmmss）
//     */
//    public static int formatToIntHHmmssFromHHmmssSS(int HHmmssSS) {
//        return HHmmssSS / 100;
//    }
//
//    /**
//     * 日付時刻フォーマット変換（HHmmssSSS --> HHmmssSS）
//     *
//     * @param HHmmssSSS
//     *            時刻数字
//     * @return 変換後数値（HHmmssSS）
//     */
//    public static int formatToIntHHmmssSSFromHHmmssSSS(int HHmmssSSS) {
//        return HHmmssSSS / 10;
//    }
//
//    /**
//     * 日付(6桁)の月日（4桁)を取得する。
//     *
//     * @param yyMMdd
//     *            変換したい6桁の日付を表す数字
//     * @return 4桁に変換された月日を表す数字
//     * @designer k-hayashi
//     * @author toshihiro-suzuki
//     */
//    public static int formatToIntMMddFromyyMMdd(int yyMMdd) {
//        return yyMMdd % 10000;
//    }
//
//    /**
//     * 日付時刻フォーマット変換（yyyyMMdd --> dd）
//     *
//     * @param yyyyMMdd
//     *            日付時刻数字（yyyyMMdd）
//     * @return 変換後数値（dd）
//     * @author k-hayashi
//     */
//    public static int formatToIntddFromyyyyMMdd(int yyyyMMdd) {
//        return yyyyMMdd % 100;
//    }
//
//    /**
//     * 日付(4桁)の年月日（6桁)を取得する。<BR>
//     * ・日付(4桁) ≧ システム日付(4桁)：今年のyyを付加<BR>
//     * ・日付(4桁) < システム日付(4桁)：来年のyyを付加<BR>
//     *
//     * @param MMdd
//     *            変換したい4桁の日付を表す数字
//     * @return 6桁に変換された月日を表す数字
//     * @author y-ogaki
//     */
//    public static int formatToIntyyMMddFromMMdd(int MMdd) {
//
//        // 日付[MMDD]が0の場合、0を返す
//        if (MMdd == 0) {
//            return 0;
//        }
//
//        // システム日付年[YY0000]算出
//        int sysYY = formatToIntyyFromyyyyMMddHHmmss(formatToStringyyyyMMddHHmmssFromMilliSeconds(getSystemDateTimeMills())) * 10000;
//
//        // システム日付[MMDD] < 日付[MMDD]
//        if (formatToIntMMddFromyyMMdd(formatToIntyyyyMMddFromMilliSeconds(getSystemDateTimeMills())) <= MMdd) {
//            // 今年とする
//            return sysYY + MMdd;
//
//        } else {
//            // 来年とする
//            return sysYY + 10000 + MMdd;
//        }
//    }
//
//    /**
//     * 納期日付を４桁から６桁を取得する<BR>
//     * ・納期が当月内なら今年のyyを付加<BR>
//     *
//     * @param MMdd
//     *            変換したい4桁の納期を表す数字
//     * @return 6桁に変換された納期を表す数字
//     * @author y-ogaki
//     */
//    public static int formatToIntyyMMddFromMMddForNki(int MMdd) {
//
//        // システム日付
//        String mills = formatToStringyyyyMMddHHmmssFromMilliSeconds(getSystemDateTimeMills());
//
//        // 納期の月
//        int intNkiMM = MMdd / 100;
//
//        // システム日付の月
//        int intSysMM = formatToIntmmFromyyyyMMddHHmmss(mills);
//
//        // 納期の月 == システム日付の月
//        if (intNkiMM == intSysMM) {
//
//            return formatToIntyyFromyyyyMMddHHmmss(mills) * 10000 + MMdd;
//
//        } else {
//            return formatToIntyyMMddFromMMdd(MMdd);
//
//        }
//    }
//
//    // --------------------------------------------------------------------------
//
//    /**
//     * 時刻（HH:mm）を取得する。
//     *
//     * @param HHmmSS
//     *            変換したい6桁の時刻を表す数字
//     * @return HH:mmの形式に編集された文字列
//     * @designer n-minegishi
//     * @author n-minegishi
//     */
//    public static String formatToStringHHmmColonFromHHmmss(int HHmmSS) {
//        String strHHmmSS = StringUtility.padZero(HHmmSS, 6);
//        return new StringBuilder().append(strHHmmSS.subSequence(0, 2)).append(
//                ":").append(strHHmmSS.substring(2, 4)).toString();
//    }
//
//    /**
//     * 時刻（HH:mm）を取得する。
//     *
//     * @param mills
//     *            ミリ秒
//     * @return HH:mmの形式に編集された文字列
//     * @designer n-minegishi
//     * @author n-minegishi
//     */
//    public static String formatToStringHHmmColonFromMilliSeconds(long mills) {
//        String strHHmmSS = DateTimeUtility.formatToStringHHmmssFromMilliSeconds(mills);
//        return new StringBuilder().append(strHHmmSS.subSequence(0, 2)).append(
//                ":").append(strHHmmSS.substring(2, 4)).toString();
//    }
//
//
//    /**
//     * 日付（yy/MM/dd）を取得する。
//     *
//     * @param yyMMdd
//     *            変換したい6桁の日付を表す数字
//     * @return yy/MM/ddの形式に編集された文字列
//     * @designer n-minegishi
//     * @author n-minegishi
//     */
//    public static String formatToStringyyMMddSlashFromyyMMdd(int yyMMdd) {
//        String stryyMMdd = StringUtility.padZero(yyMMdd, 6);
//        return new StringBuilder().append(stryyMMdd.substring(0, 2)).append("/").append(stryyMMdd.substring(2, 4)).append("/").append(stryyMMdd.substring(4, 6)).toString();
//    }
//
//    /**
//     * 日付（yy/MM/dd）を取得する。
//     *
//     * @param mills
//     *            ミリ秒
//     * @return yy/MM/ddの形式に編集された文字列
//     * @designer n-minegishi
//     * @author n-minegishi
//     */
//    public static String formatToStringyyMMddSlashFromMilliSeconds(long mills) {
//        String stryyMMdd = DateTimeUtility.formatToStringyyMMddFromMilliSeconds(mills);
//        return new StringBuilder().append(stryyMMdd.substring(0, 2)).append("/").append(stryyMMdd.substring(2, 4)).append("/").append(stryyMMdd.substring(4, 6)).toString();
//    }
//
//    /**
//     * 日付（MM/DD）を取得する。
//     *
//     * @param yyMMdd
//     *            変換したい6桁の日付を表す数字
//     * @return MM/DDの形式に編集された文字列
//     * @designer t-otsuka
//     * @author t-otsuka
//     */
//    public static String formatToStringMMddSlashFromyyMMdd(int yyMMdd) {
//        String stryyMMdd = StringUtility.padZero(yyMMdd, 6);
//        return new StringBuilder().append(stryyMMdd.substring(2, 4)).append("/").append(stryyMMdd.substring(4, 6)).toString();
//    }
//
//    /**
//     * 日付（MM/DD）をｾﾞﾛｻﾌﾟﾚｽして取得する。"09/01"→" 9/ 1"
//     *
//     * @param yyMMdd
//     *            変換したい6桁の日付を表す数字
//     * @return MM/DDの形式に編集された文字列
//     * @author k-hayashi
//     */
//    public static String formatToStringMMddSlashFromyyMMddWithZeroSuppress(
//            int yyMMdd) {
//        final String stryyMMdd = StringUtility.padZero(yyMMdd, 6);
//
//        final int mm = Integer.parseInt(stryyMMdd.substring(2, 4));
//        final int dd = Integer.parseInt(stryyMMdd.substring(4, 6));
//
//        return new StringBuilder().append(StringUtility.padSpaceLeft(mm, 2)).append("/").append(StringUtility.padSpaceLeft(dd, 2)).toString();
//    }
//
//    // --------------------------------------------------------------------------
//    /**
//     * 日付(6桁)を日付(8桁)に変換する<br>
//     * ただし、999999の場合は99999999を、0の場合は0を返す。 7桁以上の値が入ってきた場合、値をそのまま返す。<br>
//     * マイナスの値についてはチェックしない。<br>
//     * （AEFUN001の仕様になるべく合わせている。）<br>
//     *
//     * @param yyMMdd
//     *            変換したい6桁の日付を表す数字
//     * @return 8桁に変換された日付を表す数字
//     */
//    public static int formatToIntyyyyMMddFromyyMMdd(int yyMMdd) {
//        // TOD 日付の妥当性チェックが必要 別ロジックでチェックする予定
//        int dateyyyyMMdd = 0;
//        if (yyMMdd < 1000000) {
//            if (yyMMdd == 999999) {
//                dateyyyyMMdd = 99999999;
//            } else if (yyMMdd == 0) {
//                dateyyyyMMdd = 0;
//            } else if (yyMMdd >= KYOKAI_NEN) {
//                dateyyyyMMdd = yyMMdd + 19000000;
//            } else {
//                dateyyyyMMdd = yyMMdd + 20000000;
//            }
//        } else {
//            dateyyyyMMdd = yyMMdd;
//        }
//        return dateyyyyMMdd;
//    }
//
//    /**
//     * 日付計算（６桁） 週７日としてカウントする【ＪＺＤ００６】
//     *
//     * @param yyMMdd
//     *            日付
//     * @param nsu
//     *            日数
//     * @return 日数前・後の日付
//     */
//    public static int calculateDate(int yyMMdd, int nsu) {
//
//        // 日付(6桁)の妥当性チェック
//        if (!isCorrectDateyyMMdd(yyMMdd)) {
//            return 0;
//        }
//
//        // 日付(6桁)を日付(8桁)に変換する
//        int yyyyMMdd = formatToIntyyyyMMddFromyyMMdd(yyMMdd);
//
//        // カレンダーインスタンス
//        Calendar calendar = Calendar.getInstance();
//
//        // 指定した日付を基準日とする
//        calendar.set(yyyyMMdd / 10000, ((yyyyMMdd / 100) % 100) - 1,
//                yyyyMMdd % 100);
//
//        // 日数を加算・減算する
//        calendar.add(Calendar.DATE, nsu);
//
//        return (calendar.get(Calendar.YEAR) % 100) * 10000
//                + (calendar.get(Calendar.MONTH) + 1) * 100
//                + calendar.get(Calendar.DATE);
//
//    }
//
//    /**
//     * 日付計算（６桁） 週５日としてカウントする【ＪＺＤ００６】<BR>
//     * 土日の０日後はその日を返す
//     *
//     * @param yyMMdd
//     *            日付
//     * @param nsu
//     *            日数
//     * @return 日数前・後の日付
//     */
//    public static int calculateDateWeekday(int yyMMdd, int nsu) {
//
//        // 日付(6桁)の妥当性チェック
//        if (!isCorrectDateyyMMdd(yyMMdd)) {
//            return 0;
//        }
//
//        // 日付(6桁)を日付(8桁)に変換する
//        int dateyyyyMMdd = formatToIntyyyyMMddFromyyMMdd(yyMMdd);
//
//        // カレンダーインスタンス
//        Calendar calendar = Calendar.getInstance();
//
//        // 指定した日付を基準日とする
//        calendar.set(dateyyyyMMdd / 10000, ((dateyyyyMMdd / 100) % 100) - 1,
//                dateyyyyMMdd % 100);
//
//        // 基準日の曜日から土日を決める
//        boolean week[];
//        week = new boolean[7]; // true：土日、false：平日
//
//        int dayPoint = 0; // 曜日ポインタ
//        int substanceNsu = 0; // 進める・遡る実質の日数
//
//        switch (calendar.get(Calendar.DAY_OF_WEEK)) {
//            case Calendar.SUNDAY:
//                week[6] = true; // 土曜日を表す
//                week[0] = true; // 日曜日を表す
//                break;
//            case Calendar.MONDAY:
//                week[5] = true; // 土曜日を表す
//                week[6] = true; // 日曜日を表す
//                break;
//            case Calendar.TUESDAY:
//                week[4] = true; // 土曜日を表す
//                week[5] = true; // 日曜日を表す
//                break;
//            case Calendar.WEDNESDAY:
//                week[3] = true; // 土曜日を表す
//                week[4] = true; // 日曜日を表す
//                break;
//            case Calendar.THURSDAY:
//                week[2] = true; // 土曜日を表す
//                week[3] = true; // 日曜日を表す
//                break;
//            case Calendar.FRIDAY:
//                week[1] = true; // 土曜日を表す
//                week[2] = true; // 日曜日を表す
//                break;
//            case Calendar.SATURDAY:
//                week[0] = true; // 土曜日を表す
//                week[1] = true; // 日曜日を表す
//                break;
//        }
//
//        // 引数の日数を実質日数にセット
//        substanceNsu = nsu;
//
//        if (nsu >= 0) {
//            for (int i = 1; i <= substanceNsu; i++) {
//
//                // 曜日を進める
//                if (dayPoint == 6) {
//                    dayPoint = 0;
//                } else {
//                    dayPoint++;
//                }
//
//                // 土日なら、実質日数をプラスカウントアップ
//                if (week[dayPoint]) {
//                    substanceNsu += 1;
//                }
//            }
//
//        } else {
//            for (int j = -1; j >= substanceNsu; j--) {
//
//                // 曜日を遡る
//                if (dayPoint == 0) {
//                    dayPoint = 6;
//                } else {
//                    dayPoint--;
//                }
//
//                // 土日なら、実質日数をマイナスカウントアップ
//                if (week[dayPoint]) {
//                    substanceNsu -= 1;
//                }
//            }
//        }
//
//        // 実質進める日数を加算・実質遡る日数を減算
//        calendar.add(Calendar.DATE, substanceNsu);
//
//        // 算出した値を返す
//        return (calendar.get(Calendar.YEAR) % 100) * 10000
//                + (calendar.get(Calendar.MONTH) + 1) * 100
//                + calendar.get(Calendar.DATE);
//
//    }
//
//    /**
//     * 日付として正しいか判定する。<BR>
//     * 負、7桁以上、ゼロ、ALL9の場合は、誤りとする。
//     *
//     * @param yyMMdd
//     *            日付文字列（6桁）（yyMMdd）
//     * @return 正しい場合true
//     */
//    public static boolean isCorrectDateyyMMdd(int yyMMdd) {
//
//        // 負、7桁以上、ゼロ、ALL9の場合は、日付として誤っているとみなす。
//        if (yyMMdd < 0 || yyMMdd >= 1000000 || yyMMdd == 0 || yyMMdd == 999999) {
//            return false;
//        }
//
//        // 日付(6桁)を日付(8桁)に変換する
//        int dateyyyyMMdd = formatToIntyyyyMMddFromyyMMdd(yyMMdd);
//
//        // 日付チェック
//        return isCorrectDateyyyyMMdd(dateyyyyMMdd);
//    }
//
//    /**
//     * 日付として正しいか判定する。<BR>
//     * 負、9桁以上、ゼロ、ALL9の場合は、誤りとする。
//     *
//     * @param yyyyMMdd
//     *            日付文字列（8桁）（yyyyMMdd）
//     * @return 正しい場合true
//     */
//    public static boolean isCorrectDateyyyyMMdd(int yyyyMMdd) {
//
//        // 負、9桁以上、ゼロ、ALL9の場合は、日付として誤っているとみなす。
//        if (yyyyMMdd < 0 || yyyyMMdd >= 100000000 || yyyyMMdd == 0
//                || yyyyMMdd == 999999 || yyyyMMdd == 99999999) {
//            return false;
//        }
//
//        String stryyyymmdd = StringUtility.padZero(yyyyMMdd, 8);
//
//        // 変換用のSimpleDateFormatを作成
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
//        formatter.setLenient(false); // 日付時刻解析は、厳密に行う。
//
//        try {
//            // 日付（8桁）をDateに変換し、変換に失敗した場合は、日付として誤っているとみなす。
//            formatter.parse(stryyyymmdd);
//        } catch (ParseException e) {
//            return false;
//        }
//
//        return true;
//    }
//
//    /**
//     * ６桁の日付１つを６桁の日付４つに展開する(from用)
//     *
//     * FROM日付のみ指定時のDBの6桁日付の検索(ユーザー定義関数が使えない場合)等に使用 (例) (検索対象日付 BETWEEN fromFrom
//     * AND fromTo OR 検索対象日付 BETWEEN toFrom AND toTo)
//     *
//     * from>=500000 : fromFrom = from fromTo = 991231 toFrom = 101 toTo = 491231
//     * from<500000 : fromFrom = from fromTo = 491231 toFrom = from toTo =
//     * 491231
//     *
//     * @param from
//     *            FROM日付(6桁)
//     * @return FROM日付?(6桁),TO日付?(6桁),FROM日付?(6桁),TO日付?(6桁)のVO
//     *
//     */
//    public static final ExpandsDate6Keta expandsDate6ktFrom(final int from) {
//        int fromFrom;
//        int fromTo;
//        int toFrom;
//        int toTo;
//
//        if (from >= KYOKAI_NEN) {
//            fromFrom = from;
//            fromTo = 991231;
//            toFrom = 101;
//            toTo = 491231;
//        } else {
//            fromFrom = from;
//            fromTo = 491231;
//            toFrom = from;
//            toTo = 491231;
//        }
//
//        return new ExpandsDate6Keta(fromFrom, fromTo, toFrom, toTo);
//    }
//
//    /**
//     * ６桁の日付１つを６桁の日付４つに展開する(to用)
//     *
//     * TO日付のみ指定時のDBの6桁日付の検索(ユーザー定義関数が使えない場合)等に使用 (例) (検索対象日付 BETWEEN fromFrom
//     * AND fromTo OR 検索対象日付 BETWEEN toFrom AND toTo)
//     *
//     * to>=500000 : fromFrom = 500101 fromTo = to toFrom = 500101 toTo = to to<500000 :
//     * fromFrom = 500101 fromTo = 991231 toFrom = 101 toTo = to
//     *
//     * @param to
//     *            TO日付(6桁)
//     * @return FROM日付?(6桁),TO日付?(6桁),FROM日付?(6桁),TO日付?(6桁)のVO
//     */
//    public static final ExpandsDate6Keta expandsDate6ktTo(final int to) {
//        int fromFrom;
//        int fromTo;
//        int toFrom;
//        int toTo;
//
//        if (to >= KYOKAI_NEN) {
//            fromFrom = 500101;
//            fromTo = to;
//            toFrom = 500101;
//            toTo = to;
//        } else {
//            fromFrom = 500101;
//            fromTo = 991231;
//            toFrom = 101;
//            toTo = to;
//        }
//
//        return new ExpandsDate6Keta(fromFrom, fromTo, toFrom, toTo);
//    }
//
//    /**
//     * ６桁の日付２つを６桁の日付４つに展開する(from/to用)
//     *
//     * FROM-TO日付指定時のDBの6桁日付の検索(ユーザー定義関数が使えない場合)等に使用 (例) (検索対象日付 BETWEEN fromFrom
//     * AND fromTo OR 検索対象日付 BETWEEN toFrom AND toTo)
//     *
//     * from>=500000 to>=500000 : fromFrom = from fromTo = to toFrom = from toTO =
//     * to from<500000 to<500000 : fromFrom = from fromTo = to toFrom = from
//     * toTO = to from>=500000 to<500000 : fromFrom = from fromTo = 991231
//     * toFrom = 101 toTo = to from<500000 to>=500000 : fromFrom = 0 fromTo = 0
//     * toFrom = 0 toTo = 0
//     *
//     * @param from
//     *            FROM日付(6桁)
//     * @param to
//     *            TO日付(6桁)
//     * @return FROM日付?(6桁),TO日付?(6桁),FROM日付?(6桁),TO日付?(6桁)のVO
//     */
//    public static final ExpandsDate6Keta expandsDate6ktFromTo(final int from,
//            final int to) {
//        int fromFrom;
//        int fromTo;
//        int toFrom;
//        int toTo;
//
//        if ((from >= KYOKAI_NEN && to >= KYOKAI_NEN)
//                || (from < KYOKAI_NEN && to < KYOKAI_NEN)) {
//            fromFrom = from;
//            fromTo = to;
//            toFrom = from;
//            toTo = to;
//        } else {
//            if (from >= KYOKAI_NEN && to < KYOKAI_NEN) {
//                fromFrom = from;
//                fromTo = 991231;
//                toFrom = 101;
//                toTo = to;
//            } else {
//                // ERROR
//                fromFrom = 0;
//                fromTo = 0;
//                toFrom = 0;
//                toTo = 0;
//            }
//        }
//
//        return new ExpandsDate6Keta(fromFrom, fromTo, toFrom, toTo);
//    }
//
//    /**
//     * 全日付を含む６桁の日付４つに展開する(from/to用)
//     *
//     * @return FROM日付?(6桁),TO日付?(6桁),FROM日付?(6桁),TO日付?(6桁)のVO
//     */
//    public static final ExpandsDate6Keta expandsDate6ktAll() {
//        return new ExpandsDate6Keta(500101, 991231, 101, 491231);
//    }
//
//    /**
//     * 西暦を和暦に変換します。
//     *
//     * @param seireki
//     *            西暦（YYYYMMDD）
//     * @return 和暦（YYMMDD）
//     * @author s-sagiuchi
//     * @designer k-haida
//     *
//     * 西暦 ＞＝ 19890107 の場合 平成・・・ 西暦の下２桁＋12 を リターン 西暦 ＜ 19890107 の場合 昭和・・・
//     * 西暦の下２桁－25 を リターン
//     *
//     * ※現在 元号を使用しているプログラムがない為、年号のみを返却する作りになっています。
//     */
//    public static int formatSeirekiToWareki(int seireki) {
//        if (19890107 <= seireki) {
//            return (seireki + 120000) % 1000000;
//        } else {
//            return (seireki - 250000) % 1000000;
//        }
//    }
//
//    /**
//     * 和暦を西暦に変換する（「平成」専用）<br>
//     *
//     * @param wareki
//     *            和暦（YYMMDD）
//     * @return 西暦（YYMMDD）
//     * @author n-minegishi
//     */
//    public static final int formatWarekiToSeirekiForHeisei(final int wareki) {
//        return (wareki + 880000) % 1000000;
//    }
//
//    /**
//     * 日付時刻フォーマット変換（yyMMdd --> yy）
//     *
//     * @param yyMMdd
//     *            日付時刻数字（yyMMdd）
//     * @return 変換後数値（yy）
//     * @author s-oohana
//     */
//    public static int formatToIntyyFromyyMMdd(int yyMMdd) {
//        return yyMMdd / 10000;
//    }
//
//    /**
//     * 日付時刻フォーマット変換（yyMMdd --> MM）
//     *
//     * @param yyMMdd
//     *            日付時刻数字（yyMMdd）
//     * @return 変換後数値（MM）
//     * @author s-oohana
//     */
//    public static int formatToIntMMFromyyMMdd(int yyMMdd) {
//        return ((yyMMdd / 100) % 100);
//    }
//
//    /**
//     * 日付時刻フォーマット変換（yyMMdd --> dd）
//     *
//     * @param yyMMdd
//     *            日付時刻数字（yyMMdd）
//     * @return 変換後数値（dd）
//     * @author s-sagiuchi
//     */
//    public static int formatToIntddFromyyMMdd(int yyMMdd) {
//        return yyMMdd % 100;
//    }
}
