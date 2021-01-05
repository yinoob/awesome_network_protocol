import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(2000);

        System.out.println("服务端准备就绪");
        System.out.println("客户端信息:"+server.getInetAddress()+"P"+server.getLocalPort());


        for(;;) {
            Socket client = server.accept();
            ClientHandler clientHandler = new ClientHandler(client);
            clientHandler.start();
        }

    }

    private static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag=true;

        ClientHandler(Socket socket){
            this.socket=socket;
        }

        @Override
        public void run(){
            super.run();
            System.out.println("新客户端连接"+socket.getInetAddress()+"P:"+socket.getPort());

            try{
                PrintStream socketOutput=new PrintStream(socket.getOutputStream());
                BufferedReader socketInput=new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                do{
                    String str=socketInput.readLine();
                    if("bye".equalsIgnoreCase(str)){
                        flag=false;
                        socketOutput.println("bye");
                    }else{
                        System.out.println(str);
                        socketOutput.println("回送"+str.length());
                    }
                }while(flag);
                socketInput.close();
                socketOutput.close();
            }catch (Exception e){
                System.out.println("连接异常断开");
            }finally {
                //连接关闭
                try {
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            System.out.println("客户端已退出"+socket.getInetAddress()+"P:"+socket.getPort());
        }

    }
}
