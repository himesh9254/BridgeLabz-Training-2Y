class OtpGenerator {
    public static int generateOtp() {
        return (int) (Math.random() * 900000) + 100000;
    }
    
    public static int[] generateMultipleOtps() {
        int[] otps = new int[10];
        for (int i = 0; i < 10; i++) {
            otps[i] = generateOtp();
        }
        return otps;
    }
    
    public static boolean areOtpsUnique(int[] otps) {
        for (int i = 0; i < otps.length; i++) {
            for (int j = i + 1; j < otps.length; j++) {
                if (otps[i] == otps[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        int[] otps = generateMultipleOtps();
        
        System.out.println("Generated OTPs:");
        for (int otp : otps) {
            System.out.println(otp);
        }
        
        System.out.println("All OTPs unique: " + areOtpsUnique(otps));
    }
}