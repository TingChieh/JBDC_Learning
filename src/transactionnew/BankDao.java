package transactionnew;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.JdbcUtilsV2;


public class BankDao {
    public void add(String account, int money) throws Exception, SQLException {
        Connection connection = JdbcUtilsV2.getConnection();
        String sql = "update t_bank set money = money + ? where account = ? ;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, money);
        statement.setString(2, account);

        statement.executeUpdate();

        statement.close();
        connection.close();

        System.out.println("加钱成功");
    }

    public void sub(String account, int money) throws Exception, SQLException {
        Connection connection = JdbcUtilsV2.getConnection();
        String sql = "update t_bank set money = money - ? where account = ? ;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setObject(1, money);
        statement.setString(2, account);

        statement.executeUpdate();

        statement.close();

        System.out.println("减钱成功");
    }
}
