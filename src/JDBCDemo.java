
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SuppressWarnings({ "all" })
public class JDBCDemo {
    public static void main(String[] args) {
        try {
            // 注册驱动程序类
            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("加载驱动成功");
            // 连接指定数据库，并指出数据库的用户名和密码
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ ?user=root", "root", "ogihana77**");
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            System.out.println("驱动找不到");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("数据库连接不成功");
            e.printStackTrace();
        }
    }
}
