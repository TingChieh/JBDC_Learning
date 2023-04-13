package StudyJBDC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import java.sql.Statement;


public class StatementUserLogin {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1. 获取用户输入信息
        Scanner input = new Scanner(System.in);
        System.out.println("Account");
        String account = input.nextLine();
        System.out.println("Password");
        String password = input.nextLine();

        // 2. 注册驱动
        // 方案一
        // DriverManager.registerDriver(new Driver());

        // 方案二
        // new Driver();

        // 字符串 -> 提取到外部的配置文件 -> 可以在不改变代码的情况下，完成数据库的切换
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2.获取数据库连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test01", "root", "ogihana77**");

        // Properties info = new Properties();
        // info.put("user", "root");
        // info.put("password", "ogihana77**");
        // Connection connection1 =
        // DriverManager.getConnection("jdbc://localhost:3306/test01", info);

        // Connection connection2 =
        // DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");

        // 3.创建发送 SQL 语句的 statement 对象
        Statement statement = connection.createStatement();

        // 4.发送 SQL 语句（1.编写 SQL 语句 2.发送 SQl 语句）
        String sql = "SELECT * FROM testtable_user WHERE account = '" + account + "' AND PASSWORD = '" + password + "';";
        // int i = statement.executeUpdate(sql);
        ResultSet resultSet = statement.executeQuery(sql);

        // 5.查询结果集解析 resultSet
        // while (resultSet.next()) {
        // int id = resultSet.getInt(1);
        // String account1 = resultSet.getString("account");
        // String password1 = resultSet.getString(3);
        // String nickname = resultSet.getString("nickname");
        // System.out.println(id+" | "+account1+" | "+password1+" | " + nickname);
        // }
        if (resultSet.next()) {
            System.out.println("Successed");
        } else {
            System.out.println("Failed");
        }
        input.close();
        resultSet.close();
        statement.close();
        connection.close();
    }
}
