//a class to handle the terminal implementation of the main user interface

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MainSystem {
    
    private static File source = new File("src/lib/properties/property_info.csv");
    private final Scanner in;

    public MainSystem(){
        in = new Scanner(System.in);
    }

    public void run(User user){
        boolean adminLogin = false;

        if(user instanceof Admin){
            adminLogin = true;
        }
    }
}
