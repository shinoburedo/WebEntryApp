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
import jp.co.nii.sew.business.Validate;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import jp.co.nii.sew.presentation.AbstractAction;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

/**
 * タイトル:MypMskUpdInpAct 説明:MypMskUpdInpのアクションクラス 著作権: Copyright (c) 2011 会社名:
 * 日本情報産業株式会社
 *
 * @author --t.yamaguchi
 */
public class MypMskUpdInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //セッション作成
        HttpSession session = request.getSession(false);
        //返却するフォワード名
        String ret = "";
        //Formオブジェクトの作成
        MypMskUpdFrm mypMskUpdFrm = (MypMskUpdFrm) form;
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

        //保持している情報を取得
        MypMskInf moshikomiJohoInp = (MypMskInf) session.getAttribute("MoshikomiJohoInp");
        MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");
        MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");
        //年度を取得
        String nendo = moshikomiJohoInp.getNendo();
        //季を取得
        String ki = moshikomiJohoInp.getKaisu();

        //フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(moshikomiJohoInp, mypMskUpdFrm);
        //値のスペースを削除する
        moshikomiJohoInp.deleteSpace();

        //このアクション内で
        if (request.getParameter("submit") != null) {

            //フォーム取得
            DynaActionForm picForm = (DynaActionForm) form;

            // FormFileオブジェクトの取得
            FormFile fileUp = (FormFile) picForm.get("photoFile");

            //画像ＩＤを取得
            String gazoId = moshikomiJoho.getGazoId();

            boolean state = this.ValidationCaller(request, moshikomiJoho, moshikomiJohoInp, nendo, ki, fileUp, gazoId);

            if (state) {

                MskTorokuServ mskTorokuServ = new MskTorokuServ();
                //画像補正依頼区分を取得
                String hoseiIraiKbn = moshikomiJoho.getHoseiIraiKbn();

                // 画像補正依頼区分が「補正依頼」or「補正済み」の場合(画像入力フォームがある時)
                if (hoseiIraiKbn.equals(MiwConstants.HOSEI_IRAI_KBN_IRAI) || hoseiIraiKbn.equals(MiwConstants.HOSEI_IRAI_KBN_HOSEI)) {
                    if ("".equals(mskTorokuServ.checkFileRequired(fileUp))) {

                        moshikomiJohoInp.setGazoId(gazoId);

                        //画像を一時保管領域へアップロード
                        uploadTmpPicture(moshikomiJohoInp, mypageJoho, gazoId, fileUp);

                        //画像変更有無フラグにオンをセット
                        moshikomiJohoInp.setGazoUpdFlg(MiwConstants.FLG_ON);
                    } else {
                        //画像変更有無フラグにオフをセット
                        moshikomiJohoInp.setGazoUpdFlg(MiwConstants.FLG_OFF);
                    }
                } else {
                    //画像変更有無フラグにオフをセット
                    moshikomiJohoInp.setGazoUpdFlg(MiwConstants.FLG_OFF);
                }

                ret = FWD_NM_SUCCESS;
            } else {
                ret = FWD_NM_ERROR;
            }
        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        } else if (request.getParameter("logout") != null) {
            ret = FWD_NW_LOGOUT;
        } else {
            ret = FWD_NM_SESSION;
        }

        session.setAttribute("MypageJoho", mypageJoho);
        session.setAttribute("MoshikomiJoho", moshikomiJoho);
        session.setAttribute("MoshikomiJohoInp", moshikomiJohoInp);

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MypMskInf moshikomiJoho, MypMskInf moshikomiJohoInp, String nendo, String ki, FormFile fileUp, String GazoID) throws Exception {

        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        ActionMessages errors = new ActionMessages();

        String ret = null;

        //画像補正依頼区分を取得
        String hoseiIraiKbn = moshikomiJoho.getHoseiIraiKbn();

        // 画像補正依頼区分が「依頼無し」or「補正確認済み」の場合(画像入力フォームが無い時)
        if (hoseiIraiKbn.equals(MiwConstants.HOSEI_IRAI_KBN_MI) || hoseiIraiKbn.equals(MiwConstants.HOSEI_IRAI_KBN_KAKUNIN)) {
            if (!mypMskUpdServ.checkUpdPoint(moshikomiJoho, moshikomiJohoInp)) {
                //１箇所も変更が無い且つ、写真が入力されていない場合エラー
                errors.add("noupd", new ActionMessage("errors.noupd"));
            } else {
                //入力チェックを行う
                mypMskUpdServ.publicValidationCaller(errors, moshikomiJohoInp, nendo, ki);
            }
        } else {
            if (!mypMskUpdServ.checkUpdPoint(moshikomiJoho, moshikomiJohoInp) && !"".equals(mskTorokuServ.checkFileRequired(fileUp))) {
                //１箇所も変更が無い且つ、写真が入力されていない場合エラー
                errors.add("noupd", new ActionMessage("errors.noupd"));
            } else {
                //入力チェックを行う
                mypMskUpdServ.publicValidationCaller(errors, moshikomiJohoInp, nendo, ki);
                if ("".equals(mskTorokuServ.checkFileRequired(fileUp))) {
                    // 画像が入力されていたら画像チェック
                    try {
                        //画像チェックを行う
                        mypMskUpdServ.publicPicValidationCaller(errors, fileUp);
                    } catch (IOException e) {
                        //CMYK形式の場合、「Unsupported Image Type」が出力される
                        if (("Unsupported Image Type").equals(e.getMessage())) {
                            LogGenerate.infoWrite(CLASS_NAME, "execute()", "Unsupported Image Type:顔写真がRGB形式以外のため、Validateエラーを出力", request, GazoID);
                            try {
                                errors.add(ActionMessages.GLOBAL_MESSAGE,
                                        new ActionMessage("errors.fileUnsupportedImageTypeExtension"));
                                // ActionMessagesオブジェクトをエラーメッセージとしてrequestに登録
                                saveErrors(request, errors);
                            } catch (Exception ei) {
                                throw ei;
                            }
                            //「Unsupported Image Type」以外のIOExceptionのとき、再度throw
                        } else {
                            throw e;
                        }
                    }
                }
            }
        }

        //エラーをセッションBeanに格納
        moshikomiJohoInp.setErrors(errors);

        if (!errors.isEmpty()) {
            if (errors.get("noupd").hasNext()) {
                saveMessages(request, errors);
            } else {
                saveErrors(request, errors);
            }
            return false;
        } else {
            return true;
        }
    }

    /**
     * 画像保存処理
     *
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