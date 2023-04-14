package transactionnew;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import utils.JdbcUtilsV2;

public class BankService {

    @Test
    public void start() throws SQLException, Exception {
        transfer("erdan", "erdandan", 500);
    }

    public void transfer(String addAccount, String subAccount, int money) throws Exception, SQLException {
        BankDao bankDao = new BankDao();
        Connection connection = JdbcUtilsV2.getConnection();
        try {
            // 开启事务
            // 关闭事务提交
            connection.setAutoCommit(false);
            // 执行数据库动作
            bankDao.add(addAccount, money);
            System.out.println("-----");
            bankDao.sub(subAccount, money);

        } catch (Exception e) {
            // 事务回滚
            connection.rollback();

            throw e;
        } finally {
            connection.close();
        }

    }
}
