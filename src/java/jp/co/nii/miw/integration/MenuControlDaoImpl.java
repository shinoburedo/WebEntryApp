package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.MenuControl;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;
import jp.co.nii.miw.business.domain.MenuControlDao;
import jp.co.nii.sew.business.SystemTime;

/**
 * メニュー制御 DAO実装クラス
 * @author DB管理ツール
 */
public class MenuControlDaoImpl extends GeneratedMenuControlDaoImpl implements MenuControlDao {

    
    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public MenuControlDaoImpl(String datasource) {
        super(datasource);
    }
    
      
    
    @Override
    public MenuControl find(MenuControl bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND MENU_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ? ";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMenuCode());
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
    
    @Override
    public MenuControl findMenuForKikan(MenuControl bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MENU_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?"
                    + " AND KAISHI_DATE || KAISHI_TIME < ?"
                    + " AND SHURYO_DATE || SHURYO_TIME > ?";
            
            //現在日時をyyyyMMddHHmmss形式で取得する
            SystemTime systemTime = new SystemTime();
            String genzai = systemTime.getymd1() + systemTime.gethms1();
            
            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getMenuCode());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, genzai);
            stmt.setString(i++, genzai);

//            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            if (rs.next()) {
                setBoFromResultSet(bo, rs);
                if(rs.next()){
                    bo = null;
                }
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
    
    
    @Override
    public MenuControl selectNendo(MenuControl bo, String lockMode) {
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
                    + " AND MENU_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";
            
            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, MiwConstants.EVENT_CODE_ALL);
            stmt.setString(i++, MiwConstants.MENU_CODE_NENDO);
            stmt.setString(i++, MiwConstants.FLG_OFF);

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
    
    
    @Override
    public MenuControl findMenuForKikanNendo(MenuControl bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND MENU_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";
            
            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getMenuCode());
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
    
}
