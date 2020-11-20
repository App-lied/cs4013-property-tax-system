import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login {
    
    private static File source = new File("src/lib/users/userlogin.csv");
    private Scanner in;

    public Login(){
        in = new Scanner(System.in);
    }

    public void run() throws IOException {
        System.out.println("Press 'L' to login. Press 'U' to create a new account. Press 'Q' to exit.");

        String choice = in.nextLine().toLowerCase();

        if(choice.equals("l")){
            login();
        } else if(choice.equals("u")){
            createNewUser();
        } else if(choice.equals("q")){
            System.out.println("System exiting.");
        } else{
            run();
        }

    }

    private void login() throws IOException{
        String enteredName, enteredPassword;
        System.out.print("Enter Username: ");
        enteredName = in.nextLine();
        System.out.print("\nEnter Password: ");
        enteredPassword = in.nextLine();

        String combined = enteredName + "," + enteredPassword;
    
        if(searchForString(combined) && !(combined.equals("username,password"))){
            System.out.println("\nLogin Successful");
            in.close();
        } else{
            System.out.println("\nLogin Failed. Invalid Username or Password");
            run();
        }
    }

    private void createNewUser() throws IOException{
        String enteredName, enteredPassword;
        System.out.print("Enter New Username: ");
        enteredName = in.nextLine();
        System.out.print("\nEnter New Password: ");
        enteredPassword = in.nextLine();

        if(!searchForString(enteredName)){
            PropertyOwner newPO = new PropertyOwner(enteredName, enteredPassword);
            System.out.println("Account created successfully.");
            run();

        } else {
            System.out.println("Account creation failed. Username is already in use.");
            run();
        }
    }


    private static boolean searchForString(String s) throws FileNotFoundException{
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