// 3. Sensitive Data Tagging
// Mark sensitive data classes for encryption
// Create a custom marker interface

interface SensitiveData {
    // Custom marker interface for sensitive data
    // No methods - just marks the class as containing sensitive information
}

class CreditCardInfo implements SensitiveData {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardholderName;
    
    public CreditCardInfo(String cardNumber, String cvv, String expiryDate, String cardholderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardholderName = cardholderName;
    }
    
    @Override
    public String toString() {
        return "CreditCardInfo{cardNumber='****" + cardNumber.substring(cardNumber.length() - 4) + 
               "', cardholderName='" + cardholderName + "'}";
    }
    
    public String getRawData() {
        return "Card:" + cardNumber + ",CVV:" + cvv + ",Expiry:" + expiryDate + ",Name:" + cardholderName;
    }
}

class MedicalRecord implements SensitiveData {
    private String patientId;
    private String diagnosis;
    private String medications;
    private String socialSecurityNumber;
    
    public MedicalRecord(String patientId, String diagnosis, String medications, String ssn) {
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.medications = medications;
        this.socialSecurityNumber = ssn;
    }
    
    @Override
    public String toString() {
        return "MedicalRecord{patientId='" + patientId + "', diagnosis='" + diagnosis + "'}";
    }
    
    public String getRawData() {
        return "Patient:" + patientId + ",Diagnosis:" + diagnosis + 
               ",Medications:" + medications + ",SSN:" + socialSecurityNumber;
    }
}

class UserCredentials implements SensitiveData {
    private String username;
    private String password;
    private String apiKey;
    
    public UserCredentials(String username, String password, String apiKey) {
        this.username = username;
        this.password = password;
        this.apiKey = apiKey;
    }
    
    @Override
    public String toString() {
        return "UserCredentials{username='" + username + "'}";
    }
    
    public String getRawData() {
        return "Username:" + username + ",Password:" + password + ",ApiKey:" + apiKey;
    }
}

class PublicProfile {
    // Not marked as SensitiveData - can be stored without encryption
    private String name;
    private String bio;
    private String website;
    
    public PublicProfile(String name, String bio, String website) {
        this.name = name;
        this.bio = bio;
        this.website = website;
    }
    
    @Override
    public String toString() {
        return "PublicProfile{name='" + name + "', bio='" + bio + "', website='" + website + "'}";
    }
    
    public String getRawData() {
        return "Name:" + name + ",Bio:" + bio + ",Website:" + website;
    }
}

class EncryptionService {
    // Simple encryption simulation (NOT for production use)
    private static String simpleEncrypt(String data) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : data.toCharArray()) {
            encrypted.append((char)(c + 3));
        }
        return encrypted.toString();
    }
    
    private static String simpleDecrypt(String encrypted) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            decrypted.append((char)(c - 3));
        }
        return decrypted.toString();
    }
    
    public static String processData(Object obj) {
        if (obj instanceof SensitiveData) {
            System.out.println("🔒 SENSITIVE DATA DETECTED: Applying encryption");
            
            try {
                String rawData = (String) obj.getClass().getMethod("getRawData").invoke(obj);
                String encrypted = simpleEncrypt(rawData);
                System.out.println("   Original: " + rawData);
                System.out.println("   Encrypted: " + encrypted);
                return encrypted;
            } catch (Exception e) {
                System.err.println("   Encryption failed: " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("📂 PUBLIC DATA: No encryption needed");
            try {
                String rawData = (String) obj.getClass().getMethod("getRawData").invoke(obj);
                System.out.println("   Data: " + rawData);
                return rawData;
            } catch (Exception e) {
                return obj.toString();
            }
        }
    }
}

public class SensitiveDataTagging {
    public static void main(String[] args) {
        System.out.println("=== Sensitive Data Encryption System ===\n");
        
        // Create various data objects
        CreditCardInfo creditCard = new CreditCardInfo("1234567890123456", "123", "12/25", "John Doe");
        MedicalRecord medicalRecord = new MedicalRecord("P001", "Diabetes", "Insulin", "123-45-6789");
        UserCredentials credentials = new UserCredentials("admin", "SecurePass123!", "api_key_xyz");
        PublicProfile profile = new PublicProfile("John Doe", "Software Developer", "www.johndoe.com");
        
        System.out.println("PROCESSING DATA OBJECTS:");
        System.out.println("=".repeat(80));
        
        System.out.println("\n1. Credit Card Information:");
        System.out.println("-".repeat(80));
        EncryptionService.processData(creditCard);
        
        System.out.println("\n2. Medical Record:");
        System.out.println("-".repeat(80));
        EncryptionService.processData(medicalRecord);
        
        System.out.println("\n3. User Credentials:");
        System.out.println("-".repeat(80));
        EncryptionService.processData(credentials);
        
        System.out.println("\n4. Public Profile:");
        System.out.println("-".repeat(80));
        EncryptionService.processData(profile);
        
        // Summary
        System.out.println("\n" + "=".repeat(80));
        System.out.println("SUMMARY:");
        System.out.println("-".repeat(80));
        System.out.println("✓ Sensitive data (marked with SensitiveData interface) is encrypted");
        System.out.println("✓ Public data is stored without encryption");
        System.out.println("✓ System automatically detects and processes data based on marker interface");
    }
}
