package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDao {

    public int executeUpdate (String sql, Object... params) throws SQLException {
        Connection connection = JdbcUtilsV2.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 5. 占位符赋值
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i]);
        }
        // 6. 发送SQL语句
        // DML 类型
        int rows = preparedStatement.executeUpdate();

        preparedStatement.close();

        if (connection.getAutoCommit()) {
            // 没有开启事务
            // 没有开启事务 正常回收连接
            JdbcUtilsV2.freeConnection();
        }

        // connection.setAutoCommit(false); // 开启事务 不要管连接即可 业务层处理

        return rows;
    }
    
}
