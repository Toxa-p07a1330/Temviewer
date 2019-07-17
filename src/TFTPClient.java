import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TFTPClient
{
    String[] input;
    Socket socket;
    InputStream sin;
    OutputStream sout;
    String typeOfCommand, sourcePath, destPath;
    String ip;
    int port;
    int fileSize;
    public void run(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
        try {

            socket = new Socket(ip, port);
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            Scanner keyboard = new Scanner(System.in);

            while (true)
            {
                try {

                    input = keyboard.nextLine().split(" ");
                    typeOfCommand = input[0];
                    sourcePath = input[1];
                    destPath = input[2];
                }
                catch (Exception e)
                {
                    System.out.println("Bad input");
                }
                if (typeOfCommand.equals("get")){
                    get(sourcePath, destPath);
                    continue;
                }
                if (typeOfCommand.equals("put")){
                    put(sourcePath, destPath);
                    continue;
                }

                showErrorMessage();


            }


            }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
    private  void put(String sourcePath, String destPath)
    {

        File src = new File(sourcePath);
        try {
            for (String str : input){
                for (char ch : str.toCharArray()) {
                    sout.write(ch);
                    System.out.print(ch);
                }
                sout.write(' ');
                System.out.println();
            }
            sout.write('\n');
            System.out.println();
            sout.write((int)(src.length()));
            InputStream scanner = new FileInputStream(src);
            byte[] bytes = scanner.readAllBytes();
            for (byte b : bytes)
            {
                sout.write(b);
            }
            sout.close();
            socket = new Socket(ip, port);
            sout = socket.getOutputStream();
            sin = socket.getInputStream();
            System.out.println("\nDone\n");

            }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void get(String sourcePath, String destPath)
    {

    }
    private void showErrorMessage()
    {
        System.out.println("Command is incorrect");
    }
}

class TFTPClientTester
{
    public static void main(String[] args) {
        TFTPClient tftpClient = new TFTPClient();
        tftpClient.run("localhost", 7777);

    }
}
