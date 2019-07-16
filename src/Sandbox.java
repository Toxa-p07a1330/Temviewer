import java.io.*;
import java.util.Scanner;

public class Sandbox
{
    public static void main(String[] args) {


        final String wayToTemp = new String("C:\\Temp\\temporary.txt");
        Scanner keyboard  = new Scanner(System.in);
        while (true)
        {
            final String buffer = "cmd /c "+keyboard.nextLine();
            new Thread(()-> {
                try {
                    ProcessBuilder pb = new ProcessBuilder(buffer);
                    File temp = new File(wayToTemp);
                    Process p = Runtime.getRuntime().exec(buffer);
                    InputStream out = p.getInputStream();
                    InputStream err = p.getErrorStream();
                    Scanner errs = new Scanner(err);
                    Scanner fromProcess = new Scanner(out);
                    while (fromProcess.hasNextLine()) {
                        System.out.println(fromProcess.nextLine());
                    }
                    while (errs.hasNextLine()) {
                        System.out.println(errs.nextLine());
                    }
                    p.getErrorStream().close();
                    p.getOutputStream().close();
                    p.getInputStream().close();


                }
               /* try {
                    pb.redirectError(temp);
                    pb.redirectOutput(temp);
                    pb.start();
                    Thread.sleep(100);
                    System.out.println(1);              //debug
                }
                catch (Exception e)
                {
                    Runtime.getRuntime().exec("cmd /c copy NUL "+wayToTemp);
                    if (buffer.indexOf('>')!=-1)
                        Runtime.getRuntime().exec("cmd /c " + buffer);      //for writing only
                    else
                        Runtime.getRuntime().exec("cmd /c chcp 65001 && " + buffer + " > " + wayToTemp + " 2>&1");
                    Thread.sleep(100);

                    Scanner reader = new Scanner(new File(wayToTemp));
                    while (reader.hasNextLine())
                        System.out.println(reader.nextLine());
                }
                Runtime.getRuntime().exec("cmd /c copy NUL "+wayToTemp);
            }*/ catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }).start();
        }
    }
}

