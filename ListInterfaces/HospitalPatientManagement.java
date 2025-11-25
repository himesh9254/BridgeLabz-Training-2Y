import java.util.*;

class HospitalPatient {
    private String patientId;
    private String name;
    private int age;
    private String condition;
    private String status;

    public HospitalPatient(String patientId, String name, int age, String condition) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.status = "Admitted";
    }

    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCondition() { return condition; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HospitalPatient that = (HospitalPatient) o;
        return patientId.equals(that.patientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId);
    }

    @Override
    public String toString() {
        return String.format("Patient[%s, %s, Age:%d, %s, %s]", 
            patientId, name, age, condition, status);
    }
}

public class HospitalPatientManagement {
    private Set<HospitalPatient> admittedPatients;
    private Queue<HospitalPatient> treatmentQueue;
    private Stack<HospitalPatient> recentlyDischarged;
    private List<HospitalPatient> patientHistory;

    public HospitalPatientManagement() {
        admittedPatients = new HashSet<>();
        treatmentQueue = new LinkedList<>();
        recentlyDischarged = new Stack<>();
        patientHistory = new ArrayList<>();
    }

    public boolean admitPatient(HospitalPatient patient) {
        if (!admittedPatients.add(patient)) {
            System.out.println("Patient already admitted: " + patient.getPatientId());
            return false;
        }
        treatmentQueue.add(patient);
        patientHistory.add(patient);
        System.out.println("Patient admitted: " + patient);
        return true;
    }

    public HospitalPatient treatNextPatient() {
        if (treatmentQueue.isEmpty()) {
            System.out.println("No patients waiting for treatment.");
            return null;
        }
        HospitalPatient patient = treatmentQueue.poll();
        patient.setStatus("Under Treatment");
        System.out.println("Treating patient: " + patient.getName());
        return patient;
    }

    public void dischargePatient(String patientId) {
        HospitalPatient toDischarge = null;
        for (HospitalPatient patient : admittedPatients) {
            if (patient.getPatientId().equals(patientId)) {
                toDischarge = patient;
                break;
            }
        }

        if (toDischarge == null) {
            System.out.println("Patient not found: " + patientId);
            return;
        }

        admittedPatients.remove(toDischarge);
        toDischarge.setStatus("Discharged");
        recentlyDischarged.push(toDischarge);
        System.out.println("Patient discharged: " + toDischarge.getName());
    }

    public void reAdmitLastDischarged() {
        if (recentlyDischarged.isEmpty()) {
            System.out.println("No recently discharged patients to re-admit.");
            return;
        }

        HospitalPatient patient = recentlyDischarged.pop();
        patient.setStatus("Re-admitted");
        admittedPatients.add(patient);
        treatmentQueue.add(patient);
        System.out.println("Patient re-admitted: " + patient.getName());
    }

    public void displayAdmittedPatients() {
        System.out.println("\n=== Currently Admitted Patients (" + admittedPatients.size() + ") ===");
        for (HospitalPatient patient : admittedPatients) {
            System.out.println("  " + patient);
        }
    }

    public void displayTreatmentQueue() {
        System.out.println("\n=== Treatment Queue (" + treatmentQueue.size() + ") ===");
        if (treatmentQueue.isEmpty()) {
            System.out.println("  No patients in queue");
            return;
        }
        for (HospitalPatient patient : treatmentQueue) {
            System.out.println("  " + patient.getName() + " - " + patient.getCondition());
        }
    }

    public void displayRecentlyDischarged() {
        System.out.println("\n=== Recently Discharged (Stack) ===");
        if (recentlyDischarged.isEmpty()) {
            System.out.println("  No recently discharged patients");
            return;
        }
        for (HospitalPatient patient : recentlyDischarged) {
            System.out.println("  " + patient);
        }
    }

    public void displayPatientHistory() {
        System.out.println("\n=== Complete Patient History (" + patientHistory.size() + ") ===");
        for (HospitalPatient patient : patientHistory) {
            System.out.println("  " + patient);
        }
    }

    public static void main(String[] args) {
        HospitalPatientManagement hospital = new HospitalPatientManagement();

        System.out.println("=== Admitting Patients ===");
        hospital.admitPatient(new HospitalPatient("PAT001", "John Doe", 45, "Fever"));
        hospital.admitPatient(new HospitalPatient("PAT002", "Jane Smith", 30, "Fracture"));
        hospital.admitPatient(new HospitalPatient("PAT003", "Bob Wilson", 65, "Heart Issue"));
        hospital.admitPatient(new HospitalPatient("PAT004", "Alice Brown", 25, "Appendicitis"));
        hospital.admitPatient(new HospitalPatient("PAT001", "John Duplicate", 50, "Cold"));

        hospital.displayAdmittedPatients();
        hospital.displayTreatmentQueue();

        System.out.println("\n=== Treating Patients ===");
        hospital.treatNextPatient();
        hospital.treatNextPatient();

        System.out.println("\n=== Discharging Patients ===");
        hospital.dischargePatient("PAT001");
        hospital.dischargePatient("PAT002");

        hospital.displayAdmittedPatients();
        hospital.displayRecentlyDischarged();

        System.out.println("\n=== Re-admitting Last Discharged ===");
        hospital.reAdmitLastDischarged();

        hospital.displayAdmittedPatients();
        hospital.displayTreatmentQueue();
        hospital.displayPatientHistory();
    }
}
