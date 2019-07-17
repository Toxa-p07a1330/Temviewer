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
    int fileSize;
    public void run(String ip, int port)
    {
        try {

            socket = new Socket(ip, port);
            sin = socket.getInputStream();
            sout = socket.getOutputStream();

            Scanner keyboard = new Scanner(System.in);

            while (true)
            {
                input = keyboard.nextLine().split(" ");
                typeOfCommand = input[0];
                sourcePath = input[1];
                destPath = input[2];

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
            System.out.println(2);
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
            System.out.println("Done");

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
