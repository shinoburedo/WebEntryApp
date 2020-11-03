package jp.co.nii.miw.integration;

import java.util.List;
import java.util.LinkedList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jp.co.nii.miw.business.MiwConstants;
import jp.co.nii.miw.business.domain.Shikenchi;
import jp.co.nii.miw.business.service.common.KaijoJoho;
import jp.co.nii.sew.utility.LogGenerate;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.ShikenchiDao;

import org.apache.struts.util.LabelValueBean;

/**
 * 開催地区 DAO実装クラス
 * @author DB管理ツール
 */
public class ShikenchiDaoImpl extends GeneratedShikenchiDaoImpl implements ShikenchiDao {

    /**
     * インスタンスを生成する。
     * @param datasource データソース名
     */
    public ShikenchiDaoImpl(String datasource) {
        super(datasource);
    }

    @Override
    public Shikenchi find(Shikenchi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND SHIKENCHI_CODE = ?"
                    + " AND RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getShikenchiCode());
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

    /**
     * 入力欄表示用開催地区名をArrayList形式で取得
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード(受験科目)
     * @param lockMode ロック方法
     */
    @Override
    public List findList(String nendo, String kaisu, String eventCode, String lockMode) {

        List ret = new LinkedList();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND"
                    + " KAISU = ?"
                    + " AND"
                    + " EVENT_CODE = ?"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?"
                    + " ORDER BY HYOJI_JUNJO";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, kaisu);
            stmt.setString(i++, eventCode);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();

            // List型に値セット
            ret.add(new LabelValueBean("お選びください", ""));
            while (rs.next()) {
                ret.add(new LabelValueBean(rs.getString("SHIKENCHI_NAME"), rs.getString("SHIKENCHI_CODE")));
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }

        return ret;
    }

    /**
     * 開催地区名称を取得
     * @param nendo 年度
     * @param kaisu 回数
     * @param eventCode イベントコード(試験種別)
     * @param shikenchiCode 開催地区コード
     * @param lockMode ロック方法
     */
    @Override
    public String findName(String nendo, String kaisu, String eventCode, String shikenchiCode, String lockMode) {

        String ret = "";
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND"
                    + " KAISU = ?"
                    + " AND"
                    + " EVENT_CODE = ?"
                    + " AND"
                    + " SHIKENCHI_CODE = ?"
                    + " AND"
                    + " RONRI_SAKUJO_FLG = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, nendo);
            stmt.setString(i++, kaisu);
            stmt.setString(i++, eventCode);
            stmt.setString(i++, shikenchiCode);
            stmt.setString(i++, MiwConstants.FLG_OFF);

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();

            if (rs.next()) {
                ret = rs.getString("SHIKENCHI_NAME");
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }

        return ret;
    }

    @Override
    public ArrayList<Shikenchi> findList(Shikenchi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";
        ArrayList<Shikenchi> list = new ArrayList<Shikenchi>();
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?"
                    + " AND ICHIJI_NIJI_KBN = ?"
                    + " AND RONRI_SAKUJO_FLG = ?"
                    + " ORDER BY KAISAI_CHIKU_CODE ASC";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getRonriSakujoFlg());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            while (rs.next()) {
                //申込者情報一覧取得
                Shikenchi record = new Shikenchi();
                setBoFromResultSet(record, rs);
                list.add(record);
            }
            if (list.isEmpty()) {
                list = null;
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }
        return list;
    }

    @Override
    public List<KaijoJoho> selectShikenKaijo(KaijoJoho kaijoJoho) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "";

        List<KaijoJoho> bo = new ArrayList<KaijoJoho>();

        try {
            con = getConnection();
            sql = "SELECT A.shikenchi_code AS shikenchi_code,"
                    + " B.kaijo_code AS kaijo_code,"
                    + " A.shikenchi_name AS shikenchi_name,"
                    + " B.kaijo_name AS kaijo_name,"
                    + " B.kaisu AS kaisu"
                    + " FROM " + getSchemaName() + "." + TABLE_NAME + " A"
                    + " INNER JOIN "
                    + getSchemaName() + ".kaijo B ON"
                    + " A.shikenchi_code = B.shikenchi_code"
                    + " WHERE "
                    + " A.nendo = ? AND "
                    + " A.kaisu = ? AND"
                    + " A.event_code = ? AND"
                    + " A.RONRI_SAKUJO_FLG = '0' AND"
                    + " B.nendo = ? AND "
                    + " B.kaisu = ? AND"
                    + " B.event_code = ? AND"
                    + " B.RONRI_SAKUJO_FLG = '0'"
                    + " ORDER BY A.HYOJI_JUNJO";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, kaijoJoho.getNendo());
            stmt.setString(2, kaijoJoho.getKaisu());
            stmt.setString(3, kaijoJoho.getEventCode());
            stmt.setString(4, kaijoJoho.getNendo());
            stmt.setString(5, kaijoJoho.getKaisu());
            stmt.setString(6, kaijoJoho.getEventCode());

            LogGenerate.debugOutput(getSql(stmt));
            rs = stmt.executeQuery();
            while (rs.next()) {
                KaijoJoho kaijoJohoBean = new KaijoJoho();

                kaijoJohoBean.setShikenchiCode(rs.getString("shikenchi_code"));
                kaijoJohoBean.setKaijoCode(rs.getString("kaijo_code"));
                kaijoJohoBean.setShikenchiName(rs.getString("shikenchi_name"));
                kaijoJohoBean.setKaijoName(rs.getString("kaijo_name"));
                kaijoJohoBean.setKaisu(rs.getString("kaisu"));

                bo.add(kaijoJohoBean);
            }

        } catch (SQLException ex) {
            throw new SQLStateSQLExceptionTranslater().translate(getSql(stmt), ex);
        } finally {
            close(con, stmt, rs);
        }

        return bo;
    }
}
