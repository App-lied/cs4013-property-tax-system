import java.util.Scanner;
//import java.io.File;
import java.io.IOException;

/**
 * A class to handle the main interface of the system's terminal implementation.
 * It is created once in Login.java
 */
public class MainSystem {
    
    private Scanner in;

    /**
     * The default constructor.
     */
    public MainSystem(){
        in = new Scanner(System.in);
    }

    /**
     * The entry method for the flow of the class.
     * @param user The currently logged-in user.
     * @throws IOException
     */
    public void run(User user) throws IOException{
        if(user instanceof Admin){
            runAdmin(user);
        } else {
            runPropertyOwner(user);
        }
    }

    /**
     * The method to display the admin side of the program.
     * @param user The currently logged-in user.
     * @throws IOException
     */
    private void runAdmin(User user) throws IOException{
    }

    /**
     * The method to display the standard user (property owner's) side of the program.
     * @param user The currently logged-in user.
     * @throws IOException
     */
    private void runPropertyOwner(User user) throws IOException{

        System.out.println("Press 'V' to view your properties. Press 'P' to register a new property. Press 'Q' to exit.");
        boolean running = true;
        while(running){
            String choice = in.nextLine().toLowerCase();

            if(choice.equals("p")){
                registerProperty(user);
            } else if(choice.equals("q")){
                System.out.println("System exiting.");
                running = false;
            } else if(choice.equals("v")){
                Object o = displayProperties(user);
                if(o != null){
                    displayPayments((Property) o);
                }
                runPropertyOwner(user);
            }
        }
        System.exit(0);
    }

    /**
     * A private method to display the currently logged in property owner's list of properties.
     * @param user The currently logged-in user.
     * @return The selected property as a Property object. Null if the user exits.
     * @throws IOException
     */
    private Object displayProperties(User user) throws IOException{
        for(int i = 0; i < ((PropertyOwner)user).getPropertyList().size(); i++){
            System.out.println((i + 1) + ") " + ((PropertyOwner)user).getPropertyList().get(i).toString());
            System.out.println();
        }

        System.out.println("Enter the number of the property to view payments. Press 'Q' to exit.");
        String input = in.nextLine();

        if(input.toLowerCase().equals("q")){
            return null;
        }

        int c = Integer.parseInt(input) - 1;

        if(c >= 0 && c < ((PropertyOwner)user).getPropertyList().size()){
            return ((PropertyOwner)user).getPropertyList().get(c);
        } else {
            return null;
        }
    }

    private void displayPayments(Property p) throws IOException{
        for(int i = 0; i < p.getPaymentList().size(); i++){
            System.out.println((i + 1) + ") " + (p.getPaymentList().get(i).toString()));
        }

        System.out.println("Enter the number of the payment to make payment. Press 'Q' to exit.");
        String input = in.nextLine();

        if(!input.toLowerCase().equals("q")){
            int c = Integer.parseInt(input) - 1;
            if(c >= 0 && c < p.getPaymentList().size()){
                if(p.getPaymentList().get(c).isPaid()){
                    System.out.println("Payment has already been made.");
                } else {
                    int y = p.getPaymentList().get(c).getYear();
                    double a = p.getPaymentList().get(c).getAmount();
                    Payment temp = new Payment(y, a, true);

                    p.getPaymentList().get(c).removePayment(p.getPostcode());
                    p.getPaymentList().remove(p.getPaymentList().get(c));

                    temp.writeToFile(p.getPostcode());
                    p.getPaymentList().add(temp);
                }
            }
        }

    }

    /**
     * A method to register a new property, writing it to file.
     * @param user The currently logged-in user.
     * @throws IOException
     */
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
