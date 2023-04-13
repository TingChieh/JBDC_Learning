import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) {   //下面方法有不同的异常，我直接抛出一个大的异常

        Connection con = null;
        Statement stat = null;

        try{
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2、获取数据库的连接对象
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys", "root", "ogihana77**");

            //3、定义sql语句
            String sql = "insert into test01 value('10004','李白',21,59)";

            //4、获取执行sql语句的对象
            stat = con.createStatement();

            //5、执行sql并接收返回结果
            int count = stat.executeUpdate(sql);

            //6、处理结果
            System.out.println(count);
        }catch (Exception e){
            e.printStackTrace();
        }finally {

            if (con != null){   //避免空指针异常
                //7、释放资源
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null){  //避免空指针异常
                //7、释放资源
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}