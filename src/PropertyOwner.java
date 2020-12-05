import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class PropertyOwner extends User{

    private ArrayList<Property> propertyList;
    
    public PropertyOwner(String username, String password){
        super(username, password);
        propertyList = findProperties(username);
    }

    public ArrayList<Property> getPropertyList(){
        return propertyList;
    }
    

    private ArrayList<Property> findProperties(String username){
        String filename = "src/lib/properties/property_info.csv";
        ArrayList<Property> result = new ArrayList<Property>();

        try {
            final Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()){
                final String lineFromFile = scanner.nextLine();

                if(lineFromFile.contains(username)){
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