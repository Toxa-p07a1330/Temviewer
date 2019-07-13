import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


//default port 69
public class TFTPServer {
    public void run(int port) {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            for (char i : "Connected\n\n".toCharArray())
                sout.write(i);
            System.out.println("Connected\n\n");

            while (true)
            {
                String path = "C:\\Users\\User\\Desktop\\SandBox\\1.txt";
                try {
                    Scanner scanner = new Scanner(new File(path));
                    while (scanner.hasNextLine())
                    {
                       String tmp = scanner.nextLine();
                       for(char i : tmp.toCharArray())
                           sout.write(i);
                       System.out.println(tmp);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }

        }
        catch (Exception e)
        {

        }
    }
}


class TFTPTester
{
    public static void main(String[] args) {
        TFTPServer telnetServer = new TFTPServer();
        telnetServer.run(6666);
    }
}

