import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Collections;

/**
 * A class to represent and store data on a property in the system.
 */
public class Property {

    private String owner;
    private String address;
    private String postcode;
    private double estMarketValue;
    private int category;
    private String[] locations = {"Countryside", "Village", "Small Town", "Large Town", "City"};
    private boolean principalResidence;
    private ArrayList<Payment> paymentList;

    /**
     * The default constructor.
     */
    public Property() {
    }

    /**
     * 
     * @param owner The owner of the property.
     * @param address The address of the property.
     * @param postcode The postcode of the property.
     * @param estMarketValue The estimated market value of the property.
     * @param category The category, 0-4, that determines the location of the property (Countryside, City, etc.)
     * @param principalResidence Whether or not this is the owner's principal private residence.
     */
    public Property(String owner, String address, String postcode, double estMarketValue, int category, boolean principalResidence) {
        this.owner = owner;
        this.address = address;
        this.postcode = postcode;
        this.estMarketValue = estMarketValue;
        this.category = category;
        this.principalResidence = principalResidence;
        paymentList = findPayments();
        if(paymentList.size() == 0){
            createPaymentHistory();
            Collections.sort(paymentList);
        }
        
    }

    /**
     * 
     * @return The owner of the property.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * 
     * @return The address of the property.
     */
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @return The postcode of the property.
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 
     * @return The estimated market value of the property.
     */
    public double getestMarketValue() {
        return estMarketValue;
    }

    /**
     * 
     * @return The location of the property.
     */
    public String getPropertyLocation() {        
        return locations[category];
    }

    /**
     * 
     * @return The number representing the property's location category.
     */
    public int getLocationCategory() {
        return category;
    }

    public boolean isPrincipalResidence(){
        return principalResidence;
    }

    /**
     * 
     * @return The list of tax payments associated with the property.
     */
    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    /**
     * 
     * @return A string representation of the property.
     */
    public String toString() {
        return  "Owner: " + getOwner() + "\n" + "Address: " + getAddress() + "\n"
                + "Postcode: " + postcode
                + "\nEstimated Market Value: €" + getestMarketValue()
                + "\nLocation Category: " + getPropertyLocation() + "\n";
    }

    /**
     * Writes the property's data to the property_info.csv file
     * @param username The username of the owner of the property.
     */
    public void writeToFile(String username){
        String filename = "src/lib/properties/property_info.csv";

        //try to create new csv file and username/password columns if file does not exist
        try {
            File propertyDataFile = new File(filename);
            if(propertyDataFile.createNewFile()){
                System.out.println("File created: " + propertyDataFile.getName());
                try{
                    FileWriter writer = new FileWriter(filename,true);
                    writer.write("owner,address,postcode,estMarketValue,category,principalResidence,user\n");
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

        try{
            FileWriter writer = new FileWriter(filename,true);
            StringBuilder sb = new StringBuilder();
            sb.append(this.getOwner());
            sb.append(",");
            sb.append(this.getAddress());
            sb.append(",");
            sb.append(this.getPostcode());
            sb.append(",");
            sb.append(this.getestMarketValue());
            sb.append(",");
            sb.append(this.category);
            sb.append(",");
            sb.append(principalResidence == true ? "yes" : "no");
            sb.append(",");
            sb.append(username);
            sb.append("\n");
            writer.write(sb.toString());
            writer.close();
            System.out.println("Property registered successfully.");
        } catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Property registration failed.");
        }
    }

    /**
     * A method to create and store the payments for this property if it has no history.
     */
    public void createPaymentHistory(){
        ArrayList<Payment> newHistory = new ArrayList<Payment>(5);

        for(int i = 0; i < 5; i++){
            newHistory.add(new Payment(2015+i, 0, false));
        }

        TaxCalculator c = new TaxCalculator(this);
        for(Payment p : newHistory){
            p.setAmount(c.getTaxPayable(p));
            p.writeToFile(this.postcode);
        }

        this.paymentList = newHistory;
    }

    /**
     * A private method called on creation of the object to find its associated tax payment history from file.
     * @return The list of payments associated with the property.
     */
    private ArrayList<Payment> findPayments(){
        String filename = "src/lib/properties/payment_info.csv";
        ArrayList<Payment> result = new ArrayList<Payment>();

        try {
            final Scanner scanner = new Scanner(new File(filename));

            while(scanner.hasNextLine()){
                final String lineFromFile = scanner.nextLine();

                if(lineFromFile.contains(postcode)){
                    String[] s = lineFromFile.split(",");
                    result.add(new Payment(Integer.parseInt(s[0]), Double.parseDouble(s[1]),
                    (s[2].equals("true") ? true : false)));
                }
            }

            Collections.sort(result);
            return result;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
