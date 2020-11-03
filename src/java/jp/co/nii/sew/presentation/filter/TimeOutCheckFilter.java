package jp.co.nii.sew.presentation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>タイトル: フィルタクラス</p>
 * <p>説明: セッションタイムアウトのチェックを行う</p>
 * <p>著作権: Copyright (c) 2010</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author T.Yamaguchi
 */
public class TimeOutCheckFilter implements Filter {

    /** FilterConfig */
    private FilterConfig filterConfig;
    /** 対象除外URL */
    private String[] excludes;
    /**業務コード */
    static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    /** 画面URL関連プロパティ（タイムアウトURL）取得 */
    private static final String TIME_OUT_URL = PropertyUtility.getProperty(BUSINESS_CODE + "timeout_url");

    /**
     * セッションタイムアウトのチェック
     * @param req ServletRequest
     * @param res ServletResponse
     * @param fc FilterChain
     * @throws IOException err
     * @throws ServletException err
     */
    public final void doFilter(final ServletRequest req,
            final ServletResponse res,
            final FilterChain fc)
            throws IOException, ServletException {

        // リクエストの取得
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        // レスポンスの取得
        HttpServletResponse httpServletResponse = (HttpServletResponse) res;

        // JSESSIONIDの取得
        String sessionIdInRequest = "";
        Cookie[] cookies =httpServletRequest.getCookies();
        if(cookies != null){
            for(int i=0; i < cookies.length; i++){
                if("JSESSIONID".equals(cookies[i].getName())){
                    sessionIdInRequest = cookies[i].getValue();
                    i =+ cookies.length;
                }
            }
        }
        
        // URLの取得
        String thisURL = httpServletRequest.getServletPath();
        // チェック対象のURLか
        if (!isExcludePath(thisURL)) {
            // 取得したリクエストに関連づけられているセッションがない時、
            // セッションタイムアウト
            if (httpServletRequest.getSession(false) == null) {

                LogGenerate.errorOutput("セッションがありません JSESSIONID = " + sessionIdInRequest + "　URL = " + httpServletRequest.getRequestURL(),
                        this.getClass().getName());
                
                // sendRedirectメソッドの中で使えるよう、timeOutUrlをエンコード
                String encodedURL =
                        httpServletResponse.encodeRedirectURL(TIME_OUT_URL);

                // 指定されたURLを送信
                httpServletResponse.sendRedirect(encodedURL);

            } else {

                LogGenerate.infoOutput("セッションがありました JSESSIONID = " + sessionIdInRequest,                        
                        this.getClass().getName());
                
                // チェーン内の次のFilter、あるいはチェーンの最後のリソースを呼び出す
                fc.doFilter(req, res);
            }
        } else {
            //Filterチェック対象外のURL
            // チェーン内の次のFilter、あるいはチェーンの最後のリソースを呼び出す
            fc.doFilter(req, res);
        }
    }

    /**
     * リクエストされたURLが除外対象か判断する。 除外対象の場合trueを返す
     *
     * @param thisURL
     * @return
     */
    private boolean isExcludePath(String thisURL) {
        String[] excludes = this.excludes;
        for (String path : excludes) {
            // 除外対象パスの最後が「*」の場合、indexOfで含まれるか確認
            if (path.indexOf("*") == path.length() - 1) {
                if (thisURL.indexOf(path.substring(0, path.length() - 2)) >= 0) {
                    return true;
                }
            } else {
                // 上記以外は、完全一致
                if (thisURL.equals(path)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * filterConfigを取得する。
     * @return FilterConfig
     */
    public final FilterConfig getFilterConfig() {

        return this.filterConfig;
    }

    /**
     * filterConfigを設定する。
     * @param cfg FilterConfig
     */
    public final void setFilterConfig(final FilterConfig cfg) {

        this.filterConfig = cfg;
    }

    /**
     * javax.servlet.Filter 内のinit
     *
     * @param arg0
     *            FilterConfig
     * @throws ServletException
     *             err
     */
    @Override
    public final void init(final FilterConfig arg0) throws ServletException {
        String exclude = arg0.getInitParameter("exclude");
        if (exclude == null)
            return;
        this.excludes = exclude.split(",");
    }

    /**
     * javax.servlet.Filter 内のdestroy
     */
    @Override
    public final void destroy() {
    }
}