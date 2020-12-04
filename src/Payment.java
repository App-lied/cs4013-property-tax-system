import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Payment {
    private int year;
    private double amount;
    private boolean paid;

    public Payment(int year, double amount, boolean paid) {
        this.year = year;
        this.amount = amount;
        this.paid = paid;
    }

    public int getYear() {
        return year;
    }

    public boolean isPaid() {
        return paid;
    }

    public double getAmount() {
        return amount;
    }

    public String toString(){
        return year + "\n€" + amount + "\n" + (paid == true ? "Paid" : "Not paid");
    }
    
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
