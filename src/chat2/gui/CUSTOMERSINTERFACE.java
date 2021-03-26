package gui;

public interface CUSTOMERSINTERFACE {
    public boolean ONLINE();
    public boolean CHATROOM(CUSTOMERS customer);
    public boolean modify(CUSTOMERS customer, String password);
    public boolean login(String account,String password);
    public boolean exit(CUSTOMERS customers);
}