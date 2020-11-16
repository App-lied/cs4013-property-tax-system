import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
public class User {

    private String username;
    private String password;
    protected User(String u, String p) {
        username = u;
        password = p;
        String filename = "lib/users/userlogin.csv";

        //try to create new csv file and username/password columns if file does not exist
        try {
            File userDataFile = new File(filename);
            if(userDataFile.createNewFile()){
                System.out.println("File created: " + userDataFile.getName());
                try{
                    PrintWriter writer = new PrintWriter(filename);
                    writer.write("username,password\n");
                    writer.close();
                } catch(FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
        }

        //try to write the new user data to file
        try{
            PrintWriter writer = new PrintWriter(filename);
            StringBuilder sb = new StringBuilder();
            sb.append(getUsername());
            sb.append(",");
            sb.append(getPassword());
            sb.append("\n");
            writer.write(sb.toString());
            writer.close();
        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getUsername() {
        return this.username;
    }

    protected String getPassword() {
        return this.password;
    }

}
