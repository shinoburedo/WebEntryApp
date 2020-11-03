package jp.co.nii.sew.integration;

import java.sql.SQLException;
import jp.co.nii.sew.business.domain.DataAccessException;

/**
 * Interface to be implemented by classes that can translate
 * between SQLExceptions and our data access strategy-agnostic
 * DataAccessException.
 * <br>Implementations can be generic (for example,
 * using SQLState codes for JDBC) or proprietary (for example,
 * using Oracle error codes) for greater precision.
 * @author Rod Johnson
 * @version $Id: SQLExceptionTranslater.java,v 1.1 2002/08/24 08:41:31 Rod Johnson Exp $
 */
public interface SQLExceptionTranslater {

    /**
     * Translate the given SQL exception into a data access exception
     * @param sql SQL query or update that caused the problem. May be null.
     * @param sqlex SQLException encountered by JDBC implementation
     * @return データアクセス例外
     */
    DataAccessException translate(String sql, SQLException sqlex);
}
