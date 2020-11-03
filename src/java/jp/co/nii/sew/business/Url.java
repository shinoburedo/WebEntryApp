package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.LogGenerate;

/**
 * <p>タイトル: URL</p>
 * <p>説明: URLにまつわる処理を提供する。</p>
 * <p>著作権: Copyright (c) 2006</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author S.Miyazaki
 */
public class Url {

	/**
	 * URLの形式か否かをチェック<BR>
	 * 引数1にチェックする文字列を指定し、URLとして適切かチェックする。
	 *  適切ならばtrueが返る。
	 *    rfc2396 によるURIで使用可能な文字
	 *    A-Z a-z 0-9 - _ . / ~ , $ ! * ' ( ) ; : @  = & +
	 * 作成日 :
	 * @return boolean
	 * @param str String
	 */
	public boolean isUrl(String str) {
		int iPos = 0;
		boolean ret = true;

		for (int i = 0; i < str.length(); i++) {
			if (!((str.charAt(i) >= '0' && str.charAt(i) <= '9')
				|| (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
				|| (str.charAt(i) >= 'a' && str.charAt(i) <= 'z')
				|| (str.charAt(i) == '-')
				|| (str.charAt(i) == '_')
				|| (str.charAt(i) == '.')
				|| (str.charAt(i) == '/')
				|| (str.charAt(i) == '~')
				|| (str.charAt(i) == ',')
				|| (str.charAt(i) == '$')
				|| (str.charAt(i) == '!')
				|| (str.charAt(i) == '*')
				|| (str.charAt(i) == '\'')
				|| (str.charAt(i) == '(')
				|| (str.charAt(i) == ')')
				|| (str.charAt(i) == ';')
				|| (str.charAt(i) == ':')
				|| (str.charAt(i) == '@')
				|| (str.charAt(i) == '=')
				|| (str.charAt(i) == '&')
				|| (str.charAt(i) == '+'))) {
				LogGenerate.errorOutput("URLが不適切です " + str);
				ret = false;
				break;
			}
		}
		return ret;
	}
}
