import java.util.*;
import java.util.stream.*;
import java.util.function.*;

class Invoice {
    private String invoiceId;
    private String transactionId;
    private double amount;
    private String status;
    
    public Invoice(String transactionId) {
        this.transactionId = transactionId;
        this.invoiceId = "INV-" + transactionId;
        this.amount = generateRandomAmount();
        this.status = "PENDING";
    }
    
    public Invoice(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.invoiceId = "INV-" + transactionId;
        this.amount = amount;
        this.status = "PENDING";
    }
    
    private double generateRandomAmount() {
        return Math.round((Math.random() * 10000 + 100) * 100.0) / 100.0;
    }
    
    public String getInvoiceId() { return invoiceId; }
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("Invoice{id='%s', txnId='%s', amount=$%.2f, status='%s'}", 
                           invoiceId, transactionId, amount, status);
    }
}

class Transaction {
    private String transactionId;
    private double amount;
    
    public Transaction(String transactionId, double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }
    
    public String getTransactionId() { return transactionId; }
    public double getAmount() { return amount; }
}

public class InvoiceCreation {
    public static void main(String[] args) {
        List<String> transactionIds = Arrays.asList(
            "TXN001", "TXN002", "TXN003", "TXN004", "TXN005",
            "TXN006", "TXN007", "TXN008", "TXN009", "TXN010"
        );
        
        List<Transaction> transactions = Arrays.asList(
            new Transaction("TXN101", 250.50),
            new Transaction("TXN102", 1500.75),
            new Transaction("TXN103", 89.99),
            new Transaction("TXN104", 3200.00),
            new Transaction("TXN105", 450.25)
        );
        
        System.out.println("=== Invoice Generation System ===\n");
        
        System.out.println("METHOD 1: Using Constructor Reference (Single Parameter)");
        System.out.println("=".repeat(70));
        Function<String, Invoice> invoiceCreator = Invoice::new;
        
        List<Invoice> invoices1 = transactionIds.stream()
                                                .map(invoiceCreator)
                                                .collect(Collectors.toList());
        
        invoices1.forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 2: Direct Stream Mapping with Constructor Reference");
        System.out.println("=".repeat(70));
        
        List<Invoice> invoices2 = transactionIds.stream()
                                                .map(Invoice::new)
                                                .collect(Collectors.toList());
        
        System.out.println("Generated " + invoices2.size() + " invoices:");
        invoices2.forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 3: Creating Invoices from Transaction Objects");
        System.out.println("=".repeat(70));
        
        BiFunction<String, Double, Invoice> invoiceWithAmount = Invoice::new;
        
        List<Invoice> invoices3 = transactions.stream()
                                              .map(txn -> invoiceWithAmount.apply(
                                                  txn.getTransactionId(), 
                                                  txn.getAmount()))
                                              .collect(Collectors.toList());
        
        invoices3.forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 4: Supplier with Constructor Reference");
        System.out.println("=".repeat(70));
        
        String fixedTxnId = "TXN-SPECIAL-001";
        Supplier<Invoice> singleInvoiceSupplier = () -> new Invoice(fixedTxnId);
        
        Invoice specialInvoice = singleInvoiceSupplier.get();
        System.out.println("Special Invoice: " + specialInvoice);
        
        System.out.println("\n\nMETHOD 5: Batch Invoice Creation");
        System.out.println("=".repeat(70));
        
        List<Invoice> batchInvoices = IntStream.range(1, 6)
                                               .mapToObj(i -> String.format("TXN-BATCH-%03d", i))
                                               .map(Invoice::new)
                                               .collect(Collectors.toList());
        
        System.out.println("Created batch of " + batchInvoices.size() + " invoices:");
        batchInvoices.forEach(System.out::println);
        
        System.out.println("\n\nMETHOD 6: Invoice Processing Pipeline");
        System.out.println("=".repeat(70));
        
        List<String> newTransactions = Arrays.asList("TXN201", "TXN202", "TXN203");
        
        List<Invoice> processedInvoices = newTransactions.stream()
                                                         .map(Invoice::new)
                                                         .peek(inv -> inv.setStatus("PROCESSED"))
                                                         .collect(Collectors.toList());
        
        System.out.println("Processed Invoices:");
        processedInvoices.forEach(System.out::println);
        
        System.out.println("\n\nSUMMARY STATISTICS:");
        System.out.println("=".repeat(70));
        
        DoubleSummaryStatistics stats = invoices3.stream()
                                                 .mapToDouble(Invoice::getAmount)
                                                 .summaryStatistics();
        
        System.out.println("Total Invoices: " + stats.getCount());
        System.out.println("Total Amount: $" + String.format("%.2f", stats.getSum()));
        System.out.println("Average Amount: $" + String.format("%.2f", stats.getAverage()));
        System.out.println("Min Amount: $" + String.format("%.2f", stats.getMin()));
        System.out.println("Max Amount: $" + String.format("%.2f", stats.getMax()));
    }
}
