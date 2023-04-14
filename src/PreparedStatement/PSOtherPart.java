package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class PSOtherPart {
    /**
     * T0D0:
     *
     * t_user插入一条数据!并且获取数据库自增长的主键!
     * TOD0: 使用总结
     *
     * 1.创建prepareStatement 的时候,告知,携带回数据库自增长的主键
     * (sql,Statement.RETURN_GENERATED_KEYS)
     * 2.获取司机装 主键值的结果集对象，一行一列，获取对应的数据即可ResultSet resultSet =
     * statement.getGeneratedKeys() ;
     */

    @Test
    public void returnPrimaryKey() throws ClassNotFoundException, SQLException {
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        // 3. 编写 SQL 语句结果，动态值的部分使用 ? 代替
        String sql = "insert into testtable_user(account,password,nickname) values(?,?,?);";
        // 4. 创建preparedStatement.并且传入SQL语句结果
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        // 5. 占位符赋值
        statement.setObject(1, "test1");
        statement.setObject(2, "123456");
        statement.setObject(3, "Egg");

        // 6. 发送SQL语句,并且获取结果
        int i = statement.executeUpdate();
        // 7.结果解析
        if (i > 0) {
            System.out.println("数据插入成功");

            // 可以获取回显的主键
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            int id = resultSet.getInt(i);
            System.out.println("id = " + id);
        } else {
            System.out.println("插入数据失败");
        }
        // 8.关闭资源
        statement.close();
        connection.close();
    }

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
            statement.setObject(1, "test2" + i);
            statement.setObject(2, "password" + i);
            statement.setObject(3, "Hero" + i);

            statement.addBatch();
        }

        statement.executeBatch();

        long end = System.currentTimeMillis();
        // 6. 发送SQL语句,并且获取结果
        // 7.结果解析
        System.out.println("持续了一个 K 需要" + (end - start));
        // 8.关闭资源
        statement.close();
        connection.close();
    }
}
