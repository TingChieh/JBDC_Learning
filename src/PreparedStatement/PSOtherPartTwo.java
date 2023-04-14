package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

public class PSOtherPartTwo {

    @Test
    public void testBatchInsert() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        // 3. 编写 SQL 语句结果，动态值的部分使用 ? 代替
        String sql = "insert into testtable_user(account,password,nickname) values(?,?,?);";
        // 4. 创建preparedStatement.并且传入SQL语句结果
        PreparedStatement statement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            statement.setObject(1, "testttt" + i);
            statement.setObject(2, "password" + i);
            statement.setObject(3, "Hero" + i);

            statement.addBatch();
        }

        statement.executeBatch();

        long end = System.currentTimeMillis();
        // 6. 发送SQL语句,并且获取结果
        // 7.结果解析
        System.out.println("持续了一个 W 需要" + (end - start));
        // 8.关闭资源
        statement.close();
        connection.close();
    }
}
