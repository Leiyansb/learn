package ct.gui;

public interface CUSTOMERSINTERFACE {
    public boolean ONLINE();//在线的人
    public boolean CHATROOM(CUSTOMERS customer);//聊天室
    public boolean modify(CUSTOMERS customer,String password);//修改密码
    public boolean login(String account,String password);//登录
    public boolean exit(CUSTOMERS customers); //退出
}