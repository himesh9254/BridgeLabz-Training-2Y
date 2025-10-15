// 1. Data Serialization for Backup
// Mark certain classes as Serializable for backup storage
// Implement marker interface for backup processing

import java.io.*;

// Custom marker interface for backup
interface Backupable extends Serializable {
    // Marker interface - no methods
}

class UserProfile implements Backupable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private String email;
    private int age;
    
    public UserProfile(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "UserProfile{username='" + username + "', email='" + email + "', age=" + age + "}";
    }
}

class DatabaseConfig implements Backupable {
    private static final long serialVersionUID = 1L;
    
    private String host;
    private int port;
    private String database;
    
    public DatabaseConfig(String host, int port, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
    }
    
    @Override
    public String toString() {
        return "DatabaseConfig{host='" + host + "', port=" + port + ", database='" + database + "'}";
    }
}

class TemporaryData {
    // Not implementing Backupable - should not be backed up
    private String tempValue;
    
    public TemporaryData(String tempValue) {
        this.tempValue = tempValue;
    }
    
    @Override
    public String toString() {
        return "TemporaryData{tempValue='" + tempValue + "'}";
    }
}

class BackupSystem {
    public static void backupObject(Object obj, String filename) {
        if (obj instanceof Backupable) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(obj);
                System.out.println("✓ Backed up: " + obj.getClass().getSimpleName() + " to " + filename);
            } catch (IOException e) {
                System.err.println("✗ Backup failed: " + e.getMessage());
            }
        } else {
            System.out.println("✗ Cannot backup: " + obj.getClass().getSimpleName() + 
                             " (not marked as Backupable)");
        }
    }
    
    public static Object restoreObject(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object obj = ois.readObject();
            System.out.println("✓ Restored: " + obj.getClass().getSimpleName() + " from " + filename);
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✗ Restore failed: " + e.getMessage());
            return null;
        }
    }
}

public class DataSerialization {
    public static void main(String[] args) {
        System.out.println("=== Data Serialization & Backup System ===\n");
        
        // Create objects
        UserProfile user = new UserProfile("john_doe", "john@example.com", 30);
        DatabaseConfig dbConfig = new DatabaseConfig("localhost", 5432, "mydb");
        TemporaryData tempData = new TemporaryData("This should not be backed up");
        
        System.out.println("BACKING UP OBJECTS:");
        System.out.println("-".repeat(60));
        
        // Try to backup objects
        BackupSystem.backupObject(user, "user_backup.ser");
        BackupSystem.backupObject(dbConfig, "db_config_backup.ser");
        BackupSystem.backupObject(tempData, "temp_backup.ser"); // This should fail
        
        System.out.println("\nRESTORING OBJECTS:");
        System.out.println("-".repeat(60));
        
        // Restore backed up objects
        UserProfile restoredUser = (UserProfile) BackupSystem.restoreObject("user_backup.ser");
        DatabaseConfig restoredConfig = (DatabaseConfig) BackupSystem.restoreObject("db_config_backup.ser");
        
        System.out.println("\nRESTORED DATA:");
        System.out.println("-".repeat(60));
        if (restoredUser != null) {
            System.out.println("User: " + restoredUser);
        }
        if (restoredConfig != null) {
            System.out.println("DB Config: " + restoredConfig);
        }
        
        // Cleanup
        System.out.println("\nCLEANING UP BACKUP FILES:");
        System.out.println("-".repeat(60));
        new File("user_backup.ser").delete();
        new File("db_config_backup.ser").delete();
        System.out.println("✓ Cleanup completed");
    }
}
