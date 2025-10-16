interface LightController {
    void activate();
}

class SmartLight {
    private String location;
    private int brightness;
    
    public SmartLight(String location) {
        this.location = location;
        this.brightness = 0;
    }
    
    public void setBrightness(int level) {
        this.brightness = level;
        System.out.println(location + " light set to " + level + "%");
    }
    
    public void setColor(String color) {
        System.out.println(location + " light color changed to " + color);
    }
    
    public void blink() {
        System.out.println(location + " light blinking");
    }
}

public class SmartHomeLighting {
    public static void main(String[] args) {
        SmartLight livingRoom = new SmartLight("Living Room");
        SmartLight bedroom = new SmartLight("Bedroom");
        SmartLight kitchen = new SmartLight("Kitchen");
        
        System.out.println("=== Smart Home Lighting Automation ===\n");
        
        LightController motionTrigger = () -> {
            livingRoom.setBrightness(80);
            livingRoom.setColor("White");
        };
        
        LightController morningRoutine = () -> {
            bedroom.setBrightness(30);
            bedroom.setColor("Warm White");
            kitchen.setBrightness(100);
            kitchen.setColor("Cool White");
        };
        
        LightController eveningRoutine = () -> {
            livingRoom.setBrightness(50);
            livingRoom.setColor("Warm Orange");
            bedroom.setBrightness(20);
            bedroom.setColor("Dim Yellow");
        };
        
        LightController nightMode = () -> {
            livingRoom.setBrightness(10);
            bedroom.setBrightness(5);
            kitchen.setBrightness(0);
        };
        
        LightController partyMode = () -> {
            livingRoom.setBrightness(100);
            livingRoom.setColor("RGB Cycle");
            livingRoom.blink();
        };
        
        LightController voiceCommand = () -> {
            System.out.println("Voice command: 'Turn on all lights'");
            livingRoom.setBrightness(100);
            bedroom.setBrightness(100);
            kitchen.setBrightness(100);
        };
        
        System.out.println("TRIGGER: Motion Detected");
        System.out.println("-".repeat(50));
        motionTrigger.activate();
        
        System.out.println("\nTRIGGER: Morning Routine (7:00 AM)");
        System.out.println("-".repeat(50));
        morningRoutine.activate();
        
        System.out.println("\nTRIGGER: Evening Routine (6:00 PM)");
        System.out.println("-".repeat(50));
        eveningRoutine.activate();
        
        System.out.println("\nTRIGGER: Night Mode (10:00 PM)");
        System.out.println("-".repeat(50));
        nightMode.activate();
        
        System.out.println("\nTRIGGER: Party Mode");
        System.out.println("-".repeat(50));
        partyMode.activate();
        
        System.out.println("\nTRIGGER: Voice Command");
        System.out.println("-".repeat(50));
        voiceCommand.activate();
    }
}
