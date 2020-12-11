import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
/**
 * A class to represent an administrator of the system. Inherits from User.
 */
public class Admin extends User {

    private static ArrayList<PropertyOwner> users;

    /**
     * 
     * @param username The username of the admin.
     * @param password The password of the admin.
     */
    public Admin(String username, String password) {
        super(username, password);
        users = findUsers();
    }

    public ArrayList<PropertyOwner> getUsers(){
        return users;
    }

    private ArrayList<PropertyOwner> findUsers(){
        String filename = "src/lib/users/userlogin.csv";
        ArrayList<PropertyOwner> result = new ArrayList<PropertyOwner>();

        try {
            final Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()){
                final String lineFromFile = scanner.nextLine();

                if(!lineFromFile.contains(",true") && !lineFromFile.contains("username,password")){
                    String[] s = lineFromFile.split(",");
                    result.add(new PropertyOwner(s[0], s[1]));
                }
            }

            return result;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }
}