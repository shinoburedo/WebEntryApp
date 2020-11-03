package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedKessaiLog;
import jp.co.nii.miw.business.domain.GeneratedKessaiLogDao;

/**
 * 生成された 決済ログ DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedKessaiLogDaoImpl extends AbstractDao implements GeneratedKessaiLogDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "SOUJUSHIN_DATE"
            + ",SOUJUSHIN_TIME"
            + ",SEQ"
            + ",MOSHIKOMI_UKETSUKE_NO"
            + ",NENDO"
            + ",KAISU"
            + ",EVENT_CODE"
            + ",USER_ID"
            + ",KESSAI_DAIKOU_KAISHA_CODE"
            + ",MESSAGE_SHUBETSU"
            + ",MESSAGE_HONTAI"
            + ",RONRI_SAKUJO_FLG"
            + ",SHORI_KBN"
            + ",TOROKU_DATE"
            + ",TOROKU_TIME"
            + ",TOROKU_USER_ID"
            + ",KOSHIN_DATE"
            + ",KOSHIN_TIME"
            + ",KOSHIN_USER_ID";

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     *  暗号化対象項目を複号するSQL文の作成に用いる。
     */
    protected static final String FIELDS_DECRYPT = "SOUJUSHIN_DATE"
            + "," + "SOUJUSHIN_TIME"
            + "," + "SEQ"
            + "," + "MOSHIKOMI_UKETSUKE_NO"
            + "," + "NENDO"
            + "," + "KAISU"
            + "," + "EVENT_CODE"
            + "," + "USER_ID"
            + "," + "KESSAI_DAIKOU_KAISHA_CODE"
            + "," + "MESSAGE_SHUBETSU"
            + "," + "MESSAGE_HONTAI"
            + "," + "RONRI_SAKUJO_FLG"
            + "," + "SHORI_KBN"
            + "," + "TOROKU_DATE"
            + "," + "TOROKU_TIME"
            + "," + "TOROKU_USER_ID"
            + "," + "KOSHIN_DATE"
            + "," + "KOSHIN_TIME"
            + "," + "KOSHIN_USER_ID";

    /**
     * インスタンスを生成する。<br>
     * データソースを決定
     * @param datasource データソース名
     */
    public GeneratedKessaiLogDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedKessaiLogDao#create(jp.co.nii.miw.business.domain.GeneratedKessaiLog)
     */
    @Override
    public void create(GeneratedKessaiLog bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "INSERT INTO " + getSchemaName() + "." + TABLE_NAME + " ( "
                    + FIELDS
                    + " ) VALUES ("
                    + " ?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + ",?"
                    + " )";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getSoujushinDate());
            stmt.setString(i++, bo.getSoujushinTime());
            stmt.setString(i++, bo.getSeq());
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getKessaiDaikouKaishaCode());
            stmt.setString(i++, bo.getMessageShubetsu());
            stmt.setString(i++, bo.getMessageHontai());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            LogGenerate.debugOutput(getSql(stmt));
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt);
        }
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedKessaiLogDao#find(jp.co.nii.miw.business.domain.GeneratedKessaiLog, java.lang.String)
     */
    @Override
    public GeneratedKessaiLog find(GeneratedKessaiLog bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " SOUJUSHIN_DATE = ?"
                    + " AND SOUJUSHIN_TIME = ?"
                    + " AND SEQ = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getSoujushinDate());
            stmt.setString(i++, bo.getSoujushinTime());
            stmt.setString(i++, bo.getSeq());

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

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedKessaiLogDao#update(jp.co.nii.miw.business.domain.GeneratedKessaiLog)
     */
    @Override
    public void update(GeneratedKessaiLog bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + ",NENDO = ?"
                    + ",KAISU = ?"
                    + ",EVENT_CODE = ?"
                    + ",USER_ID = ?"
                    + ",KESSAI_DAIKOU_KAISHA_CODE = ?"
                    + ",MESSAGE_SHUBETSU = ?"
                    + ",MESSAGE_HONTAI = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " SOUJUSHIN_DATE = ?"
                    + " AND SOUJUSHIN_TIME = ?"
                    + " AND SEQ = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getKessaiDaikouKaishaCode());
            stmt.setString(i++, bo.getMessageShubetsu());
            stmt.setString(i++, bo.getMessageHontai());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getSoujushinDate());
            stmt.setString(i++, bo.getSoujushinTime());
            stmt.setString(i++, bo.getSeq());

            LogGenerate.debugOutput(getSql(stmt));
            if (stmt.executeUpdate() == 0) {
                throw new NoSuchDataException(getSql(stmt));
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt);
        }
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedKessaiLogDao#remove(jp.co.nii.miw.business.domain.GeneratedKessaiLog)
     */
    @Override
    public void remove(GeneratedKessaiLog bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " SOUJUSHIN_DATE = ?"
                    + " AND SOUJUSHIN_TIME = ?"
                    + " AND SEQ = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getSoujushinDate());
            stmt.setString(i++, bo.getSoujushinTime());
            stmt.setString(i++, bo.getSeq());

            LogGenerate.debugOutput(getSql(stmt));
            if (stmt.executeUpdate() == 0) {
                throw new NoSuchDataException(getSql(stmt));
            }
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt);
        }
    }

    /**
     * 検索結果であるResultSetをBusinessObjectへ詰め替える。<br>
     * 事前条件：引数のboとrsはnullでないこと。
     * 
     * @param bo BusinessObjectのインスタンス
     * @param rs 検索結果のResultSet
     */
    protected void setBoFromResultSet(GeneratedKessaiLog bo, ResultSet rs) {
        try {
            bo.setSoujushinDate(rs.getString("SOUJUSHIN_DATE"));
            bo.setSoujushinTime(rs.getString("SOUJUSHIN_TIME"));
            bo.setSeq(rs.getString("SEQ"));
            bo.setMoshikomiUketsukeNo(rs.getString("MOSHIKOMI_UKETSUKE_NO"));
            bo.setNendo(rs.getString("NENDO"));
            bo.setKaisu(rs.getString("KAISU"));
            bo.setEventCode(rs.getString("EVENT_CODE"));
            bo.setUserId(rs.getString("USER_ID"));
            bo.setKessaiDaikouKaishaCode(rs.getString("KESSAI_DAIKOU_KAISHA_CODE"));
            bo.setMessageShubetsu(rs.getString("MESSAGE_SHUBETSU"));
            bo.setMessageHontai(rs.getString("MESSAGE_HONTAI"));
            bo.setRonriSakujoFlg(rs.getString("RONRI_SAKUJO_FLG"));
            bo.setShoriKbn(rs.getString("SHORI_KBN"));
            bo.setTorokuDate(rs.getString("TOROKU_DATE"));
            bo.setTorokuTime(rs.getString("TOROKU_TIME"));
            bo.setTorokuUserId(rs.getString("TOROKU_USER_ID"));
            bo.setKoshinDate(rs.getString("KOSHIN_DATE"));
            bo.setKoshinTime(rs.getString("KOSHIN_TIME"));
            bo.setKoshinUserId(rs.getString("KOSHIN_USER_ID"));
        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(null, ex);
        }
    }
}
