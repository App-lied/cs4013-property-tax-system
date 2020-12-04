public class Payment {
    private int year;
    private boolean paid;
    private double amount;

    public Payment(int year, boolean paid, double amount) {
        this.year = year;
        this.paid = paid;
        this.amount = amount;
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
    
    

}
