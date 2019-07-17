import java.io.*;
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
            inicialization();
            Scanner keyboard = new Scanner(System.in);
            while (true) {
                getAndParseInput(keyboard);
                sendCommand();
                selector();
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

            InputStream scanner = new FileInputStream(src);
            byte[] bytes = scanner.readAllBytes();
            for (byte b : bytes)
                sout.write(b);
            sout.close();
            System.out.println("\nDone\n");
            }

        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void get(String sourcePath, String destPath){           //todo написать логику скачивания клииентом файла с сервера
    }
    private void showErrorMessage()
    {
        System.out.println("Command is incorrect");
    }
    private void inicialization()
    {
        try {
        socket = new Socket(ip, port);
        sout = socket.getOutputStream();
        sin = socket.getInputStream();
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    private void getAndParseInput(Scanner scanner)
    {
        try {

            input = scanner.nextLine().split(" ");
            typeOfCommand = input[0];
            sourcePath = input[1];
            destPath = input[2];
        }
        catch (Exception e) {
            System.out.println("Bad input");
        }
    }
    private void sendCommand()
    {
        try {

            for (String str : input) {
                for (char ch : str.toCharArray()) {
                    sout.write(ch);
                }
                sout.write(' ');
            }
            sout.write('\n');
        }
        catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    private void selector()
    {
        do{
            if (typeOfCommand.equals("get")){
                get(sourcePath, destPath);
                break;
            }
            if (typeOfCommand.equals("put")){
                put(sourcePath, destPath);
                break;
            }
            showErrorMessage();
        }
        while (false);
    }
}

class TFTPClientTester
{
    public static void main(String[] args) {
        TFTPClient tftpClient = new TFTPClient();
        tftpClient.run("localhost", 7777);

    }
}
