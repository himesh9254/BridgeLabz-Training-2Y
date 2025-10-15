// 1. Smart Device Control Interface
// All devices (lights, AC, TV) should have turnOn() and turnOff() methods

interface SmartDevice {
    void turnOn();
    void turnOff();
}

class Light implements SmartDevice {
    private String location;
    
    public Light(String location) {
        this.location = location;
    }
    
    @Override
    public void turnOn() {
        System.out.println(location + " Light is now ON");
    }
    
    @Override
    public void turnOff() {
        System.out.println(location + " Light is now OFF");
    }
}

class AirConditioner implements SmartDevice {
    private int temperature;
    
    public AirConditioner(int temperature) {
        this.temperature = temperature;
    }
    
    @Override
    public void turnOn() {
        System.out.println("AC is now ON at " + temperature + "°C");
    }
    
    @Override
    public void turnOff() {
        System.out.println("AC is now OFF");
    }
}

class Television implements SmartDevice {
    private String brand;
    
    public Television(String brand) {
        this.brand = brand;
    }
    
    @Override
    public void turnOn() {
        System.out.println(brand + " TV is now ON");
    }
    
    @Override
    public void turnOff() {
        System.out.println(brand + " TV is now OFF");
    }
}

public class SmartDeviceControl {
    public static void main(String[] args) {
        SmartDevice light = new Light("Living Room");
        SmartDevice ac = new AirConditioner(24);
        SmartDevice tv = new Television("Samsung");
        
        light.turnOn();
        ac.turnOn();
        tv.turnOn();
        
        System.out.println();
        
        light.turnOff();
        ac.turnOff();
        tv.turnOff();
    }
}
