import java.util.PriorityQueue;

class Patient implements Comparable<Patient> {
    private String name;
    private int severity;

    public Patient(String name, int severity) {
        this.name = name;
        this.severity = severity;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    @Override
    public int compareTo(Patient other) {
        return Integer.compare(other.severity, this.severity);
    }

    @Override
    public String toString() {
        return name + " (Severity: " + severity + ")";
    }
}

public class HospitalTriage {
    private PriorityQueue<Patient> triageQueue;

    public HospitalTriage() {
        triageQueue = new PriorityQueue<>();
    }

    public void addPatient(String name, int severity) {
        Patient patient = new Patient(name, severity);
        triageQueue.add(patient);
        System.out.println("Added patient: " + patient);
    }

    public Patient treatNextPatient() {
        if (triageQueue.isEmpty()) {
            System.out.println("No patients waiting.");
            return null;
        }
        Patient patient = triageQueue.poll();
        System.out.println("Treating patient: " + patient);
        return patient;
    }

    public void displayWaitingPatients() {
        System.out.println("\nPatients waiting (by priority):");
        PriorityQueue<Patient> tempQueue = new PriorityQueue<>(triageQueue);
        while (!tempQueue.isEmpty()) {
            System.out.println("  " + tempQueue.poll());
        }
    }

    public int getWaitingCount() {
        return triageQueue.size();
    }

    public static void main(String[] args) {
        HospitalTriage hospital = new HospitalTriage();

        System.out.println("=== Adding Patients ===");
        hospital.addPatient("John", 3);
        hospital.addPatient("Alice", 5);
        hospital.addPatient("Bob", 2);
        hospital.addPatient("Carol", 4);
        hospital.addPatient("David", 1);

        hospital.displayWaitingPatients();

        System.out.println("\n=== Treatment Order ===");
        while (hospital.getWaitingCount() > 0) {
            hospital.treatNextPatient();
        }

        System.out.println("\n=== Adding More Patients ===");
        hospital.addPatient("Eve", 5);
        hospital.addPatient("Frank", 3);
        hospital.addPatient("Grace", 5);
        hospital.addPatient("Henry", 2);

        hospital.displayWaitingPatients();

        System.out.println("\n=== Treatment Order ===");
        while (hospital.getWaitingCount() > 0) {
            hospital.treatNextPatient();
        }
    }
}
