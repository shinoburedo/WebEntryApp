package jp.co.nii.sew.presentation;

import java.util.Enumeration;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jp.co.nii.sew.utility.LogGenerate;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action抽象クラス
 * <dd>備考：業務アクションの呼び出しを実装します。</dd>
 * @author n-ikezawa
 */
public abstract class AbstractAction extends Action {

    /** ログに出力するクラス名 */
    protected static String CLASS_NAME;

    public AbstractAction() {
        CLASS_NAME = this.getClass().getName();
    }
    /**
     * フォワード名（正常）
     */
    protected static final String FWD_NM_SUCCESS = "success";
    /**
     * フォワード名（再表示）
     */
    protected static final String FWD_NM_RELOAD = "reload";
    /**
     * フォワード名（クレジットカード決済）
     */
    protected static final String FWD_NM_CARD = "card";
    /**
     * フォワード名（コンビニ決済）
     */
    protected static final String FWD_NM_CONVENI = "conveni";
    /**
     * フォワード名（ペイジー決済）
     */
    protected static final String FWD_NM_PAYEASY = "payeasy";
    /**
     * フォワード名（アプリケーション例外発生時）
     */
    protected static final String FWD_NM_ERROR = "error";
    /**
     * フォワード名（アプリケーション例外 決済）
     */
    protected static final String FWD_NM_ERROR_DC = "errorDC";
    /**
     * フォワード名（全画面に戻る）
     */
    protected static final String FWD_NM_BACK = "back";
    /**
     * フォワード名（不正遷移やトークン）
     */
    protected static final String FWD_NM_SESSION = "session";
    /**
     * フォワード名（システム例外発生時）
     */
    protected static final String FWD_NM_EXCEPTION = "exception";
    /**
     * フォワード名（期間外エラー）
     */
    protected static final String FWD_NM_KIKAN = "kikan";
    /**
     * フォワード名（二重登録）
     */
    protected static final String FWD_NM_DOUBLE = "double";
    /**
     * フォワード名（申込情報更新失敗）
     */
    protected static final String FWD_NM_ERROR_UD = "update_error";
    /**
     * フォワード名（画像再提出）
     */
    protected static final String FWD_NW_REVISE = "revise";
    /**
     * フォワード名（ログアウト）
     */
    protected static final String FWD_NW_LOGOUT = "logout";
    
    /**
     * <dd>メソッド名：アクション処理。</dd>
     * <dd>メソッド説明：業務処理の呼び出しを実装する。</dd>
     * <dd>備考：業務アクションの枠組みを定義しています。
     * 業務処理の実装は、doProcessをオーバーライドして下さい。</dd>
     * @param mapping 画面制御マッピング
     * @param form 画面項目フォーム
     * @param request Httpサーブレットリクエスト
     * @param response Httpサーブレットレスポンス
     * @return ActionForward 画面制御フォワード
     * @exception Exception
     * SEWExceptionが投げられた場合、または予期せぬ例外が投げられた場合
     */
    @Override
    public final ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        //業務ロジック結果格納用
        String result = null;
        result = doPreProcess(mapping, form, request, response);

        ActionForward actionFW = null;

        try {
            if (result == null) {
                result = doProcess(mapping, form, request, response);
                doPostProcess(mapping, form, request, response);
            }

        } catch (Exception ex) {
            LogGenerate.errWrite(CLASS_NAME, "execute", ex, request, "");
            //セッション破棄
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            result = FWD_NM_EXCEPTION;
        }
        // 結果により画面遷移情報をActionへ返却
        actionFW = mapping.findForward(result);
        return actionFW;

    }

    /**
     * <dd>メソッド名：業務前処理。</dd>
     * <dd>メソッド説明：業務処理の前処理を実装します。</dd>
     * <dd>備考：</dd>
     * @param mapping 画面制御マッピング
     * @param form 画面項目フォーム
     * @param request Httpサーブレットリクエスト
     * @param response Httpサーブレットレスポンス
     * @return String 画面制御フォワード名
     * @exception Exception
     * SEWExceptionが投げられた場合、または予期せぬ例外が投げられた場合
     */
    protected String doPreProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        dumpRequest(request);

        //業務プロセスを実行したくないとき、フォワード名を返す。
        return null;
    }

    private void dumpRequest(HttpServletRequest request) {

        StringBuffer sb = request.getRequestURL();
        sb.append(", JSESSIONID = ");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("JSESSIONID".equals(cookies[i].getName())) {
                    sb.append(cookies[i].getValue());
                    i = +cookies.length;
                }
            }
        }


        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            sb.append(", ");
            String paramName = paramNames.nextElement();
            sb.append(paramName);
            sb.append(" = ");
            sb.append(request.getParameter(paramName));

        }

        LogGenerate.infoOutput(sb.toString());
    }

    /**
     * <dd>メソッド名：業務アクション。</dd>
     * <dd>メソッド説明：必要な業務アクションを業務サブクラスでオーバーライドして下さい。</dd>
     * <dd>備考：</dd>
     * @param mapping 画面制御マッピング
     * @param form 画面項目フォーム
     * @param request Httpサーブレットリクエスト
     * @param response Httpサーブレットレスポンス
     * @return String 画面制御フォワード名
     * @exception Exception
     * SEWExceptionが投げられた場合、または予期せぬ例外が投げられた場合
     */
    protected abstract String doProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * <dd>メソッド名：業務後処理。</dd>
     * <dd>メソッド説明：業務処理の後処理を実装します。</dd>
     * <dd>備考：</dd>
     * @param mapping 画面制御マッピング
     * @param form 画面項目フォーム
     * @param request Httpサーブレットリクエスト
     * @param response Httpサーブレットレスポンス
     * @exception Exception
     * SEWExceptionが投げられた場合、または予期せぬ例外が投げられた場合
     */
    private void doPostProcess(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        dumpEndOfDoProcess(request);

    }

    private void dumpEndOfDoProcess(HttpServletRequest request) {
        StringBuffer sb = request.getRequestURL();
        sb.append(", JSESSIONID = ");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("JSESSIONID".equals(cookies[i].getName())) {
                    sb.append(cookies[i].getValue());
                    i = +cookies.length;
                }
            }
        }

        sb.append(" doProcess(...) normally ended.");

        LogGenerate.infoOutput(sb.toString());
    }
}