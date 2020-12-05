import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to represent a payment of the tax on a property.
 */
public class Payment {
    private int year;
    private double amount;
    private boolean paid;

    /**
     *
     * @param year The year the property tax payment was due.
     * @param amount The amount due for the payment.
     * @param paid Whether or not the payment has been made.
     */
    public Payment(int year, double amount, boolean paid) {
        this.year = year;
        this.amount = amount;
        this.paid = paid;
    }

    /**
     * 
     * @return The year of the payment.
     */
    public int getYear() {
        return year;
    }

    /**
     * 
     * @return Whether or not the payment has been made.
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * 
     * @return The monetary value of the payment.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * 
     * @return The payment in a String representation.
     */
    public String toString(){
        return year + "\n€" + amount + "\n" + (paid == true ? "Paid" : "Not paid") + "\n";
    }
    
    /**
     * A method to write a payment object to the payment_info.csv file.
     * @param postcode The postcode of the property of the payment to write.
     */
    public void writeToFile(String postcode){
        String filename = "src/lib/properties/payment_info.csv";

        //try to create new csv file and username/password columns if file does not exist
        try {
            File propertyDataFile = new File(filename);
            if(propertyDataFile.createNewFile()){
                System.out.println("File created: " + propertyDataFile.getName());
                try{
                    FileWriter writer = new FileWriter(filename,true);
                    writer.write("year,amount,paid,postcode\n");
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
            sb.append(year);
            sb.append(",");
            sb.append(amount);
            sb.append(",");
            sb.append(paid);
            sb.append(",");
            sb.append(postcode);
            sb.append("\n");
            writer.write(sb.toString());
            writer.close();
            System.out.println("Payment added successfully.");
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Payment addition failed.");
        }
    }

}
