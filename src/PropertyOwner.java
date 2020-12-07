import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

/**
 * A class to represent a property owner in the system.
 * Inherits from the User class.
 */
public class PropertyOwner extends User{

    private ArrayList<Property> propertyList;
    
    /**
     * A constructor that takes two parameters.
     * @param username The name of the user.
     * @param password The password of the user.
     */
    public PropertyOwner(String username, String password){
        super(username, password);
        propertyList = findProperties(username);
    }

    /**
     * 
     * @return The list of properties that this PropertyOwner has registered in the system.
     */
    public ArrayList<Property> getPropertyList(){
        return propertyList;
    }
    
    /**
     * A private method to find all of the PropertyOwner's properties and give it back as an ArrayList.
     * @param username The name of the user to search for in property_info.csv
     * @return The list of the user's properties.
     */
    private ArrayList<Property> findProperties(String username){
        String filename = "src/lib/properties/property_info.csv";
        ArrayList<Property> result = new ArrayList<Property>();

        try {
            final Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()){
                final String lineFromFile = scanner.nextLine();

                if(lineFromFile.contains("," + username)){
                    String[] s = lineFromFile.split(",");
                    result.add(new Property(s[0], s[1], s[2], 
                    Double.parseDouble(s[3]), Integer.parseInt(s[4]), (s[5].equals("yes") ? true : false)));
                }
            }

            return result;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}