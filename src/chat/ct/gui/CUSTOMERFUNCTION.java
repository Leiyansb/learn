package ct.gui;


import java.sql.ResultSet;
import java.sql.SQLException;

public class CUSTOMERFUNCTION implements CUSTOMERSINTERFACE {
    JDBCTOOLS tools = new JDBCTOOLS();
    CUSTOMERS customer = new CUSTOMERS();
    static String account;
    String name =" ";
  // 数据库检测在线字段
    @Override
    public boolean ONLINE() {
        String sql = "select username from customers where online = 1";
        ResultSet rs = tools.executeQuery(sql);
        try{
            while(rs.next()){
                name = name+" "+rs.getString(1);
                System.out.println(rs.getString(1));
            }
            tools.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

//推出时数据库在线字段重置
    @Override
    public boolean exit(CUSTOMERS customers){
        account= customers.getAccount();
        String sql = "update customers set online = 0 where account = "+account;
        tools.executeUpdate(sql);
        return true;
    }



//数据库检测在线人员才能加入聊天室
    @Override
    public boolean CHATROOM(CUSTOMERS customer) {
        String sql = "select account from customers where online = 1";
        ResultSet rs = tools.executeQuery(sql);
        try{
            while(rs.next()){
                System.out.println(rs.getString(1));
                if(customer.getAccount().equals(rs.getString(1))){
                    System.out.println("可以加入");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    //修改数据库密码字段
    @Override
    public boolean modify(CUSTOMERS customer, String password) {
        String sql = "update customers set user_password = "+password+" where account = "+customer.getAccount();
        tools.executeUpdate(sql);
        return true;
    }



    //登录检测账号密码是否正确
    @Override
    public boolean login(String account, String password) {
        String sql = "select * from customers where account = "+account;
        String sql1 = "update customers set online = 1 where account = "+account;
        ResultSet rs = tools.executeQuery(sql);
        CUSTOMERFUNCTION.account = account;

        try {
            while (rs.next()) {
                if (account.equals(rs.getString("account")) && password.equals(rs.getString("user_password"))) {
                    tools.executeUpdate(sql1);
                    return true;
                } else {
                    System.out.println("账号密码错误");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    //提取用户本身
    public CUSTOMERS getCustomers() {//important
        String sql = "select * from customers where account = " + account;
        ResultSet rs = tools.executeQuery(sql);
        try {
            while (rs.next()) {
                customer.setAccount(rs.getString("account"));
                customer.setPassword(rs.getString("user_password"));
                customer.setOnline(rs.getInt("online"));
                customer.setusername(rs.getString("username"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;

    }


}
