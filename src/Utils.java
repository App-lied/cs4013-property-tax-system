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
}
