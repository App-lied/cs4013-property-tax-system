import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * A class to represent a payment of the tax on a property.
 */
public class Payment implements Comparable<Payment>{
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

    public void setPaid(boolean b){
        this.paid = b;
    }

    public void setAmount(double a){
        this.amount = a;
    }
    /**
     * 
     * @return The payment in a String representation.
     */
    public String toString(){
        return year + "\n€" + amount + "\n" + (paid == true ? "Paid" : "Not paid") + "\n";
    }

    public int compareTo(Payment p){
        if(this.year > p.year){
            return 1;
        } else if (this.year == p.year){
            return 0;
        } else {
            return -1;
        }
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
            
        } catch(IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Payment addition failed.");
        }
    }

    /**
     * A method to remove a payment from the system if it gets updated.
     * Works by making a copy of the file and copying all data except the target line to the copy.
     * @param postcode The postcode associated with the payment.
     * @throws IOException
     */
    public void removePayment(String postcode) throws IOException{
        File inputFile = new File("src/lib/properties/payment_info.csv");
        File tempFile = new File("src/lib/properties/payment_duplicate.csv");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        StringBuilder sb = new StringBuilder();
            sb.append(year);
            sb.append(",");
            sb.append(amount);
            sb.append(",");
            sb.append(paid);
            sb.append(",");
            sb.append(postcode);

        String lineToRemove = sb.toString();
        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            // trim newline when comparing with lineToRemove
            String trimmedLine = currentLine.trim();
            if(trimmedLine.equals(lineToRemove)) continue;
            writer.write(currentLine + System.getProperty("line.separator"));
        }

        writer.close(); 
        reader.close(); 
        tempFile.renameTo(inputFile);
    }

}
