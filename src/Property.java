import java.util.ArrayList;
import java.util.Scanner;

public class Property {

    double estMarketValue;
    String postcode;
    String address;
    String owner;
    int category;
    String location;
    private ArrayList paymentList = new ArrayList();

    public Property(){}


    public Property(String owner, String address, String postcode, double estMarketValue, int category) {

    }

    public String getOwner() {
    this.owner = owner;
    return owner;
    }

    public String getAddress() {
    this.address = address;
    return address;
    }

    public String getPostcode() {
    this.postcode = postcode;
    return postcode;
    }

    public double getestMarketValue() {
        this.estMarketValue = estMarketValue;
        return estMarketValue;
    }

    public String getLocations() {
        this.location = location;
        return location;
    }

    public int getLocationCategory() {
        Scanner userInput = new Scanner(System.in);
        int category = userInput.nextInt();

        if(category == 0){
            System.out.println("You have chosen countryside!");
        }else if(category == 1){
            System.out.println("You have chosen a Village!");
        }else if(category == 2){
            System.out.println("You have chosen a small town!");
        }else if(category == 3){
            System.out.println("You have chosen a large Town!");
        }else if(category == 4){
            System.out.println("You have chosen a City!");
        }

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
