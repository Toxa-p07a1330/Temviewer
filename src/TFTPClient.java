import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TFTPClient
{
    public void run(String ip, int port)
    {
        try {

           /* Socket socket = new Socket(ip, port);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
*/
            String typeOfCommand = new String();        //get or put
            String sourcePath, destPath;
            int fileSize;

            Scanner keyboard = new Scanner(System.in);

            while (true)
            {
                String[] input = keyboard.nextLine().split(" ");
                typeOfCommand = input[0];
                sourcePath = input[1];
                destPath = input[2];

                if (typeOfCommand.equals("put")){
                    put(sourcePath, destPath);
                    continue;
                }
                if (typeOfCommand.equals("get")){
                    get(sourcePath, destPath);
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

    }
    private void get(String sourcePath, String destPath)
    {
                                                            //todo Доделать
    }
    private void showErrorMessage()
    {

    }
}

class TFTPClientTester
{
    public static void main(String[] args) {
        TFTPClient tftpClient = new TFTPClient();
        tftpClient.run("localhost", 6666);

    }
}
