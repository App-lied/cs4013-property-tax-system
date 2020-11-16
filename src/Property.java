import java.util.ArrayList;
import java.util.Scanner;

public class Property {

    double estMarketValue;
    String postcode;
    String address;
    String owner;
    int category;
    String location;
    private ArrayList<Payment> paymentList = new ArrayList<Payment>();

    public Property(){}


    public Property(String owner, String address, String postcode, double estMarketValue, int category) {
        this.owner = owner;
        this.address = address;
        this.postcode = postcode;
        this.estMarketValue = estMarketValue;
        this.location = location;
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

    public String getLocation() {        
        return location;
    }

    public int getLocationCategory() {     
        

        //int countryside = 0;
        //int village = 1;
        //int smallTown = 2;
        //int largeTown = 3;
        //int city = 4;

        return category;
    }

    public ArrayList getPaymentList() {
        return paymentList;
    }

    public String toString(){
       return "Owner is " + getOwner() + ", the address is " + getAddress() + " postcode is " + postcode + " the estimated market " +
               "value of the property is " + getestMarketValue() + "and the property is situated in a " + getLocationCategory();
    }



}
