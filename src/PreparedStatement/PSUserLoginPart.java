package PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PSUserLoginPart {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 1. 获取用户输入信息
        Scanner input = new Scanner(System.in);
        System.out.println("Account");
        String account = input.nextLine();
        System.out.println("Password");
        String password = input.nextLine();

        // 2. ps 的数据库流程
        // 1. 注册成功
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2. 获取链接
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        /**
         *
         * statement
         * 
         * 1. 创建statement
         *
         * 2.拼接SQL语句
         *
         * 3.发送SQL语句,并且获取返回结果
         *
         * preparedstatement
         * 1. 编写SQL语句结果不包 含动态值部分的语句,动态值部分使用占位符?替代注意: ?只能替代动态值
         *
         * 2.创建preparedStatement,并且传入动态值
         * 了.动态值 占位符赋值?单独赋值即可
         * 4. 发送SQL语句即可,并获取返回结果
         */

        // 3. 编写 SQL 语句结果
        String sql = "select * from testtable_user where account = ? and password = ? ;";

        // 4. 创建预编辑 statement 并设置 SQL 语句结果
        PreparedStatement preparedstatement = connection.prepareStatement(sql);

        // 5.单独的占位符进行赋值
        /**
         * 参数1: index 占位符的位置从左向右数从1开始账号? 1
         * 参数2: object 占位符的值可以设置任何类型的数据,避免了我们拼接和类型更加丰富!
         */
        preparedstatement.setObject(1, account);
        preparedstatement.setObject(2, password);

        // 6. 发送 SQL 语句，并获取返回结果
        // statement . executeUpdate 1 executeQuery(String sql);
        // preparedStatement. executeUpdate 1 executeQuery(); Todo 因为它已经知道语句,知道语句动态值!
        ResultSet resultSet = preparedstatement.executeQuery();

        // 7. 结果集解析
        if (resultSet.next()) {
            System.out.println("Successed");
        } else {
            System.out.println("Failed");
        }

        input.close();
        resultSet.close();
        connection.close();
    }
}
