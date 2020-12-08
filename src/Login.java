import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * A class to handle the terminal implementation of the system's login screen.
 */
public class Login {
    
    private static File source = new File("src/lib/users/userlogin.csv");
    private Scanner in;

    /**
     * The default constructor.
     */
    public Login(){
        in = new Scanner(System.in);
    }

    /**
     * The entry method for the flow of the class.
     * @throws IOException
     */
    public void run() throws IOException {
        System.out.println("Press 'L' to login. Press 'U' to create a new account. Press 'Q' to exit.");

        boolean running = true;

        while(running){
            String choice = in.nextLine().toLowerCase();
            
            if(choice.equals("l")){
                login();
            } else if(choice.equals("u")){
                createNewUser();
            } else if(choice.equals("q")){
                System.out.println("System exiting.");
                running = false;
            } 
        }

    }


    /**
     * A private method to handle the funcionality to login as a registered user.
     * @throws IOException
     */
    private void login() throws IOException{
        String enteredName, enteredPassword;
        System.out.print("Enter Username: ");
        enteredName = in.nextLine();
        System.out.print("\nEnter Password: ");
        enteredPassword = in.nextLine();

        //combine both the username and password so you can't login to a different account if it uses the same password
        String combined = enteredName + "," + enteredPassword + ",";
    
        //check if both the combined string exists, and check that it does not equal the first row in the csv that lays out the columns
        if(Utils.searchForString(source, combined) && !(combined.equals("username,password,"))){
            System.out.println("\nLogin Successful");

            //declare a new User object, then cast it depending on if it's noted as an admin or not in the file
            User loggedIn;

            if(Utils.searchForString(source, combined + ",true")){
                loggedIn = new Admin(enteredName, enteredPassword);
            } else {
                loggedIn = new PropertyOwner(enteredName, enteredPassword);
            }

            //create a new main system interface and login with the user
            MainSystem next = new MainSystem();
            next.run(loggedIn);
       
        } else{
            System.out.println("\nLogin Failed. Invalid Username or Password");
            run();
        }
    }

    /**
     * A private method to register a new user to the system, writing it to file.
     * @throws IOException
     */
    private void createNewUser() throws IOException{
        String enteredName, enteredPassword;
        System.out.print("Enter New Username: ");
        enteredName = in.nextLine();
        System.out.print("\nEnter New Password: ");
        enteredPassword = in.nextLine();

        //check if the entered username already exists, system does not allow for duplicate usernames
        if(!Utils.searchForString(source, enteredName)){
            PropertyOwner newPO = new PropertyOwner(enteredName, enteredPassword);
            newPO.writeToFile();
            System.out.println("Account created successfully.");
            run();

        } else {
            System.out.println("Account creation failed. Username is already in use.");
            run();
        }
    }
}
<<<<<<< HEAD
=======

>>>>>>> 9d684ffd54dae0796d79ff377327b1d7373a9166
