/**
 * Hospital Management System demonstrating static, this, final, and instanceof concepts
 * Complete patient management with different patient types and medical records
 */
public class HospitalManagementSystem {
    // Static variables - shared across all patients
    private static String hospitalName = "City General Hospital";
    private static int totalPatientsAdmitted = 0;
    private static double totalMedicalBills = 0.0;
    private static String[] validPatientTypes = {"Inpatient", "Outpatient", "Emergency", "ICU"};
    
    // Instance variables
    private String patientName;
    private final String patientId; // final - unique identifier that cannot be changed
    private String patientType;
    private String doctorAssigned;
    private String diagnosis;
    private double medicalBill;
    private boolean isActive;
    private final String admissionDate; // final - cannot be modified after admission
    
    // Constructor using 'this' to initialize all patient fields
    public HospitalManagementSystem(String patientName, String patientId, String patientType, 
                                   String doctorAssigned, String diagnosis, double medicalBill) {
        // Using 'this' to distinguish between parameters and instance variables
        this.patientName = patientName;
        this.patientId = patientId; // final variable initialization
        this.patientType = patientType;
        this.doctorAssigned = doctorAssigned;
        this.diagnosis = diagnosis;
        this.medicalBill = medicalBill;
        this.isActive = true;
        this.admissionDate = java.time.LocalDate.now().toString(); // final variable initialization
        
        // Update static counters
        totalPatientsAdmitted++;
        totalMedicalBills += medicalBill;
        
        System.out.println("Patient admitted: " + this.patientName + " (" + this.patientId + ") - " + this.patientType);
    }
    
    // Overloaded constructor with default medical bill
    public HospitalManagementSystem(String patientName, String patientId, String patientType, 
                                   String doctorAssigned, String diagnosis) {
        // Using 'this' to call another constructor (constructor chaining)
        this(patientName, patientId, patientType, doctorAssigned, diagnosis, calculateDefaultBill(patientType));
        System.out.println("Default medical bill applied: $" + this.medicalBill);
    }
    
    // Constructor with minimal patient information
    public HospitalManagementSystem(String patientName, String patientType) {
        this(patientName, generatePatientId(), patientType, "Dr. General", "Pending Diagnosis", 
             calculateDefaultBill(patientType));
        System.out.println("Auto-generated patient ID and default values assigned.");
    }
    
    // Static method to display hospital statistics
    public static void displayHospitalStats() {
        System.out.println("=== Hospital Management Statistics ===");
        System.out.println("Hospital Name: " + hospitalName);
        System.out.println("Total Patients Admitted: " + totalPatientsAdmitted);
        System.out.printf("Total Medical Bills: $%.2f%n", totalMedicalBills);
        if (totalPatientsAdmitted > 0) {
            System.out.printf("Average Bill per Patient: $%.2f%n", totalMedicalBills / totalPatientsAdmitted);
        }
        System.out.println("Valid Patient Types: " + String.join(", ", validPatientTypes));
        System.out.println("====================================");
    }
    
    // Static method to get hospital name
    public static String getHospitalName() {
        return hospitalName;
    }
    
    // Static method to update hospital name
    public static void updateHospitalName(String newHospitalName) {
        if (newHospitalName != null && !newHospitalName.trim().isEmpty()) {
            String oldName = hospitalName;
            hospitalName = newHospitalName;
            System.out.println("Hospital name updated: " + oldName + " -> " + hospitalName);
            System.out.println("This change affects all " + totalPatientsAdmitted + " patient records!");
        } else {
            System.out.println("Invalid hospital name provided!");
        }
    }
    
    // Method to update patient name using 'this'
    public void updatePatientName(String newPatientName) {
        if (newPatientName != null && !newPatientName.trim().isEmpty()) {
            String oldName = this.patientName;
            this.patientName = newPatientName;
            System.out.println("Patient name updated for ID " + this.patientId + ": " + oldName + " -> " + this.patientName);
        } else {
            System.out.println("Invalid patient name provided!");
        }
    }
    
    // Method to update doctor assignment using 'this'
    public void updateDoctorAssigned(String newDoctor) {
        if (newDoctor != null && !newDoctor.trim().isEmpty()) {
            String oldDoctor = this.doctorAssigned;
            this.doctorAssigned = newDoctor;
            System.out.println("Doctor updated for patient " + this.patientName + " (" + this.patientId + 
                             "): " + oldDoctor + " -> " + this.doctorAssigned);
        } else {
            System.out.println("Invalid doctor name provided!");
        }
    }
    
    // Method to update diagnosis using 'this'
    public void updateDiagnosis(String newDiagnosis, double additionalCharges) {
        if (newDiagnosis != null && !newDiagnosis.trim().isEmpty()) {
            String oldDiagnosis = this.diagnosis;
            this.diagnosis = newDiagnosis;
            
            if (additionalCharges > 0) {
                this.medicalBill += additionalCharges;
                totalMedicalBills += additionalCharges;
                System.out.printf("Diagnosis updated for %s: %s -> %s (Additional charges: $%.2f)%n", 
                                 this.patientName, oldDiagnosis, this.diagnosis, additionalCharges);
            } else {
                System.out.println("Diagnosis updated for " + this.patientName + ": " + oldDiagnosis + " -> " + this.diagnosis);
            }
        } else {
            System.out.println("Invalid diagnosis provided!");
        }
    }
    
    // Method to add treatment charges using 'this'
    public void addTreatmentCharges(String treatment, double charges) {
        if (treatment != null && !treatment.trim().isEmpty() && charges > 0) {
            this.medicalBill += charges;
            totalMedicalBills += charges;
            System.out.printf("Treatment added for %s (%s): %s - $%.2f (Total bill: $%.2f)%n", 
                             this.patientName, this.patientId, treatment, charges, this.medicalBill);
        } else {
            System.out.println("Invalid treatment or charges provided!");
        }
    }
    
    // Method to discharge/readmit patient using 'this'
    public void updatePatientStatus(boolean active) {
        this.isActive = active;
        System.out.println("Patient " + this.patientName + " (" + this.patientId + ") status: " + 
                         (this.isActive ? "Active/Admitted" : "Discharged"));
    }
    
    // Method to calculate patient stay duration using 'this'
    public int getStayDuration() {
        // Calculate days since admission (simplified)
        java.time.LocalDate admission = java.time.LocalDate.parse(this.admissionDate);
        java.time.LocalDate today = java.time.LocalDate.now();
        return (int) java.time.temporal.ChronoUnit.DAYS.between(admission, today);
    }
    
    // Method to check if patient needs follow-up using 'this'
    public boolean needsFollowUp() {
        return !this.patientType.equalsIgnoreCase("Emergency") && this.isActive;
    }
    
    // Method to calculate insurance coverage using 'this'
    public double calculateInsuranceCoverage(double coveragePercentage) {
        return this.medicalBill * (coveragePercentage / 100.0);
    }
    
    // Method to display patient details
    public void displayPatientDetails() {
        System.out.println("=== Patient Medical Record ===");
        System.out.println("Hospital: " + hospitalName); // static variable access
        System.out.println("Patient Name: " + this.patientName);
        System.out.println("Patient ID: " + this.patientId); // final variable access
        System.out.println("Admission Date: " + this.admissionDate); // final variable access
        System.out.println("Patient Type: " + this.patientType);
        System.out.println("Doctor Assigned: " + this.doctorAssigned);
        System.out.println("Diagnosis: " + this.diagnosis);
        System.out.printf("Medical Bill: $%.2f%n", this.medicalBill);
        System.out.println("Stay Duration: " + this.getStayDuration() + " days");
        System.out.println("Status: " + (this.isActive ? "Active/Admitted" : "Discharged"));
        System.out.println("Follow-up Required: " + (this.needsFollowUp() ? "Yes" : "No"));
        System.out.println("==============================");
    }
    
    // Static method to validate and process patient using instanceof
    public static void processPatient(Object obj) {
        // Using instanceof to check object type
        if (obj instanceof HospitalManagementSystem) {
            System.out.println("✓ Object is a valid Patient Record instance");
            HospitalManagementSystem patient = (HospitalManagementSystem) obj; // Safe casting
            patient.displayPatientDetails();
        } else {
            System.out.println("✗ Object is not a Patient Record instance!");
            System.out.println("Object type: " + (obj != null ? obj.getClass().getSimpleName() : "null"));
        }
    }
    
    // Method to compare patients using 'this'
    public boolean isSamePatient(HospitalManagementSystem other) {
        // Using 'this' to refer to current object and final patientId for comparison
        return other != null && this.patientId.equals(other.patientId);
    }
    
    // Method to check if same doctor using 'this'
    public boolean hasSameDoctor(HospitalManagementSystem other) {
        return other != null && this.doctorAssigned.equalsIgnoreCase(other.doctorAssigned);
    }
    
    // Method to check if same patient type using 'this'
    public boolean isSamePatientType(HospitalManagementSystem other) {
        return other != null && this.patientType.equalsIgnoreCase(other.patientType);
    }
    
    // Method to check if higher bill using 'this'
    public boolean hasHigherBillThan(HospitalManagementSystem other) {
        return other != null && this.medicalBill > other.medicalBill;
    }
    
    // Static utility method to calculate default medical bill
    private static double calculateDefaultBill(String patientType) {
        switch (patientType.toLowerCase()) {
            case "outpatient": return 150.0;
            case "inpatient": return 500.0;
            case "emergency": return 800.0;
            case "icu": return 1200.0;
            default: return 300.0;
        }
    }
    
    // Static utility method to generate unique patient ID
    private static String generatePatientId() {
        return "PID" + String.format("%07d", totalPatientsAdmitted + 1);
    }
    
    // Static method to validate patient type
    public static boolean isValidPatientType(String patientType) {
        for (String validType : validPatientTypes) {
            if (validType.equalsIgnoreCase(patientType)) {
                return true;
            }
        }
        return false;
    }
    
    // Getter methods using 'this'
    public String getPatientName() {
        return this.patientName;
    }
    
    public String getPatientId() {
        return this.patientId; // final variable - read-only access
    }
    
    public String getAdmissionDate() {
        return this.admissionDate; // final variable - read-only access
    }
    
    public String getPatientType() {
        return this.patientType;
    }
    
    public String getDoctorAssigned() {
        return this.doctorAssigned;
    }
    
    public String getDiagnosis() {
        return this.diagnosis;
    }
    
    public double getMedicalBill() {
        return this.medicalBill;
    }
    
    public boolean isActive() {
        return this.isActive;
    }
    
    // Static getter methods
    public static int getTotalPatientsAdmitted() {
        return totalPatientsAdmitted;
    }
    
    public static double getTotalMedicalBills() {
        return totalMedicalBills;
    }
    
    public static String[] getValidPatientTypes() {
        return validPatientTypes.clone(); // Return copy to prevent modification
    }
    
    // Method to get patient summary using 'this'
    public String getPatientSummary() {
        return String.format("%s (ID: %s) - %s, Doctor: %s, Bill: $%.2f, Status: %s", 
                           this.patientName, this.patientId, this.patientType, this.doctorAssigned, 
                           this.medicalBill, (this.isActive ? "Active" : "Discharged"));
    }
    
    @Override
    public String toString() {
        return String.format("Patient{id='%s', name='%s', type='%s', doctor='%s'}", 
                           patientId, patientName, patientType, doctorAssigned);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        System.out.println("=== Hospital Management System Demo ===\\n");
        
        System.out.println("1. Initial hospital state:");
        System.out.println("Hospital: " + getHospitalName());
        displayHospitalStats();
        
        System.out.println("\\n2. Admitting patients (using 'this' in constructors):");
        HospitalManagementSystem patient1 = new HospitalManagementSystem("John Smith", "PID001", "Inpatient", "Dr. Wilson", "Pneumonia", 750.0);
        HospitalManagementSystem patient2 = new HospitalManagementSystem("Alice Johnson", "PID002", "Outpatient", "Dr. Brown", "Flu");
        HospitalManagementSystem patient3 = new HospitalManagementSystem("Bob Davis", "Emergency");
        
        System.out.println("\\n3. Hospital statistics after patient admissions:");
        displayHospitalStats();
        
        System.out.println("\\n4. Testing instanceof with valid patient:");
        processPatient(patient1);
        
        System.out.println("\\n5. Testing instanceof with invalid objects:");
        processPatient("Not a patient");
        processPatient(789);
        processPatient(null);
        
        System.out.println("\\n6. Patient management operations using 'this':");
        patient1.updateDoctorAssigned("Dr. Smith (Specialist)");
        patient2.updateDiagnosis("Viral Infection", 50.0);
        patient3.addTreatmentCharges("Emergency Surgery", 2000.0);
        patient1.addTreatmentCharges("X-Ray", 100.0);
        
        System.out.println("\\n7. Updated patient details:");
        processPatient(patient1);
        processPatient(patient2);
        
        System.out.println("\\n8. Testing final variables (patientId and admissionDate cannot be changed):");
        System.out.println("Patient1 ID: " + patient1.getPatientId());
        System.out.println("Patient1 Admission Date: " + patient1.getAdmissionDate());
        // patient1.patientId = "NEWPID001"; // This would cause compilation error
        // patient1.admissionDate = "2024-01-01"; // This would cause compilation error
        System.out.println("Note: Patient ID and Admission Date are final and cannot be modified");
        
        System.out.println("\\n9. Modifying static variable (affects all patients):");
        updateHospitalName("Metro Medical Center");
        
        System.out.println("\\n10. All patients now show updated hospital name:");
        processPatient(patient1);
        processPatient(patient3);
        
        System.out.println("\\n11. Patient comparisons using 'this':");
        HospitalManagementSystem patient4 = new HospitalManagementSystem("Carol Wilson", "PID004", "ICU", "Dr. Johnson", "Heart Surgery", 3000.0);
        System.out.println("Are patient1 and patient4 the same? " + patient1.isSamePatient(patient4));
        System.out.println("Do patient1 and patient2 have the same doctor? " + patient1.hasSameDoctor(patient2));
        System.out.println("Are patient1 and patient2 the same patient type? " + patient1.isSamePatientType(patient2));
        System.out.println("Does patient4 have higher bill than patient1? " + patient4.hasHigherBillThan(patient1));
        
        System.out.println("\\n12. Multiple instanceof checks with mixed objects:");
        Object[] objects = {patient1, patient2, "String", 123, patient3, null, new java.util.HashSet()};
        
        for (int i = 0; i < objects.length; i++) {
            System.out.printf("Object %d: ", i + 1);
            if (objects[i] instanceof HospitalManagementSystem) {
                HospitalManagementSystem patient = (HospitalManagementSystem) objects[i];
                System.out.println("Patient - " + patient.getPatientSummary());
            } else {
                System.out.println("Not a Patient - " + 
                                 (objects[i] != null ? objects[i].getClass().getSimpleName() : "null"));
            }
        }
        
        System.out.println("\\n13. Patient status and insurance coverage:");
        System.out.println("Patient1 follow-up needed: " + patient1.needsFollowUp());
        System.out.println("Patient1 stay duration: " + patient1.getStayDuration() + " days");
        System.out.printf("Patient1 insurance coverage (80%%): $%.2f%n", patient1.calculateInsuranceCoverage(80.0));
        patient2.updatePatientStatus(false); // Discharge patient
        patient2.displayPatientDetails();
        patient2.updatePatientStatus(true);  // Readmit patient
        
        System.out.println("\\n14. Patient type validation:");
        System.out.println("Valid patient types: " + String.join(", ", getValidPatientTypes()));
        System.out.println("Is 'Inpatient' valid? " + isValidPatientType("Inpatient"));
        System.out.println("Is 'Pediatric' valid? " + isValidPatientType("Pediatric"));
        
        System.out.println("\\n15. Final hospital statistics:");
        displayHospitalStats();
        
        System.out.println("\\n=== Concepts Demonstrated ===");
        System.out.println("✓ Static: hospitalName and totalPatientsAdmitted shared across all instances");
        System.out.println("✓ This: Used in constructors and methods to refer to current object");
        System.out.println("✓ Final: Patient ID and Admission Date cannot be changed once assigned");
        System.out.println("✓ Instanceof: Safe type checking before casting and operations");
    }
}
