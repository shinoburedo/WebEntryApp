package jp.co.nii.sew.utility;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>タイトル　: ログ生成クラス</p>
 * <p>説明　　　: ログ出力の種類を提供するクラス</p>
 * <p>著作権　　: Copyright (c) 2005</p>
 * <p>会社名　　: 日本情報産業株式会社</p>
 * @author S.Matsumura
 */
public final class LogGenerate {

	/** Logインスタンス **/
	private static Log log = LogFactory.getLog(LogGenerate.class);

	/** プロジェクト名キー **/
	private static final String PROPERTY_KEY_ORG_NAME = "business_code";

	/** プロジェクト名 **/
	private static final String ORG_NAME =
		PropertyUtility.getProperty(PROPERTY_KEY_ORG_NAME);

	/** double型基本フォーマット **/
	private static DecimalFormat duble_format =
		new DecimalFormat("#########0.###");

	/**
	 * コンストラクタ
	 */
	private LogGenerate() {
	}

	/** LOG名称 */
	private static final String LOGGER = ORG_NAME + "LogGenerater";

	/** ヘッダー出力 可否 **/
	private static final boolean headerles = true;
	/** フッター出力 可否 **/
	private static final boolean footerles = true;

	/** ログヘッダー文字列  **/
	private static final String headerstr = "  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
	/** ログフッター文字列  **/
	private static final String footerstr = "  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";

	/** ログ出力各行の文頭文字列(ヘッダー、フッター以外) **/
	  //private static final String headstr = "";
	  private static final String headstr = "  @@@  ";

	/** Exception時のStackTrace情報表示可否 **/
	  private static final boolean extrace = true;






//★★★★★★★新規追加ログクラス　ここから★★★★★★★★★★★★★
/*
 * 記載順序,要求引数一覧
 * 行末に「★」がついているものをFTWでは使用する
 *
 *エラー
 * errWrite  (String clsname , String mtdname , Exception ex , String message , HttpServletRequest req , String id)
 * errWrite  (String clsname , String mtdname , Exception ex , HttpServletRequest req , String id)★
 * errWrite  (String clsname , String mtdname , Exception ex , String message , HttpServletRequest req)
 * errWrite  (String clsname , String mtdname , Exception ex , String message , String id)
 * errWrite  (String clsname , String mtdname , Exception ex , String message)
 * errWrite  (String clsname , String mtdname , Exception ex)
 * errWrite  (String message)
 *
 *ワーニング
 * warnWrite (String clsname , String mtdname , String message , HttpServletRequest req , String id)★
 * warnWrite (String clsname , String mtdname , String message , HttpServletRequest req)
 * warnWrite (String clsname , String mtdname , String message , String id)
 * warnWrite (String clsname , String mtdname , String message)
 * warnWrite (String message)
 *
 *インフォ
 * infoWrite (String clsname , String mtdname , String message , HttpServletRequest req , String id)★
 * infoWrite (String clsname , String mtdname , String message , HttpServletRequest req)
 * infoWrite (String clsname , String mtdname , String message , String id)
 * infoWrite (String clsname , String mtdname , String message)
 * infoWrite (String message)
 *
 *デバッグ
 * debugWrite(String clsname , String mtdname , String message , String sql , String arg , HttpServletRequest req , String id)
 * debugWrite(String clsname , String mtdname , String message , String sql , String arg , HttpServletRequest req)
 * debugWrite(String clsname , String mtdname , String message , String sql , String arg , String id)
 * debugWrite(String clsname , String mtdname , String message , String sql , String arg)★
 * debugWrite(String clsname , String mtdname , String message)
 * debugWrite(String message)
 *
 */

	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 * @param id　ID情報
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex , String message , HttpServletRequest req , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//ユーザID
		log.error(idout(id));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.error(request[i]);
		}

		//メッセージ情報
		log.error(mesout(message));

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param req　httpリクエスト
	 * @param id　ID情報
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex , HttpServletRequest req , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//ユーザID
		log.error(idout(id));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.error(request[i]);
		}

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex , String message , HttpServletRequest req){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.error(request[i]);
		}

		//メッセージ情報
		log.error(mesout(message));

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param message　メッセージ
	 * @param id ID情報
	 * @param req　httpリクエスト
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex , String message , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//ユーザID
		log.error(idout(id));

		//メッセージ情報
		log.error(mesout(message));

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex , String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//メッセージ情報
		log.error(mesout(message));

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param ex　Exception
	 * @param req　httpリクエスト
	 */
	public static void errWrite(String clsname , String mtdname , Exception ex){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//クラス名・メソッド名
		log.error(clsmtdout(clsname,mtdname));

		//Exception情報
		log.error(exout(ex));
		if (extrace){
			ex.printStackTrace();
		}

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(ERROR)ログ出力
	 * @param message　メッセージ
	 */
	public static void errWrite(String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.error(header);
		}

		//メッセージ情報
		log.error(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.error(footer);
		}

	}


	/**
	 * 情報(WARN)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 * @param id　ID情報
	 */
	public static void warnWrite(String clsname , String mtdname , String message , HttpServletRequest req , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.warn(header);
		}

		//クラス名・メソッド名
		log.warn(clsmtdout(clsname,mtdname));

		//ユーザID
		log.warn(idout(id));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.warn(request[i]);
		}

		//メッセージ情報
		log.warn(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.warn(footer);
		}

	}


	/**
	 * 情報(WARN)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 */
	public static void warnWrite(String clsname , String mtdname , String message , HttpServletRequest req){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.warn(header);
		}

		//クラス名・メソッド名
		log.warn(clsmtdout(clsname,mtdname));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.warn(request[i]);
		}

		//メッセージ情報
		log.warn(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.warn(footer);
		}

	}


	/**
	 * 情報(WARN)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param id　ID情報
	 */
	public static void warnWrite(String clsname , String mtdname , String message , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.warn(header);
		}

		//クラス名・メソッド名
		log.warn(clsmtdout(clsname,mtdname));

		//ユーザID
		log.warn(idout(id));

		//メッセージ情報
		log.warn(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.warn(footer);
		}

	}


	/**
	 * 情報(WARN)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 */
	public static void warnWrite(String clsname , String mtdname , String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.warn(header);
		}

		//クラス名・メソッド名
		log.warn(clsmtdout(clsname,mtdname));

		//メッセージ情報
		log.warn(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.warn(footer);
		}

	}


	/**
	 * 情報(WARN)ログ出力
	 * @param message　メッセージ
	 */
	public static void warnWrite(String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.warn(header);
		}

		//メッセージ情報
		log.warn(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.warn(footer);
		}

	}


	/**
	 * 情報(INFO)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 * @param id　ID情報
	 */
	public static void infoWrite(String clsname , String mtdname , String message , HttpServletRequest req , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.info(header);
		}

		//クラス名・メソッド名
		log.info(clsmtdout(clsname,mtdname));

		//ユーザID
		log.info(idout(id));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.info(request[i]);
		}

		//メッセージ情報
		log.info(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.info(footer);
		}

	}


	/**
	 * 情報(INFO)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param req　httpリクエスト
	 */
	public static void infoWrite(String clsname , String mtdname , String message , HttpServletRequest req){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.info(header);
		}

		//クラス名・メソッド名
		log.info(clsmtdout(clsname,mtdname));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.info(request[i]);
		}

		//メッセージ情報
		log.info(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.info(footer);
		}

	}


	/**
	 * 情報(INFO)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param id　ID情報
	 */
	public static void infoWrite(String clsname , String mtdname , String message , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.info(header);
		}

		//クラス名・メソッド名
		log.info(clsmtdout(clsname,mtdname));

		//ユーザID
		log.info(idout(id));

		//メッセージ情報
		log.info(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.info(footer);
		}

	}


	/**
	 * 情報(INFO)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 */
	public static void infoWrite(String clsname , String mtdname , String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.info(header);
		}

		//クラス名・メソッド名
		log.info(clsmtdout(clsname,mtdname));

		//メッセージ情報
		log.info(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.info(footer);
		}

	}


	/**
	 * 情報(INFO)ログ出力
	 * @param message　メッセージ
	 */
	public static void infoWrite(String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.info(header);
		}

		//メッセージ情報
		log.info(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.info(footer);
		}

	}




	/**
	 * 情報(DEBUG)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param sql SQL文
	 * @param qrg SQL引数
	 * @param req　httpリクエスト
	 * @param id　ID情報
	 */
	public static void debugWrite(String clsname , String mtdname , String message , String sql , String arg , HttpServletRequest req , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//クラス名・メソッド名
		log.debug(clsmtdout(clsname,mtdname));

		//ユーザID
		log.debug(idout(id));

		//SQL
		log.debug(sqlout(sql));

		//引数
		log.debug(argout(arg));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.debug(request[i]);
		}

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	/**
	 * 情報(DEBUG)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param sql SQL文
	 * @param qrg SQL引数
	 * @param req　httpリクエスト
	 */
	public static void debugWrite(String clsname , String mtdname , String message , String sql , String arg , HttpServletRequest req){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//クラス名・メソッド名
		log.debug(clsmtdout(clsname,mtdname));

		//SQL
		log.debug(sqlout(sql));

		//引数
		log.debug(argout(arg));

		//リクエスト情報
		String[] request = reqout(req);

		for(int i=1; i<=7; i++){
			log.debug(request[i]);
		}

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	/**
	 * 情報(DEBUG)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param sql SQL文
	 * @param qrg SQL引数
	 * @param id　ID情報
	 */
	public static void debugWrite(String clsname , String mtdname , String message , String sql , String arg , String id){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//クラス名・メソッド名
		log.debug(clsmtdout(clsname,mtdname));

		//ユーザID
		log.debug(idout(id));

		//SQL
		log.debug(sqlout(sql));

		//引数
		log.debug(argout(arg));

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	/**
	 * 情報(DEBUG)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 * @param sql SQL文
	 * @param qrg SQL引数
	 */
	public static void debugWrite(String clsname , String mtdname , String message , String sql , String arg){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//クラス名・メソッド名
		log.debug(clsmtdout(clsname,mtdname));

		//SQL
		log.debug(sqlout(sql));

		//引数
		log.debug(argout(arg));

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	/**
	 * 情報(DEBUG)ログ出力
	 * @param clsname　クラス名
	 * @param mtdname　メソッド名
	 * @param message　メッセージ
	 */
	public static void debugWrite(String clsname , String mtdname , String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//クラス名・メソッド名
		log.debug(clsmtdout(clsname,mtdname));

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	/**
	 * 情報(DEBUG)ログ出力
	 * @param message　メッセージ
	 */
	public static void debugWrite(String message){

		//ヘッダー
		String header = headerout();
		if (headerles){
			log.debug(header);
		}

		//メッセージ情報
		log.debug(mesout(message));

		//フッター
		String footer = footerout();
		if (footerles){
			log.debug(footer);
		}

	}


	//★★★★★★★以下共通ログ出力メソッド★★★★★★★★★★★★★



	/**
	 * ヘッダー文字列作成
	 * @return String ヘッダー文字列
	 */

	private static String headerout() {

		String log = headerstr;

		return log;
	}


	/**
	 * フッター文字列作成
	 * @return String フッター文字列
	 */

	private static String footerout() {

		String log = footerstr;

		return log;
	}


	/**
	 * クラス名・メソッド名文字列作成
	 * @param clsname　クラス名
	 * @param message　メッセージ
	 * @return String クラス名・メソッド名文字列
	 */

	private static String clsmtdout(final String clsname, final String mtdname) {

		String log = headstr + " ClassName     [" + clsname + "]     MethodName[" + mtdname + "]";

		return log;
	}


	/**
	 * HTTPリクエスト文字列作成
	 * @param req　httpリクエスト
	 * @return String[] リクエスト文字列（配列）
	 */

	private static String[] reqout(final HttpServletRequest req) {

		String[] log ;
		log = new String[8];

		//log[1] = headstr + "  -- HTTP Request Infomation --";

		if(req.getSession(false)!=null){
			log[1] = headstr + " Session-ID    [" + req.getSession(false).getId() + "]";
		}else{
			log[1] = headstr + " Session-ID    [session none]";
		}
		log[2] = headstr + " Method        [" + req.getMethod() + "]";
		log[3] = headstr + " RequestURI    [" + req.getRequestURI() + "]";
		log[4] = headstr + " Referer       [" + req.getHeader("referer") + "]";
		log[5] = headstr + " RemoteAddr    [" + req.getRemoteAddr() + "]";
		log[6] = headstr + " RemoteHost    [" + req.getRemoteHost() + "]";
		log[7] = headstr + " User-Agent    [" + req.getHeader("User-Agent") + "]";

		//log[9] = headstr + "  -- ";

		return log;
	}


	/**
	 * Exception文字列作成
	 * @param ex　Exception
	 * @return String Exception文字列
	 */

	private static String exout(Exception ex) {

		String log = headstr + " Exceptiont    [" + ex +"]";

		return log;
	}


	/**
	 * ID文字列作成
	 * @param id　ID情報
	 * @return String ID情報文字列
	 */

	private static String idout(final String id) {

		String log = headstr +  " UserID[" + id + "]";

		return log;
	}


	/**
	 * メッセージ文字列作成
	 * @param mes　メッセージ
	 * @return String メッセージ情報文字列
	 */

	private static String mesout(final String mes) {

		String log = headstr +  " Message       [" + mes + "]";

		return log;
	}


	/**
	 * SQL文字列作成
	 * @param sql　メッセージ
	 * @return String SQL情報文字列
	 */

	private static String sqlout(final String sql) {

		String log = headstr +  " SQL           [" + sql + "]";

		return log;
	}

	/**
	 * SQL引数文字列作成
	 * @param arg　メッセージ
	 * @return String SQL引数情報文字列
	 */


	private static String argout(final String arg) {

		String log = headstr +  " Argument      [" + arg + "]";

		return log;
	}

	//★★★★★★★新規追加ログクラス　ここまで★★★★★★★★★★★★★
	//★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★





//
//	/**
//	 * 情報ログ出力
//	 * @param message　メッセージ
//	 * @param str　KEY項目
//	 */
//	public static void infoWrite(final String message, final String str) {
//
//		// ログ出力
//		headerInfo(message, str);
//		footerInfo();
//	}
//
//	/**
//	 * 情報ログ出力
//	 * @param message　メッセージ
//	 * @param str　KEY項目
//	 * @param req　リクエスト
//	 */
//	public static void infoWrite(
//		final String message,
//		final String str,
//		final HttpServletRequest req) {
//
//		// ログ出力
//		headerInfo(message, str);
//		reqoutInfo(req);
//		footerInfo();
//	}
//
//	/**
//	 * 情報ログ出力
//	 * @param message　メッセージ
//	 * @param str　KEY項目
//	 */
//	private static void headerInfo(final String message, final String str) {
//
//		infoOutput("@@@  Information @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//		infoOutput("@@@  Message     " + message);
//		infoOutput("@@@  ID          [" + str + "]");
//	}
//
//	/**
//	 * 情報ログ出力
//	 * @param req　リクエスト
//	 */
//	private static void reqoutInfo(final HttpServletRequest req) {
//
//		if (req.getSession(false) != null) {
//
//			infoOutput(
//				"@@@  Session-ID     [" + req.getSession(false).getId() + "]");
//
//		} else {
//
//			infoOutput("@@@  Session-ID     [session none]");
//
//		}
//
//		infoOutput("@@@  request infomation");
//
//		infoOutput("@@@   Method        [" + req.getMethod() + "]");
//
//		infoOutput("@@@   ContextPath   [" + req.getContextPath() + "]");
//
//		infoOutput("@@@   RequestURI    [" + req.getRequestURI() + "]");
//
//		infoOutput("@@@   Referer       [" + req.getHeader("referer") + "]");
//
//		infoOutput("@@@   QueryString   [" + req.getQueryString() + "]");
//
//		infoOutput(
//			"@@@   Authorization [" + req.getHeader("Authorization") + "]");
//
//		infoOutput("@@@   RemoteAddr    [" + req.getRemoteAddr() + "]");
//
//		infoOutput("@@@   RemoteHost    [" + req.getRemoteHost() + "]");
//
//		infoOutput("@@@   User-Agent    [" + req.getHeader("User-Agent") + "]");
//
//		infoOutput("@@@   From          [" + req.getHeader("From") + "]");
//
//		infoOutput("@@@   Server        [" + req.getHeader("Server") + "]");
//
//		infoOutput("@@@   Via           [" + req.getHeader("Via") + "]");
//
//	}
//
//	/**
//	 * 情報ログ出力フッター
//	 */
//	private static void footerInfo() {
//
//		infoOutput("@@@  by " + LOGGER);
//		infoOutput("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//	}
//
	/**
	 * エラーログ出力
	 * @param message　メッセージ
	 * @param ID　KEY項目
	 */
	public static void errorWrite(final String message, final String ID) {

		// ログ出力
		headerError(message, ID);
		footerError();
	}

	/**
	 * エラーログ出力
	 * @param message　メッセージ
	 * @param ID　KEY項目
	 * @param req　リクエスト
	 */
	public static void errorWrite(
		final String message,
		final String ID,
		final HttpServletRequest req) {

		// ログ出力
		headerError(message, ID);
		reqError(req);
		footerError();
	}

	/**
	 * エラーログ出力
	 * @param message　メッセージ
	 * @param ID　KEY項目
	 * @param ex　例外
	 */
	public static void errorWrite(
		final String message,
		final String ID,
		final Exception ex) {

		// ログ出力
		headerError(message, ID);
		exError(ex);
		footerError();

	}

	/**
	 * エラーログ出力
	 * @param message　メッセージ
	 * @param ID　KEY項目
	 * @param req　リクエスト
	 * @param ex　例外
	 */
	public static void errorWrite(
		final String message,
		final String ID,
		final HttpServletRequest req,
		final Exception ex) {

		//ログ出力
		headerError(message, ID);
		reqError(req);
		exError(ex);
		footerError();

	}

	/**
	 * メッセージ出力
	 * @param message　メッセージ
	 * @param ID　KEY項目
	 */
	private static void headerError(final String message, final String ID) {

		//ログ出力
		errorOutput("@@@  Error @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		errorOutput("@@@  Message     " + message);
		errorOutput("@@@  ID          [" + ID + "]");
	}

	/**
	 * リクエストログ出力
	 * @param req　リクエスト
	 */
	private static void reqError(final HttpServletRequest req) {

		if (req.getSession(false) != null) {

			errorOutput(
				"@@@  Session-ID     [" + req.getSession(false).getId() + "]");

		} else {

			errorOutput("@@@  Session-ID     [session none]");

		}

		errorOutput("@@@  request infomation");

		errorOutput("@@@   Method        [" + req.getMethod() + "]");

		errorOutput("@@@   ContextPath   [" + req.getContextPath() + "]");

		errorOutput("@@@   RequestURI    [" + req.getRequestURI() + "]");

		errorOutput("@@@   Referer       [" + req.getHeader("referer") + "]");

		errorOutput("@@@   QueryString   [" + req.getQueryString() + "]");

		errorOutput(
			"@@@   Authorization [" + req.getHeader("Authorization") + "]");

		errorOutput("@@@   RemoteAddr    [" + req.getRemoteAddr() + "]");

		errorOutput("@@@   RemoteHost    [" + req.getRemoteHost() + "]");

		errorOutput(
			"@@@   User-Agent    [" + req.getHeader("User-Agent") + "]");

		errorOutput("@@@   From          [" + req.getHeader("From") + "]");

		errorOutput("@@@   Server        [" + req.getHeader("Server") + "]");

		errorOutput("@@@   Via           [" + req.getHeader("Via") + "]");
	}

	/**
	 * エラーログ出力
	 * @param ex　例外
	 */
	private static void exError(final Exception ex) {

		errorOutput("@@@  Exception info");
		errorOutput("@@@   " + ex.toString());
		errorOutput("@@@   " + ex.getMessage());

		ex.printStackTrace();
	}

	/**
	 * ログ出力フッター
	 */
	private static void footerError() {

		errorOutput("@@@  by " + LOGGER);
		errorOutput("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}

//	/**
//	 * 文字列出力メソッド（trace）
//	 * @param str 値
//	 */
//	public static void traceOutput(final String str) {
//
//		if (log.isTraceEnabled()) {
//
//			log.trace(str);
//		}
//	}
//
//	/**
//	 * 文字列出力メソッド（trace）<BR>
//	 * フォーマットパターン > "str2 [str1]"
//	 * @param str1 値
//	 * @param str2 説明
//	 */
//	public static void traceOutput(final String str1, final String str2) {
//
//		if (log.isTraceEnabled()) {
//
//			log.trace(strFormat(str1, str2));
//		}
//	}
//
//	/**
//	 * char型出力メソッド（trace）
//	 * @param c 値
//	 */
//	public static void traceOutput(final char c) {
//
//		Character obj = new Character(c);
//		String str = obj.toString();
//
//		traceOutput(str);
//	}
//
//	/**
//	 * char型出力メソッド（trace）
//	 * フォーマットパターン > "str [c]"
//	 * @param c 値
//	 * @param str 説明
//	 */
//	public static void traceOutput(final char c, final String str) {
//
//		Character obj = new Character(c);
//		String wkStr = obj.toString();
//
//		traceOutput(wkStr, str);
//	}
//
//	/**
//	 * boolean型出力メソッド（trace）
//	 * @param flg 値
//	 */
//	public static void traceOutput(final boolean flg) {
//
//		Boolean obj = new Boolean(flg);
//		String str = obj.toString();
//
//		traceOutput(str);
//	}
//
//	/**
//	 * boolean型出力メソッド（trace）
//	 * フォーマットパターン > "str [flg]"
//	 * @param flg 値
//	 * @param str 説明
//	 */
//	public static void traceOutput(final boolean flg, final String str) {
//
//		Boolean obj = new Boolean(flg);
//		String wkStr = obj.toString();
//
//		traceOutput(wkStr, str);
//	}
//
//	/**
//	 * double型出力メソッド（trace）
//	 * @param d 値
//	 */
//	public static void traceOutput(final double d) {
//
//		String str = duble_format.format(d);
//
//		traceOutput(str);
//	}
//
//	/**
//	 * double型出力メソッド（trace）
//	 * フォーマットパターン > "str [d]"
//	 * @param d 値
//	 * @param str 説明
//	 */
//	public static void traceOutput(final double d, final String str) {
//
//		String wkStr = duble_format.format(d);
//
//		traceOutput(wkStr, str);
//	}

	/**
	 * 文字列出力メソッド（debug）
	 * @param str 値
	 */
	public static void debugOutput(final String str) {

		if (log.isDebugEnabled()) {

			log.debug(str);
		}
	}

	/**
	 * 文字列出力メソッド（debug）<BR>
	 * フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 */
	public static void debugOutput(final String str1, final String str2) {

		if (log.isDebugEnabled()) {

			log.debug(strFormat(str1, str2));
		}
	}

	/**
	 * char型出力メソッド（debug）
	 * @param c 値
	 */
	public static void debugOutput(final char c) {

		Character obj = new Character(c);
		String str = obj.toString();

		debugOutput(str);
	}

	/**
	 * char型出力メソッド（debug）
	 * フォーマットパターン > "str [c]"
	 * @param c 値
	 * @param str 説明
	 */
	public static void debugOutput(final char c, final String str) {

		Character obj = new Character(c);
		String wkStr = obj.toString();

		debugOutput(wkStr, str);
	}

	/**
	 * boolean型出力メソッド（debug）
	 * @param flg 値
	 */
	public static void debugOutput(final boolean flg) {

		Boolean obj = new Boolean(flg);
		String str = obj.toString();

		debugOutput(str);
	}

	/**
	 * boolean型出力メソッド（debug）
	 * フォーマットパターン > "str [flg]"
	 * @param flg 値
	 * @param str 説明
	 */
	public static void debugOutput(final boolean flg, final String str) {

		Boolean obj = new Boolean(flg);
		String wkStr = obj.toString();

		debugOutput(wkStr, str);
	}

	/**
	 * double型出力メソッド（debug）
	 * @param d 値
	 */
	public static void debugOutput(final double d) {

		String str = duble_format.format(d);

		debugOutput(str);
	}

	/**
	 * double型出力メソッド（debug）
	 * フォーマットパターン > "str [d]"
	 * @param d 値
	 * @param str 説明
	 */
	public static void debugOutput(final double d, final String str) {

		String wkStr = duble_format.format(d);

		debugOutput(wkStr, str);
	}

	/**
	 * 文字列出力メソッド（info）
	 * @param str 値
	 */
	public static void infoOutput(final String str) {

		if (log.isInfoEnabled()) {

			log.info(str);
		}
	}

	/**
	 * 文字列出力メソッド（info）<BR>
	 * フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 */
	public static void infoOutput(final String str1, final String str2) {

		if (log.isInfoEnabled()) {

			log.info(strFormat(str1, str2));
		}
	}

	/**
	 * char型出力メソッド（info）
	 * @param c 値
	 */
	public static void infoOutput(final char c) {

		Character obj = new Character(c);
		String str = obj.toString();

		infoOutput(str);
	}

	/**
	 * char型出力メソッド（info）
	 * フォーマットパターン > "str [c]"
	 * @param c 値
	 * @param str 説明
	 */
	public static void infoOutput(final char c, final String str) {

		Character obj = new Character(c);
		String wkStr = obj.toString();

		infoOutput(wkStr, str);
	}

	/**
	 * boolean型出力メソッド（info）
	 * @param flg 値
	 */
	public static void infoOutput(final boolean flg) {

		Boolean obj = new Boolean(flg);
		String str = obj.toString();

		infoOutput(str);
	}

	/**
	 * boolean型出力メソッド（info）
	 * フォーマットパターン > "str [flg]"
	 * @param flg 値
	 * @param str 説明
	 */
	public static void infoOutput(final boolean flg, final String str) {

		Boolean obj = new Boolean(flg);
		String wkStr = obj.toString();

		infoOutput(wkStr, str);
	}

	/**
	 * double型出力メソッド（info）
	 * @param d 値
	 */
	public static void infoOutput(final double d) {

		String str = duble_format.format(d);

		infoOutput(str);
	}

	/**
	 * double型出力メソッド（info）
	 * フォーマットパターン > "str [d]"
	 * @param d 値
	 * @param str 説明
	 */
	public static void infoOutput(final double d, final String str) {

		String wkStr = duble_format.format(d);

		infoOutput(wkStr, str);
	}

	/**
	 * 文字列出力メソッド（warn）
	 * @param str 値
	 */
	public static void warnOutput(final String str) {

		if (log.isWarnEnabled()) {

			log.warn(str);
		}
	}

	/**
	 * 文字列出力メソッド（warn）<BR>
	 * フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 */
	public static void warnOutput(final String str1, final String str2) {

		if (log.isWarnEnabled()) {

			log.warn(strFormat(str1, str2));
		}
	}

	/**
	 * char型出力メソッド（warn）
	 * @param c 値
	 */
	public static void warnOutput(final char c) {

		Character obj = new Character(c);
		String str = obj.toString();

		warnOutput(str);
	}

	/**
	 * char型出力メソッド（warn）
	 * フォーマットパターン > "str [c]"
	 * @param c 値
	 * @param str 説明
	 */
	public static void warnOutput(final char c, final String str) {

		Character obj = new Character(c);
		String wkStr = obj.toString();

		warnOutput(wkStr, str);
	}

	/**
	 * boolean型出力メソッド（warn）
	 * @param flg 値
	 */
	public static void warnOutput(final boolean flg) {

		Boolean obj = new Boolean(flg);
		String str = obj.toString();

		warnOutput(str);
	}

	/**
	 * boolean型出力メソッド（warn）
	 * フォーマットパターン > "str [flg]"
	 * @param flg 値
	 * @param str 説明
	 */
	public static void warnOutput(final boolean flg, final String str) {

		Boolean obj = new Boolean(flg);
		String wkStr = obj.toString();

		warnOutput(wkStr, str);
	}

	/**
	 * double型出力メソッド（warn）
	 * @param d 値
	 */
	public static void warnOutput(final double d) {

		String str = duble_format.format(d);

		warnOutput(str);
	}

	/**
	 * double型出力メソッド（warn）
	 * フォーマットパターン > "str [d]"
	 * @param d 値
	 * @param str 説明
	 */
	public static void warnOutput(final double d, final String str) {

		String wkStr = duble_format.format(d);

		warnOutput(wkStr, str);
	}

	/**
	 * 文字列出力メソッド（error）
	 * @param str 値
	 */
	public static void errorOutput(final String str) {

		if (log.isErrorEnabled()) {

			log.error(str);
		}
	}

	/**
	 * 文字列出力メソッド（error）<BR>
	 * フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 */
	public static void errorOutput(final String str1, final String str2) {

		if (log.isErrorEnabled()) {

			log.error(strFormat(str1, str2));
		}
	}

	/**
	 * char型出力メソッド（error）
	 * @param c 値
	 */
	public static void errorOutput(final char c) {

		Character obj = new Character(c);
		String str = obj.toString();

		errorOutput(str);
	}

	/**
	 * char型出力メソッド（error）
	 * フォーマットパターン > "str [c]"
	 * @param c 値
	 * @param str 説明
	 */
	public static void errorOutput(final char c, final String str) {

		Character obj = new Character(c);
		String wkStr = obj.toString();

		errorOutput(wkStr, str);
	}

	/**
	 * boolean型出力メソッド（error）
	 * @param flg 値
	 */
	public static void errorOutput(final boolean flg) {

		Boolean obj = new Boolean(flg);
		String str = obj.toString();

		errorOutput(str);
	}

	/**
	 * boolean型出力メソッド（error）
	 * フォーマットパターン > "str [flg]"
	 * @param flg 値
	 * @param str 説明
	 */
	public static void errorOutput(final boolean flg, final String str) {

		Boolean obj = new Boolean(flg);
		String wkStr = obj.toString();

		errorOutput(wkStr, str);
	}

	/**
	 * double型出力メソッド（error）
	 * @param d 値
	 */
	public static void errorOutput(final double d) {

		String str = duble_format.format(d);

		errorOutput(str);
	}

	/**
	 * double型出力メソッド（error）
	 * フォーマットパターン > "str [d]"
	 * @param d 値
	 * @param str 説明
	 */
	public static void errorOutput(final double d, final String str) {

		String wkStr = duble_format.format(d);

		errorOutput(wkStr, str);
	}

	/**
	 * 文字列出力メソッド（fatal）
	 * @param str 値
	 */
	public static void fatalOutput(final String str) {

		if (log.isFatalEnabled()) {

			log.fatal(str);
		}
	}

	/**
	 * 文字列出力メソッド（fatal）<BR>
	 * フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 */
	public static void fatalOutput(final String str1, final String str2) {

		if (log.isFatalEnabled()) {

			log.fatal(strFormat(str1, str2));
		}
	}

	/**
	 * char型出力メソッド（fatal）
	 * @param c 値
	 */
	public static void fatalOutput(final char c) {

		Character obj = new Character(c);
		String str = obj.toString();

		fatalOutput(str);
	}

	/**
	 * char型出力メソッド（fatal）
	 * フォーマットパターン > "str [c]"
	 * @param c 値
	 * @param str 説明
	 */
	public static void fatalOutput(final char c, final String str) {

		Character obj = new Character(c);
		String wkStr = obj.toString();

		fatalOutput(wkStr, str);
	}

	/**
	 * boolean型出力メソッド（fatal）
	 * @param flg 値
	 */
	public static void fatalOutput(final boolean flg) {

		Boolean obj = new Boolean(flg);
		String str = obj.toString();

		fatalOutput(str);
	}

	/**
	 * boolean型出力メソッド（fatal）
	 * フォーマットパターン > "str [flg]"
	 * @param flg 値
	 * @param str 説明
	 */
	public static void fatalOutput(final boolean flg, final String str) {

		Boolean obj = new Boolean(flg);
		String wkStr = obj.toString();

		fatalOutput(wkStr, str);
	}

	/**
	 * double型出力メソッド（fatal）
	 * @param d 値
	 */
	public static void fatalOutput(final double d) {

		String str = duble_format.format(d);

		fatalOutput(str);
	}

	/**
	 * double型出力メソッド（fatal）
	 * フォーマットパターン > "str [d]"
	 * @param d 値
	 * @param str 説明
	 */
	public static void fatalOutput(final double d, final String str) {

		String wkStr = duble_format.format(d);

		fatalOutput(wkStr, str);
	}

	/**
	 * 文字列フォーマットパターン > "str2 [str1]"
	 * @param str1 値
	 * @param str2 説明
	 * @return String 編集後文字列
	 */
	private static String strFormat(final String str1, final String str2) {

		StringBuffer sb = new StringBuffer("");
		sb.append(str2 + " [ " + str1 + " ]");

		return sb.toString();
	}

}