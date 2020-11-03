package jp.co.nii.miw.presentation.dantai.info;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwConstants;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>
 * タイトル:DntInfMskListFrm
 * </p>
 * <p>
 * 説明:DntInfMskListのフォームクラス
 * </p>
 * <p>
 * 著作権: Copyright (c) 2012
 * </p>
 * <p>
 * 会社名: 日本情報産業株式会社
 * </p>
 *
 * @author --r.ehara
 */
public class DntInfMskListFrm extends DynaActionForm {

    @Override
    public void initialize(ActionMapping mapping) {
        super.initialize(mapping);
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        try {
            ArrayList sortCode = new ArrayList();
            sortCode.add(new LabelValueBean("標準", MiwConstants.SORT_CODE_DEFAULT));
            sortCode.add(new LabelValueBean("カナ氏名：昇順", MiwConstants.SORT_CODE_KANA_ASC));
            sortCode.add(new LabelValueBean("カナ氏名：降順", MiwConstants.SORT_CODE_KANA_DESC));
            request.setAttribute("sortCodes", sortCode);
        } catch (Exception e) {
        }
    }
}
