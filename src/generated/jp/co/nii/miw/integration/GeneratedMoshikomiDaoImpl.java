package jp.co.nii.miw.integration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.nii.sew.business.domain.NoSuchDataException;
import jp.co.nii.sew.integration.AbstractDao;
import jp.co.nii.sew.integration.SQLStateSQLExceptionTranslater;

import jp.co.nii.miw.business.domain.GeneratedMoshikomi;
import jp.co.nii.miw.business.domain.GeneratedMoshikomiDao;
import jp.co.nii.sew.utility.LogGenerate;

/**
 * 生成された 申込 DAO実装クラス<br>
 * table-design-ver 1
 * @author DB管理ツール
 */
abstract class GeneratedMoshikomiDaoImpl extends AbstractDao implements GeneratedMoshikomiDao {

    /**
     * テーブルカラム名のカンマ区切り文字列。<br>
     * SQL文の作成に用いる。
     */
    protected static final String FIELDS = "MOSHIKOMI_UKETSUKE_NO"
            + ",NENDO"
            + ",KAISU"
            + ",EVENT_CODE"
            + ",MOSHIKOMI_BAITAI_KBN"
            + ",KOJIN_DANTAI_KBN"
            + ",DANTAI_CODE"
            + ",DANTAI_MOSHIKOMI_UKETSUKE_NO"
            + ",USER_ID"
            + ",TETSUDUKI_JOKYO_KBN"
            + ",SHIKENCHI_CODE"
            + ",KAIJO_CODE"
            + ",JUKO_KEIKEN"
            + ",JITSUMU_KEIKEN"
            + ",KESSAI_JOKYO_KBN"
            + ",KESSAI_HOHO_KBN"
            + ",KESSAI_KAMEITEN_CODE"
            + ",KESSAI_TORIHIKI_CODE"
            + ",KESSAI_CONVENIENCE_SHUBETSU"
            + ",KESSAI_CONVENIENCE_HARAIKOMIHYO_URL"
            + ",KESSAI_KIGEN_BI"
            + ",KESSAI_KINGAKU"
            + ",KARI_UKETSUKE_BI"
            + ",KARI_UKETSUKE_TIME"
            + ",MOSHIKOMI_FINISH_BI"
            + ",MOSHIKOMI_FINISH_TIME"
            + ",MOSHIKOMI_MEMO"
            + ",KANRI_MEMO"
            + ",GAZO_ID"
            + ",HOSEI_IRAI_KBN"
            + ",HOSEI_IRAI_BI"
            + ",HOSEI_IRAI_TIME"
            + ",HOSEI_TAIO_BI"
            + ",HOSEI_TAIO_TIME"
            + ",HOSEI_FINISH_BI"
            + ",HOSEI_FINISH_TIME"
            + ",KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"
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
    protected static final String FIELDS_DECRYPT = "MOSHIKOMI_UKETSUKE_NO"
            + "," + "NENDO"
            + "," + "KAISU"
            + "," + "EVENT_CODE"
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_BAITAI_KBN")
            + "," + getSQLForDecryptByUTF8("KOJIN_DANTAI_KBN")
            + "," + getSQLForDecryptByUTF8("DANTAI_CODE")
            + "," + getSQLForDecryptByUTF8("DANTAI_MOSHIKOMI_UKETSUKE_NO")
            + "," + getSQLForDecryptByUTF8("USER_ID")
            + "," + getSQLForDecryptByUTF8("TETSUDUKI_JOKYO_KBN")
            + "," + getSQLForDecryptByUTF8("SHIKENCHI_CODE")
            + "," + getSQLForDecryptByUTF8("KAIJO_CODE")
            + "," + getSQLForDecryptByUTF8("JUKO_KEIKEN")
            + "," + getSQLForDecryptByUTF8("JITSUMU_KEIKEN")
            + "," + getSQLForDecryptByUTF8("KESSAI_JOKYO_KBN")
            + "," + getSQLForDecryptByUTF8("KESSAI_HOHO_KBN")
            + "," + getSQLForDecryptByUTF8("KESSAI_KAMEITEN_CODE")
            + "," + getSQLForDecryptByUTF8("KESSAI_TORIHIKI_CODE")
            + "," + getSQLForDecryptByUTF8("KESSAI_CONVENIENCE_SHUBETSU")
            + "," + getSQLForDecryptByUTF8("KESSAI_CONVENIENCE_HARAIKOMIHYO_URL")
            + "," + getSQLForDecryptByUTF8("KESSAI_KIGEN_BI")
            + "," + getSQLForDecryptByUTF8("KESSAI_KINGAKU")
            + "," + getSQLForDecryptByUTF8("KARI_UKETSUKE_BI")
            + "," + getSQLForDecryptByUTF8("KARI_UKETSUKE_TIME")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_FINISH_BI")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_FINISH_TIME")
            + "," + getSQLForDecryptByUTF8("MOSHIKOMI_MEMO")
            + "," + getSQLForDecryptByUTF8("KANRI_MEMO")
            + "," + getSQLForDecryptByUTF8("GAZO_ID")
            + "," + "HOSEI_IRAI_KBN"
            + "," + "HOSEI_IRAI_BI"
            + "," + "HOSEI_IRAI_TIME"
            + "," + "HOSEI_TAIO_BI"
            + "," + "HOSEI_TAIO_TIME"
            + "," + "HOSEI_FINISH_BI"
            + "," + "HOSEI_FINISH_TIME"
            + "," + "KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"
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
    public GeneratedMoshikomiDaoImpl(String datasource) {
        super(datasource);
    }

    /* (non-Javadoc)
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDao#create(jp.co.nii.miw.business.domain.GeneratedMoshikomi)
     */
    @Override
    public void create(GeneratedMoshikomi bo) {
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
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
                    + "," + getSQLForEncryptByUTF8("?")
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
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getKojinDantaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getShikenchiCode());
            stmt.setString(i++, bo.getKaijoCode());
            stmt.setString(i++, bo.getJukoKeiken());
            stmt.setString(i++, bo.getJitsumuKeiken());
            stmt.setString(i++, bo.getKessaiJokyoKbn());
            stmt.setString(i++, bo.getKessaiHohoKbn());
            stmt.setString(i++, bo.getKessaiKameitenCode());
            stmt.setString(i++, bo.getKessaiTorihikiCode());
            stmt.setString(i++, bo.getKessaiConvenienceShubetsu());
            stmt.setString(i++, bo.getKessaiConvenienceHaraikomihyoUrl());
            stmt.setString(i++, bo.getKessaiKigenBi());
            stmt.setString(i++, bo.getKessaiKingaku());
            stmt.setString(i++, bo.getKariUketsukeBi());
            stmt.setString(i++, bo.getKariUketsukeTime());
            stmt.setString(i++, bo.getMoshikomiFinishBi());
            stmt.setString(i++, bo.getMoshikomiFinishTime());
            stmt.setString(i++, bo.getMoshikomiMemo());
            stmt.setString(i++, bo.getKanriMemo());
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getHoseiIraiKbn());
            stmt.setString(i++, bo.getHoseiIraiBi());
            stmt.setString(i++, bo.getHoseiIraiTime());
            stmt.setString(i++, bo.getHoseiTaioBi());
            stmt.setString(i++, bo.getHoseiTaioTime());
            stmt.setString(i++, bo.getHoseiFinishBi());
            stmt.setString(i++, bo.getHoseiFinishTime());
            stmt.setString(i++, bo.getKessaiTokusokuMailSoshinFlg());
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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDao#find(jp.co.nii.miw.business.domain.GeneratedMoshikomi, java.lang.String)
     */
    @Override
    public GeneratedMoshikomi find(GeneratedMoshikomi bo, String lockMode) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs =null;
        String sql = "";
        try {
            con = getConnection();
            sql = "SELECT " + FIELDS_DECRYPT
                    + " FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?";

            stmt = con.prepareStatement(sql + lockMode);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDao#update(jp.co.nii.miw.business.domain.GeneratedMoshikomi)
     */
    @Override
    public void update(GeneratedMoshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "UPDATE " + getSchemaName() + "." + TABLE_NAME + " SET"
                    + " MOSHIKOMI_BAITAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KOJIN_DANTAI_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",DANTAI_MOSHIKOMI_UKETSUKE_NO = " + getSQLForEncryptByUTF8("?")
                    + ",USER_ID = " + getSQLForEncryptByUTF8("?")
                    + ",TETSUDUKI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",SHIKENCHI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KAIJO_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",JUKO_KEIKEN = " + getSQLForEncryptByUTF8("?")
                    + ",JITSUMU_KEIKEN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_JOKYO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_HOHO_KBN = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KAMEITEN_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_TORIHIKI_CODE = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_CONVENIENCE_SHUBETSU = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_CONVENIENCE_HARAIKOMIHYO_URL = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KIGEN_BI = " + getSQLForEncryptByUTF8("?")
                    + ",KESSAI_KINGAKU = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_BI = " + getSQLForEncryptByUTF8("?")
                    + ",KARI_UKETSUKE_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_BI = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_FINISH_TIME = " + getSQLForEncryptByUTF8("?")
                    + ",MOSHIKOMI_MEMO = " + getSQLForEncryptByUTF8("?")
                    + ",KANRI_MEMO = " + getSQLForEncryptByUTF8("?")
                    + ",GAZO_ID = " + getSQLForEncryptByUTF8("?")
                    + ",HOSEI_IRAI_KBN = ?"
                    + ",HOSEI_IRAI_BI = ?"
                    + ",HOSEI_IRAI_TIME = ?"
                    + ",HOSEI_TAIO_BI = ?"
                    + ",HOSEI_TAIO_TIME = ?"
                    + ",HOSEI_FINISH_BI = ?"
                    + ",HOSEI_FINISH_TIME = ?"
                    + ",KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG = ?"
                    + ",RONRI_SAKUJO_FLG = ?"
                    + ",SHORI_KBN = ?"
                    + ",TOROKU_DATE = ?"
                    + ",TOROKU_TIME = ?"
                    + ",TOROKU_USER_ID = ?"
                    + ",KOSHIN_DATE = ?"
                    + ",KOSHIN_TIME = ?"
                    + ",KOSHIN_USER_ID = ?"
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiBaitaiKbn());
            stmt.setString(i++, bo.getKojinDantaiKbn());
            stmt.setString(i++, bo.getDantaiCode());
            stmt.setString(i++, bo.getDantaiMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getUserId());
            stmt.setString(i++, bo.getTetsudukiJokyoKbn());
            stmt.setString(i++, bo.getShikenchiCode());
            stmt.setString(i++, bo.getKaijoCode());
            stmt.setString(i++, bo.getJukoKeiken());
            stmt.setString(i++, bo.getJitsumuKeiken());
            stmt.setString(i++, bo.getKessaiJokyoKbn());
            stmt.setString(i++, bo.getKessaiHohoKbn());
            stmt.setString(i++, bo.getKessaiKameitenCode());
            stmt.setString(i++, bo.getKessaiTorihikiCode());
            stmt.setString(i++, bo.getKessaiConvenienceShubetsu());
            stmt.setString(i++, bo.getKessaiConvenienceHaraikomihyoUrl());
            stmt.setString(i++, bo.getKessaiKigenBi());
            stmt.setString(i++, bo.getKessaiKingaku());
            stmt.setString(i++, bo.getKariUketsukeBi());
            stmt.setString(i++, bo.getKariUketsukeTime());
            stmt.setString(i++, bo.getMoshikomiFinishBi());
            stmt.setString(i++, bo.getMoshikomiFinishTime());
            stmt.setString(i++, bo.getMoshikomiMemo());
            stmt.setString(i++, bo.getKanriMemo());
            stmt.setString(i++, bo.getGazoId());
            stmt.setString(i++, bo.getHoseiIraiKbn());
            stmt.setString(i++, bo.getHoseiIraiBi());
            stmt.setString(i++, bo.getHoseiIraiTime());
            stmt.setString(i++, bo.getHoseiTaioBi());
            stmt.setString(i++, bo.getHoseiTaioTime());
            stmt.setString(i++, bo.getHoseiFinishBi());
            stmt.setString(i++, bo.getHoseiFinishTime());
            stmt.setString(i++, bo.getKessaiTokusokuMailSoshinFlg());
            stmt.setString(i++, bo.getRonriSakujoFlg());
            stmt.setString(i++, bo.getShoriKbn());
            stmt.setString(i++, bo.getTorokuDate());
            stmt.setString(i++, bo.getTorokuTime());
            stmt.setString(i++, bo.getTorokuUserId());
            stmt.setString(i++, bo.getKoshinDate());
            stmt.setString(i++, bo.getKoshinTime());
            stmt.setString(i++, bo.getKoshinUserId());

            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());

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
     * @see jp.co.nii.miw.business.domain.GeneratedMoshikomiDao#remove(jp.co.nii.miw.business.domain.GeneratedMoshikomi)
     */
    @Override
    public void remove(GeneratedMoshikomi bo) {
        Connection con = null;
        PreparedStatement stmt = null;
        String sql = "";
        try {
            con = getConnection();
            sql = "DELETE FROM " + getSchemaName() + "." + TABLE_NAME
                    + " WHERE"
                    + " MOSHIKOMI_UKETSUKE_NO = ?"
                    + " AND NENDO = ?"
                    + " AND KAISU = ?"
                    + " AND EVENT_CODE = ?";

            stmt = con.prepareStatement(sql);
            int i = 1;
            stmt.setString(i++, bo.getMoshikomiUketsukeNo());
            stmt.setString(i++, bo.getNendo());
            stmt.setString(i++, bo.getKaisu());
            stmt.setString(i++, bo.getEventCode());

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
    protected void setBoFromResultSet(GeneratedMoshikomi bo, ResultSet rs) {
        try {
            bo.setMoshikomiUketsukeNo(rs.getString("MOSHIKOMI_UKETSUKE_NO"));
            bo.setNendo(rs.getString("NENDO"));
            bo.setKaisu(rs.getString("KAISU"));
            bo.setEventCode(rs.getString("EVENT_CODE"));
            bo.setMoshikomiBaitaiKbn(rs.getString("MOSHIKOMI_BAITAI_KBN"));
            bo.setKojinDantaiKbn(rs.getString("KOJIN_DANTAI_KBN"));
            bo.setDantaiCode(rs.getString("DANTAI_CODE"));
            bo.setDantaiMoshikomiUketsukeNo(rs.getString("DANTAI_MOSHIKOMI_UKETSUKE_NO"));
            bo.setUserId(rs.getString("USER_ID"));
            bo.setTetsudukiJokyoKbn(rs.getString("TETSUDUKI_JOKYO_KBN"));
            bo.setShikenchiCode(rs.getString("SHIKENCHI_CODE"));
            bo.setKaijoCode(rs.getString("KAIJO_CODE"));
            bo.setJukoKeiken(rs.getString("JUKO_KEIKEN"));
            bo.setJitsumuKeiken(rs.getString("JITSUMU_KEIKEN"));
            bo.setKessaiJokyoKbn(rs.getString("KESSAI_JOKYO_KBN"));
            bo.setKessaiHohoKbn(rs.getString("KESSAI_HOHO_KBN"));
            bo.setKessaiKameitenCode(rs.getString("KESSAI_KAMEITEN_CODE"));
            bo.setKessaiTorihikiCode(rs.getString("KESSAI_TORIHIKI_CODE"));
            bo.setKessaiConvenienceShubetsu(rs.getString("KESSAI_CONVENIENCE_SHUBETSU"));
            bo.setKessaiConvenienceHaraikomihyoUrl(rs.getString("KESSAI_CONVENIENCE_HARAIKOMIHYO_URL"));
            bo.setKessaiKigenBi(rs.getString("KESSAI_KIGEN_BI"));
            bo.setKessaiKingaku(rs.getString("KESSAI_KINGAKU"));
            bo.setKariUketsukeBi(rs.getString("KARI_UKETSUKE_BI"));
            bo.setKariUketsukeTime(rs.getString("KARI_UKETSUKE_TIME"));
            bo.setMoshikomiFinishBi(rs.getString("MOSHIKOMI_FINISH_BI"));
            bo.setMoshikomiFinishTime(rs.getString("MOSHIKOMI_FINISH_TIME"));
            bo.setMoshikomiMemo(rs.getString("MOSHIKOMI_MEMO"));
            bo.setKanriMemo(rs.getString("KANRI_MEMO"));
            bo.setGazoId(rs.getString("GAZO_ID"));
            bo.setHoseiIraiKbn(rs.getString("HOSEI_IRAI_KBN"));
            bo.setHoseiIraiBi(rs.getString("HOSEI_IRAI_BI"));
            bo.setHoseiIraiTime(rs.getString("HOSEI_IRAI_TIME"));
            bo.setHoseiTaioBi(rs.getString("HOSEI_TAIO_BI"));
            bo.setHoseiTaioTime(rs.getString("HOSEI_TAIO_TIME"));
            bo.setHoseiFinishBi(rs.getString("HOSEI_FINISH_BI"));
            bo.setHoseiFinishTime(rs.getString("HOSEI_FINISH_TIME"));
            bo.setKessaiTokusokuMailSoshinFlg(rs.getString("KESSAI_TOKUSOKU_MAIL_SOSHIN_FLG"));
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
