package jp.co.nii.sew.business.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;





/**
 * タイトル:PayCvsAction
 * 説明:コンビニ決済を行う
 * 著作権:   Copyright (c) 2010
 * 会社名:   日本情報産業株式会社
 * @author --t.yamaguchi
 */
public class PayCvsAction  {

	/** ログに出力するクラス名 */
	private static String CLASS_NAME;

	private static  String SHOHIN_NAME ;
	private static  String IP ;
	private static  int  FEE;
	private static  int  COMMISSION;
	private static  int  TOTAL_FEE;

	// 業務コード
	public static final String BUSINESS_CODE =
		PropertyUtility.getProperty("business_code");



	//送信用データをプロパティから設定する
    public PayCvsAction(){
    	CLASS_NAME = this.getClass().getName();

    	SHOHIN_NAME  = PropertyUtility.getProperty(BUSINESS_CODE + "shohin_name");
    	IP = PropertyUtility.getProperty(BUSINESS_CODE + "ip_cvs");
    	FEE = Integer.parseInt(PropertyUtility.getProperty(BUSINESS_CODE + "fee"));
    	COMMISSION = Integer.parseInt(PropertyUtility.getProperty(BUSINESS_CODE + "commission_cvs"));
    	TOTAL_FEE =FEE + COMMISSION;
    }






    /**
	 * コンビニ決済処理を行う
	 * 正常の場合もエラーの場合も、
	 * デジタルチェックから返ってきた情報を
	 * 配列にして返す
	 *
	 * @param kessaiId
	 * @param shimeiSei
	 * @param shimeiMei
	 * @param shimeiSeiKana
	 * @param shimeiMeiKana
	 * @param yubin1
	 * @param yubin2
	 * @param telNo
	 * @param jusho1
	 * @param jusho2
	 * @param mail
	 * @param store
	 * @return strRtn
	 * @throws Exception
	 */
	public String[] pay(String kessaiId,
			String shimeiSei,
			String shimeiMei,
			String shimeiSeiKana,
			String shimeiMeiKana,
			String yubin1,
			String yubin2,
			String telNo,
			String jusho1,
			String jusho2,
			String mail,
			String store)throws Exception{

		// 2010/09/06 クレジット決済テスト用 Start
        HashMap sendData = new HashMap();
        PostMethod method = null;
        java.util.Date currentTime = new java.util.Date();

        //結果を格納するための配列
        String strRtn[];


        final String CONTENT_TYPE = "text/plain; charset=Shift_JIS";
		// 2010/09/06 クレジット決済テスト用 End

		try {

//			// ★負荷テスト用
//			String stressMode = PropertyUtility.getProperty("jfk.stress_mode");


			// 2010/09/06 クレジット決済テスト用 Start
            System.out.println("---- SettleRequest start ----"
                    + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(currentTime) + " ----");


            //商品名称をURLエンコードする
            String strShohinName= URLEncoder.encode(SHOHIN_NAME , "UTF-8");

            //送信用データをセット
            sendData.put("IP", IP);//決まっていません
            sendData.put("SID", kessaiId);
            sendData.put("NAME1", shimeiSei);
            sendData.put("NAME2", shimeiMei);
            sendData.put("KANA1", shimeiSeiKana);
            sendData.put("KANA2", shimeiMeiKana);
            sendData.put("YUBIN1", yubin1);
            sendData.put("YUBIN2", yubin2);
            sendData.put("TEL", telNo);
            sendData.put("ADR1", jusho1);
            sendData.put("ADR2", jusho2);
            sendData.put("MAIL", mail);
            sendData.put("FUKA", "");
            // ２Ｂ文字(URLエンコードした状態で送信)
            sendData.put("N1", strShohinName);
            sendData.put("K1", TOTAL_FEE);
            sendData.put("N2", "");
            sendData.put("K2", "");
            sendData.put("N3", "");
            sendData.put("K3", "");
            sendData.put("N4", "");
            sendData.put("K4", "");
            sendData.put("N5", "");
            sendData.put("K5", "");
            sendData.put("N6", "");
            sendData.put("K6", "");
            sendData.put("STORE",store);
            sendData.put("KIGEN", "");
            sendData.put("TAX", "");
            sendData.put("OKURL", "");
            sendData.put("RT", "");

            // デジタルチェック接続
            HttpClient client = new HttpClient();
//            method = new PostMethod( "https://www.digitalcheck.jp/settle/settle2/ubp3.dll");//本番？
//            method = new PostMethod( "http://www.digitalcheck.jp/settle/settle2/ubp3.dll");

            // パラメータセット
            Set set = sendData.entrySet();
            Iterator iterator = set.iterator();

            NameValuePair[] data = new NameValuePair[sendData.size()];

            int i = 0;
            while (iterator.hasNext()) {
                Map.Entry mapentry = (Map.Entry) iterator.next();

                data[i] = new NameValuePair((String) mapentry.getKey(),
                        (String) mapentry.getValue());
                i++;
            }

            method.setRequestBody(data);

            // 送信
            System.out.println("Send to digitalcheck ...");
            int statusCode = client.executeMethod(method);

            // 受信
            System.out.println("Get from digitalcheck");

            InputStream is = method.getResponseBodyAsStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "SJIS"));

//            response.setContentType(CONTENT_TYPE);
//
//            PrintWriter pw = response.getWriter();


            String s = "";
            int j = 0;
            strRtn = new String[7];

            while ((s = reader.readLine()) != null) {
            	strRtn[j] = s;

            		System.out.println(s);

            	j++;
            }

            System.out.println("---- SettleRequestS End "
                    + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                            .format(new java.util.Date()) + " ----");

//            pw.flush();
//            pw.close();


		} catch (Exception ex) {

			//ログ出力
			LogGenerate.errWrite (CLASS_NAME,"payCrd()  決済処理でエラーが発生しました。",ex);

			throw ex;
		}
			return strRtn;

	}
}
