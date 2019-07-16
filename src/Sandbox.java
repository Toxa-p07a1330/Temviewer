import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Sandbox
{
    public static void main(String[] args) {


        String wayToTemp = new String();
        String buffer = new String();
        wayToTemp = "C:\\Temp\\temporary.txt";
        Scanner keyboard  = new Scanner(System.in);
        while (true)
        {
            buffer = keyboard.nextLine();
            try {
                ProcessBuilder pb = new ProcessBuilder(buffer);
                File temp = new File (wayToTemp);


                try {
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
            }
            catch (Exception e)
            {
                System.out.println("Error: "+e.getMessage());
            }
        }
    }
}

