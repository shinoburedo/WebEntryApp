package jp.co.nii.miw.presentation.dantai.moshikomi;


import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import jp.co.nii.miw.business.MiwConstants;


import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>
 * タイトル:DntInpFrm
 * </p>
 * <p>
 * 説明:DntInpのフォームクラス
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
public class DntInpFrm extends DynaActionForm {

    /**
     * セレクトボックス選択値をセットする のをここでやる
     * @param ActionMapping
     * @param HttpServletRequest
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        // 都道府県
        List todofukenList = new LinkedList();
        todofukenList.add(new LabelValueBean("都道府県を選択", ""));
        for (int i = 0; i < MiwConstants.DISP_TODOFUKEN_CODE.length; i++) {
            todofukenList.add(new LabelValueBean(MiwConstants.DISP_TODOFUKEN_CODE[i][1], MiwConstants.DISP_TODOFUKEN_CODE[i][0]));
        }
        request.setAttribute("TodofukenList", todofukenList);
        
        // 団体パスワード確認用の質問
        List pswdConfList = new LinkedList();
        pswdConfList.add(new LabelValueBean("お選びください",""));
        for (int i = 0; i < MiwConstants.DISP_PASSWD_QUESTION_COD.length; i++) {
            pswdConfList.add(new LabelValueBean(MiwConstants.DISP_PASSWD_QUESTION_COD[i][1], MiwConstants.DISP_PASSWD_QUESTION_COD[i][0]));
        }
        request.setAttribute("PswdConfList", pswdConfList);
        
        
    }
}
