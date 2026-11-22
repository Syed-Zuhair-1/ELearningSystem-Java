package model;

/**
 * Interface: any payment method that can be used to pay for a course.
 * It only says WHAT must be done; each payment class decides HOW.
 */
public interface Payable {

    // Adds this method's fee to the base price and returns the total to pay
    double calculateAmount(double basePrice);

    // Validates the payment details and "charges" the amount
    boolean processPayment(double amount);

    String getPaymentMethod();
}
