package jp.co.nii.miw.presentation.mypage;


import jp.co.nii.miw.presentation.kojin.moshikomi.*;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwConstants;


import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>
 * タイトル:MypPswRemFrm
 * </p>
 * <p>
 * 説明:MypPswRemのフォームクラス
 * </p>
 * <p>
 * 著作権: Copyright (c) 2012
 * </p>
 * <p>
 * 会社名: 日本情報産業株式会社
 * </p>
 *
 * @author k-hirao
 */
public class MypPswRemFrm extends DynaActionForm {

    /**
     * セレクトボックス選択値をセットする のをここでやる
     * @param ActionMapping
     * @param HttpServletRequest
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
        List birthEraList = new LinkedList();
        //生年月日年号
        birthEraList.add(new LabelValueBean("お選びください", ""));
        for (int i = 0; i < MiwConstants.DISP_BIRTHDAY_ERA_COD.length; i++) {
            birthEraList.add(new LabelValueBean(MiwConstants.DISP_BIRTHDAY_ERA_COD[i][1], MiwConstants.DISP_BIRTHDAY_ERA_COD[i][0]));
        }
        request.setAttribute("BirthEraList", birthEraList);
        
    }
}
