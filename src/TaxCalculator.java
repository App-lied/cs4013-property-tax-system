package src;

import java.util.ArrayList;
import java.util.LinkedList;

public class TaxCalculator {
    private static double fixedCharge;
    private static double[] locationCharges;
    private static double residenceCharge;
    // private static int currentYear;
    private LinkedList<Double> taxBrackets = new LinkedList<Double>();
    private ArrayList<Double> taxRates = new ArrayList<Double>();

    // Default constructor
    public TaxCalculator() {
        fixedCharge = 100;
        locationCharges = new double[] { 100, 80, 60, 50 };
        residenceCharge = 0;
        taxBrackets.add(150000.000);
        taxBrackets.add(400001.000);
        taxBrackets.add(650001.000);
        taxRates.add(0.0);
        taxRates.add(0.01);
        taxRates.add(0.02);
        taxRates.add(0.04);
    }

    // Returns tax payable using the private methods below
    public double getTaxPayable(double value, int category, ArrayList<Payment> list) {
        double taxBeforePenalty = fixedCharge + getMarketValueTax(value) + getCategoryTax(category) + residenceCharge;
        return taxAfterPenalty(list, taxBeforePenalty);
    }

    // Calculates the market value tax by checking which
    private double getMarketValueTax(double value) {
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

    private double getCategoryTax(int category) {
        return locationCharges[category];
    }

    // Calculates the tax after penalty by looping through the list of payments and
    // counting the amount of years that the owner hasn't paid tax
    // This may be changed later if we think of a new penalty recording system
    private double taxAfterPenalty(ArrayList<Payment> list, double tax) {
        int i;
        int count = 0;
        // This for loop counts the amount of years that tax wasn't paid in
        for (i = 0; i < list.size(); i++) {
            if (!list.get(i).paid()) {
                count++;
            }
        }
        // This for loop keeps adding tax in a compound manner
        for (i = 0; i < count; i++) {
            tax = tax + 0.07 * tax;
        }
        return tax;
    }

    // If input is true the static value residenceCharge will become 100
    public static void setResidenceCharge(boolean residenceCharge) {
        if (residenceCharge) {
            TaxCalculator.residenceCharge = 100;
        }
    }

}
