import java.util.*;
import java.util.function.Predicate;

class Alert {
    private String type;
    private String severity;
    private String message;
    private String patientId;
    
    public Alert(String type, String severity, String message, String patientId) {
        this.type = type;
        this.severity = severity;
        this.message = message;
        this.patientId = patientId;
    }
    
    public String getType() { return type; }
    public String getSeverity() { return severity; }
    public String getMessage() { return message; }
    public String getPatientId() { return patientId; }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - Patient %s: %s", 
                           severity, type, patientId, message);
    }
}

public class NotificationFiltering {
    public static void main(String[] args) {
        List<Alert> alerts = Arrays.asList(
            new Alert("Vitals", "CRITICAL", "Heart rate exceeded 120 bpm", "P001"),
            new Alert("Medication", "WARNING", "Medication due in 30 minutes", "P002"),
            new Alert("Vitals", "INFO", "Blood pressure normal", "P003"),
            new Alert("Emergency", "CRITICAL", "Patient fell in room", "P004"),
            new Alert("Appointment", "INFO", "Appointment scheduled for tomorrow", "P005"),
            new Alert("Vitals", "WARNING", "Low oxygen saturation", "P001"),
            new Alert("Medication", "CRITICAL", "Missed medication dose", "P006"),
            new Alert("Lab", "INFO", "Lab results available", "P002"),
            new Alert("Emergency", "WARNING", "Call button pressed", "P007"),
            new Alert("Vitals", "CRITICAL", "Temperature spike detected", "P003")
        );
        
        System.out.println("=== Hospital Patient Alert System ===\n");
        
        System.out.println("ALL ALERTS (" + alerts.size() + " total):");
        System.out.println("=".repeat(80));
        alerts.forEach(System.out::println);
        
        Predicate<Alert> criticalOnly = alert -> alert.getSeverity().equals("CRITICAL");
        
        Predicate<Alert> vitalsAlerts = alert -> alert.getType().equals("Vitals");
        
        Predicate<Alert> emergencyOrCritical = alert -> 
            alert.getType().equals("Emergency") || alert.getSeverity().equals("CRITICAL");
        
        Predicate<Alert> medicationAlerts = alert -> alert.getType().equals("Medication");
        
        Predicate<Alert> nonInfoAlerts = alert -> !alert.getSeverity().equals("INFO");
        
        Predicate<Alert> patientP001 = alert -> alert.getPatientId().equals("P001");
        
        System.out.println("\n\nUSER PREFERENCE 1: Critical Alerts Only");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, criticalOnly);
        
        System.out.println("\n\nUSER PREFERENCE 2: Vitals Monitoring");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, vitalsAlerts);
        
        System.out.println("\n\nUSER PREFERENCE 3: Emergency & Critical");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, emergencyOrCritical);
        
        System.out.println("\n\nUSER PREFERENCE 4: Medication Reminders");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, medicationAlerts);
        
        System.out.println("\n\nUSER PREFERENCE 5: Warnings & Critical Only");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, nonInfoAlerts);
        
        System.out.println("\n\nUSER PREFERENCE 6: Specific Patient (P001)");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, patientP001);
        
        System.out.println("\n\nUSER PREFERENCE 7: Critical Vitals (Combined Filter)");
        System.out.println("=".repeat(80));
        filterAndDisplay(alerts, criticalOnly.and(vitalsAlerts));
        
        System.out.println("\n\nUSER PREFERENCE 8: All Except Info (Negation)");
        System.out.println("=".repeat(80));
        Predicate<Alert> infoAlerts = alert -> alert.getSeverity().equals("INFO");
        filterAndDisplay(alerts, infoAlerts.negate());
    }
    
    private static void filterAndDisplay(List<Alert> alerts, Predicate<Alert> filter) {
        List<Alert> filtered = new ArrayList<>();
        for (Alert alert : alerts) {
            if (filter.test(alert)) {
                filtered.add(alert);
            }
        }
        
        if (filtered.isEmpty()) {
            System.out.println("No alerts match this filter.");
        } else {
            System.out.println("Found " + filtered.size() + " alert(s):");
            filtered.forEach(alert -> System.out.println("  " + alert));
        }
    }
}
