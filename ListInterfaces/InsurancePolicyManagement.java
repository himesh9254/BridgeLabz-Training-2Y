import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

class InsurancePolicy implements Comparable<InsurancePolicy> {
    private String policyNumber;
    private String policyholderName;
    private LocalDate expiryDate;
    private String coverageType;
    private double premiumAmount;

    public InsurancePolicy(String policyNumber, String policyholderName, LocalDate expiryDate, 
                          String coverageType, double premiumAmount) {
        this.policyNumber = policyNumber;
        this.policyholderName = policyholderName;
        this.expiryDate = expiryDate;
        this.coverageType = coverageType;
        this.premiumAmount = premiumAmount;
    }

    public String getPolicyNumber() { return policyNumber; }
    public String getPolicyholderName() { return policyholderName; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public String getCoverageType() { return coverageType; }
    public double getPremiumAmount() { return premiumAmount; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsurancePolicy that = (InsurancePolicy) o;
        return policyNumber.equals(that.policyNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyNumber);
    }

    @Override
    public int compareTo(InsurancePolicy other) {
        return this.expiryDate.compareTo(other.expiryDate);
    }

    @Override
    public String toString() {
        return String.format("Policy[%s, %s, %s, %s, $%.2f]", 
            policyNumber, policyholderName, expiryDate, coverageType, premiumAmount);
    }
}

public class InsurancePolicyManagement {
    private Set<InsurancePolicy> hashSetPolicies;
    private Set<InsurancePolicy> linkedHashSetPolicies;
    private TreeSet<InsurancePolicy> treeSetPolicies;

    public InsurancePolicyManagement() {
        hashSetPolicies = new HashSet<>();
        linkedHashSetPolicies = new LinkedHashSet<>();
        treeSetPolicies = new TreeSet<>();
    }

    public void addPolicy(InsurancePolicy policy) {
        hashSetPolicies.add(policy);
        linkedHashSetPolicies.add(policy);
        treeSetPolicies.add(policy);
    }

    public void displayAllUniquePolicies() {
        System.out.println("\n=== All Unique Policies (HashSet - Quick Lookup) ===");
        for (InsurancePolicy policy : hashSetPolicies) {
            System.out.println(policy);
        }
    }

    public void displayPoliciesInOrder() {
        System.out.println("\n=== Policies in Insertion Order (LinkedHashSet) ===");
        for (InsurancePolicy policy : linkedHashSetPolicies) {
            System.out.println(policy);
        }
    }

    public void displayPoliciesSortedByExpiry() {
        System.out.println("\n=== Policies Sorted by Expiry Date (TreeSet) ===");
        for (InsurancePolicy policy : treeSetPolicies) {
            System.out.println(policy);
        }
    }

    public void displayPoliciesExpiringSoon(int days) {
        System.out.println("\n=== Policies Expiring Within " + days + " Days ===");
        LocalDate today = LocalDate.now();
        for (InsurancePolicy policy : treeSetPolicies) {
            long daysUntilExpiry = ChronoUnit.DAYS.between(today, policy.getExpiryDate());
            if (daysUntilExpiry >= 0 && daysUntilExpiry <= days) {
                System.out.println(policy + " - Expires in " + daysUntilExpiry + " days");
            }
        }
    }

    public void displayPoliciesByCoverageType(String coverageType) {
        System.out.println("\n=== Policies with Coverage Type: " + coverageType + " ===");
        for (InsurancePolicy policy : hashSetPolicies) {
            if (policy.getCoverageType().equalsIgnoreCase(coverageType)) {
                System.out.println(policy);
            }
        }
    }

    public void findDuplicatePolicies(List<InsurancePolicy> newPolicies) {
        System.out.println("\n=== Checking for Duplicate Policy Numbers ===");
        Set<String> existingNumbers = new HashSet<>();
        for (InsurancePolicy policy : hashSetPolicies) {
            existingNumbers.add(policy.getPolicyNumber());
        }

        for (InsurancePolicy policy : newPolicies) {
            if (existingNumbers.contains(policy.getPolicyNumber())) {
                System.out.println("Duplicate found: " + policy.getPolicyNumber());
            }
        }
    }

    public void performanceComparison() {
        System.out.println("\n=== Performance Comparison ===");
        int iterations = 10000;

        List<InsurancePolicy> testPolicies = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            testPolicies.add(new InsurancePolicy("PERF" + i, "Test" + i, 
                LocalDate.now().plusDays(i % 365), "Health", 100 + i));
        }

        Set<InsurancePolicy> hashTest = new HashSet<>();
        long startTime = System.nanoTime();
        for (InsurancePolicy p : testPolicies) hashTest.add(p);
        long hashAddTime = System.nanoTime() - startTime;

        Set<InsurancePolicy> linkedTest = new LinkedHashSet<>();
        startTime = System.nanoTime();
        for (InsurancePolicy p : testPolicies) linkedTest.add(p);
        long linkedAddTime = System.nanoTime() - startTime;

        TreeSet<InsurancePolicy> treeTest = new TreeSet<>();
        startTime = System.nanoTime();
        for (InsurancePolicy p : testPolicies) treeTest.add(p);
        long treeAddTime = System.nanoTime() - startTime;

        System.out.printf("Adding %d elements:\n", iterations);
        System.out.printf("  HashSet:       %.2f ms\n", hashAddTime / 1_000_000.0);
        System.out.printf("  LinkedHashSet: %.2f ms\n", linkedAddTime / 1_000_000.0);
        System.out.printf("  TreeSet:       %.2f ms\n", treeAddTime / 1_000_000.0);

        InsurancePolicy searchPolicy = testPolicies.get(iterations / 2);

        startTime = System.nanoTime();
        hashTest.contains(searchPolicy);
        long hashSearchTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        linkedTest.contains(searchPolicy);
        long linkedSearchTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        treeTest.contains(searchPolicy);
        long treeSearchTime = System.nanoTime() - startTime;

        System.out.println("\nSearching for an element:");
        System.out.printf("  HashSet:       %.4f ms\n", hashSearchTime / 1_000_000.0);
        System.out.printf("  LinkedHashSet: %.4f ms\n", linkedSearchTime / 1_000_000.0);
        System.out.printf("  TreeSet:       %.4f ms\n", treeSearchTime / 1_000_000.0);
    }

    public static void main(String[] args) {
        InsurancePolicyManagement system = new InsurancePolicyManagement();

        system.addPolicy(new InsurancePolicy("POL001", "John Doe", LocalDate.now().plusDays(15), "Health", 500.00));
        system.addPolicy(new InsurancePolicy("POL002", "Jane Smith", LocalDate.now().plusDays(45), "Auto", 750.00));
        system.addPolicy(new InsurancePolicy("POL003", "Bob Wilson", LocalDate.now().plusDays(10), "Home", 1200.00));
        system.addPolicy(new InsurancePolicy("POL004", "Alice Brown", LocalDate.now().plusDays(60), "Health", 550.00));
        system.addPolicy(new InsurancePolicy("POL005", "Charlie Davis", LocalDate.now().plusDays(5), "Auto", 800.00));
        system.addPolicy(new InsurancePolicy("POL006", "Diana Evans", LocalDate.now().plusDays(90), "Home", 1100.00));

        system.displayAllUniquePolicies();
        system.displayPoliciesInOrder();
        system.displayPoliciesSortedByExpiry();
        system.displayPoliciesExpiringSoon(30);
        system.displayPoliciesByCoverageType("Health");

        List<InsurancePolicy> newPolicies = new ArrayList<>();
        newPolicies.add(new InsurancePolicy("POL001", "Duplicate John", LocalDate.now(), "Health", 100));
        newPolicies.add(new InsurancePolicy("POL007", "New Person", LocalDate.now(), "Auto", 200));
        system.findDuplicatePolicies(newPolicies);

        system.performanceComparison();
    }
}
