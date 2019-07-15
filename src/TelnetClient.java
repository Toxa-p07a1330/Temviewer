import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TelnetClient
{
    public void run(String ip, int port)
    {
        try {

            Socket socket = new Socket(ip, port);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();


            Scanner keyboard = new Scanner(System.in);
            new Thread(()->{
                try {
                    String input = new String();
            while (true) {
                input = keyboard.nextLine();
                for (char i : (input + " \n").toCharArray())
                    sout.write(i);
            }
                }
            catch (Exception e)
            {

            }
            }).start();

            new Thread(()->{
                try {
                    int tmp;
                    while (true){
                    tmp = sin.read();
                    System.out.print((char)tmp);
                    }
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }).start();
    }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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