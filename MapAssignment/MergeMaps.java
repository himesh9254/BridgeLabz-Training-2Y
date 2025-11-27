import java.util.*;

public class MergeMaps {
    public static Map<String, Integer> mergeMapsWithSum(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> mergedMap = new HashMap<>(map1);
        
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            mergedMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
        
        return mergedMap;
    }
    
    public static <K> Map<K, Integer> mergeMapsGeneric(Map<K, Integer> map1, Map<K, Integer> map2) {
        Map<K, Integer> mergedMap = new HashMap<>(map1);
        
        map2.forEach((key, value) -> 
            mergedMap.merge(key, value, (v1, v2) -> v1 + v2));
        
        return mergedMap;
    }
    
    public static <K, V> Map<K, V> mergeMapsOverwrite(Map<K, V> map1, Map<K, V> map2) {
        Map<K, V> mergedMap = new HashMap<>(map1);
        mergedMap.putAll(map2);
        return mergedMap;
    }
    
    public static void main(String[] args) {
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("A", 1);
        map1.put("B", 2);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("B", 3);
        map2.put("C", 4);
        
        System.out.println("Map1: " + map1);
        System.out.println("Map2: " + map2);
        System.out.println("Merged (sum values): " + mergeMapsWithSum(map1, map2));
        
        Map<String, Integer> scores1 = new HashMap<>();
        scores1.put("Alice", 85);
        scores1.put("Bob", 90);
        scores1.put("Charlie", 78);
        
        Map<String, Integer> scores2 = new HashMap<>();
        scores2.put("Bob", 88);
        scores2.put("Diana", 92);
        scores2.put("Alice", 80);
        
        System.out.println("\nScores Map1: " + scores1);
        System.out.println("Scores Map2: " + scores2);
        System.out.println("Merged (sum scores): " + mergeMapsGeneric(scores1, scores2));
        
        Map<String, Integer> inventory1 = new HashMap<>();
        inventory1.put("Apples", 50);
        inventory1.put("Oranges", 30);
        inventory1.put("Bananas", 40);
        
        Map<String, Integer> inventory2 = new HashMap<>();
        inventory2.put("Bananas", 20);
        inventory2.put("Grapes", 25);
        inventory2.put("Apples", 15);
        
        System.out.println("\nInventory1: " + inventory1);
        System.out.println("Inventory2: " + inventory2);
        System.out.println("Merged Inventory (sum): " + mergeMapsWithSum(inventory1, inventory2));
        
        Map<String, String> config1 = new HashMap<>();
        config1.put("host", "localhost");
        config1.put("port", "8080");
        
        Map<String, String> config2 = new HashMap<>();
        config2.put("port", "9090");
        config2.put("timeout", "30");
        
        System.out.println("\nConfig1: " + config1);
        System.out.println("Config2: " + config2);
        System.out.println("Merged Config (overwrite): " + mergeMapsOverwrite(config1, config2));
    }
}
