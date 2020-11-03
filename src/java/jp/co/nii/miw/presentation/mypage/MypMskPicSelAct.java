package jp.co.nii.miw.presentation.mypage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskUpdServ;
import jp.co.nii.miw.business.service.mypage.MypageJoho;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

/**
 * タイトル:MskPicSelAct 説明:MskPicSelのアクションクラス 画面で「次へ」ボタンが押されたら、入力チェックを行い、
 * 申込情報を保持し、MskPicConfへ遷移する。 エラーがあった場合、エラーメッセージを画面に表示させる。
 *
 * 画面で「戻る」ボタンが押されたら、MskInpへ遷移する
 *
 * 著作権: Copyright (c) 2012 会社名: 日本情報産業株式会社
 *
 * @author --h-katayama
 */
public class MypMskPicSelAct extends AbstractAction {

    /**
     * ログに出力するクラス名
     */
    private static String CLASS_NAME;

    public MypMskPicSelAct() {
        CLASS_NAME = this.getClass().getName();
    }

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        //保持している情報を取得
        MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");
        MypMskInf moshikomiJohoInp = (MypMskInf) session.getAttribute("MoshikomiJohoInp");
        MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");
        //年度を取得
        String nendo = moshikomiJohoInp.getNendo();
        //季を取得
        String ki = moshikomiJohoInp.getKaisu();
        //フォーム取得
        DynaActionForm picForm = (DynaActionForm) form;

        // 前の画面のトークンと比較する。
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

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(moshikomiJoho, picForm);

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        if (request.getParameter("submit") != null) {//「次　へ」ボタンが押下されたら
            // FormFileオブジェクトの取得
            FormFile fileUp = (FormFile) picForm.get("photoFile");

            //MskShikeから画像ＩＤを取得
            String gazoId = moshikomiJoho.getGazoId();

            String retCheck = ValidationCaller(request, fileUp, gazoId);

            if (retCheck.equals("allok")) {

                moshikomiJohoInp.setGazoId(gazoId);

                //画像を一時保管領域へアップロード
                uploadTmpPicture(moshikomiJohoInp, mypageJoho, gazoId, fileUp);

                //画像変更有無フラグにオンをセット
                moshikomiJohoInp.setGazoUpdFlg(MiwConstants.FLG_ON);

                ret = FWD_NM_SUCCESS;

            } //エラーがあるなら
            else {

                ret = FWD_NM_ERROR;
            }
        } else if (request.getParameter("back") != null) {//「戻　る」ボタンが押下されたら
            ret = FWD_NM_BACK;
        } else if (request.getParameter("logout") != null) {
            ret = FWD_NW_LOGOUT;
        } else {
            throw new Exception();
        }

        //session保存
        session.setAttribute("MypageJoho", mypageJoho);
        session.setAttribute("MoshikomiJoho", moshikomiJoho);
        session.setAttribute("MoshikomiJohoInp", moshikomiJohoInp);


        return ret;
    }

    /**
     * ValidationCaller
     * @throws Exception
     */
    private String ValidationCaller(HttpServletRequest request, FormFile fileUp, String GazoID) throws Exception {

        // ValidationCaller全体としての結果
        String stat = "allok";

        // メッセージ格納用にActionMessagesオブジェクトの生成
        ActionMessages ams_error = new ActionMessages();

        // validateロジックを持つサービスのインスタンスを生成する
        MskTorokuServ service = new MskTorokuServ();
        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();

        String ret = null;

        // 各validateメソッドを呼び出し
        //ファイルの未入力チェック
        ret = service.checkFileRequired(fileUp);
        if (!"".equals(ret)) {
            ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(ret));
        } else {
            // 画像が入力されていたら画像チェック
            try {
                //画像チェックを行う
                mypMskUpdServ.publicPicValidationCaller(ams_error, fileUp);
            } catch (IOException e) {
                //CMYK形式の場合、「Unsupported Image Type」が出力される
                if (("Unsupported Image Type").equals(e.getMessage())) {
                    LogGenerate.infoWrite(CLASS_NAME, "execute()", "Unsupported Image Type:顔写真がRGB形式以外のため、Validateエラーを出力", request, GazoID);
                    try {
                        ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage("errors.fileUnsupportedImageTypeExtension"));
                        // ActionMessagesオブジェクトをエラーメッセージとしてrequestに登録
                        saveErrors(request, ams_error);
                    } catch (Exception ei) {
                        throw ei;
                    }
                    //「Unsupported Image Type」以外のIOExceptionのとき、再度throw
                } else {
                    throw e;
                }
            }
        }
        //エラーがあったら
        if (!ams_error.isEmpty()) {
            stat = "errors";
            // ActionMessagesオブジェクトをエラーメッセージとしてrequestに登録
            saveErrors(request, ams_error);
        }

        // ValidationCaller全体としての結果返却
        return stat;
    }

    /**
     * 画像保存処理
     * @param moshikomiJohoInp
     * @param mypageJoho
     * @param gazoId
     * @param fileUp 
     */
    private void uploadTmpPicture(MypMskInf moshikomiJohoInp, MypageJoho mypageJoho, String gazoId, FormFile fileUp) throws Exception {

        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        //保存用画像を保管する作業用ディレクトリ名を取得
        String strPhotoPathTmp = MiwConstants.PICTURE_PATH_TMP + gazoId + MiwConstants.SUFFIX;
        //表示用画像を保管するディレクトリ名を取得
        String strPhotoPath = MiwConstants.PICTURE_PATH_SMALL_TMP + gazoId + MiwConstants.SUFFIX;

        //表示用画像を圧縮して出力
        mskTorokuServ.imageCompressionOutput(fileUp, strPhotoPath);

        //保存用画像を作業用フォルダに出力
        mskTorokuServ.fileUpload(fileUp, strPhotoPathTmp);

        //一時領域のアップロードデータを削除
        fileUp.destroy();

        //File型のオブジェクト作成
        File gazoFile = new File(strPhotoPath);

        //画像の更新日時を取得
        long last = gazoFile.lastModified();

        //long型の日時データをyyyyMMddHHmmss型にする
        String yyyyMMddHHmmss = mskTorokuServ.longToString(last);

        //補正対応日を取得
        String yyyyMMdd = yyyyMMddHHmmss.substring(0, 8);

        //補正対応時刻を取得
        String HHssmm = yyyyMMddHHmmss.substring(8, 14);

        //補正対応日をセット
        moshikomiJohoInp.setHoseiTaioBi(yyyyMMdd);

        //補正対応時刻をセット
        moshikomiJohoInp.setHoseiTaioTime(HHssmm);

        // 画像補正依頼区分を補正済みにする
        moshikomiJohoInp.setHoseiIraiKbn(MiwConstants.HOSEI_IRAI_KBN_HOSEI);

        //写真のファイルセットする
        mypageJoho.setGazoData(gazoFile);
    }
}
