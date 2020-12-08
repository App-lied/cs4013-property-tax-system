import java.util.ArrayList;
import java.util.LinkedList;

/**
 * A class to calculate the tax due to be paid on a property.
 */
public class TaxCalculator {
    private static double fixedCharge;
    private static double[] locationCharges;
    private static boolean residenceCharge;
    private LinkedList<Double> taxBrackets = new LinkedList<Double>();
    private ArrayList<Double> taxRates = new ArrayList<Double>();
    private double value;
    private int category; 

    /**
     * The default constructor.
     * @param p The property to calculate the value for.
     */
    public TaxCalculator(Property p) {
        fixedCharge = 100;
        locationCharges = new double[]{100, 80, 60, 50, 25};
        residenceCharge = p.isPrincipalResidence();
        value = p.getestMarketValue();
        category = p.getLocationCategory();
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
    public double getTaxPayable(Payment p) {        
        double taxBeforePenalty = fixedCharge + getMarketValueTax() + getCategoryTax();
        if(residenceCharge == true){
            taxBeforePenalty += 100;   
        }
        return taxAfterPenalty(taxBeforePenalty, p);        
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

    /**
     * Calculates the tax due if an overdue penalty has to be paid.
     * @param tax The original value of the due tax.
     * @return The tax after the overdue penalty is added.
     */
    private double taxAfterPenalty(double tax, Payment p){
        if(!p.isPaid()){
            for (int i = 0; i < 2020 - p.getYear(); i++) {
                tax = tax + 0.07 * tax;
            }
        }
        return tax;
    }
    
}