import java.io.*;
import java.util.Scanner;

public class Sandbox
{
    public static void main(String[] args) {


        final String wayToTemp = new String("C:\\Temp\\temporary.txt");
        Scanner keyboard  = new Scanner(System.in);
        while (true);
        /*{
            final String inputToSubThread = "cmd /c "+keyboard.nextLine();
            new Thread(()-> {
                try {

                    Process p = Runtime.getRuntime().exec(inputToSubThread);
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
                catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }).start();
        }*/
    }
}

