package gui;


import java.util.Random;

public class OPENACCOUNT {
    JDBCTOOLS tools = new JDBCTOOLS();
    public boolean OpenAccount(CUSTOMERS customer) {

        customer.setAccount((accountNum()+""));
        String sql = "insert into customers values(?,?,?,?)";
        int count = tools.executeUpdate(sql,customer.getAccount(),customer.getPassword(),customer.getusername(),customer.getonline());
        if(count > 0) {
            return true;
        }
        return false;
    }
    public long accountNum() {
        StringBuffer base = new StringBuffer();
        Random random = new Random();
        base.append("6");
        for(int i = 0;i < 18;i++) {
            int j = random.nextInt(10);
            base.append(j);
        }
        return Long.parseLong(base.toString());
    }
}