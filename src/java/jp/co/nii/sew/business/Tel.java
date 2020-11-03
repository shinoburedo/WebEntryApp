package jp.co.nii.sew.business;

import jp.co.nii.sew.utility.CheckUtility;

/**
 * <p>タイトル: 電話番号チェック</p>
 * <p>説明: 電話番号の形式をチェックする</p>
 * <p>著作権: Copyright (c) 2005</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author ＷＥＢ開発チーム
 */
public class Tel {

    /**
     * 電話番号の形式か否かをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、電話番号として適切かチェックする。
     * 適切ならばtrueが返る。</b>
     * @param str String チェックする文字列(電話番号)
     * @return boolean   適切ならばtrue
     *                    適切でなければfalse
     */
    public final boolean checkTelno(final String str) {

        String tel1 = "";
        String tel2 = "";
        String tel3 = "";
        String tel4 = "";
        int delimitCnt = 0; //区切りカウント
        boolean ret = false; //戻り値
        final char NEGATIVE_SIGN = '-';
        final String ZERO = "0";

        for (int i = 0; i < str.length(); i++) {

            if (i == 0) {
                tel1 = str.substring(0, 1);

            } else if ((str.charAt(i) != NEGATIVE_SIGN) && (delimitCnt == 0)) {
                tel2 = tel2 + str.charAt(i);

            } else if ((str.charAt(i) != NEGATIVE_SIGN) && (delimitCnt == 1)) {
                tel3 = tel3 + str.charAt(i);

            } else if ((str.charAt(i) != NEGATIVE_SIGN) && (delimitCnt == 2)) {
                tel4 = tel4 + str.charAt(i);

            } else if (str.charAt(i) == NEGATIVE_SIGN) {
                delimitCnt = delimitCnt + 1;
            }

        }

        /*
        電話番号の構成
        国内プレフィックス+市外局番+市内局番+加入者番号
        （初めの「0」は、国内プレフィックスという。）
        例：0x-yyyy-zzzzでは、市外局番--x,市内局番--yyyy,加入者番号--zzzz部分となる。
        
        1桁目（国内プレフィックス）は必ず「0」(tel1)
        市外局番は「1～5」桁(tel2)
        市内局番は「0～4」桁(ハイフンが１つのときは、市内局番0桁と判断。
        　ハイフンが２つのときは、市内局番1～4桁になっているかをチェック。
        　ハイフンの数が１、２以外は、エラー）
        　ハイフン２つのとき、市内局番は、tel3
        市外局番と市内局番の合計は、固定電話であれば「4～5」桁。
        　ハイフン２つのときのチェックは、携帯電話（6桁）もありえるため、4～6桁で行う。
        　(ハイフンが１つのとき（必ず固定電話）は、市内局番0桁のため、市外局番（tel2部分）をチェック)
        加入者番号は「4」桁(ハイフンが１つのときは、tel3、ハイフンが２つのときは、tel4)
         */

        //1桁目は必ず「0」(tel1)
        if (tel1.equals(ZERO)) {

            final int TEL2_LEN = tel2.length();
            final int TEL3_LEN = tel3.length();

            //市外局番は「1～5」桁(tel2)
            if ((TEL2_LEN >= 1) && (TEL2_LEN <= 5)) {

                /*市内局番が0桁のときは、ハイフン１つ→ハイフン２つのときは、市内局番が「1～4」桁となればよい
                ハイフンの数により、市内局番の有無を判断。*/

                //ハイフン２つ
                if (delimitCnt == 2) {

                    //市内局番は「1～4」桁(tel3)
                    if ((TEL3_LEN >= 1) && (TEL3_LEN <= 4)) {

                        //市外＋市内局番(tel2+tel3)
                        final int AREACITY_CODE = TEL2_LEN + TEL3_LEN;

                        //市外局番と市内局番の合計は「4～6」桁(携帯電話の可能性があるため6桁まで)
                        if ((AREACITY_CODE >= 4) && (AREACITY_CODE <= 6)) {

                            //加入者番号は「4」桁
                            if (tel4.length() == 4) {
                                ret = true;
                            }
                        }
                    }

                    //ハイフン１つ
                } else if (delimitCnt == 1) {

                    /*市外局番と市内局番の合計は「4～5」桁→市外局番が4か5桁になっている
                    　（ハイフン１つのときは、市内局番0桁だから）*/
                    if ((TEL2_LEN == 4) || (TEL2_LEN == 5)) {

                        //加入者番号は「4」桁
                        if (TEL3_LEN == 4) {
                            ret = true;
                        }
                    }

                    //ハイフンの数が１でも２でもない
                } else {
                    ret = false;
                }
            }
        }

        return ret;
    }

    /**
     * 電話番号の形式か否かをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、電話番号として
     * 適切ならばtrueが返る。</b>
     * @param str String チェックする文字列(電話番号)
     * @return boolean  適切ならばtrue
     * 　　　　　　　　　適切でなければfalse
     */
    public final boolean isTelno1(final String str) {

        boolean ret = true;
        final char ZERO = '0';
        final char NINE = '9';
        final char NEGATIVE_SIGN = '-';
        final char START_PARENTHESIS = '(';
        final char END_PARENTHESIS = ')';

        for (int i = 0; i < str.length(); i++) {

            if (!(((str.charAt(i) >= ZERO) && (str.charAt(i) <= NINE))
                    || (str.charAt(i) == NEGATIVE_SIGN)
                    || (str.charAt(i) == START_PARENTHESIS)
                    || (str.charAt(i) == END_PARENTHESIS))) {

                ret = false;
                break;
            }
        }

        return ret;
    }

    /**
     * 電話番号の形式か否かをチェック<BR>
     * <b>解説：引数1にチェックする文字列を指定し、電話番号として適切かチェックする。
     * 適切ならばtrueが返る。</b>
     * @param str String チェックする文字列(電話番号)
     * @return boolean  適切ならばtrue
     * 　　　　　　　　　適切でなければfalse
     */
    public final boolean isTelno2(final String str) {

        boolean ret = true;

        ret = CheckUtility.isNumberOrHyphen(str);

        return ret;
    }
}
