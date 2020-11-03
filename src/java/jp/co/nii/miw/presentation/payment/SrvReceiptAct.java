package jp.co.nii.miw.presentation.payment;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import jp.co.nii.miw.business.domain.Moshikomi;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * コンビニ・ペイジー決済データ受信（入金）<BR>
 * サーバから送信されるRequestパラメータを受け取り パラメータをログ表示する<BR>
 * @author K.Narita
 */
public class SrvReceiptAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //返却するフォワード名
        String ret = "";
        
        java.util.Date currentTime = new java.util.Date();
        System.out.println("Receipt Date=" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(currentTime));
        String message = "";

        try {
            // パラメータチェック
            String errMsg = checkParameter(request);

            // パラメータエラー
            if (!errMsg.equals("")) {
                System.out.println("** パラメータエラー：" + errMsg + "**");
                ret = FWD_NM_EXCEPTION;
                return ret;
            }

             // ログ表示
            outputLog(request);
            //決済ログ本文を編集
            message = getMessage(request);
            
            //サービスクラスを作成
            MskTorokuServ mskTorokuServ = new MskTorokuServ();
            MiaMailSendServ dafMailSendServ = new MiaMailSendServ();
            //決済ログに登録
            Moshikomi moshikomi = mskTorokuServ.updateKessaiData(request.getParameter("SID"), message, request.getParameter("KINGAKU"), MiwConstants.MESSAGE_SHUBETSU_RECEIPT);
            
            if (moshikomi != null) {
                //処理成功
                Moshikomisha moshikomisha = mskTorokuServ.selectMoshikomisha(moshikomi);
                
                //メール送信
                if(dafMailSendServ.sendShutsuganResuMailKessai(moshikomi, moshikomisha)){
                    //メール送信成功
                    ret = FWD_NM_SUCCESS;
                }else{
                    //メール送信失敗
                    LogGenerate.errorOutput("取引コード：" + request.getParameter("SID") + "入金確認メール送信が失敗しました。");
                    ret = FWD_NM_EXCEPTION;
                }
                
            } else {
                ret = FWD_NM_EXCEPTION;
            }
            
        } catch (Exception e) {
            LogGenerate.errWrite(CLASS_NAME, "doProcess", e, "!Attention!リクエスト値:" + message);
            ret = FWD_NM_EXCEPTION;
        }

        return ret;
    }
   
    // パラメータチェック
    protected String checkParameter(HttpServletRequest request) {

        String ret = "";

        //必須チェック
        CheckUtility checkUrility = new CheckUtility();
        if (checkUrility.isBlank(request.getParameter("SEQ"))) {
            ret = "SEQ";
        } else if (checkUrility.isBlank(request.getParameter("DATE"))) {
            ret = "DATE";
        } else if (checkUrility.isBlank(request.getParameter("TIME"))) {
            ret = "TIME";
        } else if (checkUrility.isBlank(request.getParameter("SID"))) {
            ret = "SID";
        } else if (checkUrility.isBlank(request.getParameter("KINGAKU"))) {
            ret = "KINGAKU";
        } else if (checkUrility.isBlank(request.getParameter("CVS"))) {
            ret = "CVS";
        }
        
        if (ret.equals("")) {
            //SIDの桁数チェック（10桁以内）
            if (request.getParameter("SID").length() > 16) {
                ret = "SID";
            }
        }

        return ret;
    }

    // ログ出力
    protected void outputLog(HttpServletRequest request) {
        // 入金用の編集
        System.out.println("----- 入金データ受信 -----");
        System.out.println("取引コード:" + request.getParameter("SID"));
    }
    
    // メッセージ本文を取得
    protected String getMessage(HttpServletRequest request) {
        
        String ret = "";
        ret = request.getParameter("SEQ")
            + "/" + request.getParameter("DATE")
            + "/" + request.getParameter("TIME")
            + "/" + request.getParameter("SID")
            + "/" + request.getParameter("KINGAKU")
            + "/" + request.getParameter("CVS")
            + "/" + request.getParameter("SCODE")
            + "/" + request.getParameter("FUKA");
        
        return ret;
    }
}