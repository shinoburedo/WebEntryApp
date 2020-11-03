package jp.co.nii.miw.presentation.dantai.moshikomi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.commons.beanutils.BeanUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.upload.FormFile;

/**
 * タイトル:MskPicSelAct 説明:MskPicSelのアクションクラス 画面で「次へ」ボタンが押されたら、入力チェックを行い、
 * 申込情報を保持し、MskPicConfへ遷移する。 エラーがあった場合、エラーメッセージを画面に表示させる。
 *
 * 画面で「戻る」ボタンが押されたら、MskInpへ遷移する
 *
 * 著作権: Copyright (c) 2012 会社名: 日本情報産業株式会社
 *
 * @author --r-ehara
 */
public class MskPicSelAct extends AbstractAction {

    /**
     * ログに出力するクラス名
     */
    private static String CLASS_NAME;

    public MskPicSelAct() {
        CLASS_NAME = this.getClass().getName();
    }

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";

        //保持している情報を取得
        MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");
        //フォーム取得
        MskPicSelFrm picForm = (MskPicSelFrm) form;

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

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(mskTorokuJoho, picForm);

        //ログ出力用の画像IDを取得
//        GAZO_ID = mskTorokuJoho.getGazoId();

        if (request.getParameter("submit") != null) {//「次　へ」ボタンが押下されたら
            // FormFileオブジェクトの取得
            FormFile fileUp = (FormFile) picForm.get("photoFile");
            //ログ出力用のIDを取得
            String id = mskTorokuJoho.getUserId();
            // 画面遷移に影響するサービスはexecuteメソッド内で呼び出す（インターフェース経由）
            // 入力パラメータのチェックの呼び出し
            String retCheck = ValidationCaller(request, form, fileUp, id);

            if (retCheck.equals("allok")) {

                // 画像ＩＤ取得
                String gazoId = mskTorokuServ.gazoIdIssue();
                mskTorokuJoho.setGazoId(gazoId);

                //表示用画像を保管するディレクトリ名を取得
                String strPhotoPath = MiwConstants.PICTURE_PATH_SMALL_TMP + gazoId + MiwConstants.SUFFIX;
                //保存用画像を保管する作業用ディレクトリ名を取得
                String strPhotoPathTmp = MiwConstants.PICTURE_PATH_TMP + gazoId + MiwConstants.SUFFIX;

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

                //更新日を取得
                String yyyyMMdd = yyyyMMddHHmmss.substring(0, 8);

                //更新時刻を取得
                String HHssmm = yyyyMMddHHmmss.substring(8, 14);

                //更新日をセット
                mskTorokuJoho.setKaojashinKoshinDate(yyyyMMdd);

                //更新時刻をセット
                mskTorokuJoho.setKaojashinKoshinTime(HHssmm);

                //写真のファイルセットする
//                mskTorokuJoho.setGazoData(gazoFile);

                ret = FWD_NM_SUCCESS;

            } //エラーがあるなら
            else {

                ret = FWD_NM_ERROR;
            }
        } else if (request.getParameter("back") != null) {//「戻　る」ボタンが押下されたら

            ret = FWD_NM_BACK;
        } else {
            throw new Exception();
        }

        //session保存
        session.setAttribute("MskTorokuJoho", mskTorokuJoho);

        return ret;
    }

    /**
     * ValidationCaller
     * @throws Exception
     */
    private String ValidationCaller(HttpServletRequest request, ActionForm form, FormFile fileUp, String id) throws Exception {

        // ValidationCaller全体としての結果
        String stat = "allok";
        String exmsg = "";

        // メッセージ格納用にActionMessagesオブジェクトの生成
        ActionMessages ams_error = new ActionMessages();

        // validateロジックを持つサービスのインスタンスを生成する
        MskTorokuServ service = new MskTorokuServ();

        String ret = null;

        // 各validateメソッドを呼び出し
        try {

            //ファイルの未入力チェック
            ret = service.checkFileRequired(fileUp);
            if (!"".equals(ret)) {
                ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                        new ActionMessage(ret));
            } else {
                //ファイル存在チェック
                ret = service.checkFileSonzai(fileUp);
                if (!"".equals(ret)) {
                    ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage(ret));
                } else {

                    //ファイルの上限サイズチェック（バイト）
                    ret = service.checkFileSize(fileUp, MiwConstants.MAX_PHOTO_FILE_SIZE);
                    if (!"".equals(ret)) {
                        ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage(ret));
                    } else {
                        //ファイルの拡張子チェック
                        ret = service.checkFileSuffix(fileUp, MiwConstants.PHOTO_FILE_FIXED_SUFFIX);
                        if (!"".equals(ret)) {
                            ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                                    new ActionMessage(ret));
                        } else {
                            //ファイルの上限サイズチェック（ピクセル）
                            ret = service.checkHeightWidth(fileUp, MiwConstants.MAX_PHOTO_HEIGHT, MiwConstants.MAX_PHOTO_WIDTH, MiwConstants.MIN_PHOTO_HEIGHT, MiwConstants.MIN_PHOTO_WIDTH);
                            if (!"".equals(ret)) {
                                ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                                        new ActionMessage(ret));
                            }
                        }
                    }
                }
            }
            //エラーがあったら
            if (!ams_error.isEmpty()) {
                stat = "errors";
                // ActionMessagesオブジェクトをエラーメッセージとしてrequestに登録
                saveErrors(request, ams_error);
//						//値を変換し、セットする
//						 setForm(request, form,mskshiken);
            }

        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
            exmsg = e.getMessage();
            //CMYK形式の場合、「Unsupported Image Type」が出力される
            if (("Unsupported Image Type").equals(exmsg)) {
                LogGenerate.infoWrite(CLASS_NAME, "execute()", "Unsupported Image Type:顔写真がRGB形式以外のため、Validateエラーを出力", request, id);

                try {
                    ams_error.add(ActionMessages.GLOBAL_MESSAGE,
                            new ActionMessage("errors.fileUnsupportedImageTypeExtension"));
                    stat = "errors";
                    // ActionMessagesオブジェクトをエラーメッセージとしてrequestに登録
                    saveErrors(request, ams_error);
                } catch (Exception ei) {
                    throw ei;
                }
                //「Unsupported Image Type」以外のIOExceptionのとき、再度throw
            } else {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }

        // ValidationCaller全体としての結果返却
        return stat;
    }
}
