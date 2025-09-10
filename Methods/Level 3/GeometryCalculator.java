class GeometryCalculator {
    public static double calculateEuclideanDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    public static double[] findLineEquation(int x1, int y1, int x2, int y2) {
        double slope = (double)(y2 - y1) / (x2 - x1);
        double yIntercept = y1 - slope * x1;
        return new double[]{slope, yIntercept};
    }
    
    public static void main(String[] args) {
        int x1 = 1, y1 = 2;
        int x2 = 4, y2 = 6;
        
        double distance = calculateEuclideanDistance(x1, y1, x2, y2);
        double[] equation = findLineEquation(x1, y1, x2, y2);
        
        System.out.println("Euclidean distance: " + distance);
        System.out.println("Line equation: y = " + equation[0] + "x + " + equation[1]);
    }
}