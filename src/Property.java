import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Property {

    private String owner;
    private String address;
    private String postcode;
    private double estMarketValue;
    private int category;
    private String[] locations = {"Countryside", "Village", "Small Town", "Large Town", "City"};
    private boolean principalResidence;
    private ArrayList<Payment> paymentList;

    public Property() {
    }

    public Property(String owner, String address, String postcode, double estMarketValue, int category, boolean principalResidence) {
        this.owner = owner;
        this.address = address;
        this.postcode = postcode;
        this.estMarketValue = estMarketValue;
        this.category = category;
        this.principalResidence = principalResidence;
        paymentList = findPayments();
    }

    public String getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public double getestMarketValue() {
        return estMarketValue;
    }

    public String getPropertyLocation() {        
        return locations[category];
    }

    public int getLocationCategory() {
        return category;
    }

    public boolean isPrincipalResidence(){
        return principalResidence;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public String toString() {
        return getOwner() + "\n" + getAddress() + "\n" + postcode
                + "\nEstimated Market Value: €" + getestMarketValue()
                + "\nLocation Category: " + getPropertyLocation();
    }

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
            sb.append(this.getLocationCategory());
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

            return result;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

}
