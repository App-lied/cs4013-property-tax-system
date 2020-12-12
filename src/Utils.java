import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
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

    public static ArrayList<Payment> findPaymentsByRoutingKey(String key){
        String filename = "src/lib/properties/payment_info.csv";
        ArrayList<Payment> result = new ArrayList<Payment>();

        try {
            final Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()){
                final String lineFromFile = scanner.nextLine();

                if(lineFromFile.contains(key) && !lineFromFile.contains("year")){
                    String[] s = lineFromFile.split(",");
                    result.add(new Payment(Integer.parseInt(s[0]), Double.parseDouble(s[1]),
                    (s[2].equals("true") ? true : false)));
                }
            }

            return result;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
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

    /**
     * A method to replace all "\n" with 10 spaces
     * @param s The string to be converted.
     * @return The converted string without line breakers.
     */
    public static String removeLineBreakers(String s){
        s = s.replaceAll("\n", "          ");
        return s;
    }
}
