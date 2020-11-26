//A utility class for some commonly used static method in the system

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
public class Utils {
    //A method that searches the passed file for the passed string and returns a boolean result
    public static boolean searchForString(File source, String s) throws FileNotFoundException{
        final Scanner scanner = new Scanner(source);

        while (scanner.hasNextLine()){
            final String lineFromFile = scanner.nextLine();
            if(lineFromFile.contains(s)){
                scanner.close();
                return true;
            }
        }

        scanner.close();
        return false;
    }
}
