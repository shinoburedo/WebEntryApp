package jp.co.nii.miw.presentation.common;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import java.io.File;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * <p>タイトル: PictureAction</p>
 * <p>説明: <BR>
 * 写真をダウンロードする。<BR>
 * <p>著作権: Copyright (c) 2012
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public final class PictureAction extends DownloadAction {

    /** ログに出力するクラス名 */
    private static String CLASS_NAME;

    public PictureAction() {
        CLASS_NAME = this.getClass().getName();
    }
    private static final String CONTENT_TYPE = "image/pjpg";
    /** エラー用JPGのFile **/
    private static final File ERROR_PICTURE = new File(MiwConstants.PICTURE_PATH_SMALL_HOZON + "error.gif");
    /** エラー用JPGのFileStreamInfo **/
    private static final DownloadAction.FileStreamInfo ERROR_PICTURE_FSI = new DownloadAction.FileStreamInfo("image/gif", ERROR_PICTURE);

    protected StreamInfo getStreamInfo(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        FileStreamInfo fsi = null;

        // 写真の取得に必要な情報をリクエストから取得
        // 画像ID
        String gazo_id = request.getParameter("gazoId");
        // ファイルパス
        String path = "";
        if("1".equals(request.getParameter("param"))){
            path = MiwConstants.PICTURE_PATH_SMALL_TMP;
        }else if("2".equals(request.getParameter("param"))){
            path =MiwConstants.PICTURE_PATH_SMALL_HOZON;
        }
        
        if (gazo_id != null && !("".equals(path))) {
            try {
                File file = null;

                //写真の置いてあるパスを作成
                file = getPictureFile(gazo_id,path);

                //ファイルが読めるかテスト
                if (file.canRead()) {
                    LogGenerate.debugOutput(
                            this.getClass().getName(),
                            "写真ファイルは読み込めます");
                    //写真ファイルのFileStreamInfoをセット
                    fsi = new FileStreamInfo(CONTENT_TYPE, file);

                } else {
                    LogGenerate.warnOutput(
                            this.getClass().getName(),
                            "id=[" + gazo_id + "]の写真ファイルが見つからないので、エラーコンテンツを返却します");
                    //エラーコンテンツのFileStreamInfoをセット
                    fsi = ERROR_PICTURE_FSI;
                }

            } catch (Exception e) {
                LogGenerate.errWrite(CLASS_NAME, "getStreamInfo()", e, request, gazo_id);
                fsi = ERROR_PICTURE_FSI;
            }
        } else {
            LogGenerate.warnOutput(
                    this.getClass().getName(),
                    "情報取得エラーの為、エラーコンテンツを返却します");
            //エラーコンテンツのFileStreamInfoをセット
            fsi = ERROR_PICTURE_FSI;
        }

        //FileStreamInfoを返却
        return fsi;
    }

    /**
     * 写真ファイルを取得するメソッド<BR>
     * <BR>
     * @param gazoid
     * @param path
     * @return File
     */
    public File getPictureFile(String gazoId, String path) throws Exception {

        File file = null;

        //顔写真はPATHの中に、画像ID.JPGで保管されている
        file = new File(path + gazoId + ".jpg");
        return file;
    }
}