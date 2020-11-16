import java.util.ArrayList;

public class Property {

    private double estMarketValue;
    private String postcode;
    private String address;
    private String owner;
    private int category;
    private String[] locations;
    private boolean principalResidence;
    private ArrayList<Payment> paymentList = new ArrayList<Payment>();

    public Property() {
    }

    public Property(String owner, String address, String postcode, double estMarketValue, int category, String[] locations, boolean principalResidence, ArrayList<Payment> paymentList) {
        this.owner = owner;
        this.address = address;
        this.postcode = postcode;
        this.estMarketValue = estMarketValue;
        this.locations = locations;
        this.category = category;
        this.principalResidence = principalResidence;
        this.paymentList = paymentList;
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

    public String[] getLocations() {        
        return locations;
    }

    public int getLocationCategory() {

        // int countryside = 0;
        // int village = 1;
        // int smallTown = 2;
        // int largeTown = 3;
        // int city = 4;

        return category;
    }

    public boolean isPrincipalResidence(){
        return principalResidence;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public String toString() {
        return "Owner is " + getOwner() + ", the address is " + getAddress() + " postcode is " + postcode
                + " the estimated market " + "value of the property is " + getestMarketValue()
                + "and the property is situated in a " + getLocationCategory();
    }

}
