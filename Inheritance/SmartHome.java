class Device {
    String deviceId;
    String status;

    Device(String deviceId, String status) {
        this.deviceId = deviceId;
        this.status = status;
    }

    void displayStatus() {
        System.out.println("Device ID: " + deviceId);
        System.out.println("Status: " + status);
    }
}

class Thermostat extends Device {
    double temperatureSetting;

    Thermostat(String deviceId, String status, double temperatureSetting) {
        super(deviceId, status);
        this.temperatureSetting = temperatureSetting;
    }

    @Override
    void displayStatus() {
        System.out.println("Device Type: Thermostat");
        super.displayStatus();
        System.out.println("Temperature Setting: " + temperatureSetting + "°C");
    }

    void adjustTemperature(double newTemperature) {
        temperatureSetting = newTemperature;
        System.out.println("Temperature adjusted to: " + temperatureSetting + "°C");
    }
}

public class SmartHome {
    public static void main(String[] args) {
        Device basicDevice = new Device("DEV001", "Active");
        Thermostat livingRoomThermostat = new Thermostat("THR001", "Active", 22.5);

        System.out.println("=== Smart Home System ===\n");
        
        System.out.println("Basic Device:");
        basicDevice.displayStatus();
        
        System.out.println("\nThermostat Device:");
        livingRoomThermostat.displayStatus();
        
        System.out.println("\nAdjusting temperature...");
        livingRoomThermostat.adjustTemperature(24.0);
        
        System.out.println("\nUpdated Thermostat Status:");
        livingRoomThermostat.displayStatus();
    }
}