import java.util.*;

class Account {
    private String accountId;
    private String holderName;
    private double balance;

    public Account(String accountId, String holderName, double balance) {
        this.accountId = accountId;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountId() { return accountId; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public void credit(double amount) { this.balance += amount; }
    public boolean debit(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId.equals(account.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId);
    }

    @Override
    public String toString() {
        return String.format("Account[%s, %s, $%.2f]", accountId, holderName, balance);
    }
}

class Transaction {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private double amount;
    private String type;
    private String status;
    private double previousFromBalance;
    private double previousToBalance;

    public Transaction(String transactionId, String fromAccountId, String toAccountId, double amount, String type) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.status = "Pending";
    }

    public String getTransactionId() { return transactionId; }
    public String getFromAccountId() { return fromAccountId; }
    public String getToAccountId() { return toAccountId; }
    public double getAmount() { return amount; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getPreviousFromBalance() { return previousFromBalance; }
    public void setPreviousFromBalance(double balance) { this.previousFromBalance = balance; }
    public double getPreviousToBalance() { return previousToBalance; }
    public void setPreviousToBalance(double balance) { this.previousToBalance = balance; }

    @Override
    public String toString() {
        return String.format("Transaction[%s, %s, %s->%s, $%.2f, %s]", 
            transactionId, type, fromAccountId, toAccountId, amount, status);
    }
}

public class BankingTransactionSystem {
    private List<Transaction> transactionHistory;
    private Queue<Transaction> pendingTransactions;
    private Set<Account> validAccounts;
    private Stack<Transaction> rollbackStack;
    private Map<String, Account> accountMap;

    public BankingTransactionSystem() {
        transactionHistory = new ArrayList<>();
        pendingTransactions = new LinkedList<>();
        validAccounts = new HashSet<>();
        rollbackStack = new Stack<>();
        accountMap = new HashMap<>();
    }

    public void addAccount(Account account) {
        if (validAccounts.add(account)) {
            accountMap.put(account.getAccountId(), account);
            System.out.println("Account added: " + account);
        } else {
            System.out.println("Account already exists: " + account.getAccountId());
        }
    }

    public void addTransaction(Transaction transaction) {
        pendingTransactions.add(transaction);
        System.out.println("Transaction queued: " + transaction);
    }

    public boolean validateAccount(String accountId) {
        return accountMap.containsKey(accountId);
    }

    public void processTransactions() {
        System.out.println("\n=== Processing Transactions ===");

        while (!pendingTransactions.isEmpty()) {
            Transaction transaction = pendingTransactions.poll();

            if (!validateAccount(transaction.getFromAccountId())) {
                transaction.setStatus("Failed - Invalid From Account");
                transactionHistory.add(transaction);
                System.out.println("FAILED: " + transaction);
                continue;
            }

            if (transaction.getToAccountId() != null && !validateAccount(transaction.getToAccountId())) {
                transaction.setStatus("Failed - Invalid To Account");
                transactionHistory.add(transaction);
                System.out.println("FAILED: " + transaction);
                continue;
            }

            Account fromAccount = accountMap.get(transaction.getFromAccountId());
            Account toAccount = transaction.getToAccountId() != null ? 
                accountMap.get(transaction.getToAccountId()) : null;

            transaction.setPreviousFromBalance(fromAccount.getBalance());
            if (toAccount != null) {
                transaction.setPreviousToBalance(toAccount.getBalance());
            }

            boolean success = false;
            switch (transaction.getType()) {
                case "TRANSFER":
                    if (fromAccount.debit(transaction.getAmount())) {
                        toAccount.credit(transaction.getAmount());
                        success = true;
                    }
                    break;
                case "WITHDRAW":
                    success = fromAccount.debit(transaction.getAmount());
                    break;
                case "DEPOSIT":
                    fromAccount.credit(transaction.getAmount());
                    success = true;
                    break;
            }

            if (success) {
                transaction.setStatus("Completed");
                rollbackStack.push(transaction);
                System.out.println("SUCCESS: " + transaction);
            } else {
                transaction.setStatus("Failed - Insufficient Funds");
                System.out.println("FAILED: " + transaction);
            }

            transactionHistory.add(transaction);
        }
    }

    public void rollbackLastTransaction() {
        System.out.println("\n=== Rolling Back Last Transaction ===");

        if (rollbackStack.isEmpty()) {
            System.out.println("No transactions to rollback!");
            return;
        }

        Transaction transaction = rollbackStack.pop();
        Account fromAccount = accountMap.get(transaction.getFromAccountId());
        Account toAccount = transaction.getToAccountId() != null ? 
            accountMap.get(transaction.getToAccountId()) : null;

        fromAccount.setBalance(transaction.getPreviousFromBalance());
        if (toAccount != null) {
            toAccount.setBalance(transaction.getPreviousToBalance());
        }

        transaction.setStatus("Rolled Back");
        System.out.println("Rolled back: " + transaction);
    }

    public void displayAccounts() {
        System.out.println("\n=== Valid Accounts ===");
        for (Account account : validAccounts) {
            System.out.println("  " + account);
        }
    }

    public void displayTransactionHistory() {
        System.out.println("\n=== Transaction History ===");
        for (Transaction t : transactionHistory) {
            System.out.println("  " + t);
        }
    }

    public static void main(String[] args) {
        BankingTransactionSystem system = new BankingTransactionSystem();

        system.addAccount(new Account("ACC001", "John Doe", 5000.00));
        system.addAccount(new Account("ACC002", "Jane Smith", 3000.00));
        system.addAccount(new Account("ACC003", "Bob Wilson", 7500.00));
        system.addAccount(new Account("ACC001", "Duplicate", 1000.00));

        system.displayAccounts();

        System.out.println("\n=== Adding Transactions ===");
        system.addTransaction(new Transaction("TXN001", "ACC001", "ACC002", 500.00, "TRANSFER"));
        system.addTransaction(new Transaction("TXN002", "ACC002", null, 200.00, "WITHDRAW"));
        system.addTransaction(new Transaction("TXN003", "ACC003", null, 1000.00, "DEPOSIT"));
        system.addTransaction(new Transaction("TXN004", "ACC001", "ACC999", 100.00, "TRANSFER"));
        system.addTransaction(new Transaction("TXN005", "ACC002", null, 10000.00, "WITHDRAW"));

        system.processTransactions();
        system.displayAccounts();
        system.displayTransactionHistory();

        system.rollbackLastTransaction();
        system.displayAccounts();
    }
}
