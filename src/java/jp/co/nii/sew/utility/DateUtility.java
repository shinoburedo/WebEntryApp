package jp.co.nii.sew.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.business.SystemTime;

/**
 * <p>タイトル: 日付関係共通クラス</p>
 * <p>説明: 日付に関するチェックと変換を行う。</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class DateUtility {

	/**
	 * 日付の適正をチェック（月に対して日付が適正かのチェックができる。）<BR>
	 * <b>解説：</b>引数1、2、3、4に元号、年、月、日を指定すると存在する（適切な）場合trueが返る。
	 * 年・月・日の前ゼロなしでも使用可。
	 * @param era String 元号
	 * @param year String 年
	 * @param month String 月
	 * @param day String 日
	 * @return boolean 存在する場合true
	 */
	private static final boolean existsDateToEyymmdd(
		final String era,
		final String year,
		final String month,
		final String day) {

		// 西暦変換
		int cera = calculateAddedYears(era);
		int iADyear = Integer.parseInt(year) + cera;
		String strADyear = Integer.toString(iADyear);

		//ありうる日付かをチェック
		boolean ret = existsDate(strADyear, month, day);
		return ret;
	}

	/**
	 * 日付の適正をチェック（元号＋年月）<BR>
	 * <b>解説：引数1、2、3に元号、年、月を指定すると存在する
	 * （適切な）場合trueが返る。</b>
	 * @param era String 元号
	 * @param year String 年
	 * @param month String 月
	 * @return boolean 存在する場合true
	 */
	public final boolean existsDateToEyymm(
		final String era,
		final String year,
		final String month) {

		boolean returnFlg = true;

		try {

			// 元号の年の範囲が正しいかチェック
			if (!this.checkYear(era, Integer.parseInt(year))) {
				returnFlg = false;

				// 月が正しいか
			} else if (!this.checkMonth(Integer.parseInt(month))) {
				returnFlg = false;
			}
		} catch (NumberFormatException ex) { // 数値でないときはエラー
			ex.printStackTrace();
			returnFlg = false;
		}

		return returnFlg;
	}

	/**
	 * 日付の適正をチェック（元号＋年月日）<BR>
	 * <b>解説：引数1、2、3、4に元号、年、月、日を指定し、適正な場合tureが戻る。</b>
	 * @param era String 元号
	 * @param year String 年
	 * @param month String 月
	 * @param day String 日
	 * @return boolean 適正な場合ture
	 */
	public static final boolean proprietyDate(
		final String era,
		final String year,
		final String month,
		final String day) {

		boolean ret = true;

		try {

			/* 元号の年の範囲が正しいかチェック
			  （元号ごとの日付不正に対応　例：昭和64年1月8日は存在しない）
			   月が正しいか
			   カレンダーが正しいか */
			ret =
				checkYearMonthDay(era, Integer.parseInt(year),Integer.parseInt(month + day))
					&& checkMonth(Integer.parseInt(month))
					&& existsDateToEyymmdd(era, year, month, day);
		} catch (NumberFormatException ex) { // 数値でないときはエラー
			ex.printStackTrace();
			ret = false;
		}

		return ret;
	}

	/**
	 * 引数の年月日が、ありうる日付かをチェックする。引数で渡す値は、前ゼロなしでも可。
	 * @param year String 年（西暦）yyyy形式
	 * @param month String 月
	 * @param day String 日
	 * @return boolean 日付として正しい場合true
	 */
	public static final boolean existsDate(
		final String year,
		final String month,
		final String day) {

		boolean ret = false;
		Calendar inputCal = new GregorianCalendar();
		inputCal.set(
			Integer.parseInt(year),
			Integer.parseInt(month) - 1,
			Integer.parseInt(day));
		int calYear = inputCal.get(Calendar.YEAR);
		int calMonth = inputCal.get(Calendar.MONTH) + 1;
		int calDay = inputCal.get(Calendar.DATE);

		if (Integer.parseInt(year) == calYear
			&& Integer.parseInt(month) == calMonth
			&& Integer.parseInt(day) == calDay) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 未来の日付かどうかをチェックする。引数の月・日の値は、必ず前ゼロ付きのものを渡す。
	 * @param year String 年（西暦） yyyy形式
	 * @param month String 月 MM形式
	 * @param day String 日 dd形式
	 * @return boolean 未来日付の場合true
	 */
	public static final boolean isFuture(
		final String year,
		final String month,
		final String day) {

		boolean ret = true;
		int ymd = Integer.parseInt(year + month + day);
		SystemTime sysTim = new SystemTime();
		int sysYmd = Integer.parseInt(sysTim.getymd1());
		if (sysYmd >= ymd) {
			ret = false;
		}
		return ret;
	}


	/**
	 * 現在の日時が、指定した期間内かどうかをチェックする
	 * 期間内であればtrue,そうでなければfalseを返す
	 * @param fromDate 指定日時 始め(yyyyMMddHHmmdd形式)
	 * @param toDate 指定日時 終わり(yyyyMMddHHmmdd形式)
	 * @return
	 * @throws ParseException
	 */
	public static final boolean isRangeDate(final String fromDate, final String toDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 期限日時をCalender型へ
		Date dateFrom = sdf.parse(fromDate);
		Date dateTo = sdf.parse(toDate);
		Calendar calFrom = Calendar.getInstance();
		Calendar calTo = Calendar.getInstance();
		calFrom.setTime(dateFrom);
		calTo.setTime(dateTo);

		// 現在時刻のCalender型を取得
		Calendar timeNow = Calendar.getInstance();

		// 日時を比較
		int retFrom = calFrom.compareTo(timeNow);

		int retTo = calTo.compareTo(timeNow);

		// 現在時刻が申込期間内でなかったらエラー
		if (retFrom > 0 || retTo < 0) {
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 現在の日時が、指定した日時に対してどのような状況か
	 * 同じだったら0、過ぎていたらプラス値、まだ迎えていなかったらマイナス値を返す
	 * @param strDate 指定日時(yyyyMMddHHmmdd形式)
	 * @return
	 * @throws ParseException
	 */
	public static final int dateCompare(final String strDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		// 期限日時をCalender型へ
		Date dateDate = sdf.parse(strDate);
		Calendar calDate = Calendar.getInstance();
		calDate.setTime(dateDate);

		// 現在時刻のCalender型を取得
		Calendar timeNow = Calendar.getInstance();

		// 日時を比較
		return timeNow.compareTo(calDate);

	}





	/**
	 * １～１２の月か否かをチェック<BR>
	 * <b>解説：引数1にチェックする数値を指定し1から12の間かチェックする。 適切ならばtrueが返る。</b>
	 *
	 * @param month
	 *            int 月
	 * @return boolean 適切ならばtrue
	 */
	public static final boolean checkMonth(final int month) {
		boolean ret = false;

		// 1～12ならOK
		if (month >= 1 && month <= 12) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 明治、大正、昭和、平成の年号のチェック<BR>
	 * <b>解説：引数1に文字列（年号）を指定し、引数2に数値（年）を指定して
	 * 年号に対する数値が適切かチェックする。
	 * 適切ならばtrueが返る。</b>
	 * @param String nengo 年号
	 * @param String year 年
	 * @return boolean 適切ならばtrue
	 */
	public final boolean checkYear(final String nengo, final int year) {

		boolean ret = false;
		CheckUtility checkUtility = new CheckUtility();

		// 年号が指定されいる場合
		if (!checkUtility.isBlank(nengo)) {

			// 明治の場合
			if (nengo.equals("明治") || nengo.equals("1")) {
				if (year >= 1 && year <= 45) {
					ret = true;
				}

				// 大正の場合
			} else if (nengo.equals("大正") || nengo.equals("2")) {
				if (year >= 1 && year <= 15) {
					ret = true;
				}

				// 昭和の場合
			} else if (nengo.equals("昭和") || nengo.equals("3")) {
				if (year >= 1 && year <= 64) {
					ret = true;
				}

				// 平成の場合
			} else if (nengo.equals("平成") || nengo.equals("4")) {
				if (year >= 1) {
					ret = true;
				}
			}
		}

		return ret;
	}

	/**
	 * 明治、大正、昭和、平成の年号のチェック<BR>
	 * <b>解説：引数1に文字列（年号）を指定し、引数2に数値（年）、
	 * 			引数3に数値（月日）を指定し、
	 * 年号に対する数値が適切かチェックする。
	 * 適切ならばtrueが返る。</b>
	 * @param String nengo 年号
	 * @param String year 年
	 * @return boolean 適切ならばtrue
	 */
	public static final boolean checkYearMonthDay(final String nengo, final int year, final int monthDay) {

		boolean ret = false;
		CheckUtility checkUtility = new CheckUtility();

		// 年号が指定されいる場合
		if (!checkUtility.isBlank(nengo)) {

			// 明治の場合
			if (nengo.equals("明治") || nengo.equals("1")) {
				if (year >= 1 && year <= 45) {
					ret = true;
					//元年で9月7日以前、または45年で7月30日以降の場合不正日付
					if((year == 1 && monthDay <= 907) || (year == 45 && monthDay >= 730)){
						ret = false;
					}
				}

				// 大正の場合
			} else if (nengo.equals("大正") || nengo.equals("2")) {
				if (year >= 1 && year <= 15) {
					ret = true;
					//元年で7月29日以前、または15年で12月25日以降の場合不正日付
					if((year == 1 && monthDay <= 729) || (year == 15 && monthDay >= 1225)){
						ret = false;
					}
				}

				// 昭和の場合
			} else if (nengo.equals("昭和") || nengo.equals("3")) {
				if (year >= 1 && year <= 64) {
					ret = true;
					//元年で12月24日以前、または64年で1月8日以降の場合不正日付
					if((year == 1 && monthDay <= 1224) || (year == 64 && monthDay >= 108)){
						ret = false;
					}
				}

				// 平成の場合
			} else if (nengo.equals("平成") || nengo.equals("4")) {
				if (year >= 1) {
					ret = true;
					//元年で1月7日以前の場合不正日付
					if((year == 1 && monthDay <= 107)){
						ret = false;
					}
				}
			}
		}

		return ret;
	}

	/**
	 * 日付の引数に年月日を合わせたものを返す。○年○月○日形式
	 * @param year String 年
	 * @param month String 月
	 * @param day String 日
	 * @return String 日付　○年○月○日形式
	 */
	public static String convertYMDToYMD_n(
		final String year,
		final String month,
		final String day) {

		String conZen = year + "年" + month + "月" + day + "日";

		return conZen;
	}

	/**
	 * 日付の引数に年月日を合わせたものを返す。○年○月○日形式
	 * @param String date 年月日　(半角数字)
	 * @return String cnvstr　○年○月○日形式(前０無し)
	 */
	public static String convertYMDToYMD(final String date) {
		String cnvstr = "";

		if (date.length() >= 8) {
			String year = Integer.valueOf(date.substring(0, 4)).toString();
			String month = Integer.valueOf(date.substring(4, 6)).toString();
			String day = Integer.valueOf(date.substring(6, 8)).toString();
			cnvstr =
				StringUtility.convertNumberToZenkaku(year)
					+ "年"
					+ StringUtility.convertNumberToZenkaku(month)
					+ "月"
					+ StringUtility.convertNumberToZenkaku(day)
					+ "日";
		} else {
			cnvstr = "未定";
		}
		return cnvstr;
	}

    /**
     * 日付の引数に月日を合わせたものを返す。○月○日形式
     * @param String date 年月日　(半角数字)
     * @return String cnvstr　○月○日形式(前０無し)
     */
    public static String convertYMDToMD(final String date) {
        String cnvstr = "";

        if (date.length() >= 8) {
            String month = Integer.valueOf(date.substring(4, 6)).toString();
            String day = Integer.valueOf(date.substring(6, 8)).toString();
            cnvstr =
                    StringUtility.convertNumberToZenkaku(month)
                    + "月"
                    + StringUtility.convertNumberToZenkaku(day)
                    + "日";
        } else {
            cnvstr = "未定";
        }
        return cnvstr;
    }

	/**
	 * 和暦日付の引数の漢字和暦日付を返す。漢字年号e年M月d日形式
	 * @param gemd String 元号コード付き日付 例：3490904
	 * 元号コード　1:明治、2:大正、3:昭和、4:平成
	 * @return String 日付（和暦） 漢字年号e年M月d日形式
	 */
	public static String convertEYMDToEYMD_n(final String gemd) {
		String lsGemdN = "";
		String lsG = "";

		if (gemd != null && gemd.length() == 7) {

			String g = gemd.substring(0, 1);

			if (g.equals("4")) {

				lsG = "平成";

			} else if (g.equals("3")) {

				lsG = "昭和";

			} else if (g.equals("2")) {

				lsG = "大正";

			} else if (g.equals("1")) {

				lsG = "明治";

			} else {

				lsG = "";
			}

			int liE = Integer.parseInt(gemd.substring(1, 3));
			int liM = Integer.parseInt(gemd.substring(3, 5));
			int liD = Integer.parseInt(gemd.substring(5, 7));

			lsGemdN = lsG + liE + "年" + liM + "月" + liD + "日";
		}

		return lsGemdN;
	}

	/**
	 * 西暦から和暦に変換する。<BR>
	 * <b>解説：引数に西暦を指定すると元号コードと元号名と和暦年を返す。</b>
	 * @param yearAD String 西暦
	 * @return String[] 元号コード・元号名・和暦年
	 */
	public static String[] convertSeirekiToWareki(final String yearAD) {

		// {元号コード,元号名,和暦年}
		String[] wareki = new String[3];

		try {
			int iYearAD = Integer.parseInt(yearAD);

			if (iYearAD >= 1989) {

			//　注意！！このメソッドでは、1989年は、平成元年ではなく平成1年となります。
			//　　　　（1989年1月1日～1月7日は、昭和64年ではなく、平成1年となってしまいます）

				wareki[0] = "4";
				wareki[1] = "平成";
				wareki[2] = Integer.toString(iYearAD - 1988);

			} else if (iYearAD >= 1926 && iYearAD < 1989) {

				wareki[0] = "3";
				wareki[1] = "昭和";
				wareki[2] = Integer.toString(iYearAD - 1925);

			} else if (iYearAD >= 1912 && iYearAD < 1926) {

				wareki[0] = "2";
				wareki[1] = "大正";
				wareki[2] = Integer.toString(iYearAD - 1911);

			} else if (iYearAD >= 1868 && iYearAD < 1912) {

				wareki[0] = "1";
				wareki[1] = "明治";
				wareki[2] = Integer.toString(iYearAD - 1867);

			} else {

				wareki[0] = "0";
				wareki[1] = "";
				wareki[2] = yearAD;
			}

			return wareki;

			// 数値でないときはエラー
		} catch (NumberFormatException ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 和暦から西暦に変換するために加算する年数を求める。<BR>
	 * <b>解説：引数に元号を指定すると加算すべき年数を返す。</b>
	 * @param era String 元号
	 * @return int 加算すべき年数
	 */
	public static int calculateAddedYears(final String era) {

		int cera = -1;
		CheckUtility checkUtility = new CheckUtility();

		if (!checkUtility.isBlank(era)) {
			if (era.equals("明治") || era.equals("1")) {
				cera = 1867;
			} else if (era.equals("大正") || era.equals("2")) {
				cera = 1911;
			} else if (era.equals("昭和") || era.equals("3")) {
				cera = 1925;
			} else if (era.equals("平成") || era.equals("4")) {
				cera = 1988;
			}
		}
		return cera;
	}

	/**
	 * 文字列をカレンダーオブジェクトに変換する。
	 * @param strYear 年
	 * @param strMonth 月
	 * @param strDay 日
	 * @return Calendar カレンダーオブジェクト。変換できないときはnull。
	 */
	public static Calendar convertStringToCalendar(
		final String strYear,
		final String strMonth,
		final String strDay) {

		GregorianCalendar cal = null;

		try {
			int iYear = Integer.parseInt(strYear, 10);
			int iMonth = Integer.parseInt(strMonth, 10) - 1;
			int iDay = Integer.parseInt(strDay, 10);

			cal = new GregorianCalendar();
			cal.setLenient(true);
			cal.clear();
			cal.set(iYear, iMonth, iDay);

			boolean bYear = (iYear == cal.get(Calendar.YEAR));
			boolean bMonth = (iMonth == cal.get(Calendar.MONTH));
			boolean bDay = (iDay == cal.get(Calendar.DAY_OF_MONTH));

			if (!(bYear && bMonth && bDay)) {
				cal = null;
			}
		} catch (NumberFormatException ex) {
			cal = null;
		}

		return cal;
	}

	/**
	 * カレンダーオブジェクトの日数差を計算する。
	 * @param calFromDate Calendar 小さい日付
	 * @param calToDate Calendar 大きい日付
	 * @return int 日数差
	 */
	public static int subtractDay(
		final Calendar calFromDate,
		final Calendar calToDate) {

		long lFromDate = calFromDate.getTime().getTime();
		long lToDate = calToDate.getTime().getTime();
		long lDistance = lToDate - lFromDate;
		int iDate = (int) (lDistance / 86400000L);

		return iDate;
	}

	/**
	 * 「1年」「01年」を「元年」に変換する。引数が"1"または"01"でないとき、引数の値を
	 * そのまま返す。
	 * @param str String 年
	 * @return String 引数が"1"または"01"のとき、"元"。それ以外のとき、引数の値。
	 */
	public static String convert01ToGannen(final String str) {
		String retStr = str;
		CheckUtility checkUtility = new CheckUtility();

		if (!checkUtility.isBlank(str)) {
			if (str.equals("1") || str.equals("01")) {
				retStr = "元";
			}
		}
		return retStr;
	}

	/**
	 * 西暦日付の引数の漢字和暦日付を返す。漢字年号e年M月d日形式
	 * @param ymd String yyyymmdd形式で必ず8桁
	 * @return String 日付（和暦）漢字年号e年M月d日形式。桁数が不足している場合、空文字。
	 */
	public static String convertYyyymmddToEYMD_n(final String ymd) {
		String lsGemdN = "";
		String lsG = "";
		String lsE = "";
		int liM = 0;
		int liD = 0;

		if (ymd != null && ymd.length() == 8) {
			lsG = convertSeirekiToWareki(ymd.substring(0, 4))[1];
			lsE = convertSeirekiToWareki(ymd.substring(0, 4))[2];
			liM = Integer.parseInt(ymd.substring(4, 6));
			liD = Integer.parseInt(ymd.substring(6, 8));
			lsGemdN = lsG + lsE + "年" + liM + "月" + liD + "日";
		}
		return lsGemdN;
	}

	/**
	 * 西暦日付の引数の漢字和暦日付（数字部分は全角）を返す。漢字年号e年M月d日形式
	 * @param ymd String yyyymmdd形式で必ず8桁
	 * @return String 日付（和暦）漢字年号e年M月d日形式。桁数が不足している場合、空文字。
	 */
	public static String convertYyyymmddToEYMD_nZenkaku(final String ymd) {
		String lsGemdN = "";
		String lsG = "";
		String lsE = "";
		String lsM = "";
		String lsD = "";

		if (ymd != null && ymd.length() == 8) {
			lsG = convertSeirekiToWareki(ymd.substring(0, 4))[1];
			lsE = StringUtility
					.convertNumberToZenkaku(convertSeirekiToWareki(ymd
							.substring(0, 4))[2]);
			lsM = StringUtility.convertNumberToZenkaku(Integer.toString(Integer
					.parseInt(ymd.substring(4, 6))));
			lsD = StringUtility.convertNumberToZenkaku(Integer.toString(Integer
					.parseInt(ymd.substring(6, 8))));

			lsGemdN = lsG + lsE + "年" + lsM + "月" + lsD + "日";
		}
		return lsGemdN;
	}

	/**
	 * 年（yyyy形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 西暦年　固定4桁
	 */
	public static String convertDateToYyyy(final Date inpDate) {
		return new SimpleDateFormat("yyyy").format(inpDate);
	}

	/**
	 * 月（MM形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 月　固定2桁
	 */
	public static String convertDateToMm(final Date inpDate) {
		return new SimpleDateFormat("MM").format(inpDate);
	}

	/**
	 * 日（dd形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 日　固定2桁
	 */
	public static String convertDateToDd(final Date inpDate) {
		return new SimpleDateFormat("dd").format(inpDate);
	}

	/**
	 * 年月日時分秒（yyyyMMddHHmmss形式）（西暦）を戻す。
	 * @param inpDate Date 日付
	 * @return String 年月日時分秒（yyyyMMddHHmmss形式）
	 */
	public static String convertDateToYyyyMMddHHmmss(final Date inpDate) {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(inpDate);
	}

	/**
	 * 年月日（yyyyMMdd形式）（西暦）を戻す。
	 * @param inpDate Date 日付
	 * @return String 年月日（yyyyMMdd形式）
	 */
	public static String convertDateToYyyyMMdd(final Date inpDate) {
		return new SimpleDateFormat("yyyyMMdd").format(inpDate);
	}

	/**
	 * 年月日（yyyy年M月d日形式）（西暦）を戻す。
	 * @param inpDate Date 日付
	 * @return String 年月日（yyyy年M月d日形式）
	 */
	public static String convertDateToYyyyMd_n(final Date inpDate) {
		return new SimpleDateFormat("yyyy'年'M'月'd'日'").format(inpDate);
	}

	/**
	 * 年月日（yyyy/M/d形式）（西暦）を戻す。
	 * @param inpDate Date 日付
	 * @return String 年月日（yyyy/M/d形式）
	 */
	public static String convertDateToYyyyMdBySlash(final Date inpDate) {
		return new SimpleDateFormat("yyyy/M/d").format(inpDate);
	}

	/**
	 * 和暦での年月日（前ゼロなしの和暦年/M/d形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 和暦での年月日（前ゼロなしの和暦年/M/d形式）
	 */
	public static String convertDateToEMdBySlash(final Date inpDate) {

		//西暦での年（yyyy形式）を取得
		String strYyyy = convertDateToYyyy(inpDate);

		//和暦年を求める
		String wareki[] = convertSeirekiToWareki(strYyyy);
		return wareki[2] + new SimpleDateFormat("/M/d").format(inpDate);
	}

	/**
	 * 元号年月日（元号○年M月d日形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 元号年月日（元号○年M月d日形式）
	 */
	public static String convertDateToEMd_n(final Date inpDate) {

		//西暦での年（yyyy形式）を取得
		String strYyyy = convertDateToYyyy(inpDate);

		//和暦年を求める
		String wareki[] = convertSeirekiToWareki(strYyyy);
		return wareki[1]
			+ wareki[2]
			+ new SimpleDateFormat("'年'M'月'd'日'").format(inpDate);
	}

	/**
	 * 曜日（英語・短縮形）を戻す。
	 * @param inpDate Date 日付
	 * @return String 曜日（英語・短縮形）
	 */
	public static String getYobiUS(final Date inpDate) {
		return new SimpleDateFormat("E", Locale.US).format(inpDate);
	}

	/**
	 * 曜日（日本語・短縮形）を戻す。
	 * @param inpDate Date 日付
	 * @return String 曜日（日本語・短縮形）
	 */
	public static String getYobiJP(final Date inpDate) {
		return new SimpleDateFormat("E").format(inpDate);
	}

	/**
	 * 時分秒（HHmmss形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 時分秒（HHmmss形式）
	 */
	public static String getHHmmss(final Date inpDate) {
		return new SimpleDateFormat("HHmmss").format(inpDate);
	}

	/**
	 * 時分秒（H時m分s秒形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 時分秒（H時m分s秒形式）
	 */
	public static String getHms_n(final Date inpDate) {
		return new SimpleDateFormat("H'時'm'分's'秒'").format(inpDate);
	}

	/**
	 * 時分秒（HH:mm:ss形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 時分秒（HH:mm:ss形式）
	 */
	public static String getHHmmssByColon(final Date inpDate) {
		return new SimpleDateFormat("HH':'mm':'ss").format(inpDate);
	}

	/**
	 * 時分（H時m分形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 時分（H時m分形式）
	 */
	public static String getHm_n(final Date inpDate) {
		return new SimpleDateFormat("H'時'm'分'").format(inpDate);
	}

	/**
	 * 時分（HH:mm形式）を戻す。
	 * @param inpDate Date 日付
	 * @return String 時分（HH:mm形式）
	 */
	public static String getHHmmByColon(final Date inpDate) {
		return new SimpleDateFormat("HH':'mm").format(inpDate);
	}

	/**
	 * 日時の引数に年月日時分秒を合わせたものを返す。○年○月○日○時○分○秒形式
	 * @param String date 年月日　(半角数字)
	 * @return String cnvstr　○年○月○日○時○分○秒形式(前０無し)
	 */
	public static String convertYMDHMSToYMDHMS(final String date) {
		String cnvstr = "";

		if (date.length() >= 14) {
			String year = Integer.valueOf(date.substring(0, 4)).toString();
			String month = Integer.valueOf(date.substring(4, 6)).toString();
			String day = Integer.valueOf(date.substring(6, 8)).toString();
			String hour = Integer.valueOf(date.substring(8, 10)).toString();
			String minute = Integer.valueOf(date.substring(10, 12)).toString();
			String second = Integer.valueOf(date.substring(12, 14)).toString();
			cnvstr =
				StringUtility.convertNumberToZenkaku(year)
					+ "年"
					+ StringUtility.convertNumberToZenkaku(month)
					+ "月"
					+ StringUtility.convertNumberToZenkaku(day)
					+ "日"
					+ StringUtility.convertNumberToZenkaku(hour)
					+ "時"
					+ StringUtility.convertNumberToZenkaku(minute)
					+ "分"
					+ StringUtility.convertNumberToZenkaku(second)
					+ "秒";
		} else {
			cnvstr = "未定";
		}
		return cnvstr;
	}

	/**
	 * 時刻の引数に時分を合わせたものを返す。○時○分形式
	 * @param String time 時分　(半角数字)
	 * @return String cnvstr　○時○分形式(前０無し)
	 */
	public static String convertHMToHM(final String date) {
		String cnvstr = "";

		if (date.length() >= 4) {
			String hour = Integer.valueOf(date.substring(0, 2)).toString();
			String minute = Integer.valueOf(date.substring(2, 4)).toString();
			cnvstr =
				StringUtility.convertNumberToZenkaku(hour)
					+ "時"
					+ StringUtility.convertNumberToZenkaku(minute)
					+ "分";
		} else {
			cnvstr = date;
		}
		return cnvstr;
	}

	/**
	 * 和暦をyyyyMMdd形式に変換して返す
	 * 変換できなかった場合、ブランクを返す
	 * @param era 元号　1:明治、2:大正、3:昭和、4:平成
	 * @param year 和暦の年
	 * @param month 月
	 * @param day 日
	 * @return yyyyMMdd形式に変換した日付
	 */
	public static String convertWarekiToYYYYMMDD(final String era,final String year,final String month,final String day) {
		String yyyyMMdd = "";
		int cera = calculateAddedYears(era);
		if(cera >= 0){
			int iADyear = Integer.parseInt(year) + cera;
			String cyear = Integer.toString(iADyear);

			yyyyMMdd = cyear + month + day;
		}

		return yyyyMMdd;
	}

    /**
     * 西暦日付の引数の曜日付き漢字日付（数字部分は全角）を返す。日付M月d日（曜日）形式
     * @param ymdhms String yyyymmddhhmmss形式で必ず8桁以上
     * @return String 日付M月d日（曜日）形式。桁数が不足している場合、空文字。
     */
    public static String convertYmdhmToMDWeekDay_nZenkaku(final String ymdhms) {

        final String weeks[] = {"日", "月", "火", "水", "木", "金", "土"};

        String lsGemdN = "";
        String lsM = "";
        String lsD = "";
        String lsWD = "";//曜日

        if (ymdhms != null && ymdhms.length() >= 8) {
            lsM = StringUtility.convertNumberToZenkaku(Integer.toString(Integer.parseInt(ymdhms.substring(4, 6))));
            lsD = StringUtility.convertNumberToZenkaku(Integer.toString(Integer.parseInt(ymdhms.substring(6, 8))));

            //曜日の取得
            Calendar calender = Calendar.getInstance();
            calender.set(Integer.parseInt(ymdhms.substring(0, 4)),
                    Integer.parseInt(ymdhms.substring(4, 6)) - 1, Integer.parseInt(ymdhms.substring(6, 8)));
            int w = calender.get(Calendar.DAY_OF_WEEK);  //1(日)?7(土)の数値を返す
            lsWD = weeks[w - 1];
            
            lsGemdN = lsM + "月" + lsD + "日（" + lsWD + "）";
        }
        return lsGemdN;
    }

}
