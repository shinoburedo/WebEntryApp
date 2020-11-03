package jp.co.nii.miw.integration;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.text.NumberFormat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Event;
import jp.co.nii.miw.business.domain.EventDao;
import jp.co.nii.miw.business.service.MiaServ;
import jp.co.nii.miw.utility.CodeValueName;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

/**
 * イベント DAO実装クラス
 * @author DB管理ツール
 */
public class EventDaoImpl extends GeneratedEventDaoImpl implements EventDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     *  SQL文の作成に用いる。
     */
    protected static final String FIELDS_SELECT = "A.EVENT_CODE"
            + "," + "A.EVENT_NAME"
            + "," + "A.HYOJI_JUNJO"
            + "," + "A.EVENT_RYO AS EVENT_RYO_KOKUNAI"
            + "," + "A.CONVENIENCE_KESSAI_JIMU_TESURYO AS CONVENIENCE_KESSAI_JIMU_TESURYO_KOKUNAI"
            + "," + "A.CREDITCARD_KESSAI_JIMU_TESURYO AS CREDITCARD_KESSAI_JIMU_TESURYO_KOKUNAI"
            + "," + "B.EVENT_RYO AS EVENT_RYO_KOKUGAI"
            + "," + "B.CONVENIENCE_KESSAI_JIMU_TESURYO AS CONVENIENCE_KESSAI_JIMU_TESURYO_KOKUGAI"
            + "," + "B.CREDITCARD_KESSAI_JIMU_TESURYO AS CREDITCARD_KESSAI_JIMU_TESURYO_KOKUGAI";
    
    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     *  SQL文の作成に用いる。
     */
    protected static final String FIELDS_SELECT_2 = "EVENT_CODE"
            + "," + "EVENT_RYO"
            + "," + "CONVENIENCE_KESSAI_JIMU_TESURYO"
            + "," + "CREDITCARD_KESSAI_JIMU_TESURYO";
    
    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public EventDaoImpl(String datasource) {
        super(datasource);
    }
    
     @Override
    public Event find(Event bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " EVENT_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getRonriSakujoFlg());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                setBoFromResultSet(bo, rs);
            } else {
                bo = null;
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        return bo;
    }
    
     protected void setMapFromResultSet(Map map, ResultSet rs) {
        try {
            String eventName = rs.getString("EVENT_NAME");
            int iKokunai = rs.getInt("EVENT_RYO_KOKUNAI");
            int iCnvKokunai = rs.getInt("CONVENIENCE_KESSAI_JIMU_TESURYO_KOKUNAI");
            int iCrdKokunai = rs.getInt("CREDITCARD_KESSAI_JIMU_TESURYO_KOKUNAI");
            int iKokugai = rs.getInt("EVENT_RYO_KOKUGAI");
            int iCnvKokugai = rs.getInt("CONVENIENCE_KESSAI_JIMU_TESURYO_KOKUGAI");
            int iCrdKokugai = rs.getInt("CREDITCARD_KESSAI_JIMU_TESURYO_KOKUGAI");
            
            //各項目を編集
            eventName = eventName.replace("のみ", "");
            NumberFormat format = NumberFormat.getNumberInstance();
            String kokunai = format.format(iKokunai) + "円";
            String cnvKokunai = format.format(iCnvKokunai) + "円";
            String crdKokunai = format.format(iCrdKokunai) + "円";
            
            String kokugai;
            String cnvKokugai;
            String crdKokugai;
            
            if (iKokugai == 0) {
                //検定料０円の場合パリ会場なしとみなす
                kokugai = "-";
                cnvKokugai = "-";
                crdKokugai = "-";
            } else {
                kokugai = format.format(iKokugai) + "円";
                cnvKokugai = format.format(iCnvKokugai) + "円";
                crdKokugai = format.format(iCrdKokugai) + "円";
            }
            
            map.put("EventName", eventName);
            map.put("EventRyoKokunai", kokunai);
            map.put("CreditcardKessaiJimuTesuryoKokunai", crdKokunai);
            map.put("ConvenienceKessaiJimuTesuryoKokunai", cnvKokunai);
            map.put("EventRyoKokugai", kokugai);
            map.put("CreditcardKessaiJimuTesuryoKokugai", crdKokugai);
            map.put("ConvenienceKessaiJimuTesuryoKokugai", cnvKokugai);
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(null, ex);
        }
     }
     
     
     @Override
    public int getKenteiryo(String eventCode, String lockMode) {
    
        int ret = 0;
        
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            
            //SQL作成
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " EVENT_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            
            int i = 1;
            stmt.setString(i++, eventCode);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                ret = rs.getInt("EVENT_RYO");
            }
            
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        return ret;
    }
     

     
}
