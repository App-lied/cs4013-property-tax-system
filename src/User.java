import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

/**
 * A class to represent a generic User. The superclass for PropertyOwner and Admin.
 */
public class User {

    private String username;
    private String password;

    /**
     * 
     * @param u The username of the User.
     * @param p The password of the User.
     */
    protected User(String u, String p) {
        username = u;
        password = p;
    }

    /**
     * 
     * @param username The username to change to.
     */
    protected void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @param password The password to change to.
     */
    protected void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * @return The User's username.
     */
    protected String getUsername() {
        return this.username;
    }

    /**
     * 
     * @return The User's password.
     */
    protected String getPassword() {
        return this.password;
    }

    /**
     * Writes the User's details to file.
     */
    protected void writeToFile(){
        String filename = "src/lib/users/userlogin.csv";

        //try to create new csv file and username/password columns if file does not exist
        try {
            File userDataFile = new File(filename);
            if(userDataFile.createNewFile()){
                System.out.println("File created: " + userDataFile.getName());
                try{
                    FileWriter writer = new FileWriter(filename,true);
                    writer.write("username,password\n");
                    writer.close();
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
            }
        } catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        //try to write the new user data to file
        try{
            FileWriter writer = new FileWriter(filename,true);
            StringBuilder sb = new StringBuilder();
            sb.append(this.getUsername());
            sb.append(",");
            sb.append(this.getPassword());
            sb.append(",false");
            sb.append("\n");
            writer.write(sb.toString());
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}