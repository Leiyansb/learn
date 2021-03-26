package gui;

import java.sql.*;
import java.util.Properties;


public class JDBCTOOLS {
    public Connection conn = null;
    public ResultSet rs = null;
    public Properties prop = new Properties();
    public PreparedStatement ps = null;


    public Connection getConnection() {
        Connection conn = null;
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/chat";
        String user = "root";
        String password = "123456";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        }
        catch (Exception e ){
            e.printStackTrace();
        }
        return conn;
    }



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
