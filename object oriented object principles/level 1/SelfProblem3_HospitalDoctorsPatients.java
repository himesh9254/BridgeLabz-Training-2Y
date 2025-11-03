import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

// Patient class
class Patient {
    private String name;
    private String patientId;
    private String ailment;
    private ArrayList<Doctor> consultedDoctors;
    
    public Patient(String name, String patientId, String ailment) {
        this.name = name;
        this.patientId = patientId;
        this.ailment = ailment;
        this.consultedDoctors = new ArrayList<>();
    }
    
    public void addConsultedDoctor(Doctor doctor) {
        if (!consultedDoctors.contains(doctor)) {
            consultedDoctors.add(doctor);
        }
    }
    
    public void viewConsultationHistory() {
        System.out.println("\n" + name + "'s consultation history:");
        for (Doctor doctor : consultedDoctors) {
            System.out.println("  - Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getPatientId() {
        return patientId;
    }
    
    public String getAilment() {
        return ailment;
    }
}

// Doctor class
class Doctor {
    private String name;
    private String doctorId;
    private String specialization;
    private ArrayList<Patient> patients;
    
    public Doctor(String name, String doctorId, String specialization) {
        this.name = name;
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.patients = new ArrayList<>();
    }
    
    // Communication method
    public void consult(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
        patient.addConsultedDoctor(this);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = sdf.format(new Date());
        
        System.out.println("\n--- Consultation ---");
        System.out.println("Time: " + timestamp);
        System.out.println("Doctor: Dr. " + name + " (" + specialization + ")");
        System.out.println("Patient: " + patient.getName() + " (ID: " + patient.getPatientId() + ")");
        System.out.println("Ailment: " + patient.getAilment());
        System.out.println("Status: Consultation in progress...");
        System.out.println("Diagnosis and prescription recorded.");
    }
    
    public void viewPatients() {
        System.out.println("\nDr. " + name + "'s patients:");
        for (Patient patient : patients) {
            System.out.println("  - " + patient.getName() + " (ID: " + patient.getPatientId() + ") - " + patient.getAilment());
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getDoctorId() {
        return doctorId;
    }
    
    public String getSpecialization() {
        return specialization;
    }
}

// Hospital class
class Hospital {
    private String hospitalName;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;
    
    public Hospital(String hospitalName) {
        this.hospitalName = hospitalName;
        this.doctors = new ArrayList<>();
        this.patients = new ArrayList<>();
    }
    
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Dr. " + doctor.getName() + " joined " + hospitalName);
    }
    
    public void registerPatient(Patient patient) {
        patients.add(patient);
        System.out.println(patient.getName() + " registered at " + hospitalName);
    }
    
    public void displayHospitalInfo() {
        System.out.println("\n=== " + hospitalName + " ===");
        System.out.println("Total Doctors: " + doctors.size());
        System.out.println("Total Patients: " + patients.size());
        
        System.out.println("\nDoctors:");
        for (Doctor doctor : doctors) {
            System.out.println("  - Dr. " + doctor.getName() + " (" + doctor.getSpecialization() + ")");
        }
        
        System.out.println("\nPatients:");
        for (Patient patient : patients) {
            System.out.println("  - " + patient.getName() + " - " + patient.getAilment());
        }
    }
    
    public String getHospitalName() {
        return hospitalName;
    }
}

// Main class
public class SelfProblem3_HospitalDoctorsPatients {
    public static void main(String[] args) {
        // Create hospital
        Hospital cityHospital = new Hospital("City General Hospital");
        
        // Create doctors
        Doctor doctor1 = new Doctor("James Wilson", "D001", "Cardiology");
        Doctor doctor2 = new Doctor("Lisa Cuddy", "D002", "Endocrinology");
        Doctor doctor3 = new Doctor("Gregory House", "D003", "Diagnostics");
        Doctor doctor4 = new Doctor("Allison Cameron", "D004", "Immunology");
        
        // Add doctors to hospital
        cityHospital.addDoctor(doctor1);
        cityHospital.addDoctor(doctor2);
        cityHospital.addDoctor(doctor3);
        cityHospital.addDoctor(doctor4);
        
        System.out.println();
        
        // Create patients
        Patient patient1 = new Patient("John Doe", "P001", "Chest Pain");
        Patient patient2 = new Patient("Jane Smith", "P002", "Diabetes");
        Patient patient3 = new Patient("Robert Brown", "P003", "Fever and Rash");
        Patient patient4 = new Patient("Emily Davis", "P004", "Thyroid Issues");
        
        // Register patients
        cityHospital.registerPatient(patient1);
        cityHospital.registerPatient(patient2);
        cityHospital.registerPatient(patient3);
        cityHospital.registerPatient(patient4);
        
        // Display hospital info
        cityHospital.displayHospitalInfo();
        
        // Demonstrate consultations (communication between doctors and patients)
        System.out.println("\n=== Consultations ===");
        
        doctor1.consult(patient1); // Cardiologist sees chest pain patient
        
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        doctor2.consult(patient2); // Endocrinologist sees diabetes patient
        
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        doctor2.consult(patient4); // Endocrinologist also sees thyroid patient
        
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        doctor3.consult(patient3); // Diagnostician sees fever patient
        
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        doctor4.consult(patient3); // Immunologist also sees fever patient (second opinion)
        
        try { Thread.sleep(100); } catch (InterruptedException e) {}
        doctor1.consult(patient3); // Cardiologist also checks fever patient
        
        // Show patient consultation history
        patient1.viewConsultationHistory();
        patient2.viewConsultationHistory();
        patient3.viewConsultationHistory();
        
        // Show doctor's patient list
        doctor1.viewPatients();
        doctor2.viewPatients();
        doctor3.viewPatients();
    }
}
