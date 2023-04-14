package transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class BankService {

    @Test
    public void start() throws SQLException, Exception {
        transfer("erdan", "erdandan", 500);
    }

    public void transfer(String addAccount, String subAccount, int money) throws Exception, SQLException {
        BankDao bankDao = new BankDao();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///test01?user=root&password=ogihana77**");
        try {
            // 开启事务
            // 关闭事务提交
            connection.setAutoCommit(false);
            // 执行数据库动作
            bankDao.add(addAccount, money, connection);
            System.out.println("-----");
            bankDao.sub(subAccount, money, connection);

        } catch (Exception e) {
            // 事务回滚
            connection.rollback();

            throw e;
        } finally {
            connection.close();
        }

    }
}
