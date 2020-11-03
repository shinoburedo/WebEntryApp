package jp.co.nii.miw.business.service.dantai;

import java.util.ArrayList;
import java.util.List;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.service.moshikomi.MskToroku;
import jp.co.nii.sew.utility.CheckUtility;
import jp.co.nii.sew.utility.StringUtility;
import org.apache.struts.action.ActionMessages;

/**
 * タイトル: MskSearch
 * 説明: 申込者検索のデータbean
 * 著作権: Copyright (c) 2011
 * 会社名: 日本情報産業株式会社
 * @author t.yamaguchi
 */
public class DntInfSearch {

    ////////////////////////////////////////////
    //情報を格納するフィールド・メソッド
    ///////////////////////////////////////////
    /** 申込情報 */
    private List<MskToroku> mskTorokuBeanList;
    /** 並び順コード*/
    private String sortCode;
    /** 検索結果件数 */
    private int schResuCount;
    /** 現在ページ数 */
    private int nowPage;
    /** 検索日時 */
    private String searchNichiji;
    
    public DntInfSearch(){
        
    }
    
    public DntInfSearch(List<MskToroku> bean) {
        //情報を格納するフィールド・メソッド
        mskTorokuBeanList = new ArrayList();
        mskTorokuBeanList = bean;
        this.sortCode = "";
        this.schResuCount = 0;
        this.nowPage = 0;
        this.searchNichiji = "";
        //エラー箇所表示用
        this.errors = new ActionMessages();
    }

    public List<MskToroku> getMskTorokuBeanList(){
        return mskTorokuBeanList;
    }
    
    /**
     * @return the sortCode
     */
    public String getSortCode() {
        return sortCode;
    }

    /**
     * @param sortCode the sortCode to set
     */
    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    /**
     * @return the schResuCount
     */
    public int getSchResuCount() {
        return schResuCount;
    }

    /**
     * @param schResuCount the schResuCount to set
     */
    public void setSchResuCount(int schResuCount) {
        this.schResuCount = schResuCount;
    }

    /**
     * @return the nowPage
     */
    public int getNowPage() {
        return nowPage;
    }

    /**
     * @param nowPage the nowPage to set
     */
    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    /**
     * @return the searchNichiji
     */
    public String getSearchNichiji() {
        return searchNichiji;
    }

    /**
     * @param searchNichiji the searchNichiji to set
     */
    public void setSearchNichiji(String searchNichiji) {
        this.searchNichiji = searchNichiji;
    }


    /**
     * 最大ページ数を返す
     * @return 
     */
    public int getMaxPage() {
        int maxpage = (int) Math.ceil((double) this.schResuCount / MiwConstants.PAGE_KENSU);
        return maxpage;
    }

    /**
     * 現在ページ数を返す
     * @return 
     */
    public String getNowPageDisp() {
        return Integer.toString(this.nowPage);
    }

    /**
     * 検索結果件数を返す
     * @return 
     */
    public String getCntResuDisp() {
        return StringUtility.editComma(Integer.toString(this.schResuCount));
    }

    /**
     * 現在画面に表示されている。件目のFrm-Toを返す
     * @return 
     */
    public String getCntDisp() {
        //From件数目
        int fromNo = (this.nowPage * MiwConstants.PAGE_KENSU) - (MiwConstants.PAGE_KENSU - 1);
        //To件数目
        int toNo = (this.nowPage * MiwConstants.PAGE_KENSU);

        if (toNo > this.schResuCount) {
            toNo = this.schResuCount;
        }
        return StringUtility.editComma(Integer.toString(fromNo)) + "-" + StringUtility.editComma(Integer.toString(toNo));
    }

    /**
     * 次ページがあったらページ数を返す
     * 無かったらブランク
     * @return 
     */
    public String getNextPage() {
        //最大To件数目
        int toNo = (this.nowPage * MiwConstants.PAGE_KENSU);

        if (toNo >= this.schResuCount) {
            return "";
        } else {
            return Integer.toString(this.nowPage + 1);
        }
    }

    /**
     * 前ページがあったらページ数を返す
     * 無かったらブランク
     * @return 
     */
    public String getBackPage() {
        if (1 >= this.nowPage) {
            return "";
        } else {
            return Integer.toString(this.nowPage - 1);
        }
    }

    /**
     * 
     * ページ数の一覧を返す
     * @return 
     */
    public String[] getPageList() {
        final int page_list_cnt_zengo = 5;
        int nowPage = this.nowPage;

        int startPage = nowPage - page_list_cnt_zengo;
        int endPage = nowPage + page_list_cnt_zengo;

        if (startPage <= 0) {
            endPage += (startPage * (-1) + 1);
            startPage = 1;
        }

        if (endPage > getMaxPage()) {
            startPage -= endPage - getMaxPage();
            endPage = getMaxPage();
        }

        if (startPage <= 0) {
            startPage = 1;
        }

        String[] pageList = new String[(endPage - startPage) + 1];

        for (int i = 0; i < pageList.length; i++) {
            pageList[i] = Integer.toString(startPage++);
        }
        return pageList;
    }
    
    /**
     * 最初のページが、表示されるページリスト一覧に含まれているか
     * @return 
     */
    public boolean getIsFirstPageInList() {
        String[] list = getPageList();
        
        return CheckUtility.isPermissionSelect("1", list);
    }
    
    
    /**
     * ページリストの最小値が2かどうかを返す
     * @return 
     */
    public boolean getIsListMinPage2() {
        String[] list = getPageList();
        return list[0].equals("2");
    }
    
    /**
     * 最終ページが、表示されるページリスト一覧に含まれているか
     * @return 
     */
    public boolean getIsLastPageInList() {
        String[] list = getPageList();
        String lastPage = Integer.toString(getMaxPage());
        
        return CheckUtility.isPermissionSelect(lastPage, list);
    }
    
    
    
    ////////////////////////////////////////////
    //エラー箇所表示用
    ///////////////////////////////////////////
    /** エラー */
    private ActionMessages errors;

    /**
     * @return the errors
     */
    public ActionMessages getErrors() {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(ActionMessages errors) {
        this.errors = errors;
    }

    /**
     * 正常な場合、エラーの場合のCSSを返す
     * @return 
     */
    public String getValidateCss(String errorsName) {
        if (errors.get(errorsName).hasNext()) {
            return MiwConstants.VALIDATE_CSS_NG;
        } else {
            return MiwConstants.VALIDATE_CSS_OK;
        }
    }
}
