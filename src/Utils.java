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

    /**
     * A method to check if a given string contains only digits.
     * @param s The string to be checked.
     * @return Whether or not the string contains only numbers.
     */
    public static boolean containsOnlyNumbers(String s){
        int i;        
        for(i = 0; i < s.length(); i++){
            if (!(s.charAt(i) >= '0'
                && s.charAt(i) <= '9')) { 
                return false;
            } 
            
        } 
        return true;      
       
    }
}
