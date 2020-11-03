package jp.co.nii.miw.business.service.payment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.sew.business.service.AbstractService;
import jp.co.nii.sew.utility.StringUtility;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.NameValuePair;

/**
 * <p>タイトル: 決済処理サービス</p>
 * <p>説明: 決済処理サービスの実装クラス</p>
 * <p>著作権: Copyright (c) 2011</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author K.Narita
 */
public class PayServ extends AbstractService {
    
    //決済テストモード（テスト）
    private static final String PAY_TEST_MODE_TEST = "1";
    //決済テストモード（本番）
    private static final String PAY_TEST_MODE_HONBAN = "0";
    
//    //決済方法（クレジットカード）
//    private static final String PAY_STORE_CARD = "51";
    
    //DB接続時のユーザーを決定
    public PayServ() {
        super();
    }

    //決済一括処理
    //IP,SIDまですべてセットされている状態で使用すること。
    public String[] executePayment(PayToroku payTorokuJoho, String payKbn, String payTest, String url_1, String url_2) {
        //返り値用
        String ret[] = new String[8];
        
        try {
            //送信値（PayToroku）をHashMapに変換
            HashMap mapPay = convertMap(payTorokuJoho);
            
//            //送信値をログ出力
//            outputSendData(mapPay, payKbn);
            
            //決済要求
            ret = postMethod(mapPay, payTest, url_1, url_2);
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
        return ret;
    }
    
    //SendDataを作成
    public HashMap convertMap(PayToroku payTorokuJoho) {
        
        HashMap ret = new HashMap();
        
        try {
            ret.put("IP", payTorokuJoho.getIp());
            ret.put("SID", payTorokuJoho.getSid());
            ret.put("NAME1", StringUtility.ksiString(payTorokuJoho.getName1()));
            ret.put("NAME2", StringUtility.ksiString(payTorokuJoho.getName2()));
            ret.put("KANA1", StringUtility.ksiString(payTorokuJoho.getKana1()));
            ret.put("KANA2", StringUtility.ksiString(payTorokuJoho.getKana2()));
            ret.put("YUBIN1", payTorokuJoho.getYubin1());
            ret.put("YUBIN2", payTorokuJoho.getYubin2());
            ret.put("TEL", payTorokuJoho.getTel());
            ret.put("ADR1", StringUtility.ksiString(payTorokuJoho.getAdr1()));
            ret.put("ADR2", StringUtility.ksiString(payTorokuJoho.getAdr2()));
            ret.put("MAIL", payTorokuJoho.getMail());
//            ret.put("FUKA", StringUtility.ksiString(payTorokuJoho.getFuka()));
            ret.put("FUKA", payTorokuJoho.getFuka());
            ret.put("N1", StringUtility.ksiString(payTorokuJoho.getN1()));
            ret.put("K1", payTorokuJoho.getK1());
            ret.put("N2", StringUtility.ksiString(payTorokuJoho.getN2()));
            ret.put("K2", payTorokuJoho.getK2());
            ret.put("N3", StringUtility.ksiString(payTorokuJoho.getN3()));
            ret.put("K3", payTorokuJoho.getK3());
            ret.put("N4", StringUtility.ksiString(payTorokuJoho.getN4()));
            ret.put("K4", payTorokuJoho.getK4());
            ret.put("N5", StringUtility.ksiString(payTorokuJoho.getN5()));
            ret.put("K5", payTorokuJoho.getK5());
            ret.put("N6", StringUtility.ksiString(payTorokuJoho.getN6()));
            ret.put("K6", payTorokuJoho.getK6());
            ret.put("STORE", payTorokuJoho.getStore());

            if (MiwConstants.KESSAI_SHUBETSU_CARD.equals(payTorokuJoho.getStore().toString())) {
                //クレジットカード決済
                ret.put("KAKUTEI", payTorokuJoho.getKakutei());
                ret.put("CARDNO", payTorokuJoho.getCardno());
                ret.put("EXP", payTorokuJoho.getExp());
            } else if (MiwConstants.KESSAI_SHUBETSU_PAYEASY.equals(payTorokuJoho.getStore().toString())) {
                //ペイジー決済
                ret.put("KIGEN", payTorokuJoho.getKigen());
            } else {
                //コンビニ決済
                ret.put("KIGEN", payTorokuJoho.getKigen());
                ret.put("TAX", payTorokuJoho.getTax());
            }
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        
        return ret;
    }
    
    //SendDataをログ出力
    public void outputSendData(HashMap sendData, String payKbn) {
        try {
            //共通項目ログ出力
            System.out.println("----- Output SendData Start -----");
            System.out.println("SendData.IP:" + sendData.get("IP"));
            System.out.println("SendData.SID:" + sendData.get("SID"));
            System.out.println("SendData.NAME1:" + sendData.get("NAME1"));
            System.out.println("SendData.NAME2:" + sendData.get("NAME2"));
            System.out.println("SendData.KANA1:" + sendData.get("KANA1"));
            System.out.println("SendData.KANA2:" + sendData.get("KANA2"));
            System.out.println("SendData.YUBIN1:" + sendData.get("YUBIN1"));
            System.out.println("SendData.YUBIN2:" + sendData.get("YUBIN2"));
            System.out.println("SendData.TEL:" + sendData.get("TEL"));
            System.out.println("SendData.ADR1:" + sendData.get("ADR1"));
            System.out.println("SendData.ADR2:" + sendData.get("ADR2"));
            System.out.println("SendData.MAIL:" + sendData.get("MAIL"));
            System.out.println("SendData.FUKA:" + sendData.get("FUKA"));
            System.out.println("SendData.N1:" + sendData.get("N1"));
            System.out.println("SendData.K1:" + sendData.get("K1"));
            System.out.println("SendData.N2:" + sendData.get("N2"));
            System.out.println("SendData.K2:" + sendData.get("K2"));
            System.out.println("SendData.N3:" + sendData.get("N3"));
            System.out.println("SendData.K3:" + sendData.get("K3"));
            System.out.println("SendData.N4:" + sendData.get("N4"));
            System.out.println("SendData.K4:" + sendData.get("K4"));
            System.out.println("SendData.N5:" + sendData.get("N5"));
            System.out.println("SendData.K5:" + sendData.get("K5"));
            System.out.println("SendData.N6:" + sendData.get("N6"));
            System.out.println("SendData.K6:" + sendData.get("K6"));
            System.out.println("SendData.STORE:" + sendData.get("STORE"));

            if (MiwConstants.KESSAI_SHUBETSU_CARD.equals(sendData.get("STORE").toString())) {
                //クレジットカード決済項目
                //カード表示用に編集
                String strCardNo = sendData.get("CARDNO").toString();
                int intCardNoLength = strCardNo.length();
                String strLookUpCardNo = strCardNo.substring(0, intCardNoLength - 3) + "****";

                System.out.println("SendData.KAKUTEI:" + sendData.get("KAKUTEI"));
                System.out.println("SendData.CARDNO:" + strLookUpCardNo);
                System.out.println("SendData.EXP:" + sendData.get("EXP"));
            } else if (MiwConstants.KESSAI_SHUBETSU_PAYEASY.equals(sendData.get("STORE").toString())) {
                //ペイジー決済項目
                System.out.println("SendData.KIGEN:" + sendData.get("KIGEN"));
            } else {
                //コンビニ決済項目
                System.out.println("SendData.KIGEN:" + sendData.get("KIGEN"));
                System.out.println("SendData.TAX:" + sendData.get("TAX"));
            }
            System.out.println("----- Output SendData End -----");
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    public String[] postMethod(HashMap sendData, String payTest, String url_1, String url_2) {
        String ret[] = new String[8];
        
        try {
            //モードを確認
            if (payTest.equals(PAY_TEST_MODE_TEST)) {
                //テストモード
                //決済方法を取得
                String strStore = sendData.get("STORE").toString();
                
                int intChk = 0;
                
                if (strStore.equals(MiwConstants.KESSAI_SHUBETSU_CARD)) {
                    //カード決済
                    //正常・異常の切り分けは以下の通りです。
                    intChk = Integer.parseInt(sendData.get("CARDNO").toString().substring(0, 1));
                    
                } else if (strStore.equals(MiwConstants.KESSAI_SHUBETSU_PAYEASY)) {
                    //ペイジー決済項目
                } else {
                    //コンビニ決済
                    //正常・異常の切り分けは以下の通りです。
                    intChk = Integer.parseInt(strStore);
                }
                
                //
                //コンビニ決済
                //支払コンビニが
                //[3]…ERROR
                //[73]…CVSERROR
                //以外…正常
                //
                //カード決済
                //カード番号の１桁目が
                //[3]…ERROR
                //[9]…PRERROR
                //以外…正常
                switch (intChk) {
                    case 3:
                        ret[1] = "ERROR";
                        ret[2] = sendData.get("SID").toString();
                        ret[3] = "ERROR:ERR001 このショッピングＩＤの決済は既に完了しています。";
                        ret[4] = "このショッピングＩＤの決済は既に完了しています。";
                        ret[5] = "";
                        ret[6] = "";
                        ret[7] = "";
                        break;

                    case 9:
                        ret[1] = "PRERROR";
                        ret[2] = sendData.get("SID").toString();
                        ret[3] = "ERROR:PRM002 決済種別が誤っているか指定されていません。";
                        ret[4] = "決済種別が誤っているか指定されていません。";
                        ret[5] = "";
                        ret[6] = "";
                        ret[7] = "";

                        break;

                    case 73:
                        ret[1] = "CVSERROR";
                        ret[2] = sendData.get("SID").toString();
                        ret[3] = "ERROR:WNT002 WELLNETサーバーへの接続ができませんでした。";
                        ret[4] = "WELLNETサーバーへの接続ができませんでした。";
                        ret[5] = "";
                        ret[6] = "";
                        ret[7] = "";

                        break;

                    default:
                        int intKingaku = 0;
                        intKingaku = 
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K1").toString())) +
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K2").toString())) +
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K3").toString())) +
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K4").toString())) +
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K5").toString())) +
                            Integer.parseInt(StringUtility.convertBlankToZero(sendData.get("K6").toString()));

                        ret[1] = "OK";
                        ret[2] = sendData.get("SID").toString();
                        ret[3] = Integer.toString(intKingaku);
                        if (strStore.equals(MiwConstants.KESSAI_SHUBETSU_CARD)) {
                            //カード決済
                            ret[4] = "";
                            ret[5] = "";
                        } else if (strStore.equals(MiwConstants.KESSAI_SHUBETSU_PAYEASY)) {
                            //ペイジー決済項目
                            ret[4] = "99999-99999999999999999999-999999";
                            ret[5] = sendData.get("KIGEN").toString();
                        } else {
                            //コンビニ決済
                            ret[4] = "99999-99999-99999-99999";
                            ret[5] = sendData.get("KIGEN").toString();
                        }
                        ret[6] = sendData.get("FUKA").toString();
                        ret[7] = "http://google.co.jp";

                        break;
                }
                //koko
                ret[0] = ret[1] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[2] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[3] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[4] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[5] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[6] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR
                        + ret[7];
                
            } else {
                //本番（外部への接続）
                // パラメータセット
                Set set = sendData.entrySet();
                Iterator iterator = set.iterator();
                
                //送信データ用にセット
                NameValuePair[] data = new NameValuePair[sendData.size()];

                int i = 0;
                while (iterator.hasNext()) {
                    Map.Entry mapentry = (Map.Entry) iterator.next();

                    data[i] = new NameValuePair((String) mapentry.getKey(),
                        (String) mapentry.getValue());
                    i++;
                }
                
                //デジタルチェック接続用
                HttpClient client = new HttpClient();
                PostMethod method = null;

                //接続用（1号機）
                method = new PostMethod(url_1);
                method.setRequestBody(data);
                
                System.out.println("Send digitalcheck ...");
                
                //Proxyサーバ1号機に接続
                System.out.println("Proxy Server_1(" + url_1 + ")Connectiong...");
                int result = client.executeMethod(method);

                // 受信
                InputStream is = method.getResponseBodyAsStream();

                //決済要求結果を取得
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "SJIS"));

                String s = "";
                int j = 1;
                while ((s = reader.readLine()) != null) {
                    if (j > 7) {
                        break;
                    }

                    if (j != 1) {
                        ret[0] = ret[0] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR + s;
                    } else {
                        ret[0] = s;
                    }
                    ret[j] = s;
                    System.out.println("line" + j + ":" + s);
                    j++;
                }
                
                if (ret[1].equals("OK") || ret[1].equals("ERROR") ||
                    ret[1].equals("PRERROR") || ret[1].equals("CVSERROR")) {
                    
                    //1号機での接続ＯＫ
                    System.out.println("Proxy Server_1(" + url_1 + ") Success");
                    
                } else {
                    //1号機での接続ＮＧ
                    System.out.println("Proxy Server_1(" + url_1 + ") Error");

                    //接続用（2号機）
                    method = new PostMethod(url_2);
                    method.setRequestBody(data);
                    //Proxyサーバ2号機に接続
                    System.out.println("Proxy Server_2(" + url_2 + ")Connectiong...");
                    result = client.executeMethod(method);

                    // 受信
                    is = method.getResponseBodyAsStream();

                    //決済要求結果を取得
                    reader = new BufferedReader(new InputStreamReader(is, "SJIS"));

                    j = 1;
                    while ((s = reader.readLine()) != null) {
                        if (j > 7) {
                            break;
                        }
                        
                        if (j != 1) {
                            ret[0] = ret[0] + MiwConstants.KESSAI_LOG_MESSAGE_HONTAI_SEPARATOR + s;
                        } else {
                            ret[0] = s;
                        }
                        
                        ret[j] = s;
                        System.out.println("line" + j + ":" + s);
                        j++;
                    }
                    
                    if (ret[1].equals("OK") || ret[1].equals("ERROR") ||
                        ret[1].equals("PRERROR") || ret[1].equals("CVSERROR")) {
                    
                        //2号機での接続ＯＫ
                        System.out.println("Proxy Server_2(" + url_2 + ") Success");
                        
                    } else {
                        //2号機での接続ＮＧ
                        System.out.println("Proxy Server_2(" + url_2 + ") Error");
                        
                        // ret[1]=OK 以外はエラーメッセージを表示するので
                        //エラーのメッセージをret中に挿入
                        ret[1] = "NG";
                        ret[3] = "プロキシサーバへの接続ができませんでした。";
                        
                        return ret;
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            
        }
        
        return ret;
    }
    
}
