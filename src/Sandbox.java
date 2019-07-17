import java.io.*;
import java.util.Scanner;

public class Sandbox
{
    public static void main(String[] args) {
        int count = -2;
        File file = new File("C:\\Users\\User\\Desktop\\SandBox\\1.txt");
        try {
        int size = (int)file.length();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                String tmp = scanner.nextLine();
                for (char ch : tmp.toCharArray()){
                    System.out.print(ch);
                    count++;
                }
                count+=2;
                System.out.print("\n");
                System.out.print("\r");
            }
        }
        catch (Exception e) {

        }

        System.out.println("\n\n"+count + "\n"+file.length());
    }
}

