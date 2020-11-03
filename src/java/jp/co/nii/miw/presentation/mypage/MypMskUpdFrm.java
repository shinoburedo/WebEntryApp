package jp.co.nii.miw.presentation.mypage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.service.moshikomi.MskTorokuServ;
import jp.co.nii.miw.business.service.mypage.MypMskInf;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 * <p>
 * タイトル:MypMskInf
 * </p>
 * <p>
 * 説明:MypMskInfのフォームクラス
 * </p>
 * <p>
 * 著作権: Copyright (c) 2011
 * </p>
 * <p>
 * 会社名: 日本情報産業株式会社
 * </p>
 *
 * @author --t.yamaguchi
 */
public class MypMskUpdFrm extends DynaActionForm {

    @Override
    public void initialize(ActionMapping mapping) {
        super.initialize(mapping);
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        //エンティティの内容を取得
        MypMskInf moshikomiJoho = (MypMskInf) session.getAttribute("MoshikomiJoho");
        String nendo = moshikomiJoho.getNendo().toString();
        String kaisu = moshikomiJoho.getKaisu().toString();
        try {


            List eventCodeList = new LinkedList();
            List sexCodeList = new LinkedList();
            List shikenchiList = new LinkedList();
            List birthEraList = new LinkedList();
            List todofukenList = new LinkedList();
            List passwdQuestionCode = new LinkedList();

//            ArrayList shikenchi1 = new ArrayList();
//            ArrayList shikenchi2 = new ArrayList();
//            ArrayList shokugyoCode = new ArrayList();
//            ArrayList gakkoKbn = new ArrayList();
//            ArrayList passwdQuestionCode = new ArrayList();

            //受験科目
            for (int i = 0; i < MiwConstants.DISP_JUKEN_KAMOKU.length; i++) {
                eventCodeList.add(new LabelValueBean(MiwConstants.DISP_JUKEN_KAMOKU[i][1], MiwConstants.DISP_JUKEN_KAMOKU[i][0]));
            }

            //受験地リスト作成
            MskTorokuServ mskTorokuServ = new MskTorokuServ();
            shikenchiList = mskTorokuServ.selectShikenchi(nendo, kaisu);
            
            // 性別
            for (int i = 0; i < MiwConstants.DISP_SEX_CODE.length; i++) {
                sexCodeList.add(new LabelValueBean(MiwConstants.DISP_SEX_CODE[i][1], MiwConstants.DISP_SEX_CODE[i][0]));
            }

            // 生年月日元号
            for (int i = 0; i < MiwConstants.DISP_BIRTHDAY_ERA_COD.length; i++) {
                birthEraList.add(new LabelValueBean(MiwConstants.DISP_BIRTHDAY_ERA_COD[i][1], MiwConstants.DISP_BIRTHDAY_ERA_COD[i][0]));
            }

            //都道府県
//            todofukenList.add(new LabelValueBean("お選びください", ""));
            for (int i = 0; i < MiwConstants.DISP_TODOFUKEN_CODE.length; i++) {
                todofukenList.add(new LabelValueBean(MiwConstants.DISP_TODOFUKEN_CODE[i][1], MiwConstants.DISP_TODOFUKEN_CODE[i][0]));
            }

            for (int i = 0; i < MiwConstants.DISP_PASSWD_QUESTION_COD.length; i++) {
                passwdQuestionCode.add(new LabelValueBean(MiwConstants.DISP_PASSWD_QUESTION_COD[i][1],
                        MiwConstants.DISP_PASSWD_QUESTION_COD[i][0]));
            }


            request.setAttribute("EventCodeList", eventCodeList);
            request.setAttribute("ShikenchiList", shikenchiList);
            request.setAttribute("SexCodeList", sexCodeList);
            request.setAttribute("BirthEraList", birthEraList);
            request.setAttribute("TodofukenList", todofukenList);
            request.setAttribute("passwdQuestionCode", passwdQuestionCode);

        } catch (Exception e) {
        }
    }
}
