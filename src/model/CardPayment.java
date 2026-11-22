package model;

/**
 * Pays with a debit/credit card. Implements the Payable interface.
 */
public class CardPayment implements Payable {

    private String cardHolder;
    private String cardNumber;
    private double feePercent;

    // Constructor 1
    public CardPayment(String cardHolder, String cardNumber, double feePercent) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.feePercent = feePercent;
    }

    // Constructor 2 (overloaded): standard 2% card fee
    public CardPayment(String cardHolder, String cardNumber) {
        this(cardHolder, cardNumber, 2.0);
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(double feePercent) {
        this.feePercent = feePercent;
    }

    @Override
    public double calculateAmount(double basePrice) {
        return basePrice + (basePrice * feePercent / 100);
    }

    @Override
    public boolean processPayment(double amount) {
        if (cardNumber.length() == 16 && amount > 0) {
            String masked = "**** **** **** " + cardNumber.substring(12);
            System.out.println("Charging Rs. " + amount + " to card " + masked
                    + " (" + cardHolder + ")...");
            return true;
        }
        System.out.println("Card payment failed: card number must be 16 digits.");
        return false;
    }

    @Override
    public String getPaymentMethod() {
        return "Card (" + feePercent + "% fee)";
    }
}
