package druid;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DruidUsePart {
    public void testHard() throws SQLException {
        // 1. 连接池对象
        DruidDataSource dataSource = new DruidDataSource();
        
        // 2. 设置参数
        // 3. 连接数据库驱动类的全限定符 URL USER PASSWD
        dataSource.setUrl("jdbc:mysql:///test01?user=root&password=ogihana77**");
        dataSource.setUsername("root");
        dataSource.setPassword("ogihana77**");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // 初始化连接数量，最大的连接数量
        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);

        // 获取链接
        Connection connection = dataSource.getConnection();
        
        // 回收连接
        connection.close();
        dataSource.close();
    }

    @Test
    public void testSoft() throws Exception {
        Properties properties = new Properties();
        InputStream ips = DruidUsePart.class.getClassLoader().getResourceAsStream("druid.properties");
        properties.load(ips);

        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();

        connection.close();
    }
}
