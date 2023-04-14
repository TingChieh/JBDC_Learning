package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public class JdbcUtilsV2 {

    private static DataSource dataSource = null;

    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static {
        Properties properties = new Properties();
        InputStream ips = JdbcUtilsV2.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(ips);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        // 线程本地变量中是否存在
        Connection connection = tl.get();

        if (connection == null) {
            connection = dataSource.getConnection();
            tl.set(connection);
        }
        return dataSource.getConnection();
    }

    public static void freeConnection() throws SQLException {
        Connection connection = tl.get();
        if (connection != tl.get()) {
            connection.setAutoCommit(true);
            connection.close();
        }
    }
}
