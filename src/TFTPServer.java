import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


//default port 69
public class TFTPServer {
    ServerSocket serverSocket;
    Socket socket;
    InputStream sin;
    OutputStream sout;
    String typeOfCommand, sourcePath, destPath, input = new String();
    public void run(int port) {

        try
        {

            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
            int fileSize;

            while (true)
            {
                char ch = 0;
                while (ch!='\n') {
                    ch = (char)sin.read();
                    input+=ch;
                }
                System.out.println(input);
                String tmp[] = input.split(" " );
                typeOfCommand = tmp[0];
                sourcePath = tmp[1];
                destPath = tmp[2];

                if (typeOfCommand.equals("put")) {
                    put(sourcePath, destPath);
                    continue;
                }
                if (typeOfCommand.equals("get")) {
                    get(sourcePath, destPath);
                    continue;
                }
                showErrorMessage();

            }


        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    private  void put(String source, String dest){

        int size = 0;
        try {
            size  = sin.read();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        try {
            FileOutputStream writer = new FileOutputStream(new File(dest));
            byte[] bytes = sin.readAllBytes();
            System.out.println(1);
            for (byte b : bytes)
            {
                writer.write(b);
            }
            writer.close();
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    };
    private  void get(String source, String dest){};

    private void showErrorMessage()
    {
        System.out.println("Command is incorrect");
    }
}


class TFTPTester
{
    public static void main(String[] args) {
        TFTPServer telnetServer = new TFTPServer();
        telnetServer.run(7777);
    }
}

