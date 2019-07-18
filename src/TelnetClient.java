import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TelnetClient
{
    Thread reader, writer;
    public void run(String ip, int port)
    {
        try {
            Socket socket = new Socket(ip, port);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            Scanner keyboard = new Scanner(System.in);
            reader = new Thread(()->read(keyboard, sout));
            writer = new Thread(()->write(sin));
            reader.start();
            writer.start();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void run(String ip)
    {
        run(ip, 23);
    }

    private void read(Scanner keyboard, OutputStream sout)
    {
        try {
            String input = new String();
            while (true) {
                input = keyboard.nextLine();
                for (char i : (input + " \n").toCharArray())
                    sout.write(i);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void write(InputStream sin)
    {
        try {
            int tmp;
            while (true){
                tmp = sin.read();
                System.out.print((char)tmp);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    public void stop()
    {
        reader.stop();
        writer.stop();
    }
}

class TelnetClientTester
{
    public static void main(String[] args) {
        TelnetClient telnetClient = new TelnetClient();
        Scanner keyboard = new Scanner(System.in);
        int port;
        String ip = new String();
        ip = keyboard.nextLine();
        port = keyboard.nextInt();
        telnetClient.run(ip, port);
    }
}