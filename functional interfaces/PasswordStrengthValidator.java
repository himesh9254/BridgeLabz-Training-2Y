// 1. Password Strength Validator
// In an insurance portal, password policy rules are centrally defined
// Static method in interface to check password strength

interface SecurityUtils {
    static boolean isStrongPassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) >= 0) hasSpecial = true;
        }
        
        return hasUpper && hasLower && hasDigit && hasSpecial;
    }
    
    static String getPasswordStrength(String password) {
        if (password == null || password.length() < 6) {
            return "Weak";
        } else if (password.length() < 8) {
            return "Moderate";
        } else if (isStrongPassword(password)) {
            return "Strong";
        } else {
            return "Moderate";
        }
    }
    
    static String getPasswordRequirements() {
        return "Password must:\n" +
               "  - Be at least 8 characters long\n" +
               "  - Contain uppercase letter\n" +
               "  - Contain lowercase letter\n" +
               "  - Contain digit\n" +
               "  - Contain special character (!@#$%^&* etc.)";
    }
}

public class PasswordStrengthValidator {
    public static void main(String[] args) {
        System.out.println("=== Insurance Portal - Password Validator ===\n");
        System.out.println(SecurityUtils.getPasswordRequirements());
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Test passwords
        String[] passwords = {
            "weak",
            "Password",
            "password123",
            "Pass@123",
            "StrongP@ssw0rd",
            "Test@1234",
            "abc"
        };
        
        for (String password : passwords) {
            String strength = SecurityUtils.getPasswordStrength(password);
            boolean isStrong = SecurityUtils.isStrongPassword(password);
            
            System.out.println("Password: \"" + password + "\"");
            System.out.println("Strength: " + strength);
            System.out.println("Meets requirements: " + (isStrong ? "✓ Yes" : "✗ No"));
            System.out.println("-".repeat(50));
        }
    }
}
