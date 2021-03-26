package ct.gui;

import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Zhuce extends JFrame {
    Label label[] = new Label[6];
    JTextField text[] = new JTextField[6];
    CUSTOMERS customer = new CUSTOMERS();
    JButton btn1 = null;
    JButton btn2 = null;
    OPENACCOUNT open = new OPENACCOUNT();
//注册逻辑函数
    class ZhuceFunc implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            customer.setusername(text[0].getText());
            customer.setPassword(text[1].getText());
            if (e.getSource() == btn1) {
                if (open.OpenAccount(customer)) {
                    class openSuccess extends JFrame {
                        public openSuccess() {
                            this.setSize(500, 100);
                            this.setVisible(true);
                            JPanel jp1 = new JPanel(new GridLayout(1, 2));
                            JLabel label = new JLabel("恭喜您注册成功，您的账号为:");
                            jp1.add(label);
                            JTextField jtext = new JTextField();
                            jtext.setText(customer.getAccount());
                            jp1.add(jtext);
                            this.add(jp1);
                        }
                    }
                    new openSuccess();
                } else {
                    System.out.println("注册失败");
                }
            }
        }
    }
 //注册页面
    public Zhuce() {
        super("注册");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(7, 2));
        Zhuce.ZhuceFunc zf = new Zhuce.ZhuceFunc();

        JLabel label0 = new JLabel("昵称");
        text[0] = new JTextField();
        JLabel lable1 = new JLabel("密码");
        text[1] = new JTextField();
        btn1 = new JButton("提交");
        btn1.addActionListener(zf);
        btn2 = new JButton("重置");
        btn2.addActionListener(zf);
            jpanel.add(label0);
            jpanel.add(text[0]);
            text[0].addActionListener(zf);
        jpanel.add(lable1);
        jpanel.add(text[1]);
        text[1].addActionListener(zf);

        jpanel.add(btn1);
        jpanel.add(btn2);

        this.add(jpanel);
    }
}
