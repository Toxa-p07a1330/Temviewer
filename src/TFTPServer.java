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

            String typeOfCommand = new String();        //get or put
            String sourcePath, destPath;
            int fileSize;


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

