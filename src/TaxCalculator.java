import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class to calculate the tax due to be paid on a property.
 */
public class TaxCalculator {
    private static double fixedCharge;
    private static double[] locationCharges;
    private static boolean residenceCharge;
    //private static int currentYear;
    private LinkedList<Double> taxBrackets = new LinkedList<Double>();
    private ArrayList<Double> taxRates = new ArrayList<Double>();
    private double value;
    private int category; 
    private ArrayList<Payment> list;

    /**
     * The default constructor.
     * @param p The property to calculate the value for.
     */
    public TaxCalculator(Property p) {
        fixedCharge = 100;
        locationCharges = new double[]{100, 80, 60, 50};
        residenceCharge = p.isPrincipalResidence();
        value = p.getestMarketValue();
        category = p.getLocationCategory();
        list = p.getPaymentList();
        taxBrackets.add(150000.000);
        taxBrackets.add(400001.000);
        taxBrackets.add(650001.000);
        taxRates.add(0.0);
        taxRates.add(0.01);
        taxRates.add(0.02);
        taxRates.add(0.04);

    }   

    /**
     * A method to return the tax due for a payment.
     * @return The value of the tax due to be paid.
     */
    public double getTaxPayable() {        
        double taxBeforePenalty = fixedCharge + getMarketValueTax() + getCategoryTax();
        if(residenceCharge == true){
            taxBeforePenalty += 100;   
        }
        return taxAfterPenalty(taxBeforePenalty);        
    }

    /**
     * 
     * @return The value of tax applied based on the property's est. market value.
     */
    private double getMarketValueTax(){
        int i;
        double marketValueTax = 0;
        for (i = 0; i < taxBrackets.size(); i++) {
            if (value < taxBrackets.get(i)) {
                marketValueTax = taxRates.get(i) * value;
                break;
            }
        }
        return marketValueTax;
    }

    /**
     * 
     * @return The value of tax applied based on the property's location.
     */
    private double getCategoryTax(){
        return locationCharges[category];
    }

    // Calculates the tax after penalty by looping through the list of payments and
    // counting the amount of years that the owner hasn't paid tax
    // This may be changed later if we think of a new penalty recording system
    
    /**
     * Calculates the tax due if an overdue penalty has to be paid.
     * @param tax The original value of the due tax.
     * @return The tax after the overdue penalty is added.
     */
    private double taxAfterPenalty(double tax){
        int i;
        int count = 0;
        //This for loop counts the amount of years that tax wasn't paid in
        for(i = 0; i < list.size(); i++){
            if(!list.get(i).isPaid()){
                count++;
            }
        }
        // This for loop keeps adding tax in a compound manner
        for (i = 0; i < count; i++) {
            tax = tax + 0.07 * tax;
        }
        return tax;
    }

    
    
}
