package utils;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcCrudPart {
    public void testInsert() throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        JdbcUtils.freeConnection(connection);
    }
}
