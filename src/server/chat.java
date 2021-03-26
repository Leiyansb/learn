import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;



//若要实现创建一个账户能自动创建一个线程，则需要账户注册时在数据库加上分配端口字段，服务器遍历数据库端口字段，根据数目将封装的代码循环建立服务程序（时间太紧没有实现），现在需手动添加账户在服务器开启第n个线程



public  class chat {
    static byte[] bytes1 = new byte[1024];

    static byte[] bytes2 = new byte[1024];
//main函数启动线程
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket1 = null;
            serverSocket1 = new ServerSocket(15324);
            Socket socket1 = serverSocket1.accept();

            ServerSocket serverSocket2 = null;
            serverSocket2 = new ServerSocket(16324);
            Socket socket2 = serverSocket2.accept();

            server11 server11 = new server11(socket1, socket2);

            server22 server22 = new server22(socket2, socket1);


            server11.start();

            server22.start();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    //接收客户端1消息并且发送给客户端2
    static class server11 extends Thread {
        Socket socket = null;
        Socket socketm = null;

        public server11(Socket socket, Socket socketm) {
            this.socket = socket;
            this.socketm = socketm;
        }

        public void run() {
            try {


                InputStream inputStream = socket.getInputStream();
                while (true) {
                    byte bytes[] = new byte[1024];
                    inputStream.read(bytes);
                    bytes1 = bytes;
                    System.out.println(new String(bytes1));

                    OutputStream outputStream = socketm.getOutputStream();
                    outputStream.write(bytes1);
                    System.out.println("1发送给了2");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



    //接收客户端2的消息并且发送给客户端1
    static class server22 extends Thread {
        Socket socket = null;
        Socket socketn = null;

        public server22(Socket socket, Socket socketn) {
            this.socket = socket;
            this.socketn = socketn;
        }

        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                while (true) {
                    byte bytes[] = new byte[1024];
                    inputStream.read(bytes);
                    bytes2 = bytes;
                    System.out.println(new String(bytes2));

                    OutputStream outputStream = socketn.getOutputStream();
                    outputStream.write(bytes2);
                    System.out.println("2发送给了1");

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}





