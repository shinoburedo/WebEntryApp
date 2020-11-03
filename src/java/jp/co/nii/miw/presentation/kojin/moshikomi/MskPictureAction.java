package jp.co.nii.miw.presentation.kojin.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DownloadAction;

import java.io.File;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>タイトル: PictureAction</p>
 * <p>説明: <BR>
 * 写真をダウンロードする。<BR>
 * <p>著作権: Copyright (c) 2010
 * <p>会社名: 日本情報産業株式会社</p>
 * @author
 */
public final class MskPictureAction extends DownloadAction {

	/** ログに出力するクラス名 */
	private static String CLASS_NAME;
	public MskPictureAction() {
		CLASS_NAME = this.getClass().getName();
	}

//	// 業務コード
//	public static final String BUSINESS_CODE = PropertyUtility
//			.getProperty("business_code");
//
//	// picture関連プロパティ取得用 キー
//	public static final String PROPERTY_KEY_PICTURE_PATH = "picture_path";

	// picture保管ディレクトリ
//	public static final String PATH = PropertyUtility.getProperty(BUSINESS_CODE
//			+ PROPERTY_KEY_PICTURE_PATH);


	private static final String CONTENT_TYPE = "image/pjpg";
	/** エラー用JPGのFile **/
	private static final File ERROR_PICTURE = new File(MiwConstants.PROPERTY_KEY_PICTURE_PATH_SMALL_TMP + "error.gif");
	/** エラー用JPGのFileStreamInfo **/
	private static final DownloadAction.FileStreamInfo ERROR_PICTURE_FSI = new DownloadAction.FileStreamInfo("image/gif", ERROR_PICTURE);


	protected StreamInfo getStreamInfo(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {

		FileStreamInfo fsi = null;
		String gazo_id = "";

		//セッション開始
		HttpSession session = request.getSession(false);
		//保持した情報を取得する
		MskToroku mskTorokuJoho = (MskToroku) session.getAttribute("MskTorokuJoho");
                
                if (mskTorokuJoho != null){
                        try {
                                File file = null;

                                //画像IDを取得
                                gazo_id = mskTorokuJoho.getGazoId();

                                //写真の置いてあるパスを作成
                                file = getPictureFile(gazo_id);

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
                                "セッションがないので、エラーコンテンツを返却します");
                        //エラーコンテンツのFileStreamInfoをセット
                                fsi = ERROR_PICTURE_FSI;
                }

		//FileStreamInfoを返却
		return fsi;
	}

	/**
	 * 写真ファイルを取得するメソッド<BR>
	 * <BR>
	 * @param MskMoshikomiJoho 申込情報
	 * @return File
	 */
	public File getPictureFile(String gazoId) throws Exception {

		File file = null;

		//顔写真はPATHの中に、画像ID.JPGで保管されている
		file = new File(MiwConstants.PICTURE_PATH_SMALL_TMP + gazoId + ".jpg");
		return file;
	}

}