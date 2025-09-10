class FootballTeamHeights {
    public int[] generateHeights() {
        int[] heights = new int[11];
        for (int i = 0; i < 11; i++) {
            heights[i] = (int) (Math.random() * 101) + 150;
        }
        return heights;
    }
    
    public int sumArray(int[] heights) {
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            sum += heights[i];
        }
        return sum;
    }
    
    public double calculateMean(int[] heights) {
        return (double) sumArray(heights) / heights.length;
    }
    
    public int findShortest(int[] heights) {
        int shortest = heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] < shortest) {
                shortest = heights[i];
            }
        }
        return shortest;
    }
    
    public int findTallest(int[] heights) {
        int tallest = heights[0];
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] > tallest) {
                tallest = heights[i];
            }
        }
        return tallest;
    }
    
    public static void main(String[] args) {
        FootballTeamHeights team = new FootballTeamHeights();
        int[] heights = team.generateHeights();
        
        double mean = team.calculateMean(heights);
        int shortest = team.findShortest(heights);
        int tallest = team.findTallest(heights);
        
        System.out.println("Mean height: " + mean);
        System.out.println("Shortest height: " + shortest);
        System.out.println("Tallest height: " + tallest);
    }
}