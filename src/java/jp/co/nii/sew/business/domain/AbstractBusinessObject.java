package jp.co.nii.sew.business.domain;

/**
 * BusinessObjectの抽象的クラス
 * @author n-machida
 */
public abstract class AbstractBusinessObject implements BusinessObject {

    /**
     * コンストラクタ
     */
    protected AbstractBusinessObject() {
    }

    @Override
    public Object clone() {
        AbstractBusinessObject bo;
        try {
            bo = (AbstractBusinessObject) super.clone();
        } catch (CloneNotSupportedException ex) {
            throw new BOException(ex);
        }
        return bo;
    }
}
