import java.net.Socket;

public class TelnetClient
{
    public void run(String ip, int port)
    {
        try {
            ip = "127.0.0.1";       //debug
            port = 6666;
            Socket socket = new Socket(ip, port);
        }
        catch (Exception e)
        {

        }
    }
}
