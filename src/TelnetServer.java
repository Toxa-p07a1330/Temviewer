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


                final String inputToSubThread = "cmd /c " + buffer.substring(0, buffer.length()-2) + " 2>&1";


                new Thread(()-> {
                    try {

                        Process p = Runtime.getRuntime().exec(inputToSubThread);
                        InputStream out = p.getInputStream();
                        Scanner fromProcess = new Scanner(out);
                        try {

                            while (fromProcess.hasNextLine()) {
                                String temp = fromProcess.nextLine();
                                System.out.println(temp);
                                for (char i : temp.toCharArray())
                                    sout.write(i);
                                sout.write('\n');
                                sout.write('\r');
                            }
                        }
                        catch (Exception e)
                        {
                            String output = "Something gets wrong... Err code: "+ e.getStackTrace();
                            System.out.println(output);
                            for (char i : output.toCharArray())
                                sout.write(i);
                            sout.write('\n');
                            sout.write('\r');
                        }

                        p.getErrorStream().close();
                        p.getOutputStream().close();
                        p.getInputStream().close();
                        sout.flush();

                    }
                    catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }).start();
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