import java.util.*;

class Patient {
    private String patientId;
    private String name;
    private int age;
    
    public Patient(String patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }
    
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    
    public void printId() {
        System.out.println("Patient ID: " + patientId);
    }
    
    @Override
    public String toString() {
        return patientId + " - " + name + " (Age: " + age + ")";
    }
}

class AdminVerification {
    public static void verifyPatient(Patient patient) {
        System.out.println("✓ Verified: " + patient.getPatientId() + " - " + patient.getName());
    }
    
    public static void printHeader() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("PATIENT ID VERIFICATION REPORT");
        System.out.println("=".repeat(60));
    }
}

public class PatientIDPrinting {
    public static void main(String[] args) {
        List<Patient> patients = Arrays.asList(
            new Patient("P001", "John Smith", 45),
            new Patient("P002", "Emma Johnson", 32),
            new Patient("P003", "Michael Brown", 56),
            new Patient("P004", "Sarah Davis", 28),
            new Patient("P005", "Robert Wilson", 67),
            new Patient("P006", "Lisa Anderson", 41),
            new Patient("P007", "David Martinez", 39)
        );
        
        System.out.println("=== Hospital Patient ID Admin Verification ===");
        
        System.out.println("\nMETHOD 1: Print All Patient IDs (Instance Method Reference)");
        System.out.println("-".repeat(60));
        patients.forEach(Patient::printId);
        
        System.out.println("\nMETHOD 2: Print Patient IDs using System.out::println");
        System.out.println("-".repeat(60));
        patients.stream()
                .map(Patient::getPatientId)
                .forEach(System.out::println);
        
        System.out.println("\nMETHOD 3: Verify Patients (Static Method Reference)");
        System.out.println("-".repeat(60));
        patients.forEach(AdminVerification::verifyPatient);
        
        System.out.println("\nMETHOD 4: Print Full Patient Details");
        System.out.println("-".repeat(60));
        patients.forEach(System.out::println);
        
        System.out.println("\nMETHOD 5: Print Patient Names");
        System.out.println("-".repeat(60));
        patients.stream()
                .map(Patient::getName)
                .forEach(System.out::println);
        
        System.out.println("\nMETHOD 6: Formatted ID List");
        System.out.println("-".repeat(60));
        System.out.println("Patient IDs for Admin Review:");
        patients.stream()
                .map(Patient::getPatientId)
                .map(id -> "  - " + id)
                .forEach(System.out::println);
        
        AdminVerification.printHeader();
        patients.stream()
                .map(Patient::getPatientId)
                .sorted()
                .forEach(System.out::println);
    }
}
