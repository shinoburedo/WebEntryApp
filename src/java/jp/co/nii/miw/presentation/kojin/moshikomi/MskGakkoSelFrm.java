package jp.co.nii.miw.presentation.kojin.moshikomi;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;


/**
 * <p>タイトル: </p>
 * <p>説明: </p>
 * <p>著作権: Copyright (c) 2010</p>
 * <p>会社名: 日本情報産業株式会社</p>
 * @author --
 */
public class MskGakkoSelFrm extends DynaActionForm {

    private ArrayList gakkoList;

    public ArrayList getGakkoList() {
        return gakkoList;
    }

    public void setGakkoList(final ArrayList yubinList) {
        this.gakkoList = yubinList;
    }
    
    /**
     * セレクトボックス選択値をセットする のをここでやる
     * @param ActionMapping
     * @param HttpServletRequest
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        
    }
}

