package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;

import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.business.SystemTime;
import jp.co.nii.miw.business.domain.Moshikomisha;
import jp.co.nii.miw.business.service.MiaMailSendServ;
import jp.co.nii.miw.business.service.PayConstants;
import jp.co.nii.miw.business.service.payment.PayServ;
import jp.co.nii.miw.business.service.payment.PayToroku;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.SEWException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ActionMessage;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.business.Validate;
import jp.co.nii.sew.utility.StringUtility;

/**
 * タイトル:PayCrdWaitAct
 * 説明:PayCrdWaitのアクションクラス
 * デジタルチェックへの決済要求を行う
 * 著作権:   Copyright (c) 2011
 * 会社名:   日本情報産業株式会社
 * @author --k.narita
 */
public class MskPayCrdConfAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        // 前画面のトークンと比較する。
        if (!isTokenValid(request, true) && MiwConstants.STRESS_MODE.equals(MiwConstants.STRESS_MODE_HONBAN)) {

            ActionMessages amg = new ActionMessages();

            saveErrors(request, amg);
            //セッションを切る
            session.invalidate();
            // 前画面のトークンと異なる場合は画面遷移不正でエラー
            return FWD_NM_SESSION;
        } else {
            saveToken(request);
        }

        //エラーメッセージ編集用
        ActionMessages errors = new ActionMessages();

        //保持情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");

        //サービスクラス作成
        PayServ payTestServ = new PayServ();
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        MiaMailSendServ miwMailSendServ = new MiaMailSendServ();

        //カード番号セット
        mskTorokuJoho.setCardNo(mskTorokuJoho.getCardNo1()
                + mskTorokuJoho.getCardNo2()
                + mskTorokuJoho.getCardNo3()
                + mskTorokuJoho.getCardNo4());

        //決済情報パラメータに値セット
        PayToroku payTorokuJoho = new PayToroku();
        payTorokuJoho = mskTorokuServ.setPayTorokuJoho(mskTorokuJoho, payTorokuJoho);

        if (request.getParameter("submit") != null) {
            //二重登録チェック
            Moshikomisha retSearch = mskTorokuServ.selectMoshikomishaDouble(mskTorokuJoho, MiwConstants.FLG_OFF);

            if (retSearch == null) {
                //二重登録
                session.invalidate();
                return FWD_NM_DOUBLE;
            }

            //入力チェック
            boolean state1 = ValidationCaller1(request, mskTorokuJoho);

            if (state1 == true) {
                //申込情報にエラーなし

//                boolean state2 = ValidationCaller2(request, mskTorokuJoho);
//                if (state2 == true) {
                    //決済情報にエラーなし
                    //決済処理
                    String strRtn[] = payTestServ.executePayment(payTorokuJoho, mskTorokuJoho.getKessaiConvenienceShubetsu(),
                            MiwConstants.KESSAI_TEST_MODE, MiwConstants.KESSAI_URL_1, MiwConstants.KESSAI_URL_2);
                    
                    String message = mskTorokuServ.createMessage(mskTorokuJoho, payTorokuJoho);
                    try {
                        //決済ログ(決済要求 送信値)
                        mskTorokuServ.insertKessaiRequest(mskTorokuJoho, payTorokuJoho, message, MiwConstants.MESSAGE_SHUBETSU_REQUEST_SEND);
                        //決済ログ(決済要求 受信値)
                        mskTorokuServ.insertKessaiRequest(mskTorokuJoho, payTorokuJoho, strRtn[0], MiwConstants.MESSAGE_SHUBETSU_REQUEST_RET);
                        
                    } catch (Exception e) {
                        LogGenerate.errWrite(CLASS_NAME, "doProcess", e, "!Attention!決済ログ作成処理で例外が発生した。MSG：" + message);
                    }

                    if (strRtn[1].equals(PayConstants.PAY_RETURN_OK)) {
                        //決済成功時
                        //決済ログ本文をセット
                        mskTorokuJoho.setLog(strRtn[0]);

                        //加盟店コード / 取引コードをセット
                        mskTorokuJoho.setKessaiKameitenCode(payTorokuJoho.getIp());
                        mskTorokuJoho.setKessaiTorihikiCode(payTorokuJoho.getSid());

                        //申込日をセット
                        SystemTime sysTim = new SystemTime();
                        mskTorokuJoho.setMoshikomibi(sysTim.getymd1());

                        mskTorokuJoho.setTetsudukiJokyoKbn(MiwConstants.TETSUDUKI_JOKYO_KBN_KANRYO);
                        mskTorokuJoho.setKessaiJokyoKbn(MiwConstants.KESSAI_JOKYO_KBN_KAKUNIN);


                        //申込情報更新
                        if (mskTorokuServ.shutsugan(mskTorokuJoho, "")) {

                            //作業用フォルダの画像を保存用フォルダへ
                            mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_TMP,
                                    MiwConstants.PICTURE_PATH_HOZON, mskTorokuJoho.getGazoId());

                            //作業用縮小フォルダの画像を保管用縮小フォルダへ
                            mskTorokuServ.changeDirectory(MiwConstants.PICTURE_PATH_SMALL_TMP,
                                    MiwConstants.PICTURE_PATH_SMALL_HOZON, mskTorokuJoho.getGazoId());

//                            メール送信
                            if (miwMailSendServ.sendShutsuganResuMail(mskTorokuJoho)) {
                                //メール送信成功
                            } else {
                                //メール送信失敗
                                LogGenerate.errorOutput("ユーザＩＤ："
                                        + mskTorokuJoho.getUserId() + "の申込完了メール送信が失敗しましたが、申込情報の更新が完了しているのでエラー画面に遷移しません。");
//                            throw new Exception("ユーザＩＤ：" + mskTorokuJoho.getId() + "メール送信失敗");
                            }
                            //リクエストに情報を保存
                            request.setAttribute("MskTorokuJoho", mskTorokuJoho);
                            //セッションの情報を破棄する
                            session.removeAttribute("MskTorokuJoho");
                            //完了画面へ遷移
                            ret = FWD_NM_SUCCESS;

                        } else {
                            // 申込情報更新に失敗
                            LogGenerate.errorOutput("!Attention!ユーザＩＤ： "
                                    + mskTorokuJoho.getUserId() + " の申込情報更新に失敗しました。"
                                    + " 決済状況区分： " + mskTorokuJoho.getKessaiJokyoKbn()
                                    + " 決済方法区分： " + mskTorokuJoho.getKessaiHohoKbn()
                                    + " 決済加盟店コード：" + mskTorokuJoho.getKessaiTorihikiCode()
                                    + " 決済金額：" + mskTorokuJoho.getKessaiKingaku());

                            // 特別なエラー画面へ遷移
                            ret = FWD_NM_ERROR_UD;
                        }


                        // 決済失敗の場合
                    } else {
                        //エラーメッセージ作成
                        String kessaiError = mskTorokuServ.kessaiErrorHenkan(MiwConstants.KESSAI_HOHO_KBN_CRD, strRtn[3]);

                        errors.add("0", new ActionMessage(kessaiError));
                        saveErrors(request, errors);
                        
                        LogGenerate.infoOutput("クレジット決済でDCからNGが返却。ユーザＩＤ： "
                                + mskTorokuJoho.getUserId()
                                + " エラーコード： " + strRtn[3]);

                        ret = FWD_NM_ERROR_DC;
                    }

//                } else {
//                    //決済情報にエラー有
//                    ret = FWD_NM_ERROR;
//                }

            } else {
                LogGenerate.errWrite("決済前の入力値チェックでエラー　ユーザーＩＤ：" + mskTorokuJoho.getUserId());
                ret = FWD_NM_EXCEPTION;
            }

        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        }
        //セッションBeanにセット
        session.setAttribute("MskTorokuJoho", mskTorokuJoho);

        return ret;
    }

    private boolean ValidationCaller1(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
        ActionMessages errors = new ActionMessages();

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        //不正値チェック用
        String[] listKessaiHoho = StringUtility.getValueList(MiwConstants.DISP_KESSAI_HOHO_KBN);

        errors = mskTorokuServ.publicValidationCaller(request, mskTorokuJoho);
        Validate.validatePermissionSelect(mskTorokuJoho.getKessaiHohoKbn(), listKessaiHoho, errors, "0", "決済方法");

        if (!errors.isEmpty()) {
            saveErrors(request, errors);
            return false;
        } else {
            return true;
        }
    }

//    private boolean ValidationCaller2(HttpServletRequest request, MskToroku mskTorokuJoho) throws Exception {
//        ActionMessages errors = new ActionMessages();
//
//        String mm = mskTorokuJoho.getExpMm();
//        Validate.validateRequired(mm, errors, "1", "有効期限の月");
//        Validate.validateNumber(mm, errors, "1", "有効期限の月");
//        Validate.validateEqualLength(mm, MiwConstants.EQUAL_LEN_EXP, errors, "1", "有効期限の月");
//        String yy = mskTorokuJoho.getExpYy();
//        Validate.validateRequired(yy, errors, "1", "有効期限の年");
//        Validate.validateNumber(yy, errors, "1", "有効期限の年");
//        Validate.validateEqualLength(yy, MiwConstants.EQUAL_LEN_EXP, errors, "1", "有効期限の年");
//
//        String cardNo1 = mskTorokuJoho.getCardNo1();
//        Validate.validateRequired(cardNo1, errors, "2", "カード番号１");
//        Validate.validateNumber(cardNo1, errors, "2", "カード番号１");
//        Validate.validateMaxLength(cardNo1, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号１");
//
//        String cardNo2 = mskTorokuJoho.getCardNo2();
//        Validate.validateRequired(cardNo2, errors, "2", "カード番号２");
//        Validate.validateNumber(cardNo2, errors, "2", "カード番号２");
//        Validate.validateMaxLength(cardNo2, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号２");
//
//        String cardNo3 = mskTorokuJoho.getCardNo3();
//        Validate.validateRequired(cardNo3, errors, "2", "カード番号３");
//        Validate.validateNumber(cardNo3, errors, "2", "カード番号３");
//        Validate.validateMaxLength(cardNo3, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号３");
//
//        String cardNo4 = mskTorokuJoho.getCardNo4();
//        Validate.validateRequired(cardNo4, errors, "2", "カード番号４");
//        Validate.validateNumber(cardNo4, errors, "2", "カード番号４");
//        Validate.validateMaxLength(cardNo4, MiwConstants.MAX_LEN_CARD_NO, errors, "2", "カード番号４");
//
//        //エラーをセッションBeanに格納
//        mskTorokuJoho.setErrors(errors);
//
//        if (!errors.isEmpty()) {
//            saveErrors(request, errors);
//            return false;
//        } else {
//            return true;
//        }
//    }
}