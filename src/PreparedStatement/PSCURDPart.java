package PreparedStatement;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({ "all" })
public class PSCURDPart {
    @Test
    public void testInsert() throws ClassNotFoundException, SQLException {
        /**
         * testtable_user插入一条数据!
         * account test
         * password test
         * nickname 二狗子
         */

        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        // 3. 编写 SQL 语句结果，动态值的部分使用 ? 代替
        String sql = "insert into testtable_user(account,password,nickname) values(?,?,?);";
        // 4. 创建preparedStatement.并且传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1, "test");
        preparedStatement.setObject(2, "test");
        preparedStatement.setObject(3, "二狗子");
        // 6. 发送SQL语句
        // DML 类型
        int rows = preparedStatement.executeUpdate();
        // 7. 输出结果
        if (rows > 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }
        // 8. 关闭资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
        // 修改 id = 3 的用户 nickname = 三狗子
        // 1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        // 3. 编写 SQL 语句结果，动态值的部分使用 ? 代替
        String sql = "update testtable_user set nickname=? where id=?";
        // 4. 创建preparedStatement.并且传入SQL语句结果
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        preparedStatement.setObject(1, "三狗子");
        preparedStatement.setObject(2, 3);
        // 6. 发送SQL语句
        int i = preparedStatement.executeUpdate();
        // 7. 输出结果
        if (i > 0) {
            System.out.println("数据插入成功");
        } else {
            System.out.println("数据插入失败");
        }
        // 8. 关闭资源
        preparedStatement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        String sql = "delete from testtable_user where id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 3);
        int i = preparedStatement.executeUpdate();
        if (i > 0) {
            System.out.println("数据删除成功");
        } else {
            System.out.println("数据删除失败");
        }
        preparedStatement.close();
        connection.close();
    }

    /**
     * 目标:查询所有用户数据,并且封装到一-个List<Map> list集合中!
     * 解释:
     * 
     * 行 id account password nickname
     * 行 id account password nickname
     * 行 id account password nickname
     *
     * 数据库-> resultSet -> java ->一行- map(key=列名, value=列的内容) -> List<Map> list
     * 实现思路:
     * 遍历行数据, 一行对应一个map! 获取一行的列名和对应的列的属性,装配即可!
     * 将map装到一个集合就可以了!
     * 
     * 难点：
     * 如何获取列的名称
     * 
     */

    @Test
    public void testSelect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        String sql = "select id,account,password,nickname from testtable_user ；";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 可以省略占位符赋值
        ResultSet resultSet = preparedStatement.executeQuery();

        /**
         * Todo: 回顾
         * resultSet: 有行和有列!获取数据的时候，一行一行数据!
         * 内部有一一个游标,默认指向数据的第-行之前!
         * 我们可以利用next()方法移动游标!指向数据行!
         * 获取行中的列的数据
         */

        List<Map> list = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();

        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Map map = new HashMap<>();
            // map.put("id", resultSet.getInt("id"));
            // map.put("account", resultSet.getString("account"));
            // map.put("passwo", resultSet.getInt("password"));
            // map.put("id", resultSet.getInt("nickname"));
            // }

            for (int i = 1; i <= columnCount; i++) {
                // 获取指定列下角标的值
                Object value = resultSet.getObject(i);
                // 获取指定列下角标的列的名称 ResultMetaData
                String columnLabel = metaData.getColumnLabel(i);

                map.put(columnLabel, value);
            }
            list.add(map);
        }
        System.out.println("list = " + list);
    }
}
