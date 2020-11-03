package jp.co.nii.miw.presentation.dantai.info;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * <p>
 * タイトル:DntInfLoginFrm
 * </p>
 * <p>
 * 説明:DntInfLoginのフォームクラス
 * </p>
 * <p>
 * 著作権: Copyright (c) 2012
 * </p>
 * <p>
 * 会社名: 日本情報産業株式会社
 * </p>
 *
 * @author r-ehara
 */
public class DntInfLoginFrm extends DynaActionForm {

    /**
     * セレクトボックス選択値をセットする のをここでやる
     * @param ActionMapping
     * @param HttpServletRequest
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    }
}
