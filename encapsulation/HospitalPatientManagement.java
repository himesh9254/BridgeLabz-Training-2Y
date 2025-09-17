/**
 * Hospital Patient Management System demonstrating:
 * Abstract classes, interfaces, encapsulation, inheritance, polymorphism
 */

import java.util.*;
import java.time.LocalDate;

interface MedicalRecord {
    void addRecord(String diagnosis, String treatment);
    List<String> viewRecords();
}

abstract class Patient {
    private String patientId;
    private String name;
    private int age;
    private String gender;
    private LocalDate admissionDate;
    private List<String> medicalHistory;
    private String diagnosis;
    private boolean isActive;
    
    public Patient(String patientId, String name, int age, String gender) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.admissionDate = LocalDate.now();
        this.medicalHistory = new ArrayList<>();
        this.isActive = true;
    }
    
    public abstract double calculateBill();
    
    public void getPatientDetails() {
        System.out.println("=== Patient Details ===");
        System.out.println("ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("Admission: " + admissionDate);
        System.out.println("Bill: $" + calculateBill());
        System.out.println("Status: " + (isActive ? "Active" : "Discharged"));
        System.out.println("=======================");
    }
    
    // Encapsulated getters
    public String getPatientId() { return patientId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public boolean isActive() { return isActive; }
    
    protected void addToMedicalHistory(String record) {
        medicalHistory.add(LocalDate.now() + ": " + record);
    }
    
    protected List<String> getMedicalHistory() {
        return new ArrayList<>(medicalHistory); // Return copy for security
    }
    
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
        addToMedicalHistory("Diagnosis: " + diagnosis);
    }
    
    public void discharge() {
        this.isActive = false;
        addToMedicalHistory("Patient discharged");
    }
}

class InPatient extends Patient implements MedicalRecord {
    private int numberOfDays;
    private double dailyRate = 150.0;
    private double medicationCost = 0.0;
    private List<String> treatments;
    
    public InPatient(String patientId, String name, int age, String gender, int numberOfDays) {
        super(patientId, name, age, gender);
        this.numberOfDays = numberOfDays;
        this.treatments = new ArrayList<>();
    }
    
    @Override
    public double calculateBill() {
        double roomCharges = numberOfDays * dailyRate;
        double treatmentCost = treatments.size() * 50.0; // $50 per treatment
        return roomCharges + medicationCost + treatmentCost + 200.0; // Base hospital charges
    }
    
    @Override
    public void addRecord(String diagnosis, String treatment) {
        setDiagnosis(diagnosis);
        treatments.add(treatment);
        addToMedicalHistory("Treatment: " + treatment);
        System.out.println("Medical record added for " + getName());
    }
    
    @Override
    public List<String> viewRecords() {
        List<String> allRecords = new ArrayList<>(getMedicalHistory());
        allRecords.addAll(treatments);
        return allRecords;
    }
    
    public void addMedicationCost(double cost) {
        this.medicationCost += cost;
        addToMedicalHistory("Medication cost added: $" + cost);
    }
    
    public int getNumberOfDays() { return numberOfDays; }
    public void setNumberOfDays(int days) { 
        if (days > 0) {
            this.numberOfDays = days; 
            addToMedicalHistory("Stay extended to " + days + " days");
        }
    }
}

class OutPatient extends Patient implements MedicalRecord {
    private int numberOfVisits;
    private double consultationFee = 75.0;
    private List<String> prescriptions;
    
    public OutPatient(String patientId, String name, int age, String gender) {
        super(patientId, name, age, gender);
        this.numberOfVisits = 1;
        this.prescriptions = new ArrayList<>();
    }
    
    @Override
    public double calculateBill() {
        double consultationCost = numberOfVisits * consultationFee;
        double prescriptionCost = prescriptions.size() * 25.0; // $25 per prescription
        return consultationCost + prescriptionCost + 50.0; // Base consultation charges
    }
    
    @Override
    public void addRecord(String diagnosis, String treatment) {
        setDiagnosis(diagnosis);
        prescriptions.add(treatment);
        addToMedicalHistory("Prescription: " + treatment);
        System.out.println("Prescription added for " + getName());
    }
    
    @Override
    public List<String> viewRecords() {
        List<String> allRecords = new ArrayList<>(getMedicalHistory());
        allRecords.addAll(prescriptions);
        return allRecords;
    }
    
    public void addVisit() {
        this.numberOfVisits++;
        addToMedicalHistory("Follow-up visit #" + numberOfVisits);
    }
    
    public int getNumberOfVisits() { return numberOfVisits; }
}

public class HospitalPatientManagement {
    private List<Patient> patients;
    private static int patientCounter = 1;
    
    public HospitalPatientManagement() {
        this.patients = new ArrayList<>();
    }
    
    public void admitPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient admitted: " + patient.getName());
    }
    
    public void displayAllPatients() {
        System.out.println("\n=== ALL PATIENTS ===");
        for (Patient patient : patients) {
            patient.getPatientDetails(); // Polymorphic call
            System.out.println();
        }
    }
    
    public void processMedicalRecords() {
        System.out.println("\n=== MEDICAL RECORDS PROCESSING ===");
        for (Patient patient : patients) {
            if (patient instanceof MedicalRecord) {
                MedicalRecord record = (MedicalRecord) patient;
                System.out.println("Records for " + patient.getName() + ":");
                record.viewRecords().forEach(r -> System.out.println("  - " + r));
            }
        }
        System.out.println("==================================");
    }
    
    public void calculateTotalBilling() {
        double total = 0;
        for (Patient patient : patients) {
            total += patient.calculateBill();
        }
        System.out.println("Total Hospital Revenue: $" + total);
    }
    
    public static String generatePatientId() {
        return "P" + String.format("%04d", patientCounter++);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Hospital Patient Management Demo ===\n");
        
        HospitalPatientManagement hospital = new HospitalPatientManagement();
        
        InPatient inpatient = new InPatient(generatePatientId(), "Alice Johnson", 45, "Female", 5);
        OutPatient outpatient = new OutPatient(generatePatientId(), "Bob Smith", 30, "Male");
        
        hospital.admitPatient(inpatient);
        hospital.admitPatient(outpatient);
        
        // Add medical records
        inpatient.addRecord("Pneumonia", "Antibiotics and Rest");
        inpatient.addMedicationCost(150.0);
        
        outpatient.addRecord("Common Cold", "Paracetamol and Rest");
        outpatient.addVisit();
        
        hospital.displayAllPatients();
        hospital.processMedicalRecords();
        hospital.calculateTotalBilling();
        
        System.out.println("\n=== Concepts Demonstrated ===");
        System.out.println("✓ Abstract Classes: Patient with abstract calculateBill()");
        System.out.println("✓ Inheritance: InPatient and OutPatient extend Patient");
        System.out.println("✓ Polymorphism: Different billing calculations");
        System.out.println("✓ Interface: MedicalRecord for secure patient data");
        System.out.println("✓ Encapsulation: Protected medical history and sensitive data");
    }
}
