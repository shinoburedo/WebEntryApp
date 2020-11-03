package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.CheckUtility;

/**
 * <p>タイトル: パスワードチェック</p>
 * <p>説明: パスワード形式をチェックする</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class Password {

	/**
	 * 引数pswの文字数が引数minlengthからmaxlengthの範囲内かチェックする。<BR>
	 * 英数字が混在しているかチェックする。<BR>
	 * 条件を満たす場合trueが返る。
	 * @param psw String 検査対象の文字列
	 * @param minlength int 最小文字数
	 * @param maxlength int 最大文字数
	 * @return boolean 検査結果
	 */
	public final boolean isAcceptablePassword(
		final String psw,
		final int minlength,
		final int maxlength) {

		boolean state = false;
		CheckUtility checkUtility = new CheckUtility();

		if (checkUtility.withinNumberOfCharacters(psw, maxlength)
			&& !checkUtility.withinNumberOfCharacters(psw, minlength - 1)
			&& !checkUtility.isWrongChar(psw)
			&& checkUtility.isAlphabetOrNumber(psw)
			&& !checkUtility.isNumber(psw)
			&& !checkUtility.isChar(psw)) {

			state = true;
		}

		return state;
	}

	/**
	 * パスワード確認入力のあるときのパスワード条件チェック<BR>
	 * 引数psw1,psw2の文字数が引数minlengthからmaxlengthの範囲内かチェックする。<BR>
	 * 英数字が混在しているかチェックする。<BR>
	 * パスワードのペアが同じかチェックする。<BR>
	 * 条件を満たす場合trueが返る。
	 * @param psw1 java.lang.String 検査対象の文字列1
	 * @param psw2 java.lang.String 検査対象の文字列2
	 * @param minlength int 最小文字数
	 * @param maxlength int 最大文字数
	 * @return boolean 検査結果
	 */
	public final boolean isAcceptablePasswordPair(
		final String psw1,
		final String psw2,
		final int minlength,
		final int maxlength) {

		boolean state = false;

		if (this.isAcceptablePassword(psw1, minlength, maxlength)
			&& psw1.equals(psw2)) {

			state = true;
		}

		return state;
	}
}