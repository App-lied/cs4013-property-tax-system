import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * A utility class for static methods used throughout the system.
 */
public class Utils {

    /**
     * A method to search for a given string in a file.
     * @param source The file to search in.
     * @param s The string to search for.
     * @return Whether or not the string was found.
     * @throws FileNotFoundException
     */
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
