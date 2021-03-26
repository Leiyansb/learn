package ct.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;


public class FUNCTION extends JFrame {
    JButton[] btn = new JButton[5];


    class ExecuteFunction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
               if(e.getSource()==btn[0]) {
              ONLINE online = new ONLINE();
            }else if(e.getSource()==btn[1]) {
              CHATROOM chatroom = new CHATROOM();
            }else if(e.getSource()==btn[2]) {
             Update update = new Update();
            }else if(e.getSource()==btn[3]) {
              LIAOTIANJILU liaotianjilu = new LIAOTIANJILU();
            }else if(e.getSource()==btn[4]) {
                   Exit exit = new Exit();
            }
        }

    }

    //功能界面
    public FUNCTION() {
        super("功能区");
        this.setResizable(false);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        ExecuteFunction ef = new ExecuteFunction();

        JPanel function = new JPanel(new GridLayout(2,3));
        String[] str = new String[] {
                "在线的人","聊天室",
                "修改密码", "查看聊天记录",
                "退出"
        };
        for(int i = 0;i < str.length;i++) {
            btn[i] = new JButton(str[i]);
            function.add(btn[i]);
            btn[i].addActionListener(ef);
        }
        add(function);

    }
}

 //退出函数
class Exit{
    public Exit(){
        CUSTOMERFUNCTION cf = new CUSTOMERFUNCTION();
        CUSTOMERS customers = cf.getCustomers();
        cf.exit(customers);
        System.exit(-1);
    }

}


//显示在线的人类

class ONLINE extends JFrame {
    public ONLINE() {
        super("在线的人");
        this.setResizable(false);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        CUSTOMERFUNCTION cf = new CUSTOMERFUNCTION();
          if (cf.ONLINE() == true) {
            String name = cf.name;
            JLabel jlabel = new JLabel("在线的人: "+name);
            add(jlabel);
        }
    }
}


//修改密码类
class Update extends JFrame{
    JTextField[] text = new JTextField[2];
    CUSTOMERFUNCTION cf = new CUSTOMERFUNCTION();

    class Write implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            CUSTOMERS customer = cf.getCustomers();
            if(customer.getPassword().equals(text[0].getText())) {
                String newPassword = text[1].getText();
                if(cf.modify(customer, newPassword)) {
                    System.out.println("修改成功");
                    System.exit(-1);
                }else {
                    System.out.println("修改失败");
                }
            }else {
                System.out.println("输入密码和原密码不符");
            }

        }

    }
    public Update() {
        super("修改密码");
        this.setResizable(false);
        this.setSize(250,200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Update.Write write = new Update.Write();

        JPanel jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(2,2));

        JLabel jlabel1 = new JLabel("请输入旧密码");
        jpanel.add(jlabel1);
        text[0] = new JTextField();
        jpanel.add(text[0]);
        text[0].addActionListener(write);

        JLabel jlabel2 = new JLabel("请输入新密码");
        jpanel.add(jlabel2);
        text[1] = new JTextField();
        jpanel.add(text[1]);
        text[1].addActionListener(write);

        add(jpanel);
    }
}


//聊天室类，由于服务器ip固定，客户端ip不固定，但是防止客户端人变了ip没变的情况所以采用了账户的方法，所以没有采用输入ip的方式
class CHATROOM extends JFrame{

        JTextField[] text = new JTextField[1];
        CUSTOMERFUNCTION cf = new CUSTOMERFUNCTION();
        CUSTOMERS customers = cf.getCustomers();
        String a;
        String c;
        JTextArea jT1 = new JTextArea();

      Socket socket;

    {
        try {
            socket = new Socket("192.168.48.118", 15324);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    OutputStream outputStream;

    {
        try {
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    InputStream inputStream;

    {
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CHATROOM() {
        super("聊天室");

        this.setResizable(false);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        System.out.println(customers.getAccount());
        if(cf.CHATROOM(customers) == true) {
            System.out.println("hahahah");
            CHATROOM.Jieshou jieshou = new CHATROOM.Jieshou();
            CHATROOM.Write write = new CHATROOM.Write();
            text[0] = new JTextField();
            text[0].addActionListener(write);
            jieshou.start();
            write.start();


        }
    }




    class Jieshou extends  Thread{

        public void run() {
            try {
                JPanel jpanel = new JPanel();
                jpanel.setLayout(new GridLayout(1, 1));
                jpanel.add(jT1);
                JLabel jlabel3 = new JLabel("请输入信息");
                    System.out.println("hahahah");
                    jpanel.add(jlabel3);
                    jpanel.add(text[0]);
                    add(jpanel);

                FileOutputStream fos = null;
                File file = new File("D:/a.txt");
                if(!file.exists()){
                    file.createNewFile();
                }
                fos = new FileOutputStream("D:/a.txt", true);
                while (true) {
                    byte bytes[] = new byte[1024];
                    try {
                        inputStream.read(bytes);
                        fos.write(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    a = new String(bytes);
                    System.out.println(a);
                    jT1.append(a+"\n");
                    add(jpanel);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }


    class Write extends Thread implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {


                    FileOutputStream fos = null;
                File file = new File("D:/a.txt");
                if(!file.exists()){
                    file.createNewFile();
                }
                    fos = new FileOutputStream("D:/a.txt", true);
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd   HH:mm:ss ");
                    Date date = new Date(System.currentTimeMillis());
                    c = formatter.format(date) + cf.customer.getusername() + ":" + text[0].getText();
                    fos.write(c.getBytes());
                    outputStream.write(c.getBytes());
                    jT1.append(c+"\n");
            }
            catch (Exception e1){
                e1.printStackTrace();
            }
            text[0].setText("");
        }

        public void run (){
            Write write = new Write();
        }
    }




}


//查看聊天记录类
class LIAOTIANJILU extends JFrame{

    public LIAOTIANJILU() {
        super("聊天记录");
        String a = null;
        String b = " ";
        JTextArea jT2 = new JTextArea();
        jT2.setLineWrap(true);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("D:/a.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte bytes[]= new byte[50000000];
        try {
            fis.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        a = new String(bytes);
        b = b+a+"\n";
        b = b.replace(" ","");
        this.setResizable(false);
        this.setSize(1000,1000);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        jT2.append(b);
        add(jT2);

    }
}