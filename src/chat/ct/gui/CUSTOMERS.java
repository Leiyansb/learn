package ct.gui;
public class CUSTOMERS{
    private String account;//账号
    private String password;//密码
    private String username;
    private int online;
    public CUSTOMERS() {
    }
    public CUSTOMERS(String account, String password,String username,int online) {
        super();
        this.account = account;
        this.password = password;
        this.username = username;
        this.online = online;
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public int getonline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
    @Override
    public String toString() {
        return "Customers [account=" + account + "]";
    }



}