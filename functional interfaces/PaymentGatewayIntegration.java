// 1. Payment Gateway Integration
// Multiple payment providers integrate with your app
// A new refund method needs to be added without breaking old providers
// Add a default refund() method in the PaymentProcessor interface

interface PaymentProcessor {
    void processPayment(double amount);
    void verifyTransaction(String transactionId);
    
    // Default method added later without breaking existing implementations
    default void refund(String transactionId, double amount) {
        System.out.println("Processing refund for transaction: " + transactionId);
        System.out.println("Amount: $" + amount);
        System.out.println("Refund will be processed in 3-5 business days");
    }
}

class StripePaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Stripe: Processing payment of $" + amount);
    }
    
    @Override
    public void verifyTransaction(String transactionId) {
        System.out.println("Stripe: Verifying transaction " + transactionId);
    }
    
    // Uses default refund method
}

class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("PayPal: Processing payment of $" + amount);
    }
    
    @Override
    public void verifyTransaction(String transactionId) {
        System.out.println("PayPal: Verifying transaction " + transactionId);
    }
    
    // Overrides default refund method with custom implementation
    @Override
    public void refund(String transactionId, double amount) {
        System.out.println("PayPal: Initiating instant refund");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: $" + amount);
        System.out.println("Refund completed immediately!");
    }
}

class RazorpayProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        System.out.println("Razorpay: Processing payment of ₹" + amount);
    }
    
    @Override
    public void verifyTransaction(String transactionId) {
        System.out.println("Razorpay: Verifying transaction " + transactionId);
    }
    
    // Uses default refund method
}

public class PaymentGatewayIntegration {
    public static void main(String[] args) {
        System.out.println("=== Payment Gateway Integration System ===\n");
        
        PaymentProcessor stripe = new StripePaymentProcessor();
        PaymentProcessor paypal = new PayPalProcessor();
        PaymentProcessor razorpay = new RazorpayProcessor();
        
        // Process payments
        System.out.println("PROCESSING PAYMENTS:");
        System.out.println("-".repeat(60));
        stripe.processPayment(100.50);
        paypal.processPayment(250.00);
        razorpay.processPayment(5000.00);
        
        System.out.println("\nVERIFYING TRANSACTIONS:");
        System.out.println("-".repeat(60));
        stripe.verifyTransaction("STR-12345");
        paypal.verifyTransaction("PP-67890");
        razorpay.verifyTransaction("RZP-54321");
        
        // Refund operations (new feature using default method)
        System.out.println("\nPROCESSING REFUNDS:");
        System.out.println("-".repeat(60));
        System.out.println("\n[Stripe - Using Default Refund]");
        stripe.refund("STR-12345", 100.50);
        
        System.out.println("\n[PayPal - Custom Refund Implementation]");
        paypal.refund("PP-67890", 250.00);
        
        System.out.println("\n[Razorpay - Using Default Refund]");
        razorpay.refund("RZP-54321", 5000.00);
    }
}
