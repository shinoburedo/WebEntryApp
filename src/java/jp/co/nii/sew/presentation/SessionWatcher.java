package jp.co.nii.sew.presentation;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * セッション監視
 * @author n-machida
 */
//@WebListener でもできるが、web.xmlにまとめた
public class SessionWatcher implements HttpSessionListener {
    private static int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent ev) {
        HttpSession session = ev.getSession();
        sessionCount++;
        LogGenerate.infoOutput("セッションを生成します SESSION ID=" + session.getId()
                + " / 現在のセッション数=" + sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent ev) {
        HttpSession session = ev.getSession();
        sessionCount--;
        LogGenerate.infoOutput("セッションを破棄します SESSION ID=" + session.getId()
                + " / 現在のセッション数=" + sessionCount);
    }
}