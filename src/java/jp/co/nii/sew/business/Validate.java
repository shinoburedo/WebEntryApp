package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.DateUtility;
import jp.co.nii.sew.utility.StringUtility;
import jp.co.nii.sew.utility.CheckUtility;
import java.util.Calendar;

import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


/**
 * <p>タイトル: Validateクラス</p>
 * <p>説明: 入力値のチェックを行い、エラーだった場合、
 * ActionMessagesオブジェクトへエラーメッセージを足していく。
 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
 * 戻り値はエラーだった場合false、正常の場合true</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム

 */
public class Validate {


	/**
	 * 未入力チェックを行う
	 * 入力値がnull,ブランク,半角スペースのみの場合false
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.required"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateRequired(String value, ActionMessages errors,String errGroupName,String arg0){

		if(GenericValidator.isBlankOrNull(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.required",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列の長さが、一定の長さ以下かチェック
	 * 指定した文字数よりも長ければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.maxlength"
	 * @param value 入力値
	 * @param max 文字数
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMaxLength(String value,int max, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !GenericValidator.maxLength(value, max)){
			if(errors != null){
				errors.add(errGroupName,
						new ActionMessage("errors.maxlength",arg0,StringUtility.convertNumberToZenkaku(Integer.toString(max))));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列の長さが、一定の長さ以下かチェック
	 * 指定した文字数よりも長ければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.summaxlength"
	 * @param value 入力値
	 * @param max 文字数
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateSumMaxLength(String value,int max, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !GenericValidator.maxLength(value, max)){
			if(errors != null){
				errors.add(errGroupName,
						new ActionMessage("errors.summaxlength",arg0,StringUtility.convertNumberToZenkaku(Integer.toString(max))));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列の長さが、指定の長さかチェック
	 * 指定した文字数よりも長ければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.equallength"
	 * @param value 入力値
	 * @param max 文字数
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateEqualLength(String value,int equ, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && value.length() != equ){
			if(errors != null){
				errors.add(errGroupName,
						new ActionMessage("errors.equallength",arg0,StringUtility.convertNumberToZenkaku(Integer.toString(equ))));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列の長さが、一定の長さ以上かチェック
	 * 指定した文字数よりも短ければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.minlength"
	 * @param value 入力値
	 * @param min 文字数
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMinLength(String value,int min, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !GenericValidator.minLength(value, min)){
			if(errors != null){
				errors.add(errGroupName,
						new ActionMessage("errors.minlength",arg0,StringUtility.convertNumberToZenkaku(Integer.toString(min))));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列の長さが、一定の長さ以上、一定の長さ以下かチェック
	 * 指定した範囲内でなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.rangelength"
	 * @param value 入力値
	 * @param min 最低文字数
	 * @param max 最高文字数
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateRangeLength(String value,int min,int max, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isRangeLength(value, min, max)){
			if(errors != null){
				errors.add(errGroupName,new ActionMessage("errors.rangelength",arg0,
								StringUtility.convertNumberToZenkaku(Integer.toString(min)),
								StringUtility.convertNumberToZenkaku(Integer.toString(max))));
			}
			return false;
		}else{
			return true;
		}
	}



	/**
	 * 値が指定した正規表現にマッチするかどうかチェック
	 * マッチしなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.invalid"
	 * @param value 入力値
	 * @param mask 正規表現文字列
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMask(String value,String mask, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !GenericValidator.matchRegexp(value, mask)){
			if(errors != null){
				errors.add(errGroupName,new ActionMessage("errors.invalid",arg0));
			}
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 値が日付を表す文字列かどうかチェック(yyyyMMdd形式)
	 * 日付を表す形でなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.date"
	 * @param value 入力値(yyyyMMdd)
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateDate(String value,ActionMessages errors,String errGroupName,String arg0){
		int iYear;
		int iMonth;
		int iDay;

		if (GenericValidator.isBlankOrNull(value)) {
			return true;
		}
		try {
			iYear = Integer.parseInt(value.substring(0, 4));
			iMonth = Integer.parseInt(value.substring(4, 6)) - 1;
			iDay = Integer.parseInt(value.substring(6));

			Calendar instCalendar = Calendar.getInstance();

			instCalendar.set(iYear, iMonth, iDay);
			instCalendar.setLenient(false);

			instCalendar.getTime();
			return true;
		}catch (Exception e) {
			if(errors != null){
				errors.add(errGroupName,new ActionMessage("errors.date",arg0));
			}
			return false;
		}
	}

	/**
	 * 値が日付を表す文字列かどうかチェック(和暦)
	 * 日付を表す形でなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.date"
	 * @param era 元号e
	 * @param year 和暦年yy
	 * @param month 月MM
	 * @param day 日dd
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateDateWareki(String era,
			String year,
			String month,
			String day, ActionMessages errors,
			String errGroupName,
			String arg0){
		DateUtility dtUtlty = new DateUtility();

		if(GenericValidator.isBlankOrNull(era) ||
				GenericValidator.isBlankOrNull(year) ||
				GenericValidator.isBlankOrNull(month) ||
				GenericValidator.isBlankOrNull(day)){
			return true;
		}

		// 西暦変換
		int cera = DateUtility.calculateAddedYears(era);
		int iADyear = Integer.parseInt(year) + cera;
		String cyear = Integer.toString(iADyear);

		if(dtUtlty.isFuture(cyear, month, day) || !dtUtlty.proprietyDate(era,year,month,day)){
			if(errors != null){
				errors.add(errGroupName,new ActionMessage("errors.date",arg0));
			}
			return false;
		}else{
			return true;
		}

	}

	/**
	 * 文字列がメールアドレスかどうかチェック
	 * メールアドレスでなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.email"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateEmail(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !GenericValidator.isEmail(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.email",arg0));
			}
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 文字列が半角数字かどうかチェック
	 * 半角数字でなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.number"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateNumber(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isNumber(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.number",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が半角英数字かどうかチェック
	 * 半角英数字でなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.alphabetornumber"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateAlphabetOrNumber(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isAlphabetOrNumber(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.alphabetornumber",arg0));
			}
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 文字列が全角カタカナかどうかチェック
	 * 全角カタカナでなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.katakana"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateKatakana(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isKatakana(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.katakana",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が全角ひらがなかどうかチェック
	 * 全角ひらがなでなければfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.hiragana"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateHiragana(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isHiragana(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.hiragana",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が全て第二水準以内の文字かどうかチェック
	 * 文字列が全て全角ひらがな、全角カタカナ、第２水準までの漢字以外だったらfalse
	 * それ以外の場合trueを返す
	 * 氏名のチェック等で使用する
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.mojicode1"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMojiCode1(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isMojicode1(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.mojicode1",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が全て第二水準以内の文字かどうかチェック()
	 * 文字列が全て記号（一部）、全角数字、全角ひらがな、全角カタカナ、第２水準までの漢字以外だったらfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * 番地、町村のチェック等で使用する
	 * メッセージ・リソースキー:"errors.mojicode2"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMojiCode2(String value, ActionMessages errors,String errGroupName,String arg0){
		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isMojicode2(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.mojicode2",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が全て第二水準以内の文字かどうかチェック
	 * 文字列が全て記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外だったらfalse
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * 住所のチェック等で使用する
	 * メッセージ・リソースキー:"errors.mojicode3"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMojiCode3(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isMojicode3(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.mojicode3",arg0));
			}
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 文字列が全て第二水準以内の文字かどうかチェック
	 * 文字列が全て記号（一部）、全角数字、全角アルファベット、全角ひらがな、全角カタカナ、第２水準までの漢字以外だったらfalse
	 *  文章に対応（「。」「、」も可になります。
	 * それ以外の場合trueを返す
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.mojicode3"
	 * @param value 入力値
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validateMojiCode4(String value, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isMojicode4(value)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.mojicode4",arg0));
			}
			return false;
		}else{
			return true;
		}
	}


	/**
	 * 生年月日に対して年齢が正しく入力されているかのチェック
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.nenrei"
	 * @param value 入力値(生年月日:yyyyMMdd形式)
	 * @param nanrei 年齢
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @param arg1 メッセージ・リソースファイルの{1}部分
	 * @return
	 */
	public static boolean validateNenrei(String birth,String nenrei, ActionMessages errors,String errGroupName,String arg0,String arg1){

		if(!GenericValidator.isBlankOrNull(birth) && !GenericValidator.isBlankOrNull(nenrei)){
			int intBirth = Integer.parseInt(birth);
			//現在の日付をyyyymmddに変換
			SystemTime sysTim = new SystemTime();
			int sysYmd = Integer.parseInt(sysTim.getymd1());

			//年齢をint型に
			int iNenrei = Integer.parseInt(nenrei);
			if((sysYmd-intBirth)/10000!=iNenrei){
				if(errors != null){
					errors.add(errGroupName, new ActionMessage("errors.nenrei",arg0,arg1));
				}
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}

	}

	/**
	 * 入力値が、入力許可な値かどうかをチェックする（セレクトボック等で使用）
	 * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
	 * メッセージ・リソースキー:"errors.permission"
	 * @param value 入力値
	 * @param select 入力許可する文字の配列
	 * @param errors ActionMessagesオブジェクト
	 * @param errGroupName ActionMessagesのグループ名
	 * @param arg0 メッセージ・リソースファイルの{0}部分
	 * @return
	 */
	public static boolean validatePermissionSelect(String value,String[] select, ActionMessages errors,String errGroupName,String arg0){

		if(!GenericValidator.isBlankOrNull(value) && !CheckUtility.isPermissionSelect(value, select)){
			if(errors != null){
				errors.add(errGroupName, new ActionMessage("errors.permission",arg0));
			}
			return false;
		}else{
			return true;
		}

	}
}