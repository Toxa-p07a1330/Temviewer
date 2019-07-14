import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TelnetClient
{
    public void run(String ip, int port)
    {
        try {
            ip = "127.0.0.1";       //debug
            port = 6666;
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
        telnetClient.run("127.0.0.0", 6666);
    }
}