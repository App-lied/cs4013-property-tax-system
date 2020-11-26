//a class to handle the terminal implementation of the main user interface

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MainSystem {
    
    private static File source = new File("src/lib/properties/property_info.csv");
    private Scanner in;

    public MainSystem(){
        in = new Scanner(System.in);
    }

    public void run(User user) throws IOException{
        if(user instanceof Admin){
            runAdmin(user);
        } else {
            runPropertyOwner(user);
        }
    }

    private void runAdmin(User user) throws IOException{
    }

    private void runPropertyOwner(User user) throws IOException{

        System.out.println("Press 'P' to register a new property. Press 'Q' to exit.");
        boolean running = true;
        while(running){
            String choice = in.nextLine().toLowerCase();

            if(choice.equals("p")){
                registerProperty(user);
            } else if(choice.equals("q")){
                System.out.println("System exiting.");
                running = false;
                
            }
        }
        System.exit(0);
    }

    private void registerProperty(User user) throws IOException{
        String[] answers = new String[6];

        System.out.print("Enter your full name: ");
        answers[0] = in.nextLine();
        System.out.print("\nEnter the property's address: ");
        answers[1] = in.nextLine();
        System.out.print("\nEnter the property's eircode: ");
        answers[2] = in.nextLine();
        System.out.print("\nEnter the property's estimated market value: ");
        answers[3] = in.nextLine();
        System.out.print("\nEnter the property's location " +
        "\n(0 for countryside, 1 for village, 2 for small town, 3 for large town, 4 for city):\n ");
        answers[4] = in.nextLine();
        System.out.print("Is this your principal private residence? y/n: ");
        answers[5] = in.nextLine();

        //make sure the user doesn't enter an out of bounds number
        if(Integer.parseInt(answers[4]) > 4){
            answers[4] = "4";
        }

        //rewrite to match csv formatting
        if(answers[5].equals("y")){
            answers[5] = "yes";
        } else {
            answers[5] = "no";
        }

        Property newProperty = new Property(answers[0], answers[1], answers[2], 
        Double.parseDouble(answers[3]), Integer.parseInt(answers[4]), (answers[5].equals("yes") ? true : false));

        newProperty.writeToFile(user.getUsername());
        runPropertyOwner(user);
    }
}
