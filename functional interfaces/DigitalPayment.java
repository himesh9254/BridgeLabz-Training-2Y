// 3. Digital Payment Interface
// UPI, Credit Card, Wallet all must implement pay() method

interface Payment {
    void pay(double amount);
}

class UPIPayment implements Payment {
    private String upiId;
    
    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via UPI (" + upiId + ")");
    }
}

class CreditCardPayment implements Payment {
    private String cardNumber;
    
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Credit Card ending with " + 
                          cardNumber.substring(cardNumber.length() - 4));
    }
}

class WalletPayment implements Payment {
    private String walletName;
    private double balance;
    
    public WalletPayment(String walletName, double balance) {
        this.walletName = walletName;
        this.balance = balance;
    }
    
    @Override
    public void pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Paid ₹" + amount + " via " + walletName + " Wallet. Remaining balance: ₹" + balance);
        } else {
            System.out.println("Insufficient balance in " + walletName + " Wallet");
        }
    }
}

public class DigitalPayment {
    public static void main(String[] args) {
        Payment upi = new UPIPayment("john@paytm");
        Payment creditCard = new CreditCardPayment("1234567890123456");
        Payment wallet = new WalletPayment("PhonePe", 5000);
        
        upi.pay(500);
        creditCard.pay(1200);
        wallet.pay(300);
        wallet.pay(5000); // Insufficient balance
    }
}
