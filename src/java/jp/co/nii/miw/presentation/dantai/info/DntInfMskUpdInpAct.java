package jp.co.nii.miw.presentation.dantai.info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.dantai.DntInf;
import jp.co.nii.miw.business.service.dantai.DntInfServ;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import jp.co.nii.miw.business.service.mypage.MypMskUpdServ;
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
 * タイトル:DntInfMskUpdInpAct
 * 説明:DntInfMskUpdInpActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author --r.ehara
 */
public class DntInfMskUpdInpAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(false);
        // 返却するフォワード名
        String ret = "";
        // Formオブジェクトの作成
        DntInfMskUpdFrm dntInfMskUpdFrm = (DntInfMskUpdFrm) form;
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

        // 保持している情報を取得
        MypMskInf moshikomiJohoInp = (MypMskInf) session.getAttribute("MoshikomiJohoInp");
        MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");
        // 年度を取得
        String nendo = moshikomiJohoInp.getNendo();
        // 回数を取得
        String kaisu = moshikomiJohoInp.getKaisu();

        // フォームの内容をセッションBeanへコピー
        BeanUtils.copyProperties(moshikomiJohoInp, dntInfMskUpdFrm);
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

            boolean state = this.ValidationCaller(request, moshikomiJoho, moshikomiJohoInp, nendo, kaisu, fileUp, gazoId);

            if (state) {

                MskTorokuServ mskTorokuServ = new MskTorokuServ();
                //画像補正依頼区分を取得
                String hoseiIraiKbn = moshikomiJoho.getHoseiIraiKbn();

                // 画像補正依頼区分が「補正依頼」or「補正済み」の場合(画像入力フォームがある時)
                if (MiwConstants.HOSEI_IRAI_KBN_IRAI.equals(hoseiIraiKbn) || MiwConstants.HOSEI_IRAI_KBN_HOSEI.equals(hoseiIraiKbn)) {
                    if ("".equals(mskTorokuServ.checkFileRequired(fileUp))) {

                        moshikomiJohoInp.setGazoId(gazoId);

                        //画像を一時保管領域へアップロード
                        uploadTmpPicture(moshikomiJohoInp, gazoId, fileUp);

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
        } else if (request.getParameter("cancel") != null) {

            // 保持している情報を取得
            DntInf dntInfo = (DntInf) session.getAttribute("DntInfo");
            // サービス作成
            MypMskUpdServ mskTorokuServ = new MypMskUpdServ();
            DntInfServ dntInfServ = new DntInfServ();

            // 申込・申込者update
            mskTorokuServ.cancelMoshikomi(moshikomiJoho, dntInfo.getDantaiCode());
            
            // sessionを保存
            session.setAttribute("MoshikomiJoho", moshikomiJoho);
            //受付取消完了メッセージ
            ActionMessages msgs = new ActionMessages();
            msgs.add("cancel", new ActionMessage("messages.cancel"));
            saveMessages(request, msgs);

            ret = FWD_NM_BACK;
        } else if (request.getParameter("back") != null) {
            ret = FWD_NM_BACK;
        } else {
            ret = FWD_NM_SESSION;
        }

        session.setAttribute("MoshikomiJoho", moshikomiJoho);
        session.setAttribute("MoshikomiJohoInp", moshikomiJohoInp);

        return ret;
    }

    private boolean ValidationCaller(HttpServletRequest request, MypMskInf moshikomiJoho, MypMskInf moshikomiJohoInp, String nendo, String ki, FormFile fileUp, String GazoID) throws Exception {

        MypMskUpdServ mypMskUpdServ = new MypMskUpdServ();
        MskTorokuServ mskTorokuServ = new MskTorokuServ();
        ActionMessages errors = new ActionMessages();

        String ret = null;

        //入力チェックを行う
        mypMskUpdServ.publicValidationCaller(errors, moshikomiJohoInp, nendo, ki);

        //画像補正依頼区分を取得
        String hoseiIraiKbn = moshikomiJoho.getHoseiIraiKbn();

        // 画像補正依頼区分が「依頼無し」or「補正確認済み」の場合(画像入力フォームが無い時)
        if (MiwConstants.HOSEI_IRAI_KBN_MI.equals(hoseiIraiKbn) || MiwConstants.HOSEI_IRAI_KBN_KAKUNIN.equals(hoseiIraiKbn)) {
            if (!mypMskUpdServ.checkUpdPoint(moshikomiJoho, moshikomiJohoInp)) {
                //１箇所も変更が無い且つ、写真が入力されていない場合エラー
                errors.add("noupd", new ActionMessage("errors.noupd"));
            }
        } else {
            if (!mypMskUpdServ.checkUpdPoint(moshikomiJoho, moshikomiJohoInp) && !"".equals(mskTorokuServ.checkFileRequired(fileUp))) {
                //１箇所も変更が無い且つ、写真が入力されていない場合エラー
                errors.add("noupd", new ActionMessage("errors.noupd"));
            }

            // 画像が入力されていたら画像チェック
            if ("".equals(mskTorokuServ.checkFileRequired(fileUp))) {

                try {
                    //ファイル存在チェック
                    ret = mskTorokuServ.checkFileSonzai(fileUp);
                    if (!"".equals(ret)) {
                        errors.add(ActionMessages.GLOBAL_MESSAGE,
                                new ActionMessage(ret));
                    } else {

                        //ファイルのサイズチェック（バイト）
                        ret = mskTorokuServ.checkFileSize(fileUp, MiwConstants.MAX_PHOTO_FILE_SIZE);
                        if (!"".equals(ret)) {
                            errors.add(ActionMessages.GLOBAL_MESSAGE,
                                    new ActionMessage(ret));
                        } else {
                            //ファイルの拡張子チェック
                            ret = mskTorokuServ.checkFileSuffix(fileUp, MiwConstants.PHOTO_FILE_FIXED_SUFFIX);
                            if (!"".equals(ret)) {
                                errors.add(ActionMessages.GLOBAL_MESSAGE,
                                        new ActionMessage(ret));
                            } else {
                                //ファイルの上限サイズチェック（ピクセル）
                                ret = mskTorokuServ.checkHeightWidth(fileUp, MiwConstants.MAX_PHOTO_HEIGHT, MiwConstants.MAX_PHOTO_WIDTH, MiwConstants.MIN_PHOTO_HEIGHT, MiwConstants.MIN_PHOTO_WIDTH);
                                if (!"".equals(ret)) {
                                    errors.add(ActionMessages.GLOBAL_MESSAGE,
                                            new ActionMessage(ret));
                                }
                            }
                        }
                    }
                    //エラーがあったら
                    if (!errors.isEmpty()) {
                        saveErrors(request, errors);
                    }
                    
                } catch (FileNotFoundException e) {
                    throw e;
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
                } catch (Exception e) {
                    throw e;
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
     * @param moshikomiJohoInp
     * @param mypageJoho
     * @param gazoId
     * @param fileUp 
     */
    private void uploadTmpPicture(MypMskInf moshikomiJohoInp, String gazoId, FormFile fileUp) throws Exception {

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

    }
}