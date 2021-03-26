package ct.gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class chetClient extends JFrame {

    JTextField username;  //昵称
    JTextField password; //密码
    JButton bok; //确认按钮
    JButton bcancle; //注册按钮

    class RegisterUser implements ActionListener {
          //监听按键事件
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==bok) {
                CUSTOMERFUNCTION customer1 = new CUSTOMERFUNCTION();
                if(customer1.login(username.getText(), password.getText())) {
                    chetClient chetClient = new chetClient();
                    if(chetClient.isVisible()) {
                        chetClient.setVisible(false);
                    }
                    FUNCTION function = new FUNCTION();
                    function.setVisible(true);
                }
            }

            if(e.getSource() == bcancle) {
               new Zhuce();
            }
        }

    }

//登录界面初始化
    chetClient() {
        this.setSize(300,200);
        this.setLocation(150,250);
        Container con = this.getContentPane();
        con.setLayout(new GridLayout(2,1));
        RegisterUser ru = new RegisterUser();

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        con.add(p1);
        con.add(p2);

        JLabel l1 = new JLabel("用户号：");
        JLabel l2 = new JLabel("密     码：");

       username = new JTextField(15);
       password = new JTextField(15);

         bok = new JButton("确认");
         bcancle = new JButton("注册");
         bok.addActionListener(ru);
         bcancle.addActionListener(ru);

        p1.add(l1);
        p1.add(username);
        p1.add(l2);
        p1.add(password);
        p2.add(bok);
        p2.add(bcancle);
    }
    //程序入口
    public static void main(String args[]){
        chetClient w = new chetClient();
        w.setVisible(true);
    }
}

