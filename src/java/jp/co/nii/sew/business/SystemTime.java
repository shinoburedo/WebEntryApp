package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.DateUtility;
import java.util.Date;

/**
 * <p>タイトル: システム日付取得</p>
 * <p>説明: システム日付を取得し、DateUtilityで編集する共通クラス</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author M.Fukuyama
 */
public class SystemTime {

	/** Date */
	private Date idSysDate;

	/**
	 * コンストラクタ
	 * 現在のの日付を取得する
	 */
	public SystemTime() {

		// 日付を取得する
		this.idSysDate = new Date();
	}

	/**
	 * インスタンス作成時の日付（年）を返す。
	 * @return String 西暦年　固定4桁
	 */
	public final String getyyyy() {
		return DateUtility.convertDateToYyyy(this.idSysDate);
	}

	/**
	 * インスタンス作成時の日付（月）を返す。
	 * @return String 月　固定2桁
	 */
	public final String getmm() {
		return DateUtility.convertDateToMm(this.idSysDate);
	}

	/**
	 * インスタンス作成時の日付（日）を返す。
	 * @return String 日　固定2桁
	 */
	public final String getdd() {
		return DateUtility.convertDateToDd(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（西暦）、時刻を返す。yyyyMMddHHmmss形式
	 * @return String　日付（西暦）、時刻　yyyyMMddHHmmss形式
	 */
	public final String getymdhms() {
		return DateUtility.convertDateToYyyyMMddHHmmss(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（西暦）を返す。yyyyMMdd形式
	 * @return String　日付（西暦）　yyyyMMdd形式
	 */
	public final String getymd1() {
		return DateUtility.convertDateToYyyyMMdd(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（西暦）を返す。年月日付き。
	 * @return String　日付（西暦）　年月日付き
	 */
	public final String getymd2() {
		return DateUtility.convertDateToYyyyMd_n(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（西暦）を返す。yyyy/M/d形式
	 * @return String　日付（西暦）　yyyy/M/d形式
	 */
	public final String getymd3() {
		return DateUtility.convertDateToYyyyMdBySlash(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（和暦）を返す。e/M/d形式
	 * @return String　日付（和暦）　e/M/d形式
	 */
	public final String getgmd1() {
		return DateUtility.convertDateToEMdBySlash(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の日付（和暦）を返す。平成e/M/d形式
	 * @return String　日付（和暦）　平成e/M/d形式
	 */
	public final String getgmd2() {
		return DateUtility.convertDateToEMd_n(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の曜日を返す。英語　短縮形
	 * @return String　曜日　英語
	 */
	public final String getyobi1() {
		return DateUtility.getYobiUS(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の曜日を返す。日本語　短縮形
	 * @return String　曜日　日本語
	 */
	public final String getyobi2() {
		return DateUtility.getYobiJP(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の時刻を返す。HHmmss形式
	 * @return String　時刻　HHmmss形式
	 */
	public final String gethms1() {
		return DateUtility.getHHmmss(this.idSysDate);
	}

	/**
	 *  インスタンス作成時の時刻を返す。H'時'm'分's'秒'形式
	 * @return String　時刻　H'時'm'分's'秒'形式
	 */
	public final String gethms2() {
		return DateUtility.getHms_n(this.idSysDate);
	}

	/**
	 * インスタンス作成時の時刻を返す。HH:mm:ss形式
	 * @return　String システム時刻　時分秒　HH:mm:ss形式
	 */
	public final String gethms3() {
		return DateUtility.getHHmmssByColon(this.idSysDate);
	}

	/**
	 * インスタンス作成時の時刻を返す。H'時'm'分'形式
	 * @return　String システム時刻　時分　H'時'm'分'形式
	 */
	public final String gethm2() {
		return DateUtility.getHm_n(this.idSysDate);
	}

	/**
	 * インスタンス作成時の時刻を返す。HH':'mm形式
	 * @return　String システム時刻　時分　HH':'mm形式
	 */
	public final String gethm3() {
		return DateUtility.getHHmmByColon(this.idSysDate);
	}

}