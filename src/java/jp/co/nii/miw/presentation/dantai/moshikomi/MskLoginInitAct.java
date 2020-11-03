package jp.co.nii.miw.presentation.dantai.moshikomi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.sew.presentation.AbstractAction;

/**
 * タイトル:MskLoginInitAct
 * 説明:MskLoginInitActのアクションクラス
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author r-ehara
 */
public class MskLoginInitAct extends AbstractAction {

    @Override
    protected String doProcess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッション作成
        HttpSession session = request.getSession(true);
        // 返却するフォワード名
        String ret = "";
        // form
        MskLoginFrm mskLoginFrm = (MskLoginFrm) form;
        // 申込情報
        MskToroku mskTorokuJoho = new MskToroku();

        // 年度,期を取得
        MiaServ miaServ = new MiaServ();
        MenuControl menuControl = miaServ.selectNendoKaisu(MiwConstants.MENU_CODE_DNT_MSK);
        String nendo = menuControl.getNendo();
        String ki = menuControl.getKaisu();

        mskTorokuJoho.setNendo(nendo);
        mskTorokuJoho.setKaisu(ki);

        //サービスクラス作成
        MskTorokuServ mskTorokuServ = new MskTorokuServ();

        //各日付項目の取得
        //session-timeout時間を取得
        int intervalTime = session.getMaxInactiveInterval();
        mskTorokuJoho = mskTorokuServ.setShutsuganDateDantai(mskTorokuJoho, intervalTime);

        String[] mypageKigen = mskTorokuServ.getMypageKigenDantai(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu());
//        // TODO 団体で使うのか？
//        String jukenhyoHassoYoteibiKokunai = mskTorokuServ.getJukenhyoHassoYoteibiKokunai(mskTorokuJoho.getNendo(), mskTorokuJoho.getKaisu());

        mskTorokuJoho.setMypageKigen(mypageKigen[0]);
        mskTorokuJoho.setMypageKigenTime(mypageKigen[1]);
//        mskTorokuJoho.setJukenhyoHassobiKokunai(jukenhyoHassoYoteibiKokunai);

        session.setAttribute("MskTorokuJoho", mskTorokuJoho);
        // メニュー区分をセット
        if ("1".equals(request.getParameter("param"))) {
            session.setAttribute("menuUrl", "../MenuMoshikomishaAct.do");  // 申込者メインメニューより
        } else if ("2".equals(request.getParameter("param"))) {
            session.setAttribute("menuUrl", "../MenuDaihyoshaAct.do");  // 代表者メインメニューより
        }
//        // ログインへ戻る場合のUrlをセット
//        session.setAttribute("loginUrl", "../../dantai/Msk/MskLoginInitAct.do?param=" + request.getParameter("param"));

        ret = FWD_NM_SUCCESS;

        return ret;

    }
}