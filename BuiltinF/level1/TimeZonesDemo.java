import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeZonesDemo {
    public static void main(String[] args) {
        // Create a DateTimeFormatter for better display
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        
        // Get current time in different time zones
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
        ZonedDateTime istTime = ZonedDateTime.now(ZoneId.of("Asia/Kolkata"));
        ZonedDateTime pstTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        
        System.out.println("Current Time in Different Time Zones:");
        System.out.println("=====================================");
        System.out.println("GMT (Greenwich Mean Time): " + gmtTime.format(formatter));
        System.out.println("IST (Indian Standard Time): " + istTime.format(formatter));
        System.out.println("PST (Pacific Standard Time): " + pstTime.format(formatter));
        
        // Alternative approach - converting from one zone to another
        System.out.println("\nAlternative approach - Converting from UTC:");
        System.out.println("==========================================");
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
        System.out.println("UTC Time: " + utcNow.format(formatter));
        System.out.println("GMT Time: " + utcNow.withZoneSameInstant(ZoneId.of("GMT")).format(formatter));
        System.out.println("IST Time: " + utcNow.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).format(formatter));
        System.out.println("PST Time: " + utcNow.withZoneSameInstant(ZoneId.of("America/Los_Angeles")).format(formatter));
    }
}
