package jp.co.nii.miw.business;

import java.util.Calendar;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.DateUtility;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * <p>タイトル: JznValidateクラス</p>
 * <p>説明: 入力値のチェックを行い、エラーだった場合ActionMessagesオブジェクトへ
 * エラーメッセージを足していく。
 * 戻り値はエラーだった場合false、正常の場合true</p>
 * Validateクラスにないこの機能独自の入力チェックを行う
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 */
public class MiwValidate {

    /**
     * ローマ字氏名のチェックを行う
     * 入力値がnull,ブランク,半角スペースのみの場合false
     * それ以外の場合trueを返す
     * メッセージ・リソースキー:"errors.mojicodeRomaji"
     * @param value 入力値
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateMojiCodeRomaji(String value, ActionMessages errors, String errGroupName, String arg0) {

        if (!GenericValidator.isBlankOrNull(value) && !MiwCheckUtility.isMojicodeRomaji(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.mojicodeRomaji", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 確認用入力値の一致チェックを行う
     * ２つの入力値が一致しているとtrue、していないとfalseを返す
     * メッセージ・リソースキー:"errors.required"
     * @param value 入力値
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @param arg1 メッセージ・リソースファイルの{1}部分
     * @return
     */
    public static boolean validateKakuninInp(String value1, String value2, ActionMessages errors, String errGroupName, String arg0) {

        if (!value1.equals(value2)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.kakuninInp", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 入力値のどれか１つでも入力されていたら、
     * ２つ全て入力されていないとエラーとする
     * メッセージ・リソースキー:"errors.requiredInp"
     * @param value1 入力値1
     * @param value2 入力値2
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @param arg1 メッセージ・リソースファイルの{1}部分
     * @return
     */
    public static boolean validateRequiredInp2(String value1, String value2, ActionMessages errors, String errGroupName, String arg0) {
        int cnt = 0;
        if (!GenericValidator.isBlankOrNull(value1)) {
            cnt++;
        }
        if (!GenericValidator.isBlankOrNull(value2)) {
            cnt++;
        }
        if (cnt == 1) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.requiredInp", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 入力値のどれか１つでも入力されていたら、
     * ３つ全て入力されていないとエラーとする
     * メッセージ・リソースキー:"errors.requiredInp"
     * @param value1 入力値1
     * @param value2 入力値2
     * @param value3 入力値3
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @param arg1 メッセージ・リソースファイルの{1}部分
     * @return
     */
    public static boolean validateRequiredInp3(String value1, String value2, String value3, ActionMessages errors, String errGroupName, String arg0) {
        int cnt = 0;
        if (!GenericValidator.isBlankOrNull(value1)) {
            cnt++;
        }
        if (!GenericValidator.isBlankOrNull(value2)) {
            cnt++;
        }
        if (!GenericValidator.isBlankOrNull(value3)) {
            cnt++;
        }
        if (cnt != 0 && cnt != 3) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.requiredInp", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 値が日付を表す文字列かどうかチェック(yyyyMMdd形式)
     * 日付を表す形でなければfalse
     * また。未来の日付の場合もfalse
     * それ以外の場合trueを返す
     * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
     * メッセージ・リソースキー:"errors.date"
     * @param value 入力値(yyyyMMdd)
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateDate(String value, ActionMessages errors, String errGroupName, String arg0) {
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

            if (!DateUtility.isFuture(value.substring(0, 4), value.substring(4, 6), value.substring(6))) {
                return true;
            } else {
                if (errors != null) {
                    errors.add(errGroupName, new ActionMessage("errors.date", arg0));
                }
                return false;
            }

        } catch (Exception e) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.date", arg0));
            }
            return false;
        }
    }

    /**
     * 値が日付を表す文字列かどうかチェック(EyyMMdd形式・和暦)
     * 日付を表す形でなければfalse
     * また。未来の日付の場合もfalse
     * それ以外の場合trueを返す
     * 結果だけを取得したい場合、ActionMessagesオブジェクトにnullをセットする
     * メッセージ・リソースキー:"errors.date"
     * @param value 入力値(EyyMMdd)
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateWarekiDate(String value, ActionMessages errors, String errGroupName, String arg0) {

        if (GenericValidator.isBlankOrNull(value)) {
            return true;
        }
        try {
            String era = value.substring(0, 1);
            String year = value.substring(1, 3);
            String month = value.substring(3, 5);
            String day = value.substring(5);
            
            if (!DateUtility.proprietyDate(era, year, month, day)) {
                if (errors != null) {
                    errors.add(errGroupName, new ActionMessage("errors.date", arg0));
                }
                return false;
            } else {
		// 西暦変換
		int cera = DateUtility.calculateAddedYears(era);
		int iADyear = Integer.parseInt(year) + cera;
		String strADyear = Integer.toString(iADyear);
                if (!DateUtility.isFuture(strADyear, month, day)) {
                    return true;
                } else {
                    if (errors != null) {
                        errors.add(errGroupName, new ActionMessage("errors.date", arg0));
                    }
                    return false;
                }
            }

        } catch (Exception e) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.date", arg0));
            }
            return false;
        }
    }
    

    /**
     * 日付のFromToの値がFromがToより大きかったらエラー
     * メッセージ・リソースキー:"errors.dateFromTo"
     * @param from 日付From（8桁）
     * @param to 日付To（8桁）
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateDateFromTo(String from, String to, ActionMessages errors, String errGroupName, String arg0) {
        if (to.compareTo(from) < 0) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.dateFromTo", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 電話番号の欄がすべて未入力の場合エラー
     * メッセージ・リソースキー:"errors.required2"
     * @param value 
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @param arg1 メッセージ・リソースファイルの{1}部分
     * @return
     */
    public static boolean validateRequiredTel(String value, ActionMessages errors, String errGroupName, String arg0) {
        if (GenericValidator.isBlankOrNull(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.required2", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * ２バイト文字が含まれている場合エラー
     * メッセージ・リソースキー:"errors.byte"
     * @param value 
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateByte(String value, ActionMessages errors, String errGroupName, String arg0) {
        if (!GenericValidator.isBlankOrNull(value) && !MiwCheckUtility.is1Byte(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.hankaku", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 海外住所チェック用
     * メッセージ・リソースキー:"errors.hankaku"
     * @param value 
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateHankaku(String value, ActionMessages errors, String errGroupName, String arg0) {
        if (!GenericValidator.isBlankOrNull(value) && !MiwCheckUtility.isUpperAlphabetOrKigo(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.hankaku", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 小文字英字が含まれている場合エラー
     * メッセージ・リソースキー:"errors.upper"
     * @param value 
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateLower(String value, ActionMessages errors, String errGroupName, String arg0) {
        if (!GenericValidator.isBlankOrNull(value) && !MiwCheckUtility.isUpper(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.lower", arg0));
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 電話番号（数字、半角スペース、[+]、[-]）以外の場合エラー
     * メッセージ・リソースキー:"errors.telephone
     * @param value 
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateTelephone(String value, ActionMessages errors, String errGroupName, String arg0) {

        for (int i = 0; i < value.length(); i++) {

            if (!((value.charAt(i) >= '0' && value.charAt(i) <= '9')
                    || (value.charAt(i) == '-')
                    || (value.charAt(i) == '+')
                    || (value.charAt(i) == ' '))) {

                errors.add(errGroupName, new ActionMessage("errors.telephone", arg0));
                return false;
            }
        }

        return true;
    }

    /**
     * 半角英数字・第二水準のチェックを行う
     * 入力値がnull,ブランク,半角文字,全角文字(第二水準漢字)の場合true
     * それ以外の場合falseを返す
     * メッセージ・リソースキー:"errors.hankakuAndZenkaku"
     * @param value 入力値
     * @param errors ActionMessagesオブジェクト
     * @param errGroupName ActionMessagesのグループ名
     * @param arg0 メッセージ・リソースファイルの{0}部分
     * @return
     */
    public static boolean validateHankakuAndZenkaku(String value, ActionMessages errors, String errGroupName, String arg0) {

        if (!GenericValidator.isBlankOrNull(value) && !MiwCheckUtility.isHankakuAndZenkaku(value)) {
            if (errors != null) {
                errors.add(errGroupName, new ActionMessage("errors.hankakuAndZenkaku", arg0));
            }
            return false;
        } else {
            return true;
        }
    }
}