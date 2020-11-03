package jp.co.nii.miw.presentation.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.nii.miw.business.service.mypage.MypageJoho;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.utility.PropertyUtility;

/**
 * <p>タイトル: フィルタクラス</p>
 * <p>説明: 期間以内かのチェックを行う</p>
 * <p>著作権: Copyright (c) 2010</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author T.Yamaguchi
 */
public class MypUpdKikanCheckFilter implements Filter {

    /** FilterConfig */
    private FilterConfig filterConfig;
    /**業務コード */
    static final String BUSINESS_CODE = PropertyUtility.getProperty("business_code");
    /** 画面URL関連プロパティ（期間外エラーURL）取得 */
    private static final String KIKANERR_URL = PropertyUtility.getProperty(BUSINESS_CODE + "kikan_err_url");

    /**
     * 期間内かのチェック
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
        //セッションの取得
        HttpSession session = httpServletRequest.getSession(false);

        try {
            //保持した情報を取得する
            MypageJoho mypageJoho = (MypageJoho) session.getAttribute("MypageJoho");

            if (!mypageJoho.getIsKikanUpd()) {
                //更新可能期間ではない
                LogGenerate.errorOutput(
                        this.getClass().getName() + "　更新可能期間内ではありません", "none");

                //セッションを切る
                httpServletRequest.getSession(true).invalidate();

                // sendRedirectメソッドの中で使えるよう、URLをエンコード
                String encodedURL =
                        httpServletResponse.encodeRedirectURL(KIKANERR_URL);

                // 指定されたURLを送信
                httpServletResponse.sendRedirect(encodedURL);
            } else {
                // チェーン内の次のFilter、あるいはチェーンの最後のリソースを呼び出す
                fc.doFilter(req, res);
            }

        } catch (Exception e) {
            fc.doFilter(req, res);
        }
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
     * @param arg0 FilterConfig
     * @throws ServletException err
     */
    public final void init(final FilterConfig arg0) throws ServletException {
    }

    /**
     * javax.servlet.Filter 内のdestroy
     */
    public final void destroy() {
    }
}