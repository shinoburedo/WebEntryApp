package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.CheckUtility;

/**
 * <p>タイトル: 名前チェック</p>
 * <p>説明: 名前項目の文字をチェックする</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class Name {

	/**
	 * 文字列が全て第二水準以内の漢字かどうかを調べる－[漢字氏名用]
	 * @param strInput String 調べる文字列。Shift-Jis → unicode変換前
	 * @param value String 調べる文字列。Shift-Jis → 8859-1変換後
	 * @return boolean 第二水準の文字以外がふくまれるときfalse
	 */
	public final boolean checkKjname(
		final String strInput,
		final String value) {

		int target1int;
		String target1hex = null;
		boolean state = true;
		final String HIRAGANA_START = "829F";//'ぁ'
		final String KATAKANA_END = "8396";//'ヶ'
		final String KANJI_START = "889F";//'亜'
		final String KANJI_END = "EAA4";//'熙'
		final String REPETITION_SIGN = "8158";//'々'
		final String LONG_SIGN = "815b";//'ー'

		CheckUtility checkUtility = new CheckUtility();

		boolean reZen = checkUtility.isZenkakuWindows_31J(strInput);

		try {

			if (reZen) {

				// 全角２バイト文字だったら、文字コードで範囲チェック
				// 全角ひらがな、全角カタカナ、第２水準までの漢字以外はエラー
				for (int i = 0; i < value.length(); i = i + 2) {

					// ２バイト文字１文字分の１６進コードを得る
					target1int = (int) value.charAt(i);
					target1hex = Integer.toHexString(target1int);
					target1int = (int) value.charAt(i + 1);
					target1hex += Integer.toHexString(target1int);

					//１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
					if ((target1hex.compareToIgnoreCase(HIRAGANA_START) < 0)
						|| ((target1hex.compareToIgnoreCase(KATAKANA_END) > 0)
							&& (target1hex.compareToIgnoreCase(KANJI_START) < 0))
						|| (target1hex.compareToIgnoreCase(KANJI_END) > 0)) {

						// '々','ー'はOK
						if ((target1hex.compareToIgnoreCase(REPETITION_SIGN) != 0)
							&& (target1hex.compareToIgnoreCase(LONG_SIGN) != 0)) {

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
	 * 文字列が全て全角ひらがなかどうかを調べる－[氏名ひらがな用]
	 * @param strInput String 調べる文字列。Shift-Jis → unicode変換前
	 * @param value String 調べる文字列。Shift-Jis → 8859-1変換後
	 * @return boolean 全角ひらがな以外がふくまれるときfalse
	 */
	public final boolean checkKnname(
		final String strInput,
		final String value) {

		boolean state = true;
		int target1int;
		String target1hex = null;
		final String HIRAGANA_START = "829F";//'ぁ'
		final String HIRAGANA_END = "82f1";//'ん'
		final String LONG_SIGN = "815b";//'ー'

		try {

			CheckUtility checkUtility = new CheckUtility();

			// 全角チェック
			if (checkUtility.isZenkakuWindows_31J(strInput)) {

				//全角２バイト文字だったら、文字コードで範囲チェック
				//全角ひらがな以外はエラー
				for (int i = 0; i < value.length(); i = i + 2) {

					//２バイト文字１文字分の１６進コードを得る
					target1int = (int) value.charAt(i);
					target1hex = Integer.toHexString(target1int);
					target1int = (int) value.charAt(i + 1);
					target1hex += Integer.toHexString(target1int);

					//１６進コード自体の文字列をunicode辞書式に大小比較して範囲内外を得る
					if ((target1hex.compareToIgnoreCase(HIRAGANA_START) < 0)
						|| (target1hex.compareToIgnoreCase(HIRAGANA_END) > 0)) {

						//'ー'はOK
						if (target1hex.compareToIgnoreCase(LONG_SIGN) != 0) {

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
}