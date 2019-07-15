import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;



//default port 23
public class TelnetServer {


    boolean isRunning = true;
    public void run(int port)    {

        (new Thread(()->{ try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к вышеуказанному порту
            System.out.println("Port "+port+" is waiting for connections");

            Socket socket = ss.accept();
            System.out.println("Connected");
            System.out.println();

            // Берем входной и выходной потоки сокета, теперь можем получать и отсылать данные клиенту.
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();

            Map<String, String> env = System.getenv();
            String wayToTemp = env.get("TEMP") + "\\tmp.txt";
            for (int i :("Connected\n\n\r".toCharArray()))
                sout.write(i);
            sout.flush();

            String buffer = new String();
            while (isRunning) {

                int intReader = 0;
                while ((char) intReader != '\n') {
                    intReader = sin.read();
                    buffer += (char) intReader;
                }


                buffer = buffer.substring(0, buffer.length()-2);

                try {
                    ProcessBuilder pb = new ProcessBuilder(buffer);
                    File temp = new File (wayToTemp);

                    try {
                        pb.redirectError(temp);
                        pb.redirectOutput(temp);
                        pb.start();
                        Thread.sleep(100);
                    }
                    catch (Exception e)
                    {
                        Runtime.getRuntime().exec("cmd /c copy NUL "+wayToTemp);
                        if (buffer.indexOf('>')!=-1)
                            Runtime.getRuntime().exec("cmd /c " + buffer);      //for writing only
                        else
                            Runtime.getRuntime().exec("cmd /c chcp 65001 && " + buffer + " > " + wayToTemp + " 2>&1");
                        Thread.sleep(100);
                    }
                    Scanner scanner = new Scanner(new BufferedReader(new FileReader(temp)));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        for (char ch : line.toCharArray())
                        {
                            sout.write(ch);
                            //System.out.print(ch);
                        }
                        sout.write('\r');
                        sout.write('\n');
                        //System.out.println();
                    }
                    sout.flush();
                    Runtime.getRuntime().exec("cmd /c copy NUL "+wayToTemp);
                }
                catch (Exception e)
                {
                    System.out.println("Error: "+e.getMessage());
                }
                System.out.println(buffer);
                buffer = "";

            }
        }
        catch(Exception x)
        {
            System.out.println(x.getMessage());
        }})).start();

    }

    public void stop()
    {
        System.out.println("Server was stopped");
        this.isRunning = false;
    }
}

class TelnetTester
{
    public static void main(String[] args) {
       TelnetServer telnetServer = new TelnetServer();
       telnetServer.run(6666);
       TelnetServer telnetServer1 = new TelnetServer();
       telnetServer1.run(7777);
    }
}