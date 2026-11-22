package model;

/**
 * Pays with a mobile wallet (e.g. EasyPaisa, JazzCash). Implements the Payable interface.
 */
public class MobilePayment implements Payable {

    private String provider;
    private String mobileNumber;
    private double fixedFee;

    // Constructor 1
    public MobilePayment(String provider, String mobileNumber, double fixedFee) {
        this.provider = provider;
        this.mobileNumber = mobileNumber;
        this.fixedFee = fixedFee;
    }

    // Constructor 2 (overloaded): standard fixed fee of Rs. 25
    public MobilePayment(String provider, String mobileNumber) {
        this(provider, mobileNumber, 25.0);
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public double getFixedFee() {
        return fixedFee;
    }

    public void setFixedFee(double fixedFee) {
        this.fixedFee = fixedFee;
    }

    @Override
    public double calculateAmount(double basePrice) {
        return basePrice + fixedFee;
    }

    @Override
    public boolean processPayment(double amount) {
        if (mobileNumber.length() == 11 && mobileNumber.startsWith("03") && amount > 0) {
            System.out.println("Sending payment request of Rs. " + amount + " to " + provider
                    + " account " + mobileNumber + "...");
            return true;
        }
        System.out.println("Mobile payment failed: number must be 11 digits starting with 03.");
        return false;
    }

    @Override
    public String getPaymentMethod() {
        return provider + " (Rs. " + fixedFee + " fee)";
    }
}
