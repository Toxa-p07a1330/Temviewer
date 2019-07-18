import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;


//default port 69
public class TFTPServer {
    ServerSocket serverSocket;
    Socket socket;
    InputStream sin;
    OutputStream sout;
    int port;
    String typeOfCommand, sourcePath, destPath, input = new String();
    public void run(int port) {
            this.port = port;
            incialization();
            while (true) {
                getAndParseInput();
                selector();
            }
    }
    private  void put(String source, String dest){
            writeToFileFromSocket();
            System.out.print("\nDone\n");
    };
    private  void get(String source, String dest){
        File sending = new File(source);
        try {
            FileInputStream readFromFile = new FileInputStream(sending);
            byte[] arr = readFromFile.readAllBytes();
            byte[] bytes = ByteBuffer.allocate(Long.SIZE / Byte.SIZE).putLong(sending.length()).array();
            for (int i = 0; i<Long.SIZE / Byte.SIZE; i++)
                sout.write(bytes[i]);
            sout.flush();
            for (byte b : arr)
                sout.write(b);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    };



    private void showErrorMessage()
    {
        System.out.println("Command is incorrect");
    }

    private void  incialization()
    {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            sin = socket.getInputStream();
            sout = socket.getOutputStream();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    private void writeToFileFromSocket()
    {
        try {
            FileOutputStream writer = new FileOutputStream(new File(destPath));
            byte[] bytes = sin.readAllBytes();
            for (byte b : bytes) {
                writer.write(b);
            }
            writer.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void getAndParseInput() {
        try {
            char ch = 0;
            while (ch != '\n') {
                ch = (char) sin.read();
                input += ch;
            }
            System.out.println(input);
            String tmp[] = input.split(" ");
            typeOfCommand = tmp[0];
            sourcePath = tmp[1];
            destPath = tmp[2];
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    private void selector()
    {
        do {
            if (typeOfCommand.equals("put")) {
                put(sourcePath, destPath);
                break;
            }
            if (typeOfCommand.equals("get")) {
                get(sourcePath, destPath);
                break;
            }
            showErrorMessage();
        }
        while (false);
    }
}


class TFTPTester
{
    public static void main(String[] args) {
        TFTPServer telnetServer = new TFTPServer();
        telnetServer.run(7777);
    }
}

