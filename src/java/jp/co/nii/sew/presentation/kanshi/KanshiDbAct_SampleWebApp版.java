package jp.co.nii.sew.presentation.kanshi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.sew.business.domain.kanshi.Kanshi;

import org.apache.struts.action.*;

/**
 * タイトル: Java監視DBチェックアクション
 * 説明:
 * 著作権:   Copyright (c) 2012
 * 会社名:   日本情報産業株式会社
 * @author n-ikezawa
 */
public class KanshiDbAct_SampleWebApp版 extends Action {
    
	/**
	* 各処理を呼び出し、その結果によって遷移先を決定する
	* @param mapping マッピング
	* @param form フォーム
	* @param request リクエスト
	* @param response レスポンス
	* @return ActionForward 遷移先<BR>
	*/
	public ActionForward execute(
		final ActionMapping mapping,
		final ActionForm form,
		final HttpServletRequest request,
		final HttpServletResponse response) {

		ActionForward actionFW = null;

		try {
            Kanshi kanshi = new Kanshi(MiwConstants.DS_REGISTRANT);
            if (kanshi.checkDb()){
                //DB接続成功
                actionFW = mapping.findForward("success");
            } else {
                //DB接続失敗
                actionFW = mapping.findForward("exception");
            }

		} catch (Exception ex) {
			actionFW = mapping.findForward("exception");
		}

		return actionFW;
	}    

}