package ct.gui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCTOOLS {
    public Connection conn = null;
    public ResultSet rs = null;
    public Properties prop = new Properties();
    public PreparedStatement ps = null;


    public Connection getConnection() {
        //声明Connection对象
        Connection conn = null;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String url = "jdbc:mysql://localhost:3306/chat";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "123456";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //连接MySQL数据库
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return conn;
    }


    //执行查询
    public ResultSet executeQuery(String sql, Object... obj) {

        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);// 给一个行的第几列设置对应的值
            }
            rs = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int executeUpdate(String sql, Object... obj) {
        int count = 0;
        conn = getConnection();
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                ps.setObject(i + 1, obj[i]);
            }
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if(ps!=null) {
                ps.close();
            }
            if(conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
